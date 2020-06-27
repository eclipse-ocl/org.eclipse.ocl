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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.StringUtil;

public class RequiredSlotsDisjunction extends AbstractRequiredSlots
{
	protected final @NonNull CompositeSerializationNode serializationNode;
	private @NonNull List<@NonNull RequiredSlotsConjunction> conjunctions;

	protected RequiredSlotsDisjunction(@NonNull CompositeSerializationNode serializationNode, @NonNull List<@NonNull RequiredSlotsConjunction> conjunctions) {
		this.serializationNode = serializationNode;
		this.conjunctions = conjunctions;
	}

	public void addRequiredSlotConjunction(@NonNull RequiredSlotsConjunction requiredSlotConjunction) {
		conjunctions.add(requiredSlotConjunction);
	}

	@Override
	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getConjunction() {
		switch (conjunctions.size()) {
			case 0: return EMPTY_CONJUNCTION_TERMS;
			case 1: return conjunctions.get(0).getConjunction();
			default: throw new IllegalStateException();
		}
	}

	@Override
	public @NonNull RequiredSlotsConjunction getConjunction(int conjunctionIndex) {
		return conjunctions.get(conjunctionIndex);
	}

//	@Override
//	public @NonNull Iterable<@NonNull SimpleRequiredSlot> getConjunctionTerms(int conjunctionIndex) {
//		return conjunctions.get(conjunctionIndex).getConjunction();
//	}

	@Override
	public int getConjunctionCount() {
		return conjunctions.size();
	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlotsConjunction> getDisjunction() {
		return conjunctions;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst1 = true;
		for (@NonNull RequiredSlotsConjunction conjunction : conjunctions) {
			if (!isFirst1) {
				s.append("\n");
				StringUtil.appendIndentation(s, depth, "\t");
				s.append("|& ");
			}
			conjunction.toString(s,depth);
/*			StringUtil.appendIndentation(s, depth, "\t");
			s.append("|& ");
			boolean isFirst2 = true;
			for (@NonNull SimpleRequiredSlot term : conjunction.getConjunction()) {
				if (!isFirst2) {
					s.append("&");
				}
				s.append(term);
				isFirst2 = false;
			} */
			isFirst1 = false;
		}
	}
}