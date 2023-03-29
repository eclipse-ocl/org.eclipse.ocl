/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.merge;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;

/**
 * The MergerUpdateVisitor supports a second pass traversal of the containment tree of the merged models
 * to update with resolution of references.
 */
public class MergerUpdateVisitor extends AbstractExtendingVisitor<@Nullable Object, @NonNull Merger>
{
	public MergerUpdateVisitor(@NonNull Merger context) {
		super(context);
	}

	@Override
	public @Nullable Object visiting(@NonNull Visitable visitable) {
		System.out.println("Unsupported " + visitable.eClass().getName() + " for " + getClass().getSimpleName());
		return null;
	}
}
