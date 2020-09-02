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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.user.DynamicRuleMatch;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.user.UserModelAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.user.UserSlotsAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.conversion.ValueConverterException;

public class SerializationStepAssigns extends SerializationStepAbstractFeature
{
	private @Nullable EnumerationValue enumerationValue;
	private @NonNull Integer @Nullable [] calledRuleIndexes;	// Cannot use GrammarRuleVEctor since must preserve declaration order

	public SerializationStepAssigns(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature, @Nullable EnumerationValue enumerationValue, @NonNull Integer @Nullable [] calledRuleIndexes) {
		super(variableIndex, eStructuralFeature);
		this.enumerationValue = enumerationValue;
		this.calledRuleIndexes = calledRuleIndexes;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SerializationStepAssigns)) {
			return false;
		}
		return equalTo((SerializationStepAssigns)obj);
	}

	protected boolean equalTo(@NonNull SerializationStepAssigns that) {
		if (!super.equalTo(that)) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.enumerationValue, that.enumerationValue)) {
			return false;
		}
		if (!ClassUtil.safeEquals(this.calledRuleIndexes, that.calledRuleIndexes)) {
			return false;
		}
		return true;
	}

	public @NonNull Integer @Nullable [] getCalledRuleIndexes() {
		return calledRuleIndexes;
	}

	public @Nullable EnumerationValue getEnumerationValue() {
		return enumerationValue;
	}

	@Override
	public int hashCode() {
		int hashCode = super.hashCode();
		if (enumerationValue != null) {
			enumerationValue.hashCode();
		}
		if (calledRuleIndexes != null) {
			calledRuleIndexes.hashCode();
		}
		return hashCode;
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		Object object = serializer.consumeNext(eStructuralFeature);
		UserModelAnalysis modelAnalysis = serializer.getModelAnalysis();
		SerializationGrammarAnalysis grammarAnalysis = modelAnalysis.getGrammarAnalysis();
		@NonNull Integer[] calledRuleIndexes2 = calledRuleIndexes;
		if (eStructuralFeature instanceof EAttribute) {
			EnumerationValue enumerationValue2 = enumerationValue;
			if (enumerationValue2 != null) {
				String string = String.valueOf(object);
				if (enumerationValue2.isElement(string)) {
					serializationBuilder.append(string);
					return;
				}
			}
			if (calledRuleIndexes2 != null) {
				for (int calledRuleIndex : calledRuleIndexes2) {
					@NonNull GrammarRuleValue calledRuleValue = grammarAnalysis.getRuleValue(calledRuleIndex);
					try {
						String val = modelAnalysis.getValueConverterService().toString(object, calledRuleValue.getRuleName());
						serializationBuilder.append(String.valueOf(val));
						return;
					}
					catch (ValueConverterException e) {}
				}
			}
			serializationBuilder.appendError("Failed to convert '" + String.valueOf(object) + "'");
		}
		else if ((object != null) && (calledRuleIndexes2 != null)) {
			EReference eReference = (EReference)eStructuralFeature;
			EObject eObject = (EObject)object;
			assert eReference.isContainment();
			UserElementAnalysis elementAnalysis = modelAnalysis.getElementAnalysis(eObject);
			UserSlotsAnalysis slotsAnalysis = elementAnalysis.getSlotsAnalysis();
			Segment[][] staticSegments = serializer.getStaticSegments();
			for (int calledRuleIndex : calledRuleIndexes2) {			// search for matching rule
				@NonNull GrammarRuleValue calledRuleValue = grammarAnalysis.getRuleValue(calledRuleIndex);
				for (@NonNull SerializationRule serializationRule : ((ParserRuleValue)calledRuleValue).getSerializationRules()) {
					DynamicRuleMatch match = serializationRule.match(slotsAnalysis, staticSegments);
					if (match != null) {
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
		// enumerationValue
		if (calledRuleIndexes != null) {
			for (int calledRuleIndex : calledRuleIndexes) {
				if (!isFirst) {
					s.append("|");
				}
				s.append(calledRuleIndex);
				isFirst = false;
			}
		}
	}
}