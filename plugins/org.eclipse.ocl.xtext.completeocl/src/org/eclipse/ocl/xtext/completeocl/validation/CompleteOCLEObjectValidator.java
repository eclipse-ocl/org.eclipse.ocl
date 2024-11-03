/*******************************************************************************
 * Copyright (c) 2011, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.validation;

import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.complete.CompletePackageInternal;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.internal.validation.PivotEObjectValidator;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;

import com.google.common.collect.Lists;

/**
 * A CompleteOCLEObjectValidator validates CompleteOCL invariants during an EMF validation, provided
 * an CompleteOCLEObjectValidator instance has been registered as a validator in the EValidator.Registry.
 *
 * Loading of the Complete OCL occurs during @link{initialize()} which may be called explicitly
 * or lazily during validation.
 */
public class CompleteOCLEObjectValidator extends PivotEObjectValidator
{
	private static final Logger logger = Logger.getLogger(CompleteOCLEObjectValidator.class);

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

	/**
	 * Construct a validator to apply the CompleteOCL invariants from oclURI to ePackage
	 * for the meta-models managed by environmentFactory.
	 *
	 * @deprecated environmentFactory is not used. Use ThreadLocalExecutor.getEnvironmentFactory()
	 */
	@Deprecated
	public CompleteOCLEObjectValidator(@NonNull EPackage ePackage, @NonNull URI oclURI, @NonNull EnvironmentFactory environmentFactory) {
		this(ePackage, oclURI);
	}

	//	@Override
	protected EPackage getEPackage() {
		return ePackage;
	}

	@Deprecated
	public @NonNull PivotMetamodelManager getMetamodelManager() {
		return PivotUtilInternal.getEnvironmentFactory(null).getMetamodelManager();	// Better than nothing compatibility
	}

	/**
	 * Perform the loading and installation of the Complete OCL, returning true if successful.
	 * This is called lazily by validatePivot() but may be called eagerly to move parsing
	 * overheads up front.
	 */
	public boolean initialize(@NonNull EnvironmentFactoryInternal environmentFactory) {
		Resource ecoreResource = ePackage.eResource();
		if (ecoreResource == null) {
			return false;
		}
		Ecore2AS ecore2as = Ecore2AS.basicGetAdapter(ecoreResource, environmentFactory);
		if (ecore2as != null) {
			return true;
		}
		ecore2as = Ecore2AS.getAdapter(ecoreResource, environmentFactory);
		ResourceSet resourceSet = environmentFactory.getResourceSet(); // new ResourceSetImpl();
		List<Diagnostic> errors = ecoreResource.getErrors();
		assert errors != null;
		String message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
		if (message != null) {
			logger.error("Failed to load Ecore '" + ecoreResource.getURI() + message);
			return false;
		}
		Model pivotModel = ecore2as.getASModel();
		errors = pivotModel.eResource().getErrors();
		assert errors != null;
		message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
		if (message != null) {
			logger.error("Failed to load Pivot from '" + ecoreResource.getURI() + message);
			return false;
		}
		BaseCSResource xtextResource = (BaseCSResource)resourceSet.getResource(oclURI, true);
		errors = xtextResource.getErrors();
		assert errors != null;
		message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
		if (message != null) {
			logger.error("Failed to load '" + oclURI + message);
			return false;
		}
		CS2AS cs2as = xtextResource.getCS2AS(environmentFactory);
		Resource asResource = cs2as.getASResource();
		errors = asResource.getErrors();
		assert errors != null;
		message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
		if (message != null) {
			logger.error("Failed to load Pivot from '" + oclURI + message);
			return false;
		}
		return true;
	}

	@Override
	protected boolean validatePivot(@NonNull EClassifier eClassifier, @Nullable Object object,
			@Nullable DiagnosticChain diagnostics, Map<Object, Object> validationContext) {
		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(object);
		initialize(environmentFactory);
		ResourceSet resourceSet = getResourceSet(eClassifier, object, diagnostics);
		if (resourceSet != null) {
			boolean allOk = validate(environmentFactory, eClassifier, object, complementingModels, diagnostics, validationContext);
			return allOk || (diagnostics != null);
		}
		return true;
	}

	public void initializeDelegation(@NonNull EnvironmentFactoryInternal environmentFactory) {
		Resource ecoreResource = ePackage.eResource();
		if (ecoreResource == null) {
			return;// false;
		}
		boolean needsDelegate = false;
		org.eclipse.ocl.pivot.Package asPackage = environmentFactory.getMetamodelManager().getASOfEcore(org.eclipse.ocl.pivot.Package.class, ePackage);
		assert asPackage != null;
		CompletePackageInternal completePackage = environmentFactory.getCompleteModel().getCompletePackage(asPackage);
		for (CompleteClass completeClass : completePackage.getOwnedCompleteClasses()) {
			EClass eClass = null;
			for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
				EObject esObject = partialClass.getESObject();
				if (esObject != null) {
					eClass = (EClass)esObject;
					break;
				}
			}
			if (eClass != null) {
				for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
					EObject esObject = partialClass.getESObject();
					if (esObject == null) {
						needsDelegate = true;
						for (Constraint asConstraint : partialClass.getOwnedInvariants()) {
							String constraintName = /*"$$" +*/ asConstraint.getName();
							EAnnotation eClassAnnotation = eClass.getEAnnotation(EcorePackage.eNS_URI);
							if (eClassAnnotation == null) {
								EcoreUtil.setAnnotation(eClass, EcorePackage.eNS_URI, "constraints", constraintName);
							}
							else {
								boolean gotIt = false;
								String constraints = eClassAnnotation.getDetails().get("constraints");
								for (StringTokenizer stringTokenizer = new StringTokenizer(constraints); stringTokenizer.hasMoreTokens();) {
									String constraint = stringTokenizer.nextToken();
									if (constraint.equals(constraintName)) {
										gotIt = true;
										break;
									}
								}
								if (!gotIt) {
									eClassAnnotation.getDetails().put("constraints", constraints + " " + constraintName);
								}
							}
							EcoreUtil.setAnnotation(eClass, PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC, constraintName, "$$complete-ocl$$");		// XXX toString
						}
					}
				}
			}
		}
		if (needsDelegate) {
			List<String> validationDelegates = EcoreUtil.getValidationDelegates(ePackage);
			if (!validationDelegates.contains(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC)) {
				validationDelegates = Lists.newArrayList(validationDelegates);
				validationDelegates.add(PivotConstants.OCL_DELEGATE_URI_PIVOT_DYNAMIC);
				EcoreUtil.setValidationDelegates(ePackage, validationDelegates);
			}
		}
	}
}
