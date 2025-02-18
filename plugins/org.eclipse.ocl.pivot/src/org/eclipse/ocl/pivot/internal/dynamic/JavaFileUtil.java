/*******************************************************************************
 * Copyright (c) 2017, 2025 Willink Transformations and others.
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
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.TracingOption;
import org.osgi.framework.Bundle;

/**
 * @since 1.23
 */
public abstract class JavaFileUtil
{
	public static final @NonNull TracingOption CLASS_PATH = new TracingOption(PivotPlugin.PLUGIN_ID, "dynamic/classPath");
	public static final @NonNull TracingOption COMPILES = new TracingOption(PivotPlugin.PLUGIN_ID, "dynamic/compiles");

	/**
	 * When running maven/tycho tests locally a bin folder may leave helpful content hiding what will fail
	 * in a clean workspace. Using test-bin reduces the likelihood of delayed bug discovery.
	 */
	public static final @NonNull String TEST_BIN_FOLDER_NAME = "test-bin";
	public static final @NonNull String TEST_SRC_FOLDER_NAME = "test-src";

	private static @Nullable JavaCompiler compiler = getJavaCompiler();

	/**
	 * Returns a non-null string describing any problems, null if all ok.
	 */
	public static @Nullable String compileClasses(@NonNull List<@NonNull JavaFileObject> compilationUnits, @NonNull String sourcePath,
			@NonNull String objectPath, @Nullable JavaClasspath classpath)  {
		JavaCompiler compiler2 = compiler;
		if (compiler2 == null) {
			throw new IllegalStateException("No JavaCompiler provided by the Java platform - you need to use a JDK rather than a JRE");
		}
		StandardJavaFileManager stdFileManager = compiler2.getStandardFileManager(null, Locale.getDefault(), null);
		if (stdFileManager == null) {
			throw new IllegalStateException("No StandardJavaFileManager provided by the Java platform");
		}
		return compileClasses(stdFileManager, compilationUnits, sourcePath, objectPath, classpath);
	}

