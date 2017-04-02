/*******************************************************************************
 * Copyright (c) 2017 Willink Transformations and others.
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.osgi.framework.Bundle;

public abstract class JavaFileUtil
{
	private static @Nullable JavaCompiler compiler = getJavaCompiler();

	/**
	 * Returns a non-null string describing any problems, null if all ok.
	 */
	public static @Nullable String compileClasses(@NonNull List<@NonNull JavaFileObject> compilationUnits, @NonNull String sourcePath,
			@NonNull String objectPath, @Nullable List<@NonNull String> classpathProjects)  {
		JavaCompiler compiler2 = compiler;
		if (compiler2 == null) {
			throw new IllegalStateException("No JavaCompiler provided by the Java platform - you need to use a JDK rather than a JRE");
		}
		StandardJavaFileManager stdFileManager2 = compiler2.getStandardFileManager(null, Locale.getDefault(), null);
		if (stdFileManager2 == null) {
			throw new IllegalStateException("No StandardJavaFileManager provided by the Java platform");
		}
		try {
			//			System.out.printf("%6.3f start\n", 0.001 * (System.currentTimeMillis()-base));
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			List<@NonNull String> compilationOptions = new ArrayList<>();
			compilationOptions.add("-d");
			compilationOptions.add(objectPath);
			compilationOptions.add("-g");
			if (true/*useNullAnnotations*/) {
				compilationOptions.add("-source");
				compilationOptions.add("1.8");
			}
			if (EcorePlugin.IS_ECLIPSE_RUNNING && (classpathProjects != null)) {
				compilationOptions.add("-cp");
				compilationOptions.add(createClassPath(classpathProjects));
			}

			//			System.out.printf("%6.3f getTask\n", 0.001 * (System.currentTimeMillis()-base));
			CompilationTask compilerTask = compiler2.getTask(null, stdFileManager2, diagnostics, compilationOptions, null, compilationUnits);
			//			System.out.printf("%6.3f call\n", 0.001 * (System.currentTimeMillis()-base));
			if (compilerTask.call()) {
				return null;
			}
			StringBuilder s = new StringBuilder();
			for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				s.append("\n" + diagnostic.getMessage(null));
			}
			String message;
			if (s.length() > 0) {
				//					throw new IOException("Failed to compile " + sourcePath + s.toString());
				// If a previous generation was bad we may get many irrelevant errors.
				message = "Failed to compile " + sourcePath + s.toString();
			}
			else {
				message = "Compilation of " + sourcePath + " returned false but no diagnostics";
			}
			System.out.println(message);
			return message;
		}
		finally {
			//			System.out.printf("%6.3f close\n", 0.001 * (System.currentTimeMillis()-base));
			try {
				stdFileManager2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		// Close the file manager which re-opens automatically
			//			System.out.printf("%6.3f forName\n", 0.001 * (System.currentTimeMillis()-base));
		}
	}

	public static @NonNull String createClassPath(@NonNull List<String> projectNames) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		String pathSeparator = null;
		StringBuilder s = new StringBuilder();
		for (String projectName : projectNames) {
			String projectPath = null;
			IProject project = root.getProject(projectName);
			if (project != null) {
				IPath location = project.getLocation();
				if (location != null) {
					projectPath = location.toString() + "/";
				}
			}
			if (projectPath == null) {
				Bundle bundle = Platform.getBundle(projectName);
				if (bundle != null) {
					projectPath = bundle.getLocation();
				}
			}

			if (projectPath != null) {
				if (projectPath.startsWith("reference:")) {
					projectPath = projectPath.substring(10);
				}
				org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI(projectPath);
				if (uri.isFile()) {
					projectPath =  ClassUtil.nonNullState(uri.toFileString()).replace("\\", "/");
				}
				assert projectPath != null;
				if (projectPath.endsWith("/")) {
					projectPath = projectPath + "bin";
				}
				if (pathSeparator != null) {
					s.append(pathSeparator);
				}
				else {
					pathSeparator = System.getProperty("path.separator");
				}
				s.append(projectPath);
			}
		}
		return s.toString();
	}

	/**
	 * Compile all *.java files on sourcePath
	 */
	public static void deleteJavaFiles(@NonNull String sourcePath) {
		deleteJavaFiles(new File(sourcePath));
	}

	/**
	 * Return a list comprising a JavaFileObject for each *.java file in or below folder.
	 * A non-null compilationUnits may be provided for use as the returned list.
	 */
	private static void deleteJavaFiles(@NonNull File folder) {
		File[] listFiles = folder.listFiles();
		if (listFiles != null) {
			for (File file : listFiles) {
				if (file.isDirectory()) {
					deleteJavaFiles(file);
				}
				else if (file.isFile() && file.getName().endsWith(".java")) {
					//					System.out.println("Delete " + file);
					file.delete();
				}
			}
		}
		return;
	}

	private static @Nullable JavaCompiler getJavaCompiler() {
		//
		//	First try to find the EclipseCompiler
		//
		/*		ServiceLoader<JavaCompiler> javaCompilerLoader = ServiceLoader.load(JavaCompiler.class);
		Iterator<JavaCompiler> iterator = javaCompilerLoader.iterator();
		while (iterator.hasNext()) {
			JavaCompiler next = iterator.next();
			return next;
		} */
		//
		//	Otherwise the JDK compiler
		//
		return ToolProvider.getSystemJavaCompiler();
	}
}