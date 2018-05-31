/*******************************************************************************
 * Copyright (c) 2017, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.resource.ProjectManager;

public class JUnitStandaloneFileSystem extends TestFileSystem
{
	protected static class JUnitStandaloneTestFile implements TestFile
	{
		protected final @NonNull URI platformURI;
		protected final @NonNull File file;

		public JUnitStandaloneTestFile(@NonNull URI platformURI, @NonNull File file) {
			this.platformURI = platformURI;
			this.file = file;
		}

		@Override
		public @NonNull File getFile() {
			return file;
		}

		@Override
		public @NonNull String getFileString() {
			return String.valueOf(file);
		}

		@Override
		public @NonNull URI getFileURI() {
			return URI.createFileURI(file.toString());
		}

		@SuppressWarnings("null")
		@Override
		public @NonNull String getName() {
			return file.getName();
		}

		@Override
		public @NonNull URI getURI() {
			return platformURI;
		}

		public void mkdir() {
			if (!file.exists()) {
				file.mkdir();
			}
		}

		@Override
		public String toString() {
			return platformURI.toString() + " => " + file.toString();
		}
	}

	protected static class JUnitStandaloneTestFolder extends JUnitStandaloneTestFile implements TestFile
	{
		public JUnitStandaloneTestFolder(@NonNull URI platformURI, @NonNull File file) {
			super(platformURI, file);
			mkdir();
		}

		public @NonNull JUnitStandaloneTestFile createFile(@NonNull String name) {
			URI newURI = platformURI.appendSegment(name);
			File newFile = new File(file, name);
			return new JUnitStandaloneTestFile(newURI, newFile);
		}

		public @NonNull JUnitStandaloneTestFolder createFolder(@NonNull String name) {
			URI newURI = platformURI.appendSegment(name);
			File newFolder = new File(file, name);
			return new JUnitStandaloneTestFolder(newURI, newFolder);
		}
	}

	protected static class JUnitStandaloneTestProject extends JUnitStandaloneTestFolder implements TestProject
	{
		public JUnitStandaloneTestProject(@NonNull URI platformURI, @NonNull File file) {
			super(platformURI, file);
		}

		protected @NonNull JUnitStandaloneTestFile createFilePath(@NonNull String testFilePath) {
			JUnitStandaloneTestFolder node = this;
			@NonNull String[] testFileSegments = testFilePath.split("/");
			if (testFilePath.endsWith("/")) {
				for (int i = 0; i < testFileSegments.length; i++) {
					node = node.createFolder(testFileSegments[i]);
				}
				return node;
			}
			else {
				if (testFileSegments.length > 1) {
					for (int i = 0; i < testFileSegments.length-1; i++) {
						node = node.createFolder(testFileSegments[i]);
					}
				}
				return node.createFile(testFileSegments[testFileSegments.length-1]);
			}
		}

		protected @NonNull JUnitStandaloneTestFolder createFolderPath(@NonNull String testFilePath) {
			JUnitStandaloneTestFolder node = this;
			@NonNull String[] testFileSegments = testFilePath.split("/");
			for (int i = 0; i < testFileSegments.length; i++) {
				node = node.createFolder(testFileSegments[i]);
			}
			return node;
		}

		/*		@Override
		public @NonNull JUnitStandaloneTestFile createInputFile(@NonNull String testFilePath, @NonNull URI sourceURI) throws IOException {
			JUnitStandaloneTestFile testFile = createFilePath(testFilePath);
			InputStream inputStream = URIConverter.INSTANCE.createInputStream(sourceURI);
			FileOutputStream outputStream = new FileOutputStream(testFile.getFile());
			byte bytes[] = new byte[16384];
			for (int count; (count = inputStream.read(bytes)) > 0; ) {
				outputStream.write(bytes, 0, count);
			}
			inputStream.close();
			outputStream.close();
			return testFile;
		} */

		@Override
		public @NonNull ProjectManager createTestProjectManager() {
			return new StandaloneExtraProjectsManager(String.valueOf(file));
		}

		@Override
		public @NonNull IProject getIProject() {
			throw new IllegalStateException();
		}

		@Override
		public @NonNull String getName() {
			return platformURI.segment(1);
		}

		@Override
		public @NonNull JUnitStandaloneTestFile getOutputFile(@NonNull String testFilePath) {
			return createFilePath(testFilePath);
		}

		@Override
		public @NonNull JUnitStandaloneTestFile getOutputFile(@NonNull String testFilePath, @NonNull InputStream inputStream) throws IOException {
			JUnitStandaloneTestFile node = createFilePath(testFilePath);
			if (inputStream != null) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
				Writer writer = new FileWriter(node.getFile());
				for (String line; (line = reader.readLine()) != null; ) {
					writer.write(line);
					writer.write("\n");
				}
				writer.close();
				reader.close();
			}
			return node;
		}

		@Override
		public @NonNull JUnitStandaloneTestFile getOutputFolder(@NonNull String testFilePath) {
			JUnitStandaloneTestFile testFolder = createFilePath(testFilePath);
			testFolder.mkdir();
			return testFolder;
		}
	}

	public static @NonNull JUnitStandaloneFileSystem create(@NonNull TestFileSystemHelper helper) {
		return new JUnitStandaloneFileSystem(helper);
	}

	protected final @NonNull Map<@NonNull String, @NonNull JUnitStandaloneTestProject> projectName2testProject = new HashMap<>();

	public JUnitStandaloneFileSystem(@NonNull TestFileSystemHelper helper) {
		super(helper);
	}

	@Override
	public @NonNull TestProject getTestProject(@NonNull String projectName, boolean cleanProject) {
		JUnitStandaloneTestProject testProject = projectName2testProject.get(projectName);
		if (testProject == null) {
			URI newUri = URI.createPlatformResourceURI(projectName, true);
			File newFile = new File(projectName).getAbsoluteFile();
			if (cleanProject) {
				if (newFile.exists()) {
					TestUtil.deleteDirectory(newFile);
				}
				newFile.mkdirs();
				File dotProjectFile = new File(newFile, ".project");
				if (!dotProjectFile.exists()) {
					helper.createDotProjectFile(newFile, projectName);
					helper.createDotClasspathFile(newFile, projectName);
					helper.createManifestFile(newFile, projectName);
				}
				/*			URI location = projectMap.getLocation(projectName);
				if (location == null) {
					if (!projectMap.addProject(newFile)) {
						throw new IllegalStateException("Failed to create " + projectName);
					}
					location = projectMap.getLocation(projectName);
					EcorePlugin.getPlatformResourceMap().put(projectName, location);
				} */
			}
			testProject = new JUnitStandaloneTestProject(newUri, newFile);
			projectName2testProject.put(projectName, testProject);
		}
		return testProject;
	}

	@Override
	public String toString() {
		return "platform:/resource/* => .test*";
	}
}
