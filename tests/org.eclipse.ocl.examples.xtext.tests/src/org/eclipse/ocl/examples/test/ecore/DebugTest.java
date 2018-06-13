/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.test.ecore;

import org.eclipse.ocl.examples.test.generic.GenericDebugTest;

/**
 * The JUnit4 GUI does not support re-running individual tests in isolation.
 *
 * This dummy test is therefore a debugging placeHolder for re-use while debugging.
 */
public class DebugTest extends GenericDebugTest {
	//	public org.eclipse.ocl.pivot.Package pkg1;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		//		MetamodelManager metamodelManager = ocl.getMetamodelManager();
		//        Model root = metamodelManager.createModel(null);
		//        pkg1 = createPackage(root, "pkg1");
		//        helper.setContext(getMetaclass("Model"));
	}

	/**
	 * Tests that dot notation can be used successively to navigate multiple
	 * collection-type references.
	 */
	public void testDebug() {
		//		assertQueryFalse(null, "Sequence{4, 5, 'test'} = OrderedSet{4, 5, 'test', 5}");
		/*		helper.setContext(EcorePackage.Literals.EPACKAGE);

		try {
			OCLExpression<EClassifier> expr = helper
				.createQuery("self.eClassifiers->union(self.eSubpackages.eClassifiers->asSet())->asSet()");

			assertEquals(new java.util.HashSet<EClassifier>(
				ExpressionsPackage.eINSTANCE.getEClassifiers()), ocl.evaluate(
				ExpressionsPackage.eINSTANCE, expr));
		} catch (Exception exc) {
			fail("Failed to parse or evaluate: " + exc.getLocalizedMessage());
		} */
	}
}
