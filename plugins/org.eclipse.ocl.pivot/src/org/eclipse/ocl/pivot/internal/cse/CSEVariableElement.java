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
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * @since 1.15
 */
public class CSEVariableElement extends AbstractCSEElement<@NonNull VariableDeclaration, @NonNull VariableExp>
{
	public CSEVariableElement(@NonNull CommonSubExpressionAnalysis cseAnalysis, @NonNull VariableDeclaration variableDeclaration) {
		super(cseAnalysis, variableDeclaration);
	}

	public void addVariableExp(@NonNull VariableExp variableExp) {
		assert exemplar == PivotUtil.getReferredVariable(variableExp);
		addClient(variableExp);
	}
}
