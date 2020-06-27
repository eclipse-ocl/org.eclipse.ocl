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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;

public class SimpleConsumedSlot extends AbstractConsumedSlots //implements Iterable<@NonNull RequiredSlots>
{
	protected final @NonNull EClass eFeatureScope;
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final int lower;
	protected final int upper;

	public SimpleConsumedSlot(@NonNull EClass eFeatureScope, @NonNull EStructuralFeature eStructuralFeature, int lower, int upper) {
		super();
		this.eFeatureScope = eFeatureScope;
		this.eStructuralFeature = eStructuralFeature;
		this.lower = lower;
		this.upper = upper;
//		assert (upper < 0) || ((upper > 0) && (lower <= upper));
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	public int getLower() {
		return lower;
	}

	public int getUpper() {
		return upper;
	}

//	@Override
//	public @NonNull Iterable<@NonNull RequiredSlots> getConjunctions() {
//		return Collections.singletonList(this);
//	}

//	@Override
//	public @NonNull Iterable<@NonNull SimpleConsumedSlot> getSimpleRequiredSlots() {
//		return Collections.singletonList(this);
//	}

/*	public @Nullable ConsumedSlots isCompatible(@NonNull EObject element) {
		assert eFeature.getContainerClass().isInstance(element);
		Object object = element.eGet(eFeature);
		int lower;
		int upper;
		if (eFeature.isMany()) {
			List<?> list = (List<?>)object;
			lower = 0;
			upper = list.size();
		}
		else if (object != null) {
			lower = 1;
			upper = 1;
		}
		else if (element.eIsSet(eFeature)) {
			lower = 1;
			upper = 1;
		}
		else {
			lower = 0;
			upper = 0;
		}
		if (lower < lower) {
			return null;		// Too few
		}
		if ((upper >= 0) && (upper > upper)) {
			return null;		// Too many
		}
		return new SimpleConsumedSlot(serializationNode, lower, upper);
	} */

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		XtextGrammarUtil.appendEStructuralFeatureName(s, eFeatureScope, eStructuralFeature);
		s.append("[");
		s.append(lower);
		s.append("..");
		s.append(upper >= 0 ? upper : "*");
		s.append("]");
		return  s.toString();
	}
}