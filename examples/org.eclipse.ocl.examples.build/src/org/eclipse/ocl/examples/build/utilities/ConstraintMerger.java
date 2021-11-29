/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
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
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.OCLInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.resource.ProjectManager.IResourceDescriptor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLStandaloneSetup;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Merges a specified <tt>uri</tt> into a designated <tt>modelSlot</tt>.
 */
public class ConstraintMerger extends AbstractProjectComponent
{
	private Logger log = Logger.getLogger(getClass());
	protected @NonNull List<@NonNull String> ecoreURIs = new ArrayList<>();
	protected String libraryURI = null;
	protected @NonNull List<@NonNull String> oclURIs = new ArrayList<>();
	protected String invariantPrefix;

	public ConstraintMerger() {
		OCLstdlib.install();
		CompleteOCLStandaloneSetup.doSetup();
	}

	public void addEcoreURI(String ecoreURI) {
		assert ecoreURI != null;
		ecoreURIs.add(ecoreURI);
	}

	public void addOclURI(String oclURI) {
		assert oclURI != null;
		oclURIs.add(oclURI);
	}

	@Override
	public void checkConfiguration(Issues issues) {
		super.checkConfiguration(issues);
		if (ecoreURIs.size() <= 0) {
			issues.addError(this, "no ecoreURI specified.");
		}
		if (oclURIs.size() <= 0) {
			issues.addError(this, "no oclURI specified.");
		}
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		ResourceSet resourceSet = getResourceSet();
		StandaloneProjectMap.IProjectDescriptor projectDescriptor = ClassUtil.nonNullState(getProjectDescriptor());
		//		Resource ecoreResource = (Resource) ctx.get(getModelSlot());
		//		EPackage ecorePivotPackage = (EPackage) ecoreResource.getContents().get(0);
		//		final String pivotNsURI = ClassUtil.nonNullState(ecorePivotPackage.getNsURI());
		OCLInternal ocl = OCLInternal.newInstance(resourceSet);
		EnvironmentFactoryInternal environmentFactory = ocl.getEnvironmentFactory();
		MetamodelManagerInternal metamodelManager = ocl.getMetamodelManager();
		ResourceSet asResourceSet = metamodelManager.getASResourceSet();
		//		ocl.getResourceSet().getResources().add(ecoreResource);		// Don't load another copy
		String libraryURI2 = libraryURI;
		if (libraryURI2 != null) {
			ocl.getStandardLibrary().setDefaultStandardLibraryURI(libraryURI2);
			StandardLibraryContribution.REGISTRY.put(libraryURI2, new OCLstdlib.Loader());
		}


		//
		try {
			for (@NonNull String ecoreURI : ecoreURIs) {
				URI uri = projectDescriptor.getPlatformResourceURI(ecoreURI);
				log.info("Loading " + uri);
				Resource ecoreResource = resourceSet.getResource(uri, true);
				EcoreUtil.resolveAll(ecoreResource);
				ResourceUtils.checkResource(ecoreResource);
				for (EObject eObject : ecoreResource.getContents()) {
					if (eObject instanceof EPackage) {
						EPackage ePackage = (EPackage) eObject;
						ClassUtil.getMetamodelAnnotation(ePackage); // Install EAnnotation
					}
				}
				Ecore2AS ecore2as = Ecore2AS.getAdapter(ecoreResource, environmentFactory);
				Model pivotModel = ecore2as.getASModel();
				ASResource asResource = ClassUtil.nonNullState((ASResource)pivotModel.eResource());
				ResourceUtils.checkResource(asResource);
			}
			EcoreUtil.resolveAll(resourceSet);
			ResourceUtils.checkResourceSet(resourceSet);
			Set<@NonNull Resource> primaryASResources = Sets.newHashSet(ClassUtil.nullFree(asResourceSet.getResources()));
			//
			for (@NonNull String oclURI : oclURIs) {
				URI uri = projectDescriptor.getPlatformResourceURI(oclURI);
				log.info("Merging " + uri);
				CSResource csResource = ocl.getCSResource(uri);
				ResourceUtils.checkResourceSet(ocl.getResourceSet());
				@SuppressWarnings("unused") Resource oclResource = csResource.getASResource();
				ResourceUtils.checkResourceSet(asResourceSet);
			}
			ResourceUtils.checkResourceSet(asResourceSet);
			//
			Set<@NonNull Resource> modifiedPrimaryASResources = new HashSet<>();
			Map<@NonNull CompleteClass, @NonNull List<org.eclipse.ocl.pivot.Class>> completeClass2mergeTypes = new HashMap<>();
			for (Resource secondaryASResource : asResourceSet.getResources()) {
				if (!primaryASResources.contains(secondaryASResource)) {
					for (TreeIterator<EObject> tit = secondaryASResource.getAllContents(); tit.hasNext(); ) {
						EObject eObject = tit.next();
						if (eObject instanceof Library) {
							tit.prune();
						}
						else if (eObject instanceof Orphanage) {
							tit.prune();
						}
						else if (eObject instanceof org.eclipse.ocl.pivot.Class) {
							org.eclipse.ocl.pivot.Class mergeType = (org.eclipse.ocl.pivot.Class)eObject;
							CompleteClass completeClass = metamodelManager.getCompleteClass(mergeType);
							List<org.eclipse.ocl.pivot.Class> mergeTypes = completeClass2mergeTypes.get(completeClass);
							if (mergeTypes == null) {
								mergeTypes = new ArrayList<>();
								completeClass2mergeTypes.put(completeClass, mergeTypes);
							}
							mergeTypes.add(mergeType);
							tit.prune();
						}
					}
				}
			}
			for (@NonNull CompleteClass completeClass : completeClass2mergeTypes.keySet()) {
				List<org.eclipse.ocl.pivot.Class> mergeTypes = completeClass2mergeTypes.get(completeClass);
				assert mergeTypes != null;
				boolean merged = false;
				for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
					Resource primaryASResource = PivotUtil.getResource(partialClass);
					if (primaryASResources.contains(primaryASResource)) {
						modifiedPrimaryASResources.add(primaryASResource);
						for (org.eclipse.ocl.pivot.@NonNull Class mergeType : mergeTypes) {
							mergeType(metamodelManager, partialClass, mergeType);
						}
						merged = true;
						break;
					}
				}
				if (!merged) {
					// FIXME migrate class
				}
			}
			//
			Map<@NonNull String, @Nullable Object> options = new HashMap<>();
			options.put(AS2Ecore.OPTION_SUPPRESS_DUPLICATES, true);
			options.put(AS2Ecore.OPTION_INVARIANT_PREFIX, invariantPrefix);
			ProjectManager projectManager = StandaloneProjectMap.findAdapter(resourceSet);
			resourceSet.getResources().clear();
			for (@NonNull Resource modifiedPrimaryASResource : modifiedPrimaryASResources) {
				Model asModel = PivotUtil.getModel(modifiedPrimaryASResource);
				String externalURI = asModel.getExternalURI();
				URI ecoreURI = URI.createURI(externalURI);
				//				Resource oldEcoreResource = resourceSet.getResource(ecoreURI, false);
				//				if (oldEcoreResource != null) {
				//
				//				}
				if (projectManager != null) {
					IResourceDescriptor resourceDescriptor = null;
					resourceDescriptor = projectManager.getResourceDescriptor(ecoreURI);
					if (resourceDescriptor != null) {
						resourceDescriptor.unload(resourceSet);
						resourceDescriptor.configure(resourceSet,
							StandaloneProjectMap.CreateStrategy.INSTANCE,
							StandaloneProjectMap.MapToFirstConflictHandlerWithLog.INSTANCE);
					}
				}
				Resource newEcoreResource = AS2Ecore.createResource(environmentFactory, modifiedPrimaryASResource, ecoreURI, options);
				//				projectDescriptor.configure(ecoreResource2.getResourceSet(), StandaloneProjectMap.LoadBothStrategy.INSTANCE, null);
				XMIUtil.assignIds(newEcoreResource, new XMIUtil.StructuralENamedElementIdCreator(), null);
				Map<Object, Object> saveOptions = XMIUtil.createSaveOptions();
				XMIUtil.retainLineWidth(saveOptions, newEcoreResource);
				newEcoreResource.save(saveOptions); //getSaveOptions());
				//				resourceSet.getResources().remove(ecoreResource2);
			}
			ocl.dispose(true);
		} catch (IOException e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}