	/**
	 * Returns a non-null string describing any problems, null if all ok.
	 */
	public static @Nullable String compileClasses(@NonNull JavaFileManager fileManager, @NonNull List<@NonNull JavaFileObject> compilationUnits, @NonNull String sourcePath,
			@Nullable String objectPath, @Nullable JavaClasspath classpath)  {
		JavaCompiler compiler2 = compiler;
		if (compiler2 == null) {
			throw new IllegalStateException("No JavaCompiler provided by the Java platform - you need to use a JDK rather than a JRE");
		}
		try {
			//			System.out.printf("%6.3f start\n", 0.001 * (System.currentTimeMillis()-base));
			DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
			List<@NonNull String> compilationOptions = new ArrayList<>();
			if (objectPath != null) {
				compilationOptions.add("-d");
				compilationOptions.add(objectPath);
				compilationOptions.add("-g");
			}
			//			if (true/*useNullAnnotations*/) {			// This was a good idea to prevent Java 7 / Java 8 annotation confusion
			//				compilationOptions.add("-source");		//  but with the advent of Java 9 specifying the other of 8/9 is a cross
			//				compilationOptions.add("1.8");			//  compilation requiring the path to the bootstrap JDK to be specified
			//			}
			if ((classpath != null) && (classpath.size() > 0)) {
				compilationOptions.add("-cp");
				compilationOptions.add(classpath.getClasspath());
			}
			if (COMPILES.isActive()) {
				StringBuilder s = new StringBuilder();
				s.append("java");
				boolean isCP = false;
				for (String compilationOption : compilationOptions) {
					if (compilationOption.startsWith("-")) {
						s.append("\n\t");
					}
					if (isCP) {
						boolean isFirst = true;
						for (String entry : compilationOption.split(System.getProperty("path.separator"))) {
							if (!isFirst) {
								s.append("\n\t\t");
							}
							s.append(entry);
							isFirst = false;
						}
					}
					else {
						s.append(compilationOption);
					}
					s.append(" ");
					isCP = "-cp".equals(compilationOption);
				}
				for (JavaFileObject compilationUnit : compilationUnits) {
					s.append("\n\t");
					s.append(compilationUnit.toUri().toString());
				}
				COMPILES.println(s.toString());
			}
			//			System.out.printf("%6.3f getTask\n", 0.001 * (System.currentTimeMillis()-base));
			CompilationTask compilerTask = compiler2.getTask(null, fileManager, diagnostics, compilationOptions, null, compilationUnits);
			//			System.out.printf("%6.3f call\n", 0.001 * (System.currentTimeMillis()-base));
			if (compilerTask.call()) {
				return null;
			}
			StringBuilder s = new StringBuilder();
			for (String compilationOption : compilationOptions) {
				if (compilationOption.startsWith("-")) {
					s.append("\n");
				}
				s.append(compilationOption);
				s.append(" ");
			}
			Object currentSource = null;
			for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				s.append("\n");
				Object source = diagnostic.getSource();
				if (source != currentSource) {
					currentSource = source;
					if (currentSource instanceof FileObject) {
						s.append(((FileObject)currentSource).toUri());
					}
					else if (currentSource != null) {
						s.append(currentSource);
					}
					s.append("\n");
				}
				if (currentSource != null) {
					s.append("\t");
				}
				s.append(diagnostic.getLineNumber());
				s.append(":");
				s.append(diagnostic.getColumnNumber());
				s.append(" ");
				s.append(diagnostic.getMessage(null));
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
			// System.out.println(message);
			return message;
		}
		finally {
			//			System.out.printf("%6.3f close\n", 0.001 * (System.currentTimeMillis()-base));
			try {
				fileManager.close();			// XXX share in callers
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		// Close the file manager which re-opens automatically
			//			System.out.printf("%6.3f forName\n", 0.001 * (System.currentTimeMillis()-base));
		}
	}

	/**
	 * Return a new JavaClasspath pre-loaded with the paths needed for OCLinEcore compilation.
	 */
	public static @NonNull JavaClasspath createDefaultOCLClasspath() {
		JavaClasspath classpath = new JavaClasspath();
		classpath.addBundleForClass(org.eclipse.ocl.pivot.Model.class);
		classpath.addBundleForClass(org.eclipse.emf.ecore.EPackage.class);
		classpath.addBundleForClass(org.eclipse.emf.common.EMFPlugin.class);
		classpath.addBundleForClass(org.eclipse.jdt.annotation.NonNull.class);
		classpath.addBundleForClass(org.eclipse.osgi.util.NLS.class);
		return classpath;
	}

	/**
	 * Delete all *.java files on sourcePath
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

	public static void gatherCompilationUnits(@NonNull List<@NonNull JavaFileObject> compilationUnits, @NonNull File directory) {
		File[] files = directory.listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					gatherCompilationUnits(compilationUnits, file);
				} else if (file.isFile()) {
					//					System.out.println("Compiling " + file);
					compilationUnits.add(new JavaSourceFileObject(file.toURI()));
				}
			}
		}
	}

	/**
	 * Return a list comprising a JavaFileObject for each *.java file in or below folder.
	 * A non-null compilationUnits may be provided for use as the returned list.
	 */
	private static @NonNull List<@NonNull JavaFileObject> gatherCompilationUnits(@NonNull File folder, @Nullable List<@NonNull JavaFileObject> compilationUnits) throws IOException {
		if (compilationUnits == null) {
			compilationUnits = new ArrayList<@NonNull JavaFileObject>();
		}
		File[] listFiles = folder.listFiles();
		if (listFiles != null) {
			for (File file : listFiles) {
				if (file.isDirectory()) {
					gatherCompilationUnits(file, compilationUnits);
				}
				else if (file.isFile() && file.getName().endsWith(".java")) {
					java.net.URI uri = file.getCanonicalFile().toURI();
					compilationUnits.add(new JavaSourceFileObject(uri));
				}
			}
		}
		return compilationUnits;
	}

	public static @NonNull List<@NonNull String> gatherPackageNames(@NonNull File binFolder, @Nullable String packagePath) {
		List<@NonNull String> packagePaths = new ArrayList<>();
		gatherPackageNames(packagePaths, binFolder, packagePath);
		return packagePaths;
	}
	private static void gatherPackageNames(@NonNull List<@NonNull String> packagePaths, @NonNull File binFolder, @Nullable String packagePath) {
		boolean hasFile = false;
		for (File binFile : binFolder.listFiles()) {
			if (binFile.isFile()) {
				if (!hasFile) {
					if (packagePath != null) {
						packagePaths.add(packagePath);
					}
					hasFile = true;
				}
			}
			else if (binFile.isDirectory()) {
				String name = binFile.getName();
				if (!".".equals(name) && !"..".equals(name)) {
					gatherPackageNames(packagePaths, binFile, packagePath != null ? packagePath + "." + name : name);
				}
			}
		}
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

	/**
	 * Return the absolute path to the 'bin' folder of a workspace bundle or the jar of a plugin.
	 */
	@Deprecated /* @deprecated Use JavaClasspath */
	public static @NonNull File getOSGIClassPath(@NonNull Bundle bundle) throws IOException {
		//
		//  We could be helpful and use the classes from  a project, but that would be really confusing
		//  since template classes would come from the development project whereas referenced classes
		//  would come from the run-time plugin. Ignore the project files.
		//
		File bundleFile = FileLocator.getBundleFile(bundle);
		if (bundleFile.isDirectory()) {
			File outputPath = getOutputClassPath(bundleFile);
			if (outputPath != null) {
				return outputPath;
			}
		}
		return bundleFile;
	}

	/**
	 * Search the .classpath of bundle to locate the output classpathEntry and return the corresponding path
	 * or null if no .classpath or output classpathentry.
	 */
	public static @Nullable File getOutputClassPath(@NonNull File bundleDirectory) throws IOException {
	//	if (CGUtil.isMavenSurefire() || CGUtil.isTychoSurefire()) {
	//		return new File(bundleDirectory, MAVEN_TYCHO_BIN_FOLDER_NAME);
	//	}
		File classpathEntry = new File(bundleDirectory, ".classpath");
		if (classpathEntry.isFile()) {
			URI uri = URI.createFileURI(classpathEntry.toString());
			Resource resource = new GenericXMLResourceFactoryImpl().createResource(uri);
			resource.load(null);
			for (EObject eRoot : resource.getContents()) {
				EClass eDocumentRoot = eRoot.eClass();
				EStructuralFeature classpathentryRef = eDocumentRoot.getEStructuralFeature("classpathentry");
				EStructuralFeature kindRef = eDocumentRoot.getEStructuralFeature("kind");
				EStructuralFeature pathRef = eDocumentRoot.getEStructuralFeature("path");
				for (EObject eObject : eRoot.eContents()) {
					for (EObject eChild : eObject.eContents()) {
						if (eChild.eContainmentFeature() == classpathentryRef) {
							if ("output".equals(eChild.eGet(kindRef))) {
								String outputPath = String.valueOf(eChild.eGet(pathRef));
								return new File(bundleDirectory, outputPath);
							}
						}
					}
				}
			}
		}
		return null;
	}
}