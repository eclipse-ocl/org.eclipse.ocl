/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.junit.After;
import org.junit.Before;

/**
 * Tests for OclAny operations.
 */
public class PrettyPrinterTest extends PivotTestSuite
{
	public static class MyOCL extends TestOCL
	{
		public MyOCL(@NonNull TestFileSystem testFileSystem, @NonNull String testPackageName, @NonNull String name) {
			super(testFileSystem, testPackageName, name, OCL.NO_PROJECTS, null);
		}

		protected @Nullable Object assertPrintResults(@Nullable Object context, @NonNull String expression) {
			try {
				org.eclipse.ocl.pivot.Class contextType = getContextType(context);
				ExpressionInOCL query = createQuery(contextType, expression);
				String prettyExpression = PrettyPrinter.print(query);
				assertEquals(expression, prettyExpression);
			} catch (ParserException e) {
				fail("Failed to parse \"" + expression + "\": " + e.getLocalizedMessage());
			}
			return null;
		}

		protected @Nullable Object assertPrintResults(@Nullable Object context, @NonNull String expression, @NonNull String expectedExpression) {
			try {
				org.eclipse.ocl.pivot.Class contextType = getContextType(context);
				ExpressionInOCL query = createQuery(contextType, expression);
				String prettyExpression = PrettyPrinter.print(query);
				assertEquals(expectedExpression, prettyExpression);
			} catch (ParserException e) {
				fail("Failed to parse \"" + expression + "\": " + e.getLocalizedMessage());
			}
			return null;
		}
	}

	public PrettyPrinterTest() {
		super(false);
	}

	@Override
	protected @NonNull MyOCL createOCL() {
		return new MyOCL(getTestFileSystem(), getTestPackageName(), getName());
	}

	@Override
	@Before public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests the quoting on reserved words.
	 */
	public void test_ReservedWords() {
		MyOCL ocl = createOCL();
		ocl.assertPrintResults(null, "let _'and' : Boolean[1] = false in _'and' and _'and'");
		ocl.assertPrintResults(null, "let _'else' : Boolean[1] = false in if _'else' then _'else' else _'else' endif");
		ocl.assertPrintResults(null, "let _'endif' : Boolean[1] = false in if _'endif' then _'endif' else _'endif' endif");
		ocl.assertPrintResults(null, "let _'false' : Boolean[1] = false in _'false' and _'false'");
		ocl.assertPrintResults(null, "let _'if' : Boolean[1] = false in if _'if' then _'if' else _'if' endif");
		ocl.assertPrintResults(null, "let _'implies' : Boolean[1] = false in _'implies' implies _'implies'");
		ocl.assertPrintResults(null, "let _'in' : Boolean[1] = false in _'in'");
		ocl.assertPrintResults(null, "let _'invalid' : Boolean[1] = false in _'invalid' and invalid");
		ocl.assertPrintResults(null, "let _'let' : Boolean[1] = false in _'let'");
		ocl.assertPrintResults(null, "let _'not' : Boolean[1] = false in not _'not'");
		ocl.assertPrintResults(null, "let _'null' : Boolean[1] = false in _'null' and null");
		ocl.assertPrintResults(null, "let _'or' : Boolean[1] = false in _'or' or _'or'");
		ocl.assertPrintResults(null, "let _'self' : Boolean = false in self or _'self'", "let _'self' : Boolean[1] = false in self or self");
		ocl.assertPrintResults(null, "let _'then' : Boolean[1] = false in if _'then' then _'then' else _'then' endif");
		ocl.assertPrintResults(null, "let _'true' : Boolean[1] = false in _'true' and _'true'");
		ocl.assertPrintResults(null, "let _'xor' : Boolean[1] = false in _'xor' xor _'xor'");
		ocl.dispose();
	}

	/**
	 * Tests the precedence on a specialized collection operator.
	 */
	public void test_SetDifference() {
		MyOCL ocl = createOCL();
		ocl.assertPrintResults(null, "let a : Set(Integer) = Set{1} in let b : Set(Integer) = Set{1} in a - b");
		ocl.dispose();
	}

	/**
	 * Tests the precedence on a specialized collection operator.
	 */
	public void test_SourceNavigationPrecedence() {
		MyOCL ocl = createOCL();
		ocl.assertPrintResults(null, "let a : Set(Integer) = Set{1} in (a - a)->isEmpty()");
		ocl.assertPrintResults(null, "let a : Set(Integer) = Set{1} in a->isEmpty()");
		ocl.assertPrintResults(null, "let a : Set(Integer) = Set{1} in a->asSet()->asSet()");
		ocl.assertPrintResults(null, "let a : Set(Integer) = Set{1} in a->select(true)->asSet()");
		ocl.dispose();
	}

	/**
	 * Tests the non-printing of implicit collect's realization.
	 */
	public void test_ImplicitCollect() {
		MyOCL ocl = createOCL();
		ocl.assertPrintResults(null, "Set{1}.toString()");
		ocl.dispose();
	}

	/**
	 * Tests the unnesting of IfExp.
	 */
	public void test_ElseIf() {
		MyOCL ocl = createOCL();
		ocl.assertPrintResults(null, "if true then 1 else 2 endif");
		ocl.assertPrintResults(null, "if true then 1 elseif true then 2 else 3 endif");
		ocl.assertPrintResults(null, "if true then 1 elseif true then 2 elseif true then 3 else 4 endif");
		ocl.assertPrintResults(null, "if if true then 1 elseif true then 2 else if true then 1 elseif true then 2 else 3 endif endif then if true then 1 elseif true then 2 else 3 endif elseif if true then 1 elseif true then 2 else 3 endif then 2 else 3 endif");
		ocl.dispose();
	}
}
