/*******************************************************************************
 * Copyright (c) 2011, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.validation;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.validation.PivotEObjectValidator;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * A CompleteOCLEObjectValidator validates CompleteOCL invariants during an EMF validation, provided
 * a CompleteOCLEObjectValidator instance has been registered as a validator in the EValidator.Registry.
 * 
 * Loading of the Complete OCL occurs during @link{initialize()} which may be called explicitly
 * or lazily during validation.
 */
public class CompleteOCLEObjectValidator extends PivotEObjectValidator
{	
	private static final Logger logger = Logger.getLogger(CompleteOCLEObjectValidator.class);

	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull EPackage ePackage;
	protected final @NonNull URI oclURI;
	private Ecore2AS ecore2as = null;
	
	/**
	 * Construct a validator to apply the CompleteOCL invariants from oclURI to ePackage
	 * for the meta-models managed by metamodelManager.
	 */
	public CompleteOCLEObjectValidator(@NonNull EPackage ePackage, @NonNull URI oclURI, @NonNull EnvironmentFactory environmentFactory) {
		super(null);
		this.environmentFactory = (EnvironmentFactoryInternal) environmentFactory;
		this.ePackage = ePackage;
		this.oclURI = oclURI;
		ResourceSet resourceSet = ePackage.eResource().getResourceSet();
		if (resourceSet != null) {
			install(resourceSet, this.environmentFactory);
		}
		else {
			this.environmentFactory.loadEPackage(ePackage);
		}
	}
	
//	@Override
	protected EPackage getEPackage() {
		return ePackage;
	}
	
	public @NonNull PivotMetamodelManager getMetamodelManager() {
		return environmentFactory.getMetamodelManager();
	}
	
	/**
	 * Perform the loading and installation of the Complete OCL, returning true if successful.
	 */
	public boolean initialize() {
		Resource ecoreResource = ePackage.eResource();
		if (ecoreResource == null) {
			return false;
		}
		ecore2as = Ecore2AS.getAdapter(ecoreResource, environmentFactory);
		ResourceSet resourceSet = new ResourceSetImpl();
		environmentFactory.adapt(resourceSet);
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
		CSResource xtextResource = null;
		try {
			xtextResource = (CSResource) resourceSet.getResource(oclURI, true);
		}
		catch (WrappedException e) {
			URI retryURI = null;
			Throwable cause = e.getCause();
			if (cause instanceof CoreException) {
				IStatus status = ((CoreException)cause).getStatus();
				if ((status.getCode() == IResourceStatus.RESOURCE_NOT_FOUND) && status.getPlugin().equals(ResourcesPlugin.PI_RESOURCES)) {
					if (oclURI.isPlatformResource()) {
						retryURI = URI.createPlatformPluginURI(oclURI.toPlatformString(false), false);
					}
				}
			}
			if (retryURI != null) {
				xtextResource = (CSResource) resourceSet.getResource(retryURI, true);			
			}
			else {
				throw e;
			}
		}
		errors = xtextResource.getErrors();
		assert errors != null;
		message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
		if (message != null) {
			logger.error("Failed to load '" + oclURI + message);
			return false;
		}
		Resource asResource = xtextResource.getASResource();
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
			@Nullable DiagnosticChain diagnostics, Map<Object, Object> context) {
		if ((ecore2as == null) && (object instanceof EObject)) {
			initialize();	
			Resource eResource = ((EObject)object).eResource();
			if (eResource != null) {
				ResourceSet resourceSet = eResource.getResourceSet();
				if (resourceSet != null) {
					install(resourceSet, environmentFactory);
				}
			}
		}
		return super.validatePivot(eClassifier, object, diagnostics, context);
	}
}
