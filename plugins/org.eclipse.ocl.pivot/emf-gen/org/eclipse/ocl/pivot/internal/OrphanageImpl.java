/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Orphanage;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.internal.complete.PartialPackages;
import org.eclipse.ocl.pivot.internal.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.internal.manager.MapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeParameters;
import org.eclipse.ocl.pivot.values.MapTypeParameters;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Orphanage</b></em>'.
*
 * An Orphanage is a special purpose Package that transitively contains all metamodel elemants that
 * have no ancestral Package to contains them. There are specific type managers for Collection, Lambda,
 * Map and Tuple types. The managers weakly contain elements such as type specializations that
 * should require a container for the purposes of validation, but which should be eligible for
 * garbage collection whenever no longer in use.
 * <br>
 * There is no global orphanage. Any reference to one is stale.
 * <br>
 * Each OCL CompleteModel has a shared orphanage that contains the referenced unique synthesized elements.
 * The shared orphanage is never saved and so the Orphanage class can be used.
 * <br>
 * Each saved ASResource has a local orphanage that contains a selective copy of the shared orphanage so that
 * all references are terminated locally. ASSaver creates this copy via PivotSaveImpl.init(). The local orphanages
 * use a regular Package to avod the need for Orphange support in XMI.
 *
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class OrphanageImpl extends PackageImpl implements Orphanage
{
	/**
	 * The number of structural features of the '<em>Orphanage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORPHANAGE_FEATURE_COUNT = PackageImpl.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Orphanage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORPHANAGE_OPERATION_COUNT = PackageImpl.PACKAGE_OPERATION_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OrphanageImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.ORPHANAGE;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitOrphanage(this);
	}

/*	@Override
	public @NonNull EList<org.eclipse.ocl.pivot.Class> getOwnedClasses() {
		EList<org.eclipse.ocl.pivot.Class> ownedClasses2 = ownedClasses;
		if (ownedClasses2 == null)
		{
			ownedClasses2 = ownedClasses = new WeakEList<org.eclipse.ocl.pivot.Class>(/*WeakReference.class, this, PivotPackage.PACKAGE__OWNED_TYPE, PivotPackage.TYPE__PACKAGE* /)
					{
						@Override
						protected @NonNull ElementId getElementId(org.eclipse.ocl.pivot.Class object) {
							return object.getTypeId();
						}

					};
		}
		return ownedClasses2;
	} */

	/**
	 * @since 1.18
	 *
	@Override
	public @NonNull EList<org.eclipse.ocl.pivot.Package> getOwnedPackages() {
		EList<org.eclipse.ocl.pivot.Package> ownedPackages2 = ownedPackages;
		if (ownedPackages2 == null)
		{
			ownedPackages2 = ownedPackages = new WeakEList<org.eclipse.ocl.pivot.Package>(/*WeakReference.class, this, PivotPackage.PACKAGE__OWNED_PACKAGE, PivotPackage.PACKAGE__OWNING_PACKAGE* /)
			{
				@Override
				protected @NonNull ElementId getElementId(org.eclipse.ocl.pivot.Package object) {
					return object.getPackageId();
				}

			};
		}
		return ownedPackages2;
	} */

	/**
	 * The OrphanResource tailors the inherited ASResource functionality to support the single Resource shared by all
	 * same-OCL consumers. It is not saved and so has no xmi:ids but it does have LUSSIDs
	 * in order to contribute to the signatures of operations.
	 */
	protected static class OrphanResource extends ASResourceImpl
	{
		protected OrphanResource(@NonNull URI uri) {
			super(uri, OCLASResourceFactory.getInstance());
			setUpdating(true);
			setSaveable(false);
		}

		@Override
		protected void doUnload() {
			if (contents != null) {
				for (EObject aContent : contents) {
					if (aContent instanceof OrphanageImpl) {
						((OrphanageImpl)aContent).dispose();
					}
				}
				contents = null;
			}
			//			super.doUnload();
		}

		@Override
		public String getURIFragment(EObject eObject) {
			// The OrphanResource cannot be saved so has no LUSSID-based xmi:ids, but Xtext serialization needs a URI
			return superGetURIFragment(eObject);
		}

		@Override
		public boolean isOrphanage() {
			return true;
		}
	}

	public static final @NonNull URI ORPHANAGE_URI = ClassUtil.nonNullEMF(URI.createURI(PivotConstants.ORPHANAGE_URI + PivotConstants.DOT_OCL_AS_FILE_EXTENSION));

	/**
	 * Return the orphan package within asModel, or null if none.
	 */
	public static org.eclipse.ocl.pivot.@Nullable Package basicGetOrphanage(@NonNull Model asModel) {
		for (org.eclipse.ocl.pivot.Package asPackage : PivotUtil.getOwnedPackages(asModel)) {
			if (isOrphanage(asPackage)) {
				return asPackage;
			}
		}
		return null;
	}

	/**
	 * Return the Orphanage for a resourceSet if non-null. Obsolete deprecated functionality returns a global Orphanage if null.
	 */
	public static @Nullable Orphanage basicGetSharedOrphanage(@NonNull ResourceSet resourceSet) {
		for (Resource aResource : resourceSet.getResources()) {
			if (aResource instanceof OrphanResource) {
				for (EObject eContent : aResource.getContents()) {
					if (eContent instanceof Model) {
						for (org.eclipse.ocl.pivot.Package asPackage : ((Model)eContent).getOwnedPackages()) {
							if (asPackage instanceof Orphanage) {
								return (Orphanage) asPackage;
							}
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Create and return the local orphanage Package within resource.
	 *
	 * @since 1.18
	 */
	public static @NonNull Orphanage createLocalOrphanage(@NonNull Model asModel) {
		Orphanage orphanage = PivotFactory.eINSTANCE.createOrphanage();
		orphanage.setName(PivotConstants.ORPHANAGE_NAME);
		orphanage.setNsPrefix(PivotConstants.ORPHANAGE_PREFIX);
		orphanage.setURI(PivotConstants.ORPHANAGE_URI);
		asModel.getOwnedPackages().add(orphanage);
		return orphanage;
	}

	/**
	 * Return the Orphanage for a resourceSet if non-null. Obsolete deprecated functionality returns a global Orphanage if null.
	 */
	public static @NonNull Orphanage createLocalOrphanage(@NonNull StandardLibrary standardLibrary, @NonNull ResourceSet resourceSet) {
		for (Resource aResource : resourceSet.getResources()) {
			for (EObject eContent : aResource.getContents()) {
				if (eContent instanceof Model) {
					for (org.eclipse.ocl.pivot.Package asPackage : ((Model)eContent).getOwnedPackages()) {
						if (asPackage instanceof Orphanage) {
							assert false;
							return (Orphanage) asPackage;
						}
					}
				}
			}
		}
		return createOrphanage(standardLibrary, resourceSet);
	}

	public static @NonNull Orphanage createOrphanage(@NonNull StandardLibrary standardLibrary, @NonNull ResourceSet resourceSet) {
		OrphanageImpl orphanage = (OrphanageImpl)PivotFactory.eINSTANCE.createOrphanage();
		orphanage.init(standardLibrary, PivotConstants.ORPHANAGE_NAME, PivotConstants.ORPHANAGE_URI, PivotConstants.ORPHANAGE_PREFIX);
		Model orphanModel = PivotFactory.eINSTANCE.createModel();
		orphanModel.setName(PivotConstants.ORPHANAGE_NAME);;
		orphanModel.setExternalURI(PivotConstants.ORPHANAGE_URI);
		orphanModel.getOwnedPackages().add(orphanage);
		Resource orphanageResource = new OrphanResource(ORPHANAGE_URI);
		orphanageResource.getContents().add(orphanModel);
		resourceSet.getResources().add(orphanageResource);
		return orphanage;
	}

	/**
	 * @since 1.18
	 *
	@Deprecated
	private static @NonNull Orphanage createSharedOrphanage(@NonNull ResourceSet resourceSet) {
		Orphanage orphanage = new OrphanageImpl(PivotConstants.ORPHANAGE_NAME, PivotConstants.ORPHANAGE_URI, PivotConstants.ORPHANAGE_PREFIX);
		Model orphanModel = PivotFactory.eINSTANCE.createModel();
		orphanModel.setName(PivotConstants.ORPHANAGE_NAME);;
		orphanModel.setExternalURI(PivotConstants.ORPHANAGE_URI);
		orphanModel.getOwnedPackages().add(orphanage);
		Resource orphanageResource = new OrphanResource(ORPHANAGE_URI);
		orphanageResource.getContents().add(orphanModel);
		resourceSet.getResources().add(orphanageResource);
		return orphanage;
	} */

	/**
	 * @since 1.18
	 */
	public static @NonNull Orphanage createSharedOrphanage(@NonNull StandardLibrary standardLibrary, @NonNull ResourceSet resourceSet) {
		OrphanageImpl orphanage = (OrphanageImpl)PivotFactory.eINSTANCE.createOrphanage();
		orphanage.init(standardLibrary, PivotConstants.ORPHANAGE_NAME, PivotConstants.ORPHANAGE_URI, PivotConstants.ORPHANAGE_PREFIX);
		Model orphanModel = PivotFactory.eINSTANCE.createModel();
		orphanModel.setName(PivotConstants.ORPHANAGE_NAME);;
		orphanModel.setExternalURI(PivotConstants.ORPHANAGE_URI);
		orphanModel.getOwnedPackages().add(orphanage);
		Resource orphanageResource = new OrphanResource(ORPHANAGE_URI);
		orphanageResource.getContents().add(orphanModel);
		resourceSet.getResources().add(orphanageResource);
		return orphanage;
	}

	/**
	 * @since 1.18
	 */
	public static @NonNull WildcardType getOrphanWildcardType(org.eclipse.ocl.pivot.@NonNull Package orphanPackage) {
		List<org.eclipse.ocl.pivot.@NonNull Class> orphanClasses = PivotUtilInternal.getOwnedClassesList(orphanPackage);
		org.eclipse.ocl.pivot.Class wildcardType = NameUtil.getNameable(orphanClasses, PivotConstants.WILDCARD_NAME);
		if (wildcardType == null) {
			wildcardType = PivotFactory.eINSTANCE.createWildcardType();
			wildcardType.setName(PivotConstants.WILDCARD_NAME);
			wildcardType.setOwningPackage(orphanPackage);
		}
		return (WildcardType)wildcardType;
	}


	/**
	 * Return the Orphanage for an eObject, which is the Orphanage resource in the same ResourceSet as
	 * the eObject, else the global Orphanage.
	 */
	@Deprecated /* @deprecated - not used */
	public static @Nullable Orphanage getOrphanage(@NonNull StandardLibrary standardLibrary, @NonNull EObject eObject) {
		//		if (eObject == null) {
		//			return null;
		//		}
		Resource resource = eObject.eResource();
		if (resource == null) {
			return null;
		}
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			return null;
		}
		return getOrphanage(standardLibrary, resourceSet);
	}

	/**
	 * Return the Orphanage for a resourceSet if non-null. Obsolete deprecated functionality returns a global Orphanage if null.
	 */
	@Deprecated
	private static @NonNull Orphanage getOrphanage(@NonNull StandardLibrary standardLibrary, @Nullable ResourceSet resourceSet) {
		if (resourceSet == null) {
			assert false : "Use of the global Orphanage is deprecated";
			OrphanageImpl orphanage = (OrphanageImpl)PivotFactory.eINSTANCE.createOrphanage();
			orphanage.init(standardLibrary, PivotConstants.ORPHANAGE_NAME, PivotConstants.ORPHANAGE_URI, PivotConstants.ORPHANAGE_PREFIX);
			return orphanage;
		}
		for (Resource aResource : resourceSet.getResources()) {
			for (EObject eContent : aResource.getContents()) {
				if (eContent instanceof Model) {
					for (org.eclipse.ocl.pivot.Package asPackage : ((Model)eContent).getOwnedPackages()) {
						if (asPackage instanceof Orphanage) {
							return (Orphanage) asPackage;
						}
					}
				}
			}
		}
		return createSharedOrphanage(standardLibrary, resourceSet);
	}

	/**
	 * Return the Orphanage for a resourceSet if non-null. Obsolete deprecated functionality returns a global Orphanage if null.
	 */
	public static @NonNull Orphanage getSharedOrphanage(@NonNull StandardLibrary standardLibrary, @NonNull ResourceSet resourceSet) {
		for (Resource aResource : resourceSet.getResources()) {
			for (EObject eContent : aResource.getContents()) {
				if (eContent instanceof Model) {
					Model asModel = (Model)eContent;
					if (OrphanageImpl.isOrphanage(asModel)) {
						for (org.eclipse.ocl.pivot.Package asPackage : asModel.getOwnedPackages()) {
							if (asPackage instanceof Orphanage) {
								return (Orphanage)asPackage;
							}
						}
					}
				}
			}
		}
		return createSharedOrphanage(standardLibrary, resourceSet);
	}

	/**
	 * Return true if asElement is transitively contained by a local or shared orphanage.
	 *
	 * @since 1.18
	 */
	public static boolean isOrphan(@NonNull Element asElement) {
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getContainingPackage(asElement);
		return (asPackage != null) && isOrphanage(asPackage);
	}

	/**
	 * Return true if asModel is a shared orphanage for synthesized model elements.
	 *
	 * @since 1.18
	 */
	public static boolean isOrphanage(@NonNull Model asModel) {
		String uri = asModel.getExternalURI();
		return isOrphanage(uri);
	}

	/**
	 * Return true if asPackage is an orphanage for synthesized model elements.
	 *
	 * @since 1.18
	 */
	public static boolean isOrphanage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		String uri = asPackage.getURI();
		boolean isOrphanage = isOrphanage(uri);
		assert isOrphanage == asPackage instanceof Orphanage;
		return isOrphanage;
	}

	public static boolean isOrphanage(String uri) {
		return PivotConstants.ORPHANAGE_URI.equals(uri) || PivotConstantsInternal.OLD_ORPHANAGE_URI.equals(uri);
	}

	/**
	 * Shared cache of the lazily created, lazily deleted, specializations of each collection type.
	 */
	private @Nullable StandardLibrary standardLibrary = null;

	/**
	 * Shared cache of the lazily created, lazily deleted, specializations of each collection type.
	 */
	private @Nullable CollectionTypeManager collectionTypeManager = null;

	/**
	 * Shared cache of the lazily created, lazily deleted, specializations of each lambda type.
	 */
	private @Nullable LambdaTypeManager lambdaTypeManager = null;

	/**
	 * Shared cache of the lazily created, lazily deleted, specializations of each map type.
	 */
	private @Nullable MapTypeManager mapTypeManager = null;

	/**
	 * Shared cache of the lazily created, lazily deleted, tuples.
	 */
	private @Nullable TupleTypeManager tupleTypeManager = null;

	@Override
	public void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass) {
		System.out.println("addOrphanClass " + NameUtil.debugSimpleName(orphanClass));
		orphanClass.setOwningPackage(this);
	//	getOwnedClasses().add(orphanClass);		// FIXME why doesn't this always work? - missing inverse in bad overload
		assert orphanClass.eContainer() == this;
		assert getOwnedClasses().contains(orphanClass);
	}

	@Override
	public void addPackageListener(@NonNull PartialPackages partialPackages) {
		super.addPackageListener(partialPackages);
	}

	@Override
	public @Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeId collectionTypeId) {
		return collectionTypeManager != null ? collectionTypeManager.basicGetCollectionType(collectionTypeId) : null;
	}

	@Override
	public @Nullable MapType basicGetMapType(@NonNull MapTypeId mapTypeId) {
		return mapTypeManager != null ? getMapTypeManager().basicGetMapType(mapTypeId) : null;
	}

	@Override
	public void dispose() {
	/*	if (ownedClasses != null) {
			((WeakEList<?>)ownedClasses).dispose();
		}
		if (ownedPackages != null) {
			((WeakEList<?>)ownedPackages).dispose();
		} */
	}

	public void disposeLambdas() {
		if (lambdaTypeManager != null) {
			lambdaTypeManager.dispose();
			lambdaTypeManager = null;
		}
	}

	public void disposeTuples() {
		if (tupleTypeManager != null) {
			tupleTypeManager.dispose();
			tupleTypeManager = null;
		}
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeParameters<@NonNull Type> typeParameters) {
		return getCollectionTypeManager().getCollectionType(typeParameters);
	}

	private @NonNull CollectionTypeManager getCollectionTypeManager() {
		CollectionTypeManager collectionTypeManager2 = this.collectionTypeManager;
		if (collectionTypeManager2 == null) {
			assert standardLibrary != null;
			this.collectionTypeManager = collectionTypeManager2 = new CollectionTypeManager(this, standardLibrary);
		}
		return collectionTypeManager2;
	}

	@Override
	public @NonNull LambdaType getLambdaType(@NonNull String typeName, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes,
			@NonNull Type resultType, @Nullable TemplateParameterSubstitutions bindings) {
		return getLambdaTypeManager().getLambdaType(typeName, contextType, parameterTypes, resultType, bindings);
	}

	private @NonNull LambdaTypeManager getLambdaTypeManager() {
		LambdaTypeManager lambdaTypeManager2 = this.lambdaTypeManager;
		if (lambdaTypeManager2 == null) {
			assert standardLibrary != null;
			this.lambdaTypeManager = lambdaTypeManager2 = new LambdaTypeManager(this, standardLibrary);
		}
		return lambdaTypeManager2;
	}

	@Override
	public @NonNull MapType getMapType(@NonNull MapTypeParameters<@NonNull Type, @NonNull Type> typeParameters) {
		return getMapTypeManager().getMapType(typeParameters);
	}

	private @NonNull MapTypeManager getMapTypeManager() {
		MapTypeManager mapTypeManager2 = this.mapTypeManager;
		if (mapTypeManager2 == null) {
			assert standardLibrary != null;
			this.mapTypeManager = mapTypeManager2 = new MapTypeManager(this, standardLibrary);
		}
		return mapTypeManager2;
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull IdResolver idResolver, @NonNull TupleTypeId tupleTypeId) {
		return getTupleTypeManager().getTupleType(idResolver, tupleTypeId);
	}

	private @NonNull TupleTypeManager getTupleTypeManager() {
		TupleTypeManager tupleTypeManager2 = this.tupleTypeManager;
		if (tupleTypeManager2 == null) {
			assert standardLibrary != null;
			this.tupleTypeManager = tupleTypeManager2 = new TupleTypeManager(this, standardLibrary);
		}
		return tupleTypeManager2;
	}

	public void init(@NonNull StandardLibrary standardLibrary, @NonNull String orphanageName, @NonNull String orphanageUri, @NonNull String orphanagePrefix) {
		assert this.standardLibrary == null;
		this.standardLibrary = standardLibrary;
		setName(orphanageName);
		setURI(orphanageUri);
		setNsPrefix(orphanagePrefix);
	}

	@Override
	public void removePackageListener(@NonNull PartialPackages partialPackages) {
		super.removePackageListener(partialPackages);
	}
} //OrphanageImpl
