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
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.PrimitiveTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.manager.CollectionTypeManager;
import org.eclipse.ocl.pivot.internal.manager.MapTypeManager;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Standard Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.StandardLibrary#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getStandardLibrary()
 * @generated
 */
public interface StandardLibrary extends Element
{
	/**
	 * Returns the value of the '<em><b>Owning Complete Environment</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.pivot.CompleteEnvironment#getOwnedStandardLibrary <em>Owned Standard Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Complete Environment</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Complete Environment</em>' container reference.
	 * @see #setOwningCompleteEnvironment(CompleteEnvironment)
	 * @see org.eclipse.ocl.pivot.PivotPackage#getStandardLibrary_OwningCompleteEnvironment()
	 * @see org.eclipse.ocl.pivot.CompleteEnvironment#getOwnedStandardLibrary
	 * @generated
	 */
	CompleteEnvironment getOwningCompleteEnvironment();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.pivot.StandardLibrary#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Complete Environment</em>' container reference.
	 * @see #getOwningCompleteEnvironment()
	 * @generated
	 */
	void setOwningCompleteEnvironment(CompleteEnvironment value);

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
	 * Obtains the single instance of the EnumerationType metatype, named
	 * <tt>Enumeration</tt>.
	 *
	 * @return the <tt>Enumeration</tt> type (an instance of Enumeration)
	 */
	org.eclipse.ocl.pivot.@NonNull Class getEnumerationType();

	/**
	 * Return the Inheritance dispatch table for a given type.
	 */
	@NonNull CompleteInheritance getInheritance(org.eclipse.ocl.pivot.@NonNull Class type);

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

	/**
	 * Obtains the single instance of the VoidType metatype, named
	 * <tt>OclVoid</tt>.
	 *
	 * @return the <tt>OclVoid</tt> type (an instance of VoidType)
	 * @since 7.0
	 */
	@NonNull VoidType getOclVoidType();

	Element getOperationTemplateParameter(@NonNull Operation anOperation, int index);

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

	default @Nullable Type getPrimitiveType(@NonNull PrimitiveTypeId typeId) {
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

	/**
	 * Obtains the instance of the PrimitiveType metatype, named
	 * <tt>Real</tt>.
	 *
	 * @return the <tt>Real</tt> type (an instance of PrimitiveType)
	 * @since 7.0
	 */
	@NonNull PrimitiveType getRealType();

	org.eclipse.ocl.pivot.Package getRootPackage(@NonNull String name);

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
	 * @since 7.0
	 */
	@NonNull TupleType getTupleType(@NonNull String typeName, @NonNull Collection<@NonNull ? extends TypedElement> parts,
			@Nullable TemplateParameterSubstitutions bindings);

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
} // StandardLibrary
