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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;

public abstract class AbstractGrammarAnalysis
{
	private final @NonNull Map<@NonNull EClass, @NonNull EClassValue> eClass2eClassValue = new HashMap<>();

	protected void addEClassValue(@NonNull EClassValue eClassValue) {
		assert eClassValue != null;
		EClassValue old = ClassUtil.maybeNull(eClass2eClassValue.put(eClassValue.getEClass(), eClassValue));
		assert old == null;
	}

	public @NonNull EClassValue getEClassValue(@NonNull EClass eClass) {
		assert eClass2eClassValue.size() > 0;
		return ClassUtil.nonNullState(eClass2eClassValue.get(eClass));
	}

	public abstract @NonNull String getRuleName(int ruleValueIndex);

	public abstract @NonNull GrammarRuleValue getRuleValue(int ruleValueIndex);

	public @NonNull Iterable<@NonNull EClassValue> getSortedProducedEClassValues() {
		assert eClass2eClassValue.size() > 0;
		List<@NonNull EClassValue> eClassValues = new ArrayList<>(ClassUtil.nonNullState(eClass2eClassValue.values()));
		Collections.sort(eClassValues, NameUtil.NAMEABLE_COMPARATOR);
		return eClassValues;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		for (@NonNull EClassValue eClassValue : getSortedProducedEClassValues()) {
			StringUtil.appendIndentation(s, 1);
			eClassValue.toString(s, 1);
		}
		@SuppressWarnings("null")
		@NonNull String castString = s.toString();
		return castString;
	}
}
