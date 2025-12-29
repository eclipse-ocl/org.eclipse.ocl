/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.NormalizedTemplateParameter;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.PartialStandardLibraryImpl;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.TemplateSpecialization;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.ASSaverNormalizeVisitor;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * @since 7.0
 */
public class SaverStandardLibraryImpl extends PartialStandardLibraryImpl implements ASSaver
{
	/**
	 * @since 7.0
	 */
	protected static class ClassByTypeIdAndEntryClassComparator implements Comparator<org.eclipse.ocl.pivot.@NonNull Class>
	{
		@Override
		public int compare(org.eclipse.ocl.pivot.@NonNull Class o1, org.eclipse.ocl.pivot.@NonNull Class o2) {
			TypeId t1 = o1.getTypeId();
			TypeId t2 = o2.getTypeId();
			String s1 = t1.toString();
			String s2 = t2.toString();
			int compareTo = s1.compareTo(s2);
			if (compareTo != 0) {
				return compareTo;
			}
			if ((o1 instanceof MapType) && (o2 instanceof MapType)) {
				org.eclipse.ocl.pivot.Class ec1 = ((MapType)o1).getEntryClass();
				org.eclipse.ocl.pivot.Class ec2 = ((MapType)o2).getEntryClass();
				if (ec1 == null) {
					if (ec2 != null) {
						return -1;
					}
				}
				else {
					if (ec2 == null) {
						return 1;
					}
					else {
						t1 = ec1.getTypeId();
						t2 = ec2.getTypeId();
						s1 = t1.toString();
						s2 = t2.toString();
						compareTo = s1.compareTo(s2);
					}
				}
			}
			return compareTo;
		}
	}

	protected final @NonNull ASResource resource;

	private @Nullable List<org.eclipse.ocl.pivot.@NonNull Class> orphanClasses = null;

	private @NonNull Map<@NonNull Element, @NonNull Element> remote2local = new HashMap<>();

	/**
	 * The appropriate normalization visitor for each Resource.
	 * @since 7.0
	 */
	private /*@LazyNonNull*/ Map<@NonNull Resource, @NonNull ASSaverNormalizeVisitor> resource2normalizeVisitor;

	private @Nullable BagType bagType = null;
	private @Nullable BooleanType booleanType = null;
	private org.eclipse.ocl.pivot.@Nullable Class classType = null;
	private @Nullable CollectionType collectionType = null;
	private org.eclipse.ocl.pivot.@Nullable Class enumerationType = null;
	private @Nullable PrimitiveType integerType = null;
	private @Nullable MapType mapType = null;
	private @Nullable AnyType oclAnyType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclComparableType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclElementType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclEnumerationType = null;
//	private @Nullable Operation oclInvalidOperation = null;
//	private @Nullable Property oclInvalidProperty = null;
	private @Nullable InvalidType oclInvalidType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclLambdaType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclMessageType = null;
	private @Nullable SelfType oclSelfType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclStereotypeType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclSummableType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclTupleType = null;
	private org.eclipse.ocl.pivot.@Nullable Class oclTypeType = null;
	private @Nullable VoidType oclVoidType = null;
	private @Nullable CollectionType orderedCollectionType = null;
	private @Nullable OrderedSetType orderedSetType = null;
	private @Nullable PrimitiveType realType = null;
	private @Nullable SequenceType sequenceType = null;
	private @Nullable SetType setType = null;
	private @Nullable PrimitiveType stringType = null;
	private @Nullable CollectionType uniqueCollectionType = null;
	private @Nullable PrimitiveType unlimitedNaturalType = null;

	public SaverStandardLibraryImpl(@NonNull ASResource resource) {
		this.resource = resource;
		for (EObject eRoot : resource.getContents()) {
			if (eRoot instanceof Model) {
				for (org.eclipse.ocl.pivot.Package asPackage : PivotUtil.getOwnedPackages((Model)eRoot)) {
					if (Orphanage.isOrphanage(asPackage)) {
						loadOrphanage(asPackage);
					}
					else if (asPackage instanceof Library) {
						loadLibrary((Library)asPackage);
					}
				}
			}
		}
	}

