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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.GrammarAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.serializer.tokens.ICrossReferenceSerializer;

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

	@Inject
	private @NonNull IValueConverterService valueConverterService;

	@Inject
	private @NonNull ICrossReferenceSerializer crossReferenceSerializer;


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

	private int debugUserElementAnalysisCount = 0;
	private int debugUserSlotsAnalysisCount = 0;
	private int debugSerializeCount = 0;
	private int debugDynamicRuleMatchCount = 0;
	private int debugDynamicSerializationRules = 0;

	/**
	 * Perform analysis of each user model element.
	 */
	public void analyze(@NonNull EObject model) {
		assert model.eContainer() == null;
		UserElementAnalysis rootElementAnalysis = new UserElementAnalysis(this, null, null, model);
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

	public void debugAddDynamicSerializationRules(@NonNull DynamicSerializationRules dynamicSerializationRules) {
		debugDynamicSerializationRules++;
	}

	public void debugAddDynamicRuleMatch(@NonNull DynamicRuleMatch dynamicRuleMatch) {
		debugDynamicRuleMatchCount++;
	}

	public void debugAddUserElementAnalysis(@NonNull UserElementAnalysis userElementAnalysis) {
		debugUserElementAnalysisCount++;
	}

	public void debugAddUserSlotsAnalysis(UserSlotsAnalysis userSlotsAnalysis) {
		debugUserSlotsAnalysisCount++;
	}

	public @NonNull String diagnose() {
		StringBuilder s = new StringBuilder();
		s.append("debugUserElementAnalysisCount = " + debugUserElementAnalysisCount + "\n");
		s.append("debugUserSlotsAnalysisCount = " + debugUserSlotsAnalysisCount + "\n");
		s.append("debugSerializeCount = " + debugSerializeCount + "\n");
		s.append("debugDynamicRuleMatchCount = " + debugDynamicRuleMatchCount + "\n");
		s.append("debugDynamicSerializationRules = " + debugDynamicSerializationRules + "\n");
		return s.toString();
	}

	public @NonNull ICrossReferenceSerializer getCrossReferenceSerializer() {
		return crossReferenceSerializer;
	}

	/**
	 * Return the analysis of a user model element.
	 */
	public @NonNull UserElementAnalysis getElementAnalysis(@NonNull EObject element) {
		return ClassUtil.nonNullState(element2elementAnalysis.get(element));
	}

	public @NonNull RTGrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis.getRuntime();
	}

	@Deprecated
	public @NonNull GrammarAnalysis getInjectedGrammarAnalysis() {
		return grammarAnalysis;
	}

	public @NonNull IValueConverterService getValueConverterService() {
		return valueConverterService;
	}

	/**
	 * Create a Serializer for the appropriate configuration of element, then use it to serialize it and its descendants
	 * to the serializationBuilder.
	 */
	public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject eObject, @Nullable AbstractRuleAnalysis targetRuleAnalysis) {
		debugSerializeCount++;
		UserElementAnalysis userElementAnalysis = getElementAnalysis(eObject);
		if ("PrefixExpCS".equals(eObject.eClass().getName())) {
			getClass();	// XXX
		}
		DynamicRuleMatch dynamicRuleMatch = userElementAnalysis.createDynamicRuleMatch(targetRuleAnalysis != null ? ((ParserRuleAnalysis)targetRuleAnalysis).getParserRuleValue() : null);
		if (dynamicRuleMatch != null) {
			UserElementSerializer serializer = new UserElementSerializer(dynamicRuleMatch, this, eObject);
			serializer.serialize(serializationBuilder);
		}
		else {
			userElementAnalysis.createDynamicRuleMatch(targetRuleAnalysis != null ? ((ParserRuleAnalysis)targetRuleAnalysis).getParserRuleValue() : null);		// XXX debugging
			StringBuilder s = new StringBuilder();
			s.append("\n\n«incompatible '" + eObject.eClass().getName() + "'");
			userElementAnalysis.getSlotsAnalysis().diagnose(s);
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
			elementAnalysis.toString(s, 1);
			isFirst = false;
		}
		return s.toString();
	}

}
