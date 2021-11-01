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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.analysis.AbstractRuleAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.ParserRuleAnalysis;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.DiagnosticStringBuilder;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString.ToDebugStringable;
import org.eclipse.xtext.CompoundElement;

public abstract class AbstractSerializationElement implements SerializationElement, ToDebugStringable
{
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

	/**
	 * Return an immutable frozen sequence from the extensible listOfNodes eliminating assigned current rule calls and
	 * flattening unassigned parser rule calls and not-isRootAlternative unassigned serialization rule calls.
	 * The listOfNodes corresponds to the content of compoundElement and has an overall grammarCardinality.
	 */
	public static @NonNull SerializationElement createFrozenSequence(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality, @NonNull List<@NonNull SerializationNode> listOfNodes, boolean isRootAlternative) {
		//
		//	Rewrite ... X ... {Y.y=current} ... as {Y} ... y=X ... ...
		//
		FindContext<@NonNull AssignedCurrentSerializationNode> assignedCurrentContext = findAssignedCurrent(listOfNodes);
		if (assignedCurrentContext != null) {
			FindContext<@NonNull UnassignedGrammarRuleCallSerializationNode> unassignedRuleContext = findUnassignedRule(listOfNodes);
			if (unassignedRuleContext != null) {
				UnassignedGrammarRuleCallSerializationNode unassignedRuleCallSerializationNode = unassignedRuleContext.getElement();
				AssignedCurrentSerializationNode assignedCurrentSerializationNode = assignedCurrentContext.getElement();
			//	AssignmentAnalysis assignmentAnalysis = assignedCurrentSerializationNode.getAssignmentAnalysis();
				Iterable<@NonNull AbstractRuleAnalysis> targetRuleAnalyses = assignedCurrentSerializationNode.getTargetRuleAnalyses();
				GrammarCardinality grammarCardinality2 = assignedCurrentSerializationNode.getGrammarCardinality();
				assert grammarCardinality2.isOne();
				AbstractRuleAnalysis calledRuleAnalysis = unassignedRuleCallSerializationNode.getCalledRuleAnalysis();
				AssignedRuleCallSerializationNode assignedRuleCallSerializationNode = new AssignedRuleCallSerializationNode(assignedCurrentSerializationNode.getGrammarAnalysis(), assignedCurrentSerializationNode.getAssignedEClass(), assignedCurrentSerializationNode.getEStructuralFeature(), grammarCardinality2, calledRuleAnalysis.getIndex(), targetRuleAnalyses);
				unassignedRuleContext.replace(assignedRuleCallSerializationNode);
				assignedCurrentContext.remove();
			}
		}
		//
		//	Rewrite {... {Y.y=current} ...}? as epsilon | {... {Y.y=current} ...}
		//
		if (!grammarCardinality.isOne() && !noAssignedCurrent(listOfNodes)) {		// FIXME ? and* ok; only + hard
			SerializationElement sequenceSerializationNode = createFlattenedSequence(compoundElement, GrammarCardinality.ONE, listOfNodes, isRootAlternative);
			ListOfListOfSerializationNode disjunction = new ListOfListOfSerializationNode();
			disjunction = disjunction.addConjunction(NullSerializationNode.INSTANCE);
			disjunction = disjunction.addConjunction(sequenceSerializationNode);
			return disjunction;
		}
		return createFlattenedSequence(compoundElement, grammarCardinality, listOfNodes, isRootAlternative);
	}

	/**
	 * Return a flattened serializzation, possibly a disjunction of serializzation, corresponding to listOfNodes with all rule calls recursively replaced by their called serializations.
	 * The listOfNodes corresponds to the content of compoundElement and has an overall grammarCardinality.
	 */
	private static @NonNull SerializationElement createFlattenedSequence(@NonNull CompoundElement compoundElement, @NonNull GrammarCardinality grammarCardinality, @NonNull List<@NonNull SerializationNode> listOfNodes, boolean isRootAlternative) {
		assert listOfNodes.size() > 0;
		//
		//	Scan to see whether any parser rule calls need flattening.
		//
		UnassignedGrammarRuleCallSerializationNode firstRuleCall = null;
		for (@NonNull SerializationNode serializationNode : listOfNodes) {
			if (serializationNode instanceof UnassignedGrammarRuleCallSerializationNode) {		// FIXME UnassignedSerializationRuleCallSerializationNode too
				UnassignedGrammarRuleCallSerializationNode unassignedRuleCallSerializationNode = (UnassignedGrammarRuleCallSerializationNode)serializationNode;
				AbstractRuleAnalysis calledRuleAnalysis = unassignedRuleCallSerializationNode.getCalledRuleAnalysis();
				if (calledRuleAnalysis instanceof ParserRuleAnalysis) {
					assert firstRuleCall == null;			// Double unassigned rule calls do not happen
					firstRuleCall = unassignedRuleCallSerializationNode;
					assert firstRuleCall.isOne();			// optional/many unassigned rule calls are not allowed
				}
			}
		}
		//
		//	If no RuleCalls, just create the frozen sequence.
		//
		if (firstRuleCall == null) {
			if (listOfNodes.size() == 1) {
				SerializationNode serializationNode = listOfNodes.get(0);
				GrammarCardinality nodeGrammarCardinality = serializationNode.getGrammarCardinality();
				GrammarCardinality maxGrammarCardinality = GrammarCardinality.max(grammarCardinality, nodeGrammarCardinality);
				if (nodeGrammarCardinality == maxGrammarCardinality) {
					return serializationNode;
				}
			}
			return new SequenceSerializationNode(compoundElement, grammarCardinality, listOfNodes);
		}
		//
		//	Otherwise we have to replace, potentially creating a permutation of disjunctions for flattened rule calls,
		//
		ListOfListOfSerializationNode flattenedDisjunction = new ListOfListOfSerializationNode();
		for (@NonNull SerializationNode serializationNode : listOfNodes) {
			SerializationElement conjunction = new ListOfSerializationNode();
			ListOfListOfSerializationNode calledDisjunction = null;
			if (serializationNode instanceof UnassignedGrammarRuleCallSerializationNode) {		// FIXME UnassignedSerializationRuleCallSerializationNode too
				calledDisjunction = flattenUnassignedGrammarRuleCall((UnassignedGrammarRuleCallSerializationNode)serializationNode, grammarCardinality, isRootAlternative);
			}
			conjunction = conjunction.addConcatenation(calledDisjunction != null ? calledDisjunction : serializationNode);
			flattenedDisjunction = flattenedDisjunction.addConcatenation(conjunction.freezeSequences(compoundElement, GrammarCardinality.toEnum(compoundElement), isRootAlternative));
		}
		return flattenedDisjunction;
	}

