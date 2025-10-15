/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.utilities;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.validation.ValidationContext;
import org.eclipse.ocl.pivot.validation.ValidationRegistryAdapter;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.service.OperationCanceledManager;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.util.IAcceptor;
import org.eclipse.xtext.validation.AbstractInjectableValidator;
import org.eclipse.xtext.validation.CancelableDiagnostician;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.validation.ResourceValidatorImpl;
import org.eclipse.xtext.validation.impl.ConcreteSyntaxEValidator;

/**
 * PivotResourceValidator extends CS Resource validation to the referenced Pivot resources and attempts
 * to indicate Pivot validation problems in the appropriate CS context.
 */
public class PivotResourceValidator extends ResourceValidatorImpl
{
	public static class ValidationDiagnostic extends BasicDiagnostic implements Resource.Diagnostic
	{
		private ValidationDiagnostic(String message) {
			super(WARNING, EObjectValidator.DIAGNOSTIC_SOURCE, 0, message, null);
		}

		@Override
		public int getColumn() {
			return 0;		// This could be computed from the CS
		}

		@Override
		public int getLine() {
			return 0;		// This could be computed from the CS
		}

		@Override
		public String getLocation() {
			return null;		// This could be computed from the CS
		}

		public Integer getOffset() {
			return null;		// This could be computed from the CS
		}

		public Integer getLength() {
			return 10;		// This could be computed from the CS
		}
	}

	private static final Logger log = Logger.getLogger(PivotResourceValidator.class);
	public static final String HAS_SYNTAX_ERRORS = "has_syntax_errors";

	protected ValidationDiagnostic createDefaultDiagnostic(Diagnostician diagnostician, EObject pivotObject) {
		//		Object objectLabel = diagnostician.getObjectLabel(pivotObject);
		//		return new ValidationDiagnostic(EcorePlugin.INSTANCE.getString("_UI_DiagnosticRoot_diagnostic",
		//			new Object[] { objectLabel }), new Object[] { pivotObject });
		return new ValidationDiagnostic("");
	}

