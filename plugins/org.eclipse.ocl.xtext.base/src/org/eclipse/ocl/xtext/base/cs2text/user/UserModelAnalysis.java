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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;

import com.google.common.collect.Iterables;

/**
 * The UserModelAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element.
 */
public class UserModelAnalysis
{
	public static @NonNull EClass eClass(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eClass());
	}

	public static @NonNull EStructuralFeature eContainingFeature(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eContainingFeature());
	}

	public static @NonNull EObject eContainer(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eContainer());
	}

	/**
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull GrammarAnalysis grammarAnalysis;

	/**
	 * The analysis of each user model element.
	 */
	private final @NonNull Map<@NonNull EObject, @NonNull UserAbstractElementAnalysis> element2elementAnalysis = new HashMap<>();

	public UserModelAnalysis(@NonNull GrammarAnalysis grammarAnalysis) {
		this.grammarAnalysis = grammarAnalysis;
	}

	/**
	 * Perform analysis of each user model element.
	 */
	public void analyze(@NonNull EObject model) {
		assert model.eContainer() == null;
		element2elementAnalysis.put(model, new UserRootElementAnalysis(this, model));
		List<@NonNull UserElementAnalysis> unresolvedModelObjects = new ArrayList<>();
		for (@NonNull EObject eObject : new TreeIterable(model, false)) {
			UserElementAnalysis elementAnalysis = new UserElementAnalysis(this, eObject);
			element2elementAnalysis.put(eObject, elementAnalysis);
			if (Iterables.size(elementAnalysis.getSerializationRules()) > 1) {
				unresolvedModelObjects.add(elementAnalysis);
			}
		}
	}

	/**
	 * Return the analysis of a user model element.
	 */
	public @NonNull UserAbstractElementAnalysis getElementAnalysis(@NonNull EObject element) {
		return ClassUtil.nonNullState(element2elementAnalysis.get(element));
	}

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	/**
	 * Create a Serializer for the appropriate configuration of element, then use it to serialize it and its descendants
	 * to the serializationBuilder.
	 */
	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
		UserAbstractElementAnalysis userElementAnalysis = getElementAnalysis(element);
		if ("ReferenceCS".equals(element.eClass().getName())) {
			getClass();	// XXX
		}
		Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis = userElementAnalysis.getContentAnalysis();
		Serializer serializer = userElementAnalysis.createSerializer(eFeature2contentAnalysis);
		if (serializer != null) {
			serializer.serialize(serializationBuilder);
		}
		else {
			userElementAnalysis.createSerializer(eFeature2contentAnalysis);		// XXX debugging
			StringBuilder s = new StringBuilder();
			s.append("\n\n«incompatible '" + element.eClass().getName() + "'");
			userElementAnalysis.diagnose(s, eFeature2contentAnalysis);
			s.append("»\n\n");
			serializationBuilder.append(s.toString());
		}
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append("User object <=> Xtext containing assignment(s) : Xtext production rule\n");
		List<@NonNull UserAbstractElementAnalysis> elementAnalyses = new ArrayList<>(element2elementAnalysis.values());
		Collections.sort(elementAnalyses, NameUtil.NAMEABLE_COMPARATOR);
		boolean isFirst = true;
		for (@NonNull UserAbstractElementAnalysis elementAnalysis : elementAnalyses) {
			if (!isFirst) {
				s.append("\n");
			}
			s.append("\t");
			s.append(elementAnalysis);
			isFirst = false;
		}
		return s.toString();
	}
}
