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
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.ids.UnlimitedNaturalLiteralId;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

/**
 * @since 1.18
 */
public class UnlimitedNaturalLiteralIdImpl extends UnscopedId implements UnlimitedNaturalLiteralId
{
	private static final @NonNull Map<@NonNull UnlimitedNaturalValue, @NonNull UnlimitedNaturalLiteralId> value2id = new HashMap<>();
	private static final @NonNull UnlimitedNaturalLiteralId unlimitedLiteral = new UnlimitedNaturalLiteralIdImpl(ValueUtil.UNLIMITED_VALUE);

	public static @NonNull UnlimitedNaturalLiteralId valueOf(@NonNull UnlimitedNaturalValue value) {
		if (value.isUnlimited()) {
			return unlimitedLiteral;
		}
		UnlimitedNaturalLiteralId unlimitedNaturalLiteralId = value2id.get(value);
		if (unlimitedNaturalLiteralId == null) {
			unlimitedNaturalLiteralId = new UnlimitedNaturalLiteralIdImpl(value);
		}
		return unlimitedNaturalLiteralId;
	}

	private final @NonNull UnlimitedNaturalValue value;

	private UnlimitedNaturalLiteralIdImpl(@NonNull UnlimitedNaturalValue value) {
		super(value.hashCode(), value.toString());
		this.value = value;
		value2id.put(value, this);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitUnlimitedNaturalLiteralId(this);
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
	public @NonNull UnlimitedNaturalValue getValue() {
		return value;
	}
}