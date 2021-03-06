/*******************************************************************************
 * Copyright (c) 2010, 2021 Eclipse Modeling Project and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   L.Goubet, E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.values.BigIntegerValueImpl;
import org.eclipse.ocl.pivot.internal.values.IntIntegerValueImpl;
import org.eclipse.ocl.pivot.internal.values.LongIntegerValueImpl;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for numeric operations.
 */
@RunWith(value = Parameterized.class)
public class EvaluateNumericOperationsTest4 extends PivotTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	protected double doubleEpsilon = Math.pow(0.5, Double.SIZE - 12);

	public EvaluateNumericOperationsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateNumericOperations";
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

	@Test public void testIntValue() {
		TestOCL ocl = createOCL();
		assert ValueUtil.integerValueOf(Integer.MAX_VALUE) instanceof IntIntegerValueImpl;
		assert ValueUtil.integerValueOf((long)Integer.MAX_VALUE) instanceof IntIntegerValueImpl;
		assert ValueUtil.integerValueOf(BigInteger.valueOf(Integer.MAX_VALUE)) instanceof IntIntegerValueImpl;
		assert ValueUtil.integerValueOf(BigInteger.valueOf(Integer.MAX_VALUE + 1L)) instanceof LongIntegerValueImpl;

		assert ValueUtil.integerValueOf(Long.MAX_VALUE) instanceof LongIntegerValueImpl;
		assert ValueUtil.integerValueOf(BigInteger.valueOf(Long.MAX_VALUE)) instanceof LongIntegerValueImpl;
		assert ValueUtil.integerValueOf(BigInteger.valueOf(Long.MAX_VALUE).add(BigInteger.ONE)) instanceof BigIntegerValueImpl;

		assert ValueUtil.integerValueOf(Integer.MIN_VALUE) instanceof IntIntegerValueImpl;
		assert ValueUtil.integerValueOf((long)Integer.MIN_VALUE) instanceof IntIntegerValueImpl;
		assert ValueUtil.integerValueOf(BigInteger.valueOf(Integer.MIN_VALUE)) instanceof IntIntegerValueImpl;
		assert ValueUtil.integerValueOf(BigInteger.valueOf(Integer.MIN_VALUE - 1L)) instanceof LongIntegerValueImpl;

		assert ValueUtil.integerValueOf(Long.MIN_VALUE) instanceof LongIntegerValueImpl;
		assert ValueUtil.integerValueOf(BigInteger.valueOf(Long.MIN_VALUE)) instanceof LongIntegerValueImpl;
		assert ValueUtil.integerValueOf(BigInteger.valueOf(Long.MIN_VALUE).subtract(BigInteger.ONE)) instanceof BigIntegerValueImpl;
		ocl.dispose();
	}

	@Test public void testIntPlus() {
		// hashCode, equals
	}

	@Test public void testIntMinus() {
		// hashCode, equals
	}

	@Test public void testNumber() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 0, "0");
		//		ocl.assertSemanticErrorQuery2(null, "00", "extraneous input ''{0}'' expecting EOF", "0");
		//		ocl.assertSemanticErrorQuery2(null, "01", "extraneous input ''{0}'' expecting EOF", "1");
		ocl.assertQueryEquals(null, 3, "3");
		ocl.assertQueryEquals(null, 3.1, "3.1");
		ocl.assertQueryEquals(null, 3.1e1, "3.1e1");
		ocl.assertQueryEquals(null, 3.1e+1, "3.1e+1");
		ocl.assertQueryEquals(null, 3.1e-1, "3.1e-1");
		ocl.assertQueryEquals(null, 3e1, "3e1");
		ocl.assertQueryEquals(null, 3e+1, "3e+1");
		ocl.assertQueryEquals(null, 3e-1, "3e-1");
		ocl.assertQueryEquals(null, 3, "(3)");
		ocl.assertQueryEquals(null, 3.1, "(3.1)");
		ocl.assertQueryEquals(null, 3.1e1, "(3.1e1)");
		ocl.assertQueryEquals(null, 3.1e+1, "(3.1e+1)");
		ocl.assertQueryEquals(null, 3.1e-1, "(3.1e-1)");
		ocl.assertQueryEquals(null, 3e1, "(3e1)");
		ocl.assertQueryEquals(null, 3e+1, "(3e+1)");
		ocl.assertQueryEquals(null, 3e-1, "(3e-1)");
		ocl.assertQueryEquals(null, 3, "( 3)");
		ocl.assertQueryEquals(null, 3.1, "( 3.1 )");
		ocl.assertQueryEquals(null, 3.1e1, "( 3.1e1 )");
		ocl.assertQueryEquals(null, 3.1e+1, "( 3.1e+1 )");
		ocl.assertQueryEquals(null, 3.1e-1, "( 3.1e-1 )");
		ocl.assertQueryEquals(null, 3e1, "( 3e1 )");
		ocl.assertQueryEquals(null, 3e+1, "( 3e+1 )");
		ocl.assertQueryEquals(null, 3e-1, "( 3e-1 )");
		ocl.assertSemanticErrorQuery(null, "3 .1", "bad navigation argument");
		ocl.assertSemanticErrorQuery(null, "3. 1", "bad navigation argument");
		ocl.assertSemanticErrorQuery(null, "3.1 e1", "extraneous input ''{0}'' expecting EOF", "e1");
		ocl.assertSemanticErrorQuery(null, "3.1e 1", "missing EOF at ''{0}''", "e");
		ocl.assertSemanticErrorQuery(null, "3.1e+ 1", "missing EOF at ''{0}''", "e");
		ocl.dispose();
	}

	@Test public void testNumberAbs() {
		TestOCL ocl = createOCL();
		// Integer::abs()
		ocl.assertQueryEquals(null, 3, "3.abs()");
		ocl.assertQueryEquals(null, 3, "(-3).abs()");

		ocl.assertQueryEquals(null, 2147483647, "2147483647.abs()");
		ocl.assertQueryEquals(null, 2147483648L, "2147483648.abs()");
		ocl.assertQueryEquals(null, 2147483649L, "(-2147483649).abs()");
		ocl.assertQueryEquals(null, 2147483648L, "(-2147483648).abs()");

		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).subtract(BigInteger.ONE), "9223372036854775807.abs()");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63), "9223372036854775808.abs()");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).add(BigInteger.ONE), "(-9223372036854775809).abs()");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63), "(-9223372036854775808).abs()");

		// Real::abs()
		ocl.assertQueryEquals(null, 3.0, "(3.0).abs()", 0.0);
		ocl.assertQueryEquals(null, 3.0, "(-3.0).abs()", 0.0);
		ocl.assertQueryEquals(null, 3.1758, "(3.1758).abs()", 0.0);
		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i.abs()");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r.abs()");
		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in i.abs()");
		ocl.assertQueryInvalid(null, "let r : Real = null in r.abs()");
		ocl.dispose();
	}

	@Test public void testNumberDiv() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 1, "3.div(2)");
		ocl.assertQueryEquals(null, -1, "(-3).div(2)");
		ocl.assertQueryEquals(null, -1, "3.div(-2)");
		ocl.assertQueryEquals(null, 1, "(-3).div(-2)");
		// by zero
		ocl.assertQueryInvalid(null, "1.div(0)");
		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 1.div(i)");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i.div(1)");
		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1.div(i2)");
		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in 1.div(i)");
		ocl.assertQueryInvalid(null, "let i : Integer = null in i.div(1)");
		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1.div(i2)");
		ocl.dispose();
	}

	@Test public void testNumberDivide() {
		TestOCL ocl = createOCL();
		// A.2.1.3 Contrary to other operations, "Integer x Integer -> Real"
		// Integer::/(Integer)
		ocl.assertQueryEquals(null, 1.0, "1 / 1", 0.0);
		ocl.assertQueryEquals(null, -0.25, "1 / -4", 0.0);

		// Integer::/(Real)
		ocl.assertQueryEquals(null, 1.0, "1 / 1.0", 0.0);
		ocl.assertQueryEquals(null, -0.25, "1 / -4.0", 0.0);

		// Real::/(Integer)
		ocl.assertQueryEquals(null, 1.0, "1.0 / 1", 0.0);
		ocl.assertQueryEquals(null, -0.25, "1.0 / -4", 0.0);

		// Real::/(Real)
		ocl.assertQueryEquals(null, 1.0, "1.0 / 1.0", 0.0);
		ocl.assertQueryEquals(null, 1.11 / 1.12, "1.11 / 1.12", 1 * doubleEpsilon);

		// by zero
		ocl.assertQueryInvalid(null, "1 / 0");
		ocl.assertQueryInvalid(null, "1.0 / 0");
		ocl.assertQueryInvalid(null, "1 / 0.0");
		ocl.assertQueryInvalid(null, "1.0 / 0.0");

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 1 / i");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i / 1");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 1 / r");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r / 1");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 / i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 / r2");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in 1 / i");
		ocl.assertQueryInvalid(null, "let i : Integer = null in i / 1");
		ocl.assertQueryInvalid(null, "let r : Real = null in 1 / r");
		ocl.assertQueryInvalid(null, "let r : Real = null in r / 1");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1 / i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1 / r2");
		ocl.dispose();
	}

	@Test public void testNumberEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "4 = 5");
		ocl.assertQueryFalse(null, "1 = 4.0");
		ocl.assertQueryFalse(null, "1.0 = 4");
		ocl.assertQueryFalse(null, "1.0 = 4.0");
		ocl.assertQueryFalse(null, "1.0 = 1.0000000000000001");

		ocl.assertQueryTrue(null, "4 = 4");
		ocl.assertQueryTrue(null, "1 = 1.0");
		ocl.assertQueryTrue(null, "1.0 = 1");
		ocl.assertQueryTrue(null, "1.0 = 1.0");
		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i = 0");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in -1 = i");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r = 0.0");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in -1.0 = r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 = i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 = r2");
		// null
		ocl.assertQueryFalse(null, "let i : Integer = null in i = 0");
		ocl.assertQueryFalse(null, "let i : Integer = null in -1 = i");
		ocl.assertQueryFalse(null, "let r : Real = null in r = 0.0");
		ocl.assertQueryFalse(null, "let r : Real = null in -1.0 = r");

		ocl.assertQueryTrue(null, "let i1 : Integer = null, i2 : Integer = null in i1 = i2");
		ocl.assertQueryTrue(null, "let r1 : Real = null, r2 : Real = null in r1 = r2");
		ocl.dispose();
	}

	@Test public void testNumberFloor() {
		TestOCL ocl = createOCL();
		// Integer::floor()
		ocl.assertQueryEquals(null, 3, "3.floor()");
		ocl.assertQueryEquals(null, -3, "(-3).floor()");

		// Real::floor()
		ocl.assertQueryEquals(null, -2, "(-1.5).floor()");
		ocl.assertQueryEquals(null, 1, "(1.01).floor()");
		ocl.assertQueryEquals(null, 3, "(3.999).floor()");
		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i.floor()");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r.floor()");
		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in i.floor()");
		ocl.assertQueryInvalid(null, "let r : Real = null in r.floor()");
		ocl.dispose();
	}

	@Test public void testNumberGreaterThan() {
		TestOCL ocl = createOCL();
		// Integer::greaterThan(Integer)
		ocl.assertQueryTrue(null, "3 > 2");
		ocl.assertQueryFalse(null, "-3 > 2");
		ocl.assertQueryTrue(null, "3 > -2");
		ocl.assertQueryFalse(null, "-3 > -2");

		ocl.assertQueryTrue(null, "2147483648 > 2147483647");
		ocl.assertQueryFalse(null, "2147483647 > 2147483648");
		ocl.assertQueryFalse(null, "-2147483649 > -2147483648");
		ocl.assertQueryTrue(null, "-2147483648 > -2147483649");

		ocl.assertQueryTrue(null, "9223372036854775808 > 9223372036854775807");
		ocl.assertQueryFalse(null, "9223372036854775807 > 9223372036854775808");
		ocl.assertQueryFalse(null, "-9223372036854775809 > -9223372036854775808");
		ocl.assertQueryTrue(null, "-9223372036854775808 > -9223372036854775809");

		// Real::greaterThan(Real)
		ocl.assertQueryTrue(null, "3.0 > 2.0");
		ocl.assertQueryFalse(null, "-3.0 > 2.0");
		ocl.assertQueryTrue(null, "3.0 > -2.0");
		ocl.assertQueryFalse(null, "-3.0 > -2.0");

		// Real::greaterThan(Integer)
		ocl.assertQueryFalse(null, "3.0 > 3");
		ocl.assertQueryFalse(null, "-3.0 > 3");
		ocl.assertQueryTrue(null, "3.0 > -3");
		ocl.assertQueryFalse(null, "-3.0 > -3");

		// Integer::greaterThan(Real)
		ocl.assertQueryFalse(null, "3 > 3.0");
		ocl.assertQueryFalse(null, "-3 > 3.0");
		ocl.assertQueryTrue(null, "3 > -3.0");
		ocl.assertQueryFalse(null, "-3 > -3.0");

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i > 0");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 0 > i");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r > 0");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 0 > r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 > i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 > r2");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in i > 0");
		ocl.assertQueryInvalid(null, "let i : Integer = null in 0 > i");
		ocl.assertQueryInvalid(null, "let r : Real = null in r > 0");
		ocl.assertQueryInvalid(null, "let r : Real = null in 0 > r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1 > i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1 > r2");
		ocl.dispose();
	}

	@Test public void testNumberGreaterThanOrEqual() {
		TestOCL ocl = createOCL();
		// Integer::greaterThanOrEqual(Integer)
		ocl.assertQueryTrue(null, "3 >= 2");
		ocl.assertQueryFalse(null, "-3 >= 2");
		ocl.assertQueryTrue(null, "3 >= -2");
		ocl.assertQueryFalse(null, "-3 >= -2");

		ocl.assertQueryTrue(null, "2147483648 >= 2147483647");
		ocl.assertQueryFalse(null, "2147483647 >= 2147483648");
		ocl.assertQueryFalse(null, "-2147483649 >= -2147483648");
		ocl.assertQueryTrue(null, "-2147483648 >= -2147483649");

		ocl.assertQueryTrue(null, "9223372036854775808 >= 9223372036854775807");
		ocl.assertQueryFalse(null, "9223372036854775807 >= 9223372036854775808");
		ocl.assertQueryFalse(null, "-9223372036854775809 >= -9223372036854775808");
		ocl.assertQueryTrue(null, "-9223372036854775808 >= -9223372036854775809");

		// Real::greaterThanOrEqual(Real)
		ocl.assertQueryTrue(null, "3.0 >= 2.0");
		ocl.assertQueryFalse(null, "-3.0 >= 2.0");
		ocl.assertQueryTrue(null, "3.0 >= -2.0");
		ocl.assertQueryFalse(null, "-3.0 >= -2.0");

		// Real::greaterThanOrEqual(Integer)
		ocl.assertQueryTrue(null, "3.0 >= 3");
		ocl.assertQueryFalse(null, "-3.0 >= 3");
		ocl.assertQueryTrue(null, "3.0 >= -3");
		ocl.assertQueryTrue(null, "-3.0 >= -3");

		// Integer::greaterThanOrEqual(Real)
		ocl.assertQueryTrue(null, "3 >= 3.0");
		ocl.assertQueryFalse(null, "-3 >= 3.0");
		ocl.assertQueryTrue(null, "3 >= -3.0");
		ocl.assertQueryTrue(null, "-3 >= -3.0");

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i >= 0");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 0 >= i");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r >= 0");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 0 >= r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 >= i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 >= r2");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in i >= 0");
		ocl.assertQueryInvalid(null, "let i : Integer = null in 0 >= i");
		ocl.assertQueryInvalid(null, "let r : Real = null in r >= 0");
		ocl.assertQueryInvalid(null, "let r : Real = null in 0 >= r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1 >= i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1 >= r2");
		ocl.dispose();
	}

	@Test public void testNumberLessThan() {
		TestOCL ocl = createOCL();
		// Integer::lessThan(Integer)
		ocl.assertQueryFalse(null, "3 < 2");
		ocl.assertQueryTrue(null, "-3 < 2");
		ocl.assertQueryFalse(null, "3 < -2");
		ocl.assertQueryTrue(null, "-3 < -2");

		ocl.assertQueryFalse(null, "2147483648 < 2147483647");
		ocl.assertQueryTrue(null, "2147483647 < 2147483648");
		ocl.assertQueryTrue(null, "-2147483649 < -2147483648");
		ocl.assertQueryFalse(null, "-2147483648 < -2147483649");

		ocl.assertQueryFalse(null, "9223372036854775808 < 9223372036854775807");
		ocl.assertQueryTrue(null, "9223372036854775807 < 9223372036854775808");
		ocl.assertQueryTrue(null, "-9223372036854775809 < -9223372036854775808");
		ocl.assertQueryFalse(null, "-9223372036854775808 < -9223372036854775809");

		// Real::lessThan(Real)
		ocl.assertQueryFalse(null, "3.0 < 2.0");
		ocl.assertQueryTrue(null, "-3.0 < 2.0");
		ocl.assertQueryFalse(null, "3.0 < -2.0");
		ocl.assertQueryTrue(null, "-3.0 < -2.0");

		// Real::lessThan(Integer)
		ocl.assertQueryFalse(null, "3.0 < 3");
		ocl.assertQueryTrue(null, "-3.0 < 3");
		ocl.assertQueryFalse(null, "3.0 < -3");
		ocl.assertQueryFalse(null, "-3.0 < -3");

		// Integer::lessThan(Real)
		ocl.assertQueryFalse(null, "3 < 3.0");
		ocl.assertQueryTrue(null, "-3 < 3.0");
		ocl.assertQueryFalse(null, "3 < -3.0");
		ocl.assertQueryFalse(null, "-3 < -3.0");

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i < 0");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 0 < i");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r < 0");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 0 < r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 < i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 < r2");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in i < 0");
		ocl.assertQueryInvalid(null, "let i : Integer = null in 0 < i");
		ocl.assertQueryInvalid(null, "let r : Real = null in r < 0");
		ocl.assertQueryInvalid(null, "let r : Real = null in 0 < r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1 < i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1 < r2");
		ocl.dispose();
	}

	@Test public void testNumberLessThanOrEqual() {
		TestOCL ocl = createOCL();
		// Integer::lessThanOrEqual(Integer)
		ocl.assertQueryFalse(null, "3 <= 2");
		ocl.assertQueryTrue(null, "-3 <= 2");
		ocl.assertQueryFalse(null, "3 <= -2");
		ocl.assertQueryTrue(null, "-3 <= -2");

		ocl.assertQueryFalse(null, "2147483648 <= 2147483647");
		ocl.assertQueryTrue(null, "2147483647 <= 2147483648");
		ocl.assertQueryTrue(null, "-2147483649 <= -2147483648");
		ocl.assertQueryFalse(null, "-2147483648 <= -2147483649");

		ocl.assertQueryFalse(null, "9223372036854775808 <= 9223372036854775807");
		ocl.assertQueryTrue(null, "9223372036854775807 <= 9223372036854775808");
		ocl.assertQueryTrue(null, "-9223372036854775809 <= -9223372036854775808");
		ocl.assertQueryFalse(null, "-9223372036854775808 <= -9223372036854775809");

		// Real::lessThanOrEqual(Real)
		ocl.assertQueryFalse(null, "3.0 <= 2.0");
		ocl.assertQueryTrue(null, "-3.0 <= 2.0");
		ocl.assertQueryFalse(null, "3.0 <= -2.0");
		ocl.assertQueryTrue(null, "-3.0 <= -2.0");

		// Real::lessThanOrEqual(Integer)
		ocl.assertQueryTrue(null, "3.0 <= 3");
		ocl.assertQueryTrue(null, "-3.0 <= 3");
		ocl.assertQueryFalse(null, "3.0 <= -3");
		ocl.assertQueryTrue(null, "-3.0 <= -3");

		// Integer::lessThanOrEqual(Real)
		ocl.assertQueryTrue(null, "3 <= 3.0");
		ocl.assertQueryTrue(null, "-3 <= 3.0");
		ocl.assertQueryFalse(null, "3 <= -3.0");
		ocl.assertQueryTrue(null, "-3 <= -3.0");

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i <= 0");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 0 <= i");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r <= 0");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 0 <= r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 <= i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 <= r2");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in i <= 0");
		ocl.assertQueryInvalid(null, "let i : Integer = null in 0 <= i");
		ocl.assertQueryInvalid(null, "let r : Real = null in r <= 0");
		ocl.assertQueryInvalid(null, "let r : Real = null in 0 <= r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1 <= i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1 <= r2");
		ocl.dispose();
	}

	@Test public void testNumberMax() {
		TestOCL ocl = createOCL();
		// Integer::max(Integer)
		ocl.assertQueryEquals(null, 3, "3.max(2)");
		ocl.assertQueryEquals(null, 2, "(-3).max(2)");
		ocl.assertQueryEquals(null, 3, "3.max(-2)");
		ocl.assertQueryEquals(null, -2, "(-3).max(-2)");

		ocl.assertQueryEquals(null, 2147483648L, "2147483648.max(2147483647)");
		ocl.assertQueryEquals(null, 2147483648L, "2147483647.max(2147483648)");
		ocl.assertQueryEquals(null, -2147483648L, "(-2147483649).max(-2147483648)");
		ocl.assertQueryEquals(null, -2147483648L, "(-2147483648).max(-2147483649)");

		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63), "9223372036854775808.max(9223372036854775807)");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63), "9223372036854775807.max(9223372036854775808)");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).negate(), "(-9223372036854775809).max(-9223372036854775808)");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).negate(), "(-9223372036854775808).max(-9223372036854775809)");

		// Integer::max(Real)
		ocl.assertQueryEquals(null, 3.0, "3.max(2.0)", 0.0);
		ocl.assertQueryEquals(null, 2.0, "(-3).max(2.0)", 0.0);
		ocl.assertQueryEquals(null, 3.0, "3.max(-2.0)", 0.0);
		ocl.assertQueryEquals(null, -2.0, "(-3).max(-2.0)", 0.0);

		// Real::max(Integer)
		ocl.assertQueryEquals(null, 3.0, "(3.0).max(2)", 0.0);
		ocl.assertQueryEquals(null, 2.0, "(-3.0).max(2)", 0.0);
		ocl.assertQueryEquals(null, 3.0, "(3.0).max(-2)", 0.0);
		ocl.assertQueryEquals(null, -2.0, "(-3.0).max(-2)", 0.0);

		// Real::max(Real)
		ocl.assertQueryEquals(null, 3.0, "(3.0).max(2.0)", 0.0);
		ocl.assertQueryEquals(null, 2.0, "(-3.0).max(2.0)", 0.0);
		ocl.assertQueryEquals(null, 3.0, "(3.0).max(-2.0)", 0.0);
		ocl.assertQueryEquals(null, -2.0, "(-3.0).max(-2.0)", 0.0);

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 1.max(i)");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i.max(1)");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 1.max(r)");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r.max(1)");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1.max(i2)");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1.max(r2)");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in 1.max(i)");
		ocl.assertQueryInvalid(null, "let i : Integer = null in i.max(1)");
		ocl.assertQueryInvalid(null, "let r : Real = null in 1.max(r)");
		ocl.assertQueryInvalid(null, "let r : Real = null in r.max(1)");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1.max(i2)");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1.max(r2)");
		ocl.dispose();
	}

	@Test public void testNumberMin() {
		TestOCL ocl = createOCL();
		// Integer::min(Integer)
		ocl.assertQueryEquals(null, 2, "3.min(2)");
		ocl.assertQueryEquals(null, -3, "(-3).min(2)");
		ocl.assertQueryEquals(null, -2, "3.min(-2)");
		ocl.assertQueryEquals(null, -3, "(-3).min(-2)");

		ocl.assertQueryEquals(null, 2147483647, "2147483648.min(2147483647)");
		ocl.assertQueryEquals(null, 2147483647, "2147483647.min(2147483648)");
		ocl.assertQueryEquals(null, -2147483649L, "(-2147483649).min(-2147483648)");
		ocl.assertQueryEquals(null, -2147483649L, "(-2147483648).min(-2147483649)");

		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).subtract(BigInteger.ONE), "9223372036854775808.min(9223372036854775807)");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).subtract(BigInteger.ONE), "9223372036854775807.min(9223372036854775808)");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).negate().subtract(BigInteger.ONE), "(-9223372036854775809).min(-9223372036854775808)");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).negate().subtract(BigInteger.ONE), "(-9223372036854775808).min(-9223372036854775809)");

		// Integer::min(Real)
		ocl.assertQueryEquals(null, 2.0, "3.min(2.0)", 0.0);
		ocl.assertQueryEquals(null, -3.0, "(-3).min(2.0)", 0.0);
		ocl.assertQueryEquals(null, -2.0, "3.min(-2.0)", 0.0);
		ocl.assertQueryEquals(null, -3.0, "(-3).min(-2.0)", 0.0);

		// Real::min(Integer)
		ocl.assertQueryEquals(null, 2.0, "(3.0).min(2)", 0.0);
		ocl.assertQueryEquals(null, -3.0, "(-3.0).min(2)", 0.0);
		ocl.assertQueryEquals(null, -2.0, "(3.0).min(-2)", 0.0);
		ocl.assertQueryEquals(null, -3.0, "(-3.0).min(-2)", 0.0);

		// Real::min(Real)
		ocl.assertQueryEquals(null, 2.0, "(3.0).min(2.0)", 0.0);
		ocl.assertQueryEquals(null, -3.0, "(-3.0).min(2.0)", 0.0);
		ocl.assertQueryEquals(null, -2.0, "(3.0).min(-2.0)", 0.0);
		ocl.assertQueryEquals(null, -3.0, "(-3.0).min(-2.0)", 0.0);

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 1.min(i)");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i.min(1)");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 1.min(r)");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r.min(1)");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1.min(i2)");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1.min(r2)");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in 1.min(i)");
		ocl.assertQueryInvalid(null, "let i : Integer = null in i.min(1)");
		ocl.assertQueryInvalid(null, "let r : Real = null in 1.min(r)");
		ocl.assertQueryInvalid(null, "let r : Real = null in r.min(1)");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1.min(i2)");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1.min(r2)");
		ocl.dispose();
	}

	@Test public void testNumberMinus() {
		TestOCL ocl = createOCL();
		// Integer::-(Integer)
		ocl.assertQueryEquals(null, 0, "1 - 1");
		ocl.assertQueryEquals(null, 5, "1 - -4");

		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE), "2147483646 - -1");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(31), "2147483647 - -1");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).subtract(BigInteger.ONE), "9223372036854775806 - -1");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63), "9223372036854775807 - -1");

		ocl.assertQueryEquals(null, BigInteger.ONE.negate().shiftLeft(31), "-2147483647 - 1");
		ocl.assertQueryEquals(null, BigInteger.ONE.negate().shiftLeft(31).subtract(BigInteger.ONE), "-2147483648 - 1");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).negate(), "-9223372036854775807 - 1");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).negate().subtract(BigInteger.ONE), "-9223372036854775808 - 1");

		// Integer::-(Real)
		ocl.assertQueryEquals(null, 0.0, "1 - 1.0", 2 * doubleEpsilon);
		ocl.assertQueryEquals(null, 5.0, "1 - -4.0", 5 * doubleEpsilon);

		// Real::-(Integer)
		ocl.assertQueryEquals(null, 0.0, "1.0 - 1", 2 * doubleEpsilon);
		ocl.assertQueryEquals(null, 5.0, "1.0 - -4", 5 * doubleEpsilon);

		// Real::-(Real)
		ocl.assertQueryEquals(null, 0.0, "1.0 - 1.0", 2 * doubleEpsilon);
		ocl.assertQueryEquals(null, -0.01, "1.11 - 1.12", 3 * doubleEpsilon);

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 1 - i");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i - 1");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 1 - r");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r - 1");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 - i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 - r2");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in 1 - i");
		ocl.assertQueryInvalid(null, "let i : Integer = null in i - 1");
		ocl.assertQueryInvalid(null, "let r : Real = null in 1 - r");
		ocl.assertQueryInvalid(null, "let r : Real = null in r - 1");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1 - i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1 - r2");
		ocl.dispose();
	}

	@Test public void testNumberMod() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 1, "3.mod(2)");
		ocl.assertQueryEquals(null, -1, "(-3).mod(2)");
		ocl.assertQueryEquals(null, 1, "3.mod(-2)");
		ocl.assertQueryEquals(null, -1, "(-3).mod(-2)");

		// by zero
		ocl.assertQueryInvalid(null, "1.mod(0)");

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 1.mod(i)");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i.mod(1)");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1.mod(i2)");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in 1.mod(i)");
		//		ocl.assertQueryInvalid(null, "let i : Integer = null in mod(1)");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1.mod(i2)");
		ocl.dispose();
	}

	@Test public void testNumberNegate() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, -1, "-1");
		ocl.assertQueryEquals(null, -1.0, "-1.0", 0.0);

		ocl.assertQueryEquals(null, -2147483647, "-2147483647");
		ocl.assertQueryEquals(null, -2147483648, "-2147483648");
		ocl.assertQueryEquals(null, -2147483649L, "-2147483649");

		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).negate().add(BigInteger.ONE), "-9223372036854775807");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).negate(), "-9223372036854775808");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).negate().subtract(BigInteger.ONE), "-9223372036854775809");
		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in -i");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in -r");
		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in -i");
		ocl.assertQueryInvalid(null, "let r : Real = null in -r");
		ocl.dispose();
	}

	@Test public void testNumberNotEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "4 <> 5");
		ocl.assertQueryTrue(null, "1 <> 4.0");
		ocl.assertQueryTrue(null, "1.0 <> 4");
		ocl.assertQueryTrue(null, "1.0 <> 4.0");

		ocl.assertQueryFalse(null, "4 <> 4");
		ocl.assertQueryFalse(null, "1 <> 1.0");
		ocl.assertQueryFalse(null, "1.0 <> 1");
		ocl.assertQueryFalse(null, "1.0 <> 1.0");
		ocl.assertQueryTrue(null, "1.0 <> 1.0000000000000001");
		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i <> 0");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in -1 <> i");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r <> 0.0");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in -1.0 <> r");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 <> i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 <> r2");
		// null
		ocl.assertQueryTrue(null, "let i : Integer = null in i <> 0");
		ocl.assertQueryTrue(null, "let i : Integer = null in -1 <> i");
		ocl.assertQueryTrue(null, "let r : Real = null in r <> 0.0");
		ocl.assertQueryTrue(null, "let r : Real = null in -1.0 <> r");

		ocl.assertQueryFalse(null, "let i1 : Integer = null, i2 : Integer = null in i1 <> i2");
		ocl.assertQueryFalse(null, "let r1 : Real = null, r2 : Real = null in r1 <> r2");
		ocl.dispose();
	}

	@Test public void testNumberPlus() {
		TestOCL ocl = createOCL();
		// Integer::+(Integer)
		ocl.assertQueryEquals(null, 2, "1 + 1");
		ocl.assertQueryEquals(null, -3, "1 + -4");

		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE), "2147483646 + 1");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(31), "2147483647 + 1");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63).subtract(BigInteger.ONE), "9223372036854775806 + 1");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(63), "9223372036854775807 + 1");

		ocl.assertQueryEquals(null, BigInteger.ONE.negate().shiftLeft(31), "-2147483647 + -1");
		ocl.assertQueryEquals(null, BigInteger.ONE.negate().shiftLeft(31).subtract(BigInteger.ONE), "-2147483648 + -1");
		ocl.assertQueryEquals(null, BigInteger.ONE.negate().shiftLeft(63), "-9223372036854775807 + -1");
		ocl.assertQueryEquals(null, BigInteger.ONE.negate().shiftLeft(63).subtract(BigInteger.ONE), "-9223372036854775808 + -1");

		// Integer::+(Real)
		ocl.assertQueryEquals(null, 2.0, "1 + 1.0", 2 * doubleEpsilon);
		ocl.assertQueryEquals(null, -3.0, "1 + -4.0", 5 * doubleEpsilon);

		// Real::+(Integer)
		ocl.assertQueryEquals(null, 2.0, "1.0 + 1", 2 * doubleEpsilon);
		ocl.assertQueryEquals(null, -3.0, "1.0 + -4", 5 * doubleEpsilon);

		// Real::+(Real)
		ocl.assertQueryEquals(null, 2.0, "1.0 + 1.0", 2 * doubleEpsilon);
		ocl.assertQueryEquals(null, 2.23, "1.11 + 1.12", 3 * doubleEpsilon);

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 1 + i");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i + 1");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 1 + r");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r + 1");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 + i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 + r2");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in 1 + i");
		ocl.assertQueryInvalid(null, "let i : Integer = null in i + 1");
		ocl.assertQueryInvalid(null, "let r : Real = null in 1 + r");
		ocl.assertQueryInvalid(null, "let r : Real = null in r + 1");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1 + i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1 + r2");
		ocl.dispose();
	}

	@Test public void testNumberRound() {
		TestOCL ocl = createOCL();
		// Integer::round()
		ocl.assertQueryEquals(null, 3, "3.round()");
		ocl.assertQueryEquals(null, -3, "(-3).round()");

		// Real::round()
		ocl.assertQueryEquals(null, -1, "(-1.5).round()");
		ocl.assertQueryEquals(null, 1, "(1.01).round()");
		ocl.assertQueryEquals(null, 4, "(3.999).round()");
		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i.round()");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r.round()");
		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in i.round()");
		ocl.assertQueryInvalid(null, "let r : Real = null in r.round()");
		ocl.dispose();
	}

	@Test public void testNumberTimes() {
		TestOCL ocl = createOCL();
		// Integer::*(Integer)
		ocl.assertQueryEquals(null, 1, "1 * 1");
		ocl.assertQueryEquals(null, -4, "1 * -4");

		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(62), "-2147483648 * -2147483648");
		ocl.assertQueryEquals(null, BigInteger.ONE.shiftLeft(126), "-9223372036854775808 * -9223372036854775808");

		// Integer::*(Real)
		ocl.assertQueryEquals(null, 1.0, "1 * 1.0", 0.0);
		ocl.assertQueryEquals(null, -4.0, "1 * -4.0", 0.0);

		// Real::*(Integer)
		ocl.assertQueryEquals(null, 1.0, "1.0 * 1", 0.0);
		ocl.assertQueryEquals(null, -4.0, "1.0 * -4", 0.0);

		// Real::*(Real)
		ocl.assertQueryEquals(null, 1.0, "1.0 * 1.0", 0.0);
		@SuppressWarnings("null") @NonNull BigDecimal expected = BigDecimal.valueOf(1.11).multiply(BigDecimal.valueOf(1.12));
		ocl.assertQueryEquals(null, expected, "1.11 * 1.12", 0.0);

		// invalid
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in 1 * i");
		ocl.assertQueryInvalid(null, "let i : Integer = invalid in i * 1");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in 1 * r");
		ocl.assertQueryInvalid(null, "let r : Real = invalid in r * 1");

		ocl.assertQueryInvalid(null, "let i1 : Integer = invalid, i2 : Integer = invalid in i1 * i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = invalid, r2 : Real = invalid in r1 * r2");

		// null
		ocl.assertQueryInvalid(null, "let i : Integer = null in 1 * i");
		ocl.assertQueryInvalid(null, "let i : Integer = null in i * 1");
		ocl.assertQueryInvalid(null, "let r : Real = null in 1 * r");
		ocl.assertQueryInvalid(null, "let r : Real = null in r * 1");

		ocl.assertQueryInvalid(null, "let i1 : Integer = null, i2 : Integer = null in i1 * i2");
		ocl.assertQueryInvalid(null, "let r1 : Real = null, r2 : Real = null in r1 * r2");
		ocl.dispose();
	}

	@Test public void testNumberToString() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "1", "1.toString()");
		ocl.assertQueryEquals(null, "3.0", "3.0.toString()");
		ocl.assertQueryEquals(null, "4.0", "(1.0+3.0).toString()");
		ocl.assertQueryEquals(null, "null", "null.toString()");
		//		ocl.assertQueryEquals(null, "invalid", "invalid.toString()");
		ocl.assertQueryInvalid(null, "invalid.toString()");
		ocl.assertQueryEquals(null, "*", "*.toString()");
		ocl.dispose();
	}

	@Test public void testUnlimitedAbs() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "*.abs()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "abs");
		// invalid
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in u.abs()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "abs");
		// null
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in u.abs()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "abs");
		ocl.dispose();
	}

	/* FIXME	@Test public void testUnlimitedDiv() {
		TestOCL ocl = createMyOCL();
		/ *
	 * FIXME I'm expecting the UnlimitedNatural to conform to Integer, div
	 * and mod calls should then at least parse for them even though they
	 * return an invalid value.
	 * /
		ocl.assertQueryInvalid(null, "1.div(*)");
		ocl.assertQueryInvalid(null, "*.div(1)");

		ocl.assertQueryInvalid(null, "*.div(*)");
		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in 1.div(u)");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u.div(1)");
		// null
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in 1.div(u)");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in u.div(1)");
		ocl.dispose();
	} */

	@Test public void testUnlimitedDivide() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "1 / *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Integer", "/", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "* / 1", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "/", "Integer");

		ocl.assertSemanticErrorQuery(null, "1.0 / *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Real", "/", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "* / 1.0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "/", "Real");

		ocl.assertSemanticErrorQuery(null, "* / *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "/", "UnlimitedNatural");

		// invalid
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in * / u", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "/", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in u / *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "/", "UnlimitedNatural");

		ocl.assertSemanticErrorQuery(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 / u2", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "/", "UnlimitedNatural");

		// null
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in * / u", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "/", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in u / *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "/", "UnlimitedNatural");

		ocl.assertSemanticErrorQuery(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 / u2", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "/", "UnlimitedNatural");
		ocl.dispose();
	}

	@Test public void testUnlimitedEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "* = 1");
		ocl.assertQueryFalse(null, "1 = *");
		ocl.assertQueryFalse(null, "* = 1.0");
		ocl.assertQueryFalse(null, "1.0 = *");
		ocl.assertQueryFalse(null, "* = -1");
		ocl.assertQueryFalse(null, "-1 = *");
		ocl.assertQueryFalse(null, "* = -1.0");
		ocl.assertQueryFalse(null, "-1.0 = *");

		ocl.assertQueryTrue(null, "* = *");
		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u = *");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 = u2");
		// null
		ocl.assertQueryFalse(null, "let u : UnlimitedNatural = null in u = *");

		ocl.assertQueryTrue(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 = u2");
		ocl.dispose();
	}

	@Test public void testUnlimitedFloor() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "*.floor()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "floor");

		// invalid
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in u.floor()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "floor");

		// null
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in u.floor()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "floor");
		ocl.dispose();
	}

	@Test public void testUnlimitedGreaterThan() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "1 > *");
		ocl.assertQueryTrue(null, "* > 1");
		ocl.assertQueryFalse(null, "* > *");

		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u > 0");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in 0 > u");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 > u2");

		// null
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in u > 0");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in 0 > u");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 > u2");
		ocl.dispose();
	}

	@Test public void testUnlimitedGreaterThanOrEqual() {
		TestOCL ocl = createOCL();
		/*
		 * FIXME "(* = *) == true" but "(* >= *) == false" ? something's amiss
		 * and since this behavior isn't defined in the specification, we'll
		 * have to make an arbitrary choice. The "expected" here is Java's
		 * behavior with Double.POSITIVE_INFINITY.
		 */
		ocl.assertQueryFalse(null, "1 >= *");
		ocl.assertQueryTrue(null, "* >= 1");
		ocl.assertQueryTrue(null, "* >= *");

		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u >= 0");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in 0 >= u");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 >= u2");

		// null
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in u >= 0");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in 0 >= u");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 >= u2");
		ocl.dispose();
	}

	@Test public void testUnlimitedLessThan() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "1 < *");
		ocl.assertQueryFalse(null, "* < 1");
		ocl.assertQueryFalse(null, "* < *");

		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u < 0");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in 0 < u");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 < u2");

		// null
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in u < 0");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in 0 < u");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 < u2");
		ocl.dispose();
	}

	@Test public void testUnlimitedLessThanOrEqual() {
		TestOCL ocl = createOCL();
		/*
		 * FIXME "(* = *) == true" but "(* <= *) == false" ? something's amiss
		 * and since this behavior isn't defined in the specification, we'll
		 * have to make an arbitrary choice. The "expected" here is Java's
		 * behavior with Double.POSITIVE_INFINITY.
		 */
		ocl.assertQueryTrue(null, "1 <= *");
		ocl.assertQueryFalse(null, "* <= 1.toUnlimitedNatural()");
		ocl.assertQueryTrue(null, "* <= *");

		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u <= 0.toUnlimitedNatural()");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in 0.toUnlimitedNatural() <= u");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 <= u2");

		// null
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in u <= 0.toUnlimitedNatural()");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in 0.toUnlimitedNatural() <= u");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 <= u2");
		ocl.dispose();
	}

	@Test public void testUnlimitedMax() {
		TestOCL ocl = createOCL();
		ocl.assertQueryUnlimited(null, "1.max(*)");
		ocl.assertQueryUnlimited(null, "*.max(1)");

		ocl.assertQueryUnlimited(null, "0.max(*)");
		ocl.assertQueryUnlimited(null, "*.max(0)");

		ocl.assertQueryInvalid(null, "(-1).max(*)");
		ocl.assertQueryInvalid(null, "*.max(-1)");

		ocl.assertSemanticErrorQuery(null, "(1.0).max(*)", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Real", "max", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "*.max(1.0)", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "max", "Real");

		ocl.assertQueryUnlimited(null, "*.max(*)");

		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in *.max(u)");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u.max(*)");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1.max(u2)");

		// null
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in *.max(u)");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in u.max(*)");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1.max(u2)");
		ocl.dispose();
	}

	@Test public void testUnlimitedMin() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 1, "1.min(*)");
		ocl.assertQueryEquals(null, 1, "*.min(1)");

		ocl.assertQueryEquals(null, 0, "0.min(*)");
		ocl.assertQueryEquals(null, 0, "*.min(0)");

		ocl.assertQueryInvalid(null, "(-1).min(*)");
		ocl.assertQueryInvalid(null, "*.min(-1)");

		ocl.assertSemanticErrorQuery(null, "(1.0).min(*)", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Real", "min", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "*.min(1.0)", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "min", "Real");

		ocl.assertQueryUnlimited(null, "*.min(*)");

		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in *.min(u)");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u.min(*)");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1.min(u2)");

		// null
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in *.min(u)");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in u.min(*)");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1.min(u2)");
		ocl.dispose();
	}

	@Test public void testUnlimitedMinus() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "1 - *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Integer", "-", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "* - 1", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "-", "Integer");

		ocl.assertSemanticErrorQuery(null, "1.0 - *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Real", "-", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "* - 1.0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "-", "Real");

		ocl.assertSemanticErrorQuery(null, "* - *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "-", "UnlimitedNatural");

		// invalid
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in * - u", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "-", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in u - *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "-", "UnlimitedNatural");

		ocl.assertSemanticErrorQuery(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 - u2", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "-", "UnlimitedNatural");

		// null
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in * - u", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "-", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in u - *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "-", "UnlimitedNatural");

		ocl.assertSemanticErrorQuery(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 - u2", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "-", "UnlimitedNatural");
		ocl.dispose();
	}

	/* FIXME	@Test public void testUnlimitedMod() {
		TestOCL ocl = createMyOCL();
		/ *
	 * FIXME I'm expecting the UnlimitedNatural to conform to Integer, div
	 * and mod calls should then at least parse for them even though they
	 * return an invalid value.
	 * /
		ocl.assertQueryInvalid(null, "1.mod(*)");
		ocl.assertQueryInvalid(null, "*.mod(1)");

		ocl.assertQueryInvalid(null, "*.mod(*)");

		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in 1.mod(u)");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u.mod(1)");

		// null
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in 1.mod(u)");
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = null in u.mod(1)");
		ocl.dispose();
	} */

	@Test public void testUnlimitedNegate() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "-*", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "-");
		// invalid
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in -u", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "-");
		// null
		ocl.dispose();
	}

	@Test public void testUnlimitedNotEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "* <> 1");
		ocl.assertQueryTrue(null, "1 <> *");
		ocl.assertQueryTrue(null, "* <> 1.0");
		ocl.assertQueryTrue(null, "1.0 <> *");
		ocl.assertQueryTrue(null, "* <> -1");
		ocl.assertQueryTrue(null, "-1 <> *");
		ocl.assertQueryTrue(null, "* <> -1.0");
		ocl.assertQueryTrue(null, "-1.0 <> *");

		ocl.assertQueryFalse(null, "* <> *");
		// invalid
		ocl.assertQueryInvalid(null, "let u : UnlimitedNatural = invalid in u <> *");

		ocl.assertQueryInvalid(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 <> u2");
		// null
		ocl.assertQueryTrue(null, "let u : UnlimitedNatural = null in u <> *");

		ocl.assertQueryFalse(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 <> u2");
		ocl.dispose();
	}

	@Test public void testUnlimitedPlus() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "1 + *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Integer", "+", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "* + 1", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "+", "Integer");

		ocl.assertSemanticErrorQuery(null, "1.0 + *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Real", "+", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "* + 1.0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "+", "Real");

		ocl.assertSemanticErrorQuery(null, "* + *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "+", "UnlimitedNatural");
		// invalid
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in * + u", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "+", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in u + *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "+", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 + u2", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "+", "UnlimitedNatural");
		// null
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in * + u", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "+", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in u + *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "+", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 + u2", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "+", "UnlimitedNatural");
		ocl.dispose();
	}

	@Test public void testUnlimitedRound() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "*.round()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "round");
		// invalid
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in u.round()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "round");
		// null
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in u.round()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "UnlimitedNatural", "round");
		ocl.dispose();
	}

	@Test public void testUnlimitedTimes() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "1 * *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Integer", "*", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "* * 1", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "*", "Integer");

		ocl.assertSemanticErrorQuery(null, "1.0 * *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "Real", "*", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "* * 1.0", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "*", "Real");

		ocl.assertSemanticErrorQuery(null, "* * *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "*", "UnlimitedNatural");

		// invalid
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in * * u", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "*", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = invalid in u * *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "*", "UnlimitedNatural");

		ocl.assertSemanticErrorQuery(null, "let u1 : UnlimitedNatural = invalid, u2 : UnlimitedNatural = invalid in u1 * u2", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "*", "UnlimitedNatural");
		// null
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in * * u", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "*", "UnlimitedNatural");
		ocl.assertSemanticErrorQuery(null, "let u : UnlimitedNatural = null in u * *", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "*", "UnlimitedNatural");

		ocl.assertSemanticErrorQuery(null, "let u1 : UnlimitedNatural = null, u2 : UnlimitedNatural = null in u1 * u2", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "UnlimitedNatural", "*", "UnlimitedNatural");
		ocl.dispose();
	}
}
