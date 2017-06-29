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
 * A InvalidableTypeId provides a unique identifier for the unspecialized invalidable type Invalidable(T).
 */
public interface InvalidableTypeId extends BuiltInTypeId, TemplateableId
{
	@NonNull TypeId getElementTypeId();
	@Override
	@NonNull InvalidableTypeId getGeneralizedId();
	@Override
	@NonNull String getMetaTypeName();
	@Override
	@NonNull InvalidableTypeId getSpecializedId(@NonNull BindingsId templateBindings);
	@NonNull InvalidableTypeId getSpecializedId(@NonNull ElementId... templateBindings);
}