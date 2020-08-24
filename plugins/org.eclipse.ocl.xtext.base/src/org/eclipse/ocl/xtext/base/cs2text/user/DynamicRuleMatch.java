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
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalitySolution;
import org.eclipse.ocl.xtext.base.cs2text.solutions.RTStaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.solutions.RuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;

/**
 * A DynamicRuleMatch accumulates the results of augmenting the static match of a particular SerializationRule
 * with the actual analysis of the slots of a user model element.
 */
public class DynamicRuleMatch implements RuleMatch
{
	protected final @NonNull UserSlotsAnalysis slotsAnalysis;
	protected final @NonNull SerializationRule serializationRule;
	protected final @NonNull Iterable<@NonNull CardinalitySolutionStep> steps;
	private final @NonNull RTStaticRuleMatch debugStaticRuleMatch;
	private final @NonNull Map<@NonNull Integer, @NonNull Integer> variableIndex2value = new HashMap<>();
	private boolean checked = false;

	public DynamicRuleMatch(@NonNull UserSlotsAnalysis slotsAnalysis, @NonNull SerializationRule serializationRule, @NonNull Iterable<@NonNull CardinalitySolutionStep> steps, @NonNull RTStaticRuleMatch debugStaticRuleMatch) {
		this.slotsAnalysis = slotsAnalysis;
		this.serializationRule = serializationRule;
		this.steps = steps;
		this.debugStaticRuleMatch = debugStaticRuleMatch;
		slotsAnalysis.getModelAnalysis().debugAddDynamicRuleMatch(this);
	}

	/**
	 * Analyze the actual slots to compute the value of each cardinality variable.
	 *
	 * Returns false if analysis fails.
	 */
	public boolean analyze() {
		for (@NonNull CardinalitySolutionStep step : steps) {
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
	public @Nullable CardinalitySolution basicGetSolution(int cardinalityVariableIndex) {
		throw new IllegalStateException();		// run-time should use known values
	}

	public @NonNull RTStaticRuleMatch getDebugStaticRuleMatch() {
		return debugStaticRuleMatch;
	}

	public @NonNull SerializationRule getSerializationRule() {
		return serializationRule;
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

	public @NonNull Integer getValue(int cardinalityVariableIndex) {
		return ClassUtil.nonNullState(cardinalityVariableIndex >= 0 ? variableIndex2value.get(cardinalityVariableIndex): null);
	}

	public boolean isChecked() {
		return checked;
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