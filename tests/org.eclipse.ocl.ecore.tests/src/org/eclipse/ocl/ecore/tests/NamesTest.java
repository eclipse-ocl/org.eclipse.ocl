/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *  E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.ecore.tests;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.ocl.internal.l10n.OCLMessages;
import org.eclipse.ocl.options.EvaluationOptions;
import org.eclipse.ocl.options.ParsingOptions;

/**
 * Tests for the name overload and override resolution.
 */
@SuppressWarnings("nls")
public class NamesTest extends AbstractTestSuite
{
	protected static final String NAMES_TEST_XMI = "/model/NamesTest.xmi";
	
	public EClass level0Class;
	public EClass level1Class;
	public EClass level2aClass;
	public EClass level2bClass;
	public EClass level3Class;
	public EClass level4Class;
	public EObject container;
	public EObject level0;
	public EObject level1;
	public EObject level2a;
	public EObject level2b;
	public EObject level3;
	public EObject level4;
	//
	// Test framework
	//
	@Override
	protected void setUp() {
		super.setUp();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
				"xmi", new EcoreResourceFactoryImpl());		
		}
		initModel();
	}

	protected void initModel() {
		URI uri = getTestModelURI(NAMES_TEST_XMI);
		Resource testResource = resourceSet.getResource(uri, true);
		container = testResource.getContents().get(0);
		EClass containerClass = container.eClass();
		EPackage namesTestPackage = containerClass.getEPackage();
		level0Class = (EClass) namesTestPackage.getEClassifier("Level0");
		level1Class = (EClass) namesTestPackage.getEClassifier("Level1");
		level2aClass = (EClass) namesTestPackage.getEClassifier("Level2a");
		level2bClass = (EClass) namesTestPackage.getEClassifier("Level2b");
		level3Class = (EClass) namesTestPackage.getEClassifier("Level3");
		level4Class = (EClass) namesTestPackage.getEClassifier("Level4");
		EStructuralFeature level0Name = level0Class.getEStructuralFeature("name");
		level0 = getObject(container, level0Name, "level0");
		level1 = getObject(container, level0Name, "level1");
		level2a = getObject(container, level0Name, "level2a");
		level2b = getObject(container, level0Name, "level2b");
		level3 = getObject(container, level0Name, "level3");
		level4 = getObject(container, level0Name, "level4");
	}

	private EObject getObject(EObject eContainer, EStructuralFeature eFeature, Object values) {
		for (EObject eObject : eContainer.eContents()) {
			if (values.equals(eObject.eGet(eFeature))) {
				return eObject;
			}
		}
		return null;
	}

	public void test_overriden_withStaticDispatch() {
		helper.setContext(level0Class);
		assertQueryEquals(level0, "Level0::overridden", "self.overridden()");
		assertQueryEquals(level1, "Level0::overridden", "self.overridden()");
		assertQueryEquals(level2a, "Level0::overridden", "self.overridden()");
		assertQueryEquals(level2b, "Level0::overridden", "self.overridden()");
		assertQueryEquals(level3, "Level0::overridden", "self.overridden()");
		assertQueryEquals(level4, "Level0::overridden", "self.overridden()");
		helper.setContext(level2aClass);
		assertQueryEquals(level0, "Level2a::overridden", "self.overridden()");
		assertQueryEquals(level1, "Level2a::overridden", "self.overridden()");
		assertQueryEquals(level2a, "Level2a::overridden", "self.overridden()");
		assertQueryEquals(level2b, "Level2a::overridden", "self.overridden()");
		assertQueryEquals(level3, "Level2a::overridden", "self.overridden()");
		assertQueryEquals(level4, "Level2a::overridden", "self.overridden()");
		helper.setContext(level4Class);
		assertQueryEquals(level0, "Level4::overridden", "self.overridden()");
		assertQueryEquals(level1, "Level4::overridden", "self.overridden()");
		assertQueryEquals(level2a, "Level4::overridden", "self.overridden()");
		assertQueryEquals(level2b, "Level4::overridden", "self.overridden()");
		assertQueryEquals(level3, "Level4::overridden", "self.overridden()");
		assertQueryEquals(level4, "Level4::overridden", "self.overridden()");
	}

	public void test_overriden_withDynamicDispatch() {
	    EvaluationOptions.setOption(ocl.getEvaluationEnvironment(), EvaluationOptions.DYNAMIC_DISPATCH, true);
		helper.setContext(level0Class);
 		assertQueryEquals(level0, "Level0::overridden", "self.overridden()");
		assertQueryEquals(level1, "Level1::overridden", "self.overridden()");
		assertQueryEquals(level2a, "Level2a::overridden", "self.overridden()");
		assertQueryEquals(level2b, "Level2b::overridden", "self.overridden()");
		assertQueryEquals(level3, "Level3::overridden", "self.overridden()");
		assertQueryEquals(level4, "Level4::overridden", "self.overridden()");
		helper.setContext(level2aClass);
 		assertQueryEquals(level0, "Level0::overridden", "self.overridden()");
		assertQueryEquals(level1, "Level1::overridden", "self.overridden()");
		assertQueryEquals(level2a, "Level2a::overridden", "self.overridden()");
		assertQueryEquals(level2b, "Level2b::overridden", "self.overridden()");
		assertQueryEquals(level3, "Level3::overridden", "self.overridden()");
		assertQueryEquals(level4, "Level4::overridden", "self.overridden()");
		helper.setContext(level4Class);
 		assertQueryEquals(level0, "Level0::overridden", "self.overridden()");
		assertQueryEquals(level1, "Level1::overridden", "self.overridden()");
		assertQueryEquals(level2a, "Level2a::overridden", "self.overridden()");
		assertQueryEquals(level2b, "Level2b::overridden", "self.overridden()");
		assertQueryEquals(level3, "Level3::overridden", "self.overridden()");
		assertQueryEquals(level4, "Level4::overridden", "self.overridden()");
	}

	public void test_ambiguous_withStaticDispatch() {
		helper.setContext(level0Class);
		assertSemanticError("self.ambiguous()", OCLMessages.OperationNotFound_ERROR_, "ambiguous()", "Level0");
		helper.setContext(level1Class);
		assertQueryEquals(level0, "Level1::ambiguous", "self.ambiguous()");
		assertQueryEquals(level1, "Level1::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2a, "Level1::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2b, "Level1::ambiguous", "self.ambiguous()");
		assertQueryEquals(level3, "Level1::ambiguous", "self.ambiguous()");
		assertQueryEquals(level4, "Level1::ambiguous", "self.ambiguous()");
		helper.setContext(level2aClass);
		assertQueryEquals(level0, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level1, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2a, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2b, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level3, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level4, "Level2a::ambiguous", "self.ambiguous()");
		helper.setContext(level3Class);
		assertQueryEquals(level0, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level1, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2a, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2b, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level3, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level4, "Level2a::ambiguous", "self.ambiguous()");
		helper.setContext(level4Class);
		assertQueryEquals(level0, "Level4::ambiguous", "self.ambiguous()");
		assertQueryEquals(level1, "Level4::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2a, "Level4::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2b, "Level4::ambiguous", "self.ambiguous()");
		assertQueryEquals(level3, "Level4::ambiguous", "self.ambiguous()");
		assertQueryEquals(level4, "Level4::ambiguous", "self.ambiguous()");
	    ParsingOptions.setOption(ocl.getEnvironment(), ParsingOptions.OVERLOAD_AMBIGUITY_IS_INVALID, true);
	    helper.setContext(level3Class);
	    assertSemanticError("self.ambiguous()", OCLMessages.OperationNotFound_ERROR_, "ambiguous()", "Level3");
	}

	public void test_ambiguous_withDynamicDispatch() {
	    EvaluationOptions.setOption(ocl.getEvaluationEnvironment(), EvaluationOptions.DYNAMIC_DISPATCH, true);
		helper.setContext(level0Class);
		assertSemanticError("self.ambiguous()", OCLMessages.OperationNotFound_ERROR_, "ambiguous()", "Level0");
		helper.setContext(level1Class);
		assertQueryInvalid(level0, "self.ambiguous()");
		assertQueryEquals(level1, "Level1::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2a, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2b, "Level2b::ambiguous", "self.ambiguous()");
		assertQueryInvalid(level3, "self.ambiguous()");
		assertQueryEquals(level4, "Level4::ambiguous", "self.ambiguous()");
		helper.setContext(level2aClass);
		assertQueryInvalid(level0, "self.ambiguous()");
		assertQueryEquals(level1, "Level1::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2a, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2b, "Level2b::ambiguous", "self.ambiguous()");
		assertQueryInvalid(level3, "self.ambiguous()");
		assertQueryEquals(level4, "Level4::ambiguous", "self.ambiguous()");
		helper.setContext(level3Class);
		assertQueryInvalid(level0, "self.ambiguous()");
		assertQueryEquals(level1, "Level1::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2a, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2b, "Level2b::ambiguous", "self.ambiguous()");
		assertQueryInvalid(level3, "self.ambiguous()");
		assertQueryEquals(level4, "Level4::ambiguous", "self.ambiguous()");
		helper.setContext(level4Class);
		assertQueryInvalid(level0, "self.ambiguous()");
		assertQueryEquals(level1, "Level1::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2a, "Level2a::ambiguous", "self.ambiguous()");
		assertQueryEquals(level2b, "Level2b::ambiguous", "self.ambiguous()");
		assertQueryInvalid(level3, "self.ambiguous()");
		assertQueryEquals(level4, "Level4::ambiguous", "self.ambiguous()");
	}

	public void test_overloads() {
		helper.setContext(level0Class);
		assertQueryEquals(level0, "Level0::overload(Level4)", "self.overload(self.asLevel4())");

		helper.setContext(level0Class);
		assertQueryEquals(level0, "Level0::overload(String)", "self.overload(self.toString())");
		assertQueryEquals(level0, "Level0::overload(Level0)", "self.overload(self.asLevel0())");
		assertQueryEquals(level0, "Level0::overload(Level1)", "self.overload(self.asLevel1())");
		assertQueryEquals(level0, "Level0::overload(Level2a)", "self.overload(self.asLevel2a())");
		assertQueryEquals(level0, "Level0::overload(Level2b)", "self.overload(self.asLevel2b())");
		assertQueryEquals(level0, "Level0::overload(Level3)", "self.overload(self.asLevel3())");
		assertQueryEquals(level0, "Level0::overload(Level4)", "self.overload(self.asLevel4())");
		helper.setContext(level4Class);
		assertQueryEquals(level0, "Level0::overload(String)", "self.overload(self.toString())");
		assertQueryEquals(level0, "Level0::overload(Level0)", "self.overload(self.asLevel0())");
		assertQueryEquals(level0, "Level1::overload(Level1)", "self.overload(self.asLevel1())");
		assertQueryEquals(level0, "Level2a::overload(Level2a)", "self.overload(self.asLevel2a())");
		assertQueryEquals(level0, "Level2b::overload(Level2b)", "self.overload(self.asLevel2b())");
		assertQueryEquals(level0, "Level3::overload(Level3)", "self.overload(self.asLevel3())");
		assertQueryEquals(level0, "Level4::overload(Level4)", "self.overload(self.asLevel4())");
	}
}
