/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.build.analysis;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.build.elements.AssignedCrossReferenceSerializationNode;
import org.eclipse.ocl.build.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.build.elements.AssignedSerializationNode;
import org.eclipse.ocl.build.elements.SerializationNode;
import org.eclipse.ocl.build.elements.UnassignedKeywordSerializationNode;
import org.eclipse.ocl.xtext.idioms.AnyAssignmentLocator;
import org.eclipse.ocl.xtext.idioms.AnyElementLocator;
import org.eclipse.ocl.xtext.idioms.AssignmentLocator;
import org.eclipse.ocl.xtext.idioms.FinalLocator;
import org.eclipse.ocl.xtext.idioms.KeywordLocator;
import org.eclipse.ocl.xtext.idioms.Locator;
import org.eclipse.ocl.xtext.idioms.LocatorDeclaration;
import org.eclipse.ocl.xtext.idioms.ReturnsLocator;
import org.eclipse.ocl.xtext.idioms.RuleLocator;
import org.eclipse.ocl.xtext.idioms.util.IdiomsSwitch;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;

/**
 * The LocatorHelper defines the interface for matching of a
 * SubIdiom's Locator and a SerializationNode of the SerialzationRule.
 */
public interface LocatorHelper
{
	/**
	 * The LocatorSwitch returns a LocatorHelper instance that can perform matching of a
	 * SubIdiom's Locator
	 */
	public static class LocatorSwitch extends IdiomsSwitch<@Nullable LocatorHelper>
	{
		public static final @NonNull LocatorSwitch INSTANCE = new LocatorSwitch();

		@Override
		public @Nullable LocatorHelper caseAnyAssignmentLocator(AnyAssignmentLocator anyAssignmentLocator) {
			return AnyAssignmentLocatorHelper.INSTANCE;
		}

		@Override
		public @Nullable LocatorHelper caseAnyElementLocator(AnyElementLocator anyElementLocator) {
			return AnyElementLocatorHelper.INSTANCE;
		}

		@Override
		public @Nullable LocatorHelper caseAssignmentLocator(AssignmentLocator assignmentLocator) {
			return AssignmentLocatorHelper.INSTANCE;
		}

		@Override
		public @Nullable LocatorHelper caseFinalLocator(FinalLocator finalLocator) {
			return FinalLocatorHelper.INSTANCE;
		}

		@Override
		public @Nullable LocatorHelper caseKeywordLocator(KeywordLocator keywordLocator) {
			return KeywordLocatorHelper.INSTANCE;
		}

		@Override
		public @Nullable LocatorHelper caseLocatorDeclaration(LocatorDeclaration locatorDeclaration) {
			return doSwitch(locatorDeclaration.getOwnedLocator());
		}

		@Override
		public @Nullable LocatorHelper caseReturnsLocator(ReturnsLocator returnsLocator) {
			return ReturnsLocatorHelper.INSTANCE;
		}

		@Override
		public @Nullable LocatorHelper caseRuleLocator(RuleLocator ruleLocator) {
			return RuleLocatorHelper.INSTANCE;
		}

		@Override
		public @Nullable LocatorHelper defaultCase(EObject object) {
			throw new UnsupportedOperationException("Missing " + getClass().getName() + " support for " + object.eClass().getName());
		}
	}

