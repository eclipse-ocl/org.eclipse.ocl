/*******************************************************************************
 * Copyright (c) 2010, 2017 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.values;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.ObjectValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.TupleValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.pivot.values.ValuesPackage;

/**
 * @generated NOT
 */
public abstract class ValueImpl extends ValueUtil implements Value
{
	static class EmptyIterator implements Iterator<Value>
	{
		@Override
		public boolean hasNext() {
			return false;
		}
		@Override
		public Value next() {
			throw new NoSuchElementException();
		}
		@Override
		public void remove() {
			throw new IllegalStateException();
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return ValuesPackage.Literals.VALUE;
	}

	protected ValueImpl() {}

	@Override
	public @NonNull CollectionValue asBagValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BAG_NAME, getTypeName());
	}

	@Override
	public @NonNull CollectionValue asCollectionValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.COLLECTION_NAME, getTypeName());
	}

	@Override
	public @NonNull Double asDouble() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, "Double", getTypeName());
	}

	@Override
	public @Nullable Object asEcoreObject(@NonNull IdResolver idResolver, @Nullable Class<?> instanceClass) {
		return asObject();
	}

	@Override
	public Element asElement() {
		return null;
	}

	@Override
	public @NonNull Integer asInteger() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.INTEGER_NAME, getTypeName());
	}

	@Override
	public @NonNull IntegerValue asIntegerValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.INTEGER_NAME, getTypeName());
	}

	@Override
	public @NonNull MapValue asMapValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.MAP_NAME, getTypeName());
	}

	@Override
	public @NonNull EObject asNavigableObject() {
		Object object = asObject();
		if (object instanceof EObject) {
			return (EObject) object;
		}
		else {
			throw new InvalidValueException(PivotMessages.TypedValueRequired, "Object", getTypeName());
		}
	}

	@Override
	public @NonNull ObjectValue asObjectValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, "Object", getTypeName());
	}

	@Override
	public @NonNull CollectionValue asOrderedCollectionValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.ORDERED_COLLECTION_NAME, getTypeName());
	}

	@Override
	public @NonNull CollectionValue asOrderedSetValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.ORDERED_SET_NAME, getTypeName());
	}

	@Override
	public @NonNull RealValue asRealValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.REAL_NAME, getTypeName());
	}

	@Override
	public @NonNull CollectionValue asSequenceValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.SEQUENCE_NAME, getTypeName());
	}

	@Override
	public @NonNull CollectionValue asSetValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.SET_NAME, getTypeName());
	}

	@Override
	public @NonNull TupleValue asTupleValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.TUPLE_NAME, getTypeName());
	}

	@Override
	public @NonNull CollectionValue asUniqueCollectionValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, "Unique Collection", getTypeName());
	}

	@Override
	public @Nullable Object asUnboxedObject(@NonNull IdResolver idResolver) {
		return asObject();
	}

	@Override
	public @NonNull UnlimitedNaturalValue asUnlimitedNaturalValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.UNLIMITED_NATURAL_NAME, getTypeName());
	}

	//	public @NonNull DomainType getActualType(@NonNull DomainStandardLibrary standardLibrary) {
	//		return getType(standardLibrary);
	//	}

	public @NonNull String getTypeName() {
		return getTypeId().getDisplayName();
	}

	@Override
	public boolean isInvalid() {
		return false;
	}

	@Override
	public boolean isUndefined() {
		return false;
	}

	@Override
	public @NonNull String toString() {
		return getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(this));
	}
}
