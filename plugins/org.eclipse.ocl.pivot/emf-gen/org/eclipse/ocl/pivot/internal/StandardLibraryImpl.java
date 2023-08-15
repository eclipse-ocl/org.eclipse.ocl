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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.JavaType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.TemplateBinding;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateParameterSubstitution;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.TemplateSpecialisation;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation;
import org.eclipse.ocl.pivot.types.TuplePart;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Standard Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class StandardLibraryImpl extends ElementImpl implements StandardLibrary
{
	/**
	 * The number of structural features of the '<em>Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STANDARD_LIBRARY_FEATURE_COUNT = ElementImpl.ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of operations of the '<em>Standard Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STANDARD_LIBRARY_OPERATION_COUNT = ElementImpl.ELEMENT_OPERATION_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected StandardLibraryImpl()
	{
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.STANDARD_LIBRARY;
	}

	private @Nullable Operation oclInvalidOperation = null;
	private @Nullable Property oclInvalidProperty = null;

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class basicGetLowerBound(@NonNull TemplateParameter asTemplateParameter) {
		org.eclipse.ocl.pivot.Class asLowerBound = null;
		for (org.eclipse.ocl.pivot.Class asClass : PivotUtil.getConstrainingClasses(asTemplateParameter)) {
			if (asLowerBound == null) {
				asLowerBound = asClass;
			}
			else {
				asLowerBound = (org.eclipse.ocl.pivot.Class)getCommonType(asLowerBound, TemplateParameterSubstitutions.EMPTY, asClass, TemplateParameterSubstitutions.EMPTY);
			}
		}
		return asLowerBound;
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class basicGetLowerBound(@NonNull WildcardType asWildcard) {
		org.eclipse.ocl.pivot.Class asLowerBound = basicGetLowerBound(PivotUtil.getTemplateParameter(asWildcard));
		for (org.eclipse.ocl.pivot.Class asClass : PivotUtil.getConstrainingClasses(asWildcard)) {
			if (asLowerBound == null) {
				asLowerBound = asClass;
			}
			else {
				asLowerBound = (org.eclipse.ocl.pivot.Class)getCommonType(asLowerBound, TemplateParameterSubstitutions.EMPTY, asClass, TemplateParameterSubstitutions.EMPTY);
			}
		}
		return asLowerBound;
	}

	@Override
	public @Nullable Operation basicGetOclInvalidOperation() {
		return oclInvalidOperation;
	}

	@Override
	public @Nullable Property basicGetOclInvalidProperty() {
		return oclInvalidProperty;
	}

	@Override
	public boolean conformsTo(@NonNull Type firstType, @NonNull Type secondType) {
		return conformsTo(firstType, TemplateParameterSubstitutions.EMPTY, secondType, TemplateParameterSubstitutions.EMPTY);
	}

	@Override
	public boolean conformsTo(@NonNull Type firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions) {
		//
		//	Resolve first template parameter to its substitution
		//
		TemplateParameter firstTemplateParameter = firstType.isTemplateParameter();
		if (firstTemplateParameter != null) {
			Type firstSubstitution = firstSubstitutions.get(firstTemplateParameter);
			if (firstSubstitution != null) {
				firstType = firstSubstitution;
			}
		}
		//
		//	Accrue solution to the second template parameter
		//
		TemplateParameter secondTemplateParameter = secondType.isTemplateParameter();
		if (secondTemplateParameter != null) {
			//			Type secondSubstitution = secondSubstitutions.get(secondTemplateParameter);
			//			if (secondSubstitution != null) {
			//				secondType = secondSubstitution;
			//			}
			/*secondType =*/ secondSubstitutions.put(secondTemplateParameter, firstType);
			return true;
		}
		if (firstType == secondType) {
			return true;
		}
		if (firstType instanceof InvalidType) {
			return true;
		}
		else if (secondType instanceof InvalidType) {
			return false;
		}
		else if (firstType instanceof VoidType) {
			return true;
		}
		else if (secondType instanceof VoidType) {
			return false;
		}
		else if (secondType instanceof AnyType) {
			return true;
		}
		else if (firstType instanceof AnyType) {
			return false;
		}
		//
		//	Normalize types to their behavioral class
		//
		if (isSameCompleteClass(firstType, secondType)) {
			return true;
		}
	//	firstType = firstCompleteClass.getPrimaryClass();
		secondType = resolveBehavioralType(secondType);
		//
		//	Use specialized conformance for constructed types, inheritance tree intersection for simple types
		//
		if (firstType == secondType) {
			return true;
		}
		else if ((firstType instanceof DataType) && (secondType instanceof DataType)) {
			if ((firstType instanceof CollectionType) && (secondType instanceof CollectionType)) {
				CollectionType firstClass2 = (CollectionType)firstType;
				return conformsToCollectionType(firstClass2, firstSubstitutions, (CollectionType)secondType, secondSubstitutions);
			}
			else if ((firstType instanceof MapType) && (secondType instanceof MapType)) {
				return conformsToMapType((MapType)firstType, firstSubstitutions, (MapType)secondType, secondSubstitutions);
			}
			else if ((firstType instanceof LambdaType) && (secondType instanceof LambdaType)) {
				return conformsToLambdaType((LambdaType)firstType, firstSubstitutions, (LambdaType)secondType, secondSubstitutions);
			}
			else if ((firstType instanceof TupleType) && (secondType instanceof TupleType)) {
				return conformsToTupleType((TupleType)firstType, firstSubstitutions, (TupleType)secondType, secondSubstitutions);
			}
		}
		else if ((firstType instanceof org.eclipse.ocl.pivot.Class) && (secondType instanceof org.eclipse.ocl.pivot.Class)) {
			org.eclipse.ocl.pivot.Class firstClass = (org.eclipse.ocl.pivot.Class)firstType;
			org.eclipse.ocl.pivot.Class secondClass = (org.eclipse.ocl.pivot.Class)secondType;
			return conformsToClassType(firstClass, firstSubstitutions, secondClass, secondSubstitutions);
		}
		FlatClass firstFlatClass2 = getFlatClass((org.eclipse.ocl.pivot.Class)firstType);			// XXX cast
		FlatClass secondFlatClass2 = getFlatClass((org.eclipse.ocl.pivot.Class)secondType);			// XXX cast
		return firstFlatClass2.isSubFlatClassOf(secondFlatClass2, false);
	}

	protected boolean conformsToClassType(org.eclipse.ocl.pivot.@NonNull Class firstClass, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			org.eclipse.ocl.pivot.@NonNull Class secondClass, @NonNull TemplateParameterSubstitutions secondSubstitutions) {
		org.eclipse.ocl.pivot.Class firstGeneric = (org.eclipse.ocl.pivot.Class)firstClass.getGeneric();
		org.eclipse.ocl.pivot.Class secondGeneric = (org.eclipse.ocl.pivot.Class)secondClass.getGeneric();
		if ((firstGeneric == null) || (secondGeneric == null)) {					// Any non-generic allows simple isSubFlatClassOf test
			FlatClass firstFlatClass = getFlatClass(firstClass);
			FlatClass secondFlatClass = getFlatClass(secondClass);
			return firstFlatClass.isSubFlatClassOf(secondFlatClass, false);
		}
		if (firstGeneric != secondGeneric) {										// Inconsistent generic requires generic isSubFlatClassOf test first
			FlatClass firstFlatClass = getFlatClass(firstGeneric);
			FlatClass secondFlatClass = getFlatClass(secondGeneric);
			if (!firstFlatClass.isSubFlatClassOf(secondFlatClass, true)) {
				return false;
			}
		}
		TemplateSignature firstTemplateSignature = firstGeneric.getOwnedSignature();
		TemplateSignature secondTemplateSignature = secondGeneric.getOwnedSignature();
		if (firstTemplateSignature == null) {
			assert secondTemplateSignature == null;
			return true;
		}
		assert secondTemplateSignature != null;
		List<TemplateParameter> firstTemplateParameters = firstTemplateSignature.getOwnedParameters();
		List<TemplateParameter> secondTemplateParameters = secondTemplateSignature.getOwnedParameters();
		int iSize = firstTemplateParameters.size();
		if (iSize != secondTemplateParameters.size()) {
			return false;
		}
		List<Type> firstTemplateArguments = new ArrayList<>();
		for (TemplateBinding templateBinding : firstClass.getOwnedBindings()) {
			for (TemplateParameterSubstitution templateParameterSubstitution : templateBinding.getOwnedSubstitutions()) {
				firstTemplateArguments.add(templateParameterSubstitution.getActual());
			}
		}
		List<Type> secondTemplateArguments = new ArrayList<>();
		for (TemplateBinding templateBinding : secondClass.getOwnedBindings()) {
			for (TemplateParameterSubstitution templateParameterSubstitution : templateBinding.getOwnedSubstitutions()) {
				secondTemplateArguments.add(templateParameterSubstitution.getActual());
			}
		}
		for (int i = 0; i < iSize; i++) {		// XXX validate bindings sizes
			Type firstType;
			if (firstClass.getOwnedSignature() != null) {
				firstType = firstTemplateSignature.getOwnedParameters().get(i);
			}
			else {
				firstType = firstTemplateArguments.get(i);
			}
			Type secondType;
			if (secondClass.getOwnedSignature() != null) {
				secondType = secondTemplateSignature.getOwnedParameters().get(i);
			}
			else {
				secondType = secondTemplateArguments.get(i);
			}
			if (firstType != secondType) {
				if ((firstType == null) || (secondType == null)) {
					return false;
				}
				if (!conformsTo(firstType, firstSubstitutions, secondType, secondSubstitutions)) {
					return false;
				}
			}
		}
		return true;
	}

	/*	@Override
	public boolean conformsToCollectionType(@NonNull DomainCollectionType firstCollectionType, @NonNull DomainCollectionType secondCollectionType) {
		CollectionType firstCollectionType2 = (CollectionType)firstCollectionType;
		CollectionType secondCollectionType2 = (CollectionType)secondCollectionType;
		TemplateParameterSubstitutions firstSubstitutions = TemplateParameterSubstitutionVisitor.createBindings(this, firstCollectionType2, secondCollectionType2);
		TemplateParameterSubstitutions secondSubstitutions = TemplateParameterSubstitutionVisitor.createBindings(this, secondCollectionType2, firstCollectionType2);
		return conformsToCollectionType(firstCollectionType2, firstSubstitutions, secondCollectionType2, secondSubstitutions);
	} */

	@Override
	public boolean conformsToCollectionType(@NonNull CollectionType firstCollectionType, @NonNull CollectionType secondCollectionType) {
		boolean conformsToCollectionType1 = conformsToCollectionType1(firstCollectionType, secondCollectionType);
	//	boolean conformsToCollectionType2 = conformsToCollectionType2(firstCollectionType, secondCollectionType);
	//	if (conformsToCollectionType1 != conformsToCollectionType2) {
	//		conformsToCollectionType1 = conformsToCollectionType1(firstCollectionType, secondCollectionType);
	//		conformsToCollectionType2 = conformsToCollectionType2(firstCollectionType, secondCollectionType);
	//	}
		return conformsToCollectionType1;
	}

	private boolean conformsToCollectionType1(@NonNull CollectionType firstCollectionType, @NonNull CollectionType secondCollectionType) {
		return conformsToCollectionType(firstCollectionType, TemplateParameterSubstitutions.EMPTY, secondCollectionType, TemplateParameterSubstitutions.EMPTY);
	}

