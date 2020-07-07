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
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;

public class Serializer
{
	protected final @NonNull RequiredSlotsConjunction requiredSlotsConjunction;
	protected final @NonNull PreSerializer preSerializer;
	protected final @NonNull UserModelAnalysis modelAnalysis;
	protected final @NonNull EObject element;
	protected final @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size;
	private @Nullable Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = null;
	private @Nullable Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions = null;

	protected Serializer(@NonNull RequiredSlotsConjunction requiredSlotsConjunction, @NonNull UserModelAnalysis modelAnalysis,
			@NonNull EObject element, @NonNull Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size) {
		this.requiredSlotsConjunction = requiredSlotsConjunction;
		this.preSerializer = requiredSlotsConjunction.getPreSerializer();
		this.modelAnalysis = modelAnalysis;
		this.element = element;
		this.eFeature2size = eFeature2size;
	}

	/**
	 * Return the consumption index of the next feature slot.
	 */
	public int consume(@NonNull EStructuralFeature feature) {
		Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions = this.feature2consumptions;
		if (feature2consumptions == null) {
			this.feature2consumptions = feature2consumptions = new HashMap<>();
		}
		Integer count = feature2consumptions.get(feature);
		if (count == null) {
			feature2consumptions.put(feature, Integer.valueOf(1));
			return 0;
		}
		else {
			int intValue = count.intValue();
			feature2consumptions.put(feature, Integer.valueOf(intValue+1));
			return intValue;
		}
	}

	public @NonNull EObject getElement() {
		return element;
	}

	public boolean hasCompatibleCardinalities(@NonNull RequiredSlotsConjunction conjunction, @NonNull EObject element) {
		assert variable2value == null;
		this.variable2value = preSerializer.computeActualCardinalities(element, eFeature2size);
		return variable2value != null;
	}

	public void serializeConjunction(@NonNull SerializationBuilder serializationBuilder) {
		List<@NonNull SerializationNode> serializedNodes = requiredSlotsConjunction.getSerializedNodes();
		serializeElements(serializationBuilder, serializedNodes);
	}

	public void serializeElement(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
		SerializationBuilder nestedSerializationBuilder = serializationBuilder.createNestedSerializationBuilder();
		modelAnalysis.serialize(nestedSerializationBuilder, element);
	}

	protected void serializeElements(@NonNull SerializationBuilder serializationBuilder, @NonNull List<@NonNull SerializationNode> serializedNodes) {
		Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value2 = variable2value;
		assert variable2value2 != null;
		for (@NonNull SerializationNode serializedNode : serializedNodes) {
			CardinalityVariable variable = preSerializer.getVariable(serializedNode);
			Integer value = variable2value2.get(variable);
			assert value != null;
			for (int i = 0; i < value.intValue(); i++) {
				serializedNode.serialize(this, serializationBuilder);
			}
		}
	}

	protected void serialize1(@NonNull SerializationBuilder serializationBuilder, @NonNull SerializationNode serializedNode) {
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