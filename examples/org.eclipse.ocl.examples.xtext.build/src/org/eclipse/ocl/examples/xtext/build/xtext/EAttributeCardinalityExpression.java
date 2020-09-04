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
package org.eclipse.ocl.examples.xtext.build.xtext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.DynamicRuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.UserSlotsAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEAttributeSize;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * A CardinalityExpression equates the sum of CardinailtyVariable products to the number of elements in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class EAttributeCardinalityExpression extends AbstractCardinalityExpression
{
	protected final @NonNull EAttribute eAttribute;
	protected final @NonNull EnumerationValue enumerationValue;
	private final @NonNull Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> enumerationValue2cardinalityExpression = new HashMap<>();

	public EAttributeCardinalityExpression(@NonNull String name, /*@NonNull*/ EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		super(name);
		assert eAttribute != null;
		this.eAttribute = eAttribute;
		this.enumerationValue = enumerationValue;
	}

	@Override
	public boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		UserSlotsAnalysis slotsAnalysis = dynamicRuleMatch.getSlotsAnalysis();
		for (Entry<@NonNull EnumerationValue, @NonNull CardinalityExpression> entry : enumerationValue2cardinalityExpression.entrySet()) {
			EnumerationValue value = entry.getKey();
			CardinalityExpression nestedExpression = entry.getValue();
			int requiredCount = nestedExpression.solve(dynamicRuleMatch);
			int actualCount = slotsAnalysis.getSize(eAttribute, value);
			if (requiredCount != actualCount) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected @NonNull SerializationMatchTermEAttributeSize createSizeCardinalitySolution() {
		return new SerializationMatchTermEAttributeSize(eAttribute, enumerationValue);
	}

	@Override
	public @NonNull CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue) {
		CardinalityExpression cardinalityExpression = enumerationValue2cardinalityExpression.get(enumerationValue);
		if (cardinalityExpression == null) {
			grammarAnalysis.addEnumeration(eAttribute, enumerationValue);
			String subName = name + "." + enumerationValue2cardinalityExpression.size();
			cardinalityExpression = new EAttributeCardinalityExpression(subName, eAttribute, enumerationValue);
			enumerationValue2cardinalityExpression.put(enumerationValue, cardinalityExpression);
		}
		return cardinalityExpression;
	}

	@Override
	public @Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions() {
		return enumerationValue2cardinalityExpression.values();
	}

	public @NonNull EAttribute getEAttribute() {
		return eAttribute;
	}

	public @NonNull EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	public @Nullable Map<@NonNull EnumerationValue, @NonNull CardinalityExpression> getEnumerationValue2cardinalityExpression() {
		return enumerationValue2cardinalityExpression;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(name);
		s.append(": |");
		s.append(eAttribute.getName());
		s.append(".'");
		s.append(enumerationValue.getName());
		s.append("'| = ");
		appendSumOfProducts(s);
		List<@NonNull CardinalityExpression> sortedExpressions = new ArrayList<>(enumerationValue2cardinalityExpression.values());
		Collections.sort(sortedExpressions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityExpression cardinalityExpression : sortedExpressions) {
			StringUtil.appendIndentation(s, depth);
			cardinalityExpression.toString(s, depth);
		}
	}
}