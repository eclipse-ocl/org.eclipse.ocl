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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.emf.validation.validity.ConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.ResultSet;
import org.eclipse.ocl.examples.emf.validation.validity.RootConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ValidatableNode;
import org.eclipse.ocl.examples.emf.validation.validity.manager.ValidityManager;
import org.eclipse.ocl.examples.validity.test.ecoreTest.Eclass1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Class testing the validityManager initialization mechanism.
 */
public class ValidityManagerTests extends AbstractValidityTestCase
{
	public static class TestValidityManager extends ValidityManager
	{
		public void putResults(ResultSet resultSet) {
			for (Result result : resultSet.getResults()) {
				resultsMap.put(result.getResultValidatableNode(), result);
			}
		}
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
		initTestModels();
		Set<Resource> newResources = new HashSet<Resource>();
		newResources.add(ecoreResource);
		newResources.add(ecoreResource2);
		newResources.add(ecoreResource3);
		newResources.add(oclResource);
		newResources.add(oclResource2);
		initValidityManager(new TestValidityManager());
	}

	@After
	public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testValidityManagerGetConstrainingNodeResults() {
		ConstrainingNode constrainingNode = validityManager.getConstrainingNode(ecoreResource2.getContents().get(0));
		assertTrue(constrainingNode instanceof RootConstrainingNode);
		assertEquals(CONSTRAINABLE_ECLASS1_E1_ATT1, constrainingNode.getLabel());

		ResultSet resultSet = validityModel.createResultSet(new NullProgressMonitor());
		((TestValidityManager)validityManager).putResults(resultSet);

		ConstrainingNode constrainingNodeFromRootByLabel = getConstrainingNodeByLabel(rootNode.getConstrainingNodes(), CONSTRAINABLE_ECORETEST_OCL_ECORE);
		ConstrainingNode constrainingNodeByLabel1 = getConstrainingNodeByLabel(constrainingNodeFromRootByLabel.getChildren(), "ecoreTest");
		ConstrainingNode constrainingNodeByLabel2 = getConstrainingNodeByLabel(constrainingNodeByLabel1.getChildren(), "Eclass1");
		List<Result> constrainingNodeResults = validityManager.getConstrainingNodeResults(constrainingNodeByLabel2);
		assertEquals((Integer) 1, (Integer) constrainingNodeResults.size());
		assertEquals(VALIDATABLE_ECLASS1_E1_ATT1, constrainingNodeResults.get(0).getResultConstrainingNode().getLabel());
		assertEquals(CONSTRAINABLE_ECLASS1_CONSTRAINT, constrainingNodeResults.get(0).getResultValidatableNode().getLabel());
		assertEquals("eclass1_constraint", constrainingNodeResults.get(0).getLeafConstrainingNode().getLabel());
	}

	@Test
	public void testValidityManagerGetValidatableNodeResults() {
		ResultSet resultSet = validityModel.createResultSet(new NullProgressMonitor());
		((TestValidityManager)validityManager).putResults(resultSet);

		ValidatableNode rootValidatableNode = getValidatableNodeByLabel(rootNode.getValidatableNodes(), VALIDATABLE_ECORETEST2);
		List<Result> validatableNodeResults = validityManager.getValidatableNodeResults(rootValidatableNode);

		assertEquals((Integer) 31, (Integer) validatableNodeResults.size());

		assertNotNull(getResultFromResultValidatableNode(validatableNodeResults, CONSTRAINABLE_EPACKAGE_CONSTRAINT));

		assertNotNull(getResultFromResultValidatableNode(validatableNodeResults, CONSTRAINABLE_EPACKAGE_CONSTRAINT_2));

		assertNotNull(getResultFromResultValidatableNode(validatableNodeResults, CONSTRAINABLE_ECLASS_CONSTRAINT));

		assertNotNull(getResultFromResultValidatableNode(validatableNodeResults, CONSTRAINABLE_EATTRIBUTE_CONSTRAINT));
	}

	@Test
	public void testValidityManagerGetConstrainingNode() {
		EObject eRoot2 = ecoreResource2.getContents().get(0);
		ConstrainingNode constrainingNode = validityManager.getConstrainingNode(eRoot2);
		assertTrue(constrainingNode instanceof RootConstrainingNode);
		assertEquals(CONSTRAINABLE_ECLASS1_E1_ATT1, constrainingNode.getLabel());

		constrainingNode = validityManager.getConstrainingNode(((Eclass1) eRoot2).getClasses2().get(0));
		assertEquals("EClass2", constrainingNode.getLabel());
	}

	@Test
	public void testValidityManagerSetInput() {
		int resourcesCount = 5;	// Loaded(3 *.ecore, 2 *.ocl)
		validityManager.getModel().getResources().clear();
		assertEquals((Integer) 0, (Integer) validityManager.getModel().getResources().size());
		EObject eObject = ecoreResource2.getContents().get(0);
		validityManager.setInput(eObject);
		assertEquals((Integer) resourcesCount, (Integer) validityManager.getModel().getResources().size());
		validityManager.getModel().getResources().clear();
		assertEquals((Integer) 0, (Integer) validityManager.getModel().getResources().size());
		validityManager.setInput(ecoreResource);
		assertEquals((Integer) resourcesCount, (Integer) validityManager.getModel().getResources().size());
	}
}
