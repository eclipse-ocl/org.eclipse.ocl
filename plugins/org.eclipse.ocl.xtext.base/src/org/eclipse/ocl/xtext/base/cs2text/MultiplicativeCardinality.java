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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * MultiplicativeCardinality enacodes the alternative multiplicities in an Xtext grammar as an enumeration value.
 */
public enum MultiplicativeCardinality
{
	ONE("1", 0),
	ZERO_OR_ONE("?", 1),
	ZERO_OR_MORE("*", 3),
	ONE_OR_MORE("+", 2);

	public static @NonNull MultiplicativeCardinality toEnum(@Nullable String grammarCardinality) {
		if ("*".equals(grammarCardinality)) {
			return ZERO_OR_MORE;
		}
		else if ("+".equals(grammarCardinality)) {
			return ONE_OR_MORE;
		}
		else if ("?".equals(grammarCardinality)) {
			return ZERO_OR_ONE;
		}
		else if ("1".equals(grammarCardinality)) {
			return ONE;
		}
		else if (grammarCardinality == null) {
			return ONE;
		}
		else {
			throw new UnsupportedOperationException("Grammar cardinality '" + grammarCardinality + "'");
		}
	}

	private final @NonNull String name;
	private final int state;

	private MultiplicativeCardinality(@NonNull String name, int state) {
		this.name = name;
		this.state = state;
	}

	boolean isOne() {
		return (state & 3) == 0;
	}

	public boolean mayBeMany() {
		return (state & 2) != 0;
	}

	public boolean mayBeZero() {
		return (state & 1) != 0;
	}

	@Override
	public @NonNull String toString() {
		return name;
	}
}