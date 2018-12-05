/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *   E.D.Willink (Obeo) - 425799 Validity View Integration
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.test;

import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.ResultConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ResultSet;
import org.eclipse.ocl.examples.emf.validation.validity.ResultValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.RootNode;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityManager;
import org.eclipse.ocl.examples.validity.test.ecoreTest.EClass2;
import org.eclipse.ocl.examples.validity.test.ecoreTest.impl.Eclass1Impl;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.completeoclcs.ClassifierContextDeclCS;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLDocumentCS;
import org.eclipse.ocl.xtext.completeoclcs.PackageDeclarationCS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Class testing the validityModel initialization mechanism.
 */
public class ValidityModelTests extends AbstractValidityTestCase
{
	protected @NonNull ConstrainingNode assertHasConstrainingNodeByLabel(@NonNull ConstrainingNode constrainingNode, @NonNull String label, @NonNull Class<?> constrainingClass) {
		ConstrainingNode containedConstrainingNode = getConstrainingNodeByLabel(constrainingNode.getChildren(), label);
		assertNotNull(containedConstrainingNode);
		Class<? extends Object> constrainingObjectClass = containedConstrainingNode.getConstrainingObject().getClass();
		if (!constrainingClass.isAssignableFrom(constrainingObjectClass)) {
			fail(constrainingClass.getName() + " is not assignable from " + constrainingObjectClass.getName());
		}
		return containedConstrainingNode;
	}

	protected @NonNull ConstrainingNode assertHasConstrainingNodeByLabel(@NonNull RootNode rootNode, @NonNull String label, @NonNull Class<?> constrainingClass) {
		ConstrainingNode containedConstrainingNode = getConstrainingNodeByLabel(rootNode.getConstrainingNodes(), label);
		assertNotNull(containedConstrainingNode);
		Class<? extends Object> constrainingObjectClass = containedConstrainingNode.getConstrainingObject().getClass();
		if (!constrainingClass.isAssignableFrom(constrainingObjectClass)) {
			fail(constrainingClass.getName() + " is not assignable from " + constrainingObjectClass.getName());
		}
		return containedConstrainingNode;
	}

	protected @NonNull List<? extends ConstrainingNode> assertHasConstrainingNodes(@NonNull ConstrainingNode constrainingNode, int expectedChildCount) {
		List<ConstrainingNode> children = constrainingNode.getChildren();
		assertEquals("Expected child count for " + constrainingNode, expectedChildCount, children.size());
		return children;
	}

	protected @NonNull List<? extends ConstrainingNode> assertHasConstrainingNodes(@NonNull RootNode rootNode, int expectedChildCount) {
		List<? extends ConstrainingNode> children = rootNode.getConstrainingNodes();
		assertEquals("Expected child count for " + rootNode, expectedChildCount, children.size());
		return children;
	}

	protected @NonNull LeafConstrainingNode assertHasLeafConstrainingNodeByLabel(@NonNull ConstrainingNode constrainingNode, @NonNull String label) {
		ConstrainingNode containedConstrainingNode = getConstrainingNodeByLabel(constrainingNode.getChildren(), label);
		assertTrue(containedConstrainingNode instanceof LeafConstrainingNode);
		return (LeafConstrainingNode) containedConstrainingNode;
	}

	protected @NonNull ResultConstrainingNode assertHasResultConstrainingNodeByLabel(@NonNull ConstrainingNode constrainingNode, @NonNull String label) {
		ConstrainingNode containedConstrainingNode = getConstrainingNodeByLabel(constrainingNode.getChildren(), label);
		assertTrue(containedConstrainingNode instanceof ResultConstrainingNode);
		assertTrue(containedConstrainingNode.getConstrainingObject() == null);
		return (ResultConstrainingNode) containedConstrainingNode;
	}

	protected @NonNull List<? extends ValidatableNode> assertHasValidatableNodes(@NonNull RootNode rootNode, int expectedChildCount) {
		List<? extends ValidatableNode> children = rootNode.getValidatableNodes();
		assertEquals("Expected child count for " + rootNode, expectedChildCount, children.size());
		return children;
	}

