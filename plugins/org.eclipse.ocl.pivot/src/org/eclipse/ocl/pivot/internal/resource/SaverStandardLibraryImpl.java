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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
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
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.PartialStandardLibraryImpl;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.internal.manager.TemplateSpecialization;
import org.eclipse.ocl.pivot.internal.resource.ASSaver.ClassByTypeIdAndEntryClassComparator;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

class SaverStandardLibraryImpl extends PartialStandardLibraryImpl
{
	protected final @NonNull ASResource resource;

	private @Nullable List<org.eclipse.ocl.pivot.@NonNull Class> orphanClasses = null;

	private @NonNull Map<@NonNull Element, @NonNull Element> remote2local = new HashMap<>();
//	private @NonNull Map<@NonNull Element, @NonNull Element> local2remote = new HashMap<>();

//	private @Nullable Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap = null;

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
	//	org.eclipse.ocl.pivot.Package orphanage = PivotFactory.eINSTANCE.createPackage();
	//	orphanage.setName(PivotConstants.ORPHANAGE_NAME);
	//	orphanage.setNsPrefix(PivotConstants.ORPHANAGE_PREFIX);
	//	orphanage.setURI(PivotConstants.ORPHANAGE_URI);
	//	this.orphanage = orphanage;
	//	resource.getContents().add(orphanage);
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

/*	private void defineLibraryTypes(@NonNull Iterable<org.eclipse.ocl.pivot.@NonNull Class> pivotTypes) {
		Map<@NonNull String, org.eclipse.ocl.pivot.@NonNull Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
		if (nameToLibraryTypeMap2 == null) {
			nameToLibraryTypeMap = nameToLibraryTypeMap2 = new HashMap<>();
		}
		for (org.eclipse.ocl.pivot.@NonNull Class pivotType : pivotTypes) {
			String name = pivotType.getName();
			if (name != null) {
				if ("Model".equals(name) || "UnlimitedNatural".equals(name)) {
					getClass();		// XXX
				}
				//
				// Multiple libraries may exploit CompleteClasses, so nameToLibraryTypeMap caches just the first.
				if (!nameToLibraryTypeMap2.containsKey(name)) {
					@SuppressWarnings("unused")
					org.eclipse.ocl.pivot.Class oldType = nameToLibraryTypeMap2.put(name, pivotType);
				/ *	if ((oldType != null) && (oldType != pivotType)) {
						if (!(oldType instanceof PrimitiveType) || !(pivotType instanceof PrimitiveType)) {		// User primitives may only be DataType e.g. testQVTrLoad_ATL2QVTr_qvtre
						//	logger.warn("Conflicting pivot type '" + name + "'");
							System.err.println("Conflicting pivot type '" + name + "'");
							// qvtruntimelibrary has two an extra definition of Model from QVTimperativeLibrary
						}
					} * /
				}
			}
		}
	} */

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

