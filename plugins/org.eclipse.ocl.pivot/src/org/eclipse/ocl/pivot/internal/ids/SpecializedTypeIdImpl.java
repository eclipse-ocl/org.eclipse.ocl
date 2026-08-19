/*******************************************************************************
 * Copyright (c) 2012, 2022 Willink Transformations and others.
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
import org.eclipse.ocl.pivot.ids.BindingsId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.NestedTypeId;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.TemplateParameterId;
import org.eclipse.ocl.pivot.ids.TemplateableTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;

public /*abstract*/ class SpecializedTypeIdImpl extends AbstractSpecializedIdImpl<@NonNull TemplateableTypeId> implements TemplateableTypeId, NestedTypeId
{
	public SpecializedTypeIdImpl(@NonNull TemplateableTypeId generalizedId, @NonNull BindingsId templateBindings) {
		super(generalizedId, templateBindings);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		throw new UnsupportedOperationException();		// Must be overridden since logically abstract
	}

	@Override
	protected @NonNull TemplateableTypeId createSpecializedId(@NonNull BindingsId templateBindings) {
		throw new UnsupportedOperationException();		// FIXME migrate to generalized only
//		return new SpecializedTypeIdImpl(this, templateBindings);
	}

//	public @NonNull String getDisplayName() {
//		return parent + "::" + typeParameters;
//	}

	@Override
	public @NonNull PackageId getParent() {
		return ((NestedTypeId)generalizedId).getParent();
	}

	@Override
	public @NonNull TemplateableTypeId specialize(@NonNull BindingsId templateBindings) {
    	return createSpecializedId(templateBindings);
	}
}