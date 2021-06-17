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
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.values.SymbolicOperationCallValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.16
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicOperationCallValueImpl extends SymbolicExpressionValueImpl implements SymbolicOperationCallValue {
	protected final @NonNull LibraryOperation operation;
	protected final @NonNull List<@NonNull SymbolicValue> boxedSourceAndArgumentValues;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SymbolicOperationCallValueImpl(@NonNull OperationCallExp operationCallExp, boolean mayBeNull, boolean mayBeInvalid,
			@NonNull LibraryOperation operation, @NonNull List<@NonNull SymbolicValue> boxedSourceAndArgumentValues) {
		super(operationCallExp, mayBeNull, mayBeInvalid);
		this.operation = operation;
		this.boxedSourceAndArgumentValues = boxedSourceAndArgumentValues;
	//	assert operation == operationCallExp.getReferredOperation().getImplementation();		// XXX lazy init may not have happened
		assert boxedSourceAndArgumentValues.size() == operationCallExp.getReferredOperation().getOwnedParameters().size()+1;
	}

/*	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SimpleSymbolicConstraint symbolicConstraint) {
		operation.deduceFrom(symbolicExecutor, this, symbolicConstraint);
		SymbolicEvaluationEnvironment symbolicEvaluationEnvironment = symbolicExecutor.getEvaluationEnvironment();
		List<@Nullable Object> sourceAndArgumentValues = Lists.newArrayList(boxedSourceAndArgumentValues);
		Operation operation = PivotUtil.getReferredOperation((OperationCallExp)expression);
		symbolicEvaluationEnvironment.addSymbolicResult(operation, sourceAndArgumentValues, symbolicConstraint);
	} */

	@Override
	public @NonNull List<@Nullable Object> getBoxedSourceAndArgumentValues() {
	//	return boxedSourceAndArgumentValues;
		throw new UnsupportedOperationException();		// XXX
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(boxedSourceAndArgumentValues.get(0));
		s.append(".");
		s.append(operation.getClass().getSimpleName());
		s.append("(");
		for (int i = 1; i < boxedSourceAndArgumentValues.size(); i++) {
			Object argumentValue = boxedSourceAndArgumentValues.get(i);
			if (i > 1) {
				s.append(",");
			}
			s.append(argumentValue);
		}
		s.append(")");
		s.append(":");
		super.toString(s, lengthLimit);
	}
}
