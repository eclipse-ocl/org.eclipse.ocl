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

import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.serializer.DynamicRuleMatch;
import org.eclipse.ocl.examples.xtext.serializer.EnumerationValue;
import org.eclipse.ocl.examples.xtext.serializer.ParserRuleValue;
import org.eclipse.ocl.examples.xtext.serializer.UserSlotsAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.SerializationMatchTerm.SerializationMatchTermEReferenceSize;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

/**
 * A CardinalityExpression eqates the sum of CardinailtyVariable products to the number of elemets in an eStrucuralFeature slot.
 *
 * Multiple CardinalityExpressions provide a set of simultaneous equations for which an integer solution mmust be found to
 * select a potential serialization option.
 */
public class EReferenceCardinalityExpression extends AbstractCardinalityExpression
{
	protected final @NonNull EReference eReference;
	protected final @NonNull ParserRuleValue parserRuleValue;
	private @NonNull Map<@NonNull ParserRuleValue, @NonNull CardinalityExpression> parserRuleValue2cardinalityExpression = new HashMap<>();

	public EReferenceCardinalityExpression(@NonNull String name, /*@NonNull*/ EReference eReference, @NonNull ParserRuleValue parserRuleValue) {
		super(name);
		assert eReference != null;
		this.eReference = eReference;
		this.parserRuleValue = parserRuleValue;
	}

	@Override
	public boolean checkSize(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		UserSlotsAnalysis slotsAnalysis = dynamicRuleMatch.getSlotsAnalysis();
		for (Entry<@NonNull ParserRuleValue, @NonNull CardinalityExpression> entry : parserRuleValue2cardinalityExpression.entrySet()) {
			ParserRuleValue value = entry.getKey();
			CardinalityExpression nestedExpression = entry.getValue();
			int requiredCount = nestedExpression.solve(dynamicRuleMatch);
			int actualCount = slotsAnalysis.getSize(eReference, value);
			if (requiredCount != actualCount) {
				return false;
			}
		}
		return true;
	}

	@Override
	protected @NonNull SerializationMatchTermEReferenceSize createSizeCardinalitySolution() {
		return new SerializationMatchTermEReferenceSize(eReference, parserRuleValue);
	}

	@Override
	public @NonNull CardinalityExpression getCardinalityExpression(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EnumerationValue enumerationValue) {
		CardinalityExpression cardinalityExpression = parserRuleValue2cardinalityExpression.get(parserRuleValue);
		if (cardinalityExpression == null) {
			String subName = name + "." + parserRuleValue2cardinalityExpression.size();
			cardinalityExpression = new EReferenceCardinalityExpression(subName, eReference, parserRuleValue);
			parserRuleValue2cardinalityExpression.put(parserRuleValue, cardinalityExpression);
		}
		return cardinalityExpression;
	}

	@Override
	public @Nullable Iterable<@NonNull CardinalityExpression> getCardinalityExpressions() {
		return parserRuleValue2cardinalityExpression.values();
	}

	public @NonNull EReference getEReference() {
		return eReference;
	}

	public @NonNull ParserRuleValue getParserRuleValue() {
		return parserRuleValue;
	}

	public @Nullable Map<@NonNull ParserRuleValue, @NonNull CardinalityExpression> getParserRuleValue2cardinalityExpression() {
		return parserRuleValue2cardinalityExpression;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(name);
		s.append(": |");
		s.append(eReference.getName());
		s.append(".'");
		s.append(parserRuleValue.getRuleName());
		s.append("'| = ");
		appendSumOfProducts(s);
		List<@NonNull CardinalityExpression> sortedExpressions = new ArrayList<>(parserRuleValue2cardinalityExpression.values());
		Collections.sort(sortedExpressions, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull CardinalityExpression cardinalityExpression : sortedExpressions) {
			StringUtil.appendIndentation(s, depth);
			cardinalityExpression.toString(s, depth);
		}
	}
}