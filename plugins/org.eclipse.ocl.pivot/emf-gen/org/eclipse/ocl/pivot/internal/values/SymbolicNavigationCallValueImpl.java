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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.Value;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Symbolic Value</b></em>'.
 * @since 1.12
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SymbolicNavigationCallValueImpl extends SymbolicValueImpl {

	protected final @NonNull NavigationCallExp navigationCallExp;
	protected final @Nullable Object sourceValue;
//	protected final @NonNull Property property;

		/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param variable
	 * @generated NOT
	 */
	public SymbolicNavigationCallValueImpl(@NonNull NavigationCallExp navigationCallExp, @Nullable Object sourceValue) {//, @NonNull Property property) {
		this.navigationCallExp = navigationCallExp;
		this.sourceValue = sourceValue;
//		this.property = property;
	}

//	@Override
//	public @NonNull EObject asNavigableObject() {
//		return this;
//	}

	@Override
	public @NonNull TypeId getTypeId() {
		return navigationCallExp.getTypeId();
	}

	@Override
	public boolean mayBeNull() {
		return !navigationCallExp.isIsRequired();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		if (sourceValue instanceof Value){
			((Value)sourceValue).toString(s, lengthLimit);
		}
		else {
			s.append(String.valueOf(sourceValue));
		}
		s.append(".");
		s.append(PivotUtil.getReferredProperty(navigationCallExp).getName());
	}

} //SymbolicValueImpl
