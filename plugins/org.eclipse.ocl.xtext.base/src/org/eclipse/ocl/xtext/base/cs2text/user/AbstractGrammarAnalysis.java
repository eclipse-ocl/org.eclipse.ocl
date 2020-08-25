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
package org.eclipse.ocl.xtext.base.cs2text.user;

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
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.EClassData;

public abstract class AbstractGrammarAnalysis
{
	private final @NonNull Map<@NonNull EClass, @NonNull EClassData> eClass2eClassData = new HashMap<>();

	protected void addEClassData(@NonNull EClassData eClassData) {
		assert eClassData != null;
		EClassData old = eClass2eClassData.put(eClassData.getEClass(), eClassData);
		assert old == null;
	}

	public @NonNull EClassData getEClassData(@NonNull EClass eClass) {
		assert eClass2eClassData.size() > 0;
		return ClassUtil.nonNullState(eClass2eClassData.get(eClass));
	}

	public abstract @NonNull AbstractRuleValue getRuleValue(int ruleValueIndex);

	public @NonNull Iterable<@NonNull EClassData> getSortedProducedEClassDatas() {
		assert eClass2eClassData.size() > 0;
		List<@NonNull EClassData> eClassDatas = new ArrayList<>(ClassUtil.nonNullState(eClass2eClassData.values()));
		Collections.sort(eClassDatas, NameUtil.NAMEABLE_COMPARATOR);
		return eClassDatas;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		for (@NonNull EClassData eClassData : getSortedProducedEClassDatas()) {
			StringUtil.appendIndentation(s, 1);
			eClassData.toString(s, 1);
		}
		return s.toString();
	}
}
