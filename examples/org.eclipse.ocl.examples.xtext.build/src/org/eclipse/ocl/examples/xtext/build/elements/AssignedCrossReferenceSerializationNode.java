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
package org.eclipse.ocl.examples.xtext.build.elements;

import java.util.List;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.examples.xtext.build.xtext.DirectAssignmentAnalysis;
import org.eclipse.ocl.examples.xtext.build.xtext.GrammarUtils;
import org.eclipse.ocl.examples.xtext.build.xtext.StaticRuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepCrossReference;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.RuleCall;

public class AssignedCrossReferenceSerializationNode extends AbstractAssignedSerializationNode
{
	protected final @NonNull CrossReference crossReference;
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;
	protected final @NonNull Integer [] calledRuleIndexes;
//	private @Nullable Integer semanticHashCode = null;

	public AssignedCrossReferenceSerializationNode(@NonNull DirectAssignmentAnalysis assignmentAnalysis, @NonNull GrammarCardinality grammarCardinality, @NonNull CrossReference crossReference) {
		super(assignmentAnalysis, grammarCardinality);
		RuleCall ruleCall = (RuleCall) GrammarUtils.getTerminal(crossReference);
		AbstractRule calledRule = GrammarUtils.getRule(ruleCall);
		this.calledRuleAnalysis = assignmentAnalysis.getGrammarAnalysis().getRuleAnalysis(calledRule);
		this.calledRuleIndexes = new @NonNull Integer[] { calledRuleAnalysis.getIndex() };
		this.crossReference = crossReference;
		assert !((EReference)eStructuralFeature).isContainment();
	}

	@Override
	public @NonNull Integer @Nullable [] getAssignedRuleIndexes() {
		return calledRuleIndexes;
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		if (grammarCardinality == null) grammarCardinality = this.grammarCardinality;
		return new AssignedCrossReferenceSerializationNode((DirectAssignmentAnalysis)assignmentAnalysis, grammarCardinality, crossReference);
	}

	@Override
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull SerializationStep> stepsList) {
		stepsList.add(new SerializationStepCrossReference(staticRuleMatch.getCardinalityVariableIndex(this), eStructuralFeature, crossReference));
	}

/*	@Override
	public boolean semanticEquals(@NonNull SerializationNode serializationNode) {
		if (serializationNode == this) {
			return true;
		}
		if (!(serializationNode instanceof AssignedCrossReferenceSerializationNode)) {
			return false;
		}
		AssignedCrossReferenceSerializationNode that = (AssignedCrossReferenceSerializationNode)serializationNode;
		if (this.eStructuralFeature != that.eStructuralFeature) {
			return false;
		}
		if (this.calledRuleAnalysis != that.calledRuleAnalysis) {
			return false;
		}
		return true;
	} */

/*	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode() + eStructuralFeature.hashCode() + calledRuleAnalysis.hashCode();
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
	} */

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		GrammarUtils.appendEStructuralFeatureName(s, assignmentAnalysis);
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(calledRuleAnalysis.getRuleName());
		appendCardinality(s, depth);
	}
}