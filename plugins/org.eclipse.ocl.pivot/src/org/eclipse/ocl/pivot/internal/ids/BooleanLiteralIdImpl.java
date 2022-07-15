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
import org.eclipse.ocl.pivot.ids.BooleanLiteralId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.TypeId;

/**
 * @since 1.18
 */
public class BooleanLiteralIdImpl extends UnscopedId implements BooleanLiteralId
{
	private static final @NonNull BooleanLiteralIdImpl falseLiteral = new BooleanLiteralIdImpl(false);
	private static final @NonNull BooleanLiteralIdImpl trueLiteral = new BooleanLiteralIdImpl(true);

	public static @NonNull BooleanLiteralId valueOf(boolean b) {
		return b ? trueLiteral : falseLiteral;
	}

	private final @NonNull Boolean value;

	private BooleanLiteralIdImpl(@NonNull Boolean value) {
		super(value.hashCode(), value.toString());
		this.value = value;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitBooleanLiteralId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		return name;
	}

	@Override public @NonNull String getMetaTypeName() {
		return TypeId.BOOLEAN_TYPE_NAME;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public boolean getValue() {
		return value;
	}
}