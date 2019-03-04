/*******************************************************************************
 * Copyright (c) 2012, 2019 Willink Transformations and others.
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
import java.util.Collections;
import java.util.List;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

public class OCL2JavaFileObject extends SimpleJavaFileObject
{
	/**
	 * Load the class whose Java name is qualifiedClassName and whose class file can be found below explicitClassPath.
	 * Subsequent loads of classes such as nested classes whose names are prefixed by qualifiedClassName are also loaded from explicitClassPath.
	 * This method always uses a new ClassLoader to load the class and so ignores any previously cached loads.
	 * @param fallBackClassLoader
	 */
	public static Class<?> loadExplicitClass(@NonNull File explicitClassPath, @NonNull String qualifiedClassName) throws ClassNotFoundException, IOException {
		return loadExplicitClass(explicitClassPath, qualifiedClassName, null);
	}
	public static Class<?> loadExplicitClass(@NonNull File explicitClassPath, @NonNull String qualifiedClassName, @Nullable ClassLoader fallBackClassLoader) throws ClassNotFoundException, IOException {
		int lastDot = qualifiedClassName.lastIndexOf(".");
		String qualifiedClassPackage = lastDot >= 0 ? qualifiedClassName.substring(0, lastDot) : qualifiedClassName;
		ExplicitClassLoader classLoader = new ExplicitClassLoader(explicitClassPath, qualifiedClassPackage, fallBackClassLoader);
		return classLoader.loadClass(qualifiedClassName);
	}

	public static @Nullable String saveClass(@NonNull String explicitClassPath, @NonNull String qualifiedName, @NonNull String javaCodeSource/*, @NonNull String... extraClasspathProjects*/) throws MalformedURLException {
		JavaClasspath classpath = new JavaClasspath();
	//	if (extraClasspathProjects != null) {
	//		for (@NonNull String classpathProject : extraClasspathProjects) {
	//			classpath.addURL(new URL(classpathProject));		// FIXME fudge
	//		}
	//	}
		OCL2JavaFileObject compilationUnit = new OCL2JavaFileObject(qualifiedName, javaCodeSource);
		List<@NonNull JavaFileObject> compilationUnits = Collections.singletonList(compilationUnit);
		return JavaFileUtil.compileClasses(compilationUnits, qualifiedName, explicitClassPath, classpath);
	}

	private @NonNull String javaCode;

	/**
	 */
	public OCL2JavaFileObject(@NonNull String qualifiedName, @NonNull String javaCode) {
		super(java.net.URI.create("string:///" +qualifiedName.replaceAll("\\.", "/") + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
		this.javaCode = javaCode ;
	}

	@Override
	public @NonNull CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
		return javaCode ;
	}
}