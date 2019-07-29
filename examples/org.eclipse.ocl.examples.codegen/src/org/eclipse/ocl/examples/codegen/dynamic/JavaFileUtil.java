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
package org.eclipse.ocl.examples.codegen.dynamic;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.CodeGenConstants;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.TracingOption;
import org.osgi.framework.Bundle;

public abstract class JavaFileUtil
{
	public static final @NonNull TracingOption CLASS_PATH = new TracingOption(CodeGenConstants.PLUGIN_ID, "classPath");
	public static final @NonNull TracingOption COMPILES = new TracingOption(CodeGenConstants.PLUGIN_ID, "compiles");

	/**
	 * When running maven/tycho tests locally a bin folder may leave helpful content hiding what will fail
	 * in a clean workspace. Using test-bin reduces the likelihood of delayed bug discovery.
	 */
	public static final @NonNull String TEST_BIN_FOLDER_NAME = "test-bin";
	private static final @NonNull String MAVEN_TYCHO_BIN_FOLDER_NAME = "target/classes";
	private static final @NonNull String REGULAR_BIN_FOLDER_NAME = "bin";
	public static final @NonNull String TEST_SRC_FOLDER_NAME = "test-src";

	private static @Nullable JavaCompiler compiler = getJavaCompiler();

	@Deprecated /* @deprecated caller is in a better position to determine a project URL */
	public static @Nullable String compileClass(@NonNull String sourcePath, @NonNull String javaCodeSource, @NonNull String objectPath, @Nullable List<@NonNull String> classpathProjects) throws IOException {
		List<@NonNull JavaFileObject> compilationUnits = Collections.singletonList(new OCL2JavaFileObject(sourcePath, javaCodeSource));
		return JavaFileUtil.compileClasses(compilationUnits, sourcePath, objectPath, classpathProjects);
	}

	/**
	 * Compile all *.java files on sourcePath to objectPath.
	 * e.g. from ../xyzzy/src/a/b/c to ../xyzzy/bin
	 */
	@Deprecated /* @deprecated caller is in a better position to determine a project URL */
	public static @Nullable String compileClasses(@NonNull String sourcePath, @NonNull String objectPath, @Nullable List<@NonNull String> classpathProjects) throws IOException {
		try {
			List<@NonNull JavaFileObject> compilationUnits = gatherCompilationUnits(new File(sourcePath), null);
			return JavaFileUtil.compileClasses(compilationUnits, sourcePath, objectPath, classpathProjects);
		}
		catch (Throwable e) {
			throw e;
		}
	}

	/**
	 * Returns a non-null string describing any problems, null if all ok.
	 */
	@Deprecated /* @deprecated caller is in a better position to determine a project URL */
	public static @Nullable String compileClasses(@NonNull List<@NonNull JavaFileObject> compilationUnits, @NonNull String sourcePath,
			@NonNull String objectPath, @Nullable List<@NonNull String> classpathProjects) throws MalformedURLException  {
		JavaClasspath classpath = new JavaClasspath();
		if (classpathProjects != null) {
			for (@NonNull String classpathProject : classpathProjects) {
				classpath.addURL(new URL(classpathProject));		// FIXME fudge
			}
		}
		return compileClasses(compilationUnits, sourcePath, objectPath, classpath);
	}

