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
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;

/**
 * The MergerUpdateVisitor supports a second pass traversal of the containment tree of the merged models
 * to update with resolution of references.
 */
public class MergerUpdateVisitor extends AbstractExtendingVisitor<@NonNull Element, @NonNull Merger>
{
	public MergerUpdateVisitor(@NonNull Merger context) {
		super(context);
	}
}
