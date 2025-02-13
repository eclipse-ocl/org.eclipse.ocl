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
package org.eclipse.ocl.pivot.internal.dynamic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.eclipse.ocl.pivot.utilities.SemanticException;

/**
 * DerivedEObjectValidatorManager support creation and loading of a derived EObjectValidator class that has a public
 * override of the validate(int, Object, org.eclipse.emf.common.util.DiagnosticChain, java.util.Map<Object, Object>) method.
 * @since 1.23
 */
public final class DerivedEObjectValidatorManager
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

		@SuppressWarnings("null")
		public byte @NonNull [] toByteArray() {
			return byteArrayOutputStream.toByteArray();
		}
	}

	private static DerivedEObjectValidatorManager instance;

	public static DerivedEObjectValidatorManager getInstance() {
		if (instance == null) {
			instance = new DerivedEObjectValidatorManager();
		}
		return instance;
	}

	@SuppressWarnings("null")
	protected final @NonNull JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
	@SuppressWarnings("null")
	protected final @NonNull StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);

	private final @NonNull Map<@NonNull Class<? extends EObjectValidator>, @NonNull Class<? extends DerivedEObjectValidator>> eValidator2derivedEValidator = new HashMap<>();
	private final @NonNull Map<@NonNull Class<? extends EObjectValidator>, @NonNull String> eValidator2derivedClassName = new HashMap<>();
	private final @NonNull Map<@NonNull String, @NonNull Class<? extends EObjectValidator>> derivedClassName2eValidator = new HashMap<>();

	private @NonNull String allocateClassName(@NonNull Class<? extends EObjectValidator> eValidatorClass) {
		String className = eValidator2derivedClassName.get(eValidatorClass);
		if (className == null) {
			className = eValidatorClass.getSimpleName();
			int disambiguator = 1;
			while (derivedClassName2eValidator.containsKey(className)) {
				className = eValidatorClass.getSimpleName() + "_" + disambiguator++;
			}
			derivedClassName2eValidator.put(className, eValidatorClass);
			eValidator2derivedClassName.put(eValidatorClass, className);
		}
		return className;
	}

	private @NonNull Class<? extends DerivedEObjectValidator> compileSource(@NonNull String packageName, @NonNull String className, @NonNull String source, @NonNull JavaClasspath classPath) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SemanticException {
		String fileName = packageName.replace('.', '/') + "/" + className + ".java";
		String fullClassName = packageName + "." + className;
		DerivedEObjectValidatorManager.InternalJavaFileObject internalJavaFileObject = new InternalJavaFileObject(fileName, source) {};
		JavaFileManager javaFileManager = new InternalJavaFileManager(standardFileManager, internalJavaFileObject);
		List<@NonNull JavaFileObject> compilationUnits = Collections.singletonList(internalJavaFileObject);

		String diags = JavaFileUtil.compileClasses(javaFileManager, compilationUnits, fullClassName, null, classPath);
		if (diags != null) {
			throw new SemanticException("Auto-generated " + fullClassName + " failed\n" + source + "\n" + diags);
		}
		@SuppressWarnings("unchecked")
		Class<? extends DerivedEObjectValidator> aClass = (Class<? extends DerivedEObjectValidator>)classPath.defineClass(fullClassName, internalJavaFileObject.toByteArray());
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
	 * @param environmentFactory
	 * @throws SemanticException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 */
	public @NonNull Class<? extends DerivedEObjectValidator> findDerivedEObjectValidator(@NonNull Class<? extends EObjectValidator> eValidatorClass)
			throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalArgumentException, SecurityException, SemanticException {
		Class<? extends DerivedEObjectValidator> derivedEValidatorClass = eValidator2derivedEValidator.get(eValidatorClass);
		if (derivedEValidatorClass == null) {
			String className = allocateClassName(eValidatorClass);
			String packageName = getClass().getPackage().getName() + ".phantoms";
			String source = createSource(packageName, className, eValidatorClass);
			JavaClasspath classPath = new JavaClasspath();
			classPath.addBundleForClass(eValidatorClass);
			classPath.addBundleForClass(DerivedEObjectValidator.class);
			classPath.addBundleForClass(EObjectValidator.class);
			classPath.addBundleForClass(Diagnostician.class);
			classPath.addBundleForClass(Diagnostic.class);
			derivedEValidatorClass = compileSource(packageName, className, source, classPath);
			eValidator2derivedEValidator.put(eValidatorClass, derivedEValidatorClass);
		}
		return derivedEValidatorClass;
	}
}