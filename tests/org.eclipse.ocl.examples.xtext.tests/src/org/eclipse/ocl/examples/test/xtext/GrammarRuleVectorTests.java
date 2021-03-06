/*******************************************************************************
 * Copyright (c) 2018, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.ocl.examples.xtext.serializer.GrammarRuleVector;
import org.eclipse.ocl.examples.xtext.tests.XtextTestCase;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class GrammarRuleVectorTests extends XtextTestCase
{
	public void testGrammarRuleVector_Ctor() throws Exception {
		assertEquals(new GrammarRuleVector().toString(), "[]");
		assertEquals(new GrammarRuleVector().setCapacity(5).toString(), "[]");
		assertEquals(new GrammarRuleVector().set(5).toString(), "[5]");
		assertEquals(new GrammarRuleVector().set(63).toString(), "[63]");
		assertEquals(new GrammarRuleVector().set(32).toString(), "[32]");
		assertEquals(new GrammarRuleVector().set(63).getCapacity(), 64);
		assertEquals(new GrammarRuleVector().set(64).getCapacity(), 128);
		assertEquals(new GrammarRuleVector().set(63).set(62).toString(), "[62,63]");
		assertEquals(new GrammarRuleVector().set(62).set(63).toString(), "[62,63]");
		assertEquals(new GrammarRuleVector().set(62).set(63).getCapacity(), 64);
		assertEquals(new GrammarRuleVector().set(62).set(63).set(64).toString(), "[62,63,64]");
		assertEquals(new GrammarRuleVector().set(62).set(63).set(64).getCapacity(), 128);
		assertEquals(new GrammarRuleVector().setCapacity(200).set(62).set(63).getCapacity(), 256);
		assertEquals(new GrammarRuleVector().set(62).set(63).setAll(new GrammarRuleVector().setCapacity(200).set(64).set(62)).toString(), "[62,63,64]");
	}

	public void testGrammarRuleVector_CompareTo() throws Exception {
		assertTrue(new GrammarRuleVector().set(64).compareTo(new GrammarRuleVector().set(63)) > 0);
	}

	public void testGrammarRuleVector_Equals() throws Exception {
		assertTrue(new GrammarRuleVector().set(63).equals(new GrammarRuleVector().set(63)));
		assertFalse(new GrammarRuleVector().set(63).set(64).equals(new GrammarRuleVector().set(64)));
		assertTrue(new GrammarRuleVector().set(63).set(64).equals(new GrammarRuleVector().set(64).set(63)));
		assertTrue(new GrammarRuleVector().set(63).set(64).equals(new GrammarRuleVector().setCapacity(256).set(64).set(63)));
		assertTrue(new GrammarRuleVector().setCapacity(256).set(63).set(64).equals(new GrammarRuleVector().set(64).set(63)));
	}

	public void testGrammarRuleVector_HashCode() throws Exception {
		assertEquals(2, Sets.newHashSet(new GrammarRuleVector().set(63).set(64), new GrammarRuleVector().setCapacity(256).set(65).set(63)).size());
		assertEquals(1, Sets.newHashSet(new GrammarRuleVector().set(63).set(64), new GrammarRuleVector().setCapacity(256).set(64).set(63)).size());
	}

	public void testGrammarRuleVector_Iterable() throws Exception {
		assertEquals(Lists.newArrayList(new GrammarRuleVector().set(63).set(64)), Lists.newArrayList(63, 64));
	}

	public void testGrammarRuleVector_Test() throws Exception {
		GrammarRuleVector testValue = new GrammarRuleVector().set(62).set(63).setAll(new GrammarRuleVector().setCapacity(200).set(64).set(62));
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
