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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.runtime.GrammarCardinality;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep.SerializationStepSequence;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.StaticRuleMatch;
import org.eclipse.xtext.CompoundElement;

public class SequenceSerializationNode extends CompositeSerializationNode
{
	protected final @NonNull CompoundElement compoundElement;
	protected final @NonNull List<@NonNull SerializationNode> serializationNodes;
	private @Nullable Integer semanticHashCode = null;

	public SequenceSerializationNode(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(grammarCardinality);
		this.compoundElement = compoundElement;
		this.serializationNodes = groupSerializationNodes;
		assert !groupSerializationNodes.isEmpty();
		assert grammarCardinality.isOne() || noAssignedCurrent(this);
		assert noUnassignedParserRuleCall(this);
		assert groupSerializationNodes.size() == new HashSet<>(groupSerializationNodes).size();
	}

	public SequenceSerializationNode(@NonNull SequenceSerializationNode sequenceSerializationNode, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(sequenceSerializationNode.grammarCardinality);
		this.compoundElement = sequenceSerializationNode.compoundElement;
		this.serializationNodes = groupSerializationNodes;
	//	assert !groupSerializationNodes.isEmpty();
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
	public void gatherSteps(@NonNull StaticRuleMatch staticRuleMatch, @NonNull List<@NonNull SerializationStep> stepsList) {
		SerializationStepSequence sequenceStep = new SerializationStepSequence(staticRuleMatch.getCardinalityVariableIndex(this), 0, 0);
		stepsList.add(sequenceStep);
		int loopStartIndex = stepsList.size();
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializationNode.gatherSteps(staticRuleMatch, stepsList);
		}
		sequenceStep.setSubRange(loopStartIndex, stepsList.size());
	}

	public @NonNull List<@NonNull SerializationNode> getSerializationNodes() {
		return serializationNodes;
	}

	@Override
	public boolean semanticEquals(@NonNull SerializationNode that) {
		if (that == this) {
			return true;
		}
		if (!(that instanceof SequenceSerializationNode)) {
			return false;
		}
		List<@NonNull SerializationNode> theseNodes = this.serializationNodes;
		List<@NonNull SerializationNode> thoseNodes = ((SequenceSerializationNode)that).serializationNodes;
		int size = theseNodes.size();
		if (size != thoseNodes.size()) {
			return false;
		}
		for (int i = 0; i < size; i++) {
			if (!theseNodes.get(i).semanticEquals(thoseNodes.get(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int semanticHashCode() {
		if (semanticHashCode == null) {
			int hash = getClass().hashCode();
			for (@NonNull SerializationNode serializationNode : serializationNodes) {
				hash = 3*hash + + serializationNode.semanticHashCode();;
			}
			semanticHashCode = hash;
		}
		assert semanticHashCode != null;
		return semanticHashCode.intValue();
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