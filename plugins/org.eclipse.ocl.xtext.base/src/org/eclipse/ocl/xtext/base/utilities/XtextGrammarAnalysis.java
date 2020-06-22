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
package org.eclipse.ocl.xtext.base.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;
import org.eclipse.ocl.xtext.base.utilities.UserModelAnalysis.AbstractUserElementAnalysis;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Alternatives;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CharacterRange;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.NegatedToken;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.UntilToken;
import org.eclipse.xtext.Wildcard;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.util.XtextSwitch;

import com.google.common.collect.Lists;

/**
 * An XtextGrammarAnalysis provides the extended analysis of an Xtext (multi-)grammar.
 */
public class XtextGrammarAnalysis
{
	/**
	 * An XtextAssignmentAnalysis provides the extended analysis of an Xtext Assignment
	 */
	public static class XtextAssignmentAnalysis implements Nameable
	{
		/**
		 * The rule analysis that uses this assignment to assign a target rule result.
		 */
		protected final @NonNull XtextParserRuleAnalysis sourceRuleAnalysis;

		/**
		 * The analyzed assignment.
		 */
		protected final @NonNull Assignment assignment;

		/**
		 * The overall grammar analysis.
		 */
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;

		/**
		 * The assigned feature.
		 */
		protected final @NonNull EStructuralFeature eFeature;

		/**
		 * The rules that may produce a result compatible with the target.
		 */
		private final @NonNull List<@NonNull XtextAbstractRuleAnalysis> targetRuleAnalyses = new ArrayList<>();

		public XtextAssignmentAnalysis(@NonNull XtextParserRuleAnalysis sourceRuleAnalysis, @NonNull Assignment assignment) {
			this.sourceRuleAnalysis = sourceRuleAnalysis;
			this.assignment = assignment;
			this.grammarAnalysis = sourceRuleAnalysis.getGrammarAnalysis();
			String featureName = getFeature(assignment);
			EClass eClass = (EClass)getEClassifierScope(assignment);
			this.eFeature = XtextGrammarAnalysis.getEStructuralFeature(eClass, featureName);
			addTerminal(getTerminal(assignment));
		}

		private void addTerminal(@NonNull AbstractElement terminal) {
			if (terminal instanceof RuleCall) {
				addTerminalRuleCall((RuleCall)terminal);
			}
			else if (terminal instanceof Alternatives) {
				for (@NonNull AbstractElement element : getElements((Alternatives)terminal)) {
					addTerminal(element);
				}
			}
			else if (terminal instanceof CrossReference) {}
			else if (terminal instanceof Keyword) {}
			else {
				throw new UnsupportedOperationException();
			}
		}

		private void addTerminalRuleCall(@NonNull RuleCall terminal) {
			AbstractRule terminalRule = getRule(terminal);
			XtextAbstractRuleAnalysis terminalRuleAnalysis = grammarAnalysis.getRuleAnalysis(terminalRule);
			targetRuleAnalyses.add(terminalRuleAnalysis);
		}

		public @NonNull Assignment getAssignment() {
			return assignment;
		}

		public @NonNull String getCardinality() {
			String cardinality = assignment.getCardinality();
			return cardinality != null ?  cardinality : "@";
		/*	int lowerBound = eFeature.getLowerBound();
			int upperBound = eFeature.getUpperBound();
			if (upperBound < 0) {
				return lowerBound != 0 ? "+" : "*";
			}
			else if (upperBound == 1) {
				return lowerBound != 0 ? "1" : "?";
			}
			else if (upperBound == lowerBound) {
				return Integer.toString(lowerBound);
			}
			else {
				return lowerBound + ".." + upperBound;
			} */
		}

		public @NonNull EClass getEContainingClass() {
			return XtextGrammarAnalysis.getEContainingClass(eFeature);
		}

		public @NonNull EStructuralFeature getEStructuralFeature() {
			return eFeature;
		}

		@Override
		public @NonNull String getName() {
			return XtextGrammarAnalysis.getName(sourceRuleAnalysis.getRule()) + "-" + eFeature.getName();
		}

		public @NonNull XtextParserRuleAnalysis getSourceRuleAnalysis() {
			return sourceRuleAnalysis;
		}

		public @NonNull List<@NonNull XtextAbstractRuleAnalysis> getTargetRuleAnalyses() {
			return targetRuleAnalyses;
		}

		/**
		 * Return true if sourceActualRuleAnalysis produces an acceptable result for use as the source of this assignment.
		 */
		public boolean sourceIsAssignableFrom(@NonNull XtextAbstractRuleAnalysis sourceActualRuleAnalysis) {
			return sourceActualRuleAnalysis.getBaseRuleAnalysisClosure().contains(this.sourceRuleAnalysis);
		}

		/**
		 * Return true if targetActualRuleAnalysis produces an acceptable result for use as the target of this assignment.
		 */
		public boolean targetIsAssignableFrom(@NonNull XtextAbstractRuleAnalysis targetActualRuleAnalysis) {
			Set<@NonNull XtextAbstractRuleAnalysis> targetActualRuleAnalysisClosure = targetActualRuleAnalysis.getBaseRuleAnalysisClosure();
			for (@NonNull XtextAbstractRuleAnalysis targetRuleAnalysis : this.targetRuleAnalyses) {
				if (targetActualRuleAnalysisClosure.contains(targetRuleAnalysis)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(getName());
			s.append(" : ");
			boolean isFirst = true;
			for (@NonNull XtextAbstractRuleAnalysis targetRuleAnalysis : targetRuleAnalyses) {
				if (!isFirst) {
					s.append(",");
				}
				s.append(targetRuleAnalysis.getName());
				isFirst = false;
			}
			return s.toString();
		}
	}

	public static interface SerializationNode
	{
		@NonNull String getCardinality();
		@Nullable SerializationBuilder isCompatible(@NonNull UserModelAnalysis modelAnalysis, @NonNull StringBuilder s, @NonNull EObject element);
		boolean isNull();
		void setCardinality(@NonNull String cardinality);
		void toString(@NonNull StringBuilder s, int depth);
		void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element);
	}

	public static interface AssignedSerializationNode extends SerializationNode
	{
		@NonNull EStructuralFeature getEStructuralFeature();
	}

	public static abstract class AbstractSerializationNode implements SerializationNode
	{
		/**
		 * The overall (multi-)grammar analysis.
		 */
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;
	//	protected final @NonNull String cardinality;
		private int lowerBound;
		private int upperBound;

		public AbstractSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @Nullable String cardinality) {
			this.grammarAnalysis = grammarAnalysis;
			if (cardinality == null) {
				this.lowerBound = 1;
				this.upperBound = 1;
			}
			else if (cardinality.equals("?")) {
				this.lowerBound = 0;
				this.upperBound = 1;
			}
			else if (cardinality.equals("*")) {
				this.lowerBound = 0;
				this.upperBound = -1;
			}
			else if (cardinality.equals("+")) {
				this.lowerBound = 1;
				this.upperBound = -1;
			}
			else {
				throw new UnsupportedOperationException("Unsupported cardinality '" + cardinality + "'");
			}
		}

//		public boolean addAlternative(@NonNull SerializationNode nestedContent) {
//			return false;
//		}

//		public boolean addAlternative(@NonNull AbstractElement newContent) {
//			return false;
//		}

		protected void appendCardinality(@NonNull StringBuilder s) {
			if ((lowerBound != 1) || (upperBound != 1)) {
				s.append(getCardinality());
			}
		}

		@Override
		public @NonNull String getCardinality() {
			if (upperBound < 0) {
				return lowerBound != 0 ? "+" : "*";
			}
			else if (upperBound == 1) {
				return lowerBound != 0 ? "1" : "?";
			}
			else if (upperBound == lowerBound) {
				return Integer.toString(lowerBound);
			}
			else {
				return lowerBound + ".." + upperBound;
			}
		}

		@Override
		public @Nullable SerializationBuilder isCompatible(@NonNull UserModelAnalysis modelAnalysis, @NonNull StringBuilder s, @NonNull EObject element) {
			return new SerializationBuilder(modelAnalysis, s);
		}

		@Override
		public boolean isNull() {
			return false;
		}

		@Override
		public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
			serializationBuilder.append("<<<Unsupported serialize '" + getClass().getSimpleName() + "'>>>");
		}

