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
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.xtext.util.Arrays;

public class ParserRuleValue implements Indexed,Nameable
{
	protected final int index;
	protected final @NonNull String name;
	protected final @Nullable IndexVector subParserRuleValueIndexes;	// Includes this if non-null

	public ParserRuleValue(int index, @NonNull String name, @NonNull ParserRuleValue @Nullable [] subParserRuleValueClosure) {
		this.index = index;
		this.name = name;
		assert (subParserRuleValueClosure == null) || (subParserRuleValueClosure.length > 0) || !Arrays.contains(subParserRuleValueClosure, this);
		if (subParserRuleValueClosure == null) {
			subParserRuleValueIndexes =  null;
		}
		else {
			IndexVector subParserRuleValueIndexes = new IndexVector();
			subParserRuleValueIndexes.set(index);
			for (@NonNull ParserRuleValue parserRuleValue : subParserRuleValueClosure) {
				subParserRuleValueIndexes.set(parserRuleValue.getIndex());
			}
			this.subParserRuleValueIndexes = subParserRuleValueIndexes;
		}
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
		return this.name.equals(that.name);
	}

	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public @NonNull String getRuleName() {		// XXX not distinct
		return name;
	}

	public @Nullable IndexVector getSubParserRuleValueIndexes() {
		return subParserRuleValueIndexes;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + name.hashCode();
	}

	public boolean subParserRuleValueClosureContains(@NonNull ParserRuleValue parserRuleValue) {
		return (subParserRuleValueIndexes != null) ? subParserRuleValueIndexes.test(parserRuleValue.getIndex()) : (parserRuleValue == this);
	}

	@Override
	public @NonNull String toString() {
		return name;
	}
}