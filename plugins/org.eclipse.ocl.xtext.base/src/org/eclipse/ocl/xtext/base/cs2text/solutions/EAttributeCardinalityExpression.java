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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;

/**
 * A CardinalityExpression eqates the sum of CardinailtyVariable products to the number of elemets in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public abstract class EAttributeCardinalityExpression extends AbstractCardinalityExpression
{
	protected final @NonNull EAttribute eAttribute;
	protected final @NonNull EnumerationValue enumerationValue;
	private @Nullable Map<@NonNull EnumerationValue, @NonNull AbstractCardinalityExpression> enumerationValue2cardinalityExpression = null;

	protected EAttributeCardinalityExpression(@NonNull String name, @NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		super(name);
		this.eAttribute = eAttribute;
		this.enumerationValue = enumerationValue;
	}

	@Override
	public boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		UserSlotsAnalysis slotsAnalysis = dynamicRuleMatch.getSlotsAnalysis();
		if (enumerationValue2cardinalityExpression != null) {
			for (Entry<@NonNull EnumerationValue, @NonNull AbstractCardinalityExpression> entry : enumerationValue2cardinalityExpression.entrySet()) {
				EnumerationValue value = entry.getKey();
				AbstractCardinalityExpression nestedExpression = entry.getValue();
				int requiredCount = nestedExpression.solve(dynamicRuleMatch);
				int actualCount = slotsAnalysis.getSize(eAttribute, value);
				if (requiredCount != actualCount) {
					return false;
				}
			}
		}
		else {
			assert getEnumerationValue().isNull();
			int requiredCount = solve(dynamicRuleMatch);
			int actualCount = slotsAnalysis.getSize(eAttribute);
			if (requiredCount != actualCount) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected @NonNull EAttributeSizeCardinalitySolution createSizeCardinalitySolution() {
		return new EAttributeSizeCardinalitySolution(eAttribute, enumerationValue);
	}

	@Override
	public @NonNull AbstractCardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue) {
		Map<@NonNull EnumerationValue, @NonNull AbstractCardinalityExpression> enumerationValue2cardinalityExpression2 = enumerationValue2cardinalityExpression;
		if (enumerationValue2cardinalityExpression2 == null) {
			enumerationValue2cardinalityExpression = enumerationValue2cardinalityExpression2 = new HashMap<>();
		}
		AbstractCardinalityExpression cardinalityExpression = enumerationValue2cardinalityExpression2.get(enumerationValue);
		if (cardinalityExpression == null) {
			grammarAnalysis.addEnumeration(eAttribute, enumerationValue);
			String subName = name + "." + enumerationValue2cardinalityExpression2.size();
			if (enumerationValue.isNull()) {
				cardinalityExpression = new EStructuralFeatureCardinalityExpression(subName, eAttribute);
			}
			else {
				cardinalityExpression = new EAttributeCardinalityExpression1(subName, eAttribute, enumerationValue);
			}
			enumerationValue2cardinalityExpression2.put(enumerationValue, cardinalityExpression);
		}
		return cardinalityExpression;
	}

	@Override
	public @Nullable Iterable<@NonNull AbstractCardinalityExpression> getCardinalityExpressions() {
		return enumerationValue2cardinalityExpression != null ? enumerationValue2cardinalityExpression.values() : null;
	}

	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	public @Nullable Map<@NonNull EnumerationValue, @NonNull AbstractCardinalityExpression> getEnumerationValue2cardinalityExpression() {
		return enumerationValue2cardinalityExpression;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(name);
		s.append(": |");
		s.append(eAttribute.getName());
		if (!enumerationValue.isNull()) {
			s.append(".'");
			s.append(enumerationValue.getName());
			s.append("'");
		}
		s.append("| = ");
		appendSumOfProducts(s);
		Map<@NonNull EnumerationValue, @NonNull AbstractCardinalityExpression> enumerationValue2cardinalityExpression2 = enumerationValue2cardinalityExpression;
		if (enumerationValue2cardinalityExpression2 != null) {
			List<@NonNull AbstractCardinalityExpression> sortedExpressions = new ArrayList<>(enumerationValue2cardinalityExpression2.values());
			Collections.sort(sortedExpressions, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull AbstractCardinalityExpression cardinalityExpression : sortedExpressions) {
				StringUtil.appendIndentation(s, depth, "  ");
				s.append("- ");
				cardinalityExpression.toString(s, depth);
			}
		}
	}
}