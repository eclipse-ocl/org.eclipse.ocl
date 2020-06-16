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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.UniqueList;
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
import org.eclipse.xtext.ReferencedMetamodel;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TerminalRule;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.UntilToken;
import org.eclipse.xtext.Wildcard;
import org.eclipse.xtext.util.XtextSwitch;

public class XtextGrammarAnalysis
{
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

		public @NonNull EClass getEContainingClass() {
			return XtextGrammarAnalysis.getEContainingClass(eFeature);
		}

		public @NonNull EStructuralFeature getEStructuralFeature() {
			return eFeature;
		}

		@Override
		public @NonNull String getName() {
			return sourceRuleAnalysis.getName() + "-" + eFeature.getName();
		}

		public @NonNull XtextParserRuleAnalysis getSourceRuleAnalysis() {
			return sourceRuleAnalysis;
		}

		/**
		 * Return true if sourceRuleAnalysis produces an acceptable result for use as the source of this assignment.
		 */
		public boolean isAssignableToAsSource(@NonNull XtextParserRuleAnalysis sourceRuleAnalysis) {
			if (this.sourceRuleAnalysis == sourceRuleAnalysis) {
				return true;
			}
			List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses = sourceRuleAnalysis.getBaseRuleAnalyses();
			if ((baseRuleAnalyses != null) && baseRuleAnalyses.contains(sourceRuleAnalysis)) {
				return true;
			}
			return false;
		}

