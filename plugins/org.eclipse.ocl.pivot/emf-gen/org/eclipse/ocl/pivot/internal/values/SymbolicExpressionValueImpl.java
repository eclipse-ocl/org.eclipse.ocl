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
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SymbolicExpressionValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.16
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicExpressionValueImpl extends SymbolicValueImpl implements SymbolicExpressionValue {
	protected final @NonNull TypedElement expression;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param variable
	 * @generated NOT
	 */
	public SymbolicExpressionValueImpl(@NonNull TypedElement expression, boolean mayBeNull, boolean mayBeInvalid) {
		// FIXME getBehavioralType needed by test_umlValidation_Bug467192
		super(ClassUtil.nonNullState(PivotUtil.getBehavioralType(expression)).getTypeId(), mayBeNull, mayBeInvalid);
		this.expression = expression;
	}

	@Override
	public @NonNull TypedElement getExpression() {
		return expression;
	}
} //SymbolicValueImpl
