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
package org.eclipse.ocl.examples.xtext.build.elements;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.build.analysis.SerializationRuleAnalysis;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;

/**
 * SerializationRuleAnalysisComparator provides a stable comparison that may be used in a sort to
 * prioritize simpler rules first. This avoids gratuittous punctuation around optional
 * sequences of elements.
 */
public class SerializationRuleAnalysisComparator implements Comparator<@NonNull SerializationRuleAnalysis>
{
	private Map<@NonNull SerializationRuleAnalysis, @NonNull Integer> rule2size = new HashMap<>();

	@Override
	public int compare(@NonNull SerializationRuleAnalysis rule1, @NonNull SerializationRuleAnalysis rule2) {
		int size1 = getSize(rule1);
		int size2 = getSize(rule2);
		int diff = size1 - size2;
		if (diff != 0) {
			return diff;
		}
		String name1 = rule1.getVariantName();
		String name2 = rule2.getVariantName();
		diff = name1.compareTo(name2);
		assert diff != 0;
		return diff;
	}

	private int getSize(@NonNull SerializationRuleAnalysis serializationRule) {
		Integer size = SerializationUtils.maybeNull(rule2size.get(serializationRule));
		if (size == null) {
			size = getSize(serializationRule.getRootSerializationNode());
			rule2size.put(serializationRule, size);
		}
		return size;
	}

	private int getSize(@NonNull SerializationNode parentSerializationNode) {
		int size = 0;
		if (parentSerializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode childSerializationNode : ((SequenceSerializationNode)parentSerializationNode).getSerializationNodes()) {
				size += 2 * getSize(childSerializationNode);		// 2 penalizes nesting
			}
		}
		else {
			size++;
		}
		return size;
	}
}