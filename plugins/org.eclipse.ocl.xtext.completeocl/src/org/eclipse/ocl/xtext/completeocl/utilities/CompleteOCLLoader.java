/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
import org.eclipse.ocl.pivot.CompletePackage;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.delegate.DelegateInstaller;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.SemanticException;
import org.eclipse.ocl.pivot.validation.ValidationRegistryAdapter;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.CSI2ASMapping;
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

	/**
	 * The prevailing OCL context.
	 */
	protected final @NonNull OCLInternal ocl;

	/**
	 * The currently loaded Complete OCL AS models that a distinct PivotEObjectValidator (e.g. UML but not Ecore).
	 */
	protected final @NonNull List<@NonNull Model> oclModels = new ArrayList<>();
	protected final @NonNull Map<@NonNull EPackage, @NonNull CompletePackage> mmPackage2completePackage = new HashMap<>();

	public CompleteOCLLoader(@NonNull EnvironmentFactory environmentFactory) {
		this.ocl = OCLInternal.newInstance((EnvironmentFactoryInternal)environmentFactory);
	}

	public void dispose() {
		ocl.dispose();
	}

	protected abstract boolean error(@NonNull String primaryMessage, @Nullable String detailMessage);

	public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return ocl.getEnvironmentFactory();
	}

	public @NonNull MetamodelManager getMetamodelManager() {
		return ocl.getMetamodelManager();
	}

	public boolean loadMetamodels() {
		return loadMetamodels(null);
	}

	public boolean loadMetamodels(@Nullable StringBuilder sErrors) {
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		List<@NonNull Resource> esResources = ocl.getResourceSet().getResources();
		for (int index = 0; index < esResources.size(); index++) {		// Tolerate 'concurrent' profile resolution
			@NonNull Resource resource = esResources.get(index);
			External2AS ecore2as = External2AS.findAdapter(resource, environmentFactory);
			if (ecore2as == null) {			// Pivot has its own validation
				for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					EClass eClass = eObject.eClass();
					if (eClass != null) {																		// Skip UML-Ecore EAnnotations
						EPackage mmPackage = eClass.getEPackage();
						if ((mmPackage != null) && !mmPackage2completePackage.containsKey(mmPackage)) {			// XXX http://www.eclipse.org/emf/2002/Ecore may be a late discovery
							try {
								org.eclipse.ocl.pivot.Package asPackage = environmentFactory.getASOf(org.eclipse.ocl.pivot.Package.class, mmPackage);
								assert asPackage != null;
								CompletePackage completePackage = ocl.getMetamodelManager().getCompletePackage(asPackage);
								mmPackage2completePackage.put(mmPackage, completePackage);
							} catch (ParserException e) {
								// XXX Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
 			}
		}
		Set<@NonNull Resource> mmResources = new HashSet<>();
		for (@NonNull EPackage mmPackage : mmPackage2completePackage.keySet()) {
			Resource mmResource = EcoreUtil.getRootContainer(mmPackage).eResource();
			if (mmResource != null) {
				mmResources.add(mmResource);
			}
		}
		boolean allOk = true;
		for (@NonNull Resource  mmResource : mmResources) {
			String message2 = null;
			try {
				Element pivotModel = environmentFactory.loadResource(mmResource, null);
				if (pivotModel != null) {
					List<org.eclipse.emf.ecore.resource.Resource.Diagnostic> errors = pivotModel.eResource().getErrors();
					assert errors != null;
					String message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
					if (message != null) {
						message2 = message;
					}
				}
				else {
					message2 = "";
				}
			} catch (ParserException e) {
				message2 = e.getMessage();
			}
			if (message2 != null) {
				allOk = false;
				String message1 = "Failed to load Pivot from '" + mmResource.getURI();
				if (sErrors != null) {
					if (sErrors.length() > 0) {
						sErrors.append("\n");
					}
					sErrors.append(message1 + message2);
				}
				else {
					return error(message1, message2);
				}
			}
		}
		return allOk;
	}

	/**
	 * Install each of the oclURIs documents, then loadMetamodels and finally installPackages.
	 *
	 * Returns a non-null String describing any problems.
	 * @throws SemanticException
	 */
	public @Nullable String installDocuments(@NonNull URI... oclURIs) {
		StringBuilder s = new StringBuilder();
		if (oclURIs != null) {
			for (URI oclURI : oclURIs) {
				if (!loadDocument(oclURI, s)) {
					return s.toString();
				}
			}
		}
		if (!loadMetamodels(s)) {
			return s.toString();
		}
		installPackages();
		return null;
	}

	public void installPackages() {
		//
		//	Install validation for all the complemented packages that need a distinct PivotEObjectValidator (e.g. UML but not Ecore).
		//
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		ResourceSet resourceSet = environmentFactory.getResourceSet();
		@SuppressWarnings("unused") ValidationRegistryAdapter localValidationRegistry = ValidationRegistryAdapter.getAdapter(resourceSet);
		for (@NonNull EPackage mmPackage : mmPackage2completePackage.keySet()) {
			CompletePackage completePackage = mmPackage2completePackage.get(mmPackage);
			assert completePackage != null;
		}
	}

	public boolean loadDocument(@NonNull URI oclURI) {
		return loadDocument(oclURI, null);
	}

	public boolean loadDocument(@NonNull URI oclURI, @Nullable StringBuilder sErrors) {
		Resource asResource = loadResource(oclURI, sErrors);
		if (asResource == null) {
			return false;
		}
		//
		//	Identify the packages which the Complete OCL document complements.
		//
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject asElement = tit.next();
			if (asElement instanceof org.eclipse.ocl.pivot.Package) {				// Supertypes/referenced types
				CompletePackage completePackage = metamodelManager.getCompletePackage((org.eclipse.ocl.pivot.Package)asElement);
				org.eclipse.ocl.pivot.Package aPackage = completePackage.getPrimaryPackage();
			//	if (aPackage instanceof PivotObjectImpl) {
					EObject mmPackage = aPackage.getESObject();
					if (mmPackage instanceof EPackage) {
						mmPackage2completePackage.put((EPackage)mmPackage, completePackage);
					}
			//	}
			}
			else if (asElement instanceof Type) {
				tit.prune();
			}
			else if (asElement instanceof Model) {
				oclModels.add((Model)asElement);
			}
		}
		return true;
	}

	/**
	 * Load the Xtext resource from oclURI, then convert it to a pivot representation and return it.
	 * Return null after invoking error() to display any errors in a pop-up.
	 */
	public Resource loadResource(@NonNull URI oclURI) throws SemanticException {
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
	/*	ASResource asResource = xtextResource.getASResource();
		errors = asResource.getErrors();
		assert errors != null;
		message = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
		if (message != null) {
			error("Failed to load '" + oclURI + "' as an OCL document.", message);
			return null;
		} */
		if ((xtextResource != null) && (message2 == null)) {
			List<Resource.@NonNull Diagnostic> errors = xtextResource.getErrors();
			assert errors != null;
			message2 = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
			if (message2 == null) {
				EnvironmentFactoryInternal environmentFactory = getEnvironmentFactory();
				CS2AS cs2as = xtextResource.getCS2AS(environmentFactory);
				ASResource asResource = cs2as.getASResource();
				errors = asResource.getErrors();
				assert errors != null;
				message2 = PivotUtil.formatResourceDiagnostics(errors, "", "\n");
				if (message2 == null) {
					DelegateInstaller delegateInstaller = new DelegateInstaller( environmentFactory, null);
					try {
						delegateInstaller.installCompleteOCLDelegates(environmentFactory.getUserResourceSet(), asResource);
						return asResource;
					} catch (SemanticException e) {
						// XXX Auto-generated catch block
						e.printStackTrace();
						message2 = e.getMessage();
					}
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

	public void uninstallDocuments(@NonNull URI... oclURIs) {
		if (oclURIs != null) {
			for (URI oclURI : oclURIs) {
				unloadDocument(oclURI);
			}
		}
	//	if (!unloadMetamodels(s)) {
	//		return s.toString();
	//	}
	//	uninstallPackages();
	//	return null;
	}

	public @NonNull ASResource unloadDocument(@NonNull URI oclURI) {
		EnvironmentFactoryInternal environmentFactory = getEnvironmentFactory();
		CSResource csResource = (CSResource)environmentFactory.getResourceSet().getResource(oclURI, false);
		assert csResource != null;
	//	ASResource asResource = unloadResource(csResource);
		CSI2ASMapping iCSI2ASMapping = (CSI2ASMapping) environmentFactory.getCSI2ASMapping();
		assert iCSI2ASMapping != null;
		ASResource asResource = iCSI2ASMapping.getASResource(csResource);
		assert asResource != null;
		DelegateInstaller delegateInstaller = new DelegateInstaller(environmentFactory, null);
		delegateInstaller.uninstallCompleteOCLDelegates(environmentFactory.getUserResourceSet(), asResource);
		//
		//	XXX Identify the packages which the Complete OCL document complements.
		//
		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject asElement = tit.next();
			if (asElement instanceof org.eclipse.ocl.pivot.Package) {				// Supertypes/referenced types
				CompletePackage completePackage = metamodelManager.getCompletePackage((org.eclipse.ocl.pivot.Package)asElement);
				org.eclipse.ocl.pivot.Package aPackage = completePackage.getPrimaryPackage();
				EObject mmPackage = aPackage.getESObject();
				if (mmPackage instanceof EPackage) {
					mmPackage2completePackage.remove(mmPackage);
				}
			}
			else if (asElement instanceof Type) {
				tit.prune();
			}
			else if (asElement instanceof Model) {
				oclModels.remove(asElement);
			}
		}
		return asResource;
	}

	/**
	 * Unload the CompleteOCL CS resource and its AS resource counterpart.
	 *
	public @NonNull ASResource unloadResource(@NonNull CSResource csResource) {
		EnvironmentFactoryInternal environmentFactory = getEnvironmentFactory();
		CSI2ASMapping iCSI2ASMapping = (CSI2ASMapping) environmentFactory.getCSI2ASMapping();
		assert iCSI2ASMapping != null;
		ASResource asResource = iCSI2ASMapping.getASResource(csResource);
		assert asResource != null;
		DelegateInstaller delegateInstaller = new DelegateInstaller(environmentFactory, null);
		delegateInstaller.uninstallCompleteOCLDelegates(asResource);
		return asResource;
	} */
}
