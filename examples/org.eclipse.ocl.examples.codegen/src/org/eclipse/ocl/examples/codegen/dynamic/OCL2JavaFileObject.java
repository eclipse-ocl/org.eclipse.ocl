/*******************************************************************************
 * Copyright (c) 2012, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.dynamic;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.library.LibraryOperation;

public class OCL2JavaFileObject extends SimpleJavaFileObject
{
	/** @deprecated use saveClass/some-class-loader-loadClass */
	@Deprecated
	public static Class<?> loadClass(@NonNull String qualifiedName, @NonNull String javaCodeSource) throws Exception {
		saveClass(qualifiedName, javaCodeSource);
		Class<?> testClass = Class.forName(qualifiedName);
		return testClass;
	}

	/**
	 * Load the class whose Java name is qualifiedClassName and whose class file can be found below explicitClassPath.
	 * Subsequent loads of classes such as nested classes whose names are prefixed by qualifiedClassName are also loaded from explicitClassPath.
	 * This method always uses a new ClassLoader to load the class and so ignores any previously cached loads.
	 */
	public static Class<?> loadExplicitClass(@NonNull File explicitClassPath, @NonNull String qualifiedClassName) throws ClassNotFoundException, IOException {
		ExplicitClassLoader classLoader = new ExplicitClassLoader(explicitClassPath, qualifiedClassName);
		return classLoader.loadClass(qualifiedClassName);
	}

	/** @deprecated use saveClass/some-class-loader-loadClass */
	@Deprecated
	public static @Nullable LibraryOperation loadLibraryOperationClass(@NonNull String qualifiedName, @NonNull String javaCodeSource) throws Exception {
		saveClass(qualifiedName, javaCodeSource);
		Class<?> testClass = Class.forName(qualifiedName);
		return (LibraryOperation) testClass.newInstance();
		//		Field testField = testClass.getField("INSTANCE");
		//		System.out.printf("%6.3f get\n", 0.001 * (System.currentTimeMillis()-base));
		//		return (LibraryOperation) testField.get(null);
	}

	/** @deprecated provide explicitClassPath to avoid default directory indeterminacy */
	@Deprecated
	public static void saveClass(@NonNull String qualifiedName, @NonNull String javaCodeSource) {
		saveClass("bin", qualifiedName, javaCodeSource);
	}

	public static @Nullable String saveClass(@NonNull String explicitClassPath, @NonNull String qualifiedName, @NonNull String javaCodeSource) {
		List<@NonNull JavaFileObject> compilationUnits = Collections.singletonList(
			new OCL2JavaFileObject(qualifiedName, javaCodeSource));
		return JavaFileUtil.compileClasses(compilationUnits, qualifiedName, explicitClassPath, null);
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