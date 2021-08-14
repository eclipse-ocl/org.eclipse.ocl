/**
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.symbolic;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * @since 1.16
 */
public class SymbolicVariableValue extends AbstractLeafSymbolicValue {

	protected final @NonNull VariableDeclaration variable;

	public SymbolicVariableValue(@NonNull VariableDeclaration variable, boolean mayBeNull, boolean mayBeInvalid) { //, @NonNull SymbolicValue value) {
		super(PivotUtil.getName(variable), variable.getTypeId(), mayBeNull, mayBeInvalid, null);
		this.variable = variable;
	}

	public @NonNull VariableDeclaration getVariable() {
		return variable;
	}

	@Override
	public void toString(@NonNull StringBuilder s) {
	//	s.append("\"");
	//	s.append(variable.getName());
	//	s.append("\":");
		super.toString(s);
	}
}
