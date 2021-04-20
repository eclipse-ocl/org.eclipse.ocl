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
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment.EvaluationEnvironmentExtension;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.evaluation.BasicOCLExecutor;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicHypothesisEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.values.SimpleSymbolicConstraintImpl;
import org.eclipse.ocl.pivot.values.SymbolicExpressionValue;
import org.eclipse.ocl.pivot.values.SymbolicOperator;
import org.eclipse.ocl.pivot.values.SymbolicUnknownValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;
import org.eclipse.ocl.pivot.values.SymbolicVariableValue;

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

//	protected @NonNullSymbolicEvaluationEnvironment createNestedEvaluationEnvironment(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment) {
//		return new SymbolicEvaluationEnvironment((SymbolicEvaluationEnvironment)evaluationEnvironment);
//	}

	@Override
	protected @NonNull SymbolicEvaluationEnvironment createRootEvaluationEnvironment(@NonNull NamedElement executableObject) {
		return new SymbolicEvaluationEnvironment(this, executableObject);
	}

	@Override
	public @NonNull SymbolicEvaluationEnvironment getEvaluationEnvironment() {
		return (SymbolicEvaluationEnvironment)super.getEvaluationEnvironment();
	}

	@Override
	public void popSymbolicHypothesis() {
		popEvaluationEnvironment();
	}

	@Override
	public @NonNull SymbolicHypothesisEvaluationEnvironment pushSymbolicHypothesis(@NonNull OCLExpression caller) {
		SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicHypothesisEvaluationEnvironment nestedEvaluationEnvironment = new SymbolicHypothesisEvaluationEnvironment(evaluationEnvironment, caller);
		pushEvaluationEnvironment(nestedEvaluationEnvironment);
		//	nestedEvaluationEnvironment.add(symbolicValue, constantValue);
		//	SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, SymbolicOperator.EQUALS, constantValue);
		//	symbolicValue.deduceFrom(this, symbolicConstraint);
		return nestedEvaluationEnvironment;
	}

	@Override
	public @NonNull SymbolicEvaluationEnvironment pushSymbolicEvaluationEnvironment(@NonNull SymbolicValue symbolicValue, @Nullable Object constantValue, @NonNull OCLExpression caller) {
		SymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicEvaluationEnvironment nestedEvaluationEnvironment;
		if (symbolicValue instanceof SymbolicUnknownValue) {
			nestedEvaluationEnvironment = createNestedEvaluationEnvironment(evaluationEnvironment, caller, (Object)caller);		// XXX execuatbleObject??
			pushEvaluationEnvironment(nestedEvaluationEnvironment);
		//	nestedEvaluationEnvironment.add(symbolicValue, constantValue);
			SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, SymbolicOperator.EQUALS, constantValue);
			symbolicValue.deduceFrom(this, symbolicConstraint);
		}
		else if (symbolicValue instanceof SymbolicVariableValue) {
			nestedEvaluationEnvironment = createNestedEvaluationEnvironment(evaluationEnvironment, caller, (Object)caller);	// XXX execuatbleObject??
			pushEvaluationEnvironment(nestedEvaluationEnvironment);
			nestedEvaluationEnvironment.add(((SymbolicVariableValue)symbolicValue).getVariable(), constantValue);
		}
		else if (symbolicValue instanceof SymbolicExpressionValue) {
			@NonNull OCLExpression expression = ((SymbolicExpressionValue)symbolicValue).getExpression();
			nestedEvaluationEnvironment = createNestedEvaluationEnvironment(evaluationEnvironment, expression, (Object)caller);
			pushEvaluationEnvironment(nestedEvaluationEnvironment);
			SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, SymbolicOperator.EQUALS, constantValue);
			symbolicValue.deduceFrom(this, symbolicConstraint);
		}
	/*	else if (symbolicValue instanceof SymbolicConstraint) {
			@NonNull OCLExpression expression = ((SymbolicConstraint)symbolicValue).getExpression();
			nestedEvaluationEnvironment = createNestedEvaluationEnvironment(evaluationEnvironment, expression, (Object)caller);
			pushEvaluationEnvironment(nestedEvaluationEnvironment);
			SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, SymbolicOperator.EQUALS, constantValue);
			symbolicValue.deduceFrom(this, symbolicConstraint);
		} */
		else {
			throw new IllegalStateException();
		}
		return nestedEvaluationEnvironment;
	}
}