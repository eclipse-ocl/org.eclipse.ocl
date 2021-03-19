/*******************************************************************************
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.resource.ProjectManager;

/**
 * A TestFileSystemOwner provides an interface that providers of a TestFileSystem may implement on behalf of genertic test clients.
 */
public interface TestFileSystemOwner
{
	/**
	 * Return the URI of a file in the test harness models folder.
	 */
	@NonNull URI getModelsURI(@NonNull String filePath);

	/**
	 * Return the URI of a file in the test project based on the file name of the inputURI and
	 * file extension replaced by fileExtension.
	 */
	@NonNull URI getTestURIWithExtension(@NonNull URI inputURI, @Nullable String fileExtension);

	@NonNull TestProject getTestProject();

	@NonNull ProjectManager getTestProjectManager();
}
