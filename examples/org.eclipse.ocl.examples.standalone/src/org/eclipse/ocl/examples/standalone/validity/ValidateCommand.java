/*******************************************************************************
 * Copyright (c) 2014, 2021 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.standalone.validity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.emf.validation.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.validity.export.IValidityExporter;
import org.eclipse.ocl.examples.emf.validation.validity.export.IValidityExporterDescriptor;
import org.eclipse.ocl.examples.emf.validation.validity.export.ValidityExporterRegistry;
import org.eclipse.ocl.examples.standalone.StandaloneApplication;
import org.eclipse.ocl.examples.standalone.StandaloneCommand;
import org.eclipse.ocl.examples.standalone.StandaloneResponse;
import org.eclipse.ocl.examples.standalone.messages.StandaloneMessages;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLLoader;

/**
 * The ValidateCommand provides model validation.
 */
public class ValidateCommand extends StandaloneCommand
{
	private static final Logger logger = Logger.getLogger(ValidateCommand.class);

	protected static final class ExporterComparator implements Comparator<IValidityExporterDescriptor>
	{
		public static final @NonNull ExporterComparator INSTANCE = new ExporterComparator();

		@Override
		public int compare(IValidityExporterDescriptor o1, IValidityExporterDescriptor o2) {
			String n1 = o1.getExporterType();
			String n2 = o2.getExporterType();
			return n1.compareTo(n2);
		}
	}

	/**
	 * An optional argument to specify which exporter should be used. By
	 * default, the 'txt' exporter will be used, exporting a textual report of
	 * the validation.
	 */
	public static class ExporterToken extends StringToken
	{
		private @Nullable IValidityExporter exporter;

		public ExporterToken(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication, "-exporter", StandaloneMessages.ValidateCommand_Exporter_Help, "<later>");
		}

		@Override
		public @Nullable String getArgumentsHelp() {
			List<IValidityExporterDescriptor> exporters = new ArrayList<IValidityExporterDescriptor>(ValidityExporterRegistry.INSTANCE.getRegisteredExtensions());
			Collections.sort(exporters, ExporterComparator.INSTANCE);
			StringBuilder s = new StringBuilder();
			for (IValidityExporterDescriptor exporter : exporters) {
				if (s.length() > 0) {
					s.append("|");
				}
				s.append(exporter.getExporterType());
			}
			return s.toString();
		}

		public @Nullable IValidityExporter getExporter() {
			return exporter;
		}

		@Override
		public int getMaxArguments() {
			return 1;
		}

