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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.StringUtil;

public class RequiredSlotsDisjunction implements RequiredSlots
{
	protected final @NonNull XtextParserRuleAnalysis ruleAnalysis;
	protected final @NonNull CompositeSerializationNode serializationNode;
	private @NonNull List<@NonNull SerializationRule> serializationRules;

	protected RequiredSlotsDisjunction(@NonNull XtextParserRuleAnalysis ruleAnalysis, @NonNull CompositeSerializationNode serializationNode, @NonNull List<@NonNull SerializationRule> serializationRules) {
		this.ruleAnalysis = ruleAnalysis;
		this.serializationNode = serializationNode;
		this.serializationRules = serializationRules;
	}

	public void addRequiredSlotConjunction(@NonNull SerializationRule requiredSlotConjunction) {
		serializationRules.add(requiredSlotConjunction);
	}

	@Override
	public @NonNull List<@NonNull SerializationRule> getSerializationRules() {
		return serializationRules;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return String.valueOf(s);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		boolean isFirst1 = true;
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			if (!isFirst1) {
				s.append("\n");
				StringUtil.appendIndentation(s, depth, "\t");
				s.append("|& ");
			}
			serializationRule.toString(s, depth);
/*			StringUtil.appendIndentation(s, depth, "\t");
			s.append("|& ");
			boolean isFirst2 = true;
			for (@NonNull SimpleRequiredSlot term : conjunction.getConjunction()) {
				if (!isFirst2) {
					s.append("&");
				}
				s.append(term);
				isFirst2 = false;
			} */
			isFirst1 = false;
		}
	}
}