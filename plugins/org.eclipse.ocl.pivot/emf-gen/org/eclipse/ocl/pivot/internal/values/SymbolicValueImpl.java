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
package org.eclipse.ocl.pivot.internal.values;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.LibraryIterationOrOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.values.SymbolicValue;
import org.eclipse.ocl.pivot.values.ValuesPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.15
 * <!-- end-user-doc -->
 *
 * @generated NOT
 */
public abstract class SymbolicValueImpl extends ValueImpl implements SymbolicValue {
	/**
	 * The number of structural features of the '<em>Symbolic Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SYMBOLIC_VALUE_FEATURE_COUNT = ValueImpl.VALUE_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SymbolicValueImpl() {
		typeId = TypeId.OCL_INVALID;
		this.mayBeNull = false;
		this.mayBeInvalid = false;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValuesPackage.Literals.SYMBOLIC_VALUE;
	}

	protected final @NonNull TypeId typeId;
	protected final boolean mayBeNull;
	protected final boolean mayBeInvalid;

	private /*@LazyNonNull*/ Map<@NonNull LibraryIterationOrOperation, @NonNull Map<@NonNull List<@NonNull SymbolicValue>, @NonNull SymbolicValue>> callable2arguments2symbolicValue = null;
	private /*@LazyNonNull*/ Map<@NonNull LibraryProperty, @NonNull SymbolicValue> property2symbolicValue = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param variable
	 * @generated NOT
	 */
	public SymbolicValueImpl(@NonNull TypeId typeId, boolean mayBeNull, boolean mayBeInvalid) {
		this.typeId = typeId;
		this.mayBeNull = mayBeNull;
		this.mayBeInvalid = mayBeInvalid;
	}

//	@Override
//	public @NonNull EObject asNavigableObject() {
//		return this;
//	}

	@Override
	public @NonNull Object asObject() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @Nullable SymbolicValue basicGetChildSymbolicValue(@NonNull LibraryIterationOrOperation libraryCallable, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		Map<@NonNull LibraryIterationOrOperation, @NonNull Map<@NonNull List<@NonNull SymbolicValue>, @NonNull SymbolicValue>> callable2arguments2symbolicValue2 = callable2arguments2symbolicValue;
		if (callable2arguments2symbolicValue2 == null) {
			return null;
		}
		Map<@NonNull List<@NonNull SymbolicValue>, @NonNull SymbolicValue> arguments2symbolicValue = callable2arguments2symbolicValue.get(libraryCallable);
		if (arguments2symbolicValue == null) {
			return null;
		}
		return arguments2symbolicValue.get(argumentSymbolicValues);
	}

	@Override
	public @Nullable SymbolicValue basicGetChildSymbolicValue(@NonNull LibraryProperty libraryProperty) {
		Map<@NonNull LibraryProperty, @NonNull SymbolicValue> property2symbolicValue2 = property2symbolicValue;
		if (property2symbolicValue2 == null) {
			return null;
		}
		return property2symbolicValue2.get(libraryProperty);
	}

//	@Override
//	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SimpleSymbolicConstraint symbolicConstraint) {
//	}

/*	@Override
	public @NonNull RealValue divideReal(@NonNull RealValue right) {
		if (right.asDouble() == 1.0) {
			return this;
		}
	//	double x = 1/0 / 0.0;
		if (right.asDouble() == 0.0) {
			throw new InvalidValueException(new ArithmeticException("/ by zero"));
		}
		return this;
	} */

//	@Override
//	public @NonNull CollectionTypeId getCollectionTypeId() {
//		throw new InvalidValueException(PivotMessages.ConvertibleValueRequired, "Invalid");
//	}

//	@Override
//	public @NonNull TupleTypeId getTupleTypeId() {
//		throw new InvalidValueException(PivotMessages.ConvertibleValueRequired, "Invalid");
//	}

	@Override
	public @NonNull TypeId getTypeId() {
		return typeId;
	}

	@Override
	public boolean isFalse() {
		return false;
	}

	@Override
	public boolean isInvalid() {
		return false;
	}

	@Override
	public boolean isKnown() {
		return false;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public boolean isTrue() {
		return false;
	}

	@Override
	public boolean isZero() {
		return false;
	}

	@Override
	public boolean mayBeInvalid() {
		return mayBeInvalid;
	}

	@Override
	public boolean mayBeInvalidOrNull() {
		return mayBeInvalid || mayBeNull;
	}

	@Override
	public boolean mayBeNull() {
		return mayBeNull;
	}

	@Override
	public boolean mayBeZero() {
		return (getTypeId() == TypeId.REAL) || (getTypeId() == TypeId.INTEGER) || (getTypeId() == TypeId.UNLIMITED_NATURAL);	// FIXME Behavioral
	}

	@Override
	public @Nullable SymbolicValue putChildSymbolicValue(@NonNull LibraryIterationOrOperation libraryCallable, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues, @NonNull SymbolicValue childSymbolicValue) {
		Map<@NonNull LibraryIterationOrOperation, @NonNull Map<@NonNull List<@NonNull SymbolicValue>, @NonNull SymbolicValue>> callable2arguments2symbolicValue2 = callable2arguments2symbolicValue;
		if (callable2arguments2symbolicValue2 == null) {
			callable2arguments2symbolicValue2 = callable2arguments2symbolicValue = new HashMap<>();
		}
		Map<@NonNull List<@NonNull SymbolicValue>, @NonNull SymbolicValue> arguments2symbolicValue = callable2arguments2symbolicValue.get(libraryCallable);
		if (arguments2symbolicValue == null) {
			arguments2symbolicValue = new HashMap<>();
			callable2arguments2symbolicValue.put(libraryCallable, arguments2symbolicValue);
		}
		SymbolicValue old = arguments2symbolicValue.put(argumentSymbolicValues, childSymbolicValue);
		assert old == null;
		return old;
	}

	@Override
	public @Nullable SymbolicValue putChildSymbolicValue(@NonNull LibraryProperty libraryProperty, @NonNull SymbolicValue childSymbolicValue) {
		Map<@NonNull LibraryProperty, @NonNull SymbolicValue> property2symbolicValue2 = property2symbolicValue;
		if (property2symbolicValue2 == null) {
			property2symbolicValue2 = property2symbolicValue = new HashMap<>();
		}
		SymbolicValue old = property2symbolicValue2.put(libraryProperty, childSymbolicValue);
		assert old == null;
		return old;
	}

	@Override
	public @NonNull SymbolicValue setIsNullFree() {
		throw new UnsupportedOperationException();			// XXX
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(typeId);
		s.append("[");
		s.append(mayBeNull ? "?" : "1");
		if (mayBeInvalid) {
			s.append("!");
		}
		s.append("]");
	}

/*	@Override
	public Element asElement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object asUnboxedObject(@NonNull IdResolver idResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvalid() {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
//	public @NonNull OclVoidTypeId getTypeId() {
		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public int intValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean oclEquals(@NonNull OCLValue thatValue) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int oclHashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public @Nullable List<?> asEcoreObject(@NonNull IdResolver idResolver,
			@Nullable Class<?> instanceClass) {
		// TODO Auto-generated method stub
		return null;
	} */
} //SymbolicValueImpl
