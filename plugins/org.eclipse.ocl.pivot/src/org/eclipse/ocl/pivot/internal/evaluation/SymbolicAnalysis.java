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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationHaltedException;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicMapContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicKnownValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUnknownValue;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.TracingOption;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValue;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.OCLValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * @since 1.16
 */
public abstract class SymbolicAnalysis /*extends BasicOCLExecutor implements SymbolicExecutor, ExecutorInternal*/
{
	public static final @NonNull TracingOption HYPOTHESIS = new TracingOption(PivotPlugin.PLUGIN_ID, "symbolic/hypothesis");

	private final @NonNull BasicOCLExecutor executor;
	protected final EnvironmentFactoryInternal.@NonNull EnvironmentFactoryInternalExtension environmentFactory;
	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;
	private @Nullable BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment =null;
	private final @NonNull EvaluationVisitor evaluationVisitor;
	private @Nullable List<@NonNull HypothesizedSymbolicEvaluationEnvironment> hypothesizedEvaluationEnvironments = null;
	private @Nullable String analysisIncompatibility = null;		// set by analyze()

	/**
	 * A cache for known symbolic values of known literal values.
	 */
	private @NonNull Map<@Nullable Object, @NonNull SymbolicKnownValue> knownValue2symbolicValue = new HashMap<>();

	/**
	 * A cache of maybe-invalid symbolic value of known TYpeIds.
	 */
	private @NonNull Map<@NonNull TypeId, @NonNull List<@NonNull SymbolicUnknownValue>> typeid2unknownValues = new HashMap<>();


	/**
	 * The expressions for which contradicting a hypothesized value allows a more precise re-evaluation.
	 */
//	private @Nullable Map<@NonNull CSEElement, @NonNull List<@NonNull Hypothesis>> cseElement2hypotheses = null;
//	private @Nullable Map<@NonNull Iterable<@NonNull TypedElement>, @NonNull List<@NonNull Hypothesis>> ztypedElement2hypotheses = null;

	/**
	 * The hypotheses that may allow the symbolic values of some typed elements to be refined, indexed by the
	 * height of each TypedElemnt's CSE.
	 */
	private @Nullable List<@Nullable List<@NonNull Hypothesis>> height2hypotheses = null;

	/**
	 * The height index of height2hypotheses that are currently being resolved.
	 */
	private int height2hypothesisHeight = 0;

	/**
	 * The hypotheses that apply to each typed element.
	 */
	private @Nullable Map<@NonNull TypedElement, @NonNull List<@NonNull Hypothesis>> typedElement2hypotheses = null;

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
	protected SymbolicAnalysis(@NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
		this.executor = new BasicOCLExecutor(environmentFactory, modelManager);
		this.environmentFactory = environmentFactory;
		this.cseAnalysis = new CommonSubExpressionAnalysis();
		this.evaluationVisitor = executor.createEvaluationVisitor();
	//	this.baseSymbolicEvaluationEnvironment = new BaseSymbolicEvaluationEnvironment(this, expressionInOCL);
	}

	public void addHypothesis(@NonNull Hypothesis hypothesis) {
		List<@Nullable List<@NonNull Hypothesis>> height2hypotheses2 = height2hypotheses;
		if (height2hypotheses2 == null) {
			height2hypotheses2 = height2hypotheses = new ArrayList<>();
		}
		int height = hypothesis.getCSEElement().getHeight();
		System.out.println(height + "- " + hypothesis);		// XXX
		while (height2hypotheses2.size() <= height) {
			height2hypotheses2.add(null);
		}
		List<@NonNull Hypothesis> sameHeightHypotheses = height2hypotheses2.get(height);
		if (sameHeightHypotheses == null) {
			sameHeightHypotheses = new ArrayList<>();
			height2hypotheses2.set(height, sameHeightHypotheses);
		}
		sameHeightHypotheses.add(hypothesis);
		Map<@NonNull TypedElement, @NonNull List<@NonNull Hypothesis>> typedElement2hypotheses2 = typedElement2hypotheses;
		assert typedElement2hypotheses2 != null;
		for (@NonNull TypedElement typedElement : hypothesis.getCSEElement().getElements()) {
			List<@NonNull Hypothesis> sameTypedElementHypotheses = typedElement2hypotheses2.get(typedElement);
			if (sameTypedElementHypotheses == null) {
				sameTypedElementHypotheses = new ArrayList<>();
				typedElement2hypotheses2.put(typedElement, sameTypedElementHypotheses);
			}
			assert !sameTypedElementHypotheses.contains(hypothesis);
			sameTypedElementHypotheses.add(hypothesis);
		}
	}

