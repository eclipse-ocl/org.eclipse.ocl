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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.ToDebugString;
import org.eclipse.ocl.xtext.base.cs2text.ToDebugString.ToDebugStringable;
import org.eclipse.xtext.AbstractRule;

/**
 * An AbstractRuleAnalysis provides the extended analysis of an Xtext AbstractRule
 */
public abstract class AbstractRuleAnalysis implements Indexed, Nameable, ToDebugStringable
{
	/**#
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull GrammarAnalysis grammarAnalysis;

	protected final int index;

	/**
	 * The analyzed Xtext rule.
	 */
	protected final @NonNull AbstractRule abstractRule;

	/**
	 * The semi-qualified name of this rule (final part of grammar name and rule name).
	 */
	protected final @NonNull String name;

	@SuppressWarnings("unused")			// Used in the debugger
	private final @NonNull ToDebugString toDebugSring = new ToDebugString(this){};

	public AbstractRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, int index, @NonNull AbstractRule abstractRule) {
		this.grammarAnalysis = grammarAnalysis;
		this.index = index;
		this.abstractRule = abstractRule;
		String grammarName = XtextGrammarUtil.getEContainingGrammar(abstractRule).getName();
		int dotIndex = grammarName.lastIndexOf('.');
		if (dotIndex >= 0) {
			grammarName = grammarName.substring(dotIndex+1);
		}
		this.name = grammarName + "::" + XtextGrammarUtil.getName(abstractRule);
	}

	public abstract @Nullable AbstractRuleValue basicGetRuleValue();

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public @NonNull AbstractRule getRule() {
		return abstractRule;
	}

	public @NonNull String getRuleName() {
		return XtextGrammarUtil.getName(abstractRule);
	}

	public abstract @NonNull AbstractRuleValue getRuleValue();

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		s.append(toString());
	}

	@Override
	public @NonNull String toString() {
		return getRuleName();
	}

}