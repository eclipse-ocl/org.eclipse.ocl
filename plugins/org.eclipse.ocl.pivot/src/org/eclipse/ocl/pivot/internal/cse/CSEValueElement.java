/**
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.cse;

import org.eclipse.jdt.annotation.NonNull;

public class CSEValueElement extends AbstractCSEElement
{
	protected final @NonNull Object value;
//	protected final @NonNull List<@NonNull VariableExp> variableExps = new ArrayList<>();

	public CSEValueElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull Object value) {
		super(cseAnalysis);
		this.value = value;
	}

/*	public void addVariableExp(@NonNull VariableExp variableExp) {
		assert this.variable == PivotUtil.getReferredVariable(variableExp);
		assert !variableExps.contains(variableExp);
		variableExps.add(variableExp);
	} */

	@Override
	public void toString(@NonNull StringBuilder s, int lengthLimit) {
		s.append(value);
	//	s.append("[");
	//	s.append(mayBeNull ? "?" : "1");
	//	if (mayBeInvalid) {
	//		s.append("!");
	//	}
	//	s.append("]");
	}
}
