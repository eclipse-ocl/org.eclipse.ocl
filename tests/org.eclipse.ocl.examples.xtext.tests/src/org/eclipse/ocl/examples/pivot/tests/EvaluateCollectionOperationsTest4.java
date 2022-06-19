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
 * Tests for collection operations.
 */
@RunWith(value = Parameterized.class)
public class EvaluateCollectionOperationsTest4 extends PivotTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public EvaluateCollectionOperationsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateCollectionOperations";
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

	@Test public void testCollectionAppend() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Sequence{'a', 'b', 'c'}", "Sequence{'a', 'b'}->append('c')");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', 'c'}", "OrderedSet{'a', 'b'}->append('c')");
		ocl.assertQueryResults(null, "Sequence{1..4,0}", "Sequence{1..4}->append(0)");
		ocl.assertQueryResults(null, "Sequence{1..4,4}", "Sequence{1..4}->append(4)");
		ocl.assertQueryResults(null, "Sequence{1..5}", "Sequence{1..4}->append(5)");
		ocl.assertQueryResults(null, "Sequence{1..4,6}", "Sequence{1..4}->append(6)");
		ocl.assertQueryResults(null, "OrderedSet{1..4,0}", "OrderedSet{1..4}->append(0)");
		ocl.assertQueryResults(null, "OrderedSet{1,3,4,2}", "OrderedSet{1..4}->append(2)");
		ocl.assertQueryResults(null, "OrderedSet{1..5}", "OrderedSet{1..4}->append(5)");
		ocl.assertQueryResults(null, "OrderedSet{1..4,6}", "OrderedSet{1..4}->append(6)");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->append('a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->append('a')");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b'}->append(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b'}->append(invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->append('a')");
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->append('a')");
		// null collection element
		ocl.assertQueryResults(null, "Sequence{'a', 'b', null}", "Sequence{'a', 'b'}->append(null)");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', null}", "OrderedSet{'a', 'b'}->append(null)");
		ocl.assertQueryResults(null, "Sequence{'a', null, 'b', null}", "Sequence{'a', null, 'b'}->append(null)");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', null}", "OrderedSet{'a', null, 'b'}->append(null)");
		ocl.assertQueryResults(null, "Sequence{'1..2', null}", "Sequence{'1..2'}->append(null)");
		ocl.assertQueryResults(null, "OrderedSet{'1..2', null}", "OrderedSet{'1..2'}->append(null)");
		ocl.dispose();
	}

	@Test public void testCollectionAppendAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Sequence{'a', 'b', 'c', 'd'}", "Sequence{'a', 'b'}->appendAll(Sequence{'c', 'd'})");
		ocl.assertQueryResults(null, "Sequence{'a', 'b', 'c', 'd'}", "Sequence{'a', 'b'}->appendAll(OrderedSet{'c', 'd'})");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', 'c', 'd'}", "OrderedSet{'a', 'b'}->appendAll(Sequence{'c', 'd'})");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', 'c', 'd'}", "OrderedSet{'a', 'b'}->appendAll(OrderedSet{'c', 'd'})");
		ocl.assertQueryResults(null, "Sequence{10..40,0..10}", "Sequence{10..40}->appendAll(Sequence{0..10})");
		ocl.assertQueryResults(null, "Sequence{10..40,40..50}", "Sequence{10..40}->appendAll(Sequence{40..50})");
		ocl.assertQueryResults(null, "Sequence{1..9}", "Sequence{1..4}->appendAll(Sequence{5..9})");
		ocl.assertQueryResults(null, "Sequence{1..4,6..10}", "Sequence{1..4}->appendAll(Sequence{6..10})");
		ocl.assertQueryResults(null, "OrderedSet{12..40,0..11}", "OrderedSet{10..40}->appendAll(OrderedSet{0..11})");
		ocl.assertQueryResults(null, "OrderedSet{1,3,2,4}", "OrderedSet{1..4}->appendAll(OrderedSet{2,4})");
		ocl.assertQueryResults(null, "OrderedSet{1..10}", "OrderedSet{1..4}->appendAll(OrderedSet{5..10})");
		ocl.assertQueryResults(null, "OrderedSet{1..4,6..10}", "OrderedSet{1..4}->appendAll(OrderedSet{6..10})");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->appendAll(Sequence{'c', 'd'})");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->appendAll(OrderedSet{'c', 'd'})");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b'}->appendAll(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b'}->appendAll(invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->appendAll(Sequence{'c', 'd'})");
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->appendAll(OrderedSet{'c', 'd'})");
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b'}->appendAll(null)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b'}->appendAll(null)");
		// null collection element
		ocl.assertQueryResults(null, "Sequence{'a', 'b', null, null}", "Sequence{'a', 'b'}->appendAll(Sequence{null,null})");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', null}", "OrderedSet{'a', 'b'}->appendAll(Sequence{null,null})");
		ocl.assertQueryResults(null, "Sequence{'a', null, 'b', null, null}", "Sequence{'a', null, 'b'}->appendAll(Sequence{null,null})");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', null}", "OrderedSet{'a', null, 'b'}->appendAll(Sequence{null,null})");
		ocl.assertQueryResults(null, "Sequence{'1..2', null, null}", "Sequence{'1..2'}->appendAll(Sequence{null,null})");
		ocl.assertQueryResults(null, "OrderedSet{'1..2', null}", "OrderedSet{'1..2'}->appendAll(Sequence{null,null})");
		ocl.dispose();
	}

	@Test public void testCollectionAsBag() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Sequence{}->asBag()");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Bag{}->asBag()");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Set{}->asBag()");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "OrderedSet{}->asBag()");

		ocl.assertQueryResults(null, "Bag{1, 2.0, '3'}", "Sequence{1, 2.0, '3'}->asBag()");
		ocl.assertQueryResults(null, "Bag{1, 2.0, '3'}", "Bag{1, 2.0, '3'}->asBag()");
		ocl.assertQueryResults(null, "Bag{1, 2.0, '3'}", "Set{1, 2.0, '3'}->asBag()");
		ocl.assertQueryResults(null, "Bag{1, 2.0, '3'}", "OrderedSet{1, 2.0, '3'}->asBag()");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->asBag()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->asBag()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->asBag()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->asBag()");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->asBag()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->asBag()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->asBag()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->asBag()");
		ocl.dispose();
	}

	@Test public void testCollectionAsOrderedSet() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, ocl.getEmptyOrderedSetValue(), "Sequence{}->asOrderedSet()");
		ocl.assertQueryEquals(null, ocl.getEmptyOrderedSetValue(), "Bag{}->asOrderedSet()");
		ocl.assertQueryEquals(null, ocl.getEmptyOrderedSetValue(), "Set{}->asOrderedSet()");
		ocl.assertQueryEquals(null, ocl.getEmptyOrderedSetValue(), "OrderedSet{}->asOrderedSet()");

		ocl.assertQueryResults(null, "OrderedSet{1, 2.0, '3'}", "Sequence{1, 2.0, '3'}->asOrderedSet()");
		ocl.assertQueryResults(null, "OrderedSet{1, 2.0, '3'}", "OrderedSet{1, 2.0, '3'}->asOrderedSet()");

		/*
		 * Bag and Set are not ordered, simply check that the result is an
		 * OrderedSet and it contains all needed values.
		 */
		ocl.assertResultContainsAll(null, "OrderedSet{1, 2.0, '3'}", "Set{1, 2.0, '3'}->asOrderedSet()");
		ocl.assertResultContainsAll(null, "OrderedSet{1, 2.0, '3'}", "Bag{1, 2.0, '3'}->asOrderedSet()");

		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', 'c'}", "Sequence{'a', 'b', 'c', 'b'}->asOrderedSet()");
		ocl.assertResultContainsAll(null, "OrderedSet{'a', 'b', 'c'}", "Bag{'a', 'b', 'c', 'b'}->asOrderedSet()");
		ocl.assertResultContainsAll(null, "OrderedSet{'a', 'b', 'c'}", "Set{'a', 'b', 'c', 'b'}->asOrderedSet()");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', 'c'}", "OrderedSet{'a', 'b', 'c', 'b'}->asOrderedSet()");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->asOrderedSet()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->asOrderedSet()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->asOrderedSet()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->asOrderedSet()");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->asOrderedSet()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->asOrderedSet()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->asOrderedSet()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->asOrderedSet()");
		ocl.dispose();
	}

	@Test public void testCollectionAsSequence() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, ocl.getEmptySequenceValue(), "Sequence{}->asSequence()");
		ocl.assertQueryEquals(null, ocl.getEmptySequenceValue(), "Bag{}->asSequence()");
		ocl.assertQueryEquals(null, ocl.getEmptySequenceValue(), "Set{}->asSequence()");
		ocl.assertQueryEquals(null, ocl.getEmptySequenceValue(), "OrderedSet{}->asSequence()");

		ocl.assertQueryResults(null, "Sequence{1, 2.0, '3'}", "Sequence{1, 2.0, '3'}->asSequence()");
		ocl.assertQueryResults(null, "Sequence{1, 2.0, '3'}", "OrderedSet{1, 2.0, '3'}->asSequence()");
		//		ocl.assertQueryResults(null, "OrderedSet{1, 2.0, '3'}", "OrderedSet{1, 2.0, '3'}->asSequence()");

		/*
		 * Bag and Set are not ordered, simply check that the result is a
		 * Sequence and it contains all needed values.
		 */
		ocl.assertResultContainsAll(null, "Sequence{1, 2.0, '3'}", "Bag{1, 2.0, '3'}->asSequence()");
		ocl.assertResultContainsAll(null, "Sequence{1, 2.0, '3'}", "Set{1, 2.0, '3'}->asSequence()");
		// invalid collection
		//		ocl.assertQueryInvalid(null, "invalid->asSequence()");
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->asSequence()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->asSequence()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->asSequence()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->asSequence()");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->asSequence()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->asSequence()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->asSequence()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->asSequence()");
		ocl.dispose();
	}

	@Test public void testCollectionAsSet() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Sequence{}->asSet()");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Bag{}->asSet()");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{}->asSet()");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "OrderedSet{}->asSet()");

		ocl.assertQueryResults(null, "Set{1, 2.0, '3'}", "Sequence{1, 2.0, '3'}->asSet()");
		ocl.assertResultContainsAll(null, "Set{1, 2.0, '3'}", "Bag{1, 2.0, '3'}->asSet()");
		ocl.assertResultContainsAll(null, "Set{1, 2.0, '3'}", "Set{1, 2.0, '3'}->asSet()");
		ocl.assertQueryResults(null, "Set{1, 2.0, '3'}", "OrderedSet{1, 2.0, '3'}->asSet()");

		ocl.assertQueryResults(null, "Set{'a', 'b', 'c'}", "Sequence{'a', 'b', 'c', 'b'}->asSet()");
		ocl.assertQueryResults(null, "Set{'a', 'b', 'c'}", "Bag{'a', 'b', 'c', 'b'}->asSet()");
		ocl.assertQueryResults(null, "Set{'a', 'b', 'c'}", "Set{'a', 'b', 'c', 'b'}->asSet()");
		ocl.assertQueryResults(null, "Set{'a', 'b', 'c'}", "OrderedSet{'a', 'b', 'c', 'b'}->asSet()");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->asSet()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->asSet()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->asSet()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->asSet()");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->asSet()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->asSet()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->asSet()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->asSet()");
		ocl.dispose();
	}

	@Test public void testCollectionAt() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "a", "Sequence{'a', 'b'}->at(1)");
		ocl.assertQueryEquals(null, "b", "OrderedSet{'a', 'b'}->at(2)");
		ocl.assertQueryEquals(null, -3, "Sequence{-3..-1}->at(1)");
		ocl.assertQueryInvalid(null, "Sequence{-1..-3}->at(3)");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->at(1)");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->at(1)");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', invalid}->at(1)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', invalid}->at(1)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->at(1)");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->at(1)");
		// null collection element
		ocl.assertQueryNull(null, "Sequence{null, 'a'}->at(1)");
		ocl.assertQueryNull(null, "OrderedSet{null, 'a'}->at(1)");
		// out of bounds
		ocl.assertQueryInvalid(null, "Sequence{'a'}->at(0)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a'}->at(0)");

		ocl.assertQueryInvalid(null, "Sequence{'a'}->at(2)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a'}->at(2)");

		ocl.assertQueryInvalid(null, "Sequence{-1..-3}->at(0)");
		ocl.assertQueryInvalid(null, "Sequence{-1..-3}->at(4)");
		ocl.dispose();
	}

	@Test public void testCollectionCount() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 1, "Sequence{3, 'test', 4.0, 4, 4.0, 'test'}->count(3)");
		ocl.assertQueryEquals(null, 3, "Sequence{3, 'test', 4.0, 4, 4.0, 'test'}->count(4)");
		ocl.assertQueryEquals(null, 3, "Sequence{3, 'test', 4.0, 4, 4.0, 'test'}->count(-(-4))");
		ocl.assertQueryEquals(null, 3, "Sequence{3, 'test', 4.0, 4, 4.0, 'test'}->count(4.0)");
		ocl.assertQueryEquals(null, 2, "Sequence{3, 'test', 4.0, 4, 4.0, 'test'}->count('test')");
		ocl.assertQueryEquals(null, 1, "Bag{3, 'test', 4.0, 4, 4.0, 'test'}->count(3)");
		ocl.assertQueryEquals(null, 3, "Bag{3, 'test', 4.0, 4, 4.0, 'test'}->count(4)");
		ocl.assertQueryEquals(null, 3, "Bag{3, 'test', 4.0, 4, 4.0, 'test'}->count(-(-4))");
		ocl.assertQueryEquals(null, 3, "Bag{3, 'test', 4.0, 4, 4.0, 'test'}->count(4.0)");
		ocl.assertQueryEquals(null, 2, "Bag{3, 'test', 4.0, 4, 4.0, 'test'}->count('test')");
		ocl.assertQueryEquals(null, 1, "Set{3, 'test', 4.0, 4, 4.0, 'test'}->count(3)");
		ocl.assertQueryEquals(null, 1, "Set{3, 'test', 4.0, 4, 4.0, 'test'}->count(4)");
		ocl.assertQueryEquals(null, 1, "Set{3, 'test', 4.0, 4, 4.0, 'test'}->count(-(-4))");
		ocl.assertQueryEquals(null, 1, "Set{3, 'test', 4.0, 4, 4.0, 'test'}->count(4.0)");
		ocl.assertQueryEquals(null, 1, "Set{3, 'test', 4.0, 4, 4.0, 'test'}->count('test')");
		ocl.assertQueryEquals(null, 1, "OrderedSet{3, 'test', 4.0, 4, 4.0, 'test'}->count(3)");
		ocl.assertQueryEquals(null, 1, "OrderedSet{3, 'test', 4.0, 4, 4.0, 'test'}->count(4)");
		ocl.assertQueryEquals(null, 1, "OrderedSet{3, 'test', 4.0, 4, 4.0, 'test'}->count(-(-4))");
		ocl.assertQueryEquals(null, 1, "OrderedSet{3, 'test', 4.0, 4, 4.0, 'test'}->count(4.0)");
		ocl.assertQueryEquals(null, 1, "OrderedSet{3, 'test', 4.0, 4, 4.0, 'test'}->count('test')");
		ocl.assertQueryEquals(null, 1, "Sequence{-4..-4}->count(-4)");
		ocl.assertQueryEquals(null, 1, "Sequence{-4..-1}->count(-4)");
		ocl.assertQueryEquals(null, 0, "Sequence{-1..-4}->count(-4)");
		ocl.assertQueryEquals(null, 1, "Sequence{-4..-1}->count(-1)");
		ocl.assertQueryEquals(null, 0, "Sequence{-1..-4}->count(-1)");

		ocl.assertQueryEquals(null, 0, "Sequence{3, 'test', 4.0, 4, 4.0, 'test'}->count(0)");
		ocl.assertQueryEquals(null, 0, "Bag{3, 'test', 4.0, 4, 4.0, 'test'}->count(0)");
		ocl.assertQueryEquals(null, 0, "Set{3, 'test', 4.0, 4, 4.0, 'test'}->count(0)");
		ocl.assertQueryEquals(null, 0, "OrderedSet{3, 'test', 4.0, 4, 4.0, 'test'}->count(0)");
		ocl.assertQueryEquals(null, 0, "Sequence{-4..-1}->count(-5)");
		ocl.assertQueryEquals(null, 0, "Sequence{-1..-4}->count(-5)");
		ocl.assertQueryEquals(null, 0, "Sequence{-4..-1}->count(0)");
		ocl.assertQueryEquals(null, 0, "Sequence{-1..-4}->count(0)");
		ocl.assertQueryEquals(null, 0, "Sequence{-1..-4}->count('x')");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->count(0)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->count(0)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->count(0)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = invalid in os->count(0)");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{3, 'test', 4.0, 4, 4.0, 'test'}->count(invalid)");
		ocl.assertQueryInvalid(null, "Bag{3, 'test', 4.0, 4, 4.0, 'test'}->count(invalid)");
		ocl.assertQueryInvalid(null, "Set{3, 'test', 4.0, 4, 4.0, 'test'}->count(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{3, 'test', 4.0, 4, 4.0, 'test'}->count(invalid)");

		ocl.assertQueryInvalid(null, "Sequence{3, invalid, 4.0, invalid, 'test'}->count(3)");
		ocl.assertQueryInvalid(null, "Bag{3, invalid, 4.0, invalid, 'test'}->count(3)");
		ocl.assertQueryInvalid(null, "Set{3, invalid, 4.0, invalid, 'test'}->count(3)");
		ocl.assertQueryInvalid(null, "OrderedSet{3, invalid, 4.0, invalid, 'test'}->count(3)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->count(0)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->count(0)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->count(0)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = null in os->count(0)");
		// null collection element
		ocl.assertQueryEquals(null, 0, "Sequence{3, 'test', 4.0, 4, 4.0, 'test'}->count(null)");
		ocl.assertQueryEquals(null, 0, "Bag{3, 'test', 4.0, 4, 4.0, 'test'}->count(null)");
		ocl.assertQueryEquals(null, 0, "Set{3, 'test', 4.0, 4, 4.0, 'test'}->count(null)");
		ocl.assertQueryEquals(null, 0, "OrderedSet{3, 'test', 4.0, 4, 4.0, 'test'}->count(null)");

		ocl.assertQueryEquals(null, 2, "Sequence{3, null, 4.0, null, 'test'}->count(null)");
		ocl.assertQueryEquals(null, 2, "Bag{3, null, 4.0, null, 'test'}->count(null)");
		ocl.assertQueryEquals(null, 1, "Set{3, null, 4.0, null, 'test'}->count(null)");
		ocl.assertQueryEquals(null, 1, "OrderedSet{3, null, 4.0, null, 'test'}->count(null)");
		ocl.dispose();
	}

	@Test public void testCollectionElementType() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		org.eclipse.ocl.pivot.Class integerType = standardLibrary.getIntegerType();
		org.eclipse.ocl.pivot.Class oclAnyType = standardLibrary.getOclAnyType();
		org.eclipse.ocl.pivot.Class stringType = standardLibrary.getStringType();