	protected @NonNull List<? extends ValidatableNode> assertHasValidatableNodes(@NonNull ValidatableNode validatableNode, int expectedChildCount) {
		List<? extends ValidatableNode> children = validatableNode.getChildren();
		assertEquals("Expected child count for " + validatableNode, expectedChildCount, children.size());
		return children;
	}

	protected @NonNull ValidatableNode assertHasValidatableNodeByLabel(@NonNull RootNode rootNode, @NonNull String label, @NonNull Class<?> constrainingClass) {
		ValidatableNode containedValidatableNode = getValidatableNodeByLabel(rootNode.getValidatableNodes(), label);
		assertNotNull(containedValidatableNode);
		assertTrue(constrainingClass.isAssignableFrom(containedValidatableNode.getConstrainedObject().getClass()));
		return containedValidatableNode;
	}

	protected @NonNull ValidatableNode assertHasValidatableNodeByLabel(@NonNull ValidatableNode validatableNode, @NonNull String label, @NonNull Class<?> constrainingClass) {
		ValidatableNode containedValidatableNode = getValidatableNodeByLabel(validatableNode.getChildren(), label);
		assertNotNull(containedValidatableNode);
		assertTrue(constrainingClass.isAssignableFrom(containedValidatableNode.getConstrainedObject().getClass()));
		return containedValidatableNode;
	}

