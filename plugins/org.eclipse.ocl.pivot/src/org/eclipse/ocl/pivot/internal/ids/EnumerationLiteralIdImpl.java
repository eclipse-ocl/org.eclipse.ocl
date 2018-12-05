/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.TypeId;

public class EnumerationLiteralIdImpl extends UnscopedId implements EnumerationLiteralId
{
	protected final @NonNull EnumerationId parentId;

	public EnumerationLiteralIdImpl(@NonNull EnumerationId parentId, @NonNull String name) {
		super(name);
		this.parentId = parentId;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitEnumerationLiteralId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		return parentId + "::" + name;
	}

	@Override public @NonNull String getMetaTypeName() {
		return TypeId.ENUMERATION_NAME;
	}

	@Override
	public @NonNull EnumerationId getParentId() {
		return parentId;
	}
}