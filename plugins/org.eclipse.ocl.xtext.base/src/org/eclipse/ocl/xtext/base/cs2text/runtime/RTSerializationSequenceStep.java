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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

public class RTSerializationSequenceStep extends RTAbstractSerializationStep
{
	/**
	 * The number of steps within the linearized steps for the rule that support the sequence.
	 */
	private int stepsRange = 0;

	public RTSerializationSequenceStep(@NonNull CardinalityVariable cardinalityVariable) {
		super(cardinalityVariable);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof RTSerializationSequenceStep)) {
			return false;
		}
		return equalTo((RTSerializationSequenceStep)obj);
	}

	protected boolean equalTo(@NonNull RTSerializationSequenceStep that) {
		return super.equalTo(that);
	}

	@Override
	public @NonNull CardinalityVariable getCardinalityVariable() {
		assert cardinalityVariable != null;
		return cardinalityVariable;
	}

	public int getStepsRange() {
		return stepsRange;
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		throw new IllegalStateException();			// A sequence is steppped in a recursing caller
	}

	public void setStepsRange(int stepsRange) {
		assert stepsRange > 0;
		this.stepsRange = stepsRange ;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		super.toString(s, depth);
		s.append("next-");
		s.append(stepsRange);
		s.append("-steps");
	}
}