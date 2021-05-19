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
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.OCLExpression;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.15
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicCollectionValueImpl extends SymbolicExpressionValueImpl {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SymbolicCollectionValueImpl(@NonNull OCLExpression expression, boolean mayBeNull, boolean mayBeInvalid) {
		super(expression, mayBeNull, mayBeInvalid);
//		this.operation = operation;
//		this.sourceValue = sourceValue;
//		this.argumentValue = argumentValue;
//		assert operation == operationCallExp.getReferredOperation().getImplementation();
		assert expression.getType() instanceof CollectionType;
	}

//	@Override
//	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SimpleSymbolicConstraint symbolicConstraint) {
	//	if (operation instanceof InvertibleLibraryOperation) {
	//		((InvertibleLibraryOperation)operation).deduceFrom(symbolicExecutor, this, symbolicConstraint);
	//	}
//	}

	@Override
	public boolean isCollection() {
		return true;
	}

/*	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(operation.getClass().getSimpleName());
		s.append("(");
		s.append(sourceValue);
		s.append(",");
		s.append(argumentValue);
		s.append(")");
		s.append(":");
		super.toString(s, lengthLimit);
	} */
}
