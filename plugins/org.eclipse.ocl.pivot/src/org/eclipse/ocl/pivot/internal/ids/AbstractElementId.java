/*******************************************************************************
 * Copyright (c) 2012, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.SingletonScope;

public abstract class AbstractElementId implements ElementId
{
	@Override
	public final boolean equals(Object that) {
		if (that instanceof SingletonScope.KeyAndValue) {	// A SingletonScope.Key may be used to lookup a singleton
			return that.equals(this);
		}
		else {										// But normally ElementId instances are singletons
			return this == that;
		}
	}

	public @Nullable String getLiteralName() {
		return null;
	}

	@Override
	public abstract int hashCode();

	@Override
	public String toString() {
		return getDisplayName();
	}
}