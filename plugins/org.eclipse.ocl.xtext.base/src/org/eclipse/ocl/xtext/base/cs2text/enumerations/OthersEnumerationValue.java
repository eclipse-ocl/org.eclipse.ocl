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

/**
 * The OthersEnumerationValue provides a reserved EnumerationValue to key the usage of unknown enumeration values.
 */
public class OthersEnumerationValue extends AbstractEnumerationValue
{
	public static final @NonNull OthersEnumerationValue INSTANCE = new OthersEnumerationValue();

	private OthersEnumerationValue() {}

	@Override
	public boolean equals(Object obj) {
		return obj == this;
	}

	@Override
	public @NonNull String getName() {
		return "«others»";
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
	public boolean isOthers() {
		return true;
	}

	@Override
	public @NonNull String toString() {
		return getName();
	}
}