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
import org.eclipse.ocl.xtext.base.utilities.XtextGrammarAnalysis.SerializationBuilder;
import org.eclipse.ocl.xtext.base.utilities.XtextGrammarAnalysis.XtextAbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.utilities.XtextGrammarAnalysis.XtextAssignmentAnalysis;
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
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;
		protected final @NonNull EObject element;
		private final @NonNull String name;
//		private @NonNull List<@NonNull XtextTermsAnalysis> correlators = new ArrayList<>();

		public AbstractUserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
			this.modelAnalysis = modelAnalysis;
			this.grammarAnalysis = modelAnalysis.getGrammarAnalysis();
			this.element = element;
			this.name = element.eClass().getName() + "@" + ++count;
		}

//		public void addCorrelation(@NonNull XtextTermsAnalysis correlator) {
//			correlators.add(correlator);
//		}

		public abstract @Nullable AbstractUserElementAnalysis getContainingElementAnalysis();

		public abstract @Nullable EStructuralFeature geteContainingFeature();

		public @NonNull EObject getElement() {
			return element;
		}

		public @NonNull XtextGrammarAnalysis getGrammarAnalysis() {
			return grammarAnalysis;
		}

		@Override
		public @NonNull String getName() {
			return name;
		}

		public abstract @NonNull Iterable<@NonNull XtextAbstractRuleAnalysis> getProductionRules();

		protected abstract boolean hasCompatibleContainmentHierarchy();

		/**
		 * Return true if the containing feature is compatible with one of its containing assignments.
		 *
		 * If non-null each compatible assignme nt is assign to its corresponding production rulein ruleAnalysis2assignmentAnalyses.
		 *
		 * Compatbility requires
		 *
		 * The produced rule for this element is assignable to the assignment target's rule.
		 * The produced rule for the container of this element is assignable to the assignment source's rule.
		 * Recursively the container of this element has a similarly compatoble assignement.
		 */
		protected abstract boolean isCompatible(@Nullable Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> ruleAnalysis2assignmentAnalyses);

		public void serialize(@NonNull StringBuilder s) {
			s.append("<<<unsupported '" + element.eClass().getName() + "'>>>");
		}
	}

	/**
	 * A RootUserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
	 * that can produce and assign a user model element that has no container.
	 */
	public static class RootUserElementAnalysis extends AbstractUserElementAnalysis
	{
		private @NonNull List<@NonNull XtextAbstractRuleAnalysis> productionRuleAnalyses;

		public RootUserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
			super(modelAnalysis, element);
			assert element.eContainer() == null;
			EClass targetEClass = eClass(element);
			this.productionRuleAnalyses = grammarAnalysis.getProducingRuleAnalyses(targetEClass);
		}

		@Override
		public @Nullable AbstractUserElementAnalysis getContainingElementAnalysis() {
			return null;
		}

		@Override
		public @Nullable EStructuralFeature geteContainingFeature() {
			return null;
		}

		@Override
		public @NonNull Iterable<@NonNull XtextAbstractRuleAnalysis> getProductionRules() {
			return productionRuleAnalyses;
		}

		@Override
		protected boolean hasCompatibleContainmentHierarchy() {
			return true;
		}

		@Override
		protected boolean isCompatible(@Nullable Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> ruleAnalysis2assignmentAnalyses) {
			return true;
		}

	/*	@Override
		public void serialize(@NonNull StringBuilder s) {
			for (@NonNull XtextAbstractRuleAnalysis productionRuleAnalysis : productionRuleAnalyses) {
				if (productionRuleAnalysis.isCompatible(element)) {
					productionRuleAnalysis.serialize(s, element);
					return;
				}
			}
			s.append("<<<incompatible '" + element.eClass().getName() + "'>>>");
		} */

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(getName());
			s.append(" <=>");
			boolean isMany = productionRuleAnalyses.size() > 1;
			for (@NonNull XtextAbstractRuleAnalysis productionRuleAnalysis : productionRuleAnalyses) {
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

		private @Nullable Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> productionRuleAnalysis2containingAssignmentAnalyses = null;

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
		private @NonNull Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> analyzeProduction() {
			EClass targetEClass = eClass(element);
			@SuppressWarnings("unused") String eClassName = targetEClass.getName();
			if ("MultiplicityStringCS".equals(eClassName)) {
				getClass();				// XXX
			}
			Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> ruleAnalysis2assignmentAnalyses = new HashMap<>();
			isCompatible(ruleAnalysis2assignmentAnalyses);
			return ruleAnalysis2assignmentAnalyses;
		}

		@Override
		protected boolean isCompatible(@Nullable Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> ruleAnalysis2assignmentAnalyses) {
			Iterable<@NonNull XtextAssignmentAnalysis> containingAssignmentAnalysisCandidates = grammarAnalysis.getAssignmentAnalyses(eContainingFeature);
			for (@NonNull XtextAssignmentAnalysis containingAssignmentAnalysisCandidate : containingAssignmentAnalysisCandidates) {
				List<@NonNull XtextAbstractRuleAnalysis> compatibleTargetRuleAnalysisCandidates = null;
				EClass targetEClass = eClass(element);
				Iterable<@NonNull XtextAbstractRuleAnalysis> targetRuleAnalysisCandidates = grammarAnalysis.getProducingRuleAnalyses(targetEClass);
				for (@NonNull XtextAbstractRuleAnalysis targetRuleAnalysisCandidate : targetRuleAnalysisCandidates) {
					if (containingAssignmentAnalysisCandidate.targetIsAssignableFrom(targetRuleAnalysisCandidate)) {					// If target rule compatible
						boolean isOkSource = false;
						Iterable<@NonNull XtextAbstractRuleAnalysis> containerProductionRules = containingElementAnalysis.getProductionRules();
						for (@NonNull XtextAbstractRuleAnalysis sourceRuleAnalysisCandidate : containerProductionRules) {
							if (containingAssignmentAnalysisCandidate.sourceIsAssignableFrom(sourceRuleAnalysisCandidate)) {			// If source rule compatible
								if (containingElementAnalysis.isCompatible(null)) {													// If transitively compatible
									isOkSource = true;
									break;
								}
							}
						}
						if (isOkSource) {
							if (compatibleTargetRuleAnalysisCandidates == null) {
								compatibleTargetRuleAnalysisCandidates = new ArrayList<>(4);
							}
							compatibleTargetRuleAnalysisCandidates.add(targetRuleAnalysisCandidate);
						}
					}
				}
				if (compatibleTargetRuleAnalysisCandidates != null) {
					for (@NonNull XtextAbstractRuleAnalysis compatibleTargetRuleAnalysisCandidate : compatibleTargetRuleAnalysisCandidates) {
						if (ruleAnalysis2assignmentAnalyses == null) {
							return true;
						}
						List<@NonNull XtextAssignmentAnalysis> containingAssignmentAnalyses = ruleAnalysis2assignmentAnalyses.get(compatibleTargetRuleAnalysisCandidate);
						if (containingAssignmentAnalyses == null) {
							containingAssignmentAnalyses = new ArrayList<>();
							ruleAnalysis2assignmentAnalyses.put(compatibleTargetRuleAnalysisCandidate, containingAssignmentAnalyses);
						}
						containingAssignmentAnalyses.add(containingAssignmentAnalysisCandidate);
					}
				}
			}
			return false;
		}

		@Override
		public @NonNull AbstractUserElementAnalysis getContainingElementAnalysis() {
			return containingElementAnalysis;
		}

		@Override
		public @NonNull EStructuralFeature geteContainingFeature() {
			return eContainingFeature;
		}

		public @NonNull Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> getParserRuleAnalysis2assignmentAnalyses() {
			return ClassUtil.nonNullState(productionRuleAnalysis2containingAssignmentAnalyses);
		}

		@Override
		public @NonNull Iterable<@NonNull XtextAbstractRuleAnalysis> getProductionRules() {
			assert productionRuleAnalysis2containingAssignmentAnalyses != null;
			return productionRuleAnalysis2containingAssignmentAnalyses.keySet();
		}

		@Override
		protected boolean hasCompatibleContainmentHierarchy() {
			return getParserRuleAnalysis2assignmentAnalyses().size() > 0;
		}

		@Override
		public @NonNull String toString() {
			StringBuilder s = new StringBuilder();
			s.append(getName());
			s.append(" <=>");
			Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> productionRuleAnalysis2containingAssignmentAnalyses2 = productionRuleAnalysis2containingAssignmentAnalyses;
			if (productionRuleAnalysis2containingAssignmentAnalyses2 != null) {
				List<@NonNull XtextAbstractRuleAnalysis> productionRuleAnalyses = new ArrayList<>(productionRuleAnalysis2containingAssignmentAnalyses2.keySet());
				Collections.sort(productionRuleAnalyses, NameUtil.NAMEABLE_COMPARATOR);
				boolean isMany1 = productionRuleAnalyses.size() > 1;
				for (@NonNull XtextAbstractRuleAnalysis productionRuleAnalysis : productionRuleAnalyses) {
					s.append(isMany1 ? "\n\t\t" : " ");
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
						s.append(containingAssignmentAnalysis./*getEStructuralFeature().*/getName());
						s.append(":");
						boolean isFirst3 = true;
						for (@NonNull XtextAbstractRuleAnalysis targetAnalysis : containingAssignmentAnalysis.getTargetRuleAnalyses()) {
							if (!isFirst3) {
								s.append(",");
							}
							s.append(targetAnalysis.getRuleName());
							isFirst3 = false;
						}
						isFirst2 = false;
					}
					if (isMany2) {
						s.append("}");
					}
					s.append(" : ");
					s.append(productionRuleAnalysis.getName());
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

		for (@NonNull AbstractUserElementAnalysis elementAnalysis : element2elementAnalysis.values()) {
			for (@NonNull XtextAbstractRuleAnalysis ruleAnalysis : elementAnalysis.getProductionRules()) {
			//	XtextCorrelator correlation = new XtextCorrelator(ruleAnalysis);
			//	if (correlation.correlate()) {
			//		elementAnalysis.addCorrelation(correlation);
			//	}
			}
		}
	}

	public @NonNull AbstractUserElementAnalysis getElementAnalysis(@NonNull EObject element) {
		return ClassUtil.nonNullState(element2elementAnalysis.get(element));
	}

	public @NonNull XtextGrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	public @NonNull String serialize(@NonNull EObject rootElement) {
		SerializationBuilder serializationBuilder = new SerializationBuilder(this, new StringBuilder());
		serializationBuilder.serialize(rootElement);
		return serializationBuilder.toRenderedString();
	}

//	public void serialize(@NonNull StringBuilder s, @NonNull EObject element) {
//	  AbstractUserElementAnalysis elementAnalysis = getElementAnalysis(element);
//	  elementAnalysis.serialize(s);
//	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append("User object <=> Xtext containing assignment(s) : Xtext production rule\n");
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
