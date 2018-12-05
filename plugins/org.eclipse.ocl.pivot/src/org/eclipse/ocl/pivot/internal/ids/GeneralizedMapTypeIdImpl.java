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
import org.eclipse.ocl.pivot.ids.IdHash;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class GeneralizedMapTypeIdImpl extends GeneralizedTypeIdImpl<MapTypeId> implements MapTypeId
{
	public GeneralizedMapTypeIdImpl(@NonNull IdManager idManager, @NonNull String name) {
		super(IdHash.createGlobalHash(MapTypeId.class, name), 2, name);
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
	public @NonNull String getDisplayName() {
		return name;
	}

	@Override
	public @NonNull MapTypeId getGeneralizedId() {
		return this;
	}

	@Override
	public @NonNull TemplateParameterId getKeyTypeId() {
		return TypeId.T_1;
	}

	@Override
	public @Nullable String getLiteralName() {
		return "MAP";
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return name + "Type";
	}

	@Override
	public @NonNull TemplateParameterId getValueTypeId() {
		return TypeId.T_2;
	}

    @Override
	public @NonNull MapTypeId specialize(@NonNull BindingsId templateBindings) {
    	return getSpecializedId(templateBindings);
	}
}