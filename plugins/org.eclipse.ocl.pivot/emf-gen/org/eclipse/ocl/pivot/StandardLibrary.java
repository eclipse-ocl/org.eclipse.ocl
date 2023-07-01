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
package org.eclipse.ocl.pivot;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatModel;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Standard Library</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getStandardLibrary()
 * @generated
 */
public interface StandardLibrary extends Element
{
	@Nullable AnyType basicGetOclAnyType();
	@Nullable Operation basicGetOclInvalidOperation();
	@Nullable Property basicGetOclInvalidProperty();
	@Nullable InvalidType basicGetOclInvalidType();
	@NonNull WildcardType createWildcardType(@NonNull Type asType);
	void dispose();

	/**
	 * Obtains the generic instance of the BagType metatype, named
	 * <tt>Bag(T)</tt>.
	 *
	 * @return the <tt>Bag(T)</tt> type (an instance of BagType)
	 */
	@NonNull BagType getBagType();

	/**
	 * Return the specialized bag type for the specializing elementType with isNullFree content and optional lower and upper bounds.
	 * May return an InvalidType if elementType is a proxy.
	 */
	org.eclipse.ocl.pivot.@NonNull Class getBagType(@NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	@Nullable PrimitiveType getBehavioralClass(java.lang.@NonNull Class<?> javaClass);

	/**
	 * Obtains the instance of the PrimitiveType metatype, named
	 * <tt>Boolean</tt>.
	 *
	 * @return the <tt>Boolean</tt> type (an instance of PrimitiveType)
	 */
	@NonNull BooleanType getBooleanType();

	/**
	 * Obtains the single instance of the org.eclipse.ocl.pivot.Class metatype, named
	 * <tt>Class</tt>.
	 *
	 * @return the <tt>Class</tt> type (an instance of Class)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getClassType();

	/**
	 * Obtains the generic instance of the CollectionType metatype, named
	 * <tt>Collection(T)</tt>.
	 *
	 * @return the <tt>Collection(T)</tt> type (an instance of CollectionType)
	 */
	@NonNull CollectionType getCollectionType();

	/**
	 * Return the unspecialized CollectionType for the given unspecialized CollectionTypeId.
	 */
	@NonNull CollectionType getCollectionType(@NonNull CollectionTypeId genericTypeId);

	/**
	 * Return the unspecialized ordered/not-ordered. unique/not-unique CollectionType.
	 */
	@NonNull CollectionType getCollectionType(boolean isOrdered, boolean isUnique);

	/**
	 * Return the specialized collection type for the unspecialized containerType, specializing elementType with isNullFree content and optional lower and upper bounds.
	 * May return an InvalidType if elementType is a proxy.
	 */
	org.eclipse.ocl.pivot.@NonNull Class getCollectionType(@NonNull CollectionType containerType, @NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	@Nullable Type getCommonTupleType(@NonNull TupleType leftType, @NonNull TemplateParameterSubstitutions leftSubstitutions,
			@NonNull TupleType rightType, @NonNull TemplateParameterSubstitutions rightSubstitutions);

	/**
	 * Obtains the single instance of the EnumerationType metatype, named
	 * <tt>Enumeration</tt>.
	 *
	 * @return the <tt>Enumeration</tt> type (an instance of Enumeration)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getEnumerationType();

	/**
	 * Return the FlatClass for a given type.
	 */
	@NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class type);

	/**
	 * Obtains the instance of the PrimitiveType metatype, named
	 * <tt>Integer</tt>.
	 *
	 * @return the <tt>Integer</tt> type (an instance of PrimitiveType)
	 */
	@NonNull PrimitiveType getIntegerType();

	@NonNull LambdaType getLambdaType(@NonNull Type contextType, @NonNull List<@NonNull ? extends Type> parameterTypes, @NonNull Type resultType,
			@Nullable TemplateParameterSubstitutions bindings);

	org.eclipse.ocl.pivot.@Nullable Class getLibraryType(@NonNull String string, @NonNull List<@NonNull ? extends Type> templateArguments);

	@NonNull <T extends org.eclipse.ocl.pivot.Class> T getLibraryType(@NonNull T libraryType, @NonNull List<@NonNull ? extends Type> templateArguments);

	org.eclipse.ocl.pivot.Class getLibraryType(@NonNull String typeName);

	org.eclipse.ocl.pivot.@NonNull Class getMapOfEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass);

