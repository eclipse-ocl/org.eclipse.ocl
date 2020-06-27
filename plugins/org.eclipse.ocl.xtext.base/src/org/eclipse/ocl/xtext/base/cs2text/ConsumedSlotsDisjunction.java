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

import org.eclipse.jdt.annotation.NonNull;

public class ConsumedSlotsDisjunction extends AbstractConsumedSlots
{
	private @NonNull List<@NonNull ConsumedSlotsConjunction> conjunctions = new ArrayList<>();

	protected ConsumedSlotsDisjunction() {}

	public void addConsumedSlot(@NonNull ConsumedSlotsConjunction consumedSlotConjunction) {
		conjunctions.add(consumedSlotConjunction);
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;
		for (@NonNull ConsumedSlotsConjunction conjunction : conjunctions) {
			if (!isFirst) {
				s.append("\n");
			}
			s.append(conjunction);
			isFirst = false;
		}
		return  s.toString();
	}
}