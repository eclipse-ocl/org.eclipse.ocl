/*******************************************************************************
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.ocl.pivot.InvalidLiteralExp;
import org.eclipse.ocl.pivot.LiteralExp;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.BagValue;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.ObjectValue;
import org.eclipse.ocl.pivot.values.OrderedCollectionValue;
import org.eclipse.ocl.pivot.values.OrderedSetValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SetValue;
import org.eclipse.ocl.pivot.values.TupleValue;
import org.eclipse.ocl.pivot.values.UniqueCollectionValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;
import org.eclipse.ocl.pivot.values.Value;
import org.eclipse.ocl.pivot.values.ValuesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated not
 */
public abstract class ValueImpl extends ValueUtil implements Value {
	/**
	 * @generated NOT
	 */
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
	 * The number of structural features of the '<em>Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_FEATURE_COUNT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected EClass eStaticClass() {
		return ValuesPackage.Literals.VALUE;
	}

	/**
	 * @generated NOT
	 */
	protected ValueImpl() {}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull BagValue asBagValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.BAG_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull CollectionValue asCollectionValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.COLLECTION_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull Double asDouble() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, "Double", getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @Nullable Object asEcoreObject(@NonNull IdResolver idResolver, @Nullable Class<?> instanceClass) {
		return asObject();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public Element asElement() {
		return null;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull Integer asInteger() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.INTEGER_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull IntegerValue asIntegerValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.INTEGER_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull MapValue asMapValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.MAP_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
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

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull ObjectValue asObjectValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, "Object", getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull OrderedCollectionValue asOrderedCollectionValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.ORDERED_COLLECTION_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull OrderedSetValue asOrderedSetValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.ORDERED_SET_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull RealValue asRealValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.REAL_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull SequenceValue asSequenceValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.SEQUENCE_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull SetValue asSetValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.SET_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull TupleValue asTupleValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.TUPLE_NAME, getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull UniqueCollectionValue asUniqueCollectionValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, "Unique Collection", getTypeName());
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @Nullable Object asUnboxedObject(@NonNull IdResolver idResolver) {
		return asObject();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public @NonNull UnlimitedNaturalValue asUnlimitedNaturalValue() {
		throw new InvalidValueException(PivotMessages.TypedValueRequired, TypeId.UNLIMITED_NATURAL_NAME, getTypeName());
	}

	@Override
	public @NonNull LiteralExp createLiteralExp() {
		InvalidLiteralExp literalExp = PivotFactory.eINSTANCE.createInvalidLiteralExp();
		literalExp.setName("Unsupported createLiteralExp() for " + getClass().getSimpleName());
		return literalExp;
	}

	/**
	 * @generated NOT
	 */
	public @NonNull String getTypeName() {
		return getTypeId().getDisplayName();
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean isInvalid() {
		return false;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public boolean isUndefined() {
		return false;
	}

	/**
	 * @generated NOT
	 */
	@Override
	public void toString(@NonNull StringBuilder s, int sizeLimit) {		// FIXME change to abstract once a major version permits the API change
		s.append(getClass().getSimpleName());
	}
} //ValueImpl
