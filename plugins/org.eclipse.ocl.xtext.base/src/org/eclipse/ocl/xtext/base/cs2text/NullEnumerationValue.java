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

import org.eclipse.jdt.annotation.NonNull;

public class NullEnumerationValue extends AbstractEnumerationValue
{
	public static final @NonNull NullEnumerationValue INSTANCE = new NullEnumerationValue();

	private NullEnumerationValue() {}

	@Override
	public boolean equals(Object obj) {
		return obj == this;
	}

	@Override
	public @NonNull String getName() {
		return "null";
	}

	@Override
	public int hashCode() {
		return getClass().hashCode();
	}

	@Override
	public boolean isElement(@NonNull String string) {
		return false;
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public @NonNull String toString() {
		return "null";
	}
}