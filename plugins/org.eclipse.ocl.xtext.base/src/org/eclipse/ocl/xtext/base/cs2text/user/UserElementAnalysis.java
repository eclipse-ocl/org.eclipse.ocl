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
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRules;

/**
 * A UserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element that has a container.
 */
public class UserElementAnalysis implements Nameable
{
	private static int count = 0;

	protected final @NonNull UserModelAnalysis modelAnalysis;
	protected final @NonNull GrammarAnalysis grammarAnalysis;
	protected final @Nullable UserElementAnalysis containingElementAnalysis;
	protected final @Nullable EReference eContainmentFeature;
	protected final @NonNull EObject eObject;
	protected final @NonNull EClass eClass;
	protected final @NonNull String name;
	protected final @NonNull DynamicSerializationRules serializationRules;
	private @Nullable UserSlotsAnalysis slotsAnalysis = null;

	public UserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @Nullable UserElementAnalysis containingElementAnalysis,
			@Nullable EReference eContainmentFeature, @NonNull EObject eObject) {
		assert (eContainmentFeature == null) == (containingElementAnalysis == null);
		assert eObject.eContainer() == (containingElementAnalysis == null ? null : containingElementAnalysis.getEObject());
		this.modelAnalysis = modelAnalysis;
		this.grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		this.containingElementAnalysis = containingElementAnalysis;
		this.eContainmentFeature = eContainmentFeature;
		this.eObject = eObject;
		this.eClass = UserModelAnalysis.eClass(eObject);
		this.name = eClass.getName() + "@" + ++count;
		this.serializationRules = analyzeSerializationRules();
	}

	/**
	 * Determine the rules able to produce this element and the containing assignments by which it can be contained.
	 */
	private @NonNull DynamicSerializationRules analyzeSerializationRules() {
		String eClassName = eClass.getName();
		if ("SelfExpCS".equals(eClassName)) {
			getClass();				// XXX
		}
		Set<@NonNull AbstractRuleAnalysis> targetRuleAnalyses = null;
		EReference eContainmentFeature2 = eContainmentFeature;
		if (eContainmentFeature2 != null) {
			UserElementAnalysis containingElementAnalysis2 = containingElementAnalysis;
			assert containingElementAnalysis2 != null;
			SerializationRules parentSerializationRules = grammarAnalysis.getSerializationRules(containingElementAnalysis2.getEClass());
			targetRuleAnalyses = parentSerializationRules.getAssignedTargetRuleAnalyses(eContainmentFeature2);
			for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : new ArrayList<>(targetRuleAnalyses)) {
				if (targetRuleAnalysis instanceof ParserRuleAnalysis) {
					targetRuleAnalyses.addAll(((ParserRuleAnalysis)targetRuleAnalysis).getSubRuleAnalysesClosure());
				}
			}
		}
		SerializationRules serializationRules2 = grammarAnalysis.getSerializationRules(eClass);
		return serializationRules2.createDynamicSerializationRules(targetRuleAnalyses);
	}

	public @Nullable DynamicRuleMatch createDynamicRuleMatch(@Nullable AbstractRuleAnalysis targetRuleAnalysis) {
		UserSlotsAnalysis slotsAnalysis = getSlotsAnalysis();
		return serializationRules.createDynamicRuleMatch(slotsAnalysis, targetRuleAnalysis);
	}

	public @Nullable UserElementAnalysis getContainingElementAnalysis() {
		return containingElementAnalysis;
	}

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

	public @NonNull DynamicSerializationRules getSerializationRules() {
		return serializationRules;
	}

	public @NonNull UserSlotsAnalysis getSlotsAnalysis() {
		UserSlotsAnalysis slotsAnalysis2 = slotsAnalysis;
		if (slotsAnalysis2 == null) {
			slotsAnalysis = slotsAnalysis2 = new UserSlotsAnalysis(modelAnalysis, serializationRules, eObject);
		}
		return slotsAnalysis2;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(getName());
		s.append(" - ");
		DynamicSerializationRules serializationRules2 = ClassUtil.maybeNull(serializationRules);
		if (serializationRules2 != null) {
			serializationRules2.toString(s, depth);
		}
	}
}