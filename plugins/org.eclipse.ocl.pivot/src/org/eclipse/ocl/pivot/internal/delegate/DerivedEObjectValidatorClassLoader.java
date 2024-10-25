/*******************************************************************************
 * Copyright (c) 2024 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.dynamic.JavaClasspath;
import org.eclipse.ocl.pivot.internal.dynamic.JavaFileUtil;
import org.eclipse.ocl.pivot.utilities.SemanticException;

/**
 * DerivedEObjectValidatorClassLoader support creation and loading of a derived EObjectValidator class that has a public
 * override of the validate(int, Object, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map<Object, Object>) method.
 */
public final class DerivedEObjectValidatorClassLoader //extends ClassLoader
{
	/**
	 * InternalJavaFileManager returns the InternalJavaFileObject as all output files.
	 */
	private static final class InternalJavaFileManager extends ForwardingJavaFileManager<JavaFileManager>
	{
		private final @NonNull InternalJavaFileObject internalJavaFileObject;

		public InternalJavaFileManager(@NonNull JavaFileManager fileManager, @NonNull InternalJavaFileObject internalJavaFileObject) {
			super(fileManager);
			this.internalJavaFileObject = internalJavaFileObject;
		}

		@Override
		public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) {
			return internalJavaFileObject;
		}
	}

	/**
	 * InternalJavaFileObject overrides a SimpleJavaFileObject to take its input direct from a text source
	 * and return its compiled result direct to a ByteArrayOutputStream.
	 */
	private static /*final*/ class InternalJavaFileObject extends SimpleJavaFileObject
	{
		private final @NonNull String source;
		private final @NonNull ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

		public InternalJavaFileObject(@NonNull String fileName, @NonNull String source) {
			super(URI.create(fileName), JavaFileObject.Kind.SOURCE);
			this.source = source;
		}

		@Override
		public CharSequence getCharContent(boolean ignoreEncodingErrors) {
			return source;
		}

		@Override
		public OutputStream openOutputStream() throws IOException {
			return byteArrayOutputStream;
		}

		public byte[] toByteArray() {
			return byteArrayOutputStream.toByteArray();
		}
	}

	private static DerivedEObjectValidatorClassLoader instance;

	public static DerivedEObjectValidatorClassLoader getInstance() {
		if (instance == null) {
			instance = new DerivedEObjectValidatorClassLoader();
		}
		return instance;
	}

	public DerivedEObjectValidatorClassLoader() {
	//	super(DerivedEObjectValidator.class.getClassLoader());
		System.out.println("DerivedEObjectValidatorClassLoader.init class loader " + getClass().getName() + " " + getClass().getClassLoader().toString());		// XXX
	}

	protected final @NonNull JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
	protected final @NonNull StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);

	private final @NonNull Map<@NonNull Class<? extends EObjectValidator>, @NonNull Class<? extends DerivedEObjectValidator>> eValidator2derivedEValidator = new HashMap<>();
	private final @NonNull Map<@NonNull Class<? extends EObjectValidator>, @NonNull String> eValidator2derivedClassName = new HashMap<>();
	private final @NonNull Map<@NonNull String, @NonNull Class<? extends EObjectValidator>> derivedClassName3eValidator = new HashMap<>();

	private @NonNull String allocateClassName(@NonNull Class<? extends EObjectValidator> eValidatorClass) {
		String className = eValidator2derivedClassName.get(eValidatorClass);
		if (className == null) {
			className = eValidatorClass.getSimpleName();
			int disambiguator = 1;
			while (derivedClassName3eValidator.containsKey(className)) {
				className = eValidatorClass.getSimpleName() + "_" + disambiguator++;
			}
			derivedClassName3eValidator.put(className, eValidatorClass);
			eValidator2derivedClassName.put(eValidatorClass, className);
		}
		return className;
	}

