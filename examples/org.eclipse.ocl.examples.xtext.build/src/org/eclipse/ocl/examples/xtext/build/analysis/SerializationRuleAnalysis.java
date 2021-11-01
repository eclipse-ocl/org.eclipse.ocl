/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.build.analysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.LocatorHelper.LocatorSwitch;
import org.eclipse.ocl.examples.xtext.build.elements.AbstractUnassignedSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AlternativeAssignedKeywordsSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AlternativeAssignsSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedRuleCallSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.CompositeSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SequenceSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationNode;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsUtils;
import org.eclipse.ocl.examples.xtext.idioms.Locator;
import org.eclipse.ocl.examples.xtext.idioms.ReferredLocator;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.idioms.impl.LocatorImpl;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder.DiagnosticStringBuilderWithHelper;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.Nameable;
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.RuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEAttributeSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEReferenceSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermInteger;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermUnsupported;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.SerializationAttribute;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.SerializationEnumeratedAttribute;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.SerializationFeature;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.SerializationReference;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRule.SerializationSimpleAttribute;
import org.eclipse.ocl.examples.xtext.serializer.SerializationRuleHelper;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString.ToDebugStringable;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * A SerializationRuleAnalysis supervises the analysis leading to the MetaData for a SerializationRule.
 */
public class SerializationRuleAnalysis implements Nameable, ToDebugStringable, RuleMatch, SerializationRuleHelper
{
	private static final @NonNull Map<@NonNull SerializationRuleAnalysis, @NonNull SerializationRule> debugMap = new HashMap<>();

	protected final @NonNull ParserRuleAnalysis ruleAnalysis;
	protected final @NonNull GrammarAnalysis grammarAnalysis;
	protected final @NonNull SerializationNode rootSerializationNode;
	protected final @NonNull EClass producedEClass;
	protected final int variantNumber;
	protected final @NonNull String variantName;

	@SuppressWarnings("unused")			// Used in the debugger
	private final @NonNull ToDebugString toDebugString = new ToDebugString(this){};

	/**
	 * The variables, expressions and solutions to determine if an actual user element matches.
	 */
	private boolean hasMatchAnalyses = false;

	/**
	 * The subidioms to decorate each node during serialization.
	 */
	private @Nullable Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms = null;

	private @Nullable Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues = null;
	private @Nullable Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes = null;

	/**
	 * The assigned EAttributes to which an orthogonal String establishes an enumerated term.
	 */
	private @Nullable Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> eAttribute2enumerationValue2grammarCardinality = null;

	/**
	 * The assigned EReferences to which a not necessarily orthogonal RuleCall establishes a discriminated term.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality = null;
	/**
	 * The per-feature expression that (re-)computes the required number of assigned slots from the solved
	 * cardinality variables. This is checked against the actual number of slots in an actual user element.
	 */
	protected final @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> eStructuralFeature2requiredSlotsExpression = new HashMap<>();

	/**
	 * The per-variable solution expression that computes the variable's value from the actual number of slots of an actual user element.
	 *
	 * Lazily populated as solutions found.
	 */
	protected final @NonNull Map<@NonNull Integer, @NonNull SerializationMatchTerm> variableIndex2solution = new HashMap<>();

	/**
	 * The ordered sequence of assign/check instructions to evaluate at run-time to realize the computation of
	 * each solution for its variable.
	 */
	protected final @NonNull List<@NonNull SerializationMatchStep> matchSteps = new ArrayList<>();

	/**
	 * The CardinalityVariable for each node, unless always exactly 1.
	 *
	 * Only used during serialization - replae by known indexes.
	 */
	private final @NonNull Map<@NonNull SerializationNode, @NonNull CardinalityVariable> node2variable = new HashMap<>();

	/**
	 * The Node which each cardinality variable defines the iteration for the inverse of node2variable.
	 *
	 * ?? Only used as a debugging convenience ??
	 */
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull SerializationNode> variable2node = new HashMap<>();

	/**
	 * The Node which each cardinality variable defines the iteration for; the inverse of node2variable.
	 *
	 * ?? Only used as a debugging convenience ??
	 */
	private final @NonNull List<@NonNull AssignedSerializationNode> assignedSerializationNodes = new ArrayList<>();

	private @Nullable SerializationRule serializationRule = null;

	private @Nullable Map<@NonNull SerializationNode, @Nullable SerializationNode> node2parent = new HashMap<>();

	public SerializationRuleAnalysis(@NonNull ParserRuleAnalysis ruleAnalysis, int variantNumber, @NonNull SerializationNode rootSerializationNode) {
		this.ruleAnalysis = ruleAnalysis;
		this.grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
		this.rootSerializationNode = rootSerializationNode;
		this.producedEClass = refineProducedEClass(rootSerializationNode, ruleAnalysis.getReturnedEClass());
		this.variantNumber = variantNumber;
		this.variantName = ruleAnalysis.getName() + "-" + variantNumber;
		assert rootSerializationNode.onlyRootUnassignedSerializationRuleCall(true);
	}

	/**
	 * Accumulate an additional matchTerm expression for a cardinalityVariable.
	 */
	public void addMatchTerm(@Nullable CardinalityVariable cardinalityVariable, @NonNull SerializationMatchTerm matchTerm) {
		SerializationMatchStep newStep;
		if (cardinalityVariable != null) {
			int cardinalityVariableIndex = cardinalityVariable.getIndex();
		//	assert !cardinalityVariable.isOne();
			boolean isAssigned = true;
			for (@NonNull SerializationMatchStep matchStep : matchSteps) {
				if (matchStep.isAssignTo(cardinalityVariableIndex)) {
					isAssigned = false;
					break;
				}
			}
			if (isAssigned) {
				newStep = new SerializationMatchStep.MatchStep_Assign(cardinalityVariableIndex, matchTerm);
				variableIndex2solution.put(cardinalityVariableIndex, matchTerm);
			}
			else {
				newStep = new SerializationMatchStep.MatchStep_ValueCheck(cardinalityVariableIndex, matchTerm);
			}
		}
		else {
			newStep = new SerializationMatchStep.MatchStep_Assert(matchTerm);
		}
		matchSteps.add(newStep);
	}

	private void analyzeAssignment(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue, @NonNull GrammarCardinality netGrammarCardinality) {
		Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> eAttribute2enumerationValue2grammarCardinality2 = eAttribute2enumerationValue2grammarCardinality;
		if (eAttribute2enumerationValue2grammarCardinality2 == null) {
			eAttribute2enumerationValue2grammarCardinality = eAttribute2enumerationValue2grammarCardinality2 = new HashMap<>();
		}
		Map<@Nullable EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = SerializationUtils.maybeNull(eAttribute2enumerationValue2grammarCardinality2.get(eAttribute));
		if (enumerationValue2grammarCardinality == null) {
			enumerationValue2grammarCardinality = new HashMap<>();
			eAttribute2enumerationValue2grammarCardinality2.put(eAttribute, enumerationValue2grammarCardinality);
		}
		GrammarCardinality oldGrammarCardinality = SerializationUtils.maybeNull(enumerationValue2grammarCardinality.get(enumerationValue));
		GrammarCardinality newGrammarCardinality = refineGrammarCardinality(netGrammarCardinality, oldGrammarCardinality);
		enumerationValue2grammarCardinality.put(enumerationValue, newGrammarCardinality);
	}

