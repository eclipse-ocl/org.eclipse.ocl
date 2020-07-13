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
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;

import com.google.common.collect.Iterables;

/**
 * An UserAbstractElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element.
 */
public abstract class UserAbstractElementAnalysis implements Nameable
{
	private static int count = 0;

	protected final @NonNull UserModelAnalysis modelAnalysis;
	protected final @NonNull XtextGrammarAnalysis grammarAnalysis;
	protected final @NonNull EObject element;
	private final @NonNull String name;
	private @Nullable Iterable<@NonNull RequiredSlotsConjunction> serializationRules = null;

	public UserAbstractElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
		this.modelAnalysis = modelAnalysis;
		this.grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		this.element = element;
		this.name = element.eClass().getName() + "@" + ++count;
	}

	public @Nullable Serializer createSerializer(@NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		Iterable<@NonNull RequiredSlotsConjunction> serializationRules = getSerializationRules();
		for (@NonNull RequiredSlotsConjunction serializationRule : serializationRules) {
			Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = serializationRule.computeActualCardinalities(element, eFeature2contentAnalysis);
			if (variable2value != null) {
				return new Serializer(serializationRule, modelAnalysis, element, variable2value);
			}
		}
		return null;
	}

	protected Map<@NonNull EStructuralFeature, @NonNull Object> getContentAnalysis() {
		Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis = new HashMap<>();
		EClass eClass = element.eClass();
		for (EStructuralFeature eFeature : eClass.getEAllStructuralFeatures()) {
			assert eFeature != null;
			if (!eFeature.isDerived() && !eFeature.isTransient() && !eFeature.isVolatile() && (!(eFeature instanceof EReference) || !((EReference)eFeature).isContainer())) {
				Object contentAnalysis = null;
				Object object = element.eGet(eFeature);
				if (eFeature.isMany()) {
					List<?> elements = (List<?>)object;
					int size = elements.size();
					if ((size > 0) && (eFeature instanceof EAttribute) && (grammarAnalysis.getEnumerations((EAttribute)eFeature) != null)) {
						Map<@Nullable String, @NonNull Integer> value2count = new HashMap<>();
						for (Object element : elements) {
							String string = String.valueOf(element);
							Integer count = value2count.get(string);
							value2count.put(string, (count == null ? 1 : count.intValue()) + 1);
						}
						value2count.put(null, size);
						contentAnalysis = value2count;
					}
					else {
						contentAnalysis = size;
					}
				}
				else if (element.eIsSet(eFeature)) {
					String string = String.valueOf(object);
					if ((eFeature instanceof EAttribute) && (grammarAnalysis.getEnumerations((EAttribute)eFeature) != null)) {
						Map<@NonNull String, @NonNull Integer> value2count = new HashMap<>();
						value2count.put(string, 1);
						contentAnalysis = value2count;
					}
					else {
						contentAnalysis = 1;
					}
				}
				else {
					contentAnalysis = 0;
				}
				eFeature2contentAnalysis.put(eFeature, contentAnalysis);
			}
		}
		return eFeature2contentAnalysis;
	}

	public void diagnose(@NonNull StringBuilder s, @NonNull Map<@NonNull EStructuralFeature, @NonNull Object> eFeature2contentAnalysis) {
		Iterable<@NonNull RequiredSlotsConjunction> serializationRules2 = serializationRules;
		if (serializationRules2 == null) {
			s.append(" - No serialization rules.");
			return;
		}
		char c = 'A';
		for (@NonNull RequiredSlotsConjunction serializationRule : serializationRules2) {
			s.append("\n  [");
			s.append("" + c++);
			s.append("] ");
			serializationRule.toRuleString(s);
		}
		s.append("\n");
		c = 'A';
		s.append(String.format("%-20.20s%9s", "feature", "actual"));
		Set<@NonNull EStructuralFeature> allFeatures = new HashSet<>();
		for (@NonNull RequiredSlotsConjunction serializationRule : serializationRules2) {
			s.append(" [");
			s.append("" + c++);
			s.append("]");
			for (@NonNull EStructuralFeature eStructuralFeature : serializationRule.getEStructuralFeatures()) {
				allFeatures.add(eStructuralFeature);
			}
		}
		List<@NonNull EStructuralFeature> sortedFeatures = new ArrayList<>(allFeatures);
		Collections.sort(sortedFeatures, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		for (@NonNull EStructuralFeature eStructuralFeature : sortedFeatures) {
			s.append("\n");
			int size = CardinalityExpression.getSize(eFeature2contentAnalysis, eStructuralFeature, null);
			s.append(String.format("%-20.20s%8d", eStructuralFeature.getName(), size));
			for (@NonNull RequiredSlotsConjunction serializationRule : serializationRules2) {
				MultiplicativeCardinality multiplicativeCardinality = serializationRule.getMultiplicativeCardinality(eStructuralFeature);
				s.append(String.format("%4s", multiplicativeCardinality != null ? multiplicativeCardinality.toString() : "0"));
			}
			if (eStructuralFeature instanceof EAttribute) {
				Iterable<@NonNull String> enumerations = grammarAnalysis.getEnumerations((EAttribute)eStructuralFeature);
				if (enumerations != null) {
					for (@NonNull String enumeration : enumerations) {
						int size2 = CardinalityExpression.getSize(eFeature2contentAnalysis, eStructuralFeature, enumeration);
						s.append(String.format("\n  %-18.18s%8d", "\"" + enumeration + "\"", size2));
					}
				}
			}
		}
		s.append("\n");
	}

	public abstract @Nullable UserAbstractElementAnalysis getContainingElementAnalysis();

	public abstract @Nullable EStructuralFeature geteContainingFeature();

	public @NonNull EObject getElement() {
		return element;
	}

	public @NonNull XtextGrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public @NonNull Iterable<@NonNull RequiredSlotsConjunction> getSerializationRules() {
		EClass eClass = UserModelAnalysis.eClass(element);
		if ("TopLevelCS".equals(eClass.getName())) {
			getClass(); // XXX
		}
		if (serializationRules == null) {
			serializationRules = grammarAnalysis.getSerializationRules(eClass);
		}
		assert serializationRules != null;
		return serializationRules;
	}

	protected abstract boolean hasCompatibleContainmentHierarchy();

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getName());
		s.append(" <=>");
		Iterable<@NonNull RequiredSlotsConjunction> serializationRules2 = getSerializationRules();
		if (serializationRules2 != null) {
			boolean isMany = Iterables.size(serializationRules2) > 1;
			for (@NonNull RequiredSlotsConjunction serializationRule : serializationRules2) {
				if (isMany) {
					s.append("\n\t\t");
				}
				else {
					s.append(" ");
				}
				s.append(serializationRule.getName());
				s.append(" - ");
				serializationRule.toRuleString(s);
			}
		}
		return s.toString();
	}
}