	private  static @Nullable FindContext<@NonNull UnassignedGrammarRuleCallSerializationNode> findUnassignedRule(@NonNull List<@NonNull SerializationNode> serializationNodes) {
		int index = 0;
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			if (serializationNode instanceof UnassignedGrammarRuleCallSerializationNode) {
				return new FindContext<>(serializationNodes, (UnassignedGrammarRuleCallSerializationNode)serializationNode, index);
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

	private static @Nullable FindContext<@NonNull AssignedCurrentSerializationNode> findAssignedCurrent(@NonNull List<@NonNull SerializationNode> serializationNodes) {
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

	public static @Nullable ListOfListOfSerializationNode flattenUnassignedGrammarRuleCall(@NonNull UnassignedGrammarRuleCallSerializationNode serializationNode, @NonNull GrammarCardinality grammarCardinality, boolean isRootAlternative) {
		ListOfListOfSerializationNode calledDisjunction = null;
		AbstractRuleAnalysis calledRuleAnalysis = serializationNode.getCalledRuleAnalysis();
		if (calledRuleAnalysis instanceof ParserRuleAnalysis) {
			calledDisjunction = new ListOfListOfSerializationNode();
			ParserRuleAnalysis calledParserRuleAnalysis = (ParserRuleAnalysis) calledRuleAnalysis;
			Iterable<@NonNull SerializationRuleAnalysis> calledSerializationRuleAnalyses = calledParserRuleAnalysis.getSerializationRuleAnalyses();
			if (!isRootAlternative) {
				for (@NonNull SerializationRuleAnalysis serializationRuleAnalysis : calledSerializationRuleAnalyses) {
					SerializationNode deepClone = serializationRuleAnalysis.getRootSerializationNode().clone(null);
					calledDisjunction = calledDisjunction.addConjunction(deepClone);
				}
			}
			else {
				for (@NonNull SerializationRuleAnalysis calledSerializationRuleAnalysis : calledSerializationRuleAnalyses) {
					SerializationRuleAnalysis serializationRuleAnalysis = calledSerializationRuleAnalysis;
					for (@NonNull SerializationNode rootSerializationNode; (rootSerializationNode = serializationRuleAnalysis.getRootSerializationNode()) instanceof UnassignedSerializationRuleCallSerializationNode;
						serializationRuleAnalysis = ((UnassignedSerializationRuleCallSerializationNode)rootSerializationNode).getCalledRuleAnalysis()) {
					}
					EClass producedEClass = serializationRuleAnalysis.getProducedEClass();
					SerializationNode serializationRuleCall = new UnassignedSerializationRuleCallSerializationNode(producedEClass, grammarCardinality, serializationRuleAnalysis);
					calledDisjunction = calledDisjunction.addConjunction(serializationRuleCall);
				}
			}
		}
		return calledDisjunction;
	}

	private  static boolean noAssignedCurrent(@NonNull List<@NonNull SerializationNode> serializationNodes) {
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

	protected void appendNodeToList(@NonNull List<@NonNull SerializationNode> listOfNodes, @NonNull SerializationNode serializationNode) {
	//	if (serializationNode.isOne() && (serializationNode instanceof SequenceSerializationNode)) {
	//		listOfNodes.addAll(((SequenceSerializationNode)serializationNode).getSerializationNodes());
	//	}
	//	else {
			listOfNodes.add(serializationNode);		// FIXME conditionalize on compounded locators
	//	}
	}

	protected void appendNodeToListOfList(@NonNull List<@NonNull List<@NonNull SerializationNode>> listOfListOfNodes, @NonNull SerializationNode serializationNode) {
		ArrayList<@NonNull SerializationNode> additionalListOfNodes = new ArrayList<>();
		appendNodeToList(additionalListOfNodes, serializationNode);
		listOfListOfNodes.add(additionalListOfNodes);
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
	public boolean noUnassignedParserRuleCall() {
		return true;
	}

	@Override
	public boolean onlyRootUnassignedSerializationRuleCall(boolean isRootAlternative) {
		return true;
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		s.append(toString());
	}

	@Override
	public @NonNull String toString() {
		DiagnosticStringBuilder s = new DiagnosticStringBuilder();
		toString(s, 0);
		return s.toString();
	}
}