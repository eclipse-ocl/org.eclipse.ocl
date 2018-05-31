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

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

public class JUnitPluginFileSystem extends TestFileSystem
{
	protected static class JUnitPluginTestFile implements TestFile
	{
		protected final @NonNull URI platformURI;
		protected final @NonNull File file;

		public JUnitPluginTestFile(@NonNull URI platformURI, @NonNull File file) {
			this.platformURI = platformURI;
			this.file = file;
		}

		//		public @NonNull JUnitPluginTestFile createFile(@NonNull String name) {
		//			URI newURI = platformURI.appendSegment(name);
		//			File newFile = new File(file, name);
		//			return new JUnitPluginTestFile(newURI, newFile);
		//		}

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

		@Override
		public String toString() {
			return platformURI.toString() + " => " + file.toString();
		}
	}

	protected static class JUnitPluginTestFolder extends JUnitPluginTestFile implements TestFile
	{
		protected final @NonNull IContainer container;

		public JUnitPluginTestFolder(@NonNull URI platformURI, @NonNull IContainer container) {
			super(platformURI, ClassUtil.nonNullState(container.getLocation().toFile()));
			this.container = container;
		}

		public @NonNull JUnitPluginTestFile createFile(@NonNull String name, @Nullable InputStream inputStream) {
			URI newURI = platformURI.appendSegment(name);
			IFile newFile = ClassUtil.nonNullState(container.getFile(new Path(name)));
			if (inputStream != null) {
				try {
					newFile.create(inputStream, true, null);
				} catch (CoreException e) {
					throw new WrappedException(e);
				}
			}
			return new JUnitPluginTestFile(newURI, ClassUtil.nonNullState(newFile.getLocation().toFile()));
		}

		public @NonNull JUnitPluginTestFolder createFolder(@NonNull String name) {
			URI newURI = platformURI.appendSegment(name);
			IFolder newFolder = ClassUtil.nonNullState(container.getFolder(new Path(name)));
			if (!newFolder.exists()) {
				//				container.refreshLocal(IResource.DEPTH_INFINITE, null);
				try {
					newFolder.create(true, true, null);
				} catch (CoreException e) {
					throw new WrappedException(e);
				}
				//				container.refreshLocal(IResource.DEPTH_INFINITE, null);
				//				newFolder.getFile("dummy.txt").create(new StringInputStream("xx"), true, null);
			}
			return new JUnitPluginTestFolder(newURI, newFolder);
		}
	}

	protected static class JUnitPluginTestProject extends JUnitPluginTestFolder implements TestProject
	{
		//		protected @NonNull JUnitPluginFileSystem testFileSystem;
		protected @NonNull IProject javaProject;

		public JUnitPluginTestProject(@NonNull JUnitPluginFileSystem testFileSystem, @NonNull URI platformURI, @NonNull IProject javaProject) {
			super(platformURI, javaProject);
			//			this.testFileSystem = testFileSystem;
			this.javaProject = javaProject;
		}

		protected @NonNull JUnitPluginTestFile createFilePath(@NonNull String testFilePath, @Nullable InputStream inputStream) {
			JUnitPluginTestFolder node = this;
			@NonNull String[] testFileSegments = testFilePath.split("/");
			if (testFilePath.endsWith("/")) {
				for (int i = 0; i < testFileSegments.length; i++) {
					node = node.createFolder(testFileSegments[i]);
				}
				TestUIUtil.flushEvents();
				return node;
			}
			else {
				if (testFileSegments.length > 1) {
					for (int i = 0; i < testFileSegments.length-1; i++) {
						node = node.createFolder(testFileSegments[i]);
					}
				}
				JUnitPluginTestFile createFile = node.createFile(testFileSegments[testFileSegments.length-1], inputStream);
				TestUIUtil.flushEvents();
				return createFile;
			}
			/*			@NonNull String[] testFileSegments = testFilePath.split("/");
			int lastIndex = testFileSegments.length-1;
			JUnitPluginTestFolder testFolder;
			try {
				testFolder = createFolderPath(testFileSegments, lastIndex);
				return testFolder.createFile(testFileSegments[lastIndex], null);
			} catch (CoreException e) {
				throw new WrappedException(e);
			} */
		}