	public void addMayBeEmptyHypothesis(@NonNull Iterable<@NonNull TypedElement> typedElements) {
		Hypothesis hypothesis = getHypothesis(typedElements, Hypothesis.MayBeEmptyHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeEmptyHypothesis(this, typedElements);
			addHypothesis(hypothesis);
		}
	}

	public void addMayBeInvalidHypothesis(@NonNull Iterable<@NonNull TypedElement> typedElements) {
		Hypothesis hypothesis = getHypothesis(typedElements, Hypothesis.MayBeInvalidHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeInvalidHypothesis(this, typedElements);
			addHypothesis(hypothesis);
		}
	}

	public void addMayBeNullHypothesis(@NonNull Iterable<@NonNull TypedElement> typedElements) {
		Hypothesis hypothesis = getHypothesis(typedElements, Hypothesis.MayBeNullHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeNullHypothesis(this, typedElements);
			addHypothesis(hypothesis);
		}
	}

	public void addMayBeZeroHypothesis(@NonNull Iterable<@NonNull TypedElement> typedElements) {
		Hypothesis hypothesis = getHypothesis(typedElements, Hypothesis.MayBeZeroHypothesis.class);
		if (hypothesis == null) {
			hypothesis = new Hypothesis.MayBeZeroHypothesis(this, typedElements);
			addHypothesis(hypothesis);
		}
	}

	public @Nullable String analyze(@NonNull ExpressionInOCL expressionInOCL, @Nullable Object selfObject, @Nullable Object resultObject, @Nullable Object @Nullable [] parameters) {
		if (isCanceled()) {
			throw new EvaluationHaltedException("Canceled");
		}
		if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
			StringBuilder s = new StringBuilder();
			s.append("Analyzing: ");
			s.append(expressionInOCL);
		//	s.append("\n\tself = ");
			s.append(" for ");
			ValueUtil.toString(selfObject, s, 100);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
				//	s.append("\n\t");
					s.append(", ");
				//	s.append(PivotUtil.getOwnedParameter(expressionInOCL, i).getName());
				//	s.append(" = ");
					ValueUtil.toString(parameters[i], s, 100);
				}
			}
			SymbolicAnalysis.HYPOTHESIS.println(s.toString());
		}
		BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment2 = new BaseSymbolicEvaluationEnvironment(this, expressionInOCL);
		this.baseSymbolicEvaluationEnvironment = baseSymbolicEvaluationEnvironment2;
		executor.initializeEvaluationEnvironment(expressionInOCL);
		cseAnalysis.analyze(expressionInOCL);
		this.analysisIncompatibility = baseSymbolicEvaluationEnvironment2.analyze(selfObject, resultObject, parameters);
		if (analysisIncompatibility != null) {
			return analysisIncompatibility;
		}
		List<@Nullable List<@NonNull Hypothesis>> height2hypotheses2 = height2hypotheses;
		if (height2hypotheses2 != null) {
			for ( ; height2hypothesisHeight < height2hypotheses2.size(); height2hypothesisHeight++) {
				List<@NonNull Hypothesis> hypotheses = height2hypotheses2.get(height2hypothesisHeight);
				if (hypotheses != null) {
					if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
						SymbolicAnalysis.HYPOTHESIS.println(" resolving hypotheses at " + height2hypothesisHeight);
					}
					for (int index = 0; index < hypotheses.size(); index++) {
						Hypothesis hypothesis = hypotheses.get(index);
						if (isCanceled()) {
							throw new EvaluationHaltedException("Canceled");
						}
						hypothesis.analyze();
					}
				}
			}
		}
		return null;
	}

	public @NonNull SymbolicEvaluationVisitor createSymbolicEvaluationVisitor(@NonNull SymbolicEvaluationEnvironment symbolicEvaluationEnvironment) {
		SymbolicEvaluationVisitor symbolicEvaluationVisitor = new SymbolicEvaluationVisitor(this, evaluationVisitor, symbolicEvaluationEnvironment);
		return symbolicEvaluationVisitor;
	}

	public @NonNull HypothesizedSymbolicEvaluationEnvironment createHypothesizedSymbolicEvaluationEnvironment(@NonNull Hypothesis hypothesis, @NonNull TypedElement typedElement) {
		HypothesizedSymbolicEvaluationEnvironment hypothesizedEvaluationEnvironment = new HypothesizedSymbolicEvaluationEnvironment(getBaseSymbolicEvaluationEnvironment(), hypothesis, typedElement);
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

	public @NonNull String createVariableName() {
		return "s#" + variableCounter++;
	}

	public @Nullable String getAnalysisIncompatibility() {
		return analysisIncompatibility;
	}

	public @NonNull BaseSymbolicEvaluationEnvironment getBaseSymbolicEvaluationEnvironment() {
		assert baseSymbolicEvaluationEnvironment != null;
		return baseSymbolicEvaluationEnvironment;
	}

	public @NonNull CommonSubExpressionAnalysis getCSEAnalysis() {
		return cseAnalysis;
	}

	public @NonNull CSEElement getCSEElement(@NonNull TypedElement element) {
		return cseAnalysis.getCSEElement(element);
	}

	public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return environmentFactory;
	}

	public @NonNull ExecutorInternal getExecutor() {
		return executor;
	}