/*	private boolean conformsToCollectionType2(@NonNull CollectionType firstCollectionType, @NonNull CollectionType secondCollectionType) {
		Type firstContainerType = firstCollectionType.getContainerType();
		Type secondContainerType = secondCollectionType.getContainerType();
		if (firstContainerType != secondContainerType) {
			FlatClass firstFlatClass = firstContainerType.getFlatClass(this);
			FlatClass secondFlatClass = secondContainerType.getFlatClass(this);
			if (!secondFlatClass.isSuperFlatClassOf(firstFlatClass, true)) {
				return false;
			}
		}
		Type firstElementType = firstCollectionType.getElementType();
		Type secondElementType = secondCollectionType.getElementType();
		if (firstElementType != secondElementType) {
			if ((firstElementType == null) || (secondElementType == null)) {
				return false;
			}
			if (!firstElementType.conformsTo(this, secondElementType)) {
				return false;
			}
		}
		IntegerValue firstLower = firstCollectionType.getLowerValue();
		IntegerValue secondLower = secondCollectionType.getLowerValue();
		if (firstLower.compareTo(secondLower) < 0) {
			return false;
		}
		UnlimitedNaturalValue firstUpper = firstCollectionType.getUpperValue();
		UnlimitedNaturalValue secondUpper = secondCollectionType.getUpperValue();
		if (firstUpper.compareTo(secondUpper) > 0) {
			return false;
		}
		return true;
	} */

	protected boolean conformsToCollectionType(@NonNull CollectionType firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull CollectionType secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions) {
		org.eclipse.ocl.pivot.Class firstContainerType = firstType.getContainerType();
		org.eclipse.ocl.pivot.Class secondContainerType = secondType.getContainerType();
		if (firstContainerType != secondContainerType) {
			FlatClass firstContainerFlatClass = getFlatClass(firstContainerType);
			FlatClass secondContainerFlatClass = getFlatClass(secondContainerType);
			if (!firstContainerFlatClass.isSubFlatClassOf(secondContainerFlatClass, true)) {
				return false;
			}
		}
		Type firstElementType = firstType.getElementType();
		Type secondElementType = secondType.getElementType();
		if ((firstElementType == null) || (secondElementType == null)) {
			return false;
		}
		IntegerValue firstLower = firstType.getLowerValue();
		IntegerValue secondLower = secondType.getLowerValue();
		if (firstLower.compareTo(secondLower) < 0) {
			return false;
		}
		UnlimitedNaturalValue firstUpper = firstType.getUpperValue();
		UnlimitedNaturalValue secondUpper = secondType.getUpperValue();
		if (firstUpper.compareTo(secondUpper) > 0) {
			return false;
		}
		return conformsTo(firstElementType, firstSubstitutions, secondElementType, secondSubstitutions);
	}

	@Override
	public boolean conformsToLambdaType(@NonNull LambdaType firstLambdaType, @NonNull LambdaType secondLambdaType) {
		throw new UnsupportedOperationException();
	}

	protected boolean conformsToLambdaType(@NonNull LambdaType actualType, @NonNull TemplateParameterSubstitutions actualSubstitutions,
			@NonNull LambdaType requiredType, @NonNull TemplateParameterSubstitutions requiredSubstitutions) {
		Type actualContextType = actualType.getContextType();
		Type requiredContextType = requiredType.getContextType();
		if ((actualContextType == null) || (requiredContextType == null)) {
			return false;
		}
		if (!conformsTo(actualContextType, actualSubstitutions, requiredContextType, requiredSubstitutions)) {
			return false;
		}
		Type actualResultType = actualType.getResultType();
		Type requiredResultType = requiredType.getResultType();
		if ((actualResultType == null) || (requiredResultType == null)) {
			return false;
		}
		if (!conformsTo(requiredResultType, requiredSubstitutions, actualResultType, actualSubstitutions)) {	// contravariant
			return false;
		}
		List<Type> actualParameterTypes = actualType.getParameterType();
		List<Type> requiredParameterTypes = requiredType.getParameterType();
		int iMax = actualParameterTypes.size();
		if (iMax != requiredParameterTypes.size()) {
			return false;
		}
		for (int i = 0; i < iMax; i++) {
			Type actualParameterType = actualParameterTypes.get(i);
			Type requiredParameterType = requiredParameterTypes.get(i);
			if ((actualParameterType == null) || (requiredParameterType == null)) {
				return false;
			}
			if (!conformsTo(actualParameterType, actualSubstitutions, requiredParameterType, requiredSubstitutions)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean conformsToMapType(@NonNull MapType firstMapType, @NonNull MapType secondMapType) {
		return conformsToMapType(firstMapType, TemplateParameterSubstitutions.EMPTY, secondMapType, TemplateParameterSubstitutions.EMPTY);
	}
/*		//		Type firstContainerType = firstMapType.getContainerType();
		//		Type secondContainerType = secondMapType.getContainerType();
		//		if (firstContainerType != secondContainerType) {
		//			CompleteInheritance firstInheritance = firstContainerType.getInheritance(standardLibrary);
		//			CompleteInheritance secondInheritance = secondContainerType.getInheritance(standardLibrary);
		//			if (!secondInheritance.isSuperInheritanceOf(firstInheritance)) {
		//				return false;
		//			}
		//		}
		Type firstKeyType = firstMapType.getKeyType();
		Type secondKeyType = secondMapType.getKeyType();
		if (firstKeyType != secondKeyType) {
			if ((firstKeyType == null) || (secondKeyType == null)) {
				return false;
			}
			if (!firstKeyType.conformsTo(this, secondKeyType)) {
				return false;
			}
		}
		Type firstValueType = firstMapType.getValueType();
		Type secondValueType = secondMapType.getValueType();
		if (firstValueType != secondValueType) {
			if ((firstValueType == null) || (secondValueType == null)) {
				return false;
			}
			if (!firstValueType.conformsTo(this, secondValueType)) {
				return false;
			}
		}
		return true;
	} */

	protected boolean conformsToMapType(@NonNull MapType firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull MapType secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions) {
		//		org.eclipse.ocl.pivot.Class firstContainerType = firstType.getContainerType();
		//		org.eclipse.ocl.pivot.Class secondContainerType = secondType.getContainerType();
		//		if (firstContainerType != secondContainerType) {
		//			CompleteClass firstContainerCompleteClass = getCompleteClass(firstContainerType);
		//			CompleteClass secondContainerCompleteClass = getCompleteClass(secondContainerType);
		//			CompleteInheritance firstContainerInheritance = firstContainerCompleteClass.getCompleteInheritance();
		//			CompleteInheritance secondContainerInheritance = secondContainerCompleteClass.getCompleteInheritance();
		//			if (!firstContainerInheritance.isSubInheritanceOf(secondContainerInheritance)) {
		//				return false;
		//			}
		//		}
		Type firstKeyType = firstType.getKeyType();
		Type secondKeyType = secondType.getKeyType();
		if ((firstKeyType == null) || (secondKeyType == null)) {
			return false;
		}
		if (!conformsTo(firstKeyType, firstSubstitutions, secondKeyType, secondSubstitutions)) {
			return false;
		}
		Type firstValueType = firstType.getValueType();
		Type secondValueType = secondType.getValueType();
		if ((firstValueType == null) || (secondValueType == null)) {
			return false;
		}
		return conformsTo(firstValueType, firstSubstitutions, secondValueType, secondSubstitutions);
	}

	@Override
	public boolean conformsToTupleType(@NonNull TupleType firstTupleType, @NonNull TupleType secondTupleType) {
		if (isEqualToTupleType(firstTupleType, secondTupleType)) {
			return true;
		}
		FlatClass firstFlatClass = firstTupleType.getFlatClass(this);
		FlatClass secondFlatClass = secondTupleType.getFlatClass(this);
		return firstFlatClass.isSuperFlatClassOf(secondFlatClass, false);
	}

	protected boolean conformsToTupleType(@NonNull TupleType actualType, @NonNull TemplateParameterSubstitutions actualSubstitutions,
			@NonNull TupleType requiredType, @NonNull TemplateParameterSubstitutions requiredSubstitutions) {
		List<Property> actualProperties = actualType.getOwnedProperties();
		List<Property> requiredProperties = requiredType.getOwnedProperties();
		if (actualProperties.size() != requiredProperties.size()) {
			return false;
		}
		for (Property actualProperty : actualProperties) {
			Property requiredProperty = NameUtil.getNameable(requiredProperties, actualProperty.getName());
			if (requiredProperty == null) {
				return false;
			}
			Type actualPropertyType = actualProperty.getType();
			Type requiredPropertyType = requiredProperty.getType();
			if ((actualPropertyType == null) || (requiredPropertyType == null)) {
				return false;
			}
			if (!conformsTo(actualPropertyType, actualSubstitutions, requiredPropertyType, requiredSubstitutions)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void dispose() {
		resetLibrary();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getBagType(@NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getBagType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @Nullable PrimitiveType getBehavioralClass(@NonNull Class<?> instanceClass) {
		return (PrimitiveType)PivotUtil.getBehavioralClass(this, instanceClass);
	}

	@Override
	public @NonNull CollectionType getCollectionType(boolean isOrdered, boolean isUnique) {
		return isOrdered ? isUnique ? getOrderedSetType() : getSequenceType() : isUnique ? getSetType() : getBagType();
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeId genericTypeId) {
		if (genericTypeId == TypeId.BAG) {
			return getBagType();
		}
		else if (genericTypeId == TypeId.ORDERED_SET) {
			return getOrderedSetType();
		}
		else if (genericTypeId  == TypeId.SEQUENCE) {
			return getSequenceType();
		}
		else if (genericTypeId == TypeId.SET) {
			return getSetType();
		}
		else if (genericTypeId == TypeId.ORDERED_COLLECTION) {
			return getOrderedCollectionType();
		}
		else if (genericTypeId == TypeId.UNIQUE_COLLECTION) {
			return getUniqueCollectionType();
		}
		else {
			return getCollectionType();
		}
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		assert genericType == PivotUtil.getUnspecializedTemplateableElement(genericType);
		if (genericType.eIsProxy() || elementType.eIsProxy()) {
			return getOclInvalidType();
		}
	//	if (isUnspecialized(genericType, elementType, isNullFree, lower, upper)) {		// XXX Fix Bug 582115++
	//		return genericType;
	//	}
		return getOrphanage().getCollectionType(genericType, elementType, isNullFree, lower, upper);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCommonCollectionType(@NonNull CollectionType leftCollectionType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull CollectionType rightCollectionType, @NonNull TemplateParameterSubstitutions rightSubstitutions) {
		FlatClass leftFlatClass = leftCollectionType.getFlatClass(this);
		FlatClass rightFlatClass = rightCollectionType.getFlatClass(this);
		FlatClass commonFlatClass = leftFlatClass.getCommonFlatClass(rightFlatClass, true);
		org.eclipse.ocl.pivot.Class commonCollectionType = getPrimaryClass(commonFlatClass.getASClass());
		Type leftElementType = ClassUtil.nonNullModel(leftCollectionType.getElementType());
		Type rightElementType = ClassUtil.nonNullModel(rightCollectionType.getElementType());
		Type commonElementType = getCommonType(leftElementType, leftSubstitutions, rightElementType, rightSubstitutions);
		boolean commonIsNullFree = leftCollectionType.isIsNullFree() && rightCollectionType.isIsNullFree();
		return getCollectionType((CollectionType) commonCollectionType, commonElementType, commonIsNullFree, null, null);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCommonMapType(@NonNull MapType leftMapType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull MapType rightMapType, @NonNull TemplateParameterSubstitutions rightSubstitutions) {
		Type leftKeyType = ClassUtil.nonNullModel(leftMapType.getKeyType());
		Type rightKeyType = ClassUtil.nonNullModel(rightMapType.getKeyType());
		Type commonKeyType = getCommonType(leftKeyType, leftSubstitutions, rightKeyType, rightSubstitutions);
		Type leftValueType = ClassUtil.nonNullModel(leftMapType.getValueType());
		Type rightValueType = ClassUtil.nonNullModel(rightMapType.getValueType());
		Type commonValueType = getCommonType(leftValueType, leftSubstitutions, rightValueType, rightSubstitutions);
		boolean commonIsKeysAreNullFree = leftMapType.isKeysAreNullFree() && rightMapType.isKeysAreNullFree();
		boolean commonIsValuesAreNullFree = leftMapType.isValuesAreNullFree() && rightMapType.isValuesAreNullFree();
		return getMapType(commonKeyType, commonIsKeysAreNullFree, commonValueType, commonIsValuesAreNullFree);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCommonTupleType(@NonNull TupleType leftType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull TupleType rightType, @NonNull TemplateParameterSubstitutions rightSubstitutions) {
		List<Property> leftProperties = leftType.getOwnedProperties();
		List<Property> rightProperties = rightType.getOwnedProperties();
		int iSize = leftProperties.size();
		if (iSize != rightProperties.size()) {
			return getOclAnyType();
		}
		List<@NonNull TuplePartId> commonPartIds = new ArrayList<>(iSize);
		for (int i = 0; i < iSize; i++) {
			Property leftProperty = leftProperties.get(i);
			if (leftProperty == null) {
				return getOclAnyType();				// Never happens
			}
			String name = leftProperty.getName();
			if (name == null) {
				return getOclAnyType();				// Never happens
			}
			Property rightProperty = NameUtil.getNameable(rightProperties, name);
			if (rightProperty == null) {
				return getOclAnyType();				// Happens for inconsistent tuples
			}
			Type leftPropertyType = leftProperty.getType();
			if (leftPropertyType == null) {
				return getOclAnyType();				// Never happens
			}
			Type rightPropertyType = rightProperty.getType();
			if (rightPropertyType == null) {
				return getOclAnyType();				// Never happens
			}
			Type commonType = getCommonType(leftPropertyType, leftSubstitutions, rightPropertyType, rightSubstitutions);
			TuplePartId commonPartId = IdManager.getTuplePartId(i, name, commonType.getTypeId());
			commonPartIds.add(commonPartId);
		}
		TupleTypeId commonTupleTypeId = IdManager.getTupleTypeId(TypeId.TUPLE_NAME, commonPartIds);
		return getTupleType(commonTupleTypeId);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCommonClass(org.eclipse.ocl.pivot.@NonNull Class leftClass, org.eclipse.ocl.pivot.@NonNull Class rightClass) {
		return (org.eclipse.ocl.pivot.Class)getCommonType(leftClass, TemplateParameterSubstitutions.EMPTY, rightClass, TemplateParameterSubstitutions.EMPTY);
	}

	@Override
	public @NonNull Type getCommonType(@NonNull Type leftType, @NonNull Type rightType) {
		return getCommonType(leftType, TemplateParameterSubstitutions.EMPTY, rightType, TemplateParameterSubstitutions.EMPTY);
	}

	@Override
	public @NonNull Type getCommonType(@NonNull Type leftType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, @NonNull TemplateParameterSubstitutions rightSubstitutions) {
		if ((leftType instanceof CollectionType) && (rightType instanceof CollectionType)) {
			return getCommonCollectionType((CollectionType)leftType, leftSubstitutions, (CollectionType)rightType, rightSubstitutions);
		}
		if ((leftType instanceof MapType) && (rightType instanceof MapType)) {
			return getCommonMapType((MapType)leftType, leftSubstitutions, (MapType)rightType, rightSubstitutions);
		}
		if ((leftType instanceof TupleType) && (rightType instanceof TupleType)) {
			return getCommonTupleType((TupleType)leftType, leftSubstitutions, (TupleType)rightType, rightSubstitutions);
		}
		if (conformsTo(leftType, leftSubstitutions, rightType, rightSubstitutions)) {
			return rightType;
		}
		if (conformsTo(rightType, rightSubstitutions, leftType, leftSubstitutions)) {
			return leftType;
		}
		FlatClass leftFlatClass = leftType.getFlatClass(this);
		FlatClass rightFlatClass = rightType.getFlatClass(this);
		FlatClass commonFlatClass = leftFlatClass.getCommonFlatClass(rightFlatClass, false);
		return getPrimaryClass(commonFlatClass.getASClass());
	}

	@Override
	public @NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return getFlatModel().getFlatClass(asClass);
	}

	@Override
	public @NonNull LambdaType getLambdaType(@NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType,
			@Nullable TemplateParameterSubstitutions bindings) {
		if (bindings == null) {
			return getOrphanage().getLambdaType(getOclLambdaType(), contextType, parameterTypes, resultType);
		}
		else {
			Type specializedContextType = getSpecializedType(contextType, bindings);
			List<@NonNull Type> specializedParameterTypes = new ArrayList<>();
			for (@NonNull Type parameterType : parameterTypes) {
				specializedParameterTypes.add(getSpecializedType(parameterType, bindings));
			}
			Type specializedResultType = getSpecializedType(resultType, bindings);
			return getOrphanage().getLambdaType(getOclLambdaType(), specializedContextType, specializedParameterTypes, specializedResultType);
		}
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getLowerBound(@NonNull TemplateParameter asTemplateParameter) {
		org.eclipse.ocl.pivot.Class asLowerBound = null;
		for (org.eclipse.ocl.pivot.Class asClass : PivotUtil.getConstrainingClasses(asTemplateParameter)) {
			if (asLowerBound == null) {
				asLowerBound = asClass;
			}
			else {
				asLowerBound = (org.eclipse.ocl.pivot.Class)getCommonType(asLowerBound, TemplateParameterSubstitutions.EMPTY, asClass, TemplateParameterSubstitutions.EMPTY);
			}
		}
		if (asLowerBound == null) {
			asLowerBound = getOclAnyType();
		}
		return asLowerBound;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getLowerBound(@NonNull WildcardType asWildcard) {
		org.eclipse.ocl.pivot.Class asLowerBound = basicGetLowerBound(PivotUtil.getTemplateParameter(asWildcard));
	//	org.eclipse.ocl.pivot.@NonNull Class asLowerBound = getLowerBound(PivotUtil.getTemplateParameter(asWildcard));
		for (org.eclipse.ocl.pivot.Class asClass : PivotUtil.getConstrainingClasses(asWildcard)) {
			if (asLowerBound == null) {
				asLowerBound = asClass;
			}
			else {
				asLowerBound = (org.eclipse.ocl.pivot.Class)getCommonType(asLowerBound, TemplateParameterSubstitutions.EMPTY, asClass, TemplateParameterSubstitutions.EMPTY);
			}
		}
		if (asLowerBound == null) {
			asLowerBound = getOclAnyType();
		}
		return asLowerBound;
	}

	@Override
	public @NonNull String getMetaclassName(@NonNull Type asInstanceType) {
		if (asInstanceType instanceof CollectionType) {
			if (asInstanceType instanceof BagType) {
				return TypeId.BAG_TYPE_NAME;
			}
			else if (asInstanceType instanceof OrderedSetType) {
				return TypeId.ORDERED_SET_TYPE_NAME;
			}
			else if (asInstanceType instanceof SequenceType) {
				return TypeId.SEQUENCE_TYPE_NAME;
			}
			else if (asInstanceType instanceof SetType) {
				return TypeId.SET_TYPE_NAME;
			}
			else {
				return TypeId.COLLECTION_TYPE_NAME;
			}
		}
		else if (asInstanceType instanceof AnyType) {
			return TypeId.ANY_TYPE_NAME;
		}
		else if (asInstanceType instanceof Enumeration) {
			return TypeId.ENUMERATION_NAME;
		}
		else if (asInstanceType instanceof InvalidType) {
			return TypeId.INVALID_TYPE_NAME;
		}
		else if (asInstanceType instanceof MapType) {
			return TypeId.MAP_TYPE_NAME;
		}
		else if (asInstanceType instanceof VoidType) {
			return TypeId.VOID_TYPE_NAME;
		}
		else if (asInstanceType instanceof BooleanType) {
			return TypeId.BOOLEAN_TYPE_NAME;
		}
		else if (asInstanceType instanceof PrimitiveType) {
			return TypeId.PRIMITIVE_TYPE_NAME;
		}
		else if (asInstanceType instanceof Stereotype) {
			return TypeId.STEREOTYPE_NAME;
		}
		else if (asInstanceType instanceof TupleType) {
			return TypeId.TUPLE_TYPE_NAME;
		}
		return TypeId.CLASS_NAME;		// fallback for e.g. TemplateParameter
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getMapType(@NonNull Type keyType, @Nullable Boolean keysAreNullFree, @NonNull Type valueType, @Nullable Boolean valuesAreNullFree) {
		if (keyType.eIsProxy() || valueType.eIsProxy()) {
			return getOclInvalidType();
		}
		MapType genericType = getMapType();
	//	if (isUnspecialized(keyType, keysAreNullFree, valueType, valuesAreNullFree)) {
	//		return genericType;
	//	}
		if (keysAreNullFree == null) {
			keysAreNullFree = PivotConstants.DEFAULT_MAP_KEYS_ARE_NULL_FREE;
		}
		if (valuesAreNullFree == null) {
			valuesAreNullFree = PivotConstants.DEFAULT_MAP_VALUES_ARE_NULL_FREE;
		}
		return getOrphanage().getMapType(genericType, keyType, keysAreNullFree, valueType, valuesAreNullFree);
	}

	@Override
	public @NonNull Type getMetaType(@NonNull Type instanceType) {
	//	if (instanceType instanceof PrimitiveType) {
	//		return getASClass(TypeId.PRIMITIVE_TYPE_NAME);
	//	}
		//		throw new UnsupportedOperationException();
		return getMetaclass(instanceType);
	}

	private @NonNull Type getNormalizedType(@NonNull Type type) {
		try {
			return type.getFlatClass(this).getASClass();
		}
		catch (Throwable e) {
			return type instanceof TemplateParameter ? getOclAnyType() : type;			// WIP FIXME should never happen
		}
	}

	@Override
	public @NonNull Operation getOclInvalidOperation() {
		Operation oclInvalidOperation2 = oclInvalidOperation;
		if (oclInvalidOperation2 == null) {
			org.eclipse.ocl.pivot.Class anyType = getOclAnyType();
			org.eclipse.ocl.pivot.Class invalidType = getOclInvalidType();
			List<Operation> invalidOperations = invalidType.getOwnedOperations();
			String invalidName = "oclBadOperation";
			oclInvalidOperation2 = NameUtil.getNameable(invalidOperations, invalidName);
			if (oclInvalidOperation2 == null) {
				oclInvalidOperation2 = PivotFactory.eINSTANCE.createOperation();
				oclInvalidOperation2.setName(invalidName);
				oclInvalidOperation2.setType(anyType);
				oclInvalidOperation2.setImplementation(OclAnyUnsupportedOperation.INSTANCE);
				invalidOperations.add(oclInvalidOperation2);
			}
			oclInvalidOperation = oclInvalidOperation2;
		}
		return oclInvalidOperation2;
	}

	@Override
	public @NonNull Property getOclInvalidProperty() {
		Property oclInvalidProperty2 = oclInvalidProperty;
		if (oclInvalidProperty2 == null) {
			org.eclipse.ocl.pivot.Class anyType = getOclAnyType();
			org.eclipse.ocl.pivot.Class invalidType = getOclInvalidType();
			List<Property> invalidProperties = invalidType.getOwnedProperties();
			String invalidName = "oclBadProperty";
			oclInvalidProperty2 = NameUtil.getNameable(invalidProperties, invalidName);
			if (oclInvalidProperty2 == null) {
				oclInvalidProperty2 = PivotFactory.eINSTANCE.createProperty();
				oclInvalidProperty2.setName(invalidName);
				oclInvalidProperty2.setType(anyType);
				oclInvalidProperty2.setImplementation(OclAnyUnsupportedOperation.INSTANCE);
				invalidProperties.add(oclInvalidProperty2);
			}
			oclInvalidProperty = oclInvalidProperty2;
		}
		return oclInvalidProperty2;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOrderedSetType(@NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getOrderedSetType(), elementType, isNullFree, lower, upper);
	}

	public org.eclipse.ocl.pivot.@NonNull Class getPrimaryClass(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return asClass;
	}

	public @NonNull Type getPrimaryType(@NonNull Type asType) {
		return asType;
	}

	@Override
	public @Nullable Type getPrimitiveType(@NonNull PrimitiveTypeId typeId) {
		if (typeId == TypeId.BOOLEAN) {
			return getBooleanType();
		}
		else if (typeId == TypeId.INTEGER) {
			return getIntegerType();
		}
		else if (typeId == TypeId.REAL) {
			return getRealType();
		}
		else if (typeId == TypeId.STRING) {
			return getStringType();
		}
		else if (typeId == TypeId.UNLIMITED_NATURAL) {
			return getUnlimitedNaturalType();
		}
		else if (typeId == TypeId.OCL_ANY) {
			return getOclAnyType();
		}
		else if (typeId == TypeId.OCL_COMPARABLE) {
			return getOclComparableType();
		}
		else if (typeId == TypeId.OCL_ENUMERATION) {
			return getOclEnumerationType();
		}
		else if (typeId == TypeId.OCL_SELF) {
			return getOclSelfType();
		}
		else if (typeId == TypeId.OCL_SUMMABLE) {
			return getOclSummableType();
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getSequenceType(@NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSequenceType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getSetType(@NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSetType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull Type getSpecializedType(@NonNull Type type, @Nullable TemplateParameterSubstitutions substitutions) {
		if ((substitutions == null) || substitutions.isEmpty()) {
			return type;
		}
		if ((type instanceof TemplateableElement) && (((TemplateableElement)type).getGeneric() != null)) {
			return type;
		}
		TemplateParameter asTemplateParameter = type.isTemplateParameter();
		if (asTemplateParameter != null) {
			Type boundType = substitutions.get(asTemplateParameter);
			org.eclipse.ocl.pivot.Class asClass = boundType != null ? boundType.isClass() : null;
			return asClass != null ? asClass : type;
		}
		else if (type instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)type;
			CollectionType unspecializedType = PivotUtil.getUnspecializedTemplateableElement(collectionType);
			if (!substitutions.isEmpty()) {
				TemplateParameter templateParameter = unspecializedType.getOwnedSignature().getOwnedParameters().get(0);
				Type templateArgument = substitutions.get(templateParameter);
				assert templateArgument != null;
				Object nullFreeObject = substitutions.getValue(templateParameter, PivotPackage.Literals.COLLECTION_TYPE__IS_NULL_FREE);
				Object lowerObject = substitutions.getValue(templateParameter, PivotPackage.Literals.COLLECTION_TYPE__LOWER);
				Object upperObject = substitutions.getValue(templateParameter, PivotPackage.Literals.COLLECTION_TYPE__UPPER);
				Boolean isNullFree = nullFreeObject instanceof Boolean ? (Boolean)nullFreeObject : null;
				IntegerValue lowerValue = lowerObject instanceof IntegerValue ? (IntegerValue)lowerObject : null;
				UnlimitedNaturalValue upperValue = upperObject instanceof UnlimitedNaturalValue ? (UnlimitedNaturalValue)upperObject : null;
				return getCollectionType(unspecializedType, templateArgument, isNullFree, lowerValue, upperValue);
			//	}
			}
			return collectionType;
		}
		else if (type instanceof MapType) {
			MapType mapType = (MapType)type;
			MapType unspecializedType = PivotUtil.getUnspecializedTemplateableElement(mapType);
			if (!substitutions.isEmpty()) {
				List<TemplateParameter> ownedParameters = unspecializedType.getOwnedSignature().getOwnedParameters();
				TemplateParameter keyTemplateParameter = ownedParameters.get(0);
				TemplateParameter valueTemplateParameter = ownedParameters.get(1);
				Type keyTemplateArgument = substitutions.get(keyTemplateParameter);
				Type valueTemplateArgument = substitutions.get(valueTemplateParameter);
				assert keyTemplateArgument != null;
				assert valueTemplateArgument != null;
				Object nullFreeKeysObject = substitutions.getValue(keyTemplateParameter, PivotPackage.Literals.MAP_TYPE__KEYS_ARE_NULL_FREE);
				Object nullFreeValuesObject = substitutions.getValue(valueTemplateParameter, PivotPackage.Literals.MAP_TYPE__VALUES_ARE_NULL_FREE);
				Boolean isKeysAreNullFree = nullFreeKeysObject instanceof Boolean ? (Boolean)nullFreeKeysObject : null;
				Boolean isValuesAreNullFree = nullFreeValuesObject instanceof Boolean ? (Boolean)nullFreeValuesObject : null;
				return getMapType(keyTemplateArgument, isKeysAreNullFree, valueTemplateArgument, isValuesAreNullFree);
			}
			return mapType;
		}
		else if (type instanceof TupleType) {
			return getTupleType((TupleType) type, substitutions);
		}
		else if (type instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)type;
			Type contextType = ClassUtil.nonNullModel(lambdaType.getContextType());
			@NonNull List<@NonNull Type> parameterTypes = PivotUtil.getParameterTypes(lambdaType);
			Type resultType = ClassUtil.nonNullModel(lambdaType.getResultType());
			return getLambdaType(contextType, parameterTypes, resultType, substitutions);
		}
		else if (type instanceof org.eclipse.ocl.pivot.Class) {
			//
			//	Get the bindings of the type.
			//
			org.eclipse.ocl.pivot.Class unspecializedType = PivotUtil.getUnspecializedTemplateableElement((org.eclipse.ocl.pivot.Class)type);
			//
			//	Prepare the template argument list, one template argument per template parameter.
			//
			TemplateSignature templateSignature = unspecializedType.getOwnedSignature();
			if (templateSignature != null) {
				List<@NonNull TemplateParameter> templateParameters = ClassUtil.nullFree(templateSignature.getOwnedParameters());
				List<@NonNull Type> templateArguments = new ArrayList<@NonNull Type>(templateParameters.size());
				for (@NonNull TemplateParameter templateParameter : templateParameters) {
					Type templateArgument = substitutions.get(templateParameter);
					templateArguments.add(templateArgument != null ? templateArgument : templateParameter);
				}
				return getLibraryType(unspecializedType, templateArguments);	// XXX nullFree
			}
		}
		return type;
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull IdResolver idResolver, @NonNull TupleTypeId tupleTypeId) {
		TupleType tupleType = getOrphanage().basicGetTupleType(tupleTypeId);
		if (tupleType == null) {
			@NonNull TuplePartId[] partIds = tupleTypeId.getPartIds();
			int partCount = partIds.length;
			@NonNull TuplePart[] tupleParts = new @NonNull TuplePart[partCount];
			for (int i = 0; i < partCount;i++) {
				@NonNull TuplePartId partId = partIds[i];
				Type partType = idResolver.getType(partId.getTypeId());
				TuplePart part = new TuplePart.TuplePartImpl(NameUtil.getSafeName(partId), partType);
				tupleParts[i] = part;
			}
			tupleType = getOrphanage().getTupleType(getOclTupleType(), tupleParts);
		}
		return tupleType;
	}

	protected abstract @NonNull TupleType getTupleType(@NonNull TupleTypeId commonTupleTypeId);		// XXX promote

	@Override
	public @NonNull WildcardType getWildcardType(@NonNull TemplateParameter templateParameter) {
		return getOrphanage().getWildcardType(templateParameter);
	}

	@Override
	public boolean isEqualTo(@NonNull Type firstType, @NonNull Type secondType) {
		if ((firstType instanceof CollectionType) && (secondType instanceof CollectionType)) {
			return isEqualToCollectionType((CollectionType)firstType, (CollectionType)secondType);
		}
		if ((firstType instanceof JavaType) && (secondType instanceof JavaType)) {
			return firstType == secondType;
		}
		if ((firstType instanceof MapType) && (secondType instanceof MapType)) {
			return isEqualToMapType((MapType)firstType, (MapType)secondType);
		}
	//	if ((firstType instanceof LambdaType) && (secondType instanceof LambdaType)) {
	//		return isEqualToLambdaType((LambdaType)firstType, (LambdaType)secondType);
	//	}
		if ((firstType instanceof TupleType) && (secondType instanceof TupleType)) {
			return isEqualToTupleType((TupleType)firstType, (TupleType)secondType);
		}
		if (firstType == secondType) {
			return true;
		}
		firstType = getNormalizedType(firstType);
		secondType = getNormalizedType(secondType);
		return firstType == secondType;
	}

	@Override
	public boolean isEqualToCollectionType(@NonNull CollectionType firstCollectionType, @NonNull CollectionType secondCollectionType) {
		Type firstContainerType = firstCollectionType.getContainerType();
		Type secondContainerType = secondCollectionType.getContainerType();
		if (firstContainerType != secondContainerType) { //&& !isEqualToUnspecializedType(firstContainerType, secondContainerType)) {
			return false;
		}
		Type firstElementType = firstCollectionType.getElementType();
		Type secondElementType = secondCollectionType.getElementType();
		if (firstElementType != secondElementType) {
			if ((firstElementType == null) || (secondElementType == null)) {
				return false;
			}
			if (!isEqualTo(firstElementType, secondElementType)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEqualToMapType(@NonNull MapType firstMapType, @NonNull MapType secondMapType) {
		//		Type firstContainerType = firstMapType.getContainerType();
		//		Type secondContainerType = secondMapType.getContainerType();
		//		if ((firstContainerType != secondContainerType) && !firstContainerType.isEqualToUnspecializedType(standardLibrary, secondContainerType)) {
		//			return false;
		//		}
		Type firstKeyType = firstMapType.getKeyType();
		Type secondKeyType = secondMapType.getKeyType();
		if (firstKeyType != secondKeyType) {
			if ((firstKeyType == null) || (secondKeyType == null)) {
				return false;
			}
			if (!isEqualTo(firstKeyType, secondKeyType)) {
				return false;
			}
		}
		Type firstValueType = firstMapType.getValueType();
		Type secondValueType = secondMapType.getValueType();
		if (firstValueType != secondValueType) {
			if ((firstValueType == null) || (secondValueType == null)) {
				return false;
			}
			if (!isEqualTo(firstValueType, secondValueType)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean isEqualToTupleType(@NonNull TupleType firstTupleType, @NonNull TupleType secondTupleType) {
		TypeId firstParts = firstTupleType.getTypeId();
		TypeId secondParts = secondTupleType.getTypeId();
		return firstParts == secondParts;
	}

	protected abstract boolean isSameCompleteClass(@NonNull Type firstType, @NonNull Type secondType);

	@Override
	public boolean isTypeServeable(@NonNull Type type) {
		//		if (pivotType .getUnspecializedElement() != null) {
		//			return false;
		//		}
		if (type.isTemplateParameter() != null) {
			return false;
		}
		//		if (pivotType instanceof UnspecifiedType) {
		//			return false;
		//		}
		if (type instanceof LambdaType) {
			return false;
		}
		//		if (pivotType instanceof TupleType) {
		//			return false;
		//		}
		if (type.eContainer() instanceof TemplateParameterSubstitution) {
			return false;
		}
		return true;
	}

	protected abstract boolean isUnspecialized(@NonNull CollectionType genericType, @NonNull Type elementType,
			@Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	protected abstract boolean isUnspecialized(@NonNull Type keyType, @Nullable Boolean keysAreNullFree, @NonNull Type valueType, @Nullable Boolean valuesAreNullFree);

	public void resetLibrary() {
		oclInvalidOperation = null;
		oclInvalidProperty = null;

	}

	protected abstract @NonNull Type resolveBehavioralType(@NonNull Type asType);

	/**
	 * Return the specialization of asType suitable for use as the type of self in a feature. A generic class
	 * is specialized by its own template parameters.
	 */
	@Override
	public @NonNull Type resolveContextSpecialization(@NonNull Type asType) {
		if (!TemplateSpecialisation.needsSpecialisation(asType)) {
			return asType;
		}		// XXX transitive
		assert (asType instanceof TemplateableElement) && (((TemplateableElement)asType).getGeneric() == null);
		TemplateableElement unspecializedType = PivotUtil.getUnspecializedTemplateableElement((TemplateableElement)asType);
		TemplateSignature asTemplateSignature = unspecializedType.getOwnedSignature();
		assert asTemplateSignature != null;
		List<@NonNull TemplateParameter> asTemplateParameters = PivotUtilInternal.getOwnedParametersList(asTemplateSignature);
		if (asType instanceof CollectionType) {
			TemplateParameter asTemplateParameter = asTemplateParameters.get(0);
			return getCollectionType((CollectionType)unspecializedType, asTemplateParameter, null, null, null);
		}
		else if (asType instanceof MapType) {
			TemplateParameter keyTemplateParameter = asTemplateParameters.get(0);
			TemplateParameter valueTemplateParameter = asTemplateParameters.get(1);
			return getMapType(keyTemplateParameter, null, valueTemplateParameter, null);
		}
		else if (asType instanceof org.eclipse.ocl.pivot.Class) {
			return getLibraryType((org.eclipse.ocl.pivot.Class)unspecializedType, asTemplateParameters);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * Return the specialization of the under-specified generic asType suitable for type of Variable. Missing (all) parameters
	 * are filled in with distinct WildCardTypes.
	 */
	@Override
	public @NonNull Type resolveIncompleteSpecialization(@NonNull Type asType) {
		if (!TemplateSpecialisation.needsSpecialisation(asType) || (asType instanceof LambdaType) || (asType instanceof TupleType)) {
			return asType;
		}
		assert (asType instanceof TemplateableElement) && (((TemplateableElement)asType).getGeneric() == null);
		TemplateableElement genericType = PivotUtil.getUnspecializedTemplateableElement((TemplateableElement)asType);
		TemplateSignature asTemplateSignature = genericType.getOwnedSignature();
		assert asTemplateSignature != null;
		List<@NonNull TemplateParameter> asTemplateParameters = PivotUtilInternal.getOwnedParametersList(asTemplateSignature);
		if (asType instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)asType;
			org.eclipse.ocl.pivot.Class asWildcard = getWildcardType(asTemplateParameters.get(0));
			return getCollectionType((CollectionType)genericType, asWildcard, collectionType.isIsNullFree(), collectionType.getLowerValue(), collectionType.getUpperValue());
		}
		else if (asType instanceof MapType) {
			MapType mapType = (MapType)asType;
			org.eclipse.ocl.pivot.Class keyType = getWildcardType(asTemplateParameters.get(0));
			org.eclipse.ocl.pivot.Class valueType = getWildcardType(asTemplateParameters.get(1));
			return getMapType(keyType, mapType.isKeysAreNullFree(), valueType, mapType.isValuesAreNullFree());
		}
		else if (asType instanceof org.eclipse.ocl.pivot.Class) {
			org.eclipse.ocl.pivot.Class classType = (org.eclipse.ocl.pivot.Class)asType;
			List<org.eclipse.ocl.pivot.@NonNull Class> wildcards = new ArrayList<>();		// XX use defined templagteArguments
			for (TemplateParameter asTemplateParameter : asTemplateParameters) {
				wildcards.add(getWildcardType(asTemplateParameter));
			}
			return getLibraryType((org.eclipse.ocl.pivot.Class)genericType, wildcards);
		}
		else {
			throw new UnsupportedOperationException();
		}
	/*	if (asType instanceof CollectionType) {
			TemplateParameter asTemplateParameter = asTemplateParameters.get(0);
			return getCollectionType((CollectionType)unspecializedType, asTemplateParameter, null, null, null);
		}
		else if (asType instanceof MapType) {
			TemplateParameter keyTemplateParameter = asTemplateParameters.get(0);
			TemplateParameter valueTemplateParameter = asTemplateParameters.get(1);
			return getMapType(keyTemplateParameter, null, valueTemplateParameter, null);
		}
		else if (asType instanceof org.eclipse.ocl.pivot.Class) {
			return getLibraryType((org.eclipse.ocl.pivot.Class)unspecializedType, asTemplateParameters);
		}
		else { */
	//		throw new UnsupportedOperationException();
	//	}
	}

	/**
	 * Return the specialization of asType suitable for use as a type literal. A generic class
	 * is specialized by its lower bound.
	 */
	@Override
	public @NonNull Type resolveLowerBoundSpecialization(@NonNull Type asType) {
		assert TemplateSpecialisation.needsCompletion(asType);
		assert (asType instanceof TemplateableElement) && (((TemplateableElement)asType).getGeneric() == null);
		TemplateableElement genericType = PivotUtil.getUnspecializedTemplateableElement((TemplateableElement)asType);
		TemplateSignature asTemplateSignature = genericType.getOwnedSignature();
		assert asTemplateSignature != null;
		List<@NonNull TemplateParameter> asTemplateParameters = PivotUtilInternal.getOwnedParametersList(asTemplateSignature);
		if (asType instanceof CollectionType) {
			CollectionType collectionType = (CollectionType)asType;
			org.eclipse.ocl.pivot.Class asLowerBound = getLowerBound(asTemplateParameters.get(0));
			return getCollectionType((CollectionType)genericType, asLowerBound, collectionType.isIsNullFree(), collectionType.getLowerValue(), collectionType.getUpperValue());
		}
		else if (asType instanceof MapType) {
			MapType mapType = (MapType)asType;
			org.eclipse.ocl.pivot.Class keyType = getLowerBound(asTemplateParameters.get(0));
			org.eclipse.ocl.pivot.Class valueType = getLowerBound(asTemplateParameters.get(1));
			return getMapType(keyType, mapType.isKeysAreNullFree(), valueType, mapType.isValuesAreNullFree());
		}
		else if (asType instanceof org.eclipse.ocl.pivot.Class) {
			org.eclipse.ocl.pivot.Class classType = (org.eclipse.ocl.pivot.Class)asType;
			List<org.eclipse.ocl.pivot.@NonNull Class> lowerBounds = new ArrayList<>();		// XX use defined templagteArguments
			for (TemplateParameter asTemplateParameter : asTemplateParameters) {
				lowerBounds.add(getLowerBound(asTemplateParameter));
			}
			return getLibraryType((org.eclipse.ocl.pivot.Class)genericType, lowerBounds);
		}
		else {
			throw new UnsupportedOperationException();
		}
	}
} //AbstractStandardLibraryImpl
