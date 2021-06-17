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
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.evaluation.EvaluationHaltedException;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor.EvaluationVisitorExtension;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public class SymbolicAnalysis extends BasicOCLExecutor implements SymbolicExecutor, ExecutorInternal
{
	private @Nullable List<@NonNull HypothesizedSymbolicEvaluationEnvironment> hypothesizedEvaluationEnvironments = null;

	protected final @NonNull ExpressionInOCL expressionInOCL;
	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;

	/**
	 * The expressions for which contradicting a hypothesized value allows a more precise re-evaluation.
	 */
	private @Nullable Map<@NonNull TypedElement, @NonNull List<@NonNull Hypothesis>> typedElement2hypotheses = null;

	private @Nullable List<@NonNull Hypothesis> allHypotheses = null;

	/**
	 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
	 */
	public SymbolicAnalysis(@NonNull ExpressionInOCL expressionInOCL, @NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		super(environmentFactory, modelManager);
		this.expressionInOCL = expressionInOCL;
		this.cseAnalysis = new CommonSubExpressionAnalysis();
	}

	@Override
	public void addHypothesis(@NonNull TypedElement typedElement, @NonNull Hypothesis hypothesis) {
		List<@NonNull Hypothesis> hypotheses = getHypotheses(typedElement);
		hypotheses.add(hypothesis);
	//	assert old == null : "Repeated hypothesis : " + hypothesis;
		List<@NonNull Hypothesis> allHypotheses2 = allHypotheses;
		if (allHypotheses2 == null) {
			allHypotheses2 = allHypotheses = new ArrayList<>();
		}
		allHypotheses2.add(hypothesis);
	}

	public void addMayBeEmptyHypothesis(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue) {
		Hypothesis hypothesis = getHypotheses(typedElement, Hypothesis.MayBeEmptyHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeEmptyHypothesis(this, typedElement, symbolicValue);
			addHypothesis(typedElement, hypothesis);
		}
	}

	public void addMayBeInvalidHypothesis(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue) {
		Hypothesis hypothesis = getHypotheses(typedElement, Hypothesis.MayBeInvalidHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeInvalidHypothesis(this, typedElement, symbolicValue);
			addHypothesis(typedElement, hypothesis);
		}
	}

	public void addMayBeNullHypothesis(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue) {
		Hypothesis hypothesis = getHypotheses(typedElement, Hypothesis.MayBeNullHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeNullHypothesis(this, typedElement, symbolicValue);
			addHypothesis(typedElement, hypothesis);
		}
	}

/*	public void addMayBeSmallerThanHypothesis(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue, @NonNull SymbolicValue minSizeValue) {
		Hypothesis hypothesis = getHypotheses(typedElement, Hypothesis.MayBeSmallerThanHypothesis.class);		// minSIze
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeSmallerThanHypothesis(this, typedElement, symbolicValue, minSizeValue);
			addHypothesis(typedElement, hypothesis);
		}
	} */

	public void addMayBeZeroHypothesis(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue) {
		Hypothesis hypothesis = getHypotheses(typedElement, Hypothesis.MayBeZeroHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeZeroHypothesis(this, typedElement, symbolicValue);
			addHypothesis(typedElement, hypothesis);
		}
	}

	@Override
	protected @NonNull EvaluationVisitorExtension createEvaluationVisitor() {
		EvaluationVisitorExtension evaluationVisitor = super.createEvaluationVisitor();
		SymbolicEvaluationVisitor symbolicEvaluationVisitor = new SymbolicEvaluationVisitor(evaluationVisitor);
		return symbolicEvaluationVisitor;
	}

	@Override
	public @NonNull HypothesizedSymbolicEvaluationEnvironment createHypothesizedSymbolicEvaluationEnvironment(@NonNull Hypothesis hypothesis) {
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment = (BaseSymbolicEvaluationEnvironment) getEvaluationEnvironment();
	//	ConstrainedSymbolicEvaluationEnvironment constrainedEvaluationEnvironment2 = constrainedSymbolicEvaluationEnvironment;
	//	assert constrainedEvaluationEnvironment2 != null;
		HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment = new HypothesizedSymbolicEvaluationEnvironment(symbolicEvaluationEnvironment, hypothesis);
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

	@Override
	protected @NonNull AbstractSymbolicEvaluationEnvironment createRootEvaluationEnvironment(@NonNull NamedElement executableObject) {
		return new BaseSymbolicEvaluationEnvironment(this, executableObject);
	}

	public @NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment() {
		return getEvaluationEnvironment().getBaseSymbolicEvaluationEnvironment();
	}

	public @NonNull CSEElement getCSEElement(@NonNull Element element) {
		return cseAnalysis.getElementCSE(element);
	}

	@Override
	public @NonNull AbstractSymbolicEvaluationEnvironment getEvaluationEnvironment() {
		return (AbstractSymbolicEvaluationEnvironment)super.getEvaluationEnvironment();
	}

	private @Nullable Hypothesis getHypotheses(@NonNull TypedElement typedElement, @NonNull Class<?> hypothesisClass) {
		for (@NonNull Hypothesis hypothesis : getHypotheses(typedElement)) {
			if (hypothesis.getClass() == hypothesisClass) {
				return hypothesis;
			}
		}
		return null;
	}

	private @NonNull List<@NonNull Hypothesis> getHypotheses(@NonNull TypedElement typedElement) {
		Map<@NonNull TypedElement, @NonNull List<@NonNull Hypothesis>> typedElement2hypotheses2 = typedElement2hypotheses;
		if (typedElement2hypotheses2 == null) {
			typedElement2hypotheses = typedElement2hypotheses2 = new HashMap<>();
		}
		List<@NonNull Hypothesis> hypotheses = typedElement2hypotheses2.get(typedElement);
		if (hypotheses == null) {
			hypotheses = new ArrayList<>();
			typedElement2hypotheses2.put(typedElement, hypotheses);
		}
		return hypotheses;
	}

	public @Nullable List<@NonNull HypothesizedSymbolicEvaluationEnvironment> getHypothesizedEvaluationEnvironments() {
		return hypothesizedEvaluationEnvironments;
	}

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
				if (o1 instanceof VariableExp) h1++;
				if (o2 instanceof VariableExp) h2++;
				if (o1 instanceof ExpressionInOCL) h1++;
				if (o2 instanceof ExpressionInOCL) h2++;
				diff = h1 - h2;
				if (diff != 0) {
					return diff;
				}
				return System.identityHashCode(o1) - System.identityHashCode(o2);
			}
		};
	}

	public void initializeEvaluationEnvironment(@NonNull ExpressionInOCL expressionInOCL, @Nullable Object contextElement, @Nullable Object @Nullable [] parameters) {
		initializeEvaluationEnvironment(expressionInOCL);
		cseAnalysis.analyze(expressionInOCL);
		BaseSymbolicEvaluationEnvironment symbolicEvaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
	//	IdResolver idResolver = environmentFactory.getIdResolver();
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable != null) {
		//	Object contextValue = idResolver.boxedValueOf(contextElement);
		//	symbolicEvaluationEnvironment.add(contextVariable, contextValue);
			CSEElement cseElement = getCSEElement(contextVariable);
			symbolicEvaluationEnvironment.traceValue(cseElement, contextElement);
		}
		int i = 0;
		assert parameters != null;
		for (Variable parameterVariable : PivotUtil.getOwnedParameters(expressionInOCL)) {
			Object parameter = parameters[i++];
		//	Object parameterValue = idResolver.boxedValueOf(parameter);
		//	symbolicEvaluationEnvironment.add(parameterVariable, parameterValue);
			CSEElement cseElement = getCSEElement(parameterVariable);
			symbolicEvaluationEnvironment.traceValue(cseElement, parameter);
		}
	}

