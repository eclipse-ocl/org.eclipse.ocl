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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
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
	@NonNull Iterable<@NonNull RequiredSlots> getConjunction();

	@NonNull RequiredSlotsConjunction getConjunction(int conjunctionIndex);

	/**
	 * Return the number of disjunct alternative conjunctions
	 */
	int getConjunctionCount();

	/**
	 * Return the conjunctions of the disjunction, or iterable of this for a unit conjunction/disjunction.
	 */
	@NonNull Iterable<@NonNull RequiredSlotsConjunction> getDisjunction();

	@NonNull Iterable<@NonNull EStructuralFeature> getEStructuralFeatures();

	/**
	 * Return true if this the null RequiredSlots denoting nothing required.
	 */

	boolean isNull();

	void toString(@NonNull StringBuilder s, int i);

//	static final @NonNull RequiredSlotsConjunction EMPTY_CONJUNCTION = new RequiredSlotsConjunction();
	static final @NonNull List<@NonNull RequiredSlots> EMPTY_CONJUNCTION_TERMS = Collections.emptyList();
	static final @NonNull List<@NonNull RequiredSlotsConjunction> EMPTY_DISJUNCTION_TERMS = Collections.emptyList();
}