		//		protected @NonNull JUnitPluginTestFile createFilePath(@NonNull String testFilePath, @Nullable InputStream inputStream) throws CoreException {
		//			JUnitPluginTestFolder testFolder = createFolderPath(testFilePath);
		//			@NonNull String[] testFileSegments = testFilePath.split("/");
		//			return testFolder.createFile(testFileSegments[testFileSegments.length-1], inputStream);
		//		}

		protected @NonNull JUnitPluginTestFolder createFolderPath(@NonNull String[] testFileSegments, int lastIndex) throws CoreException {
			JUnitPluginTestFolder testFolder = this;
			for (int i = 0; i < lastIndex; i++) {
				@NonNull String pathSegment = testFileSegments[i];
				testFolder = testFolder.createFolder(pathSegment);
			}
			return testFolder;
		}

		/*		@Override
		public @NonNull JUnitPluginTestFile createInputFile(@NonNull String testFilePath, @NonNull URI sourceURI) throws IOException, CoreException {
			@NonNull String[] testFileSegments = testFilePath.split("/");
			int lastIndex = testFileSegments.length-1;
			JUnitPluginTestFolder testFolder = createFolderPath(testFileSegments, lastIndex);
			//
			ResourceSet resourceSet = new ResourceSetImpl();
			InputStream inputStream = resourceSet.getURIConverter().createInputStream(sourceURI);
			JUnitPluginTestFile testFile = testFolder.createFile(testFileSegments[lastIndex], inputStream);
			inputStream.close();
			return testFile;
		} */

		@Override
		public @NonNull ProjectManager createTestProjectManager() {
			return new ProjectMap(false);
		}

		@Override
		public @NonNull IProject getIProject() {
			return javaProject;
		}

		@Override
		public @NonNull String getName() {
			return platformURI.segment(1);
		}

		@Override
		public @NonNull JUnitPluginTestFile getOutputFile(@NonNull String testFilePath) {
			return createFilePath(testFilePath, null);
		}

		@Override
		public @NonNull JUnitPluginTestFile getOutputFile(@NonNull String testFilePath, @Nullable InputStream inputStream) {
			return createFilePath(testFilePath, inputStream);
		}

		@Override
		public @NonNull JUnitPluginTestFolder getOutputFolder(@NonNull String testFilePath) {
			@NonNull String[] testFileSegments = testFilePath.split("/");
			try {
				return createFolderPath(testFileSegments, testFileSegments.length);
			} catch (CoreException e) {
				throw new WrappedException(e);
			}
		}
	}

	public static @NonNull JUnitPluginFileSystem create(@NonNull TestFileSystemHelper helper) {
		TestUIUtil.closeIntro();
		return new JUnitPluginFileSystem(helper);
	}

	protected final @NonNull Map<@NonNull String, @NonNull JUnitPluginTestProject> projectName2testProject = new HashMap<>();

	public JUnitPluginFileSystem(@NonNull TestFileSystemHelper helper) {
		super(helper);
	}

	@Override
	public @NonNull TestProject getTestProject(@NonNull String projectName, boolean cleanProject) {
		JUnitPluginTestProject testProject = projectName2testProject.get(projectName);
		if (testProject == null) {
			IWorkspace workspace = ResourcesPlugin.getWorkspace();
			IWorkspaceRoot root = workspace.getRoot();
			IProject project = root.getProject(projectName);
			assert project != null;
			if (cleanProject) {
				try {
					if (!project.exists()) {
						project.create(null);
					}
					if (!project.isOpen()) {
						project.open(null);
					}
					IProjectDescription projectDescription = project.getDescription();
					if (projectDescription != null) {
						projectDescription = helper.updateProjectDescription(projectDescription);
						if (projectDescription != null) {
							project.setDescription(projectDescription, null);
						}
					}
					project.refreshLocal(IResource.DEPTH_INFINITE, null);
					TestUIUtil.flushEvents();
				} catch (CoreException e) {
					throw new WrappedException(e);
				}
			}
			testProject = new JUnitPluginTestProject(this, URI.createPlatformResourceURI(projectName, true), project);
			projectName2testProject.put(projectName, testProject);
			/*			URI location = projectMap.getLocation(projectName);
			if ((location == null) || location.isPlatform()) {
				projectMap.addProject(javaProject);
			} */
		}
		return testProject;
	}

	//	@Override
	//	public String toString() {
	//		return javaProject.toString();
	//	}
}
