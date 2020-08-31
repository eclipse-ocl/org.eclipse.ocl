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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.enumerations.EnumerationValue;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;
import org.eclipse.ocl.xtext.base.cs2text.xtext.XtextGrammarUtil;

public class RTSerializationAssignStep extends RTSerializationAbstractFeatureStep
{
//	protected final @NonNull EnumerationValue enumerationValue;

	@Deprecated
	public RTSerializationAssignStep(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature) {
		super(variableIndex, eStructuralFeature);
	}

	public RTSerializationAssignStep(int variableIndex, /*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull EnumerationValue enumerationValue) {
		super(variableIndex, eStructuralFeature);
//		this.enumerationValue = enumerationValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof RTSerializationAssignStep)) {
			return false;
		}
		return equalTo((RTSerializationAssignStep)obj);
	}

	protected boolean equalTo(@NonNull RTSerializationAssignStep that) {
		return super.equalTo(that);
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		Object object = serializer.consumeNext(eStructuralFeature);
		serializationBuilder.append(String.valueOf(object));
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		super.toString(s, depth);
		s.append(XtextGrammarUtil.getName(XtextGrammarUtil.getEContainingClass(eStructuralFeature)));
		s.append("::");
		s.append(XtextGrammarUtil.getName(eStructuralFeature));
	//	s.append(eStructuralFeature.isMany() ? "+=" : "=");
	//	s.append(((RuleCall)crossReference.getTerminal()).getRule().getName());
	}
}