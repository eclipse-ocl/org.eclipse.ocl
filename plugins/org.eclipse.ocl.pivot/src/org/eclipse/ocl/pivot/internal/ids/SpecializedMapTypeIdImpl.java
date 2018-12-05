/*******************************************************************************
 * Copyright (c) 2015, 2018 Willink Transformations and others.
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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class SpecializedMapTypeIdImpl extends AbstractSpecializedIdImpl<MapTypeId> implements MapTypeId
{
	private @Nullable TypeId keyTypeId;
	private @Nullable TypeId valueTypeId;

	public SpecializedMapTypeIdImpl(@NonNull MapTypeId generalizedId, @NonNull BindingsId templateBindings) {
		super(generalizedId, templateBindings);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitMapTypeId(this);
	}

	@Override
	protected @NonNull MapTypeId createSpecializedId(@NonNull BindingsId templateBindings) {
		return new SpecializedMapTypeIdImpl(this, templateBindings);
	}

	@Override
	public @NonNull TypeId getKeyTypeId() {
		TypeId keyTypeId2 = keyTypeId;
		if (keyTypeId2 == null) {
			keyTypeId = keyTypeId2 = (TypeId) generalizedId.getKeyTypeId().specialize(templateBindings);
		}
		return keyTypeId2;
	}

	@Override
	public @NonNull TypeId getValueTypeId() {
		TypeId valueTypeId2 = valueTypeId;
		if (valueTypeId2 == null) {
			valueTypeId = valueTypeId2 = (TypeId) generalizedId.getValueTypeId().specialize(templateBindings);
		}
		return valueTypeId2;
	}

    @Override
	public @NonNull MapTypeId specialize(@NonNull BindingsId templateBindings) {
    	return createSpecializedId(templateBindings);
	}
}