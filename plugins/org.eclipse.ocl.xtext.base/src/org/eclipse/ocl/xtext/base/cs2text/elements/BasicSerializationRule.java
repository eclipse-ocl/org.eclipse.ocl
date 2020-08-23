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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.ToDebugString;
import org.eclipse.ocl.xtext.base.cs2text.ToDebugString.ToDebugStringable;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationRule2;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

import com.google.common.collect.Iterables;

public class BasicSerializationRule implements SerializationRule, ToDebugStringable
{
	protected final @NonNull ParserRuleAnalysis ruleAnalysis;
	protected final @NonNull SerializationNode rootSerializationNode;
	private @Nullable EClass producedEClass = null;

	@SuppressWarnings("unused")			// Used in the debugger
	private final @NonNull ToDebugString toDebugSring = new ToDebugString(this){};

	/**
	 * The variables, expressions and solutions to determine if an actual user element matches.
	 */
	private @Nullable StaticRuleMatch staticRuleMatch = null;

	/**
	 * The subidioms to decorate each node duringf serialization.
	 */
	private @Nullable Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom = null;

	private @Nullable RTSerializationRule runtime = null;

	public BasicSerializationRule(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		this.ruleAnalysis = ruleAnalysis;
		this.rootSerializationNode = rootSerializationNode;
	}

	private @Nullable Set<@NonNull ParserRuleAnalysis> gatherAssignedRuleAnalyses(@Nullable Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses,
			@Nullable Set<@NonNull ParserRuleAnalysis> assignedRuleAnalyses) {
		if (ruleAnalyses != null) {
			for (@NonNull AbstractRuleAnalysis ruleAnalysis : ruleAnalyses) {
				if (ruleAnalysis instanceof ParserRuleAnalysis) {
					if (assignedRuleAnalyses == null) {
						assignedRuleAnalyses = new HashSet<>();
					}
					assignedRuleAnalyses.add((ParserRuleAnalysis) ruleAnalysis);
				}
			}
		}
		return assignedRuleAnalyses;
	}

