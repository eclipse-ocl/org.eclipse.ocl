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

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.xtext.util.Strings;

public class SubstringStep
{
	private static final @NonNull String KEY_EXACT = "e-";

	public static void toStepString(@NonNull DiagnosticStringBuilder s, @NonNull List<@NonNull SubstringStep> substringSteps) {
		toStepString(s, substringSteps, 0);
	}

	private static int toStepString(@NonNull DiagnosticStringBuilder s, @NonNull List<@NonNull SubstringStep> substringSteps, int index) {
		SubstringStep step = substringSteps.get(index++);
		step.toStepString(s);
		s.append(")");
		return index;
	}

	private @Nullable Integer hashCode = null;
	protected final @NonNull String string;
	protected final @NonNull SerializationSegment @NonNull [] serializationSegments;

	public SubstringStep(@NonNull String string, @NonNull SerializationSegment @NonNull [] serializationSegments) {
		this.string = string;
		this.serializationSegments = serializationSegments;
	}

	protected @Nullable Integer basicGetHashCode() {
		return hashCode;
	}

	protected int computeHashCode() {
		return getClass().hashCode() + 5 * string.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SubstringStep)) {
			return false;
		}
		return equalTo((SubstringStep)obj);
	}

	protected boolean equalTo(@NonNull SubstringStep that) {
		return this.string.equals(that.string);
	}

	public @NonNull String getGlobalSortKey() {
		StringBuilder s = new StringBuilder();
		s.append(KEY_EXACT);
		s.append(string);
		return s.toString();
	}

	@Override
	public final int hashCode() {
		Integer hashCode2 = hashCode;
		if (hashCode2 == null) {
			hashCode = hashCode2 = computeHashCode();
		}
		return hashCode2.intValue();
	}

	public @NonNull SerializationSegment @NonNull [] getSerializationSegments() {
		return serializationSegments;
	}

	public @NonNull String getString() {
		return string;
	}

	public void toRuleString(@NonNull DiagnosticStringBuilder s) {
		@SuppressWarnings("null")
		@NonNull String javaString = Strings.convertToJavaString(string);
		s.append("'");
		s.append(javaString);
		s.append("'");
	}

	public void toStepString(@NonNull DiagnosticStringBuilder s) {
		@SuppressWarnings("null")
		@NonNull String javaString = Strings.convertToJavaString(string);
		s.append("'");
		s.append(javaString);
		s.append("' ||");
		for (@NonNull SerializationSegment serializationSegment : serializationSegments) {
			s.append(" ");
			s.append(serializationSegment.toString());
		}
	}

	@Override
	public @NonNull String toString() {
		DiagnosticStringBuilder s = new DiagnosticStringBuilder();
		toString(s, 0);
		return s.toString();
	}

	public void toString(@NonNull DiagnosticStringBuilder s, int depth) {
		toRuleString(s);
	}

}