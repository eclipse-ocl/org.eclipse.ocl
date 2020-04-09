/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.manager;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.VariableDeclaration;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.values.SymbolicConstraint;

/**
 * @since 1.12
 */
public interface SymbolicExecutor extends Executor
{
	void add(@NonNull VariableDeclaration variable, @NonNull SymbolicConstraint symbolicConstraint);
	@NonNull EvaluationEnvironment pushSymbolicEvaluationEnvironment(@NonNull NamedElement executableObject, @Nullable Object symbolicValue, @Nullable Object constantValue);
}