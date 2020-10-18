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
package org.eclipse.ocl.examples.xtext.build.analysis;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedCrossReferenceSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedKeywordSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.AssignedSerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.SerializationNode;
import org.eclipse.ocl.examples.xtext.build.elements.UnassignedKeywordSerializationNode;
import org.eclipse.ocl.examples.xtext.idioms.AnyAssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.AnyElementLocator;
import org.eclipse.ocl.examples.xtext.idioms.AssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.FinalLocator;
import org.eclipse.ocl.examples.xtext.idioms.KeywordLocator;
import org.eclipse.ocl.examples.xtext.idioms.Locator;
import org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.ReturnsLocator;
import org.eclipse.ocl.examples.xtext.idioms.util.IdiomsSwitch;
import org.eclipse.xtext.AbstractElement;
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
		public @Nullable LocatorHelper caseReturnsLocator(ReturnsLocator producedEClassLocator) {
			return ReturnsLocatorHelper.INSTANCE;
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
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull ParserRuleAnalysis parserRuleAnalysis) {
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
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull ParserRuleAnalysis parserRuleAnalysis) {
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
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull ParserRuleAnalysis parserRuleAnalysis) {
			if (grammarElement instanceof Assignment) {
				AssignmentAnalysis assignmentAnalysis = parserRuleAnalysis.getGrammarAnalysis().getAssignmentAnalysis((Assignment)grammarElement);
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
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull ParserRuleAnalysis parserRuleAnalysis) {
			if (grammarElement instanceof Assignment) {
				Assignment assignment = (Assignment)grammarElement;
				if (assignment.getTerminal() instanceof CrossReference) {
					return true;
				}
				AssignmentAnalysis assignmentAnalysis = parserRuleAnalysis.getGrammarAnalysis().getAssignmentAnalysis(assignment);
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
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull ParserRuleAnalysis parserRuleAnalysis) {
			String value = null;
			if (grammarElement instanceof Keyword) {
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
		public boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull ParserRuleAnalysis parserRuleAnalysis) {
			if (grammarElement.eContainer() instanceof ParserRule) {
				ReturnsLocator producedEClassLocator = (ReturnsLocator)locator;
				EClass producedEClass = parserRuleAnalysis.getReturnedEClass();
				EClass eClass = producedEClassLocator.getEClass();
				if (AnalysisUtils.isSuperTypeOf(eClass, producedEClass)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public boolean matches(@NonNull Locator locator, @NonNull SerializationNode serializationNode, @NonNull SerializationRuleAnalysis serializationRuleAnalysis) {
			if (serializationNode == serializationRuleAnalysis.getRootSerializationNode()) {
				ReturnsLocator producedEClassLocator = (ReturnsLocator)locator;
				EClass producedEClass = serializationRuleAnalysis.getProducedEClass();
				EClass eClass = producedEClassLocator.getEClass();
				if (AnalysisUtils.isSuperTypeOf(eClass, producedEClass)) {
					return true;
				}
			}
			return false;
		}
	}

	/**
	 * Return true if locator matches grammarElement on the context of a parserRuleAnalysis.
	 */
	boolean matches(@NonNull Locator locator, @NonNull AbstractElement grammarElement, @NonNull ParserRuleAnalysis parserRuleAnalysis);

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