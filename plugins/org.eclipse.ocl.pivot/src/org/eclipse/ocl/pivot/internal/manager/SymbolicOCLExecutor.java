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
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment.EvaluationEnvironmentExtension;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.evaluation.BasicOCLExecutor;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.values.SimpleSymbolicConstraintImpl;
import org.eclipse.ocl.pivot.values.SymbolicExpressionValue;
import org.eclipse.ocl.pivot.values.SymbolicOperator;

/**
 * @since 1.15
 */
public class SymbolicOCLExecutor extends BasicOCLExecutor implements SymbolicExecutor, ExecutorInternal
{
	public SymbolicOCLExecutor( @NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		super(environmentFactory, modelManager);
	}

	@Override
	protected @NonNull SymbolicEvaluationEnvironment createNestedEvaluationEnvironment(@NonNull EvaluationEnvironmentExtension evaluationEnvironment,
			@NonNull NamedElement executableObject, @Nullable Object caller) {
		return new SymbolicEvaluationEnvironment((SymbolicEvaluationEnvironment)evaluationEnvironment, executableObject, caller);
	}

	@Override
	protected @NonNull SymbolicEvaluationEnvironment createRootEvaluationEnvironment(@NonNull NamedElement executableObject) {
		return new SymbolicEvaluationEnvironment(this, executableObject);
	}

	@Override
	public @NonNull SymbolicEvaluationEnvironment getEvaluationEnvironment() {
		return (SymbolicEvaluationEnvironment)super.getEvaluationEnvironment();
	}

	@Override
	public @NonNull SymbolicEvaluationEnvironment pushSymbolicEvaluationEnvironment(@NonNull SymbolicExpressionValue symbolicValue, @Nullable Object constantValue) {
		@NonNull OCLExpression expression = symbolicValue.getExpression();
		SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicEvaluationEnvironment nestedEvaluationEnvironment = createNestedEvaluationEnvironment(evaluationEnvironment, expression, (TypedElement)null);
		pushEvaluationEnvironment(nestedEvaluationEnvironment);
	//	if (symbolicValue instanceof SymbolicValue) {
			SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, SymbolicOperator.EQUALS, constantValue);
			symbolicValue.deduceFrom(this, symbolicConstraint);
	//	}
		return nestedEvaluationEnvironment;
	}
}