	private @Nullable List<@NonNull AssignedSerializationNode> gatherAssignedSerializationNodes(@NonNull EReference eReference, @NonNull SerializationNode serializationNode, @Nullable List<@NonNull AssignedSerializationNode> assignedSerializationNodes) {
		if (serializationNode instanceof AssignedSerializationNode) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			if (assignedSerializationNode.getEStructuralFeature() == eReference) {
				if (assignedSerializationNodes == null) {
					assignedSerializationNodes = new ArrayList<>();
				}
				assignedSerializationNodes.add(assignedSerializationNode);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				assignedSerializationNodes = gatherAssignedSerializationNodes(eReference, nestedSerializationNode, assignedSerializationNodes);
			}
		}
		return assignedSerializationNodes;
	}

	@Override
	public @Nullable Set<@NonNull ParserRuleAnalysis> getAssignedRuleAnalyses(@NonNull EReference eReference, @Nullable Set<@NonNull ParserRuleAnalysis> assignedRuleAnalyses) {
		return getAssignedRuleAnalyses(eReference, getRootSerializationNode(), assignedRuleAnalyses);
	}
	private @Nullable Set<@NonNull ParserRuleAnalysis> getAssignedRuleAnalyses(@NonNull EReference eReference, @NonNull SerializationNode serializationNode, @Nullable Set<@NonNull ParserRuleAnalysis> assignedRuleAnalyses) {
		/* if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			AlternativeAssignedKeywordsSerializationNode assignedKeywordsSerializationNode = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
			if (assignedKeywordsSerializationNode.getEStructuralFeature() == eReference) {
				ParserRuleAnalysis enumerationValue = assignedKeywordsSerializationNode.getEnumerationValue();
				if (!enumerationValue.isNull()) {
					ruleAnalyses.add(enumerationValue);
				}
			}
		}
		else*/ if ((serializationNode instanceof AssignedRuleCallSerializationNode) || (serializationNode instanceof AlternativeAssignedRuleCallsSerializationNode)) {
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			if (assignedSerializationNode.getEStructuralFeature() == eReference) {
				Iterable<@NonNull AbstractRuleAnalysis> ruleAnalyses = assignedSerializationNode.getAssignedRuleAnalyses();
				assignedRuleAnalyses = gatherAssignedRuleAnalyses(ruleAnalyses, assignedRuleAnalyses);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				assignedRuleAnalyses = getAssignedRuleAnalyses(eReference, nestedSerializationNode, assignedRuleAnalyses);
			}
		}
		return assignedRuleAnalyses;
	}

	@Override
	public @Nullable Iterable<@NonNull AssignedSerializationNode> getAssignedSerializationNodes(@NonNull EReference eReference) {
		return gatherAssignedSerializationNodes(eReference, rootSerializationNode, null);
	}

	@Override
	public @NonNull BasicSerializationRule getBasicSerializationRule() {
		return this;
	}

	@Override
	public @Nullable Set<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute, @Nullable Set<@NonNull EnumerationValue> enumerationValues) {
		return getEnumerationValues(eAttribute, getRootSerializationNode(), enumerationValues);
	}
	private @Nullable Set<@NonNull EnumerationValue> getEnumerationValues(@NonNull EAttribute eAttribute, @NonNull SerializationNode serializationNode, @Nullable Set<@NonNull EnumerationValue> enumerationValues) {
		if (serializationNode instanceof AlternativeAssignedKeywordsSerializationNode) {
			AlternativeAssignedKeywordsSerializationNode assignedKeywordsSerializationNode = (AlternativeAssignedKeywordsSerializationNode)serializationNode;
			if (assignedKeywordsSerializationNode.getEStructuralFeature() == eAttribute) {
				EnumerationValue enumerationValue = assignedKeywordsSerializationNode.getEnumerationValue();
				if (enumerationValues == null) {
					enumerationValues = new HashSet<>();
				}
				enumerationValues.add(enumerationValue);
			}
		}
		else if (serializationNode instanceof AssignedKeywordSerializationNode) {
			AssignedKeywordSerializationNode assignedKeywordSerializationNode = (AssignedKeywordSerializationNode)serializationNode;
			if (assignedKeywordSerializationNode.getEStructuralFeature() == eAttribute) {
				EnumerationValue enumerationValue = assignedKeywordSerializationNode.getEnumerationValue();
				if (enumerationValues == null) {
					enumerationValues = new HashSet<>();
				}
				enumerationValues.add(enumerationValue);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				enumerationValues = getEnumerationValues(eAttribute, nestedSerializationNode, enumerationValues);
			}
		}
		return enumerationValues;
	}



	private void getIdiomMatches(@NonNull SerializationNode serializationNode, @NonNull Iterable<@NonNull Idiom> idioms,
			@Nullable IdiomMatch @NonNull [] idiomMatches) {
		int idiomIndex = 0;
		for (@NonNull Idiom idiom : idioms) {
			IdiomMatch idiomMatch = idiomMatches[idiomIndex];
			if (idiomMatch == null) {
				idiomMatches[idiomIndex] = idiom.firstMatch(serializationNode, this);
			}
			else {
				idiomMatch.nextMatch(serializationNode, this);
			}
			idiomIndex++;
		}
		if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				getIdiomMatches(nestedSerializationNode, idioms, idiomMatches);
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

	@Override
	public @NonNull String getName() {
		return ruleAnalysis.getName();
	}

	@Override
	public @NonNull EClass getProducedEClass() {
		EClass producedEClass2 = producedEClass;
		if (producedEClass2 == null) {
			EClass returnedEClass = ruleAnalysis.getReturnedEClass();
			producedEClass = producedEClass2 = refineProducedEClass(rootSerializationNode, returnedEClass);
			if ("EnumerationCS".equals(producedEClass2.getName())) {		// XXX debugging
				refineProducedEClass(rootSerializationNode, returnedEClass);
				getClass();
			}
		}
		return producedEClass2;
	}

	@Override
	public @NonNull SerializationNode getRootSerializationNode() {
		return rootSerializationNode;
	}

	@Override
	public @NonNull ParserRuleAnalysis getRuleAnalysis() {
		return ruleAnalysis;
	}

	public @NonNull RTSerializationRule getRuntime() {
		RTSerializationRule runtime2 = runtime;
		if (runtime2 == null) {
			getStaticRuleMatch();
			runtime2 = runtime;
			if (runtime2 == null) {
				runtime = runtime2 = RTSerializationRule2.create(this, getSerializationNode2subIdioms());
			}
		}
		return runtime2;
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
		return getSerializationNode2subIdioms().get(serializationNode);
	}

	private @NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> getSerializationNode2subIdioms() {
		Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom2 = serializationNode2subIdiom;
		if (serializationNode2subIdiom2 == null) {
			assert staticRuleMatch != null;
			@NonNull Iterable<@NonNull Idiom> idioms = staticRuleMatch.getSerializationRule().getRuleAnalysis().getGrammarAnalysis().getIdioms();
			//
			//	Locate the matches for each idiom.
			//
			@Nullable IdiomMatch @NonNull [] idiomMatches = new @Nullable IdiomMatch[Iterables.size(idioms)];
			getIdiomMatches(rootSerializationNode, idioms, idiomMatches);
			//
			//	Install the subidioms for each first full idiom match.
			//
			serializationNode2subIdiom2 = new HashMap<>();
			for (@Nullable IdiomMatch idiomMatch : idiomMatches) {
				if (idiomMatch != null) {
					idiomMatch.installIn(serializationNode2subIdiom2);
				}
			}
			serializationNode2subIdiom = serializationNode2subIdiom2;
		}
		return serializationNode2subIdiom2;
	}

	public @NonNull Iterable<@NonNull CardinalityVariable> getVariables() {
		assert staticRuleMatch != null;
		return staticRuleMatch.getCardinalityVariables();
	}

	public @Nullable DynamicRuleMatch match(@NonNull UserSlotsAnalysis slotsAnalysis) {
		assert staticRuleMatch != null;
		return staticRuleMatch.match(slotsAnalysis);
	}

	public boolean needsDefault(@NonNull EStructuralFeature eStructuralFeature) {
		assert staticRuleMatch != null;
		return staticRuleMatch.needsDefault(eStructuralFeature);
	}

	protected @NonNull EClass refineProducedEClass(@NonNull SerializationNode serializationNode, @NonNull EClass producedEClass) {
		if (serializationNode instanceof AssignedSerializationNode) {
			EClass assignedEClass = ((AssignedSerializationNode)serializationNode).getAssignedEClass();
			if (producedEClass.isSuperTypeOf(assignedEClass)) {
				producedEClass = assignedEClass;
			}
			else {
				assert assignedEClass.isSuperTypeOf(producedEClass);
			}
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode nestedSerializationNode : ((SequenceSerializationNode)serializationNode).getSerializationNodes()) {
				producedEClass = refineProducedEClass(nestedSerializationNode, producedEClass);
			}
		}
		return producedEClass;
	}

	@Override
	public void toDebugString(@NonNull StringBuilder s, int depth) {
		rootSerializationNode.toString(s, -1);
	}

	@Override
	public void toRuleString(@NonNull StringBuilder s) {
		rootSerializationNode.toString(s, -1);
	}

	@Override
	public void toSolutionString(@NonNull StringBuilder s, int depth) {
		StaticRuleMatch staticRuleMatch2 = staticRuleMatch;
		if (staticRuleMatch2 != null) {
			staticRuleMatch2.toSolutionString(s, depth);
		}
	}

	@Override
	public final @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, -1);
		return String.valueOf(s);
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