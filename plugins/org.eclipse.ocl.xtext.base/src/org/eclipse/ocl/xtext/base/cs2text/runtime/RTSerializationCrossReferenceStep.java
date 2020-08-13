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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.RuleCall;

public class RTSerializationCrossReferenceStep extends RTSerializationAbstractFeatureStep
{
	protected final @NonNull CrossReference crossReference;

	public RTSerializationCrossReferenceStep(@Nullable CardinalityVariable variable, @NonNull EStructuralFeature eStructuralFeature, @NonNull CrossReference crossReference) {
		super(variable, eStructuralFeature);
		this.crossReference = crossReference;
	}

	public RTSerializationCrossReferenceStep(int variableIndex, @NonNull EStructuralFeature eStructuralFeature) {
		super(variableIndex, eStructuralFeature);
		this.crossReference = (CrossReference)eStructuralFeature;			// XXX
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof RTSerializationCrossReferenceStep)) {
			return false;
		}
		return equalTo((RTSerializationCrossReferenceStep)obj);
	}

	protected boolean equalTo(@NonNull RTSerializationCrossReferenceStep that) {
		return super.equalTo(that) && crossReference.equals(that.crossReference);
	}

	@Override
	public int hashCode() {
		return super.hashCode() + 5 * crossReference.hashCode();
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		EObject eGet = (EObject)serializer.consumeNext(eStructuralFeature);
		EObject context = serializer.getElement();
		String string = serializer.getModelAnalysis().getCrossReferenceSerializer().serializeCrossRef(context, crossReference, eGet, null, null);
		serializationBuilder.append(string);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		super.toString(s, depth);
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
		s.append(eStructuralFeature.isMany() ? "+=" : "=");
		s.append(((RuleCall)crossReference.getTerminal()).getRule().getName());
	}
}