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
package org.eclipse.ocl.xtext.base.cs2text.enumerations;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.xtext.util.Strings;

public class MultipleEnumerationValue extends AbstractEnumerationValue
{
	protected final @NonNull String @NonNull [] values;
	protected final @NonNull String name;
	private @Nullable Integer hashCode = null;

	public MultipleEnumerationValue(@NonNull List<@NonNull String> values) {
		this(values.toArray(new @NonNull String [values.size()]));
	}

	public MultipleEnumerationValue(@NonNull String @NonNull [] values) {
		this.values = values;
		assert values.length > 0;
		Arrays.sort(this.values);
	//	this.name = this.values.get(0) + "...";
		StringBuilder s = new StringBuilder();
		boolean isFirst = true;
		for (@NonNull String value : this.values) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(Strings.convertToJavaString(value));
			isFirst = false;
		}
		this.name = s.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof MultipleEnumerationValue)) {
			return false;
		}
		MultipleEnumerationValue that = (MultipleEnumerationValue)obj;
		if (this.values.length != that.values.length) {
			return false;
		}
		for (int i = 0; i < this.values.length; i++) {
			if (!this.values[i].equals(that.values[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	public @NonNull String @NonNull [] getValues() {
		return values;
	}

	@Override
	public int hashCode() {
		if (hashCode == null) {
			int hash = getClass().hashCode();
			for (@NonNull String value : values) {
				hash += value.hashCode();
			}
			hashCode = hash;
		}
		assert hashCode != null;
		return hashCode.intValue();
	}

	@Override
	public boolean isElement(@NonNull String string) {
		for (@NonNull String value : values) {
			if (value.equals(string)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		s.append("'");
		boolean isFirst = true;
		for (@NonNull String value : values) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(Strings.convertToJavaString(value));
			isFirst = false;
		}
		s.append("'");
		return s.toString();
	}
}