	/**
	 * Returns a non-null string describing any problems, null if all ok.
	 */
	public static @Nullable String compileClasses(@NonNull List<@NonNull JavaFileObject> compilationUnits, @NonNull String sourcePath,
			@NonNull String objectPath, @Nullable JavaClasspath classpath)  {
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
			CompilationTask compilerTask = compiler2.getTask(null, stdFileManager2, diagnostics, compilationOptions, null, compilationUnits);
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
				stdFileManager2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		// Close the file manager which re-opens automatically
			//			System.out.printf("%6.3f forName\n", 0.001 * (System.currentTimeMillis()-base));
		}
	}

	/**
	 * Return a javac -cp argument using the system path.separator for each of the projectPaths.
	 *
	 * The projectPaths should be OS-specific filesystems paths, but may for backward compatibility
	 * purposes by simple dit-separated project names.
	 * @throws MalformedURLException
	 */
	@Deprecated /* @deprecated use JavaClasspath; caller is in a better position to determine a project URL */
	public static @NonNull String createClassPath(@NonNull List<@NonNull String> projectPaths) throws MalformedURLException {
		JavaClasspath classpath = new JavaClasspath();
		for (String projectName : projectPaths) {
			if (EMFPlugin.IS_ECLIPSE_RUNNING) {
				IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				String projectPath = null;
				if (projectName.contains("/") || projectName.contains("\\")) {
					projectPath = projectName;
				}
				else {
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
							projectPath = projectPath + JavaFileUtil.REGULAR_BIN_FOLDER_NAME;
						}
					}
				}
				classpath.addURL(new URL(projectPath));		// FIXME fudge
			}
			/*			else {
					String pathSeparator = null;
					StringBuilder s = new StringBuilder();
					for (String projectPath : projectPaths) {
						if (pathSeparator != null) {
							s.append(pathSeparator);
						}
						else {
							pathSeparator = System.getProperty("path.separator");
						}
						s.append(projectPath);
					}
					return s.toString();
				} */
		}
		return classpath.getClasspath();
	}

	@Deprecated /* @deprecated no longer used */
	public static @NonNull List<@NonNull String> createClassPathProjectList(@NonNull URIConverter uriConverter, @NonNull List<@NonNull String> projectNames) {
		List<@NonNull String> classpathProjectList = new ArrayList<@NonNull String>();
		for (@NonNull String projectName : projectNames) {
			File path = getProjectBinFolder(uriConverter, projectName);
			if (path != null) {
				classpathProjectList.add(String.valueOf(path));
			}
		}
		//		}
		return classpathProjectList;
	}

	/**
	 * Return a new list of exemplar classes whose projects need to be on the class path.
	 */
	@Deprecated /* @deprecated use createDefaultOCLclasspath() and then addClass(). */
	public static @NonNull JavaClasspath createClasspathProjectNameList(@NonNull Class<?>... exemplarClasses) {
		JavaClasspath classpath = createDefaultOCLClasspath();
		if (exemplarClasses != null) {
			for (@NonNull Class<?> exemplarClass : exemplarClasses) {
				classpath.addClass(/*0,*/ exemplarClass);
			}
		}
		return classpath;
	}

	/*
	public static @NonNull List<@NonNull String> createClassPathProjectList(@NonNull URIConverter uriConverter, @NonNull List<@NonNull Class<?>> exemplarProjectClasses) {
		List<@NonNull String> classpathProjectList = new ArrayList<>();
		for (@NonNull Class<?> exemplarProjectClass : exemplarProjectClasses) {
		//	File path = getProjectBinFolder(uriConverter, projectName);
		//	if (path != null) {
		//		classpathProjectList.add(String.valueOf(path));
		//	}
			String modifiedName = "/" + exemplarProjectClass.getName().replace('.', '/') + ".class";
			URL projectURL = exemplarProjectClass.getResource(modifiedName);
			if (projectURL != null) {
				if (EMFPlugin.IS_ECLIPSE_RUNNING) {
					try {
						projectURL = FileLocator.resolve(projectURL);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				String projectURLstring = projectURL.toString();
				projectURLstring = projectURLstring.substring(0, projectURLstring.length() - modifiedName.length());
				if (projectURLstring.startsWith("jar:") && projectURLstring.endsWith("!")) {
					projectURLstring = projectURLstring.substring(4, projectURLstring.length()-1);
				}
				URI projectURI = URI.createURI(projectURLstring);
				String projectString = projectURI.isFile() ? projectURI.toFileString() : projectURI.toString();
				if (projectString != null) {
					classpathProjectList.add(projectString);
				}
			}
		}
		//		}
		return classpathProjectList;
	} */

	/**
	 * Return a new JavaClasspath preloaded with the paths needed for OCLinEcore compilation.
	 */
	public static @NonNull JavaClasspath createDefaultOCLClasspath() {
		JavaClasspath classpath = new JavaClasspath();
		classpath.addClass(org.eclipse.ocl.pivot.Model.class);
		classpath.addClass(org.eclipse.emf.ecore.EPackage.class);
		classpath.addClass(org.eclipse.emf.common.EMFPlugin.class);
		classpath.addClass(org.eclipse.jdt.annotation.NonNull.class);
		classpath.addClass(org.eclipse.osgi.util.NLS.class);
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

	@Deprecated /* @deprecated Use gatherCompilationUnits */
	public static @NonNull List<@NonNull JavaFileObject> getCompilationUnits(@NonNull File... srcFiles)
			throws Exception {
		List<@NonNull JavaFileObject> compilationUnits = new ArrayList<>();
		if (srcFiles != null) {
			for (@NonNull File srcFile : srcFiles) {
				gatherCompilationUnits(compilationUnits, srcFile);
			}
		}
		return compilationUnits;
	}

	@Deprecated /* @deprecated Use gatherCompilationUnits */
	protected void getCompilationUnits(@NonNull List<JavaFileObject> compilationUnits, @NonNull IContainer container) throws CoreException {
		for (IResource member : container.members()) {
			if (member instanceof IContainer) {
				getCompilationUnits(compilationUnits, (IContainer) member);
			} else if ((member instanceof IFile)
					&& member.getFileExtension().equals("java")) {
				java.net.URI locationURI = member.getLocationURI();
				assert locationURI != null;
				//				System.out.println("Compiling " + locationURI);
				compilationUnits.add(new JavaSourceFileObject(locationURI));
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
	 * Return the file system folder suitable for use as a javac classpath entry.
	 *
	 * For workspace projects this is the "bin" folder. For plugins it is the jar file.
	 */
	@Deprecated /* @deprecated Use JavaClasspath */
	public static @Nullable File getProjectBinFolder(@NonNull URIConverter uriConverter, @NonNull String projectName) {
		String path = null;
		URI platformURI = URI.createPlatformResourceURI("/" + projectName + "/", true);
		URI pathURI = uriConverter.normalize(platformURI);
		String location = null;
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			Bundle bundle = Platform.getBundle(projectName);
			if (bundle != null) {
				try {
					File bundleFilePath = getOSGIClassPath(bundle);
					location = bundle.getLocation();
					path = bundleFilePath.toString();
				} catch (IOException e) {
					// Doesn't fail for sensible names.
				}
			}
			if (path == null) {					// platform:/resource
				IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
				IResource project = workspaceRoot.findMember(projectName);
				if (project != null) {
					location = String.valueOf(project.getLocation());
					path = location + "/" + TEST_BIN_FOLDER_NAME;
				}
			}
		}
		else if (pathURI.isArchive()) {
			path = pathURI.toString();
			if (path.startsWith("archive:file:") && path.endsWith("!/")) {
				path = path.substring(13, path.length()-2);
			}
		}
		else {
			path = pathURI.toFileString();
			if (path != null) {
				if (!new File(path + "/META-INF").exists()) {
					path = path + TEST_BIN_FOLDER_NAME;
				}
				else {
					String binDir = CGUtil.isMavenSurefire() || CGUtil.isTychoSurefire() ? MAVEN_TYCHO_BIN_FOLDER_NAME : REGULAR_BIN_FOLDER_NAME;  // FIXME determine "bin" from JDT
					path = path + binDir;
				}
			}
		}
		if (CLASS_PATH.isActive()) {
			StringBuilder s = new StringBuilder();
			s.append(projectName);
			s.append(" => ");
			s.append(pathURI);
			s.append(" => ");
			if (location != null) {
				s.append(location);
				s.append(" => ");
			}
			s.append(path);
			System.out.println(s.toString());
		}
		return path != null ? new File(path) : null;
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