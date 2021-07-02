/**
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot.internal.symbolic;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.MapTypeId;

/**
 * @since 1.16
 */
public class SymbolicMapContent extends SymbolicContent
{
	public SymbolicMapContent(@NonNull String name, @NonNull MapTypeId typeId) {
		super(name, typeId);
	}

	protected SymbolicMapContent(@NonNull SymbolicMapContent originalContent) {
		super(originalContent);
	}

	@Override
	public @NonNull SymbolicContent shallowClone() {
		return new SymbolicMapContent(this);
	}
}
