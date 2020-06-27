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
import java.util.List;

import org.eclipse.emf.ecore.EObject;
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

	@Override
	public @NonNull RequiredSlots getRequiredSlots() {
		RequiredSlots requiredSlots = this.requiredSlots;
		if (requiredSlots == null) {
			RequiredSlotsConjunction emptyConjunction = null;
			List<@NonNull RequiredSlotsConjunction> outerDisjunction = new ArrayList<>();
			String cardinality2 = cardinality;
			if ("*".equals(cardinality) ) {
				RequiredSlotsConjunction outerConjunction = new RequiredSlotsConjunction();
				for (@NonNull SerializationNode alternativeSerializationNode : alternativeSerializationNodes) {
					RequiredSlots innerRequiredSlots = alternativeSerializationNode.getRequiredSlots();
					if (!innerRequiredSlots.isNull()) {
						for (int i = 0; i < innerRequiredSlots.getConjunctionCount(); i++) {
							RequiredSlotsConjunction innerConjunction = innerRequiredSlots.getConjunction(i);
							outerConjunction.accumulate(innerConjunction, "*");
						}
					}
				}
				outerConjunction.getConjunction();		// XXX eager
				outerDisjunction.add(outerConjunction);
			}
			else if ("+".equals(cardinality) ) {
				for (@NonNull SerializationNode alternativeSerializationNode1 : alternativeSerializationNodes) {
					RequiredSlots innerRequiredSlots1 = alternativeSerializationNode1.getRequiredSlots();
					if (!innerRequiredSlots1.isNull()) {
						RequiredSlotsConjunction outerConjunction = new RequiredSlotsConjunction();
						for (int i = 0; i < innerRequiredSlots1.getConjunctionCount(); i++) {
							RequiredSlotsConjunction innerConjunction1 = innerRequiredSlots1.getConjunction(i);
							for (@NonNull SerializationNode alternativeSerializationNode2 : alternativeSerializationNodes) {
								RequiredSlots innerRequiredSlots2 = alternativeSerializationNode2.getRequiredSlots();
								if (!innerRequiredSlots2.isNull()) {
									for (int j = 0; j < innerRequiredSlots2.getConjunctionCount(); j++) {
										RequiredSlotsConjunction innerConjunction2 = innerRequiredSlots2.getConjunction(j);
										outerConjunction.accumulate(innerConjunction2, alternativeSerializationNode1 == alternativeSerializationNode2 ? "+" : "*");
									}
								}
							}
						}
						outerConjunction.getConjunction();		// XXX eager
						outerDisjunction.add(outerConjunction);
					}
				}
			}
			else {
				if ("?".equals(cardinality) ) {
					emptyConjunction = new RequiredSlotsConjunction();
					emptyConjunction.accumulate(this, null);
					outerDisjunction.add(emptyConjunction);
					cardinality2 = "1";
				}
				for (@NonNull SerializationNode alternativeSerializationNode : alternativeSerializationNodes) {
					RequiredSlots innerRequiredSlots = alternativeSerializationNode.getRequiredSlots();
					if (innerRequiredSlots.isNull()) {
						if (emptyConjunction == null) {
							emptyConjunction = new RequiredSlotsConjunction();
							emptyConjunction.accumulate(this, null);
							outerDisjunction.add(emptyConjunction);
						}
					}
					else {
						for (int i = 0; i < innerRequiredSlots.getConjunctionCount(); i++) {
							RequiredSlotsConjunction outerConjunction = new RequiredSlotsConjunction();
							RequiredSlotsConjunction innerConjunction = innerRequiredSlots.getConjunction(i);
							outerConjunction.accumulate(innerConjunction, "1");
							outerConjunction.getConjunction();		// XXX eager
							outerDisjunction.add(outerConjunction);
						}
					}
				}
			}
			requiredSlots = createRequiredSlots(outerDisjunction);
			this.requiredSlots = requiredSlots;
		}
		return requiredSlots;
	}

	@Override
	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
		SerializationNode serializationNode = serializationBuilder.getAlternative(this);
	//	serializationNode.serialize(serializationBuilder, element);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("\t");
		s.append("{");
	//	boolean isFirst = true;
		for (@NonNull SerializationNode alternativeSerializationNode : alternativeSerializationNodes) {
		//	if (!isFirst) {
				s.append("\n");
		//	}
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("| ");
			alternativeSerializationNode.toString(s, depth+1);
		//	isFirst = false;
		}
		s.append("\n");
		StringUtil.appendIndentation(s, depth, "\t");
		s.append("}");
		appendCardinality(s);
	}
}