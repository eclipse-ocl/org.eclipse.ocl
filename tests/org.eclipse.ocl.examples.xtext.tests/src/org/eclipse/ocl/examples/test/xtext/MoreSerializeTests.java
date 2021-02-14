/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

/**
 * Tests that check that an Ecore model can be serialized to OCLinEcore.
 */
public class MoreSerializeTests extends SerializeTests
{
	// Fails due to bad specialisation templates
	public void testEcoreSerialize() throws Exception {
		doSerialize(getTestModelURI("models/ecore/Ecore.ecore"));
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
		doSerializeUML(getTestModelURI("models/uml/Fruit.uml"), SUPPRESS_VALIDATION);
	}

	// Fails ?? due to missing specialized features
	public void testOCLSerialize() throws Exception {
		doSerialize(getTestModelURI("models/ecore/OCL.ecore"));
	}

	// Fails due to conflicting ecore package
	public void testOCLEcoreSerialize() throws Exception {
		doSerialize(getTestModelURI("models/ecore/OCLEcore.ecore"));
	}

	// Fails because no support for -2 multiplicity
	public void testXMLTypeSerialize() throws Exception {
		doSerialize(getTestModelURI("models/ecore/XMLType.ecore"));
	}

	// Fails due to FIXME in BaseDeclarationVisitor.visitProperty; no CS syntax for implicit opposite
	public void testOCLTestSerialize() throws Exception {
		doSerialize(getTestModelURI("models/ecore/OCLTest.ecore"));
	}
}
