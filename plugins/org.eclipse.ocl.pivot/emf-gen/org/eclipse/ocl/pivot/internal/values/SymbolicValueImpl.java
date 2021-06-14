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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicMapContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicStatus;
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
public abstract class SymbolicValueImpl extends AbstractSymbolicValue {
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
	private @Nullable SymbolicContent content = null;

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

	@Override
	public @Nullable SymbolicStatus basicGetBooleanStatus() {
		if (typeId == TypeId.BOOLEAN) {
			return SymbolicStatus.UNDECIDED;
		}
		return null;
	//	throw new IllegalStateException("No Boolean source configured");
	}

	public @Nullable SymbolicContent basicGetContent() {
		return content;
	}

	@Override
	public @Nullable SymbolicStatus basicGetZeroStatus() {
		if ((typeId == TypeId.REAL) || (typeId == TypeId.INTEGER) || (typeId == TypeId.UNLIMITED_NATURAL)) {	// FIXME Behavioral
			return SymbolicStatus.UNDECIDED;
		}
		return null;
	}

	@Override
	public @NonNull SymbolicValue getBaseValue() {
		return this;
	}

	@Override
	public @NonNull SymbolicCollectionContent getCollectionContent() {
		assert typeId instanceof CollectionTypeId;
		SymbolicContent content2 = content;
		if (content2 == null) {
			content = content2 = new SymbolicCollectionContent((CollectionTypeId)typeId);
		}
		return (SymbolicCollectionContent)content2;
	}

//	@Override
//	public @NonNull CollectionTypeId getCollectionTypeId() {
//		throw new InvalidValueException(PivotMessages.ConvertibleValueRequired, "Invalid");
//	}

	@Override
	public @NonNull SymbolicContent getContent() {
		if (typeId instanceof CollectionTypeId) {
			return getCollectionContent();
		}
		if (typeId instanceof MapTypeId) {
			return getMapContent();
		}
		throw new IllegalStateException();
	}

	@Override
	public @NonNull SymbolicStatus getDeadStatus() {
		return SymbolicStatus.UNSATISFIED;
	}

	@Override
	public @NonNull SymbolicStatus getInvalidStatus() {
		return mayBeInvalid ? SymbolicStatus.UNDECIDED : SymbolicStatus.UNSATISFIED;
	}

	@Override
	public @NonNull SymbolicMapContent getMapContent() {
		assert typeId instanceof MapTypeId;
		SymbolicContent content2 = content;
		if (content2 == null) {
			content = content2 = new SymbolicMapContent((MapTypeId)typeId);
		}
		return (SymbolicMapContent)content2;
	}

	@Override
	public @NonNull SymbolicStatus getNullStatus() {
		return mayBeNull ? SymbolicStatus.UNDECIDED : SymbolicStatus.UNSATISFIED;
	}

//	@Override
//	public @NonNull TupleTypeId getTupleTypeId() {
//		throw new InvalidValueException(PivotMessages.ConvertibleValueRequired, "Invalid");
//	}

	@Override
	public @NonNull TypeId getTypeId() {
		return typeId;
	}

	@Override
	public boolean isCollection() {
		return typeId instanceof CollectionTypeId;
	}

//	@Override
//	public boolean isEmpty() {
//		SymbolicContent content = basicGetContent();
//		return (content != null) && content.isEmpty();
//	}

//	@Override
//	public boolean isFalse() {
//		return false;
//	}

	@Override
	public boolean isKnown() {
		return false;
	}

	@Override
	public boolean isMap() {
		return typeId instanceof MapTypeId;
	}

	@Override
	public boolean isNullFree() {
		return false;			// XXX
	}

/*	@Override
	public boolean isSmallerThan(@NonNull SymbolicValue minSizeValue) {
		return false;
	} */

//	@Override
//	public boolean isTrue() {
//		return false;
//	}

//	@Override
//	public boolean mayBeEmpty() {
//		SymbolicContent content = basicGetContent();
//		return (content == null) || content.mayBeEmpty();
//	}

	@Override
	public boolean mayBeFalse() {
		return true;
	}

/*	@Override
	public boolean mayBeSmallerThan(@NonNull SymbolicValue minSizeValue) {
		if (!minSizeValue.isKnown()) {
			return true;
		}
		Object value = ((SymbolicKnownValue)minSizeValue).getValue();
		if (value instanceof Number) {
			return ((Number)value).intValue() > 0;
		}
		if (value instanceof IntegerValue) {
			return ((IntegerValue)value).intValue() > 0;
		}
		return true;
	} */

	@Override
	public boolean mayBeTrue() {
		return true;
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
