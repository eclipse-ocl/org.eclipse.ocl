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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.solutions.CardinalityVariable;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

public class RTSerializationSequenceStep extends RTAbstractSerializationStep
{
	/**
	 * The number of steps within the linearized steps for the rule that support the sequence.
	 */
	private int startIndex = 0;
	private int endIndex = 0;

	public RTSerializationSequenceStep(@Nullable CardinalityVariable cardinalityVariable) {
		super(cardinalityVariable);		// Default unit sequence wraps the whole rule and may have a comment idiom
	}

	public RTSerializationSequenceStep(int variableIndex, int startIndex, int endIndex) {
		super(variableIndex);
		this.startIndex = startIndex;
		this.endIndex = endIndex;
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

	public int getEndIndex() {
		return endIndex;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public int getStepsRange() {
		return endIndex- startIndex;
	}

	@Override
	public void serialize(@NonNull UserElementSerializer serializer, @NonNull SerializationBuilder serializationBuilder) {
		serializer.getSerializationRule().serializeSubRule(startIndex, endIndex, serializer, serializationBuilder);
	}

	public void setSubRange(int startIndex, int endIndex) {
		assert startIndex >= 0;
		assert endIndex > startIndex;
		this.startIndex = startIndex ;
		this.endIndex = endIndex ;
	}

	@Override
	public void toString(@NonNull StringBuilder s, int depth) {
		super.toString(s, depth);
		s.append("next-");
		s.append(getStepsRange());
		s.append("-steps");
	}
}