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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.DataType;
import org.eclipse.ocl.pivot.LambdaParameter;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.MapTypeManager;
import org.eclipse.ocl.pivot.internal.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Standard Library</b></em>'.
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
	 * The URI used by default for the OCL Standard Library. NB. This
	 * constant is repeated in GenerateOCLstdlibModel.mwe2 and in
	 * org.eclipse.ocl.pivot/plugin.xml.
	 */
	public static final @NonNull String DEFAULT_OCL_STDLIB_URI = LibraryConstants.STDLIB_URI;

	/**
	 * The URI to provide the default Standard Library. This value may be
	 * reassigned pior to any OCL analysis or evaluation to select a different
	 * default. Alternatively the need for default may be bypassed by explicitly
	 * invoking loadLibrary().
	 */
	protected @NonNull String defaultStandardLibraryURI = DEFAULT_OCL_STDLIB_URI;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StandardLibraryImpl()
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
		return PivotPackage.Literals.STANDARD_LIBRARY;
	}

	/**
	 * Shared cache of the lazily created lazily deleted specializations of each collection type.
	 */
	private /*@LazyNonNull*/ CollectionTypeManager collectionTypeManager = null;

	/**
	 * Shared cache of the lazily created lazily deleted specializations of each map type.
	 */
	private /*@LazyNonNull*/ MapTypeManager mapTypeManager = null;

	/**
	 * Shared cache of the lazily created lazily deleted specializations of each tuple type.
	 */
	private /*@LazyNonNull*/ TupleTypeManager tupleTypeManager = null;

	/**
	 * @since 7.0
	 */
	protected abstract @Nullable Type basicGetBehavioralType(@NonNull Type type);

	@Override
	public @Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		return getCollectionTypeManager().basicGetCollectionType(typeArguments);
	}

	@Override
	public boolean conformsTo(@NonNull Type firstType, boolean firstIsRequired, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, boolean secondIsRequired, @NonNull TemplateParameterSubstitutions secondSubstitutions) {
		if (!firstIsRequired && secondIsRequired) {
			return false;
		}
		return conformsTo(firstType, firstSubstitutions, secondType, secondSubstitutions, true);
	}

	@Override
	public boolean conformsTo(@NonNull Type firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions) {
		return conformsTo(firstType, firstSubstitutions, secondType, secondSubstitutions, false);
	}

	/**
	 * @since 7.0
	 */
	protected boolean conformsTo(@NonNull Type firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions, boolean enforceNullity) {
		if (firstType == secondType) {
			return true;
		}
		//
		//	Resolve first template parameters to its substitution
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
		//
		//	Normalize types to their behavioral class
		//
//		CompleteClass firstCompleteClass = getCompleteClass(firstType);
//		CompleteClass secondCompleteClass = getCompleteClass(secondType);
//		if (firstCompleteClass == secondCompleteClass) {
//			return true;
//		}
	//	firstType = firstCompleteClass.getPrimaryClass();
//		Type behavioralClass = secondCompleteClass.getBehavioralClass();
//		if ((behavioralClass != null) && (behavioralClass != secondType)) {
//			secondCompleteClass = getCompleteClass(behavioralClass);		// See Bug 574431 / Issue 2190 for discussion of this dodgy downcast
//			secondType = behavioralClass;
//		}
		firstType = getPrimaryType(firstType);
		secondType = getPrimaryType(secondType);
		if (firstType == secondType) {
			return true;
		}
		Type behavioralSecondType = basicGetBehavioralType(secondType);
		if (behavioralSecondType != null) {
			secondType = behavioralSecondType;
		}
		//
		//	Use specialized conformance for constructed types, inheritance tree intersection for simple types
		//
		if (firstType == secondType) {
			return true;
		}
		else if ((firstType instanceof DataType) && (secondType instanceof DataType)) {
			if ((firstType instanceof CollectionType) && (secondType instanceof CollectionType)) {
				return conformsToCollectionType((CollectionType)firstType, firstSubstitutions, (CollectionType)secondType, secondSubstitutions, enforceNullity);
			}
			else if ((firstType instanceof MapType) && (secondType instanceof MapType)) {
				return conformsToMapType((MapType)firstType, firstSubstitutions, (MapType)secondType, secondSubstitutions, enforceNullity);
			}
			else if ((firstType instanceof LambdaType) && (secondType instanceof LambdaType)) {
				return conformsToLambdaType((LambdaType)firstType, firstSubstitutions, (LambdaType)secondType, secondSubstitutions, enforceNullity);
			}
			else if ((firstType instanceof TupleType) && (secondType instanceof TupleType)) {
				return conformsToTupleType((TupleType)firstType, firstSubstitutions, (TupleType)secondType, secondSubstitutions, enforceNullity);
			}
		}
		return conformsToType(firstType, firstSubstitutions, secondType, secondSubstitutions);
	}

	/*	@Override
	public boolean conformsToCollectionType(@NonNull DomainCollectionType firstCollectionType, @NonNull DomainCollectionType secondCollectionType) {
		CollectionType firstCollectionType2 = (CollectionType)firstCollectionType;
		CollectionType secondCollectionType2 = (CollectionType)secondCollectionType;
		TemplateParameterSubstitutions firstSubstitutions = TemplateParameterSubstitutionVisitor.createBindings(this, firstCollectionType2, secondCollectionType2);
		TemplateParameterSubstitutions secondSubstitutions = TemplateParameterSubstitutionVisitor.createBindings(this, secondCollectionType2, firstCollectionType2);
		return conformsToCollectionType(firstCollectionType2, firstSubstitutions, secondCollectionType2, secondSubstitutions);
	} */

	/**
	 * @since 7.0
	 */
	protected boolean conformsToCollectionType(@NonNull CollectionType firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull CollectionType secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions, boolean enforceNullity) {
		org.eclipse.ocl.pivot.Class firstContainerType = firstType.getContainerType();
		org.eclipse.ocl.pivot.Class secondContainerType = secondType.getContainerType();
		if (!conformsToType(firstContainerType, firstSubstitutions, secondContainerType, secondSubstitutions)) {
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
		Type firstElementType = PivotUtil.getElementType(firstType);
		Type secondElementType = PivotUtil.getElementType(secondType);
		if (enforceNullity) {
			boolean firstIsNullFree = firstType.isIsNullFree();
			boolean secondIsNullFree = secondType.isIsNullFree();
			return conformsTo(firstElementType, firstIsNullFree, firstSubstitutions, secondElementType, secondIsNullFree, secondSubstitutions);
		}
		else {
			return conformsTo(firstElementType, firstSubstitutions, secondElementType, secondSubstitutions, false);
		}
	}

	/**
	 * @since 7.0
	 */
	protected boolean conformsToLambdaType(@NonNull LambdaType actualType, @NonNull TemplateParameterSubstitutions actualSubstitutions,
			@NonNull LambdaType requiredType, @NonNull TemplateParameterSubstitutions requiredSubstitutions, boolean enforceNullity) {
		LambdaParameter actualContext = PivotUtil.getOwnedContext(actualType);
		LambdaParameter requiredContext = PivotUtil.getOwnedContext(requiredType);
		Type actualContextType = PivotUtil.getType(actualContext);
		Type requiredContextType = PivotUtil.getType(requiredContext);
		if (enforceNullity) {
			boolean actualIsRequired = actualContext.isIsRequired();
			boolean requiredIsRequired = requiredContext.isIsRequired();
			if (!conformsTo(actualContextType, actualIsRequired, actualSubstitutions, requiredContextType, requiredIsRequired, requiredSubstitutions)) {
				return false;
			}
		}
		else {
			if (!conformsTo(actualContextType, actualSubstitutions, requiredContextType, requiredSubstitutions, false)) {
				return false;
			}
		}
		LambdaParameter actualResult = PivotUtil.getOwnedResult(actualType);
		LambdaParameter requiredResult = PivotUtil.getOwnedResult(requiredType);
		Type actualResultType = PivotUtil.getType(actualResult);
		Type requiredResultType = PivotUtil.getType(requiredResult);
		if (enforceNullity) {
			boolean actualIsRequired = actualResult.isIsRequired();
			boolean requiredIsRequired = requiredResult.isIsRequired();
			if (!conformsTo(requiredResultType, requiredIsRequired, requiredSubstitutions, actualResultType, actualIsRequired, actualSubstitutions)) {	// contravariant
				return false;
			}
		}
		else {
			if (!conformsTo(actualResultType, actualSubstitutions, requiredResultType, requiredSubstitutions, false)) {
				return false;
			}
		}
		List<@NonNull LambdaParameter> actualParameters = PivotUtil.getOwnedParametersList(actualType);
		List<@NonNull LambdaParameter> requiredParameters = PivotUtil.getOwnedParametersList(requiredType);
		int iMax = actualParameters.size();
		if (iMax != requiredParameters.size()) {
			return false;
		}
		for (int i = 0; i < iMax; i++) {
			LambdaParameter actualParameter = actualParameters.get(i);
			LambdaParameter requiredParameter = requiredParameters.get(i);
			Type actualParameterType = PivotUtil.getType(actualParameter);
			Type requiredParameterType = PivotUtil.getType(requiredParameter);
			if (enforceNullity) {
				boolean actualIsRequired = actualParameter.isIsRequired();
				boolean requiredIsRequired = requiredParameter.isIsRequired();
				if (!conformsTo(actualParameterType, actualIsRequired, actualSubstitutions, requiredParameterType, requiredIsRequired, requiredSubstitutions)) {
					return false;
				}
			}
			else {
				if (!conformsTo(actualParameterType, actualSubstitutions, requiredParameterType, requiredSubstitutions, false)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @since 7.0
	 */
	protected boolean conformsToMapType(@NonNull MapType firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull MapType secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions, boolean enforceNullity) {
		Type firstKeyType = PivotUtil.getKeyType(firstType);
		Type secondKeyType = PivotUtil.getKeyType(secondType);
		if (enforceNullity) {
			boolean firstKeysAreNullFree =  firstType.isKeysAreNullFree();
			boolean secondKeysAreNullFree = secondType.isKeysAreNullFree();
			if (!conformsTo(firstKeyType, firstKeysAreNullFree, firstSubstitutions, secondKeyType, secondKeysAreNullFree, secondSubstitutions)) {
				return false;
			}
		}
		else {
			if (!conformsTo(firstKeyType, firstSubstitutions, secondKeyType, secondSubstitutions, false)) {
				return false;
			}
		}
		Type firstValueType = PivotUtil.getValueType(firstType);
		Type secondValueType = PivotUtil.getValueType(secondType);
		if (enforceNullity) {
			boolean firstValuesAreNullFree = firstType.isValuesAreNullFree();
			boolean secondValuesAreNullFree = secondType.isValuesAreNullFree();
			return conformsTo(firstValueType, firstValuesAreNullFree, firstSubstitutions, secondValueType, secondValuesAreNullFree, secondSubstitutions);
		}
		else {
			return conformsTo(firstValueType, firstSubstitutions, secondValueType, secondSubstitutions, false);
		}
	}

	/**
	 * @since 7.0
	 */
	protected boolean conformsToTupleType(@NonNull TupleType actualType, @NonNull TemplateParameterSubstitutions actualSubstitutions,
			@NonNull TupleType requiredType, @NonNull TemplateParameterSubstitutions requiredSubstitutions, boolean enforceNullity) {
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
			Type actualPropertyType = PivotUtil.getType(actualProperty);
			Type requiredPropertyType = PivotUtil.getType(requiredProperty);
			if (enforceNullity) {
				boolean actualIsRequired = actualProperty.isIsRequired();
				boolean requiredIsRequired = requiredProperty.isIsRequired();
				if (!conformsTo(actualPropertyType, actualIsRequired, actualSubstitutions, requiredPropertyType, requiredIsRequired, requiredSubstitutions)) {
					return false;
				}
			}
			else {
				if (!conformsTo(actualPropertyType, actualSubstitutions, requiredPropertyType, requiredSubstitutions, false)) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * @since 7.0
	 */
	protected abstract boolean conformsToType(@NonNull Type firstType, @NonNull TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, @NonNull TemplateParameterSubstitutions secondSubstitutions);	// XXX substitutions not used

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull CollectionTypeManager createCollectionTypeManager();

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull MapTypeManager createMapTypeManager();

	/**
	 * @since 7.0
	 */
	protected abstract @NonNull TupleTypeManager createTupleTypeManager();

	@Override
	public @NonNull CollectionType getBagType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getBagType(), elementType, isNullFree, lower, upper);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		return getCollectionTypeManager().getCollectionType(typeArguments);
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeId collectionTypeId) {
		return getCollectionTypeManager().getCollectionType(collectionTypeId);
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		CollectionTypeArguments typeArguments = new CollectionTypeArguments(genericType.getTypeId(), elementType, isNullFree, lower, upper);
		return getCollectionTypeManager().getCollectionType(typeArguments);
	}

	@Override
	public @NonNull CollectionTypeManager getCollectionTypeManager() {
		CollectionTypeManager collectionTypeManager2 = collectionTypeManager;
		if (collectionTypeManager2 == null) {
			collectionTypeManager = collectionTypeManager2 = createCollectionTypeManager();
		}
		return collectionTypeManager2;
	}

	@Override
	public @NonNull Type getCommonType(@NonNull Type leftType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, @NonNull TemplateParameterSubstitutions rightSubstitutions) {
		if ((leftType instanceof TupleType) && (rightType instanceof TupleType)) {
			Type commonType = getTupleTypeManager().getCommonType((TupleType)leftType, leftSubstitutions, (TupleType)rightType, rightSubstitutions);
			if (commonType == null) {
				commonType = getOclAnyType();
			}
			return commonType;
		}
		if ((leftType instanceof CollectionType) && (rightType instanceof CollectionType)) {
			CompleteInheritance leftInheritance = leftType.getInheritance(this);
			CompleteInheritance rightInheritance = rightType.getInheritance(this);
			CompleteInheritance commonInheritance = leftInheritance.getCommonInheritance(rightInheritance);
			CollectionType commonCollectionType = (CollectionType)getPrimaryType(commonInheritance.getPivotClass());
			CollectionType leftCollectionType = (CollectionType)leftType;
			CollectionType rightCollectionType = (CollectionType)rightType;
			Type leftElementType = ClassUtil.requireNonNull(leftCollectionType.getElementType());
			Type rightElementType = ClassUtil.requireNonNull(rightCollectionType.getElementType());
			Type commonElementType = getCommonType(leftElementType, leftSubstitutions, rightElementType, rightSubstitutions);
			boolean commonIsNullFree = leftCollectionType.isIsNullFree() && rightCollectionType.isIsNullFree();
			return getCollectionType(commonCollectionType, commonElementType, commonIsNullFree, null, null);
		}
		if (conformsTo(leftType, leftSubstitutions, rightType, rightSubstitutions, false)) {
			return rightType;
		}
		if (conformsTo(rightType, rightSubstitutions, leftType, leftSubstitutions, false)) {
			return leftType;
		}
		CompleteInheritance leftInheritance = leftType.getInheritance(this);
		CompleteInheritance rightInheritance = rightType.getInheritance(this);
		CompleteInheritance commonInheritance = leftInheritance.getCommonInheritance(rightInheritance);
		return getPrimaryType(commonInheritance.getPivotClass());
	}

	@Override
	public boolean getCommonIsRequired(boolean leftIsRequired, boolean rightIsRequired) {
		return leftIsRequired && rightIsRequired;
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getLibraryClass(@NonNull String className) {
		return ClassUtil.requireNonNull(basicGetLibraryClass(className));
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MapType getMapEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		return getMapTypeManager().getMapEntryType(entryClass);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MapType getMapType(@NonNull Type keyType, boolean keyValuesAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		MapTypeArguments typeArguments = new MapTypeArguments(keyType, keyValuesAreNullFree, valueType, valuesAreNullFree);
		return getMapTypeManager().getMapType(typeArguments);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull MapTypeManager getMapTypeManager() {
		MapTypeManager mapTypeManager2 = mapTypeManager;
		if (mapTypeManager2 == null) {
			mapTypeManager = mapTypeManager2 = createMapTypeManager();
		}
		return mapTypeManager2;
	}

	@Override
	public @NonNull CollectionType getOrderedSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getOrderedSetType(), elementType, isNullFree, lower, upper);
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
	public @NonNull CollectionType getSequenceType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSequenceType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull CollectionType getSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return getCollectionType(getSetType(), elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull Collection<@NonNull ? extends TypedElement> parts, @Nullable TemplateParameterSubstitutions bindings) {
		return getTupleTypeManager().getTupleType(parts, bindings);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull List<@NonNull PartId> partIds) {
		return getTupleTypeManager().getTupleType(partIds);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull TupleTypeId typeId) {
		return getTupleTypeManager().getTupleType(typeId);
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @NonNull TupleTypeManager getTupleTypeManager() {
		TupleTypeManager tupleTypeManager2 = tupleTypeManager;
		if (tupleTypeManager2 == null) {
			tupleTypeManager = tupleTypeManager2 = createTupleTypeManager();
		}
		return tupleTypeManager2;
	}

	protected void resetLibrary() {
		if (collectionTypeManager != null) {
			collectionTypeManager.dispose();
			collectionTypeManager = null;
		}
		if (mapTypeManager != null) {
			mapTypeManager.dispose();
			mapTypeManager = null;
		}
		if (tupleTypeManager != null) {
			tupleTypeManager.dispose();
			tupleTypeManager = null;
		}
	}
} //StandardLibraryImpl
