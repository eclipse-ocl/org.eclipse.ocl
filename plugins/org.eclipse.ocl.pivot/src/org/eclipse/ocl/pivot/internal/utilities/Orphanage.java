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
package org.eclipse.ocl.pivot.internal.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Package;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.LambdaTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.PackageImpl;
import org.eclipse.ocl.pivot.internal.TupleTypeImpl;
import org.eclipse.ocl.pivot.internal.ids.GeneralizedNestedTypeIdImpl;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TuplePart;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

import com.google.common.collect.Iterables;

/**
 * The 'orphanage' is a regular Package that transitively contains all metamodel elemants that
 * have no ancestral Package to contain them. A regular package is sufficient and desirable
 * for regular XMI load and save.
 * <br>
 * The Orphanage class adapts the regular Package to add state and behaviour to facilate
 * efficient access to orphans.
 * <br>
 * There is no global orphanage. Any reference to one is stale.
 * <br>
 * Each OCL CompleteModel has a shared orphanage that contains the referenced unique synthesized elements.
 * The shared orphanage is never saved and so the Orphanage class can be used.
 * <br>
 * Each saved ASResource has a local orphanage that contains a selective copy of the shared orphanage so that
 * all references are terminated locally. ASSaver creates this copy via PivotSaveImpl.init(). The local orphanages
 * use a regular Package to avoid the need for Orphanage support in XMI.
 * <br>
 * Correspondingly a loaded ASResource has a local orphanage that is created by conventional XMI APIs.
 */
public class Orphanage extends AdapterImpl
{
	/**
	 * The OrphanResource tailors the inherited ASResource functionality to support the single Resource shared by all
	 * same-OCL consumers. It is not saved and so has no xmi:ids but it does have LUSSIDs
	 * in order to contribute to the signatures of operations.
	 */
	public static class OrphanResource extends ASResourceImpl		// XXX private
	{
		protected OrphanResource(@NonNull URI uri) {
			super(uri, OCLASResourceFactory.getInstance());
			setUpdating(true);
			setSaveable(false);
		}

