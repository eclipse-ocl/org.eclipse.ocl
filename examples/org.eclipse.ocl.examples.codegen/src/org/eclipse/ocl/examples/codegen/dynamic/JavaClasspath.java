/*******************************************************************************
 * Copyright (c) 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.dynamic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

/**
 * JavaClasspath maintains a list of classpath entries providing some flexibility in the way in
 * which a path is defined, pissibly from an example class on the entry, and in the way in whivh the
 * path is subsequently used.
 */
public class JavaClasspath
{
	/**
	 * URLs in the format returned by Class.getResource, save that jar:...! wrapping is removed.
	 * The entries are therefore fully-protocoled paths terminating typically in either *.jar or /bin.
	 */
	private final @NonNull List<@NonNull URL> urls = new ArrayList<>();

	/**
	 * Add the classpath by which loadedClass was loaded to the list of classpath elememnts.
	 */
	public void addClass(@NonNull Class<?> loadedClass) {
		try {
			String modifiedName = "/" + loadedClass.getName().replace('.', '/') + ".class";
			URL projectURL = loadedClass.getResource(modifiedName);
			if (projectURL != null) {
				if (EMFPlugin.IS_ECLIPSE_RUNNING) {
						projectURL = FileLocator.resolve(projectURL);
				}
				String classpathString = projectURL.toString();
				classpathString = classpathString.substring(0, classpathString.length() - modifiedName.length());
				if (classpathString.startsWith("jar:") && classpathString.endsWith("!")) {
					classpathString = classpathString.substring(4, classpathString.length()-1);
				}
				addURL(new URL(classpathString));
			}
		} catch (IOException e) {		// Never happens
			// e.printStackTrace();
		}
	}

	/**
	 * Add the File to the list of classpath elememnts.
	 */
	public void addFile(@NonNull File file) throws MalformedURLException {
		URI fileURI = URI.createFileURI(file.toString());
		addURL(new URL(fileURI.toString()));
	}

/*	public void addProject(@NonNull String projectName) throws IOException {
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
			IResource project = workspaceRoot.findMember(projectName);
			if (project != null) {
				String location = String.valueOf(project.getLocation());
				File projectFile = new File(location);
				File outputClassPath = JavaFileUtil.getOutputClassPath(projectFile);
				String path = location + "/" + ""; //TEST_BIN_FOLDER_NAME;
				if (outputClassPath != null) {
					addFile(outputClassPath);
				}
				return;
			}
			String path = null;
			Bundle bundle = Platform.getBundle(projectName);
			if (bundle != null) {
				try {
					File bundleFile = FileLocator.getBundleFile(bundle);
					if (bundleFile.isDirectory()) {
					//	File outputPath = getOutputClassPath(bundleFile);
					//	if (outputPath != null) {
					//		addFile(outputPath);
					//	}
					}

				//	File bundleFilePath = getOSGIClassPath(bundle);
				//	location = bundle.getLocation();
				//	path = bundleFilePath.toString();
				} catch (IOException e) {
					// Doesn't fail for sensible names.
				}
			}
			if (path == null) {					// platform:/resource
			}
		}
	} */

	/**
	 * Add the optionally protocolled string to the list of classpath elememnts.
	 *
	 * @throws MalformedURLException
	 */
	public void addString(@NonNull String string) throws MalformedURLException {
		URI uri = URI.createURI(string);
		addURI(uri);
	}

	/**
	 * Add the URI to the list of classpath elememnts.
	 *
	 * @throws MalformedURLException
	 */
	public void addURI(@NonNull URI uri) throws MalformedURLException {
		addURL(new URL(uri.isFile() ? uri.toString() : uri.toFileString()));
	}

	/**
	 * Add the URL to the list of classpath elememnts.
	 */
	public void addURL(@NonNull URL classpathURL) {
		urls.add(classpathURL);
	}

	public @NonNull String getClasspath() {
		StringBuilder s = new StringBuilder();
		String pathSeparator = null;
		for (@NonNull URL url : urls) {
			String classpathElement = getClasspathElement(url);
			if (classpathElement != null) {
				if (pathSeparator == null) {
					pathSeparator = System.getProperty("path.separator");
				}
				else {
					s.append(pathSeparator);
				}
				s.append(classpathElement);
			}
		}
		return s.toString();
	}

	public @NonNull List<@NonNull String> getClasspathElements() {
		List<@NonNull String> classpathProjectList = new ArrayList<>();
		for (@NonNull URL url : urls) {
			String classpathElement = getClasspathElement(url);
			if (classpathElement != null) {
				classpathProjectList.add(classpathElement);
			}
		}
		return classpathProjectList;
	}

	private @Nullable String getClasspathElement(@NonNull URL classpathURL) {
		URI classpathURI = URI.createURI(classpathURL.toString());
		return classpathURI.isFile() ? classpathURI.toFileString() : classpathURI.toString();
	}

	public @NonNull Iterable<@NonNull URL> getClasspathURLs() {
		return urls;
	}

	public int size() {
		return urls.size();
	}

	@Override
	public @NonNull String toString() {
		StringBuilder s = new StringBuilder();
		for (@NonNull URL url : urls) {
			if (s.length() > 0) {
				s.append("\n");
			}
			s.append(url.toString());
		}
		return s.toString();
	}
}