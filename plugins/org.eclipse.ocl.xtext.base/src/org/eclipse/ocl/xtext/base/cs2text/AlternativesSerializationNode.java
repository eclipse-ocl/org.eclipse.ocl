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
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.xtext.Alternatives;

public class AlternativesSerializationNode extends CompositeSerializationNode
{
	protected final @NonNull Alternatives alternatives;
	protected final @NonNull List<@NonNull SerializationNode> alternativeSerializationNodes;
	private @Nullable RequiredSlots requiredSlots = null;

	public AlternativesSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull Alternatives alternatives, @NonNull List<@NonNull SerializationNode> alternativeSerializationNodes) {
		super(grammarAnalysis, alternatives);
		this.alternatives = alternatives;
		this.alternativeSerializationNodes = alternativeSerializationNodes;
	}

	/**
	 * Return the alternative for alternativeIndex or null for an invalid index, whicj may be appropriate for
	 * the optional alternative.
	 */
	public @Nullable SerializationNode getAlternativeSerializationNode(int alternativeIndex) {
		return alternativeIndex < alternativeSerializationNodes.size() ? alternativeSerializationNodes.get(alternativeIndex) : null;
	}

	@Override
	public @NonNull RequiredSlots getRequiredSlots() {
		RequiredSlots requiredSlots = this.requiredSlots;
		if (requiredSlots == null) {
			RequiredSlotsConjunction emptyConjunction = null;
			List<@NonNull RequiredSlotsConjunction> outerDisjunction = new ArrayList<>();
		//	String cardinality2 = cardinality;
			if (multiplicativeCardinality.mayBeMany()) {
				if (multiplicativeCardinality.mayBeZero()) {	// (A|B)* => A* | B*
					RequiredSlotsConjunction outerConjunction = new RequiredSlotsConjunction();
					for (@NonNull SerializationNode alternativeSerializationNode : alternativeSerializationNodes) {
						RequiredSlots innerRequiredSlots = alternativeSerializationNode.getRequiredSlots();
						if (!innerRequiredSlots.isNull()) {
							for (int i = 0; i < innerRequiredSlots.getConjunctionCount(); i++) {
								RequiredSlotsConjunction innerConjunction = innerRequiredSlots.getConjunction(i);
								outerConjunction.accumulate(innerConjunction, MultiplicativeCardinality.ZERO_OR_MORE);
							}
						}
					}
					outerConjunction.getConjunction();		// XXX eager
					Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice = new HashMap<>();
					alternatives2choice.put(this, null);
					outerConjunction.setAlternatives(alternatives2choice);
					outerDisjunction.add(outerConjunction);
				}
				else { 											// (A|B)+ => A+B* | A*B+
					for (@NonNull SerializationNode alternativeSerializationNode1 : alternativeSerializationNodes) {
						RequiredSlots innerRequiredSlots1 = alternativeSerializationNode1.getRequiredSlots();
						if (!innerRequiredSlots1.isNull()) {
							RequiredSlotsConjunction outerConjunction = new RequiredSlotsConjunction();
							for (int i = 0; i < innerRequiredSlots1.getConjunctionCount(); i++) {
							//	RequiredSlotsConjunction innerConjunction1 = innerRequiredSlots1.getConjunction(i);
								for (@NonNull SerializationNode alternativeSerializationNode2 : alternativeSerializationNodes) {
									RequiredSlots innerRequiredSlots2 = alternativeSerializationNode2.getRequiredSlots();
									if (!innerRequiredSlots2.isNull()) {
										for (int j = 0; j < innerRequiredSlots2.getConjunctionCount(); j++) {
											RequiredSlotsConjunction innerConjunction2 = innerRequiredSlots2.getConjunction(j);
											outerConjunction.accumulate(innerConjunction2, alternativeSerializationNode1 == alternativeSerializationNode2 ? MultiplicativeCardinality.ONE_OR_MORE : MultiplicativeCardinality.ZERO_OR_MORE);
										}
									}
								}
							}
							outerConjunction.getConjunction();		// XXX eager
							Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice = new HashMap<>();
							alternatives2choice.put(this, alternativeSerializationNode1);
							outerConjunction.setAlternatives(alternatives2choice);
							outerDisjunction.add(outerConjunction);
						}
					}
				}
			}
			else {
				if (multiplicativeCardinality.mayBeZero()) {	// (A|B)? => A|B|epsilon
					emptyConjunction = new RequiredSlotsConjunction();
				//	emptyConjunction.accumulate(this, null);
					Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice = new HashMap<>();
					alternatives2choice.put(this, null);
					emptyConjunction.setAlternatives(alternatives2choice);
					outerDisjunction.add(emptyConjunction);
				//	cardinality2 = "1";
				}
				for (@NonNull SerializationNode alternativeSerializationNode : alternativeSerializationNodes) {
					RequiredSlots innerRequiredSlots = alternativeSerializationNode.getRequiredSlots();
					if (innerRequiredSlots.isNull()) {
						if (emptyConjunction == null) {
							emptyConjunction = new RequiredSlotsConjunction();
						//	emptyConjunction.accumulate(this, null);
							Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice = new HashMap<>();
							alternatives2choice.put(this, null);
							emptyConjunction.setAlternatives(alternatives2choice);
							outerDisjunction.add(emptyConjunction);
						}
					}
					else {
						RequiredSlotsConjunction outerConjunction = new RequiredSlotsConjunction();
						for (int i = 0; i < innerRequiredSlots.getConjunctionCount(); i++) {
						//	RequiredSlotsConjunction outerConjunction = new RequiredSlotsConjunction();
							RequiredSlotsConjunction innerConjunction = innerRequiredSlots.getConjunction(i);
							outerConjunction.accumulate(innerConjunction, MultiplicativeCardinality.ONE);
							outerConjunction.getConjunction();		// XXX eager
						//	outerConjunction.setAlternatives(alternatives2choice);
						//	outerDisjunction.add(outerConjunction);
						}
						Map<@NonNull AlternativesSerializationNode, @Nullable SerializationNode> alternatives2choice = new HashMap<>();
						alternatives2choice.put(this, alternativeSerializationNode);
						outerConjunction.setAlternatives(alternatives2choice);
						outerDisjunction.add(outerConjunction);
					}
				}
			}
			requiredSlots = createRequiredSlots(outerDisjunction);
			this.requiredSlots = requiredSlots;
		}
		return requiredSlots;
	}

	@Override
	public void preSerialize(@NonNull PreSerializer preSerializer) {
		SerializationNode chosenNode = preSerializer.getChosenNode(this);
		if (chosenNode != null) {
			chosenNode.preSerialize(preSerializer);
		}
	}

//	@Override
//	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
//		SerializationNode serializationNode = serializationBuilder.getAlternative(this);
	//	serializationNode.serialize(serializationBuilder, element);
//	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		if (depth >= 0) {
			s.append("\t");
		}
		s.append("{");
	//	boolean isFirst = true;
		for (@NonNull SerializationNode alternativeSerializationNode : alternativeSerializationNodes) {
		//	if (!isFirst) {
				s.append(depth >= 0 ? "\n" : " ");
		//	}
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("| ");
			alternativeSerializationNode.toString(s, depth >= 0 ? depth+1 : depth);
		//	isFirst = false;
		}
		s.append(depth >= 0 ? "\n" : " ");
		StringUtil.appendIndentation(s, depth, "\t");
		s.append("}");
		appendCardinality(s, depth);
	}
}