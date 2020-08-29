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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.ToDebugString;
import org.eclipse.ocl.xtext.base.cs2text.ToDebugString.ToDebugStringable;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.ParserRule;

public abstract class AbstractSerializationElement implements SerializationElement, ToDebugStringable
{
	@SuppressWarnings("unused")			// Used in the debugger
	private final @NonNull ToDebugString toDebugSring = new ToDebugString(this){};

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

	protected void appendNodeToList(@NonNull List<@NonNull SerializationNode> listOfNodes, @NonNull SerializationNode serializationNode) {
		if (serializationNode.isOne() && (serializationNode instanceof SequenceSerializationNode)) {
			listOfNodes.addAll(((SequenceSerializationNode)serializationNode).getSerializationNodes());
		}
		else {
			listOfNodes.add(serializationNode);
		}
	}

	protected void appendNodeToListOfList(@NonNull List<@NonNull List<@NonNull SerializationNode>> listOfListOfNodes, @NonNull SerializationNode serializationNode) {
		ArrayList<@NonNull SerializationNode> additionalListOfNodes = new ArrayList<>();
		appendNodeToList(additionalListOfNodes, serializationNode);
		listOfListOfNodes.add(additionalListOfNodes);
	}

	/**
	 * Return an immutable frozen sequence from the extensible listOfNodes eliminating assigned current rule calls and
	 * flattening unassigned rule calls.
	 * The listOfNodes corresponds to the content of compoundElement and has an overall multiplicativeCardinality.
	 */
	protected @NonNull SerializationElement createFrozenSequence(@NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> listOfNodes) {
		//
		//	Rewrite ... X ... {Y.y=current} ... as {Y} ... y=X ... ...
		//
		FindContext<@NonNull AssignedCurrentSerializationNode> assignedCurrentContext = findAssignedCurrent(listOfNodes);
		if (assignedCurrentContext != null) {
			FindContext<@NonNull UnassignedRuleCallSerializationNode> unassignedRuleContext = findUnassignedRule(listOfNodes);
			if (unassignedRuleContext != null) {
				UnassignedRuleCallSerializationNode unassignedRuleCallSerializationNode = unassignedRuleContext.getElement();
				AssignedCurrentSerializationNode assignedCurrentSerializationNode = assignedCurrentContext.getElement();
				AssignmentAnalysis assignmentAnalysis = assignedCurrentSerializationNode.getAssignmentAnalysis();
				MultiplicativeCardinality multiplicativeCardinality2 = assignedCurrentSerializationNode.getMultiplicativeCardinality();
				assert multiplicativeCardinality2.isOne();
				AbstractRuleAnalysis calledRuleAnalysis = unassignedRuleCallSerializationNode.getCalledRuleAnalysis();
				AssignedRuleCallSerializationNode assignedRuleCallSerializationNode = new AssignedRuleCallSerializationNode(assignmentAnalysis, multiplicativeCardinality2, calledRuleAnalysis);
				unassignedRuleContext.replace(assignedRuleCallSerializationNode);
				assignedCurrentContext.remove();
			}
		}
		//
		//	Rewrite {... {Y.y=current} ...}? as epsilon | {... {Y.y=current} ...}
		//
		if (multiplicativeCardinality.isOne() || noAssignedCurrent(listOfNodes)) {
			return createFlattenedSequence(compoundElement, multiplicativeCardinality, listOfNodes);
		}
		else {
			SerializationElement sequenceSerializationNode = createFlattenedSequence(compoundElement, MultiplicativeCardinality.ONE, listOfNodes);
			ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
			disjunction = disjunction.addConjunction(NullSerializationNode.INSTANCE);
			disjunction = disjunction.addConjunction(sequenceSerializationNode);
			return disjunction;
		}
	}

