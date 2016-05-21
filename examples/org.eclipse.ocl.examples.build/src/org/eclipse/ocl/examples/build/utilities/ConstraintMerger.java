/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.ecore.es2as.Ecore2AS;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;

/**
 * Merges a specified <tt>uri</tt> into a designated <tt>modelSlot</tt>.
 */
public class ConstraintMerger extends AbstractProjectComponent
{
	private Logger log = Logger.getLogger(getClass());	
	protected String uri;
	protected String invariantPrefix;

	public ConstraintMerger() {
		OCLstdlib.install();
		CompleteOCLStandaloneSetup.doSetup();
	}

	@Override
	public void checkConfiguration(Issues issues) {
		super.checkConfiguration(issues);
		if (uri == null) {
			issues.addError(this, "uri not specified.");
		}
	}

	public String getUri() {
		return uri;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		StandaloneProjectMap.IProjectDescriptor projectDescriptor = ClassUtil.nonNullState(getProjectDescriptor());
		assert uri != null;
		URI inputURI = projectDescriptor.getPlatformResourceURI(uri);
		log.info("Merging '" + inputURI + "'");
		Resource ecoreResource = (Resource) ctx.get(getModelSlot());
		EPackage ecorePivotPackage = (EPackage) ecoreResource.getContents().get(0);
		final String pivotNsURI = ClassUtil.nonNullState(ecorePivotPackage.getNsURI());
//		IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(URI.createURI(pivotNsURI));
//		packageDescriptor.setUseModel(true, null);				// Hide packages installed by CompleteOCLStandaloneSetup
			
		OCLInternal ocl = OCLInternal.newInstance();
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		ResourceSet asResourceSet = metamodelManager.getASResourceSet();
//		metamodelManager.setLibraryLoadInProgress(true);
		ocl.getResourceSet().getResources().add(ecoreResource);		// Don't load another copy
		ocl.getStandardLibrary().setDefaultStandardLibraryURI(pivotNsURI);
		StandardLibraryContribution.REGISTRY.put(pivotNsURI, new OCLstdlib.Loader());
//		metamodelManager.getBooleanType();
		for (EObject eObject : ecoreResource.getContents()) {
			if (eObject instanceof EPackage) {
				EPackage ePackage = (EPackage) eObject;
				ClassUtil.getMetamodelAnnotation(ePackage); // Install EAnnotation
			}
		}
		Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, ocl.getEnvironmentFactory());
		Model pivotModel = ecore2as.getASModel();
//		metamodelManager.setPivotMetamodel(pivotModel.getNestedPackage().get(0));
//		metamodelManager.setLibraryLoadInProgress(false);
		Resource asResource = ClassUtil.nonNullState(pivotModel.eResource());
//FIXME		diagnoseErrors(asResource);
//		URI fileURI = URI.createPlatformResourceURI(uri, true);
		try {
			@SuppressWarnings("unused")CSResource csResource = ocl.getCSResource(inputURI);
			ResourceUtils.checkResourceSet(asResourceSet);
//			CS2ASResourceAdapter cs2as = CS2ASResourceAdapter.getAdapter(xtextResource, metamodelManager);
//			Resource oclResource = cs2as.getPivotResource(xtextResource);
//			Set<Resource> primaryPivotResources = new HashSet<Resource>();
//			Set<Resource> libraryPivotResources = new HashSet<Resource>();
//			Iterable<org.eclipse.ocl.pivot.Package> somePackages = metamodelManager.getPackageManager().getAllPackages();
//			identifyResources(metamodelManager, somePackages, primaryPivotResources, libraryPivotResources);
//			Set<Resource> secondaryPivotResources = new HashSet<Resource>(metamodelManager.getPivotResourceSet().getResources());
//			secondaryPivotResources.removeAll(primaryPivotResources);
//			secondaryPivotResources.removeAll(libraryPivotResources);
//			primaryPivotResources.removeAll(libraryPivotResources);
//			for (Resource secondaryPivotResource : secondaryPivotResources) {
			for (Resource resource : metamodelManager.getASResourceSet().getResources()) {
				if (resource != asResource) {
					for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
						EObject eObject = tit.next();
						if ((eObject instanceof Library) || (eObject instanceof Orphanage)) {
							tit.prune();
						}
						else if (eObject instanceof org.eclipse.ocl.pivot.Class) {
							org.eclipse.ocl.pivot.Class mergeType = (org.eclipse.ocl.pivot.Class)eObject;
							CompleteClass completeClass = metamodelManager.getCompleteClass(mergeType);
							for (org.eclipse.ocl.pivot.Class partialClass : completeClass.getPartialClasses()) {
								if (partialClass != null) {
									if (partialClass.eResource() == asResource) {
										mergeType(metamodelManager, partialClass, mergeType);
										break;
									}
								}
							}
							tit.prune();
						}
					}
				}
			}
//			List<Resource> resources = resourceSet.getResources();
			URI ecoreURI = ClassUtil.nonNullState(ecoreResource.getURI());
//			for (int i = resources.size() - 1; i >= 0; --i) {
//				Resource resource = resources.get(i);
//				if (ecoreURI.equals(resource.getURI())) {
//					resources.remove(resource);
//				}
//			}
//				System.out.println("AS2Ecore " + asResource.getURI());
			Map<String,Object> options = new HashMap<String,Object>();
			options.put(AS2Ecore.OPTION_SUPPRESS_DUPLICATES, true);
			options.put(AS2Ecore.OPTION_INVARIANT_PREFIX, invariantPrefix);
			Resource ecoreResource2 = AS2Ecore.createResource(metamodelManager.getEnvironmentFactory(), asResource, ecoreURI, options);
			ctx.set(getModelSlot(), ecoreResource2);
			projectDescriptor.configure(ecoreResource2.getResourceSet(), StandaloneProjectMap.LoadBothStrategy.INSTANCE, null);
			
//			for (EObject eObject : oclResource.getContents()) {
//				if (eObject instanceof org.eclipse.ocl.pivot.Package) {
//					org.eclipse.ocl.pivot.Package pivotPackage = (org.eclipse.ocl.pivot.Package)eObject;
//					PackageTracker packageTracker = metamodelManager.getPackageTracker(pivotPackage);
//					PackageServer packageServer = packageTracker.getPackageServer();
//					packageServer.removePackage(pivotPackage);
//				}
//			}
//			EcoreUtil.resolveAll(resourceSet);
//			ResourceUtils.checkResourceSet(resourceSet);
//			ctx.set(getModelSlot(), resource);
			ocl.getResourceSet().getResources().remove(ecoreResource2);
			ocl.dispose();
		} catch (IOException e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}

	protected void mergeType(@NonNull MetamodelManager metamodelManager, org.eclipse.ocl.pivot.@NonNull Class primaryType, org.eclipse.ocl.pivot.@NonNull Class mergeType) {
		List<Constraint> mergeInvariants = mergeType.getOwnedInvariants();
		List<Constraint> primaryInvariants = primaryType.getOwnedInvariants();
		for (Constraint mergeInvariant : new ArrayList<Constraint>(mergeInvariants)) {
			mergeInvariant.setIsCallable(true);
			PivotUtilInternal.resetContainer(mergeInvariant);
			primaryInvariants.add(mergeInvariant);
		}
		List<Property> mergeProperties = mergeType.getOwnedProperties();
		if (mergeProperties.size() > 0) {
			List<Property> primaryProperties = primaryType.getOwnedProperties();
			for (@SuppressWarnings("null")@NonNull Property mergeProperty : new ArrayList<Property>(mergeProperties)) {
				Property primaryProperty = metamodelManager.getPrimaryProperty(mergeProperty);
				if (primaryProperty != mergeProperty) {			// If merge needed
					LanguageExpression pivotDefaultExpression = mergeProperty.getOwnedExpression();
					LanguageExpression primaryDefaultExpression = primaryProperty.getOwnedExpression();
					if ((primaryDefaultExpression == null) && (pivotDefaultExpression != null)) {
						primaryProperty.setOwnedExpression(pivotDefaultExpression);
					}
				}
				else											// Else simple promotion
				{
					primaryProperties.add(mergeProperty);
				}
			}
		}
		List<Operation> mergeOperations = mergeType.getOwnedOperations();
		if (mergeOperations.size() > 0) {
			List<Operation> primaryOperations = primaryType.getOwnedOperations();
			for (@SuppressWarnings("null")@NonNull Operation mergeOperation : new ArrayList<Operation>(mergeOperations)) {
				Operation primaryOperation = metamodelManager.getPrimaryOperation(mergeOperation);
				if (primaryOperation != mergeOperation) {		// If merge needed
					LanguageExpression pivotBodyExpression = mergeOperation.getBodyExpression();
					LanguageExpression primaryBodyExpression = primaryOperation.getBodyExpression();
					if ((primaryBodyExpression == null) && (pivotBodyExpression != null)) {
						PivotUtilInternal.resetContainer(pivotBodyExpression);
						primaryOperation.setBodyExpression(pivotBodyExpression);
					}
				}
				else											// Else simple promotion
				{
					PivotUtilInternal.resetContainer(mergeOperation);
					primaryOperations.add(mergeOperation);
				}
			}
		}
	}

/*	public void identifyResources(MetamodelManager metamodelManager, Iterable<org.eclipse.ocl.pivot.Package> somePackages,
			Set<Resource> primaryPivotResources, Set<Resource> libraryPivotResources) {
		for (org.eclipse.ocl.pivot.Package pPackage : somePackages) {
			if (pPackage instanceof Library) {
				libraryPivotResources.add(pPackage.eResource());
			}
			else {
				for (Type pType : pPackage.getOwnedType()) {
					TypeTracker typeTracker = metamodelManager.getTypeTracker(pType);
					if (typeTracker instanceof TypeServer) {
						primaryPivotResources.add(pPackage.eResource());
						break;
					}
				}
				identifyResources(metamodelManager, pPackage.getNestedPackage(),  primaryPivotResources, libraryPivotResources);
			}
		}
	} */

	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * Define a prefix such as "validate" for all invariant operation names.
	 */
	public void setInvariantPrefix(String invariantPrefix) {
		this.invariantPrefix = invariantPrefix;
	}
}
