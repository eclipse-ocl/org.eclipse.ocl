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

import java.util.Collections;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.RuleMatch;

public abstract class SerializationMatchTermAbstract implements SerializationMatchTerm
{
	@Override
	public @Nullable Integer basicGetIntegerSolution(@NonNull RuleMatch ruleMatch) {
		return null;
	}

	@Override
	public abstract boolean equals(Object obj);

	@Override
	public @NonNull Set<@NonNull SerializationMatchTerm> getChildClosure() {
		return Collections.singleton(this);
	}

	@Override
	public abstract int hashCode();

//	@Override
//	public boolean isOptional() {
//		return false;
//	}

	@Override
	public boolean isRuntime() {
		return false;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}
}