/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************/
package org.eclipse.ocl.examples.eventmanager.tests.framework;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.ocl.examples.eventmanager.tests.util.BaseTest;
import org.eclipse.ocl.examples.eventmanager.util.CompositeCollection;
import org.eclipse.ocl.examples.eventmanager.util.CompositeSet;
import org.junit.Before;
import org.junit.Test;



public class CompositeSetTest extends BaseTest {
	private Set<Integer> emptySet = Collections.emptySet();
	private Set<Integer> firstSet;
	private Set<Integer> secondSet;

	@Override
	@Before
	public void setUp() {
		firstSet = new HashSet<Integer>();
		firstSet.add(1);
		firstSet.add(2);
		firstSet.add(3);
		secondSet = new HashSet<Integer>();
		secondSet.add(4);
		secondSet.add(5);
		secondSet.add(6);
	}

	@Test
	public void testEmpty() {
		@SuppressWarnings("unchecked")
		CompositeCollection<Integer> cs = new CompositeSet<Integer>();
		assertTrue(cs.isEmpty());
		assertEquals(0, cs.size());
	}

	@Test
	public void testSimpleSet() {
		@SuppressWarnings("unchecked")
		CompositeCollection<Integer> cs = new CompositeSet<Integer>(firstSet);
		assertFalse(cs.isEmpty());
		assertEquals(firstSet.size(), cs.size());
		assertTrue(firstSet.containsAll(cs));
		assertTrue(cs.containsAll(firstSet));
		assertEquals(cs, firstSet);
	}

	@Test
	public void testTwoSets() {
		@SuppressWarnings("unchecked")
		CompositeCollection<Integer> cs = new CompositeSet<Integer>(firstSet, secondSet);
		assertFalse(cs.isEmpty());
		assertEquals(firstSet.size()+secondSet.size(), cs.size());
		assertTrue(cs.containsAll(firstSet));
		assertTrue(cs.containsAll(secondSet));
		assertContainsAll(cs, firstSet);
		assertContainsAll(cs, secondSet);
	}

	@Test
	public void testIntermittentEmptySets() {
		@SuppressWarnings("unchecked")
		CompositeCollection<Integer> cs = new CompositeSet<Integer>(emptySet, firstSet, emptySet, secondSet, emptySet);
		assertFalse(cs.isEmpty());
		assertEquals(firstSet.size()+secondSet.size(), cs.size());
		assertTrue(cs.containsAll(firstSet));
		assertTrue(cs.containsAll(secondSet));
		assertContainsAll(cs, firstSet);
		assertContainsAll(cs, secondSet);
	}

	private <T> void assertContainsAll(CompositeCollection<T> cs, Collection<T> other) {
		Set<T> notFound = new HashSet<T>(other);
		for (T t : cs) {
			notFound.remove(t);
		}
		assertEquals("Didn't find elements "+notFound+" in composite set's iterator", 0, notFound.size());
	}

}
