/*******************************************************************************
 * Copyright (c) 2010, 2024 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink (Obeo) - Add complete ocl registry to enable export and re-use CompleteOCL files
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.internal.validation.PivotEObjectValidator;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.validation.ValidationRegistryAdapter;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;

public abstract class CompleteOCLLoader
{  // FIXME This is a pragmatic re-use. Redesign as part of a coherent API.
	public static final class CompleteOCLLoaderWithLog extends CompleteOCLLoader
	{
		StringBuilder s = new StringBuilder();

		public CompleteOCLLoaderWithLog(@NonNull EnvironmentFactory environmentFactory) {
			super(environmentFactory);
		}

		@Override
		protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
		//	s.append("\n");
			s.append(primaryMessage);
		//	s.append("\n");
			s.append(detailMessage);
			return false;
		}

		@Override
		public String toString() {
			return s.toString();
		}
	}

	protected final @NonNull OCLInternal ocl;
	protected final @NonNull List<@NonNull Model> oclModels = new ArrayList<>();
	protected final @NonNull Set<@NonNull EPackage> mmPackages;

	public CompleteOCLLoader(@NonNull EnvironmentFactory environmentFactory) {
		this.ocl = OCLInternal.newInstance((EnvironmentFactoryInternal)environmentFactory);
		this.mmPackages = new HashSet<>();
	}

	public void dispose() {
		ocl.dispose();
	}

	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return ocl.getEnvironmentFactory();
	}

	public @NonNull MetamodelManager getMetamodelManager() {
		return ocl.getMetamodelManager();
	}

	public boolean loadMetamodels() {
		for (Resource resource : ocl.getResourceSet().getResources()) {
			assert resource != null;
			External2AS ecore2as = Ecore2AS.findAdapter(resource, ocl.getEnvironmentFactory());
			if (ecore2as == null) {			// Pivot has its own validation
				for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					EClass eClass = eObject.eClass();
					if (eClass != null) {
						EPackage mmPackage = eClass.getEPackage();
						if (mmPackage != null) {
							mmPackages.add(mmPackage);
						}
					}
				}
 			}
		}
		Set<Resource> mmResources = new HashSet<Resource>();
		for (@NonNull EPackage mmPackage : mmPackages) {
			Resource mmResource = EcoreUtil.getRootContainer(mmPackage).eResource();
			if (mmResource != null) {
				mmResources.add(mmResource);
			}
		}
		for (Resource mmResource : mmResources) {
			assert mmResource != null;
			try {
				Element pivotModel = ocl.getEnvironmentFactory().loadResource(mmResource, null);
				if (pivotModel != null) {
					List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> errors = pivotModel.eResource().getErrors();
					assert errors != null;
					String message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
					if (message != null) {
						return error("Failed to load Pivot from '" + mmResource.getURI(), message);
					}
				}
				else {
					return error("Failed to load Pivot from '" + mmResource.getURI(), "");
				}
			} catch (ParserException e) {
				return error("Failed to load Pivot from '" + mmResource.getURI(), e.getMessage());
			}
		}
		return true;
	}

	protected abstract boolean error(@NonNull String primaryMessage, @Nullable String detailMessage);

	public void installPackages() {
		//
		//	Install validation for all the complemented packages
		//
		ResourceSet resourceSet = ocl.getEnvironmentFactory().getResourceSet();
		ValidationRegistryAdapter localValidationRegistry = ValidationRegistryAdapter.getAdapter(resourceSet);
		PivotEObjectValidator extraEValidator = new PivotEObjectValidator(oclModels);
		for (EPackage mmPackage : mmPackages) {
			localValidationRegistry.add(mmPackage, extraEValidator);
		}
	}

	public boolean loadDocument(@NonNull URI oclURI) {
		return loadDocument(oclURI, null);
	}

	public boolean loadDocument(@NonNull URI oclURI, @Nullable StringBuilder sErrors) {
		Resource resource = loadResource(oclURI, sErrors);
		if (resource == null) {
			return false;
		}
		//
		//	Identify the packages which the Complete OCL document complements.
		//
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof org.eclipse.ocl.pivot.Package) {
				org.eclipse.ocl.pivot.Package aPackage = metamodelManager.getPrimaryPackage((org.eclipse.ocl.pivot.Package)eObject);
				if (aPackage instanceof PivotObjectImpl) {
					EObject mmPackage = ((PivotObjectImpl)aPackage).getESObject();
					if (mmPackage instanceof EPackage) {
						mmPackages.add((EPackage)mmPackage);
					}
				}
			}
			else if (eObject instanceof Type) {
				tit.prune();
			}
			else if (eObject instanceof Model) {
				oclModels .add((Model)eObject);
			}
		}
		return true;
	}

	/**
	 * Load the Xtext resource from oclURI, then convert it to a pivot representation and return it.
	 * Return null after invoking error() to display any errors in a pop-up.
	 */
	public Resource loadResource(@NonNull URI oclURI) {
		return loadResource(oclURI, null);
	}

	/**
	 * Load the Xtext resource from oclURI, then convert it to a pivot representation and return it.
	 * If sErrors is null, return null after invoking error() to display any errors in a pop-up.
	 * Else returns error messages to sErrors.
	 */
	public Resource loadResource(@NonNull URI oclURI, @Nullable StringBuilder sErrors) {
		CompleteOCLStandaloneSetup.init();
		ResourceSet resourceSet = ocl.getResourceSet();
		Resource resource = null;
		URI loadURI = oclURI;
		String message2 = null;
		try {
			resource = resourceSet.getResource(loadURI, true);
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
				loadURI = retryURI;
				resource = resourceSet.getResource(retryURI, true);
			}
			else {
				throw e;
			}
		}
		BaseCSResource xtextResource = null;
		if (resource instanceof BaseCSResource) {
			xtextResource = (BaseCSResource) resource;
		}
		else {
			message2 = "An " + resource.getClass().getName() + " loaded rather than the required BaseCSResource.";
		}
		if ((xtextResource != null) && (message2 == null)) {
			List<Resource.@NonNull Diagnostic> errors = xtextResource.getErrors();
			assert errors != null;
			message2 = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
			if (message2 == null) {
				CS2AS cs2as = xtextResource.getCS2AS(getEnvironmentFactory());
				Resource asResource = cs2as.getASResource();
				errors = asResource.getErrors();
				assert errors != null;
				message2 = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
				if (message2 == null) {
					return asResource;
				}
			}
		}
		assert message2 != null;
		String message1 = "Failed to load '" + loadURI + "' as an OCL document.";
		if (sErrors != null) {
			sErrors.append(message1);
			sErrors.append("\n");
			sErrors.append(message2);
		}
		else {
			error(message1, message2);
		}
		return null;
	}
}
