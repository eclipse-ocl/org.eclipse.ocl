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

import org.eclipse.jdt.annotation.NonNull;

/**
 * The NullRequiredSlots INSTANCE is used when no slots are required.
 */
public class NullRequiredSlots implements RequiredSlots
{
	static final @NonNull List<@NonNull SerializationRule> EMPTY_DISJUNCTION_TERMS = Collections.emptyList();
	static final @NonNull NullRequiredSlots INSTANCE = new NullRequiredSlots();

	private NullRequiredSlots() {}

	@Override
	public @NonNull List<@NonNull SerializationRule> getSerializationRules() {
		return EMPTY_DISJUNCTION_TERMS;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("«null»");
	}
}