//
		ocl.assertSemanticErrorQuery(null, "Set{'1'}->elementType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Set(String)", "elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}->_'Collection'::elementType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Collection", "elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}->SetType::elementType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "CollectionType::elementType");
//
		ocl.assertSemanticErrorQuery(null, "Set{'1'}.elementType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "String", "elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}.SetType::elementType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "CollectionType::elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}._'Collection'::elementType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Collection", "elementType");
//
		ocl.assertQueryEquals(null, stringType, "Set{'1'}->oclType().elementType");
		ocl.assertQueryEquals(null, stringType, "Set{'1'}->oclType().SetType::elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}->oclType()._'Collection'::elementType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Collection", "elementType");
//
		ocl.assertSemanticErrorQuery(null, "Set{'1'}.oclType()->elementType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Bag(PrimitiveType)", "elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}.oclType()->SetType::elementType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "CollectionType::elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}.oclType()->_'Collection'::elementType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Collection", "elementType");
//
		ocl.assertSemanticErrorQuery(null, "Set{'1'}.oclType().elementType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "PrimitiveType", "elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}.oclType().SetType::elementType", EssentialOCLCS2ASMessages.PropertyCallExp_IncompatibleProperty, "CollectionType::elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}.oclType()._'Collection'::elementType", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Collection", "elementType");
