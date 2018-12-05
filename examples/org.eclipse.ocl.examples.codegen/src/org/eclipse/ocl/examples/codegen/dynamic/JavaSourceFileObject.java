/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
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
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.tools.SimpleJavaFileObject;

import org.eclipse.jdt.annotation.NonNull;

/**
 * JavaSourceFileObject supports use of a File as a Java compilation unit.
 */
public class JavaSourceFileObject extends SimpleJavaFileObject
{
	/**
	 * Load the class whose Java name is qualifiedClassName and whose class file can be found below explicitClassPath.
	 * Subsequent loads of classes such as nested classes whose names are prefixed by qualifiedClassName are also loaded from explicitClassPath.
	 * This method always uses a new ClassLoader to load the class and so ignores any previously cached loads.
	 */
	public static Class<?> loadExplicitClass(@NonNull File explicitClassPath, @NonNull String qualifiedClassName) throws ClassNotFoundException, IOException {
		ClassLoader thisClassLoader = JavaSourceFileObject.class.getClassLoader();
		assert thisClassLoader != null;
		ExplicitClassLoader classLoader = new ExplicitClassLoader(explicitClassPath, qualifiedClassName, thisClassLoader);
		return classLoader.loadClass(qualifiedClassName);
	}

	public JavaSourceFileObject(java.net.@NonNull URI uri) {
		super(uri, Kind.SOURCE);
	}

	@Override
	public @NonNull CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
		char[] buf = new char[4096];
		StringBuilder s = new StringBuilder();
		Reader reader = new FileReader(new File(uri));
		try {
			int len;
			while ((len = reader.read(buf)) > 0) {
				s.append(buf, 0, len);
			}
		}
		finally {
			reader.close();
		}
		return s.toString();
	}
}