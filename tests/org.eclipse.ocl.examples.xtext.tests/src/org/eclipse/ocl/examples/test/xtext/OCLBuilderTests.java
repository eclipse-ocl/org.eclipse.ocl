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

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.xmi.UnresolvedReferenceException;

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
}
