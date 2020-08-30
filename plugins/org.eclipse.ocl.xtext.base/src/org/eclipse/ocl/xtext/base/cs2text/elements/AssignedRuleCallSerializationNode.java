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
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationAssignedRuleCallStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class AssignedRuleCallSerializationNode extends AbstractAssignedSerializationNode
{
	protected final int calledRuleIndex;
	private @Nullable Integer semanticHashCode = null;

	public AssignedRuleCallSerializationNode(@NonNull AssignmentAnalysis assignmentAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality, int calledRuleIndex) {
		super(assignmentAnalysis, multiplicativeCardinality);
		this.calledRuleIndex = calledRuleIndex;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new AssignedRuleCallSerializationNode(assignmentAnalysis, multiplicativeCardinality, calledRuleIndex);
	}

	@Override
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> stepsList) {
		int cardinalityVariableIndex = staticRuleMatch.getCardinalityVariableIndex(this);
		stepsList.add(new RTSerializationAssignedRuleCallStep(cardinalityVariableIndex, eStructuralFeature, calledRuleIndex));
	}

	@Override
	public @Nullable IndexVector getAssignedRuleIndexes() {
		return new IndexVector().set(calledRuleIndex);
	}

	@Override
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
	}

	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + eStructuralFeature.hashCode() + calledRuleIndex;
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(assignmentAnalysis.getGrammarAnalysis().getRuleValue(calledRuleIndex).getRuleName());
		appendCardinality(s, depth);
	}
}