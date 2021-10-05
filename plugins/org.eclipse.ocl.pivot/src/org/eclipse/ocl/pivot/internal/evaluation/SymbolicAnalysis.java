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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationHaltedException;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicCollectionContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent.SymbolicMapContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicKnownValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUnknownValue;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
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
 * @since 1.17
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
	//	System.out.println(height + "- " + hypothesis);		// XXX
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

	public @Nullable String analyzeExpression(@NonNull ExpressionInOCL expressionInOCL, @Nullable Object selfObject, @Nullable Object resultObject, @Nullable Object @Nullable [] parameters) {
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
		BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment2 = new BaseSymbolicEvaluationEnvironment(this);
		this.baseSymbolicEvaluationEnvironment = baseSymbolicEvaluationEnvironment2;
		executor.initializeEvaluationEnvironment(expressionInOCL);
		cseAnalysis.analyze(expressionInOCL);
		this.analysisIncompatibility = baseSymbolicEvaluationEnvironment2.analyze(expressionInOCL, selfObject, resultObject, parameters);
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

	protected @Nullable String analyzeInvariants(org.eclipse.ocl.pivot.@NonNull Class selfClass, @NonNull Iterable<@NonNull ExpressionInOCL> expressionsInOCL, @Nullable Object selfObject) {
		if (isCanceled()) {
			throw new EvaluationHaltedException("Canceled");
		}
		if (SymbolicAnalysis.HYPOTHESIS.isActive()) {
			StringBuilder s = new StringBuilder();
			s.append("Analyzing: for: ");
			ValueUtil.toString(selfObject, s, 0);
			for (@NonNull ExpressionInOCL expressionInOCL : expressionsInOCL) {
				s.append("\n\t");
				s.append(expressionInOCL);
			}
			SymbolicAnalysis.HYPOTHESIS.println(s.toString());
		}
		BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment2 = new BaseSymbolicEvaluationEnvironment(this);
		this.baseSymbolicEvaluationEnvironment = baseSymbolicEvaluationEnvironment2;
		executor.initializeEvaluationEnvironment(selfClass);
		for (@NonNull ExpressionInOCL expressionInOCL : expressionsInOCL) {
			cseAnalysis.analyze(expressionInOCL);
		}
		this.analysisIncompatibility = baseSymbolicEvaluationEnvironment2.analyze(expressionsInOCL, selfObject);
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

	public @NonNull String createConstantName() {
		return "k#" + constantCounter++;
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

	public @NonNull SymbolicUnknownValue createUnknownValue(@NonNull TypeId typeId, @Nullable SymbolicReason mayBeNullReason, @Nullable SymbolicReason mayBeInvalidReason) {
		return new SymbolicUnknownValue(createVariableName(), typeId, mayBeNullReason, mayBeInvalidReason);
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

	public abstract @Nullable String getIncompatibility(@NonNull HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment, @NonNull TypedElement hypothesizedTypedElement);

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
				String constantName = createConstantName();
				TypeId typeId = type.getTypeId();
				SymbolicContent content = null;
				if (boxedValue instanceof InvalidValue) {
				}
				else if (boxedValue instanceof CollectionValue) {
					CollectionValue collectionValue = (CollectionValue)boxedValue;
					content = new SymbolicCollectionContent("c#" + constantName + "%", collectionValue.getTypeId(), collectionValue);
					content.setSize(getKnownValue(collectionValue.isEmpty() ? ValueUtil.ZERO_VALUE : ValueUtil.ONE_VALUE));
				}
				else if (boxedValue instanceof MapValue) {
					MapValue mapValue = (MapValue)boxedValue;
					content = new SymbolicMapContent("m#" + constantName + "%", (MapTypeId)typeId, mapValue);
					content.setSize(getKnownValue(((MapValue)boxedValue).isEmpty() ? ValueUtil.ZERO_VALUE : ValueUtil.ONE_VALUE));
				}
				symbolicKnownValue = new SymbolicKnownValue(constantName, typeId, boxedValue, content);
				knownValue2symbolicValue.put(boxedValue, symbolicKnownValue);
			}
		}
		return symbolicKnownValue;
	}

	public @Nullable SymbolicValue getMayBeInvalidValue(@NonNull TypeId typeid, @Nullable SymbolicReason mayBeNullReason, @NonNull SymbolicReason mayBeInvalidReason) {
		List<@NonNull SymbolicUnknownValue> unknownValues = typeid2unknownValues.get(typeid);
		if (unknownValues == null) {
			unknownValues = new ArrayList<>();
			typeid2unknownValues.put(typeid, unknownValues);
		}
		for (@NonNull SymbolicUnknownValue unknownValue : unknownValues) {
		//	if (unknownValue.mayBeNull() == (mayBeNullReason != null)) {
			if (ClassUtil.safeEquals(unknownValue.mayBeNullReason(), mayBeNullReason)
			 && ClassUtil.safeEquals(unknownValue.mayBeInvalidReason(), mayBeInvalidReason)) {
				return unknownValue;
			}
		}
		SymbolicUnknownValue unknownValue = createUnknownValue(typeid, mayBeNullReason, mayBeInvalidReason);
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

	public @NonNull SymbolicValue getUnknownValue(@NonNull TypedElement typedElement, @Nullable SymbolicReason mayBeNullReason, @Nullable SymbolicReason mayBeInvalidReason) {
		SymbolicValue symbolicValue = getBaseSymbolicEvaluationEnvironment().basicGetSymbolicValue(typedElement);
		if (symbolicValue instanceof SymbolicUnknownValue) {
			assert symbolicValue.getTypeId() == typedElement.getTypeId();
			if (mayBeNullReason == null) {
				if (symbolicValue.mayBeNull()) {
					symbolicValue = AbstractSymbolicRefinedValue.createExceptValue(symbolicValue, null);
				}
			}
			else {
				assert symbolicValue.mayBeNull();
			}
			if (mayBeInvalidReason == null) {
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
			return createUnknownValue(typedElement.getTypeId(), mayBeNullReason, mayBeInvalidReason);
		}
	}

	private boolean isCanceled() {
		return executor.isCanceled();
	}

	protected static abstract class SymbolicAbstractClassAnalysis extends SymbolicAnalysis
	{
		protected final org.eclipse.ocl.pivot.@NonNull Class primaryClass;
		private @Nullable Iterable<@NonNull ExpressionInOCL> invariantBodies = null;
		protected @Nullable String incompatibility;

		/**
		 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
		 */
		protected SymbolicAbstractClassAnalysis(org.eclipse.ocl.pivot.@NonNull Class primaryClass,
				@NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
			super(environmentFactory, modelManager);
			this.primaryClass = primaryClass;
		}

		public void analyzeInvariants() {
			assert incompatibility == null;
			SymbolicValue selfVariable = new SymbolicUnknownValue("self", primaryClass.getTypeId(), null, null);
			Iterable<@NonNull ExpressionInOCL> invariantBodies2 = invariantBodies;
			assert invariantBodies2 == null;
			try {
				this.invariantBodies = invariantBodies2 = gatherInvariantBodies();
			}
			catch (ParserException e) {
				incompatibility = e.toString();
				return;
			}
			analyzeInvariants(primaryClass, invariantBodies2, selfVariable);
		}

		protected abstract @NonNull List<@NonNull ExpressionInOCL> gatherInvariantBodies() throws ParserException;

		public @NonNull SymbolicAnalysis getSymbolicAnalysis(@NonNull ExpressionInOCL expressionInOCL) {
			return new SymbolicExpressionAnalysis(expressionInOCL, environmentFactory, getExecutor().getModelManager());
		}

		@Override
		public @Nullable String getIncompatibility(@NonNull HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment, @NonNull TypedElement hypothesizedTypedElement) {
			if (incompatibility != null) {
				return incompatibility;
			}
			Iterable<@NonNull ExpressionInOCL> invariantBodies2 = invariantBodies;
			if (invariantBodies2 != null) {
				ExpressionInOCL hypothesizedExpressionInOCL = PivotUtil.getContainingExpressionInOCL(hypothesizedTypedElement);
				for (@NonNull ExpressionInOCL invariantBody : invariantBodies2) {
					SymbolicValue symbolicValue = hypothesizedSymbolicEvaluationEnvironment.getSymbolicValue(invariantBody);
					String incompatibility = symbolicValue.asIncompatibility();
					if (incompatibility != null) {
						return incompatibility;
					}
					else if (invariantBody != hypothesizedExpressionInOCL) {
						if (symbolicValue.isFalse()) {
							return "Incompatible " + ((NamedElement)invariantBody.eContainer()).getName();
						}
					}
				}
			}
			return null;
		}

		protected void toString(@NonNull StringBuilder s, org.eclipse.ocl.pivot.@NonNull Class asClass) {
			BaseSymbolicEvaluationEnvironment evaluationEnvironment = getBaseSymbolicEvaluationEnvironment();
			for (@NonNull Constraint invariant : PivotUtil.getOwnedInvariants(asClass)) {
				LanguageExpression ownedSpecification = invariant.getOwnedSpecification();
				if (ownedSpecification != null) {
					try {
						ExpressionInOCL expressionInOCL = environmentFactory.parseSpecification(ownedSpecification);
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
					} catch (ParserException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					}
				}
			}
		}
	}

	public static class SymbolicClassAnalysis extends SymbolicAbstractClassAnalysis
	{
		/**
		 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
		 */
		public SymbolicClassAnalysis(org.eclipse.ocl.pivot.@NonNull Class selfClass, @NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
			super(selfClass, environmentFactory, modelManager);
//			this.expressionsInOCL = expressionsInOCL;
		}

		@Override
		protected @NonNull List<@NonNull ExpressionInOCL> gatherInvariantBodies() throws ParserException {
			List<@NonNull ExpressionInOCL> invariantBodies2 = new ArrayList<>();
			for (@NonNull Constraint invariant : PivotUtil.getOwnedInvariants(primaryClass)) {
				LanguageExpression ownedSpecification = invariant.getOwnedSpecification();
				if (ownedSpecification != null) {
					invariantBodies2.add(environmentFactory.parseSpecification(ownedSpecification));
				}
			}
			return invariantBodies2;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			toString(s, primaryClass);
		/*	List<@NonNull CSEElement> keys = new ArrayList<>(cseElement2symbolicValue.keySet());
			if (keys.size() > 1) {
				Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
			}
			s.append("\t" + keys.size() + " cses");
			for (@NonNull CSEElement key : keys) {
				Object value = cseElement2symbolicValue.get(key);
				s.append("\n\t\t" + key + " => " + value);
			} */
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

	public static class SymbolicCompleteClassAnalysis extends SymbolicAbstractClassAnalysis
	{
		protected final @NonNull CompleteClass completeClass;

		/**
		 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
		 */
		public SymbolicCompleteClassAnalysis(@NonNull CompleteClass completeClass, @NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
			super(completeClass.getPrimaryClass(), environmentFactory, modelManager);
			this.completeClass = completeClass;
//			this.expressionsInOCL = expressionsInOCL;
		}

		@Override
		protected @NonNull List<@NonNull ExpressionInOCL> gatherInvariantBodies() throws ParserException {
			List<@NonNull ExpressionInOCL> invariantBodies2 = new ArrayList<>();
			for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
				for (@NonNull Constraint invariant : PivotUtil.getOwnedInvariants(partialClass)) {
					LanguageExpression ownedSpecification = invariant.getOwnedSpecification();
					if (ownedSpecification != null) {
						invariantBodies2.add(environmentFactory.parseSpecification(ownedSpecification));
					}
				}
			}
			return invariantBodies2;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			for (org.eclipse.ocl.pivot.@NonNull Class partialClass : PivotUtil.getPartialClasses(completeClass)) {
				toString(s, partialClass);
			}
		/*	List<@NonNull CSEElement> keys = new ArrayList<>(cseElement2symbolicValue.keySet());
			if (keys.size() > 1) {
				Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
			}
			s.append("\t" + keys.size() + " cses");
			for (@NonNull CSEElement key : keys) {
				Object value = cseElement2symbolicValue.get(key);
				s.append("\n\t\t" + key + " => " + value);
			} */
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

	public static class SymbolicExpressionAnalysis extends SymbolicAnalysis
	{
		protected final @NonNull ExpressionInOCL expressionInOCL;

		/**
		 * Initializes the symbolic analysis of expressionInOCL that delegates to a non-symbolic evaluation visitor.
		 */
		public SymbolicExpressionAnalysis(@NonNull ExpressionInOCL expressionInOCL,
				@NonNull EnvironmentFactoryInternalExtension environmentFactory, @NonNull ModelManager modelManager) {
			super(environmentFactory, modelManager);
			this.expressionInOCL = expressionInOCL;
		}

		@Override
		@Nullable
		public String getIncompatibility(@NonNull HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment, @NonNull TypedElement hypothesizedTypedElement) {
			SymbolicValue symbolicValue = hypothesizedSymbolicEvaluationEnvironment.getSymbolicValue(expressionInOCL);
			return symbolicValue.asIncompatibility();
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
		/*	List<@NonNull CSEElement> keys = new ArrayList<>(cseElement2symbolicValue.keySet());
			if (keys.size() > 1) {
				Collections.sort(keys, NameUtil.TO_STRING_COMPARATOR);
			}
			s.append("\t" + keys.size() + " cses");
			for (@NonNull CSEElement key : keys) {
				Object value = cseElement2symbolicValue.get(key);
				s.append("\n\t\t" + key + " => " + value);
			} */
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
}
