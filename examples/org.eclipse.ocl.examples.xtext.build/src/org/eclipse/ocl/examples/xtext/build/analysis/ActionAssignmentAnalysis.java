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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;
import org.eclipse.xtext.Action;

/**
 * An ActionAssignmentAnalysis provides the extended analysis of an Xtext current assignment Action
 */
public class ActionAssignmentAnalysis extends AbstractAssignmentAnalysis<@NonNull Action>
{
	/**
	 * The rule analysis that provides the assigned current value.
	 */
	protected final @NonNull ParserRuleAnalysis currentRuleAnalysis;

	public ActionAssignmentAnalysis(@NonNull ParserRuleAnalysis sourceRuleAnalysis, @NonNull Action action, @NonNull ParserRuleAnalysis currentRuleAnalysis) {
		super(sourceRuleAnalysis, (EClass)SerializationUtils.getClassifier(SerializationUtils.getType(action)), SerializationUtils.getFeature(action), action);
		this.currentRuleAnalysis = currentRuleAnalysis;
	}

	@Override
	public void analyzeContainmentAndTargets() {
		analyzeContainment();
		addTargetRuleAnalysis(currentRuleAnalysis);
	}
}