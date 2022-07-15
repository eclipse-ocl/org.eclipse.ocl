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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.IntegerLiteralId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;

/**
 * @since 1.18
 */
public class IntegerLiteralIdImpl extends UnscopedId implements IntegerLiteralId
{
	private static final @NonNull Map<@NonNull IntegerValue, @NonNull IntegerLiteralId> value2id = new HashMap<>();
	private static final @NonNull IntegerLiteralId zeroLiteral = new IntegerLiteralIdImpl(ValueUtil.ZERO_VALUE);

	public static @NonNull IntegerLiteralId valueOf(@NonNull IntegerValue value) {
		if (value.signum() == 0) {
			return zeroLiteral;
		}
		IntegerLiteralId integerLiteralId = value2id.get(value);
		if (integerLiteralId == null) {
			integerLiteralId = new IntegerLiteralIdImpl(value);
		}
		return integerLiteralId;
	}

	private final @NonNull IntegerValue value;

	private IntegerLiteralIdImpl(@NonNull IntegerValue value) {
		super(value.hashCode(), value.toString());
		this.value = value;
		value2id.put(value, this);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitIntegerLiteralId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		return name;
	}

	@Override public @NonNull String getMetaTypeName() {
		return TypeId.PRIMITIVE_TYPE_NAME;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @NonNull IntegerValue getValue() {
		return value;
	}
}