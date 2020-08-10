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
import org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules;

public class RTGrammarAnalysis
{
//	private /*@NonNull*/ GrammarAnalysis grammarAnalysis;

	/**
	 * The prioritized serialization rules for each EClass.
	 */
	private final @NonNull Map<@NonNull EClass, @NonNull SerializationRules> eClass2serializationRules = new HashMap<>();

	public RTGrammarAnalysis() {
//		this.grammarAnalysis = null;
	}

//	public RTGrammarAnalysis(@NonNull GrammarAnalysis grammarAnalysis) {
//		this.grammarAnalysis = grammarAnalysis;
//	}

	public void addSerializationRules(/*@NonNull*/ SerializationRules serializationRules) {
		assert serializationRules != null;
		SerializationRules old = eClass2serializationRules.put(serializationRules.getEClass(), serializationRules);
		assert old == null;
	}

	public @NonNull SerializationRules getSerializationRules(@NonNull EClass eClass) {
		assert eClass2serializationRules.size() > 0;
		return ClassUtil.nonNullState(eClass2serializationRules.get(eClass));
	}

	public @NonNull Iterable<@NonNull EClass> getSortedProducedEClasses() {
		assert eClass2serializationRules.size() > 0;
		List<@NonNull EClass> list = new ArrayList<>(ClassUtil.nonNullState(eClass2serializationRules.keySet()));
		Collections.sort(list, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		return list;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		for (@NonNull EClass eClass : getSortedProducedEClasses()) {
			SerializationRules serializationRules = getSerializationRules(eClass);
			assert serializationRules != null;
			s.append("\n  ");;
			s.append(eClass.getEPackage(). getName());
			s.append("::");;
			s.append(eClass.getName());
			if ("PackageCS".equals(eClass.getName())) {
				getClass(); // XXX debugging
			}
			s.append(" <=>");;
			serializationRules.toString(s, 2);
		}
		return s.toString();
	}
}
