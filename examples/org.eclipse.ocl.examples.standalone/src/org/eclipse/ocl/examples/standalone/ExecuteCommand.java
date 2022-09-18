/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.standalone;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.standalone.messages.StandaloneMessages;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.internal.context.ClassContext;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician;
import org.eclipse.ocl.pivot.queries.QueriesFactory;
import org.eclipse.ocl.pivot.queries.QueryModel;
import org.eclipse.ocl.pivot.queries.QueryResult;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * The HelpCommand provides interactive help.
 */
public class ExecuteCommand extends StandaloneCommand
{
	private static final Logger logger = Logger.getLogger(ExecuteCommand.class);

	public static interface IResultExporter {
		void close() throws IOException;
		void export(@NonNull Appendable s, @NonNull String query, @Nullable ExpressionInOCL expression, @Nullable Object context, @Nullable Object result, @Nullable String error) throws IOException;
		void open(@NonNull OCL ocl, @Nullable File exportedFile, boolean echoText, boolean echoModel, boolean showText, boolean showModel) throws IOException;
	}

	public static class TextResultExporter implements IResultExporter
	{
		private boolean echoText;

		@Override
		public void close() throws IOException {}

		@Override
		public void export(@NonNull Appendable s, @NonNull String query, @Nullable ExpressionInOCL expression, @Nullable Object context, @Nullable Object result, @Nullable String error) throws IOException {
			if (echoText) {
				s.append("Query : " + query + "\nResult: ");
			}
			s.append(result + "\n");
			if (error != null) {
				s.append("Error : " + error + "\n");
			}
		}

		@Override
		public void open(@NonNull OCL ocl, @Nullable File exportedFile, boolean echoText, boolean echoModel, boolean showText, boolean showModel) throws IOException {
			this.echoText = echoText;
		}
	}

	public static class ModelResultExporter implements IResultExporter
	{
		private boolean echoText;
		private boolean echoModel;
		private boolean showText;
		private boolean showModel;
		private Resource resource;
		private @NonNull QueryModel queryModel = QueriesFactory.eINSTANCE.createQueryModel();

		@Override
		public void close() throws IOException {
			if (resource != null) {
				resource.save(null);
			}
		}

		@Override
		public void export(@NonNull Appendable s, @NonNull String query, @Nullable ExpressionInOCL expression, @Nullable Object context, @Nullable Object result, @Nullable String error)throws IOException {
			QueryResult queryResult = QueriesFactory.eINSTANCE.createQueryResult();
			if (echoText) {
				queryResult.setQuery(query);
			}
			if (expression != null) {
				expression.setBody(null);
				if (echoModel) {
					queryResult.setExpression(expression);
				}
			}
			if (result != null) {
				if (showText) {
					queryResult.setResult(String.valueOf(result));
				}
				if (showModel) {
					queryResult.setValue(ValueUtil.createLiteralExp(result));
				}
			}
			if (error != null) {
				queryResult.setError(error);
			}
			queryModel.getResults().add(queryResult);
		}

		@Override
		public void open(@NonNull OCL ocl, @Nullable File exportedFile, boolean echoText, boolean echoModel, boolean showText, boolean showModel) throws IOException {
			this.echoModel = echoModel;
			this.echoText = echoText;
			this.showModel = showModel;
			this.showText = showText;
			if (exportedFile != null) {
				resource = ocl.getResourceSet().createResource(URI.createFileURI(exportedFile.getAbsolutePath()));
				resource.getContents().add(queryModel);
			}
			else {
				resource = null;
			}
		}
	}

	/**
	 * An optional argument to specify which exporter should be used. By
	 * default, the 'text' exporter will be used, exporting a textual report of
	 * the validation.
	 */
	public static class ExporterToken extends StringToken
	{
		private @Nullable IResultExporter exporter;

		public ExporterToken() {
			super("-exporter", StandaloneMessages.ExecuteCommand_Exporter_Help, "none|text|model");
		}

		public @Nullable IResultExporter getExporter() {
			return exporter;
		}

		@Override
		public boolean isRequired() {
			return true;
		}