		/**
		 * Return true if targetRuleAnalysis produces an acceptable result for use as the target of this assignment.
		 */
		public boolean isAssignableAsTarget(@NonNull XtextParserRuleAnalysis targetRuleAnalysis) {
			if (targetRuleAnalyses.contains(targetRuleAnalysis)) {
				return true;
			}
			Iterable<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses = targetRuleAnalysis.getBaseRuleAnalyses();
			if (baseRuleAnalyses != null) {
				for (@NonNull XtextAbstractRuleAnalysis ruleAnalysis : baseRuleAnalyses) {
					if (targetRuleAnalyses.contains(ruleAnalysis)) {
						return true;
					}
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

	public static class XtextAbstractRuleAnalysis implements Nameable
	{
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;
		protected final @NonNull AbstractRule abstractRule;
		protected final @NonNull String name;
		protected final @NonNull List<@NonNull EClassifier> eClassifiers = new ArrayList<>();

		/**
		 * RuleAnalyses that this RuleAnalysis may be used as an alternative for.
		 */
		private @Nullable List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses = null;

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
				this.eClassifiers.add(eClassifier);
			}
		}

		protected void analyze() {}

		public @Nullable List<@NonNull XtextAbstractRuleAnalysis> getBaseRuleAnalyses() {
			return baseRuleAnalyses;
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

		public void postAnalyze() {
			List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses2 = baseRuleAnalyses;
			if (baseRuleAnalyses2 != null) {
				UniqueList<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses = new UniqueList<>(baseRuleAnalyses2);
				for (int i = 0; i < baseRuleAnalyses.size(); i++) {
					XtextAbstractRuleAnalysis baseRuleAnalysis = baseRuleAnalyses.get(i);
					List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses3 = baseRuleAnalysis.getBaseRuleAnalyses();
					if (baseRuleAnalyses3 !=  null) {
						baseRuleAnalyses2.addAll(baseRuleAnalyses3);
					}
				}
				this.baseRuleAnalyses = baseRuleAnalyses;
			}
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(getName());
			List<@NonNull XtextAbstractRuleAnalysis> baseRuleAnalyses2 = baseRuleAnalyses;
			if (baseRuleAnalyses2 != null) {
				s.append(" -> ");
				boolean isFirst = true;
				for (@NonNull XtextAbstractRuleAnalysis baseRuleAnalyses : baseRuleAnalyses2) {
					if (!isFirst) {
						s.append(",");
					}
					s.append(baseRuleAnalyses.getName());
					isFirst = false;
				}
			}
			s.append(" <=> ");
			boolean isFirst = true;
			for (@NonNull EClassifier eClassifier : eClassifiers) {
				if (!isFirst) {
					s.append(",");
				}
				s.append(eClassifier.getName());
				isFirst = false;
			}
			return s.toString();
		}
	}

	public static class XtextParserRuleAnalysis extends XtextAbstractRuleAnalysis
	{
		private final @NonNull Map<@NonNull EStructuralFeature, @NonNull List<@NonNull XtextAssignmentAnalysis>> eFeature2assignmentAnalyses = new HashMap<>();

		public XtextParserRuleAnalysis(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull ParserRule parserRule) {
			super(grammarAnalysis, parserRule);
			analyze();
		}

		@Override
		protected void analyze() {
			addProducedTypeRef(getType(abstractRule));
			for (EObject eObject : new TreeIterable(abstractRule, false)) {
				if (eObject instanceof Action) {
					Action action = (Action)eObject;
					if (isFirstResultType(action)) {
						addProducedTypeRef(getType(action));
					}
				}
				else if (eObject instanceof RuleCall) {
					RuleCall ruleCall = (RuleCall)eObject;
					if (isFirstResultType(ruleCall)) {
						AbstractRule rule = XtextGrammarAnalysis.getRule(ruleCall);
						addProducedTypeRef(getType(rule));
					}
				}
			}
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

		/**
		 * Retyrn true if the transitove descendants of element involve a RuleCall.
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
				return false;
			}
			throw new UnsupportedOperationException();
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

	public static class XtextTerminalRuleAnalysis extends XtextAbstractRuleAnalysis
	{
		public XtextTerminalRuleAnalysis(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull TerminalRule terminalRule) {
			super(grammarAnalysis, terminalRule);
			analyze();
		}

		public @NonNull TerminalRule getTerminalRule() {
			return (TerminalRule)abstractRule;
		}
	}

	private class GrammarSwitch extends XtextSwitch<Object>
	{

		@Override
		public Object caseAction(Action object) {
			return this;
		}

		@Override
		public Object caseAlternatives(Alternatives alternatives) {
			assert alternatives != null;
			EObject eContainer = alternatives.eContainer();
			if (eContainer instanceof AbstractRule) {
				AbstractRule callingRule = (AbstractRule)eContainer;
				XtextAbstractRuleAnalysis callingRuleAnalysis = getRuleAnalysis(callingRule);
				for (AbstractElement abstractElement : alternatives.getElements()) {
					if (abstractElement instanceof RuleCall) {
						AbstractRule calledRule = getRule((RuleCall)abstractElement);
						XtextAbstractRuleAnalysis calledRuleAnalysis = getRuleAnalysis(calledRule);
						calledRuleAnalysis.addBaseRuleAnalysis(callingRuleAnalysis);
					}
				}
			}
			return this;
		}

		@Override
		public Object caseAssignment(Assignment assignment) {
			assert assignment != null;
			XtextParserRuleAnalysis parserRuleAnalysis = (XtextParserRuleAnalysis)getRuleAnalysis(assignment);
			XtextAssignmentAnalysis assignmentAnalysis = new XtextAssignmentAnalysis(parserRuleAnalysis, assignment);
			assignment2assignmentAnalysis.put(assignment, assignmentAnalysis);
			parserRuleAnalysis.addAssignmentAnalysis(assignmentAnalysis);
		//	EClassifier eClassifier = getEClassifierScope(assignment);
		//	assert parserRule != null;
		//	EClassifier eClassifier = parserRuleAnalysis.getEClassifier();
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
			return this;
		}

		@Override
		public Object caseCharacterRange(CharacterRange object) {
			return this;
		}

		@Override
		public Object caseCrossReference(CrossReference object) {
			return this;
		}

		@Override
		public Object caseGrammar(Grammar object) {
			return this;
		}

		@Override
		public Object caseGroup(Group object) {
			return this;
		}

		@Override
		public Object caseKeyword(Keyword object) {
			return this;
		}

		@Override
		public Object caseNegatedToken(NegatedToken object) {
			return this;
		}

		@Override
		public Object caseParserRule(ParserRule parserRule) {
			assert parserRule != null;
			XtextParserRuleAnalysis parserRuleAnalysis = (XtextParserRuleAnalysis)getRuleAnalysis(parserRule);
			rule2ruleAnalysis.put(parserRule, parserRuleAnalysis);
			for (@NonNull EClassifier eClassifier : parserRuleAnalysis.getEClassifiers()) {
				List<@NonNull XtextParserRuleAnalysis> parserRuleAnalyses = eClassifier2parserRuleAnalyses.get(eClassifier);
				if (parserRuleAnalyses == null) {
					parserRuleAnalyses = new ArrayList<>();
					eClassifier2parserRuleAnalyses.put(eClassifier, parserRuleAnalyses);
				}
				parserRuleAnalyses.add(parserRuleAnalysis);
			}
			return this;
		}

		@Override
		public Object caseReferencedMetamodel(ReferencedMetamodel referencedMetamodel) {
		//	String alias = referencedMetamodel.getAlias();
		//	EPackage ePackage = referencedMetamodel.getEPackage();
		//	EPackage old = alias2ePackage.put(alias, ePackage);
		//	assert (old == null) || (old == ePackage);
			return this;
		}

		@Override
		public Object caseRuleCall(RuleCall object) {
			return this;
		}

		@Override
		public Object caseTerminalRule(TerminalRule object) {
			return this;
		}

		@Override
		public Object caseTypeRef(TypeRef object) {
			return this;
		}

		@Override
		public Object caseUntilToken(UntilToken object) {
			return this;
		}

		@Override
		public Object caseWildcard(Wildcard object) {
			return this;
		}

		@Override
		public Object defaultCase(EObject object) {
			throw new UnsupportedOperationException("Unsupported '" + object.eClass().getName() + "' in GrammarSwitch");
		//	return null;
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
				List<AbstractElement> elements = ((Group)eObject).getElements();
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

	public static @NonNull Grammar getEContainingGrammar(@NonNull EObject eObject) {
		for (EObject eCursor = eObject; (eCursor != null); eCursor = eCursor.eContainer()) {
			if (eCursor instanceof Grammar) {
				return (Grammar)eCursor;
			}
		}
		throw new IllegalStateException();
	}

	public static @NonNull EClass getEContainingClass(@NonNull EStructuralFeature eFeature) {
		return ClassUtil.nonNullState(eFeature.getEContainingClass());
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

	protected final @NonNull AbstractGrammarResource grammarResource;
	private final @NonNull GrammarSwitch grammarSwitch = new GrammarSwitch();

//	private @NonNull Map<@Nullable String, @NonNull EPackage> alias2ePackage = new HashMap<>();
//	private @NonNull Map<@NonNull ParserRule, @NonNull EClassifier> parserRule2eClassifier = new HashMap<>();
//	private @NonNull Map<@NonNull Assignment, @NonNull EStructuralFeature> assignment2eFeature = new HashMap<>();
	private @NonNull Map<@NonNull EClassifier, List<@NonNull XtextParserRuleAnalysis>> eClassifier2parserRuleAnalyses = new HashMap<>();
	private @NonNull Map<@NonNull EReference, @NonNull List<@NonNull XtextAssignmentAnalysis>> containment2assignmentAnalyses = new HashMap<>();
	private @NonNull Map<@NonNull AbstractRule, @NonNull XtextAbstractRuleAnalysis> rule2ruleAnalysis = new HashMap<>();
	private @NonNull Map<@NonNull Assignment, @NonNull XtextAssignmentAnalysis> assignment2assignmentAnalysis = new HashMap<>();

	public XtextGrammarAnalysis(@NonNull AbstractGrammarResource grammarResource) {
		this.grammarResource = grammarResource;
	}

	public void analyze() {
		for (@NonNull EObject eObject : new TreeIterable(grammarResource)) {
			grammarSwitch.doSwitch(eObject);
		}
	//	for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : rule2ruleAnalysis.values()) {
	//		abstractRuleAnalysis.analyze();
	//	}
		for (@NonNull XtextAbstractRuleAnalysis abstractRuleAnalysis : rule2ruleAnalysis.values()) {
			abstractRuleAnalysis.postAnalyze();
		}
	}

	public @NonNull List<@NonNull XtextAssignmentAnalysis> getAssignmentAnalyses(@NonNull EStructuralFeature eFeature) {
		return ClassUtil.nonNullState(containment2assignmentAnalyses.get(eFeature));
	}

	public @NonNull List<@NonNull XtextParserRuleAnalysis> getParserRuleAnalyses(@NonNull EClassifier eClassifier) {
		return ClassUtil.nonNullState(eClassifier2parserRuleAnalyses.get(eClassifier));
	}

	public @NonNull XtextAbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractElement abstractElement) {
		for (EObject eObject = abstractElement; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof ParserRule) {
				return ClassUtil.nonNullState(rule2ruleAnalysis.get(eObject));
			}
		}
		throw new IllegalStateException();
	}

	public @NonNull XtextAbstractRuleAnalysis getRuleAnalysis(@NonNull AbstractRule abstractRule) {
		XtextAbstractRuleAnalysis ruleAnalysis = rule2ruleAnalysis.get(abstractRule);
		if (ruleAnalysis == null) {
			if (abstractRule instanceof ParserRule) {
				ruleAnalysis = new XtextParserRuleAnalysis(this, (ParserRule)abstractRule);
			}
			else if (abstractRule instanceof TerminalRule) {
				ruleAnalysis = new XtextTerminalRuleAnalysis(this, (TerminalRule)abstractRule);
			}
			else {
				throw new UnsupportedOperationException();
			}
			rule2ruleAnalysis.put(abstractRule, ruleAnalysis);
		}
		return ClassUtil.nonNullState(ruleAnalysis);
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Xtext production rule -> Xtext base rules => User EClass - User EStructuralFeatures");
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
		}
		s.append("\n\nUser EClass <=> Xtext production rule(s)");
		List<@NonNull EClassifier> eClassifiers = new ArrayList<>(eClassifier2parserRuleAnalyses.keySet());
		Collections.sort(eClassifiers, NameUtil.ENAMED_ELEMENT_COMPARATOR);
		for (@NonNull EClassifier eClassifier : eClassifiers) {
			List<@NonNull XtextParserRuleAnalysis> parserRuleAnalyses2 = new ArrayList<>(eClassifier2parserRuleAnalyses.get(eClassifier));
		//	assert parserRuleAnalyses2 != null;
			Collections.sort(parserRuleAnalyses2, NameUtil.NAMEABLE_COMPARATOR);
			s.append("\n\t");;
			s.append(eClassifier.getName());
			s.append(" <=>");;
			for (@NonNull XtextParserRuleAnalysis parserRuleAnalysis : parserRuleAnalyses2) {
				s.append(" ");;
				s.append(parserRuleAnalysis.getName());;
			}
		}
		return s.toString();
	}
}
