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
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.InvalidableTypeId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class GeneralizedInvalidableTypeIdImpl extends GeneralizedTypeIdImpl<@NonNull InvalidableTypeId> implements InvalidableTypeId
{
	public GeneralizedInvalidableTypeIdImpl(@NonNull IdManager idManager, @NonNull String name) {
		super(IdHash.createGlobalHash(CollectionTypeId.class, name), 1, name);
		assert !MAP_NAME.equals(name);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitInvalidableTypeId(this);
	}

	@Override
	protected @NonNull InvalidableTypeId createSpecializedId(@NonNull BindingsId templateBindings) {
		return new SpecializedInvalidableTypeIdImpl(this, templateBindings);
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
	public @NonNull InvalidableTypeId getGeneralizedId() {
		return this;
	}

	@Override
	public @NonNull String getLiteralName() {
		return "INVALIDABLE";
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return TypeId.INVALIDABLE_TYPE_NAME;
	}

	@Override
	public @NonNull InvalidableTypeId specialize(@NonNull BindingsId templateBindings) {
		return getSpecializedId(templateBindings);
	}
}