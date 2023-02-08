/*******************************************************************************
 * Copyright (c) 2018, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * A TestFileSystemHelper provides call-backs to assist in building a test file system.
 */
public class TestFileSystemHelper
{
	public TestFileSystemHelper() {
		super();
	}

	protected void appendBuildSpec(@NonNull Writer s) throws IOException {}

	protected void appendNatures(@NonNull Writer s) throws IOException {}

	protected @Nullable File createBuildDotProperties(@NonNull File projectFolder, @NonNull String projectName) {
		return null;
	}

	protected @Nullable File createDotClasspathFile(@NonNull File projectFolder, @NonNull String projectName) {
		return null;
	}

	protected @NonNull File createDotProjectFile(@NonNull File projectFolder, @NonNull String projectName) {
		File file = new File(projectFolder, ".project");
		Writer s;
		try {
			s = new FileWriter(file);
			s.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			s.append("<projectDescription>\n");
			s.append("	<name>" + projectName + "</name>\n");
			appendBuildSpec(s);
			appendNatures(s);
			s.append("</projectDescription>\n");
			s.close();
		} catch (IOException e) {
			throw new WrappedException(e);
		}
		return file;
	}

	public @NonNull File createFile(@NonNull File file, @NonNull String fileContents) {
		Writer s;
		try {
			s = new FileWriter(file);
			s.append(fileContents);
			s.close();
		} catch (IOException e) {
			throw new WrappedException(e);
		}
		return file;
	}

	public void createFiles(@NonNull File projectFolder, @NonNull String projectName) {
		createDotProjectFile(projectFolder, projectName);
		createDotClasspathFile(projectFolder, projectName);
		createManifestFile(projectFolder, projectName);
		createBuildDotProperties(projectFolder, projectName);
	}

	protected @Nullable File createManifestFile(@NonNull File projectFolder, @NonNull String projectName) {
		return null;
	}

	/**
	 * Update the almost empty current projectDescription to add natures/builders/....
	 * Return null to suppress update.
	 */
	public @Nullable IProjectDescription updateProjectDescription(@NonNull IProjectDescription projectDescription) {
		return null;
	}
}
