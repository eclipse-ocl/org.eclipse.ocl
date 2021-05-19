/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.pivot.internal.evaluation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor.EvaluationVisitorExtension;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.manager.SymbolicOCLExecutor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.SymbolicKnownValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.15
 */
public class SymbolicAnalysis extends SymbolicOCLExecutor	// FIXME merge SymbolicAnalysis + SymbolicOCLExecutor
{
	protected final @NonNull ExpressionInOCL expressionInOCL;
	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;
//	protected final @NonNull Map<@NonNull VariableDeclaration, @NonNull List<@NonNull VariableExp>> variable2variableExps = new HashMap<>();

	/**
	 * The expressions for which contradicting a hypothesized value allows a more precise re-evaluation.
	 */
	private @Nullable Map<@NonNull OCLExpression, @NonNull Hypothesis> expression2hypothesis = null;

	/**
	 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
	 */
	public SymbolicAnalysis(@NonNull ExpressionInOCL expressionInOCL, @NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		super(environmentFactory, modelManager);
		this.expressionInOCL = expressionInOCL;
		this.cseAnalysis = new CommonSubExpressionAnalysis();
//		analyze(expressionInOCL);
	}

	@Override
	public void addHypothesis(@NonNull OCLExpression expression, @NonNull Hypothesis hypothesis) {
		Map<@NonNull OCLExpression, @NonNull Hypothesis> expression2hypothesis2 = expression2hypothesis;
		if (expression2hypothesis2 == null) {
			expression2hypothesis = expression2hypothesis2 = new HashMap<>();
		}
		Hypothesis old = expression2hypothesis2.put(expression, hypothesis);
		assert old == null;
	}

/*	private void analyze(@NonNull ExpressionInOCL expressionInOCL) {
		for (@NonNull EObject eObject : new TreeIterable(expressionInOCL, true)) {
			if (eObject instanceof VariableExp) {
				VariableExp variableExp = (VariableExp)eObject;
				VariableDeclaration variable = PivotUtil.getReferredVariable(variableExp);
				List<@NonNull VariableExp> variableExps = variable2variableExps.get(variable);
				if (variableExps == null) {
					variableExps = new ArrayList<>();
					variable2variableExps.put(variable, variableExps);
				}
				variableExps.add(variableExp);
			}
		//	if (eObject instanceof TypedElement) {
		//		TypedElement typedElement = (TypedElement)eObject;
		//		expression2height.put(typedElement, getHeight(typedElement));
		//	}
		}
	//	getHeight(expressionInOCL);
	} */

/*	private int getHeight(@NonNull TypedElement typedElement) {
		CSEElement cseElement = getCSEElement(typedElement);
		cseElement.getHeight();
		Integer knownHeight = cse2height.get(cseElement);
		if (knownHeight != null) {
			return knownHeight.intValue();
		}
		int maxHeight = 0;
		if (typedElement instanceof VariableExp) {
			VariableExp variableExp = (VariableExp)typedElement;
			VariableDeclaration variable = PivotUtil.getReferredVariable(variableExp);
			maxHeight = getHeight(variable) + 1;
		}
		else {
			for (EObject eObject : typedElement.eContents()) {
				if (eObject instanceof TypedElement) {
					int height = getHeight((TypedElement)eObject) + 1;
					if (height > maxHeight) {
						maxHeight = height;
					}
				}
			}
		}
		Integer old = cse2height.put(cseElement, maxHeight);
		assert old == null;
		return maxHeight;
	} */

	public @NonNull Comparator<@NonNull TypedElement> getTypedElementHeightComparator() {
		return new Comparator<@NonNull TypedElement>()
		{
			@Override
			public int compare(@NonNull TypedElement o1, @NonNull TypedElement o2) {
				int h1 = SymbolicAnalysis.this.getCSEElement(o1).getHeight();
				int h2 = SymbolicAnalysis.this.getCSEElement(o2).getHeight();
				int diff = h1 - h2;
				if (diff != 0) {
					return diff;
				}
				return System.identityHashCode(o1) - System.identityHashCode(o2);
			}
		};
	}

/*	public @NonNull Comparator<@NonNull CSEElement> getHeightComparator() {
		return new Comparator<@NonNull CSEElement>()
		{
			@Override
			public int compare(@NonNull CSEElement o1, @NonNull CSEElement o2) {
				int h1 = o1.getHeight();
				int h2 = o2.getHeight();
				int diff = h1 - h2;
				if (diff != 0) {
					return diff;
				}
				return System.identityHashCode(o1) - System.identityHashCode(o2);
			}
		};
	} */

	@Override
	protected @NonNull EvaluationVisitorExtension createEvaluationVisitor() {
		EvaluationVisitorExtension evaluationVisitor = super.createEvaluationVisitor();
		SymbolicEvaluationVisitor symbolicEvaluationVisitor = new SymbolicEvaluationVisitor(evaluationVisitor);
		return symbolicEvaluationVisitor;
	}

	public @NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment() {
		return getEvaluationEnvironment().getBaseSymbolicEvaluationEnvironment();
	}

	public @NonNull CSEElement getCSEElement(@NonNull TypedElement element) {
		return cseAnalysis.getElementCSE(element);
	}

