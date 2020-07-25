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

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.Serializer;
import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;

import com.google.common.collect.Iterables;

/**
 * An UserAbstractElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element.
 */
public abstract class UserAbstractElementAnalysis implements Nameable
{
	private static int count = 0;

	protected final @NonNull UserModelAnalysis modelAnalysis;
	protected final @NonNull GrammarAnalysis grammarAnalysis;
	protected final @NonNull EObject eObject;
	protected final @NonNull EClass eClass;
	private final @NonNull String name;

	public UserAbstractElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject eObject) {
		this.modelAnalysis = modelAnalysis;
		this.grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		this.eObject = eObject;
		this.eClass = UserModelAnalysis.eClass(eObject);
		this.name = eClass.getName() + "@" + ++count;
	}

	public @Nullable Serializer createSerializer(@NonNull UserSlotsAnalysis slotsAnalysis) {
		Iterable<@NonNull SerializationRule> serializationRules = getSerializationRules();
		for (@NonNull SerializationRule serializationRule : serializationRules) {
			BasicSerializationRule basicSerializationRule = serializationRule.getBasicSerializationRule();
			Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = basicSerializationRule.computeActualCardinalities(slotsAnalysis);
			if (variable2value != null) {
				return new Serializer(basicSerializationRule, modelAnalysis, eObject, variable2value);
			}
		}
		return null;
	}

	public abstract @Nullable UserAbstractElementAnalysis getContainingElementAnalysis();

	public @NonNull EClass getEClass() {
		return eClass;
	}

	public @NonNull EObject getEObject() {
		return eObject;
	}

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

//	public abstract @NonNull Iterable<@NonNull ParserRuleAnalysis> getProductionRules();

	public abstract @NonNull Iterable<@NonNull SerializationRule> getSerializationRules();

	protected @NonNull UserSlotsAnalysis getSlotsAnalysis() {
		return new UserSlotsAnalysis(getSerializationRules(), eObject);
	}

	/**
	 * Return true if the containing feature is compatible with one of its containing assignments.
	 *
	 * If non-null each compatible assignment is assign to its corresponding production rulein ruleAnalysis2assignmentAnalyses.
	 *
	 * Compatbility requires
	 *
	 * The produced rule for this element is assignable to the assignment target's rule.
	 * The produced rule for the container of this element is assignable to the assignment source's rule.
	 * Recursively the container of this element has a similarly compatoble assignement.
	 */
//	protected abstract boolean isCompatible(@Nullable Set<@NonNull ParserRuleAnalysis> ruleAnalyses);

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getName());
		s.append(" <=>");
		Iterable<@NonNull SerializationRule> serializationRules2 = getSerializationRules();
		boolean isMany = Iterables.size(serializationRules2) > 1;
		for (@NonNull SerializationRule serializationRule : serializationRules2) {
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
		return s.toString();
	}
}