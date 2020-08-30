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
package org.eclipse.ocl.xtext.base.cs2text.runtime;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.user.UserModelAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.conversion.ValueConverterException;

public class RTSerializationAssignedRuleCallsStep extends RTSerializationAbstractFeatureStep
{
	private @NonNull IndexVector calledRuleIndexes;

	public RTSerializationAssignedRuleCallsStep(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull IndexVector calledRuleIndexes) {
		super(variableIndex, eStructuralFeature);
		this.calledRuleIndexes = calledRuleIndexes;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof RTSerializationAssignedRuleCallsStep)) {
			return false;
		}
		return equalTo((RTSerializationAssignedRuleCallsStep)obj);
	}

	protected boolean equalTo(@NonNull RTSerializationAssignedRuleCallsStep that) {
		if (!super.equalTo(that)) {
			return false;
		}
		return this.calledRuleIndexes.equals(that.calledRuleIndexes);
	}

	public @NonNull IndexVector getCalledRuleIndexes() {
		return calledRuleIndexes;
	}

	@Override
	public int hashCode() {
		int hashCode = super.hashCode() + calledRuleIndexes.hashCode();
		return hashCode;
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		Object object = serializer.consumeNext(eStructuralFeature);
		UserModelAnalysis modelAnalysis = serializer.getModelAnalysis();
		if (eStructuralFeature instanceof EAttribute) {
			for (int calledRuleIndex : calledRuleIndexes) {
				@NonNull AbstractRuleValue calledRuleValue = modelAnalysis.getGrammarAnalysis().getRuleValue(calledRuleIndex);
				try {
					String val = modelAnalysis.getValueConverterService().toString(object, calledRuleValue.getRuleName());
					serializationBuilder.append(String.valueOf(val));
					return;
				}
				catch (ValueConverterException e) {}
			}
			serializationBuilder.appendError("Failed to convert '" + String.valueOf(object) + "'");
		}
		else if (object != null) {
			EReference eReference = (EReference)eStructuralFeature;
			EObject eObject = (EObject)object;
			assert eReference.isContainment();
			UserElementAnalysis elementAnalysis = modelAnalysis.getElementAnalysis(eObject);
			UserSlotsAnalysis slotsAnalysis = elementAnalysis.getSlotsAnalysis();
			Segment[][] staticSegments = serializer.getStaticSegments();
			for (int calledRuleIndex : calledRuleIndexes) {			// search for matching rule
				@NonNull AbstractRuleValue calledRuleValue = modelAnalysis.getGrammarAnalysis().getRuleValue(calledRuleIndex);
				for (@NonNull SerializationRule serializationRule : ((ParserRuleValue)calledRuleValue).getSerializationRules()) {
					DynamicRuleMatch match = serializationRule.match(slotsAnalysis, staticSegments);
					if (match != null) {
						// XXX we could mark the serializationBuilder context and catch a backtracking exception/null return if needed here
						serializer.serializeElement(serializationBuilder, eObject, calledRuleValue);
						return;
					}
				}
			}
			serializationBuilder.appendError("Failed to convert " + eObject.eClass().getName() + ": '" + String.valueOf(object) + "'");
		}
		// else {}		-- null never happens
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		super.toString(s, depth);
		s.append(XtextGrammarUtil.getName(XtextGrammarUtil.getEContainingClass(eStructuralFeature)));
		s.append("::");
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		boolean isFirst = true;
		for (int calledRuleIndex : calledRuleIndexes) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(calledRuleIndex);
			isFirst = false;
		}
	}
}