	public void initializeEvaluationEnvironment(@NonNull ExpressionInOCL expressionInOCL, @Nullable Object contextElement, @Nullable Object @Nullable [] parameters) {
		cseAnalysis.analyze(expressionInOCL);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
		IdResolver idResolver = environmentFactory.getIdResolver();
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable != null) {
			Object contextValue = idResolver.boxedValueOf(contextElement);
			symbolicEvaluationEnvironment.add(contextVariable, contextValue);
			symbolicEvaluationEnvironment.traceValue(contextVariable, contextElement);
		}
		int i = 0;
		assert parameters != null;
		for (Variable parameterVariable : PivotUtil.getOwnedParameters(expressionInOCL)) {
			Object parameter = parameters[i++];
			Object parameterValue = idResolver.boxedValueOf(parameter);
			symbolicEvaluationEnvironment.add(parameterVariable, parameterValue);
			symbolicEvaluationEnvironment.traceValue(parameterVariable, parameter);
		}
	//	symbolicEvaluate(expressionInOCL);
	}

	public @NonNull SymbolicValue mergeValue(@NonNull SymbolicValue leftSymbolicValue, @NonNull SymbolicValue rightSymbolicValue) {
		if (leftSymbolicValue == rightSymbolicValue) {
			return leftSymbolicValue;
		}
	//	assert leftSymbolicValue.getTypeId() == rightSymbolicValue.getTypeId();	// XXX need to tolerate inheritance
		boolean leftKnown = leftSymbolicValue instanceof SymbolicKnownValue;
		boolean leftMayBeInvalid = leftSymbolicValue.mayBeInvalid();
		boolean leftMayBeNull = leftSymbolicValue.mayBeNull();
		boolean leftMayZero = leftSymbolicValue.mayBeZero();
		boolean rightKnown = rightSymbolicValue instanceof SymbolicKnownValue;
		boolean rightMayBeInvalid = rightSymbolicValue.mayBeInvalid();
		boolean rightMayBeNull = rightSymbolicValue.mayBeNull();
		boolean rightMayZero = rightSymbolicValue.mayBeZero();
		boolean leftStricter = (leftKnown && !rightKnown) || (!leftMayBeInvalid && rightMayBeInvalid) || (!leftMayBeNull && rightMayBeNull) || (!leftMayZero && rightMayZero);
		boolean rightStricter = (rightKnown && !leftKnown) || (!rightMayBeInvalid && leftMayBeInvalid) || (!rightMayBeNull && leftMayBeNull) || (!rightMayZero && leftMayZero);
		if (leftStricter && rightStricter) {
			throw new UnsupportedOperationException();
		}
		if (leftStricter) {
			return leftSymbolicValue;
		}
		if (rightStricter) {
			return rightSymbolicValue;
		}
		throw new UnsupportedOperationException();
	}

	protected void resolveHypotheses() {
		Map<@NonNull OCLExpression, @NonNull Hypothesis> expression2hypothesis2 = expression2hypothesis;
		if (expression2hypothesis2 != null) {
		//	AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
			List<@NonNull Hypothesis> hypotheses = new ArrayList<>(expression2hypothesis2.values());
			if (hypotheses.size() > 1) {
				Collections.sort(hypotheses);
			}
			for (@NonNull Hypothesis hypothesis : hypotheses) {
				hypothesis.check();
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

	public @NonNull SymbolicValue symbolicEvaluate(@NonNull ExpressionInOCL expressionInOCL) {
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		SymbolicValue result = evaluationEnvironment.symbolicEvaluate(expressionInOCL);
		resolveHypotheses();
		return result;
	}

	@Override
	public @NonNull String toString() {
		BaseSymbolicEvaluationEnvironment evaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
		StringBuilder s = new StringBuilder();
		for (EObject eObject : new TreeIterable(expressionInOCL, true)) {
			s.append("\n  ");
			for (EObject eParent = eObject; eParent != null && eParent != expressionInOCL; eParent = eParent.eContainer()) {
				s.append("  ");
			}
			s.append(eObject.eClass().getName());
			s.append(" : ");
			s.append(eObject.toString());
			s.append("\n  ");
			for (EObject eParent = eObject; eParent != null && eParent != expressionInOCL; eParent = eParent.eContainer()) {
				s.append("  ");
			}
			s.append("  => ");
			SymbolicValue symbolicValue = eObject instanceof TypedElement ? evaluationEnvironment.basicGetSymbolicValue((TypedElement)eObject) : null;
			if (symbolicValue == null) {
				s.append("not-computed");
			}
			else {
				s.append(symbolicValue.getClass().getSimpleName());
				s.append(" : ");
				s.append(symbolicValue);
			}
		}
		List<@NonNull HypothesizedSymbolicEvaluationEnvironment> hypothesizedEvaluationEnvironments = getHypothesizedEvaluationEnvironments();
		if (hypothesizedEvaluationEnvironments != null) {
			StringUtil.appendIndentation(s, 1);
			for (HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironments : hypothesizedEvaluationEnvironments) {
				hypothesizedSymbolicEvaluationEnvironments.toString(s, 1);
			}
		}
		return s.toString();
	}
}
