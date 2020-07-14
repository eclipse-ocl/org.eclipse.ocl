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

/**
 * The RequiredSlots are a disjunction (alternatives) of conjunctions of SimpleRequiredSlot identifying the cardinality for a single
 * assigned feature. Derived classes may optimize so that for instance a SimpleRequiredSlot is useable as a disjunction of a conjunction
 * of the one required slot.
 */
public interface RequiredSlots
{
	/**
	 * Return the terms of a conjunction, or iterable of this for a unit conjunction/disjunction, or an ISE for a real disjunction..
	 */
//	@NonNull Iterable<? extends @NonNull SimpleRequiredSlot> getConjunction();

	/**
	 * Return the conjunctions of the disjunction, or iterable of this for a unit conjunction/disjunction.
	 */
	@NonNull List<@NonNull SerializationRule> getSerializationRules();

	/**
	 * Return true if this the null RequiredSlots denoting nothing required.
	 */
	boolean isNull();

	void toString(@NonNull StringBuilder s, int i);
}