		@Override
		public void setCardinality(@NonNull String cardinality) {
			if ("?".equals(cardinality)) {
				lowerBound = 0;
			}
			else if ("*".equals(cardinality)) {
				lowerBound = 0;
				upperBound = -1;
			}
			else if ("+".equals(cardinality)) {
			//??	lowerBound = 1;
				upperBound = -1;
			}
			else {
				throw new UnsupportedOperationException("Unsupported cardinality '" + cardinality + "'");
			}
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			toString(s, 0);
			return s.toString();
		}
	}

	public static abstract class AbstractAssignedSerializationNode extends SimpleSerializationNode implements AssignedSerializationNode
	{
		protected final @NonNull EStructuralFeature eFeature;

		protected AbstractAssignedSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EStructuralFeature eFeature, @Nullable String cardinality) {
			super(grammarAnalysis, cardinality);
			this.eFeature = eFeature;
		}

		@Override
		public @NonNull EStructuralFeature getEStructuralFeature() {
			return eFeature;
		}
	}

	public static class AlternativeAssignedKeywordsSerializationNode extends AbstractAssignedSerializationNode
	{
		protected final @NonNull List<@NonNull String> values = new ArrayList<>();

		public AlternativeAssignedKeywordsSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EStructuralFeature eFeature) {
			super(grammarAnalysis, eFeature, null);
		}

		public void addKeyword(@NonNull Keyword keyword) {
			assert keyword.getCardinality() == null;
			values.add(getValue(keyword));
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			boolean isFirst = true;
			s.append(XtextGrammarAnalysis.getName(eFeature));
			s.append(eFeature.isMany() ? "+=" : "=");
			s.append("{");
			for (@NonNull String value : values) {
				if (!isFirst) {
					s.append("|");
				}
				s.append("\"");
				s.append(Strings.convertToJavaString(value));
				s.append("\"");
				isFirst = false;
			}
			s.append("}");
			appendCardinality(s);
		}
	}

	public static class AlternativeAssignedRuleCallsSerializationNode extends AbstractAssignedSerializationNode
	{
		protected final @NonNull List<@NonNull  XtextAbstractRuleAnalysis> ruleAnalyses;

		public AlternativeAssignedRuleCallsSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EStructuralFeature eFeature) {
			super(grammarAnalysis, eFeature, null);
			this.ruleAnalyses = eFeature.isUnique() ? new UniqueList<>() : new ArrayList<>();
		}

		public void addRuleAnalysis(@NonNull XtextAbstractRuleAnalysis ruleAnalysis) {
			ruleAnalyses.add(ruleAnalysis);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(XtextGrammarAnalysis.getName(eFeature));
			s.append(eFeature.isMany() ? "+=" : "=");
			if (ruleAnalyses.size() > 1) {
				s.append("{");
			}
			boolean isFirst = true;
			for (@NonNull XtextAbstractRuleAnalysis ruleAnalysis : ruleAnalyses) {
				if (!isFirst) {
					s.append("|");
				}
				s.append(ruleAnalysis.getRuleName());
				isFirst = false;
			}
			if (ruleAnalyses.size() > 1) {
				s.append("}");
			}
			appendCardinality(s);
		}
	}

	public static class AlternativeKeywordsSerializationNode extends AbstractSerializationNode
	{
		protected final @NonNull List<@NonNull String> values = new ArrayList<>();

		public AlternativeKeywordsSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis) {
			super(grammarAnalysis, null);
		}

		public void addKeyword(@NonNull Keyword keyword) {
			assert keyword.getCardinality() == null;
			values.add(getValue(keyword));
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			boolean isFirst = true;
			if (values.size() > 1) {
				s.append("{");
			}
			for (@NonNull String value : values) {
				if (!isFirst) {
					s.append("|");
				}
				s.append("\"");
				s.append(Strings.convertToJavaString(value));
				s.append("\"");
				isFirst = false;
			}
			if (values.size() > 1) {
				s.append("}");
			}
			appendCardinality(s);
		}
	}

	public static class AlternativesSerializationNode extends CompositeSerializationNode
	{
		public AlternativesSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @Nullable String cardinality, @NonNull List<@NonNull SerializationNode> alternatives) {
			super(grammarAnalysis, cardinality, alternatives);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("\t");
			s.append("{");
		//	boolean isFirst = true;
			for (@NonNull SerializationNode serializationNode : serializationNodes) {
			//	if (!isFirst) {
					s.append("\n");
			//	}
				StringUtil.appendIndentation(s, depth, "\t");
				s.append("| ");
				serializationNode.toString(s, depth+1);
			//	isFirst = false;
			}
			s.append("\n");
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("}");
			appendCardinality(s);
		}
	}

	public static class AssignedCrossReferenceSerializationNode extends AbstractAssignedSerializationNode
	{
		protected final @NonNull CrossReference crossReference;
		protected final @NonNull RuleCall ruleCall;

		public AssignedCrossReferenceSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EStructuralFeature eFeature, @Nullable String cardinality, @NonNull CrossReference crossReference) {
			super(grammarAnalysis, eFeature, cardinality);
			this.crossReference = crossReference;
			this.ruleCall = (RuleCall) crossReference.getTerminal();
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(XtextGrammarAnalysis.getName(eFeature));
			s.append(eFeature.isMany() ? "+=" : "=");
			s.append(ruleCall.getRule().getName());
			appendCardinality(s);
		}
	}

	public static class AssignedKeywordSerializationNode extends AbstractAssignedSerializationNode
	{
		protected final @NonNull String value;

		public AssignedKeywordSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EStructuralFeature eFeature, @Nullable String cardinality, @NonNull Keyword keyword) {
			super(grammarAnalysis, eFeature, cardinality);
			this.value = getValue(keyword);
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(XtextGrammarAnalysis.getName(eFeature));
			s.append(eFeature.isMany() ? "+=" : "=");
			s.append("\"");
			s.append(value);
			s.append("\"");
			appendCardinality(s);
		}
	}

	public static class AssignedRuleCallSerializationNode extends AbstractAssignedSerializationNode
	{
		protected final @NonNull XtextAbstractRuleAnalysis ruleAnalysis;

		public AssignedRuleCallSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull EStructuralFeature eFeature, @Nullable String cardinality, @NonNull XtextAbstractRuleAnalysis ruleAnalysis) {
			super(grammarAnalysis, eFeature, cardinality);
			this.ruleAnalysis = ruleAnalysis;
		}

		@Override
		public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
			// serializationBuilder.serialize(element);
			int index = serializationBuilder.consume(eFeature);
			Object eGet = element.eGet(eFeature);
			if (eFeature instanceof EReference) {
				assert ((EReference)eFeature).isContainment();
				if (eFeature.isMany()) {
					List<EObject> eList = (List<EObject>)eGet;
					assert index < eList.size();
					eGet = eList.get(index);
				}
				else {
					assert index == 0;
				}
				serializationBuilder.serialize((EObject)eGet);
			}
			else {
				serializationBuilder.append("<<attribute-call>>");
			}
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(XtextGrammarAnalysis.getName(eFeature));
			s.append(eFeature.isMany() ? "+=" : "=");
			s.append(ruleAnalysis.getRuleName());
			appendCardinality(s);
		}
	}

	public static class CharacterRangeSerializationNode extends AbstractSerializationNode
	{
	//	protected final @NonNull CharacterRange characterRange;
		protected final @NonNull String left;
		protected final @NonNull String right;


		public CharacterRangeSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull CharacterRange characterRange) {
			super(grammarAnalysis, characterRange.getCardinality());
		//	this.characterRange = characterRange;
			this.left = getValue(characterRange.getLeft());
			this.right = getValue(characterRange.getRight());
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("[\"");
			s.append(Strings.convertToJavaString(left));
			s.append("\"-\"");
			s.append(Strings.convertToJavaString(right));
			s.append("\"]");
			appendCardinality(s);
		}
	}

	public static abstract class CompositeSerializationNode extends AbstractSerializationNode
	{
		protected final @NonNull List<@NonNull SerializationNode> serializationNodes;

		public CompositeSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @Nullable String cardinality, @NonNull List<@NonNull SerializationNode> serializationNodes) {
			super(grammarAnalysis, cardinality);
			this.serializationNodes = serializationNodes;
		//	assert serializationNodes.size() > 1;
		}

