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
import org.eclipse.ocl.xtext.base.cs2text.elements.ProxyRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.conversion.ValueConverterException;

public class RTSerializationAssignedRuleCallsStep extends RTSerializationAbstractFeatureStep
{
	private @NonNull AbstractRuleValue @NonNull [] calledRuleValues;

	public RTSerializationAssignedRuleCallsStep(int variableIndex, @NonNull EStructuralFeature eStructuralFeature, @NonNull AbstractRuleValue @NonNull [] calledRuleValues) {
		super(variableIndex, eStructuralFeature);
		this.calledRuleValues = calledRuleValues;
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
		AbstractRuleValue[] theseCalledRuleValues = this.getCalledRuleValues();
		AbstractRuleValue[] thoseCalledRuleValues = that.getCalledRuleValues();
		if (theseCalledRuleValues.length != thoseCalledRuleValues.length) {
			return false;
		}
		for (int i = 0; i < theseCalledRuleValues.length; i++) {
			if (!theseCalledRuleValues[i].equals(thoseCalledRuleValues[i])) {
				return false;
			}
		}
		return true;
	}

	public @NonNull AbstractRuleValue @NonNull [] getCalledRuleValues() {
		for (int i = 0; i < calledRuleValues.length; i++) {
			AbstractRuleValue calledRuleValue = calledRuleValues[i];
			if (calledRuleValue instanceof ProxyRuleValue) {
				calledRuleValues[i] = ((ProxyRuleValue)calledRuleValue).getRuleValue();
			}
		}
		return calledRuleValues;
	}

	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		for (@NonNull AbstractRuleValue calledRuleValue : getCalledRuleValues()) {
			hashCode += 5 * calledRuleValue.hashCode();
		}
		return hashCode;
	}

	public void init(@NonNull AbstractRuleValue @NonNull [] calledRuleValues) {
		assert this.calledRuleValues.length == calledRuleValues.length;
		for (int i = 0; i < calledRuleValues.length; i++) {
			assert this.calledRuleValues[i] == null;
			this.calledRuleValues[i] = calledRuleValues[i];
		}
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		Object object = serializer.consumeNext(eStructuralFeature);
		if (eStructuralFeature instanceof EAttribute) {
			for (@NonNull AbstractRuleValue calledRuleValue : getCalledRuleValues()) {
				try {
					String val = serializer.getModelAnalysis().getValueConverterService().toString(object, calledRuleValue.getRuleName());
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
			UserElementAnalysis elementAnalysis = serializer.getModelAnalysis().getElementAnalysis(eObject);
			UserSlotsAnalysis slotsAnalysis = elementAnalysis.getSlotsAnalysis();
			for (@NonNull AbstractRuleValue calledRuleValue : getCalledRuleValues()) {		// search for matching rule
				for (@NonNull RTSerializationRule serializationRule : ((ParserRuleValue)calledRuleValue).getSerializationRules()) {
					DynamicRuleMatch match = serializationRule.getBasicSerializationRule().getStaticRuleMatch().match(slotsAnalysis);
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
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		boolean isFirst = true;
		for (@NonNull AbstractRuleValue calledRuleValue : getCalledRuleValues()) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(calledRuleValue.getRuleName());
			isFirst = false;
		}
	}
}