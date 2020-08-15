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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.ParserRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.conversion.ValueConverterException;

public class RTSerializationAssignedRuleCallsStep extends RTSerializationAbstractFeatureStep
{
	protected final @NonNull Iterable<@NonNull AbstractRuleAnalysis> calledRuleAnalyses;

	public RTSerializationAssignedRuleCallsStep(@Nullable CardinalityVariable variable, @NonNull EStructuralFeature eStructuralFeature, @NonNull Iterable<@NonNull AbstractRuleAnalysis> calledRuleAnalyses) {
		super(variable, eStructuralFeature);
		this.calledRuleAnalyses = calledRuleAnalyses;
	}

	public RTSerializationAssignedRuleCallsStep(int variableIndex, EStructuralFeature eStructuralFeature, @NonNull String @NonNull ... calledRuleKeys) {
		super(variableIndex, eStructuralFeature);
		this.calledRuleAnalyses = Collections.emptyList();	// XXX
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
		return super.equalTo(that) && this.calledRuleAnalyses.equals(that.calledRuleAnalyses);
	}

	public @NonNull Iterable<@NonNull String> getCalledRuleKeys() {
		List<@NonNull String> list = new ArrayList<>();
		for (@NonNull AbstractRuleAnalysis calledRuleAnalysis : calledRuleAnalyses) {
			list.add(calledRuleAnalysis.getName());
		}
		return list;
	}

	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		for (@NonNull AbstractRuleAnalysis calledRuleAnalysis : calledRuleAnalyses) {
			hashCode += 5 * calledRuleAnalysis.hashCode();
		}
		return hashCode;
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		Object object = serializer.consumeNext(eStructuralFeature);
		if (eStructuralFeature instanceof EAttribute) {
			for (@NonNull AbstractRuleAnalysis calledRuleAnalysis : calledRuleAnalyses) {
				try {
					String val = serializer.getModelAnalysis().getValueConverterService().toString(object, calledRuleAnalysis.getRuleName());
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
			for (@NonNull AbstractRuleAnalysis calledRuleAnalysis : calledRuleAnalyses) {		// search for matching rule
				for (@NonNull SerializationRule serializationRule : ((ParserRuleAnalysis)calledRuleAnalysis).getSerializationRules()) {
					DynamicRuleMatch match = serializationRule.getBasicSerializationRule().getStaticRuleMatch().match(slotsAnalysis);
					if (match != null) {
						// XXX we could mark the serializationBuilder context and catch a backtracking exception/null return if needed here
						serializer.serializeElement(serializationBuilder, eObject, calledRuleAnalysis.getRuleValue());
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
		for (@NonNull AbstractRuleAnalysis calledRuleAnalysis : calledRuleAnalyses) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(calledRuleAnalysis.getRuleName());
			isFirst = false;
		}
	}
}