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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.standalone.messages.StandaloneMessages;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypeExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.internal.context.ClassContext;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotDiagnostician;
import org.eclipse.ocl.pivot.queries.QueriesFactory;
import org.eclipse.ocl.pivot.queries.QueryModel;
import org.eclipse.ocl.pivot.queries.QueryResult;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserContext;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.URIUtil;
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
		void export(@NonNull String query, @Nullable ExpressionInOCL expression, @Nullable Object context, @Nullable Object result, @Nullable List<@NonNull String> errors) throws IOException;
		void open(@NonNull File exportedFile, boolean hideQuery, boolean hideResult, boolean hideASQuery, boolean hideASResult, boolean hideASTypes) throws IOException;
	}

	public static abstract class AbstractResultExporter implements IResultExporter
	{
		protected final @NonNull StandaloneApplication standaloneApplication;

		protected AbstractResultExporter(@NonNull StandaloneApplication standaloneApplication) {
			this.standaloneApplication = standaloneApplication;
		}
	}

	public static class TextResultExporter extends AbstractResultExporter
	{
		private FileWriter s;
		private boolean hideQuery;
		private boolean hideResult;

		protected TextResultExporter(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication);
		}

		@Override
		public void close() throws IOException {
			s.close();
		}

		@Override
		public void export(@NonNull String query, @Nullable ExpressionInOCL expression, @Nullable Object context, @Nullable Object result, @Nullable List<@NonNull String> errors) throws IOException {
			if (errors == null) {
				if (hideQuery) {
					if (!hideResult) {
						s.append(result + "\n");
					}
					else {
						s.append(result + "\n");
					}
				}
				else {
					s.append("Query : " + query + "\n");
					if (!hideResult) {
						s.append("Result: " + result + "\n");
					}
				}
			}
			else {
				s.append("Query : " + query + "\n");
				s.append("Result: " + result + "\n");
				for (String error : errors) {
					s.append("Error : " + error + "\n");
				}
			}
		}

		@Override
		public void open(@NonNull File exportedFile, boolean hideQuery, boolean hideResult, boolean hideASQuery, boolean hideASResult, boolean hideASTypes) throws IOException {
			this.hideQuery = hideQuery;
			this.hideResult = hideResult;
			s = new FileWriter(exportedFile);
		}
	}

	public static class DefaultResultExporter extends AbstractResultExporter
	{
		private final @NonNull StringBuilder s = new StringBuilder();

		protected DefaultResultExporter(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication);
		}

		@Override
		public void close() {
			if (!standaloneApplication.isTest()) {
				System.out.println(s.toString());
			}
		}

		@Override
		public void export(@NonNull String query, @Nullable ExpressionInOCL expression, @Nullable Object context, @Nullable Object result, @Nullable List<@NonNull String> errors) throws IOException {
			if (errors == null) {
				s.append(result);
			}
			else {
				for (String error : errors) {
					s.append(error + "\n");
				}
			}
			s.append("\n");
		}

		public @NonNull String getConsoleText() {
			return s.toString();
		}

		@Override
		public void open(@NonNull File exportedFile, boolean hideQuery, boolean hideResult, boolean hideASQuery, boolean hideASResult, boolean hideASTypes) {}
	}

	public static class ModelResultExporter extends AbstractResultExporter
	{
		private boolean hideQuery;
		private boolean hideResult;
		private boolean hideASQuery;
		private boolean hideASResult;
		private boolean hideASTypes;
		private Resource resource;
		private @NonNull QueryModel queryModel = QueriesFactory.eINSTANCE.createQueryModel();

		public ModelResultExporter(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication);
		}

		@Override
		public void close() throws IOException {
			if (resource != null) {
				if (hideASTypes) {
					List<@NonNull EObject> contents = resource.getContents();
					Collection<@NonNull EObject> newContents = EcoreUtil.copyAll(contents);
					contents.clear();
					contents.addAll(newContents);
					for (EObject eObject : new TreeIterable(resource)) {
						if (eObject instanceof TypedElement) {
							((TypedElement)eObject).setType(null);
						}
						if (eObject instanceof TypeExp) {
							((TypeExp)eObject).setReferredType(null);
							((TypeExp)eObject).setTypeValue(null);
						}
					}
				}
				resource.save(null);
				resource.getResourceSet().getResources().remove(resource);
			}
		}

		@Override
		public void export(@NonNull String query, @Nullable ExpressionInOCL expression, @Nullable Object context, @Nullable Object result, @Nullable List<@NonNull String> errors)throws IOException {
			QueryResult queryResult = QueriesFactory.eINSTANCE.createQueryResult();
			if (context != null) {
				queryResult.setSelf((EObject)context);
			}
			if (!hideQuery) {
				queryResult.setQuery(query);
			}
			if (expression != null) {
				expression.setBody(null);
				if (!hideASQuery) {
					queryResult.setExpression(expression);
				}
			}
			if (result != null) {
				if (!hideResult) {
				//	queryResult.setResult(String.valueOf(result));
					StringBuilder s = new StringBuilder();
					ValueUtil.toString(result, s, -1);
					queryResult.setResult(s.toString());
				}
				if (!hideASResult) {
					queryResult.setValue(ValueUtil.createLiteralExp(result));
				}
			}
			if (errors != null) {
				List<String> errors2 = queryResult.getErrors();
				for (String error : errors) {
					errors2.add(error);
				}
			}
			queryModel.getResults().add(queryResult);
		}

		@Override
		public void open(@Nullable File exportedFile, boolean hideQuery, boolean hideResult, boolean hideASQuery, boolean hideASResult, boolean hideASTypes) throws IOException {
			this.hideResult = hideResult;
			this.hideQuery = hideQuery;
			this.hideASResult = hideASResult;
			this.hideASQuery = hideASQuery;
			this.hideASTypes = hideASTypes;
			if (exportedFile != null) {
				ResourceSet resourceSet = standaloneApplication.getOCL().getMetamodelManager().getASResourceSet();
				URI uri = URI.createFileURI(exportedFile.getAbsolutePath());
			//	resource = resourceSet.createResource(uri);
				resource = OCLASResourceFactory.getInstance().createResource(uri);
				resourceSet.getResources().add(resource);
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

		public ExporterToken(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication, "-exporter", StandaloneMessages.ExecuteCommand_Exporter_Help, "none|text|model");
		}

		public @Nullable IResultExporter getExporter() {
			return exporter;
		}

		@Override
		public boolean parseCheck(@NonNull String string) {
			if ("none".equals(string)) {
				exporter = null;
			}
			else if ("text".equals(string) ) {
				exporter = new TextResultExporter(standaloneApplication);
			}
			else if ("model".equals(string) ) {
				exporter = new ModelResultExporter(standaloneApplication);
			}
			else {
				logger.error("Unrecognized 'exporter' " + string);
				return false;
			}
			return true;
		}
	}

	/**
	 * A mandatory argument to provide one or more queries to evaluate.
	 */
	public static class QueryToken extends StringToken
	{
		private @Nullable List<@NonNull String> queries = null;

		public QueryToken(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication, "-query", StandaloneMessages.ExecuteCommand_Query_Help, "<ocl-query>");
			setIsRequired();
		}

		@Override
		protected boolean analyze(@Nullable String string) {
			List<@NonNull String> queries2 = queries;
			if (queries2 == null) {
				queries = queries2 = new ArrayList<>();
			}
			queries2.add(string);
			return true;
		}

		@Override
		public int getMaxArguments() {
			return -1;
		}

		public @Nullable List<@NonNull String> getQueries() {
			return queries;
		}

		@Override
		public boolean isSingleton() {
			return false;
		}
	}

	/**
	 * An optional argument to specify the self element
	 */
	public static class SelfToken extends StringToken
	{
		private @Nullable EObject self = null;

		public SelfToken(@NonNull StandaloneApplication standaloneApplication) {
			super(standaloneApplication, "-self", StandaloneMessages.ExecuteCommand_Self_Help, "<self-uri>");
		}

		@Override
		protected boolean analyze(@Nullable String string) {
			URI rawURI = URI.createURI(string);
			String fragment = rawURI.fragment();
			rawURI = URIUtil.getAbsoluteURI(rawURI.trimFragment());
			Resource selfResource = standaloneApplication.getResourceSet().getResource(rawURI, true);
			if (selfResource == null) {
				String string2 = rawURI.toString();
				logger.error("Failed to load '" + string2 + "'");
				return false;
			}
			self = selfResource.getEObject(fragment);
			if (self == null) {
				String string2 = rawURI.toString();
				logger.error("Failed to locate '" + fragment + "' within '" + string2 + "'");
			}
			return self != null;
		}

		public @Nullable EObject getSelf() {
			return self;
		}
	}

	public final @NonNull BooleanToken hideResultToken = new BooleanToken(standaloneApplication, "-hideResult", "Omit the text representation of each result..");
	public final @NonNull BooleanToken hideQueryToken = new BooleanToken(standaloneApplication, "-hideQuery", "Omit the text representation of each query.");
	public final @NonNull BooleanToken hideASResultToken = new BooleanToken(standaloneApplication, "-hideASResult", "Omit the Abstract Syntax representation of each result.");
	public final @NonNull BooleanToken hideASQueryToken = new BooleanToken(standaloneApplication, "-hideASQuery", "Omit the Abstract Syntax representation of each query.");
	public final @NonNull BooleanToken hideASTypesToken = new BooleanToken(standaloneApplication, "-hideASTypes", "Omit type fields from Abstract Syntax; reduces file size by perhaps 75%");
	public final @NonNull ExporterToken exporterToken = new ExporterToken(standaloneApplication);
	public final @NonNull OutputToken outputToken = new OutputToken(standaloneApplication);
	public final @NonNull QueryToken queryToken = new QueryToken(standaloneApplication);
	public final @NonNull SelfToken selfToken = new SelfToken(standaloneApplication);

	public ExecuteCommand(@NonNull StandaloneApplication standaloneApplication) {
		super(standaloneApplication, "execute", StandaloneMessages.ExecuteCommand_Help);
		queryToken.setIsRequired();
		addToken(hideResultToken);
		addToken(hideQueryToken);
		addToken(hideASResultToken);
		addToken(hideASQueryToken);
		addToken(hideASTypesToken);
		addToken(outputToken);
		addToken(exporterToken);
		addToken(queryToken);
		addToken(selfToken);
	}

	protected void appendChildren(List<@NonNull String> errors, List<Diagnostic> children) {
		for (Diagnostic child : children) {
			errors.add(child.getMessage());
			appendChildren(errors, child.getChildren());
		}
	}

	@Override
	public @NonNull StandaloneResponse execute() throws IOException {
		standaloneApplication.doEssentialOCLSetup();
		EObject context = selfToken.getSelf();
		OCL ocl = standaloneApplication.getOCL();
		boolean hideResult = hideResultToken.isPresent();
		boolean hideQuery = hideQueryToken.isPresent();
		boolean hideASResult = hideASResultToken.isPresent();
		boolean hideASQuery = hideASQueryToken.isPresent();
		boolean hideASTypes = hideASTypesToken.isPresent();
		File outputFile = outputToken.getOutputFile();
		IResultExporter selectedExporter = exporterToken.getExporter();
		if ((outputFile == null) || (selectedExporter == null)) {
			selectedExporter = new DefaultResultExporter(standaloneApplication);
		}
		else {
			selectedExporter.open(outputFile, hideQuery, hideResult, hideASQuery, hideASResult, hideASTypes);
		}
		org.eclipse.ocl.pivot.Class classContext = ocl.getContextType(context);
		boolean allOk = true;
		for (@NonNull String queryString : queryToken.getQueries()) {
			Object result = null;
			ExpressionInOCL query = null;
			List<@NonNull String> errors = new ArrayList<>();
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
				if (children.size() > 0) {
					appendChildren(errors, children);
					allOk = false;
				}
				if ((children.size() <= 0) && (!hideResult || !hideASResult)) {
					EvaluationVisitor evaluationVisitor = ocl.createEvaluationVisitor(context, query);
					try {
						result = query.accept(evaluationVisitor);
						if (result == null) {
							result = ValueUtil.createLiteralExp(null);
						}
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
				errors.add(e.toString());
				allOk = false;
			}
			try {
				selectedExporter.export(queryString, query, context, result, errors.size() > 0 ? errors : null);
			} catch (IOException e) {
				logger.error(StandaloneMessages.OCLValidatorApplication_ExportProblem, e);
				allOk = false;
			}
		}
		selectedExporter.close();
		if (selectedExporter instanceof DefaultResultExporter) {
			standaloneApplication.setConsoleOutput(((DefaultResultExporter)selectedExporter).getConsoleText());
		}
		return allOk ? StandaloneResponse.OK : StandaloneResponse.FAIL;
	}
}
