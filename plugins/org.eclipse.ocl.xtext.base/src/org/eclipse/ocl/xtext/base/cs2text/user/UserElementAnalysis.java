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
package org.eclipse.ocl.xtext.base.cs2text.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.elements.AssignedSerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;

/**
 * A UserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element that has a container.
 */
public class UserElementAnalysis extends UserAbstractElementAnalysis
{
	protected final @NonNull UserAbstractElementAnalysis containingElementAnalysis;
	protected final @NonNull EReference eContainmentFeature;
	private @NonNull Iterable<@NonNull SerializationRule> serializationRules;
//	private @Nullable Map<@NonNull ParserRuleAnalysis, @NonNull List<@NonNull AssignmentAnalysis>> productionRuleAnalysis2containingAssignmentAnalyses = null;
//	private @Nullable Iterable<@NonNull ParserRuleAnalysis> productionRuleAnalyses = null;

	public UserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull UserAbstractElementAnalysis containingElementAnalysis,
			@NonNull EReference eContainmentFeature, @NonNull EObject eObject) {
		super(modelAnalysis, eObject);
		assert eObject.eContainer() != null;
		this.containingElementAnalysis = containingElementAnalysis;
		this.eContainmentFeature = eContainmentFeature;
		this.serializationRules = analyzeSerializationRules();
//		this.productionRuleAnalyses = analyzeProduction();
	}

	/**
	 * Determine the rules able to produce this element and the containing assignments by which it can be contained.
	 */
	private @NonNull Iterable<@NonNull SerializationRule> analyzeSerializationRules() {
		String eClassName = eClass.getName();
		if ("ImportCS".equals(eClassName)) {
			getClass();				// XXX
		}
		Set<AbstractRuleAnalysis> targetRuleAnalyses = new HashSet<>();
		for (@NonNull SerializationRule parentSerializationRule : grammarAnalysis.getSerializationRules(containingElementAnalysis.getEClass())) {
			Iterable<@NonNull AssignedSerializationNode> assignedSerializationNodes = parentSerializationRule.getAssignedSerializationNodes(eContainmentFeature);
			if (assignedSerializationNodes != null) {
				for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
					for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getAssignmentAnalysis().getTargetRuleAnalyses()) {
						targetRuleAnalyses.add(targetRuleAnalysis);
					}
				}
			}
		}
		List<@NonNull SerializationRule> serializationRules = new ArrayList<>();
		for (@NonNull SerializationRule serializationRule : grammarAnalysis.getSerializationRules(eClass)) {
			if (targetRuleAnalyses.contains(serializationRule.getRuleAnalysis())) {
				serializationRules.add(serializationRule);
			}
		}
		return serializationRules;
	}

	/**
	 * Determine the rules able to produce this element and the containing assignments by which it can be contained.
	 *
	private @NonNull Iterable<@NonNull ParserRuleAnalysis> analyzeProduction() {
		getSerializationRules();
		String eClassName = eClass.getName();
		if ("MultiplicityBoundsCS".equals(eClassName)) {
			getClass();				// XXX
		}
		Set<AbstractRuleAnalysis> targetRuleAnalyses = new HashSet<>();
		for (@NonNull SerializationRule parentSerializationRule : grammarAnalysis.getSerializationRules(containingElementAnalysis.getEClass())) {
			Iterable<@NonNull AssignedSerializationNode> assignedSerializationNodes = parentSerializationRule.getAssignedSerializationNodes(eContainmentFeature);
			if (assignedSerializationNodes != null) {
				for (@NonNull AssignedSerializationNode assignedSerializationNode : assignedSerializationNodes) {
					for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : assignedSerializationNode.getAssignmentAnalysis().getTargetRuleAnalyses()) {
						targetRuleAnalyses.add(targetRuleAnalysis);
					}
				}
			}
		}
		List<@NonNull SerializationRule> serializationRules = new ArrayList<>();
		for (@NonNull SerializationRule serializationRule : grammarAnalysis.getSerializationRules(eClass)) {
			if (targetRuleAnalyses.contains(serializationRule.getRuleAnalysis())) {
				serializationRules.add(serializationRule);
			}
		}
		Set<@NonNull ParserRuleAnalysis> ruleAnalyses = new HashSet<>();
		isCompatible(ruleAnalyses);
		return ruleAnalyses;
	}

	@Override
	protected boolean isCompatible(@Nullable Set<@NonNull ParserRuleAnalysis> ruleAnalyses) {
		Iterable<@NonNull AssignmentAnalysis> containingAssignmentAnalysisCandidates = grammarAnalysis.getAssignmentAnalyses(eContainmentFeature);
		for (@NonNull AssignmentAnalysis containingAssignmentAnalysisCandidate : containingAssignmentAnalysisCandidates) {
			List<@NonNull ParserRuleAnalysis> compatibleTargetRuleAnalysisCandidates = null;
		//	EClass targetEClass = eClass;
			Iterable<@NonNull ParserRuleAnalysis> targetRuleAnalysisCandidates = grammarAnalysis.getProducingRuleAnalyses(eClass);
			for (@NonNull ParserRuleAnalysis targetRuleAnalysisCandidate : targetRuleAnalysisCandidates) {
				if (containingAssignmentAnalysisCandidate.targetIsAssignableFrom(targetRuleAnalysisCandidate)) {					// If target rule compatible
					boolean isOkSource = false;
					Iterable<@NonNull ParserRuleAnalysis> containerProductionRules = containingElementAnalysis.getProductionRules();
					for (@NonNull AbstractRuleAnalysis sourceRuleAnalysisCandidate : containerProductionRules) {
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
				for (@NonNull ParserRuleAnalysis compatibleTargetRuleAnalysisCandidate : compatibleTargetRuleAnalysisCandidates) {
					if (ruleAnalyses == null) {
						return true;
					}
					ruleAnalyses.add(compatibleTargetRuleAnalysisCandidate);
				}
			}
		}
		return false;
	} */

	@Override
	public @NonNull UserAbstractElementAnalysis getContainingElementAnalysis() {
		return containingElementAnalysis;
	}

//	@Override
//	public @NonNull EStructuralFeature geteContainingFeature() {
//		return eContainingFeature;
//	}

//	public @NonNull Map<@NonNull ParserRuleAnalysis, @NonNull List<@NonNull AssignmentAnalysis>> getParserRuleAnalysis2assignmentAnalyses() {
//		return ClassUtil.nonNullState(productionRuleAnalysis2containingAssignmentAnalyses);
//	}

//	@Override
//	public @NonNull Iterable<@NonNull ParserRuleAnalysis> getProductionRules() {
//		assert productionRuleAnalyses != null;
//		return productionRuleAnalyses;
//	}

	@Override
	public @NonNull Iterable<@NonNull SerializationRule> getSerializationRules() {
		return serializationRules;
	}

/*	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getName());
		s.append(" <=>");
/ *		Map<@NonNull XtextParserRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> productionRuleAnalysis2containingAssignmentAnalyses2 = productionRuleAnalysis2containingAssignmentAnalyses;
		if (productionRuleAnalysis2containingAssignmentAnalyses2 != null) {
			List<@NonNull XtextParserRuleAnalysis> productionRuleAnalyses = new ArrayList<>(productionRuleAnalysis2containingAssignmentAnalyses2.keySet());
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
					s.append(containingAssignmentAnalysis./ *getEStructuralFeature().* /getName());
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
		} * /
		return s.toString();
	} */
}