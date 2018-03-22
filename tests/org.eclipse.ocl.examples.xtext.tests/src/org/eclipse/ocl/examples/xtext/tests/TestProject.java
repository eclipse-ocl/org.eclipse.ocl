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
package org.eclipse.ocl.examples.xtext.tests;

import java.io.InputStream;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.resource.ProjectManager;

/**
 * A TestProject provides polymorphism between writeable project areas for a variety of test hartnesses.
 */
public interface TestProject extends TestFile
{
	@NonNull ProjectManager createTestProjectManager();

	/**
	 * Return the Eclipse IProject behind this TestProject.
	 *
	 * @throws IllegalStateException if not an Eclipse file system
	 */
	@NonNull IProject getIProject();
	@NonNull TestFile getOutputFile(@NonNull String testFilePath);
	@NonNull TestFile getOutputFile(@NonNull String testFilePath, @Nullable InputStream inputStream);
	@NonNull TestFile getOutputFolder(@NonNull String testFilePath);
}