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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.xtext.base.utilities.XtextGrammarAnalysis.XtextAssignmentAnalysis;
import org.eclipse.ocl.xtext.base.utilities.XtextGrammarAnalysis.XtextParserRuleAnalysis;
import org.eclipse.xtext.Assignment;

/**
 * The UserModelAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element.
 */
public class UserModelAnalysis
{
	/**
	 * An AbstractUserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
	 * that can produce and assign a user model element.
	 */
	public static abstract class AbstractUserElementAnalysis implements Nameable
	{
		private static int count = 0;

		protected final @NonNull UserModelAnalysis modelAnalysis;
		protected final @NonNull EObject element;
		private final @NonNull String name;

		public AbstractUserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
			this.modelAnalysis = modelAnalysis;
			this.element = element;
			this.name = element.eClass().getName() + "@" + ++count;
		}

		public @NonNull EObject getElement() {
			return element;
		}

		public @NonNull XtextGrammarAnalysis getGrammarAnalysis() {
			return modelAnalysis.getGrammarAnalysis();
		}

		@Override
		public @NonNull String getName() {
			return name;
		}

		protected abstract boolean hasCompatibleContainmentHierarchy();
	}

	/**
	 * A RootUserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
	 * that can produce and assign a user model element that has no container.
	 */
	public static class RootUserElementAnalysis extends AbstractUserElementAnalysis
	{
		private @NonNull List<@NonNull XtextParserRuleAnalysis> productionRuleAnalyses;

		public RootUserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
			super(modelAnalysis, element);
			assert element.eContainer() == null;
			EClass targetEClass = eClass(element);
			XtextGrammarAnalysis grammarAnalysis = getGrammarAnalysis();
			this.productionRuleAnalyses = grammarAnalysis.getParserRuleAnalyses(targetEClass);
		}

		@Override
		protected boolean hasCompatibleContainmentHierarchy() {
			return true;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(getName());
			s.append(" <=>");
			boolean isMany = productionRuleAnalyses.size() > 1;
			for (@NonNull XtextParserRuleAnalysis productionRuleAnalysis : productionRuleAnalyses) {
				if (isMany) {
					s.append("\n\t\t");
				}
				else {
					s.append(" ");
				}
				s.append(productionRuleAnalysis.getName());
			}
			return s.toString();
		}
	}

	/**
	 * A UserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
	 * that can produce and assign a user model element that has a container.
	 */
	public static class UserElementAnalysis extends AbstractUserElementAnalysis
	{
		protected final @NonNull EStructuralFeature eContainingFeature;
		protected final @NonNull AbstractUserElementAnalysis containingElementAnalysis;
		private @Nullable Map<@NonNull XtextParserRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> productionRuleAnalysis2containingAssignmentAnalyses = null;

		public UserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
			super(modelAnalysis, element);
			assert element.eContainer() != null;
			this.eContainingFeature = eContainingFeature(element);
			this.containingElementAnalysis = modelAnalysis.getElementAnalysis(eContainer(element));
			this.productionRuleAnalysis2containingAssignmentAnalyses = analyzeProduction();
		}

		/**
		 * Determine the rules able to produce this element and the containing assignments by which it can be contained.
		 */
		private @NonNull Map<@NonNull XtextParserRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> analyzeProduction() {
			EClass targetEClass = eClass(element);
			Map<@NonNull XtextParserRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> parserRuleAnalysis2assignmentAnalyses = new HashMap<>();
			XtextGrammarAnalysis grammarAnalysis = getGrammarAnalysis();
			Iterable<@NonNull XtextParserRuleAnalysis> targetRuleAnalysisCandidates = grammarAnalysis.getParserRuleAnalyses(targetEClass);
			Iterable<@NonNull XtextAssignmentAnalysis> containingAssignmentAnalysisCandidates = grammarAnalysis.getAssignmentAnalyses(eContainingFeature);
			for (@NonNull XtextParserRuleAnalysis targetRuleAnalysisCandidate : targetRuleAnalysisCandidates) {
				for (@NonNull XtextAssignmentAnalysis containingAssignmentAnalysisCandidate : containingAssignmentAnalysisCandidates) {
					if (containingAssignmentAnalysisCandidate.isAssignableAsTarget(targetRuleAnalysisCandidate)) {
					//	if (isCompatibleContainmentHierarchy(containingAssignmentAnalysisCandidate)) {
							List<@NonNull XtextAssignmentAnalysis> containingAssignmentAnalyses = parserRuleAnalysis2assignmentAnalyses.get(targetRuleAnalysisCandidate);
							if (containingAssignmentAnalyses == null) {
								containingAssignmentAnalyses = new ArrayList<>();
								parserRuleAnalysis2assignmentAnalyses.put(targetRuleAnalysisCandidate, containingAssignmentAnalyses);
							}
							containingAssignmentAnalyses.add(containingAssignmentAnalysisCandidate);
					//	}
					}
				}
			}
			return parserRuleAnalysis2assignmentAnalyses;
		}

		public @NonNull Map<@NonNull XtextParserRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> getParserRuleAnalysis2assignmentAnalyses() {
			return ClassUtil.nonNullState(productionRuleAnalysis2containingAssignmentAnalyses);
		}

		@Override
		protected boolean hasCompatibleContainmentHierarchy() {
			return getParserRuleAnalysis2assignmentAnalyses().size() > 0;
		}

	/*	private boolean isCompatibleContainmentHierarchy(@NonNull XtextAssignmentAnalysis assignmentAnalysis) {
			EObject eContainer = containingElementAnalysis.getElement();
			EClass eContainingClass = assignmentAnalysis.getEContainingClass();
			if (!eContainingClass.isInstance(eContainer)) {
				return false;
			}
			assignmentAnalysis.getEStructuralFeature();
			return containingElementAnalysis.hasCompatibleContainmentHierarchy();
		} */

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(getName());
			s.append(" <=>");
			Map<@NonNull XtextParserRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> productionRuleAnalysis2containingAssignmentAnalyses2 = productionRuleAnalysis2containingAssignmentAnalyses;
			if (productionRuleAnalysis2containingAssignmentAnalyses2 != null) {
				List<@NonNull XtextParserRuleAnalysis> productionRuleAnalyses = new ArrayList<>(productionRuleAnalysis2containingAssignmentAnalyses2.keySet());
				Collections.sort(productionRuleAnalyses, NameUtil.NAMEABLE_COMPARATOR);
				boolean isMany1 = productionRuleAnalyses.size() > 1;
				for (@NonNull XtextParserRuleAnalysis productionRuleAnalysis : productionRuleAnalyses) {
					s.append(isMany1 ? "\n\t\t" : " ");
					s.append(productionRuleAnalysis.getName());
					s.append("-");
					List<@NonNull XtextAssignmentAnalysis> containingAssignmentAnalyses = productionRuleAnalysis2containingAssignmentAnalyses2.get(productionRuleAnalysis);
					assert containingAssignmentAnalyses != null;
					boolean isMany2 = containingAssignmentAnalyses.size() > 1;
					if (isMany2) {
						s.append("{");
					}
					boolean isFirst2 = true;
					for (@NonNull XtextAssignmentAnalysis containingAssignmentAnalysis : containingAssignmentAnalyses) {
						if (!isFirst2) {
							s.append(",");
						}
						s.append(containingAssignmentAnalysis.getEStructuralFeature().getName());
						isFirst2 = false;
					}
					if (isMany2) {
						s.append("}");
					}
				}
			}
			return s.toString();
		}
	}

	public static @NonNull EClass eClass(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eClass());
	}

	public static @NonNull EStructuralFeature eContainingFeature(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eContainingFeature());
	}

	public static @NonNull EObject eContainer(@NonNull EObject eObject) {
		return ClassUtil.nonNullState(eObject.eContainer());
	}

	/**
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull XtextGrammarAnalysis grammarAnalysis;

	private @NonNull Map<@NonNull EObject, @NonNull List<@NonNull Assignment>> modelObject2assignments = new HashMap<>();
	private @NonNull Map<@NonNull EObject, @NonNull AbstractUserElementAnalysis> element2elementAnalysis = new HashMap<>();

	public UserModelAnalysis(@NonNull XtextGrammarAnalysis grammarAnalysis) {
		this.grammarAnalysis = grammarAnalysis;
	}

	public void analyze(@NonNull EObject model) {
		assert model.eContainer() == null;
		element2elementAnalysis.put(model, new RootUserElementAnalysis(this, model));
		List<@NonNull UserElementAnalysis> unresolvedModelObjects = new ArrayList<>();
		for (@NonNull EObject eObject : new TreeIterable(model, false)) {
			UserElementAnalysis elementAnalysis = new UserElementAnalysis(this, eObject);
			element2elementAnalysis.put(eObject, elementAnalysis);
			if (elementAnalysis.getParserRuleAnalysis2assignmentAnalyses().size() > 1) {
				unresolvedModelObjects.add(elementAnalysis);
			}
		}
		for (@NonNull UserElementAnalysis elementAnalysis : unresolvedModelObjects) {
			EObject eObject = elementAnalysis.getElement();
			EStructuralFeature eContainingFeature1 = eObject.eContainingFeature();
			List<@NonNull Assignment> assignments1 = modelObject2assignments.get(eObject);
		/*	assert assignments1 != null;
			for (EObject eContainer = eObject; (eContainer = eContainer.eContainer()) != null; ) {
				EStructuralFeature eContainingFeature2 = eContainer.eContainingFeature();
				List<@NonNull Assignment> assignments2 = modelObject2assignments.get(eContainer);
				assert assignments2 != null;
			} */
		}
	}

//	public @NonNull List<@NonNull XtextAssignmentAnalysis> getAssignmentAnalyses(@NonNull EStructuralFeature eFeature) {
//		return ClassUtil.nonNullState(containment2assignmentAnalyses.get(eFeature));
//	}

	public @NonNull AbstractUserElementAnalysis getElementAnalysis(@NonNull EObject element) {
		return ClassUtil.nonNullState(element2elementAnalysis.get(element));
	}

	public @NonNull XtextGrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append("User object <=> Xtext containing assignment : Xtext production rule\n");
		List<@NonNull AbstractUserElementAnalysis> elementAnalyses = new ArrayList<>(element2elementAnalysis.values());
		Collections.sort(elementAnalyses, NameUtil.NAMEABLE_COMPARATOR);
		boolean isFirst = true;
		for (@NonNull AbstractUserElementAnalysis elementAnalysis : elementAnalyses) {
			if (!isFirst) {
				s.append("\n");
			}
			s.append("\t");
			s.append(elementAnalysis);
			isFirst = false;
		}
		return s.toString();
	}
}
