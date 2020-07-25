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
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;

import com.google.inject.Inject;

/**
 * The UserModelAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element.
 */
public class UserModelAnalysis
{
	/**
	 * The overall (multi-)grammar analysis.
	 */
	@Inject
	private @NonNull GrammarAnalysis grammarAnalysis;

	public static @NonNull EClass eClass(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eClass());
	}

	public static @NonNull EStructuralFeature eContainingFeature(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eContainingFeature());
	}

	public static @NonNull EReference eContainmentFeature(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eContainmentFeature());
	}

	public static @NonNull EObject eContainer(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eContainer());
	}

	/**
	 * The analysis of each user model element.
	 */
	private final @NonNull Map<@NonNull EObject, @NonNull UserElementAnalysis> element2elementAnalysis = new HashMap<>();

	/**
	 * Perform analysis of each user model element.
	 */
	public void analyze(@NonNull EObject model) {
		assert model.eContainer() == null;
		UserElementAnalysis rootElementAnalysis = new UserElementAnalysis(this, model);
		analyzeHierarchy(rootElementAnalysis, model);
		rootElementAnalysis.getSerializationRules();		// Avoid lazy serializationRules being omiited by a toString().
	}

	private void analyzeHierarchy(@NonNull UserElementAnalysis parentAnalysis, @NonNull EObject eParent) {
		element2elementAnalysis.put(eParent, parentAnalysis);
		for (EObject eChild : eParent.eContents()) {
			EReference eContainmentFeature = eChild.eContainmentFeature();
			assert eContainmentFeature.isContainment() && !eContainmentFeature.isDerived() && !eContainmentFeature.isTransient() && !eContainmentFeature.isVolatile();
			UserElementAnalysis childAnalysis = new UserElementAnalysis(this, parentAnalysis, eContainmentFeature, eChild);
			analyzeHierarchy(childAnalysis, eChild);
		//	if (Iterables.size(elementAnalysis.getSerializationRules()) > 1) {
		//		unresolvedModelObjects.add(elementAnalysis);
		//	}
		}
	}

	/**
	 * Return the analysis of a user model element.
	 */
	public @NonNull UserElementAnalysis getElementAnalysis(@NonNull EObject element) {
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
		UserElementAnalysis userElementAnalysis = getElementAnalysis(element);
		if ("EnumerationCS".equals(element.eClass().getName())) {
			getClass();	// XXX
		}
		UserSlotsAnalysis slotsAnalysis = userElementAnalysis.getSlotsAnalysis();
		Serializer serializer = userElementAnalysis.createSerializer(slotsAnalysis);
		if (serializer != null) {
			serializer.serialize(serializationBuilder);
		}
		else {
			userElementAnalysis.createSerializer(slotsAnalysis);		// XXX debugging
			StringBuilder s = new StringBuilder();
			s.append("\n\n«incompatible '" + element.eClass().getName() + "'");
			slotsAnalysis.diagnose(s);
			s.append("»\n\n");
			serializationBuilder.appendError(s.toString());
		}
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append("User object <=> Xtext containing assignment(s) : Xtext production rule\n");
		List<@NonNull UserElementAnalysis> elementAnalyses = new ArrayList<>(element2elementAnalysis.values());
		Collections.sort(elementAnalyses, NameUtil.NAMEABLE_COMPARATOR);
		boolean isFirst = true;
		for (@NonNull UserElementAnalysis elementAnalysis : elementAnalyses) {
			if (!isFirst) {
				s.append("\n");
			}
			s.append("  ");
			s.append(elementAnalysis);
			isFirst = false;
		}
		return s.toString();
	}
}
