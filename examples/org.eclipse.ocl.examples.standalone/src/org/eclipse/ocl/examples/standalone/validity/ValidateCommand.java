/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
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
import java.util.Map;

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
import org.eclipse.ocl.pivot.internal.validation.PivotEObjectValidator.ValidationAdapter;
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
	 * default, the �txt� exporter will be used, exporting a textual report of
	 * the validation.
	 */
	public static class ExporterToken extends StringToken
	{
		public ExporterToken() {
			super("-exporter", StandaloneMessages.ValidateCommand_Exporter_Help);
		}

		@Override
		public boolean check(@NonNull List<String> strings) {
			return getExporter(strings) != null;
		}

		@Override
		public @Nullable String getArgsHelp() {
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

		private @Nullable IValidityExporter getExporter(@NonNull List<String> strings) {
			if (strings.size() <= 0) {
				return null;
			}
			String string = strings.get(0);
			return ValidityExporterRegistry.INSTANCE.getExporter(string);
		}

		public @Nullable IValidityExporter getExporter(@NonNull Map<CommandToken, List<String>> token2strings) {
			List<String> strings = token2strings.get(this);
			if (strings == null) {
				return null;
			}
			return getExporter(strings);
		}


		/**
		 * Gets the validation exporter corresponding to the argument read after the
		 * <b>-report</b> argument.
		 *
		 * @return The validation exporter.
		 */
		//		public AbstractExporter getExporter() {
		//			return exporter;
		//		}
	}

	/**
	 * A mandatory argument key of the model file path. This argument key must
	 * be followed by the model file path.
	 */
	public static class ModelToken extends StringToken
	{
		public ModelToken() {
			super("-model", StandaloneMessages.ValidateCommand_Model_Help);
		}

		@Override
		public boolean check(@NonNull List<String> strings) {
			return getModelFileName(strings) != null;
		}

		@Override
		public @Nullable String getArgsHelp() {
			return "<file-name>";
		}

		private @Nullable String getModelFileName(@NonNull List<String> strings) {
			if (strings.size() <= 0) {
				return null;
			}
			String string = strings.get(0);
			return getCheckedFileName(string);
		}

		public @Nullable String getModelFileName(@NonNull Map<CommandToken, List<String>> token2strings) {
			List<String> strings = token2strings.get(this);
			if (strings == null) {
				return null;
			}
			return getModelFileName(strings);
		}

		/**
		 * Gets the absolute path to the model file deduced from the value specified
		 * after the argument <b>-model</b>.
		 *
		 * @return the model path as a String.
		 */
		//		public IPath getModelFilePath() {
		//			return modelPath;
		//		}
	}

	/**
	 * An optional argument to define the output file path. The exporter will
	 * create results within that target file.
	 */
	public static class OutputToken extends StringToken
	{
		public OutputToken() {
			super("-output", StandaloneMessages.ValidateCommand_Output_Help);
		}

		@Override
		public boolean check(@NonNull List<String> strings) {
			return getOutputFile(strings) != null;
		}

		@Override
		public @Nullable String getArgsHelp() {
			return "<file-name>";
		}

		private @Nullable File getOutputFile(@NonNull List<String> strings) {
			if (strings.size() <= 0) {
				return null;
			}
			String string = strings.get(0);
			try {
				File file = new File(string).getCanonicalFile();
				if (file.exists()) {
					if (file.isFile()) {
						file.delete();
					} else {
						logger.error(StandaloneMessages.OCLArgumentAnalyzer_OutputFile
								+ file.getAbsolutePath()
								+ StandaloneMessages.OCLArgumentAnalyzer_NotFile);
					}
				}
				if (!file.exists()) {
					//					outputFilePath = new Path(file.getAbsolutePath());
					//					outputFile = file;
					File outputFolder = file.getParentFile();
					if (!outputFolder.exists()) {
						logger.error(StandaloneMessages.OCLArgumentAnalyzer_OutputDir
								+ outputFolder.getAbsolutePath()
								+ StandaloneMessages.OCLArgumentAnalyzer_NotExist);
					} else {
						return file;
					}
				}
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
			return null;
		}

		public File getOutputFile(@NonNull Map<CommandToken, List<String>> token2strings) {
			List<String> strings = token2strings.get(this);
			if (strings == null) {
				return null;
			}
			return getOutputFile(strings);
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

		public RulesToken() {
			super("-rules", StandaloneMessages.ValidateCommand_Rules_Help);
		}

		@Override
		public boolean check(@NonNull List<String> strings) {
			if (strings.size() <= 0) {
				//				return false;			-- all files might be ignored
			}
			for (String string : strings) {
				String checkedName = getCheckedFileName(string);
				if (checkedName == null) {
					return false;
				}
			}
			return true;
		}

		@Override
		public int parseArgument(@NonNull List<String> strings, @NonNull String @NonNull [] arguments, int i) {
			if (i < arguments.length){
				String argument = arguments[i++];
				checkOclFile(strings, argument);
				return i;
			}
			else {
				logger.error("No argument for '" + name + "'");
				return -1;
			}
		}

		/**
		 * Checks consistency of the ocl file passed to the command line.
		 *
		 * @param argument
		 *            is the path to the relative/absolute path to the resource
		 * @return <code>true</code> if the model exists and is a file,
		 *         <code>false</code> otherwise.
		 */
		private void checkOclFile(@NonNull List<String> strings, @NonNull String argument) {
			URI uri = URI.createURI(argument);
			argument = uri.isFile() ? uri.toFileString() : argument;
			boolean ignored = false;
			URIConverter uriConverter = getURIConverter();
			boolean exists = uriConverter.exists(uri, null);
			// a txt file may contain relative or absolute path to a set of OCL files.
			String fileExtension = uri.fileExtension(); //path.getFileExtension();
			if (TEXT_FILE_EXTENSION.equals(fileExtension.toLowerCase())) {
				extractOCLUris(strings, uri);
			} else if (OCL_FILE_EXTENSION.equals(fileExtension.toLowerCase())) {
				if (!exists) {
					logger.warn(StandaloneMessages.OCLArgumentAnalyzer_OCLResource + uri + StandaloneMessages.OCLArgumentAnalyzer_NotExist);
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
				logger.warn(StandaloneMessages.OCLArgumentAnalyzer_OCLFile + uri + StandaloneMessages.OCLArgumentAnalyzer_ignored);
			}
		}

		/**
		 * Extracts information contained in the text file.
		 *
		 * @param txtFile
		 *            The file containing relative path to OCL files.
		 */
		private void extractOCLUris(@NonNull List<@NonNull String> strings, @NonNull URI txtURI) {
			try {
				InputStream inputStream = getURIConverter().createInputStream(txtURI);
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				String line = reader.readLine();
				while (line != null) {
					URI childURI = URI.createURI(line).resolve(txtURI);
					checkOclFile(strings, childURI.toString());
					line = reader.readLine();
				}
				reader.close();
			} catch (FileNotFoundException e) {
				logger.error(MessageFormat.format(StandaloneMessages.OCLArgumentAnalyzer_OCLFileNotFound, txtURI));
			} catch (IOException e) {
				logger.warn(e.getMessage());
			}
		}

		@Override
		public @Nullable String getArgsHelp() {
			return "<file-name>";
		}

		/**
		 * Gets the collection of OCL resources deduced from values specified after
		 * the <b>-rule</b> argument.
		 *
		 * @return A List of OCL Uris
		 */
		public @NonNull List<String> getOCLFileNames(@NonNull Map<CommandToken, List<String>> token2strings) {
			List<String> strings = token2strings.get(this);
			if (strings == null) {
				return Collections.emptyList();
			}
			return strings;
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
		private static final String ALL_LOCATORS = "all"; //$NON-NLS-1$
		/** "-using" argument value to additionally run the OCL constraints. */
		private static final String OCL_LOCATOR = "ocl"; //$NON-NLS-1$
		/** "-using" argument value to additionally run the Java constraints. */
		private static final String JAVA_LOCATOR = "java"; //$NON-NLS-1$
		/** "-using" argument value to additionally run the UML constraints. */
		private static final String UML_LOCATOR = "uml"; //$NON-NLS-1$

		public UsingToken() {
			super("-using", StandaloneMessages.ValidateCommand_Using_Help);
		}

		@Override
		public boolean check(@NonNull List<String> locators) {
			for (String locator : locators) {
				if (!ALL_LOCATORS.equals(locator) && !JAVA_LOCATOR.equals(locator) && !OCL_LOCATOR.equals(locator) && !UML_LOCATOR.equals(locator)) {
					logger.error("Unknown locator '" + locator + "'");
					return false;
				}
			}
			return true;
		}

		public boolean doRunJavaConstraints(@NonNull Map<CommandToken, List<String>> token2strings) {
			List<String> strings = token2strings.get(this);
			return (strings == null) || strings.contains(JAVA_LOCATOR) || strings.contains(ALL_LOCATORS);
		}

		public boolean doRunOCLConstraints(@NonNull Map<CommandToken, List<String>> token2strings) {
			List<String> strings = token2strings.get(this);
			return (strings == null) || strings.contains(OCL_LOCATOR) || strings.contains(ALL_LOCATORS);
		}

		public boolean doRunUMLConstraints(@NonNull Map<CommandToken, List<String>> token2strings) {
			List<String> strings = token2strings.get(this);
			return (strings == null) || strings.contains(UML_LOCATOR) || strings.contains(ALL_LOCATORS);
		}

		@Override
		public @Nullable String getArgsHelp() {
			return ALL_LOCATORS + "|" + JAVA_LOCATOR + "|" + OCL_LOCATOR + "|" + UML_LOCATOR;
		}

		@Override
		public boolean isSingleton() {
			return false;
		}

		@Override
		public int parseArgument(@NonNull List<String> strings, @NonNull String @NonNull [] arguments, int i) {
			if (i < arguments.length){
				String argument = arguments[i++];
				String[] locators = argument.split(",");
				for (String locator : locators) {
					if (!ALL_LOCATORS.equals(locator) && !JAVA_LOCATOR.equals(locator) && !OCL_LOCATOR.equals(locator) && !UML_LOCATOR.equals(locator)) {
						logger.error("Unknown locator '" + locator + "'");
						return -1;
					}
				}
				for (String locator : locators) {
					strings.add(locator);
				}
				return i;
			}
			else {
				logger.error("No argument for '" + name + "'");
				return -1;
			}
		}
	}

	protected static String getCheckedFileName(@NonNull String string) {
		URI uri = URI.createURI(string);
		string = uri.isFile() ? uri.toFileString() : string;
		boolean exists = getURIConverter().exists(uri, null);
		if (!exists) {
			logger.error(StandaloneMessages.OCLArgumentAnalyzer_ModelFile
					+ uri
					+ StandaloneMessages.OCLArgumentAnalyzer_NotExist);
		} else {
			return string;
		}
		return null;
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

	public final @NonNull ExporterToken exporterToken = new ExporterToken();
	public final @NonNull ModelToken modelToken = new ModelToken();
	public final @NonNull OutputToken outputToken = new OutputToken();
	public final @NonNull RulesToken rulesToken = new RulesToken();
	public final @NonNull UsingToken usingToken = new UsingToken();

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
	public @NonNull StandaloneResponse execute(@NonNull Map<CommandToken, List<String>> token2strings) {
		standaloneApplication.doCompleteOCLSetup();
		String modelFileName = modelToken.getModelFileName(token2strings);
		List<String> oclFileNames = rulesToken.getOCLFileNames(token2strings);
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
		if (ValidationAdapter.findAdapter(standaloneApplication.getResourceSet()) == null) {
			logger.error(StandaloneMessages.OCLValidatorApplication_Aborted);
			return StandaloneResponse.FAIL;
		}
		StandaloneValidityManager validityManager = initiateValidityManager(standaloneApplication.getResourceSet(), token2strings);

		if (validityManager != null) {
			// run the validation
			validate(validityManager);

			// export results
			File outputFile = outputToken.getOutputFile(token2strings);
			exportValidationResults(validityManager.getRootNode(), outputFile, token2strings);
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
	private void exportValidationResults(@NonNull RootNode rootNode, @Nullable File outputFile, @NonNull Map<CommandToken, List<String>> token2strings) {
		final IValidityExporter selectedExporter = exporterToken.getExporter(token2strings);
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
	private @NonNull StandaloneValidityManager initiateValidityManager(@NonNull ResourceSet resourceSet, @NonNull Map<CommandToken, List<String>> token2strings) {
		StandaloneValidityManager validityManager = new StandaloneValidityManager();
		validityManager.setRunJavaConstraints(usingToken.doRunJavaConstraints(token2strings));
		validityManager.setRunOCLConstraints(usingToken.doRunOCLConstraints(token2strings));
		validityManager.setRunUMLConstraints(usingToken.doRunUMLConstraints(token2strings));
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
