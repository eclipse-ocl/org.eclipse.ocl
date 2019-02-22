/*******************************************************************************
 * Copyright (c) 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.xmi.UnresolvedReferenceException;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.xtext.base.ui.BaseUIActivator;
import org.eclipse.ocl.xtext.base.ui.builder.MultiValidationJob;
import org.eclipse.ocl.xtext.base.ui.builder.ValidationEntry;

import com.google.common.collect.Lists;

/**
 * Tests that the MultiValidationJob does some validating.
 */
public class OCLBuilderTests extends AbstractBuilderTests
{
	public void testBuilder_IOException() throws Exception {
		String testDocument =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"UnresolvedReference\" nsURI=\"http://UnresolvedReference\" nsPrefix=\"UnresolvedReference\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"MyClass\">\n" +
						"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"comparison\" eType=\"#//ComparisonKind\"\n" +
						"        defaultValueLiteral=\"=\"/>\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n" +
						"";
		IFile file = createEcoreIFile(getTestProject().getName(), "UnresolvedReference.ecore", testDocument);
		doValidation(file, UnresolvedReferenceException.class, null);
	}

	public void testBuilder_MalformedName() throws Exception {
		String testDocument =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"MalformedName\" nsURI=\"http://MalformedName\" nsPrefix=\"MalformedName\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"My#Class\">\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n" +
						"";
		IFile file = createEcoreIFile(getTestProject().getName(), "MalformedName.ecore", testDocument);
		doValidation(file, null, Lists.newArrayList("The name 'My#Class' is not well formed"));
	}

	public void testBuilder_OK() throws Exception {
		String testDocument =
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
						"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
						"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"OK\" nsURI=\"http://OK\" nsPrefix=\"OK\">\n" +
						"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"MyClass\">\n" +
						"  </eClassifiers>\n" +
						"</ecore:EPackage>\n" +
						"";
		IFile file = createEcoreIFile(getTestProject().getName(), "OK.ecore", testDocument);
		doValidation(file, null, null);
	}

	@Override
	protected void doValidation(@NonNull IFile file, @Nullable Class<?> exceptionClass, @Nullable List<@NonNull String> expectedMarkerTexts) {
		ValidationEntry validationEntry = new ValidationEntry(file, ClassUtil.nonNullState(EValidator.MARKER));
		MultiValidationJob multiValidationJob = BaseUIActivator.getMultiValidationJob();
		assert multiValidationJob != null;
		multiValidationJob.addValidations(Collections.singletonList(validationEntry));
		try {
			synchronized (validationEntry) {
				validationEntry.wait(20000);
			}
			Throwable throwable = validationEntry.getThrowable();
			if (exceptionClass != null) {
				assertNotNull("The expected " + exceptionClass.getName() + " exception was not thrown", throwable);
				assert throwable != null;
				assertTrue("Expected a " + exceptionClass.getName() + " exception rather than a " + throwable.getClass().getName(), exceptionClass.isAssignableFrom(throwable.getClass()));
			}
			else {
				assertNull("Unexpected exception", throwable);
				List<MultiValidationJob.@NonNull MarkerData> markerDatas = validationEntry.getMarkerDatas();
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
