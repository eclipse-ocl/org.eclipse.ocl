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
package org.eclipse.ocl.xtext.base.cs2text.elements;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

import com.google.common.collect.Iterables;

public class BasicSerializationRule extends AbstractSerializationRule
{
	/**
	 * The variables, expressions and solutions to determine if an actual user element matches.
	 */
	private @Nullable StaticRuleMatch staticRuleMatch = null;

	/**
	 * The subidioms to decorate each node duringf serialization.
	 */
	private @Nullable Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom = null;

	public BasicSerializationRule(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		super(ruleAnalysis, rootSerializationNode);
	}

	@Override
	public @NonNull BasicSerializationRule getBasicSerializationRule() {
		return this;
	}

//	public @NonNull Iterable<@NonNull EAttribute> getEStructuralFeatures() {
//		return eAttribute2enumerationValue2multiplicativeCardinality.keySet();
//	}

	private void getIdiomMatches(@NonNull SerializationNode outerSerializationNode, @NonNull Iterable<@NonNull Idiom> idioms,
			@Nullable IdiomMatch @NonNull [] idiomMatches) {
		int idiomIndex = 0;
		for (@NonNull Idiom idiom : idioms) {
			IdiomMatch idiomMatch = idiomMatches[idiomIndex];
			if (idiomMatch == null) {
				idiomMatches[idiomIndex] = idiom.firstMatch(outerSerializationNode, this);
			}
			else {
				idiomMatch.nextMatch(outerSerializationNode, this);
			}
			idiomIndex++;
		}
		if (outerSerializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode innerSerializationNode : ((SequenceSerializationNode)outerSerializationNode).getSerializationNodes()) {
				getIdiomMatches(innerSerializationNode, idioms, idiomMatches);
			}
		}
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EStructuralFeature eStructuralFeature) {
		assert staticRuleMatch != null;
		return staticRuleMatch.getMultiplicativeCardinality(eStructuralFeature);
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EAttribute eAttribute, @NonNull EnumerationValue enumerationValue) {
		assert staticRuleMatch != null;
		return staticRuleMatch.getMultiplicativeCardinality(eAttribute, enumerationValue);
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EReference eReference, @NonNull ParserRuleAnalysis ruleAnalysis) {
		assert staticRuleMatch != null;
		return staticRuleMatch.getMultiplicativeCardinality(eReference, ruleAnalysis);
	}

	public @NonNull StaticRuleMatch getStaticRuleMatch() {
		StaticRuleMatch staticRuleMatch2 = staticRuleMatch;
		if (staticRuleMatch2 == null) {
			if ("EssentialOCL::TupleTypeCS".equals(ruleAnalysis.getName())) {
				getClass();	// XXX debugging
			}
			//
			staticRuleMatch = staticRuleMatch2 = new StaticRuleMatch(this);
			//
			//	Traverse the chosen serialization tree path to determine the
			//	cardinality variables and expressions to be solved to characterize the serialization.
			//
			staticRuleMatch2.analyzeSerialization();
			//
			//	Analyze the cardinality expressions to determine the steps that compute/validate each cardinality variable.
			//
			staticRuleMatch2.analyzeSolution();
		}
		return staticRuleMatch2;
	}

	public @Nullable SubIdiom getSubIdiom(@NonNull SerializationNode serializationNode) {
		Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom2 = serializationNode2subIdiom;
		if (serializationNode2subIdiom2 == null) {
			serializationNode2subIdiom = serializationNode2subIdiom2 = getSerializationNode2subIdioms();
		}
		SubIdiom subIdiom = serializationNode2subIdiom2.get(serializationNode);
		return subIdiom;
	}

	private @NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> getSerializationNode2subIdioms() {
		assert staticRuleMatch != null;
		@NonNull Iterable<@NonNull Idiom> idioms = staticRuleMatch.getSerializationRule().getRuleAnalysis().getGrammarAnalysis().getIdioms();
		//
		//	Locate the matches for each idiom.
		//
		@Nullable IdiomMatch @NonNull [] idiomMatches = new @Nullable IdiomMatch[Iterables.size(idioms)];
		getIdiomMatches(rootSerializationNode, idioms, idiomMatches);
		//
		//	Install the subdioms for each first full idiom match.
		//
		Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom = new HashMap<>();
		for (@Nullable IdiomMatch idiomMatch : idiomMatches) {
			if (idiomMatch != null) {
				idiomMatch.installIn(serializationNode2subIdiom);
			}
		}
		return serializationNode2subIdiom;
	}

	public @NonNull CardinalityVariable getVariable(@NonNull SerializationNode serializationNode) {
		getStaticRuleMatch();
		assert staticRuleMatch != null;
		return staticRuleMatch.getVariable(serializationNode);
	}

	public @NonNull Iterable<@NonNull CardinalityVariable> getVariables() {
		assert staticRuleMatch != null;
		return staticRuleMatch.getVariables();
	}

	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis) {
		assert staticRuleMatch != null;
		return staticRuleMatch.match(slotsAnalysis);
	}

	public boolean needsDefault(@NonNull EStructuralFeature eStructuralFeature) {
		assert staticRuleMatch != null;
		return staticRuleMatch.needsDefault(eStructuralFeature);
	}

	@Override
	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		StaticRuleMatch staticRuleMatch2 = staticRuleMatch;
		if (staticRuleMatch2 != null) {
			staticRuleMatch2.toSolutionString(s, depth);
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(ruleAnalysis.getName());
		s.append("(");
		EClass producedEClass = getProducedEClass();
		s.append(producedEClass.getEPackage().getName());
		s.append("::");
		s.append(producedEClass.getName());
		s.append("): ");
		rootSerializationNode.toString(s, depth);
	}
}