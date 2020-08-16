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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;

/**
 * ProxyRuleValue supports deferred initialization of RuleValue references to avoid a recursive StackOverflow during construction.
 *
 * RuleValue's can be constructed regularly so that they can be referenced, but using the Proxy for the cyclic hazard. The proxy is lazily
 * resolved when fitrst referenced.
 */
public class ProxyRuleValue extends AbstractRuleValue
{
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;

	public ProxyRuleValue(@NonNull AbstractRuleAnalysis calledRuleAnalysis) {
		super(-999, calledRuleAnalysis.getName());			// Index (and name) should never be used.
		this.calledRuleAnalysis = calledRuleAnalysis;
	}

	@Override
	public boolean equals(Object obj) {
		throw new IllegalStateException();		// Proxy should have been deproxified.
	}

	public @NonNull AbstractRuleValue getRuleValue() {
		return calledRuleAnalysis.getRuleValue();
	}

	@Override
	public int getIndex() {
		throw new IllegalStateException();		// Proxy should have been deproxified.
	}

	@Override
	public @NonNull String getName() {
		throw new IllegalStateException();		// Proxy should have been deproxified.
	}

	@Override
	public @NonNull String getRuleName() {
		throw new IllegalStateException();		// Proxy should have been deproxified.
	}

	@Override
	public int hashCode() {
		throw new IllegalStateException();		// Proxy should have been deproxified.
	}

	@Override
	public @NonNull String toString() {
		throw new IllegalStateException();		// Proxy should have been deproxified.
	}
}
