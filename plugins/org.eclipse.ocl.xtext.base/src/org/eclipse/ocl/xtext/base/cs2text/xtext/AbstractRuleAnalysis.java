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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.xtext.AbstractRule;

/**
 * An XtextAbstractRuleAnalysis provides the extended analysis of an Xtext AbstractRule
 */
public class AbstractRuleAnalysis implements Nameable
{
	/**#
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull GrammarAnalysis grammarAnalysis;

	/**
	 * The analyzed Xtext rule.
	 */
	protected final @NonNull AbstractRule abstractRule;

	/**
	 * The semi-qualified name of this rule (final part of grammar name and rule name).
	 */
	protected final @NonNull String name;

	/**
	 * RuleAnalyses that this RuleAnalysis may be directly used as an alternative for.
	 */
	private @Nullable List<@NonNull AbstractRuleAnalysis> baseRuleAnalyses = null;		// XXX obsolete

	/**
	 * Lazily computed closure of RuleAnalyses that this RuleAnalysis may be used as an alternative for.
	 */
	private @Nullable UniqueList<@NonNull AbstractRuleAnalysis> baseRuleAnalysesClosure = null;

	/**
	 * The terms for each possible permutation of alternatives.
	 */
//	private @Nullable List<@NonNull XtextTermsAnalysis> termsAnalyses = null;

	public AbstractRuleAnalysis(@NonNull GrammarAnalysis grammarAnalysis, @NonNull AbstractRule abstractRule) {
		this.grammarAnalysis = grammarAnalysis;
		this.abstractRule = abstractRule;
		String grammarName = XtextGrammarUtil.getEContainingGrammar(abstractRule).getName();
		int index = grammarName.lastIndexOf('.');
		if (index >= 0) {
			grammarName = grammarName.substring(index+1);
		}
		this.name = grammarName + "::" + XtextGrammarUtil.getName(abstractRule);
	}

	public void addBaseRuleAnalysis(@NonNull AbstractRuleAnalysis baseRuleAnalysis) {
		List<@NonNull AbstractRuleAnalysis> baseRuleAnalyses2 = baseRuleAnalyses;
		if (baseRuleAnalyses2 == null) {
			baseRuleAnalyses = baseRuleAnalyses2 = new ArrayList<>();
		}
		if (!baseRuleAnalyses2.contains(baseRuleAnalysis)) {
			baseRuleAnalyses2.add(baseRuleAnalysis);
		}
	}

//	public void addTermsAnalysis(@NonNull XtextTermsAnalysis termsAnalysis) {
//		assert termsAnalyses != null;
//		termsAnalyses.add(termsAnalysis);
//	}

	public @Nullable List<@NonNull AbstractRuleAnalysis> basicGetBaseRuleAnalyses() {
		return baseRuleAnalyses;
	}

//	public @Nullable List<@NonNull XtextTermsAnalysis> basicGetTermsAnalyses() {
//		return termsAnalyses;
//	}

	public @NonNull UniqueList<@NonNull AbstractRuleAnalysis> getBaseRuleAnalysisClosure() {
		UniqueList<@NonNull AbstractRuleAnalysis> baseRuleAnalysesClosure = this.baseRuleAnalysesClosure;
		if (baseRuleAnalysesClosure == null) {
			baseRuleAnalysesClosure = new UniqueList<>();
			baseRuleAnalysesClosure.add(this);
			for (int i = 0; i < baseRuleAnalysesClosure.size(); i++) {
				AbstractRuleAnalysis ruleAnalysis = baseRuleAnalysesClosure.get(i);
				List<@NonNull AbstractRuleAnalysis> baseRuleAnalyses = ruleAnalysis.basicGetBaseRuleAnalyses();
				if (baseRuleAnalyses != null) {
					baseRuleAnalysesClosure.addAll(baseRuleAnalyses);
				}
			}
		}
		Collections.sort(baseRuleAnalysesClosure, NameUtil.NAMEABLE_COMPARATOR);
		this.baseRuleAnalysesClosure = baseRuleAnalysesClosure;
		return baseRuleAnalysesClosure;
	}

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
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

//	public @NonNull List<@NonNull XtextTermsAnalysis> getTermsAnalyses() {
//		assert termsAnalyses != null;
//		return termsAnalyses;
//	}

	/**
	 * Perform the inter analysis to determine the base rule closure.
	 *
	public void interAnalyze() {
		if ("TypedTypeRefCS".equals(abstractRule.getName())) {
			getClass(); //XXX
		}
		List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses2 = baseRuleAnalyses;
		if (baseRuleAnalyses2 != null) {
			UniqueList<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalysisClosure = new UniqueList<>(baseRuleAnalyses2);
			for (int i = 0; i < baseRuleAnalysisClosure.size(); i++) {
				XtextAbstractRuleAnalysis baseRuleAnalysis = baseRuleAnalysisClosure.get(i);
				List<@NonNull XtextAbstractRuleAnalysis> nestedBaseRuleAnalyses = baseRuleAnalysis.getBaseRuleAnalyses();
				if (nestedBaseRuleAnalyses != null) {
					baseRuleAnalysisClosure.addAll(nestedBaseRuleAnalyses);
				}
			}
			this.baseRuleAnalyses = baseRuleAnalysisClosure;
		}
	}*/

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getName());
		s.append(" -> ");
		boolean isFirst1 = true;
		for (@NonNull AbstractRuleAnalysis baseRuleAnalyses : getBaseRuleAnalysisClosure()) {
			if (!isFirst1) {
				s.append(",");
			}
			s.append(baseRuleAnalyses.getName());
			isFirst1 = false;
		}
/*		s.append(" <=> ");
		boolean isFirst2 = true;
		for (@NonNull EClassifier eClassifier : eClassifiers) {
			if (!isFirst2) {
				s.append(",");
			}
			s.append(eClassifier.getName());
			isFirst2 = false;
		} */
		return s.toString();
	}
}