	private void analyzeAssignment(@NonNull EReference eReference, @NonNull GrammarRuleVector grammarRuleVector, @NonNull GrammarCardinality netGrammarCardinality) {
		Map<@NonNull EReference, @NonNull Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality2 = eReference2ruleAnalysis2grammarCardinality;
		if (eReference2ruleAnalysis2grammarCardinality2 == null) {
			eReference2ruleAnalysis2grammarCardinality = eReference2ruleAnalysis2grammarCardinality2 = new HashMap<>();
		}
		Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = SerializationUtils.maybeNull(eReference2ruleAnalysis2grammarCardinality2.get(eReference));
		if (ruleAnalysis2grammarCardinality == null) {
			ruleAnalysis2grammarCardinality = new HashMap<>();
			eReference2ruleAnalysis2grammarCardinality2.put(eReference, ruleAnalysis2grammarCardinality);
		}
		for (int ruleIndex : grammarRuleVector) {
			@NonNull AbstractRuleAnalysis ruleAnalysis2 = grammarAnalysis.getRuleAnalysis(ruleIndex);
			if (ruleAnalysis2 instanceof ParserRuleAnalysis) {
				GrammarCardinality oldGrammarCardinality = SerializationUtils.maybeNull(ruleAnalysis2grammarCardinality.get(ruleAnalysis2));
				GrammarCardinality newGrammarCardinality = refineGrammarCardinality(netGrammarCardinality, oldGrammarCardinality);
				ruleAnalysis2grammarCardinality.put((ParserRuleAnalysis) ruleAnalysis2, newGrammarCardinality);
			}
		}
	}

	/**
	 * Create/update the sum-of-products expression for the feature assigned by assignedSerializationNode to include
	 * a product for assignedSerializationNode's cardinality variable and for all surrounding sequences in outerVariables.
	 */
	private void analyzeAssignment(@NonNull AssignedSerializationNode assignedSerializationNode, @NonNull Iterable<@NonNull CardinalityVariable> cardinalityVariables,
			@NonNull GrammarCardinality netGrammarCardinality) {
		EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
		//
		//	Accumulate enumerated attributes
		//
		CardinalityExpression cardinalityExpression = SerializationUtils.maybeNull(eStructuralFeature2requiredSlotsExpression.get(eStructuralFeature));
		if (eStructuralFeature instanceof EAttribute) {
			EAttribute eAttribute = (EAttribute)eStructuralFeature;
			EnumerationValue enumerationValue = assignedSerializationNode.getEnumerationValue();
			analyzeAssignment(eAttribute, enumerationValue, netGrammarCardinality);
			//
			//	Get / create the  CardinalityExpression accumulating a sum of products for this assigned feature.
			//
			if (cardinalityExpression == null) {
				String name = String.format("E%02d", eStructuralFeature2requiredSlotsExpression.size());
				assert name != null;
				if (enumerationValue.isNull()) {
					cardinalityExpression = new CardinalityExpression.EStructuralFeatureCardinalityExpression(name, eAttribute);
				}
				else {
					cardinalityExpression = new CardinalityExpression.EAttributeCardinalityExpression(name, eAttribute);
				}
				eStructuralFeature2requiredSlotsExpression.put(eAttribute, cardinalityExpression);
			}
			if (!enumerationValue.isNull()) {
				cardinalityExpression = cardinalityExpression.getCardinalityExpression(grammarAnalysis, enumerationValue);
			}
		}
		//
		//	Accumulate discriminated references
		//
		else {
			EReference eReference = (EReference)eStructuralFeature;
			GrammarRuleVector grammarRuleVector = assignedSerializationNode.getAssignedGrammarRuleVector();
			analyzeAssignment(eReference, grammarRuleVector, netGrammarCardinality);
			//
			//	Get / create the  CardinalityExpression accumulating a sum of products for this assigned feature.
			//
			cardinalityExpression = SerializationUtils.maybeNull(eStructuralFeature2requiredSlotsExpression.get(eReference));
			if (cardinalityExpression == null) {
				String name = String.format("E%02d", eStructuralFeature2requiredSlotsExpression.size());
				assert name != null;;
			//	if (grammarRuleVector == null) {
					cardinalityExpression = new CardinalityExpression.EStructuralFeatureCardinalityExpression(name, eReference);
			//	}
			//	else {
			//		cardinalityExpression = new CardinalityExpression.EReferenceCardinalityExpression(name, eReference);
			//	}
				eStructuralFeature2requiredSlotsExpression.put(eReference, cardinalityExpression);
			}
			//
			//	Add cardinalityVariables as a further product term to the sum of products.
			//
		//	if (grammarRuleVector != null) {
		//		cardinalityExpression = cardinalityExpression.getCardinalityExpression(grammarAnalysis, grammarRuleVector);
		//	}
		}
		//
		//	Add cardinalityVariables as a further product term to the sum of products.
		//
		cardinalityExpression.addMultiplicityProduct(cardinalityVariables, assignedSerializationNode.getAssignedGrammarRuleVector());
	}

	public void analyzeMatches() {
		if (!hasMatchAnalyses) {
			hasMatchAnalyses = true;
			//
			//	Traverse the chosen serialization tree path to determine the
			//	cardinality variables and expressions to be solved to characterize the serialization.
			//
			analyzeSerialization();
			if (!isOrderedAndHeterogeneous()) {		// Can disable this to force everything to Runtime fallback
				//
				//	Analyze the cardinality expressions to determine the steps that compute/validate each cardinality variable.
				//
				generateMatchSteps();
			}
			else {
			//	matchSteps.add(new SerializationMatchStep.MatchStep_Runtime()); -- empty list defaults to Runtime
			}
		}
	}

	protected void analyzeSerialization() {
		analyzeSerialization(rootSerializationNode, new Stack<@NonNull CardinalityVariable>(), GrammarCardinality.ONE);
	}
	protected void analyzeSerialization(@NonNull SerializationNode serializationNode, @NonNull Stack<@NonNull CardinalityVariable> cardinalityVariables, @NonNull GrammarCardinality outerGrammarCardinality) {
		//
		//	Allocate a CardinalityVariable for an indeterminate multiplicity.
		//
		CardinalityVariable cardinalityVariable = null;
		if (!serializationNode.isRedundant()) {
			GrammarCardinality grammarCardinality = serializationNode.getGrammarCardinality();
			if (!grammarCardinality.isOne()) {
				int index = variable2node.size();
			//	String name = String.format("C%02d", index);
			//	assert name != null;
				cardinalityVariable = new CardinalityVariable(index, grammarCardinality);
				CardinalityVariable old2 = SerializationUtils.maybeNull(node2variable.put(serializationNode, cardinalityVariable));
				assert old2 == null;
				SerializationNode old3 = SerializationUtils.maybeNull(variable2node.put(cardinalityVariable, serializationNode));
				assert old3 == null;
			}
		}
		GrammarCardinality innerGrammarCardinality = serializationNode.getGrammarCardinality();
		GrammarCardinality netGrammarCardinality = GrammarCardinality.max(innerGrammarCardinality, outerGrammarCardinality);
		//
		//	Create the feature size expressions for an assignment
		//
		if (cardinalityVariable != null) {
			cardinalityVariables.push(cardinalityVariable);
		}
		if (serializationNode instanceof AssignedSerializationNode) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			assignedSerializationNodes.add(assignedSerializationNode);
			analyzeAssignment(assignedSerializationNode, cardinalityVariables, netGrammarCardinality);
		}
		//
		//	Recurse for sequences
		//
		else if (serializationNode instanceof SequenceSerializationNode) {
			SequenceSerializationNode sequenceSerializationNode = (SequenceSerializationNode)serializationNode;
			for (@NonNull SerializationNode nestedSerializationNode : sequenceSerializationNode.getSerializationNodes()) {
				analyzeSerialization(nestedSerializationNode, cardinalityVariables, netGrammarCardinality);
			}
		}
		if (cardinalityVariable != null) {
			cardinalityVariables.pop();
		}
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(int cardinalityVariableIndex) {
		SerializationMatchTerm solution = SerializationUtils.maybeNull(variableIndex2solution.get(cardinalityVariableIndex));
		return solution != null ? solution.basicGetIntegerSolution(this) : null;
	}

