/*******************************************************************************
 * Copyright (c) 2020, 2021 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationHaltedException;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicMapContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicKnownValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUnknownValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValue;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.OCLValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public class SymbolicAnalysis /*extends BasicOCLExecutor implements SymbolicExecutor, ExecutorInternal*/
{
	public static final @NonNull TracingOption HYPOTHESIS = new TracingOption(PivotPlugin.PLUGIN_ID, "symbolic/hypothesis");

	private final @NonNull BasicOCLExecutor executor;
	protected final EnvironmentFactoryInternal.@NonNull EnvironmentFactoryInternalExtension environmentFactory;
	protected final @NonNull ExpressionInOCL expressionInOCL;
	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;
	protected final @NonNull BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment;
	private final @NonNull EvaluationVisitor evaluationVisitor;
	private @Nullable List<@NonNull HypothesizedSymbolicEvaluationEnvironment> hypothesizedEvaluationEnvironments = null;


	/**
	 * The known symbolic value of known literal values.
	 */
	private @NonNull Map<@Nullable Object, org.eclipse.ocl.pivot.internal.symbolic.SymbolicKnownValue> knownValue2symbolicValue = new HashMap<>();

	/**
	 * The maybe-invalid symbolic value of known TYpeIds.
	 */
	private @NonNull Map<@NonNull TypeId, org.eclipse.ocl.pivot.internal.symbolic.SymbolicUnknownValue> typeid2symbolicValue = new HashMap<>();

	/**
	 * The expressions for which contradicting a hypothesized value allows a more precise re-evaluation.
	 */
//	private @Nullable Map<@NonNull CSEElement, @NonNull List<@NonNull Hypothesis>> cseElement2hypotheses = null;
	private @Nullable Map<@NonNull Iterable<@NonNull TypedElement>, @NonNull List<@NonNull Hypothesis>> typedElement2hypotheses = null;

	private @Nullable List<@NonNull Hypothesis> allHypotheses = null;

	/**
	 * Counter for allocated constants.
	 */
	private int constantCounter = 0;

	/**
	 * Counter for allocated variables.
	 */
	private int variableCounter = 0;

	/**
	 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
	 */
	public SymbolicAnalysis(@NonNull ExpressionInOCL expressionInOCL, @NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		this.executor = new BasicOCLExecutor(environmentFactory, modelManager);
		this.environmentFactory = environmentFactory;
		this.expressionInOCL = expressionInOCL;
		this.cseAnalysis = new CommonSubExpressionAnalysis();
		this.evaluationVisitor = executor.createEvaluationVisitor();
		this.baseSymbolicEvaluationEnvironment = new BaseSymbolicEvaluationEnvironment(this, expressionInOCL);
	}

	public void addHypothesis(@NonNull Hypothesis hypothesis) {
	//	for (@NonNull TypedElement typedElement : hypothesis.getTypedElements()) {
	//		List<@NonNull Hypothesis> hypotheses = getHypotheses(typedElement);
	//		hypotheses.add(hypothesis);
	//	}
		//	assert old == null : "Repeated hypothesis : " + hypothesis;
		List<@NonNull Hypothesis> allHypotheses2 = allHypotheses;
		if (allHypotheses2 == null) {
			allHypotheses2 = allHypotheses = new ArrayList<>();
		}
		allHypotheses2.add(hypothesis);
	}

	public void addMayBeEmptyHypothesis(@NonNull Iterable<@NonNull TypedElement> typedElements) {
		Hypothesis hypothesis = getHypotheses(typedElements, Hypothesis.MayBeEmptyHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeEmptyHypothesis(this, typedElements);
			addHypothesis(hypothesis);
		}
	}

	public void addMayBeInvalidHypothesis(@NonNull Iterable<@NonNull TypedElement> typedElements) {
		Hypothesis hypothesis = getHypotheses(typedElements, Hypothesis.MayBeInvalidHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeInvalidHypothesis(this, typedElements);
			addHypothesis(hypothesis);
		}
	}

	public void addMayBeNullHypothesis(@NonNull Iterable<@NonNull TypedElement> typedElements) {
		Hypothesis hypothesis = getHypotheses(typedElements, Hypothesis.MayBeNullHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeNullHypothesis(this, typedElements);
			addHypothesis(hypothesis);
		}
	}

	public void addMayBeZeroHypothesis(@NonNull Iterable<@NonNull TypedElement> typedElements) {
		Hypothesis hypothesis = getHypotheses(typedElements, Hypothesis.MayBeZeroHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeZeroHypothesis(this, typedElements);
			addHypothesis(hypothesis);
		}
	}

	public @NonNull SymbolicEvaluationVisitor createSymbolicEvaluationVisitor(@NonNull SymbolicEvaluationEnvironment symbolicEvaluationEnvironment) {
		SymbolicEvaluationVisitor symbolicEvaluationVisitor = new SymbolicEvaluationVisitor(this, evaluationVisitor, symbolicEvaluationEnvironment);
		return symbolicEvaluationVisitor;
	}

	public @NonNull HypothesizedSymbolicEvaluationEnvironment createHypothesizedSymbolicEvaluationEnvironment(@NonNull Hypothesis hypothesis, @NonNull TypedElement typedElement) {
		HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment = new HypothesizedSymbolicEvaluationEnvironment(baseSymbolicEvaluationEnvironment, hypothesis, typedElement);
		List<@NonNull HypothesizedSymbolicEvaluationEnvironment> hypothesizedEvaluationEnvironments2 = hypothesizedEvaluationEnvironments;
		if (hypothesizedEvaluationEnvironments2 == null) {
			hypothesizedEvaluationEnvironments = hypothesizedEvaluationEnvironments2 = new ArrayList<>();
		}
		hypothesizedEvaluationEnvironments2.add(hypothesizedEvaluationEnvironment);
		return hypothesizedEvaluationEnvironment;
	}

	public @NonNull SymbolicUnknownValue createUnknownValue(@NonNull TypeId typeId, boolean mayBeNull, boolean mayBeInvalid) {
		return new SymbolicUnknownValue(createVariableName(), typeId, mayBeNull, mayBeInvalid);
	}

	public @NonNull SymbolicValue createUnknownValue(@NonNull TypedElement typedElement, boolean mayBeNull, boolean mayBeInvalid) {
		return createUnknownValue(typedElement.getTypeId(), mayBeNull, mayBeInvalid);
	}

	public @NonNull String createVariableName() {
		return "s#" + variableCounter++;
	}

	public @NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment() {
		return baseSymbolicEvaluationEnvironment;
	}

	public @NonNull CSEElement getCSEElement(@NonNull TypedElement element) {
		return cseAnalysis.getElementCSE(element);
	}

	public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return environmentFactory;
	}

	public @NonNull ExecutorInternal getExecutor() {
		return executor;
	}

	private @Nullable Hypothesis getHypotheses(@NonNull Iterable<@NonNull TypedElement> typedElements, @NonNull Class<?> hypothesisClass) {
		for (@NonNull Hypothesis hypothesis : getHypotheses(typedElements)) {
			if (hypothesis.getClass() == hypothesisClass) {
				return hypothesis;
			}
		}
		return null;
	}

	private @NonNull List<@NonNull Hypothesis> getHypotheses(@NonNull Iterable<@NonNull TypedElement> typedElements) {
		Map<@NonNull Iterable<@NonNull TypedElement>, @NonNull List<@NonNull Hypothesis>> typedElement2hypotheses2 = typedElement2hypotheses;
		if (typedElement2hypotheses2 == null) {
			typedElement2hypotheses = typedElement2hypotheses2 = new HashMap<>();
		}
		List<@NonNull Hypothesis> hypotheses = typedElement2hypotheses2.get(typedElements);
		if (hypotheses == null) {
			hypotheses = new ArrayList<>();
			typedElement2hypotheses2.put(typedElements, hypotheses);
		}
		return hypotheses;
	}

	public @Nullable List<@NonNull HypothesizedSymbolicEvaluationEnvironment> getHypothesizedEvaluationEnvironments() {
		return hypothesizedEvaluationEnvironments;
	}

	public @NonNull SymbolicValue getKnownValue(@Nullable Object boxedValue) {
		assert ValueUtil.isBoxed(boxedValue);
		SymbolicKnownValue symbolicKnownValue = knownValue2symbolicValue.get(boxedValue);
		if (symbolicKnownValue == null) {
			if (boxedValue instanceof OCLValue) {
				for (@Nullable Object key : knownValue2symbolicValue.keySet()) {		// FIXME ?? smarter cache ?? Redundant OCLValue is already smart
					if ((key instanceof OCLValue) && ((OCLValue)boxedValue).oclEquals((OCLValue)key)) {
						symbolicKnownValue = knownValue2symbolicValue.get(key);
					}
				}
			}
			if (symbolicKnownValue == null) {
				Type type = environmentFactory.getIdResolver().getStaticTypeOfValue(null, boxedValue);
				String constantName = "k#" + constantCounter++;
				TypeId typeId = type.getTypeId();
				SymbolicContent content = null;
				if (boxedValue instanceof InvalidValue) {
				}
				else if (boxedValue instanceof CollectionValue) {
					content = new SymbolicCollectionContent("c#" + constantName + "%", (CollectionTypeId)typeId);
					content.setSize(getKnownValue(((CollectionValue)boxedValue).isEmpty() ? ValueUtil.ZERO_VALUE : ValueUtil.ONE_VALUE));
				}
				else if (boxedValue instanceof MapValue) {
					content = new SymbolicMapContent("m#" + constantName + "%", (MapTypeId)typeId);
					content.setSize(getKnownValue(((MapValue)boxedValue).isEmpty() ? ValueUtil.ZERO_VALUE : ValueUtil.ONE_VALUE));
				}
				symbolicKnownValue = new SymbolicKnownValue(constantName, typeId, boxedValue, content);
				knownValue2symbolicValue.put(boxedValue, symbolicKnownValue);
			}
		}
		return symbolicKnownValue;
	}

	public @Nullable SymbolicValue getMayBeInvalidValue(@NonNull TypeId typeid) {
		SymbolicUnknownValue symbolicUnknownValue = typeid2symbolicValue.get(typeid);
		if (symbolicUnknownValue == null) {
			symbolicUnknownValue = createUnknownValue(typeid, false, true);
			typeid2symbolicValue.put(typeid, symbolicUnknownValue);
		}
		return symbolicUnknownValue;
	}

	public @NonNull SymbolicEvaluationEnvironment getSymbolicEvaluationEnvironment() {
		return baseSymbolicEvaluationEnvironment.getSymbolicEvaluationEnvironment();
	}

	public @NonNull Comparator<@NonNull TypedElement> getTypedElementHeightComparator() {
		return cseAnalysis.getTypedElementHeightComparator();
	}

	public void initializeEvaluationEnvironment(@NonNull ExpressionInOCL expressionInOCL, @Nullable Object selfObject, @Nullable Object resultObject, @Nullable Object @Nullable [] parameters) {
		executor.initializeEvaluationEnvironment(expressionInOCL);
		cseAnalysis.analyze(expressionInOCL);
		Variable contextVariable = expressionInOCL.getOwnedContext();
		if (contextVariable != null) {
			CSEElement cseElement = getCSEElement(contextVariable);
			traceValue(cseElement, selfObject);
		}
		Variable resultVariable = expressionInOCL.getOwnedResult();
		if (resultVariable != null) {
			CSEElement cseElement = getCSEElement(resultVariable);
			traceValue(cseElement, resultObject);
		}
		int i = 0;
		assert parameters != null;
		for (Variable parameterVariable : PivotUtil.getOwnedParameters(expressionInOCL)) {
			Object parameter = parameters[i++];
			CSEElement cseElement = getCSEElement(parameterVariable);
			traceValue(cseElement, parameter);
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

	private boolean isCanceled() {
		return executor.isCanceled();
	}

	protected void resolveHypotheses() {
		Map<@NonNull Iterable<@NonNull TypedElement>, @NonNull List<@NonNull Hypothesis>> typedElement2hypotheses2 = typedElement2hypotheses;
		if (typedElement2hypotheses2 != null) {
			if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
				SymbolicAnalysis.HYPOTHESIS.println(" resolving hypotheses");
			}
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
	}

	public void symbolicEvaluate(@NonNull ExpressionInOCL expressionInOCL) {
		if (isCanceled()) {
			throw new EvaluationHaltedException("Canceled");
		}
		if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
			SymbolicAnalysis.HYPOTHESIS.println("Analyzing: " + SymbolicUtil.printPath(expressionInOCL));
		}
		List<@NonNull TypedElement> typedElements = new ArrayList<>();
		for (@NonNull EObject eObject : new TreeIterable(expressionInOCL, true)) {
			if (eObject instanceof TypedElement) {
				typedElements.add((TypedElement) eObject);
			}
		}
		Collections.sort(typedElements, getTypedElementHeightComparator());
		BaseSymbolicEvaluationEnvironment evaluationEnvironment = (BaseSymbolicEvaluationEnvironment) getSymbolicEvaluationEnvironment();
		for (@NonNull TypedElement typedElement : typedElements) {
			evaluationEnvironment.symbolicEvaluate(typedElement, true);
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

	private @NonNull SymbolicValue traceValue(@NonNull CSEElement cseElement, @Nullable Object value) {
		SymbolicValue symbolicValue;
		if (value instanceof SymbolicValue) {
			symbolicValue = (SymbolicValue) value;
		}
		else {
			Object boxedValue = environmentFactory.getIdResolver().boxedValueOf(value);
			symbolicValue = getKnownValue(boxedValue);
		}
		BaseSymbolicEvaluationEnvironment evaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
		return evaluationEnvironment.traceSymbolicValue(cseElement, symbolicValue);
	}
}
