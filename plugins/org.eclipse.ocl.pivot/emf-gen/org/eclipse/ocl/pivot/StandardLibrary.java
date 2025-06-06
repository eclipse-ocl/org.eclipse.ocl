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
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.flat.FlatModel;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.PartId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.manager.JavaTypeManager;
import org.eclipse.ocl.pivot.manager.LambdaTypeManager;
import org.eclipse.ocl.pivot.manager.MapTypeManager;
import org.eclipse.ocl.pivot.manager.TupleTypeManager;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Library</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getStandardLibrary()
 * @generated
 */
public interface StandardLibrary extends Element
{
	/**
	 * @since 7.0
	 */
	@Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeArguments typeArguments);

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@Nullable Class basicGetLibraryClass(@NonNull String className);

	/**
	 * Return true if leftType conforms to rightType within standardLibrary.
	 * @since 7.0
	 */
	boolean conformsTo(@NonNull Type leftType, @NonNull Type rightType);

	/**
	 * Return true if firstType + firstIsRequired augmented by firstSubstitutions conforms to
	 * secondType + secondIsRequired augmented by secondSubstitutions.
	 *
	 * This method should be used during validation once nullity is determined and neds checking.
	 *
	 * @since 7.0
	 */
	boolean conformsTo(@NonNull Type leftType, boolean leftIsRequired, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, boolean rightIsRequired, @Nullable TemplateParameterSubstitutions rightSubstitutions);

	/**
	 * @since 7.0
	 */
	boolean conformsTo(@NonNull Type leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions, boolean enforceNullity);

	/**
	 * Return true if firstType augmented by firstSubstitutions conforms to
	 * secondType augmented by secondSubstitutions.
	 *
	 * This method should be used during parsing before nullity is determined.
	 *
	 * @since 7.0
	 */
	boolean conformsTo(@NonNull Type firstType, @Nullable TemplateParameterSubstitutions firstSubstitutions,
			@NonNull Type secondType, @Nullable TemplateParameterSubstitutions secondSubstitutions);

	@NonNull Iterable<@NonNull ? extends CompletePackage> getAllCompletePackages();

	/**
	 * Obtains the generic instance of the BagType metatype, named
	 * <tt>Bag(T)</tt>.
	 *
	 * @return the <tt>Bag(T)</tt> type (an instance of BagType)
	 * @since 7.0
	 */
	@NonNull BagType getBagType();

	/**
	 * Return the instance of the Bag metatype whose elements are of elementType.
	 * @since 7.0
	 */
	@NonNull CollectionType getBagType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	org.eclipse.ocl.pivot.@Nullable Class getBehavioralClass(java.lang.@NonNull Class<?> javaClass);

	/**
	 * Obtains the instance of the PrimitiveType metatype, named
	 * <tt>Boolean</tt>.
	 *
	 * @return the <tt>Boolean</tt> type (an instance of PrimitiveType)
	 * @since 7.0
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
	 * @since 7.0
	 */
	@NonNull CollectionType getCollectionType();

	/**
	 * @since 7.0
	 */
	@NonNull CollectionType getCollectionType(@NonNull CollectionTypeArguments typeArguments);

	/**
	 * @since 7.0
	 */
	@NonNull CollectionType getCollectionType(@NonNull CollectionType genericType, @NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	/**
	 * @since 7.0
	 */
	@NonNull CollectionType getCollectionType(@NonNull CollectionTypeId collectionTypeId);

	/**
	 * @since 7.0
	 */
	@NonNull CollectionTypeManager getCollectionTypeManager();

	/**
	 * @since 7.0
	 */
	boolean getCommonIsRequired(boolean leftIsRequired, boolean rightIsRequired);

	/**
	 * @since 7.0
	 */
	@NonNull Type getCommonType(@NonNull Type leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull Type rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions);

	/**
	 * Return the most derived type common to this type and thatType within this standardLibrary.
	 * @since 7.0
	 */
	@NonNull Type getCommonType(@NonNull Type thisType, @NonNull Type thatType);

	/**
	 * Return the most derived type common to this type and thatType within this standardLibrary.
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getCommonType(org.eclipse.ocl.pivot.@NonNull Class thisType, org.eclipse.ocl.pivot.@NonNull Class thatType);

	/**
	 * Obtains the single instance of the EnumerationType metatype, named
	 * <tt>Enumeration</tt>.
	 *
	 * @return the <tt>Enumeration</tt> type (an instance of Enumeration)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getEnumerationType();

	/**
	 * Return the FlatClass for a given type.
	 * @since 7.0
	 */
	@NonNull FlatClass getFlatClass(org.eclipse.ocl.pivot.@NonNull Class type);

	/**
	 * @since 7.0
	 */
	@NonNull FlatModel getFlatModel();

	/**
	 * @since 7.0
	 */
	@NonNull IdResolver getIdResolver();

	/**
	 * Obtains the instance of the PrimitiveType metatype, named
	 * <tt>Integer</tt>.
	 *
	 * @return the <tt>Integer</tt> type (an instance of PrimitiveType)
	 * @since 7.0
	 */
	@NonNull PrimitiveType getIntegerType();

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getJavaType(@NonNull Object object);

	/**
	 * @since 7.0
	 */
	@NonNull JavaTypeManager getJavaTypeManager();

	/**
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getLibraryClass(@NonNull String className);

	/**
	 * @since 7.0
	 */
	@NonNull LambdaTypeManager getLambdaManager();

	/**
	 * @since 7.0
	 */
	@NonNull MapType getMapEntryType(org.eclipse.ocl.pivot.@NonNull Class entryClass);

	/**
	 * @since 7.0
	 */
	@NonNull MapType getMapType();

	/**
	 * @since 7.0
	 */
	@NonNull MapTypeManager getMapTypeManager();

	/**
	 * @since 7.0
	 */
	@NonNull MapType getMapType(@NonNull Type keyType, boolean keysAreNullFree, @NonNull Type valueType, boolean valuesAreNullFree);

	/**
	 * @since 7.0
	 */
	@NonNull MapType getMapType(@NonNull MapTypeArguments typeArguments);

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
	 * @since 7.0
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
	 * @since 7.0
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
	 * @since 7.0
	 */
	@NonNull InvalidType getOclInvalidType();

	/**
	 * Obtains the single instance of the LambdaType metatype, named
	 * <tt>OclLambda</tt>.
	 * @since 7.0
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

	/**
	 * Obtains the single instance of the OclType metatype, named
	 * <tt>OclTyp</tt>.
	 *
	 * @return the <tt>OclType</tt> type (an instance of Class)
	 * @since 7.0
	 */
	org.eclipse.ocl.pivot.@NonNull Class getOclTypeType();

	/**
	 * Obtains the single instance of the VoidType metatype, named
	 * <tt>OclVoid</tt>.
	 *
	 * @return the <tt>OclVoid</tt> type (an instance of VoidType)
	 * @since 7.0
	 */
	@NonNull VoidType getOclVoidType();

	/**
	 * Obtains the generic instance of the OrderedCollection metatype, named
	 * <tt>OrderedCollection(T)</tt>.
	 *
	 * @return the <tt>OrderedCollection(T)</tt> type (an instance of CollectionType)
	 * @since 7.0
	 */
	@NonNull CollectionType getOrderedCollectionType();

	/**
	 * Obtains the generic instance of the OrderedSetType metatype, named
	 * <tt>OrderedSet(T)</tt>.
	 *
	 * @return the <tt>OrderedSet(T)</tt> type (an instance of OrderedSetType)
	 * @since 7.0
	 */
	@NonNull OrderedSetType getOrderedSetType();

	/**
	 * Return the instance of the OrderedSet metatype whose elements are of elementType.
	 * @since 7.0
	 */
	@NonNull CollectionType getOrderedSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	/**
	 * Obtains the package containing the library types
	 */
	org.eclipse.ocl.pivot.@NonNull Package getPackage();

	/**
	 * @since 7.0
	 */
	@NonNull Type getPrimaryType(@NonNull Type asType);

	@Nullable Type getPrimitiveType(@NonNull PrimitiveTypeId typeId);

	/**
	 * Obtains the instance of the PrimitiveType metatype, named
	 * <tt>Real</tt>.
	 *
	 * @return the <tt>Real</tt> type (an instance of PrimitiveType)
	 * @since 7.0
	 */
	@NonNull PrimitiveType getRealType();

	/**
	 * Obtains the generic instance of the SequenceType metatype, named
	 * <tt>Sequence(T)</tt>.
	 *
	 * @return the <tt>Sequence(T)</tt> type (an instance of SequenceType)
	 * @since 7.0
	 */
	@NonNull SequenceType getSequenceType();

	/**
	 * Return the instance of the Sequence metatype whose elements are of elementType.
	 * @since 7.0
	 */
	@NonNull CollectionType getSequenceType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	/**
	 * Obtains the generic instance of the SetType metatype, named
	 * <tt>Set(T)</tt>.
	 *
	 * @return the <tt>Set(T)</tt> type (an instance of SetType)
	 * @since 7.0
	 */
	@NonNull SetType getSetType();

	/**
	 * Return the instance of the Set metatype whose elements are of elementType.
	 * @since 7.0
	 */
	@NonNull CollectionType getSetType(@NonNull Type elementType, boolean isNullFree, @Nullable IntegerValue lower, @Nullable UnlimitedNaturalValue upper);

	/**
	 * @since 7.0
	 */
	@NonNull Type getSpecializedType(@NonNull Type type, @Nullable TemplateParameterSubstitutions substitutions);

	/**
	 * Obtains the instance of the PrimitiveType metatype, named
	 * <tt>String</tt>.
	 *
	 * @return the <tt>String</tt> type (an instance of PrimitiveType)
	 * @since 7.0
	 */
	@NonNull PrimitiveType getStringType();

	/**
	 * Return the named tuple typeId with the defined parts (which need not be alphabetically ordered).
	 * @since 7.0
	 */
	@NonNull TupleType getTupleType(@NonNull List<@NonNull PartId> partList);

	/**
	 * @since 7.0
	 */
	@NonNull TupleType getTupleType(@NonNull TupleTypeId typeId);

	/**
	 * @since 7.0
	 */
	@NonNull TupleType getTupleType(@NonNull Collection<@NonNull ? extends TypedElement> parts, @Nullable TemplateParameterSubstitutions bindings);

	/**
	 * @since 7.0
	 */
	@NonNull TupleTypeManager getTupleTypeManager();

	/**
	 * Obtains the generic instance of the UniqueCollection metatype, named
	 * <tt>Set(T)</tt>.
	 *
	 * @return the <tt>Set(T)</tt> type (an instance of CollectionType)
	 * @since 7.0
	 */
	@NonNull CollectionType getUniqueCollectionType();

	/**
	 * Obtains the instance of the PrimitiveType metatype,
	 * named <tt>UnlimitedNatural</tt>.
	 *
	 * @return the <tt>UnlimitedNatural</tt> type (an instance of
	 *     PrimitiveType)
	 * @since 7.0
	 */
	@NonNull PrimitiveType getUnlimitedNaturalType();

	/**
	 * Return true if leftType is the same type as rightType within this standardLibrary.
	 * @since 7.0
	 */
	boolean isEqualTo(@NonNull Type leftType, @NonNull Type rightType);
} // StandardLibrary
