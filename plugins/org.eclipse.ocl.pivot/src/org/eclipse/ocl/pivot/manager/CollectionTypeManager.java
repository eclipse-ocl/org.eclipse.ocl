/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.manager;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.values.CollectionTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

/**
 * CollectionTypeManager encapsulates the knowledge about known collection types.
 *
 * @since 7.0
 */
public interface CollectionTypeManager
{
	/**
	 * Return the collection type characterized by the typeArguments if it exists else null.
	 */
	@Nullable CollectionType basicGetCollectionType(@NonNull CollectionTypeArguments typeArguments);

	boolean conformsToCollectionType(@NonNull CollectionType leftType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
			@NonNull CollectionType rightType, @Nullable TemplateParameterSubstitutions rightSubstitutions, boolean enforceNullity);

	void dispose();

	/**
	 * Return, and if necessary create, the collection type characterized by the typeArguments.
	 */
	@NonNull CollectionType getCollectionType(@NonNull CollectionTypeArguments typeArguments);

	@NonNull CollectionType getCollectionType(@NonNull CollectionTypeId collectionTypeId);

	@NonNull CollectionType getCommonCollectionType(@NonNull CollectionType leftCollectionType, @Nullable TemplateParameterSubstitutions leftSubstitutions,
				@NonNull CollectionType rightCollectionType, @Nullable TemplateParameterSubstitutions rightSubstitutions);

	boolean isEqualToCollectionType(@NonNull CollectionType leftCollectionType, @NonNull CollectionType rightCollectionType);
}