		@Override
		protected void doUnload() {			// XXX is this still needed
			if (contents != null) {
				for (EObject aContent : contents) {
					if (aContent instanceof Model) {
						for (org.eclipse.ocl.pivot.Package asPackage : PivotUtil.getOwnedPackages((Model)aContent)) {
							if (isOrphanPackage(asPackage)) {
								Orphanage orphanage = basicGetOrphanage(asPackage);
								if (orphanage != null) {
									orphanage.dispose();
								}
							}
						}
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

	private static final Logger logger = Logger.getLogger(Orphanage.class);

	public static final @NonNull URI ORPHANAGE_URI = ClassUtil.nonNullEMF(URI.createURI(PivotConstants.ORPHANAGE_URI + PivotConstants.DOT_OCL_AS_FILE_EXTENSION));

	public static org.eclipse.ocl.pivot.@Nullable Package basicGetContainingOrphanPackage(@Nullable EObject element) {
		for (EObject eObject = element; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof org.eclipse.ocl.pivot.Package) {
				org.eclipse.ocl.pivot.Package asPackage = (org.eclipse.ocl.pivot.Package)eObject;
				if (Orphanage.isOrphanPackage(asPackage)) {
					return asPackage;
				}
			}
		}
		return null;
	}

	/**
	 * Return the orphan package within asModel, or null if none.
	 */
	public static org.eclipse.ocl.pivot.@Nullable Package basicGetOrphanPackage(@NonNull Model asModel) {
		for (org.eclipse.ocl.pivot.Package asPackage : PivotUtil.getOwnedPackages(asModel)) {
			if (isOrphanPackage(asPackage)) {
				return asPackage;
			}
		}
		return null;
	}

	public static Package basicGetOrphanPackage(@NonNull ASResource asResource) {
		for (EObject eContent : asResource.getContents()) {
			if (eContent instanceof Model) {
				return basicGetOrphanPackage((Model)eContent);
			}
		}
		return null;
	}

	public static @Nullable Orphanage basicGetOrphanage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		assert isOrphanPackage(asPackage);
		for (Adapter adapter : asPackage.eAdapters()) {
			if (adapter instanceof Orphanage) {
				return (Orphanage)adapter;
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
				org.eclipse.ocl.pivot.Package asPackage = basicGetOrphanPackage((OrphanResource)aResource);
				if (asPackage != null) {
					return basicGetOrphanage(asPackage);
				}
			}
		}
		return null;
	}

	/**
	 * Return the shared Orphanage that contains asType or null if not in a shared Orphanage.
	 */
	public static @Nullable Orphanage basicGetSharedOrphanage(@NonNull Type asType) {
		EObject eContainer = asType.eContainer(); //.eInternalContainer();
		if (eContainer instanceof org.eclipse.ocl.pivot.Package) {
			org.eclipse.ocl.pivot.Package asPackage = (org.eclipse.ocl.pivot.Package)eContainer;
			if (isOrphanPackage(asPackage) && (asPackage.eResource() instanceof OrphanResource)) {
				return basicGetOrphanage(asPackage);
			}
		}
		return null;
	}

	public static org.eclipse.ocl.pivot.@NonNull Package createOrphanagePackage() {
		org.eclipse.ocl.pivot.Package orphanPackage = PivotFactory.eINSTANCE.createPackage();
		orphanPackage.setName(PivotConstants.ORPHANAGE_NAME);
		orphanPackage.setURI(PivotConstants.ORPHANAGE_URI);
		orphanPackage.setNsPrefix(PivotConstants.ORPHANAGE_PREFIX);
		return orphanPackage;
	}

	/**
	 * @since 1.18
	 */
	public static @NonNull Orphanage createSharedOrphanage(@NonNull StandardLibrary standardLibrary, @NonNull ResourceSet resourceSet) {
		org.eclipse.ocl.pivot.Package orphanPackage = createOrphanagePackage();
		Orphanage sharedOrphanage = new Orphanage(orphanPackage, standardLibrary);
		Model orphanModel = PivotFactory.eINSTANCE.createModel();
		orphanModel.setName(PivotConstants.ORPHANAGE_NAME);;
		orphanModel.setExternalURI(PivotConstants.ORPHANAGE_URI);
		orphanModel.getOwnedPackages().add(sharedOrphanage.getPackage());
		Resource orphanageResource = new OrphanResource(ORPHANAGE_URI);
		orphanageResource.getContents().add(orphanModel);
		resourceSet.getResources().add(orphanageResource);
		return sharedOrphanage;
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
	 * Return true if asElement is transitively contained by a local or shared orphanage.
	 *
	 * @since 1.18
	 */
	public static boolean isOrphanElement(@NonNull Element asElement) {
		org.eclipse.ocl.pivot.Package asPackage = PivotUtil.getContainingPackage(asElement);
		return (asPackage != null) && isOrphanPackage(asPackage);
	}

	/**
	 * Return true if asModel is a shared orphanage for synthesized model elements.
	 *
	 * @since 1.18
	 */
	public static boolean isOrphanModel(@NonNull Model asModel) {
		String uri = asModel.getExternalURI();
		return isOrphanURI(uri);
	}

	/**
	 * Return true if asPackage is an orphanage for synthesized model elements.
	 *
	 * @since 1.18
	 */
	public static boolean isOrphanPackage(org.eclipse.ocl.pivot.@NonNull Package asPackage) {
		String uri = asPackage.getURI();
		return isOrphanURI(uri);
	}

	public static boolean isOrphanURI(String uri) {
		return PivotConstants.ORPHANAGE_URI.equals(uri) || PivotConstantsInternal.OLD_ORPHANAGE_URI.equals(uri);
	}

	private @NonNull PackageImpl orphanPackage;

	private final @NonNull StandardLibrary standardLibrary;

	/**
	 * Shared cache of the lazily created, lazily deleted, specializations of each type.
	 * This identifies all orphans for a given OCL environment/thread/metamodelmanager. Those originating
	 * from loaded models remain owned by the loaded model's orphanage. Only those subsequently created
	 * are present in orphanPackage.ownedClasses.
	 */
	private final @NonNull Map<@NonNull TypeId, @NonNull Type> typeId2type = new HashMap<>();

	/**
	 * The elements that reference each orphan. An element that references more than once e.g. Map<X,X> has a duplicate entry.
	 */
	private final @NonNull Map<@NonNull TypeId, @NonNull List<@NonNull Element>> typeId2typeRefs = new HashMap<>(); // XXX need lazy catchup for loaded orphanages

	public Orphanage(org.eclipse.ocl.pivot.@NonNull Package orphanPackage, @NonNull StandardLibrary standardLibrary) {
		this.orphanPackage = (PackageImpl)orphanPackage;
		orphanPackage.eAdapters().add(this);
		this.standardLibrary = standardLibrary;
	}

	private void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass) {
		TypeId typeId = orphanClass.basicGetTypeId();
		assert typeId == null;
		typeId = orphanClass.getTypeId();
	//	System.out.println("addOrphanClass " + NameUtil.debugSimpleName(orphanClass) + " : " + NameUtil.debugSimpleName(typeId) + " : " + orphanClass);
		Type old = typeId2type.put(typeId, orphanClass);
		assert old == null;
		List<org.eclipse.ocl.pivot.@NonNull Class> ownedClasses = PivotUtilInternal.getOwnedClassesList(orphanPackage);
		assert !ownedClasses.contains(orphanClass);
		ownedClasses.add(orphanClass);
		assert orphanClass.eContainer() == orphanPackage;
		assert ownedClasses.contains(orphanClass);
	}

	public void addReference(@NonNull Type type, @NonNull Element asElement) {
		TypeId typeId = type.getTypeId();
// XXX		assert typeId2type.containsKey(typeId);
		synchronized (typeId2typeRefs) {
			List<@NonNull Element> list = typeId2typeRefs.get(typeId);
			if (list == null) {
				list = new ArrayList<>();
				typeId2typeRefs.put(typeId, list);
			}
			list.add(asElement);
		}
	}

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

/*	@Override
	protected void didAddClass(@NonNull Class asClass) {
		TypeId typeId = asClass.basicGetTypeId();
		if (typeId != null) {			// null for a proto orphan.
			Type old = typeId2type.put(typeId, asClass);
			assert (old == null) || (old == asClass);
		}
		super.didAddClass(asClass);
	} */

	public boolean assertConsistent() {
		Map<EObject, Collection<Setting>> references = EcoreUtil.CrossReferencer.find(orphanPackage.getOwnedClasses());
		assert references != null;
		for (EObject eTarget : references.keySet()) {
			org.eclipse.ocl.pivot.Package containingOrphanPackage = basicGetContainingOrphanPackage(eTarget);
			assert (containingOrphanPackage == null) || (containingOrphanPackage == orphanPackage);
		}
		return true;
	}

	public void dispose() {
	/*	if (ownedClasses != null) {
			((WeakEList<?>)ownedClasses).dispose();
		}
		if (ownedPackages != null) {
			((WeakEList<?>)ownedPackages).dispose();
		} */
		typeId2type.clear();
/*		if (ownedClasses != null) {
			ownedClasses.clear();
		} */
	}

	/**
	 * Traverse the orphanage to prune all entries for types that are no longer well contained
	 * (all transitively referenced types have a non-null eResource()).
	 * <br>
	 * This is an expensive operation that is only needed in long running heavily mutating applications.
	 * Use sparingly.
	 *
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
	} */

	/**
	 * Return the specialized collection type for a collection type characteristics.
	 */
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
				resolveSuperClasses(collectionType, genericType);
			//	collectionType.getSuperClasses().addAll(unspecializedType.getSuperClasses());
				collectionType.setIsNullFree(isNullFree);
				try {
					collectionType.setLower(lower);
				} catch (InvalidValueException e) {
					logger.error("Out of range lower bound for " + specializedTypeId, e);
				}
				try {
					collectionType.setUpper(upper);
				} catch (InvalidValueException e) {
					logger.error("Out of range upper bound for " + specializedTypeId, e);
				}
				collectionType.setUnspecializedElement(genericType);
				addOrphanClass(collectionType);
			}
			assert collectionType.isWellContained();
			return collectionType;
		}
	}

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
				addOrphanClass(lambdaType);
			}
			assert lambdaType.isWellContained();
			return lambdaType;
		}
	}

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
				mapType.setUnspecializedElement(genericType);
				mapType.setEntryClass(entryClass);
				addOrphanClass(mapType);
			}
			assert mapType.isWellContained();
			return mapType;
		}
	}

	/**
	 * Return the specialized map type for a map type descriptor.
	 */
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
				mapType.setUnspecializedElement(genericType);
			//	mapType.setEntryClass(typeParameters.getEntryClass());
				addOrphanClass(mapType);
			}
			assert mapType.isWellContained();
			return mapType;
		}
	}

	public org.eclipse.ocl.pivot.@NonNull Package getPackage() {
		return orphanPackage;
	}

	public org.eclipse.ocl.pivot.@NonNull Class getSpecialization(org.eclipse.ocl.pivot.Class genericClass, @NonNull List<@NonNull ? extends Type> templateArguments) {
		BindingsId bindingsId = IdManager.getBindingsId(templateArguments.toArray(new @NonNull Type[templateArguments.size()]));
		GeneralizedNestedTypeIdImpl classId = (GeneralizedNestedTypeIdImpl) genericClass.getTypeId();
		TypeId specializedId = classId.getSpecializedId(bindingsId);
		TemplateSignature templateSignature = genericClass.getOwnedSignature();
		List<TemplateParameter> templateSignatureParameters = templateSignature.getOwnedParameters();
		int iMax = templateSignatureParameters.size();
		if (templateArguments.size() != iMax) {
			throw new IllegalArgumentException("Incompatible template argument count");
		}
		synchronized (typeId2type) {
		//	System.out.println("getOrphanSpecialization: " + NameUtil.debugSimpleName(specializedId) + " : " + specializedId + " for " + NameUtil.debugSimpleName(genericClass) + " : " + genericClass);
			org.eclipse.ocl.pivot.Class specializedType = (org.eclipse.ocl.pivot.Class)typeId2type.get(specializedId);
			if (specializedType == null) {
				String typeName = genericClass.getName();
				List<TemplateParameter> templateParameters = templateSignature.getOwnedParameters();
				EClass eClass = genericClass.eClass();
				EFactory eFactoryInstance = eClass.getEPackage().getEFactoryInstance();
				specializedType = (org.eclipse.ocl.pivot.Class) eFactoryInstance.create(eClass);
				specializedType.setName(typeName);
				TemplateBinding templateBinding = PivotFactory.eINSTANCE.createTemplateBinding();
				for (int i = 0; i < templateParameters.size(); i++) {
					TemplateParameter formalParameter = templateParameters.get(i);
					if (formalParameter != null) {
						Element templateArgument = templateArguments.get(i);
						if (templateArgument instanceof Type) {
							Type actualType = (Type) templateArgument;
							TemplateParameterSubstitution templateParameterSubstitution = PivotUtil.createTemplateParameterSubstitution(formalParameter, actualType);
							templateBinding.getOwnedSubstitutions().add(templateParameterSubstitution);
						}
					}
				}
				specializedType.getOwnedBindings().add(templateBinding);
				resolveSuperClasses(specializedType, genericClass);
				specializedType.setUnspecializedElement(genericClass);
				addOrphanClass(specializedType);
			//	System.out.println("getOrphanSpecialization: " + NameUtil.debugSimpleName(specializedId) + " : " + specializedId + " => " + NameUtil.debugSimpleName(specializedType) + " : " + specializedType);
			}
			return specializedType;
		}
	}

	public @NonNull StandardLibrary getStandardLibrary() {
		return ClassUtil.nonNullState(standardLibrary);
	}

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
					tupleType = PivotFactory.eINSTANCE.createTupleType();
					List<@NonNull Property> asParts = new ArrayList<>();
					for (@NonNull TuplePart part : parts) {
						String partName = NameUtil.getName(part);
						Type partType = part.getType();
						assert partType != null;
						Property asPart = PivotFactory.eINSTANCE.createProperty();
						asParts.add(asPart);
						asPart.setName(partName);
						asPart.setType(partType);
					}
					Collections.sort(asParts, NameUtil.NAMEABLE_COMPARATOR);
					((TupleTypeImpl)tupleType).init(tupleTypeId, oclTupleType, asParts);
					addOrphanClass(tupleType);
				}
			}
		}
		assert tupleType.isWellContained();
		return tupleType;
	}

