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
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.solutions.RuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;

public class SerializationMatchTermInteger extends SerializationMatchTermAbstract
{
	protected final int value;

	public SerializationMatchTermInteger(int value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SerializationMatchTermInteger)) {
			return false;
		}
		SerializationMatchTermInteger that = (SerializationMatchTermInteger) obj;
		if (this.value != that.value) return false;
		return true;
	}

	@Override
	public @NonNull Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return value;
	}

	public int getValue() {
		return value;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + value;
	}

	@Override
	public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
		return true;
	}

	@Override
	public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
		return true;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(Integer.toString(value));
	}
}