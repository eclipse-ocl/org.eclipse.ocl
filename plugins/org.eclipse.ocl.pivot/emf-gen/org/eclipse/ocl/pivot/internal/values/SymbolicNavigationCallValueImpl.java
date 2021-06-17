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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.16
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicNavigationCallValueImpl extends SymbolicExpressionValueImpl {
	protected final @NonNull SymbolicValue sourceValue;
	protected final @NonNull Property property;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public SymbolicNavigationCallValueImpl(@NonNull NavigationCallExp navigationCallExp, boolean mayBeNull, boolean mayBeInvalid, @NonNull SymbolicValue sourceValue) {
		super(navigationCallExp, mayBeNull, mayBeInvalid);
		this.sourceValue = sourceValue;
		this.property = PivotUtil.getReferredProperty(navigationCallExp);
	}

/*	@Override
	public void deduceFrom(@NonNull SymbolicExecutor symbolicExecutor, @NonNull SimpleSymbolicConstraint symbolicConstraint) {
		SymbolicEvaluationEnvironment symbolicEvaluationEnvironment = symbolicExecutor.getEvaluationEnvironment();
		List<@Nullable Object> sourceAndArgumentValues = Lists.newArrayList(sourceValue);
		symbolicEvaluationEnvironment.addSymbolicResult(property, sourceAndArgumentValues, symbolicConstraint);
	} */

	public @Nullable Object getSourceValue() {
		return sourceValue;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(sourceValue);
		s.append(".");
		s.append(property.getName());
		s.append(":");
		super.toString(s, lengthLimit);
	}
}
