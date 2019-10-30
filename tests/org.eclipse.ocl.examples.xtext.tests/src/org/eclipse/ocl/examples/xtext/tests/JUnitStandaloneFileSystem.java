/*******************************************************************************
 * Copyright (c) 2017, 2019 Willink Transformations and others.
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
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

	protected static class JUnitStandaloneTestFolder extends JUnitStandaloneTestFile implements TestFolder
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

		@Override
		public @NonNull IContainer getIContainer() {
			throw new IllegalStateException();
		}
	}

	protected static class JUnitStandaloneTestProject extends JUnitStandaloneTestFolder implements TestProject
	{
		public JUnitStandaloneTestProject(@NonNull URI platformURI, @NonNull File file) {
			super(platformURI, file);
		}

		@Override
		public @NonNull TestFile copyFile(@NonNull URIConverter uriConverter, @Nullable TestFolder testFolder, @NonNull URI sourceURI) throws IOException {
			InputStream inputStream = uriConverter.createInputStream(sourceURI);
			String lastSegment = sourceURI.lastSegment();
			assert lastSegment != null;
			if (testFolder != null) {
				lastSegment = testFolder.getName() + "/" + lastSegment;
			}
			return getOutputFile(lastSegment, inputStream);
		}

		@Override
		public @NonNull TestFile copyFiles(@NonNull ProjectManager projectManager, @Nullable TestFolder testFolder, @NonNull URI sourceFolderURI, @NonNull String @NonNull ... fileNames) throws IOException {
			ResourceSet resourceSet = new ResourceSetImpl();
			projectManager.initializeResourceSet(resourceSet);
			URIConverter uriConverter = resourceSet.getURIConverter();
			JUnitStandaloneTestFile firstOutputFile = null;
			for (@NonNull String fileName : fileNames) {
				URI sourceURI = sourceFolderURI.appendSegment(fileName);
				InputStream inputStream = uriConverter.createInputStream(sourceURI);
				String lastSegment = sourceURI.lastSegment();
				assert lastSegment != null;
				if (testFolder != null) {
					lastSegment = testFolder.getName() + "/" + lastSegment;
				}
				JUnitStandaloneTestFile outputFile = getOutputFile(lastSegment, inputStream);
				if (firstOutputFile == null) {
					firstOutputFile = outputFile;
				}
			}
			assert firstOutputFile != null;
			return firstOutputFile;
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
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			Writer writer = new OutputStreamWriter(new FileOutputStream(node.getFile()), "UTF-8");
			for (String line; (line = reader.readLine()) != null; ) {
				writer.write(line);
				writer.write("\n");
			}
			writer.close();
			reader.close();
			return node;
		}

		@Override
		public @NonNull JUnitStandaloneTestFolder getOutputFolder(@NonNull String testFilePath) {
			JUnitStandaloneTestFolder testFolder = createFolderPath(testFilePath);
			testFolder.mkdir();
			return testFolder;
		}
	}

	public static @NonNull JUnitStandaloneFileSystem create(@NonNull TestFileSystemHelper helper, @NonNull String pathFromCurrentWorkingDirectoryToFileSystem) {
		return new JUnitStandaloneFileSystem(helper, pathFromCurrentWorkingDirectoryToFileSystem);
	}

	protected final @NonNull Map<@NonNull String, @NonNull JUnitStandaloneTestProject> projectName2testProject = new HashMap<>();

	public JUnitStandaloneFileSystem(@NonNull TestFileSystemHelper helper, @NonNull String pathFromCurrentWorkingDirectoryToFileSystem) {
		super(helper, pathFromCurrentWorkingDirectoryToFileSystem);
	}

	@Override
	public @NonNull TestProject getTestProject(@NonNull String projectName, boolean cleanProject) {
		JUnitStandaloneTestProject testProject = projectName2testProject.get(projectName);
		if (testProject == null) {
			URI newUri = URI.createPlatformResourceURI(projectName, true);
			File projectFolder = new File(pathFromCurrentWorkingDirectoryToFileSystem + projectName).getAbsoluteFile();
			if (cleanProject) {
				if (projectFolder.exists()) {
					TestUtil.deleteDirectory(projectFolder);
					try {
						Thread.sleep(1000);		// Delay before recreating folders
					} catch (InterruptedException e) {}
				}
				projectFolder.mkdirs();
				File settingsFolder = new File(projectFolder, ".settings");
				settingsFolder.mkdir();
				File resourcesFile = new File(settingsFolder, "org.eclipse.core.resources.prefs");
				helper.createFile(resourcesFile, getResourcesPreferenceContents());
				File runtimeFile = new File(settingsFolder, "org.eclipse.core.runtime.prefs");
				helper.createFile(runtimeFile, getRuntimePreferenceContents());
				File dotProjectFile = new File(projectFolder, ".project");
				if (!dotProjectFile.exists()) {
					helper.createDotProjectFile(projectFolder, projectName);
					helper.createDotClasspathFile(projectFolder, projectName);
					helper.createManifestFile(projectFolder, projectName);
					helper.createBuildDotProperties(projectFolder, projectName);
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
			testProject = new JUnitStandaloneTestProject(newUri, projectFolder);
			projectName2testProject.put(projectName, testProject);
		}
		return testProject;
	}

	@Override
	public String toString() {
		return "platform:/resource/* => .test*";
	}
}
