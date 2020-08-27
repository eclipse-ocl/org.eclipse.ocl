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
package org.eclipse.ocl.xtext.base.cs2text.xtext;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityExpression;

public class EStructuralFeatureData implements Nameable
{
	protected final @NonNull EStructuralFeature eStructuralFeature;
	protected final /*@NonNull*/ CardinalityExpression cardinalityExpression;

	public EStructuralFeatureData(/*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull CardinalityExpression cardinalityExpression) {
		assert eStructuralFeature != null;
		this.eStructuralFeature = eStructuralFeature;
		this.cardinalityExpression = cardinalityExpression;
	}
	public EStructuralFeatureData(/*@NonNull*/ EStructuralFeature eStructuralFeature, @NonNull String cardinalityExpression) {
		assert eStructuralFeature != null;
		this.eStructuralFeature = eStructuralFeature;
		this.cardinalityExpression = null;//cardinalityExpression;		// XXX
	}

	public @NonNull EStructuralFeature getEStructuralFeature() {
		return eStructuralFeature;
	}

	public @NonNull CardinalityExpression getCardinalityExpression() {
		return cardinalityExpression;
	}

	@Override
	public @NonNull String getName() {
		return XtextGrammarUtil.getName(eStructuralFeature);
	}


	@Override
	public @NonNull String toString() {
		return eStructuralFeature.getEContainingClass().getName() + "::" + eStructuralFeature.getName() + " " + cardinalityExpression;
	}
}