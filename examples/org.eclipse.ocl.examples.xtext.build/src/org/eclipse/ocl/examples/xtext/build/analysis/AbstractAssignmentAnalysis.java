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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.GrammarCardinality;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtext.AbstractElement;

/**
 * An AbstractAssignmentAnalysis provides the extended analysis of a direct Xtext Assignment or indirect current assignment Action.
 */
public abstract class AbstractAssignmentAnalysis<T extends AbstractElement> implements AssignmentAnalysis
{
	/**
	 * The overall grammar analysis.
	 */
	protected final @NonNull GrammarAnalysis grammarAnalysis;

	/**
	 * The rule analysis that uses this assignment to assign a target rule result.
	 */
	protected final @NonNull ParserRuleAnalysis sourceRuleAnalysis;

	/**
	 * The assigned scope.
	 */
	protected final @NonNull EClass eClass;

	/**
	 * The assigned feature.
	 */
	protected final @NonNull EStructuralFeature eStructuralFeature;

	/**
	 * The analyzed current action or assignment.
	 */
	protected final @NonNull T actionOrAssignment;

	/**
	 * The rules declared to be useable as producers of the target. Multiples may arise from an Alternatives terminal
	 * or from sub-rules.
	 */
	private final @NonNull List<@NonNull AbstractRuleAnalysis> targetRuleAnalyses = new ArrayList<>();

	protected AbstractAssignmentAnalysis(@NonNull ParserRuleAnalysis sourceRuleAnalysis, @NonNull EClass eClass, @NonNull String featureName, @NonNull T actionOrAssignment) {
		this.grammarAnalysis = sourceRuleAnalysis.getGrammarAnalysis();
		this.sourceRuleAnalysis = sourceRuleAnalysis;
		this.eClass = eClass;
		this.eStructuralFeature = SerializationUtils.getEStructuralFeature(eClass, featureName);
		this.actionOrAssignment = actionOrAssignment;
	}

	protected void addTargetRuleAnalysis(@NonNull AbstractRuleAnalysis targetRuleAnalysis) {
		if (targetRuleAnalysis instanceof ParserRuleAnalysis) {
			Iterable<@NonNull ParserRuleAnalysis> subRuleAnalysesClosure = ((ParserRuleAnalysis)targetRuleAnalysis).getSubRuleAnalysesClosure();
			for (@NonNull ParserRuleAnalysis ruleAnalysis : subRuleAnalysesClosure) {
				targetRuleAnalyses.add(ruleAnalysis);
			}
		}
		else {
			targetRuleAnalyses.add(targetRuleAnalysis);
		}
	}

	protected void analyzeContainment() {
		if (eStructuralFeature instanceof EReference) {
			EReference eReference = (EReference)eStructuralFeature;
			if (eReference.isContainment()) {
				grammarAnalysis.addContainment(this, eReference);
			}
		}
	}

	@Override
	public @NonNull T getActionOrAssignment() {
		return actionOrAssignment;
	}

	public @NonNull String getCardinality() {
		String cardinality = actionOrAssignment.getCardinality();
		return cardinality != null ?  cardinality : "@";
	}

	@Override
	public @NonNull EClass getEClass() {
		return eClass;
	}

	@Override
	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	@Override
	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	public @NonNull GrammarCardinality getGrammarCardinality() {
		return GrammarCardinality.toEnum(actionOrAssignment);
	}

	@Override
	public @NonNull String getName() {
		return SerializationUtils.getName(sourceRuleAnalysis.getRule()) + "-" + eStructuralFeature.getName();
	}

	@Override
	public @NonNull ParserRuleAnalysis getSourceRuleAnalysis() {
		return sourceRuleAnalysis;
	}

	@Override
	public @NonNull List<@NonNull AbstractRuleAnalysis> getTargetRuleAnalyses() {
		return targetRuleAnalyses;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append(getName());
		s.append(" : ");
		boolean isFirst = true;
		for (@NonNull AbstractRuleAnalysis targetRuleAnalysis : getTargetRuleAnalyses()) {
			if (!isFirst) {
				s.append(",");
			}
			s.append(targetRuleAnalysis.getQualifiedName());
			isFirst = false;
		}
		@SuppressWarnings("null")
		@NonNull String castString = s.toString();
		return castString;
	}
}