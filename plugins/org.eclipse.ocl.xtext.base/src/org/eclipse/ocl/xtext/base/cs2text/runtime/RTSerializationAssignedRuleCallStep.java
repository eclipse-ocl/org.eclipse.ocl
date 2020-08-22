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
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.ProxyRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.AbstractRuleValue;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class RTSerializationAssignedRuleCallStep extends RTSerializationAbstractFeatureStep
{
	private static class NullRuleValue extends AbstractRuleValue
	{
		private static final @NonNull NullRuleValue INSTANCE = new NullRuleValue();

		private NullRuleValue() {
			super(-999, "null");			// Index (and name) should never be used.
		}

		@Override
		public boolean equals(Object obj) {
			throw new IllegalStateException();		// Null should have been replaced.
		}

		public @NonNull AbstractRuleValue getRuleValue() {
			throw new IllegalStateException();		// Null should have been replaced.
		}

		@Override
		public int getIndex() {
			throw new IllegalStateException();		// Null should have been replaced.
		}

		@Override
		public @NonNull String getName() {
			throw new IllegalStateException();		// Null should have been replaced.
		}

		@Override
		public @NonNull String getRuleName() {
			throw new IllegalStateException();		// Null should have been replaced.
		}

		@Override
		public int hashCode() {
			throw new IllegalStateException();		// Null should have been replaced.
		}

		@Override
		public @NonNull String toString() {
			throw new IllegalStateException();		// Null should have been replaced.
		}
	}

	private @NonNull AbstractRuleValue calledRuleValue;

	public RTSerializationAssignedRuleCallStep(int variableIndex, @NonNull EStructuralFeature eStructuralFeature) {
		super(variableIndex, eStructuralFeature);
		this.calledRuleValue = NullRuleValue.INSTANCE;
	}

	public RTSerializationAssignedRuleCallStep(int variableIndex, @NonNull EStructuralFeature eStructuralFeature, @NonNull AbstractRuleValue calledRuleValue) {
		super(variableIndex, eStructuralFeature);
		this.calledRuleValue = calledRuleValue;
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
		return super.equalTo(that) && this.getCalledRuleValue().equals(that.getCalledRuleValue());
	}

	public @NonNull AbstractRuleValue getCalledRuleValue() {
		if (calledRuleValue instanceof ProxyRuleValue) {
			calledRuleValue = ((ProxyRuleValue)calledRuleValue).getRuleValue();
		}
		return calledRuleValue;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 5 * getCalledRuleValue().hashCode();
	}

	public void init(@NonNull AbstractRuleValue calledRuleValue) {
		assert this.calledRuleValue == NullRuleValue.INSTANCE;
		this.calledRuleValue = calledRuleValue;
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
	//	Object object = serializer.consumeNext(eStructuralFeature);
	//	serializationBuilder.append(String.valueOf(object));
		Object eGet = serializer.consumeNext(eStructuralFeature);
		if (eStructuralFeature instanceof EReference) {
			assert ((EReference)eStructuralFeature).isContainment();
			if (eGet != null) {
				serializer.serializeElement(serializationBuilder, (EObject)eGet, getCalledRuleValue());
			}
		}
		else {
			String val = serializer.getModelAnalysis().getValueConverterService().toString(eGet, getCalledRuleValue().getRuleName());
			serializationBuilder.append(String.valueOf(val));
		}
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		super.toString(s, depth);
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(getCalledRuleValue().getRuleName());
	}
}