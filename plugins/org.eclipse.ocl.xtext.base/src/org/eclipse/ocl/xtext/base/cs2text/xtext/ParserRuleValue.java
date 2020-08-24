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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule;

public class ParserRuleValue extends AbstractRuleValue
{
	protected final @Nullable IndexVector subParserRuleValueIndexes;	// Includes this if non-null
	protected final @NonNull RTSerializationRule @NonNull [] rtSerializationRules;

	public ParserRuleValue(int ruleIndex, @NonNull String name, @NonNull RTSerializationRule @NonNull[] rtSerializationRules, @Nullable IndexVector subParserRuleValueIndexes) {
		super(ruleIndex, name);
		this.subParserRuleValueIndexes = subParserRuleValueIndexes;
		this.rtSerializationRules = rtSerializationRules;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof ParserRuleValue)) {
			return false;
		}
		ParserRuleValue that = (ParserRuleValue)obj;
		return (this.ruleIndex == that.ruleIndex) && this.name.equals(that.name) && ClassUtil.safeEquals(this.subParserRuleValueIndexes, that.subParserRuleValueIndexes);
	}

	public @NonNull RTSerializationRule @NonNull[] getSerializationRules() {
		return rtSerializationRules;
	}

	public @Nullable IndexVector getSubParserRuleValueIndexes() {
		return subParserRuleValueIndexes;
	}

	public boolean subParserRuleValueClosureContains(int ruleValueIndex) {
		return (subParserRuleValueIndexes != null) ? subParserRuleValueIndexes.test(ruleValueIndex) : ruleValueIndex == ruleIndex;
	}
}