/*		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			appendPrefixCardinality(s);
			s.append("{");
		//	boolean isFirst = true;
			for (@NonNull SerializationNode element : elements) {
			//	if (!isFirst) {
					s.append("\n");
			//	}
				StringUtil.appendIndentation(s, depth, "\t");
				s.append("| ");
				element.toString(s, depth+1);
			//	isFirst = false;
			}
			s.append("\n");
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("}");
		} */
	}

	public static class KeywordSerializationNode extends AbstractSerializationNode
	{
		protected final @NonNull String value;

		public KeywordSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull Keyword keyword) {
			super(grammarAnalysis, keyword.getCardinality());
			this.value = getValue(keyword);
		}

		@Override
		public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
			serializationBuilder.appendSoftSpace();
			serializationBuilder.append(value);
			serializationBuilder.appendSoftSpace();
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("\"");
			s.append(Strings.convertToJavaString(value));
			s.append("\"");
			appendCardinality(s);
		}
	}

	public static class NegatedTokenSerializationNode extends CompositeSerializationNode
	{
		public NegatedTokenSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull NegatedToken negatedToken, @NonNull SerializationNode serializationNode) {
			super(grammarAnalysis, negatedToken.getCardinality(), Lists.newArrayList(serializationNode));
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("\t");
		//	s.append("~ ");
		//	boolean isFirst = true;
			for (@NonNull SerializationNode serializationNode : serializationNodes) {
			//	StringUtil.appendIndentation(s, depth, "\t");
				s.append("~");
				appendCardinality(s);
				s.append(" ");
				serializationNode.toString(s, depth);
			//	isFirst = false;
			}
		}
	}

	public static class NullSerializationNode extends AbstractSerializationNode
	{
		public NullSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis) {
			super(grammarAnalysis, null);
		}

		@Override
		public boolean isNull() {
			return true;
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("<<null>>");
			appendCardinality(s);
		}
	}

	public static class SequenceSerializationNode extends CompositeSerializationNode
	{
		public SequenceSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @Nullable String cardinality, @NonNull List<@NonNull SerializationNode> elements) {
			super(grammarAnalysis, cardinality, elements);
		}

		@Override
		public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
			for (@NonNull SerializationNode serializationNode : serializationNodes) {
				serializationBuilder.appendSoftSpace();
				serializationNode.serialize(serializationBuilder, element);
				serializationBuilder.appendSoftSpace();
			}
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("\t");
			s.append("{");
		//	boolean isFirst = true;
			for (@NonNull SerializationNode serializationNode : serializationNodes) {
			//	if (!isFirst) {
					s.append("\n");
			//	}
				StringUtil.appendIndentation(s, depth, "\t");
				s.append("+ ");
				serializationNode.toString(s, depth+1);
			//	isFirst = false;
			}
			s.append("\n");
			StringUtil.appendIndentation(s, depth, "\t");
			s.append("}");
			appendCardinality(s);
		}
	}

	public static abstract class SimpleSerializationNode extends AbstractSerializationNode
	{
		public SimpleSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @Nullable String cardinality) {
			super(grammarAnalysis, cardinality);
		}
	}

	public static class UntilTokenSerializationNode extends AbstractSerializationNode
	{
		protected final @NonNull String value;

		public UntilTokenSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull UntilToken untilToken) {
			super(grammarAnalysis, untilToken.getCardinality());
			this.value = getValue((Keyword)untilToken.getTerminal());
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append(" -> \"");
			s.append(Strings.convertToJavaString(value));
			s.append("\"");
			appendCardinality(s);
		}
	}

	public static class WildcardSerializationNode extends AbstractSerializationNode
	{
		public WildcardSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull Wildcard wildcard) {
			super(grammarAnalysis, wildcard.getCardinality());
		}

		@Override
		public void toString(@NonNull StringBuilder s, int depth) {
			s.append("<<WILDCARD>>");
			appendCardinality(s);
		}
	}

	/**
	 * An XtextAbstractRuleAnalysis provides the extended analysis of an Xtext AbstractRule
	 */
	public static class XtextAbstractRuleAnalysis implements Nameable
	{
		/**#
		 * The overall (multi-)grammar analysis.
		 */
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;

		/**
		 * The analyzed Xtext rule.
		 */
		protected final @NonNull AbstractRule abstractRule;

		/**
		 * The semi-qualified name of this rule (final part of grammar name and rule name).
		 */
		protected final @NonNull String name;

		/**
		 * The EClassifiers that this rule may produce.
		 */
		protected final @NonNull List<@NonNull EClassifier> eClassifiers = new ArrayList<>();

		/**
		 * RuleAnalyses that this RuleAnalysis may be directly used as an alternative for.
		 */
		private @Nullable List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses = null;

		/**
		 * Lazily computed closure of RuleAnalyses that this RuleAnalysis may be used as an alternative for.
		 */
		private @Nullable UniqueList<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalysesClosure = null;

		/**
		 * The terms for each possible permutation of alternatives.
		 */
	//	private @Nullable List<@NonNull XtextTermsAnalysis> termsAnalyses = null;
		private @Nullable SerializationNode serializationNode = null;

		public XtextAbstractRuleAnalysis(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull AbstractRule abstractRule) {
			this.grammarAnalysis = grammarAnalysis;
			this.abstractRule = abstractRule;
			String grammarName = getEContainingGrammar(abstractRule).getName();
			int index = grammarName.lastIndexOf('.');
			if (index >= 0) {
				grammarName = grammarName.substring(index+1);
			}
			this.name = grammarName + "::" + XtextGrammarAnalysis.getName(abstractRule);
		}

		public void addBaseRuleAnalysis(@NonNull XtextAbstractRuleAnalysis baseRuleAnalysis) {
			List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses2 = baseRuleAnalyses;
			if (baseRuleAnalyses2 == null) {
				baseRuleAnalyses = baseRuleAnalyses2 = new ArrayList<>();
			}
			if (!baseRuleAnalyses2.contains(baseRuleAnalysis)) {
				baseRuleAnalyses2.add(baseRuleAnalysis);
			}
		}

		protected void addProducedTypeRef(@NonNull TypeRef type) {
			EClassifier eClassifier = getClassifier(type);
			if (!this.eClassifiers.contains(eClassifier)) {
			//	if ("AttributeCS".equals(eClassifier.getName())) {
			//		getClass();
			//	}
				this.eClassifiers.add(eClassifier);
			}
		}

	//	public void addTermsAnalysis(@NonNull XtextTermsAnalysis termsAnalysis) {
	//		assert termsAnalyses != null;
	//		termsAnalyses.add(termsAnalysis);
	//	}

		public @Nullable List<@NonNull XtextAbstractRuleAnalysis> basicGetBaseRuleAnalyses() {
			return baseRuleAnalyses;
		}

	//	public @Nullable List<@NonNull XtextTermsAnalysis> basicGetTermsAnalyses() {
	//		return termsAnalyses;
	//	}

		public @Nullable SerializationNode basicGetContents() {
			return serializationNode;
		}

		public @NonNull UniqueList<@NonNull XtextAbstractRuleAnalysis> getBaseRuleAnalysisClosure() {
			UniqueList<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalysesClosure = this.baseRuleAnalysesClosure;
			if (baseRuleAnalysesClosure == null) {
				baseRuleAnalysesClosure = new UniqueList<>();
				baseRuleAnalysesClosure.add(this);
				for (int i = 0; i < baseRuleAnalysesClosure.size(); i++) {
					XtextAbstractRuleAnalysis ruleAnalysis = baseRuleAnalysesClosure.get(i);
					List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses = ruleAnalysis.basicGetBaseRuleAnalyses();
					if (baseRuleAnalyses != null) {
						baseRuleAnalysesClosure.addAll(baseRuleAnalyses);
					}
				}
			}
			Collections.sort(baseRuleAnalysesClosure, NameUtil.NAMEABLE_COMPARATOR);
			this.baseRuleAnalysesClosure = baseRuleAnalysesClosure;
			return baseRuleAnalysesClosure;
		}

		public @NonNull List<@NonNull EClassifier> getEClassifiers() {
			return eClassifiers;
		}

		public @NonNull XtextGrammarAnalysis getGrammarAnalysis() {
			return grammarAnalysis;
		}

		@Override
		public @NonNull String getName() {
			return name;
		}

		public @NonNull AbstractRule getRule() {
			return abstractRule;
		}

		public @NonNull String getRuleName() {
			return XtextGrammarAnalysis.getName(abstractRule);
		}

	//	public @NonNull List<@NonNull XtextTermsAnalysis> getTermsAnalyses() {
	//		assert termsAnalyses != null;
	//		return termsAnalyses;
	//	}

		/**
		 * Perform the inter analysis to determine the base rule closure.
		 *
		public void interAnalyze() {
			if ("TypedTypeRefCS".equals(abstractRule.getName())) {
				getClass(); //XXX
			}
			List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses2 = baseRuleAnalyses;
			if (baseRuleAnalyses2 != null) {
				UniqueList<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalysisClosure = new UniqueList<>(baseRuleAnalyses2);
				for (int i = 0; i < baseRuleAnalysisClosure.size(); i++) {
					XtextAbstractRuleAnalysis baseRuleAnalysis = baseRuleAnalysisClosure.get(i);
					List<@NonNull XtextAbstractRuleAnalysis> nestedBaseRuleAnalyses = baseRuleAnalysis.getBaseRuleAnalyses();
					if (nestedBaseRuleAnalyses != null) {
						baseRuleAnalysisClosure.addAll(nestedBaseRuleAnalyses);
					}
				}
				this.baseRuleAnalyses = baseRuleAnalysisClosure;
			}
		}*/

		/**
		 * Perform the intra analysis to determine the locally produced EClassifiers and local base rules.
		 */
		protected void intraAnalyze() {
			addProducedTypeRef(getType(abstractRule));
			for (EObject eObject : new TreeIterable(abstractRule, false)) {
				if (eObject instanceof Action) {
					Action action = (Action)eObject;
				//	if (isFirstResultType(action)) {
						addProducedTypeRef(getType(action));
				//	}
				}
				else if (eObject instanceof RuleCall) {
					RuleCall ruleCall = (RuleCall)eObject;
					if (isFirstResultType(ruleCall)) {
						AbstractRule derivedRule = XtextGrammarAnalysis.getRule(ruleCall);
						XtextAbstractRuleAnalysis derivedRuleAnalysis = grammarAnalysis.getRuleAnalysis(derivedRule);
						derivedRuleAnalysis.addBaseRuleAnalysis(this);
					}
				}
			}
			this.serializationNode = new XtextTermsSwitch(this).correlate();
		}

		/**
		 * Return true if the transitive descendants of element involve a RuleCall.
		 */
		private boolean hasResultType(@NonNull AbstractElement element) {
			if (element instanceof RuleCall) {
				return true;
			}
			if (element instanceof Group) {
				for (@NonNull AbstractElement childElement : getElements((Group)element)) {
					return hasResultType(childElement);
				}
				return false;
			}
			if (element instanceof Alternatives) {
				for (@NonNull AbstractElement childElement : getElements((Alternatives)element)) {
					return hasResultType(childElement);
				}
				return false;
			}
			if (element instanceof Action) {
				return true;
			}
			if (element instanceof Keyword) {
				return false;
			}
			if (element instanceof Assignment) {
				return true;
			}
			throw new UnsupportedOperationException();
		}

		public @Nullable SerializationBuilder isCompatible(@NonNull UserModelAnalysis modelAnalysis, @NonNull StringBuilder s, @NonNull EObject element) {
			assert serializationNode != null;
			return serializationNode.isCompatible(modelAnalysis, s, element);
		}

		private boolean isFirstResultType(@NonNull AbstractElement element) {
			EObject eContainer = element.eContainer();
			if (eContainer instanceof Group) {
				Group group = (Group)eContainer;
				List<@NonNull AbstractElement> siblings = getElements(group);
				int index = siblings.indexOf(element);
				for (int i = 0; i < index; i++) {
					AbstractElement predecessor = siblings.get(i);
					if (hasResultType(predecessor)) {
						return false;
					}
				}
			}
			else if (eContainer instanceof Alternatives) {}
			else if (eContainer instanceof Assignment) {
				return false;
			}
			else if (eContainer instanceof CrossReference) {
				return false;
			}
			else if (eContainer instanceof AbstractRule) {
				return true;
			}
			else {
				throw new UnsupportedOperationException();
			}
			return isFirstResultType((AbstractElement)eContainer);
		}

		public void serialize(@NonNull SerializationBuilder serializationBuilder, @NonNull EObject element) {
			assert serializationNode != null;
			serializationNode.serialize(serializationBuilder, element);
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(getName());
			s.append(" -> ");
			boolean isFirst1 = true;
			for (@NonNull XtextAbstractRuleAnalysis baseRuleAnalyses : getBaseRuleAnalysisClosure()) {
				if (!isFirst1) {
					s.append(",");
				}
				s.append(baseRuleAnalyses.getName());
				isFirst1 = false;
			}
			s.append(" <=> ");
			boolean isFirst2 = true;
			for (@NonNull EClassifier eClassifier : eClassifiers) {
				if (!isFirst2) {
					s.append(",");
				}
				s.append(eClassifier.getName());
				isFirst2 = false;
			}
			return s.toString();
		}
	}

	/**
	 * An XtextParserRuleAnalysis provides the extended analysis of an Xtext ParserRule
	 */
	public static class XtextParserRuleAnalysis extends XtextAbstractRuleAnalysis
	{
		private final @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> eFeature2assignmentAnalyses = new HashMap<>();

		public XtextParserRuleAnalysis(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull ParserRule parserRule) {
			super(grammarAnalysis, parserRule);
		}

		public void addAssignmentAnalysis(@NonNull XtextAssignmentAnalysis assignmentAnalysis) {
			EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
			List<@NonNull XtextAssignmentAnalysis> assignmentAnalyses = eFeature2assignmentAnalyses.get(eStructuralFeature);
			if (assignmentAnalyses == null) {
				assignmentAnalyses = new ArrayList<>();
				eFeature2assignmentAnalyses.put(eStructuralFeature, assignmentAnalyses);
			}
			assignmentAnalyses.add(assignmentAnalysis);
		}

		public @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> getEFeature2assignmentAnalyses() {
			return eFeature2assignmentAnalyses;
		}

		public @NonNull ParserRule getParserRule() {
			return (ParserRule) abstractRule;
		}
	}

	/**
	 * An XtextTerminalRuleAnalysis provides the extended analysis of an Xtext TerminalRule
	 */
	public static class XtextTerminalRuleAnalysis extends XtextAbstractRuleAnalysis
	{
		public XtextTerminalRuleAnalysis(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull TerminalRule terminalRule) {
			super(grammarAnalysis, terminalRule);
		}

		public @NonNull TerminalRule getTerminalRule() {
			return (TerminalRule)abstractRule;
		}
	}

	public static class SerializationBuilder
	{
		private static final @NonNull Character SOFT_SPACE = new Character(Character.highSurrogate(' '));

		protected final @NonNull UserModelAnalysis modelAnalysis;
		protected final @NonNull StringBuilder s;
		private int startIndex;
		private @Nullable Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions = null;

		public SerializationBuilder(@NonNull UserModelAnalysis modelAnalysis, @NonNull StringBuilder s) {
			this.modelAnalysis = modelAnalysis;
			this.s = s;
			this.startIndex = s.length();
		}

		public void append(@NonNull String string) {
			s.append(string);
		}

		public void appendSoftSpace() {
			s.append(SOFT_SPACE);
		}

		/**
		 * Return the consumption index of the next feature slot.
		 */
		public int consume(@NonNull EStructuralFeature feature) {
			Map<@NonNull EStructuralFeature, @NonNull Integer> feature2consumptions = this.feature2consumptions;
			if (feature2consumptions == null) {
				this.feature2consumptions = feature2consumptions = new HashMap<>();
			}
			Integer count = feature2consumptions.get(feature);
			if (count == null) {
				feature2consumptions.put(feature, Integer.valueOf(1));
				return 0;
			}
			else {
				int intValue = count.intValue();
				feature2consumptions.put(feature, Integer.valueOf(intValue+1));
				return intValue;
			}
		}

		public void serialize(@NonNull EObject element) {
			AbstractUserElementAnalysis userElementAnalysis = modelAnalysis.getElementAnalysis(element);
			Iterable<@NonNull XtextAbstractRuleAnalysis> productionRuleAnalyses = userElementAnalysis.getProductionRules();
			for (@NonNull XtextAbstractRuleAnalysis productionRuleAnalysis : productionRuleAnalyses) {
				SerializationBuilder nestedSerializationBuilder = productionRuleAnalysis.isCompatible(modelAnalysis, s, element);
				if (nestedSerializationBuilder != null) {
					productionRuleAnalysis.serialize(nestedSerializationBuilder, element);
					return;
				}
			}
			s.append("<<<incompatible '" + element.eClass().getName() + "'>>>");
			// TODO Auto-generated method stub

		}

		public @NonNull String toRenderedString() {
			StringBuilder s = new StringBuilder();
			for (int i = 0; i < this.s.length(); i++) {
				char ch = this.s.charAt(i);
				int length = s.length();
				char prevCh = length <= 0 ? ' ' : s.charAt(length-1);
				switch (prevCh) {
				/*	case -1: {
						if (ch == SOFT_SPACE) {}
						else {
							s.append(ch);
						}
						break;
					} */
					case ' ': {
						if (ch == SOFT_SPACE) {}
						else {
							s.append(ch);
						}
						break;
					}
					case '\n': {
						if (ch == SOFT_SPACE) {}
						else {
							s.append(ch);
						}
						break;
					}
					default: {
						if (ch == SOFT_SPACE) {
							s.append(' ');
						}
						else {
							s.append(ch);
						}
						break;
					}
				}
			}
			return String.valueOf(s);
		}

		@Override
		public @NonNull String toString() {
			return String.valueOf(s.substring(startIndex));
		}
	}

	public static @NonNull EClassifier getClassifier(TypeRef type) {
		return ClassUtil.nonNullState(type.getClassifier());
	}

	public static @NonNull EClassifier getEClassifierScope(@NonNull AbstractElement abstractElement) {
		TypeRef type = null;
		for (EObject eObject = abstractElement, eChild = null; (type == null) && (eObject != null); eChild = eObject, eObject = eObject.eContainer()) {
			if (eObject instanceof ParserRule) {
				type = ((ParserRule)eObject).getType();
			}
			else if (eObject instanceof Group) {
				List<@NonNull AbstractElement> elements = getElements((Group)eObject);
				int index = elements.indexOf(eChild);
				assert index >= 0;
				for (int i = index; --i >= 0; ) {
					AbstractElement element = elements.get(i);
					if (element instanceof Action) {
						type = ((Action)element).getType();
						break;
					}
				}
			}
		}
		if (type != null) {
			return getClassifier(type);
		}
		throw new IllegalStateException();
	}