	public static abstract class AbstractLocatorHelper implements LocatorHelper
	{
		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode, @NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			return matches(locator, serializationNode);
		}
	}

	public static class AnyAssignmentLocatorHelper extends AbstractLocatorHelper
	{
		public static final @NonNull AnyAssignmentLocatorHelper INSTANCE = new AnyAssignmentLocatorHelper();

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull AbstractNonTerminalRuleAnalysis nonTerminalRuleAnalysis) {
			if (grammarElement instanceof Assignment) {
				return true;
			}
			return false;
		}

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
			if (serializationNode instanceof AssignedSerializationNode) {
				return true;
			}
			return false;
		}
	}

	public static class AnyElementLocatorHelper extends AbstractLocatorHelper
	{
		public static final @NonNull AnyElementLocatorHelper INSTANCE = new AnyElementLocatorHelper();

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull AbstractNonTerminalRuleAnalysis nonTerminalRuleAnalysis) {
			return true;
		}

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
			return true;
		}
	}

	public static class AssignmentLocatorHelper extends AbstractLocatorHelper
	{
		public static final @NonNull AssignmentLocatorHelper INSTANCE = new AssignmentLocatorHelper();

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull AbstractNonTerminalRuleAnalysis nonTerminalRuleAnalysis) {
			if (grammarElement instanceof Assignment) {
				AssignmentAnalysis assignmentAnalysis = nonTerminalRuleAnalysis.getGrammarAnalysis().getAssignmentAnalysis((Assignment)grammarElement);
				AssignmentLocator assignmentLocator = (AssignmentLocator)locator;
				EStructuralFeature assignedEStructuralFeature = assignmentAnalysis.getEStructuralFeature();
				return AnalysisUtils.isEqual(assignmentLocator.getEStructuralFeature(), assignedEStructuralFeature);
			}
			return false;
		}

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
			if (serializationNode instanceof AssignedSerializationNode) {
				AssignmentLocator assignmentLocator = (AssignmentLocator)locator;
				EStructuralFeature assignedEStructuralFeature = ((AssignedSerializationNode)serializationNode).getEStructuralFeature();
				return AnalysisUtils.isEqual(assignmentLocator.getEStructuralFeature(), assignedEStructuralFeature);
			}
			return false;
		}
	}

	public static class FinalLocatorHelper extends AbstractLocatorHelper
	{
		public static final @NonNull FinalLocatorHelper INSTANCE = new FinalLocatorHelper();

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull AbstractNonTerminalRuleAnalysis nonTerminalRuleAnalysis) {
			if (grammarElement instanceof Assignment) {
				Assignment assignment = (Assignment)grammarElement;
				if (assignment.getTerminal() instanceof CrossReference) {
					return true;
				}
				AssignmentAnalysis assignmentAnalysis = nonTerminalRuleAnalysis.getGrammarAnalysis().getAssignmentAnalysis(assignment);
				EStructuralFeature eStructuralFeature = assignmentAnalysis.getEStructuralFeature();
				if (eStructuralFeature instanceof EAttribute) {
					return true;
				}
			}
			else if (grammarElement instanceof Keyword) {
				return true;
			}
			return false;
		}

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
			if (serializationNode instanceof AssignedCrossReferenceSerializationNode) {
				return true;
			}
			else if (serializationNode instanceof UnassignedKeywordSerializationNode) {
				return true;
			}
			else if (serializationNode instanceof AssignedSerializationNode) {
				AssignedSerializationNode assignedSerializationNode = (AssignedSerializationNode)serializationNode;
				return assignedSerializationNode.getEStructuralFeature() instanceof EAttribute;
			}
			return false;
		}
	}

	public static class KeywordLocatorHelper extends AbstractLocatorHelper
	{
		public static final @NonNull KeywordLocatorHelper INSTANCE = new KeywordLocatorHelper();

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull AbstractNonTerminalRuleAnalysis nonTerminalRuleAnalysis) {
			String value = null;
			if (grammarElement instanceof Assignment) {
				AbstractElement terminal = ((Assignment)grammarElement).getTerminal();
				if (terminal instanceof Keyword) {
					value = ((Keyword)terminal).getValue();
				}
			}
			else if (grammarElement instanceof Keyword) {
				value = ((Keyword)grammarElement).getValue();
			}
			KeywordLocator keywordLocator = (KeywordLocator)locator;
			String string = keywordLocator.getString();
			if (!string.equals(value)) {
				return false;
			}
			return true;
		}

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode) {
			String value = null;
			if (serializationNode instanceof AssignedKeywordSerializationNode) {
				value = ((AssignedKeywordSerializationNode)serializationNode).getValue();
			}
			else if (serializationNode instanceof UnassignedKeywordSerializationNode) {
				value = ((UnassignedKeywordSerializationNode)serializationNode).getValue();
			}
			KeywordLocator keywordLocator = (KeywordLocator)locator;
			String string = keywordLocator.getString();
			if (!string.equals(value)) {
				return false;
			}
			return true;
		}
	}

	public static class ReturnsLocatorHelper extends AbstractLocatorHelper
	{
		public static final @NonNull ReturnsLocatorHelper INSTANCE = new ReturnsLocatorHelper();

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull AbstractNonTerminalRuleAnalysis nonTerminalRuleAnalysis) {
			if (grammarElement.eContainer() instanceof ParserRule) {
				ReturnsLocator returnsLocator = (ReturnsLocator)locator;
				EClassifier producedEClass = nonTerminalRuleAnalysis.getReturnedEClassifier();
				EClass eClass = returnsLocator.getEClass();
				if (AnalysisUtils.isSuperTypeOf(eClass, producedEClass)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode, @NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			if (serializationNode == serializationRuleAnalysis.getRootSerializationNode()) {
				ReturnsLocator returnsLocator = (ReturnsLocator)locator;
				EClass producedEClass = serializationRuleAnalysis.getProducedEClass();
				EClass eClass = returnsLocator.getEClass();
				if (AnalysisUtils.isSuperTypeOf(eClass, producedEClass)) {
					return true;
				}
			}
			return false;
		}
	}


	public static class RuleLocatorHelper extends AbstractLocatorHelper
	{
		public static final @NonNull RuleLocatorHelper INSTANCE = new RuleLocatorHelper();

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull AbstractNonTerminalRuleAnalysis nonTerminalRuleAnalysis) {
			if (grammarElement.eContainer() instanceof ParserRule) {
				RuleLocator ruleLocator = (RuleLocator)locator;
				AbstractRule rule = nonTerminalRuleAnalysis.getRule();
				if (rule == ruleLocator.getReferredRule()) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode, @NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			if (serializationNode == serializationRuleAnalysis.getRootSerializationNode()) {
				RuleLocator ruleLocator = (RuleLocator)locator;
				AbstractRule rule = serializationRuleAnalysis.getRuleAnalysis().getRule();
				if (rule == ruleLocator.getReferredRule()) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Return true if locator matches grammarElement on the context of a nonTerminalRuleAnalysis.
	 */
	boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull AbstractNonTerminalRuleAnalysis nonTerminalRuleAnalysis);

	/**
	 * Return true if locator matches serializationNode.
	 *
	 * This variant without a serializationRuleAnalysis argument throws an UnsupportedOperationException
	 * for non-compounded locators that need a context.
	 */
	boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode);

	/**
	 * Return true if locator matches serializationNode on the context of a serializationRuleAnalysis.
	 */
	boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode, @NonNull SerializationRuleAnalysis serializationRuleAnalysis);
}