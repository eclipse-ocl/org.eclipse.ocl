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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.GrammarAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.examples.xtext.serializer.SerializationStep.SerializationStepWrapper;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtext.CompoundElement;

public class SequenceSerializationNode extends CompositeSerializationNode
{
	protected final @NonNull CompoundElement compoundElement;
	protected final @NonNull List<@NonNull SerializationNode> serializationNodes;

	public SequenceSerializationNode(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(grammarCardinality);
		this.compoundElement = compoundElement;
		this.serializationNodes = groupSerializationNodes;
		assert !groupSerializationNodes.isEmpty();
		assert grammarCardinality.isOne() || noAssignedCurrent(this);
		assert noUnassignedParserRuleCall();
		assert groupSerializationNodes.size() == new HashSet<>(groupSerializationNodes).size();
		assert onlyRootUnassignedSerializationRuleCall(true);
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable GrammarCardinality grammarCardinality) {
		List<@NonNull SerializationNode> newList = new ArrayList<>(serializationNodes.size());
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			newList.add(serializationNode.clone(null));
		}
		if (grammarCardinality == null) grammarCardinality = this.grammarCardinality;
		return new SequenceSerializationNode(compoundElement, grammarCardinality, newList);
	}

	@Override
	public void gatherStepsAndSubIdioms(@NonNull SerializationRuleAnalysis serializationRuleAnalysis, @NonNull List<@NonNull SerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull List<@NonNull SubIdiom>> serializationNode2subIdioms) {
		@NonNull SerializationSegment @Nullable [] serializationSegments = null;
		List<@NonNull SubIdiom> subIdioms = SerializationUtils.maybeNull(serializationNode2subIdioms.get(this));
		if (subIdioms != null) {
			GrammarAnalysis grammarAnalysis = serializationRuleAnalysis.getGrammarAnalysis();
			serializationSegments = grammarAnalysis.getSerializationSegments(subIdioms, null);
		}
		int cardinalityVariableIndex = serializationRuleAnalysis.getCardinalityVariableIndex(this);
		if (cardinalityVariableIndex < 0) {
			if (stepsList.isEmpty() && (serializationSegments != null)) {
				SerializationStepWrapper wrapperStep = new SerializationStepWrapper(serializationSegments);
				stepsList.add(wrapperStep);
			}
			for (@NonNull SerializationNode serializationNode : serializationNodes) {
				serializationNode.gatherStepsAndSubIdioms(serializationRuleAnalysis, stepsList, serializationNode2subIdioms);
			}
		}
		else {
			SerializationStepSequence sequenceStep = new SerializationStepSequence(cardinalityVariableIndex, 0, grammarCardinality, null);
			stepsList.add(sequenceStep);
			int loopStartIndex = stepsList.size();
			for (@NonNull SerializationNode serializationNode : serializationNodes) {
				serializationNode.gatherStepsAndSubIdioms(serializationRuleAnalysis, stepsList, serializationNode2subIdioms);
			}
			sequenceStep.setStepsRange(stepsList.size() - loopStartIndex);
		}
	}

	public @NonNull List<@NonNull SerializationNode> getSerializationNodes() {
		return serializationNodes;
	}

	private boolean noAssignedCurrent(@NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof AssignedCurrentSerializationNode) {
			return false;
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				if (!noAssignedCurrent(nestedSerializationNode)) {
					return false;
				}
			}
		}
		else if (serializationNode.isList() || serializationNode.isListOfList() || serializationNode.isNull() || (serializationNode instanceof AlternativesSerializationNode)) {
			throw new UnsupportedOperationException();
		}
		return true;
	}

	@Override
	public boolean noUnassignedParserRuleCall() {
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			if (!serializationNode.noUnassignedParserRuleCall()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean onlyRootUnassignedSerializationRuleCall(boolean isRootAlternative) {
		if (serializationNodes.size() > 1) {
			isRootAlternative = false;
		}
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			if (!serializationNode.onlyRootUnassignedSerializationRuleCall(isRootAlternative)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		s.append("{");
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			s.appendIndentation(depth);
			s.append(depth >= 0 ? "+\t" : " ");
			serializationNode.toString(s, depth >= 0 ? depth+1 : depth);
		}
		s.appendIndentation(depth);
		s.append(" }");
		appendCardinality(s, depth);
	}
}