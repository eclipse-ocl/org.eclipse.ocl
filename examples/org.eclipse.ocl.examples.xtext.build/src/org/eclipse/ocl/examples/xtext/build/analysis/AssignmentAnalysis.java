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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.Nameable;
import org.eclipse.xtext.AbstractElement;

/**
 * An AssignmentAnalysis provides the extended analysis of an Xtext Assignment or current Action
 */
public interface AssignmentAnalysis extends Nameable
{
	void analyzeContainmentAndTargets();
	@NonNull AbstractElement getActionOrAssignment();
	@NonNull EClass getEClass();
	@NonNull EStructuralFeature getEStructuralFeature();
	@NonNull GrammarAnalysis getGrammarAnalysis();
	@NonNull ParserRuleAnalysis getSourceRuleAnalysis();
	@NonNull Iterable<@NonNull AbstractRuleAnalysis> getTargetRuleAnalyses();
}