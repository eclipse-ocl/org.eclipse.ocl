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
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.NullableTypeId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class GeneralizedNullableTypeIdImpl extends GeneralizedTypeIdImpl<@NonNull NullableTypeId> implements NullableTypeId
{
	public GeneralizedNullableTypeIdImpl(@NonNull IdManager idManager, @NonNull String name) {
		super(IdHash.createGlobalHash(CollectionTypeId.class, name), 1, name);
		assert !MAP_NAME.equals(name);
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
	public @NonNull String getDisplayName() {
		StringBuilder s = new StringBuilder();
		s.append(name);
		s.append(IdManager.getParametersId(TypeId.T_1));
		return s.toString();
	}

	@Override
	public @NonNull TemplateParameterId getElementTypeId() {
		return TypeId.T_1;
	}

	@Override
	public @NonNull NullableTypeId getGeneralizedId() {
		return this;
	}

	@Override
	public @Nullable String getLiteralName() {
		return "NULLABLE";
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return TypeId.NULLABLE_TYPE_NAME;
	}

	@Override
	public @NonNull NullableTypeId specialize(@NonNull BindingsId templateBindings) {
		return getSpecializedId(templateBindings);
	}
}