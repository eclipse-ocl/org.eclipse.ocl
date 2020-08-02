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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.AbstractElement;

/**
 * An XtextAssignmentAnalysis provides the extended analysis of an Xtext Assignment
 */
public abstract class AbstractAssignmentAnalysis implements AssignmentAnalysis
{
	/**
	 * The overall grammar analysis.
	 */
	protected final @NonNull GrammarAnalysis grammarAnalysis;

	/**
	 * The assigned scope.
	 */
	protected final @NonNull EClass eClass;

	/**
	 * The assigned feature.
	 */
	protected final @NonNull EStructuralFeature eStructuralFeature;

	protected AbstractAssignmentAnalysis(@NonNull GrammarAnalysis grammarAnalysis, @NonNull EClass eClass, @NonNull String featureName) {
		this.grammarAnalysis = grammarAnalysis;
		this.eClass = eClass;
		this.eStructuralFeature = XtextGrammarUtil.getEStructuralFeature(eClass, featureName);
	}

	protected abstract void analyzeContainmentAndTargets();

	protected abstract @NonNull AbstractElement getAssignment();

	public @NonNull EClass getEClass() {
		return eClass;
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	public @NonNull GrammarAnalysis getGrammarAnalysis() {
		return grammarAnalysis;
	}

	public abstract @NonNull Iterable<@NonNull AbstractRuleAnalysis> getTargetRuleAnalyses();
}