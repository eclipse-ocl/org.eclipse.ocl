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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.ParserRule;

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

	protected @NonNull SerializationElement createFrozenSequence(@NonNull GrammarAnalysis grammarAnalysis, @NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> listOfNodes) {
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
			return createFlattenedSequence(grammarAnalysis, compoundElement, multiplicativeCardinality, listOfNodes);
		}
		else {
			SerializationElement sequenceSerializationNode = createFlattenedSequence(grammarAnalysis, compoundElement, MultiplicativeCardinality.ONE, listOfNodes);
			ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
			disjunction = disjunction.addConjunction(NullSerializationNode.INSTANCE);
			disjunction = disjunction.addConjunction(sequenceSerializationNode);
			return disjunction;
		}
	}

	protected @NonNull SerializationElement createFlattenedSequence(@NonNull GrammarAnalysis grammarAnalysis, @NonNull CompoundElement compoundElement, @NonNull MultiplicativeCardinality multiplicativeCardinality, @NonNull List<@NonNull SerializationNode> listOfNodes) {
		//
		//	Recursively flatten nested ParserRuleCalls
		//
		int index = 0;
		for (@NonNull SerializationNode serializationNode : listOfNodes) {
			if (serializationNode instanceof UnassignedRuleCallSerializationNode) {
				EObject searchElement = compoundElement;
				for (; searchElement instanceof Alternatives; searchElement = searchElement.eContainer()) {}
				boolean isDelegator = searchElement instanceof ParserRule;
				AbstractRuleAnalysis calledRuleAnalysis = ((UnassignedRuleCallSerializationNode)serializationNode).getCalledRuleAnalysis();
				if (calledRuleAnalysis instanceof ParserRuleAnalysis) {
					ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
					ParserRuleAnalysis calledParserRuleAnalysis = (ParserRuleAnalysis) calledRuleAnalysis;
					if (isDelegator) {
						disjunction = disjunction.addConjunction(serializationNode);
					}
					else {
						for (@NonNull SerializationRule serializationRule : calledParserRuleAnalysis.getSerializationRules()) {
							SerializationElement flattened = new ListOfSerializationNode();
							for (int i = 0; i < index; i++) {
								flattened = flattened.addConcatenation(listOfNodes.get(i));
							}
							flattened = flattened.addConcatenation(serializationRule.getRootSerializationNode());
							for (int i = index+1; i < listOfNodes.size(); i++) {
								flattened = flattened.addConcatenation(listOfNodes.get(i));
							}
							disjunction = disjunction.addConjunction(flattened.freezeSequences(grammarAnalysis, compoundElement));
						//	SerializationElement flattenedSequence = createFlattenedSequence(grammarAnalysis, compoundElement, multiplicativeCardinality, cflattenedonjunction.getNodes());
						//	disjunction = disjunction.addConjunction(flattenedSequence);
						}
					}
					return disjunction; //.freezeSequences(grammarAnalysis, compoundElement);
				}
			}
			index++;
		}
		return new SequenceSerializationNode(grammarAnalysis, compoundElement, multiplicativeCardinality, listOfNodes);
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