	/**
	 * Obtains the generic instance of the MapType metatype, named
	 * <tt>Map(T)</tt>.
	 *
	 * @return the <tt>Map(T)</tt> type (an instance of MapType)
	 */
	@NonNull MapType getMapType();

	/**
	 *  Return the specialized map type for the specializing keyType and valueType with keyValuesAreNullFree and valuesAreNullFree content.
	 *  May return an InvalidType if keyType or valueType is a proxy.
	 */
	org.eclipse.ocl.pivot.@NonNull Class getMapType(@NonNull Type keyType, @Nullable Boolean keyValuesAreNullFree, @NonNull Type valueType, @Nullable Boolean valuesAreNullFree);

	/**
	 * Return the metaclass to which classType conforms.
	 */
	org.eclipse.ocl.pivot.@NonNull Class getMetaclass(@NonNull Type classType);

	org.eclipse.ocl.pivot.Package getNestedPackage(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name);

	Type getNestedType(org.eclipse.ocl.pivot.@NonNull Package parentPackage, @NonNull String name);

	/**
	 * Returns the meta-type of a given type.
	 */
	@Deprecated /* @deprecated use getMetaclass */
	@NonNull Type getMetaType(@NonNull Type type);

	org.eclipse.ocl.pivot.Package getNsURIPackage(@NonNull String nsURI);

	/**
	 * Return the known nsURIs
	 *
	 * @since 1.14
	 */
	default @NonNull Set<@NonNull String> getNsURIs() { return Collections.emptySet(); }

	/**
	 * Obtains the single instance of the AnyType metatype, named
	 * <tt>OclAny</tt>.
	 *
	 * @return the <tt>OclAny</tt> type (an instance of AnyType)
	 */
	@NonNull AnyType getOclAnyType();

	/**
	 * Obtains the single instance of the OclComparable metatype, named
	 * <tt>OclAny</tt>.
	 *
	 * @return the <tt>OclAny</tt> type (an instance of Class)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclComparableType();

	/**
	 * Obtains the single instance of the Class metatype, named
	 * <tt>OclElement</tt>.
	 *
	 * @return the <tt>OclElement</tt> type (an instance of Class)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclElementType();

	/**
	 * Obtains the single instance of the Class metatype, named
	 * <tt>OclEnumeration</tt>.
	 *
	 * @return the <tt>OclEnumeration</tt> type (an instance of Class)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclEnumerationType();

	@NonNull Operation getOclInvalidOperation();

	/**
	 * @since 1.4
	 */
	@NonNull Property getOclInvalidProperty();

	/**
	 * Obtains the single instance of the InvalidType metatype, named
	 * <tt>OclInvalid</tt>.
	 *
	 * @return the <tt>OclInvalid</tt> type (an instance of InvalidType)
	 */
	@NonNull InvalidType getOclInvalidType();

	/**
	 * Obtains the single instance of the LambdaType metatype, named
	 * <tt>OclLambda</tt>.
	 *
	 * @return the <tt>OclLambda</tt> type (an instance of LambdaType)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclLambdaType();

	/**
	 * Obtains the generic instance of the MessageType metatype, named
	 * <tt>OclMessage</tt>.
	 *
	 * @return the <tt>OclMessage</tt> type (an instance of MessageType)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclMessageType();

	/**
	 * Obtains the single instance of the OclSelf pseudo-metatype, named
	 * <tt>OclSelf</tt>.
	 *
	 * @return the <tt>OclSelf</tt> type (an instance of SelfType)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclSelfType();

	/**
	 * Obtains the single instance of the OclStereotype metatype, named
	 * <tt>OclStereotype</tt>.
	 *
	 * @return the <tt>OclStereotype</tt> type (an instance of Class)
	 * @since 1.1
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclStereotypeType();

	/**
	 * Obtains the single instance of the OclSummable metatype, named
	 * <tt>OclAny</tt>.
	 *
	 * @return the <tt>OclAny</tt> type (an instance of Class)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclSummableType();

	/**
	 * Obtains the single instance of the OclTupleType metatype, named
	 * <tt>OclVoid</tt>.
	 *
	 * @return the <tt>OclTuple</tt> type (an instance of Class)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclTupleType();

	Type getOclType(@NonNull String typeName);

	org.eclipse.ocl.pivot.@NonNull Class getOclTypeType();

	/**
	 * Obtains the single instance of the VoidType metatype, named
	 * <tt>OclVoid</tt>.
	 *
	 * @return the <tt>OclVoid</tt> type (an instance of VoidType)
	 */
	@NonNull VoidType getOclVoidType();

