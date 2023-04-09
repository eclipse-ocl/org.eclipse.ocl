/**
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.ReflectiveStandardLibrary;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.utilities.IllegalLibraryException;
import org.eclipse.osgi.util.NLS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class ReflectiveStandardLibraryImpl extends StandardLibraryImpl implements ReflectiveStandardLibrary
{
	/**
	 * The number of structural features of the '<em>Reflective Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REFLECTIVE_STANDARD_LIBRARY_FEATURE_COUNT = StandardLibraryImpl.STANDARD_LIBRARY_FEATURE_COUNT + 0;
	/**
	 * The number of operations of the '<em>Reflective Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REFLECTIVE_STANDARD_LIBRARY_OPERATION_COUNT = StandardLibraryImpl.STANDARD_LIBRARY_OPERATION_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReflectiveStandardLibraryImpl()
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
		return PivotPackage.Literals.REFLECTIVE_STANDARD_LIBRARY;
	}

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

	@Override
	public @Nullable AnyType basicGetOclAnyType() {
		return oclAnyType;
	}

	@Override
	public @Nullable InvalidType basicGetOclInvalidType() {
		return oclInvalidType;
	}

	@Override
	public @NonNull BagType getBagType() {
		BagType bagType2 = bagType;
		if (bagType2 == null) {
			bagType2 = bagType = resolveRequiredTemplateableType(BagType.class, TypeId.BAG_NAME, 1);
		}
		return bagType2;
	}

	@Override
	public @NonNull BooleanType getBooleanType() {
		BooleanType booleanType2 = booleanType;
		if (booleanType2 == null) {
			booleanType2 = booleanType = resolveRequiredSimpleType(BooleanType.class, TypeId.BOOLEAN_NAME);
		}
		return booleanType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getClassType() {
		org.eclipse.ocl.pivot.Class classType2 = classType;
		if (classType2 == null) {
			classType2 = classType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.CLASS_NAME);
		}
		return classType2;
	}

	@Override
	public @NonNull CollectionType getCollectionType() {
		CollectionType collectionType2 = collectionType;
		if (collectionType2 == null) {
			collectionType2 = collectionType = resolveRequiredTemplateableType(CollectionType.class, TypeId.COLLECTION_NAME, 1);
		}
		return collectionType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEnumerationType() {
		org.eclipse.ocl.pivot.Class enumerationType2 = enumerationType;
		if (enumerationType2 == null) {
			enumerationType2 = enumerationType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.ENUMERATION_NAME);
		}
		return enumerationType2;
	}

	@Override
	public @NonNull PrimitiveType getIntegerType() {
		PrimitiveType integerType2 = integerType;
		if (integerType2 == null) {
			integerType2 = integerType = resolveRequiredSimpleType(PrimitiveType.class, TypeId.INTEGER_NAME);
		}
		return integerType2;
	}

	@Override
	public abstract org.eclipse.ocl.pivot.Class getLibraryType(@NonNull String typeName);
	@Override
	public @NonNull MapType getMapType() {
		MapType mapType2 = mapType;
		if (mapType2 == null) {
			mapType2 = mapType = resolveRequiredTemplateableType(MapType.class, TypeId.MAP_NAME, 2);
		}
		return mapType2;
	}

	@Override
	public @NonNull AnyType getOclAnyType() {
		AnyType oclAnyType2 = oclAnyType;
		if (oclAnyType2 == null) {
			oclAnyType2 = oclAnyType = resolveRequiredSimpleType(AnyType.class, TypeId.OCL_ANY_NAME);
		}
		return oclAnyType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclComparableType() {
		org.eclipse.ocl.pivot.Class oclComparableType2 = oclComparableType;
		if (oclComparableType2 == null) {
			oclComparableType2 = oclComparableType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_COMPARABLE_NAME);
		}
		return oclComparableType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclElementType() {
		org.eclipse.ocl.pivot.Class oclElementType2 = oclElementType;
		if (oclElementType2 == null) {
			oclElementType2 = oclElementType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_ELEMENT_NAME);
		}
		return oclElementType2;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclEnumerationType() {
		org.eclipse.ocl.pivot.Class oclEnumerationType2 = oclEnumerationType;
		if (oclEnumerationType2 == null) {
			oclEnumerationType2 = oclEnumerationType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_ENUMERATION_NAME);
		}
		return oclEnumerationType2;
	}

	@Override
	public @NonNull InvalidType getOclInvalidType() {
		InvalidType oclInvalidType2 = oclInvalidType;
		if (oclInvalidType2 == null) {
			oclInvalidType2 = oclInvalidType = resolveRequiredSimpleType(InvalidType.class, TypeId.OCL_INVALID_NAME);
		}
		return oclInvalidType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclLambdaType() {
		org.eclipse.ocl.pivot.Class oclLambdaType2 = oclLambdaType;
		if (oclLambdaType2 == null) {
			oclLambdaType2 = oclLambdaType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_LAMBDA_NAME);
		}
		return oclLambdaType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclMessageType() {
		org.eclipse.ocl.pivot.Class oclMessageType2 = oclMessageType;
		if (oclMessageType2 == null) {
			oclMessageType2 = oclMessageType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_MESSAGE_NAME);
		}
		return oclMessageType2;
	}

	@Override
	public @NonNull SelfType getOclSelfType() {
		SelfType oclSelfType2 = oclSelfType;
		if (oclSelfType2 == null) {
			oclSelfType2 = oclSelfType = resolveRequiredSimpleType(SelfType.class, TypeId.OCL_SELF_NAME);
		}
		return oclSelfType2;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclStereotypeType() {
		org.eclipse.ocl.pivot.Class oclStereotypeType2 = oclStereotypeType;
		if (oclStereotypeType2 == null) {
			oclStereotypeType2 = oclStereotypeType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_STEREOTYPE_NAME);
		}
		return oclStereotypeType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSummableType() {
		org.eclipse.ocl.pivot.Class oclSummableType2 = oclSummableType;
		if (oclSummableType2 == null) {
			oclSummableType2 = oclSummableType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_SUMMABLE_NAME);
		}
		return oclSummableType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTupleType() {
		org.eclipse.ocl.pivot.Class oclTupleType2 = oclTupleType;
		if (oclTupleType2 == null) {
			oclTupleType2 = oclTupleType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_TUPLE_NAME);
		}
		return oclTupleType2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTypeType() {
		org.eclipse.ocl.pivot.Class oclTypeType2 = oclTypeType;
		if (oclTypeType2 == null) {
			oclTypeType2 = oclTypeType = resolveRequiredSimpleType(org.eclipse.ocl.pivot.Class.class, TypeId.OCL_TYPE_NAME);
		}
		return oclTypeType2;
	}

	@Override
	public @NonNull VoidType getOclVoidType() {
		VoidType oclVoidType2 = oclVoidType;
		if (oclVoidType2 == null) {
			oclVoidType2 = oclVoidType = resolveRequiredSimpleType(VoidType.class, TypeId.OCL_VOID_NAME);
		}
		return oclVoidType2;
	}

	@Override
	public @NonNull CollectionType getOrderedCollectionType() {
		CollectionType orderedCollectionType2 = orderedCollectionType;
		if (orderedCollectionType2 == null) {
			orderedCollectionType2 = orderedCollectionType = resolveRequiredTemplateableType(CollectionType.class, TypeId.ORDERED_COLLECTION_NAME, 1);
		}
		return orderedCollectionType2;
	}

	@Override
	public @NonNull OrderedSetType getOrderedSetType() {
		OrderedSetType orderedSetType2 = orderedSetType;
		if (orderedSetType2 == null) {
			orderedSetType2 = orderedSetType = resolveRequiredTemplateableType(OrderedSetType.class, TypeId.ORDERED_SET_NAME, 1);
		}
		return orderedSetType2;
	}

	@Override
	public @NonNull PrimitiveType getRealType() {
		PrimitiveType realType2 = realType;
		if (realType2 == null) {
			realType2 = realType = resolveRequiredSimpleType(PrimitiveType.class, TypeId.REAL_NAME);
		}
		return realType2;
	}

	public org.eclipse.ocl.pivot.@NonNull Class getRequiredLibraryType(@NonNull String typeName) {
		org.eclipse.ocl.pivot.Class type = getLibraryType(typeName);
		if (type == null) {
			throw new IllegalLibraryException(NLS.bind(PivotMessagesInternal.MissingLibraryType_ERROR_, typeName));
		}
		return type;
	}

	@Override
	public @NonNull SequenceType getSequenceType() {
		SequenceType sequenceType2 = sequenceType;
		if (sequenceType2 == null) {
			sequenceType2 = sequenceType = resolveRequiredTemplateableType(SequenceType.class, TypeId.SEQUENCE_NAME, 1);
		}
		return sequenceType2;
	}

	@Override
	public @NonNull SetType getSetType() {
		SetType setType2 = setType;
		if (setType2 == null) {
			setType2 = setType = resolveRequiredTemplateableType(SetType.class, TypeId.SET_NAME, 1);
		}
		return setType2;
	}

	@Override
	public @NonNull PrimitiveType getStringType() {
		PrimitiveType stringType2 = stringType;
		if (stringType2 == null) {
			stringType2 = stringType = resolveRequiredSimpleType(PrimitiveType.class, TypeId.STRING_NAME);
		}
		return stringType2;
	}

	@Override
	public @NonNull CollectionType getUniqueCollectionType() {
		CollectionType uniqueCollectionType2 = uniqueCollectionType;
		if (uniqueCollectionType2 == null) {
			uniqueCollectionType2 = uniqueCollectionType = resolveRequiredTemplateableType(CollectionType.class, TypeId.UNIQUE_COLLECTION_NAME, 1);
		}
		return uniqueCollectionType2;
	}

	@Override
	public @NonNull PrimitiveType getUnlimitedNaturalType() {
		PrimitiveType unlimitedNaturalType2 = unlimitedNaturalType;
		if (unlimitedNaturalType2 == null) {
			unlimitedNaturalType2 = unlimitedNaturalType = resolveRequiredSimpleType(PrimitiveType.class, TypeId.UNLIMITED_NATURAL_NAME);
		}
		return unlimitedNaturalType2;
	}

	@Override
	public void resetLibrary() {
		super.resetLibrary();
		bagType = null;
		booleanType = null;
		classType = null;
		collectionType = null;
		enumerationType = null;
		integerType = null;
		mapType = null;
		oclAnyType = null;
		oclComparableType = null;
		oclElementType = null;
		oclEnumerationType = null;
		oclInvalidType = null;
		oclLambdaType = null;
		oclMessageType = null;
		oclSelfType = null;
		oclSummableType = null;
		oclTupleType = null;
		oclTypeType = null;
		oclVoidType = null;
		orderedCollectionType = null;
		orderedSetType = null;
		realType = null;
		sequenceType = null;
		setType = null;
		stringType = null;
		uniqueCollectionType = null;
		unlimitedNaturalType = null;
	}

	protected @NonNull <T extends TemplateableElement> T resolveRequiredSimpleType(@NonNull Class<T> requiredClassType, @NonNull String name) {
		org.eclipse.ocl.pivot.Class type = getRequiredLibraryType(name);
		if (requiredClassType.isAssignableFrom(type.getClass())) {
			@SuppressWarnings("unchecked")
			T type2 = (T) type;
			return type2;
		}
		else {
			throw new IllegalLibraryException(name + " is not a " + requiredClassType.getSimpleName());
		}
	}

	protected @NonNull <T extends TemplateableElement> T resolveRequiredTemplateableType(@NonNull Class<T> requiredClassType, @NonNull String name, int parameterCount) {
		org.eclipse.ocl.pivot.Class type = getRequiredLibraryType(name);
		if (requiredClassType.isAssignableFrom(type.getClass())) {
			if (type.getOwnedSignature() == null) {
				throw new IllegalLibraryException(name + " is not a templated type");
			}
			else if (type.getOwnedSignature().getOwnedParameters().size() != parameterCount) {
				throw new IllegalLibraryException(name + " is not a templated type with " + parameterCount + " argument" + (parameterCount != 1 ? "s" : ""));
			}
			@SuppressWarnings("unchecked")
			T type2 = (T) type;
			return type2;
		}
		else {
			throw new IllegalLibraryException(name + " is not a " + requiredClassType.getSimpleName());
		}
	}
}