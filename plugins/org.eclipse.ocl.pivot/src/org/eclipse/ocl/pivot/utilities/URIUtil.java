/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.utilities;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;

/**
 * @since 1.4
 */
public class URIUtil
{
	public static final URI PLATFORM_RESOURCE = URI.createPlatformResourceURI("/", false);

	/**
	 * Convert uri to a form whereby it can be resolved relocatably within Eclipse.
	 */
	public static @NonNull URI getAbsoluteOrPlatformURI(@NonNull URI uri) {
		@SuppressWarnings("unused") boolean isHierarchical = uri.isHierarchical();
		@SuppressWarnings("unused") boolean isPlatform = uri.isPlatform();
		@SuppressWarnings("unused") boolean isRelative = uri.isRelative();
		if (uri.isRelative()) {
			return URI.createPlatformResourceURI(uri.toString(), true);
		}
		return uri;
	}

	/**
	 * If uri is for an AS resource, return is non-AS equivalent. Otherwise just return uri.
	 */
	public static @NonNull URI getNonASURI(@NonNull URI uri) {
		if (PivotUtilInternal.isASURI(uri)) {
			uri = PivotUtilInternal.getNonASURI(uri);
		}
		return uri;
	}

	/**
	 * Return the Eclipse IFile corresponding to a URI
	 */
	public static @Nullable IFile getResolvedFile(@NonNull URI uri) {
		if (uri.isHierarchical()) {
			if (uri.isRelative() || (uri = uri.deresolve(PLATFORM_RESOURCE)).isRelative()) {
				IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
				IFile file = workspaceRoot.getFile(new Path(uri.toString()));
				return file;
			}
		}
		return null;
	}
}
