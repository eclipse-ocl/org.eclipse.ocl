/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *	E.D.Willink (CEA LIST) - Bug 399252
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.CompleteStandardLibrary;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.compatibility.EMF_2_9;
import org.eclipse.ocl.pivot.internal.complete.CompleteModelInternal;
import org.eclipse.ocl.pivot.internal.complete.PartialModels;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore;
import org.eclipse.ocl.pivot.internal.ecore.as2es.AS2Ecore.InverseConversion;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactoryRegistry;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.pivot.utilities.TracingOption;

public class PivotMetamodelManager implements MetamodelManager, Adapter.Internal
{
	private static final Logger logger = Logger.getLogger(PivotMetamodelManager.class);

	/**
	 * @since 1.3
	 */
	public static final @NonNull TracingOption INSTALL_MODEL = new TracingOption(PivotPlugin.PLUGIN_ID, "install/model");

	/**
	 * Leak debugging aid. Set non-null to diagnose MetamodelManager construction and finalization.
	 */
	public static WeakHashMap<@NonNull PivotMetamodelManager, @Nullable Object> liveMetamodelManagers = null;

	/**
	 * Return the non-null MetamodelManager for which resourceSet is an AS ResourceSet, or null if not an AS ResourceSet.
	 */
	public static @Nullable PivotMetamodelManager findAdapter(@NonNull ResourceSet asResourceSet) {
		@SuppressWarnings("null")@NonNull List<Adapter> eAdapters = asResourceSet.eAdapters();
		return ClassUtil.getAdapter(PivotMetamodelManager.class, eAdapters);
	}

	/**
	 * Return the non-null MetamodelManager for the asResourceSet.
	 */
	@Deprecated /* Use LocalThreadExecutor */
	public static @NonNull PivotMetamodelManager getAdapter(@NonNull ResourceSet asResourceSet) {
		@SuppressWarnings("null")@NonNull List<Adapter> eAdapters = asResourceSet.eAdapters();
		PivotMetamodelManager adapter = ClassUtil.getAdapter(PivotMetamodelManager.class, eAdapters);
		return ClassUtil.requireNonNull(adapter);
	}

	protected final @NonNull EnvironmentFactory environmentFactory;
	private final @NonNull CompleteStandardLibrary standardLibrary;

	/**
	 * The known packages.
	 */
	private final @NonNull CompleteModel completeModel;

	protected final @NonNull ResourceSet asResourceSet;

	/**
	 * Map of URI to external resource converter.
	 */
	private final @NonNull Map<URI, External2AS> external2asMap = new HashMap<>();

	private @Nullable Map<String, GenPackage> genPackageMap = null;

	/**
	 * Lazily computed, eagerly invalidated static analysis of the control flow within invariants and bodies.
	 */
	private @Nullable Map<@NonNull OCLExpression, @NonNull FlowAnalysis> oclExpression2flowAnalysis = null;

	private @Nullable Map<@NonNull URI, @NonNull External2AS> uri2es2as = null;

	/**
	 * Construct a MetamodelManager that will use environmentFactory to create its artefacts
	 * such as an asResourceSet to contain pivot copies of meta-models.
	 * @since 7.0
	 */
	public PivotMetamodelManager(@NonNull EnvironmentFactory environmentFactory, @NonNull ResourceSet asResourceSet) {
		this.environmentFactory = environmentFactory;
		this.asResourceSet = asResourceSet;
		List<Adapter> asResourceSetAdapters = asResourceSet.eAdapters();
		assert !asResourceSetAdapters.contains(this);
		asResourceSetAdapters.add(this);
		assert asResourceSetAdapters.contains(environmentFactory.getProjectManager());
		standardLibrary = environmentFactory.getStandardLibrary();
		completeModel = environmentFactory.getCompleteModel();
		//		System.out.println("ctor " + this);
		//		initializePivotResourceSet(asResourceSet);
		if (liveMetamodelManagers != null) {
			liveMetamodelManagers.put(this, null);
			PivotUtil.debugPrintln("Create " + NameUtil.debugSimpleName(this)
			+ " " + NameUtil.debugSimpleName(asResourceSet));
		}
	}

