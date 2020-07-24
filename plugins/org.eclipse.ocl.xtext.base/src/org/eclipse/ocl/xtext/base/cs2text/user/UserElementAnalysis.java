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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;

/**
 * A UserElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
 * that can produce and assign a user model element that has a container.
 */
public class UserElementAnalysis extends UserAbstractElementAnalysis
{
	protected final @NonNull EStructuralFeature eContainingFeature;

	protected final @NonNull UserAbstractElementAnalysis containingElementAnalysis;

	public UserElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
		super(modelAnalysis, element);
		assert element.eContainer() != null;
		this.eContainingFeature = UserModelAnalysis.eContainingFeature(element);
		this.containingElementAnalysis = modelAnalysis.getElementAnalysis(UserModelAnalysis.eContainer(element));
	}

	@Override
	public @NonNull UserAbstractElementAnalysis getContainingElementAnalysis() {
		return containingElementAnalysis;
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