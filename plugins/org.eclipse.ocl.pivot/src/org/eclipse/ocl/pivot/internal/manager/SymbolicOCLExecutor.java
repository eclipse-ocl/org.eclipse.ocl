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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.evaluation.AbstractSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.BasicOCLExecutor;
import org.eclipse.ocl.pivot.internal.evaluation.BasicSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.ConstrainedSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.internal.evaluation.HypothesizedSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicAnalysis;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.15
 */
public class SymbolicOCLExecutor extends BasicOCLExecutor implements SymbolicExecutor, ExecutorInternal
{
	private @Nullable AbstractSymbolicEvaluationEnvironment constrainedSymbolicEvaluationEnvironment = null;
	private @Nullable List<@NonNull HypothesizedSymbolicEvaluationEnvironment> hypothesizedEvaluationEnvironments = null;

	/**
	 * The expressions for which contradicting a hypothesized value allows a more precise re-evaluation.
	 */
	private @Nullable Map<@NonNull TypedElement, @NonNull SymbolicValue> expression2hypothesizedValue = null;

	public SymbolicOCLExecutor(@NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		super(environmentFactory, modelManager);
	}

	@Override
	public void addHypothesis(@NonNull TypedElement expression, @NonNull SymbolicValue symbolicValue, @NonNull SymbolicValue hypothesizedValue) {
		assert symbolicValue == getEvaluationEnvironment().basicGetSymbolicValue(expression);
		Map<@NonNull TypedElement, @NonNull SymbolicValue> expression2hypothesizedValue2 = expression2hypothesizedValue;
		if (expression2hypothesizedValue2 == null) {
			expression2hypothesizedValue = expression2hypothesizedValue2 = new HashMap<>();
		}
		SymbolicValue old = expression2hypothesizedValue2.put(expression, hypothesizedValue);
		assert old == null;
	}

	@Override
	public @NonNull HypothesizedSymbolicEvaluationEnvironment createHypothesizedSymbolicEvaluationEnvironment(@NonNull CSEElement cseElement) {
		AbstractSymbolicEvaluationEnvironment symbolicEvaluationEnvironment = getEvaluationEnvironment();
	//	ConstrainedSymbolicEvaluationEnvironment constrainedEvaluationEnvironment2 = constrainedSymbolicEvaluationEnvironment;
	//	assert constrainedEvaluationEnvironment2 != null;
		HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment = new HypothesizedSymbolicEvaluationEnvironment(symbolicEvaluationEnvironment, cseElement);
	//	pushEvaluationEnvironment(nestedEvaluationEnvironment);
		//	nestedEvaluationEnvironment.add(symbolicValue, constantValue);
		//	SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, SymbolicOperator.EQUALS, constantValue);
		//	symbolicValue.deduceFrom(this, symbolicConstraint);
		List<@NonNull HypothesizedSymbolicEvaluationEnvironment> hypothesizedEvaluationEnvironments2 = hypothesizedEvaluationEnvironments;
		if (hypothesizedEvaluationEnvironments2 == null) {
			hypothesizedEvaluationEnvironments = hypothesizedEvaluationEnvironments2 = new ArrayList<>();
		}
		hypothesizedEvaluationEnvironments2.add(hypothesizedEvaluationEnvironment);
		return hypothesizedEvaluationEnvironment;
	}

//	@Override
//	protected @NonNull SymbolicEvaluationEnvironment createNestedEvaluationEnvironment(@NonNull EvaluationEnvironmentExtension evaluationEnvironment,
//			@NonNull NamedElement executableObject, @Nullable Object caller) {
//		return new SymbolicEvaluationEnvironment((SymbolicEvaluationEnvironment)evaluationEnvironment, executableObject, caller);
//	}

	protected @NonNull AbstractSymbolicEvaluationEnvironment createNestedEvaluationEnvironment(@NonNull AbstractSymbolicEvaluationEnvironment evaluationEnvironment, @NonNull CSEElement cseElement) {
		return new BasicSymbolicEvaluationEnvironment(evaluationEnvironment, (TypedElement)cseElement.getElement());
	}

	@Override
	protected @NonNull AbstractSymbolicEvaluationEnvironment createRootEvaluationEnvironment(@NonNull NamedElement executableObject) {
		return new BasicSymbolicEvaluationEnvironment(this, executableObject);
	}

	@Override
	public @NonNull AbstractSymbolicEvaluationEnvironment getEvaluationEnvironment() {
		return (AbstractSymbolicEvaluationEnvironment)super.getEvaluationEnvironment();
	}

	public @Nullable List<@NonNull HypothesizedSymbolicEvaluationEnvironment> getHypothesizedEvaluationEnvironments() {
		return hypothesizedEvaluationEnvironments;
	}

