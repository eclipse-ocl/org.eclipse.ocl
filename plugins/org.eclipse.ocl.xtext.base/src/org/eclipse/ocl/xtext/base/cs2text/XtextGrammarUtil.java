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
package org.eclipse.ocl.xtext.base.cs2text;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
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
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.UntilToken;

public class XtextGrammarUtil
{
	public static void appendCardinality(@NonNull StringBuilder s, int lowerBound, int upperBound) {
		s.append("[");
		if (upperBound < 0) {
			s.append(lowerBound != 0 ? "+" : "*");
		}
		else if (upperBound == 1) {
			s.append(lowerBound != 0 ? "1" : "?");
		}
		else if (upperBound == lowerBound) {
			s.append(Integer.toString(lowerBound));
		}
		else {
			s.append(lowerBound + ".." + upperBound);
		}
		s.append("]");
	}

	public static void appendEStructuralFeatureName(@NonNull StringBuilder s, @NonNull EClass eFeatureScope, @NonNull EStructuralFeature eStructuralFeature) {
		if (eFeatureScope != getEContainingClass(eStructuralFeature)) {
			s.append(XtextGrammarUtil.getName(eFeatureScope));
			s.append("::");
		}
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
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
			else if ((eObject instanceof Action) && (((Action)eObject).getFeature() != null))  {
				type = ((Action)eObject).getType();
				break;
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

	public static @NonNull String getFeature(@NonNull Action action) {
		return ClassUtil.nonNullState(action.getFeature());
	}

	public static @NonNull String getFeature(@NonNull Assignment assignment) {
		return ClassUtil.nonNullState(assignment.getFeature());
	}

	public static @NonNull Keyword getLeft(@NonNull CharacterRange characterRange) {
		return ClassUtil.nonNullState(characterRange.getLeft());
	}

	public static @NonNull String getName(@NonNull AbstractRule abstractRule) {
		return ClassUtil.nonNullState(abstractRule.getName());
	}

	public static @NonNull String getName(@NonNull ENamedElement eNamedElement) {
		return ClassUtil.nonNullState(eNamedElement.getName());
	}

	public static @NonNull Keyword getRight(@NonNull CharacterRange characterRange) {
		return ClassUtil.nonNullState(characterRange.getRight());
	}

	public static @NonNull AbstractRule getRule(@NonNull RuleCall ruleCall) {
		return ClassUtil.nonNullState(ruleCall.getRule());
	}

	public static @NonNull AbstractElement getTerminal(@NonNull Assignment assignment) {
		return ClassUtil.nonNullState(assignment.getTerminal());
	}

	public static @NonNull AbstractElement getTerminal(@NonNull CrossReference crossReference) {
		return ClassUtil.nonNullState(crossReference.getTerminal());
	}

	public static @NonNull AbstractElement getTerminal(@NonNull UntilToken untilToken) {
		return ClassUtil.nonNullState(untilToken.getTerminal());
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
}