	@Override
	public void addExternal2AS(@NonNull External2AS es2as) {
		Map<@NonNull URI, @NonNull External2AS> uri2es2as2 = uri2es2as;
		if (uri2es2as2 == null) {
			uri2es2as = uri2es2as2 = new HashMap<>();
		}
		URI uri = es2as.getURI();
		External2AS oldES2AS = uri2es2as2.put(uri, es2as);
		assert (oldES2AS == null) || (es2as instanceof InverseConversion);// && !(oldES2AS instanceof InverseConversion));
		oldES2AS = external2asMap.put(uri, es2as);
		//		assert (oldES2AS == null) || (es2as instanceof AS2Ecore.InverseConversion); -- FIXME DelegatesTests thrashes this in the global EnvironmentFactory
	}

	@Override
	public void addGenModel(@NonNull GenModel genModel) {
		for (@SuppressWarnings("null")@NonNull GenPackage genPackage : genModel.getAllGenUsedAndStaticGenPackagesWithClassifiers()) {
			addGenPackage(genPackage);
		}
	}

	@Override
	public void addGenPackage(@NonNull GenPackage genPackage) {
		Map<String, GenPackage> genPackageMap2 = genPackageMap;
		if (genPackageMap2 == null) {
			genPackageMap = genPackageMap2 = new HashMap<>();
		}
		genPackageMap2.put(genPackage.getNSURI(), genPackage);
	}

	@Override
	public @NonNull Orphanage createOrphanage() {
		return Orphanage.getOrphanage(asResourceSet);
	}

	@Override
	public void dispose() {
		EnvironmentFactory threadEnvironmentFactory = ThreadLocalExecutor.basicGetEnvironmentFactory();
		if ((threadEnvironmentFactory != environmentFactory) && (threadEnvironmentFactory != null)) {		// XXX
			Thread thread = Thread.currentThread();
			String threadName = thread.getName();
			if (!"Finalizer".equals(threadName)) {
				System.out.println("[" + threadName + "] environmentFactory = " + NameUtil.debugSimpleName(environmentFactory));
				System.out.println("[" + threadName + "] ThreadLocalExecutor.basicGetEnvironmentFactory() = " + NameUtil.debugSimpleName(threadEnvironmentFactory));
			}
		}
// XXX		assert (threadEnvironmentFactory == null) || (threadEnvironmentFactory == environmentFactory) : "Cannot dispose() a foreign EnvironmentFactory";
	//	if (environmentFactory != ThreadLocalExecutor.basicGetEnvironmentFactory()) {
	//		System.err.println("Correcting current EnvironmentFactory to facilitate dispose()");
	//		environmentFactory.activate();
	//	}
		//		System.out.println("[" + Thread.currentThread().getName() + "] dispose AS " + NameUtil.debugSimpleName(asResourceSet));
		asResourceSet.eAdapters().remove(this);
		List<@NonNull Resource> asResources = asResourceSet.getResources();
		List<@NonNull Resource> asResourcesCopy = new ArrayList<>(asResources);
		for (@NonNull Resource asResource : asResourcesCopy) {
			asResource.unload();
		}
		for (@NonNull Resource asResource : asResourcesCopy) {
			asResource.eAdapters().clear();
		}
		asResources.clear();
		asResourceSet.setPackageRegistry(null);
		asResourceSet.setResourceFactoryRegistry(null);
		asResourceSet.setURIConverter(null);
		//		asResourceSet.setURIResourceMap(null);
		StandaloneProjectMap projectMap = StandaloneProjectMap.findAdapter(asResourceSet);
		if (projectMap != null) {
			projectMap.unload(asResourceSet);
		}
		asResourceSet.eAdapters().clear();
		//		StandaloneProjectMap.dispose(asResourceSet);
		/*		ResourceSet externalResourceSet2 = externalResourceSet;
		if (externalResourceSet2 != null) {
//			System.out.println("dispose CS " + ClassUtil.debugSimpleName(externalResourceSet));
			StandaloneProjectMap projectMap2 = StandaloneProjectMap.findAdapter(externalResourceSet2);
			if (projectMap2 != null) {
				projectMap2.unload(externalResourceSet2);
				externalResourceSet2.eAdapters().remove(projectMap2);
			}
//			StandaloneProjectMap.dispose(externalResourceSet2);
			externalResourceSet2.setPackageRegistry(null);
			externalResourceSet2.setResourceFactoryRegistry(null);
			externalResourceSet2.setURIConverter(null);
			if (externalResourceSet2 instanceof ResourceSetImpl) {
				((ResourceSetImpl)externalResourceSet2).setURIResourceMap(null);
			}
			for (Resource resource : new ArrayList<Resource>(externalResourceSet2.getResources())) {
				resource.unload();
			}
			externalResourceSet = null;
		} */
		external2asMap.clear();
		Map<@NonNull URI, @NonNull External2AS> uri2es2as2 = uri2es2as;
		if (uri2es2as2 != null) {
			for (External2AS es2as : uri2es2as2.values()) {
				es2as.dispose();
			}
			uri2es2as = null;
		}
		completeModel.dispose();
		standardLibrary.dispose();
	}

