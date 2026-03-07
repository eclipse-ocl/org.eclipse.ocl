/*******************************************************************************
 * Copyright (c) 2026 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import java.util.Collection;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.AnyType;
import org.eclipse.ocl.pivot.BagType;
import org.eclipse.ocl.pivot.BooleanType;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.InvalidType;
import org.eclipse.ocl.pivot.LambdaType;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OrderedSetType;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.SequenceType;
import org.eclipse.ocl.pivot.SetType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.VoidType;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatModel;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.internal.manager.Orphanage;
import org.eclipse.ocl.pivot.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.manager.JavaTypeManager;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.manager.MapTypeManager;
import org.eclipse.ocl.pivot.manager.SpecializedTypeManager;
import org.eclipse.ocl.pivot.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibTables;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateArguments;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * A DelegateStandardLibrary is used by an EcoreExecutorManager so that the simple efficient shared support of a
 * PartialStandardLibrary may be re-used until it is discovered that a custom CompleteStandardLibrary is required.
 *
 * @since 7.0
 */
public class DelegateStandardLibrary extends AbstractStandardLibrary
{
	private @NonNull StandardLibrary standardLibrary;

	public DelegateStandardLibrary(@NonNull PartialStandardLibrary standardLibrary) {
		this.standardLibrary = standardLibrary;
	}

