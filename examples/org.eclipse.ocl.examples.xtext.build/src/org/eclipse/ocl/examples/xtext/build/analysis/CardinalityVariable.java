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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.serializer.Nameable;

/**
 * A CardinalityVariable represents the unknown cardinality of a grammar term for which a constant value must be deduced prior
 * to serialization of the term.
 */
public class CardinalityVariable implements Nameable
{
	/**
	 * Distinguishing index within the seriailzation rule.
	 */
	protected final int index;

	/**
	 * A convenience debug name for the variable. Jnique within its paent rule match.
	 */
	protected final @NonNull String name;

	/**
	 * The rule analyses that type the variable. null for sequence variables.
	 */
	protected final @Nullable GrammarRuleVector ruleAnalyses;

	/**
	 * The possible cardinalities of the variable. ?/+/*. Unit variables are known/redundant and so excluded from computations.
	 */
	protected final @NonNull GrammarCardinality grammarCardinality;

	public CardinalityVariable(int index, @NonNull String name, @Nullable GrammarRuleVector ruleAnalyses, @NonNull GrammarCardinality grammarCardinality) {
		this.index = index;
		this.name = name;
		this.ruleAnalyses = ruleAnalyses;
		this.grammarCardinality = grammarCardinality;
		assert !grammarCardinality.isOne();
	}

	public int getIndex() {
		return index;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

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
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(name);
		GrammarRuleVector ruleAnalyses2 = ruleAnalyses;
		if (ruleAnalyses2 != null) {
			s.append(":");
			boolean isFirst = true;
			for (@NonNull Integer ruleIndex : ruleAnalyses2) {
				if (!isFirst) {
					s.append("|");
				}
				s.append(ruleIndex);
				isFirst = false;
			}
		}
		s.append("[");
		s.append(grammarCardinality);
		s.append("]");
	}
}