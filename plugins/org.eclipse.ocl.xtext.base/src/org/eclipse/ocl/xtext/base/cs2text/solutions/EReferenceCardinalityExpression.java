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
	protected final @Nullable ParserRuleAnalysis ruleAnalysis;
	private @Nullable Map<@Nullable ParserRuleAnalysis, @NonNull AbstractCardinalityExpression> ruleAnalysis2cardinalityExpression = null;

	public EReferenceCardinalityExpression(@NonNull String name, @NonNull EReference eReference, @Nullable ParserRuleAnalysis ruleAnalysis) {
		super(name);
		this.eReference = eReference;
		this.ruleAnalysis = ruleAnalysis;
	}

	@Override
	public boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		UserSlotsAnalysis slotsAnalysis = dynamicRuleMatch.getSlotsAnalysis();
		if (ruleAnalysis2cardinalityExpression != null) {
			for (Entry<@Nullable ParserRuleAnalysis, @NonNull AbstractCardinalityExpression> entry : ruleAnalysis2cardinalityExpression.entrySet()) {
				ParserRuleAnalysis value = entry.getKey();
				AbstractCardinalityExpression nestedExpression = entry.getValue();
				int requiredCount = nestedExpression.solve(dynamicRuleMatch);
				int actualCount = slotsAnalysis.getSize(eReference, value);
				if (requiredCount != actualCount) {
					return false;
				}
			}
		}
		else {
			assert ruleAnalysis == null;
			int requiredCount = solve(dynamicRuleMatch);
			int actualCount = slotsAnalysis.getSize(eReference);
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
		Map<@Nullable ParserRuleAnalysis, @NonNull AbstractCardinalityExpression> ruleAnalysis2cardinalityExpression2 = ruleAnalysis2cardinalityExpression;
		if (ruleAnalysis2cardinalityExpression2 == null) {
			ruleAnalysis2cardinalityExpression = ruleAnalysis2cardinalityExpression2 = new HashMap<>();
		}
		AbstractCardinalityExpression cardinalityExpression = ruleAnalysis2cardinalityExpression2.get(ruleAnalysis);
		if (cardinalityExpression == null) {
		//	grammarAnalysis.addEnumeration(eReference, enumerationValue);
			String subName = name + "." + ruleAnalysis2cardinalityExpression2.size();
			cardinalityExpression = new EReferenceCardinalityExpression(subName, eReference, ruleAnalysis);
			ruleAnalysis2cardinalityExpression2.put(ruleAnalysis, cardinalityExpression);
		}
		return cardinalityExpression;
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractCardinalityExpression> getCardinalityExpressions() {
		return ruleAnalysis2cardinalityExpression != null ? ruleAnalysis2cardinalityExpression.values() : null;
	}

	public @Nullable ParserRuleAnalysis getRuleAnalysis() {
		return ruleAnalysis;
	}

	public @Nullable Map<@Nullable ParserRuleAnalysis, @NonNull AbstractCardinalityExpression> getRuleAnalysis2cardinalityExpression() {
		return ruleAnalysis2cardinalityExpression;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(name);
		s.append(": |");
		s.append(eReference.getName());
		ParserRuleAnalysis ruleAnalysis2 = ruleAnalysis;
		if (ruleAnalysis2 != null) {
			s.append(".'");
			s.append(ruleAnalysis2.getName());
			s.append("'");
		}
		s.append("| = ");
		appendSumOfProducts(s);
		Map<@Nullable ParserRuleAnalysis, @NonNull AbstractCardinalityExpression> ruleAnalysis2cardinalityExpression2 = ruleAnalysis2cardinalityExpression;
		if (ruleAnalysis2cardinalityExpression2 != null) {
			List<@NonNull AbstractCardinalityExpression> sortedExpressions = new ArrayList<>(ruleAnalysis2cardinalityExpression2.values());
			Collections.sort(sortedExpressions, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull AbstractCardinalityExpression cardinalityExpression : sortedExpressions) {
				StringUtil.appendIndentation(s, depth, "  ");
				s.append("- ");
				cardinalityExpression.toString(s, depth);
			}
		}
	}
}