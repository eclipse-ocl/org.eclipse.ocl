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
import org.eclipse.ocl.pivot.utilities.Nameable;

public class ParserRuleValue implements Nameable
{
	protected final @NonNull String name;

	public ParserRuleValue(@NonNull String name) {
		this.name = name;
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
	public @NonNull String getName() {
		return name;
	}

//	public @NonNull Collection<@NonNull ParserRuleValue> getSubRuleAnalysesClosure() {
//		// TODO Auto-generated method stub
//		return null;
//	}

	public @NonNull String getRuleName() {		// XXX not distinct
		return name;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + name.hashCode();
	}

	@Override
	public @NonNull String toString() {
		return name;
	}
}