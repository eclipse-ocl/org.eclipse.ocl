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
import java.util.Map;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.jdt.annotation.NonNull;

/**
 * DerivedEObjectValidatorClassLoader support creation and loading of a derived EObjectValidator class that has a public
 * override of the validate(int, Object, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map<Object, Object>) method.
 */
public final class DerivedEObjectValidatorClassLoader extends ClassLoader
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
	private static final class InternalJavaFileObject extends SimpleJavaFileObject
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

	protected final @NonNull JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
	protected final @NonNull StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);

	private final @NonNull Map<@NonNull Class<? extends EObjectValidator>, @NonNull Class<? extends DerivedEObjectValidator>> eValidator2derivedEValidator = new HashMap<>();
	private final @NonNull Map<@NonNull Class<? extends EObjectValidator>, @NonNull String> eValidator2derivedClassName = new HashMap<>();
	private final @NonNull Map<@NonNull String, @NonNull Class<? extends EObjectValidator>> derivedClassName3eValidator = new HashMap<>();

	private @NonNull String allocateClassName(@NonNull Class<? extends EObjectValidator> eValidator) {
		String className = eValidator2derivedClassName.get(eValidator);
		if (className == null) {
			className = eValidator.getSimpleName();
			int disambiguator = 1;
			while (derivedClassName3eValidator.containsKey(className)) {
				className = eValidator.getSimpleName() + "_" + disambiguator++;
			}
			derivedClassName3eValidator.put(className, eValidator);
			eValidator2derivedClassName.put(eValidator, className);
		}
		return className;
	}

	private @NonNull Class<? extends DerivedEObjectValidator> compileSource(@NonNull String packageName, @NonNull String className, @NonNull String source) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String fileName = packageName.replace('.', '/') + "/" + className + ".java";
		String fullClassName = packageName + "." + className;
		DerivedEObjectValidatorClassLoader.InternalJavaFileObject internalJavaFileObject = new InternalJavaFileObject(fileName, source);
		JavaFileManager javaFileManager = new InternalJavaFileManager(standardFileManager, internalJavaFileObject);

		javaCompiler.getTask(null, javaFileManager, null, null, null, Collections.singletonList(internalJavaFileObject)).call();
		byte[] bytes = internalJavaFileObject.toByteArray();

		@SuppressWarnings("unchecked")
		Class<? extends DerivedEObjectValidator> aClass = (Class<? extends DerivedEObjectValidator>)defineClass(fullClassName, bytes, 0, bytes.length);
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
	//	System.out.println("\n" + s.toString());
		return s.toString();
	}

	/**
	 * Return a class that derives from eValidator with a public validate(int, Object, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map<Object, Object>) method.
	 */
	public @NonNull Class<? extends DerivedEObjectValidator> findDerivedEObjectValidator(@NonNull Class<? extends EObjectValidator> eValidator)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		Class<? extends DerivedEObjectValidator> derivedEValidator = eValidator2derivedEValidator.get(eValidator);
		if (derivedEValidator == null) {
			String className = allocateClassName(eValidator);
			String packageName = getClass().getPackage().getName() + ".phantoms";
			String source = createSource(packageName, className, eValidator);
			derivedEValidator = compileSource(packageName, className, source);
			eValidator2derivedEValidator.put(eValidator, derivedEValidator);
		}
		return derivedEValidator;
	}
}