	@Override
	protected void finalize() throws Throwable {
		if (liveMetamodelManagers != null) {
			PivotUtil.debugPrintln("Finalize " + NameUtil.debugSimpleName(this));
			List<@NonNull PivotMetamodelManager> keySet = new ArrayList<>(liveMetamodelManagers.keySet());
			if (!keySet.isEmpty()) {
				StringBuilder s = new StringBuilder();
				s.append(" live");
				for (@NonNull PivotMetamodelManager metamodelManager : keySet) {
					s.append(" @" + Integer.toHexString(metamodelManager.hashCode()));
				}
				System.out.println(s.toString());
			}
		}
	}

	public @Nullable Element getASElement(@NonNull URI uri) {
		if (uri.fragment() == null) {
			ASResource resource = (ASResource)asResourceSet.getResource(uri, true);
			return resource.getModel();
		}
		else {
			Element element = (Element)asResourceSet.getEObject(uri, true);
			return element;
		}
	}

	@Override
	public @Nullable <T extends Element> T getASOfEcore(@NonNull Class<T> pivotClass, @Nullable EObject eObject) {
		if (eObject == null) {
			return null;
		}
		Resource metamodel = eObject.eResource();
		if (metamodel == null) {
			return null;
		}
		External2AS es2as = External2AS.findAdapter(metamodel, environmentFactory);
		if (es2as == null) {
			es2as = External2AS.getAdapter(metamodel, environmentFactory);
		}
		return es2as.getCreated(pivotClass, eObject);
	}

	@Override
	public @NonNull ResourceSet getASResourceSet() {
		return asResourceSet;
	}

	@Override
	public @NonNull CompleteModel getCompleteModel() {
		return completeModel;
	}

	@Override
	public @Nullable External2AS getES2AS(@NonNull Resource esResource) {
		Map<@NonNull URI, @NonNull External2AS> uri2es2as2 = uri2es2as;
		if (uri2es2as2 == null) {
			return null;
		}
		External2AS external2as = uri2es2as2.get(esResource.getURI());
		if (external2as == null) {
			return null;
		}
		Resource resource = external2as.getResource();
		if (resource != esResource) {
			getClass();			// XXX caller checks
		}
		return external2as;
	}

	@Override
	public @Nullable <T extends EObject> T getEcoreOfPivot(@NonNull Class<T> ecoreClass, @NonNull Element element) {
		EObject eTarget = element.getESObject();
		if (eTarget != null) {
			if (!ecoreClass.isAssignableFrom(eTarget.getClass())) {
				logger.error("Ecore " + eTarget.getClass().getName() + "' element is not a '" + ecoreClass.getName() + "'"); //$NON-NLS-1$
				return null;
			}
			@SuppressWarnings("unchecked")
			T castTarget = (T) eTarget;
			return castTarget;
		}
		Model root = (Model)EcoreUtil.getRootContainer(element);
		Resource asResource = element.eResource();
		if (asResource == null) {
			return null;
		}
		if (asResource instanceof OCLstdlib) {		// Not really a model so no Ecore
			return null;
		}
		URI ecoreURI;
		String externalUri = root.getExternalURI();
		URI externalURI = URI.createURI(externalUri);
		if (PivotUtil.isASURI(externalURI)) {
			ecoreURI = ClassUtil.requireNonNull(externalURI.trimFileExtension());
		}
		else {
			ecoreURI = ClassUtil.requireNonNull(externalURI.appendFileExtension("ecore"));
		}
		AS2Ecore converter = new AS2Ecore(environmentFactory, asResource, ecoreURI, null);
		return converter.getCreated(ecoreClass, element);
	}