	public @Nullable SerializationMatchTerm basicGetMatchTerm(@NonNull CardinalityVariable cardinalityVariable) {
		return variableIndex2solution.get(cardinalityVariable.getIndex());
	}

	public @NonNull SerializationAttribute @Nullable [] basicGetSerializationAttributes() {
		Map<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> eAttribute2enumerationValue2grammarCardinality2 = eAttribute2enumerationValue2grammarCardinality;
		if (eAttribute2enumerationValue2grammarCardinality2 == null) {
			return null;
		}
		@NonNull SerializationAttribute [] serializationAttributes = new @NonNull SerializationAttribute[eAttribute2enumerationValue2grammarCardinality2.size()];
		int i1 = 0;
		for (Map.Entry<@NonNull EAttribute, @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality>> entry1 : eAttribute2enumerationValue2grammarCardinality2.entrySet()) {
			EAttribute eAttribute = SerializationUtils.maybeNull(entry1.getKey());
			assert eAttribute != null;
			@SuppressWarnings("null") @NonNull Map<@Nullable EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = entry1.getValue();
			CardinalityExpression cardinalityExpression = SerializationUtils.maybeNull(eStructuralFeature2requiredSlotsExpression.get(eAttribute));
			boolean needsDefault = SerializationUtils.nonNullState(cardinalityExpression).isOne();
			List<@Nullable EnumerationValue> enumerationValueList = new ArrayList<>(enumerationValue2grammarCardinality.keySet());
			EnumerationValue firstEnumerationValue;
			if ((enumerationValueList.size() == 1) && ((firstEnumerationValue = enumerationValueList.get(0)) != null) && firstEnumerationValue.isNull()) {
				GrammarCardinality grammarCardinality = enumerationValue2grammarCardinality.get(firstEnumerationValue);
				assert grammarCardinality != null;
				serializationAttributes[i1++] = new SerializationSimpleAttribute(eAttribute, needsDefault, grammarCardinality);
			}
			else {
				@SuppressWarnings("null") List<@NonNull EnumerationValue> enumerationValueList2 = (List<@NonNull EnumerationValue>)enumerationValueList;
				Collections.sort(enumerationValueList2, SerializationUtils.NAMEABLE_COMPARATOR);
				int iMax = enumerationValueList2.size();
				@NonNull EnumerationValue [] enumerationValues = new @NonNull EnumerationValue[iMax];
				@NonNull GrammarCardinality [] grammarCardinalities = new @NonNull GrammarCardinality[iMax];
				for (int i = 0; i < iMax; i++) {
					EnumerationValue enumerationValue = SerializationUtils.maybeNull(enumerationValueList2.get(i));
					assert enumerationValue != null;
					enumerationValues[i] = enumerationValue;
					GrammarCardinality grammarCardinaity = SerializationUtils.maybeNull(enumerationValue2grammarCardinality.get(enumerationValue));
					assert grammarCardinaity != null;
					grammarCardinalities[i] = grammarCardinaity;
				}
				serializationAttributes[i1++] = new SerializationEnumeratedAttribute(eAttribute, needsDefault, enumerationValues, grammarCardinalities);
			}
		}
		Arrays.sort(serializationAttributes, SerializationUtils.NAMEABLE_COMPARATOR);
		return serializationAttributes;
	}

	public @NonNull SerializationFeature @Nullable [] basicGetSerializationFeatures() {
		@NonNull SerializationAttribute @Nullable [] serializationAttributes = basicGetSerializationAttributes();
		@NonNull SerializationReference @Nullable [] serializationReferences = basicGetSerializationReferences();
		int attributesSize = serializationAttributes != null ? serializationAttributes.length : 0;
		int referencesSize = serializationReferences != null ? serializationReferences.length : 0;
		int featuresSize = attributesSize + referencesSize;
		if (featuresSize <= 0)  {
			return null;
		}
		@NonNull SerializationFeature[] serializationFeatures = new @NonNull SerializationFeature[featuresSize];
		int j = 0;
		for (int i = 0; i < attributesSize; i++) {
			assert serializationAttributes != null;
			serializationFeatures[j++] = serializationAttributes[i];
		}
		for (int i = 0; i < referencesSize; i++) {
			assert serializationReferences != null;
			serializationFeatures[j++] = serializationReferences[i];
		}
		return serializationFeatures;
	}

	public @NonNull SerializationReference @Nullable [] basicGetSerializationReferences() {
		Map<@NonNull EReference, @NonNull Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality2 = eReference2ruleAnalysis2grammarCardinality;
		if (eReference2ruleAnalysis2grammarCardinality2 == null) {
			return null;
		}
		@NonNull SerializationReference [] eReference2ruleValueIndex2grammarCardinality = new @NonNull SerializationReference[eReference2ruleAnalysis2grammarCardinality2.size()];
		int i1 = 0;
		for (Map.Entry<@NonNull EReference, @NonNull Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality>> entry1 : eReference2ruleAnalysis2grammarCardinality2.entrySet()) {
			@SuppressWarnings("null")
			@NonNull EReference eReference = entry1.getKey();
			@SuppressWarnings("null")
			@NonNull Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = entry1.getValue();
			int iMax = ruleAnalysis2grammarCardinality.size();
			int @NonNull [] grammarRuleIndexes = new int[iMax];
			@NonNull GrammarCardinality @NonNull [] grammarCardinalities = new @NonNull GrammarCardinality[iMax];
			int i2 = 0;
			List<@NonNull ParserRuleAnalysis> ruleAnalyses = new ArrayList<>(ruleAnalysis2grammarCardinality.keySet());
			Collections.sort(ruleAnalyses, SerializationUtils.NAMEABLE_COMPARATOR);
			for (@NonNull ParserRuleAnalysis ruleAnalysis : ruleAnalyses) {
				grammarRuleIndexes[i2] = ruleAnalysis.getIndex();
				GrammarCardinality grammarCardinality = SerializationUtils.maybeNull(ruleAnalysis2grammarCardinality.get(ruleAnalysis));
				assert grammarCardinality != null;
				grammarCardinalities[i2] = grammarCardinality;
				i2++;
			}
			GrammarRuleVector targetGrammarRuleVector = SerializationUtils.maybeNull(getEReference2AssignedRuleValueIndexes().get(eReference));
			eReference2ruleValueIndex2grammarCardinality[i1++] = new SerializationReference(eReference, targetGrammarRuleVector, grammarRuleIndexes, grammarCardinalities);
		}
		Arrays.sort(eReference2ruleValueIndex2grammarCardinality, SerializationUtils.NAMEABLE_COMPARATOR);
		return eReference2ruleValueIndex2grammarCardinality;
	}

