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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AssignmentAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;

/**
 * A RootUserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element that has no container.
 */
public class UserRootElementAnalysis extends UserAbstractElementAnalysis
{
	private @NonNull List<@NonNull ParserRuleAnalysis> productionRuleAnalyses;

	public UserRootElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject eObject) {
		super(modelAnalysis, eObject);
		assert eObject.eContainer() == null;
		EClass targetEClass = UserModelAnalysis.eClass(eObject);
		this.productionRuleAnalyses = (List<@NonNull ParserRuleAnalysis>)(Object)grammarAnalysis.getProducingRuleAnalyses(targetEClass);
	}

	@Override
	public @Nullable UserAbstractElementAnalysis getContainingElementAnalysis() {
		return null;
	}

	@Override
	public @NonNull Iterable<@NonNull ParserRuleAnalysis> getProductionRules() {
		return productionRuleAnalyses;
	}

	@Override
	protected boolean isCompatible(@Nullable Map<@NonNull ParserRuleAnalysis, @NonNull List<@NonNull AssignmentAnalysis>> ruleAnalysis2assignmentAnalyses) {
		return true;
	}

}