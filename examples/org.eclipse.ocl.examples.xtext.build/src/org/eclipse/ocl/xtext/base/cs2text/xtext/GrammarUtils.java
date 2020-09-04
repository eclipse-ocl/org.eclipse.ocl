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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.AbstractRule;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.CharacterRange;
import org.eclipse.xtext.CompoundElement;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.Group;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.TypeRef;
import org.eclipse.xtext.UntilToken;

public class GrammarUtils extends SerializationUtils
{
	public static void appendEStructuralFeatureName(@NonNull StringBuilder s, @NonNull EClass eFeatureScope, @NonNull EStructuralFeature eStructuralFeature) {
	//	if (eFeatureScope != getEContainingClass(eStructuralFeature)) {
	//		s.append(XtextGrammarUtil.getName(eFeatureScope));
	//		s.append("::");
	//	}
		s.append(GrammarUtils.getName(eStructuralFeature));
	}

	public static void appendEStructuralFeatureName(@NonNull StringBuilder s, @NonNull AssignmentAnalysis assignmentAnalysis) {
		appendEStructuralFeatureName(s, assignmentAnalysis.getEClass(), assignmentAnalysis.getEStructuralFeature());
	}

	public static @NonNull AbstractElement getAlternatives(@NonNull AbstractRule abstractRule) {
		return ClassUtil.nonNullState(abstractRule.getAlternatives());
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

	public static @NonNull List<@NonNull AbstractElement> getElements(@NonNull CompoundElement compoundElement) {
		return ClassUtil.nullFree(compoundElement.getElements());
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

	public static @NonNull Resource getResource(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eResource());
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

	/**
	 * Return true if the hierachical name of thisEClassifier is the same as thatEClassifier.
	 *
	 * This avoids problems from metamodel schizophrenia resulting from too many ResourceSets and inconsistent referencing.
	 */
	public static boolean isEqual(@Nullable EClassifier thisEClassifier, @Nullable EClassifier thatEClassifier) {
		if (thisEClassifier == thatEClassifier) {
			return true;
		}
		if ((thisEClassifier == null) || (thatEClassifier == null)) {
			return false;
		}
		if (thisEClassifier.eClass() != thatEClassifier.eClass()) {
			return false;
		}
		if (!ClassUtil.safeEquals(thisEClassifier.getName(), thatEClassifier.getName())) {
			return false;
		}
		return isEqual(thisEClassifier.getEPackage(), thatEClassifier.getEPackage());
	}

	/**
	 * Return true if the hierachical name of thisEPackage is the same as thatEPackage.
	 *
	 * This avoids problems from metamodel schizophrenia resulting from too many ResourceSets and inconsistent referencing.
	 */
	public static boolean isEqual(@Nullable EPackage thisEPackage, @Nullable EPackage thatEPackage) {
		if (thisEPackage == thatEPackage) {
			return true;
		}
		if ((thisEPackage == null) || (thatEPackage == null)) {
			return false;
		}
		if (thisEPackage.eClass() != thatEPackage.eClass()) {
			return false;
		}
		if (!ClassUtil.safeEquals(thisEPackage.getName(), thatEPackage.getName())) {
			return false;
		}
		EPackage thisESuperPackage = thisEPackage.getESuperPackage();
		EPackage thatESuperPackage = thatEPackage.getESuperPackage();
		if ((thisESuperPackage == null) && (thatESuperPackage == null)) {
			return ClassUtil.safeEquals(thisEPackage.getNsURI(), thatEPackage.getNsURI());
		}
		else {
			return isEqual(thisESuperPackage, thatESuperPackage);
		}
	}

	/**
	 * Return true if the hierachical name of thisEStructuralFeature is the same as thatEStructuralFeature.
	 *
	 * This avoids problems from metamodel schizophrenia resulting from too many ResourceSets and inconsistent referencing.
	 */
	public static boolean isEqual(@Nullable EStructuralFeature thisEStructuralFeature, @Nullable EStructuralFeature thatEStructuralFeature) {
		if (thisEStructuralFeature == thatEStructuralFeature) {
			return true;
		}
		if ((thisEStructuralFeature == null) || (thatEStructuralFeature == null)) {
			return false;
		}
		if (thisEStructuralFeature.eClass() != thatEStructuralFeature.eClass()) {
			return false;
		}
		if (!ClassUtil.safeEquals(thisEStructuralFeature.getName(), thatEStructuralFeature.getName())) {
			return false;
		}
		return isEqual(thisEStructuralFeature.getEContainingClass(), thatEStructuralFeature.getEContainingClass());
	}

	public static boolean isSuperTypeOf(@Nullable EClass thisEClass, @NonNull EClass thatEClass) {
		if (isEqual(thisEClass, thatEClass)) {
			return true;
		}
		for (EClass thatSuperEClass : thatEClass.getEAllSuperTypes()) {
			if (isEqual(thisEClass, thatSuperEClass)) {
				return true;
			}
		}
		return false;
	}
}
