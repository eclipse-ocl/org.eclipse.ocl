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
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.xtext.CompoundElement;

public abstract class AbstractSerializationElement implements SerializationElement
{
	@Override
	public @NonNull ListOfSerializationNode asList() {
		throw new IllegalStateException();
	}

	@Override
	public @NonNull ListOfListOfSerializationNode asListOfList() {
		throw new IllegalStateException();
	}

	@Override
	public @NonNull SerializationNode asNode() {
		throw new IllegalStateException();
	}

	private static class FindContext<@NonNull E> {
		protected final @NonNull List<@NonNull SerializationNode> elements;
		protected final E element;
		protected final int index;

		public FindContext(@NonNull List<@NonNull SerializationNode> elements, E element, int index) {
			this.elements = elements;
			this.element = element;
			this.index = index;
			assert elements.get(index) == element;
		}

		public @NonNull E getElement() {
			return element;
		}

		public @NonNull E remove() {
			SerializationNode removed = elements.remove(index);
			assert removed == element;
			return element;
		}

		public void replace(@NonNull SerializationNode serializationNode) {
			elements.remove(index);
			elements.add(index, serializationNode);
		}

		@Override
		public String toString() {
			return element.toString();
		}
	}

	protected @NonNull SerializationElement createFrozenSequence(@NonNull GrammarAnalysis grammarAnalysis, @NonNull CompoundElement compoundElement, @NonNull List<@NonNull SerializationNode> listOfNodes) {
		//
		//	Rewrite ... X ... {Y.y=current} ... as {Y} ... y=X ... ...
		//
		FindContext<@NonNull UnassignedRuleCallSerializationNode> unassignedRuleContext = findUnassignedRule(listOfNodes);
		FindContext<@NonNull AssignedCurrentSerializationNode> assignedCurrentContext = findAssignedCurrent(listOfNodes);
		if ((unassignedRuleContext != null) && (assignedCurrentContext != null)) {
			UnassignedRuleCallSerializationNode unassignedRuleCallSerializationNode = unassignedRuleContext.getElement();
			AssignedCurrentSerializationNode assignedCurrentSerializationNode = assignedCurrentContext.getElement();
			AssignmentAnalysis assignmentAnalysis = assignedCurrentSerializationNode.getAssignmentAnalysis();
			MultiplicativeCardinality multiplicativeCardinality = assignedCurrentSerializationNode.getMultiplicativeCardinality();
			assert multiplicativeCardinality.isOne();
			AbstractRuleAnalysis calledRuleAnalysis = unassignedRuleCallSerializationNode.getCalledRuleAnalysis();
			AssignedRuleCallSerializationNode assignedRuleCallSerializationNode = new AssignedRuleCallSerializationNode(assignmentAnalysis, multiplicativeCardinality, calledRuleAnalysis);
			unassignedRuleContext.replace(assignedRuleCallSerializationNode);
			assignedCurrentContext.remove();
		}
		//
		//	Rewrite {... {Y.y=current} ...}? as epsilon | {... {Y.y=current} ...}
		//
		MultiplicativeCardinality multiplicativeCardinality = MultiplicativeCardinality.toEnum(compoundElement);
		if (multiplicativeCardinality.isOne() || noAssignedCurrent(listOfNodes)) {
			SequenceSerializationNode sequenceSerializationNode = new SequenceSerializationNode(grammarAnalysis, compoundElement, multiplicativeCardinality, listOfNodes);
			return sequenceSerializationNode;
		}
		else {
			SequenceSerializationNode sequenceSerializationNode = new SequenceSerializationNode(grammarAnalysis, compoundElement, MultiplicativeCardinality.ONE, listOfNodes);
			ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
			disjunction.addConjunction(NullSerializationNode.INSTANCE);
			disjunction.addConjunction(sequenceSerializationNode);
			return disjunction;
		}
	}

	private @Nullable FindContext<@NonNull UnassignedRuleCallSerializationNode> findUnassignedRule(@NonNull List<@NonNull SerializationNode> serializationNodes) {
		int index = 0;
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			if (serializationNode instanceof UnassignedRuleCallSerializationNode) {
				return new FindContext<>(serializationNodes, (UnassignedRuleCallSerializationNode)serializationNode, index);
			}
			else if (serializationNode instanceof SequenceSerializationNode) {
				return findUnassignedRule(((SequenceSerializationNode)serializationNode).getSerializationNodes());
			}
			else if (serializationNode.isList() || serializationNode.isListOfList() || serializationNode.isNull() || (serializationNode instanceof AlternativesSerializationNode)) {
				throw new UnsupportedOperationException();
			}
			index++;
		}
		return null;
	}

	private @Nullable FindContext<@NonNull AssignedCurrentSerializationNode> findAssignedCurrent(@NonNull List<@NonNull SerializationNode> serializationNodes) {
		int index = 0;
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			if (serializationNode instanceof AssignedCurrentSerializationNode) {
				return new FindContext<>(serializationNodes, (AssignedCurrentSerializationNode)serializationNode, index);
			}
			else if (serializationNode instanceof SequenceSerializationNode) {
				return findAssignedCurrent(((SequenceSerializationNode)serializationNode).getSerializationNodes());
			}
			else if (serializationNode.isList() || serializationNode.isListOfList() || serializationNode.isNull() || (serializationNode instanceof AlternativesSerializationNode)) {
				throw new UnsupportedOperationException();
			}
			index++;
		}
		return null;
	}

	private boolean noAssignedCurrent(@NonNull List<@NonNull SerializationNode> serializationNodes) {
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			if (serializationNode instanceof AssignedCurrentSerializationNode) {
				return false;
			}
			else if (serializationNode instanceof SequenceSerializationNode) {
				return noAssignedCurrent(((SequenceSerializationNode)serializationNode).getSerializationNodes());
			}
			else if (serializationNode.isList() || serializationNode.isListOfList() || serializationNode.isNull() || (serializationNode instanceof AlternativesSerializationNode)) {
				throw new UnsupportedOperationException();
			}
		}
		return true;
	}

	@Override
	public boolean isList() {
		return false;
	}

	@Override
	public boolean isListOfList() {
		return false;
	}

	@Override
	public boolean isNode() {
		return false;
	}

	@Override
	public boolean isNull() {
		return false;
	}

//	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append("«Unsupported serialize '" + getClass().getSimpleName() + "'»");
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}
}