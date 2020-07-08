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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;

/**
	 * An UserAbstractElementAnalysis provides the working context to assist in the determination of the Xtext grammar rule
	 * that can produce and assign a user model element.
	 */
	public abstract class UserAbstractElementAnalysis implements Nameable
	{
		private static int count = 0;

		protected final @NonNull UserModelAnalysis modelAnalysis;
		protected final @NonNull XtextGrammarAnalysis grammarAnalysis;
		protected final @NonNull EObject element;
		private final @NonNull String name;
//		private @NonNull List<@NonNull XtextTermsAnalysis> correlators = new ArrayList<>();

		public UserAbstractElementAnalysis(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
			this.modelAnalysis = modelAnalysis;
			this.grammarAnalysis = modelAnalysis.getGrammarAnalysis();
			this.element = element;
			this.name = element.eClass().getName() + "@" + ++count;
		}

//		public void addCorrelation(@NonNull XtextTermsAnalysis correlator) {
//			correlators.add(correlator);
//		}

		public @Nullable Serializer createSerializer() {
			Iterable<@NonNull XtextParserRuleAnalysis> productionRuleAnalyses = getProductionRules();
			for (@NonNull XtextParserRuleAnalysis productionRuleAnalysis : productionRuleAnalyses) {
				Serializer serializer = productionRuleAnalysis.createSerializer(modelAnalysis, element);
				if (serializer != null) {
					return serializer;
				}
			}
			return null;
		}

		public abstract @Nullable UserAbstractElementAnalysis getContainingElementAnalysis();

		public abstract @Nullable EStructuralFeature geteContainingFeature();

		public @NonNull EObject getElement() {
			return element;
		}

		public @NonNull XtextGrammarAnalysis getGrammarAnalysis() {
			return grammarAnalysis;
		}

		@Override
		public @NonNull String getName() {
			return name;
		}

		public abstract @NonNull Iterable<@NonNull XtextParserRuleAnalysis> getProductionRules();

		protected abstract boolean hasCompatibleContainmentHierarchy();

		/**
		 * Return true if the containing feature is compatible with one of its containing assignments.
		 *
		 * If non-null each compatible assignment is assign to its corresponding production rulein ruleAnalysis2assignmentAnalyses.
		 *
		 * Compatbility requires
		 *
		 * The produced rule for this element is assignable to the assignment target's rule.
		 * The produced rule for the container of this element is assignable to the assignment source's rule.
		 * Recursively the container of this element has a similarly compatoble assignement.
		 */
		protected abstract boolean isCompatible(@Nullable Map<@NonNull XtextParserRuleAnalysis, @NonNull List<@NonNull XtextAssignmentAnalysis>> ruleAnalysis2assignmentAnalyses);
	}