/*	public @NonNull SymbolicValue mergeValue(@NonNull SymbolicValue leftSymbolicValue, @NonNull SymbolicValue rightSymbolicValue) {
		if (leftSymbolicValue == rightSymbolicValue) {
			return leftSymbolicValue;
		}
	//	assert leftSymbolicValue.getTypeId() == rightSymbolicValue.getTypeId();	// XXX need to tolerate inheritance
		boolean leftKnown = leftSymbolicValue instanceof SymbolicKnownValue;
		boolean leftMayBeInvalid = leftSymbolicValue.mayBeInvalid();
		boolean leftMayBeNull = leftSymbolicValue.mayBeNull();
		boolean leftMayBeZero = leftSymbolicValue.mayBeZero();
		boolean rightKnown = rightSymbolicValue instanceof SymbolicKnownValue;
		boolean rightMayBeInvalid = rightSymbolicValue.mayBeInvalid();
		boolean rightMayBeNull = rightSymbolicValue.mayBeNull();
		boolean rightMayBeZero = rightSymbolicValue.mayBeZero();
		boolean leftStricter = (leftKnown && !rightKnown) || (!leftMayBeInvalid && rightMayBeInvalid) || (!leftMayBeNull && rightMayBeNull) || (!leftMayBeZero && rightMayBeZero);
		boolean rightStricter = (rightKnown && !leftKnown) || (!rightMayBeInvalid && leftMayBeInvalid) || (!rightMayBeNull && leftMayBeNull) || (!rightMayBeZero && leftMayBeZero);
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
	} */

	protected void resolveHypotheses() {
		Map<@NonNull TypedElement, @NonNull List<@NonNull Hypothesis>> typedElement2hypotheses2 = typedElement2hypotheses;
		if (typedElement2hypotheses2 != null) {
		//	AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
			List<@NonNull Hypothesis> hypotheses = new ArrayList<>(allHypotheses);
			if (hypotheses.size() > 1) {
				Collections.sort(hypotheses);
			}
			for (@NonNull Hypothesis hypothesis : hypotheses) {	// XXX domain growth
				if (isCanceled()) {
					throw new EvaluationHaltedException("Canceled");
				}
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

	public void symbolicEvaluate(@NonNull ExpressionInOCL expressionInOCL) {
		if (isCanceled()) {
			throw new EvaluationHaltedException("Canceled");
		}
		List<@NonNull TypedElement> typedElements = new ArrayList<>();
		for (@NonNull EObject eObject : new TreeIterable(expressionInOCL, true)) {
			if (eObject instanceof TypedElement) {
				typedElements.add((TypedElement) eObject);
			}
		}
		Collections.sort(typedElements, getTypedElementHeightComparator());
		AbstractSymbolicEvaluationEnvironment evaluationEnvironment = getEvaluationEnvironment();
		for (@NonNull TypedElement typedElement : typedElements) {
			evaluationEnvironment.symbolicEvaluate(typedElement);
		}
		resolveHypotheses();
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
			for (HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironments : hypothesizedEvaluationEnvironments) {
				StringUtil.appendIndentation(s, 1);
				s.append("{ ");
			//	StringUtil.appendIndentation(s, 2);
				hypothesizedSymbolicEvaluationEnvironments.toString(s, 1);
				StringUtil.appendIndentation(s, 1);
				s.append("}");
			}
		}
		return s.toString();
	}
}
