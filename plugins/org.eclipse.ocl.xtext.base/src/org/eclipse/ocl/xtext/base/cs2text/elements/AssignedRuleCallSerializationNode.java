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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepAssignedRuleCall;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarUtils;
import org.eclipse.ocl.xtext.base.cs2text.xtext.StaticRuleMatch;

public class AssignedRuleCallSerializationNode extends AbstractAssignedSerializationNode
{
	protected final int calledRuleIndex;
	protected final @NonNull Integer [] calledRuleIndexes;
//	private @Nullable Integer semanticHashCode = null;

	public AssignedRuleCallSerializationNode(@NonNull AssignmentAnalysis assignmentAnalysis, @NonNull GrammarCardinality grammarCardinality, int calledRuleIndex) {
		super(assignmentAnalysis, grammarCardinality);
		this.calledRuleIndex = calledRuleIndex;
		this.calledRuleIndexes = new @NonNull Integer[] { calledRuleIndex };
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) grammarCardinality = this.grammarCardinality;
		return new AssignedRuleCallSerializationNode(assignmentAnalysis, grammarCardinality, calledRuleIndex);
	}

	@Override
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull SerializationStep> stepsList) {
		int cardinalityVariableIndex = staticRuleMatch.getCardinalityVariableIndex(this);
		stepsList.add(new SerializationStepAssignedRuleCall(cardinalityVariableIndex, eStructuralFeature, calledRuleIndex));
	}

	public int getAssignedRuleIndex() {
		return calledRuleIndex;
	}

	@Override
	public @NonNull Integer @Nullable [] getAssignedRuleIndexes() {
		return calledRuleIndexes;
	}

/*	@Override
	public boolean semanticEquals(@NonNull SerializationNode serializationNode) {
		if (serializationNode == this) {
			return true;
		}
		if (!(serializationNode instanceof AssignedRuleCallSerializationNode)) {
			return false;
		}
		AssignedRuleCallSerializationNode that = (AssignedRuleCallSerializationNode)serializationNode;
		if (this.eStructuralFeature != that.eStructuralFeature) {
			return false;
		}
		if (this.calledRuleIndex != that.calledRuleIndex) {
			return false;
		}
		return true;
	} */

/*	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + eStructuralFeature.hashCode() + calledRuleIndex;
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
	} */

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		GrammarUtils.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		GrammarRuleValue ruleValue = assignmentAnalysis.getGrammarAnalysis().basicGetRuleValue(calledRuleIndex);
		s.append(ruleValue != null ? ruleValue.getRuleName() : calledRuleIndex);
		appendCardinality(s, depth);
	}
}