	/**
	 * Return all the expressions that use any of variableGroup.
	 */
	protected @NonNull Iterable<@NonNull CardinalityExpression> computeExpressions(@NonNull Set<@NonNull CardinalityVariable> variableGroup,
			@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> variable2expressions) {
		Set<@NonNull CardinalityExpression> unresolvedExpressions = new HashSet<>();
		for (@NonNull CardinalityVariable variable : variableGroup) {
			Set<@NonNull CardinalityExpression> expressions = SerializationUtils.maybeNull(variable2expressions.get(variable));
			assert expressions != null;
			unresolvedExpressions.addAll(expressions);
		}
		return unresolvedExpressions;
	}

	/**
	 * Return the Map from each of expressions to its variables that lack a solution.
	 */
	protected @NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> computeExpression2unsolvedVariables(
			@NonNull Iterable<@NonNull CardinalityExpression> expressions) {
		Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables = new HashMap<>();
		for (@NonNull CardinalityExpression expression : expressions) {
			Iterable<@NonNull CardinalityVariable> unsolvedVariables = expression.getUnknownVariables(this);
			if (unsolvedVariables != null) {
				for (@NonNull CardinalityVariable variable : unsolvedVariables) {
					Set<@NonNull CardinalityVariable> variables = SerializationUtils.maybeNull(expression2unsolvedVariables.get(expression));
					if (variables == null) {
						variables = new HashSet<>();
						expression2unsolvedVariables.put(expression, variables);
					}
					variables.add(variable);
				}
			}
		}
		return expression2unsolvedVariables;
	}

	/**
	 * Return a Map from each variable to the set of variables that participate in non-independent expressions.
	 */
	protected @NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> computeUnsolvedVariableGroups(
			@NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> unsolvedVariable2expressions,
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables) {
		Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> unsolvedVariable2unresolvedVariables = new HashMap<>();
		List<@NonNull CardinalityVariable> workVariables = new ArrayList<>(unsolvedVariable2expressions.keySet());
		for (@NonNull CardinalityVariable workVariable : workVariables) {
			Set<@NonNull CardinalityVariable> unsolvedVariables = SerializationUtils.maybeNull(unsolvedVariable2unresolvedVariables.get(workVariable));
			if (unsolvedVariables == null) {
				unsolvedVariables = new HashSet<>();
				unsolvedVariable2unresolvedVariables.put(workVariable, unsolvedVariables);
				unsolvedVariables.add(workVariable);
				Set<@NonNull CardinalityExpression> expressions = SerializationUtils.maybeNull(unsolvedVariable2expressions.get(workVariable));
				assert expressions != null;
				for (@NonNull CardinalityExpression expression : expressions) {
					Set<@NonNull CardinalityVariable> moreUnsolvedVariables1 = SerializationUtils.maybeNull(expression2unsolvedVariables.get(expression));
					assert moreUnsolvedVariables1 != null;
					Set<@NonNull CardinalityVariable> moreUnsolvedVariables2 = new HashSet<>(moreUnsolvedVariables1);
					moreUnsolvedVariables2.removeAll(unsolvedVariables);
					for (@NonNull CardinalityVariable anotherUnsolvedVariable : moreUnsolvedVariables2) {
						unsolvedVariables.add(anotherUnsolvedVariable);
						unsolvedVariable2unresolvedVariables.put(anotherUnsolvedVariable, unsolvedVariables);
					}
				}
			}
		}
		return unsolvedVariable2unresolvedVariables;
	}

	/**
	 * Return the inverse of the Map from expressions to variables.
	 */
	protected @NonNull Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> computeVariable2expressions(
			@NonNull Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2variables) {
		Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> variable2expressions = new HashMap<>();
		for (@NonNull CardinalityExpression expression : expression2variables.keySet()) {
			Set<@NonNull CardinalityVariable> variables = SerializationUtils.maybeNull(expression2variables.get(expression));
			assert variables != null;
			for (@NonNull CardinalityVariable variable : variables) {
				Set<@NonNull CardinalityExpression> expressions = SerializationUtils.maybeNull(variable2expressions.get(variable));
				if (expressions == null) {
					expressions = new HashSet<>();
					variable2expressions.put(variable, expressions);
				}
				expressions.add(expression);
			}
		}
		return variable2expressions;
	}

