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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRuleAnalysis;

/**
 * SerializationRuleComparator provides a stable comparison that may be used in a sort to
 * prioritize simpler rules first. This avoids gratuittous punctuation around optional
 * sequences of elements.
 */
public class SerializationRuleComparator implements Comparator<@NonNull SerializationRuleAnalysis>
{
	private Map<@NonNull SerializationRuleAnalysis, @NonNull Integer> rule2size = new HashMap<>();

	@Override
	public int compare(@NonNull SerializationRuleAnalysis rule1, @NonNull SerializationRuleAnalysis rule2) {
		int size1 = getSize(rule1);
		int size2 = getSize(rule2);
		if (size1 != size2) {
			return size1 - size2;
		}
		String string1 = rule1.toString();
		String string2 = rule2.toString();
		return string1.compareTo(string2);
	}

	private int getSize(@NonNull SerializationRuleAnalysis serializationRule) {
		Integer size = rule2size.get(serializationRule);
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