	/**
	 * Return an ElementExtension for asStereotype reusing any that already exist in asElementExtensions.
	 */
	@Override
	public @NonNull ElementExtension getElementExtension(@NonNull Element asStereotypedElement, @NonNull Stereotype asStereotype) {
		List<ElementExtension> extensions = asStereotypedElement.getOwnedExtensions();
		for (ElementExtension asElementExtension : extensions) {
			if (asElementExtension.getStereotype() == asStereotype) {
				return asElementExtension;
			}
		}
		@NonNull ElementExtension asElementExtension = PivotFactory.eINSTANCE.createElementExtension();
		asElementExtension.setStereotype(asStereotype);
		String name = environmentFactory.getTechnology().getExtensionName(asStereotypedElement);
		asElementExtension.setName(name + "$" + asStereotype.getName());
		//		asElementExtension.getSuperClass().add(getOclAnyType());
		extensions.add(asElementExtension);
		return asElementExtension;
	}

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @NonNull FlowAnalysis getFlowAnalysis(@NonNull OCLExpression oclExpression) {
		OCLExpression contextExpression = FlowAnalysis.getControlExpression(oclExpression);
		Map<@NonNull OCLExpression, @NonNull FlowAnalysis> oclExpression2flowAnalysis2 = oclExpression2flowAnalysis;
		if (oclExpression2flowAnalysis2 == null) {
			oclExpression2flowAnalysis2 = oclExpression2flowAnalysis = new HashMap<>();
		}
		FlowAnalysis flowAnalysis = oclExpression2flowAnalysis2.get(contextExpression);
		if (flowAnalysis == null) {
			flowAnalysis = environmentFactory.createFlowAnalysis(contextExpression);
			oclExpression2flowAnalysis2.put(contextExpression, flowAnalysis);
		}
		return flowAnalysis;
	}

	@Override
	public @Nullable GenPackage getGenPackage(@NonNull String nsURI) {
		if (genPackageMap != null) {
			GenPackage genPackage = genPackageMap.get(nsURI);
			if (genPackage != null) {
				return genPackage;
			}
		}
		ResourceSet externalResourceSet = environmentFactory.getResourceSet();
		URI uri = EMF_2_9.EcorePlugin.getEPackageNsURIToGenModelLocationMap(true).get(nsURI);
		if (uri != null) {
			Resource resource = externalResourceSet.getResource(uri, true);
			for (EObject eObject : resource.getContents()) {
				if (eObject instanceof GenModel) {
					GenModel genModel = (GenModel)eObject;
					genModel.reconcile();
					for (GenPackage genPackage : genModel.getGenPackages()) {
						if (genPackage != null) {
							addGenPackage(genPackage);
							return genPackage;
						}
					}
				}
			}
		}
		return null;
	}

	@Override
	public @NonNull ASResource getResource(@NonNull URI uri, @Nullable String contentType) {
		Resource asResource = asResourceSet.getResource(uri, false);
		if (asResource == null) {
			Object asResourceFactory = asResourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().get(contentType);
			if (asResourceFactory == null) {
				throw new IllegalStateException("No registration for content type '" + contentType + "'");
			} else if (!(asResourceFactory instanceof ASResourceFactory)) {
				throw new IllegalStateException("Non ASResourceFactory registration for content type '" + contentType + "'");
			}
			// XXX upgrade EnvironmentFactory to UMLEcoreTechnology, UMLIdResolver
			asResource = ((ASResourceFactory)asResourceFactory).createResource(uri);
			assert asResource != null;
			asResourceSet.getResources().add(asResource);
		}
		return (ASResource)asResource;
	}

	@Override
	public @NonNull CompleteStandardLibrary getStandardLibrary() {
		return standardLibrary;
	}

	@Override
	public ResourceSet getTarget() {
		return asResourceSet;
	}

	@Override
	public void installResource(@NonNull Resource asResource) {
		for (EObject eObject : asResource.getContents()) {
			if (eObject instanceof Model) {
				installRoot((Model)eObject);
			}
		}
		if (asResource instanceof OCLstdlib) {
			standardLibrary.installLibrary();
		}
	}