	protected void issueFromDiagnostics(IAcceptor<Issue> acceptor, ValidationDiagnostic diagnostic) {
		for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
			//			System.out.println(" issueFromEValidatorDiagnostic " + childDiagnostic);
			issueFromEValidatorDiagnostic(childDiagnostic, acceptor);
		}
	}

	protected void performValidation(IAcceptor<Issue> acceptor, Resource asResource, CancelIndicator monitor) {
		assert asResource != null;
		//		System.out.println(ThreadLocalExecutor.getBracketedThreadName() + " performValidation " + NameUtil.debugSimpleName(asResource));
		ValidationRegistryAdapter validationRegistry = ValidationRegistryAdapter.getAdapter(asResource);
		ValidationContext validationContext = new ValidationContext(validationRegistry);
		Diagnostician diagnostician = validationContext.getDiagnostician();
		//		List<Resource> resources = asResource.getResourceSet().getResources();
		//		for (int i = 0; i < resources.size(); i++) {
		Resource pResource = asResource; //resources.get(i);
		//			if (PivotConstants.ORPHANAGE_URI.equals(String.valueOf(pResource.getURI()))) {
		//				continue;		// GC may not have eliminated all the dangling references
		//			}
		//			System.out.println(" performValidation " + pResource.getURI() + " on " + ThreadLocalExecutor.getBracketedThreadName());
		removeValidationDiagnostics(pResource.getErrors());
		removeValidationDiagnostics(pResource.getWarnings());
		List<@NonNull EObject> contents = pResource.getContents();
		for (int j = 0; j < contents.size(); j++) {		// Beware concurrent unload
			try {
				if (monitor.isCanceled())
					return;
				EObject pObject = contents.get(j);
				ValidationDiagnostic diagnostic = createDefaultDiagnostic(diagnostician, pObject);
				diagnostician.validate(pObject, diagnostic, validationContext);
				if (!diagnostic.getChildren().isEmpty()) {
					if (diagnostic.getSeverity() == Diagnostic.ERROR) {
						pResource.getErrors().add(diagnostic);
					}
					else if (diagnostic.getSeverity() == Diagnostic.WARNING) {
						pResource.getWarnings().add(diagnostic);
					}
					issueFromDiagnostics(acceptor, diagnostic);
				}
			} catch (RuntimeException e) {
				if (!monitor.isCanceled()) {
					pResource.getErrors().add(new ValidationDiagnostic(e.getMessage()));
					//					log.error(e.getMessage(), e);
				}
			}
		}
		//		}
	}

	protected void removeValidationDiagnostics(List<Resource.@NonNull Diagnostic> diagnostics) {
		//		System.out.println(ThreadLocalExecutor.getBracketedThreadName() + " removeValidationDiagnostics ");
		for (int i = diagnostics.size()-1; i >= 0; i--) {
			Resource.Diagnostic diagnostic = diagnostics.get(i);
			if (diagnostic instanceof ValidationDiagnostic) {
				diagnostics.remove(i);
			}
		}
	}

	@Override
	public List<Issue> validate(Resource resource, final CheckMode mode, CancelIndicator mon) {
//		System.out.println(ThreadLocalExecutor.getBracketedThreadName() + " validate " + NameUtil.debugSimpleName(resource) + " '" + resource.getURI() + "' for " + NameUtil.debugSimpleName(mon));
		//		System.out.println(new Date() + " Validate " + mode + " : " + csResource.getURI() + " on " + ThreadLocalExecutor.getBracketedThreadName());
		EnvironmentFactory environmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		boolean locallyCreatedEnvironmentFactory = false;
		if (environmentFactory == null) {
			environmentFactory = PivotUtil.getEnvironmentFactory(resource.getResourceSet());		// GC will eventually clean up
			locallyCreatedEnvironmentFactory = true;
		}
		assert environmentFactory != null;
		try {
			List<Issue> result = super.validate(resource, mode, mon);
			if (resource instanceof BaseCSResource) {
				BaseCSResource csResource = (BaseCSResource)resource;
				CS2AS cs2as = csResource.findCS2AS();
				if (cs2as != null) {
					Resource asResource = cs2as.getASResource();
					IAcceptor<Issue> acceptor = createAcceptor(result);
					CancelIndicator monitor = mon == null ? CancelIndicator.NullImpl : mon;
					performValidation(acceptor, asResource, monitor);
				}
			}
			return result;
		}
		finally {
			//		System.out.println(ThreadLocalExecutor.getBracketedThreadName() + " validate end " + NameUtil.debugSimpleName(resource));
			if (locallyCreatedEnvironmentFactory) {
				ThreadLocalExecutor.resetEnvironmentFactory();
			}
		}
	}

	@Override		// Overridden to use ValidationContext and its Diagnostician, and track BaseCSResource.getErrors()
	protected void validate(Resource resource, final CheckMode mode, final CancelIndicator monitor, IAcceptor<Issue> acceptor) {
		// Pre Xtext 2.8 problems are discussed in Bug 461764
		// A request to facilitate super-reuse remains open as Bug 389675
		assert resource != null;
		ValidationRegistryAdapter validationRegistry = ValidationRegistryAdapter.getAdapter(resource);
		ValidationContext validationContext = new ValidationContext(validationRegistry);
		validationContext.put(CheckMode.KEY, mode);
		validationContext.put(CancelableDiagnostician.CANCEL_INDICATOR, monitor);
		// disable concrete syntax validation, since a semantic model that has been parsed
		// from the concrete syntax always complies with it - otherwise there are parse errors.
		validationContext.put(ConcreteSyntaxEValidator.DISABLE_CONCRETE_SYNTAX_EVALIDATOR, Boolean.TRUE);
		// see EObjectValidator.getRootEValidator(Map<Object, Object>)
		boolean hasSyntaxError = false;
		if (resource instanceof XtextResource) {
			validationContext.put(AbstractInjectableValidator.CURRENT_LANGUAGE_NAME, ((XtextResource) resource).getLanguageName());
			if (resource instanceof BaseCSResource) {
				BaseCSResource csResource = (BaseCSResource)resource;
				@NonNull List<Resource.@NonNull Diagnostic> errors = csResource.getErrors();
				hasSyntaxError = ElementUtil.hasSyntaxError(errors);
				if (hasSyntaxError) {
					validationContext.put(PivotResourceValidator.HAS_SYNTAX_ERRORS, Boolean.TRUE);
				}
			}
		}
		if (!hasSyntaxError) {
			OperationCanceledManager operationCanceledManager = getOperationCanceledManager();
			if (operationCanceledManager == null) {
				throw new NullPointerException(getClass().getName() + " was not constructed using an injector");
			}
			Diagnostician diagnostician = validationContext.getDiagnostician();
			for (EObject ele : resource.getContents()) {
				operationCanceledManager.checkCanceled(monitor);
				try {
					Diagnostic diagnostic = diagnostician.validate(ele, validationContext);
					if (!diagnostic.getChildren().isEmpty()) {
						for (Diagnostic childDiagnostic : diagnostic.getChildren()) {
							issueFromEValidatorDiagnostic(childDiagnostic, acceptor);
						}
					} else {
						issueFromEValidatorDiagnostic(diagnostic, acceptor);
					}
				} catch (RuntimeException e) {
					operationCanceledManager.propagateAsErrorIfCancelException(e);
					log.error(e.getMessage(), e);
				}
			}
		}
	}
}