	protected void mergeType(@NonNull MetamodelManager metamodelManager, org.eclipse.ocl.pivot.@NonNull Class primaryType, org.eclipse.ocl.pivot.@NonNull Class mergeType) {
		List<Constraint> mergeInvariants = mergeType.getOwnedInvariants();
		List<Constraint> primaryInvariants = primaryType.getOwnedInvariants();
		for (Constraint mergeInvariant : new ArrayList<>(mergeInvariants)) {
			mergeInvariant.setIsCallable(true);
			PivotUtilInternal.resetContainer(mergeInvariant);
			primaryInvariants.add(mergeInvariant);
		}
		List<Property> mergeProperties = mergeType.getOwnedProperties();
		if (mergeProperties.size() > 0) {
			List<Property> primaryProperties = primaryType.getOwnedProperties();
			for (@SuppressWarnings("null")@NonNull Property mergeProperty : new ArrayList<>(mergeProperties)) {
				Property primaryProperty = metamodelManager.getPrimaryProperty(mergeProperty);
				if (primaryProperty != mergeProperty) {			// If merge needed
					LanguageExpression pivotDefaultExpression = mergeProperty.getOwnedExpression();
					LanguageExpression primaryDefaultExpression = primaryProperty.getOwnedExpression();
					if ((primaryDefaultExpression == null) && (pivotDefaultExpression != null)) {
						primaryProperty.setOwnedExpression(pivotDefaultExpression);
					}
					mergeComments(primaryProperty, mergeProperty);
				}
				else											// Else simple promotion
				{
					//					boolean b1 = primaryProperty.isIsImplicit();
					//					boolean b2 = mergeProperty.isIsImplicit();
					PivotUtilInternal.resetContainer(mergeProperty);
					primaryProperties.add(mergeProperty);
				}
			}
		}
		List<Operation> mergeOperations = mergeType.getOwnedOperations();
		if (mergeOperations.size() > 0) {
			List<Operation> primaryOperations = primaryType.getOwnedOperations();
			for (@SuppressWarnings("null")@NonNull Operation mergeOperation : new ArrayList<>(mergeOperations)) {
				Operation primaryOperation = metamodelManager.getPrimaryOperation(mergeOperation);
				if (primaryOperation != mergeOperation) {		// If merge needed
					LanguageExpression pivotBodyExpression = mergeOperation.getBodyExpression();
					LanguageExpression primaryBodyExpression = primaryOperation.getBodyExpression();
					if ((primaryBodyExpression == null) && (pivotBodyExpression != null)) {
						PivotUtilInternal.resetContainer(pivotBodyExpression);
						primaryOperation.setBodyExpression(pivotBodyExpression);
					}
					mergeComments(primaryOperation, mergeOperation);
				}
				else											// Else simple promotion
				{
					PivotUtilInternal.resetContainer(mergeOperation);
					primaryOperations.add(mergeOperation);
				}
			}
		}
	}

	public void mergeComments(@NonNull Element primaryElement, @NonNull Element mergeElement) {
		if (primaryElement.getOwnedComments().isEmpty()) {
			Iterable<@NonNull Comment> mergeComments = PivotUtil.getOwnedComments(mergeElement);
			if (!Iterables.isEmpty(mergeComments)) {
				for (Comment mergeComment : Lists.newArrayList(mergeComments)) {
					PivotUtilInternal.resetContainer(mergeComment);
					primaryElement.getOwnedComments().add(mergeComment);
				}
			}
		}
	}

	//	public void setUri(String uri) {
	//		this.uri = uri;
	//	}

	/**
	 * Define a prefix such as "validate" for all invariant operation names.
	 */
	public void setInvariantPrefix(String invariantPrefix) {
		this.invariantPrefix = invariantPrefix;
	}

	/**
	 * Define a URI to bw loade as the OCL Standard Library.
	 */
	public void setLibraryURI(String libraryURI) {
		this.libraryURI = libraryURI;
	}
}
