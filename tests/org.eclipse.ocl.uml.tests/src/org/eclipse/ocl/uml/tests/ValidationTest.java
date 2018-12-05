/*******************************************************************************
 * Copyright (c) 2005, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.uml.tests;

import org.eclipse.ocl.SemanticException;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.uml2.uml.Classifier;


/**
 * Tests the validation of OCL expressions.
 *
 * @author Christian W. Damus (cdamus)
 */
@SuppressWarnings("nls")
public class ValidationTest extends AbstractTestSuite {

	/**
	 * Tests that operation calls may only invoke query operations.
	 */
	public void test_callNonQueryOperation_136778() {
		expectModified = true;
		// newApple() is not a query operation
		OCLExpression<Classifier> expr = parseConstraintUnvalidated(
			"package ocltest context Apple " +
					"inv: Apple.allInstances()->includes(self.newApple()) " +
				"endpackage");

		try {
			ocl.validate(expr);
			fail("Should not have successfully validated");
		} catch (SemanticException e) {
			// success
			debugPrintln("Got expected exception: " + e.getLocalizedMessage());
		}
	}

	//
	// Fixture methods
	//

	@Override
	protected void setUp() {
		super.setUp();

		apple_newApple.setIsQuery(false);
	}
}