	protected @NonNull ResultValidatableNode assertHasResultValidatableNodeByLabel(@NonNull ValidatableNode validatableNode, @NonNull String label) {
		ValidatableNode containedValidatableNode = getValidatableNodeByLabel(validatableNode.getChildren(), label);
		assertTrue(containedValidatableNode instanceof ResultValidatableNode);
		assertNull(containedValidatableNode.getConstrainedObject());
		return (ResultValidatableNode) containedValidatableNode;
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		initTestModels();
		initValidityManager(EMFPlugin.IS_ECLIPSE_RUNNING ? null : new ValidityManager());
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testValidityModel_AllResourcesArePresent() {
		Collection<Resource> resources = validityModel.getResources();
		assertEquals((Integer) 5, (Integer) resources.size());
		assertTrue(resources.contains(ecoreResource));
		assertTrue(resources.contains(ecoreResource2));
		assertTrue(resources.contains(ecoreResource3));
		assertTrue(isCompleteOCLCSResourcePresent(resources));
	}

	@Test
	public void testValidityModelInitialization_RootValidatableNodesArePresent() {
		final ValidatableNode _e1Att1 = assertHasValidatableNodeByLabel(rootNode, VALIDATABLE_ECLASS1_E1_ATT1, Eclass1Impl.class);
		assertHasValidatableNodes(_e1Att1, 3);
		assertHasResultValidatableNodeByLabel(_e1Att1, CONSTRAINABLE_ECLASS1_CONSTRAINT);

		final ValidatableNode _e1Att1__EClass2 = assertHasValidatableNodeByLabel(_e1Att1, VALIDATABLE_ECLASS2, EClass2.class);
		assertHasValidatableNodes(_e1Att1__EClass2, 1);
		assertHasResultValidatableNodeByLabel(_e1Att1__EClass2, CONSTRAINABLE_ECLASS2_CONSTRAINT);

		final ValidatableNode containedValidatableNode2 = assertHasValidatableNodeByLabel(_e1Att1, VALIDATABLE_ECLASS2, EClass2.class);		// FIXME Duplicate
		assertHasValidatableNodes(containedValidatableNode2, 1);
		assertHasResultValidatableNodeByLabel(containedValidatableNode2, CONSTRAINABLE_ECLASS2_CONSTRAINT);

		final ValidatableNode _ecoreTest2 = assertHasValidatableNodeByLabel(rootNode, VALIDATABLE_ECORETEST2, EPackage.class);
		assertHasValidatableNodes(_ecoreTest2, 9/*3*/);
		assertHasResultValidatableNodeByLabel(_ecoreTest2, CONSTRAINABLE_EPACKAGE_CONSTRAINT_2);
		assertHasResultValidatableNodeByLabel(_ecoreTest2, CONSTRAINABLE_EPACKAGE_CONSTRAINT);

		final ValidatableNode _ecoreTest2__Eclass5 = assertHasValidatableNodeByLabel(_ecoreTest2, VALIDATABLE_E_CLASS5, EClass.class);
		assertHasValidatableNodes(_ecoreTest2__Eclass5, 13/*2*/);
		assertHasResultValidatableNodeByLabel(_ecoreTest2__Eclass5, CONSTRAINABLE_ECLASS_CONSTRAINT);

		final ValidatableNode _ecoreTest2__Eclass5__eAttribute5 = assertHasValidatableNodeByLabel(_ecoreTest2__Eclass5, TITLE_E_ATTRIBUTE5_E_STRING, EAttribute.class);
		assertHasValidatableNodes(_ecoreTest2__Eclass5__eAttribute5, 9/*1*/);
		assertHasResultValidatableNodeByLabel(_ecoreTest2__Eclass5__eAttribute5, CONSTRAINABLE_EATTRIBUTE_CONSTRAINT);

	}

	@Test
	public void testValidityModelInitializationModelElements() {
		final ValidatableNode _e1Att1 = assertHasValidatableNodeByLabel(rootNode, VALIDATABLE_ECLASS1_E1_ATT1, Eclass1Impl.class);
		assertHasValidatableNodes(_e1Att1, 3);
		assertHasResultValidatableNodeByLabel(_e1Att1, CONSTRAINABLE_ECLASS1_CONSTRAINT);

		final ValidatableNode _e1Att1__EClass2 = assertHasValidatableNodeByLabel(_e1Att1, VALIDATABLE_ECLASS2, EClass2.class);		// FIXME Duplicate
		assertTrue(_e1Att1__EClass2.getConstrainedObject() instanceof EClass2);
		assertHasValidatableNodes(_e1Att1__EClass2, 1);
		assertHasResultValidatableNodeByLabel(_e1Att1__EClass2, CONSTRAINABLE_ECLASS2_CONSTRAINT);

		final ValidatableNode containedValidatableNode2 = assertHasValidatableNodeByLabel(_e1Att1, VALIDATABLE_ECLASS2, EClass2.class);
		assertHasValidatableNodes(containedValidatableNode2, 1);
		assertHasResultValidatableNodeByLabel(containedValidatableNode2, CONSTRAINABLE_ECLASS2_CONSTRAINT);

		final ValidatableNode _ecoreTest2 = assertHasValidatableNodeByLabel(rootNode, VALIDATABLE_ECORETEST2, EPackage.class);
		assertHasValidatableNodes(_ecoreTest2, 9/*3*/);
		assertHasResultValidatableNodeByLabel(_ecoreTest2, CONSTRAINABLE_EPACKAGE_CONSTRAINT_2);
		assertHasResultValidatableNodeByLabel(_ecoreTest2, CONSTRAINABLE_EPACKAGE_CONSTRAINT);

		final ValidatableNode _ecoreTest2__Eclass5 = assertHasValidatableNodeByLabel(_ecoreTest2, VALIDATABLE_E_CLASS5, EClass.class);
		assertHasResultValidatableNodeByLabel(_ecoreTest2__Eclass5, CONSTRAINABLE_ECLASS_CONSTRAINT);
		
		final ValidatableNode _ecoreTest2__Eclass5__eAttribute5 = assertHasValidatableNodeByLabel(_ecoreTest2__Eclass5, TITLE_E_ATTRIBUTE5_E_STRING, EAttribute.class);
		assertHasValidatableNodes(_ecoreTest2__Eclass5__eAttribute5, 9/*1*/);
		assertHasResultValidatableNodeByLabel(_ecoreTest2__Eclass5__eAttribute5, CONSTRAINABLE_EATTRIBUTE_CONSTRAINT);
	}

	@Test
	public void testValidityModelInitializationConstraints() {

		Collection<Resource> resources = validityModel.getResources();
		assertEquals((Integer) 5, (Integer) resources.size());
		assertTrue(resources.contains(ecoreResource));
		assertTrue(resources.contains(ecoreResource2));
		assertTrue(resources.contains(ecoreResource3));
		assertTrue(isCompleteOCLCSResourcePresent(resources));

//		for (Result result : results) {
//			System.out.println(result.getResultValidatableNode().getLabel() + " " + result.getResultConstrainingNode().getLabel());
//		}

		// Tests that we have all the RootConstrainingNode ecoreTest and ecore.
		assertHasConstrainingNodes(rootNode, 3/*4*//*3*//*2*/);
		final ConstrainingNode _ecore = assertHasConstrainingNodeByLabel(rootNode, CONSTRAINABLE_ECORE, EPackage.class);
		assertHasConstrainingNodes(_ecore, 45/*3*/);
		final ConstrainingNode _ecore__EClass = assertHasConstrainingNodeByLabel(_ecore, "EClass", EClass.class);
		assertHasConstrainingNodes(_ecore__EClass, 8);
		final ConstrainingNode _ecore__EPackage = assertHasConstrainingNodeByLabel(_ecore, "EPackage", EClass.class);
		assertHasConstrainingNodes(_ecore__EPackage, 5);
		
		final ConstrainingNode _ecore_ocl = assertHasConstrainingNodeByLabel(rootNode, CONSTRAINABLE_ECORE_OCL_ECORE, CompleteOCLDocumentCS.class);
		assertHasConstrainingNodes(_ecore_ocl, 1);
		final ConstrainingNode _ecore_ocl_ecore = assertHasConstrainingNodeByLabel(_ecore_ocl, "ecore", PackageDeclarationCS.class);
		assertHasConstrainingNodes(_ecore_ocl_ecore, 3);
		final ConstrainingNode _ecore_ocl_ecore__EClass = assertHasConstrainingNodeByLabel(_ecore_ocl_ecore, "EClass", ClassifierContextDeclCS.class);
		assertHasConstrainingNodes(_ecore_ocl_ecore__EClass, 1);
		final ConstrainingNode _ecore_ocl_ecore__EClass__eclass_constraint = assertHasLeafConstrainingNodeByLabel(_ecore_ocl_ecore__EClass, "eclass_constraint");
		assertTrue(_ecore_ocl_ecore__EClass__eclass_constraint.getConstrainingObject() instanceof ConstraintCS);
		assertHasConstrainingNodes(_ecore_ocl_ecore__EClass__eclass_constraint, 4);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EClass__eclass_constraint, CONSTRAINABLE_ECLASS1);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EClass__eclass_constraint, CONSTRAINABLE_ECLASS5);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EClass__eclass_constraint, CONSTRAINABLE_ECLASS2);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EClass__eclass_constraint, CONSTRAINABLE_ECLASS3);

		final ConstrainingNode _ecore_ocl_ecore__EPackage = assertHasConstrainingNodeByLabel(_ecore_ocl_ecore, "EPackage", ClassifierContextDeclCS.class);
		assertHasConstrainingNodes(_ecore_ocl_ecore__EPackage, 2);
		final ConstrainingNode _ecore_ocl_ecore__EPackage__epackage_constraint = assertHasLeafConstrainingNodeByLabel(_ecore_ocl_ecore__EPackage, "epackage_constraint");
		assertHasConstrainingNodes(_ecore_ocl_ecore__EPackage__epackage_constraint, 2);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EPackage__epackage_constraint, CONSTRAINABLE_ECORETEST);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EPackage__epackage_constraint, VALIDATABLE_ECORETEST2);

		final ConstrainingNode _ecore_ocl_ecore__EPackage__epackage_constraint_2 = assertHasLeafConstrainingNodeByLabel(_ecore_ocl_ecore__EPackage, "epackage_constraint_2");
		assertHasConstrainingNodes(_ecore_ocl_ecore__EPackage__epackage_constraint_2, 2);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EPackage__epackage_constraint_2, CONSTRAINABLE_ECORETEST);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EPackage__epackage_constraint_2, VALIDATABLE_ECORETEST2);

		final ConstrainingNode _ecore_ocl_ecore__EAttribute = assertHasConstrainingNodeByLabel(_ecore_ocl_ecore, "EAttribute", ClassifierContextDeclCS.class);
		assertHasConstrainingNodes(_ecore_ocl_ecore__EAttribute, 1);
		final ConstrainingNode _ecore_ocl_ecore__EAttribute__eattribute_constraint = assertHasLeafConstrainingNodeByLabel(_ecore_ocl_ecore__EAttribute, "eattribute_constraint");
		assertHasConstrainingNodes(_ecore_ocl_ecore__EAttribute__eattribute_constraint, 5);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EAttribute__eattribute_constraint, VALIDATABLE_E_ATTRIBUTE1_E_STRING);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EAttribute__eattribute_constraint, VALIDATABLE_E_ATTRIBUTE2_E_STRING);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EAttribute__eattribute_constraint, VALIDATABLE_E_ATTRIBUTE3_E_SHORT);
		assertHasResultConstrainingNodeByLabel(_ecore_ocl_ecore__EAttribute__eattribute_constraint, VALIDATABLE_E_ATTRIBUTE5_E_STRING);

		final ConstrainingNode _ecoreTest = assertHasConstrainingNodeByLabel(rootNode, CONSTRAINABLE_ECORETEST_OCL_ECORE, CompleteOCLDocumentCS.class);
		assertHasConstrainingNodes(_ecoreTest, 1);
		final ConstrainingNode _ecoreTest_ecoreTest = assertHasConstrainingNodeByLabel(_ecoreTest, "ecoreTest", PackageDeclarationCS.class);
		assertHasConstrainingNodes(_ecoreTest_ecoreTest, 2);

		final ConstrainingNode _ecoreTest__Eclass1 = assertHasConstrainingNodeByLabel(_ecoreTest_ecoreTest, "Eclass1", ClassifierContextDeclCS.class);
		assertHasConstrainingNodes(_ecoreTest__Eclass1, 1);
		final ConstrainingNode _ecoreTest__Eclass1__eclass1_constraint = assertHasLeafConstrainingNodeByLabel(_ecoreTest__Eclass1, "eclass1_constraint");
		assertHasConstrainingNodes(_ecoreTest__Eclass1__eclass1_constraint, 1);
		assertHasResultConstrainingNodeByLabel(_ecoreTest__Eclass1__eclass1_constraint, VALIDATABLE_ECLASS1_E1_ATT1);

		final ConstrainingNode _ecoreTest__EClass2 = assertHasConstrainingNodeByLabel(_ecoreTest_ecoreTest, "EClass2", ClassifierContextDeclCS.class);
		assertHasConstrainingNodes(_ecoreTest__EClass2, 1);
		final ConstrainingNode _ecoreTest__EClass2__eclass2_constraint = assertHasLeafConstrainingNodeByLabel(_ecoreTest__EClass2, "eclass2_constraint");
		assertHasConstrainingNodes(_ecoreTest__EClass2__eclass2_constraint, 2);
		assertHasResultConstrainingNodeByLabel(_ecoreTest__EClass2__eclass2_constraint, VALIDATABLE_E_CLASS2);
		assertHasResultConstrainingNodeByLabel(_ecoreTest__EClass2__eclass2_constraint, VALIDATABLE_E_CLASS2);		// FIXME Duplicate
	}

	@Test
	public void testValidityModelCreateResultSet() {
		ResultSet resultSet = validityManager.createResultSet(new NullProgressMonitor());
		List<Result> results = resultSet.getResults();
		for (Result result : results) {
			ResultValidatableNode resultValidatableNode = result.getResultValidatableNode();
			assertNotNull(resultValidatableNode);
			ResultConstrainingNode resultConstrainingNode = resultValidatableNode.getResultConstrainingNode();
			assertNotNull(resultConstrainingNode);
			assertEquals(resultValidatableNode, resultConstrainingNode.getResultValidatableNode());
		}
		assertEquals(EXPECTED_RESULTS, (Integer) results.size());
	}
}
