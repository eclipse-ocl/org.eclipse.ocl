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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;

/**
 * The NullRequiredSlots INSTANCE is used when no slots are required.
 */
public class NullRequiredSlots extends AbstractRequiredSlots
{
	public static final @NonNull NullRequiredSlots INSTANCE = new NullRequiredSlots();

	private NullRequiredSlots() {}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlots> getConjunction() {
		return EMPTY_CONJUNCTION_TERMS;
	}

	@Override
	public @NonNull RequiredSlotsConjunction getConjunction(int conjunctionIndex) {
		assert conjunctionIndex == 0;
		return EMPTY_CONJUNCTION;
	}

	@Override
	public int getConjunctionCount() {
		return 0;
	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlotsConjunction> getDisjunction() {
		return EMPTY_DISJUNCTION_TERMS;
	}

	@Override
	public @NonNull Iterable<@NonNull EStructuralFeature> getEStructuralFeatures() {
		throw new IllegalStateException();
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("«null»");
	}
}