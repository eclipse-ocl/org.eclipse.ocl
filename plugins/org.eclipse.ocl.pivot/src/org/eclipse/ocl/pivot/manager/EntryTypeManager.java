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
import org.eclipse.ocl.pivot.EntryType;
import org.eclipse.ocl.pivot.values.MapTypeArguments;
import org.eclipse.ocl.pivot.values.TemplateArguments;

/**
 * EntryTypeManager encapsulates the knowledge about known map entry type creation and access.
 *
 * @since 7.0
 */
public interface EntryTypeManager
{
	/**
	 * Return the entry type characterized by the typeArguments if it exists else null.
	 */
	@Nullable EntryType basicGetEntryType(@NonNull MapTypeArguments typeArguments);

	boolean conformsToEntryType(@NonNull EntryType leftType, @Nullable TemplateArguments leftTemplateArguments,
			@NonNull EntryType rightType, @Nullable TemplateArguments rightTemplateArguments, boolean enforceNullity);

	void dispose();

	@NonNull EntryType getCommonEntryType(@NonNull EntryType leftEntryType, @Nullable TemplateArguments leftTemplateArguments,
			@NonNull EntryType rightEntryType, @Nullable TemplateArguments rightTemplateArguments);

	/**
	 * Return, and if necessary create, the entry type characterized by the typeArguments.
	 */
	@NonNull EntryType getEntryType(@NonNull MapTypeArguments typeArguments);

	boolean isEqualToEntryType(@NonNull EntryType leftEntryType, @NonNull EntryType rightEntryType);

	/**
	 * Add a pre-existing EntryType to the managed EntryTypes.
	 */
	void load(@NonNull EntryType asEntryType);
}