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

import java.util.Comparator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.Indexed;
import org.eclipse.ocl.examples.xtext.serializer.Nameable;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString;
import org.eclipse.ocl.examples.xtext.serializer.ToDebugString.ToDebugStringable;
import org.eclipse.xtext.AbstractRule;

/**
 * An AbstractRuleAnalysis provides the extended analysis of an Xtext AbstractRule
 */
public abstract class AbstractRuleAnalysis implements Indexed, Nameable, ToDebugStringable
{
	public static final class QualifiedNameableComparator implements Comparator<@NonNull AbstractRuleAnalysis>
	{
		public static final @NonNull QualifiedNameableComparator INSTANCE = new QualifiedNameableComparator();

		@Override
		public int compare(@NonNull AbstractRuleAnalysis o1, @NonNull AbstractRuleAnalysis o2) {
			String n1 = o1.getQualifiedName();
			String n2 = o2.getQualifiedName();
			return SerializationUtils.safeCompareTo(n1, n2);
		}
	}

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
	protected final @NonNull String qualifiedName;

	@SuppressWarnings("unused")			// Used in the debugger
	private final @NonNull ToDebugString toDebugSring = new ToDebugString(this){};

	public AbstractRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, int index, @NonNull AbstractRule abstractRule) {
		this.grammarAnalysis = grammarAnalysis;
		this.index = index;
		this.abstractRule = abstractRule;
		String grammarName = SerializationUtils.getEContainingGrammar(abstractRule).getName();
		int dotIndex = grammarName.lastIndexOf('.');
		if (dotIndex >= 0) {
			grammarName = grammarName.substring(dotIndex+1);
		}
		this.qualifiedName = grammarName + "::" + SerializationUtils.getName(abstractRule);
	}

	public abstract @Nullable GrammarRuleValue basicGetRuleValue();

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public @NonNull String getName() {
		return SerializationUtils.getName(abstractRule);
	}

	public @NonNull String getQualifiedName() {
		return qualifiedName;
	}

	public @NonNull AbstractRule getRule() {
		return abstractRule;
	}

	public abstract @NonNull GrammarRuleValue getRuleValue();

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		s.append(toString());
	}

	@Override
	public @NonNull String toString() {
		return getName();
	}

}