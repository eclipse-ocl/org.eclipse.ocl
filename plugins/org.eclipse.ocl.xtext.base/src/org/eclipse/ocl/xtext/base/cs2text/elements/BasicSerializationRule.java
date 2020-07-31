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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityExpression;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.StaticRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

public class BasicSerializationRule extends AbstractSerializationRule
{
	private final @NonNull Map<@NonNull EStructuralFeature, @NonNull Map<@NonNull EnumerationValue, org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality>> eFeature2enumerationValue2multiplicativeCardinality = new HashMap<>();
	private final @NonNull Map<@NonNull SerializationNode, /*@NonNull*/ CardinalityVariable> node2variable;		// XXX debugging @NonNull
	private final @NonNull Map<@NonNull CardinalityVariable, @NonNull SerializationNode> variable2node;
	private @Nullable StaticRuleMatch staticRuleMatch = null;
	private @Nullable Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom = null;

	public BasicSerializationRule(@NonNull ParserRuleAnalysis ruleAnalysis, @NonNull SerializationNode rootSerializationNode) {
		super(ruleAnalysis, rootSerializationNode);
		this.node2variable = new HashMap<>();
		this.variable2node = new HashMap<>();
		if ("EnumerationCS".equals(ruleAnalysis.getRuleName())) {
			getClass();	// XXX
		}
		accumulate(rootSerializationNode, MultiplicativeCardinality.ONE);
	}

	private void accumulate(@NonNull SerializationNode serializationNode, @NonNull MultiplicativeCardinality outerMultiplicativeCardinality) {
		MultiplicativeCardinality innerMultiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
		MultiplicativeCardinality netMultiplicativeCardinality = MultiplicativeCardinality.max(innerMultiplicativeCardinality, outerMultiplicativeCardinality);
		if (serializationNode instanceof AssignedSerializationNode) {		// XXX bad cast
			AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
			EnumerationValue enumerationValue = assignedSerializationNode.getEnumerationValue();
			AssignmentAnalysis assignmentAnalysis = assignedSerializationNode.getAssignmentAnalysis();
			EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
			if ("ownedProperties".equals(eStructuralFeature.getName())) {
				getClass();	// XXX
			}
			MultiplicativeCardinality newMultiplicativeCardinality = netMultiplicativeCardinality;
			Map<@NonNull EnumerationValue, org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eFeature2enumerationValue2multiplicativeCardinality.get(eStructuralFeature);
			if (enumerationValue2multiplicativeCardinality == null) {
				enumerationValue2multiplicativeCardinality = new HashMap<>();
				eFeature2enumerationValue2multiplicativeCardinality.put(eStructuralFeature, enumerationValue2multiplicativeCardinality);
			}
			MultiplicativeCardinality oldMultiplicativeCardinality = enumerationValue2multiplicativeCardinality.get(enumerationValue);
			if (oldMultiplicativeCardinality != null) {
				boolean newMayBeMany = netMultiplicativeCardinality.mayBeMany();
				boolean newMayBeZero = netMultiplicativeCardinality.mayBeZero();
				boolean oldMayBeMany = oldMultiplicativeCardinality.mayBeMany();
				boolean oldMayBeZero = oldMultiplicativeCardinality.mayBeZero();
				if (!oldMayBeZero) {
					newMayBeZero = false;
				}
				if (oldMayBeMany) {
					newMayBeMany = true;
				}
				newMultiplicativeCardinality = newMayBeMany
					? newMayBeZero ? MultiplicativeCardinality.ZERO_OR_MORE : MultiplicativeCardinality.ONE_OR_MORE
					: newMayBeZero ? MultiplicativeCardinality.ZERO_OR_ONE : MultiplicativeCardinality.ONE;
			}
			enumerationValue2multiplicativeCardinality.put(enumerationValue, newMultiplicativeCardinality);
//			assignedSerializationNodes.add(assignedSerializationNode);
		}
		else if (serializationNode instanceof SequenceSerializationNode) {
			SequenceSerializationNode sequenceSerializationNode = (SequenceSerializationNode)serializationNode;
			for (@NonNull SerializationNode nestedSerializationNode : sequenceSerializationNode.getSerializationNodes()) {
				accumulate(nestedSerializationNode, netMultiplicativeCardinality);
			}
		}
	}

	public void addAssignedNode(@NonNull AssignedSerializationNode assignedSerializationNode, @NonNull EnumerationValue enumerationValue, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		assert staticRuleMatch != null;
		CardinalityExpression cardinalityExpression = staticRuleMatch.addAssignedNode(assignedSerializationNode);
		List<@NonNull CardinalityVariable> variables = new ArrayList<>();
		for (SerializationNode serializationNode : parentStack) {
			CardinalityVariable cardinalityVariable = node2variable.get(serializationNode);
			if (cardinalityVariable != null) {
				variables.add(cardinalityVariable);
			}
		}
		CardinalityVariable cardinalityVariable = node2variable.get(assignedSerializationNode);
		if (cardinalityVariable != null) {
			variables.add(cardinalityVariable);
		}
		if (!enumerationValue.isNull()) {
			CardinalityExpression cardinalityExpression2 = cardinalityExpression.getCardinalityExpression(ruleAnalysis.getGrammarAnalysis(), enumerationValue);
			cardinalityExpression2.addMultiplicityProduct(variables);
		}
		else {
			cardinalityExpression.addMultiplicityProduct(variables);
		}
	}

