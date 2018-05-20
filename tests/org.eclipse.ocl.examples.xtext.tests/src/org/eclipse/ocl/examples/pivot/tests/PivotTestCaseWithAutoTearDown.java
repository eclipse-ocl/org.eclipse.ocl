/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bugs 243079, 244948, 244886, 245619
 *   E.D.Willink - Bug 191689, 254919, 296409, 298634
 *   Obeo - Bug 291310
 *   E.D.Willink (CEA LIST) - Bug 388529
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestFile;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystemHelper;
import org.eclipse.ocl.examples.xtext.tests.TestProject;
import org.eclipse.ocl.examples.xtext.tests.TestProjectManager;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * Default test framework.
 */
public abstract class PivotTestCaseWithAutoTearDown extends PivotTestCase
{
	public @Nullable TestFileSystem testFileSystem = null;
	public @Nullable TestProject testProject = null;
	public @Nullable ProjectManager testProjectManager = null;

	@SuppressWarnings("null")
	protected void autoTearDown() throws Exception {
		//
		//	Null out any references that a test may have left behind, so that unwanted
		//	objects are not locked into memory.
		//
		for (java.lang.Class<?> aClass = getClass(); PivotTestCaseWithAutoTearDown.class.isAssignableFrom(aClass); aClass = aClass.getSuperclass()) {
			for (Field field : aClass.getDeclaredFields()) {
				int modifiers = field.getModifiers();
				if (Modifier.isFinal(modifiers)) {
				}
				else if (!Modifier.isStatic(modifiers)) {
					java.lang.Class<?> fieldType = field.getType();
					if (Object.class.isAssignableFrom(fieldType)) {
						String fieldName = field.getName();
						try {
							String tearDownName = "tearDown_" + fieldName;
							Method method = aClass.getDeclaredMethod(tearDownName);
							try {
								tearDownUsing(method);
							} catch (Exception e) {
								// tearDown_xxx must be public
								fail("Failed to invoke " + getClass().getSimpleName() + "." + tearDownName + " : " + e);  //$NON-NLS-2$//$NON-NLS-3$
							}
						}
						catch (NoSuchMethodException e) {
							try {
								tearDownField(field);
							} catch (Exception e1) {
								// xxx without a tearDown_xxx must be public to ensure that leakage can be stopped
								fail("Failed to set " + getClass().getSimpleName() + "." + fieldName + " to null : " + e1); //$NON-NLS-2$ //$NON-NLS-3$
							}
						}
					}
				} else {
					tearDownStatic(aClass, field);
				}
			}
		}
	}

	/**
	 * Return the name of the test bundle. The default implementation assumes that the package name is
	 * the same as the bundle name. Override when this assumption is unjustified.
	 */
	protected @NonNull String getTestBundleName() {
		return ClassUtil.nonNullState(TestProjectManager.class.getPackage().getName());
	}
	protected @NonNull URI getTestBundleURI() {
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			return URI.createPlatformPluginURI("/" + getTestBundleName(), true);
		}
		else {
			return URI.createPlatformResourceURI("/" + getTestBundleName(), true);
		}
	}

	/**
	 * Return the URI of the file within the testProject.
	 */
	protected @NonNull URI getTestFileURI(@NonNull String filePath) {
		TestProject testProject = getTestProject();
		TestFile outFile = testProject.getOutputFile(filePath);
		return URI.createFileURI(outFile.getFile().toString());
	}

	protected @NonNull TestFileSystem getTestFileSystem() {
		TestFileSystem testFileSystem2 = testFileSystem;
		if (testFileSystem2 == null) {
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
				File testBundleFile = new File(".project");
				assert !testBundleFile.exists() : "Default working directory should be the workspace rather than a project: " + testBundleFile.getAbsolutePath();
			}
			testFileSystem = testFileSystem2 = TestFileSystem.create(getTestFileSystemHelper());
		}
		return testFileSystem2;
	}

	protected @NonNull TestFileSystemHelper getTestFileSystemHelper() {
		return new TestFileSystemHelper();
	}

	protected @NonNull String getTestPackageName() {
		return "test_package";
	}

	protected @NonNull TestProject getTestProject() {
		TestProject testProject2 = testProject;
		if (testProject2 == null) {
			String testProjectName = "_OCL_" + getClass().getSimpleName() + "__" + getTestName();
			testProject = testProject2 = getTestFileSystem().getTestProject(testProjectName, true);
		}
		return testProject2;
	}

	protected @NonNull ProjectManager getTestProjectManager() {
		ProjectManager testProjectManager2 = testProjectManager;
		if (testProjectManager2 == null) {
			testProjectManager = testProjectManager2 = getTestProject().createTestProjectManager();
		}
		return testProjectManager2;
	}

	/**
	 * Return the URI of the filePath within the testProject.
	 */
	protected @NonNull URI getTestURI(@NonNull String filePath) throws Exception {
		TestProject testProject = getTestProject();
		TestFile outFile = testProject.getOutputFile(filePath);
		return outFile.getURI();
	}

	protected void tearDownField(@NonNull Field field) throws IllegalAccessException {
		field.set(this, null);
	}

	protected void tearDownStatic(java.lang.@NonNull Class<?> aClass, @NonNull Field field) {
		if (aClass != PivotTestSuite.class) {
			// Tests may not have statics since they are prone to memory leakage
			fail("static test variable:" + field);
		}
	}

	protected void tearDownUsing(@NonNull Method method)
			throws IllegalAccessException, InvocationTargetException {
		method.invoke(this);
	}
}