	@Override
	public void installRoot(@NonNull Model pivotModel) {
		PartialModels partialModels = ((CompleteModelInternal)completeModel).getPartialModels();
		if (partialModels.contains(pivotModel)) {
			return;
		}
		if (INSTALL_MODEL.isActive()) {
			INSTALL_MODEL.println(NameUtil.debugSimpleName(this) + " " + pivotModel);
		}
		ASResource asResource = (ASResource) pivotModel.eResource();			// XXX cast
		if (asResource != null) {												// XXX some test models don't bother with a Resource
			((CompleteModelInternal)completeModel).getCompleteClasses(asResource);
		}
		List<org.eclipse.ocl.pivot.Package> ownedPackages = pivotModel.getOwnedPackages();
		List<Import> ownedImports = pivotModel.getOwnedImports();
		if (ownedPackages.isEmpty() && ownedImports.isEmpty()) {
			return;				// Don't install "/* Please wait */" in case we're editing a pivot MM
		}
		partialModels.add(pivotModel);
		for (Import asImport : ownedImports) {
			Namespace asNamespace = asImport.getImportedNamespace();
			if (asNamespace != null) {
				Model asModel = PivotUtil.basicGetContainingModel(asNamespace);
				if ((asModel != null) && !partialModels.contains(asModel)) {
					installRoot(asModel);
				}
			}
		}
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return type == PivotMetamodelManager.class;
	}

