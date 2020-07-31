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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.util.Strings;

public class SingleEnumerationValue extends AbstractEnumerationValue
{
	protected final @NonNull String value;

	public SingleEnumerationValue(@NonNull String value) {
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof SingleEnumerationValue)) {
			return false;
		}
		return value.equals(((SingleEnumerationValue)obj).value);
	}

	@Override
	public @NonNull String getName() {
		return value;
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	@Override
	public boolean isElement(@NonNull String string) {
		return value.equals(string);
	}

	@Override
	public @NonNull String toString() {
		return "'" + Strings.convertToJavaString(value) + "'";
	}
}