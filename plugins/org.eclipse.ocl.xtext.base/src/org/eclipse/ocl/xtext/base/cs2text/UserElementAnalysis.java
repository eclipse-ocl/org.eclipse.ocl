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

/**
 * A UserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element that has a container.
 */
public class UserElementAnalysis extends UserAbstractElementAnalysis
{
	protected final @NonNull EStructuralFeature eContainingFeature;

	protected final @NonNull UserAbstractElementAnalysis containingElementAnalysis;

	private @Nullable Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> productionRuleAnalysis2containingAssignmentAnalyses = null;

	public UserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
		super(modelAnalysis, element);
		assert element.eContainer() != null;
		this.eContainingFeature = UserModelAnalysis.eContainingFeature(element);
		this.containingElementAnalysis = modelAnalysis.getElementAnalysis(UserModelAnalysis.eContainer(element));
		this.productionRuleAnalysis2containingAssignmentAnalyses = analyzeProduction();
	}

	/**
	 * Determine the rules able to produce this element and the containing assignments by which it can be contained.
	 */
	private @NonNull Map<@NonNull XtextAbstractRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> analyzeProduction() {
		EClass targetEClass = UserModelAnalysis.eClass(element);
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
			EClass targetEClass = UserModelAnalysis.eClass(element);
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
	public @NonNull UserAbstractElementAnalysis getContainingElementAnalysis() {
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