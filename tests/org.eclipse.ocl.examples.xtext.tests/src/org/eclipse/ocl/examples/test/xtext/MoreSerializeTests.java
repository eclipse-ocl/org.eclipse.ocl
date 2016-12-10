/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.ocl.pivot.utilities.OCL;


/**
 * Tests that check that an Ecore model can be serialized to OCLinEcore.
 */
public class MoreSerializeTests extends SerializeTests
{
	// Fails due to bad specialisation templates
	public void testEcoreSerialize() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, "Ecore");
		ocl.dispose();
	}

	//	public void testMarkupSerialize() throws Exception {
	//		doSerialize(ocl, "Markup");
	//	}

	//	public void test_model_uml_Serialize() throws Exception {
	//		doSerializeUML("model");
	//	}

	// Fails ? because lowerBounds do not propagate from UML2Ecore ?
	// Fails because no support for redefines
	public void test_Fruit_uml_Serialize() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerializeUML(ocl, "Fruit", null);
		ocl.dispose();
	}

	// Fails ?? due to missing specialized features
	public void testOCLSerialize() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, "OCL");
		ocl.dispose();
	}

	// Fails due to conflicting ecore package
	public void testOCLEcoreSerialize() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, "OCLEcore");
		ocl.dispose();
	}

	// Fails because no support for -2 multiplicity
	public void testXMLTypeSerialize() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, "XMLType");
		ocl.dispose();
	}

	// Fails due to FIXME in BaseDeclarationVisitor.visitProperty; no CS syntax for implicit opposite
	public void testOCLTestSerialize() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		doSerialize(ocl, "OCLTest");
		ocl.dispose();
	}
}
