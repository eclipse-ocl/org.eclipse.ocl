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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.OclVoidTypeId;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.NullValue;
import org.eclipse.ocl.pivot.values.OCLValue;
import org.eclipse.ocl.pivot.values.RealValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.12
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicUnknownValueImpl extends UndefinedValueImpl implements SymbolicValue {
	private static final long serialVersionUID = 1L;

	protected final @NonNull TypeId typeId;
	protected final boolean mayBeNull;
	protected final boolean mayBeInvalid;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param variable
	 * @generated NOT
	 */
	public SymbolicUnknownValueImpl(@NonNull TypeId typeId, boolean mayBeNull, boolean mayBeInvalid) {
		super("dummy-message");
		this.typeId = typeId;
		this.mayBeNull = mayBeNull;
		this.mayBeInvalid = mayBeInvalid;
	}

//	@Override
//	public @NonNull EObject asNavigableObject() {
//		return this;
//	}

	@Override
	public @NonNull NullValue divideReal(@NonNull RealValue right) {
		if (right.asDouble() == 1.0) {
			return this;
		}
	//	double x = 1/0 / 0.0;
		if (right.asDouble() == 0.0) {
			throw new InvalidValueException(new ArithmeticException("/ by zero"));
		}
		return this;
	}

	@Override
	public @NonNull CollectionTypeId getCollectionTypeId() {
		throw new InvalidValueException(PivotMessages.ConvertibleValueRequired, "Invalid");
	}

	@Override
	public @NonNull RealValue asRealValue() {
		if (typeId == TypeId.REAL) {
			return this;
		}
		return super.asRealValue();
	}

	@Override
	public @NonNull TupleTypeId getTupleTypeId() {
		throw new InvalidValueException(PivotMessages.ConvertibleValueRequired, "Invalid");
	}

	@Override
	public @NonNull OclVoidTypeId getTypeId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull TypeId getTypeId2() {
		return typeId;
	}

	@Override
	public boolean mayBeInvalid() {
		return mayBeInvalid;
	}

	@Override
	public boolean mayBeNull() {
		return mayBeNull;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(typeId);
		s.append("-");
		s.append(mayBeNull);
		s.append("-");
		s.append(mayBeInvalid);
	}

	@Override
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
	}
} //SymbolicValueImpl