	public @Nullable EObject getLocal(@NonNull EObject target) {
	//	return local2remote.get(target);
		return remote2local.get(target);
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
	//	Map<@NonNull String, @NonNull Class> nameToLibraryTypeMap2 = nameToLibraryTypeMap;
	//	nameToLibraryTypeMap2 = nameToLibraryTypeMap = new HashMap<>();
		for (org.eclipse.ocl.pivot.@NonNull Class asLocalClass : PivotUtil.getOwnedClasses(asPackage)) {
			String name = asLocalClass.getName();
	//		nameToLibraryTypeMap2.put(name, asLocalClass);
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

	private <T extends Element> @NonNull T localize(@NonNull T asReferencedElement) {
		if (!Orphanage.isOrphan(asReferencedElement)) {
		//	if (!(PivotUtil.basicGetContainingPackage(asReferencedElement) instanceof Orphanage)) {
		//	System.out.println("localize bypass for " + NameUtil.debugSimpleName(asReferencedElement) + " " + asReferencedElement);
			return asReferencedElement;
		}
		EObject localElement = remote2local.get(asReferencedElement);
		if (localElement != null) {
			return (T)localElement;
		}
		else if (asReferencedElement instanceof CollectionType) {
			CollectionType asCollectionType = (CollectionType)asReferencedElement;
			CollectionType genericCollectionType = PivotUtil.getGenericElement(asCollectionType);
			Type elementType = localize(PivotUtil.getElementType(asCollectionType));
			boolean isNullFree = asCollectionType.isIsNullFree();
			IntegerValue lowerValue = asCollectionType.getLowerValue();
			UnlimitedNaturalValue upperValue = asCollectionType.getUpperValue();
			CollectionType localCollectionType = getCollectionType(genericCollectionType, elementType, isNullFree, lowerValue, upperValue);
			resolveSuperClasses(localCollectionType, genericCollectionType);
			assert localCollectionType.eContainer() != null;
			putLocal(asCollectionType, localCollectionType);
			return (T)localCollectionType;
		}
		else if (asReferencedElement instanceof LambdaType) {
			LambdaType asLambdaType = (LambdaType)asReferencedElement;
			LambdaType genericLambdaType = PivotUtil.getGenericElement(asLambdaType);
			LambdaParameter contextParameter = PivotUtil.getOwnedContext(asLambdaType);
			Type localContextType = localize(PivotUtil.getType(contextParameter));
			TypedElement localContextParameter = LambdaTypeManager.createCandidateLambdaParameter(PivotUtil.getName(contextParameter), localContextType, contextParameter.isIsRequired());
			List<@NonNull LambdaParameter> ownedParameters = PivotUtil.getOwnedParametersList(asLambdaType);
			List<@NonNull TypedElement> localParameters = new ArrayList<>(ownedParameters.size());
			for (@NonNull LambdaParameter asParameter : ownedParameters) {
				Type parameterType = localize(PivotUtil.getType(asParameter));
				TypedElement localParameter = LambdaTypeManager.createCandidateLambdaParameter(PivotUtil.getName(asParameter), parameterType, asParameter.isIsRequired());
				localParameters.add(localParameter);
			}
			LambdaParameter resultParameter = PivotUtil.getOwnedResult(asLambdaType);
			Type localResultType = localize(PivotUtil.getType(resultParameter));
			TypedElement localResultParameter = LambdaTypeManager.createCandidateLambdaParameter(PivotUtil.getName(resultParameter), localResultType, resultParameter.isIsRequired());
			LambdaType localLambdaType = getLambdaType(localContextParameter, localParameters, localResultParameter, null);
			resolveSuperClasses(localLambdaType, genericLambdaType);
			assert localLambdaType.eContainer() != null;
			putLocal(asLambdaType, localLambdaType);
			return (T)localLambdaType;
		}
		else if (asReferencedElement instanceof MapType) {
			MapType asMapType = (MapType)asReferencedElement;
			MapType genericMapType = PivotUtil.getGenericElement(asMapType);
			Type keyType = localize(PivotUtil.getKeyType(asMapType));
			Type valueType = localize(PivotUtil.getValueType(asMapType));
			boolean keysAreNullFree = asMapType.isKeysAreNullFree();
			boolean valuesAreNullFree = asMapType.isValuesAreNullFree();
			MapType localMapType = getMapType(keyType, keysAreNullFree, valueType, valuesAreNullFree);
			resolveSuperClasses(localMapType, genericMapType);
			assert localMapType.eContainer() != null;
			putLocal(asMapType, localMapType);
			return (T)localMapType;
		}
		else if (asReferencedElement instanceof NormalizedTemplateParameter) {
			NormalizedTemplateParameter asNormalizedTemplateParameter = (NormalizedTemplateParameter)asReferencedElement;
			int index = asNormalizedTemplateParameter.getIndex();
			org.eclipse.ocl.pivot.@NonNull Package localOrphanage = getLocalOrphanage();
			NormalizedTemplateParameter localNormalizedTemplateParameter = Orphanage.getNormalizedTemplateParameter(localOrphanage, index);
			putLocal(asNormalizedTemplateParameter, localNormalizedTemplateParameter);
			return (T)localNormalizedTemplateParameter;
		}
		else if (asReferencedElement instanceof TupleType) {
			TupleType asTupleType = (TupleType)asReferencedElement;
			TupleType genericTupleType = PivotUtil.getGenericElement(asTupleType);
			List<@NonNull Property> localParts = new ArrayList<>();
			List<@NonNull PartId> partIds = new ArrayList<>();
			for (@NonNull Property asPart : PivotUtil.getOwnedProperties(asTupleType)) {
				Type partType = localize(PivotUtil.getType(asPart));
				Property localPart = PivotUtil.createProperty(PivotUtil.getName(asPart), partType);
				localPart.setIsRequired(asPart.isIsRequired());
				localParts.add(localPart);
				putLocal(asPart, localPart);
				partIds.add(IdManager.getPartId(asPart));
			}
			TupleType localTupleType = getTupleType(localParts, partIds);
			resolveSuperClasses(localTupleType, genericTupleType);
			assert localTupleType.eContainer() != null;
			putLocal(asTupleType, localTupleType);
			return (T)localTupleType;
		}
		else if (asReferencedElement instanceof WildcardType) {
			WildcardType asWildcardType = (WildcardType)asReferencedElement;
			org.eclipse.ocl.pivot.@NonNull Package localOrphanage = getLocalOrphanage();
			WildcardType localWildcardType = Orphanage.getOrphanWildcardType(localOrphanage);
			putLocal(asWildcardType, localWildcardType);
			return (T)localWildcardType;
		}
		else if (asReferencedElement instanceof org.eclipse.ocl.pivot.Class) {
			org.eclipse.ocl.pivot.Class asSpecializedClass = (org.eclipse.ocl.pivot.Class)asReferencedElement;
			org.eclipse.ocl.pivot.Class genericSpecializedClass = PivotUtil.getGenericElement(asSpecializedClass);
			List<@NonNull TemplateParameter> asTemplateParameters = genericSpecializedClass.basicGetOwnedTemplateParameters();
			if (asTemplateParameters != null) {
				TemplateSpecialization templateSpecialization = TemplateSpecialization.getTemplateSpecialization(asSpecializedClass);
				List<@NonNull Type> localTemplateArguments = new ArrayList<@NonNull Type>(asTemplateParameters.size());
				for (@NonNull TemplateParameter templateParameter : asTemplateParameters) {
					Type templateArgument = templateSpecialization.get(templateParameter);
					localTemplateArguments.add(localize(templateArgument));
				}
				org.eclipse.ocl.pivot.Class localSpecializedClass = getSpecializedType(genericSpecializedClass, localTemplateArguments);
				putLocal(asSpecializedClass, localSpecializedClass);
				for (@NonNull Property asProperty : PivotUtil.getOwnedProperties(asSpecializedClass)) {
					if (asProperty.isIsImplicit()) {
						assert asProperty.isIsImplicit();
						assert !asProperty.isIsComposite();
						assert !asProperty.isIsDerived();
					//	assert !asProperty.isIsResolveProxies();
						assert !asProperty.isIsTransient();
						assert !asProperty.isIsVolatile();
						Property asOpposite = asProperty.getOpposite();
						assert asOpposite != null;
						Type asType = localize(PivotUtil.getType(asProperty));
						Property localProperty = PivotUtil.createProperty(PivotUtil.getName(asProperty), asType);
						localProperty.setIsReadOnly(asProperty.isIsReadOnly());
						localProperty.setIsRequired(asProperty.isIsRequired());
						localProperty.setIsResolveProxies(asProperty.isIsResolveProxies());
						localProperty.setOpposite(asOpposite);
						localSpecializedClass.getOwnedProperties().add(localProperty);
						putLocal(asProperty, localProperty);
					}
				}
				return (T) localSpecializedClass;
			}
			else {
				return (T)asSpecializedClass;		// Only implicit properties
			}
		}
		else if (asReferencedElement instanceof TemplateableElement) {
			TemplateableElement asSpecializedElement = (TemplateableElement)asReferencedElement;
			throw new UnsupportedOperationException();
		}
		else if (asReferencedElement instanceof Property) {
			Property asProperty = (Property)asReferencedElement;
			EObject eContainer = asProperty.eContainer();
			Element localContainer = localize((Element)eContainer);
			if (eContainer instanceof TupleType) {				// A tuple part
				return (T) remote2local.get(asProperty);
			}
			else if (asProperty.isIsImplicit()) {
				return (T) asProperty;
			}
			else {
				assert asProperty.isIsImplicit();
				assert !asProperty.isIsComposite();
				assert !asProperty.isIsDerived();
			//	assert !asProperty.isIsResolveProxies();
				assert !asProperty.isIsTransient();
				assert !asProperty.isIsVolatile();
				Property asOpposite = asProperty.getOpposite();
				assert asOpposite != null;
				assert resource == asOpposite.eResource();
				return (T) remote2local.get(asProperty);
			}
		}
		throw new UnsupportedOperationException();
		//	putLocal(asReferencedElement, asReferencedElement);
		//	return asReferencedElement;
	}

	/**
	 * Prepare a pivot resource for save by creating local copies of all shared orphans.
	 * References to the shared orphans are not redirected to avoid mutation of prevailing resources.
	 * Rather BaseCSXMIResource.getHREF redirects when establishing the persisted reference.
	 *
	 * @since 7.0
	 */
	public void localizeOrphans() {
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
					if ((eTarget instanceof Property) && ((Property)eTarget).isIsImplicit()) {
						getClass();			// XXX
					}
					Element localETarget = localize((Element)eTarget);
					if (localETarget != eTarget) {
						if (moreEObjects == null) {
							moreEObjects = new ArrayList<>();
						}
						moreEObjects.add(localETarget);
					}
				}
			}
		}

