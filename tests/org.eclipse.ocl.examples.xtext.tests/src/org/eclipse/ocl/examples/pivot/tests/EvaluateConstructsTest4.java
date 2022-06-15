/*******************************************************************************
 * Copyright (c) 2012, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.pivot.tests.EvaluateUMLTest4.MyOCL;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.osgi.util.NLS;
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
public class EvaluateConstructsTest4 extends PivotTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public EvaluateConstructsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull MyOCL createOCL() {
		return new MyOCL(getTestFileSystem(), getTestPackageName(), getName(), null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateConstructs";
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

	@Test public void testConstruct_if() throws Exception {
		TestOCL ocl = createOCL();
		ocl.assertValidationErrorQuery(null, "if null then 1 else 2 endif",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "IfExp::ConditionTypeIsBoolean", "if null then 1 else 2 endif");
		//
		ocl.assertQueryFalse(null, "if true then false else false endif");
		ocl.assertQueryEquals(null, 1, "if true then 1 else 2 endif");
		ocl.assertQueryEquals(null, 2, "if false then 1 else 2 endif");
		ocl.assertQueryEquals(null, 3.0, "if true then 3 else 4.0 endif");
		ocl.assertQueryEquals(null, 4.0, "if false then 3 else 4.0 endif");
		//
		ocl.assertValidationErrorQuery(null, "if null then 1 else 2 endif",
			PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "IfExp::ConditionTypeIsBoolean", "if null then 1 else 2 endif");
		ocl.assertQueryInvalid(null, "if null then 1 else 2 endif");
		ocl.assertQueryInvalid(null, "if invalid then 1 else 2 endif");
		//
		ocl.assertQueryEquals(null, 4.5, "if true then 4.5 else null endif");
		ocl.assertQueryEquals(null, "ok", "if false then null else 'ok' endif");
		ocl.assertQueryEquals(null, 4.5, "if true then 4.5 else invalid endif");
		ocl.assertQueryEquals(null, "ok", "if false then invalid else 'ok' endif");
		//
		ocl.assertQueryTrue(null, "if self.oclIsUndefined() then true else false endif");
		ocl.assertQueryInvalid(null, "if 4 then 4 else 4 endif",
			NLS.bind(PivotMessages.TypedValueRequired, "Boolean", "Integer"), null);
		ocl.assertQueryEquals(null, 4, "if 4=4 then 4 else 4 endif");
		//
		ocl.assertValidQuery(ocl.getStandardLibrary().getOclAnyType(), "let a : Boolean = false in if true then OrderedSet{5} else OrderedSet{} endif->first()+5");
		ocl.dispose();
	}

	@Test public void testConstruct_let() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 3, "let a : Integer = 1 in a + 2");
		ocl.assertQueryEquals(null, 7, "1 + let a : Integer = 2 in a * 3");
		ocl.assertQueryEquals(null, 4/2+5*3, "4/2 + let a : Integer = 5 in a * 3");
		ocl.assertQueryEquals(null, 4/2+3*5*7, "4/2 + (let a : Integer = 5 in 3 * (let b : Integer = 7 in a * b))");
		ocl.assertSemanticErrorQuery(null, "4/2 + (let a : Integer = 5 in 3) * (let b : Integer = 7 in a * b)",
			PivotMessagesInternal.UnresolvedElement_ERROR_, "", "a");
		ocl.assertQueryEquals(null, 4/2+3*5*7, "4/2 + let a : Integer = 5 in 3 * let b : Integer = 7 in a * b");
		ocl.assertQueryEquals(null, (64.0 / 16) / (8 / 4), "(64 / 16) / (let b : Integer = 0 in 8 / (let c : Integer = 0 in 4 ))");
		ocl.assertQueryEquals(null, (64.0 / 16) / (8 / 4), "64 / 16 / let b : Integer = 0 in 8 / let c : Integer = 0 in 4");
		ocl.assertQueryEquals(null, -5, "-let a : Integer = 5 in a");
		//
		ocl.assertQueryEquals(null, 5 * 7 * 9, "let a : Integer = 5, b : Real = 7, c : UnlimitedNatural = 9 in a * b * c.toInteger()");
		//
		ocl.assertQueryNull(null, "let a : Integer = null in a");
		//
		ocl.assertQueryInvalid(null, "let a : Integer = invalid in a");
		ocl.dispose();
	}

	@Test public void testConstruct_invalidIndexOf_456057() {
		TestOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "let s = Sequence{0.0,0.0,0.0}, t = Sequence{1.0,2.0,3.0} in s->collect(r | r + t->at(t->indexOf(r))) ");
		ocl.assertQueryResults(null, "Sequence{1.0,1.0,1.0}", "let s = Sequence{0.0,0.0,0.0} in s->collect(r | r + Sequence{1.0,2.0,3.0}->at(s->indexOf(r)))");
		ocl.assertQueryResults(null, "Sequence{2.0,4.0,6.0}", "let s = Sequence{1.0,2.0,3.0} in s->collect(r | r + Sequence{1.0,2.0,3.0}->at(s->indexOf(r)))");
		ocl.dispose();
	}
}
