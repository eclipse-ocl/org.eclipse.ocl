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

import java.util.HashSet;
import java.util.List;
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.xtext.CompoundElement;

public class SequenceSerializationNode extends CompositeSerializationNode
{
	protected final @NonNull CompoundElement compoundElement;
	protected final @NonNull List<@NonNull SerializationNode> serializationNodes;

	public SequenceSerializationNode(@NonNull GrammarAnalysis grammarAnalysis, @NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(grammarAnalysis, multiplicativeCardinality);
		this.compoundElement = compoundElement;
		this.serializationNodes = groupSerializationNodes;
		assert !groupSerializationNodes.isEmpty();
		assert multiplicativeCardinality.isOne() || noAssignedCurrent(this);
		assert noUnassignedParserRuleCall(this);
		assert groupSerializationNodes.size() == new HashSet<>(groupSerializationNodes).size();
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

	public SequenceSerializationNode(@NonNull SequenceSerializationNode sequenceSerializationNode, @NonNull List<@NonNull SerializationNode> groupSerializationNodes) {
		super(sequenceSerializationNode.grammarAnalysis, sequenceSerializationNode.multiplicativeCardinality);
		this.compoundElement = sequenceSerializationNode.compoundElement;
		this.serializationNodes = groupSerializationNodes;
	//	assert !groupSerializationNodes.isEmpty();
	}

	@Override
	public @NonNull SerializationNode clone(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		return new SequenceSerializationNode(grammarAnalysis, compoundElement, multiplicativeCardinality, serializationNodes);
	}

	public @NonNull List<@NonNull SerializationNode> getSerializationNodes() {
		return serializationNodes;
	}

	@Override
	public void preSerialize(@NonNull BasicSerializationRule serializationRule, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		super.preSerialize(serializationRule, parentStack);
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			parentStack.push(this);
			serializationNode.preSerialize(serializationRule, parentStack);
			parentStack.pop();
		}
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializer.serializeNode(serializationBuilder, serializationNode);
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("{");
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			StringUtil.appendIndentation(s, depth, "\t");
			s.append(depth >= 0 ? "+\t" : " ");
			serializationNode.toString(s, depth >= 0 ? depth+1 : depth);
		}
		StringUtil.appendIndentation(s, depth, "\t");
		s.append("}");
		appendCardinality(s, depth);
	}
}