	private @Nullable List<@NonNull AssignedSerializationNode> gatherAssignedSerializationNodes(@NonNull EReference eReference, @NonNull SerializationNode serializationNode, @Nullable List<@NonNull AssignedSerializationNode> assignedSerializationNodes) {
		if (serializationNode instanceof AssignedSerializationNode) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			if (assignedSerializationNode.getEStructuralFeature() == eReference) {
				if (assignedSerializationNodes == null) {
					assignedSerializationNodes = new ArrayList<>();
				}
				assignedSerializationNodes.add(assignedSerializationNode);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				assignedSerializationNodes = gatherAssignedSerializationNodes(eReference, nestedSerializationNode, assignedSerializationNodes);
			}
		}
		return assignedSerializationNodes;
	}

	/**
	 * Append the match steps necessary to validate the contained children.
	 */
	protected void generateContainmentChecks(@NonNull List<@NonNull SerializationMatchStep> steps) {
		Map<@NonNull EReference, @NonNull Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality>> eReference2ruleAnalysis2grammarCardinality2 = eReference2ruleAnalysis2grammarCardinality;
		if (eReference2ruleAnalysis2grammarCardinality2 != null) {
			List<@NonNull EReference> eContainments = null;
			for (@NonNull EReference eReference : eReference2ruleAnalysis2grammarCardinality2.keySet()) {
				if (eReference.isContainment()) {
					if (eContainments == null) {
						eContainments = new ArrayList<>();
					}
					eContainments.add(eReference);
				}
			}
			if (eContainments != null) {
				Collections.sort(eContainments, SerializationUtils.ENAMED_ELEMENT_COMPARATOR);
				for (@NonNull EReference eReference : eContainments) {
					Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality> value = eReference2ruleAnalysis2grammarCardinality2.get(eReference);
					assert value != null;
				//	@SuppressWarnings("null")
					GrammarRuleVector grammarRuleVector = new GrammarRuleVector();
					for (@NonNull ParserRuleAnalysis assignedRuleAnalysis : value.keySet()) {
						for (@NonNull ParserRuleAnalysis subRuleAnalysis : assignedRuleAnalysis.getSubRuleAnalysesClosure()) {
							grammarRuleVector.set(subRuleAnalysis.getIndex());
						}
					}
				//	assert assignedRuleAnalyses.size() > 0;
					steps.add(new SerializationMatchStep.MatchStep_RuleCheck(eReference, grammarRuleVector));
				}
			}
		}
	}

	/**
	 * Determine the match steps necessary to validate the use of the SerializationRule.
	 */
	protected void generateMatchSteps() {
		generateContainmentChecks(matchSteps);
		List<@NonNull CardinalityExpression> residualExpressions = getExpressionsToSolve();
		List<@NonNull CardinalityVariable> variables = Lists.newArrayList(getVariables());
		Collections.sort(variables, SerializationUtils.INDEXED_COMPARATOR);
		//
		//	Confirm that variables with a "1" solution were skipped.
		//
		for (@NonNull CardinalityVariable variable : variables) {
			assert !variable.isOne();
		}
		int oldSize;
		//
		//	Eliminate expressions that involve no unresolved variables or which provide a linear solution for a single variable.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
				if (residualExpression.generateLinear(this, false)) {
					residualExpressions.remove(i);
				}
			}
		} while (residualExpressions.size() < oldSize);
		//
		//	Eliminate expressions that involve no unresolved variables.
		//	assign 0/1 solutions for all optional cardinalities that are common factors to all other products.
		//		e.g. |F| = V1 + V1 * V2 can be solved as V2 = !F| -1 if V1 is optional.
		//
		boolean gotOne;
		do {
			oldSize = residualExpressions.size();
			gotOne = false;
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
				if (residualExpression.generateMayBeZeroCommonFactors(this, false)) {
					gotOne = true;
					break;
				}
			}
			if (gotOne) {
				for (int i = oldSize; --i >= 0; ) {
					CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
					if (residualExpression.generateLinear(this, false)) {
						residualExpressions.remove(i);
					}
				}
			}
		} while (gotOne || (residualExpressions.size() < oldSize));
		//
		//	Ditto, but consider mayBeMany for the case of gratuitous mayBeMany products.
		//
		do {
			oldSize = residualExpressions.size();
			gotOne = false;
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
				if (residualExpression.generateMayBeZeroCommonFactors(this, true)) {
					gotOne = true;
					break;
				}
			}
			if (gotOne) {
				for (int i = oldSize; --i >= 0; ) {
					CardinalityExpression residualExpression = SerializationUtils.nonNullState(residualExpressions.get(i));
					if (residualExpression.generateLinear(this, true)) {
						residualExpressions.remove(i);
					}
				}
			}
		} while (gotOne || (residualExpressions.size() < oldSize));
		/*
		//	Assign 0/1 solutions for all variables involved in a linear equation in the light of other solutions.
		//
		do {
			oldSize = residualExpressions.size();
			for (int i = oldSize; --i >= 0; ) {
				CardinalityExpression residualExpression = residualExpressions.get(i);
				if (residualExpression.analyzeTrivial(this)) {
					residualExpressions.remove(i);
				}
			}
		} while (residualExpressions.size() < oldSize); */
		if (residualExpressions.size() > 0) {
			Map<@NonNull CardinalityExpression, @NonNull Set<@NonNull CardinalityVariable>> expression2unsolvedVariables =
					computeExpression2unsolvedVariables(residualExpressions);
			Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityExpression>> unsolvedVariable2expressions =
					computeVariable2expressions(expression2unsolvedVariables);
			Map<@NonNull CardinalityVariable, @NonNull Set<@NonNull CardinalityVariable>> unsolvedVariable2unsolvedVariableGroups =
					computeUnsolvedVariableGroups(unsolvedVariable2expressions, expression2unsolvedVariables);

			List<@NonNull Set<@NonNull CardinalityVariable>> unsolvedVariableGroups = new ArrayList<>(new HashSet<>(unsolvedVariable2unsolvedVariableGroups.values()));
			for (@NonNull Set<@NonNull CardinalityVariable> unsolvedVariableGroup : unsolvedVariableGroups) {
				Iterable<@NonNull CardinalityExpression> unresolvedExpressions = computeExpressions(unsolvedVariableGroup, unsolvedVariable2expressions);
				int size = Iterables.size(unresolvedExpressions);
				if (size == 1) {
					CardinalityExpression residualExpression = SerializationUtils.nonNullState(unresolvedExpressions.iterator().next());
					/*if (residualExpression.generateMayBeZeroCommonFactors(this, true)) {
						// ok
					}
					else*/ if (residualExpression.generateRedundantProducts(this)) {
						// ok
					}
					else {
						SerializationMatchTermRuntime runtimeMatchTerm = new SerializationMatchTermRuntime(unsolvedVariableGroup, unresolvedExpressions);
						List<@NonNull CardinalityVariable> unsolvedVariables = new ArrayList<>(unsolvedVariableGroup);
						Collections.sort(unsolvedVariables, SerializationUtils.INDEXED_COMPARATOR);
						for (@NonNull CardinalityVariable unresolvedVariable : unsolvedVariableGroup) {
							addMatchTerm(unresolvedVariable, runtimeMatchTerm);
						}
					}
				}
				else {
					//
					//	assign run-time search solution to remaining expressions.
					//
					SerializationMatchTermRuntime runtimeMatchTerm = new SerializationMatchTermRuntime(unsolvedVariableGroup, unresolvedExpressions);
					for (@NonNull CardinalityVariable unresolvedVariable : unsolvedVariableGroup) {
						addMatchTerm(unresolvedVariable, runtimeMatchTerm);
					}
				}
			}
		}
		//
		// Assign solutions to gratuitous grammar terms.
		// FIXME need to encode residue for run-time resolution
		//
		for (@NonNull CardinalityVariable variable : variables) {
			assert !variable.isOne();
			if (basicGetMatchTerm(variable) == null) {
				if (residualExpressions.isEmpty()) {
					addMatchTerm(variable, new SerializationMatchTermInteger(variable.mayBeNone() ? 0 : 1));
				}
				else {
					addMatchTerm(variable, new SerializationMatchTermUnsupported());
				}
			}
		}
		//
		//	Prefix checks that not-serialized features are unused.
		//
		Set<@NonNull EStructuralFeature> eStructuralFeaturesSet = new HashSet<>();
		for (EStructuralFeature eFeature : producedEClass.getEAllStructuralFeatures()) {	// Gather serializeable features
			assert eFeature != null;
			if (!eFeature.isDerived() && !eFeature.isTransient() && !eFeature.isVolatile()) {
				if (eFeature instanceof EReference) {
					if (!((EReference)eFeature).isContainer()) {
						eStructuralFeaturesSet.add(eFeature);
					}
				}
				else {
					eStructuralFeaturesSet.add(eFeature);
				}
			}
		}
		for (@NonNull SerializationMatchStep matchStep : matchSteps) {						// Remove serialized features
			for (@NonNull SerializationMatchTerm matchTerm : matchStep.getMatchTermClosure()) {
				if (matchTerm instanceof SerializationMatchTermEAttributeSize) {
					eStructuralFeaturesSet.remove(((SerializationMatchTermEAttributeSize)matchTerm).getEAttribute());
				}
				else if (matchTerm instanceof SerializationMatchTermEReferenceSize) {
					eStructuralFeaturesSet.remove(((SerializationMatchTermEReferenceSize)matchTerm).getEReference());
				}
				else if (matchTerm instanceof SerializationMatchTermEStructuralFeatureSize) {
					eStructuralFeaturesSet.remove(((SerializationMatchTermEStructuralFeatureSize)matchTerm).getEStructuralFeature());
				}
			}
		}
		if (!eStructuralFeaturesSet.isEmpty()) {
			int insertIndex = 0;
			List<@NonNull EStructuralFeature> eStructuralFeatures = new ArrayList<>(eStructuralFeaturesSet);
			Collections.sort(eStructuralFeatures, SerializationUtils.ENAMED_ELEMENT_COMPARATOR);
			for (@NonNull EStructuralFeature eStructuralFeature : eStructuralFeatures) {	// Add checks
				SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize matchTerm = new SerializationMatchTerm.SerializationMatchTermEStructuralFeatureSize(eStructuralFeature);
				SerializationMatchStep.MatchStep_Assert matchStep = new SerializationMatchStep.MatchStep_Assert(matchTerm);
				matchSteps.add(insertIndex++, matchStep);
			}
		}
	}

	protected List<@NonNull CardinalityExpression> getExpressionsToSolve() {
		List<@NonNull CardinalityExpression> residualExpressions = new ArrayList<>();
		for (@NonNull CardinalityExpression expression : eStructuralFeature2requiredSlotsExpression.values()) {
			Iterable<@NonNull CardinalityExpression> cardinalityExpressions = expression.getCardinalityExpressions();
			if (cardinalityExpressions != null) {
				for (@NonNull CardinalityExpression cardinalityExpression : cardinalityExpressions) {
					residualExpressions.add(cardinalityExpression);
				}
			}
			else {
				residualExpressions.add(expression);
			}
		}
		Collections.sort(residualExpressions, SerializationUtils.NAMEABLE_COMPARATOR);
		return residualExpressions;
	}

	public @Nullable Iterable<@NonNull AssignedSerializationNode> getAssignedSerializationNodes(@NonNull EReference eReference) {
		return gatherAssignedSerializationNodes(eReference, rootSerializationNode, null);
	}

	public int getCardinalityVariableIndex(@NonNull SerializationNode serializationNode) {
		CardinalityVariable cardinalityVariable = SerializationUtils.maybeNull(node2variable.get(serializationNode));
		return cardinalityVariable != null ? cardinalityVariable.getIndex() : -1;
	}

	public @NonNull Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> getEAttribute2EnumerationValues() {
		Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues2 = eAttribute2enumerationValues;
		if (eAttribute2enumerationValues2 == null) {
			eAttribute2enumerationValues = eAttribute2enumerationValues2 = new HashMap<>();
			getEAttribute2EnumerationValues(rootSerializationNode, eAttribute2enumerationValues2);
		}
		if (eAttribute2enumerationValues2.size() > 0) {
			for (Map.Entry<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> entry : eAttribute2enumerationValues2.entrySet()) {
				@SuppressWarnings("null")
				@NonNull Set<@NonNull EnumerationValue> assignedRuleIndexes = entry.getValue();
				eAttribute2enumerationValues2.put(entry.getKey(), assignedRuleIndexes);
			}
		}
		return eAttribute2enumerationValues2;
	}

	private void getEAttribute2EnumerationValues(@NonNull SerializationNode serializationNode, @NonNull Map<@NonNull EAttribute, @NonNull Set<@NonNull EnumerationValue>> eAttribute2enumerationValues) {
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			AlternativeAssignedKeywordsSerializationNode assignedKeywordsSerializationNode = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
			EAttribute eAttribute = (EAttribute)assignedKeywordsSerializationNode.getEStructuralFeature();
			Set<@NonNull EnumerationValue> enumerationValues = SerializationUtils.maybeNull(eAttribute2enumerationValues.get(eAttribute));
			if (enumerationValues == null) {
				enumerationValues = new HashSet<>();
				eAttribute2enumerationValues.put(eAttribute, enumerationValues);
			}
			EnumerationValue enumerationValue = assignedKeywordsSerializationNode.getEnumerationValue();
			enumerationValues.add(enumerationValue);
		}
		else if (serializationNode instanceof AlternativeAssignsSerializationNode) {
			AlternativeAssignsSerializationNode assignsSerializationNode = (AlternativeAssignsSerializationNode)serializationNode;
			EStructuralFeature eStructuralFeature = assignsSerializationNode.getEStructuralFeature();
			if (eStructuralFeature instanceof EAttribute) {
				EAttribute eAttribute = (EAttribute)eStructuralFeature;
				EnumerationValue enumerationValue = assignsSerializationNode.getEnumerationValue();
			//	if (!enumerationValue.isNull()) {
					Set<@NonNull EnumerationValue> enumerationValues = SerializationUtils.maybeNull(eAttribute2enumerationValues.get(eAttribute));
					if (enumerationValues == null) {
						enumerationValues = new HashSet<>();
						eAttribute2enumerationValues.put(eAttribute, enumerationValues);
					}
					enumerationValues.add(enumerationValue);
			//	}
			}
		}
		else if (serializationNode instanceof AssignedKeywordSerializationNode) {
			AssignedKeywordSerializationNode assignedKeywordSerializationNode = (AssignedKeywordSerializationNode)serializationNode;
			EAttribute eAttribute = (EAttribute)assignedKeywordSerializationNode.getEStructuralFeature();
			Set<@NonNull EnumerationValue> enumerationValues = SerializationUtils.maybeNull(eAttribute2enumerationValues.get(eAttribute));
			if (enumerationValues == null) {
				enumerationValues = new HashSet<>();
				eAttribute2enumerationValues.put(eAttribute, enumerationValues);
			}
			EnumerationValue enumerationValue = assignedKeywordSerializationNode.getEnumerationValue();
			enumerationValues.add(enumerationValue);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getEAttribute2EnumerationValues(nestedSerializationNode, eAttribute2enumerationValues);
			}
		}
	}

	public @NonNull Map<@NonNull EReference, @NonNull GrammarRuleVector> getEReference2AssignedRuleValueIndexes() {
		Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes2 = eReference2assignedRuleIndexes;
		if (eReference2assignedRuleIndexes2 == null) {
			eReference2assignedRuleIndexes = eReference2assignedRuleIndexes2 = new HashMap<>();
			getEReference2AssignedRuleIndexes(rootSerializationNode, eReference2assignedRuleIndexes2);
		}
		return eReference2assignedRuleIndexes2;
	}

	private void getEReference2AssignedRuleIndexes(@NonNull SerializationNode serializationNode, @NonNull Map<@NonNull EReference, @NonNull GrammarRuleVector> eReference2assignedRuleIndexes) {
		if ((serializationNode instanceof AssignedRuleCallSerializationNode) || (serializationNode instanceof AlternativeAssignsSerializationNode)) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			EStructuralFeature eStructuralFeature = assignedSerializationNode.getEStructuralFeature();
			if (eStructuralFeature instanceof EReference) {
				EReference eReference = (EReference)eStructuralFeature;
				GrammarRuleVector netGrammarRuleVector = SerializationUtils.maybeNull(eReference2assignedRuleIndexes.get(eReference));
				if (netGrammarRuleVector == null) {
					netGrammarRuleVector = new GrammarRuleVector();
					eReference2assignedRuleIndexes.put(eReference, netGrammarRuleVector);
				}
				GrammarRuleVector grammarRuleVector = assignedSerializationNode.getAssignedGrammarRuleVector();
				netGrammarRuleVector.setAll(grammarRuleVector);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getEReference2AssignedRuleIndexes(nestedSerializationNode, eReference2assignedRuleIndexes);
			}
		}
	}

	public @NonNull Map<@NonNull EStructuralFeature, @NonNull CardinalityExpression> getEStructuralFeature2requiredSlotsExpression() {
		return eStructuralFeature2requiredSlotsExpression;
	}

	public @Nullable Set<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute) {
		return getEAttribute2EnumerationValues().get(eAttribute);
	}

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EStructuralFeature eStructuralFeature) {
		assert hasMatchAnalyses;
		if (eAttribute2enumerationValue2grammarCardinality != null) {
			Map<@Nullable EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = SerializationUtils.maybeNull(eAttribute2enumerationValue2grammarCardinality.get(eStructuralFeature));
			if (enumerationValue2grammarCardinality != null) {
				return enumerationValue2grammarCardinality.get(null);
			}
		}
	/*	if (eReference2ruleAnalysis2grammarCardinality != null) {
			Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = SerializationUtils.maybeNull(eReference2ruleAnalysis2grammarCardinality.get(eStructuralFeature));
			if (ruleAnalysis2grammarCardinality != null) {
				return ruleAnalysis2grammarCardinality.get(null);
			}
		} */
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		assert hasMatchAnalyses;
		if (eAttribute2enumerationValue2grammarCardinality != null) {
			Map<@Nullable EnumerationValue, @NonNull GrammarCardinality> enumerationValue2grammarCardinality = SerializationUtils.maybeNull(eAttribute2enumerationValue2grammarCardinality.get(eAttribute));
			if (enumerationValue2grammarCardinality != null) {
				return enumerationValue2grammarCardinality.get(enumerationValue);
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EReference eReference, @NonNull ParserRuleAnalysis ruleAnalysis) {
		assert hasMatchAnalyses;
		if (eReference2ruleAnalysis2grammarCardinality != null) {
			Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = SerializationUtils.maybeNull(eReference2ruleAnalysis2grammarCardinality.get(eReference));
			if (ruleAnalysis2grammarCardinality != null) {
				return ruleAnalysis2grammarCardinality.get(ruleAnalysis);
			}
		}
		return null;
	}

	public @Nullable GrammarCardinality getGrammarCardinality(@NonNull EReference eReference, @NonNull ParserRuleValue ruleValue) {
		assert hasMatchAnalyses;
		if (eReference2ruleAnalysis2grammarCardinality != null) {
			Map<@NonNull ParserRuleAnalysis, @NonNull GrammarCardinality> ruleAnalysis2grammarCardinality = SerializationUtils.maybeNull(eReference2ruleAnalysis2grammarCardinality.get(eReference));
			if (ruleAnalysis2grammarCardinality != null) {
				for (@NonNull ParserRuleAnalysis parserRuleAnalysis : ruleAnalysis2grammarCardinality.keySet()) {
					if (parserRuleAnalysis.getRuleValue() == ruleValue) {
						return ruleAnalysis2grammarCardinality.get(parserRuleAnalysis);
					}
				}
			}
		}
		return null;
	}

	@Override
	public GrammarRuleValue getGrammarRuleValue(int ruleValueIndex) {
		return grammarAnalysis.getRuleAnalysis(ruleValueIndex).getRuleValue();
	}

	private void getIdiomMatches(@NonNull SerializationNode serializationNode, @NonNull Iterable<@NonNull Idiom> idioms,
			@Nullable IdiomSerializationMatch @NonNull [] idiomMatches) {
		int idiomIndex = 0;
		for (@NonNull Idiom idiom : idioms) {
			IdiomSerializationMatch idiomMatch = idiomMatches[idiomIndex];
			if (idiomMatch == null) {
				SubIdiom firstSubIdiom = idiom.getOwnedSubIdioms().get(0);
				assert firstSubIdiom != null;
				boolean firstSubIdiomMatches = matches(firstSubIdiom, serializationNode);
				idiomMatches[idiomIndex] = firstSubIdiomMatches ? grammarAnalysis.createIdiomMatch(idiom, serializationNode) : null;
			}
			else {
				idiomMatch.nextMatch(serializationNode, this);
			}
			idiomIndex++;
		}
		if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getIdiomMatches(nestedSerializationNode, idioms, idiomMatches);
			}
		}
	}

	@Override
	public @NonNull String getName() {
		return ruleAnalysis.getName();
	}

	public @Nullable SerializationNode getParent(@NonNull SerializationNode serializationNode) {
		Map<@NonNull SerializationNode, @Nullable SerializationNode> node2parent2 = node2parent;
		if (node2parent2 == null) {
			node2parent2 = node2parent = new HashMap<>();
			node2parent2.put(rootSerializationNode, null);
			getParentInternal(node2parent2, rootSerializationNode);
		}
		return node2parent2.get(serializationNode);
	}
	private void getParentInternal(@NonNull Map<@NonNull SerializationNode, @Nullable SerializationNode> node2parent2, @NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode childNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				node2parent2.put(childNode, serializationNode);
				getParentInternal(node2parent2, childNode);
			}
		}
		else {
			assert !(serializationNode instanceof CompositeSerializationNode);
		}
	}

	public @NonNull EClass getProducedEClass() {
		return producedEClass;
	}

	public @NonNull SerializationNode getRootSerializationNode() {
		return rootSerializationNode;
	}

	public @NonNull ParserRuleAnalysis getRuleAnalysis() {
		return ruleAnalysis;
	}

	public int getRuleValueIndex() {
		return ruleAnalysis.getIndex();
	}

	public @NonNull Iterable<@NonNull SerializationMatchStep> getSerializationMatchSteps() {
		assert hasMatchAnalyses;
		return matchSteps;
	}

	public @NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> getSerializationNode2subIdioms() {
		Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms2 = serializationNode2subIdioms;
		if (serializationNode2subIdioms2 == null) {
			assert hasMatchAnalyses;
			EClass producedEClass = getProducedEClass();
			List<@NonNull Idiom> idioms = new ArrayList<>();
			for (@NonNull Idiom idiom : grammarAnalysis.getIdioms()) {
				boolean isOk = true;
				EClass inEClass = idiom.getForEClass();
				if ((inEClass != null) && !AnalysisUtils.isSuperTypeOf(inEClass, producedEClass)) {
					isOk = false;
				}
				Pattern pattern = idiom.getRegexPattern();
				if (pattern != null) {
					Matcher matcher = pattern.matcher(getName());
					if (!matcher.matches()) {
						isOk = false;
					}
				}
				if (isOk) {
					idioms.add(idiom);
				}
			}
			//
			//	Locate the matches for each idiom.
			//
			@Nullable IdiomSerializationMatch @NonNull [] idiomMatches = new @Nullable IdiomSerializationMatch[Iterables.size(idioms)];
			getIdiomMatches(rootSerializationNode, idioms, idiomMatches);
			//
			//	Install the subidioms for each first/mixin full idiom match.
			//
			serializationNode2subIdioms2 = new HashMap<>();
			for (@Nullable IdiomSerializationMatch idiomMatch : idiomMatches) {
				if (idiomMatch != null) {
					idiomMatch.installIn(serializationNode2subIdioms2);
				}
			}
			serializationNode2subIdioms = serializationNode2subIdioms2;
		}
		return serializationNode2subIdioms2;
	}

	public @NonNull SerializationRule getSerializationRule() {
		SerializationRule serializationRule2 = serializationRule;
		if (serializationRule2 == null) {
			analyzeMatches();
			serializationRule2 = serializationRule;
			if (serializationRule2 == null) {
				@NonNull SerializationMatchStep @NonNull [] matchStepsArray = SerializationUtils.nonNullState(matchSteps.toArray(new @NonNull SerializationMatchStep[matchSteps.size()]));
				List<@NonNull SerializationStep> stepsList = new ArrayList<>();
				Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms = getSerializationNode2subIdioms();
				rootSerializationNode.gatherStepsAndSubIdioms(this, stepsList, serializationNode2subIdioms);
				int size = stepsList.size();
				@NonNull SerializationStep @NonNull [] serializationSteps = SerializationUtils.nonNullState(stepsList.toArray(new @NonNull SerializationStep[size]));
				serializationRule = serializationRule2 = new SerializationRule(this, getVariantName(), ruleAnalysis.getIndex(), matchStepsArray, serializationSteps, basicGetSerializationFeatures());
				SerializationRule old = SerializationUtils.maybeNull(debugMap.put(this, serializationRule2));		// FIXME debugging
				assert old == null;
			}
		}
		return serializationRule2;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EStructuralFeature eStructuralFeature) {
		throw new UnsupportedOperationException();// return null;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		throw new UnsupportedOperationException();// return null;
	}

	@Override
	public @Nullable Integer getSize(@NonNull EReference eReference, @NonNull GrammarRuleVector grammarRuleVector) {
		throw new UnsupportedOperationException();// return null;
	}

	public @Nullable List<@NonNull SubIdiom> getSubIdioms(@NonNull SerializationNode serializationNode) {
		return getSerializationNode2subIdioms().get(serializationNode);
	}

	public @NonNull Iterable<@NonNull CardinalityVariable> getVariables() {
		assert hasMatchAnalyses;
		return SerializationUtils.nonNullState(variable2node.keySet());
	}

	/**
	 * Return true if there is an ordered many feature assignment from diverse parser rules.
	 */
	protected boolean isOrderedAndHeterogeneous() {
		List<@NonNull CardinalityExpression> cardinalityExpressions = getExpressionsToSolve();
		if (cardinalityExpressions.size() <= 0) {
			return false;
		}
	//	boolean isOrderedAndHeterogeneous = false;
		for (@NonNull CardinalityExpression cardinalityExpression : cardinalityExpressions) {
			if (cardinalityExpression.isOrderedAndHeterogeneous()) {
				return true;
			}
		}
		return false;
	}

	public boolean matches(@NonNull SubIdiom subIdiom, @NonNull SerializationNode serializationNode) {
		Locator locator = subIdiom.getOwnedLocator();
		if (locator == null) {
			return false;
		}
		return matches(locator, serializationNode);
	}

	protected boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
		if (locator instanceof ReferredLocator) {
			locator = IdiomsUtils.getOwnedLocator(IdiomsUtils.getLocatorDeclaration((ReferredLocator)locator));
		}
		LocatorImpl locatorImpl = (LocatorImpl)locator;
		LocatorHelper locatorHelper = (LocatorHelper)locatorImpl.basicGetHelper();
		if (locatorHelper == null) {
			LocatorSwitch subIdiomLocatorSwitch = grammarAnalysis.getLocatorSwitch();
			locatorHelper = subIdiomLocatorSwitch.doSwitch(locator);
			locatorImpl.setHelper(locatorHelper);
		}
		if (locatorHelper == null) {	// Only actually null after an UnsupportedOperationException
			return false;
		}
		return locatorHelper.matches(locator, serializationNode, this);
	}

	private @NonNull GrammarCardinality refineGrammarCardinality(@NonNull GrammarCardinality netGrammarCardinality, @Nullable GrammarCardinality oldGrammarCardinality) {
		if (oldGrammarCardinality == null) {
			return netGrammarCardinality;
		}
		boolean newMayBeMany = netGrammarCardinality.mayBeMany();
		boolean newMayBeZero = netGrammarCardinality.mayBeZero();
		boolean oldMayBeMany = oldGrammarCardinality.mayBeMany();
		boolean oldMayBeZero = oldGrammarCardinality.mayBeZero();
		if (!oldMayBeZero) {
			newMayBeZero = false;
		}
		if (oldMayBeMany) {
			newMayBeMany = true;
		}
		return newMayBeMany
			? newMayBeZero ? GrammarCardinality.ZERO_OR_MORE : GrammarCardinality.ONE_OR_MORE
			: newMayBeZero ? GrammarCardinality.ZERO_OR_ONE : GrammarCardinality.ONE;
	}

