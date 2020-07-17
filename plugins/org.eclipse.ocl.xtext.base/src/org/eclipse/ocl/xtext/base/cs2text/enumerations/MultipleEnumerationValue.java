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

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.util.Strings;

import com.google.common.collect.Lists;

public class MultipleEnumerationValue extends AbstractEnumerationValue
{
	protected final @NonNull List<@NonNull String> values;
	protected final @NonNull String name;

	public MultipleEnumerationValue(@NonNull Iterable<@NonNull String> values) {
		this.values = Lists.newArrayList(values);
		assert !this.values.isEmpty();
		Collections.sort(this.values);
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
		return values.equals(((MultipleEnumerationValue)obj).values);
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		return values.hashCode();
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
		s.append("\"");
		boolean isFirst = true;
		for (@NonNull String value : values) {
			if (!isFirst) {
				s.append("|");
			}
			s.append(Strings.convertToJavaString(value));
			isFirst = false;
		}
		s.append("\"");
		return s.toString();
	}
}
