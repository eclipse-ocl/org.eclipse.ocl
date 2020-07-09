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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
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

	public @Nullable Serializer createSerializer() {
		Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size = new HashMap<>();
		EClass eClass = element.eClass();
		for (EStructuralFeature eFeature : eClass.getEAllStructuralFeatures()) {
			assert eFeature != null;
			if (!eFeature.isDerived() && !eFeature.isTransient() && !eFeature.isVolatile() && (!(eFeature instanceof EReference) || !((EReference)eFeature).isContainer())) {
				int size;
				Object object = element.eGet(eFeature);
				if (eFeature.isMany()) {
					size = ((List<?>)object).size();
				}
				else if (element.eIsSet(eFeature)) {
					size = 1;
				}
				else {
					size = 0;
				}
				if (size > 0) {
					eFeature2size.put(eFeature, size);
				}
			}
		}
		Iterable<@NonNull RequiredSlotsConjunction> serializationRules = grammarAnalysis.getSerializationRules(eClass);
		for (@NonNull RequiredSlotsConjunction serializationRule : serializationRules) {
			Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = serializationRule.computeActualCardinalities(element, eFeature2size);
			if (variable2value != null) {
				return new Serializer(serializationRule, modelAnalysis, element, variable2value);
			}
		}
		return null;
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
		if ("PathElementWithURICS".equals(eClass.getName())) {
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
		Iterable<@NonNull RequiredSlotsConjunction> serializationRules2 = serializationRules;
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
			}
		}
		return s.toString();
	}
}