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
package org.eclipse.ocl.xtext.completeocl.validation;

import java.util.Map;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.validation.PivotEObjectValidator;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.validation.ValidationContext;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLPlugin;

/**
 * A CompleteOCLEObjectValidator validates CompleteOCL invariants during an EMF validation, provided
 * an CompleteOCLEObjectValidator instance has been registered as a validator in the EValidator.Registry.
 *
 * Loading of the Complete OCL occurs during @link{initialize()} which may be called explicitly
 * or lazily during validation.
 */
public class CompleteOCLEObjectValidator extends PivotEObjectValidator
{
	private static final @NonNull String COMPLETE_OCL_LOAD_SUCCESSFUL = "Complete OCL lolad successful";

	protected final @NonNull EPackage ePackage;
	protected final @NonNull URI oclURI;

	/**
	 * Construct a validator to apply the CompleteOCL invariants from oclURI to ePackage.
	 * The returned validator should be put into an appropriate EValidator.Registry, which should
	 * normally be a ValidationRegistryAdapter to avoid leakage and concurrency issues with the
	 * shared global registry.
	 */
	public CompleteOCLEObjectValidator(@NonNull EPackage ePackage, @NonNull URI oclURI) {
		super(null);
		this.ePackage = ePackage;
		this.oclURI = oclURI;
	}

	//	@Override
	protected EPackage getEPackage() {
		return ePackage;
	}

	/**
	 * Perform the loading and installation of the Complete OCL, returning true if successful.
	 * This is called lazily by validatePivot() but may be called eagerly to move parsing
	 * overheads up front.
	 */
	public @Nullable ASResource initialize(@NonNull EnvironmentFactory environmentFactory) throws ParserException {
		return environmentFactory.loadCompleteOCLResource(ePackage, oclURI);
	}

	@Override
	protected boolean validatePivot(@NonNull EClassifier eClassifier, @Nullable Object object,
			@Nullable DiagnosticChain diagnostics, Map<Object, Object> validationContext) {
		EnvironmentFactory environmentFactory = ValidationContext.getEnvironmentFactory(validationContext, object);
		Object cachedCompleteOCLEObjectValidator = validationContext.get(COMPLETE_OCL_LOAD_SUCCESSFUL);
		if (cachedCompleteOCLEObjectValidator == null) {
			try {
				initialize(environmentFactory);
			} catch (ParserException e) {
				if (diagnostics != null) {
					String localizedMessage = e.getLocalizedMessage();
					Object[] data = new Object[] {ePackage, e};
					diagnostics.add(new BasicDiagnostic(Diagnostic.ERROR, CompleteOCLPlugin.PLUGIN_ID, 0, localizedMessage, data));
				}
				return false;
			}
			validationContext.put(COMPLETE_OCL_LOAD_SUCCESSFUL, this);	// Only cache if no errors, else regenerate errors next time
		}
		boolean allOk = validate(environmentFactory, eClassifier, object, complementingModels, diagnostics, validationContext);
		return allOk || (diagnostics != null);
	}
}
