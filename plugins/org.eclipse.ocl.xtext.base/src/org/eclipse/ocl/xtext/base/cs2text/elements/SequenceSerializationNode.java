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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationSequenceStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.xtext.CompoundElement;

public class SequenceSerializationNode extends CompositeSerializationNode
{
	protected final @NonNull CompoundElement compoundElement;
	protected final @NonNull List<@NonNull SerializationNode> serializationNodes;

	public SequenceSerializationNode(@NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(multiplicativeCardinality);
		this.compoundElement = compoundElement;
		this.serializationNodes = groupSerializationNodes;
		assert !groupSerializationNodes.isEmpty();
		assert multiplicativeCardinality.isOne() || noAssignedCurrent(this);
		assert noUnassignedParserRuleCall(this);
		assert groupSerializationNodes.size() == new HashSet<>(groupSerializationNodes).size();
	}

	public SequenceSerializationNode(@NonNull SequenceSerializationNode sequenceSerializationNode, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(sequenceSerializationNode.multiplicativeCardinality);
		this.compoundElement = sequenceSerializationNode.compoundElement;
		this.serializationNodes = groupSerializationNodes;
	//	assert !groupSerializationNodes.isEmpty();
	}

	@Override
	public @NonNull SerializationNode clone(@Nullable MultiplicativeCardinality multiplicativeCardinality) {
		List<@NonNull SerializationNode> newList = new ArrayList<>(serializationNodes.size());
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			newList.add(serializationNode.clone(null));
		}
		if (multiplicativeCardinality == null) multiplicativeCardinality = this.multiplicativeCardinality;
		return new SequenceSerializationNode(compoundElement, multiplicativeCardinality, newList);
	}

	@Override
	public void gatherRuntime(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull RTSerializationStep> stepsList,
			@NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdioms, @NonNull List<@Nullable SubIdiom> subIdiomsList) {
		RTSerializationSequenceStep sequenceStep = new RTSerializationSequenceStep(staticRuleMatch.getCardinalityVariableIndex(this), 0, 0);
		stepsList.add(sequenceStep);
		subIdiomsList.add(serializationNode2subIdioms.get(this));
		int loopStartIndex = stepsList.size();
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializationNode.gatherRuntime(staticRuleMatch, stepsList, serializationNode2subIdioms, subIdiomsList);
		}
		sequenceStep.setSubRange(loopStartIndex, stepsList.size());
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

	private boolean noUnassignedParserRuleCall(@NonNull SerializationNode serializationNode) {
		if (serializationNode instanceof UnassignedRuleCallSerializationNode) {
			return !(((UnassignedRuleCallSerializationNode)serializationNode).getCalledRuleAnalysis() instanceof ParserRuleAnalysis);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				if (!noUnassignedParserRuleCall(nestedSerializationNode)) {
					return false;
				}
			}
		}
		else if (serializationNode.isList() || serializationNode.isListOfList() || serializationNode.isNull() || (serializationNode instanceof AlternativesSerializationNode)) {
			throw new UnsupportedOperationException();
		}
		return true;
	}

//	@Override
//	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
//		for (@NonNull SerializationNode serializationNode : serializationNodes) {
//			serializer.serializeNode(serializationBuilder, serializationNode);
//		}
//	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{");
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			StringUtil.appendIndentation(s, depth);
			s.append(depth >= 0 ? "+\t" : " ");
			serializationNode.toString(s, depth >= 0 ? depth+1 : depth);
		}
		StringUtil.appendIndentation(s, depth);
		s.append(" }");
		appendCardinality(s, depth);
	}
}