	/**
	 * Return a flattened serializzation, possibly a disjunction of serializzation, corresponding to listOfNodes with all rule calls recursively replaced by their called serializations.
	 * The listOfNodes corresponds to the content of compoundElement and has an overall multiplicativeCardinality.
	 */
	protected @NonNull SerializationElement createFlattenedSequence(@NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> listOfNodes) {
		assert listOfNodes.size() > 0;
		//
		//	Scan to see whether any parser rule calls need flattening.
		//
		UnassignedRuleCallSerializationNode firstRuleCall = null;
		for (@NonNull SerializationNode serializationNode : listOfNodes) {
			if (serializationNode instanceof UnassignedRuleCallSerializationNode) {
				UnassignedRuleCallSerializationNode unassignedRuleCallSerializationNode = (UnassignedRuleCallSerializationNode)serializationNode;
				AbstractRuleAnalysis calledRuleAnalysis = unassignedRuleCallSerializationNode.getCalledRuleAnalysis();
				if (calledRuleAnalysis instanceof ParserRuleAnalysis) {
					assert firstRuleCall == null;			// Double unassigned rule calls do not happen
					firstRuleCall = unassignedRuleCallSerializationNode;
					assert firstRuleCall.isOne();			// optional/many unassigned rule calls are not allowed
				//	break;
				}
			}
		}
		//
		//	If no RuleCalls, just create the frozen sequence.
		//
		if (firstRuleCall == null) {
			if (listOfNodes.size() == 1) {
				SerializationNode serializationNode = listOfNodes.get(0);
				MultiplicativeCardinality nodeMultiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
				MultiplicativeCardinality maxMultiplicativeCardinality = MultiplicativeCardinality.max(multiplicativeCardinality, nodeMultiplicativeCardinality);
				if (nodeMultiplicativeCardinality == maxMultiplicativeCardinality) {
					return serializationNode;
				}
			}
			return new SequenceSerializationNode(compoundElement, multiplicativeCardinality, listOfNodes);
		}
		//
		//	If flattening a single rule call as part of an alternative hierarchy at the root, the rule call can be delegated.
		//
		if (listOfNodes.size() == 1) {
			EObject searchElement = compoundElement;
			for (; searchElement instanceof Alternatives; searchElement = searchElement.eContainer()) {}
			boolean isDelegator = searchElement instanceof ParserRule;
			if (isDelegator) {
				if (multiplicativeCardinality.isOne()) {
					return firstRuleCall;
				}
				return new SequenceSerializationNode(compoundElement, multiplicativeCardinality, listOfNodes);
			}
		}
		//
		//	Otherwise we have to replace, potentially creating a permutation of disjunctions for flattened rule calls,
		//
		ListOfListOfSerializationNode flattenedDisjunction = new ListOfListOfSerializationNode();
		for (@NonNull SerializationNode serializationNode : listOfNodes) {
			SerializationElement conjunction = new ListOfSerializationNode();
			ListOfListOfSerializationNode calledDisjunction = null;
			if (serializationNode instanceof UnassignedRuleCallSerializationNode) {
				AbstractRuleAnalysis calledRuleAnalysis = ((UnassignedRuleCallSerializationNode)serializationNode).getCalledRuleAnalysis();
				if (calledRuleAnalysis instanceof ParserRuleAnalysis) {
					ParserRuleAnalysis calledParserRuleAnalysis = (ParserRuleAnalysis) calledRuleAnalysis;
					calledDisjunction = new ListOfListOfSerializationNode();
					for (@NonNull SerializationRuleAnalysis serializationRule : calledParserRuleAnalysis.getSerializationRuleAnalyses()) {
						SerializationNode deepClone = serializationRule.getRootSerializationNode().clone(null);
						calledDisjunction = calledDisjunction.addConjunction(deepClone);
					}
				}
			}
			conjunction = conjunction.addConcatenation(calledDisjunction != null ? calledDisjunction : serializationNode);
			flattenedDisjunction = flattenedDisjunction.addConcatenation(conjunction.freezeSequences(compoundElement, MultiplicativeCardinality.toEnum(compoundElement)));
		}
		return flattenedDisjunction;
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

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		s.append(toString());
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}
}