//
		ocl.assertQueryEquals(null, oclAnyType, "Set{1, 2.0, '3'}->oclType().elementType");
		ocl.assertQueryEquals(null, integerType, "Set{1, 2, 3}->oclType().elementType");
		ocl.assertQueryEquals(null, integerType, "Set{1, 2, 3}->oclAsType(Collection(Real))->oclType().elementType");
		ocl.assertSemanticErrorQuery(null, "Set{'1'}->SetType::size()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "SetType", "size");
	}

	@Test public void testCollectionEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Bag{1} = 1");
		ocl.assertQueryFalse(null, "OrderedSet{1} = 1");
		ocl.assertQueryFalse(null, "Sequence{1} = 1");
		ocl.assertQueryFalse(null, "Set{1} = 1");

		ocl.assertQueryFalse(null, "1 = Bag{1}");
		ocl.assertQueryFalse(null, "1 = OrderedSet{1}");
		ocl.assertQueryFalse(null, "1 = Sequence{1}");
		ocl.assertQueryFalse(null, "1 = Set{1}");
		ocl.assertQueryFalse(null, "Set{1} = Set{Set{1}}");

		ocl.assertQueryFalse(null, "Bag{1.01} = Bag{1}");
		ocl.assertQueryFalse(null, "OrderedSet{1.01} = OrderedSet{1}");
		ocl.assertQueryFalse(null, "Sequence{1.01} = Sequence{1}");
		ocl.assertQueryFalse(null, "Set{1.01} = Set{1}");
		ocl.assertQueryFalse(null, "Set{Set{1.01}} = Set{Set{1}}");

		ocl.assertQueryTrue(null, "Bag{1.0} = Bag{1}");
		ocl.assertQueryTrue(null, "OrderedSet{1.0} = OrderedSet{1}");
		ocl.assertQueryTrue(null, "Sequence{1.0} = Sequence{1}");
		ocl.assertQueryTrue(null, "Set{1.0} = Set{1}");
		ocl.assertQueryTrue(null, "Set{Set{1.0}} = Set{Set{1}}");

		ocl.assertQueryTrue(null, "Sequence{1,2} = Sequence{1,2}");
		ocl.assertQueryTrue(null, "Sequence{1..2} = Sequence{1..2}");
		ocl.assertQueryTrue(null, "Sequence{1..2} = Sequence{1,2}");
		ocl.assertQueryTrue(null, "Sequence{1,2} = Sequence{1..2}");
		ocl.assertQueryFalse(null, "Sequence{1..2} = Sequence{2,1}");
		ocl.assertQueryFalse(null, "Sequence{1..2} = Sequence{1,2,1}");
		ocl.assertQueryTrue(null, "OrderedSet{1,2} = OrderedSet{1,2}");
		ocl.assertQueryTrue(null, "OrderedSet{1..2} = OrderedSet{1..2}");
		ocl.assertQueryTrue(null, "OrderedSet{1..2} = OrderedSet{1,2}");
		ocl.assertQueryTrue(null, "OrderedSet{1,2} = OrderedSet{1..2}");
		ocl.assertQueryFalse(null, "OrderedSet{1..2} = OrderedSet{2,1}");
		ocl.assertQueryTrue(null, "OrderedSet{1..2} = OrderedSet{1,2,1}");
		ocl.assertQueryFalse(null, "Sequence{1..2} = OrderedSet{1,2}");
		// null collection element

		ocl.assertQueryTrue(null, "Bag{null} = Bag{null}");
		ocl.assertQueryTrue(null, "OrderedSet{null} = OrderedSet{null}");
		ocl.assertQueryTrue(null, "Sequence{null} = Sequence{null}");
		ocl.assertQueryTrue(null, "Set{null} = Set{null}");
		ocl.assertQueryTrue(null, "Set{Set{null}} = Set{Set{null}}");
		ocl.dispose();
	}

	@Test public void testCollectionEqualOrderedXOrdered() {
		TestOCL ocl = createOCL();
		// same order, same quantities
		ocl.assertQueryTrue(null, "Sequence{4, 5, 'test'} = Sequence{4, 5, 'test'}");
		ocl.assertQueryTrue(null, "Sequence{4, 5, 'test', 5} = Sequence{4, 5, 'test', 5}");
		ocl.assertQueryTrue(null, "OrderedSet{4, 5, 'test', 5} = OrderedSet{4, 5, 'test'}");
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test'} = OrderedSet{4, 5, 'test', 5}");
		ocl.assertQueryFalse(null, "OrderedSet{4, 5, 'test', 5} = Sequence{4, 5, 'test'}");

		// distinct order, same quantities
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test'} = Sequence{4, 'test', 5}");
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test', 5} = Sequence{5, 4, 'test', 5}");
		ocl.assertQueryFalse(null, "OrderedSet{4, 5, 'test', 5} = OrderedSet{4, 'test', 5}");
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test'} = OrderedSet{5, 4, 'test', 5}");
		ocl.assertQueryFalse(null, "OrderedSet{4, 5, 'test', 5} = Sequence{5, 4, 'test'}");

		// distinct quantities
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test', 5} = Sequence{4, 5, 'test'}");
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test', 5} = OrderedSet{4, 5, 'test', 5}");
		ocl.assertQueryFalse(null, "OrderedSet{4, 5, 'test', 5} = Sequence{4, 5, 'test', 5}");
		ocl.dispose();
	}

	@Test public void testCollectionEqualOrderedXUnordered() {
		TestOCL ocl = createOCL();
		// same quantities
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test'} = Set{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test', 4} = Bag{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "OrderedSet{4, 5, 'test', 4} = Set{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "OrderedSet{4, 5, 'test', 4} = Bag{4, 'test', 5}");

		// distinct quantities
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test', 4} = Set{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test'} = Bag{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "OrderedSet{4, 5, 'test', 4} = Bag{4, 'test', 5, 4}");
		ocl.dispose();
	}

	@Test public void testCollectionEqualUnorderedXUnordered() {
		TestOCL ocl = createOCL();
		// same quantities
		ocl.assertQueryFalse(null, "Bag{4, 5, 'test'} = Set{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "Bag{4, 5, 'test', 4} = Bag{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "Set{4, 5, 'test', 4} = Set{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "Set{4, 5, 'test', 4} = Bag{4, 'test', 5}");

		// distinct quantities
		ocl.assertQueryFalse(null, "Bag{4, 5, 'test', 4} = Set{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "Bag{4, 5, 'test'} = Bag{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "Set{4, 5, 'test', 4} = Bag{4, 'test', 5, 4}");
		ocl.dispose();
	}

	@Test public void testCollectionEqualInvalid() {
		TestOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s = Sequence{5}");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in Bag{5} = b");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s = Set{5}");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in OrderedSet{5} = o");

		ocl.assertQueryInvalid(null, "let s1 : Sequence(Integer) = invalid, s2 : Sequence(Integer) = invalid in s1 = s2");
		ocl.assertQueryInvalid(null, "let b1 : Bag(Integer) = invalid, b2 : Bag(Integer) = invalid in b1 = b2");
		ocl.assertQueryInvalid(null, "let s1 : Set(Integer) = invalid, s2 : Set(Integer) = invalid in s1 = s2");
		ocl.assertQueryInvalid(null, "let o1 : OrderedSet(Integer) = invalid, o2 : OrderedSet(Integer) = invalid in o1 = o2");
		ocl.dispose();
	}

	@Test public void testCollectionEqualNull() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Bag{} = null");
		ocl.assertQueryFalse(null, "OrderedSet{} = null");
		ocl.assertQueryFalse(null, "Sequence{} = null");
		ocl.assertQueryFalse(null, "Set{} = null");

		ocl.assertQueryFalse(null, "null = Bag{}");
		ocl.assertQueryFalse(null, "null = OrderedSet{}");
		ocl.assertQueryFalse(null, "null = Sequence{}");
		ocl.assertQueryFalse(null, "null = Set{}");

		ocl.assertQueryFalse(null, "let b : Bag(Integer) = null in b = Bag{}");
		ocl.assertQueryFalse(null, "let b : Bag(Integer) = null, s : Set(Integer) = Set{} in b = s");
		ocl.assertQueryFalse(null, "let s : Sequence(Integer) = null in s = Sequence{5}");
		ocl.assertQueryFalse(null, "let b : Bag(Integer) = null in Bag{5} = b");
		ocl.assertQueryFalse(null, "let s : Set(Integer) = null in s = Set{5}");
		ocl.assertQueryFalse(null, "let o : OrderedSet(Integer) = null in OrderedSet{5} = o");

		ocl.assertQueryTrue(null, "let s1 : Sequence(Integer) = null, s2 : Sequence(Integer) = null in s1 = s2");
		ocl.assertQueryTrue(null, "let b1 : Bag(Integer) = null, b2 : Bag(Integer) = null in b1 = b2");
		ocl.assertQueryTrue(null, "let s1 : Set(Integer) = null, s2 : Set(Integer) = null in s1 = s2");
		ocl.assertQueryTrue(null, "let o1 : OrderedSet(Integer) = null, o2 : OrderedSet(Integer) = null in o1 = o2");
		ocl.dispose();
	}

	@Test public void testCollectionExcludes() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->excludes(3)");
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->excludes(3.0)");
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->excludes(4)");
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->excludes('test')");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->excludes(3)");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->excludes(3.0)");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->excludes(4)");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->excludes('test')");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->excludes(3)");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->excludes(3.0)");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->excludes(4)");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->excludes('test')");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->excludes(3)");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->excludes(3.0)");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->excludes(4)");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->excludes('test')");

		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->excludes(3.5)");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->excludes(3.5)");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->excludes(3.5)");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->excludes(3.5)");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->excludes(0)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->excludes(0)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->excludes(0)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = invalid in os->excludes(0)");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{3, 4.0, 'test'}->excludes(invalid)");
		ocl.assertQueryInvalid(null, "Bag{3, 4.0, 'test'}->excludes(invalid)");
		ocl.assertQueryInvalid(null, "Set{3, 4.0, 'test'}->excludes(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{3, 4.0, 'test'}->excludes(invalid)");

		ocl.assertQueryInvalid(null, "Sequence{0, invalid}->excludes(0)");
		ocl.assertQueryInvalid(null, "Bag{0, invalid}->excludes(0)");
		ocl.assertQueryInvalid(null, "Set{0, invalid}->excludes(0)");
		ocl.assertQueryInvalid(null, "OrderedSet{0, invalid}->excludes(0)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->excludes(0)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->excludes(0)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->excludes(0)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = null in os->excludes(0)");
		// null collection element
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, null, 'test'}->excludes(null)");
		ocl.assertQueryFalse(null, "Sequence{null}->excludes(null)");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, null, 'test'}->excludes(null)");
		ocl.assertQueryFalse(null, "Bag{null}->excludes(null)");
		ocl.assertQueryFalse(null, "Set{3, 4.0, null, 'test'}->excludes(null)");
		ocl.assertQueryFalse(null, "Set{null}->excludes(null)");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, null, 'test'}->excludes(null)");
		ocl.assertQueryFalse(null, "OrderedSet{null}->excludes(null)");

		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->excludes(null)");
		ocl.assertQueryTrue(null, "Sequence{}->excludes(null)");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->excludes(null)");
		ocl.assertQueryTrue(null, "Bag{}->excludes(null)");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->excludes(null)");
		ocl.assertQueryTrue(null, "Set{}->excludes(null)");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->excludes(null)");
		ocl.assertQueryTrue(null, "OrderedSet{}->excludes(null)");
		ocl.dispose();
	}

	@Test public void testCollectionExcludesAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->excludesAll(Sequence{3, 'test'})");
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->excludesAll(Bag{3, 'test'})");
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->excludesAll(Set{3, 'test'})");
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->excludesAll(OrderedSet{3, 'test'})");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->excludesAll(Sequence{3, 'test'})");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->excludesAll(Bag{3, 'test'})");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->excludesAll(Set{3, 'test'})");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->excludesAll(OrderedSet{3, 'test'})");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->excludesAll(Sequence{3, 'test'})");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->excludesAll(Bag{3, 'test'})");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->excludesAll(Set{3, 'test'})");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->excludesAll(OrderedSet{3, 'test'})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->excludesAll(Sequence{3, 'test'})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->excludesAll(Bag{3, 'test'})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->excludesAll(Set{3, 'test'})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->excludesAll(OrderedSet{3, 'test'})");

		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->excludesAll(Sequence{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->excludesAll(Bag{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->excludesAll(Set{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->excludesAll(OrderedSet{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->excludesAll(Sequence{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->excludesAll(Bag{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->excludesAll(Set{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->excludesAll(OrderedSet{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->excludesAll(Sequence{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->excludesAll(Bag{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->excludesAll(Set{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->excludesAll(OrderedSet{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->excludesAll(Sequence{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->excludesAll(Bag{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->excludesAll(Set{3.5, 'TEST'})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->excludesAll(OrderedSet{3.5, 'TEST'})");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->excludesAll(Sequence{0})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->excludesAll(Bag{0})");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->excludesAll(Set{0})");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = invalid in os->excludesAll(OrderedSet{0})");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in Sequence{0}->excludesAll(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in Bag{0}->excludesAll(b)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in Set{0}->excludesAll(s)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = invalid in OrderedSet{0}->excludesAll(os)");
		// invalid collection element
		// Collections can't contain the invalid value
		ocl.assertQueryInvalid(null, "let b : Bag(String) = invalid in b->excludesAll(OrderedSet{'test'})");
		ocl.assertQueryInvalid(null, "Sequence{3, 4, invalid, 'test'}->excludesAll(OrderedSet{'test'})");
		ocl.assertQueryInvalid(null, "Bag{3, 4, invalid, 'test'}->excludesAll(Set{'test'})");
		ocl.assertQueryInvalid(null, "Set{3, 4, invalid, 'test'}->excludesAll(Bag{'test'})");
		ocl.assertQueryInvalid(null, "OrderedSet{3, 4, invalid, 'test'}->excludesAll(Sequence{'test'})");

		ocl.assertQueryInvalid(null, "Sequence{3, 4, 'test'}->excludesAll(OrderedSet{'test', invalid})");
		ocl.assertQueryInvalid(null, "Bag{3, 4, 'test'}->excludesAll(Set{'test', invalid})");
		ocl.assertQueryInvalid(null, "Set{3, 4, 'test'}->excludesAll(Bag{'test', invalid})");
		ocl.assertQueryInvalid(null, "OrderedSet{3, 4, 'test'}->excludesAll(Sequence{'test', invalid})");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->excludesAll(Sequence{0})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->excludesAll(Bag{0})");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->excludesAll(Set{0})");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = null in os->excludesAll(OrderedSet{0})");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in Sequence{0}->excludesAll(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in Bag{0}->excludesAll(b)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in Set{0}->excludesAll(s)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = null in OrderedSet{0}->excludesAll(os)");
		// null collection element
		ocl.assertQueryFalse(null, "Sequence{3, 4, null, 'test'}->excludesAll(OrderedSet{'TEST', null})");
		ocl.assertQueryFalse(null, "Bag{3, 4, null, 'test'}->excludesAll(Set{'TEST', null})");
		ocl.assertQueryFalse(null, "Set{3, 4, null, 'test'}->excludesAll(Bag{'TEST', null})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4, null, 'test'}->excludesAll(Sequence{'TEST', null})");

		ocl.assertQueryTrue(null, "Sequence{3, 4, 'test'}->excludesAll(OrderedSet{'TEST', null})");
		ocl.assertQueryTrue(null, "Bag{3, 4, 'test'}->excludesAll(Set{'TEST', null})");
		ocl.assertQueryTrue(null, "Set{3, 4, 'test'}->excludesAll(Bag{'TEST', null})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4, 'test'}->excludesAll(Sequence{'TEST', null})");
		ocl.dispose();
	}

	@Test public void testCollectionExcluding() {
		TestOCL ocl = createOCL();
		/*
		 * FIXME OMG-issue to add OrderedSet::excluding
		 * since it's defined in oclstdlib.ecore. However the defined
		 * "OrderedSet::excluding(T) : Set" should be
		 * "OrderedSet::excluding(T) : OrderedSet"
		 */
		ocl.assertQueryResults(null, "Sequence{'a', 'c'}", "Sequence{'b', 'a', 'b', 'c'}->excluding('b')");
		ocl.assertQueryResults(null, "Bag{'c', 'a'}", "Bag{'b', 'a', 'b', 'c'}->excluding('b')");
		ocl.assertQueryResults(null, "Set{'c', 'a'}", "Set{'a', 'b', 'c'}->excluding('b')");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'c'}", "OrderedSet{'a', 'b', 'c'}->excluding('b')");
		ocl.assertQueryResults(null, "Sequence{1,3,4}", "Sequence{1..4}->excluding(2)");
		ocl.assertQueryResults(null, "OrderedSet{1,3,4}", "OrderedSet{1..4}->excluding(2)");
		ocl.assertQueryResults(null, "Sequence{1..3,6..9}", "Sequence{1..4,6,7..9}->excluding(4)");
		ocl.assertQueryResults(null, "OrderedSet{1..3,6..9}", "OrderedSet{1..4,6,7..9}->excluding(4)");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->excluding('a')");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = invalid in b->excluding('a')");
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in s->excluding('a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->excluding('a')");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b'}->excluding(invalid)");
		ocl.assertQueryInvalid(null, "Bag{'a', 'b'}->excluding(invalid)");
		ocl.assertQueryInvalid(null, "Set{'a', 'b'}->excluding(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b'}->excluding(invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->excluding('a')");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = null in b->excluding('a')");
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in s->excluding('a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->excluding('a')");
		// invalid collection element
		ocl.assertQueryResults(null, "Sequence{'a', 'b'}", "Sequence{null, 'a', null, 'b'}->excluding(null)");
		ocl.assertQueryResults(null, "Bag{'b', 'a'}", "Bag{null, 'a', null, 'b'}->excluding(null)");
		ocl.assertQueryResults(null, "Set{'b', 'a'}", "Set{'a', null, 'b'}->excluding(null)");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b'}", "OrderedSet{'a', null, 'b'}->excluding(null)");
		ocl.dispose();
	}

	@Test public void testCollectionExcludingAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Sequence{'a', 'c'}", "Sequence{'b', 'a', 'd', 'd', 'b', 'c', 'd'}->excludingAll(Sequence{'d', 'd', 'b', 'e'})");
		ocl.assertQueryResults(null, "Bag{'c', 'a'}", "Bag{'b', 'a', 'd', 'd', 'b', 'c', 'd'}->excludingAll(Bag{'b', 'd', 'd', 'e'})");
		ocl.assertQueryResults(null, "Set{'c', 'a'}", "Set{'a', 'b', 'c', 'd'}->excludingAll(Set{'b', 'd', 'e'})");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'c'}", "OrderedSet{'a', 'b', 'c', 'd'}->excludingAll(OrderedSet{'b', 'd', 'e'})");
		ocl.assertQueryResults(null, "Sequence{1,3,7..8,10}", "Sequence{1..10}->excludingAll(Sequence{2,4..6,9})");
		ocl.assertQueryResults(null, "OrderedSet{1,3,7..8,10}", "OrderedSet{1..10}->excludingAll(OrderedSet{2,4..6,9})");
		ocl.assertQueryResults(null, "Sequence{1..2,4,8..9}", "Sequence{1..4,6,7..9}->excludingAll(Sequence{3,5..7})");
		ocl.assertQueryResults(null, "OrderedSet{1..2,4,8..9}", "OrderedSet{1..4,6,7..9}->excludingAll(OrderedSet{3,5..7})");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->excludingAll(Sequence{'a'})");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = invalid in b->excludingAll(Bag{'a'})");
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in s->excludingAll(Set{'a'})");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->excludingAll(OrderedSet{'a'})");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b'}->excludingAll(invalid)");
		ocl.assertQueryInvalid(null, "Bag{'a', 'b'}->excludingAll(invalid)");
		ocl.assertQueryInvalid(null, "Set{'a', 'b'}->excludingAll(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b'}->excludingAll(invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->excludingAll(Sequence{'a'})");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = null in b->excludingAll(Bag{'a'})");
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in s->excludingAll(Set{'a'})");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->excludingAll(OrderedSet{'a'})");
		// invalid collection element
		ocl.assertQueryResults(null, "Sequence{'a', 'b'}", "Sequence{null, 'a', null, 'b', 'c'}->excludingAll(Sequence{null, 'c'})");
		ocl.assertQueryResults(null, "Bag{'b', 'a'}", "Bag{null, 'a', null, 'b'}->excludingAll(Bag{null})");
		ocl.assertQueryResults(null, "Set{'b', 'a'}", "Set{'a', null, 'b'}->excludingAll(Set{null})");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b'}", "OrderedSet{'a', null, 'b'}->excludingAll(OrderedSet{null})");
		ocl.dispose();
	}

	@Test public void testCollectionFirst() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 1, "Sequence{1, 2.0, '3'}->first()");
		ocl.assertQueryEquals(null, 1, "OrderedSet{1, 2.0, '3'}->first()");
		// empty
		ocl.assertQueryInvalid(null, "OrderedSet{}->first()");
		ocl.assertQueryInvalid(null, "Sequence{}->first()");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->first()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->first()");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{1, invalid}->first()");
		ocl.assertQueryInvalid(null, "OrderedSet{1, invalid}->first()");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->first()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->first()");
		// null collection element
		ocl.assertQueryNull(null, "Sequence{null}->first()");
		ocl.assertQueryNull(null, "OrderedSet{null}->first()");
		ocl.dispose();
	}

	@Test public void testCollectionFlatten() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, ocl.getEmptySequenceValue(), "Sequence{}->flatten()");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Bag{}->flatten()");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{}->flatten()");
		ocl.assertQueryEquals(null, ocl.getEmptyOrderedSetValue(), "OrderedSet{}->flatten()");

		String expression = "Sequence{Set{1,2,3}, Sequence{2.0, 3.0}, Bag{'test'}}->flatten()";
		String expectedResultExpression = "Sequence{1, 2, 3, 2.0, 3.0, 'test'}";
		/*
		 * as the Sequence is ordered and we cannot know in which order the
		 * result of the Set flattening were inserted, simply check that the
		 * result is a Sequence and contains all elements.
		 */
		ocl.assertResultContainsAll(null, expectedResultExpression, expression);

		expression = "Bag{Set{Bag{'test', 2, 3.0}}, Sequence{OrderedSet{2.0, 3, 1}}}->flatten()";
		expectedResultExpression = "Bag{1, 2, 3, 2.0, 3.0, 'test'}";
		ocl.assertQueryResults(null, expectedResultExpression, expression);

		expression = "Set{OrderedSet{Set{3.0, 'test'}, Sequence{2.0, 2}, Bag{1, 3}}}->flatten()";
		expectedResultExpression = "Set{1, 2.0, 3.0, 'test'}";
		ocl.assertResultContainsAll(null, expectedResultExpression, expression);

		expression = "OrderedSet{Set{Set{3.0, 'test'}, Sequence{2.0, 2}, Bag{1, 3}}}->flatten()";
		expectedResultExpression = "OrderedSet{1, 2, 2.0, 3.0, 'test'}";
		ocl.assertResultContainsAll(null, expectedResultExpression, expression);

		expression = "Set{Sequence{Sequence{3.0, 'test'}}, Sequence{Sequence{2.0, 2}, Sequence{1, 3}}}->flatten()";
		expectedResultExpression = "Set{1, 2, 3, 2.0, 3.0, 'test'}";
		ocl.assertResultContainsAll(null, expectedResultExpression, expression);
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->flatten()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->flatten()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->flatten()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->flatten()");
		// non collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->flatten()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->flatten()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->flatten()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->flatten()");
		// pseudo collection
		ocl.assertQueryResults(null, "Set{1}", "1->flatten()");
		ocl.assertQueryResults(null, "Set{1}", "let s : Sequence(Integer) = null in 1->flatten()");
		ocl.assertQueryResults(null, "Set{1}", "let b : Bag(Integer) = null in 1->flatten()");
		ocl.assertQueryResults(null, "Set{1}", "let s : Set(Integer) = null in 1->flatten()");
		ocl.assertQueryResults(null, "Set{1}", "let o : OrderedSet(Integer) = null in 1->flatten()");
	}

	@Test public void testCollectionIncludes() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->includes(3)");
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->includes(3.0)");
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->includes(4)");
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->includes('test')");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->includes(3)");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->includes(3.0)");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->includes(4)");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->includes('test')");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->includes(3)");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->includes(3.0)");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->includes(4)");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->includes('test')");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->includes(3)");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->includes(3.0)");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->includes(4)");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->includes('test')");

		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->includes(3.5)");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->includes(3.5)");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->includes(3.5)");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->includes(3.5)");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->includes(0)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->includes(0)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->includes(0)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = invalid in os->includes(0)");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{3, 4.0, 'test'}->includes(invalid)");
		ocl.assertQueryInvalid(null, "Bag{3, 4.0, 'test'}->includes(invalid)");
		ocl.assertQueryInvalid(null, "Set{3, 4.0, 'test'}->includes(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{3, 4.0, 'test'}->includes(invalid)");

		ocl.assertQueryInvalid(null, "Sequence{0, invalid}->includes(0)");
		ocl.assertQueryInvalid(null, "Bag{0, invalid}->includes(0)");
		ocl.assertQueryInvalid(null, "Set{0, invalid}->includes(0)");
		ocl.assertQueryInvalid(null, "OrderedSet{0, invalid}->includes(0)");
		// invalid null
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->includes(0)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->includes(0)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->includes(0)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = null in os->includes(0)");
		// invalid null element
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, null, 'test'}->includes(null)");
		ocl.assertQueryTrue(null, "Sequence{null}->includes(null)");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, null, 'test'}->includes(null)");
		ocl.assertQueryTrue(null, "Bag{null}->includes(null)");
		ocl.assertQueryTrue(null, "Set{3, 4.0, null, 'test'}->includes(null)");
		ocl.assertQueryTrue(null, "Set{null}->includes(null)");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, null, 'test'}->includes(null)");
		ocl.assertQueryTrue(null, "OrderedSet{null}->includes(null)");

		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->includes(null)");
		ocl.assertQueryFalse(null, "Sequence{}->includes(null)");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->includes(null)");
		ocl.assertQueryFalse(null, "Bag{}->includes(null)");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->includes(null)");
		ocl.assertQueryFalse(null, "Set{}->includes(null)");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->includes(null)");
		ocl.assertQueryFalse(null, "OrderedSet{}->includes(null)");
		ocl.dispose();
	}

	@Test public void testCollectionIncludesAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->includesAll(Sequence{3, 'test'})");
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->includesAll(Bag{3, 'test'})");
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->includesAll(Set{3, 'test'})");
		ocl.assertQueryTrue(null, "Sequence{3, 4.0, 'test'}->includesAll(OrderedSet{3, 'test'})");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->includesAll(Sequence{3, 'test'})");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->includesAll(Bag{3, 'test'})");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->includesAll(Set{3, 'test'})");
		ocl.assertQueryTrue(null, "Bag{3, 4.0, 'test'}->includesAll(OrderedSet{3, 'test'})");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->includesAll(Sequence{3, 'test'})");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->includesAll(Bag{3, 'test'})");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->includesAll(Set{3, 'test'})");
		ocl.assertQueryTrue(null, "Set{3, 4.0, 'test'}->includesAll(OrderedSet{3, 'test'})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->includesAll(Sequence{3, 'test'})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->includesAll(Bag{3, 'test'})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->includesAll(Set{3, 'test'})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4.0, 'test'}->includesAll(OrderedSet{3, 'test'})");

		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->includesAll(Sequence{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->includesAll(Bag{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->includesAll(Set{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Sequence{3, 4.0, 'test'}->includesAll(OrderedSet{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->includesAll(Sequence{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->includesAll(Bag{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->includesAll(Set{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Bag{3, 4.0, 'test'}->includesAll(OrderedSet{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->includesAll(Sequence{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->includesAll(Bag{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->includesAll(Set{3.5, 'test'})");
		ocl.assertQueryFalse(null, "Set{3, 4.0, 'test'}->includesAll(OrderedSet{3.5, 'test'})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->includesAll(Sequence{3.5, 'test'})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->includesAll(Bag{3.5, 'test'})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->includesAll(Set{3.5, 'test'})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4.0, 'test'}->includesAll(OrderedSet{3.5, 'test'})");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->includesAll(Sequence{0})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->includesAll(Bag{0})");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->includesAll(Set{0})");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = invalid in os->includesAll(OrderedSet{0})");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in Sequence{0}->includesAll(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in Bag{0}->includesAll(b)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in Set{0}->includesAll(s)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = invalid in OrderedSet{0}->includesAll(os)");
		// invalid collection element
		// Collections can't contain the invalid value
		ocl.assertQueryInvalid(null, "Sequence{3, 4, invalid, 'test'}->includesAll(OrderedSet{'test'})");
		ocl.assertQueryInvalid(null, "Bag{3, 4, invalid, 'test'}->includesAll(Set{'test'})");
		ocl.assertQueryInvalid(null, "Set{3, 4, invalid, 'test'}->includesAll(Bag{'test'})");
		ocl.assertQueryInvalid(null, "OrderedSet{3, 4, invalid, 'test'}->includesAll(Sequence{'test'})");

		ocl.assertQueryInvalid(null, "Sequence{3, 4, 'test'}->includesAll(OrderedSet{'test', invalid})");
		ocl.assertQueryInvalid(null, "Bag{3, 4, 'test'}->includesAll(Set{'test', invalid})");
		ocl.assertQueryInvalid(null, "Set{3, 4, 'test'}->includesAll(Bag{'test', invalid})");
		ocl.assertQueryInvalid(null, "OrderedSet{3, 4, 'test'}->includesAll(Sequence{'test', invalid})");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->includesAll(Sequence{0})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->includesAll(Bag{0})");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->includesAll(Set{0})");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = null in os->includesAll(OrderedSet{0})");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in Sequence{0}->includesAll(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in Bag{0}->includesAll(b)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in Set{0}->includesAll(s)");
		ocl.assertQueryInvalid(null, "let os : OrderedSet(Integer) = null in OrderedSet{0}->includesAll(os)");
		// null collection element
		ocl.assertQueryTrue(null, "Sequence{3, 4, null, 'test'}->includesAll(OrderedSet{'test', null})");
		ocl.assertQueryTrue(null, "Bag{3, 4, null, 'test'}->includesAll(Set{'test', null})");
		ocl.assertQueryTrue(null, "Set{3, 4, null, 'test'}->includesAll(Bag{'test', null})");
		ocl.assertQueryTrue(null, "OrderedSet{3, 4, null, 'test'}->includesAll(Sequence{'test', null})");

		ocl.assertQueryFalse(null, "Sequence{3, 4, 'test'}->includesAll(OrderedSet{'test', null})");
		ocl.assertQueryFalse(null, "Bag{3, 4, 'test'}->includesAll(Set{'test', null})");
		ocl.assertQueryFalse(null, "Set{3, 4, 'test'}->includesAll(Bag{'test', null})");
		ocl.assertQueryFalse(null, "OrderedSet{3, 4, 'test'}->includesAll(Sequence{'test', null})");
		ocl.dispose();
	}

	@Test public void testCollectionIncluding() {
		TestOCL ocl = createOCL();
		/*
		 * FIXME OMG-issue to add OrderedSet::including
		 * since it's defined in oclstdlib.ecore. However the defined
		 * "OrderedSet::including(T) : Set" should be
		 * "OrderedSet::including(T) : OrderedSet"
		 */
		ocl.assertQueryResults(null, "Sequence{'a', 'b', 'c'}", "Sequence{'a', 'b'}->including('c')");
		ocl.assertQueryResults(null, "Bag{'c', 'b', 'a'}", "Bag{'a', 'b'}->including('c')");
		ocl.assertQueryResults(null, "Set{'a', 'c', 'b'}", "Set{'a', 'b'}->including('c')");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', 'c'}", "OrderedSet{'a', 'b'}->including('c')");
		ocl.assertQueryResults(null, "OrderedSet{1..2,3..4}", "OrderedSet{1..4}->including(4)");
		ocl.assertQueryResults(null, "Sequence{1..2,3..4,4}", "Sequence{1..4}->including(4)");
		ocl.assertQueryResults(null, "Sequence{1..5}", "Sequence{1..4}->including(5)");
		ocl.assertQueryResults(null, "Sequence{1..3,4,6}", "Sequence{1..4}->including(6)");
		ocl.assertQueryResults(null, "Sequence{1..4,0}", "Sequence{1..4}->including(0)");
		ocl.assertQueryResults(null, "Set{1..9}", "Set{1..4,6,7..9}->including(5)");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->including('a')");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = invalid in b->including('a')");
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in s->including('a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->including('a')");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b'}->including(invalid)");
		ocl.assertQueryInvalid(null, "Bag{'a', 'b'}->including(invalid)");
		ocl.assertQueryInvalid(null, "Set{'a', 'b'}->including(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b'}->including(invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->including('a')");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = null in b->including('a')");
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in s->including('a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->including('a')");
		// null collection element
		ocl.assertQueryResults(null, "Sequence{'a', 'b', null}", "Sequence{'a', 'b'}->including(null)");
		ocl.assertQueryResults(null, "Bag{null, 'b', 'a'}", "Bag{'a', 'b'}->including(null)");
		ocl.assertQueryResults(null, "Set{'a', null, 'b'}", "Set{'a', 'b'}->including(null)");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', null}", "OrderedSet{'a', 'b'}->including(null)");
		ocl.assertQueryResults(null, "Sequence{'a', null, 'b', null}", "Sequence{'a', null, 'b'}->including(null)");
		ocl.assertQueryResults(null, "Bag{null, 'b', null, 'a'}", "Bag{'a', null, 'b'}->including(null)");
		ocl.assertQueryResults(null, "Set{'a', null, 'b'}", "Set{'a', null, 'b'}->including(null)");
		ocl.assertQueryResults(null, "OrderedSet{'a', null, 'b'}", "OrderedSet{'a', null, 'b'}->including(null)");
		ocl.assertQueryResults(null, "Sequence{'1..4', null}", "Sequence{'1..4'}->including(null)");
		ocl.assertQueryResults(null, "OrderedSet{'1..4', null}", "OrderedSet{'1..4'}->including(null)");
		ocl.dispose();
	}

	@Test public void testCollectionIncludingAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Sequence{'a', 'b', 'c', 'd'}", "Sequence{'a', 'b'}->includingAll(Sequence{'c', 'd'})");
		ocl.assertQueryResults(null, "Bag{'c', 'b', 'a', 'd'}", "Bag{'a', 'b'}->includingAll(Bag{'c', 'd'})");
		ocl.assertQueryResults(null, "Set{'a', 'c', 'b', 'd'}", "Set{'a', 'b'}->includingAll(Set{'c', 'd'})");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', 'c', 'd'}", "OrderedSet{'a', 'b'}->includingAll(OrderedSet{'c', 'd'})");
		ocl.assertQueryResults(null, "OrderedSet{1..2,3..4}", "OrderedSet{1..4}->includingAll(OrderedSet{2..4})");
		ocl.assertQueryResults(null, "Sequence{1..2,3..4,4..6}", "Sequence{1..4}->includingAll(Sequence{4..6})");
		ocl.assertQueryResults(null, "Sequence{1..7}", "Sequence{1..4}->includingAll(Sequence{5..7})");
		ocl.assertQueryResults(null, "Sequence{1..3,4,6..8}", "Sequence{1..4}->includingAll(Sequence{6..8})");
		ocl.assertQueryResults(null, "Sequence{2..4,0..1}", "Sequence{2..4}->includingAll(Sequence{0..1})");
		ocl.assertQueryResults(null, "Set{1..9}", "Set{1..4,7,8..9}->includingAll(Set{5..7})");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->includingAll(Sequence{'a'})");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = invalid in b->includingAll(Bag{'a'})");
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in s->includingAll(Set{'a'})");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->includingAll(OrderedSet{'a'})");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b'}->includingAll(invalid)");
		ocl.assertQueryInvalid(null, "Bag{'a', 'b'}->includingAll(invalid)");
		ocl.assertQueryInvalid(null, "Set{'a', 'b'}->includingAll(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b'}->includingAll(invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->includingAll(Sequence{'a'})");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = null in b->includingAll(Bag{'a'})");
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in s->includingAll(Set{'a'})");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->includingAll(OrderedSet{'a'})");
		// null collection element
		ocl.assertQueryResults(null, "Sequence{'a', 'b', null, null}", "Sequence{'a', 'b'}->includingAll(Sequence{null, null})");
		ocl.assertQueryResults(null, "Bag{null, null, 'b', 'a'}", "Bag{'a', 'b'}->includingAll(Bag{null, null})");
		ocl.assertQueryResults(null, "Set{'a', null, 'b'}", "Set{'a', 'b'}->includingAll(Set{null})");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', null}", "OrderedSet{'a', 'b'}->includingAll(OrderedSet{null})");
		ocl.assertQueryResults(null, "Sequence{'a', null, 'b', null}", "Sequence{'a', null, 'b'}->includingAll(Sequence{null})");
		ocl.assertQueryResults(null, "Bag{null, 'b', null, 'a'}", "Bag{'a', null, 'b'}->includingAll(Bag{null})");
		ocl.assertQueryResults(null, "Set{'a', null, 'b'}", "Set{'a', null, 'b'}->includingAll(Set{null})");
		ocl.assertQueryResults(null, "OrderedSet{'a', null, 'b'}", "OrderedSet{'a', null, 'b'}->includingAll(OrderedSet{null})");
		ocl.assertQueryResults(null, "Sequence{'1..4', null}", "Sequence{'1..4'}->includingAll(Sequence{null})");
		ocl.assertQueryResults(null, "OrderedSet{'1..4', null}", "OrderedSet{'1..4'}->includingAll(OrderedSet{null})");
		ocl.dispose();
	}

	@Test public void testCollectionIndexOf() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 1, "Sequence{'a', 'b'}->indexOf('a')");
		ocl.assertQueryEquals(null, 2, "OrderedSet{'a', 'b'}->indexOf('b')");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->indexOf('a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->indexOf('a')");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', invalid}->indexOf(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', invalid}->indexOf(invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->indexOf('a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->indexOf('a')");
		// null collection element
		ocl.assertQueryEquals(null, 1, "Sequence{null, 'a'}->indexOf(null)");
		ocl.assertQueryEquals(null, 1, "OrderedSet{null, 'a'}->indexOf(null)");
		// non-element
		ocl.assertQueryInvalid(null, "Sequence{'a'}->indexOf('b')");
		ocl.assertQueryInvalid(null, "OrderedSet{'a'}->indexOf('b')");
		ocl.dispose();
	}

	@Test public void testCollectionInsertAt() {
		TestOCL ocl = createOCL();
		// For now resolve Issue 14980 semantics by by first removing an existing content
		ocl.assertQueryResults(null, "Sequence{'c', 'a', 'b'}", "Sequence{'a', 'b'}->insertAt(1, 'c')");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'c', 'b'}", "OrderedSet{'a', 'b'}->insertAt(2, 'c')");

		// We can add _after_ the last element
		ocl.assertQueryResults(null, "Sequence{'a', 'b', 'c'}", "Sequence{'a', 'b'}->insertAt(3, 'c')");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', 'c'}", "OrderedSet{'a', 'b'}->insertAt(3, 'c')");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->insertAt(1, 'a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->insertAt(1, 'a')");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a'}->insertAt(1, invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a'}->insertAt(1, invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->insertAt(1, 'a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->insertAt(1, 'a')");
		// null collection element
		ocl.assertQueryResults(null, "Sequence{null, 'a'}", "Sequence{'a'}->insertAt(1, null)");
		ocl.assertQueryResults(null, "Sequence{null, null}", "Sequence{null}->insertAt(1, null)");
		//		ocl.assertQueryResults(null, "Sequence{'a', null}", "Sequence{null}->insertAt(1, 'a')");
		ocl.assertQueryResults(null, "OrderedSet{'a', null}", "OrderedSet{'a'}->insertAt(2, null)");
		ocl.assertQueryResults(null, "OrderedSet{null}", "OrderedSet{null}->insertAt(1, null)");
		//		ocl.assertQueryResults(null, "OrderedSet{null, 'a'}", "OrderedSet{null}->insertAt(2, 'a')");
		// out of bounds
		ocl.assertQueryInvalid(null, "Sequence{'a'}->insertAt(0, 'b')");
		ocl.assertQueryInvalid(null, "OrderedSet{'a'}->insertAt(0, 'b')");
		ocl.assertQueryInvalid(null, "Sequence{'a'}->insertAt(3, 'b')");
		ocl.assertQueryInvalid(null, "OrderedSet{'a'}->insertAt(3, 'b')");
		ocl.assertQueryInvalid(null, "OrderedSet{'a'}->insertAt(2, 'a')");
		// duplicates
		ocl.assertQueryResults(null, "Sequence{'b', 'a', 'b', 'c'}", "Sequence{'a', 'b', 'c'}->insertAt(1, 'b')");
		ocl.assertQueryResults(null, "Sequence{'a', 'b', 'c', 'b'}", "Sequence{'a', 'b', 'c'}->insertAt(4, 'b')");
		ocl.assertQueryResults(null, "OrderedSet{'b', 'a', 'c'}", "OrderedSet{'a', 'b', 'c'}->insertAt(1, 'b')");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'b', 'c'}", "OrderedSet{'a', 'b', 'c'}->insertAt(2, 'b')");
		ocl.assertQueryResults(null, "OrderedSet{'a', 'c', 'b'}", "OrderedSet{'a', 'b', 'c'}->insertAt(3, 'b')");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b', 'c'}->insertAt(4, 'b')");
		ocl.dispose();
	}

	@Test public void testCollectionIntersection() {
		TestOCL ocl = createOCL();
		// No duplicates
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{'a', 'b'}->intersection(Set{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{'a', 'b'}->intersection(Sequence{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{'a', 'b'}->intersection(OrderedSet{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{'a', 'b'}->intersection(Bag{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Sequence{'a', 'b'}->intersection(Set{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Sequence{'a', 'b'}->intersection(Sequence{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Sequence{'a', 'b'}->intersection(OrderedSet{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Sequence{'a', 'b'}->intersection(Bag{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "OrderedSet{'a', 'b'}->intersection(Set{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "OrderedSet{'a', 'b'}->intersection(Sequence{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "OrderedSet{'a', 'b'}->intersection(OrderedSet{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "OrderedSet{'a', 'b'}->intersection(Bag{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Bag{'a', 'b'}->intersection(Set{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Bag{'a', 'b'}->intersection(Sequence{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Bag{'a', 'b'}->intersection(OrderedSet{'c', 'd'})");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Bag{'a', 'b'}->intersection(Bag{'c', 'd'})");
		// Duplicates
		ocl.assertQueryResults(null, "Set{'a', 'b'}", "Set{'a', 'b', 'a'}->intersection(Set{'a', 'b', 'c'})");
		ocl.assertQueryResults(null, "Set{'a', 'b'}", "Set{'a', 'b', 'a'}->intersection(Bag{'a', 'b', 'c'})");
		ocl.assertQueryResults(null, "Set{'a', 'b'}", "Bag{'a', 'b', 'a'}->intersection(Set{'a', 'b', 'c'})");

		ocl.assertQueryResults(null, "Bag{'a', 'b'}", "Bag{'a', 'b', 'a'}->intersection(Bag{'a', 'b'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b'}", "Bag{'a', 'b'}->intersection(Bag{'a', 'b', 'a'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b', 'b'}", "Bag{'a', 'b', 'a', 'b'}->intersection(Bag{'a', 'b', 'b'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b'}", "Bag{'a', 'b', 'a'}->intersection(Bag{'a', 'b', 'c'})");

		ocl.assertQueryResults(null, "Bag{'a'}", "Bag{'a', 'a', 'a', 'a'}->intersection(Bag{'a', 'b', 'b'})");

		// empty collection
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{3, 4}->intersection(Set{})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{3, 4}->intersection(Bag{})");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Bag{3, 4}->intersection(Bag{})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Bag{3, 4}->intersection(Set{})");
		// implicit collection
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "1->intersection(Set{})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "2->intersection(Bag{})");

		//        assertBadQuery(SemanticException.class, Diagnostic.ERROR,
		//        	"Set{}->intersection(Set{3, 4})",
		//        	OCLMessages.OperationNotFound_ERROR_, "intersection(Set(Integer))", "Set(OclVoid)");

		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{}->intersection(Set{3, 4})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{}->intersection(Bag{3, 4})");
		ocl.assertQueryEquals(null, ocl.getEmptyBagValue(), "Bag{}->intersection(Bag{3, 4})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Bag{}->intersection(Set{3, 4})");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->intersection(Set{4})");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->intersection(Bag{4})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->intersection(Set{4})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->intersection(Bag{4})");

		//		assertSemanticErrorQuery("let s : Set(Integer) = invalid in Set{4}->intersection(s)",
		//		OCLMessages.UnresolvedOperationCall_ERROR_, "intersection", "Set(UnlimitedNatural)", "Set(Integer)");
		//		assertSemanticErrorQuery("let s : Set(Real) = invalid in Set{4}->intersection(s)",
		//			OCLMessages.UnresolvedOperationCall_ERROR_, "Set(Integer)", "intersection", "Set(Real)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in Set{4.0}->intersection(s)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in Bag{4.0}->intersection(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in Set{4.0}->intersection(b)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in Bag{4.0}->intersection(b)");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Set{3, 4}->intersection(Set{invalid})");
		ocl.assertQueryInvalid(null, "Set{3, invalid}->intersection(Bag{4})");
		ocl.assertQueryInvalid(null, "Bag{3, invalid}->intersection(Set{4})");
		ocl.assertQueryInvalid(null, "Bag{3, 4}->intersection(Bag{invalid})");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->intersection(Set{4})");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->intersection(Bag{4})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->intersection(Set{4})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->intersection(Bag{4})");

		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in Set{4.0}->intersection(s)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in Bag{4.0}->intersection(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in Set{4.0}->intersection(b)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in Bag{4.0}->intersection(b)");
		// null collection element
		ocl.assertQueryResults(null, "Set{2, null}", "Set{2, 3, null}->intersection(Set{2, 4, null})");
		ocl.assertQueryResults(null, "Set{2, null}", "Set{2, 3, null}->intersection(Bag{2, 4, null})");
		ocl.assertQueryResults(null, "Set{2, null}", "Bag{2, 3, null}->intersection(Set{2, 4, null})");
		ocl.assertQueryResults(null, "Bag{null, null}", "Bag{3, 4, null, null}->intersection(Bag{null, 2, null})");
		ocl.dispose();
	}

	@Test public void testCollectionIntersectionReturnType() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Set{'c'}", "Set{'a'}->intersection(Set{'b'})->including('c')");
		ocl.assertQueryResults(null, "Set{'c'}", "let domainVars: Set(String) = Set{'a'}, whenVars: Set(String) = Set{'b'}, tev: String = 'c' in domainVars->intersection(whenVars)->including(tev)");
		ocl.assertQueryTrue(null, "let domainTopVars: Set(String) = Set{'c'}, domainVars: Set(String) = Set{'a'}, whenVars: Set(String) = Set{'b'}, tev: String = 'c' in domainTopVars = domainVars->intersection(whenVars)->including(tev)");
		ocl.dispose();
	}

	@Test public void testCollectionIsEmpty() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Sequence{}->isEmpty()");
		ocl.assertQueryTrue(null, "Bag{}->isEmpty()");
		ocl.assertQueryTrue(null, "Set{}->isEmpty()");
		ocl.assertQueryTrue(null, "OrderedSet{}->isEmpty()");

		ocl.assertQueryFalse(null, "Sequence{4, 4, 'test'}->isEmpty()");
		ocl.assertQueryFalse(null, "Bag{4, 4, 'test'}->isEmpty()");
		ocl.assertQueryFalse(null, "Set{4, 4, 'test'}->isEmpty()");
		ocl.assertQueryFalse(null, "OrderedSet{4, 4, 'test'}->isEmpty()");

		ocl.assertQueryResults(null, "Set{'test'}", "'test'.oclAsSet()");
		ocl.assertQueryFalse(null, "'test'.oclAsSet()->isEmpty()");
		ocl.assertQueryFalse(null, "'test'->isEmpty()");
		ocl.assertQueryFalse(null, "''->isEmpty()");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->isEmpty()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->isEmpty()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->isEmpty()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->isEmpty()");
		// invalid collection element
		ocl.assertQueryInvalid(null, "invalid->isEmpty()");
		ocl.assertQueryInvalid(null, "Sequence{invalid}->isEmpty()");
		ocl.assertQueryInvalid(null, "Bag{invalid}->isEmpty()");
		ocl.assertQueryInvalid(null, "Set{invalid}->isEmpty()");
		ocl.assertQueryInvalid(null, "OrderedSet{invalid}->isEmpty()");
		// null collection
		//		ocl.assertQueryInvalid(null, "null->isEmpty()");
		ocl.assertQueryTrue(null, "null->isEmpty()");
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->isEmpty()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->isEmpty()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->isEmpty()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->isEmpty()");
		// null collection element
		//		ocl.assertQueryInvalid(null, "let s : String = null in s->isEmpty()");
		ocl.assertQueryTrue(null, "let s : String = null in s->isEmpty()");
		ocl.assertQueryFalse(null, "Sequence{null}->isEmpty()");
		ocl.assertQueryFalse(null, "Bag{null}->isEmpty()");
		ocl.assertQueryFalse(null, "Set{null}->isEmpty()");
		ocl.assertQueryFalse(null, "OrderedSet{null}->isEmpty()");
		ocl.dispose();
	}

	@Test public void testCollectionLast() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "3", "Sequence{1, 2.0, '3'}->last()"); //$NON-NLS-2$
		ocl.assertQueryEquals(null, "3", "OrderedSet{1, 2.0, '3'}->last()"); //$NON-NLS-2$
		// empty
		ocl.assertQueryInvalid(null, "Sequence{}->last()");
		ocl.assertQueryInvalid(null, "OrderedSet{}->last()");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->last()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->last()");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{invalid, 1}->last()");
		ocl.assertQueryInvalid(null, "OrderedSet{invalid, 1}->last()");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->last()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->last()");
		// null collection element
		ocl.assertQueryNull(null, "Sequence{null}->last()");
		ocl.assertQueryNull(null, "OrderedSet{null}->last()");
		ocl.dispose();
	}

	@Test public void testCollectionLower() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 3, "Sequence{1, 2.0, '3'}->oclType().lower");
		ocl.assertQueryEquals(null, 3, "Sequence{1, 2.0, 3}->oclAsType(Collection(Real))->oclType().lower");
		ocl.assertQueryEquals(null, 3, "Set{1, 2.0, 3}->oclAsType(Collection(Real[2..4]))->oclType().lower"); // no change to dynamic bound
		ocl.assertQueryEquals(null, 3, "Sequence{1, 2.0, '3'}->oclAsType(Collection(OclAny))->oclType().lower");
		ocl.assertQueryEquals(null, 3, "Sequence{1, 2.0, '3'}->oclAsType(Sequence(OclAny))->oclType().lower");
		String string = useCodeGen ? "Set(OclAny[*|?])" : "Set(OclAny)"; 		// FIXME See Bug 578117
		ocl.assertQueryInvalid(null, "Sequence{1, 2.0, '3'}->oclAsType(Set(OclAny))->oclType().lower",
			StringUtil.bind(PivotMessages.IncompatibleOclAsTypeSourceType, "Sequence(OclAny[3|?])", string), InvalidValueException.class);
		ocl.assertSemanticErrorQuery(null, "Sequence{1, 2.0, '3'}->oclAsType(OclVoid).oclType().lower",
			PivotMessagesInternal.UnresolvedProperty_ERROR_, "OclVoid", "lower");
		ocl.assertSemanticErrorQuery(null, "Sequence{1, 2.0, '3'}->oclAsType(OclAny).oclType().lower",
			PivotMessagesInternal.UnresolvedProperty_ERROR_, "OclAny", "lower");
		ocl.dispose();
	}

	@Test public void testCollectionMax() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 2, "Sequence{1, 2}->max()");
		ocl.assertQueryEquals(null, 5.0, "Set{5, 4.0, 3.0, 2, 1}->max()");
		ocl.assertQueryEquals(null, 1, "Bag{1}->max()");
		ocl.assertQueryEquals(null, 1, "Bag{1}->max()");
		ocl.assertQueryInvalid(null, "OrderedSet{'hi', 'lo'}->max()");
		ocl.assertQueryInvalid(null, "Set{}->max()");
		ocl.assertQueryInvalid(null, "OrderedSet{true, 1, 'bad'}->max()");
		//        assertBadQuery(SemanticException.class, Diagnostic.ERROR,
		//        	"OrderedSet{'hi', 'lo'}->max()",
		//        	OCLMessages.MaxOperator_ERROR_);
		//        assertBadQuery(SemanticException.class, Diagnostic.ERROR,
		//        	"Set{}->max()",
		//        	OCLMessages.MaxOperator_ERROR_);
		//        assertBadQuery(SemanticException.class, Diagnostic.ERROR,
		//        	"OrderedSet{true, 1, 'bad'}->max()",
		//        	OCLMessages.MaxOperator_ERROR_);
		// FIXME Bug 301351 Subtest-not-implemented user-defined max
		ocl.dispose();
	}

	@Test public void testCollectionMin() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 1, "Sequence{1, 2}->min()");
		ocl.assertQueryEquals(null, 1.0, "Set{5, 4.0, 3.0, 2, 1}->min()");
		ocl.assertQueryEquals(null, 1, "Bag{1}->min()");
		ocl.assertQueryInvalid(null, "OrderedSet{'hi', 'lo'}->min()");
		ocl.assertQueryInvalid(null, "Set{}->min()");
		ocl.assertQueryInvalid(null, "OrderedSet{true, 1, 'bad'}->min()");
		//        assertBadQuery(SemanticException.class, Diagnostic.ERROR,
		//        	"OrderedSet{'hi', 'lo'}->min()",
		//        	OCLMessages.MinOperator_ERROR_);
		//        assertBadQuery(SemanticException.class, Diagnostic.ERROR,
		//        	"Set{}->min()",
		//        	OCLMessages.MinOperator_ERROR_);
		//        assertBadQuery(SemanticException.class, Diagnostic.ERROR,
		//        	"OrderedSet{true, 1, 'bad'}->min()",
		//        	OCLMessages.MinOperator_ERROR_);
		// FIXME Bug 301351 Subtest-not-implemented user-defined min
		ocl.dispose();
	}

	@Test public void testCollectionMinus() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Set{'b'}", "Set{'a', 'b', 'c'} - Set{'c', 'a'}");
		/*
		 * FIXME OMG-issue generalise to UniqueCollection::-
		 * "OrderedSet::-(Set) : Set", we also need "Set::-(OrderedSet) : Set"
		 * and "OrderedSet::-(OrderedSet) : OrderedSet". That being said,
		 * "OrderedSet::-(Set) : Set" should be
		 * "OrderedSet::-(Set) : OrderedSet". revisit all "testCollectionMinus*"
		 * to add the new
		 */
		ocl.assertQueryResults(null, "OrderedSet{'b'}", "OrderedSet{'a', 'b', 'c'} - Set{'c', 'a'}");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in s - Set{'c'}");
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in Set{'a'} - s");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Set{'a', invalid} - Set{'c', invalid}");
		ocl.assertQueryInvalid(null, "Set{'a', invalid} - Set{'c', 'a'}");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in s - Set{'c', null}");
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in Set{'a', null} - s");
		// null collection element
		ocl.assertQueryResults(null, "Set{'a'}", "Set{'a', null} - Set{'c', null}");
		ocl.assertQueryResults(null, "Set{null}", "Set{'a', null} - Set{'c', 'a'}");
		ocl.dispose();
	}

	@Test public void testCollectionNotEmpty() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "Sequence{}->notEmpty()");
		ocl.assertQueryFalse(null, "Bag{}->notEmpty()");
		ocl.assertQueryFalse(null, "Set{}->notEmpty()");
		ocl.assertQueryFalse(null, "OrderedSet{}->notEmpty()");

		ocl.assertQueryTrue(null, "Sequence{4, 4, 'test'}->notEmpty()");
		ocl.assertQueryTrue(null, "Bag{4, 4, 'test'}->notEmpty()");
		ocl.assertQueryTrue(null, "Set{4, 4, 'test'}->notEmpty()");
		ocl.assertQueryTrue(null, "OrderedSet{4, 4, 'test'}->notEmpty()");

		ocl.assertQueryTrue(null, "'test'->notEmpty()");
		ocl.assertQueryTrue(null, "''->notEmpty()");
		// invalid collection
		ocl.assertQueryInvalid(null, "invalid->notEmpty()", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->notEmpty()", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->notEmpty()", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->notEmpty()", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->notEmpty()", "invalid", InvalidValueException.class);
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{invalid}->notEmpty()", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "Bag{invalid}->notEmpty()", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "Set{invalid}->notEmpty()", "invalid", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "OrderedSet{invalid}->notEmpty()", "invalid", InvalidValueException.class);
		// null collection
		//		ocl.assertQueryInvalid(null, "null->notEmpty()");
		ocl.assertQueryFalse(null, "null->notEmpty()");
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->notEmpty()", "'Collection' rather than 'OclVoid' value required", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->notEmpty()", "'Collection' rather than 'OclVoid' value required", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->notEmpty()", "'Collection' rather than 'OclVoid' value required", InvalidValueException.class);
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->notEmpty()", "'Collection' rather than 'OclVoid' value required", InvalidValueException.class);
		// null collection element
		ocl.assertQueryTrue(null, "Sequence{null}->notEmpty()");
		ocl.assertQueryTrue(null, "Bag{null}->notEmpty()");
		ocl.assertQueryTrue(null, "Set{null}->notEmpty()");
		ocl.assertQueryTrue(null, "OrderedSet{null}->notEmpty()");
		ocl.dispose();
	}

	@Test public void testCollectionNotEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "Bag{1} <> 1");
		ocl.assertQueryTrue(null, "OrderedSet{1} <> 1");
		ocl.assertQueryTrue(null, "Sequence{1} <> 1");
		ocl.assertQueryTrue(null, "Set{1} <> 1");

		ocl.assertQueryTrue(null, "1 <> Bag{1}");
		ocl.assertQueryTrue(null, "1 <> OrderedSet{1}");
		ocl.assertQueryTrue(null, "1 <> Sequence{1}");
		ocl.assertQueryTrue(null, "1 <> Set{1}");
		ocl.assertQueryTrue(null, "Set{1} <> Set{Set{1}}");

		ocl.assertQueryTrue(null, "Bag{1.01} <> Bag{1}");
		ocl.assertQueryTrue(null, "OrderedSet{1.01} <> OrderedSet{1}");
		ocl.assertQueryTrue(null, "Sequence{1.01} <> Sequence{1}");
		ocl.assertQueryTrue(null, "Set{1.01} <> Set{1}");
		ocl.assertQueryTrue(null, "Set{Set{1.01}} <> Set{Set{1}}");

		ocl.assertQueryFalse(null, "Bag{1.0} <> Bag{1}");
		ocl.assertQueryFalse(null, "OrderedSet{1.0} <> OrderedSet{1}");
		ocl.assertQueryFalse(null, "Sequence{1.0} <> Sequence{1}");
		ocl.assertQueryFalse(null, "Set{1.0} <> Set{1}");
		ocl.assertQueryFalse(null, "Set{Set{1.0}} <> Set{Set{1}}");

		ocl.assertQueryFalse(null, "Sequence{1..2} <> Sequence{1,2}");
		ocl.assertQueryFalse(null, "OrderedSet{1..2} <> OrderedSet{1,2}");
		ocl.assertQueryTrue(null, "Sequence{1..2} <> Sequence{2,1}");
		ocl.assertQueryTrue(null, "OrderedSet{1..2} <> OrderedSet{2,1}");
		ocl.assertQueryTrue(null, "Sequence{1..2} <> OrderedSet{1,2}");
		ocl.dispose();
	}

	@Test public void testCollectionNotEqualInvalid() {
		TestOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s <> Sequence{5}");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in Bag{5} <> b");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s <> Set{5}");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in OrderedSet{5} <> o");

		ocl.assertQueryInvalid(null, "let s1 : Sequence(Integer) = invalid, s2 : Sequence(Integer) = invalid in s1 <> s2");
		ocl.assertQueryInvalid(null, "let b1 : Bag(Integer) = invalid, b2 : Bag(Integer) = invalid in b1 <> b2");
		ocl.assertQueryInvalid(null, "let s1 : Set(Integer) = invalid, s2 : Set(Integer) = invalid in s1 <> s2");
		ocl.assertQueryInvalid(null, "let o1 : OrderedSet(Integer) = invalid, o2 : OrderedSet(Integer) = invalid in o1 <> o2");
		ocl.dispose();
	}

	@Test public void testCollectionNotEqualNull() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "let s : Sequence(Integer) = null in s <> Sequence{5}");
		ocl.assertQueryTrue(null, "let b : Bag(Integer) = null in Bag{5} <> b");
		ocl.assertQueryTrue(null, "let s : Set(Integer) = null in s <> Set{5}");
		ocl.assertQueryTrue(null, "let o : OrderedSet(Integer) = null in OrderedSet{5} <> o");

		ocl.assertQueryFalse(null, "let s1 : Sequence(Integer) = null, s2 : Sequence(Integer) = null in s1 <> s2");
		ocl.assertQueryFalse(null, "let b1 : Bag(Integer) = null, b2 : Bag(Integer) = null in b1 <> b2");
		ocl.assertQueryFalse(null, "let s1 : Set(Integer) = null, s2 : Set(Integer) = null in s1 <> s2");
		ocl.assertQueryFalse(null, "let o1 : OrderedSet(Integer) = null, o2 : OrderedSet(Integer) = null in o1 <> o2");
		ocl.dispose();
	}

	@Test public void testCollectionNotEqualOrderedXOrdered() {
		TestOCL ocl = createOCL();
		// same order, same quantities
		/*		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test'} <> Sequence{4, 5, 'test'}");
		ocl.assertQueryFalse(null, "Sequence{4, 5, 'test', 5} <> Sequence{4, 5, 'test', 5}");
		ocl.assertQueryFalse(null, "OrderedSet{4, 5, 'test', 5} <> OrderedSet{4, 5, 'test'}");
		ocl.assertQueryTrue(null, "Sequence{4, 5, 'test'} <> OrderedSet{4, 5, 'test', 5}");
		ocl.assertQueryTrue(null, "OrderedSet{4, 5, 'test', 5} <> Sequence{4, 5, 'test'}");

		// distinct order, same quantities
		ocl.assertQueryTrue(null, "Sequence{4, 5, 'test'} <> Sequence{4, 'test', 5}");
		ocl.assertQueryTrue(null, "Sequence{4, 5, 'test', 5} <> Sequence{5, 4, 'test', 5}");
		 */		ocl.assertQueryTrue(null, "OrderedSet{4, 5, 'test', 5} <> OrderedSet{4, 'test', 5}");
		 ocl.assertQueryTrue(null, "Sequence{4, 5, 'test'} <> OrderedSet{5, 4, 'test', 5}");
		 ocl.assertQueryTrue(null, "OrderedSet{4, 5, 'test', 5} <> Sequence{5, 4, 'test'}");

		 // distinct quantities
		 ocl.assertQueryTrue(null, "Sequence{4, 5, 'test', 5} <> Sequence{4, 5, 'test'}");
		 ocl.assertQueryTrue(null, "Sequence{4, 5, 'test', 5} <> OrderedSet{4, 5, 'test', 5}");
		 ocl.assertQueryTrue(null, "OrderedSet{4, 5, 'test', 5} <> Sequence{4, 5, 'test', 5}");
		 ocl.dispose();
	}

	@Test public void testCollectionNotEqualOrderedXUnordered() {
		TestOCL ocl = createOCL();
		// same quantities
		ocl.assertQueryTrue(null, "Sequence{4, 5, 'test'} <> Set{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "Sequence{4, 5, 'test', 4} <> Bag{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "OrderedSet{4, 5, 'test', 4} <> Set{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "OrderedSet{4, 5, 'test', 4} <> Bag{4, 'test', 5}");

		// distinct quantities
		ocl.assertQueryTrue(null, "Sequence{4, 5, 'test', 4} <> Set{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "Sequence{4, 5, 'test'} <> Bag{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "OrderedSet{4, 5, 'test', 4} <> Bag{4, 'test', 5, 4}");
		ocl.dispose();
	}

	@Test public void testCollectionNotEqualUnorderedXUnordered() {
		TestOCL ocl = createOCL();
		// same quantities
		ocl.assertQueryTrue(null, "Bag{4, 5, 'test'} <> Set{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "Bag{4, 5, 'test', 4} <> Bag{4, 'test', 5, 4}");
		ocl.assertQueryFalse(null, "Set{4, 5, 'test', 4} <> Set{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "Set{4, 5, 'test', 4} <> Bag{4, 'test', 5}");

		// distinct quantities
		ocl.assertQueryTrue(null, "Bag{4, 5, 'test', 4} <> Set{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "Bag{4, 5, 'test'} <> Bag{4, 'test', 5, 4}");
		ocl.assertQueryTrue(null, "Set{4, 5, 'test', 4} <> Bag{4, 'test', 5, 4}");
		ocl.dispose();
	}

	@Test public void testCollectionPrepend() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Sequence{'c', 'a', 'b'}", "Sequence{'a', 'b'}->prepend('c')");
		ocl.assertQueryResults(null, "OrderedSet{'c', 'a', 'b'}", "OrderedSet{'a', 'b'}->prepend('c')");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->prepend('a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->prepend('a')");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b'}->prepend(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b'}->prepend(invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->prepend('a')");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->prepend('a')");
		// null collection element
		ocl.assertQueryResults(null, "Sequence{null, 'a', null, 'b'}", "Sequence{'a', null, 'b'}->prepend(null)");
		ocl.assertQueryResults(null, "OrderedSet{null, 'a', 'b'}", "OrderedSet{'a', null, 'b'}->prepend(null)");
		ocl.dispose();
	}

	@Test public void testCollectionPrependAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Sequence{'c', 'd', 'a', 'b'}", "Sequence{'a', 'b'}->prependAll(Sequence{'c', 'd'})");
		ocl.assertQueryResults(null, "Sequence{'c', 'd', 'a', 'b'}", "Sequence{'a', 'b'}->prependAll(OrderedSet{'c', 'd'})");
		ocl.assertQueryResults(null, "OrderedSet{'c', 'd', 'a', 'b'}", "OrderedSet{'a', 'b'}->prependAll(Sequence{'c', 'd'})");
		ocl.assertQueryResults(null, "OrderedSet{'c', 'd', 'a', 'b'}", "OrderedSet{'a', 'b'}->prependAll(OrderedSet{'c', 'd'})");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->prependAll(Sequence{'a', 'b'})");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->prependAll(OrderedSet{'a', 'b'})");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b'}->prependAll(invalid)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b'}->prependAll(invalid)");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->prependAll(Sequence{'a', 'b'})");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->prependAll(OrderedSet{'a', 'b'})");
		// null collection element
		ocl.assertQueryResults(null, "Sequence{null, null, 'a', null, 'b'}", "Sequence{'a', null, 'b'}->prependAll(Sequence{null,null})");
		ocl.assertQueryResults(null, "OrderedSet{null, 'a', 'b'}", "OrderedSet{'a', null, 'b'}->prependAll(Sequence{null,null})");
		ocl.dispose();
	}

	@Test public void testCollectionProduct() {
		TestOCL ocl = createOCL();
		//		Abstract2Moniker.TRACE_MONIKERS.setState(true);
		String expectedResultExpression = "Set{Tuple{first = 3, second = 3.0}, Tuple{first = 3, second = 4.0}, Tuple{first = 4, second = 3.0}, Tuple{first = 4, second = 4.0}}";

		// Sequence::product(Collection)
		ocl.assertQueryResults(null, expectedResultExpression, "Sequence{3, 4}->product(Sequence{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "Sequence{3, 4}->product(Bag{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "Sequence{3, 4}->product(Set{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "Sequence{3, 4}->product(OrderedSet{3.0, 4.0})");

		// Bag::product(Collection)
		ocl.assertQueryResults(null, expectedResultExpression, "Bag{3, 4}->product(Sequence{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "Bag{3, 4}->product(Bag{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "Bag{3, 4}->product(Set{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "Bag{3, 4}->product(OrderedSet{3.0, 4.0})");

		// Set::product(Collection)
		ocl.assertQueryResults(null, expectedResultExpression, "Set{3, 4}->product(Sequence{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "Set{3, 4}->product(Bag{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "Set{3, 4}->product(Set{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "Set{3, 4}->product(OrderedSet{3.0, 4.0})");

		// OrderedSet::product(Collection)
		ocl.assertQueryResults(null, expectedResultExpression, "OrderedSet{3, 4}->product(Sequence{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "OrderedSet{3, 4}->product(Bag{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "OrderedSet{3, 4}->product(Set{3.0, 4.0})");
		ocl.assertQueryResults(null, expectedResultExpression, "OrderedSet{3, 4}->product(OrderedSet{3.0, 4.0})");
		// bug284129
		//		ocl.assertQueryResults(null, "Set{Tuple{first = 3, second = 3.0}, Tuple{first = 3, second = 4}, Tuple{first = 4.0, second = 3.0}, Tuple{first = 4.0, second = 4}}", "Sequence{3, 4.0}->product(Sequence{3.0, 4})");
		ocl.assertQueryResults(null, "Set{Tuple{first = 3.0, second = 3.0}, Tuple{first = 3.0, second = 4.0}, Tuple{first = 4.0, second = 3.0}, Tuple{first = 4.0, second = 4.0}}", "Sequence{3, 4.0}->product(Sequence{3.0, 4})");
		// empty
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Sequence{3, 4}->product(OrderedSet{})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Bag{3, 4}->product(Set{})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{3, 4}->product(Bag{})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "OrderedSet{3, 4}->product(Sequence{})");

		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Sequence{}->product(OrderedSet{3, 4})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Bag{}->product(Set{3, 4})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "Set{}->product(Bag{3, 4})");
		ocl.assertQueryEquals(null, ocl.getEmptySetValue(), "OrderedSet{}->product(Sequence{3, 4})");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in OrderedSet{3, 4}->product(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in Set{3, 4}->product(b)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in Bag{3, 4}->product(s)");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in Sequence{3, 4}->product(o)");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->product(OrderedSet{3, 4})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->product(Set{3, 4})");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->product(Bag{3, 4})");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->product(Sequence{3, 4})");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{3, 4}->product(OrderedSet{invalid})");
		ocl.assertQueryInvalid(null, "Bag{3, 4}->product(Set{invalid})");
		ocl.assertQueryInvalid(null, "Set{3, 4}->product(Bag{invalid})");
		ocl.assertQueryInvalid(null, "OrderedSet{3, 4}->product(Sequence{invalid})");

		ocl.assertQueryInvalid(null, "Sequence{invalid, 4}->product(Sequence{3})");
		ocl.assertQueryInvalid(null, "Bag{invalid, 4}->product(Set{3})");
		ocl.assertQueryInvalid(null, "Set{invalid, 4}->product(Bag{3})");
		ocl.assertQueryInvalid(null, "OrderedSet{invalid, 4}->product(Sequence{3})");
		ocl.dispose();
	}

	@Test public void testCollectionProductNull() {
		TestOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in OrderedSet{3, 4}->product(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in Set{3, 4}->product(b)");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in Bag{3, 4}->product(s)");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in Sequence{3, 4}->product(o)");

		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->product(OrderedSet{3, 4})");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->product(Set{3, 4})");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->product(Bag{3, 4})");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->product(Sequence{3, 4})");
		ocl.dispose();
	}

	@Test public void testCollectionProductNullValue() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Set{Tuple{first = 3, second = null}, Tuple{first = 4, second = null}}", "Sequence{3, 4}->product(OrderedSet{null})");
		ocl.assertQueryResults(null, "Set{Tuple{first = 3, second = null}, Tuple{first = 4, second = null}}", "Bag{3, 4}->product(Set{null})");
		ocl.assertQueryResults(null, "Set{Tuple{first = 3, second = null}, Tuple{first = 4, second = null}}", "Set{3, 4}->product(Bag{null})");
		ocl.assertQueryResults(null, "Set{Tuple{first = 3, second = null}, Tuple{first = 4, second = null}}", "OrderedSet{3, 4}->product(Sequence{null})");

		ocl.assertQueryResults(null, "Set{Tuple{first = null, second = 3}, Tuple{first = null, second = 4}}", "Sequence{null}->product(OrderedSet{3, 4})");
		ocl.assertQueryResults(null, "Set{Tuple{first = null, second = 3}, Tuple{first = null, second = 4}}", "Bag{null}->product(Set{3, 4})");
		ocl.assertQueryResults(null, "Set{Tuple{first = null, second = 3}, Tuple{first = null, second = 4}}", "Set{null}->product(Bag{3, 4})");
		ocl.assertQueryResults(null, "Set{Tuple{first = null, second = 3}, Tuple{first = null, second = 4}}", "OrderedSet{null}->product(Sequence{3, 4})");

		ocl.assertQueryResults(null, "let nu : Integer = null in Set{Tuple{first = nu, second = 3}, Tuple{first = 4, second = 3}}", "Sequence{null, 4}->product(Sequence{3})");
		ocl.assertQueryResults(null, "let nu : Integer = null in Set{Tuple{first = nu, second = 3}, Tuple{first = 4, second = 3}}", "Bag{null, 4}->product(Set{3})");
		ocl.assertQueryResults(null, "let nu : Integer = null in Set{Tuple{first = nu, second = 3}, Tuple{first = 4, second = 3}}", "Set{null, 4}->product(Bag{3})");
		ocl.assertQueryResults(null, "let nu : Integer = null in Set{Tuple{first = nu, second = 3}, Tuple{first = 4, second = 3}}", "OrderedSet{null, 4}->product(Sequence{3})");

		ocl.assertQueryResults(null, "let n : Real = null in Set{Tuple{first = n, second = 3}, Tuple{first = 4.0, second = 3}}", "Sequence{null, 4.0}->product(Sequence{3})");
		ocl.assertQueryResults(null, "let n : Real = null in Set{Tuple{first = n, second = 3}, Tuple{first = 4.0, second = 3}}", "Bag{null, 4.0}->product(Sequence{3})");
		ocl.assertQueryResults(null, "let n : Real = null in Set{Tuple{first = n, second = 3}, Tuple{first = 4.0, second = 3}}", "Set{null, 4.0}->product(Sequence{3})");
		ocl.assertQueryResults(null, "let n : Real = null in Set{Tuple{first = n, second = 3}, Tuple{first = 4.0, second = 3}}", "OrderedSet{null, 4.0}->product(Sequence{3})");
		ocl.dispose();
	}

	@Test public void testCollectionReverse() {
		TestOCL ocl = createOCL();
		ocl.assertSemanticErrorQuery(null, "Bag{1,3,null,2}->reverse()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Bag(Integer[*|?])", "reverse");
		ocl.assertQueryResults(null, "OrderedSet{}", "OrderedSet{}->reverse()");
		ocl.assertQueryResults(null, "OrderedSet{null}", "OrderedSet{null}->reverse()");
		ocl.assertQueryResults(null, "OrderedSet{2,1}", "OrderedSet{1,2}->reverse()");
		ocl.assertQueryResults(null, "OrderedSet{'a','b'}", "OrderedSet{'b','a'}->reverse()");
		ocl.assertQueryResults(null, "OrderedSet{}", "OrderedSet{}->reverse()");
		ocl.assertQueryResults(null, "OrderedSet{21,20,19,18,17,16,13,24,23,22,15,14,12,11,10,9,null,8,7,6,5,4,3,2,1}", "OrderedSet{1..4,3..8,null,9..12,14..15,4,22..24,13..21}->reverse()");
		ocl.assertQueryResults(null, "Sequence{2,null,3,1}", "Sequence{1,3,null,2}->reverse()");
		ocl.assertQueryResults(null, "Sequence{21,20,19,18,17,16,15,14,13,24,23,22,4,15,14,12,11,10,9,null,8,7,6,5,4,3,4,3,2,1}", "Sequence{1..4,3..8,null,9..12,14..15,4,22..24,13..21}->reverse()");
		ocl.assertQueryResults(null, "Sequence{Set{1..3},Sequence{1..3},OrderedSet{1,3},Bag{1,1,1}}", "Sequence{Bag{1,1,1},OrderedSet{1,3},Sequence{1..3},Set{1..3}}->reverse()");
		ocl.assertSemanticErrorQuery(null, "Set{}->reverse()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Set(OclVoid)", "reverse");
		ocl.dispose();
	}

	@Test public void testCollectionSelectByKind() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Bag{4,4}", "Bag{4, 4, 5.0, 'test', null}->selectByKind(Integer)");
		ocl.assertQueryResults(null, "OrderedSet{4}", "OrderedSet{4, 4, 5.0, 'test', null}->selectByKind(Integer)");
		ocl.assertQueryResults(null, "Sequence{4,4}", "Sequence{4, 4, 5.0, 'test', null}->selectByKind(Integer)");
		ocl.assertQueryResults(null, "Set{4}", "Set{4, 4, 5.0, 'test', null}->selectByKind(Integer)");
		//
		ocl.assertQueryResults(null, "Bag{}", "Bag{}->selectByKind(Integer)");
		ocl.assertQueryResults(null, "OrderedSet{}", "OrderedSet{}->selectByKind(Integer)");
		ocl.assertQueryResults(null, "Sequence{}", "Sequence{}->selectByKind(Integer)");
		ocl.assertQueryResults(null, "Set{}", "Set{}->selectByKind(Integer)");
		//
		ocl.assertQueryResults(null, "Bag{}", "Bag{null}->selectByKind(Integer)");
		ocl.assertQueryResults(null, "OrderedSet{}", "OrderedSet{null}->selectByKind(Integer)");
		ocl.assertQueryResults(null, "Sequence{}", "Sequence{null}->selectByKind(Integer)");
		ocl.assertQueryResults(null, "Set{}", "Set{null}->selectByKind(Integer)");
		//
		ocl.assertQueryResults(null, "Bag{}", "Bag{null}->selectByKind(OclVoid)");
		ocl.assertQueryResults(null, "OrderedSet{}", "OrderedSet{null}->selectByKind(OclVoid)");
		ocl.assertQueryResults(null, "Sequence{}", "Sequence{null}->selectByKind(OclVoid)");
		ocl.assertQueryResults(null, "Set{}", "Set{null}->selectByKind(OclVoid)");
		//
		ocl.assertQueryResults(null, "Bag{}", "Bag{null}->selectByKind(OclInvalid)");
		ocl.assertQueryResults(null, "OrderedSet{}", "OrderedSet{null}->selectByKind(OclInvalid)");
		ocl.assertQueryResults(null, "Sequence{}", "Sequence{null}->selectByKind(OclInvalid)");
		ocl.assertQueryResults(null, "Set{}", "Set{null}->selectByKind(OclInvalid)");
		//
		ocl.assertQueryResults(null, "Bag{*, *}", "Bag{4, 4, *, 5.0, 'test', *}->selectByKind(UnlimitedNatural)");
		ocl.assertQueryResults(null, "OrderedSet{*}", "OrderedSet{4, 4, *, 5.0, 'test', *}->selectByKind(UnlimitedNatural)");
		ocl.assertQueryResults(null, "Sequence{*, *}", "Sequence{4, 4, *, 5.0, 'test', *}->selectByKind(UnlimitedNatural)");
		ocl.assertQueryResults(null, "Set{*}", "Set{4, 4, *, 5.0, 'test', *}->selectByKind(UnlimitedNatural)");
		//
		ocl.assertQueryResults(null, "Sequence{'TEST'}", "Sequence{4, 4, 5.0, 'test'}->selectByKind(String).toUpper()");
		ocl.assertQueryEquals(null, 9.0, "Set{4, 4, 5.0, 'test'}->selectByKind(Real)->sum()");
		ocl.assertQueryEquals(null, 4, "Set{4, 4, 5.0, 'test'}->selectByKind(Integer)->sum()");
		ocl.dispose();
	}

	@Test public void testCollectionSelectByType() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Bag{4,4}", "Bag{4, 4, 5.0, 'test', null}->selectByType(Integer)");
		ocl.assertQueryResults(null, "OrderedSet{4}", "OrderedSet{4, 4, 5.0, 'test', null}->selectByType(Integer)");
		ocl.assertQueryResults(null, "Sequence{4,4}", "Sequence{4, 4, 5.0, 'test', null}->selectByType(Integer)");
		ocl.assertQueryResults(null, "Set{4}", "Set{4, 4, 5.0, 'test', null}->selectByType(Integer)");
		//
		ocl.assertQueryResults(null, "Bag{}", "Bag{}->selectByType(Integer)");
		ocl.assertQueryResults(null, "OrderedSet{}", "OrderedSet{}->selectByType(Integer)");
		ocl.assertQueryResults(null, "Sequence{}", "Sequence{}->selectByType(Integer)");
		ocl.assertQueryResults(null, "Set{}", "Set{}->selectByType(Integer)");
		//
		ocl.assertQueryResults(null, "Bag{}", "Bag{null}->selectByType(Integer)");
		ocl.assertQueryResults(null, "OrderedSet{}", "OrderedSet{null}->selectByType(Integer)");
		ocl.assertQueryResults(null, "Sequence{}", "Sequence{null}->selectByType(Integer)");
		ocl.assertQueryResults(null, "Set{}", "Set{null}->selectByType(Integer)");
		//
		ocl.assertQueryResults(null, "Bag{null}", "Bag{null}->selectByType(OclVoid)");
		ocl.assertQueryResults(null, "OrderedSet{null}", "OrderedSet{null}->selectByType(OclVoid)");
		ocl.assertQueryResults(null, "Sequence{null}", "Sequence{null}->selectByType(OclVoid)");
		ocl.assertQueryResults(null, "Set{null}", "Set{null}->selectByType(OclVoid)");
		//
		ocl.assertQueryResults(null, "Bag{}", "Bag{null}->selectByType(OclInvalid)");
		ocl.assertQueryResults(null, "OrderedSet{}", "OrderedSet{null}->selectByType(OclInvalid)");
		ocl.assertQueryResults(null, "Sequence{}", "Sequence{null}->selectByType(OclInvalid)");
		ocl.assertQueryResults(null, "Set{}", "Set{null}->selectByType(OclInvalid)");
		//
		ocl.assertQueryResults(null, "Bag{*, *}", "Bag{4, 4, *, 5.0, 'test', *}->selectByType(UnlimitedNatural)");
		ocl.assertQueryResults(null, "OrderedSet{*}", "OrderedSet{4, 4, *, 5.0, 'test', *}->selectByType(UnlimitedNatural)");
		ocl.assertQueryResults(null, "Sequence{*, *}", "Sequence{4, 4, *, 5.0, 'test', *}->selectByType(UnlimitedNatural)");
		ocl.assertQueryResults(null, "Set{*}", "Set{4, 4, *, 5.0, 'test', *}->selectByType(UnlimitedNatural)");
		//
		ocl.assertQueryResults(null, "Sequence{'TEST'}", "Sequence{4, 4, 5.0, 'test'}->selectByType(String).toUpper()");
		ocl.assertQueryEquals(null, 5.0, "Set{4, 4, 5.0, 'test'}->selectByType(Real)->sum()");
		ocl.assertQueryEquals(null, 4, "Set{4, 4, 5.0, 'test'}->selectByType(Integer)->sum()");
		ocl.dispose();
	}

	@Test public void testCollectionSize() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 4, "Sequence{4, 4, 5, 'test'}->size()");
		ocl.assertQueryEquals(null, 4, "Bag{4, 4, 5, 'test'}->size()");
		ocl.assertQueryEquals(null, 3, "Set{4, 4, 5, 'test'}->size()");
		ocl.assertQueryEquals(null, 3, "OrderedSet{4, 4, 5, 'test'}->size()");

		ocl.assertQueryEquals(null, 0, "Sequence{}->size()");
		ocl.assertQueryEquals(null, 0, "Bag{}->size()");
		ocl.assertQueryEquals(null, 0, "Set{}->size()");
		ocl.assertQueryEquals(null, 0, "OrderedSet{}->size()");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : String = invalid in s->size()");
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->size()");
		// invalid collection element
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b', invalid}->size()");
		ocl.assertQueryInvalid(null, "Set{'a', 'b', invalid}->size()");
		ocl.assertQueryInvalid(null, "Bag{'a', 'b', invalid}->size()");
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b', invalid}->size()");
		// null collection
		//		ocl.assertQueryInvalid(null, "let s : String = null in s->size()");
		ocl.assertQueryEquals(null, 0, "let s : String = null in s->size()");
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->size()");
		// safe navigation
		ocl.assertQueryEquals(null, 2, "let s : Sequence(Integer) = Sequence{1, null} in s->size()");
		ocl.assertQueryEquals(null, 1, "let s : Sequence(Integer) = Sequence{1, null} in s?->size()");
		// null collection element
		ocl.assertQueryEquals(null, 4, "Sequence{'a', 'b', null, null}->size()");
		ocl.assertQueryEquals(null, 4, "Bag{'a', 'b', null, null}->size()");
		ocl.assertQueryEquals(null, 3, "Set{'a', 'b', null, null}->size()");
		ocl.assertQueryEquals(null, 3, "OrderedSet{'a', 'b', null, null}->size()");
		ocl.dispose();
	}

	@Test public void testCollectionSubOrderedSet() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "OrderedSet{'a'}", "OrderedSet{'a', 'b', 'c', 'd'}->subOrderedSet(1, 1)");
		ocl.assertQueryResults(null, "OrderedSet{'b', 'c'}", "OrderedSet{'a', 'b', 'c', 'd'}->subOrderedSet(2, 3)");
		ocl.assertQueryResults(null, "OrderedSet{'d'}", "OrderedSet{'a', 'b', 'c', 'd'}->subOrderedSet(4, 4)");
		// invalid collection
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = invalid in o->subOrderedSet(1, 1)");
		// null collection
		ocl.assertQueryInvalid(null, "let o : OrderedSet(String) = null in o->subOrderedSet(1, 1)");
		// out of bounds
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b', 'c', 'd'}->subOrderedSet(0, 1)");
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b', 'c', 'd'}->subOrderedSet(4, 5)");
		// illegal arguments
		ocl.assertQueryInvalid(null, "OrderedSet{'a', 'b', 'c', 'd'}->subOrderedSet(2, 1)");
		ocl.dispose();
	}

	@Test public void testCollectionSubSequence() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Sequence{'a'}", "Sequence{'a', 'b', 'c', 'd'}->subSequence(1, 1)");
		ocl.assertQueryResults(null, "Sequence{'b', 'c'}", "Sequence{'a', 'b', 'c', 'd'}->subSequence(2, 3)");
		ocl.assertQueryResults(null, "Sequence{'d'}", "Sequence{'a', 'b', 'c', 'd'}->subSequence(4, 4)");
		// invalid collection
		ocl.assertQueryInvalid(null, "let o : Sequence(String) = invalid in o->subSequence(1, 1)");
		// null collection
		ocl.assertQueryInvalid(null, "let o : Sequence(String) = null in o->subSequence(1, 1)");
		// out of bounds
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b', 'c', 'd'}->subSequence(0, 1)");
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b', 'c', 'd'}->subSequence(4, 5)");
		// illegal arguments
		ocl.assertQueryInvalid(null, "Sequence{'a', 'b', 'c', 'd'}->subSequence(2, 1)");
		ocl.dispose();
	}

	@Test public void testCollectionSum() {
		TestOCL ocl = createOCL();
		StandardLibrary standardLibrary = ocl.getStandardLibrary();
		ocl.assertQueryEquals(null, 0, "let s : Sequence(Integer) = Sequence{} in s->sum()");
		ocl.assertQueryEquals(null, 0.0, "let b : Bag(Real) = Bag{} in b->sum()");
		ocl.assertQueryEquals(null, 0.0, "let s : Set(Real) = Set{} in s->sum()");
		ocl.assertQueryEquals(null, 0, "let o : OrderedSet(Integer) = OrderedSet{} in o->sum()");

		ocl.assertQueryEquals(null, 13.0, "Sequence{4.0, 4.0, 5.0}->sum()");
		ocl.assertQueryEquals(null, 13, "Bag{4, 4, 5}->sum()");
		ocl.assertQueryEquals(null, 9.0, "Set{4, 4.0, 5.0}->sum()");
		ocl.assertQueryEquals(null, 9.0, "OrderedSet{4.0, 4.0, 5.0}->sum()");
		ocl.assertQueryEquals(null, standardLibrary.getRealType(), "Bag{4.0, 4, 5}->sum().oclType()");
		ocl.assertQueryEquals(null, standardLibrary.getIntegerType(), "Bag{4, -4, -5}->sum().oclType()");
		ocl.assertQueryEquals(null, standardLibrary.getIntegerType(), "Bag{4, 4, 5}->sum().oclType()");

		ocl.assertQueryEquals(null, 4, "4->sum()");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = invalid in s->sum()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = invalid in b->sum()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = invalid in s->sum()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = invalid in o->sum()");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Sequence{4.0, invalid, 5.0}->sum()");
		ocl.assertQueryInvalid(null, "Bag{4, invalid, 5}->sum()");
		ocl.assertQueryInvalid(null, "Set{4, invalid, 5}->sum()");
		ocl.assertQueryInvalid(null, "OrderedSet{4.0, invalid, 5.0}->sum()");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Sequence(Integer) = null in s->sum()");
		ocl.assertQueryInvalid(null, "let b : Bag(Integer) = null in b->sum()");
		ocl.assertQueryInvalid(null, "let s : Set(Integer) = null in s->sum()");
		ocl.assertQueryInvalid(null, "let o : OrderedSet(Integer) = null in o->sum()");
		// null collection element
		ocl.assertQueryInvalid(null, "Sequence{4.0, null, 5.0}->sum()");
		ocl.assertQueryInvalid(null, "Bag{4, null, 5}->sum()");
		ocl.assertQueryInvalid(null, "Set{4, null, 5}->sum()");
		ocl.assertQueryInvalid(null, "OrderedSet{4.0, null, 5.0}->sum()");

		// FIXME Bug 301351 Subtest-not-implemented user-defined +
		ocl.dispose();
	}

	@Test public void testCollectionSymmetricDifference() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Set{'a', 'c'}", "Set{'a', 'b'}->symmetricDifference(Set{'b', 'c'})");
		// invalid collection
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in s->symmetricDifference(Set{'a'})");
		// invalid collection element
		ocl.assertQueryInvalid(null, "Set{'a', invalid, 'b'}->symmetricDifference(Set{'b', 'c'})");
		// null collection
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in s->symmetricDifference(Set{'a'})");
		// null collection element
		ocl.assertQueryResults(null, "Set{'a', null, 'c'}", "Set{'a', null, 'b'}->symmetricDifference(Set{'b', 'c'})");
		ocl.dispose();
	}

	@Test public void testCollectionUnionDuplicates() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Set{'a', 'b', 'c'}", "Set{'a', 'b', 'a'}->union(Set{'b', 'c'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b', 'b', 'c'}", "Set{'a', 'b', 'a'}->union(Bag{'b', 'c'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b', 'a', 'b', 'c'}", "Bag{'a', 'b', 'a'}->union(Bag{'b', 'c'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b', 'a', 'b', 'c'}", "Bag{'a', 'b', 'a'}->union(Set{'b', 'c'})");

		//		ocl.assertQueryResults(null, "Sequence{'a', 'b', 'a', 'b', 'c'}", "Sequence{'a', 'b', 'a'}->union(Sequence{'b', 'c'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b', 'a', 'b', 'c'}", "Sequence{'a', 'b', 'a'}->union(Sequence{'b', 'c'})");
		ocl.dispose();
	}

	@Test public void testCollectionUnionEmptyCollection() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Set{3, 4}", "Set{}->union(Set{3, 4})");
		//
		ocl.assertQueryResults(null, "Set{3, 4}", "Set{3, 4}->union(Set{})");
		ocl.assertQueryResults(null, "Bag{3, 4}", "Set{3, 4}->union(Bag{})");
		ocl.assertQueryResults(null, "Bag{3, 4}", "Bag{3, 4}->union(Bag{})");
		ocl.assertQueryResults(null, "Bag{3, 4}", "Bag{3, 4}->union(Set{})");
		ocl.assertQueryResults(null, "Bag{3, 4}", "Sequence{3, 4}->union(Sequence{})");

		ocl.assertQueryResults(null, "Set{3, 4}", "Set{}->union(Set{3, 4})");
		ocl.assertQueryResults(null, "Bag{3, 4}", "Set{}->union(Bag{3, 4})");
		ocl.assertQueryResults(null, "Bag{3, 4}", "Bag{}->union(Bag{3, 4})");
		ocl.assertQueryResults(null, "Bag{3, 4}", "Bag{}->union(Set{3, 4})");
		ocl.assertQueryResults(null, "Bag{3, 4}", "Sequence{}->union(Sequence{3, 4})");
		ocl.dispose();
	}

	@Test public void testCollectionUnionInvalid() {
		TestOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in s->union(Set{'a'})");
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in s->union(Bag{'a'})");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = invalid in b->union(Bag{'a'})");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = invalid in b->union(Set{'a'})");
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in s->union(Sequence{'a'})");

		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in Set{'a'}->union(s)");
		ocl.assertQueryInvalid(null, "let s : Set(String) = invalid in Bag{'a'}->union(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = invalid in Bag{'a'}->union(b)");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = invalid in Set{'a'}->union(b)");
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = invalid in Sequence{'a'}->union(s)");
		ocl.dispose();
	}

	@Test public void testCollectionUnionInvalidValue() {
		TestOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "Set{'a', invalid}->union(Set{'b', invalid})");
		ocl.assertQueryInvalid(null, "Set{'a', invalid}->union(Bag{'b', invalid})");
		ocl.assertQueryInvalid(null, "Bag{'a', invalid}->union(Bag{'b', invalid})");
		ocl.assertQueryInvalid(null, "Bag{'a', invalid}->union(Set{'b', invalid})");
		ocl.assertQueryInvalid(null, "Sequence{'a', invalid}->union(Sequence{'b', invalid})");
		ocl.dispose();
	}

	@Test public void testCollectionUnionNoDuplicates() {
		TestOCL ocl = createOCL();
		/*
		 * FIXME OMG-issue generalise to Collection::union
		 * the specification defines operations Set::union(Set),
		 * Set::union(Bag), Bag::union(Set) and Bag::union(Bag) with the same
		 * semantics "the union of self and bag" and operation
		 * Sequence::union(Sequence) with the description
		 * "The sequence consisting of all elements in self, followed by all elements in s"
		 * . Why aren't there Sequence::union(OrderedSet),
		 * OrderedSet::union(Sequence) and OrderedSet::union(OrderedSet) with
		 * the same semantics as Sequence::union(Sequence) ? That is most likely
		 * an overlook.
		 */
		ocl.assertQueryResults(null, "Set{'a', 'b', 'c', 'd'}", "Set{'a', 'b'}->union(Set{'c', 'd'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b', 'c', 'd'}", "Set{'a', 'b'}->union(Bag{'c', 'd'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b', 'c', 'd'}", "Bag{'a', 'b'}->union(Bag{'c', 'd'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b', 'c', 'd'}", "Bag{'a', 'b'}->union(Set{'c', 'd'})");

		//		ocl.assertQueryResults(null, "Sequence{'a', 'b', 'c', 'd'}", "Sequence{'a', 'b'}->union(Sequence{'c', 'd'})");
		ocl.assertQueryResults(null, "Bag{'a', 'b', 'c', 'd'}", "Sequence{'a', 'b'}->union(Sequence{'c', 'd'})");
		ocl.dispose();
	}

	@Test public void testCollectionUnionNull() {
		TestOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in s->union(Set{'a'})");
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in s->union(Bag{'a'})");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = null in b->union(Bag{'a'})");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = null in b->union(Set{'a'})");
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in s->union(Sequence{'a'})");

		ocl.assertQueryInvalid(null, "let s : Set(String) = null in Set{'a'}->union(s)");
		ocl.assertQueryInvalid(null, "let s : Set(String) = null in Bag{'a'}->union(s)");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = null in Bag{'a'}->union(b)");
		ocl.assertQueryInvalid(null, "let b : Bag(String) = null in Set{'a'}->union(b)");
		ocl.assertQueryInvalid(null, "let s : Sequence(String) = null in Sequence{'a'}->union(s)");
		ocl.dispose();
	}

	@Test public void testCollectionUnionNullValue() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Set{'a', null, 'b'}", "Set{'a', null}->union(Set{'b', null})");
		ocl.assertQueryResults(null, "Bag{'a', null, 'b', null}", "Set{'a', null}->union(Bag{'b', null})");
		ocl.assertQueryResults(null, "Bag{'a', null, 'b', null}", "Bag{'a', null}->union(Bag{'b', null})");
		ocl.assertQueryResults(null, "Bag{'a', null, 'b', null}", "Bag{'a', null}->union(Set{'b', null})");
		//		ocl.assertQueryResults(null, "Sequence{'a', null, 'b', null}", "Sequence{'a', null}->union(Sequence{'b', null})");
		ocl.assertQueryResults(null, "Bag{'a', null, 'b', null}", "Sequence{'a', null}->union(Sequence{'b', null})");
		ocl.dispose();
	}

	@Test public void testCollectionUpper() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 3, "Sequence{1, 2.0, '3'}->oclType().upper");
		ocl.assertQueryEquals(null, 3, "Sequence{1, 2.0, 3}->oclAsType(Collection(Real))->oclType().upper");
		ocl.assertQueryEquals(null, 3, "Set{1, 2.0, 3}->oclAsType(Collection(Real[2..4]))->oclType().upper"); // no change to dynamic bound
		ocl.dispose();
	}
}