/*	protected @NonNull EClass refineProducedEClass(@NonNull SerializationNode serializationNode, @NonNull EClass producedEClass) {
		if (serializationNode instanceof AssignedSerializationNode) {
			EClass assignedEClass = ((AssignedSerializationNode)serializationNode).getAssignedEClass();
			producedEClass = SerializationUtils.getSubTypeOf(producedEClass, assignedEClass);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				producedEClass = refineProducedEClass(nestedSerializationNode, producedEClass);
			}
		}
		return producedEClass;
	} */

	protected @NonNull EClass refineProducedEClass(@NonNull SerializationNode serializationNode, @NonNull EClass producedEClass) {
		if (serializationNode instanceof AssignedSerializationNode) {
			EClass assignedEClass = ((AssignedSerializationNode)serializationNode).getAssignedEClass();
			producedEClass = SerializationUtils.getSubTypeOf(producedEClass, assignedEClass);
		}
		else if (serializationNode instanceof AbstractUnassignedSerializationNode) {
			EClass assignedEClass = ((AbstractUnassignedSerializationNode)serializationNode).getProducedEClass();
			producedEClass = SerializationUtils.getSubTypeOf(producedEClass, assignedEClass);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				producedEClass = refineProducedEClass(nestedSerializationNode, producedEClass);
			}
		}
		return producedEClass;
	}

	public EClass getSuperTypeOf(EClass producedEClass, EClass assignedEClass) {
		if (producedEClass.isSuperTypeOf(assignedEClass)) {
			producedEClass = assignedEClass;
		}
		else {
			assert assignedEClass.isSuperTypeOf(producedEClass);
		}
		return producedEClass;
	}

	public @NonNull String getVariantName() {
		return variantName;
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		rootSerializationNode.toString(new DiagnosticStringBuilder(s), -1);
	}

