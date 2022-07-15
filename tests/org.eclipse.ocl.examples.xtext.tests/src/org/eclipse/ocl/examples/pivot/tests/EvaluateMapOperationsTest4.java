/*******************************************************************************
 * Copyright (c) 2015, 2021 Eclipse Modeling Project and others.
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
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.xtext.essentialocl.cs2as.EssentialOCLCS2ASMessages;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


/**
 * Tests for map operations.
 */
@RunWith(value = Parameterized.class)
public class EvaluateMapOperationsTest4 extends PivotTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public EvaluateMapOperationsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateMapOperations";
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

	@Test public void testMapAt() {
		TestOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "Set{invalid}", "invalid", InvalidValueException.class);

		ocl.assertQueryInvalid(null, "Map{}->at(3)", StringUtil.bind(PivotMessages.IndexNotInUse, 3), InvalidValueException.class);

		ocl.assertQueryEquals(null, "b", "Map{'a' with 'b'}->at('a')");
		ocl.assertQueryInvalid(null, "Map{'a' with 'b'}->at('b')", StringUtil.bind(PivotMessages.IndexNotInUse, "b"), InvalidValueException.class);
		ocl.assertQueryInvalid(null, "Map{'a' with 'b'}->at(null)", StringUtil.bind(PivotMessages.IndexNotInUse, "null"), InvalidValueException.class);

		ocl.assertQueryEquals(null, "b", "Map{null with 'b'}->at(null)");
		ocl.assertQueryInvalid(null, "Map{null with 'b'}->at('b')", StringUtil.bind(PivotMessages.IndexNotInUse, "b"), InvalidValueException.class);

		ocl.assertQueryNull(null, "Map{'a' with null}->at('a')");
		ocl.assertQueryInvalid(null, "Map{'a' with null}->at(null)", StringUtil.bind(PivotMessages.IndexNotInUse, "null"), InvalidValueException.class);

		ocl.assertQueryInvalid(null, "Map{'a' with 'b'}->at(invalid)", "invalid", InvalidValueException.class);

		ocl.dispose();
	}

	@Test public void testMapEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{} = Bag{}");
		ocl.assertQueryFalse(null, "Map{} = OrderedSet{}");
		ocl.assertQueryFalse(null, "Map{} = Sequence{}");
		ocl.assertQueryFalse(null, "Map{} = Set{}");

		ocl.assertQueryFalse(null, "Map{1 with 1} = 1");
		ocl.assertQueryFalse(null, "1 = Map{1 with 1}");
		ocl.assertQueryFalse(null, "Set{1} = Set{Set{1}}");

		ocl.assertQueryTrue(null, "Map{1 with 1} = Map{1 with 1}");
		ocl.assertQueryTrue(null, "Map{1.0 with 1} = Map{1 with 1}");
		ocl.assertQueryTrue(null, "Map{1 with 1.0} = Map{1 with 1}");
		ocl.assertQueryTrue(null, "Map{1.0 with 1.0} = Map{1 with 1}");

		ocl.assertQueryFalse(null, "Map{1.01 with 1} = Map{1 with 1}");
		ocl.assertQueryFalse(null, "Map{1 with 1.01} = Map{1 with 1}");
		ocl.assertQueryFalse(null, "Map{1.01 with 1.01} = Map{1 with 1}");

		ocl.assertQueryFalse(null, "Map{1 with 1} = Map{1 with 1, 2 with 1}");

		ocl.assertQueryTrue(null, "Map{Map{'a' with 'b'} with 1} = Map{Map{'a' with 'b'} with 1}");
		ocl.assertQueryFalse(null, "Map{Map{'a' with 'b'} with 1} = Map{Map{'a' with 'c'} with 1}");
		ocl.assertQueryFalse(null, "Map{Map{'a' with 'b'} with 1} = Map{Map{'b' with 'b'} with 1}");
		ocl.assertQueryTrue(null, "Map{1 with Map{'a' with 'b'}} = Map{1 with Map{'a' with 'b'}}");
		ocl.assertQueryFalse(null, "Map{1 with Map{'a' with 'b'}} = Map{1 with Map{'a' with 'c'}}");
		ocl.assertQueryFalse(null, "Map{1 with Map{'a' with 'b'}} = Map{1 with Map{'b' with 'b'}}");

		// null map element
		ocl.assertQueryTrue(null, "Map{null with null} = Map{null with null}");
		ocl.assertQueryFalse(null, "Map{null with 1} = Map{null with null}");
		ocl.assertQueryFalse(null, "Map{true with null} = Map{null with null}");
		ocl.assertQueryFalse(null, "Map{'4' with 4} = Map{null with null}");
		ocl.dispose();
	}

	@Test public void testMapErrors() {
		TestOCL ocl = createOCL();
	//	ocl.assertValidationErrorQuery(null, "Map{}?->keyType", PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "CallExp::SafeSourceCannotBeMap", ocl);
		ocl.assertValidationErrorQuery(null, "Map{}?->size()", PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "CallExp::SafeSourceCannotBeMap", ocl);
		ocl.assertValidationErrorQuery(null, "Map{}?->collect(c | '')", PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "CallExp::SafeSourceCannotBeMap", "Map{}?->collect(c : OclVoid[1] | '')");
		ocl.assertValidationErrorQuery(null, "Map{}?->iterate(c; acc:String = '' | '')", PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "CallExp::SafeSourceCannotBeMap", "Map{}?->iterate(c : OclVoid[1]; acc : String[1] = '' | '')");
		ocl.dispose();
	}

	@Test public void testMapExcludes() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(3)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(3.0)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(4.0)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes('test')");

		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(3.5)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(8)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes('tst')");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->excludes(0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->excludes(0)");

		// invalid null element
		ocl.assertQueryFalse(null, "Map{3 with 8, null with 'tst', 'test' with true}->excludes(null)");
		ocl.assertQueryFalse(null, "Map{null with null}->excludes(null)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->excludes(null)");
		ocl.assertQueryTrue(null, "Map{}->excludes(null)");

		ocl.dispose();
	}

	@Test public void testMapExcludesAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Sequence{3, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Bag{3, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Set{3, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(OrderedSet{3, 'test'})");

		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Sequence{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Bag{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Set{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(OrderedSet{3.5, 'test'})");

		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Sequence{3.5, 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Bag{3.5, 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Set{3.5, 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(OrderedSet{3.5, 'tst'})");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = invalid in s->excludesAll(Sequence{0})");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in Map{0 with false}->excludesAll(s)");

		// invalid collection element
		// Collections can't contain the invalid value
		ocl.assertQueryInvalid(null, "Map{3 with true, 4.0 with true, invalid with true, 'test' with true}->excludesAll(OrderedSet{'test'})");
		ocl.assertQueryInvalid(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(Sequence{'test', invalid})");

		// null collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->excludesAll(Sequence{0})");
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in Map{0 with false}->excludesAll(s)");

		// null collection element
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, null with true, 'test' with true}->excludesAll(OrderedSet{'test', null})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(OrderedSet{'test', null})");
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesAll(OrderedSet{'tst', null})");

		ocl.dispose();
	}

	@Test public void testMapExcludesMap() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{3 with 8})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{3.0 with 8})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{3 with 8, 4.0 with 'tst'})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{3 with 8, 4 with 'tst', 'test' with true})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{4.0 with 'tst'})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{'test' with true})");

		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{3.5 with 8})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{3 with 8, 3.5 with 8, 4.0 with 'tst', 'test' with true})");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{3 with true})");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{'tst' with 4.0})");

		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesMap(Map{3.5 with true})");
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->excludesMap(Map{3.5 with true, 3 with false})");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->excludesMap(Map{0 with 0})");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{invalid with 'tst'})");
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesMap(Map{4.0 with invalid})");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->excludesMap(Map{0 with 0})");

		// invalid null element
		ocl.assertQueryFalse(null, "Map{3 with 8, null with 'tst', 'test' with null}->excludesMap(Map{null with 'tst'})");
		ocl.assertQueryFalse(null, "Map{3 with 8, null with 'tst', 'test' with null}->excludesMap(Map{'test' with null})");
		ocl.assertQueryFalse(null, "Map{null with null}->excludesMap(Map{null with null})");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->excludesMap(Map{null with 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->excludesMap(Map{4 with null})");
		ocl.assertQueryTrue(null, "Map{}->excludesMap(Map{null with null})");

		ocl.dispose();
	}

	@Test public void testMapExcludesPair() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(3, 8)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(3.0, 8)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(4.0, 'tst')");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes('test', true)");

		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(3.5, 8)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(3, true)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes('tst', 4.0)");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->excludes(0, 0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(invalid, 'tst')");
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludes(4.0, invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->excludes(0, 0)");

		// invalid null element
		ocl.assertQueryFalse(null, "Map{3 with 8, null with 'tst', 'test' with null}->excludes(null, 'tst')");
		ocl.assertQueryFalse(null, "Map{3 with 8, null with 'tst', 'test' with null}->excludes('test', null)");
		ocl.assertQueryFalse(null, "Map{null with null}->excludes(null, null)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->excludes(null, 'tst')");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->excludes(4, null)");
		ocl.assertQueryTrue(null, "Map{}->excludes(null, null)");

		ocl.dispose();
	}

	@Test public void testMapExcludesValue() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesValue(8)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesValue(8.0)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesValue('tst')");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesValue(true)");

		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesValue(3.5)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesValue(3)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesValue('test')");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->excludesValue(0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->excludesValue(invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excludesValue(0)");

		// invalid null element
		ocl.assertQueryFalse(null, "Map{3 with 8, 'tst' with null, 'test' with true}->excludesValue(null)");
		ocl.assertQueryFalse(null, "Map{null with null}->excludesValue(null)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->excludesValue(null)");
		ocl.assertQueryTrue(null, "Map{}->excludesValue(null)");

		ocl.dispose();
	}

	@Test public void testMapExcluding() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' with true, 'c' with true}", "Map{'b' with true, 'a' with true, 'b' with true, 'c' with true}->excluding('b')");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = invalid in m->excluding('a')");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->excluding(invalid)");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excluding('a')");
		// invalid map element
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true}", "Map{null with true, 'a' with true, null with true, 'b' with true}->excluding(null)");
		ocl.dispose();
	}

	@Test public void testMapExcludingAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' with true, 'c' with true}", "Map{'b' with true, 'a' with true, 'd' with true, 'd' with true, 'b' with true, 'c' with true, 'd' with true}->excludingAll(Sequence{'d', 'd', 'b', 'e'})");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = invalid in m->excludingAll(Sequence{'a'})");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->excludingAll(invalid)");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excludingAll(Sequence{'a'})");
		// invalid map element
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true}", "Map{null with true, 'a' with true, null with true, 'b' with true, 'c' with true}->excludingAll(Sequence{null, 'c'})");
		ocl.dispose();
	}

	@Test public void testMapExcludingMap() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' with true, 'c' with true, 'd' with true}", "Map{'b' with true, 'a' with true, 'd' with true, 'd' with true, 'b' with true, 'c' with true, 'd' with true}->excludingMap(Map{'d' with true, 'd' with false, 'b' with true, 'e' with true})");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = invalid in m->excludingMap(Map{'a' with true})");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->excludingMap(Map{invalid with true})");
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->excludingMap(Map{'a' with invalid})");
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->excludingMap(Map{invalid with invalid})");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excludingMap(Map{'a' with true})");
		// invalid map element
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true}", "Map{null with true, 'a' with true, null with true, 'b' with true, 'c' with true}->excludingMap(Map{null with true, 'c' with true})");
		ocl.dispose();
	}

	@Test public void testMapExcludingPair() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' with true, 'c' with true}", "Map{'b' with true, 'a' with true, 'b' with true, 'c' with true}->excluding('b', true)");
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true, 'c' with true}", "Map{'b' with true, 'a' with true, 'b' with true, 'c' with true}->excluding('b', false)");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = invalid in m->excluding('a', true)");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->excluding(invalid, true)");
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->excluding('a', invalid)");
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->excluding(invalid, invalid)");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excluding('a', true)");
		// invalid map element
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true}", "Map{null with true, 'a' with true, null with true, 'b' with true}->excluding(null, true)");
		ocl.dispose();
	}

	@Test public void testMapIncludes() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(3)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(3.0)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(4.0)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes('test')");

		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(3.5)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(8)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes('tst')");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->includes(0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includes(0)");

		// invalid null element
		ocl.assertQueryTrue(null, "Map{3 with 8, null with 'tst', 'test' with true}->includes(null)");
		ocl.assertQueryTrue(null, "Map{null with null}->includes(null)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->includes(null)");
		ocl.assertQueryFalse(null, "Map{}->includes(null)");

		ocl.dispose();
	}

	@Test public void testMapIncludesAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(Sequence{3, 'test'})");
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(Bag{3, 'test'})");
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(Set{3, 'test'})");
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(OrderedSet{3, 'test'})");

		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(Sequence{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(Bag{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(Set{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(OrderedSet{3.5, 'test'})");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = invalid in s->includesAll(Sequence{0})");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in Map{0 with false}->includesAll(s)");

		// invalid collection element
		// Collections can't contain the invalid value
		ocl.assertQueryInvalid(null, "Map{3 with true, 4.0 with true, invalid with true, 'test' with true}->includesAll(OrderedSet{'test'})");
		ocl.assertQueryInvalid(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(Sequence{'test', invalid})");

		// null collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includesAll(Sequence{0})");
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in Map{0 with false}->includesAll(s)");

		// null collection element
		ocl.assertQueryTrue(null, "Map{3 with true, 4.0 with true, null with true, 'test' with true}->includesAll(OrderedSet{'test', null})");
		ocl.assertQueryFalse(null, "Map{3 with true, 4.0 with true, 'test' with true}->includesAll(OrderedSet{'test', null})");

		ocl.dispose();
	}

	@Test public void testMapIncludesMap() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{3 with 8})");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{3.0 with 8})");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{3 with 8, 4.0 with 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{3 with 8, 4 with 'tst', 'test' with true})");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{4.0 with 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{'test' with true})");

		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{3.5 with 8})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{3 with 8, 3.5 with 8, 4.0 with 'tst', 'test' with true})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{3 with true})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{'tst' with 4.0})");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->includesMap(Map{0 with 0})");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{invalid with 'tst'})");
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesMap(Map{4.0 with invalid})");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includesMap(Map{0 with 0})");

		// invalid null element
		ocl.assertQueryTrue(null, "Map{3 with 8, null with 'tst', 'test' with null}->includesMap(Map{null with 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 with 8, null with 'tst', 'test' with null}->includesMap(Map{'test' with null})");
		ocl.assertQueryTrue(null, "Map{null with null}->includesMap(Map{null with null})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->includesMap(Map{null with 'tst'})");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->includesMap(Map{4 with null})");
		ocl.assertQueryFalse(null, "Map{}->includesMap(Map{null with null})");

		ocl.dispose();
	}

	@Test public void testMapIncludesPair() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(3, 8)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(3.0, 8)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(4.0, 'tst')");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes('test', true)");

		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(3.5, 8)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(3, true)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes('tst', 4.0)");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->includes(0, 0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(invalid, 'tst')");
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includes(4.0, invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includes(0, 0)");

		// invalid null element
		ocl.assertQueryTrue(null, "Map{3 with 8, null with 'tst', 'test' with null}->includes(null, 'tst')");
		ocl.assertQueryTrue(null, "Map{3 with 8, null with 'tst', 'test' with null}->includes('test', null)");
		ocl.assertQueryTrue(null, "Map{null with null}->includes(null, null)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->includes(null, 'tst')");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->includes(4, null)");
		ocl.assertQueryFalse(null, "Map{}->includes(null, null)");

		ocl.dispose();
	}

	@Test public void testMapIncludesValue() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesValue(8)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesValue(8.0)");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesValue('tst')");
		ocl.assertQueryTrue(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesValue(true)");

		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesValue(3.5)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesValue(3)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesValue('test')");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->includesValue(0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 with 8, 4.0 with 'tst', 'test' with true}->includesValue(invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includesValue(0)");

		// invalid null element
		ocl.assertQueryTrue(null, "Map{3 with 8, 'tst' with null, 'test' with true}->includesValue(null)");
		ocl.assertQueryTrue(null, "Map{null with null}->includesValue(null)");
		ocl.assertQueryFalse(null, "Map{3 with 8, 4 with 'tst', 'test' with true}->includesValue(null)");
		ocl.assertQueryFalse(null, "Map{}->includesValue(null)");

		ocl.dispose();
	}

	@Test public void testMapIncludingMap() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with false, 'c' with true, 'd' with true}", "Map{'a' with true, 'b' with true}->includingMap(Map{'c' with true, 'd' with true, 'b' with false})");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(String, Integer) = invalid in m->includingMap(Map{'a' with true})");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->includingMap(Map{invalid with true})");
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->includingMap(Map{true with invalid})");
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->includingMap(Map{invalid with invalid})");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(String, Integer) = null in m->includingMap(Map{'a' with true})");
		// null map element
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true, null with true, null with true}", "Map{'a' with true, 'b' with true}->includingMap(Map{null with true, null with true})");
		ocl.assertQueryResults(null, "Map{'a' with true, null with true, 'b' with true, null with false}", "Map{'a' with true, null with true, 'b' with true}->includingMap(Map{null with false})");
		ocl.assertQueryResults(null, "Map{'a' with true, null with true, 'b' with null, null with true}", "Map{'a' with true, null with true, 'b' with true}->includingMap(Map{'b' with null})");
		ocl.dispose();
	}

	@Test public void testMapIncludingPair() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true, 'c' with true}", "Map{'a' with true, 'b' with true}->including('c', true)");
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with false}", "Map{'a' with true, 'b' with true}->including('b', false)");
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with 'c'}", "Map{'a' with true, 'b' with true}->including('b', 'c')");
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true, true with 'c'}", "Map{'a' with true, 'b' with true}->including(true, 'c')");
		ocl.assertQueryEquals(null, standardLibrary.getStringType(), "Map{'a' with true, 'b' with true}->including('b', false)->oclType().keyType");
		ocl.assertQueryEquals(null, standardLibrary.getBooleanType(), "Map{'a' with true, 'b' with true}->including('b', false)->oclType().valueType");
		ocl.assertQueryEquals(null, standardLibrary.getOclAnyType(), "Map{'a' with true, 'b' with true}->including('b', 'c')->oclType().valueType");
		ocl.assertQueryEquals(null, standardLibrary.getOclAnyType(), "Map{'a' with true, 'b' with true}->including(true, 'c')->oclType().keyType");

		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(String, Integer) = invalid in m->including('a', null)");

		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->including(invalid, true)");
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->including('c', invalid)");
		ocl.assertQueryInvalid(null, "Map{'a' with true, 'b' with true}->including(invalid, invalid)");

		// null map
		ocl.assertQueryInvalid(null, "let m : Map(String, Integer) = null in m->including('a', true)");

		// null map element
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true, null with true}", "Map{'a' with true, 'b' with true}->including(null, true)");
		ocl.assertQueryResults(null, "Map{'a' with true, 'b' with true, 'c' with null}", "Map{'a' with true, 'b' with true}->including('c', null)");
		ocl.dispose();
	}

	@Test public void testMapIsEmpty() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{}->isEmpty()");
		ocl.assertQueryFalse(null, "Map{1 with 4, 2 with 4, 3 with 'test'}->isEmpty()");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->isEmpty()");

		// null map
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = null in s->isEmpty()");

		ocl.assertQueryFalse(null, "Map{null with null}->isEmpty()");
		ocl.dispose();
	}

	@Test public void testMapKeyType() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		org.eclipse.ocl.pivot.Class integerType = standardLibrary.getIntegerType();
		org.eclipse.ocl.pivot.Class oclAnyType = standardLibrary.getOclAnyType();
		org.eclipse.ocl.pivot.Class stringType = standardLibrary.getStringType();
		//
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->keyType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map(String,Boolean)", "keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->_'Map'::keyType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->MapType::keyType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "MapType::keyType");
//
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.keyType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "String", "keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.MapType::keyType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "MapType::keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}._'Map'::keyType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "keyType");
//
		ocl.assertQueryEquals(null, stringType, "Map{'1' with true}->oclType().keyType");
		ocl.assertQueryEquals(null, stringType, "Map{'1' with true}->oclType().MapType::keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->oclType()._'Map'::keyType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "keyType");
//
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType()->keyType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Bag(PrimitiveType)", "keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType()->MapType::keyType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "MapType::keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType()->_'Map'::keyType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "keyType");
//
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType().keyType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "PrimitiveType", "keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType().MapType::keyType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "MapType::keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType()._'Map'::keyType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "keyType");
//
		ocl.assertQueryEquals(null, oclAnyType, "Map{1 with true, 2.0 with true, '3' with true}->oclType().keyType");
		ocl.assertQueryEquals(null, integerType, "Map{1 with true, 2 with true, 3 with true}->oclType().keyType");
		ocl.assertQueryEquals(null, integerType, "Map{1 with true, 2 with true, 3 with true}->oclAsType(Map(Real, Boolean))->oclType().keyType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->MapType::size()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "MapType", "size");
	}

	@Test public void testMapKeys() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Map{}->keys()");

		ocl.assertQueryResults(null, "Set{1, 2.0, '3'}", "Map{1 with null, 2.0 with true, '3' with 2}->keys()");

		ocl.assertQueryResults(null, "Set{'a', 'b', 'c'}", "Map{'a' with true, 'b' with true, 'c' with null, 'b' with null}->keys()");

		// invalid collection
		ocl.assertQueryInvalid(null, "let m : Map(Integer, Integer) = invalid in m->keys()");

		// null collection
		ocl.assertQueryInvalid(null, "let m : Map(Integer, Integer) = null in m->keys()");
		ocl.dispose();
	}

	@Test public void testMapLiteral() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 0, "Map{}->size()");
		ocl.assertQueryEquals(null, 1, "Map{'a' with 'b'}->size()");
		ocl.assertQueryEquals(null, 1, "Map{'a' with 'b','a' with 'b'}->size()");
		ocl.assertQueryEquals(null, 2, "Map{'a' with 'b','b' with 'a'}->size()");
		ocl.assertQueryTrue(null, "let m = Map{'a' with 'b','a' with 'c'} in (m->size()=1) and (m->at('a')='c')");

		ocl.assertQueryEquals(null, 1, "Map{null with 'b'}->size()");
		ocl.assertQueryEquals(null, 1, "Map{'a' with null}->size()");
		ocl.assertQueryEquals(null, 1, "Map{null with null}->size()");

		ocl.assertQueryInvalid(null, "Map{invalid with 'b'}", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "Map{'a' with invalid}", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "Map{invalid with invalid}", "invalid", InvalidValueException.class);
		ocl.dispose();
	}

	@Test public void testMapNotEmpty() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{}->notEmpty()");
		ocl.assertQueryTrue(null, "Map{1 with 4, 2 with 4, 3 with 'test'}->notEmpty()");

		// invalid map
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->notEmpty()");


		// null map
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = null in s->notEmpty()");

		ocl.assertQueryTrue(null, "Map{null with null}->notEmpty()");
		ocl.dispose();
	}

	@Test public void testMapNotEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{1 with 1} <> Map{1 with 1}");
		//
		ocl.assertQueryTrue(null, "Map{} <> Bag{}");
		ocl.assertQueryTrue(null, "Map{} <> OrderedSet{}");
		ocl.assertQueryTrue(null, "Map{} <> Sequence{}");
		ocl.assertQueryTrue(null, "Map{} <> Set{}");

		ocl.assertQueryTrue(null, "Map{1 with 1} <> 1");
		ocl.assertQueryTrue(null, "1 <> Map{1 with 1}");
		ocl.assertQueryTrue(null, "Set{1} <> Set{Set{1}}");

		ocl.assertQueryFalse(null, "Map{1 with 1} <> Map{1 with 1}");
		ocl.assertQueryFalse(null, "Map{1.0 with 1} <> Map{1 with 1}");
		ocl.assertQueryFalse(null, "Map{1 with 1.0} <> Map{1 with 1}");
		ocl.assertQueryFalse(null, "Map{1.0 with 1.0} <> Map{1 with 1}");

		ocl.assertQueryTrue(null, "Map{1.01 with 1} <> Map{1 with 1}");
		ocl.assertQueryTrue(null, "Map{1 with 1.01} <> Map{1 with 1}");
		ocl.assertQueryTrue(null, "Map{1.01 with 1.01} <> Map{1 with 1}");

		ocl.assertQueryTrue(null, "Map{1 with 1} <> Map{1 with 1, 2 with 1}");

		ocl.assertQueryFalse(null, "Map{Map{'a' with 'b'} with 1} <> Map{Map{'a' with 'b'} with 1}");
		ocl.assertQueryTrue(null, "Map{Map{'a' with 'b'} with 1} <> Map{Map{'a' with 'c'} with 1}");
		ocl.assertQueryTrue(null, "Map{Map{'a' with 'b'} with 1} <> Map{Map{'b' with 'b'} with 1}");
		ocl.assertQueryFalse(null, "Map{1 with Map{'a' with 'b'}} <> Map{1 with Map{'a' with 'b'}}");
		ocl.assertQueryTrue(null, "Map{1 with Map{'a' with 'b'}} <> Map{1 with Map{'a' with 'c'}}");
		ocl.assertQueryTrue(null, "Map{1 with Map{'a' with 'b'}} <> Map{1 with Map{'b' with 'b'}}");

		// null map element
		ocl.assertQueryFalse(null, "Map{null with null} <> Map{null with null}");
		ocl.assertQueryTrue(null, "Map{null with 1} <> Map{null with null}");
		ocl.assertQueryTrue(null, "Map{true with null} <> Map{null with null}");
		ocl.assertQueryTrue(null, "Map{'4' with 4} <> Map{null with null}");
		ocl.dispose();
	}

	/**
	 * Tests the reject() iterator.
	 */
	@Test public void testMapReject() {
		TestOCL ocl = createOCL();
		/*		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) ocl.getEnvironmentFactory();
		IdResolver idResolver = ocl.getIdResolver();
		@SuppressWarnings("null") @NonNull Type packageType = environmentFactory.getASClass("Package");
		CollectionTypeId typeId = TypeId.SET.getSpecializedCollectionTypeId(packageType.getTypeId());
		CollectionValue expected = idResolver.createSetOfEach(typeId, ocl.pkg2, ocl.pkg3);

		// complete form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->reject(p : ocl::Package | p.name = 'bob')");

		// shorter form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->reject(p | p.name = 'bob')");

		// shortest form
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->reject(name = 'bob')");

		expected = idResolver.createSetOfEach(typeId);
		ocl.assertQueryEquals(ocl.pkg1, expected, "ownedPackages?->reject(true)"); */
		ocl.dispose();
	}

	@Test public void testMapSize() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 0, "Map{}->size()");
		ocl.assertQueryEquals(null, 1, "Map{'a' with 'b'}->size()");
		ocl.assertQueryEquals(null, 1, "Map{'1' with true}->size()");
		ocl.assertQueryEquals(null, 1, "Map{'1' with true}->_'Map'::size()");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->MapType::size()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "MapType", "size");
		ocl.dispose();
	}

	@Test public void testMapValueType() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		org.eclipse.ocl.pivot.Class integerType = standardLibrary.getIntegerType();
		org.eclipse.ocl.pivot.Class oclAnyType = standardLibrary.getOclAnyType();
		org.eclipse.ocl.pivot.Class booleanType = standardLibrary.getBooleanType();
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->valueType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map(String,Boolean)", "valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->_'Map'::valueType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->MapType::valueType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "MapType::valueType");
//
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.valueType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "String", "valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.MapType::valueType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "MapType::valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}._'Map'::valueType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "valueType");
//
		ocl.assertQueryEquals(null, booleanType, "Map{'1' with true}->oclType().valueType");
		ocl.assertQueryEquals(null, booleanType, "Map{'1' with true}->oclType().MapType::valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->oclType()._'Map'::valueType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "valueType");
//
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType()->valueType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Bag(PrimitiveType)", "valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType()->MapType::valueType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "MapType::valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType()->_'Map'::valueType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "valueType");
//
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType().valueType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "PrimitiveType", "valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType().MapType::valueType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "MapType::valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}.oclType()._'Map'::valueType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Map", "valueType");
//
		ocl.assertQueryEquals(null, oclAnyType, "Map{1 with 1, 2.0 with '2', '3' with true}->oclType().valueType");
		ocl.assertQueryEquals(null, integerType, "Map{1 with 1, 2 with 2, 3 with 3}->oclType().valueType");
		ocl.assertQueryInvalid(null, "Map{1 with 1, 2 with 2, 3 with 3}->oclAsType(Map(Real, Boolean))->oclType().valueType",
			StringUtil.bind(PivotMessages.IncompatibleOclAsTypeSourceType, "Map(Integer,Integer)", "Map(Real,Boolean)"), InvalidValueException.class);
		ocl.assertQueryEquals(null, integerType, "Map{1 with 1, 2 with 2, 3 with 3}->oclAsType(Map(Real, Real))->oclType().valueType");
		ocl.assertSemanticErrorQuery(null, "Map{'1' with true}->MapType::size()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "MapType", "size");
	}

	@Test public void testMapValues() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Map{}->values()");

		ocl.assertQueryResults(null, "Bag{null, 2, true}", "Map{1 with null, 2.0 with true, '3' with 2}->values()");

		ocl.assertQueryResults(null, "Bag{true, null, null}", "Map{'a' with true, 'b' with true, 'c' with null, 'b' with null}->values()");

		// invalid collection
		ocl.assertQueryInvalid(null, "let m : Map(Integer, Integer) = invalid in m->values()");

		// null collection
		ocl.assertQueryInvalid(null, "let m : Map(Integer, Integer) = null in m->values()");
		ocl.dispose();
	}
}