	protected boolean isUnspecialized(@NonNull List<TemplateParameter> templateParameters, @NonNull List<? extends Type> templateArguments) {
		int iMax = templateParameters.size();
		assert templateArguments.size() == iMax;
		for (int i = 0; i < iMax; i++) {
			if (templateArguments.get(i) != templateParameters.get(i)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public @Nullable Element loadResource(@NonNull URI uri, String zzalias, @Nullable ResourceSet resourceSet) throws ParserException {
		// FIXME alias not used
		URI resourceURI = uri.trimFragment();
		if (PivotUtil.isASURI(resourceURI)) {
			Element asElement = getASElement(uri);
		/*	if (asElement instanceof Model) {
				for (EObject eObject : ((Model)asElement).getOwnedPackages()) {
					if (eObject instanceof Library) {
						if (asLibraries.isEmpty() && (standardLibrary.getLibraryResource() == null)) {
							asLibraryResource = asElement.eResource();
							installLibrary((Library)eObject);
						}
					}
				}
			} */
			return asElement;
		}
		// if (EPackage.Registry.INSTANCE.containsKey(resourceOrNsURI))
		// return EPackage.Registry.INSTANCE.getEPackage(resourceOrNsURI);
		ResourceSet externalResourceSet = environmentFactory.getResourceSet();
		EPackage.Registry packageRegistry = (resourceSet != null ? resourceSet : externalResourceSet).getPackageRegistry();
		String uriString = ClassUtil.requireNonNull(resourceURI.toString());
		Resource resource = null;
		String fragment = uri.fragment();
		if (fragment == null) {
			//
			//	fragment-less URI may be explicit namespace URI
			//
			EPackage ePackage = packageRegistry.getEPackage(uriString);
			if (ePackage != null) {
				return environmentFactory.getASOf(Element.class, ePackage);
			}
			//
			//	fragment-less URI may be an OCL Standard Library
			//
			resource = standardLibrary.loadLibraryResource(uriString);
		}
		else {
			//
			//	fragment-full URI may have a registered package to mark the unfragmented name
			//
			EPackage ePackage = packageRegistry.getEPackage(uriString);
			if (ePackage != null) {
				Resource eResource = ePackage.eResource();
				EObject eObject = eResource.getEObject(fragment);
				if (eObject != null) {
					Element asElement = environmentFactory.getASOf(Element.class, eObject);
					if (asElement != null) {
						return asElement;
					}
				}
			}
		}
		if (resource == null) {
			External2AS external2as = external2asMap.get(resourceURI);
			if (external2as != null) {
				resource = external2as.getResource();
			}
			else {
				//				try {
				resource = externalResourceSet.getResource(resourceURI, true);
				//				}
				//				catch (RuntimeException e) {
				//					resource = externalResourceSet.getResource(resourceURI, false);
				//					if (resource != null) {
				////						externalResourceSet.getResources().remove(resource);
				//						resource = null;
				//					}
				//					throw e;
				//				}
				if (resource != null) {
					for (Resource.Diagnostic diagnostic : resource.getErrors()) {
						if (diagnostic instanceof WrappedException) {
							throw (WrappedException)diagnostic;
						}
					}
				}
				//				if (resource != null) {
				//					if (externalResources == null) {
				//						externalResources = new HashMap<URI, Resource>();
				//					}
				//					externalResources.put(uri, resource);
				//				}
				//
				//	If this resource already loaded under its internal URI reuse old one
				//
				if (resource != null) {
					if (resource instanceof StandaloneProjectMap.DelegatedSinglePackageResource) {
						resource = ((StandaloneProjectMap.DelegatedSinglePackageResource)resource).getResource();
					}
					List<@NonNull EObject> contents = resource.getContents();
					if (contents.size() > 0) {
						EObject firstContent = contents.get(0);
						for (ASResourceFactory resourceFactory : ASResourceFactoryRegistry.INSTANCE.getLoadedResourceFactories()) {
							URI packageURI = resourceFactory.getPackageURI(firstContent);
							if (packageURI != null) {
								External2AS external2as2 = external2asMap.get(packageURI);
								if (external2as2 != null) {
									Resource knownResource = external2as2.getResource();
									if (knownResource != resource) {			// isCompatible
										for (EObject eContent : resource.getContents()) {
											if (eContent instanceof Pivotable) {
												Element pivot = ((Pivotable)firstContent).getPivot();
												if (pivot instanceof Model) {				// XXX straight to get(0)
													Model root = (Model)pivot;
													return root;
												/*	completeModel.getPartialModels().remove(root);
													ASResource asResource = (ASResource) root.eResource();
													if (asResource != null) {
														assert false;				// XXX
														boolean wasUpdating = asResource.setUpdating(true);
														asResourceSet.getResources().remove(asResource);
														asResource.unload();
														asResource.setUpdating(wasUpdating);
														break;	// XXX No point iterating the proxies
													} */
												}
											}
										}
										if (!resourceFactory.getASResourceFactory().isCompatibleResource(resource, knownResource)) {				// XXX
											logger.error("Resource '" + resource.getURI() + "' already loaded as '" + knownResource.getURI() + "'");
										}
										return PivotUtil.getModel(resource);
									//	if (!resourceFactory.getASResourceFactory().isCompatibleResource(resource, knownResource)) {				// XXX
									//		logger.error("Resource '" + resource.getURI() + "' already loaded as '" + knownResource.getURI() + "'");
									//	}
										//											resource.unload();
									//	resource.getResourceSet().getResources().remove(resource);
									//	resource = knownResource;
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		if (resource != null) {
			return environmentFactory.loadResource(resource, uri);
		}
		logger.warn("Cannot load package with URI '" + uri + "'");
		return null;
	}

	@Override
	public void notifyChanged(Notification notification) {}

	@Override
	public void removeASResource(@NonNull ASResource asResource) {
		Model asModel = PivotUtil.getModel(asResource);
		String externalURIString = asModel.getExternalURI();
		URI externalURI = URI.createURI(externalURIString);
		Map<@NonNull URI, @NonNull External2AS> uri2es2as2 = uri2es2as;
		if (uri2es2as2 != null) {
			external2asMap.remove(externalURI);
			uri2es2as2.remove(externalURI);
		}
		asResource.unload();
		asResource.eAdapters().clear();
	}

	@Override
	public void removeExternalResource(@NonNull External2AS external2as) {
		external2asMap.remove(external2as.getURI());
	}

	@Override
	public void removeExternalResource(@NonNull Resource esResource) {
		Map<@NonNull URI, @NonNull External2AS> uri2es2as2 = uri2es2as;
		if (uri2es2as2 != null) {
			External2AS es2as = uri2es2as2.remove(esResource.getURI());
			if (es2as != null) {
				es2as.dispose();
			}
		}
	}

	/**
	 * @since 1.3
	 */
	@Override
	public void resetFlowAnalysis() {
		oclExpression2flowAnalysis = null;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		//		assert newTarget == asResourceSet;
	}

	@Override
	public void unsetTarget(Notifier oldTarget) {
		//		assert oldTarget == asResourceSet;
	}
}
