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
package org.eclipse.ocl.pivot.ids;

import org.eclipse.jdt.annotation.NonNull;

/**
 * A NullableTypeId provides a unique identifier for the unspecialized nullable type Nullable(T).
 */
public interface NullableTypeId extends BuiltInTypeId, TemplateableId
{
	@NonNull TypeId getElementTypeId();
	@Override
	@NonNull NullableTypeId getGeneralizedId();
	@Override
	@NonNull String getMetaTypeName();
	@Override
	@NonNull NullableTypeId getSpecializedId(@NonNull BindingsId templateBindings);
	@NonNull NullableTypeId getSpecializedId(@NonNull ElementId... templateBindings);
}