		@Override
		public boolean parseCheck(@NonNull String string) {
			exporter = ValidityExporterRegistry.INSTANCE.getExporter(string);;
			if (exporter == null) {
				logger.error("Unrecognized 'exporter' " + string);
				return false;
			}
			return true;
		}
	}

	/**
	 * A mandatory argument key of the model file path. This argument key must
	 * be followed by the model file path.
	 */
	public static class ModelToken extends StringToken
	{
		private @Nullable String fileName;

		public ModelToken(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication, "-model", StandaloneMessages.ValidateCommand_Model_Help, "<file-name>");
		}

		@Override
		public boolean analyze(@Nullable String string) {
			URI uri = URI.createURI(string);
			fileName = uri.isFile() ? uri.toFileString() : string;
			boolean exists = standaloneApplication.getURIConverter().exists(uri, null);
			if (!exists) {
				logger.error(StandaloneMessages.OCLArgumentAnalyzer_ModelFile + uri + StandaloneMessages.OCLArgumentAnalyzer_NotExist);
				return false;
			}
			return true;
		}

		public @Nullable String getModelFileName() {
			return fileName;
		}
	}


	/**
	 * A mandatory argument used to define the paths to the OCL documents
	 * containing the constraints to evaluate. Users can specify one or several
	 * OCL Documents paths in the command line, separated with a whitespace. A
	 * text file containing a list of OCL Documents paths can be used instead,
	 * in which case all OCL constraints defined in all of these documents will
	 * be evaluated sequentially.
	 */
	public static class RulesToken extends CommandToken
	{
		/** Possible "text" extension file for the "-rules" argument entry. */
		private static final Object TEXT_FILE_EXTENSION = "txt"; //$NON-NLS-1$
		/** Possible "ocl" extension file for the "-rules" argument entry. */
		private static final Object OCL_FILE_EXTENSION = "ocl"; //$NON-NLS-1$

		private @Nullable List<@NonNull String> oclFileNames;

		public RulesToken(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication, "-rules", StandaloneMessages.ValidateCommand_Rules_Help, "<file-name>");
		}

		@Override
		public boolean analyze(@Nullable String string) {
			URI uri = URI.createURI(string);
			URIConverter uriConverter = standaloneApplication.getURIConverter();
			List<@NonNull String> strings = new ArrayList<>();
			checkOclFile(uriConverter, strings, uri.toString());
			List<@NonNull String> oclFileNames2 = oclFileNames;
			if (oclFileNames2 == null) {
				oclFileNames = oclFileNames2 = new ArrayList<>();
			}
			oclFileNames2.addAll(strings);
			return true;
		}

		/**
		 * Checks consistency of the ocl file passed to the command line.
		 *
		 * @param argument
		 *            is the path to the relative/absolute path to the resource
		 * @return <code>true</code> if the model exists and is a file,
		 *         <code>false</code> otherwise.
		 */
		private void checkOclFile(@NonNull URIConverter uriConverter, @NonNull List<String> strings, @NonNull String argument) {
			URI uri = URI.createURI(argument);
			argument = uri.isFile() ? uri.toFileString() : argument;
			boolean ignored = false;
			boolean exists = uriConverter.exists(uri, null);
			// a txt file may contain relative or absolute path to a set of OCL files.
			String fileExtension = uri.fileExtension(); //path.getFileExtension();
			if (TEXT_FILE_EXTENSION.equals(fileExtension.toLowerCase())) {
				extractOCLUris(uriConverter, strings, uri);
			} else if (OCL_FILE_EXTENSION.equals(fileExtension.toLowerCase())) {
				if (!exists) {
					logger.warn(StandaloneMessages.OCLArgumentAnalyzer_OCLResource + " " + uri + StandaloneMessages.OCLArgumentAnalyzer_NotExist);
					ignored = true;
				} else {
					strings.add(uri.toString());
				}
			} else {
				logger.warn(StandaloneMessages.OCLArgumentAnalyzer_FileExt
						+ uri.lastSegment()
						+ StandaloneMessages.OCLArgumentAnalyzer_ExtensionPb);
				ignored = true;
			}

			if (ignored) {
				logger.warn(StandaloneMessages.OCLArgumentAnalyzer_OCLFile + " " + uri + StandaloneMessages.OCLArgumentAnalyzer_ignored);
			}
		}

		/**
		 * Extracts information contained in the text file.
		 * @param uriConverter
		 *
		 * @param txtFile
		 *            The file containing relative path to OCL files.
		 */
		private void extractOCLUris(@NonNull URIConverter uriConverter, @NonNull List<@NonNull String> strings, @NonNull URI txtURI) {
			try {
				InputStream inputStream = uriConverter.createInputStream(txtURI);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				String line = reader.readLine();
				while (line != null) {
					URI childURI = URI.createURI(line).resolve(txtURI);
					checkOclFile(uriConverter, strings, childURI.toString());
					line = reader.readLine();
				}
				reader.close();
			} catch (FileNotFoundException e) {
				logger.error(MessageFormat.format(StandaloneMessages.OCLArgumentAnalyzer_OCLFileNotFound, txtURI));
			} catch (IOException e) {
				logger.warn(e.getMessage());
			}
		}

		/**
		 * Gets the collection of OCL resources deduced from values specified after
		 * the <b>-rule</b> argument.
		 *
		 * @return A List of OCL Uris
		 */
		public @NonNull List<String> getOCLFileNames() {
			return oclFileNames != null ? oclFileNames : Collections.emptyList();
		}
	}


	/**
	 * An optional argument used if the user wishes to run all constraints or to
	 * only run the OCL, Java or UML constraints validation. Otherwise, all
	 * constraints will be checked against the input model.
	 */
	public static class UsingToken extends StringToken
	{
		/** "-using" argument value to run the all constraints (ocl, java and uml). */
		private static final @NonNull String ALL_LOCATORS = "all"; //$NON-NLS-1$
		/** "-using" argument value to additionally run the OCL constraints. */
		private static final @NonNull String OCL_LOCATOR = "ocl"; //$NON-NLS-1$
		/** "-using" argument value to additionally run the Java constraints. */
		private static final @NonNull String JAVA_LOCATOR = "java"; //$NON-NLS-1$
		/** "-using" argument value to additionally run the UML constraints. */
		private static final @NonNull String UML_LOCATOR = "uml"; //$NON-NLS-1$

		private boolean doJava = false;
		private boolean doOCL = false;
		private boolean doUML = false;

		public UsingToken(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication, "-using", StandaloneMessages.ValidateCommand_Using_Help, ALL_LOCATORS + "|" + JAVA_LOCATOR + "|" + OCL_LOCATOR + "|" + UML_LOCATOR);
		}

		public boolean doRunJavaConstraints() {
			return doJava || (!doJava && !doOCL && !doUML);
		}

		public boolean doRunOCLConstraints() {
			return doOCL || (!doJava && !doOCL && !doUML);
		}

		public boolean doRunUMLConstraints() {
			return doUML || (!doJava && !doOCL && !doUML);
		}

		@Override
		public int getMaxArguments() {
			return -1;
		}

		@Override
		public boolean isSingleton() {
			return false;
		}

		@Override
		public boolean parseCheck(@NonNull String string) {
			boolean okLocators = true;
			String[] locators = string.split(",");
			for (String locator : locators) {
				if (ALL_LOCATORS.equals(locator)) {
					doJava = true;
					doOCL = true;
					doUML = true;
				}
				else if (JAVA_LOCATOR.equals(locator)) {
					doJava = true;
				}
				else if (OCL_LOCATOR.equals(locator)) {
					doOCL = true;
				}
				else if (UML_LOCATOR.equals(locator)) {
					doUML = true;
				}
				else {
					logger.error("Unknown locator '" + locator + "'");
					okLocators = false;
				}
			}
			return okLocators;
		}
	}

	/**
	 * Gets an URI from a file Path.
	 *
	 * @param filePath
	 *            the file path.
	 * @return an URI from the path.
	 */
	private static URI getFileUri(@NonNull String fileName) {
		final URI fileUri;
		File file;
		try {
			file = new File(fileName).getCanonicalFile();		// FIXME is this necessary
			IPath filePath = new Path(file.getAbsolutePath());
			if (isRelativePath(filePath)) {
				fileUri = URI.createPlatformResourceURI(filePath.toString(), true);
			} else {
				fileUri = URI.createFileURI(filePath.toString());
			}
			return fileUri;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Checks if the path is relative or absolute.
	 *
	 * @param path
	 *            a file path.
	 * @return true if the path is relative, false otherwise.
	 */
	private static boolean isRelativePath(IPath path) {
		if (ResourcesPlugin.getPlugin() != null) {
			IResource resource = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
			return resource != null && resource.exists();
		}
		else {
			return false;
		}
	}

	public static boolean isWindows() {
		String os = System.getProperty("os.name");
		return (os != null) && os.startsWith("Windows");
	}

	public final @NonNull ExporterToken exporterToken = new ExporterToken(standaloneApplication);
	public final @NonNull ModelToken modelToken = new ModelToken(standaloneApplication);
	public final @NonNull OutputToken outputToken = new OutputToken(standaloneApplication);
	public final @NonNull RulesToken rulesToken = new RulesToken(standaloneApplication);
	public final @NonNull UsingToken usingToken = new UsingToken(standaloneApplication);

	public ValidateCommand(@NonNull StandaloneApplication standaloneApplication) {
		super(standaloneApplication, "validate", StandaloneMessages.ValidateCommand_Help);
		modelToken.setIsRequired();
		rulesToken.setIsRequired();
		addToken(modelToken);
		addToken(rulesToken);
		addToken(outputToken);
		addToken(exporterToken);
		addToken(usingToken);
	}

	@Override
	public @NonNull StandaloneResponse execute() {
		standaloneApplication.doCompleteOCLSetup();
		String modelFileName = modelToken.getModelFileName();
		List<String> oclFileNames = rulesToken.getOCLFileNames();
		URI modelURI = URI.createURI(modelFileName, true);
		if (!modelURI.isPlatform()) {
			modelURI = getFileUri(modelFileName);
		}
		// Load model resource
		Resource modelResource = standaloneApplication.loadModelFile(modelURI);
		if (modelResource == null) {
			logger.error(MessageFormat.format(StandaloneMessages.OCLValidatorApplication_ModelLoadProblem, modelFileName));
			return StandaloneResponse.FAIL;
		}
		if (!processResources(modelFileName, oclFileNames)) {
			logger.error(StandaloneMessages.OCLValidatorApplication_Aborted);
			return StandaloneResponse.FAIL;
		}
		if (ThreadLocalExecutor.basicGetEnvironmentFactory() == null) {
			logger.error(StandaloneMessages.OCLValidatorApplication_Aborted);
			return StandaloneResponse.FAIL;
		}
		StandaloneValidityManager validityManager = initiateValidityManager(standaloneApplication.getResourceSet());

		if (validityManager != null) {
			// run the validation
			validate(validityManager);

			// export results
			File outputFile = outputToken.getOutputFile();
			exportValidationResults(validityManager.getRootNode(), outputFile);
			//			try {
			//				exportValidationResults(getOutputWriter(), validityManager.getRootNode());
			//			} catch (IOException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
		}
		return StandaloneResponse.OK;
	}

	/**
	 * Exports Validation results.
	 *
	 * @param rootNode
	 *            the validity model rootNode.
	 * @param outputPath
	 *            the exported file path.
	 */
	private void exportValidationResults(@NonNull RootNode rootNode, @Nullable File outputFile) {
		final IValidityExporter selectedExporter = exporterToken.getExporter();
		if (selectedExporter != null && rootNode != null) {
			//			logger.info(StandaloneMessages.OCLValidatorApplication_ExportStarting);
			Appendable s = null;
			try {
				s = outputFile != null ? new FileWriter(outputFile) : DEFAULT_OUTPUT_STREAM;
				selectedExporter.export(s, rootNode, outputFile != null ? outputFile.toString() : null);
			} catch (IOException e) {
				logger.error(StandaloneMessages.OCLValidatorApplication_ExportProblem, e);
			} finally {
				if ((s != DEFAULT_OUTPUT_STREAM) && (s instanceof OutputStreamWriter)) {
					try {
						((OutputStreamWriter)s).close();
					} catch (IOException e) {}
				}
			}
			//			logger.info(StandaloneMessages.OCLValidatorApplication_ExportedFileGenerated);
			//		} else {
			//			logger.info(StandaloneMessages.OCLValidatorApplication_ExportProblem);
		}
	}

	/**
	 * Initiates the validity manager using the resourceSet.
	 *
	 * @param resourceSet
	 *            the resource set.
	 */
	private @NonNull StandaloneValidityManager initiateValidityManager(@NonNull ResourceSet resourceSet) {
		StandaloneValidityManager validityManager = new StandaloneValidityManager();
		validityManager.setRunJavaConstraints(usingToken.doRunJavaConstraints());
		validityManager.setRunOCLConstraints(usingToken.doRunOCLConstraints());
		validityManager.setRunUMLConstraints(usingToken.doRunUMLConstraints());
		validityManager.setInput(resourceSet);
		return validityManager;
	}

	/**
	 * Loads the entered model and ocl files.
	 *
	 * @param modelFilePath
	 *            the model to validate file path.
	 * @param oclPaths
	 *            the ocl files paths.
	 * @return true if there is not problem while loading, false otherwise.
	 */
	private boolean processResources(@NonNull String modelFilePath, @NonNull List<String> oclFileNames) {
		boolean allOk = true;

		CompleteOCLLoader helper = new CompleteOCLLoader(standaloneApplication.getEnvironmentFactory()) {
			@Override
			protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
				logger.error(primaryMessage + detailMessage);
				return false;
			}
		};

		for (String oclFileName : oclFileNames) {
			URI oclURI = URI.createURI(oclFileName, true);
			if (!oclURI.isPlatform()) {
				oclURI = getFileUri(oclFileName);
			}
			if (allOk && oclURI == null) {
				logger.error(MessageFormat.format(StandaloneMessages.OCLValidatorApplication_OclUriProblem, oclFileName));
				allOk = false;
			}

			// Load ocl models
			//			if (done && standaloneApplication.loadModelFile(oclURI) == null) {
			//				logger.error(MessageFormat.format(StandaloneMessages.OCLValidatorApplication_OclLoadProblem, oclFileName));
			//				done = false;
			//			}

			// Load as ocl documents
			try {
				if (allOk) {
					Resource oclResource = helper.loadResource(oclURI);
					if (oclResource == null) {
						logger.error(MessageFormat.format(StandaloneMessages.OCLValidatorApplication_OclLoadProblem, oclFileName));
						allOk = false;
					}
				}
			} catch (Throwable e) {
				logger.error(MessageFormat.format(StandaloneMessages.OCLValidatorApplication_OclLoadProblem, oclFileName));
				allOk = false;
			}
		}

		if (allOk && !helper.loadMetamodels()) {
			logger.error(StandaloneMessages.OCLValidatorApplication_MetamodelsLoadProblem);
			allOk = false;
		}

		helper.installPackages();
		helper.dispose();
		return allOk;
	}

	/**
	 * Runs the validation
	 */
	private void validate(@NonNull StandaloneValidityManager validityManager) {
		//		logger.info(StandaloneMessages.OCLValidatorApplication_ValidationStarting);
		validityManager.runValidation();
		//		logger.info(StandaloneMessages.OCLValidatorApplication_ValidationComplete);
	}
}
