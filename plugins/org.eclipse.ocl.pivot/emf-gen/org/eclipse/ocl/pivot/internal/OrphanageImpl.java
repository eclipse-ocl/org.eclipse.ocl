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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.PartialPackages;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.types.TuplePart;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

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

	private static final Logger logger = Logger.getLogger(OrphanageImpl.class);

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
	public static org.eclipse.ocl.pivot.@NonNull Package createLocalOrphanage(@NonNull Model asModel) {
		org.eclipse.ocl.pivot.Package orphanage = PivotFactory.eINSTANCE.createPackage();
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
	//	assert isOrphanage == asPackage instanceof Orphanage;
		return isOrphanage;
	}

	public static boolean isOrphanage(String uri) {
		return PivotConstants.ORPHANAGE_URI.equals(uri) || PivotConstantsInternal.OLD_ORPHANAGE_URI.equals(uri);
	}

	private @Nullable StandardLibrary standardLibrary = null;

	/**
	 * Shared cache of the lazily created, lazily deleted, specializations of each type.
	 */
	private final @NonNull Map<@NonNull TypeId, @NonNull Type> typeId2type = new HashMap<>();

	/**
	 * The elements that reference each orphan. An element that references more than once e.g. Map<X,X> has a duplicate entry.
	 */
	private final @NonNull Map<@NonNull TypeId, @NonNull List<@NonNull Element>> typeId2typeRefs = new HashMap<>();

	private int wildcardCount = 0;

	private void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass) {
		TypeId typeId = orphanClass.getTypeId();
	//	System.out.println("addOrphanClass " + NameUtil.debugSimpleName(orphanClass) + " : " + NameUtil.debugSimpleName(typeId) + " : " + orphanClass);
		Type old = typeId2type.get(typeId);
		assert old == orphanClass;
		assert !getOwnedClasses().contains(orphanClass);
		orphanClass.setOwningPackage(this);
	//	getOwnedClasses().add(orphanClass);		// FIXME why doesn't this always work? - missing inverse in bad overload
		assert orphanClass.eContainer() == this;
		assert getOwnedClasses().contains(orphanClass);
	}

	private void addOrphanWildcard(@NonNull WildcardType orphanWildcard) {
	//	TypeId typeId = orphanClass.getTypeId();
	//	System.out.println("addOrphanClass " + NameUtil.debugSimpleName(orphanClass) + " : " + NameUtil.debugSimpleName(typeId) + " : " + orphanClass);
	//	Type old = typeId2type.get(typeId);
	//	assert old == orphanClass;
		assert !getOwnedClasses().contains(orphanWildcard);
		orphanWildcard.setOwningPackage(this);
		assert orphanWildcard.eContainer() == this;
		assert getOwnedClasses().contains(orphanWildcard);
		wildcardCount++;
	}

	@Override
	public void addPackageListener(@NonNull PartialPackages partialPackages) {
		super.addPackageListener(partialPackages);
	}

	@Override
	public void addReference(@NonNull Type type, @NonNull Element asElement) {
		TypeId typeId = type.getTypeId();
		synchronized (typeId2typeRefs) {
			List<@NonNull Element> list = typeId2typeRefs.get(typeId);
			if (list == null) {
				list = new ArrayList<>();
				typeId2typeRefs.put(typeId, list);
			}
			list.add(asElement);
		}
	}

	@Override
	public @Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeId collectionTypeId) {
		CollectionType collectionType = (CollectionType)typeId2type.get(collectionTypeId);
		if (collectionType == null) {
			return null;
		}
		if (collectionType.isWellContained()) {
			return collectionType;
		}
		synchronized (typeId2type) {
			if (collectionType.isWellContained()) {
				return collectionType;
			}
			typeId2type.remove(collectionTypeId);
		}
		return null;
	}

	@Override
	public @Nullable LambdaType basicGetLambdaType(@NonNull LambdaTypeId lambdaTypeId) {
		LambdaType lambdaType = (LambdaType)typeId2type.get(lambdaTypeId);
		if (lambdaType == null) {
			return null;
		}
		if (lambdaType.isWellContained()) {
			return lambdaType;
		}
		synchronized (typeId2type) {
			if (lambdaType.isWellContained()) {
				return lambdaType;
			}
			typeId2type.remove(lambdaTypeId);
		}
		return null;
	}

	@Override
	public @Nullable MapType basicGetMapType(@NonNull MapTypeId mapTypeId) {
		MapType mapType = (MapType)typeId2type.get(mapTypeId);
		if (mapType == null) {
			return null;
		}
		if (mapType.isWellContained()) {
			return mapType;
		}
		synchronized (typeId2type) {
			if (mapType.isWellContained()) {
				return mapType;
			}
			typeId2type.remove(mapTypeId);
		}
		return null;
	}

	@Override
	public @Nullable TupleType basicGetTupleType(@NonNull TupleTypeId tupleTypeId) {
		TupleType tupleType = (TupleType)typeId2type.get(tupleTypeId);
		if (tupleType == null) {
			return null;
		}
		if (tupleType.isWellContained()) {
			return tupleType;
		}
		synchronized (typeId2type) {
			if (tupleType.isWellContained()) {
				return tupleType;
			}
			typeId2type.remove(tupleTypeId);
		}
		return null;
	}

	@Override
	public @Nullable Type basicGetType(@NonNull TypeId typeId, boolean retainStaleEntry) {
		Type type = typeId2type.get(typeId);
		if (type == null) {
			return null;
		}
		if (retainStaleEntry || type.isWellContained()) {
			return type;
		}
		synchronized (typeId2type) {
			if (type.isWellContained()) {
				return type;
			}
			typeId2type.remove(typeId);
		}
		return null;
	}

	@Override
	public @NonNull WildcardType createWildcardType(@NonNull Type asType) {
		WildcardType wildcardType = PivotFactory.eINSTANCE.createWildcardType();
		wildcardType.setName(PivotConstants.WILDCARD_NAME + wildcardCount);
		// XXX templateableId from asType
		addOrphanWildcard(wildcardType);
		return wildcardType;
	}

	@Override
	public void dispose() {
	/*	if (ownedClasses != null) {
			((WeakEList<?>)ownedClasses).dispose();
		}
		if (ownedPackages != null) {
			((WeakEList<?>)ownedPackages).dispose();
		} */
		typeId2type.clear();
	}

	@Override
	public void gc() {
		synchronized (typeId2type) {
			for (TypeId typeId : new ArrayList<>(typeId2type.keySet())) {
				Type type = typeId2type.get(typeId);
				assert type != null;
				if (!type.isWellContained()) {
					typeId2type.remove(typeId);
				}
			}
		typeId2type.clear();
		}
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType,
			@Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		if (isNullFree == null) {
			isNullFree = PivotConstants.DEFAULT_COLLECTIONS_ARE_NULL_FREE;
		}
		if (lower == null) {
			lower = ValueUtil.ZERO_VALUE;
		}
		if (upper == null) {
			upper = ValueUtil.UNLIMITED_VALUE;
		}
		CollectionTypeId genericTypeId = genericType.getTypeId();
		TypeId elementTypeId = elementType.getTypeId();
		CollectionTypeId specializedTypeId = genericTypeId.getSpecializedId(elementTypeId, isNullFree, lower, upper);
		synchronized (typeId2type) {
			CollectionType collectionType = (CollectionType)typeId2type.get(specializedTypeId);
			if ((collectionType != null) && !collectionType.isWellContained()) {
				removeOrphanClass(collectionType);
				collectionType = null;
			}
			if (collectionType == null) {
				assert (elementType != null) && (elementType.eResource() != null);
				if (genericTypeId == TypeId.BAG) {
					collectionType = PivotFactory.eINSTANCE.createBagType();
				}
				else if (genericTypeId == TypeId.ORDERED_SET) {
					collectionType = PivotFactory.eINSTANCE.createOrderedSetType();
				}
				else if (genericTypeId == TypeId.SEQUENCE) {
					collectionType = PivotFactory.eINSTANCE.createSequenceType();
				}
				else if (genericTypeId == TypeId.SET) {
					collectionType = PivotFactory.eINSTANCE.createSetType();
				}
				else {
					collectionType = PivotFactory.eINSTANCE.createCollectionType();
				}
				collectionType.setName(genericType.getName());
				TemplateSignature templateSignature = genericType.getOwnedSignature();
				List<@NonNull TemplateParameter> templateParameters = ClassUtil.nullFree(templateSignature.getOwnedParameters());
				TemplateParameter formalParameter = ClassUtil.nonNull(templateParameters.get(0));
				assert formalParameter != null;
				TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
				TemplateParameterSubstitution templateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(formalParameter, elementType);
				templateBinding.getOwnedSubstitutions().add(templateParameterSubstitution);
				collectionType.getOwnedBindings().add(templateBinding);
				getStandardLibrary().resolveSuperClasses(collectionType, genericType);
			//	collectionType.getSuperClasses().addAll(unspecializedType.getSuperClasses());
				collectionType.setIsNullFree(isNullFree);
				try {
					collectionType.setLowerValue(lower);
				} catch (InvalidValueException e) {
					logger.error("Out of range lower bound for " + specializedTypeId, e);
				}
				try {
					collectionType.setUpperValue(upper);
				} catch (InvalidValueException e) {
					logger.error("Out of range upper bound for " + specializedTypeId, e);
				}
				collectionType.setGeneric(genericType);
				Type old = typeId2type.put(specializedTypeId, collectionType);
				assert old == null;
				assert specializedTypeId == ((CollectionTypeImpl)collectionType).immutableGetTypeId();		// XXX
				if (basicGetType(specializedTypeId, true) != collectionType) {			// XXX debugging
					basicGetType(specializedTypeId, true);
				}
				String s = collectionType.toString();
				if (s.contains("Set(Bag(b::B")) {
					getClass();		// XXX
				}
				addOrphanClass(collectionType);
			}
			assert collectionType.isWellContained();
			return collectionType;
		}
	}

	@Override
	public @NonNull LambdaType getLambdaType(org.eclipse.ocl.pivot.@NonNull Class oclLambdaType, @NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType) {
		String name = TypeId.LAMBDA_NAME;
		@NonNull TypeId @NonNull [] typeIds = new @NonNull TypeId[2+parameterTypes.size()];
		typeIds[0] = contextType.getTypeId();
		typeIds[1] = resultType.getTypeId();
		for (int i = 0; i < parameterTypes.size(); i++) {
			typeIds[2+i] = parameterTypes.get(i).getTypeId();
		}
		LambdaTypeId lambdaTypeId = IdManager.getLambdaTypeId(name, typeIds);
		synchronized (typeId2type) {
			LambdaType lambdaType = (LambdaType) typeId2type.get(lambdaTypeId);
			if ((lambdaType != null) && !lambdaType.isWellContained()) {
				removeOrphanClass(lambdaType);
				lambdaType = null;
			}
			if (lambdaType == null) {
				lambdaType = PivotFactory.eINSTANCE.createLambdaType();
				lambdaType.setName(name);
				lambdaType.setContextType(contextType);
				lambdaType.setResultType(resultType);
				lambdaType.getParameterType().addAll(parameterTypes);
				lambdaType.getSuperClasses().add(oclLambdaType);
				typeId2type.put(lambdaTypeId, lambdaType);
				assert lambdaTypeId == ((LambdaTypeImpl)lambdaType).immutableGetTypeId();		// XXX
				addOrphanClass(lambdaType);
			}
			assert lambdaType.isWellContained();
			return lambdaType;
		}
	}

	@Override
	public @NonNull MapType getMapOfEntryType(@NonNull MapType genericType, org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		Iterable<@NonNull Property> ownedProperties = PivotUtil.getOwnedProperties(entryClass);
		Property keyProperty = ClassUtil.nonNullState(NameUtil.getNameable(ownedProperties, "key"));
		Property valueProperty = ClassUtil.nonNullState(NameUtil.getNameable(ownedProperties, "value"));
		Type keyType = PivotUtil.getType(keyProperty);
		boolean keysAreNullFree = keyProperty.isIsRequired();
		Type valueType = PivotUtil.getType(valueProperty);
		boolean valuesAreNullFree = valueProperty.isIsRequired();
		TypeId keyTypeId = keyType.getTypeId();
		TypeId valueTypeId = valueType.getTypeId();
		MapTypeId mapTypeId = TypeId.MAP.getSpecializedId(keyTypeId, valueTypeId, keysAreNullFree, valuesAreNullFree);
		synchronized (typeId2type) {
			MapType mapType = basicGetMapType(mapTypeId);
			if ((mapType != null) && !mapType.isWellContained()) {
				removeOrphanClass(mapType);
				mapType = null;
			}
			if (mapType == null) {
				String typeName = genericType.getName();
				TemplateSignature templateSignature = genericType.getOwnedSignature();
				List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
				mapType = PivotFactory.eINSTANCE.createMapType();
				mapType.setName(typeName);
				TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
				TemplateParameter keyFormalParameter = templateParameters.get(0);
				TemplateParameter valueFormalParameter = templateParameters.get(1);
				assert keyFormalParameter != null;
				assert valueFormalParameter != null;
				TemplateParameterSubstitution keyTemplateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(keyFormalParameter, keyType);
				TemplateParameterSubstitution valueTemplateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(valueFormalParameter, valueType);
				templateBinding.getOwnedSubstitutions().add(keyTemplateParameterSubstitution);
				templateBinding.getOwnedSubstitutions().add(valueTemplateParameterSubstitution);
				mapType.getOwnedBindings().add(templateBinding);
			//	resolveSuperClasses(specializedMapType, unmapType);
				mapType.getSuperClasses().addAll(genericType.getSuperClasses());
				mapType.setKeysAreNullFree(keysAreNullFree);
				mapType.setValuesAreNullFree(valuesAreNullFree);
				mapType.setGeneric(genericType);
				mapType.setEntryClass(entryClass);
				typeId2type.put(mapTypeId, mapType);
				assert mapTypeId == ((MapTypeImpl)mapType).immutableGetTypeId();		// XXX
				addOrphanClass(mapType);
			}
			assert mapType.isWellContained();
			return mapType;
		}
	}

	@Override
	public @NonNull MapType getMapType(@NonNull MapType genericType, @NonNull Type keyType, boolean keysAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		TypeId keyTypeId = keyType.getTypeId();
		TypeId valueTypeId = valueType.getTypeId();
		MapTypeId mapTypeId = TypeId.MAP.getSpecializedId(keyTypeId, valueTypeId, keysAreNullFree, valuesAreNullFree);
		synchronized (typeId2type) {
			MapType mapType = (MapType)typeId2type.get(mapTypeId);
			if ((mapType != null) && !mapType.isWellContained()) {
				removeOrphanClass(mapType);
				mapType = null;
			}
			if (mapType == null) {
				assert (keyType != null) && (keyType.eResource() != null);
				assert (valueType != null) && (valueType.eResource() != null);
				String typeName = genericType.getName();
				TemplateSignature templateSignature = genericType.getOwnedSignature();
				List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
				mapType = PivotFactory.eINSTANCE.createMapType();
				mapType.setName(typeName);
				TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
				TemplateParameter keyFormalParameter = templateParameters.get(0);
				TemplateParameter valueFormalParameter = templateParameters.get(1);
				assert keyFormalParameter != null;
				assert valueFormalParameter != null;
				TemplateParameterSubstitution keyTemplateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(keyFormalParameter, keyType);
				TemplateParameterSubstitution valueTemplateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(valueFormalParameter, valueType);
				templateBinding.getOwnedSubstitutions().add(keyTemplateParameterSubstitution);
				templateBinding.getOwnedSubstitutions().add(valueTemplateParameterSubstitution);
				mapType.getOwnedBindings().add(templateBinding);
			//	resolveSuperClasses(specializedMapType, unmapType);
				mapType.getSuperClasses().addAll(genericType.getSuperClasses());
				mapType.setKeysAreNullFree(keysAreNullFree);
				mapType.setValuesAreNullFree(valuesAreNullFree);
				mapType.setGeneric(genericType);
			//	mapType.setEntryClass(typeParameters.getEntryClass());
				typeId2type.put(mapTypeId, mapType);
				assert mapTypeId == ((MapTypeImpl)mapType).immutableGetTypeId();		// XXX
				addOrphanClass(mapType);
			}
			assert mapType.isWellContained();
			return mapType;
		}
	}

	@Override
	public @NonNull StandardLibrary getStandardLibrary() {
		return ClassUtil.nonNullState(standardLibrary);
	}

	@Override
	public @NonNull TupleType getTupleType(org.eclipse.ocl.pivot.@NonNull Class oclTupleType, @NonNull TuplePart @NonNull ... parts) {
		@NonNull TupleTypeId tupleTypeId = IdManager.getOrderedTupleTypeId(TypeId.TUPLE_NAME, parts);
		TupleType tupleType = basicGetTupleType(tupleTypeId);
		if (tupleType == null) {
			synchronized (typeId2type) {
				tupleType = (TupleType)typeId2type.get(tupleTypeId);
				if ((tupleType != null) && !tupleType.isWellContained()) {
					removeOrphanClass(tupleType);
					tupleType = null;
				}
				if (tupleType == null) {
					tupleType = new TupleTypeImpl(tupleTypeId);
					EList<@NonNull Property> ownedAttributes = (EList<@NonNull Property>)tupleType.getOwnedProperties();
					for (@NonNull TuplePart part : parts) {
						String partName = NameUtil.getName(part);
						Type partType = part.getType();
						assert partType != null;
						Property asPart = PivotFactory.eINSTANCE.createProperty();
						ownedAttributes.add(asPart);
						asPart.setName(partName);
						asPart.setType(partType);
					}
					ECollections.sort(ownedAttributes, NameUtil.NAMEABLE_COMPARATOR);
					tupleType.getSuperClasses().add(oclTupleType);
					typeId2type.put(tupleTypeId, tupleType);
					addOrphanClass(tupleType);
				}
			}
		}
		assert tupleType.isWellContained();
		return tupleType;
	}

	public void init(@NonNull StandardLibrary standardLibrary, @NonNull String orphanageName, @NonNull String orphanageUri, @NonNull String orphanagePrefix) {
		assert this.standardLibrary == null;
		this.standardLibrary = standardLibrary;
		setName(orphanageName);
		setURI(orphanageUri);
		setNsPrefix(orphanagePrefix);
	}

	private void removeOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass) {
	//	System.out.println("removeOrphanClass " + NameUtil.debugSimpleName(orphanClass) + " : " + orphanClass);
		TypeId typeId = orphanClass.getTypeId();
		assert typeId2type.get(typeId) != null;
		typeId2type.remove(typeId);					// XXX this is not really necessary
		orphanClass.setOwningPackage(null);
	//	getOwnedClasses().remove(orphanClass);		// FIXME why doesn't this always work? - missing inverse in bad overload
		assert orphanClass.eContainer() == null;
		assert !getOwnedClasses().contains(orphanClass);
	}

	@Override
	public void removePackageListener(@NonNull PartialPackages partialPackages) {
		super.removePackageListener(partialPackages);
	}

	@Override
	public void removeReference(@NonNull Type type, @NonNull Element asElement) {
		TypeId typeId = type.getTypeId();
		synchronized (typeId2typeRefs) {
			List<@NonNull Element> list = typeId2typeRefs.get(typeId);
			assert list != null;
			boolean wasRemoved = list.remove(asElement);
			assert wasRemoved;
			if (list.isEmpty()) {
				synchronized (typeId2type) {
					typeId2type.remove(typeId);
					typeId2typeRefs.remove(typeId);
				}
			}
		}
	}
} //OrphanageImpl
