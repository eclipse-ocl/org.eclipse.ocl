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
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TemplateParameter;
import org.eclipse.ocl.pivot.TemplateSignature;
import org.eclipse.ocl.pivot.TemplateableElement;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation;
import org.eclipse.ocl.pivot.types.TuplePart;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.TypeUtil;
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
	public @Nullable Operation basicGetOclInvalidOperation() {
		return oclInvalidOperation;
	}

	@Override
	public @Nullable Property basicGetOclInvalidProperty() {
		return oclInvalidProperty;
	}

	@Override
	public @NonNull CollectionType getCollectionType(boolean isOrdered, boolean isUnique) {
		return isOrdered ? isUnique ? getOrderedSetType() : getSequenceType() : isUnique ? getSetType() : getBagType();
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

	public @NonNull Type getPrimaryType(@NonNull Type asType) {
		return asType;
	}

	@Override
	public @Nullable Type getPrimitiveType(@NonNull PrimitiveTypeId typeId) {
		return TypeUtil.getPrimitiveType(this, typeId);
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
				if (templateArgument == null) {
					templateArgument = templateParameter;
				}
				if (templateArgument != null) {
					return getCollectionType(unspecializedType, templateArgument, null, null, null);
				}
			}
			return collectionType;
		}
		else if (type instanceof TupleType) {
			return getTupleType((TupleType) type, substitutions);
		}
		else if (type instanceof LambdaType) {
			LambdaType lambdaType = (LambdaType)type;
			Type contextType = ClassUtil.nonNullModel(lambdaType.getContextType());
			@NonNull List<@NonNull Type> parameterType = PivotUtil.getParameterType(lambdaType);
			Type resultType = ClassUtil.nonNullModel(lambdaType.getResultType());
			return getLambdaType(contextType, parameterType, resultType, substitutions);
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
				return getLibraryType(unspecializedType, templateArguments);
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

	protected abstract boolean isUnspecialized(@NonNull CollectionType genericType, @NonNull Type elementType,
			@Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	protected abstract boolean isUnspecialized(@NonNull Type keyType, @Nullable Boolean keysAreNullFree, @NonNull Type valueType, @Nullable Boolean valuesAreNullFree);

	public void resetLibrary() {
		oclInvalidOperation = null;
		oclInvalidProperty = null;

	}

	@Override
	public @NonNull Type resolveSelfSpecialization(@NonNull Type asType) {
		Type specializedType = getSpecializedType(asType, TemplateParameterSubstitutions.SELF);
		if (asType != specializedType) {
			System.out.println("resolveSelfSpecialization " + asType + " => " + specializedType);
		}
		return specializedType;
	}
} //AbstractStandardLibraryImpl
