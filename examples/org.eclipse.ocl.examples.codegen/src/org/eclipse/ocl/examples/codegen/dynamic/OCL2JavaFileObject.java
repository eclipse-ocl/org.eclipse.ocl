/*******************************************************************************
 * Copyright (c) 2012, 2016 Willink Transformations and others.
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.library.LibraryOperation;

public class OCL2JavaFileObject extends SimpleJavaFileObject
{
//	public static long base = System.currentTimeMillis();
	
	private static @Nullable JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
//	private static @Nullable StandardJavaFileManager stdFileManager = compiler != null ? compiler.getStandardFileManager(null, Locale.getDefault(), null) : null;
//	private static List<String> compilationOptions = Arrays.asList("-d", "bin", "-source", "1.5", "-target", "1.5", "-g");
//	private static List<String> compilationOptions = Arrays.asList("-d", "bin", "-source", "1.6", "-target", "1.6", "-g");
//	private static List<String> compilationOptions = Arrays.asList("-d", "bin", "-g");
	
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
	public static void saveClass(@NonNull String qualifiedName, @NonNull String javaCodeSource) throws Exception {
		saveClass("bin", qualifiedName, javaCodeSource);
	}

	public static void saveClass(@NonNull String explicitClassPath, @NonNull String qualifiedName, @NonNull String javaCodeSource) throws IOException {
		JavaCompiler compiler2 = compiler;
		if (compiler2 == null) {
			throw new IllegalStateException("No JavaCompiler provided by the Java platform - you need to use a JDK rather than a JRE");
		}
		StandardJavaFileManager stdFileManager2 = compiler2.getStandardFileManager(null, Locale.getDefault(), null);
		if (stdFileManager2 == null) {
			throw new IllegalStateException("No StandardJavaFileManager provided by the Java platform");
		}
//		System.out.printf("%6.3f start\n", 0.001 * (System.currentTimeMillis()-base));
		List<? extends JavaFileObject> compilationUnits = Collections.singletonList(
				new OCL2JavaFileObject(qualifiedName, javaCodeSource));
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		
		List<String> compilationOptions = Arrays.asList("-d", explicitClassPath, "-g");

//		System.out.printf("%6.3f getTask\n", 0.001 * (System.currentTimeMillis()-base));
		CompilationTask compilerTask = compiler2.getTask(null, stdFileManager2,
				diagnostics, compilationOptions, null, compilationUnits);
//		System.out.printf("%6.3f call\n", 0.001 * (System.currentTimeMillis()-base));
		if (!compilerTask.call()) {
			StringBuilder s = new StringBuilder();
			for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				s.append("\n" + diagnostic);
			}
			if (s.length() > 0) {
				throw new IOException("Failed to compile " + qualifiedName + s.toString());
			}
			System.out.println("Compilation of " + qualifiedName + " returned false but no diagnostics");
		}
//		System.out.printf("%6.3f close\n", 0.001 * (System.currentTimeMillis()-base));
		stdFileManager2.close();		// Close the file manager which re-opens automatically
//		System.out.printf("%6.3f forName\n", 0.001 * (System.currentTimeMillis()-base));
	}
	
    private String javaCode ;
 
    /**
     */
    public OCL2JavaFileObject(String qualifiedName, String javaCode) {
        super(java.net.URI.create("string:///" +qualifiedName.replaceAll("\\.", "/") + JavaFileObject.Kind.SOURCE.extension), JavaFileObject.Kind.SOURCE);
        this.javaCode = javaCode ;
    }
 
    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return javaCode ;
    }
}