	@Override
	public void addOrphanClass(org.eclipse.ocl.pivot.@NonNull Class pivotElement) {
	//	return standardLibrary.addOrphanClass();
		throw new UnsupportedOperationException();				// XXX ??? remove from inherited interface
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class basicGetBehavioralClass(@NonNull Class<?> javaClass) {
		return standardLibrary.basicGetBehavioralClass(javaClass);
	}

	@Override
	public @Nullable Type basicGetBehavioralType(@NonNull Type type) {
		return standardLibrary.basicGetBehavioralType(type);
	}

	@Override
	public @Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		return standardLibrary.basicGetCollectionType(typeArguments);
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Class basicGetLibraryClass(@NonNull String className) {
		return standardLibrary.basicGetLibraryClass(className);
	}

	@Override
	public org.eclipse.ocl.pivot.@Nullable Package basicGetOrphanage() {
	//	return standardLibrary.basicGetOrphanage();
		throw new UnsupportedOperationException();				// XXX ??? remove from inherited interface
	}

	@Override
	public @NonNull BagType getBagType() {
		return standardLibrary.getBagType();
	}

	@Override
	public @NonNull BooleanType getBooleanType() {
		return standardLibrary.getBooleanType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getClassType() {
		return standardLibrary.getClassType();
	}

	@Override
	public @NonNull CollectionType getCollectionType() {
		return standardLibrary.getCollectionType();
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionTypeArguments typeArguments) {
		return standardLibrary.getCollectionType(typeArguments);
	}

	@Override
	public @NonNull CollectionType getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType,
			boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper) {
		return standardLibrary.getCollectionType(genericType, elementType, isNullFree, lower, upper);
	}

	@Override
	public @NonNull CollectionType getCollectionType( @NonNull CollectionTypeId collectionTypeId) {
		return standardLibrary.getCollectionType();
	}

	@Override
	public @NonNull CollectionTypeManager getCollectionTypeManager() {
		return standardLibrary.getCollectionTypeManager();
	}

	@Override
	public boolean getCommonIsRequired(boolean leftIsRequired, boolean rightIsRequired) {
		return standardLibrary.getCommonIsRequired(leftIsRequired, rightIsRequired);
	}

	@Override
	public @NonNull Type getCommonType(@NonNull Type leftType, @Nullable TemplateArguments leftTemplateArguments,
			@NonNull Type rightType, @Nullable TemplateArguments rightTemplateArguments) {
		return standardLibrary.getCommonType(leftType, leftTemplateArguments, rightType, rightTemplateArguments);
	}

	@Override
	public @NonNull Type getCommonType(@NonNull Type thisType, @NonNull Type thatType) {
		return standardLibrary.getCommonType(thisType, thatType);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getCommonType(org.eclipse.ocl.pivot.@NonNull Class thisType,org.eclipse.ocl.pivot.@NonNull Class thatType) {
		return standardLibrary.getCommonType(thisType, thatType);
	}

	@Override
	public @NonNull CreateStrategy getCreateStrategy() {
		return standardLibrary.getCreateStrategy();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEnumerationType() {
		return standardLibrary.getEnumerationType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getEquivalentClass(@NonNull Model thisModel, org.eclipse.ocl.pivot.@NonNull Class thatClass) {
		return standardLibrary.getEquivalentClass(thisModel, thatClass);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getEquivalentPackage(@NonNull Model thisModel, org.eclipse.ocl.pivot.@NonNull Package thatPackage) {
		return standardLibrary.getEquivalentPackage(thisModel, thatPackage);
	}

	@Override
	public @NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class type) {
		FlatClass flatClass = type.basicGetFlatClass();
		if (flatClass != null) {
			return type.getFlatClass(standardLibrary);					// Use built-in flat class directly
		}
		if (standardLibrary == OCLstdlibTables.LIBRARY) {
			EnvironmentFactory environmentFactory = PivotUtil.getEnvironmentFactory(type);
			standardLibrary = environmentFactory.getStandardLibrary();
		}
		return standardLibrary.getFlatClass(type);
	}

	@Override
	public @NonNull FlatModel getFlatModel() {
		return standardLibrary.getFlatModel();
	}

	@Override
	public @NonNull IdResolver getIdResolver() {
		return standardLibrary.getIdResolver();
	}

	@Override
	public @NonNull PrimitiveType getIntegerType() {
		return standardLibrary.getIntegerType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getJavaType(@NonNull Object object) {
		return standardLibrary.getJavaType(object);
	}

	@Override
	public @NonNull JavaTypeManager getJavaTypeManager() {
		return standardLibrary.getJavaTypeManager();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getLibraryClass(@NonNull String className) {
		return standardLibrary.getLibraryClass(className);
	}

	@Override
	public @NonNull LambdaTypeManager getLambdaTypeManager() {
		return standardLibrary.getLambdaTypeManager();
	}

	@Override
	public @NonNull LambdaType getLambdaType(@NonNull TypedElement contextType, @NonNull List<@NonNull ? extends TypedElement> parameterTypes,
			@NonNull TypedElement resultType, @Nullable TemplateArguments bindings) {
		return standardLibrary.getLambdaType(contextType, parameterTypes, resultType, bindings);
	}

	@Override
	public @NonNull MapType getMapEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass) {
		return standardLibrary.getMapEntryType(entryClass);
	}

	@Override
	public @NonNull MapType getMapType() {
		return standardLibrary.getMapType();
	}

	@Override
	public @NonNull MapTypeManager getMapTypeManager() {
		return standardLibrary.getMapTypeManager();
	}

	@Override
	public @NonNull MapType getMapType(@NonNull Type keyType, boolean keysAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree) {
		return standardLibrary.getMapType(keyType, keysAreNullFree, valueType, valuesAreNullFree);
	}

	@Override
	public @NonNull MapType getMapType(@NonNull MapTypeArguments typeArguments) {
		return standardLibrary.getMapType(typeArguments);
	}

	@Override
	public org.eclipse.ocl.pivot.Package getNsURIPackage(@NonNull String nsURI) {
		return standardLibrary.getNsURIPackage(nsURI);
	}

	@Override
	public @NonNull AnyType getOclAnyType() {
		return standardLibrary.getOclAnyType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclComparableType() {
		return standardLibrary.getOclComparableType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclElementType() {
		return standardLibrary.getOclElementType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclEnumerationType() {
		return standardLibrary.getOclEnumerationType();
	}

	@Override
	public @NonNull Operation getOclInvalidOperation() {
		return standardLibrary.getOclInvalidOperation();
	}

	@Override
	public @NonNull Property getOclInvalidProperty() {
		return standardLibrary.getOclInvalidProperty();
	}

	@Override
	public @NonNull InvalidType getOclInvalidType() {
		return standardLibrary.getOclInvalidType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclLambdaType() {
		return standardLibrary.getOclLambdaType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclMessageType() {
		return standardLibrary.getOclMessageType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSelfType() {
		return standardLibrary.getOclSelfType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclStereotypeType() {
		return standardLibrary.getOclStereotypeType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclSummableType() {
		return standardLibrary.getOclSummableType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTupleType() {
		return standardLibrary.getOclTupleType();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getOclTypeType() {
		return standardLibrary.getOclTypeType();
	}

	@Override
	public @NonNull VoidType getOclVoidType() {
		return standardLibrary.getOclVoidType();
	}

	@Override
	public @NonNull CollectionType getOrderedCollectionType() {
		return standardLibrary.getOrderedCollectionType();
	}

	@Override
	public @NonNull OrderedSetType getOrderedSetType() {
		return standardLibrary.getOrderedSetType();
	}

	@Override
	public @NonNull Orphanage getOrphanage() {
//		return standardLibrary.getOrphanage();
		throw new UnsupportedOperationException();				// XXX ??? remove from inherited interface
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Package getPackage() {
		return standardLibrary.getPackage();
	}

	@Override
	public @NonNull Type getPrimaryType(@NonNull Type asType) {
		return standardLibrary.getPrimaryType(asType);
	}

	@Override
	public @Nullable Type getPrimitiveType(@NonNull PrimitiveTypeId typeId) {
		return standardLibrary.getPrimitiveType(typeId);
	}

	@Override
	public @NonNull PrimitiveType getRealType() {
		return standardLibrary.getRealType();
	}

	@Override
	public @NonNull SequenceType getSequenceType() {
		return standardLibrary.getSequenceType();
	}

	@Override
	public @NonNull SetType getSetType() {
		return standardLibrary.getSetType();
	}

	@Override
	public StatusCodes.@Nullable Severity getSeverity(@Nullable Object validationKey) {
		return standardLibrary.getSeverity(validationKey);
	}

	@Override
	public @NonNull Type getSpecializedType(@NonNull Type type, @Nullable TemplateArguments templateArguments) {
		return standardLibrary.getSpecializedType(type, templateArguments);
	}

	@Override
	public @NonNull SpecializedTypeManager getSpecializedTypeManager() {
		return standardLibrary.getSpecializedTypeManager();
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getSpecializedType(org.eclipse.ocl.pivot.@NonNull Class genericClass, @NonNull List<@NonNull ? extends Type> superTemplateArgumentList) {
		return standardLibrary.getSpecializedType(genericClass, superTemplateArgumentList);
	}

	@Override
	public @NonNull PrimitiveType getStringType() {
		return standardLibrary.getStringType();
	}

	@Override
	public @NonNull TupleType getTupleType(@Nullable List<@NonNull Property> asParts, @NonNull List<@NonNull PartId> partList) {
		return standardLibrary.getTupleType(asParts, partList);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull TupleTypeId typeId) {
		return standardLibrary.getTupleType(typeId);
	}

	@Override
	public @NonNull TupleType getTupleType(@NonNull Collection<@NonNull ? extends TypedElement> parts, @Nullable TemplateArguments bindings) {
		return standardLibrary.getTupleType(parts, bindings);
	}

	@Override
	public @NonNull TupleTypeManager getTupleTypeManager() {
		return standardLibrary.getTupleTypeManager();
	}

	@Override
	public @NonNull CollectionType getUniqueCollectionType() {
		return standardLibrary.getUniqueCollectionType();
	}

	@Override
	public @NonNull PrimitiveType getUnlimitedNaturalType() {
		return standardLibrary.getUnlimitedNaturalType();
	}

	@Override
	public void installImplicitOppositeProperty(@NonNull Property thisProperty, @NonNull String oppositeName) {
		standardLibrary.installImplicitOppositeProperty(thisProperty, oppositeName);
	}

	@Override
	public void installOppositeProperty(@NonNull Property thisProperty, @NonNull String oppositeName, boolean isOrdered,
			boolean isUnique, @NonNull IntegerValue lower, @NonNull UnlimitedNaturalValue upper) {
		standardLibrary.installOppositeProperty(thisProperty, oppositeName, isOrdered, isUnique, lower, upper);
	}

	@Override
	public boolean isEqualTo(@NonNull Type leftType, @NonNull Type rightType) {
		return standardLibrary.isEqualTo(leftType, rightType);
	}

	@Override
	public void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass) {
		standardLibrary.resolveSuperClasses(specializedClass, unspecializedClass);
	}

	@Override
	public @NonNull CreateStrategy setCreateStrategy(@NonNull CreateStrategy createStrategy) {
		return standardLibrary.setCreateStrategy(createStrategy);
	}
}