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
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.user.UserModelAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

/**
 * A Serializer supports the serialization of a user model element, automatocally creating a hierarchy
 * of Serizers for the containment herarchy od the user model element.
 */
public class Serializer
{
	protected final @NonNull SerializationRule serializationRule;
	protected final @NonNull PreSerializer preSerializer;
	protected final @NonNull UserModelAnalysis modelAnalysis;
	protected final @NonNull EObject element;
	protected final @NonNull Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value;
	private @Nullable Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions = null;

	public Serializer(@NonNull SerializationRule serializationRule, @NonNull UserModelAnalysis modelAnalysis,
			@NonNull EObject element, @NonNull Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value) {
		this.serializationRule = serializationRule;
		this.preSerializer = serializationRule.getPreSerializer();
		this.modelAnalysis = modelAnalysis;
		this.element = element;
		this.variable2value = variable2value;
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

	public @NonNull EObject getElement() {
		return element;
	}

	/**
	 * Serialize this serializer's configured element to the serializationBuilder.
	 */
	public void serialize(@NonNull SerializationBuilder serializationBuilder) {
		List<@NonNull SerializationNode> serializationNodes = Collections.singletonList(serializationRule.getRootSerializationNode());
		serializeNodes(serializationBuilder, serializationNodes);
	}

	/**
	 * Create and use a new serilaizer to to serialize element to the serializationBuilder.
	 */
	public void serializeElement(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
		modelAnalysis.serialize(serializationBuilder, element);
	}

	/**
	 * Serialize a serializationNode to the serializationBuilder.
	 */
	protected void serializeNode(@NonNull SerializationBuilder serializationBuilder, @NonNull SerializationNode serializationNode) {
		if (serializationNode.getMultiplicativeCardinality().isOne()) {
			serializationNode.serialize(this, serializationBuilder);
		}
		else {
			CardinalityVariable variable = preSerializer.getVariable(serializationNode);
			Integer value = variable2value.get(variable);
			assert value != null;
			for (int i = 0; i < value.intValue(); i++) {
				serializationNode.serialize(this, serializationBuilder);
			}
		}
	}

	/**
	 * Serialize a sequence of serializationNodes to the serializationBuilder.
	 */
	public void serializeNodes(@NonNull SerializationBuilder serializationBuilder, @NonNull Iterable<@NonNull SerializationNode> serializationNodes) {
		for (@NonNull SerializationNode serializationNode : serializationNodes) {
			serializeNode(serializationBuilder, serializationNode);
		}
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