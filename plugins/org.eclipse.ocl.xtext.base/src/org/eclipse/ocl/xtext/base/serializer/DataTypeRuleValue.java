/*******************************************************************************
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.serializer;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class DataTypeRuleValue extends GrammarRuleValue
{
	protected final @NonNull SerializationSegment @NonNull [] serializationSegments;
	protected final @NonNull SubstringStep @NonNull [] substringSteps;

	public DataTypeRuleValue(int ruleIndex, @NonNull String name, @NonNull SerializationSegment @NonNull [] serializationSegments, @NonNull SubstringStep @Nullable [] substringSteps) {
		super(ruleIndex, name);
		this.serializationSegments = serializationSegments;
		this.substringSteps = substringSteps != null ? substringSteps : new @NonNull SubstringStep [0];
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DataTypeRuleValue)) {
			return false;
		}
		DataTypeRuleValue that = (DataTypeRuleValue)obj;
		return (this.ruleIndex == that.ruleIndex) && this.name.equals(that.name);
	}

	public @NonNull SerializationSegment @NonNull [] getSerializationSegments() {
		return serializationSegments;
	}

	public @NonNull SerializationSegment @NonNull [] getSerializationSegments(@Nullable String value) {
		if (value != null) {
			for (@NonNull SubstringStep substringStep : substringSteps) {
				if (value.equals(substringStep.getString())) {
					return substringStep.getSerializationSegments();
				}
			}
		}
		return serializationSegments;
	}

	public @NonNull SubstringStep @NonNull [] getSubstringSteps() {
		return substringSteps;
	}
}