/*	private @NonNull Class<? extends DerivedEObjectValidator> zzcompileSource1(@NonNull String packageName, @NonNull String className, @NonNull String source) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SemanticException {
		String fileName = packageName.replace('.', '/') + "/" + className + ".java";
		String fullClassName = packageName + "." + className;
		DerivedEObjectValidatorClassLoader.InternalJavaFileObject internalJavaFileObject = new InternalJavaFileObject(fileName, source);
		JavaFileManager javaFileManager = new InternalJavaFileManager(standardFileManager, internalJavaFileObject);

		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		List<@NonNull String> compilationOptions = new ArrayList<>();
		List<InternalJavaFileObject> compilationUnits = Collections.singletonList(internalJavaFileObject);

		compilationOptions.add("-cp");
		compilationOptions.add("E:\\Tools\\Eclipse\\4.32\\plugins\\org.eclipse.emf.ecore_2.36.0.v20240203-0859.jar");


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
		System.out.println(s.toString());


		CompilationTask task = javaCompiler.getTask(null, javaFileManager, diagnostics, compilationOptions, null, compilationUnits);
		Boolean status = task.call();
		if (!status) {
			throw new SemanticException("Failed to compile auto-generated " + fullClassName);
		}
		byte[] bytes = internalJavaFileObject.toByteArray();

		@SuppressWarnings("unchecked")
		Class<? extends DerivedEObjectValidator> aClass = (Class<? extends DerivedEObjectValidator>)defineClass(fullClassName, bytes, 0, bytes.length);
		return aClass;
	} */

	private @NonNull Class<? extends DerivedEObjectValidator> compileSource2(@NonNull List<@NonNull ClassLoader> classLoaders, @NonNull String packageName, @NonNull String className, @NonNull String source, @NonNull JavaClasspath classPath) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SemanticException {
		String fileName = packageName.replace('.', '/') + "/" + className + ".java";
		String fullClassName = packageName + "." + className;
		DerivedEObjectValidatorClassLoader.InternalJavaFileObject internalJavaFileObject = new InternalJavaFileObject(fileName, source) {};
		JavaFileManager javaFileManager = new InternalJavaFileManager(standardFileManager, internalJavaFileObject);
		System.out.println("DerivedEObjectValidatorClassLoader.init internalJavaFileObject class loader " + internalJavaFileObject.getClass().getName() + " " + internalJavaFileObject.getClass().getClassLoader().toString());		// XXX

		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		List<JavaFileObject> compilationUnits = Collections.singletonList(internalJavaFileObject);


		ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.println("JavaFileUtil.compileSource2 contextClassLoader class loader " + contextClassLoader.getClass().getName() + " " + contextClassLoader.toString());		// XXX
	//	JavaFileUtil.class.getDeclaredConstructor().
		String diags = new JavaFileUtil(){}.compileClasses2(javaFileManager, compilationUnits, fullClassName, null, classPath);
		System.out.println(diags);
	//	CompilationTask task = javaCompiler.getTask(null, javaFileManager, diagnostics, compilationOptions, null, compilationUnits);
	//	Boolean status = task.call();
	//	if (!status) {
	//		throw new SemanticException("Failed to compile auto-generated " + fullClassName);
	//	}
		byte[] bytes = internalJavaFileObject.toByteArray();

	//	List<@NonNull URL> classpathURLs = classPath.getClasspathURLs();
	//	Class<? extends DerivedEObjectValidator> aClass = new URLClassLoader(classpathURLs.toArray(new URL[classpathURLs.size()]), DerivedEObjectValidator.class.getClassLoader())
	//	{
	//		public Class<? extends DerivedEObjectValidator> doIt() {
	//			return (Class<? extends DerivedEObjectValidator>) defineClass(fullClassName, bytes, 0, bytes.length);
	//		}
	//	}.doIt();
	//	@SuppressWarnings("unchecked")
		Class<? extends DerivedEObjectValidator> aClass = new ClassLoader()
		{
			Class<? extends DerivedEObjectValidator> doIt() {
				return (Class<? extends DerivedEObjectValidator>)defineClass(fullClassName, bytes, 0, bytes.length);
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
			//	return getParent().loadClass(name);
			}

		}.doIt();
		return aClass;
	}

	private @NonNull String createSource(@NonNull String packageName, @NonNull String className, @NonNull Class<? extends EObjectValidator> eValidator) {
		StringBuilder s = new StringBuilder();
		s.append("package " + packageName + ";\n\n");
		s.append("public class " + className + "\n");
		s.append("\textends " + eValidator.getName() + "\n");
		s.append("\timplements " + DerivedEObjectValidator.class.getName() + "\n");
		s.append("{\n");
		s.append("\t@Override\n");
		s.append("\tpublic boolean validate(int classifierID, Object object, org.eclipse.emf.common.util.DiagnosticChain diagnostics, java.util.Map<Object, Object> context) {\n");
		s.append("\t\treturn super.validate(classifierID, object, diagnostics, context);\n");
		s.append("\t}\n");
		s.append("}\n");
		System.out.println("\n" + s.toString());
		return s.toString();
	}

	/**
	 * Return a class that derives from eValidator with a public validate(int, Object, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map<Object, Object>) method.
	 * @param environmentFactory
	 * @throws SemanticException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public @NonNull Class<? extends DerivedEObjectValidator> findDerivedEObjectValidator(@NonNull List<@NonNull ClassLoader> classLoaders, @NonNull Class<? extends EObjectValidator> eValidatorClass)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalArgumentException, SecurityException, SemanticException {
		Class<? extends DerivedEObjectValidator> derivedEValidatorClass = eValidator2derivedEValidator.get(eValidatorClass);
		if (derivedEValidatorClass == null) {
	/*		DerivedEObjectValidator test = new DerivedEObjectValidator() {

				@Override
				public boolean validate(EObject eObject,
						DiagnosticChain diagnostics,
						Map<Object, Object> context) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean validate(EClass eClass, EObject eObject,
						DiagnosticChain diagnostics,
						Map<Object, Object> context) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean validate(EDataType eDataType, Object value,
						DiagnosticChain diagnostics,
						Map<Object, Object> context) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public boolean validate(int classifierID, Object object,
						DiagnosticChain diagnostics,
						Map<Object, Object> context) {
					// TODO Auto-generated method stub
					return false;
				}};
		//		ClassLoader testCLoader1 = test.getClass().getClassLoader(); */
				ClassLoader testCLoader2 = DerivedEObjectValidator.class.getClassLoader();
			String className = allocateClassName(eValidatorClass);
			String packageName = getClass().getPackage().getName() + ".phantoms";
			String source = createSource(packageName, className, eValidatorClass);
			JavaClasspath classPath = new JavaClasspath();
			classPath.addClass(eValidatorClass);
			classPath.addClass(DerivedEObjectValidator.class);
			classPath.addClass(EObjectValidator.class);		// XXX all supers
			classPath.addClass(Diagnostician.class);
			classPath.addClass(Diagnostic.class);
			derivedEValidatorClass = compileSource2(classLoaders, packageName, className, source, classPath);
			eValidator2derivedEValidator.put(eValidatorClass, derivedEValidatorClass);
		}
		return derivedEValidatorClass;
	}
}