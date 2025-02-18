/*******************************************************************************
 * Copyright (c) 2019, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.dynamic;

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
import org.eclipse.ocl.pivot.utilities.UniqueList;

/**
 * JavaClasspath maintains a list of classpath entries providing some flexibility in the way in
 * which a path is defined, possibly from an example class on the entry, and in the way in which the
 * path is subsequently used. If the entries are defined from loaded classes, a further list
 * of their classloaders may be used to define additional classes.
 * @since 1.23
 */
public class JavaClasspath extends ClassLoader
{
	/**
	 * URLs in the format returned by Class.getResource, save that jar:...! wrapping is removed.
	 * The entries are therefore fully-protocoled paths terminating typically in either *.jar or /bin.
	 */
	private final @NonNull List<@NonNull URL> urls = new UniqueList<>();

	/**
	 * The ClassLoaders that can load the example classpath elements.
	 */
	private final @NonNull List<@NonNull ClassLoader> classLoaders = new UniqueList<>();

	/**
	 * Add the the bundle that loaded loadedClass to the list of URLs from which a compiler class path is
	 * generated and to the list of classloaders that may be used by defineClass.
	 */
	public void addBundleForClass(@NonNull Class<?> loadedClass) {
		try {
			ClassLoader classLoader = loadedClass.getClassLoader();
			assert classLoader != null;
			classLoaders.add(classLoader);
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
				addURL(new URL(classpathString));		// XXX superclasses too
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

	public @NonNull Class<?> defineClass(@NonNull String name, byte @NonNull [] bytes) {
		Class<?> aClass = defineClass(name, bytes, 0, bytes.length);
		assert aClass != null;
		return aClass;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		for (ClassLoader classLoader : classLoaders) {
			try {
				Class<?> theClass = classLoader.loadClass(name);
				if (theClass != null) {
					return theClass;
				}
			}
			catch (ClassNotFoundException e) {}
		}
		return super.findClass(name);
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

	public @NonNull List<@NonNull URL> getClasspathURLs() {
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