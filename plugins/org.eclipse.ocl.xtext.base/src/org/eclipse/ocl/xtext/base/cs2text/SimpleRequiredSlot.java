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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class SimpleRequiredSlot extends AbstractRequiredSlots //implements Iterable<@NonNull RequiredSlots>
{
//	protected final @NonNull AssignedSerializationNode serializationNode;
	protected final @NonNull EClass eFeatureScope;
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final @NonNull MultiplicativeCardinality multiplicativeCardinality;

	public SimpleRequiredSlot(@NonNull EClass eFeatureScope, @NonNull EStructuralFeature eStructuralFeature, @NonNull MultiplicativeCardinality multiplicativeCardinality) {
		this.eFeatureScope = eFeatureScope;
		this.eStructuralFeature = eStructuralFeature;
		this.multiplicativeCardinality = multiplicativeCardinality;
	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlots> getConjunction() {
		return Collections.singletonList(this);
	}

	@Override
	public @NonNull RequiredSlotsConjunction getConjunction(int conjunctionIndex) {
		assert conjunctionIndex == 0;
		RequiredSlotsConjunction requiredSlotsConjunction = new RequiredSlotsConjunction();
		requiredSlotsConjunction.accumulate(this, MultiplicativeCardinality.ONE);
		requiredSlotsConjunction.getConjunction();		// XXX eager
		return requiredSlotsConjunction;
	}

	@Override
	public int getConjunctionCount() {
		return 1;
	}

	@Override
	public @NonNull Iterable<@NonNull RequiredSlotsConjunction> getDisjunction() {
		throw new UnsupportedOperationException();
	}

	public @NonNull EClass getEFeatureScope() {
		return eFeatureScope;
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	@Override
	public @NonNull Iterable<@NonNull EStructuralFeature> getEStructuralFeatures() {
		return Collections.singletonList(eStructuralFeature);
	}

	@Deprecated
	public int getLowerBound() {
		return multiplicativeCardinality.mayBeZero() ? 0 : 1;
	}

	@Deprecated
	@Override
	public int getLowerBound(@NonNull EStructuralFeature eStructuralFeature) {
		return eStructuralFeature == this.eStructuralFeature ? eStructuralFeature.getLowerBound() : 0;
	}

	public @NonNull MultiplicativeCardinality getMultiplicativeCardinality() {
		return multiplicativeCardinality;
	}

	@Deprecated
	@Override
	public int getQuantum(@NonNull EStructuralFeature eStructuralFeature) {
		return eStructuralFeature == this.eStructuralFeature ? 1 : 0;
	}

	@Deprecated
	public int getUpperBound() {
		return multiplicativeCardinality.mayBeMany() ? -1 : 1;
	}

	@Deprecated
	@Override
	public int getUpperBound(@NonNull EStructuralFeature eStructuralFeature) {
		return eStructuralFeature == this.eStructuralFeature ? eStructuralFeature.getUpperBound() : 0;
	}

	public @Nullable SimpleConsumedSlot isCompatible(@NonNull EObject element) {
		assert eStructuralFeature.getContainerClass().isInstance(element);
		Object object = element.eGet(eStructuralFeature);
		int lower;
		int upper;
		if (eStructuralFeature.isMany()) {
			List<?> list = (List<?>)object;
			lower = 0;
			upper = list.size();
		}
		else if (object != null) {
			lower = 1;
			upper = 1;
		}
		else if (element.eIsSet(eStructuralFeature)) {
			lower = 1;
			upper = 1;
		}
		else {
			lower = 0;
			upper = 0;
		}
		assert (upper < 0) || ((upper > 0) && (lower <= upper));
		if (lower < getLowerBound()) {
			if (getLowerBound() <= upper) {
				lower = getLowerBound();
			}
			else {
				return null;		// Too few
			}
		}
		if ((getUpperBound() >= 0) && (upper > getUpperBound())) {
			return null;		// Too many
		}
		return new SimpleConsumedSlot(eFeatureScope, eStructuralFeature, lower, upper);
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append("[");
		s.append(multiplicativeCardinality);
		s.append("]");
	}
}