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

public class ParserRuleValue extends AbstractRuleValue
{
	protected final @Nullable IndexVector subParserRuleValueIndexes;	// Includes this if non-null

	public ParserRuleValue(int index, @NonNull String name, @Nullable IndexVector subParserRuleValueIndexes) {
		super(index, name);
		this.subParserRuleValueIndexes = subParserRuleValueIndexes;
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
		return (this.index == that.index) && this.name.equals(that.name) && ClassUtil.safeEquals(this.subParserRuleValueIndexes, that.subParserRuleValueIndexes);
	}

	public @Nullable IndexVector getSubParserRuleValueIndexes() {
		return subParserRuleValueIndexes;
	}

	public boolean subParserRuleValueClosureContains(@NonNull ParserRuleValue parserRuleValue) {
		return (subParserRuleValueIndexes != null) ? subParserRuleValueIndexes.test(parserRuleValue.getIndex()) : (parserRuleValue == this);
	}
}