/*******************************************************************************
 * Copyright (c) 2012, 2019 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.TypeId;

public class SpecializedCollectionTypeIdImpl extends AbstractSpecializedIdImpl<CollectionTypeId> implements CollectionTypeId
{
	private @Nullable TypeId elementTypeId;

	public SpecializedCollectionTypeIdImpl(@NonNull CollectionTypeId generalizedId, @NonNull BindingsId templateBindings) {
		super(generalizedId, templateBindings);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitCollectionTypeId(this);
	}

	@Override
	protected @NonNull CollectionTypeId createSpecializedId(@NonNull BindingsId templateBindings) {
		return new SpecializedCollectionTypeIdImpl(this, templateBindings);
	}

	@Override
	public @NonNull TypeId getElementTypeId() {
		TypeId elementTypeId2 = elementTypeId;
		if (elementTypeId2 == null) {
			elementTypeId = elementTypeId2 = (TypeId) generalizedId.getElementTypeId().specialize(templateBindings);
		}
		return elementTypeId2;
	}

	/**
	 * @since 1.7
	 */
	@Override
	public boolean isTemplated() {
		return true;
	}

	@Override
	public @NonNull CollectionTypeId specialize(@NonNull BindingsId templateBindings) {
		return createSpecializedId(templateBindings);
	}
}