/*******************************************************************************
 * Copyright (c) 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;
import org.eclipse.ocl.xtext.base.cs2text.xtext.IndexVector;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class IndexVectorTests extends XtextTestCase
{
	public void testIndexVector_Ctor() throws Exception {
		assertEquals(new IndexVector().toString(), "[]");
		assertEquals(new IndexVector().setCapacity(5).toString(), "[]");
		assertEquals(new IndexVector().set(5).toString(), "[5]");
		assertEquals(new IndexVector().set(63).toString(), "[63]");
		assertEquals(new IndexVector().set(32).toString(), "[32]");
		assertEquals(new IndexVector().set(63).getCapacity(), 64);
		assertEquals(new IndexVector().set(64).getCapacity(), 128);
		assertEquals(new IndexVector().set(63).set(62).toString(), "[62,63]");
		assertEquals(new IndexVector().set(62).set(63).toString(), "[62,63]");
		assertEquals(new IndexVector().set(62).set(63).getCapacity(), 64);
		assertEquals(new IndexVector().set(62).set(63).set(64).toString(), "[62,63,64]");
		assertEquals(new IndexVector().set(62).set(63).set(64).getCapacity(), 128);
		assertEquals(new IndexVector().setCapacity(200).set(62).set(63).getCapacity(), 256);
		assertEquals(new IndexVector().set(62).set(63).setAll(new IndexVector().setCapacity(200).set(64).set(62)).toString(), "[62,63,64]");
	}

	public void testIndexVector_Equals() throws Exception {
		assertTrue(new IndexVector().set(63).equals(new IndexVector().set(63)));
		assertFalse(new IndexVector().set(63).set(64).equals(new IndexVector().set(64)));
		assertTrue(new IndexVector().set(63).set(64).equals(new IndexVector().set(64).set(63)));
		assertTrue(new IndexVector().set(63).set(64).equals(new IndexVector().setCapacity(256).set(64).set(63)));
		assertTrue(new IndexVector().setCapacity(256).set(63).set(64).equals(new IndexVector().set(64).set(63)));
	}

	public void testIndexVector_HashCode() throws Exception {
		assertEquals(Sets.newHashSet(new IndexVector().set(63).set(64), new IndexVector().setCapacity(256).set(64).set(63)).size(), 1);
	}

	public void testIndexVector_Iterable() throws Exception {
		assertEquals(Lists.newArrayList(new IndexVector().set(63).set(64)), Lists.newArrayList(63, 64));
	}

	public void testIndexVector_Test() throws Exception {
		IndexVector testValue = new IndexVector().set(62).set(63).setAll(new IndexVector().setCapacity(200).set(64).set(62));
		assertFalse(testValue.test(0));
		assertFalse(testValue.test(1));
		assertFalse(testValue.test(61));
		assertTrue(testValue.test(62));
		assertTrue(testValue.test(63));
		assertTrue(testValue.test(64));
		assertFalse(testValue.test(65));
		assertFalse(testValue.test(127));
		assertFalse(testValue.test(128));
		assertFalse(testValue.test(192));
		assertFalse(testValue.test(255));
		assertFalse(testValue.test(256));			// Checks inclusive index is not out of bounds
		assertFalse(testValue.test(257));
		assertFalse(testValue.test(1110));
	}
}