	@Override
	public void popConstrainedSymbolicEvaluationEnvironment() {
		AbstractSymbolicEvaluationEnvironment constrainedSymbolicEvaluationEnvironment2 = constrainedSymbolicEvaluationEnvironment;
		assert constrainedSymbolicEvaluationEnvironment2 != null;
		constrainedSymbolicEvaluationEnvironment = constrainedSymbolicEvaluationEnvironment2.getParent();
	}

	private void popSymbolicHypothesis() {
		popEvaluationEnvironment();
	}

	@Override
	public @NonNull ConstrainedSymbolicEvaluationEnvironment pushConstrainedSymbolicEvaluationEnvironment(@NonNull OCLExpression expression) {
		AbstractSymbolicEvaluationEnvironment constrainedSymbolicEvaluationEnvironment2 = constrainedSymbolicEvaluationEnvironment;
		if (constrainedSymbolicEvaluationEnvironment2 == null) {
			constrainedSymbolicEvaluationEnvironment2 = getEvaluationEnvironment();
		}
		constrainedSymbolicEvaluationEnvironment = new ConstrainedSymbolicEvaluationEnvironment(constrainedSymbolicEvaluationEnvironment2, expression);
		return (ConstrainedSymbolicEvaluationEnvironment) constrainedSymbolicEvaluationEnvironment;
	}

	protected void resolveHypotheses() {
		Map<@NonNull TypedElement, @NonNull SymbolicValue> expression2hypothesizedValue2 = expression2hypothesizedValue;
		if (expression2hypothesizedValue2 != null) {
			AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
			List<@NonNull TypedElement> expressions = new ArrayList<>(expression2hypothesizedValue2.keySet());
			if (expressions.size() > 1) {
				Collections.sort(expressions, ((SymbolicAnalysis)this).getHeightComparator());
			}
			for (@NonNull TypedElement expression : expressions) {
				SymbolicValue symbolicValue = evaluationEnvironment.basicGetSymbolicValue(expression);
				SymbolicValue hypothesizedValue = expression2hypothesizedValue2.get(expression);
				CSEElement cseElement = ((SymbolicAnalysis)this).getCSEElement(expression);
				HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment = createHypothesizedSymbolicEvaluationEnvironment(cseElement);

				hypothesizedEvaluationEnvironment.putHypothesizedValue(expression, hypothesizedValue);
				hypothesizedEvaluationEnvironment.putHypothesizedValue(symbolicValue, hypothesizedValue);
				hypothesizedEvaluationEnvironment.putHypothesizedTerm((OCLExpression)expression);

				pushEvaluationEnvironment(hypothesizedEvaluationEnvironment);


				boolean isContradiction = hypothesizedEvaluationEnvironment.isContradiction();


				popEvaluationEnvironment();

			//	hypothesizedEvaluationEnvironment.resolveHypothesis(evaluationEnvironment);
			}
		}
	/*	if (hypothesizedEvaluationEnvironments != null) {
			for (@NonNull HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment : hypothesizedEvaluationEnvironments) {
				CSEElement hypothesizedElement = hypothesizedEvaluationEnvironment.getHypothesizedElement();
				AbstractSymbolicEvaluationEnvironment nestedEvaluationEnvironment = createNestedEvaluationEnvironment(getEvaluationEnvironment(), hypothesizedElement);		// XXX execuatbleObject?					pushEvaluationEnvironment(nestedEvaluationEnvironment);
				hypothesizedEvaluationEnvironment.resolveHypothesis(nestedEvaluationEnvironment);
				pushEvaluationEnvironment(nestedEvaluationEnvironment);

				nestedEvaluationEnvironment.symbolicEvaluate(hypothesizedElement);
				//	nestedEvaluationEnvironment.add(symbolicValue, constantValue);
			//		SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, SymbolicOperator.EQUALS, constantValue);
			//		symbolicValue.deduceFrom(this, symbolicConstraint);
			//	}


				popEvaluationEnvironment();
			}
		} */
	}

/*	@Override
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
	/ *	else if (symbolicValue instanceof SymbolicConstraint) {
			@NonNull OCLExpression expression = ((SymbolicConstraint)symbolicValue).getExpression();
			nestedEvaluationEnvironment = createNestedEvaluationEnvironment(evaluationEnvironment, expression, (Object)caller);
			pushEvaluationEnvironment(nestedEvaluationEnvironment);
			SimpleSymbolicConstraintImpl symbolicConstraint = new SimpleSymbolicConstraintImpl(symbolicValue.getTypeId(), false, false, SymbolicOperator.EQUALS, constantValue);
			symbolicValue.deduceFrom(this, symbolicConstraint);
		} * /
		else {
			throw new IllegalStateException();
		}
		return nestedEvaluationEnvironment;
	} */
}