/*******************************************************************************
 * Copyright (c) 2010, 2015 Eclipse Modeling Project and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   L.Goubet, E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for Boolean operations.
 */
@RunWith(value = Parameterized.class)
public class EvaluateBooleanOperationsTest4 extends PivotTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public EvaluateBooleanOperationsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getTestName(), useCodeGen ? getProjectMap() : OCL.NO_PROJECTS);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateBooleanOperations";
	}

	@BeforeClass public static void resetCounter() throws Exception {
		PivotTestSuite.resetCounter();
	}

	@Override
	@Before public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	@Test public void testBoolean() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "false");
		ocl.assertQueryTrue(null, "true");
		// invalid
		ocl.assertQueryFalse(null, "let b : Boolean = false in b");
		ocl.assertQueryTrue(null, "let b : Boolean = true in b");
		ocl.assertQueryNull(null, "let b : Boolean = null in b");
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in b");
		ocl.dispose();
	}

	@Test public void testBooleanAnd() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "false and false");
		ocl.assertQueryFalse(null, "false and true");
		ocl.assertQueryFalse(null, "true and false");
		ocl.assertQueryTrue(null, "true and true");
		// invalid
		ocl.assertQueryFalse(null, "let b : Boolean = invalid in false and b");
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in true and b");
		ocl.assertQueryFalse(null, "let a : Boolean = invalid in a and false");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid in a and true");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid, b : Boolean = invalid in a and b");
		// null
		ocl.assertQueryFalse(null, "let b : Boolean = null in false and b");
		ocl.assertQueryNull(null, "let b : Boolean = null in true and b");
		ocl.assertQueryNull(null, "let a : Boolean = null, b : Boolean = null in a and b");
		ocl.assertQueryInvalid(null, "let a : Boolean = null, b : Boolean = invalid in a and b");
		ocl.assertQueryInvalid(null, "let a : Boolean = null in a and Sequence{true}->at(0)");
		ocl.assertQueryFalse(null, "let a : Boolean = null in a and false");
		ocl.assertQueryNull(null, "let a : Boolean = null in a and true");
		ocl.assertQueryNull(null, "let a : Boolean = null, b : Boolean = null in a and b");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid, b : Boolean = null in a and b");
		ocl.assertQueryInvalid(null, "let b : Boolean = null in Sequence{true}->at(0) and b");
		ocl.dispose();
	}

	@Test public void testBooleanEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "true = false");

		ocl.assertQueryTrue(null, "true = true");
		ocl.assertQueryTrue(null, "false = false");
		// invalid
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in b = true");
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in false = b");

		ocl.assertQueryInvalid(null, "let b1 : Boolean = invalid, b2 : Boolean = invalid in b1 = b2");
		// null
		ocl.assertQueryFalse(null, "let b : Boolean = null in b = true");
		ocl.assertQueryFalse(null, "let b : Boolean = null in false = b");

		ocl.assertQueryTrue(null, "let b1 : Boolean = null, b2 : Boolean = null in b1 = b2");
		ocl.dispose();
	}

	@Test public void testBooleanImplies() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "false implies false");
		ocl.assertQueryTrue(null, "false implies true");
		ocl.assertQueryFalse(null, "true implies false");
		ocl.assertQueryTrue(null, "true implies true");
		// invalid
		ocl.assertQueryTrue(null, "let b : Boolean = invalid in false implies b");
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in true implies b");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid in a implies false");
		ocl.assertQueryTrue(null, "let a : Boolean = invalid in a implies true");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid, b : Boolean = invalid in a implies b");
		// null
		ocl.assertQueryTrue(null, "let b : Boolean = null in false implies b");
		ocl.assertQueryNull(null, "let b : Boolean = null in true implies b");
		ocl.assertQueryInvalid(null, "let a : Boolean = null, b : Boolean = invalid in a implies b");
		ocl.assertQueryInvalid(null, "let a : Boolean = null in a implies Sequence{true}->at(0)");
		ocl.assertQueryNull(null, "let a : Boolean = null in a implies false");
		ocl.assertQueryTrue(null, "let a : Boolean = null in a implies true");
		ocl.assertQueryNull(null, "let a : Boolean = null, b : Boolean = null in a implies b");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid, b : Boolean = null in a implies b");
		ocl.assertQueryInvalid(null, "let b : Boolean = null in Sequence{true}->at(0) implies b");
		ocl.dispose();
	}

	@Test public void testBooleanNot() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "not false");
		ocl.assertQueryFalse(null, "not true");
		// invalid
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid in not a");
		// null
		ocl.assertQueryNull(null, "let a : Boolean = null in not a");
		ocl.dispose();
	}

	@Test public void testBooleanNotEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "true <> false");

		ocl.assertQueryFalse(null, "true <> true");
		ocl.assertQueryFalse(null, "false <> false");
		// invalid
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in b <> true");
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in false <> b");

		ocl.assertQueryInvalid(null, "let b1 : Boolean = invalid, b2 : Boolean = invalid in b1 <> b2");
		// null
		ocl.assertQueryTrue(null, "let b : Boolean = null in b <> true");
		ocl.assertQueryTrue(null, "let b : Boolean = null in false <> b");

		ocl.assertQueryFalse(null, "let b1 : Boolean = null, b2 : Boolean = null in b1 <> b2");
		ocl.dispose();
	}

	@Test public void testBooleanOr() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "false or false");
		ocl.assertQueryTrue(null, "false or true");
		ocl.assertQueryTrue(null, "true or false");
		ocl.assertQueryTrue(null, "true or true");
		// invalid
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in false or b");
		ocl.assertQueryTrue(null, "let b : Boolean = invalid in true or b");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid in a or false");
		ocl.assertQueryTrue(null, "let a : Boolean = invalid in a or true");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid, b : Boolean = invalid in a or b");
		// null
		ocl.assertQueryNull(null, "let b : Boolean = null in false or b");
		ocl.assertQueryTrue(null, "let b : Boolean = null in true or b");
		ocl.assertQueryNull(null, "let a : Boolean = null, b : Boolean = null in a or b");
		ocl.assertQueryInvalid(null, "let a : Boolean = null, b : Boolean = invalid in a or b");
		ocl.assertQueryInvalid(null, "let a : Boolean = null in a or Sequence{true}->at(0)");
		ocl.assertQueryNull(null, "let a : Boolean = null in a or false");
		ocl.assertQueryTrue(null, "let a : Boolean = null in a or true");
		ocl.assertQueryNull(null, "let a : Boolean = null, b : Boolean = null in a or b");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid, b : Boolean = null in a or b");
		ocl.assertQueryInvalid(null, "let b : Boolean = null in Sequence{true}->at(0) or b");
		ocl.dispose();
	}

	@Test public void testBooleanToString() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "false", "false.toString()");
		ocl.assertQueryEquals(null, "true", "true.toString()");
		ocl.assertQueryEquals(null, "true", "(not false).toString()");
		ocl.dispose();
	}

	@Test public void testBooleanXor() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "false xor false");
		ocl.assertQueryTrue(null, "false xor true");
		ocl.assertQueryTrue(null, "true xor false");
		ocl.assertQueryFalse(null, "true xor true");
		// invalid
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in false xor b");
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in true xor b");
		ocl.assertQueryInvalid(null, "let a : Boolean = null, b : Boolean = invalid in a xor b");
		ocl.assertQueryInvalid(null, "let a : Boolean = null in a xor Sequence{true}->at(0)");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid in a xor false");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid in a xor true");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid, b : Boolean = null in a xor b");
		ocl.assertQueryInvalid(null, "let a : Boolean = invalid, b : Boolean = null in a xor b");
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in Sequence{true}->at(0) xor b");
		// xor
		ocl.assertQueryNull(null, "let b : Boolean = null in false xor b");
		ocl.assertQueryNull(null, "let b : Boolean = null in true xor b");
		ocl.assertQueryNull(null, "let b : Boolean = null in true xor b");
		ocl.assertQueryNull(null, "let a : Boolean = null in a xor true");
		ocl.assertQueryNull(null, "let a : Boolean = null, b : Boolean = null in a xor b");
		ocl.dispose();
	}
}
