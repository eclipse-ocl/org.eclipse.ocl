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

/**
 * An AbstractAssignmentAnalysis provides the extended analysis of a direct Xtext Assignment or indirect current assignment Action.
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
}