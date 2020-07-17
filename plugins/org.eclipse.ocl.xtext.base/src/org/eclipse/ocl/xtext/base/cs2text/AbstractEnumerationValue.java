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

public abstract class AbstractEnumerationValue implements EnumerationValue
{
	@Override
	public abstract boolean equals(Object obj);

	@Override
	public abstract int hashCode();

	@Override
	public boolean isNull() {
		return false;
	}
}