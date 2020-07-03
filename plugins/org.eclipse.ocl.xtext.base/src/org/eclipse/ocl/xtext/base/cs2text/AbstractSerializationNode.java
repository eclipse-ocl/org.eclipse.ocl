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

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractSerializationNode implements SerializationNode
{
	/**
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull XtextGrammarAnalysis grammarAnalysis;
	protected /*final*/ @NonNull MultiplicativeCardinality multiplicativeCardinality;	// XXX
//	private int lowerBound;
//	private int upperBound;

	public AbstractSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.grammarAnalysis = grammarAnalysis;
		this.multiplicativeCardinality = multiplicativeCardinality;
/*		if (cardinality == null) {
			this.lowerBound = 1;
			this.upperBound = 1;
		}
		else if (cardinality.equals("?")) {
			this.lowerBound = 0;
			this.upperBound = 1;
		}
		else if (cardinality.equals("*")) {
			this.lowerBound = 0;
			this.upperBound = -1;
		}
		else if (cardinality.equals("+")) {
			this.lowerBound = 1;
			this.upperBound = -1;
		}
		else {
			throw new UnsupportedOperationException("Unsupported cardinality '" + cardinality + "'");
		} */
	}

	//		public boolean addAlternative(@NonNull SerializationNode nestedContent) {
	//			return false;
	//		}

	//		public boolean addAlternative(@NonNull AbstractElement newContent) {
	//			return false;
	//		}

	protected void appendCardinality(@NonNull StringBuilder s) {
		s.append(multiplicativeCardinality);
	}

	/*		@Override
		public @NonNull RequiredSlotsConjunction getConjunction(int conjunctionIndex) {
			RequiredSlots requiredSlots = getRequiredSlots();
			return requiredSlots.getConjunction(conjunctionIndex);
		} */

	public int getLowerBound() {
		return multiplicativeCardinality.mayBeZero() ? 0 : 1;
	}

	public int getUpperBound() {
		return multiplicativeCardinality.mayBeMany() ? -1 : 1;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public @Nullable List<@NonNull SerializationNode> selectSerializedNodes(@NonNull EObject element) {
	//	ConsumedSlotsDisjunction consumedSlotsDisjunction = new ConsumedSlotsDisjunction();
		for (@NonNull RequiredSlotsConjunction conjunction : getRequiredSlots().getDisjunction()) {
		//	ConsumedSlotsConjunction consumedSlotsConjunction = new ConsumedSlotsConjunction(conjunction);
		/*	boolean allOk = true;
			for (@NonNull SimpleRequiredSlot simpleRequiredSlot : conjunction.getConjunction()) {
				SimpleConsumedSlot consumedSlot = simpleRequiredSlot.isCompatible(element);
				if (consumedSlot != null) {
				//	consumedSlotsConjunction.addConsumedSlot(consumedSlot);
				}
				else {
				//	consumedSlotsConjunction = null;
					allOk = false;
					break;
				}
			}
			if (allOk) { //consumedSlotsConjunction != null) { */
				ConsumedSlotsConjunction consumedSlotsConjunction = new ConsumedSlotsConjunction(conjunction);
				List<@NonNull SerializationNode> serializedNodes = consumedSlotsConjunction.selectSerializedNodes(conjunction, element);
				if (serializedNodes != null) {
					return serializedNodes;
				}
		//	}
		}
		return null;
	}

	@Override
	public void serialize(@NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append("<<<Unsupported serialize '" + getClass().getSimpleName() + "'>>>");
	}

	@Override
	public void setCardinality(@NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.multiplicativeCardinality = multiplicativeCardinality;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}
}