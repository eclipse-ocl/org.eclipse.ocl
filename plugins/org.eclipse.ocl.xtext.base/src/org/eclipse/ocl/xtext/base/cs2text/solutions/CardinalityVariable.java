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
package org.eclipse.ocl.xtext.base.cs2text.solutions;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;

/**
 * A CardinalityVariable represents the cardinality of a grammar term for which a constant value must be deduced prior
 * to serialization of the term.
 */
public class CardinalityVariable implements Nameable
{
	protected final @NonNull String name;
	protected final @Nullable Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses;
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

	public CardinalityVariable(@NonNull String name, @Nullable Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.name = name;
		this.ruleAnalyses = ruleAnalyses;
		this.multiplicativeCardinality = multiplicativeCardinality;
		assert !multiplicativeCardinality.isOne();
	}

	public boolean isOne() {
		return multiplicativeCardinality.isOne();
	}

	public boolean mayBeMany() {
		return multiplicativeCardinality.mayBeMany();
	}

	public boolean mayBeNone() {
		return multiplicativeCardinality.mayBeZero();
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(name);
		Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses2 = ruleAnalyses;
		if (ruleAnalyses2 != null) {
			s.append(":");
			boolean isFirst = true;
			for (@NonNull AbstractRuleAnalysis ruleAnalysis : ruleAnalyses2) {
				if (!isFirst) {
					s.append("|");
				}
				s.append(ruleAnalysis.getRuleName());
				isFirst = false;
			}
		}
		s.append("[");
		s.append(multiplicativeCardinality);
		s.append("]");
	}
}