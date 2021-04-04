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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.values.SimpleSymbolicConstraint;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.15
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicVariableValueImpl extends SymbolicValueImpl { //implements EObject {

	protected final @NonNull VariableDeclaration variable;
//	protected final @NonNull SymbolicValue value;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param variable
	 * @param value
	 * @param object
	 * @generated NOT
	 */
	public SymbolicVariableValueImpl(@NonNull VariableDeclaration variable, boolean mayBeNull, boolean mayBeInvalid) { //, @NonNull SymbolicValue value) {
		super(variable.getTypeId(), mayBeNull, mayBeInvalid);
		this.variable = variable;
//		this.value = value;
	}

//	@Override
//	public @NonNull EObject asNavigableObject() {
//		return this;
//	}

	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SimpleSymbolicConstraint symbolicConstraint) {
		symbolicExecutor.getEvaluationEnvironment().addSymbolicResult(variable, null, symbolicConstraint);
	}

//	@Override
//	public @NonNull TypeId getTypeId2() {
//		return variable.getTypeId();
//	}

//	public @NonNull SymbolicValue getValue() {
//		return value;
//	}

//	public @NonNull VariableDeclaration getVariable() {
//		return variable;
//	}

//	@Override
//	public boolean mayBeNull() {
//		boolean isRequired = variable.isIsRequired();
//		boolean mayBeNull = value.mayBeNull();
//		assert !isRequired || !mayBeNull;
//		return mayBeNull;
//	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append("\"");
		s.append(variable.getName());
		s.append("\":");
		super.toString(s, lengthLimit);
	}

/*	@Override
	public EList<Adapter> eAdapters() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean eDeliver() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void eSetDeliver(boolean deliver) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void eNotify(Notification notification) {
		throw new UnsupportedOperationException();
	}

	@Override
	public EClass eClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Resource eResource() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EObject eContainer() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EStructuralFeature eContainingFeature() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EReference eContainmentFeature() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EList<EObject> eContents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public TreeIterator<@NonNull EObject> eAllContents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean eIsProxy() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EList<EObject> eCrossReferences() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object eGet(EStructuralFeature feature) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object eGet(EStructuralFeature feature, boolean resolve) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void eSet(EStructuralFeature feature, Object newValue) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean eIsSet(EStructuralFeature feature) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void eUnset(EStructuralFeature feature) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object eInvoke(EOperation operation, EList<?> arguments) throws InvocationTargetException {
		throw new UnsupportedOperationException();
	} */
} //SymbolicValueImpl
