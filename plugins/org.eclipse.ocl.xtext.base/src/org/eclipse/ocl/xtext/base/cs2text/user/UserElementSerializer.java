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
package org.eclipse.ocl.xtext.base.cs2text.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

/**
 * A Serializer supports the serialization of a user model element, automatocally creating a hierarchy
 * of Serizers for the containment herarchy od the user model element.
 */
public class UserElementSerializer
{
	protected final @NonNull DynamicRuleMatch dynamicRuleMatch;
	protected final @NonNull SerializationRule serializationRule;
	protected final @NonNull UserModelAnalysis modelAnalysis;

	protected final @NonNull EObject element;
	private @Nullable Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions = null;

	public UserElementSerializer(@NonNull DynamicRuleMatch dynamicRuleMatch, @NonNull UserModelAnalysis modelAnalysis,
			@NonNull EObject element) {
		this.dynamicRuleMatch = dynamicRuleMatch;
		this.serializationRule = dynamicRuleMatch.getSerializationRule();
		this.modelAnalysis = modelAnalysis;
		this.element = element;
	}

	/**
	 * Return the next eStructuralFeature child object of this serializer's element.
	 */
	public @Nullable Object consumeNext(@NonNull EStructuralFeature eStructuralFeature) {
		Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions = this.feature2consumptions;
		if (feature2consumptions == null) {
			this.feature2consumptions = feature2consumptions = new HashMap<>();
		}
		int index;
		Integer count = feature2consumptions.get(eStructuralFeature);
		if (count == null) {
			feature2consumptions.put(eStructuralFeature, Integer.valueOf(1));
			index = 0;
		}
		else {
			int intValue = count.intValue();
			feature2consumptions.put(eStructuralFeature, Integer.valueOf(intValue+1));
			index = intValue;
		}
		Object object = element.eGet(eStructuralFeature);
		if (eStructuralFeature.isMany()) {
			@SuppressWarnings("unchecked") List<EObject> eList = (List<EObject>)object;
			assert index < eList.size();
			object = eList.get(index);
		}
		else {
			assert index == 0;
		}
		return object;
	}

	public @NonNull DynamicRuleMatch getDynamicRuleMatch() {
		return dynamicRuleMatch;
	}

	public @NonNull EObject getElement() {
		return element;
	}

	public @NonNull UserModelAnalysis getModelAnalysis() {
		return modelAnalysis;
	}

	public @NonNull SerializationRule getSerializationRule() {
		return serializationRule;
	}

	public @Nullable SubIdiom getSubIdiom(@NonNull SerializationNode serializationNode) {
		return serializationRule.getBasicSerializationRule().getSubIdiom(serializationNode);
	}

	public int getValue(int cardinalityVariableIndex) {
		return ClassUtil.nonNullState(dynamicRuleMatch.getValue(cardinalityVariableIndex)).intValue();
	}

	/**
	 * Serialize this serializer's configured element to the serializationBuilder.
	 */
	public void serialize(@NonNull SerializationBuilder serializationBuilder) {
		serializationRule.serializeRule(this, serializationBuilder);
	}

	/**
	 * Create and use a new serilaizer to to serialize element to the serializationBuilder.
	 */
	public void serializeElement(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element, @Nullable AbstractRuleValue targetRuleValue) {
		modelAnalysis.serialize(serializationBuilder, element, targetRuleValue);
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions2 = feature2consumptions;
		if (feature2consumptions2 != null) {
			List<@NonNull EStructuralFeature> features = new ArrayList<>(feature2consumptions2.keySet());
			Collections.sort(features, NameUtil.ENAMED_ELEMENT_COMPARATOR);
			boolean isFirst = true;
			for (@NonNull EStructuralFeature feature : features) {
				if (!isFirst) {
					s.append(",");
				}
				s.append(XtextGrammarUtil.getName(feature));
				Integer consumed = feature2consumptions2.get(feature);
				assert consumed != null;
				s.append("[");
				s.append(consumed);
				s.append("]");
				isFirst = false;
			}
		}
		return  s.toString();
	}
}