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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.AbstractElement;

/**
 * MultiplicativeCardinality enacodes the alternative multiplicities in an Xtext grammar as an enumeration value.
 */
public enum MultiplicativeCardinality
{
	ONE("1", 0),
	ZERO_OR_ONE("?", 1),
	ZERO_OR_MORE("*", 3),
	ONE_OR_MORE("+", 2);

	public static @NonNull MultiplicativeCardinality toEnum(@NonNull AbstractElement abstractElement) {
		String cardinality = abstractElement.getCardinality();
		if ("*".equals(cardinality)) {
			return ZERO_OR_MORE;
		}
		else if ("+".equals(cardinality)) {
			return ONE_OR_MORE;
		}
		else if ("?".equals(cardinality)) {
			return ZERO_OR_ONE;
		}
		else if ("1".equals(cardinality)) {
			return ONE;
		}
		else if (cardinality == null) {
			return ONE;
		}
		else {
			throw new UnsupportedOperationException("Grammar cardinality '" + cardinality + "'");
		}
	}

	public static @NonNull MultiplicativeCardinality max(@NonNull MultiplicativeCardinality multiplicativeCardinality1, @NonNull MultiplicativeCardinality multiplicativeCardinality2) {
		int newState = multiplicativeCardinality1.state | multiplicativeCardinality2.state;
		switch (newState) {
			case 0: return ONE;
			case 1: return ZERO_OR_ONE;
			case 2: return ONE_OR_MORE;
			case 3: return ZERO_OR_MORE;
			default: throw new IllegalStateException();
		}
	}

	private final @NonNull String name;
	private final int state;

	private MultiplicativeCardinality(@NonNull String name, int state) {
		this.name = name;
		this.state = state;
	}

	public boolean isConstant() {
		return isOne();
	}

	public boolean isOne() {
		return (state & 3) == ONE.state;
	}

	public boolean isOneOrMore() {
		return (state & 3) == ONE_OR_MORE.state;
	}

	public boolean isZeroOrMore() {
		return (state & 3) == ZERO_OR_MORE.state;
	}

	public boolean isZeroOrOne() {
		return (state & 3) == ZERO_OR_ONE.state;
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