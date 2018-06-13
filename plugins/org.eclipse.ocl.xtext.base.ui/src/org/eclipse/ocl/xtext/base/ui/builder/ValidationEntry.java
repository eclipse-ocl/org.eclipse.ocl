/*******************************************************************************
 * Copyright (c) 2017, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation based on org.eclipse.xtext.builder.nature.XtextNature
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.ui.builder;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.annotation.NonNull;

/**
 * A ValidationEntry identifies an IFile to be validated and the Marker id for its problems.
 */
public class ValidationEntry
{
	protected final @NonNull IFile file;
	protected final @NonNull String markerId;

	public ValidationEntry(@NonNull IFile file, @NonNull String markerId) {
		this.file = file;
		this.markerId = markerId;
	}

	public void deleteMarkers() throws CoreException {
		file.deleteMarkers(markerId, true, IResource.DEPTH_ZERO);
	}

	public @NonNull IFile getFile() {
		return file;
	}

	public @NonNull String getMarkerId() {
		return markerId;
	}

	@Override
	public String toString() {
		return file + " => " + markerId;
	}
}
