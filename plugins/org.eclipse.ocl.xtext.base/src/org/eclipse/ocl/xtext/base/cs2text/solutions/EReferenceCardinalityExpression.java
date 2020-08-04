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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

/**
 * A CardinalityExpression eqates the sum of CardinailtyVariable products to the number of elemets in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class EReferenceCardinalityExpression extends AbstractCardinalityExpression
{
	protected final @NonNull EReference eReference;
	protected final @NonNull ParserRuleAnalysis ruleAnalysis;
	private @NonNull Map<@NonNull ParserRuleAnalysis, @NonNull AbstractCardinalityExpression> ruleAnalysis2cardinalityExpression = new HashMap<>();

	public EReferenceCardinalityExpression(@NonNull String name, @NonNull EReference eReference, @NonNull ParserRuleAnalysis ruleAnalysis) {
		super(name);
		this.eReference = eReference;
		this.ruleAnalysis = ruleAnalysis;
	}

	@Override
	public boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		UserSlotsAnalysis slotsAnalysis = dynamicRuleMatch.getSlotsAnalysis();
		for (Entry<@NonNull ParserRuleAnalysis, @NonNull AbstractCardinalityExpression> entry : ruleAnalysis2cardinalityExpression.entrySet()) {
			ParserRuleAnalysis value = entry.getKey();
			AbstractCardinalityExpression nestedExpression = entry.getValue();
			int requiredCount = nestedExpression.solve(dynamicRuleMatch);
			int actualCount = slotsAnalysis.getSize(eReference, value);
			if (requiredCount != actualCount) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected @NonNull EReferenceSizeCardinalitySolution createSizeCardinalitySolution() {
		return new EReferenceSizeCardinalitySolution(eReference, ruleAnalysis);
	}

	@Override
	public @NonNull AbstractCardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue) {
		AbstractCardinalityExpression cardinalityExpression = ruleAnalysis2cardinalityExpression.get(ruleAnalysis);
		if (cardinalityExpression == null) {
			String subName = name + "." + ruleAnalysis2cardinalityExpression.size();
			cardinalityExpression = new EReferenceCardinalityExpression(subName, eReference, ruleAnalysis);
			ruleAnalysis2cardinalityExpression.put(ruleAnalysis, cardinalityExpression);
		}
		return cardinalityExpression;
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractCardinalityExpression> getCardinalityExpressions() {
		return ruleAnalysis2cardinalityExpression.values();
	}

	public @Nullable ParserRuleAnalysis getRuleAnalysis() {
		return ruleAnalysis;
	}

	public @Nullable Map<@NonNull ParserRuleAnalysis, @NonNull AbstractCardinalityExpression> getRuleAnalysis2cardinalityExpression() {
		return ruleAnalysis2cardinalityExpression;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(name);
		s.append(": |");
		s.append(eReference.getName());
		s.append(".'");
		s.append(ruleAnalysis.getName());
		s.append("'| = ");
		appendSumOfProducts(s);
		List<@NonNull AbstractCardinalityExpression> sortedExpressions = new ArrayList<>(ruleAnalysis2cardinalityExpression.values());
		Collections.sort(sortedExpressions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull AbstractCardinalityExpression cardinalityExpression : sortedExpressions) {
			StringUtil.appendIndentation(s, depth);
			s.append("- ");
			cardinalityExpression.toString(s, depth);
		}
	}
}