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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public abstract class AbstractSerializationNode implements SerializationNode
{
	/**
	 * The overall (multi-)grammar analysis.
	 */
	protected final @NonNull XtextGrammarAnalysis grammarAnalysis;
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

	public AbstractSerializationNode(@NonNull XtextGrammarAnalysis grammarAnalysis, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.grammarAnalysis = grammarAnalysis;
		this.multiplicativeCardinality = multiplicativeCardinality;
	}

	protected void appendCardinality(@NonNull StringBuilder s, int depth) {
		if ((depth >= 0) || !multiplicativeCardinality.isOne()) {
			s.append("[");
			s.append(multiplicativeCardinality);
			s.append("]");
		}
	}

	@Override
	public @Nullable Serializer createSerializer(@NonNull UserModelAnalysis modelAnalysis, @NonNull EObject element) {
		Map<@NonNull EStructuralFeature, @NonNull Integer> eFeature2size = new HashMap<>();
		for (EStructuralFeature eFeature : element.eClass().getEAllStructuralFeatures()) {
			assert eFeature != null;
			if (!eFeature.isDerived() && !eFeature.isTransient() && !eFeature.isVolatile()) {
				int size;
				Object object = element.eGet(eFeature);
				if (eFeature.isMany()) {
					size = ((List<?>)object).size();
				}
				else if (element.eIsSet(eFeature)) {
					size = 1;
				}
				else {
					size = 0;
				}
				if (size > 0) {
					eFeature2size.put(eFeature, size);
				}
			}
		}
		for (@NonNull RequiredSlotsConjunction conjunction : getRequiredSlots().getDisjunction()) {
			Map<@NonNull CardinalityVariable, @NonNull Integer> variable2value = conjunction.computeActualCardinalities(element, eFeature2size);
			if (variable2value != null) {
				return new Serializer(conjunction, modelAnalysis, element, variable2value);
			}
		}
		return null;
	}

	@Override
	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return multiplicativeCardinality;
	}

	@Override
	public boolean isNull() {
		return false;
	}

	@Override
	public void serialize(@NonNull Serializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializationBuilder.append("<<<Unsupported serialize '" + getClass().getSimpleName() + "'>>>");
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		toString(s, 0);
		return s.toString();
	}
}