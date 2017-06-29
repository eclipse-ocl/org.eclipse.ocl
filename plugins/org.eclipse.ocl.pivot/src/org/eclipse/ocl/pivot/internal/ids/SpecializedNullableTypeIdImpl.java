/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.NullableTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class SpecializedNullableTypeIdImpl extends AbstractSpecializedIdImpl<@NonNull NullableTypeId> implements NullableTypeId
{
	private @Nullable TypeId elementTypeId;

	public SpecializedNullableTypeIdImpl(@NonNull NullableTypeId generalizedId, @NonNull BindingsId templateBindings) {
		super(generalizedId, templateBindings);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitNullableTypeId(this);
	}

	@Override
	protected @NonNull NullableTypeId createSpecializedId(@NonNull BindingsId templateBindings) {
		return new SpecializedNullableTypeIdImpl(this, templateBindings);
	}

	@Override
	public @NonNull TypeId getElementTypeId() {
		TypeId elementTypeId2 = elementTypeId;
		if (elementTypeId2 == null) {
			elementTypeId = elementTypeId2 = (TypeId) generalizedId.getElementTypeId().specialize(templateBindings);
		}
		return elementTypeId2;
	}

	@Override
	public @NonNull NullableTypeId specialize(@NonNull BindingsId templateBindings) {
		return createSpecializedId(templateBindings);
	}
}