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
package org.eclipse.ocl.xtext.base.cs2text.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.RuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;

/**
 * A DynamicRuleMatch accumulates the results of augmenting the static match of a particular SerializationRule
 * with the actual analysis of the slots of a user model element.
 */
public class DynamicRuleMatch implements RuleMatch
{
	protected final @NonNull StaticRuleMatch staticRuleMatch;
	protected final @NonNull UserSlotsAnalysis slotsAnalysis;
	protected final @NonNull Map<@NonNull Integer, @NonNull Integer> variableIndex2value = new HashMap<>();
	private boolean checked = false;

	public DynamicRuleMatch(@NonNull StaticRuleMatch staticRuleMatch, @NonNull UserSlotsAnalysis slotsAnalysis) {
		this.staticRuleMatch = staticRuleMatch;
		this.slotsAnalysis = slotsAnalysis;
		slotsAnalysis.getModelAnalysis().debugAddDynamicRuleMatch(this);
	}

	/**
	 * Analyze the actual slots to compute the value of each cardinality variable.
	 */
	public boolean analyze() {
		for (@NonNull CardinalitySolutionStep step : staticRuleMatch.getSteps()) {
			if (!step.execute(this)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public @Nullable Integer basicGetIntegerSolution(int cardinalityVariableIndex) {
		return variableIndex2value.get(cardinalityVariableIndex);
	}

	@Override
	public @Nullable CardinalitySolution basicGetSolution(@NonNull CardinalityVariable cardinalityVariable) {
		return basicGetSolution(cardinalityVariable.getIndex());
	}

	@Override
	public @Nullable CardinalitySolution basicGetSolution(int cardinalityVariableIndex) {
		throw new IllegalStateException();		// run-time shoild use known values
	}

	public @NonNull Integer getIntegerSolution(@NonNull CardinalityVariable cardinalityVariable) {
		return ClassUtil.nonNullState(variableIndex2value.get(cardinalityVariable.getIndex()));
	}

//	@Override
	public @NonNull RTSerializationRule getSerializationRule() {
		return staticRuleMatch.getSerializationRule().getRuntime();
	}

	@Override
	public @NonNull Integer getSize(@NonNull EStructuralFeature eStructuralFeature) {
		return slotsAnalysis.getSize(eStructuralFeature);
	}

	@Override
	public @NonNull Integer getSize(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		return slotsAnalysis.getSize(eAttribute, enumerationValue);
	}

	@Override
	public @NonNull Integer getSize(@NonNull EReference eReference, @NonNull ParserRuleValue parserRuleValue) {
		return slotsAnalysis.getSize(eReference, parserRuleValue);
	}

	public @NonNull UserSlotsAnalysis getSlotsAnalysis() {
		return slotsAnalysis;
	}

	public @NonNull StaticRuleMatch getStaticRuleMatch() {
		return staticRuleMatch;
	}

	public @NonNull Integer getValue(@Nullable CardinalityVariable cardinalityVariable) {
		return ClassUtil.nonNullState(cardinalityVariable != null ? variableIndex2value.get(cardinalityVariable.getIndex()): null);
	}

	public @NonNull Integer getValue(int cardinalityVariableIndex) {
		return ClassUtil.nonNullState(cardinalityVariableIndex >= 0 ? variableIndex2value.get(cardinalityVariableIndex): null);
	}

	public boolean isChecked() {
		return checked;
	}

	public void putValue(@NonNull CardinalityVariable cardinalityVariable, @NonNull Integer integerSolution) {
		variableIndex2value.put(cardinalityVariable.getIndex(), integerSolution);
	}
	public void putValue(@NonNull Integer cardinalityVariableIndex, @NonNull Integer integerSolution) {
		variableIndex2value.put(cardinalityVariableIndex, integerSolution);
	}

	public void setChecked() {
		checked  = true;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		slotsAnalysis.toString(s, depth);
		List<@NonNull Integer> variableIndexes = new ArrayList<>(variableIndex2value.keySet());
		Collections.sort(variableIndexes);
		for (@NonNull Integer variableIndex : variableIndexes) {
			Integer value = variableIndex2value.get(variableIndex);
			StringUtil.appendIndentation(s, depth);
			s.append("V");
			s.append(variableIndex);
			s.append(" = ");
			s.append(value);
		}
	}
}