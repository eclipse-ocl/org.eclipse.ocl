/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.WildcardType;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.WildcardId;

/**
 * @since 1.18
 */
public class WildcardIdImpl extends UnscopedId implements WildcardId
{
//	protected final @NonNull WildcardType wildcard;
	protected final @NonNull String name;

	public WildcardIdImpl(@NonNull IdManager idManager, @NonNull WildcardType wildcard) {
//		this.wildcard = wildcard;
		this.name = wildcard.getName();
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitWildcardId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		return name;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}
}