		@Override
		public boolean parseCheck(@NonNull String string) {
			if ("none".equals(string)) {
				exporter = null;
			}
			else if ("text".equals(string) ) {
				exporter = new TextResultExporter();
			}
			else if ("model".equals(string) ) {
				exporter = new ModelResultExporter();
			}
			else {
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
	public static class QueryToken extends StringToken
	{
		private @Nullable List<@NonNull String> queries = null;

		public QueryToken() {
			super("-query", StandaloneMessages.ExecuteCommand_Query_Help, "<ocl-query>");
		}

		@Override
		protected @Nullable String analyze(@NonNull StandaloneApplication standaloneApplication, @NonNull String string) {
			List<@NonNull String> queries2 = queries;
			if (queries2 == null) {
				queries = queries2 = new ArrayList<>();
			}
			queries2.add(string);
			return null;
		}

		public @Nullable List<@NonNull String> getQueries() {
			return queries;
		}

		@Override
		public boolean isSingleton() {
			return false;
		}
	}

	public final @NonNull BooleanToken echoModelToken = new BooleanToken("-echoModel", "Echo each query as an AS model");
	public final @NonNull BooleanToken echoTextToken = new BooleanToken("-echoText", "Echo each query as text");
	public final @NonNull BooleanToken showModelToken = new BooleanToken("-showModel", "Show each result as an AS model");
	public final @NonNull BooleanToken showTextToken = new BooleanToken("-showText", "Show each result as text");
	public final @NonNull ExporterToken exporterToken = new ExporterToken();
	public final @NonNull OutputToken outputToken = new OutputToken();
	public final @NonNull QueryToken queryToken = new QueryToken();

	public ExecuteCommand(@NonNull StandaloneApplication standaloneApplication) {
		super(standaloneApplication, "execute", StandaloneMessages.ExecuteCommand_Help);
		queryToken.setIsRequired();
		addToken(echoModelToken);
		addToken(echoTextToken);
		addToken(showModelToken);
		addToken(showTextToken);
		addToken(outputToken);
		addToken(exporterToken);
		addToken(queryToken);
	}

	@Override
	public @NonNull StandaloneResponse execute() throws IOException {
		standaloneApplication.doCompleteOCLSetup();
		EObject context = null;
		OCL ocl = standaloneApplication.getOCL();
		File outputFile = outputToken.getOutputFile();
		final @Nullable IResultExporter selectedExporter = exporterToken.getExporter();
		if (selectedExporter != null) {
			boolean echoModel = echoModelToken.isPresent();
			boolean echoText = echoTextToken.isPresent();
			boolean showModel = showModelToken.isPresent();
			boolean showText = showTextToken.isPresent();
			selectedExporter.open(ocl, outputFile, echoText, echoModel, showText, showModel);
		}
		org.eclipse.ocl.pivot.Class classContext = ocl.getContextType(context);
		for (@NonNull String queryString : queryToken.getQueries()) {
			Object result = null;
			ExpressionInOCL query = null;
			String error = null;
			ParserContext parserContext = new ClassContext(ocl.getEnvironmentFactory(), null, classContext, (context instanceof Type) && !(context instanceof ElementExtension) ? (Type)context : null);
			try {
				query = parserContext.parse(classContext, queryString);
				//	PivotTestSuite.assertNoValidationErrors(expression, query);
				Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
				//		Resource eResource = ClassUtil.nonNullState(eObject.eResource());
				//		PivotUtilInternal.getMetamodelManager(eResource);	// FIXME oclIsKindOf fails because ExecutableStandardLibrary.getMetaclass is bad
				//		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject, validationContext);
				BasicDiagnostic diagnostics = PivotDiagnostician.BasicDiagnosticWithRemove.validate(query, validationContext);
				List<Diagnostic> children = diagnostics.getChildren();
				if (children.size() <= 0) {
					EvaluationVisitor evaluationVisitor = ocl.createEvaluationVisitor(context, query);
					try {
						result = query.accept(evaluationVisitor);
					}
					catch (InvalidValueException e) {
						if (e == ValueUtil.INVALID_VALUE) {
							result = e;
						}
						else {
							throw e;
						}
					}
				}
			} catch (Exception | AssertionError e) {
				error = e.toString();
			}
			Appendable s = null;
			try {
				s = outputFile != null ? new FileWriter(outputFile) : DEFAULT_OUTPUT_STREAM;
				selectedExporter.export(s, queryString, query, context, result, error);
			} catch (IOException e) {
				logger.error(StandaloneMessages.OCLValidatorApplication_ExportProblem, e);
			} finally {
				if ((s != DEFAULT_OUTPUT_STREAM) && (s instanceof OutputStreamWriter)) {
					try {
						((OutputStreamWriter)s).close();
					} catch (IOException e) {}
				}
			}
		}

/*		List<String> oclFileNames = rulesToken.getOCLFileNames(token2strings);
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
		StandaloneValidityManager validityManager = initiateValidityManager(standaloneApplication.getResourceSet(), token2strings);

		if (validityManager != null) {
			// run the validation
			validate(validityManager);
		} */

		// export results
			//			try {
			//				exportValidationResults(getOutputWriter(), validityManager.getRootNode());
			//			} catch (IOException e) {
			//				// TODO Auto-generated catch block
			//				e.printStackTrace();
			//			}
		if (selectedExporter != null) {
			selectedExporter.close();
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
	 *
	private void exportExecutionResults(@NonNull ExpressionInOCL query, @Nullable Object result, @Nullable File outputFile, @NonNull Map<@NonNull CommandToken, @NonNull List<@NonNull String>> token2strings) {
		final @Nullable IResultExporter selectedExporter = exporterToken.getExporter(token2strings);
		if (selectedExporter != null && query != null) {
			//			logger.info(StandaloneMessages.OCLValidatorApplication_ExportStarting);
			Appendable s = null;
			try {
				s = outputFile != null ? new FileWriter(outputFile) : DEFAULT_OUTPUT_STREAM;
				selectedExporter.export(s, query, result, outputFile != null ? outputFile.toString() : null);
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
	} */
}