/*	public static class XtextTermsAnalysis extends XtextSwitch<@NonNull Object>
	{
	//	private final @NonNull Map<@NonNull EStructuralFeature,  @NonNull List<@NonNull XtextAssignmentAnalysis>> feature2assignmentAnalyses;

		public XtextTermsAnalysis(@NonNull XtextAbstractRuleAnalysis ruleAnalysis) {
		//	this.ruleAnalysis = ruleAnalysis;
		//	this.grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
		//	this.feature2assignmentAnalyses = new HashMap<>();
		//	this.userElement2element = new HashMap<>();
		}

	//	private XtextTermsAnalysis(@NonNull XtextTermsAnalysis correlator) {
		//	this.ruleAnalysis = correlator.ruleAnalysis;
		//	this.grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
		//	this.feature2assignmentAnalyses = new HashMap<>(correlator.feature2assignmentAnalyses);
		//	this.userElement2element = new HashMap<>(correlator.userElement2element);
	//	}
	} */

	public static class XtextTermsSwitch extends XtextSwitch<@NonNull SerializationNode>
	{
		protected final @NonNull XtextAbstractRuleAnalysis ruleAnalysis;

		/**
		 * The overall (multi-)grammar analysis.
		 */
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;

		protected final @NonNull NullSerializationNode nullSerializationNode;

//		private final @NonNull Map<@NonNull EObject, @NonNull AbstractElement> userElement2element;

		public XtextTermsSwitch(@NonNull XtextAbstractRuleAnalysis ruleAnalysis) {
			this.ruleAnalysis = ruleAnalysis;
			this.grammarAnalysis = ruleAnalysis.getGrammarAnalysis();
			this.nullSerializationNode = new NullSerializationNode(grammarAnalysis);
		//	this.feature2assignmentAnalyses = new HashMap<>();
		//	this.userElement2element = new HashMap<>();
		}

	/*	private void addCorrelators(@NonNull List<@NonNull XtextTermsAnalysis> correlators, @NonNull Object correlatorOrCorrelators) {
			if (correlatorOrCorrelators instanceof XtextTermsAnalysis) {
				correlators.add((XtextTermsAnalysis)correlatorOrCorrelators);
			}
			else {
				correlators.addAll((Collection<@NonNull XtextTermsAnalysis>)correlatorOrCorrelators);
			}
		} */

/*		protected void accumulateContents(@NonNull List<@NonNull AbstractSerializationNode> contents, @NonNull Object nestedContentOrContents) {
			if (nestedContentOrContents instanceof AbstractSerializationNode) {
				contents.add((AbstractSerializationNode)nestedContentOrContents);
			}
			else {
				@SuppressWarnings("unchecked")
				List<@NonNull AbstractSerializationNode> nestedContents = (List<@NonNull AbstractSerializationNode>)nestedContentOrContents;
				contents.addAll(nestedContents);
			}
		} */

/*		private boolean addContent(@NonNull List<@NonNull AbstractSerializationNode> oldContents, @NonNull AbstractElement newContent) {
			for (@NonNull AbstractSerializationNode oldContent : oldContents) {
				if (oldContent.addAlternative(newContent)) {
					return true;
				}
			}
			return false;
		} */

		@Override
		public @NonNull SerializationNode caseAction(Action object) {
			return nullSerializationNode;
		}

		@Override
		public @NonNull SerializationNode caseAlternatives(Alternatives alternatives) {
			assert alternatives != null;
			List<@NonNull SerializationNode> serializationNodes = new ArrayList<>();
			AlternativeKeywordsSerializationNode alternativeKeywordsSerializationNode = null;
			Map<@NonNull EStructuralFeature, @NonNull AlternativeAssignedRuleCallsSerializationNode> eFeature2ruleCallSerializationNode = null;
			Map<@NonNull EStructuralFeature, @NonNull AlternativeAssignedKeywordsSerializationNode> eFeature2keywordsSerializationNode = null;
		//	boolean hasAlternativeAssignedRuleCalls = false;
			for (@NonNull AbstractElement element : getElements(alternatives)) {
				boolean doSwitchNeeded = true;
				if (element instanceof Keyword) {
					if (alternativeKeywordsSerializationNode == null) {
						alternativeKeywordsSerializationNode = new AlternativeKeywordsSerializationNode(grammarAnalysis);
						serializationNodes.add(alternativeKeywordsSerializationNode);
					}
					alternativeKeywordsSerializationNode.addKeyword((Keyword)element);
					doSwitchNeeded = false;
				}
				else if (element instanceof Assignment) {
					Assignment assignment = (Assignment)element;
					XtextAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
					EStructuralFeature eFeature = assignmentAnalysis.getEStructuralFeature();
					AbstractElement terminal = assignment.getTerminal();
					if (terminal instanceof Keyword) {
						Keyword keyword = (Keyword)terminal;
						if (eFeature2keywordsSerializationNode == null) {
							eFeature2keywordsSerializationNode = new HashMap<>();
						}
						AlternativeAssignedKeywordsSerializationNode serializationNode = eFeature2keywordsSerializationNode.get(eFeature);
						if (serializationNode == null) {
							serializationNode = new AlternativeAssignedKeywordsSerializationNode(grammarAnalysis, eFeature);
							eFeature2keywordsSerializationNode.put(eFeature, serializationNode);
							serializationNodes.add(serializationNode);
						}
						serializationNode.addKeyword(keyword);
						doSwitchNeeded = false;
					}
					else if (terminal instanceof RuleCall) {
						RuleCall ruleCall = (RuleCall)terminal;
						if (eFeature2ruleCallSerializationNode == null) {
							eFeature2ruleCallSerializationNode = new HashMap<>();
						}
						AlternativeAssignedRuleCallsSerializationNode serializationNode = eFeature2ruleCallSerializationNode.get(eFeature);
						if (serializationNode == null) {
							serializationNode = new AlternativeAssignedRuleCallsSerializationNode(grammarAnalysis, eFeature);
							eFeature2ruleCallSerializationNode.put(eFeature, serializationNode);
							serializationNodes.add(serializationNode);
						}
						serializationNode.addRuleAnalysis(grammarAnalysis.getRuleAnalysis(getRule(ruleCall)));
						doSwitchNeeded = false;
					}
				}
				if (doSwitchNeeded) {
					SerializationNode serializationNode = doSwitch(element);
					if (!serializationNode.isNull()) {
						serializationNodes.add(serializationNode);
					}
				/*	boolean merged = false;
					if (nestedContentOrContents instanceof AbstractAssignedSerializationNode) {
						for (@NonNull SerializationNode content : contents) {
							if (content.addAlternative((AssignedSerializationNode)nestedContentOrContents)) {
								merged = true;
								break;
							}
						}
					}
					if (!merged) {
						if (nestedContentOrContents instanceof AbstractSerializationNode) {
							SerializationNode nestedContent = (SerializationNode)nestedContentOrContents;
							if ((contents.size() != 1) || !contents.get(0).addAlternative(nestedContent)) {
								contents.add(nestedContent);		// XXX alternatives case
							}
						}
						else {
							@SuppressWarnings("unchecked")
							List<@NonNull SerializationNode> nestedContents = (List<@NonNull SerializationNode>)nestedContentOrContents;
							if ((contents.size() != 1) || (nestedContents.size() != 1) || !contents.get(0).addAlternative(nestedContents.get(0))) {
								contents.addAll(nestedContents);		// XXX alternatives case
							}
						}
					} */
				}
			}
		/*	String cardinality = alternatives.getCardinality();
			if ((contents.size() > 1) && !"*".equals(cardinality)) {
				return new AlternativesSerializationNode(grammarAnalysis, cardinality, contents);
			}
			if (cardinality != null) {
				for (@NonNull SerializationNode content : contents) {
					content.setCardinality(cardinality);
				}
			} */
			if (serializationNodes.size() <= 0) {
				return nullSerializationNode;
			}
			String cardinality = alternatives.getCardinality();
			if (serializationNodes.size() == 1) {
				if (alternativeKeywordsSerializationNode != null) {
					if (cardinality != null) {
						alternativeKeywordsSerializationNode.setCardinality(cardinality);
					}
					return alternativeKeywordsSerializationNode;
				}
				else if ((eFeature2keywordsSerializationNode != null) && (eFeature2keywordsSerializationNode.size() == 1)) {
					for (@NonNull AlternativeAssignedKeywordsSerializationNode serializationNode : eFeature2keywordsSerializationNode.values()) {		// All one value
						if (cardinality != null) {
							serializationNode.setCardinality(cardinality);
						}
						return serializationNode;
					}
				}
				else if ((eFeature2ruleCallSerializationNode != null) && (eFeature2ruleCallSerializationNode.size() == 1)) {
					for (@NonNull AlternativeAssignedRuleCallsSerializationNode serializationNode : eFeature2ruleCallSerializationNode.values()) {		// All one value
						if (cardinality != null) {
							serializationNode.setCardinality(cardinality);
							}
						return serializationNode;
					}
				}
			}
			return new AlternativesSerializationNode(grammarAnalysis, cardinality, serializationNodes);
		}

		@Override
		public @NonNull SerializationNode caseAssignment(Assignment assignment) {
			assert assignment != null;
			XtextAssignmentAnalysis assignmentAnalysis = grammarAnalysis.getAssignmentAnalysis(assignment);
			EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
			String cardinality = assignment.getCardinality();
			AbstractElement terminal = getTerminal(assignment);
			if (terminal instanceof RuleCall) {
				return new AssignedRuleCallSerializationNode(grammarAnalysis, eStructuralFeature, cardinality, grammarAnalysis.getRuleAnalysis(getRule((RuleCall)terminal)));
			}
			else if (terminal instanceof Keyword) {
				return new AssignedKeywordSerializationNode(grammarAnalysis, eStructuralFeature, cardinality, (Keyword)terminal);
			}
			else if (terminal instanceof Alternatives) {
				Alternatives alternatives = (Alternatives)terminal;
			//	List<@NonNull XtextAbstractContent> contents = new ArrayList<>();
				SerializationNode content = null;
		/*		for (@NonNull AbstractElement alternative : getElements(alternatives)) {
					if (content == null) {
						if (alternative instanceof RuleCall) {
							content = new AssignedRuleCallSerializationNode(grammarAnalysis, eStructuralFeature, cardinality, grammarAnalysis.getRuleAnalysis(getRule((RuleCall)alternative)));
						}
						else if (alternative instanceof Keyword) {
							content = new AssignedKeywordSerializationNode(grammarAnalysis, eStructuralFeature, cardinality, (Keyword)alternative);
						}
						else {
							throw new UnsupportedOperationException("Unsupported Assignment alternative terminal '" + alternative.eClass().getName() + "'");
						}
					//	content = doSwitch(alternative);
					}
					else if (content instanceof AbstractSerializationNode) {
						if (!((AbstractSerializationNode)content).addAlternative(alternative)) {
							content = null;
							break;
						}
					}
				/ *	Object nestedContentOrContents = doSwitch(alternative);
					if (alternative instanceof XtextAbstractContent) {
						addContent(contents, alternative);
					}
					else if (alternative instanceof RuleCall) {
						addContent(contents, alternative);
					}
					else {
						throw new UnsupportedOperationException("Unsupported Assignment alternative terminal '" + alternative.eClass().getName() + "'");
					} * /
				} */
				if (content != null) {
					return content;
				}
			//	return contents;
				return nullSerializationNode;
			}
			else if (terminal instanceof CrossReference) {
				return new AssignedCrossReferenceSerializationNode(grammarAnalysis, eStructuralFeature, cardinality, (CrossReference)terminal);
			}
			else {
				throw new UnsupportedOperationException("Unsupported Assignment terminal '" + terminal.eClass().getName() + "'");
			}
		//	return new XtextAbstractContent(eStructuralFeature);
		}

		@Override
		public @NonNull SerializationNode caseCharacterRange(CharacterRange characterRange) {
			assert characterRange != null;
			return new CharacterRangeSerializationNode(grammarAnalysis, characterRange);
		}

	/*	@Override
		public @NonNull SerializationNode caseCrossReference(CrossReference object) {
			return nullSerializationNode;
		} */

		@Override
		public @NonNull SerializationNode caseGroup(Group group) {
			assert group != null;
			List<@NonNull SerializationNode> serializationNodes = new ArrayList<>();
			for (@NonNull AbstractElement element : getElements(group)) {		// XXX optimize the no alternatives case
				SerializationNode serializationNode = doSwitch(element);
				if (!serializationNode.isNull()) {
					serializationNodes.add(serializationNode);
				}
			}
			return new SequenceSerializationNode(grammarAnalysis, group.getCardinality(), serializationNodes);
		}

		@Override
		public @NonNull SerializationNode caseKeyword(Keyword keyword) {
			assert keyword != null;
			return new KeywordSerializationNode(grammarAnalysis, keyword);
		}

		@Override
		public @NonNull SerializationNode caseNegatedToken(NegatedToken negatedToken) {
			assert negatedToken != null;
			return new NegatedTokenSerializationNode(grammarAnalysis, negatedToken, doSwitch(negatedToken.getTerminal()));
		}

		@Override
		public @NonNull SerializationNode caseRuleCall(RuleCall ruleCall) {
			assert ruleCall != null;
			assert !(ruleCall.eContainer() instanceof Assignment);
			return nullSerializationNode;
		}

/*		@Override
		public @NonNull SerializationNode caseTypeRef(TypeRef object) {
			return nullSerializationNode;
		} */

		@Override
		public @NonNull SerializationNode caseUntilToken(UntilToken untilToken) {
			assert untilToken != null;
			return new UntilTokenSerializationNode(grammarAnalysis, untilToken);
		}

		@Override
		public @NonNull SerializationNode caseWildcard(Wildcard wildcard) {
			assert wildcard != null;
			return new WildcardSerializationNode(grammarAnalysis, wildcard);
		}

		public @NonNull SerializationNode correlate() {
			AbstractElement rootElement = ruleAnalysis.getRule().getAlternatives();
			return doSwitch(rootElement);
		}

		@Override
		public @NonNull SerializationNode defaultCase(EObject object) {
			throw new UnsupportedOperationException("Unsupported '" + object.eClass().getName() + "' in Correlator");
		//	return null;
		}

	/*	@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			List<@NonNull EStructuralFeature> features = new ArrayList<>(feature2assignmentAnalyses.keySet());
			Collections.sort(features, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		//	if (conten)
			boolean isFirst1 = true;
			for (@NonNull EStructuralFeature feature : features) {
				List<@NonNull XtextAssignmentAnalysis> assignmentAnalyses = feature2assignmentAnalyses.get(feature);
				assert assignmentAnalyses != null;
				if (!isFirst1) {
					s.append(",");
				}
				s.append(feature.getName());
				s.append("(");
				boolean isFirst2 = true;
				for (@NonNull XtextAssignmentAnalysis assignmentAnalysis : assignmentAnalyses) {
					assert assignmentAnalyses != null;
					if (!isFirst2) {
						s.append(",");
					}
					s.append(assignmentAnalysis.getCardinality());
					isFirst2 = false;
				}
				s.append(")");
				isFirst1 = false;
			}
			return s.toString();
		}*/
	}


	public static @NonNull EClass getEContainingClass(@NonNull EStructuralFeature eFeature) {
		return ClassUtil.nonNullState(eFeature.getEContainingClass());
	}

	public static @NonNull Grammar getEContainingGrammar(@NonNull EObject eObject) {
		for (EObject eCursor = eObject; (eCursor != null); eCursor = eCursor.eContainer()) {
			if (eCursor instanceof Grammar) {
				return (Grammar)eCursor;
			}
		}
		throw new IllegalStateException();
	}

	public static @NonNull EStructuralFeature getEStructuralFeature(@NonNull EClass eClass, @NonNull String featureName) {
		return ClassUtil.nonNullState(eClass.getEStructuralFeature(featureName));
	}

	public static @NonNull List<@NonNull AbstractElement> getElements(@NonNull Alternatives alternatives) {
		return ClassUtil.nullFree(alternatives.getElements());
	}

	public static @NonNull List<@NonNull AbstractElement> getElements(@NonNull Group group) {
		return ClassUtil.nullFree(group.getElements());
	}

	public static @NonNull String getFeature(@NonNull Assignment assignment) {
		return ClassUtil.nonNullState(assignment.getFeature());
	}

	public static @NonNull String getName(@NonNull AbstractRule abstractRule) {
		return ClassUtil.nonNullState(abstractRule.getName());
	}

	public static @NonNull String getName(@NonNull ENamedElement eNamedElement) {
		return ClassUtil.nonNullState(eNamedElement.getName());
	}

	public static @NonNull AbstractRule getRule(@NonNull RuleCall ruleCall) {
		return ClassUtil.nonNullState(ruleCall.getRule());
	}

	public static @NonNull AbstractElement getTerminal(@NonNull Assignment assignment) {
		return ClassUtil.nonNullState(assignment.getTerminal());
	}

	public static @NonNull TypeRef getType(@NonNull AbstractRule abstractRule) {
		return ClassUtil.nonNullState(abstractRule.getType());
	}

	public static @NonNull TypeRef getType(@NonNull Action action) {
		return ClassUtil.nonNullState(action.getType());
	}

	public static @NonNull String getValue(@NonNull Keyword keyword) {
		return ClassUtil.nonNullState(keyword.getValue());
	}

	/**
	 * The (multi-)grammar model.
	 */
	protected final @NonNull AbstractGrammarResource grammarResource;

	/**
	 * The rule analysis for each rule.
	 */
	private @Nullable Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis = null;

	/**
	 * The assignment analysis for each assignment.
	 */
	private @Nullable Map<@NonNull Assignment, @NonNull XtextAssignmentAnalysis> assignment2assignmentAnalysis = null;

	/**
	 * The possible assignment analyses for containment EReference.
	 */
	private @Nullable Map<@NonNull EReference, @NonNull List<@NonNull XtextAssignmentAnalysis>> containment2assignmentAnalyses = null;

	/**
	 * The possible producing rule analyses for each EClassifier. This analysis excludes overrides.
	 */
	private @Nullable Map<@NonNull EClassifier, List<@NonNull XtextAbstractRuleAnalysis>> eClassifier2ruleAnalyses = null;

	public XtextGrammarAnalysis(@NonNull AbstractGrammarResource grammarResource) {
		this.grammarResource = grammarResource;
	}

	/**
	 * Perform the analysis to determine and populate thae Assignment and Rile analyses.
	 */
	public void analyze() {
		Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls = new HashMap<>();
		Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules = analyzeRuleNames(rule2ruleCalls);
		Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis = createRuleAnalyses(ruleName2rules, rule2ruleCalls);
		this.rule2ruleAnalysis = rule2ruleAnalysis;
		this.assignment2assignmentAnalysis = analyzeAssignments(rule2ruleAnalysis);
		this.containment2assignmentAnalyses = analyzeContainnments(assignment2assignmentAnalysis);
		Iterable<@NonNull XtextAbstractRuleAnalysis> ruleAnalyses = rule2ruleAnalysis.values();
		//
		// Perform the intra rule analysis to determine the locally produced EClassifiers and local base rules.
		//
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			abstractRuleAnalysis.intraAnalyze();
		}
		this.eClassifier2ruleAnalyses = analyzeProductions(rule2ruleAnalysis);
		//
		// Perform the inter rule analysis to determine the base rule closure.
		/*
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : ruleAnalyses) {
			abstractRuleAnalysis.interAnalyze();
		} */
	}

	/**
	 *	Create an assignment analysis for each assignment..
	 */
	protected @NonNull Map<@NonNull Assignment, @NonNull XtextAssignmentAnalysis> analyzeAssignments(
			@NonNull Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis) {
		/**
		 * The assignment analysis for each assignment.
		 */
		Map<@NonNull Assignment, @NonNull XtextAssignmentAnalysis> assignment2assignmentAnalysis = new HashMap<>();
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : rule2ruleAnalysis.values()) {
			AbstractRule abstractRule = abstractRuleAnalysis.getRule();
			for (@NonNull EObject eObject : new TreeIterable(abstractRule, false)) {
				if (eObject instanceof Assignment) {
					Assignment assignment = (Assignment)eObject;
					XtextParserRuleAnalysis parserRuleAnalysis = (XtextParserRuleAnalysis)getRuleAnalysis(assignment);
					XtextAssignmentAnalysis assignmentAnalysis = new XtextAssignmentAnalysis(parserRuleAnalysis, assignment);
					assignment2assignmentAnalysis.put(assignment, assignmentAnalysis);
					parserRuleAnalysis.addAssignmentAnalysis(assignmentAnalysis);
				}
			}
		}
		return assignment2assignmentAnalysis;
	}

	/**
	 *	Identify the assignment analyses that are containments.
	 */
	protected @NonNull Map<@NonNull EReference, @NonNull List<@NonNull XtextAssignmentAnalysis>> analyzeContainnments(
			@NonNull Map<@NonNull Assignment, @NonNull XtextAssignmentAnalysis> assignment2assignmentAnalysis) {
		Map<@NonNull EReference, @NonNull List<@NonNull XtextAssignmentAnalysis>> containment2assignmentAnalyses = new HashMap<>();
		for (@NonNull XtextAssignmentAnalysis assignmentAnalysis : assignment2assignmentAnalysis.values()) {
			EStructuralFeature eFeature = assignmentAnalysis.getEStructuralFeature();
			if (eFeature instanceof EReference) {
				EReference eReference = (EReference)eFeature;
				if (eReference.isContainment()) {
					List<@NonNull XtextAssignmentAnalysis> assignmentAnalyses = containment2assignmentAnalyses.get(eReference);
					if (assignmentAnalyses == null) {
						assignmentAnalyses = new ArrayList<>();
						containment2assignmentAnalyses.put(eReference, assignmentAnalyses);
					}
					assignmentAnalyses.add(assignmentAnalysis);
				}
			}
		}
		return containment2assignmentAnalyses;
	}

	/**
	 *	Identify the production rule(s) for each EClassifier.
	 */
	protected @NonNull Map<@NonNull EClassifier, @NonNull List<@NonNull XtextAbstractRuleAnalysis>> analyzeProductions(
			@NonNull Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis) {
		Map<@NonNull EClassifier, @NonNull List<@NonNull XtextAbstractRuleAnalysis>> eClassifier2ruleAnalyses = new HashMap<>();
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : rule2ruleAnalysis.values()) {
			for (@NonNull EClassifier eClassifier : abstractRuleAnalysis.getEClassifiers()) {
				List<@NonNull XtextAbstractRuleAnalysis> ruleAnalyses = eClassifier2ruleAnalyses.get(eClassifier);
				if (ruleAnalyses == null) {
					ruleAnalyses = new ArrayList<>();
					eClassifier2ruleAnalyses.put(eClassifier, ruleAnalyses);
				}
				ruleAnalyses.add(abstractRuleAnalysis);
			}
		}
		return eClassifier2ruleAnalyses;
	}

	/**
	 *	Return the rules for each rule name and populate the rule2ruleCalls from each rule.
	 */
	protected @NonNull Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> analyzeRuleNames(
			@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules = new HashMap<>();
		for (@NonNull EObject eObject : new TreeIterable(grammarResource)) {
			if (eObject instanceof AbstractRule) {
				AbstractRule abstractRule = (AbstractRule)eObject;
				String ruleName = getName(abstractRule);
				List<@NonNull AbstractRule> rules = ruleName2rules.get(ruleName);
				if (rules == null) {
					rules = new ArrayList<>();
					ruleName2rules.put(ruleName, rules);
				}
				rules.add(abstractRule);
			}
			else if (eObject instanceof RuleCall) {
				RuleCall ruleCall = (RuleCall)eObject;
				AbstractRule rule = getRule(ruleCall);
				List<@NonNull RuleCall> ruleCalls = rule2ruleCalls.get(rule);
				if (ruleCalls == null) {
					ruleCalls = new ArrayList<>();
					rule2ruleCalls.put(rule, ruleCalls);
				}
				ruleCalls.add(ruleCall);
			}
		}
		return ruleName2rules;
	}

	/**
	 *	Create a RuleAnalysis for each distinct name.
	 */
	protected @NonNull Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> createRuleAnalyses(
			@NonNull Map<@NonNull String, @NonNull List<@NonNull AbstractRule>> ruleName2rules,
			@NonNull Map<@NonNull AbstractRule, @NonNull List<@NonNull RuleCall>> rule2ruleCalls) {
		Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis = new HashMap<>();
		List<@NonNull String> ruleNames = new ArrayList<>(ruleName2rules.keySet());
		Collections.sort(ruleNames);
		for (@NonNull String ruleName : ruleNames) {
			List<@NonNull AbstractRule> rules = ruleName2rules.get(ruleName);
			assert rules != null;
			AbstractRule activeRule = null;
			if (rules.size() == 1) {
				activeRule = rules.get(0);
			}
			else {
				for (@NonNull AbstractRule rule : rules) {
					List<@NonNull RuleCall> ruleCalls = rule2ruleCalls.get(rule);
					if (ruleCalls != null) {
						if (activeRule != null) {
							throw new IllegalStateException("Duplicate overridden rule '" + ruleName + "'");
						}
						activeRule = rule;
					}
				}
			}
			if (activeRule == null) {
				throw new IllegalStateException("No unique rule '" + ruleName + "'");
			}
			XtextAbstractRuleAnalysis ruleAnalysis;
			if (activeRule instanceof ParserRule) {
				ruleAnalysis = new XtextParserRuleAnalysis(this, (ParserRule)activeRule); //, activeRuleCalls);
			}
			else if (activeRule instanceof TerminalRule) {
				ruleAnalysis = new XtextTerminalRuleAnalysis(this, (TerminalRule)activeRule); //, activeRuleCalls);
			}
			else {
				throw new UnsupportedOperationException();
			}
			rule2ruleAnalysis.put(activeRule, ruleAnalysis);
		}
		return rule2ruleAnalysis;
	}

	public @NonNull XtextAssignmentAnalysis getAssignmentAnalysis(@NonNull Assignment assignment) {
		assert assignment2assignmentAnalysis != null;
		return ClassUtil.nonNullState(assignment2assignmentAnalysis.get(assignment));
	}

	public @NonNull List<@NonNull XtextAssignmentAnalysis> getAssignmentAnalyses(@NonNull EStructuralFeature eFeature) {
		assert containment2assignmentAnalyses != null;
		return ClassUtil.nonNullState(containment2assignmentAnalyses.get(eFeature));
	}

	public @NonNull List<@NonNull XtextAbstractRuleAnalysis> getProducingRuleAnalyses(@NonNull EClassifier eClassifier) {
		assert eClassifier2ruleAnalyses != null;
		return ClassUtil.nonNullState(eClassifier2ruleAnalyses.get(eClassifier));
	}

	public @NonNull XtextAbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractElement abstractElement) {
		for (EObject eObject = abstractElement; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof AbstractRule) {
				return getRuleAnalysis((AbstractRule)eObject);
			}
		}
		throw new IllegalStateException();
	}

	public @NonNull XtextAbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractRule abstractRule) {
		assert rule2ruleAnalysis != null;
		return ClassUtil.nonNullState(rule2ruleAnalysis.get(abstractRule));
	}

	@Override
	public @NonNull String toString() {
		Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis = this.rule2ruleAnalysis;
		if (rule2ruleAnalysis == null) {
			return "<<not-ready>>";
		}
		StringBuilder s = new StringBuilder();
		s.append("Xtext production rule -> Xtext base rules <=> User EClass - User EStructuralFeatures");
		List<@NonNull XtextAbstractRuleAnalysis> abstractRuleAnalyses = new ArrayList<>(rule2ruleAnalysis.values());
		Collections.sort(abstractRuleAnalyses, NameUtil.NAMEABLE_COMPARATOR);
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : abstractRuleAnalyses) {
			s.append("\n\t");
			s.append(abstractRuleAnalysis);
			if (abstractRuleAnalysis instanceof XtextParserRuleAnalysis) {
				XtextParserRuleAnalysis parserRuleAnalysis = (XtextParserRuleAnalysis)abstractRuleAnalysis;
				s.append(" -");
				Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> eFeature2assignmentAnalyses = parserRuleAnalysis.getEFeature2assignmentAnalyses();
				List<@NonNull EStructuralFeature> eFeatures = new ArrayList<>(eFeature2assignmentAnalyses.keySet());
				Collections.sort(eFeatures, NameUtil.ENAMED_ELEMENT_COMPARATOR);
				boolean isFirstFeature = true;
				for (@NonNull EStructuralFeature eFeature : eFeatures) {
					if (isFirstFeature) {
						s.append(" ");
					}
					else {
						s.append(",");
					}
					List<@NonNull XtextAssignmentAnalysis> assignmentAnalyses = eFeature2assignmentAnalyses.get(eFeature);
					assert assignmentAnalyses != null;
					int size = assignmentAnalyses.size();
					if (size != 1) {
						s.append(size);
						s.append("*");
					}
					s.append(eFeature.getName());
					isFirstFeature = false;
				}
			}
			SerializationNode abstractContent = abstractRuleAnalysis.basicGetContents();
			if (abstractContent != null) {
				s.append("\n");
				StringUtil.appendIndentation(s, abstractContent instanceof CompositeSerializationNode ? 1 : 2, "\t");
				abstractContent.toString(s, 2);
			}
		}
		s.append("\n\nUser EClass <=> Active Xtext production rule(s)");
		Map<@NonNull EClassifier, List<@NonNull XtextAbstractRuleAnalysis>> eClassifier2ruleAnalyses2 = eClassifier2ruleAnalyses;
		assert eClassifier2ruleAnalyses2 != null;
		List<@NonNull EClassifier> eClassifiers2 = new ArrayList<>(eClassifier2ruleAnalyses2.keySet());
		Collections.sort(eClassifiers2, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		for (@NonNull EClassifier eClassifier : eClassifiers2) {
			List<@NonNull XtextAbstractRuleAnalysis> parserRuleAnalyses2 = new ArrayList<>(eClassifier2ruleAnalyses2.get(eClassifier));
		//	assert parserRuleAnalyses2 != null;
			Collections.sort(parserRuleAnalyses2, NameUtil.NAMEABLE_COMPARATOR);
			s.append("\n\t");;
			s.append(eClassifier.getName());
			s.append(" <=>");;
			for (@NonNull XtextAbstractRuleAnalysis parserRuleAnalysis : parserRuleAnalyses2) {
				s.append(" ");;
				s.append(parserRuleAnalysis.getName());;
			}
		}
		return s.toString();
	}
}
