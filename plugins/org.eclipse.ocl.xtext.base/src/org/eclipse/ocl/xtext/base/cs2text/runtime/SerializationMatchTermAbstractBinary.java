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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;

public abstract class SerializationMatchTermAbstractBinary extends SerializationMatchTermAbstract
{
	protected final @NonNull SerializationMatchTerm left;
	protected final @NonNull SerializationMatchTerm right;
	private @Nullable Set<org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm> childClosure = null;

	public SerializationMatchTermAbstractBinary(@NonNull SerializationMatchTerm left, @NonNull SerializationMatchTerm right) {
		this.left = left;
		this.right = right;
	}

	@Override
	public @NonNull Set<org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm> getChildClosure() {
		Set<org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationMatchTerm> childClosure2 = childClosure;
		if (childClosure2 == null) {
			childClosure = childClosure2 = new HashSet<>();
			childClosure2.add(this);
			childClosure2.addAll(left.getChildClosure());
			childClosure2.addAll(right.getChildClosure());
		}
		return childClosure2;
	}

	public @NonNull SerializationMatchTerm getLeft() {
		return left;
	}

	public @NonNull SerializationMatchTerm getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		return getClass().hashCode() + left.hashCode() + right.hashCode() * 7;
	}

	@Override
	public boolean isConstant(@NonNull StaticRuleMatch ruleMatch) {
		return left.isConstant(ruleMatch) && right.isConstant(ruleMatch);
	}

	@Override
	public boolean isKnown(@NonNull StaticRuleMatch ruleMatch) {
		return left.isKnown(ruleMatch) && right.isKnown(ruleMatch);
	}
}