	public void addSerializedNode(@NonNull SerializationNode serializationNode, @NonNull Stack<@NonNull SerializationNode> parentStack) {
		MultiplicativeCardinality multiplicativeCardinality = serializationNode.getMultiplicativeCardinality();
		String name = String.format("C%02d", variable2node.size());
		assert name != null;
		if (!multiplicativeCardinality.isConstant()) {
			AbstractRuleAnalysis ruleAnalysis = serializationNode.getRuleAnalysis();
			CardinalityVariable cardinalityVariable = new CardinalityVariable(name, ruleAnalysis, multiplicativeCardinality);
			CardinalityVariable old2 = node2variable.put(serializationNode, cardinalityVariable);
			assert old2 == null;
			SerializationNode old3 = variable2node.put(cardinalityVariable, serializationNode);
			assert old3 == null;
		}
	}

	@Override
	public @NonNull BasicSerializationRule getBasicSerializationRule() {
		return this;
	}

	public @NonNull Iterable<@NonNull EStructuralFeature> getEStructuralFeatures() {
		return eFeature2enumerationValue2multiplicativeCardinality.keySet();
	}

	private void getIdiomMatches(@NonNull SerializationNode outerSerializationNode, @NonNull Idiom @NonNull [] idioms,
			@Nullable IdiomMatch @NonNull [] idiomMatches) {
		for (int idiomIndex = 0; idiomIndex < idioms.length; idiomIndex++) {
			IdiomMatch idiomMatch = idiomMatches[idiomIndex];
			if (idiomMatch == null) {
				Idiom idiom = idioms[idiomIndex];
				idiomMatches[idiomIndex] = idiom.firstMatch(outerSerializationNode, this);
			}
			else {
				idiomMatch.nextMatch(outerSerializationNode, this);
			}
		}
		if (outerSerializationNode instanceof SequenceSerializationNode) {
			for (@NonNull SerializationNode innerSerializationNode : ((SequenceSerializationNode)outerSerializationNode).getSerializationNodes()) {
				getIdiomMatches(innerSerializationNode, idioms, idiomMatches);
			}
		}
	}

	public @Nullable MultiplicativeCardinality getMultiplicativeCardinality(@NonNull EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue) {
		Map<@NonNull EnumerationValue, org.eclipse.ocl.xtext.base.cs2text.elements.MultiplicativeCardinality> enumerationValue2multiplicativeCardinality = eFeature2enumerationValue2multiplicativeCardinality.get(eStructuralFeature);
		if (enumerationValue2multiplicativeCardinality == null) {
			return null;
		}
		return enumerationValue2multiplicativeCardinality.get(enumerationValue);
	}

	public @NonNull StaticRuleMatch getStaticRuleMatch() {
		StaticRuleMatch staticRuleMatch2 = staticRuleMatch;
		if (staticRuleMatch2 == null) {
			if ("EssentialOCL::TupleTypeCS".equals(ruleAnalysis.getName())) {
				getClass();	// XXX debugging
			}
			staticRuleMatch = staticRuleMatch2 = new StaticRuleMatch(this);
			//
			//	Traverse the chosen serialization tree path to trigger addAssignedNode/addSerializedNode call-backs to determine the
			//	cardinality variables and expressions to be solved to characterize the serialization.
			//
			rootSerializationNode.analyze(this, new Stack<@NonNull SerializationNode>());
			//
			//	Analyze the cardinality expressions to find the solution for each cardinality variable.
			//
			staticRuleMatch2.analyze();
		}
		return staticRuleMatch2;
	}

	public @NonNull SubIdiom getSubIdiom(@NonNull SerializationNode serializationNode) {
		Map<@NonNull SerializationNode, @NonNull SubIdiom> serializationNode2subIdiom2 = serializationNode2subIdiom;
		if (serializationNode2subIdiom2 == null) {
			serializationNode2subIdiom = serializationNode2subIdiom2 = getSerializationNode2subIdioms(Idiom.IDIOMS);
		}
		SubIdiom subIdiom = serializationNode2subIdiom2.get(serializationNode);
		return subIdiom != null ? subIdiom : SubIdiom.VALUE;
	}

	private @NonNull Map<@NonNull SerializationNode, @NonNull SubIdiom> getSerializationNode2subIdioms(@NonNull Idiom @NonNull [] idioms) {
		//
		//	Locate the matches for each idiom.
		//
		@Nullable IdiomMatch @NonNull [] idiomMatches = new @Nullable IdiomMatch[idioms.length];
		getIdiomMatches(rootSerializationNode, idioms, idiomMatches);
		//
		//	Install the subdioms for each first full idom match.
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
		return ClassUtil.nonNullState(node2variable.get(serializationNode));
	}

	public @NonNull Iterable<@NonNull CardinalityVariable> getVariables() {
		return variable2node.keySet();
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
			List<@NonNull CardinalityVariable> variables = new ArrayList<>(variable2node.keySet());
			Collections.sort(variables, NameUtil.NAMEABLE_COMPARATOR);
			for (@NonNull CardinalityVariable variable : variables) {
				SerializationNode serializationNode = variable2node.get(variable);
				assert serializationNode != null;
				StringUtil.appendIndentation(s, depth, "  ");
				s.append("- ");
				s.append(variable);
				s.append(": ");
				serializationNode.toString(s, -1);
			}
			staticRuleMatch2.toString(s, depth);
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		s.append(ruleAnalysis.getName());
		s.append(": ");
		EClass producedEClass = getProducedEClass();
		s.append(producedEClass.getEPackage().getName());
		s.append("::");
		s.append(producedEClass.getName());
		s.append(" ");
		rootSerializationNode.toString(s, depth);
	}
}