//	public @NonNull ExpressionInOCL getExpressionInOCL() {
//		return expressionInOCL;
//	}

	private @Nullable Hypothesis getHypothesis(@NonNull Iterable<@NonNull TypedElement> typedElements, @NonNull Class<?> hypothesisClass) {
		Map<@NonNull TypedElement, @NonNull List<@NonNull Hypothesis>> typedElement2hypotheses2 = typedElement2hypotheses;
		if (typedElement2hypotheses2 == null) {
			typedElement2hypotheses = typedElement2hypotheses2 = new HashMap<>();
		}
		Hypothesis theHypothesis = null;
		for (@NonNull TypedElement typedElement : typedElements) {
			List<@NonNull Hypothesis> hypotheses = typedElement2hypotheses2.get(typedElement);
			if (hypotheses != null) {
				for (@NonNull Hypothesis hypothesis : hypotheses) {
					if (hypothesis.getClass() == hypothesisClass) {
						if (theHypothesis == null) {
							theHypothesis = hypothesis;
						}
						else {
							assert theHypothesis == hypothesis;
						}
					}
				}
			}
		}
		return theHypothesis;
	}

/*	private @NonNull List<@NonNull Hypothesis> getHypotheses(@NonNull Iterable<@NonNull TypedElement> typedElements) {
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
	} */

	public @Nullable List<@NonNull HypothesizedSymbolicEvaluationEnvironment> getHypothesizedEvaluationEnvironments() {
		return hypothesizedEvaluationEnvironments;
	}

	public abstract @Nullable String getIncompatibility(@NonNull HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment);

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

	public @Nullable SymbolicValue getMayBeInvalidValue(@NonNull TypeId typeid, boolean mayBeNull) {
		List<@NonNull SymbolicUnknownValue> unknownValues = typeid2unknownValues.get(typeid);
		if (unknownValues == null) {
			unknownValues = new ArrayList<>();
			typeid2unknownValues.put(typeid, unknownValues);
		}
		for (@NonNull SymbolicUnknownValue unknownValue : unknownValues) {
			if (unknownValue.mayBeNull() == mayBeNull) {
				return unknownValue;
			}
		}
		SymbolicUnknownValue unknownValue = createUnknownValue(typeid, mayBeNull, true);
		unknownValues.add(unknownValue);
		return unknownValue;
	}

	public @NonNull SymbolicEvaluationEnvironment getSymbolicEvaluationEnvironment() {
		return getBaseSymbolicEvaluationEnvironment().getSymbolicEvaluationEnvironment();
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

	public @NonNull SymbolicValue getUnknownValue(@NonNull TypedElement typedElement, boolean mayBeNull, boolean mayBeInvalid) {
		SymbolicValue symbolicValue = getBaseSymbolicEvaluationEnvironment().basicGetSymbolicValue(typedElement);
		if (symbolicValue instanceof SymbolicUnknownValue) {
			assert symbolicValue.getTypeId() == typedElement.getTypeId();
			if (!mayBeNull) {
				if (symbolicValue.mayBeNull()) {
					symbolicValue = AbstractSymbolicRefinedValue.createExceptValue(symbolicValue, null);
				}
			}
			else {
				assert symbolicValue.mayBeNull();
			}
			if (!mayBeInvalid) {
				if (symbolicValue.mayBeInvalid()) {
					symbolicValue = AbstractSymbolicRefinedValue.createExceptValue(symbolicValue, ValueUtil.INVALID_VALUE);
				}
			}
			else {
				assert symbolicValue.mayBeInvalid();
			}
			return symbolicValue;
		}
		else {
			return createUnknownValue(typedElement.getTypeId(), mayBeNull, mayBeInvalid);
		}
	}

	private boolean isCanceled() {
		return executor.isCanceled();
	}
}