//	@Override
//	public void toRuleString(@NonNull DiagnosticStringBuilder s) {
//		rootSerializationNode.toString(s, -1);
//	}

	public void toRuleString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.append(ruleAnalysis.getQualifiedName());
		s.append("-");
		s.appendObject(variantNumber);
		s.append("(");
		EClass producedEClass = getProducedEClass();
		s.appendObject(producedEClass.getEPackage().getName());
		s.append("::");
		s.appendObject(producedEClass.getName());
		s.append("): ");
		rootSerializationNode.toString(s, depth);
	}

	@Override
	public final @NonNull String toString() {
		DiagnosticStringBuilder s = new DiagnosticStringBuilderWithHelper(this);
		toRuleString(s, -1);
		return s.toString();
	}


/*	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(serializationRuleAnalysis.getName());
		s.append(" : ");
		serializationRuleAnalysis.toRuleString(s);
		List<@NonNull CardinalityExpression> expressions = new ArrayList<>(eStructuralFeature2requiredSlotsExpression.values());
		Collections.sort(expressions, SerializationUtils.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityExpression expression : expressions) {
			StringUtil.appendIndentation(s, depth);
			expression.toString(s, depth);
		}
		List<@NonNull Integer> variables = new ArrayList<>(variableIndex2solution.keySet());
		Collections.sort(variables);
		for (@NonNull Integer variable : variables) {
			SerializationNode serializationNode = variableIndex2node.get(variable);
			assert serializationNode != null;
			StringUtil.appendIndentation(s, depth);
			s.append(variable);
			s.append(": ");
			serializationNode.toString(s, -1);
		}
		for (SerializationMatchStep step : steps) {
			StringUtil.appendIndentation(s, depth);
			step.toString(s, depth+1);
			s.append(";");
		}
	} */
}