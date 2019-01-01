/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.java;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Manage the mapping from long fully qualified class names to the short class names that may be used once an import has been provided.
 *
 * (This operates on names rather than classes since classes are not always available when using genModel.)
 */
public interface ImportNameManager
{
	/**
	 * Register the new fully qualified name of a class (optionally including a $suffix) and return a non-null name
	 * that may be used in references.
	 * @param isRequired
	 */
	@NonNull String addImport(@Nullable Boolean isRequired, @NonNull String newLongName);

	@NonNull Map<@NonNull String, @Nullable String> getLong2ShortImportNames();
}
