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

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A RootUserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element that has no container.
 */
public class UserRootElementAnalysis extends UserAbstractElementAnalysis
{
	private @NonNull List<@NonNull XtextParserRuleAnalysis> productionRuleAnalyses;

	public UserRootElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
		super(modelAnalysis, element);
		assert element.eContainer() == null;
		EClass targetEClass = UserModelAnalysis.eClass(element);
		this.productionRuleAnalyses = (List<@NonNull XtextParserRuleAnalysis>)(Object)grammarAnalysis.getProducingRuleAnalyses(targetEClass);
	}

	@Override
	public @Nullable UserAbstractElementAnalysis getContainingElementAnalysis() {
		return null;
	}

	@Override
	public @Nullable EStructuralFeature geteContainingFeature() {
		return null;
	}

	@Override
	public @NonNull Iterable<@NonNull XtextParserRuleAnalysis> getProductionRules() {
		return productionRuleAnalyses;
	}

	@Override
	protected boolean hasCompatibleContainmentHierarchy() {
		return true;
	}

	@Override
	protected boolean isCompatible(@Nullable Map<@NonNull XtextParserRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> ruleAnalysis2assignmentAnalyses) {
		return true;
	}

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