	Element getOperationTemplateParameter(@NonNull Operation anOperation, int index);

	/**
	 * Obtains the generic instance of the OrderedCollection metatype, named
	 * <tt>OrderedCollection(T)</tt>.
	 *
	 * @return the <tt>OrderedCollection(T)</tt> type (an instance of CollectionType)
	 */
	@NonNull CollectionType getOrderedCollectionType();

	/**
	 * Obtains the generic instance of the OrderedSetType metatype, named
	 * <tt>OrderedSet(T)</tt>.
	 *
	 * @return the <tt>OrderedSet(T)</tt> type (an instance of OrderedSetType)
	 */
	@NonNull OrderedSetType getOrderedSetType();

	/**
	 * Return the specialized ordered set type for the specializing elementType with isNullFree content and optional lower and upper bounds.
	 * May return an InvalidType if elementType is a proxy.
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOrderedSetType(@NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	/**
	 * Obtains the package containing the library types
	 */
	org.eclipse.ocl.pivot.@NonNull Package getPackage();

	Type getPrimitiveType(@NonNull PrimitiveTypeId id);

	/**
	 * Obtains the instance of the PrimitiveType metatype, named
	 * <tt>Real</tt>.
	 *
	 * @return the <tt>Real</tt> type (an instance of PrimitiveType)
	 */
	@NonNull PrimitiveType getRealType();

	org.eclipse.ocl.pivot.Package getRootPackage(@NonNull String name);

	/**
	 * Obtains the generic instance of the SequenceType metatype, named
	 * <tt>Sequence(T)</tt>.
	 *
	 * @return the <tt>Sequence(T)</tt> type (an instance of SequenceType)
	 */
	@NonNull SequenceType getSequenceType();

	/**
	 * Return the specialized sequence type for the specializing elementType with isNullFree content and optional lower and upper bounds.
	 * May return an InvalidType if elementType is a proxy.
	 */
	org.eclipse.ocl.pivot.@NonNull Class getSequenceType(@NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	/**
	 * Obtains the generic instance of the SetType metatype, named
	 * <tt>Set(T)</tt>.
	 *
	 * @return the <tt>Set(T)</tt> type (an instance of SetType)
	 */
	@NonNull SetType getSetType();

	/**
	 * Return the specialized set type for the specializing elementType with isNullFree content and optional lower and upper bounds.
	 * May return an InvalidType if elementType is a proxy.
	 */
	org.eclipse.ocl.pivot.@NonNull Class getSetType(@NonNull Type elementType, @Nullable Boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	@NonNull Orphanage getOrphanage();

	@NonNull Type getSpecializedType(@NonNull Type referenceType, @Nullable TemplateParameterSubstitutions referenceBindings);

	/**
	 * Obtains the instance of the PrimitiveType metatype, named
	 * <tt>String</tt>.
	 *
	 * @return the <tt>String</tt> type (an instance of PrimitiveType)
	 */
	@NonNull PrimitiveType getStringType();

	@NonNull TupleType getTupleType(@NonNull String typeName, @NonNull Collection<@NonNull ? extends TypedElement> parts,
			@Nullable TemplateParameterSubstitutions bindings);

	@NonNull TupleType getTupleType(@NonNull String tupleName, @NonNull Map<@NonNull String, @NonNull ? extends Type> parts);

	@NonNull TupleType getTupleType(@NonNull TupleType type, @Nullable TemplateParameterSubstitutions usageBindings);

	@NonNull TupleType getTupleType(@NonNull IdResolver idResolver, @NonNull TupleTypeId tupleTypeId);

	/**
	 * Obtains the generic instance of the UniqueCollection metatype, named
	 * <tt>Set(T)</tt>.
	 *
	 * @return the <tt>Set(T)</tt> type (an instance of CollectionType)
	 */
	@NonNull CollectionType getUniqueCollectionType();

	/**
	 * Obtains the instance of the PrimitiveType metatype,
	 * named <tt>UnlimitedNatural</tt>.
	 *
	 * @return the <tt>UnlimitedNatural</tt> type (an instance of
	 *     PrimitiveType)
	 */
	@NonNull PrimitiveType getUnlimitedNaturalType();

	@NonNull FlatModel getFlatModel();

	@NonNull Type resolveSelfSpecialization(@NonNull Type asType);
	void resolveSuperClasses(org.eclipse.ocl.pivot.@NonNull Class specializedClass, org.eclipse.ocl.pivot.@NonNull Class unspecializedClass);
} // AbstractStandardLibrary
