/*******************************************************************************
 * Copyright (c) 2015, 2019 Eclipse Modeling Project and others.
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
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
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
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), useCodeGen ? getProjectMap() : OCL.NO_PROJECTS);
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

		ocl.assertQueryEquals(null, "b", "Map{'a'<-'b'}->at('a')");
		ocl.assertQueryInvalid(null, "Map{'a'<-'b'}->at('b')", StringUtil.bind(PivotMessages.IndexNotInUse, "b"), InvalidValueException.class);
		ocl.assertQueryInvalid(null, "Map{'a'<-'b'}->at(null)", StringUtil.bind(PivotMessages.IndexNotInUse, "null"), InvalidValueException.class);

		ocl.assertQueryEquals(null, "b", "Map{null<-'b'}->at(null)");
		ocl.assertQueryInvalid(null, "Map{null<-'b'}->at('b')", StringUtil.bind(PivotMessages.IndexNotInUse, "b"), InvalidValueException.class);

		ocl.assertQueryNull(null, "Map{'a'<-null}->at('a')");
		ocl.assertQueryInvalid(null, "Map{'a'<-null}->at(null)", StringUtil.bind(PivotMessages.IndexNotInUse, "null"), InvalidValueException.class);

		ocl.assertQueryInvalid(null, "Map{'a'<-'b'}->at(invalid)", "invalid", InvalidValueException.class);

		ocl.dispose();
	}

	@Test public void testMapEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{} = Bag{}");
		ocl.assertQueryFalse(null, "Map{} = OrderedSet{}");
		ocl.assertQueryFalse(null, "Map{} = Sequence{}");
		ocl.assertQueryFalse(null, "Map{} = Set{}");

		ocl.assertQueryFalse(null, "Map{1 <- 1} = 1");
		ocl.assertQueryFalse(null, "1 = Map{1 <- 1}");
		ocl.assertQueryFalse(null, "Set{1} = Set{Set{1}}");

		ocl.assertQueryTrue(null, "Map{1 <- 1} = Map{1 <- 1}");
		ocl.assertQueryTrue(null, "Map{1.0 <- 1} = Map{1 <- 1}");
		ocl.assertQueryTrue(null, "Map{1 <- 1.0} = Map{1 <- 1}");
		ocl.assertQueryTrue(null, "Map{1.0 <- 1.0} = Map{1 <- 1}");

		ocl.assertQueryFalse(null, "Map{1.01 <- 1} = Map{1 <- 1}");
		ocl.assertQueryFalse(null, "Map{1 <- 1.01} = Map{1 <- 1}");
		ocl.assertQueryFalse(null, "Map{1.01 <- 1.01} = Map{1 <- 1}");

		ocl.assertQueryFalse(null, "Map{1 <- 1} = Map{1 <- 1, 2 <- 1}");

		ocl.assertQueryTrue(null, "Map{Map{'a'<-'b'} <- 1} = Map{Map{'a'<-'b'} <- 1}");
		ocl.assertQueryFalse(null, "Map{Map{'a'<-'b'} <- 1} = Map{Map{'a'<-'c'} <- 1}");
		ocl.assertQueryFalse(null, "Map{Map{'a'<-'b'} <- 1} = Map{Map{'b'<-'b'} <- 1}");
		ocl.assertQueryTrue(null, "Map{1 <- Map{'a'<-'b'}} = Map{1 <- Map{'a'<-'b'}}");
		ocl.assertQueryFalse(null, "Map{1 <- Map{'a'<-'b'}} = Map{1 <- Map{'a'<-'c'}}");
		ocl.assertQueryFalse(null, "Map{1 <- Map{'a'<-'b'}} = Map{1 <- Map{'b'<-'b'}}");

		// null map element
		ocl.assertQueryTrue(null, "Map{null <- null} = Map{null <- null}");
		ocl.assertQueryFalse(null, "Map{null <- 1} = Map{null <- null}");
		ocl.assertQueryFalse(null, "Map{true <- null} = Map{null <- null}");
		ocl.assertQueryFalse(null, "Map{'4' <- 4} = Map{null <- null}");
		ocl.dispose();
	}

	@Test public void testMapErrors() {
		TestOCL ocl = createOCL();
		ocl.assertValidationErrorQuery(null, "Map{}?->keyType", PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "CallExp::SafeSourceCannotBeMap", ocl);
		ocl.assertValidationErrorQuery(null, "Map{}?->size()", PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "CallExp::SafeSourceCannotBeMap", ocl);
		ocl.assertValidationErrorQuery(null, "Map{}?->collect(c | '')", PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "CallExp::SafeSourceCannotBeMap", "Map{}?->collect(c : OclVoid[1] <- 2_ : OclVoid[1] | '')");
		ocl.assertValidationErrorQuery(null, "Map{}?->iterate(c; acc:String = '' | '')", PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "CallExp::SafeSourceCannotBeMap", "Map{}?->iterate(c : OclVoid[1] <- 2_ : OclVoid[1]; acc : String[1] = '' | '')");
		ocl.dispose();
	}

	@Test public void testMapExcludes() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(3)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(3.0)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(4.0)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes('test')");

		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(3.5)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(8)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes('tst')");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->excludes(0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->excludes(0)");

		// invalid null element
		ocl.assertQueryFalse(null, "Map{3 <- 8, null <- 'tst', 'test' <- true}->excludes(null)");
		ocl.assertQueryFalse(null, "Map{null <- null}->excludes(null)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->excludes(null)");
		ocl.assertQueryTrue(null, "Map{}->excludes(null)");

		ocl.dispose();
	}

	@Test public void testMapExcludesAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Sequence{3, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Bag{3, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Set{3, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(OrderedSet{3, 'test'})");

		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Sequence{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Bag{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Set{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(OrderedSet{3.5, 'test'})");

		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Sequence{3.5, 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Bag{3.5, 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Set{3.5, 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(OrderedSet{3.5, 'tst'})");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = invalid in s->excludesAll(Sequence{0})");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in Map{0 <- false}->excludesAll(s)");

		// invalid collection element
		// Collections can't contain the invalid value
		ocl.assertQueryInvalid(null, "Map{3 <- true, 4.0 <- true, invalid <- true, 'test' <- true}->excludesAll(OrderedSet{'test'})");
		ocl.assertQueryInvalid(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(Sequence{'test', invalid})");

		// null collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->excludesAll(Sequence{0})");
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in Map{0 <- false}->excludesAll(s)");

		// null collection element
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, null <- true, 'test' <- true}->excludesAll(OrderedSet{'test', null})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(OrderedSet{'test', null})");
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesAll(OrderedSet{'tst', null})");

		ocl.dispose();
	}

	@Test public void testMapExcludesMap() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{3 <- 8})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{3.0 <- 8})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{3 <- 8, 4.0 <- 'tst'})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{3 <- 8, 4 <- 'tst', 'test' <- true})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{4.0 <- 'tst'})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{'test' <- true})");

		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{3.5 <- 8})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{3 <- 8, 3.5 <- 8, 4.0 <- 'tst', 'test' <- true})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{3 <- true})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{'tst' <- 4.0})");

		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesMap(Map{3.5 <- true})");
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->excludesMap(Map{3.5 <- true, 3 <- false})");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->excludesMap(Map{0 <- 0})");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{invalid <- 'tst'})");
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesMap(Map{4.0 <- invalid})");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->excludesMap(Map{0 <- 0})");

		// invalid null element
		ocl.assertQueryFalse(null, "Map{3 <- 8, null <- 'tst', 'test' <- null}->excludesMap(Map{null <- 'tst'})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, null <- 'tst', 'test' <- null}->excludesMap(Map{'test' <- null})");
		ocl.assertQueryFalse(null, "Map{null <- null}->excludesMap(Map{null <- null})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->excludesMap(Map{null <- 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->excludesMap(Map{4 <- null})");
		ocl.assertQueryTrue(null, "Map{}->excludesMap(Map{null <- null})");

		ocl.dispose();
	}

	@Test public void testMapExcludesPair() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(3, 8)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(3.0, 8)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(4.0, 'tst')");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes('test', true)");

		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(3.5, 8)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(3, true)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes('tst', 4.0)");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->excludes(0, 0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(invalid, 'tst')");
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludes(4.0, invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->excludes(0, 0)");

		// invalid null element
		ocl.assertQueryFalse(null, "Map{3 <- 8, null <- 'tst', 'test' <- null}->excludes(null, 'tst')");
		ocl.assertQueryFalse(null, "Map{3 <- 8, null <- 'tst', 'test' <- null}->excludes('test', null)");
		ocl.assertQueryFalse(null, "Map{null <- null}->excludes(null, null)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->excludes(null, 'tst')");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->excludes(4, null)");
		ocl.assertQueryTrue(null, "Map{}->excludes(null, null)");

		ocl.dispose();
	}

	@Test public void testMapExcludesValue() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesValue(8)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesValue(8.0)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesValue('tst')");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesValue(true)");

		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesValue(3.5)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesValue(3)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesValue('test')");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->excludesValue(0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->excludesValue(invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excludesValue(0)");

		// invalid null element
		ocl.assertQueryFalse(null, "Map{3 <- 8, 'tst' <- null, 'test' <- true}->excludesValue(null)");
		ocl.assertQueryFalse(null, "Map{null <- null}->excludesValue(null)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->excludesValue(null)");
		ocl.assertQueryTrue(null, "Map{}->excludesValue(null)");

		ocl.dispose();
	}

	@Test public void testMapExcluding() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' <- true, 'c' <- true}", "Map{'b' <- true, 'a' <- true, 'b' <- true, 'c' <- true}->excluding('b')");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = invalid in m->excluding('a')");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->excluding(invalid)");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excluding('a')");
		// invalid map element
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true}", "Map{null <- true, 'a' <- true, null <- true, 'b' <- true}->excluding(null)");
		ocl.dispose();
	}

	@Test public void testMapExcludingAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' <- true, 'c' <- true}", "Map{'b' <- true, 'a' <- true, 'd' <- true, 'd' <- true, 'b' <- true, 'c' <- true, 'd' <- true}->excludingAll(Sequence{'d', 'd', 'b', 'e'})");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = invalid in m->excludingAll(Sequence{'a'})");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->excludingAll(invalid)");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excludingAll(Sequence{'a'})");
		// invalid map element
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true}", "Map{null <- true, 'a' <- true, null <- true, 'b' <- true, 'c' <- true}->excludingAll(Sequence{null, 'c'})");
		ocl.dispose();
	}

	@Test public void testMapExcludingMap() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' <- true, 'c' <- true, 'd' <- true}", "Map{'b' <- true, 'a' <- true, 'd' <- true, 'd' <- true, 'b' <- true, 'c' <- true, 'd' <- true}->excludingMap(Map{'d' <- true, 'd' <- false, 'b' <- true, 'e' <- true})");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = invalid in m->excludingMap(Map{'a' <- true})");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->excludingMap(Map{invalid <- true})");
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->excludingMap(Map{'a' <- invalid})");
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->excludingMap(Map{invalid <- invalid})");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excludingMap(Map{'a' <- true})");
		// invalid map element
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true}", "Map{null <- true, 'a' <- true, null <- true, 'b' <- true, 'c' <- true}->excludingMap(Map{null <- true, 'c' <- true})");
		ocl.dispose();
	}

	@Test public void testMapExcludingPair() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' <- true, 'c' <- true}", "Map{'b' <- true, 'a' <- true, 'b' <- true, 'c' <- true}->excluding('b', true)");
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true, 'c' <- true}", "Map{'b' <- true, 'a' <- true, 'b' <- true, 'c' <- true}->excluding('b', false)");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = invalid in m->excluding('a', true)");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->excluding(invalid, true)");
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->excluding('a', invalid)");
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->excluding(invalid, invalid)");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(Integer,Boolean) = null in m->excluding('a', true)");
		// invalid map element
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true}", "Map{null <- true, 'a' <- true, null <- true, 'b' <- true}->excluding(null, true)");
		ocl.dispose();
	}

	@Test public void testMapIncludes() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(3)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(3.0)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(4.0)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes('test')");

		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(3.5)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(8)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes('tst')");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->includes(0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includes(0)");

		// invalid null element
		ocl.assertQueryTrue(null, "Map{3 <- 8, null <- 'tst', 'test' <- true}->includes(null)");
		ocl.assertQueryTrue(null, "Map{null <- null}->includes(null)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->includes(null)");
		ocl.assertQueryFalse(null, "Map{}->includes(null)");

		ocl.dispose();
	}

	@Test public void testMapIncludesAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(Sequence{3, 'test'})");
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(Bag{3, 'test'})");
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(Set{3, 'test'})");
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(OrderedSet{3, 'test'})");

		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(Sequence{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(Bag{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(Set{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(OrderedSet{3.5, 'test'})");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = invalid in s->includesAll(Sequence{0})");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in Map{0 <- false}->includesAll(s)");

		// invalid collection element
		// Collections can't contain the invalid value
		ocl.assertQueryInvalid(null, "Map{3 <- true, 4.0 <- true, invalid <- true, 'test' <- true}->includesAll(OrderedSet{'test'})");
		ocl.assertQueryInvalid(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(Sequence{'test', invalid})");

		// null collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includesAll(Sequence{0})");
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in Map{0 <- false}->includesAll(s)");

		// null collection element
		ocl.assertQueryTrue(null, "Map{3 <- true, 4.0 <- true, null <- true, 'test' <- true}->includesAll(OrderedSet{'test', null})");
		ocl.assertQueryFalse(null, "Map{3 <- true, 4.0 <- true, 'test' <- true}->includesAll(OrderedSet{'test', null})");

		ocl.dispose();
	}

	@Test public void testMapIncludesMap() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{3 <- 8})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{3.0 <- 8})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{3 <- 8, 4.0 <- 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{3 <- 8, 4 <- 'tst', 'test' <- true})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{4.0 <- 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{'test' <- true})");

		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{3.5 <- 8})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{3 <- 8, 3.5 <- 8, 4.0 <- 'tst', 'test' <- true})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{3 <- true})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{'tst' <- 4.0})");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->includesMap(Map{0 <- 0})");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{invalid <- 'tst'})");
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesMap(Map{4.0 <- invalid})");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includesMap(Map{0 <- 0})");

		// invalid null element
		ocl.assertQueryTrue(null, "Map{3 <- 8, null <- 'tst', 'test' <- null}->includesMap(Map{null <- 'tst'})");
		ocl.assertQueryTrue(null, "Map{3 <- 8, null <- 'tst', 'test' <- null}->includesMap(Map{'test' <- null})");
		ocl.assertQueryTrue(null, "Map{null <- null}->includesMap(Map{null <- null})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->includesMap(Map{null <- 'tst'})");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->includesMap(Map{4 <- null})");
		ocl.assertQueryFalse(null, "Map{}->includesMap(Map{null <- null})");

		ocl.dispose();
	}

	@Test public void testMapIncludesPair() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(3, 8)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(3.0, 8)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(4.0, 'tst')");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes('test', true)");

		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(3.5, 8)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(3, true)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes('tst', 4.0)");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->includes(0, 0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(invalid, 'tst')");
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includes(4.0, invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includes(0, 0)");

		// invalid null element
		ocl.assertQueryTrue(null, "Map{3 <- 8, null <- 'tst', 'test' <- null}->includes(null, 'tst')");
		ocl.assertQueryTrue(null, "Map{3 <- 8, null <- 'tst', 'test' <- null}->includes('test', null)");
		ocl.assertQueryTrue(null, "Map{null <- null}->includes(null, null)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->includes(null, 'tst')");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->includes(4, null)");
		ocl.assertQueryFalse(null, "Map{}->includes(null, null)");

		ocl.dispose();
	}

	@Test public void testMapIncludesValue() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesValue(8)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesValue(8.0)");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesValue('tst')");
		ocl.assertQueryTrue(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesValue(true)");

		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesValue(3.5)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesValue(3)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesValue('test')");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->includesValue(0)");

		// invalid collection element
		ocl.assertQueryInvalid(null, "Map{3 <- 8, 4.0 <- 'tst', 'test' <- true}->includesValue(invalid)");

		// invalid null
		ocl.assertQueryInvalid(null, "let s : Map(Integer,Boolean) = null in s->includesValue(0)");

		// invalid null element
		ocl.assertQueryTrue(null, "Map{3 <- 8, 'tst' <- null, 'test' <- true}->includesValue(null)");
		ocl.assertQueryTrue(null, "Map{null <- null}->includesValue(null)");
		ocl.assertQueryFalse(null, "Map{3 <- 8, 4 <- 'tst', 'test' <- true}->includesValue(null)");
		ocl.assertQueryFalse(null, "Map{}->includesValue(null)");

		ocl.dispose();
	}

	@Test public void testMapIncludingMap() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- false, 'c' <- true, 'd' <- true}", "Map{'a' <- true, 'b' <- true}->includingMap(Map{'c' <- true, 'd' <- true, 'b' <- false})");
		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(String, Integer) = invalid in m->includingMap(Map{'a' <- true})");
		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->includingMap(Map{invalid <- true})");
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->includingMap(Map{true <- invalid})");
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->includingMap(Map{invalid <- invalid})");
		// null map
		ocl.assertQueryInvalid(null, "let m : Map(String, Integer) = null in m->includingMap(Map{'a' <- true})");
		// null map element
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true, null <- true, null <- true}", "Map{'a' <- true, 'b' <- true}->includingMap(Map{null <- true, null <- true})");
		ocl.assertQueryResults(null, "Map{'a' <- true, null <- true, 'b' <- true, null <- false}", "Map{'a' <- true, null <- true, 'b' <- true}->includingMap(Map{null <- false})");
		ocl.assertQueryResults(null, "Map{'a' <- true, null <- true, 'b' <- null, null <- true}", "Map{'a' <- true, null <- true, 'b' <- true}->includingMap(Map{'b' <- null})");
		ocl.dispose();
	}

	@Test public void testMapIncludingPair() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true, 'c' <- true}", "Map{'a' <- true, 'b' <- true}->including('c', true)");
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- false}", "Map{'a' <- true, 'b' <- true}->including('b', false)");
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- 'c'}", "Map{'a' <- true, 'b' <- true}->including('b', 'c')");
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true, true <- 'c'}", "Map{'a' <- true, 'b' <- true}->including(true, 'c')");
		ocl.assertQueryEquals(null, standardLibrary.getStringType(), "Map{'a' <- true, 'b' <- true}->including('b', false).oclType().keyType");
		ocl.assertQueryEquals(null, standardLibrary.getBooleanType(), "Map{'a' <- true, 'b' <- true}->including('b', false).oclType().valueType");
		ocl.assertQueryEquals(null, standardLibrary.getOclAnyType(), "Map{'a' <- true, 'b' <- true}->including('b', 'c').oclType().valueType");
		ocl.assertQueryEquals(null, standardLibrary.getOclAnyType(), "Map{'a' <- true, 'b' <- true}->including(true, 'c').oclType().keyType");

		// invalid map
		ocl.assertQueryInvalid(null, "let m : Map(String, Integer) = invalid in m->including('a', null)");

		// invalid map element
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->including(invalid, true)");
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->including('c', invalid)");
		ocl.assertQueryInvalid(null, "Map{'a' <- true, 'b' <- true}->including(invalid, invalid)");

		// null map
		ocl.assertQueryInvalid(null, "let m : Map(String, Integer) = null in m->including('a', true)");

		// null map element
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true, null <- true}", "Map{'a' <- true, 'b' <- true}->including(null, true)");
		ocl.assertQueryResults(null, "Map{'a' <- true, 'b' <- true, 'c' <- null}", "Map{'a' <- true, 'b' <- true}->including('c', null)");
		ocl.dispose();
	}

	@Test public void testMapIsEmpty() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Map{}->isEmpty()");
		ocl.assertQueryFalse(null, "Map{1 <- 4, 2 <- 4, 3 <- 'test'}->isEmpty()");

		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->isEmpty()");

		// null map
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = null in s->isEmpty()");

		ocl.assertQueryFalse(null, "Map{null <- null}->isEmpty()");
		ocl.dispose();
	}

	@Test public void testMapKeyType() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertQueryEquals(null, standardLibrary.getStringType(), "Map{'1' <- true}->oclType().keyType");
		ocl.assertQueryEquals(null, standardLibrary.getOclAnyType(), "Map{1 <- true, 2.0 <- true, '3' <- true}->oclType().keyType");
		ocl.assertQueryEquals(null, standardLibrary.getIntegerType(), "Map{1 <- true, 2 <- true, 3 <- true}->oclType().keyType");
		ocl.assertQueryEquals(null, standardLibrary.getIntegerType(), "Map{1 <- true, 2 <- true, 3 <- true}->oclAsType(Map(Real, Boolean))->oclType().keyType");
		// FIXME fails because common type is Set(T) and then because T is not type-servable and has no OclAny inheritance
		//		ocl.assertQueryEquals(null, metamodelManager.getSetType(), "Sequence{Set{1}, Set{2.0}, Set{'3'}}->elementType");
		// FIXME fails because common type is inadequate for implicit collect
		//				ocl.assertQueryEquals(null, metamodelManager.getOclAnyType(), "Sequence{Set{1}, Set{2.0}, Set{'3'}}.elementType");
	}

	@Test public void testMapKeys() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Map{}->keys()");

		ocl.assertQueryResults(null, "Set{1, 2.0, '3'}", "Map{1 <- null, 2.0 <- true, '3' <- 2}->keys()");

		ocl.assertQueryResults(null, "Set{'a', 'b', 'c'}", "Map{'a' <- true, 'b' <- true, 'c' <- null, 'b' <- null}->keys()");

		// invalid collection
		ocl.assertQueryInvalid(null, "let m : Map(Integer, Integer) = invalid in m->keys()");

		// null collection
		ocl.assertQueryInvalid(null, "let m : Map(Integer, Integer) = null in m->keys()");
		ocl.dispose();
	}

	@Test public void testMapLiteral() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 0, "Map{}->size()");
		ocl.assertQueryEquals(null, 1, "Map{'a'<-'b'}->size()");
		ocl.assertQueryEquals(null, 1, "Map{'a'<-'b','a'<-'b'}->size()");
		ocl.assertQueryEquals(null, 2, "Map{'a'<-'b','b'<-'a'}->size()");
		ocl.assertQueryTrue(null, "let m = Map{'a'<-'b','a'<-'c'} in (m->size()=1) and (m->at('a')='c')");

		ocl.assertQueryEquals(null, 1, "Map{null<-'b'}->size()");
		ocl.assertQueryEquals(null, 1, "Map{'a'<-null}->size()");
		ocl.assertQueryEquals(null, 1, "Map{null<-null}->size()");

		ocl.assertQueryInvalid(null, "Map{invalid<-'b'}", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "Map{'a'<-invalid}", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "Map{invalid<-invalid}", "invalid", InvalidValueException.class);
		ocl.dispose();
	}

	@Test public void testMapNotEmpty() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{}->notEmpty()");
		ocl.assertQueryTrue(null, "Map{1 <- 4, 2 <- 4, 3 <- 'test'}->notEmpty()");

		// invalid map
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = invalid in s->notEmpty()");


		// null map
		ocl.assertQueryInvalid(null, "let s : Map(Integer,String) = null in s->notEmpty()");

		ocl.assertQueryTrue(null, "Map{null <- null}->notEmpty()");
		ocl.dispose();
	}

	@Test public void testMapNotEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Map{1 <- 1} <> Map{1 <- 1}");
		//
		ocl.assertQueryTrue(null, "Map{} <> Bag{}");
		ocl.assertQueryTrue(null, "Map{} <> OrderedSet{}");
		ocl.assertQueryTrue(null, "Map{} <> Sequence{}");
		ocl.assertQueryTrue(null, "Map{} <> Set{}");

		ocl.assertQueryTrue(null, "Map{1 <- 1} <> 1");
		ocl.assertQueryTrue(null, "1 <> Map{1 <- 1}");
		ocl.assertQueryTrue(null, "Set{1} <> Set{Set{1}}");

		ocl.assertQueryFalse(null, "Map{1 <- 1} <> Map{1 <- 1}");
		ocl.assertQueryFalse(null, "Map{1.0 <- 1} <> Map{1 <- 1}");
		ocl.assertQueryFalse(null, "Map{1 <- 1.0} <> Map{1 <- 1}");
		ocl.assertQueryFalse(null, "Map{1.0 <- 1.0} <> Map{1 <- 1}");

		ocl.assertQueryTrue(null, "Map{1.01 <- 1} <> Map{1 <- 1}");
		ocl.assertQueryTrue(null, "Map{1 <- 1.01} <> Map{1 <- 1}");
		ocl.assertQueryTrue(null, "Map{1.01 <- 1.01} <> Map{1 <- 1}");

		ocl.assertQueryTrue(null, "Map{1 <- 1} <> Map{1 <- 1, 2 <- 1}");

		ocl.assertQueryFalse(null, "Map{Map{'a'<-'b'} <- 1} <> Map{Map{'a'<-'b'} <- 1}");
		ocl.assertQueryTrue(null, "Map{Map{'a'<-'b'} <- 1} <> Map{Map{'a'<-'c'} <- 1}");
		ocl.assertQueryTrue(null, "Map{Map{'a'<-'b'} <- 1} <> Map{Map{'b'<-'b'} <- 1}");
		ocl.assertQueryFalse(null, "Map{1 <- Map{'a'<-'b'}} <> Map{1 <- Map{'a'<-'b'}}");
		ocl.assertQueryTrue(null, "Map{1 <- Map{'a'<-'b'}} <> Map{1 <- Map{'a'<-'c'}}");
		ocl.assertQueryTrue(null, "Map{1 <- Map{'a'<-'b'}} <> Map{1 <- Map{'b'<-'b'}}");

		// null map element
		ocl.assertQueryFalse(null, "Map{null <- null} <> Map{null <- null}");
		ocl.assertQueryTrue(null, "Map{null <- 1} <> Map{null <- null}");
		ocl.assertQueryTrue(null, "Map{true <- null} <> Map{null <- null}");
		ocl.assertQueryTrue(null, "Map{'4' <- 4} <> Map{null <- null}");
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
		CollectionTypeId typeId = TypeId.SET.getSpecializedId(packageType.getTypeId());
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
		ocl.assertQueryEquals(null, 1, "Map{'a'<-'b'}->size()");
		ocl.dispose();
	}

	@Test public void testMapValueType() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertQueryEquals(null, standardLibrary.getStringType(), "Map{true <- '1'}->oclType().valueType");
		ocl.assertQueryEquals(null, standardLibrary.getOclAnyType(), "Map{'1' <- 1, '2' <- 2.0, '3' <- '3'}->oclType().valueType");
		ocl.assertQueryEquals(null, standardLibrary.getIntegerType(), "Map{'1' <- 1, '2' <- 2, '3' <- 3}->oclType().valueType");
		ocl.assertQueryEquals(null, standardLibrary.getIntegerType(), "Map{'1' <- 1, '2' <- 2, '3' <- 3}->oclAsType(Map(String, Real))->oclType().valueType");
		// FIXME fails because common type is Set(T) and then because T is not type-servable and has no OclAny inheritance
		//		ocl.assertQueryEquals(null, metamodelManager.getSetType(), "Sequence{Set{1}, Set{2.0}, Set{'3'}}->valueType");
		// FIXME fails because common type is inadequate for implicit collect
		//				ocl.assertQueryEquals(null, metamodelManager.getOclAnyType(), "Sequence{Set{1}, Set{2.0}, Set{'3'}}.valueType");
	}

	@Test public void testMapValues() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Map{}->values()");

		ocl.assertQueryResults(null, "Bag{null, 2, true}", "Map{1 <- null, 2.0 <- true, '3' <- 2}->values()");

		ocl.assertQueryResults(null, "Bag{true, null, null}", "Map{'a' <- true, 'b' <- true, 'c' <- null, 'b' <- null}->values()");

		// invalid collection
		ocl.assertQueryInvalid(null, "let m : Map(Integer, Integer) = invalid in m->values()");

		// null collection
		ocl.assertQueryInvalid(null, "let m : Map(Integer, Integer) = null in m->values()");
		ocl.dispose();
	}
}