	@Override
	public void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class localClass) {		// XXX is override necessary / consistent
		List<org.eclipse.ocl.pivot.@NonNull Class> orphanClasses2 = orphanClasses;
		if (orphanClasses2 == null) {
			org.eclipse.ocl.pivot.Package localOrphanPackage = getLocalOrphanage();
			orphanClasses2 = orphanClasses = PivotUtil.getOwnedClassesList(localOrphanPackage);
		}
		orphanClasses2.add(localClass);
	}

	@Override
	public @NonNull BagType getBagType() {
		return bagType != null ? bagType : super.getBagType();
	}

	@Override
	public @NonNull BooleanType getBooleanType() {
		return booleanType != null ? booleanType : super.getBooleanType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getClassType() {
		return classType != null ? classType : super.getClassType();
	}

	@Override
	public @NonNull CollectionType getCollectionType() {
		return collectionType != null ? collectionType : super.getCollectionType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEnumerationType() {
		return enumerationType != null ? enumerationType : super.getEnumerationType();
	}

	@Override
	public @NonNull FlatClass getFlatClass(@NonNull Class type) {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull PrimitiveType getIntegerType() {
		return integerType != null ? integerType : super.getIntegerType();
	}

	@Override
	public @NonNull Element getLocal(@NonNull Element element) {
		Element local = remote2local.get(element);
		Element element3 = local != null ? local : element;
		assert (element3.eResource() ==getResource()) || !Orphanage.isOrphan(element3);
		return element3;
	}

	protected org.eclipse.ocl.pivot.@NonNull Package getLocalOrphanage() {
		Model asModel = PivotUtil.getModel(resource);
		org.eclipse.ocl.pivot.Package localOrphanPackage = Orphanage.basicGetLocalOrphanPackage(asModel);
		if (localOrphanPackage == null) {
			localOrphanPackage = Orphanage.createLocalOrphanPackage(asModel);
		}
		return localOrphanPackage;
	}

	@Override
	public @NonNull MapType getMapType() {
		return mapType != null ? mapType : super.getMapType();
	}

	/**
	 * @since 7.0
	 */
	protected @NonNull ASSaverNormalizeVisitor getNormalizeVisitor(@NonNull EObject eObject) {
		Resource resource = eObject.eResource();
		if (resource == null) {
			throw new IllegalStateException("Cannot locate " + ASSaverNormalizeVisitor.class.getName() + " for resource-less " + eObject.eClass().getName());
		}
		if (resource2normalizeVisitor == null) {
			resource2normalizeVisitor = new HashMap<>();
		}
		ASSaverNormalizeVisitor visitor = resource2normalizeVisitor.get(resource);
		if (visitor != null) {
			return visitor;
		}
		if (resource instanceof ASResource) {
			ASResource asResource = (ASResource)resource;
			visitor = asResource.getASResourceFactory().createASSaverNormalizeVisitor(this);
			resource2normalizeVisitor.put(resource, visitor);
			return visitor;
		}
		else {
			throw new IllegalStateException("Cannot locate " + ASSaverNormalizeVisitor.class.getName() + " for non-OCL " + resource.getClass().getName());
		}
	}

	@Override
	public @NonNull AnyType getOclAnyType() {
		return oclAnyType != null ? oclAnyType : super.getOclAnyType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclComparableType() {
		return oclComparableType != null ? oclComparableType : super.getOclElementType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclElementType() {
		return oclElementType != null ? oclElementType : super.getOclElementType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclEnumerationType() {
		return oclEnumerationType != null ? oclEnumerationType : super.getOclEnumerationType();
	}

	@Override
	public @NonNull Operation getOclInvalidOperation() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Property getOclInvalidProperty() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull InvalidType getOclInvalidType() {
		return oclInvalidType != null ? oclInvalidType : super.getOclInvalidType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclLambdaType() {
		return oclLambdaType != null ? oclLambdaType : super.getOclLambdaType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclMessageType() {
		return oclMessageType != null ? oclMessageType : super.getOclMessageType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSelfType() {
		return oclSelfType != null ? oclSelfType : super.getOclSelfType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclStereotypeType() {
		return oclStereotypeType != null ? oclStereotypeType : super.getOclStereotypeType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSummableType() {
		return oclSummableType != null ? oclSummableType : super.getOclSummableType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTupleType() {
		return oclTupleType != null ? oclTupleType : super.getOclTupleType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTypeType() {
		return oclTypeType != null ? oclTypeType : super.getOclTypeType();
	}

	@Override
	public @NonNull VoidType getOclVoidType() {
		return oclVoidType != null ? oclVoidType : super.getOclVoidType();
	}

	@Override
	public @NonNull CollectionType getOrderedCollectionType() {
		return orderedCollectionType != null ? orderedCollectionType : super.getOrderedCollectionType();
	}

	@Override
	public @NonNull OrderedSetType getOrderedSetType() {
		return orderedSetType != null ? orderedSetType : super.getOrderedSetType();
	}

	@Override
	public @NonNull PrimitiveType getRealType() {
		return realType != null ? realType : super.getRealType();
	}

	@Override
	public @NonNull Resource getResource() {
		return resource;
	}

	@Override
	public @NonNull SequenceType getSequenceType() {
		return sequenceType != null ? sequenceType : super.getSequenceType();
	}

	@Override
	public @NonNull SetType getSetType() {
		return setType != null ? setType : super.getSetType();
	}

	@Override
	public @NonNull PrimitiveType getStringType() {
		return stringType != null ? stringType : super.getStringType();
	}

	@Override
	public @NonNull CollectionType getUniqueCollectionType() {
		return uniqueCollectionType != null ? uniqueCollectionType : super.getUniqueCollectionType();
	}

	@Override
	public @NonNull PrimitiveType getUnlimitedNaturalType() {
		return unlimitedNaturalType != null ? unlimitedNaturalType : super.getUnlimitedNaturalType();
	}

	private void loadLibrary(@NonNull Library asPackage) {
		for (org.eclipse.ocl.pivot.@NonNull Class asLocalClass : PivotUtil.getOwnedClasses(asPackage)) {
			String name = asLocalClass.getName();
			switch (name) {
				case TypeId.BAG_NAME : bagType = (BagType) asLocalClass; break;
				case TypeId.BOOLEAN_NAME : booleanType = (BooleanType) asLocalClass; break;
				case TypeId.CLASS_NAME :  classType = asLocalClass; break;
				case TypeId.COLLECTION_NAME : collectionType = (CollectionType) asLocalClass; break;
				case TypeId.ENUMERATION_NAME :  enumerationType = asLocalClass; break;
				case TypeId.INTEGER_NAME : integerType = (PrimitiveType) asLocalClass; break;
				case TypeId.MAP_NAME : mapType = (MapType) asLocalClass; break;
				case TypeId.OCL_ANY_NAME : oclAnyType = (AnyType) asLocalClass; break;
				case TypeId.OCL_COMPARABLE_NAME :  oclComparableType = asLocalClass; break;
				case TypeId.OCL_ELEMENT_NAME :  oclElementType = asLocalClass; break;
				case TypeId.OCL_ENUMERATION_NAME :  oclEnumerationType = asLocalClass; break;
			//	case TypeId. : oclInvalidOperation = (Operation) asLocalClass; break;
			//	case TypeId. : oclInvalidProperty = (Property) asLocalClass; break;
				case TypeId.OCL_INVALID_NAME : oclInvalidType = (InvalidType) asLocalClass; break;
				case TypeId.OCL_LAMBDA_NAME : oclLambdaType = asLocalClass; break;
				case TypeId.OCL_MESSAGE_NAME : oclMessageType = asLocalClass; break;
				case TypeId.OCL_SELF_NAME : oclSelfType = (SelfType) asLocalClass; break;
				case TypeId.OCL_STEREOTYPE_NAME :  oclStereotypeType = asLocalClass; break;
				case TypeId.OCL_SUMMABLE_NAME :  oclSummableType = asLocalClass; break;
				case TypeId.OCL_TUPLE_NAME : oclTupleType = asLocalClass; break;
				case TypeId.OCL_TYPE_NAME :  oclTypeType = asLocalClass; break;
				case TypeId.OCL_VOID_NAME : oclVoidType = (VoidType) asLocalClass; break;
				case TypeId.ORDERED_COLLECTION_NAME : orderedCollectionType = (CollectionType) asLocalClass; break;
				case TypeId.ORDERED_SET_NAME : orderedSetType = (OrderedSetType) asLocalClass; break;
				case TypeId.REAL_NAME : realType = (PrimitiveType) asLocalClass; break;
				case TypeId.SEQUENCE_NAME : sequenceType = (SequenceType) asLocalClass; break;
				case TypeId.SET_NAME : setType = (SetType) asLocalClass; break;
				case TypeId.STRING_NAME : stringType = (PrimitiveType) asLocalClass; break;
				case TypeId.UNIQUE_COLLECTION_NAME : uniqueCollectionType = (CollectionType) asLocalClass; break;
				case TypeId.UNLIMITED_NATURAL_NAME : unlimitedNaturalType = (PrimitiveType) asLocalClass; break;
			}
		}
	}

	private void loadOrphanage(org.eclipse.ocl.pivot.@NonNull Package localOrphanage) {
		for (org.eclipse.ocl.pivot.@NonNull Class asLocalClass : PivotUtil.getOwnedClasses(localOrphanage)) {
			if (asLocalClass instanceof CollectionType) {
				getCollectionTypeManager().load((CollectionType)asLocalClass);
			}
			else if (asLocalClass instanceof LambdaType) {
				getLambdaTypeManager().load((LambdaType)asLocalClass);
			}
			else if (asLocalClass instanceof MapType) {
				getMapTypeManager().load((MapType)asLocalClass);
			}
			else if (asLocalClass instanceof NormalizedTemplateParameter) {
				;
			}
			else if (asLocalClass instanceof TupleType) {
				getTupleTypeManager().load((TupleType)asLocalClass);
			}
			else if (asLocalClass instanceof WildcardType) {
				;
			}
			else if (Orphanage.isOrphan(asLocalClass)) {
				;
			}
			else {
				getSpecializedTypeManager().load(asLocalClass);
			}
		}
	}

	protected @NonNull CollectionType localizeCollectionType(@NonNull CollectionType asCollectionType) {
		// parent package is localized by the CollectionTypeManager
		// attributes are localized here along with the class
		CollectionType genericCollectionType = PivotUtil.getGenericElement(asCollectionType);
		Type elementType = localizeElement(PivotUtil.getElementType(asCollectionType));
		boolean isNullFree = asCollectionType.isIsNullFree();
		IntegerValue lowerValue = asCollectionType.getLowerValue();
		UnlimitedNaturalValue upperValue = asCollectionType.getUpperValue();
		CollectionType localCollectionType = getCollectionType(genericCollectionType, elementType, isNullFree, lowerValue, upperValue);
		resolveSuperClasses(localCollectionType, genericCollectionType);
		assert localCollectionType.eContainer() != null;
		remote2local.put(asCollectionType, localCollectionType);
		return localCollectionType;
	}

	protected @NonNull LambdaType localizeLambdaType(@NonNull LambdaType asLambdaType) {
		// parent package is localized by the LambdaTypeManager
		// attributes are localized here along with the class
		LambdaType genericLambdaType = PivotUtil.getGenericElement(asLambdaType);
		LambdaParameter contextParameter = PivotUtil.getOwnedContext(asLambdaType);
		Type localContextType = localizeElement(PivotUtil.getType(contextParameter));
		TypedElement localContextParameter = LambdaTypeManager.createCandidateLambdaParameter(PivotUtil.getName(contextParameter), localContextType, contextParameter.isIsRequired());
		List<@NonNull LambdaParameter> ownedParameters = PivotUtil.getOwnedParametersList(asLambdaType);
		List<@NonNull TypedElement> localParameters = new ArrayList<>(ownedParameters.size());
		for (@NonNull LambdaParameter asParameter : ownedParameters) {
			Type parameterType = localizeElement(PivotUtil.getType(asParameter));
			TypedElement localParameter = LambdaTypeManager.createCandidateLambdaParameter(PivotUtil.getName(asParameter), parameterType, asParameter.isIsRequired());
			localParameters.add(localParameter);
		}
		LambdaParameter resultParameter = PivotUtil.getOwnedResult(asLambdaType);
		Type localResultType = localizeElement(PivotUtil.getType(resultParameter));
		TypedElement localResultParameter = LambdaTypeManager.createCandidateLambdaParameter(PivotUtil.getName(resultParameter), localResultType, resultParameter.isIsRequired());
		LambdaType localLambdaType = getLambdaType(localContextParameter, localParameters, localResultParameter, null);
		resolveSuperClasses(localLambdaType, genericLambdaType);
		assert localLambdaType.eContainer() != null;
		remote2local.put(asLambdaType, localLambdaType);
		return localLambdaType;
	}

	@SuppressWarnings("unchecked")
	protected <T extends Element> @NonNull T localizeElement(@NonNull T asReferencedElement) {
		if (!Orphanage.isOrphan(asReferencedElement)) {
			return asReferencedElement;		// Non-orphan is referenceable and so does not need localization
		}
		EObject localElement = remote2local.get(asReferencedElement);
		if (localElement != null) {
			return (T)localElement;			// re-use existing localization
		}
		if (asReferencedElement instanceof CollectionType) {
			return (T)localizeCollectionType((CollectionType)asReferencedElement);
		}
		else if (asReferencedElement instanceof LambdaType) {
			return (T)localizeLambdaType((LambdaType)asReferencedElement);
		}
		else if (asReferencedElement instanceof MapType) {
			return (T)localizeMapType((MapType)asReferencedElement);
		}
		else if (asReferencedElement instanceof NormalizedTemplateParameter) {
			return (T)localizeNormalizedTemplateParameter((NormalizedTemplateParameter)asReferencedElement);
		}
		else if (asReferencedElement instanceof Property) {
			return (T) localizeProperty((Property)asReferencedElement);
		}
		else if (asReferencedElement instanceof TupleType) {
			return (T)localizeTupleType((TupleType)asReferencedElement);
		}
		else if (asReferencedElement instanceof WildcardType) {
			return (T)localizeWildcardType((WildcardType)asReferencedElement);
		}
		else if (asReferencedElement instanceof org.eclipse.ocl.pivot.Class) {
			org.eclipse.ocl.pivot.Class asClass = (org.eclipse.ocl.pivot.Class)asReferencedElement;
			org.eclipse.ocl.pivot.Class genericSpecializedClass = PivotUtil.getGenericElement(asClass);
			List<@NonNull TemplateParameter> asTemplateParameters = genericSpecializedClass.basicGetOwnedTemplateParameters();
			if (asTemplateParameters != null) {
				return (T)localizeSpecializedClass(asClass);
			}
			else {
				// This class only localized when child properties are localized on demand.
				return (T)asClass;		// Only implicit properties
			}
		}
		else if (asReferencedElement instanceof org.eclipse.ocl.pivot.Package) {
			org.eclipse.ocl.pivot.Package asPackage = (org.eclipse.ocl.pivot.Package)asReferencedElement;
			// This package only localized when child classes are localized on demand.
			return (T)asPackage;
		}
		throw new UnsupportedOperationException();
	}

	protected @NonNull MapType localizeMapType(@NonNull MapType asMapType) {
		// parent package is localized by the MapTypeManager
		// attributes are localized here along with the class
		MapType genericMapType = PivotUtil.getGenericElement(asMapType);
		Type keyType = localizeElement(PivotUtil.getKeyType(asMapType));
		Type valueType = localizeElement(PivotUtil.getValueType(asMapType));
		boolean keysAreNullFree = asMapType.isKeysAreNullFree();
		boolean valuesAreNullFree = asMapType.isValuesAreNullFree();
		MapType localMapType = getMapType(keyType, keysAreNullFree, valueType, valuesAreNullFree);
		resolveSuperClasses(localMapType, genericMapType);
		assert localMapType.eContainer() != null;
		remote2local.put(asMapType, localMapType);
		return localMapType;
	}

	protected @NonNull NormalizedTemplateParameter localizeNormalizedTemplateParameter(@NonNull NormalizedTemplateParameter asNormalizedTemplateParameter) {
		// create the corresponding local singletons
		int index = asNormalizedTemplateParameter.getIndex();
		org.eclipse.ocl.pivot.@NonNull Package localOrphanage = getLocalOrphanage();
		NormalizedTemplateParameter localNormalizedTemplateParameter = Orphanage.getNormalizedTemplateParameter(localOrphanage, index);
		remote2local.put(asNormalizedTemplateParameter, localNormalizedTemplateParameter);
		return localNormalizedTemplateParameter;
	}

	/**
	 * Prepare a pivot resource for save by creating local copies of all shared orphans.
	 * References to the shared orphans are not redirected to avoid mutation of prevailing resources.
	 * Rather BaseCSXMIResource.getHREF redirects when establishing the persisted reference.
	 *
	 * @since 7.0
	 */
	@Override
	public void localize() {
		Model asModel = PivotUtil.getModel(resource);
		org.eclipse.ocl.pivot.Package localOrphanPackage = Orphanage.basicGetLocalOrphanPackage(asModel);
		if (localOrphanPackage != null) {
			loadOrphanage(localOrphanPackage);
		}
		List<@NonNull EObject> moreEObjects = resource.getContents();
		while ((moreEObjects != null) && !moreEObjects.isEmpty()) {
			Map<EObject, Collection<Setting>> references = EcoreUtil.CrossReferencer.find(moreEObjects);
			moreEObjects = null;
			for (Map.Entry<EObject, Collection<Setting>> entry : references.entrySet()) {
				EObject eTarget = entry.getKey();
				if (eTarget instanceof Element) {
					Element localETarget = localizeElement((Element)eTarget);
					if (localETarget != eTarget) {
						if (moreEObjects == null) {
							moreEObjects = new ArrayList<>();
						}
						moreEObjects.add(localETarget);
					}
				}
			}
		}

	/*	Map<EObject, Collection<Setting>> references = EcoreUtil.CrossReferencer.find(resource.getContents());
		for (EObject eTarget : references.keySet()) {
			if (eTarget instanceof Element) {
				if ((eTarget instanceof Property) && ((Property)eTarget).isIsImplicit()) {
					continue;
				}
				Resource eResource = eTarget.eResource();
				if ((eResource != resource) && Orphanage.isOrphan((Element)eTarget)) {
					EObject localTarget = remote2local.get(eTarget);
					assert localTarget instanceof Element;		-- fails for orphan Package
					assert (localTarget.eResource() == resource) && Orphanage.isOrphan((Element) localTarget);
				}
			}
		} */
		if (orphanClasses != null) {
			ECollections.sort((EList<org.eclipse.ocl.pivot.@NonNull Class>)orphanClasses, new ClassByTypeIdAndEntryClassComparator());
		}
	}

	protected @NonNull Property localizeProperty(@NonNull Property asProperty) {
		EObject eContainer = asProperty.eContainer();
		if (eContainer instanceof TupleType) {				// A tuple part
			localizeTupleType((TupleType)eContainer);		// Tuple parts localized by TupleType
			Property localProperty = (Property)remote2local.get(asProperty);
			assert localProperty != null;
			return localProperty;
		}
		else if (asProperty.isIsImplicit()) {
			// implicit properties are not localized // XXX ??? why aren't they also transient and volatile ??
			return asProperty;
		}
		else {
			throw new UnsupportedOperationException();
		/*	assert asProperty.isIsImplicit();
			assert !asProperty.isIsComposite();
			assert !asProperty.isIsDerived();
		//	assert !asProperty.isIsResolveProxies();
			assert !asProperty.isIsTransient();
			assert !asProperty.isIsVolatile();
			Property asOpposite = asProperty.getOpposite();
			assert asOpposite != null;
			assert resource == asOpposite.eResource();
			return remote2local.get(asProperty); */
		}
	}

	protected org.eclipse.ocl.pivot.@NonNull Class localizeSpecializedClass(org.eclipse.ocl.pivot.@NonNull Class asSpecializedClass) {
		org.eclipse.ocl.pivot.Class genericSpecializedClass = PivotUtil.getGenericElement(asSpecializedClass);
		List<@NonNull TemplateParameter> asTemplateParameters = PivotUtil.getOwnedTemplateParametersList(genericSpecializedClass);
		TemplateSpecialization templateSpecialization = TemplateSpecialization.getTemplateSpecialization(asSpecializedClass);
		List<@NonNull Type> localTemplateArguments = new ArrayList<@NonNull Type>(asTemplateParameters.size());
		for (@NonNull TemplateParameter templateParameter : asTemplateParameters) {
			Type templateArgument = templateSpecialization.get(templateParameter);
			assert templateArgument != null;
			localTemplateArguments.add(localizeElement(templateArgument));
		}
		org.eclipse.ocl.pivot.Class localSpecializedClass = getSpecializedType(genericSpecializedClass, localTemplateArguments);
		remote2local.put(asSpecializedClass, localSpecializedClass);
		for (@NonNull Property asProperty : PivotUtil.getOwnedProperties(asSpecializedClass)) {
			if (asProperty.isIsImplicit()) {			// XXX is this redundant / doable lazily on demand
				assert asProperty.isIsImplicit();
				assert !asProperty.isIsComposite();
				assert !asProperty.isIsDerived();
			//	assert !asProperty.isIsResolveProxies();
				assert !asProperty.isIsTransient();
				assert !asProperty.isIsVolatile();
				Property asOpposite = asProperty.getOpposite();
				assert asOpposite != null;
				Type asType = localizeElement(PivotUtil.getType(asProperty));
				Property localProperty = PivotUtil.createProperty(PivotUtil.getName(asProperty), asType);
				localProperty.setIsReadOnly(asProperty.isIsReadOnly());
				localProperty.setIsRequired(asProperty.isIsRequired());
				localProperty.setIsResolveProxies(asProperty.isIsResolveProxies());
				localProperty.setOpposite(asOpposite);
				localSpecializedClass.getOwnedProperties().add(localProperty);
				remote2local.put(asProperty, localProperty);
			}
		}
		return localSpecializedClass;
	}

	protected @NonNull TupleType localizeTupleType(@NonNull TupleType asTupleType) {
		// parent package is localized by the TupleTypeManager
		// child parts are localized here along with the class
		TupleTypeId tupleTypeId = asTupleType.getTupleTypeId();
		TupleType localTupleType = getTupleType(tupleTypeId);
		Element old = remote2local.put(asTupleType, localTupleType);
		Iterable<@NonNull Property> localParts = PivotUtil.getOwnedProperties(localTupleType);
		for (@NonNull Property asPart : PivotUtil.getOwnedProperties(asTupleType)) {
			Property localPart = NameUtil.getNameable(localParts, PivotUtil.getName(asPart));
			assert localPart != null;
			remote2local.put(asPart, localPart);
		}
		return localTupleType;
	}

	protected @NonNull WildcardType localizeWildcardType(@NonNull WildcardType asWildcardType) {
		// create the corresponding local singleton
		org.eclipse.ocl.pivot.@NonNull Package localOrphanage = getLocalOrphanage();
		WildcardType localWildcardType = Orphanage.getOrphanWildcardType(localOrphanage);
		remote2local.put(asWildcardType, localWildcardType);
		return localWildcardType;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public void normalizeContents() {
		List<@NonNull EObject> allContents = new ArrayList<>();
		for (@NonNull TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Visitable) {
				allContents.add(eObject);
			}
		}
		Map<EClass, @NonNull ASSaverNormalizeVisitor> eClass2normalizeVisitor = new HashMap<>();
		for (@NonNull EObject eObject : allContents) {
			EClass eClass = eObject.eClass();
			ASSaverNormalizeVisitor normalizeVisitor = eClass2normalizeVisitor.get(eClass);
			if (normalizeVisitor == null) {
				normalizeVisitor = getNormalizeVisitor(eObject);
				eClass2normalizeVisitor.put(eClass, normalizeVisitor);
			}
			normalizeVisitor.safeVisit((Visitable) eObject);
		}
	}

	@Override
	public void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass) {
		super.resolveSuperClasses(specializedClass, unspecializedClass);
		List<org.eclipse.ocl.pivot.@NonNull Class> superClassesList = PivotUtil.getSuperClassesList(specializedClass);
		for (int i = 0; i < superClassesList.size(); i++) {
			org.eclipse.ocl.pivot.@NonNull Class superClass = superClassesList.get(i);
		//	if (Orphanage.isOrphan(superClass) && (superClass.eResource() != resource)) {
			org.eclipse.ocl.pivot.Class localSuperClass = localizeElement(superClass);
			if (localSuperClass != superClass) {
				superClassesList.set(i, localSuperClass);
			}
		//	}
		}
	}
}