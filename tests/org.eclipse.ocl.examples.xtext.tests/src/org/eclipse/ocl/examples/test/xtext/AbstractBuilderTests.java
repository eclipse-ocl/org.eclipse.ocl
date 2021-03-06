/*******************************************************************************
 * Copyright (c) 2019, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestUIUtil;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.base.ui.BaseUIActivator;
import org.eclipse.ocl.xtext.base.ui.builder.MultiValidationJob;
import org.eclipse.ocl.xtext.base.ui.builder.ValidationEntry;
import org.eclipse.xtext.ui.editor.XtextEditor;

/**
 * Tests that the MultiValidationJob does some validating.
 */
public abstract class AbstractBuilderTests extends XtextTestCase
{
	public static int VALIDATION_TIMEOUT = 20000; // Time in millisecionds before validation test fails. may be set to 0 for debugging

	protected @NonNull IFile createEcoreIFile(String projectName, String fileName, String testDocument)throws IOException, CoreException {
		OCL ocl = OCL.newInstance(getProjectMap());
	//	String ecoreString = createEcoreString(ocl, fileName, testDocument, true);
		InputStream inputStream = new URIConverter.ReadableInputStream(testDocument, "UTF-8");
		IFile file = createIFile(projectName, fileName, inputStream);
		ocl.dispose();
		return file;
	}

	protected @NonNull IFile createIFile(String projectName, String testFile, InputStream inputStream) throws CoreException {
		IProject project = createProject(projectName);
		IFile file = project.getFile(testFile);
		file.create(inputStream, true, null);
		return file;
	}

	protected @NonNull IProject createProject(String projectName) throws CoreException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		IProject project = root.getProject(projectName);
		if (!project.exists()) {
			project.create(null);
		}
		project.open(null);
		return project;
	}

	protected void doTearDown(XtextEditor editor) {
		TestUIUtil.flushEvents();
		editor.close(false);
		TestUIUtil.flushEvents();
	}

	@Override
	protected void setUp() throws Exception {
		TestUIUtil.suppressGitPrefixPopUp();
		TestUIUtil.closeIntro();
		super.setUp();
	}
	protected void doValidation(@NonNull IFile file, @Nullable Class<?> exceptionClass, @Nullable List<@NonNull String> expectedMarkerTexts) {
		ValidationEntry validationEntry = new ValidationEntry(file, EValidator.MARKER);
		MultiValidationJob multiValidationJob = ClassUtil.nonNullState(BaseUIActivator.getMultiValidationJob());
		multiValidationJob.addValidations(Collections.singletonList(validationEntry));
		try {
			Throwable throwable = null;
			List<MultiValidationJob.@NonNull MarkerData> markerDatas = null;
			synchronized (validationEntry) {
				validationEntry.wait(VALIDATION_TIMEOUT);
				throwable = validationEntry.getThrowable();
				markerDatas = validationEntry.getMarkerDatas();
			}
			if (exceptionClass != null) {
				assertNotNull("The expected " + exceptionClass.getName() + " exception was not thrown", throwable);
				assert throwable != null;
				Class<? extends @NonNull Throwable> throwableClass = throwable.getClass();
				assertTrue("Expected a " + exceptionClass.getName() + " exception rather than a " + throwableClass.getName(), exceptionClass.isAssignableFrom(throwableClass));
			}
			else {
				if (throwable != null) {
					fail("Unexpected exception " + throwable.toString());
				}
				if ((expectedMarkerTexts != null) || ((markerDatas != null) && (markerDatas.size() > 0))) {
					List<@NonNull String> expectedTexts = expectedMarkerTexts != null ? new ArrayList<>(expectedMarkerTexts) : new ArrayList<>();
					List<@NonNull String> actualTexts = new ArrayList<>();
					if (markerDatas != null) {
						for (MultiValidationJob.@NonNull MarkerData markerData : markerDatas) {
							actualTexts.add(markerData.getMessageText());
						}
					}
					StringBuilder s = new StringBuilder();
					Collections.sort(expectedTexts);
					Collections.sort(actualTexts);
					int iExpected = 0;
					int iActual = 0;
					int sizeExpected = expectedTexts.size();
					int sizeActual = actualTexts.size();
					while ((iExpected < sizeExpected) || (iActual < sizeActual)) {
						if (iExpected >= sizeExpected) {
							s.append("\n\tUnexpected: " + actualTexts.get(iActual));
							iActual++;
						}
						else if (iActual >= sizeActual) {
							s.append("\n\tMissing: " + expectedTexts.get(iExpected));
							iExpected++;
						}
						else {
							String expected = expectedTexts.get(iExpected);
							String actual = actualTexts.get(iActual);
							int diff = expected.compareTo(actual);
							if (diff < 0) {
								s.append("\n\tUnexpected: " + actual);
								iActual++;
							}
							else if (diff > 0) {
								s.append("\n\tMissing: " + expected);
								iExpected++;
							}
							else {
								iExpected++;
								iActual++;
							}
						}
					}
					if (s.length() > 0) {
						fail("Inconsistent MarkerData texts" + s.toString());
					}
				}
			}
		}
		catch (Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			pw.close();
			fail(sw.toString());
		}
	}
}