/*	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getType(@NonNull Type asType) {
		if (asType instanceof CollectionType) {
			CollectionType asCollectionType = (CollectionType)asType;
			CollectionType asGenericType = (CollectionType)asCollectionType.getUnspecializedElement();
			assert asGenericType != null;
			Type asElementType = PivotUtil.getElementType(asCollectionType);
			boolean isNullFree = asCollectionType.isIsNullFree();
			IntegerValue lowerValue = asCollectionType.getLowerValue();
			UnlimitedNaturalValue upperValue = asCollectionType.getUpperValue();
			return getCollectionType(asGenericType, asElementType, isNullFree, lowerValue, upperValue);
		}
		else if (asType instanceof LambdaType) {
			LambdaType asLambdaType = (LambdaType)asType;
			LambdaType asGenericType = (LambdaType)asLambdaType.getUnspecializedElement();
			assert asGenericType != null;
			Type asContextType = PivotUtil.getContextType(asLambdaType);
			List<@NonNull Type> asParameterTypes = PivotUtil.getParameterType(asLambdaType);
			Type asResultType = PivotUtil.getResultType(asLambdaType);
			return getLambdaType(asGenericType, asContextType, asParameterTypes, asResultType);
		}
		else if (asType instanceof MapType) {
			MapType asMapType = (MapType)asType;
			MapType asGenericType = (MapType)asMapType.getUnspecializedElement();
			assert asGenericType != null;
			Type asKeyType = PivotUtil.getKeyType(asMapType);
			boolean keysAreNullFree = asMapType.isKeysAreNullFree();
			Type asValueType = PivotUtil.getValueType(asMapType);
			boolean valuesAreNullFree = asMapType.isValuesAreNullFree();
			return getMapType(asGenericType, asKeyType, keysAreNullFree, asValueType, valuesAreNullFree);
		}
		else if (asType instanceof TupleType) {
			TupleType asTupleType = (TupleType)asType;
			org.eclipse.ocl.pivot.Class oclTupleType = asTupleType.getSuperClasses().get(0);
			assert oclTupleType != null;
			List<@NonNull Property> asParts = PivotUtilInternal.getOwnedPropertiesList(asTupleType);
			@NonNull TuplePart[] tupleParts = new @NonNull TuplePart[asParts.size()];
			int i = 0;
			for (@NonNull Property asPart : asParts) {
				tupleParts[i++] = new TuplePart.TuplePartImpl(NameUtil.getName(asPart), PivotUtil.getType(asPart));
			}
			return getTupleType(oclTupleType, tupleParts);
		}
	//	else if (asType instanceof WildcardType) {
	//		return getOrphanWildcardType(this);
	//	}
		else if (asType instanceof org.eclipse.ocl.pivot.Class) {
			org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class)asType;
			org.eclipse.ocl.pivot.Class asGenericType = (org.eclipse.ocl.pivot.Class)asClass.getUnspecializedElement();
			if (asGenericType != null) {
				List<@NonNull Type> asTypes = new ArrayList<>();
				for (@NonNull TemplateBinding asBinding : PivotUtil.getOwnedBindings(asClass)) {
					for (@NonNull TemplateParameterSubstitution asSustitution : PivotUtil.getOwnedSubstitutions(asBinding)) {
						asTypes.add(asSustitution.getActual());
					}
				}
				return getSpecialization(asGenericType, asTypes);
			}
			else {
				return asClass;
			}
		}
		throw new UnsupportedOperationException("OrphanageImpl.getType() for " + asType.getClass().getName());
	} */

	public void installLoadedClasses(@NonNull ASResource asResource) {
		org.eclipse.ocl.pivot.Package localOrphanPackage = Orphanage.basicGetOrphanPackage(asResource);
		if (localOrphanPackage != null) {
			Map<org.eclipse.ocl.pivot.@NonNull Class, org.eclipse.ocl.pivot.@NonNull Class> local2shared = null;	// Duplocate orphan and its replacement
			List<org.eclipse.ocl.pivot.@NonNull Class> usableClasses = new ArrayList<>();	// Novel orphans to be 'added' to shared orphaange
			//
			//	Using the old containment resolve the transient typeId and triage usable/rewrite
			//
			for (org.eclipse.ocl.pivot.@NonNull Class asLocalClass : PivotUtil.getOwnedClasses(localOrphanPackage)) {
				TypeId typeId = asLocalClass.getTypeId();
				org.eclipse.ocl.pivot.Class asSharedClass = (org.eclipse.ocl.pivot.Class)typeId2type.get(typeId);
				if (asSharedClass == null) {
					typeId2type.put(typeId, asLocalClass);
					usableClasses.add(asLocalClass);
				}
				else {
					assert asResource.isSaveable();				// XXX !isReadOnly()
					if (local2shared == null) {
						local2shared = new HashMap<>();
					}
					local2shared.put(asLocalClass, asSharedClass);
				}
			}
			//
			//	Eliminate the old containment for rewritten/not-useable classes.
			//
			PivotUtilInternal.getOwnedClassesList(localOrphanPackage).retainAll(usableClasses);		// Exploit compatible ordering.
		//	PivotUtilInternal.getOwnedClassesList(orphanPackage).addAll(migratingClasses);
			//
			//	Rewrite all references to classes that already have shared orphan counterparts.
			//
			if (local2shared != null) {
				Map<EObject, Collection<Setting>> object2references = EcoreUtil.CrossReferencer.find(asResource.getContents());
				for (EObject referencedLocalObject : object2references.keySet()) {
					assert referencedLocalObject != null;
					org.eclipse.ocl.pivot.Class asSharedClass = local2shared.get(referencedLocalObject);
					if (asSharedClass != null) {
						Collection<Setting> settings = object2references.get(referencedLocalObject);
						if (settings != null) {
							relocateReferencesTo(asSharedClass, settings, referencedLocalObject);
						}
					}
					// XXX update typeId2typeRefs
				}
			}
		}
	}

	private void relocateReferencesTo(@NonNull EObject newObject, @NonNull Collection<Setting> settings, @NonNull EObject oldObject) {
		for (Setting setting : settings) {
			EObject referencingObject = setting.getEObject();
			EStructuralFeature eReference = setting.getEStructuralFeature();
			if (!eReference.isDerived() && !eReference.isTransient()) {
				if (eReference.isMany()) {
					@SuppressWarnings("unchecked") List<EObject> referencedObjects = (List<EObject>)referencingObject.eGet(eReference);
					referencedObjects.replaceAll(new UnaryOperator<EObject>() {
						@Override
						public EObject apply(EObject t) {
							return t == oldObject ? newObject : t;
						}
					});
				}
				else {
					referencingObject.eSet(eReference, newObject);
				}
			}
		}
	}

	private void removeOrphanClass(org.eclipse.ocl.pivot.@NonNull Class orphanClass) {
	//	System.out.println("removeOrphanClass " + NameUtil.debugSimpleName(orphanClass) + " : " + orphanClass);
		TypeId typeId = orphanClass.getTypeId();
		assert typeId2type.get(typeId) != null;
		typeId2type.remove(typeId);					// XXX this is not really necessary
		orphanClass.setOwningPackage(null);
	//	getOwnedClasses().remove(orphanClass);		// FIXME why doesn't this always work? - missing inverse in bad overload
		assert orphanClass.eContainer() == null;
		assert !orphanPackage.getOwnedClasses().contains(orphanClass);
	}

	public void removeReference(@NonNull Type type, @NonNull Element asElement) {
		TypeId typeId = type.getTypeId();
		synchronized (typeId2typeRefs) {
			List<@NonNull Element> list = typeId2typeRefs.get(typeId);
			assert list != null;
			boolean wasRemoved = list.remove(asElement);
			if (!wasRemoved) {
				System.out.println("removeReference " + NameUtil.debugSimpleName(asElement) + " " + asElement + " : " + typeId);

			}
			assert wasRemoved;
			if (list.isEmpty()) {
				synchronized (typeId2type) {
					typeId2type.remove(typeId);
					typeId2typeRefs.remove(typeId);
				}
			}
		}
	}

	private void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass) {
		Map<@NonNull TemplateParameter, @NonNull Element> specializedFormal2Actual = new HashMap<>();
		for (@NonNull TemplateBinding specializedTemplateBinding : PivotUtil.getOwnedBindings(specializedClass)) {
			for (@NonNull TemplateParameterSubstitution specializedParameterSubstitution : PivotUtil.getOwnedSubstitutions(specializedTemplateBinding)) {
				TemplateParameter specializedFormal = PivotUtil.getFormal(specializedParameterSubstitution);
				Element specializedActual = PivotUtil.getActual(specializedParameterSubstitution);
				specializedFormal2Actual.put(specializedFormal, specializedActual);
			}
		}
		List<org.eclipse.ocl.pivot.@NonNull Class> superClasses = PivotUtilInternal.getSuperClassesList(specializedClass);
		for (org.eclipse.ocl.pivot.@NonNull Class superClass : PivotUtil.getSuperClasses(unspecializedClass)) {
			org.eclipse.ocl.pivot.@NonNull Class resolvedSuperClass;
			org.eclipse.ocl.pivot.Class genericSuperClass = PivotUtil.getUnspecializedTemplateableElement(superClass);
			Iterable<@NonNull TemplateBinding> superTemplateBindings = PivotUtil.getOwnedBindings(superClass);
			if (Iterables.size(superTemplateBindings) <= 0) {
				resolvedSuperClass = superClass; //getType(superClass);
			}
			else if (superClass instanceof CollectionType) {
				CollectionType superCollectionType = (CollectionType)superClass;
				Type superElementType = resolveSuperElementType(PivotUtil.getElementType(superCollectionType), specializedFormal2Actual);
				resolvedSuperClass = getCollectionType((CollectionType)genericSuperClass, superElementType, superCollectionType.isIsNullFree(), superCollectionType.getLowerValue(), superCollectionType.getUpperValue());
			}
			// XXX LambdaType
			else if (superClass instanceof MapType) {
				MapType superMapType = (MapType)superClass;
				Type superKeyType = resolveSuperElementType(PivotUtil.getKeyType(superMapType), specializedFormal2Actual);
				Type superValueType = resolveSuperElementType(PivotUtil.getValueType(superMapType), specializedFormal2Actual);
				resolvedSuperClass = getMapType((MapType)genericSuperClass, superKeyType, superMapType.isKeysAreNullFree(), superValueType, superMapType.isValuesAreNullFree());
			}
			// XXX TupleType
			else {
				List<org.eclipse.ocl.pivot.@NonNull Type> superActuals = new ArrayList<>();
				for (@NonNull TemplateBinding superTemplateBinding : superTemplateBindings) {
					for (@NonNull TemplateParameterSubstitution superParameterSubstitution : PivotUtil.getOwnedSubstitutions(superTemplateBinding)) {
						Type superActualType = resolveSuperElementType((Type)PivotUtil.getActual(superParameterSubstitution), specializedFormal2Actual);			// XXX cast
						superActuals.add(superActualType);
					}
				}
				resolvedSuperClass = getSpecialization(genericSuperClass, superActuals);
			}
			superClasses.add(resolvedSuperClass);
		}
	}

	protected @NonNull Type resolveSuperElementType(@NonNull Type superElementType, @NonNull Map<@NonNull TemplateParameter, @NonNull Element> specializedFormal2Actual) {
		Element resolvedSuperElementType = specializedFormal2Actual.get(superElementType);
		return resolvedSuperElementType != null ? (Type) resolvedSuperElementType : superElementType;
	}

	@Override
	public @NonNull String toString() {
		EObject eContainer = orphanPackage.eContainer();
		if (eContainer instanceof Model) {
			return "Orphanage:\"" + ((Model)eContainer).getExternalURI() + "\"(" + typeId2type.size() + ")";
		}
		else {
			return NameUtil.debugSimpleName(this) + "(" + typeId2type.size() + ")";
		}
	}
}