		Map<EObject, Collection<Setting>> references = EcoreUtil.CrossReferencer.find(resource.getContents());
		for (EObject eTarget : references.keySet()) {
			if (eTarget instanceof Element) {
				if ((eTarget instanceof Property) && ((Property)eTarget).isIsImplicit()) {
					continue;
				}
				Resource eResource = eTarget.eResource();
				if ((eResource != resource) && Orphanage.isOrphan((Element)eTarget)) {
					EObject localTarget = remote2local.get(eTarget);
					assert localTarget instanceof Element;
					assert (localTarget.eResource() == resource) && Orphanage.isOrphan((Element) localTarget);
				}
			}
		}
		if (orphanClasses != null) {
			ECollections.sort((EList<org.eclipse.ocl.pivot.@NonNull Class>)orphanClasses, new ClassByTypeIdAndEntryClassComparator());
		}
	}

	protected void putLocal(@NonNull Element remoteElement, @NonNull Element localElement) {
		remote2local.put(remoteElement, localElement);
//		local2remote.put(localElement, remoteElement);
	}

	/**
	 * Return the localized variant of eObject. If eObject is an orphan, localizeSpecializations should have created
	 * a local copy that is returned here. Else returns eObject.
	 * @since 7.0
	 */
	public @Nullable EObject resolveOrphan(@NonNull EObject eObject) {
		EObject localEObject = remote2local.get(eObject);
		EObject eObject2 = localEObject != null ? localEObject : eObject;
	//	Model containingModel = PivotUtil.getContainingModel(eObject2);
	//	assert (containingModel == null) || !Orphanage.isOrphanage(containingModel);		// ElementLiteralExp references may be anywhere.
		return eObject2;
	}

	@Override
	public void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass) {
		super.resolveSuperClasses(specializedClass, unspecializedClass);
		List<org.eclipse.ocl.pivot.@NonNull Class> superClassesList = PivotUtil.getSuperClassesList(specializedClass);
		for (int i = 0; i < superClassesList.size(); i++) {
			org.eclipse.ocl.pivot.@NonNull Class superClass = superClassesList.get(i);
		//	if (Orphanage.isOrphan(superClass) && (superClass.eResource() != resource)) {
			org.eclipse.ocl.pivot.Class localSuperClass = localize(superClass);
			if (localSuperClass != superClass) {
				superClassesList.set(i, localSuperClass);
			}
		//	}
		}
	}
}