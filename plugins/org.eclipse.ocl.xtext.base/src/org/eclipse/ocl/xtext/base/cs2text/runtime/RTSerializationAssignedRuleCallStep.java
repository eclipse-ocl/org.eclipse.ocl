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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class RTSerializationAssignedRuleCallStep extends RTSerializationAbstractFeatureStep
{
	protected final @NonNull AbstractRuleAnalysis calledRuleAnalysis;

	public RTSerializationAssignedRuleCallStep(@Nullable CardinalityVariable variable, @NonNull EStructuralFeature eStructuralFeature, @NonNull AbstractRuleAnalysis calledRuleAnalysis) {
		super(variable, eStructuralFeature);
		this.calledRuleAnalysis = calledRuleAnalysis;
	}

	public RTSerializationAssignedRuleCallStep(int variableIndex, @NonNull EStructuralFeature eStructuralFeature, @NonNull String calledRuleKey) {
		super(variableIndex, eStructuralFeature);
		this.calledRuleAnalysis = (AbstractRuleAnalysis)(Object)calledRuleKey;		// XXX
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof RTSerializationAssignedRuleCallStep)) {
			return false;
		}
		return equalTo((RTSerializationAssignedRuleCallStep)obj);
	}

	protected boolean equalTo(@NonNull RTSerializationAssignedRuleCallStep that) {
		return super.equalTo(that) && this.calledRuleAnalysis.equals(that.calledRuleAnalysis);
	}

	public @NonNull String getCalledRuleKey() {
		return calledRuleAnalysis.getName();
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 5 * calledRuleAnalysis.hashCode();
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
	//	Object object = serializer.consumeNext(eStructuralFeature);
	//	serializationBuilder.append(String.valueOf(object));
		Object eGet = serializer.consumeNext(eStructuralFeature);
		if (eStructuralFeature instanceof EReference) {
			assert ((EReference)eStructuralFeature).isContainment();
			if (eGet != null) {
				serializer.serializeElement(serializationBuilder, (EObject)eGet, calledRuleAnalysis);
			}
		}
		else {
			String val = serializer.getModelAnalysis().getValueConverterService().toString(eGet, calledRuleAnalysis.getRuleName());
			serializationBuilder.append(String.valueOf(val));
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		super.toString(s, depth);
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(calledRuleAnalysis.getRuleName());
	}
}