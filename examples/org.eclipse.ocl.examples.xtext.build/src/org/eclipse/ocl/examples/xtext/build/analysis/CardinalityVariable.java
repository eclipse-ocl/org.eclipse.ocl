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
package org.eclipse.ocl.examples.xtext.build.analysis;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.Indexed;

/**
 * A CardinalityVariable represents the unknown cardinality of a grammar term for which a constant value must be deduced prior
 * to serialization of the term.
 */
public class CardinalityVariable implements Indexed//, Nameable
{
	/**
	 * Distinguishing index within the serialization rule.
	 */
	protected final int index;

	/**
	 * The possible cardinalities of the variable. ?/+/*. Unit variables are known/redundant and so excluded from computations.
	 */
	protected final @NonNull GrammarCardinality grammarCardinality;

	public CardinalityVariable(int index, @NonNull GrammarCardinality grammarCardinality) {
		this.index = index;
		this.grammarCardinality = grammarCardinality;
		assert !grammarCardinality.isOne();
	}

	@Override
	public int getIndex() {
		return index;
	}

//	@Override
//	public @NonNull String getName() {
//		return "V" + index;
//	}

	public boolean isOne() {
		return grammarCardinality.isOne();
	}

	public boolean mayBeMany() {
		return grammarCardinality.mayBeMany();
	}

	public boolean mayBeNone() {
		return grammarCardinality.mayBeZero();
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		@SuppressWarnings("null")
		@NonNull String castString = s.toString();
		return castString;
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append("V");
		s.append(index);
		s.append("[");
		s.append(grammarCardinality);
		s.append("]");
	}
}