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
import java.util.regex.PatternSyntaxException;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for String operations.
 */
@RunWith(value = Parameterized.class)
public class EvaluateStringOperationsTest4 extends PivotTestSuite
{
	@Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][]{{false}, {true}};
		return Arrays.asList(data);
	}

	public EvaluateStringOperationsTest4(boolean useCodeGen) {
		super(useCodeGen);
	}

	@Override
	protected @NonNull TestOCL createOCL() {
		return new TestOCL(getTestFileSystem(), getTestPackageName(), getName(), useCodeGen ? getProjectMap() : OCL.NO_PROJECTS, null);
	}

	@Override
	protected @NonNull String getTestPackageName() {
		return "EvaluateStringOperations";
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

	@Test public void testStringAt() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "t", "'test'.at(1)");
		ocl.assertQueryEquals(null, "e", "'test'.at(2)");
		ocl.assertQueryEquals(null, "t", "'test'.at(4)");
		// out of bounds
		ocl.assertQueryInvalid(null, "'test'.at(0)");
		ocl.assertQueryInvalid(null, "'test'.at(5)");
		ocl.assertQueryInvalid(null, "''.at(1)");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.at(1)");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.at(1)");
		ocl.dispose();
	}

	@Test public void testStringCharacters() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, new String[] {}, "''.characters()");
		ocl.assertQueryEquals(null, new String[] {"a"}, "'a'.characters()");
		ocl.assertQueryEquals(null, new String[] {"a", "\r", "\n", "b"}, "'a\\r\nb'.characters()");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.characters()");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.characters()");
		ocl.dispose();
	}

	@Test public void testStringConcat() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "concatenationTest", "'concatenation'.concat('Test')");
		ocl.assertQueryEquals(null, "concatenation\n", "'concatenation'.concat('\\n')");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'concatenation'.concat(s)");
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.concat('concatenation')");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in 'concatenation'.concat(s)");
		ocl.assertQueryInvalid(null, "let s : String = null in s.concat('concatenation')");
		ocl.dispose();
	}

	@Test public void testStringEndsWith() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "'abcdef'.endsWith('aabcdef')");
		ocl.assertQueryTrue(null, "'abcdef'.endsWith('abcdef')");
		ocl.assertQueryTrue(null, "'abcdef'.endsWith('cdef')");
		ocl.assertQueryTrue(null, "'abcdef'.endsWith('f')");
		ocl.assertQueryTrue(null, "'abcdef'.endsWith('')");
		ocl.assertQueryTrue(null, "''.endsWith('')");
		ocl.assertQueryFalse(null, "''.endsWith('a')");
		ocl.assertQueryTrue(null, "'abcdef'.endsWith('')");
		ocl.assertQueryFalse(null, "'abcdef'.endsWith('bcd')");
		ocl.assertQueryFalse(null, "'abcdef'.endsWith('ab')");
		ocl.assertQueryFalse(null, "'abcdef'.endsWith('a')");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.endsWith('')");
		ocl.assertQueryInvalid(null, "let s : String = invalid in ''.endsWith(s)");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.endsWith('')");
		ocl.assertQueryInvalid(null, "let s : String = null in ''.endsWith(s)");
		ocl.dispose();
	}

	@Test public void testStringEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "'test' = 'se'");
		ocl.assertQueryTrue(null, "'test' = 'test'");
		ocl.assertQueryFalse(null, "'tESt' = 'TesT'");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s = 'se'");
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'test' = s");
		ocl.assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 = s2");
		// null
		ocl.assertQueryFalse(null, "let s : String = null in s = 'se'");
		ocl.assertQueryFalse(null, "let s : String = null in 'test' = s");
		ocl.assertQueryTrue(null, "let s1 : String = null, s2 : String = null in s1 = s2");
		ocl.dispose();
	}

	@Test public void testStringEqualIgnoresCase() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "'test'.equalsIgnoreCase('se')");
		ocl.assertQueryTrue(null, "'test'.equalsIgnoreCase('test')");
		ocl.assertQueryTrue(null, "'Test'.equalsIgnoreCase('tEst')");
		ocl.assertQueryTrue(null, "'tesT'.equalsIgnoreCase('teSt')");
		ocl.assertQueryTrue(null, "'TEST'.equalsIgnoreCase('test')");
		ocl.assertQueryTrue(null, "'test'.equalsIgnoreCase('TEST')");
		ocl.dispose();
	}

	@Test public void testStringGreaterThan() {
		TestOCL ocl = createOCL();
		// FIXME Analyzer-extraOperation String::> should not be defined
		ocl.assertQueryFalse(null, "'3' > '4'");
		ocl.assertQueryFalse(null, "'a' > 'b'");
		ocl.assertQueryFalse(null, "'aardvark' > 'aardvarks'");

		ocl.assertQueryTrue(null, "'3.2' > '3.1'");
		ocl.assertQueryTrue(null, "'a' > 'A'");
		ocl.assertQueryTrue(null, "'aardvark' > 'aardvarK'");

		ocl.assertQueryFalse(null, "'3' > '3'");
		ocl.assertQueryFalse(null, "'a' > 'a'");
		ocl.assertQueryFalse(null, "'aardvark' > 'aardvark'");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s > 'se'");
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'test' > s");
		ocl.assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 > s2");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s > 'se'");
		ocl.assertQueryInvalid(null, "let s : String = null in 'test' > s");
		ocl.assertQueryInvalid(null, "let s1 : String = null, s2 : String = null in s1 > s2");
		ocl.dispose();
	}

	@Test public void testStringGreaterThanOrEqual() {
		TestOCL ocl = createOCL();
		// FIXME Analyzer-extraOperation String::>= should not be defined
		ocl.assertQueryFalse(null, "'3' >= '4'");
		ocl.assertQueryFalse(null, "'a' >= 'b'");
		ocl.assertQueryFalse(null, "'aardvark' >= 'aardvarks'");

		ocl.assertQueryTrue(null, "'3.2' >= '3.1'");
		ocl.assertQueryTrue(null, "'a' >= 'A'");
		ocl.assertQueryTrue(null, "'aardvark' >= 'aardvarK'");

		ocl.assertQueryTrue(null, "'3' >= '3'");
		ocl.assertQueryTrue(null, "'a' >= 'a'");
		ocl.assertQueryTrue(null, "'aardvark' >= 'aardvark'");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s >= 'se'");
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'test' >= s");
		ocl.assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 >= s2");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s >= 'se'");
		ocl.assertQueryInvalid(null, "let s : String = null in 'test' >= s");
		ocl.assertQueryInvalid(null, "let s1 : String = null, s2 : String = null in s1 >= s2");
		ocl.dispose();
	}

	@Test public void testStringIndexOf() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 1, "'test'.indexOf('t')");
		ocl.assertQueryEquals(null, 1, "'test'.indexOf('te')");
		ocl.assertQueryEquals(null, 2, "'test'.indexOf('es')");
		ocl.assertQueryEquals(null, 3, "'test'.indexOf('st')");
		ocl.assertQueryEquals(null, 5, "'tesla'.indexOf('a')");
		// out of bounds
		ocl.assertQueryEquals(null, 0, "'test'.indexOf('xyzzy')");
		ocl.assertQueryEquals(null, 0, "'test'.indexOf('est2')");
		// empty
		ocl.assertQueryEquals(null, 1, "'test'.indexOf('')");
		ocl.assertQueryEquals(null, 1, "''.indexOf('')");
		ocl.assertQueryEquals(null, 0, "''.indexOf('t')");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'test'.indexOf(s)");
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.indexOf('s')");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in 'test'.indexOf(s)");
		ocl.assertQueryInvalid(null, "let s : String = null in s.indexOf('s')");
		ocl.dispose();
	}

	@Test public void testStringLastIndexOf() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 4, "'test'.lastIndexOf('t')");
		ocl.assertQueryEquals(null, 1, "'test'.lastIndexOf('te')");
		ocl.assertQueryEquals(null, 2, "'test'.lastIndexOf('es')");
		ocl.assertQueryEquals(null, 3, "'test'.lastIndexOf('st')");
		ocl.assertQueryEquals(null, 5, "'tesla'.lastIndexOf('a')");
		ocl.assertQueryEquals(null, 1, "'ates'.lastIndexOf('a')");
		// out of bounds
		ocl.assertQueryEquals(null, 0, "'test'.lastIndexOf('xyzzy')");
		ocl.assertQueryEquals(null, 0, "'test'.lastIndexOf('est2')");
		// empty
		ocl.assertQueryEquals(null, 5, "'test'.lastIndexOf('')");
		ocl.assertQueryEquals(null, 1, "''.lastIndexOf('')");
		ocl.assertQueryEquals(null, 0, "''.lastIndexOf('t')");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'test'.lastIndexOf(s)");
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.lastIndexOf('s')");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in 'test'.lastIndexOf(s)");
		ocl.assertQueryInvalid(null, "let s : String = null in s.lastIndexOf('s')");
		ocl.dispose();
	}

	@Test public void testStringLessThan() {
		TestOCL ocl = createOCL();
		// FIXME Analyzer-extraOperation String::< should not be defined
		ocl.assertQueryTrue(null, "'3' < '4'");
		ocl.assertQueryTrue(null, "'a' < 'b'");
		ocl.assertQueryTrue(null, "'aardvark' < 'aardvarks'");

		ocl.assertQueryFalse(null, "'3.2' < '3.1'");
		ocl.assertQueryFalse(null, "'a' < 'A'");
		ocl.assertQueryFalse(null, "'aardvark' < 'aardvarK'");

		ocl.assertQueryFalse(null, "'3' < '3'");
		ocl.assertQueryFalse(null, "'a' < 'a'");
		ocl.assertQueryFalse(null, "'aardvark' < 'aardvark'");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s < 'se'");
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'test' < s");
		ocl.assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 < s2");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s < 'se'");
		ocl.assertQueryInvalid(null, "let s : String = null in 'test' < s");
		ocl.assertQueryInvalid(null, "let s1 : String = null, s2 : String = null in s1 < s2");
		ocl.dispose();
	}

	@Test public void testStringLessThanOrEqual() {
		TestOCL ocl = createOCL();
		// FIXME Analyzer-extraOperation String::<= should not be defined
		ocl.assertQueryTrue(null, "'3' <= '4'");
		ocl.assertQueryTrue(null, "'a' <= 'b'");
		ocl.assertQueryTrue(null, "'aardvark' <= 'aardvarks'");

		ocl.assertQueryFalse(null, "'3.2' <= '3.1'");
		ocl.assertQueryFalse(null, "'a' <= 'A'");
		ocl.assertQueryFalse(null, "'aardvark' <= 'aardvarK'");

		ocl.assertQueryTrue(null, "'3' <= '3'");
		ocl.assertQueryTrue(null, "'a' <= 'a'");
		ocl.assertQueryTrue(null, "'aardvark' <= 'aardvark'");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s <= 'se'");
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'test' <= s");
		ocl.assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 <= s2");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s <= 'se'");
		ocl.assertQueryInvalid(null, "let s : String = null in 'test' <= s");
		ocl.assertQueryInvalid(null, "let s1 : String = null, s2 : String = null in s1 <= s2");
		ocl.dispose();
	}

	@Test public void testStringMatches() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "'characters and spaces'.matches('[\\\\w\\\\s]+')");		// *2 for Java, *2 for OCL
		ocl.assertQueryFalse(null, "'characters and 3 digits'.matches('[\\\\p{Alpha}\\\\s]+')");
		//
		ocl.assertQueryTrue(null, "''.matches('')");
		ocl.assertQueryTrue(null, "''.matches('')");
		ocl.assertQueryFalse(null, "'a'.matches('')");
		ocl.assertQueryFalse(null, "''.matches('b')");
		//
		ocl.assertQueryInvalid(null, "'repla ce operation'.matches('a[b-')", null, PatternSyntaxException.class);
		//
		ocl.assertQueryInvalid(null, "let s : String = null in s.matches('(\\\\w+)\\\\s*')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.matches(null)");
		//
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.matches('(\\\\w+)\\\\s*')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.matches(invalid)");
		// -- visual inspection of println's demonstrates cache re-use; this test just conforms cache still ok once full
		ocl.assertQueryEquals(null, 50, "let seq = Sequence{1..20}, rseq = seq->reverse(), seqs = Sequence{seq,rseq,seq,rseq,seq}->flatten() in seqs->iterate(i; acc : Integer = 0 | if '123456789'.matches('.*' + i.toString() + '.*') then acc + 1 else acc endif)");
		ocl.dispose();
	}

	@Test public void testStringNotEqual() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "'test' <> 'se'");
		ocl.assertQueryFalse(null, "'test' <> 'test'");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s <> 'se'");
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'test' <> s");
		ocl.assertQueryInvalid(null, "let s1 : String = invalid, s2 : String = invalid in s1 <> s2");
		// null
		ocl.assertQueryTrue(null, "let s : String = null in s <> 'se'");
		ocl.assertQueryTrue(null, "let s : String = null in 'test' <> s");
		ocl.assertQueryFalse(null, "let s1 : String = null, s2 : String = null in s1 <> s2");
		ocl.dispose();
	}

	@Test public void testStringOclAsType() {
		TestOCL ocl = createOCL();
		ocl.assertQueryInvalid(null, "'test'.oclAsType(Integer)");
		ocl.assertQueryEquals(null, "test", "'test'.oclAsType(String)");
		ocl.assertQueryEquals(null, "test", "'test'.oclAsType(OclAny)");
		ocl.assertQueryInvalid(null, "'test'.oclAsType(OclVoid)");
		ocl.assertQueryInvalid(null, "'test'.oclAsType(OclInvalid)");
		ocl.dispose();
	}

	@Test public void testStringOclIsInvalid() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "'test'.oclIsInvalid()");
		ocl.assertQueryFalse(null, "''.oclIsInvalid()");
		ocl.dispose();
	}

	@Test public void testStringOclIsKindOf() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "'test'.oclIsKindOf(Integer)");
		ocl.assertQueryTrue(null, "'test'.oclIsKindOf(String)");
		ocl.assertQueryTrue(null, "'test'.oclIsKindOf(OclAny)");
		ocl.assertQueryFalse(null, "'test'.oclIsKindOf(OclVoid)");
		ocl.assertQueryFalse(null, "'test'.oclIsKindOf(OclInvalid)");
		ocl.dispose();
	}

	@Test public void testStringOclIsTypeOf() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "'test'.oclIsTypeOf(Integer)");
		ocl.assertQueryTrue(null, "'test'.oclIsTypeOf(String)");
		ocl.assertQueryFalse(null, "'test'.oclIsTypeOf(OclAny)");
		ocl.assertQueryFalse(null, "'test'.oclIsTypeOf(OclVoid)");
		ocl.assertQueryFalse(null, "'test'.oclIsTypeOf(OclInvalid)");
		ocl.dispose();
	}

	@Test public void testStringOclIsUndefined() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "'test'.oclIsUndefined()");
		ocl.assertQueryFalse(null, "''.oclIsUndefined()");
		ocl.dispose();
	}

	@Test public void testStringPlus() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "concatenationTest", "'concatenation' + 'Test'");
		ocl.assertQueryEquals(null, "concatenation\n", "'concatenation' + '\\n'");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in 'concatenation' + s");
		ocl.assertQueryInvalid(null, "let s : String = invalid in s + 'concatenation'");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in 'concatenation' + s");
		ocl.assertQueryInvalid(null, "let s : String = null in s + 'concatenation'");
		ocl.dispose();
	}

	@Test public void testStringReplaceAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "rePlaceAll oPeration", "'replaceAll operation'.replaceAll('p', 'P')");
		ocl.assertQueryEquals(null, "ReplaceAllOperation", "'Repla ce All Operation'.replaceAll('(\\\\w+)\\\\s*', '$1')");
		//
		ocl.assertQueryEquals(null, "xx", "''.replaceAll('', 'xx')");
		ocl.assertQueryEquals(null, "xxrxxexxpxxlxxaxx xxcxxexx xxoxxpxxexxrxxaxxtxxixxoxxnxx", "'repla ce operation'.replaceAll('', 'xx')");
		ocl.assertQueryEquals(null, "", "'repla ce operation'.replaceAll('(\\\\w+)\\\\s*', '')");
		ocl.assertQueryEquals(null, "repla ce operation", "'repla ce operation'.replaceAll('', '')");
		//
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceAll('a[b-', '$1')", null, PatternSyntaxException.class);
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceAll('', '$1')", "No group 1", IndexOutOfBoundsException.class);
		//
		ocl.assertQueryInvalid(null, "let s : String = null in s.replaceAll('(\\\\w+)\\\\s*', '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceAll(null, '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceAll('(\\\\w+)\\\\s*', null)");
		//
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.replaceAll('(\\\\w+)\\\\s*', '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceAll(invalid, '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceAll('(\\\\w+)\\\\s*', invalid)");
		ocl.dispose();
	}

	@Test public void testStringReplaceFirst() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "rePlace operation", "'replace operation'.replaceFirst('p', 'P')");
		ocl.assertQueryEquals(null, "replace operation", "'repla ce operation'.replaceFirst('(\\\\w+)\\\\s*', '$1')");
		//
		ocl.assertQueryEquals(null, "xx", "''.replaceFirst('', 'xx')");
		ocl.assertQueryEquals(null, "xxrepla ce operation", "'repla ce operation'.replaceFirst('', 'xx')");
		ocl.assertQueryEquals(null, "ce operation", "'repla ce operation'.replaceFirst('(\\\\w+)\\\\s*', '')");
		ocl.assertQueryEquals(null, "repla ce operation", "'repla ce operation'.replaceFirst('', '')");
		//
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceFirst('a[b-', '$1')", null, PatternSyntaxException.class);
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceFirst('', '$1')", "No group 1", IndexOutOfBoundsException.class);
		//
		ocl.assertQueryInvalid(null, "let s : String = null in s.replaceFirst('(\\\\w+)\\\\s*', '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceFirst(null, '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceFirst('(\\\\w+)\\\\s*', null)");
		//
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.replaceFirst('(\\\\w+)\\\\s*', '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceFirst(invalid, '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.replaceFirst('(\\\\w+)\\\\s*', invalid)");
		ocl.dispose();
	}

	@Test public void testStringSize() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, Integer.valueOf(4), "'test'.size()");
		ocl.assertQueryEquals(null, Integer.valueOf(0), "''.size()");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.size()");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.size()");
		ocl.dispose();
	}

	@Test public void testStringStartsWith() {
		TestOCL ocl = createOCL();
		ocl.assertQueryFalse(null, "'abcdef'.startsWith('abcdefg')");
		ocl.assertQueryTrue(null, "'abcdef'.startsWith('abcdef')");
		ocl.assertQueryTrue(null, "'abcdef'.startsWith('abcd')");
		ocl.assertQueryTrue(null, "'abcdef'.startsWith('a')");
		ocl.assertQueryTrue(null, "'abcdef'.startsWith('')");
		ocl.assertQueryTrue(null, "''.startsWith('')");
		ocl.assertQueryFalse(null, "''.startsWith('a')");
		ocl.assertQueryTrue(null, "'abcdef'.startsWith('')");
		ocl.assertQueryFalse(null, "'abcdef'.startsWith('bcd')");
		ocl.assertQueryFalse(null, "'abcdef'.startsWith('ef')");
		ocl.assertQueryFalse(null, "'abcdef'.startsWith('f')");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.startsWith('')");
		ocl.assertQueryInvalid(null, "let s : String = invalid in ''.startsWith(s)");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.startsWith('')");
		ocl.assertQueryInvalid(null, "let s : String = null in ''.startsWith(s)");
		ocl.dispose();
	}

	@Test public void testStringSubstituteAll() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "subsTiTuTeAll operaTion", "'substituteAll operation'.substituteAll('t', 'T')");
		//
		ocl.assertQueryEquals(null, "xx", "''.replaceAll('', 'xx')");
		ocl.assertQueryEquals(null, "xxrxxexxpxxlxxaxx xxcxxexx xxoxxpxxexxrxxaxxtxxixxoxxnxx", "'repla ce operation'.substituteAll('', 'xx')");
		ocl.assertQueryEquals(null, "repla ce operation", "'repla ce operation'.substituteAll('(\\\\w+)\\\\s*', '')");
		ocl.assertQueryEquals(null, "repla ce operation", "'repla ce operation'.substituteAll('', '')");
		//
		ocl.assertQueryInvalid(null, "let s : String = null in s.substituteAll('(\\\\w+)\\\\s*', '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.substituteAll(null, '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.substituteAll('(\\\\w+)\\\\s*', null)");
		//
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.substituteAll('(\\\\w+)\\\\s*', '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.substituteAll(invalid, '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.substituteAll('(\\\\w+)\\\\s*', invalid)");
		ocl.dispose();
	}

	@Test public void testStringSubstituteFirst() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "subsTiTuTeFirst operaTion", "'substiTuTeFirst operaTion'.substituteFirst('t', 'T')");
		ocl.assertQueryEquals(null, "SubstiTuTeFirst operaTion", "'substiTuTeFirst operaTion'.substituteFirst('s', 'S')");
		ocl.assertQueryEquals(null, "substiTuTeFirst operaTioN", "'substiTuTeFirst operaTion'.substituteFirst('n', 'N')");
		ocl.assertQueryEquals(null, "substiTuTeFirst operaTion", "'substiTuTeFirst operaTion'.substituteFirst('n', 'n')");
		ocl.assertQueryEquals(null, "substiTuTeFirst operaTiON", "'substiTuTeFirst operaTion'.substituteFirst('on', 'ON')");
		ocl.assertQueryEquals(null, "a[b-c]d\r\n*", "'a[b-c]d\\\\w*'.substituteFirst('\\\\w', '\r\n')");
		//
		ocl.assertQueryEquals(null, "xx", "''.substituteFirst('', 'xx')");
		ocl.assertQueryEquals(null, "xxrepla ce operation", "'repla ce operation'.substituteFirst('', 'xx')");
		ocl.assertQueryEquals(null, "repla ce operation", "'repla ce operation'.substituteFirst('', '')");
		//
		ocl.assertQueryInvalid(null, "'repla ce operation'.substituteFirst('(\\\\w+)\\\\s*', '')",
			StringUtil.bind(PivotMessages.MissingSubstring, "(\\w+)\\s*", "repla ce operation"), null);
		//
		ocl.assertQueryInvalid(null, "let s : String = null in s.substituteFirst('(\\\\w+)\\\\s*', '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.substituteFirst(null, '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.substituteFirst('(\\\\w+)\\\\s*', null)");
		//
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.substituteFirst('(\\\\w+)\\\\s*', '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.substituteFirst(invalid, '$1')");
		ocl.assertQueryInvalid(null, "'repla ce operation'.substituteFirst('(\\\\w+)\\\\s*', invalid)");
		ocl.dispose();
	}

	@Test public void testStringSubstring() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "t", "'test'.substring(1, 1)");
		ocl.assertQueryEquals(null, "es", "'test'.substring(2, 3)");
		ocl.assertQueryEquals(null, "t", "'test'.substring(4, 4)");
		// illegal
		ocl.assertQueryInvalid(null, "'test'.substring(2, 1)");
		ocl.assertQueryInvalid(null, "'test'.substring(3, 1)");
		// out of bounds
		ocl.assertQueryInvalid(null, "'test'.substring(0, 1)");
		ocl.assertQueryInvalid(null, "'test'.substring(4, 5)");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.substring(1, 1)");
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.substring(5, 5)");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.substring(1, 1)");
		ocl.assertQueryInvalid(null, "let s : String = null in s.substring(5, 5)");
		ocl.dispose();
	}

	@Test public void testStringToBoolean() {
		TestOCL ocl = createOCL();
		ocl.assertQueryTrue(null, "'true'.toBoolean()");
		ocl.assertQueryNull(null, "' true'.toBoolean()");
		ocl.assertQueryNull(null, "'true '.toBoolean()");
		ocl.assertQueryNull(null, "'True'.toBoolean()");
		ocl.assertQueryFalse(null, "'false'.toBoolean()");
		ocl.assertQueryNull(null, "'-4'.toBoolean()");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.toBoolean()");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.toBoolean()");
		ocl.dispose();
	}

	@Test public void testStringToInteger() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, Integer.valueOf(4), "'4'.toInteger()");
		ocl.assertQueryEquals(null, Integer.valueOf(-4), "'-4'.toInteger()");
		ocl.assertQueryNull(null, "'4.0'.toInteger()");

		ocl.assertQueryNull(null, "'2.4.0'.toInteger()");
		ocl.assertQueryNull(null, "'a'.toInteger()");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.toInteger()");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.toInteger()");
		ocl.dispose();
	}

	@Test public void testStringToLowerCase() {
		TestOCL ocl = createOCL();
		//		checkForUTF8Encoding()
		ocl.assertQueryEquals(null, "4", "'4'.toLowerCase()"); //$NON-NLS-2$
		ocl.assertQueryEquals(null, "mixed", "'MiXeD'.toLowerCase()"); //$NON-NLS-2$
		ocl.assertQueryEquals(null, "upper", "'UPPER'.toLowerCase()"); //$NON-NLS-2$
		// Ensures word-final sigma and regular sigmas are converted as needed
		// TODO re-enable once the Unicode problems on Hudson have been resolved
		//		ocl.assertQueryEquals(null, "ὀδυσσεύς", "'ὈΔΥΣΣΕΎΣ'.toLowerCase()");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.toLowerCase()");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.toLowerCase()");
		ocl.dispose();
	}

	@Test public void testStringToReal() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, 4.0, "'4'.toReal()", 0.0);
		ocl.assertQueryEquals(null, -4.0, "'-4'.toReal()", 0.0);
		ocl.assertQueryEquals(null, 4.0, "'4.0'.toReal()", 0.0);

		ocl.assertQueryNull(null, "'2.4.0'.toReal()");
		ocl.assertQueryNull(null, "'a'.toReal()");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.toReal()");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.toReal()");
		ocl.dispose();
	}

	@Test public void testStringToString() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "4.0", "'4.0'.toString()");
		ocl.assertQueryEquals(null, "4.0\t4", "('4.0' + '\t' + '4').toString()");
		ocl.dispose();
	}

	@Test public void testStringToUpperCase() {
		TestOCL ocl = createOCL();
		//		checkForUTF8Encoding();
		ocl.assertQueryEquals(null, "4", "'4'.toUpperCase()");
		ocl.assertQueryEquals(null, "MIXED", "'MiXeD'.toUpperCase()");
		ocl.assertQueryEquals(null, "LOWER", "'lower'.toUpperCase()");

		// Ensures word-final sigma and regular sigmas are converted as needed
		// TODO re-enable once the Unicode problems on Hudson have been resolved
		//		ocl.assertQueryEquals(null, "ὈΔΥΣΣΕΎΣ", "'ὀδυσσεύς'.toUpperCase()");

		// Sharp s should be mapped to a double S upper case
		// TODO re-enable once the Unicode problems on Hudson have been resolved
		//		ocl.assertQueryEquals(null, "SS", "'ß'.toUpperCase()");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.toUpperCase()");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.toUpperCase()");
		ocl.dispose();
	}

	@Test public void testStringTokenize() {
		TestOCL ocl = createOCL();
		ocl.assertQueryResults(null, "Sequence{'a','b','c','d'}", "'\na b\tc\fd\r'.tokenize()");
		ocl.assertQueryResults(null, "Sequence{'a','b','c','d'}", "' \t\n\r\fa b\tc\fd \t\n\r\f'.tokenize()");
		ocl.assertQueryResults(null, "Sequence{' ','\t','\n','\r','\f','a',' ','b','\t','c','\f','d',' ','\t','\n','\r','\f'}", "' \t\n\r\fa b\tc\fd \t\n\r\f'.tokenize(' \t\n\r\f', true)");
		ocl.assertQueryResults(null, "Sequence{'\na',' ', 'b\tc\fd\r'}", "'\na b\tc\fd\r'.tokenize(' ', true)");
		ocl.assertQueryResults(null, "Sequence{'\na','b\tc\fd\r'}", "'\na b\tc\fd\r'.tokenize(' ')");
		ocl.assertQueryResults(null, "Sequence{'1','2','3','4'}", "'1.2.3.4'.tokenize('.')");					// BUG 422296
		ocl.assertQueryResults(null, "Sequence{}", "''.tokenize(' ', true)");
		ocl.assertQueryResults(null, "Sequence{' \t\n\r\f'}", "' \t\n\r\f'.tokenize('', true)");
		ocl.assertQueryResults(null, "Sequence{}", "''.tokenize('', true)");
		ocl.assertQueryResults(null, "Sequence{}", "''.tokenize(' \t\n\r\f', true)");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.tokenize()");
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.tokenize('')");
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.tokenize('',true)");
		ocl.assertQueryInvalid(null, "let s : String = invalid in ''.tokenize(s)");
		ocl.assertQueryInvalid(null, "let s : String = invalid in ''.tokenize(s,true)");
		ocl.assertQueryInvalid(null, "let b : Boolean = invalid in ''.tokenize('',b)");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.tokenize()");
		ocl.assertQueryInvalid(null, "let s : String = null in s.tokenize('')");
		ocl.assertQueryInvalid(null, "let s : String = null in s.tokenize('',true)");
		ocl.assertQueryInvalid(null, "let s : String = null in ''.tokenize(s)");
		ocl.assertQueryInvalid(null, "let s : String = null in ''.tokenize(s,true)");
		ocl.assertQueryInvalid(null, "let b : Boolean = null in ''.tokenize('',b)");
		// cached
		ocl.assertQueryTrue(null, "'1.2.3.4'.tokenize('.')='1.2.3.4'.tokenize('.')");

		ocl.assertSemanticErrorQuery(null, "''.tokenize('',false,null)", PivotMessagesInternal.UnresolvedOperationCall_ERROR_, "String", "tokenize", "'',false,null");
		ocl.dispose();
	}

	@Test public void testStringTrim() {
		TestOCL ocl = createOCL();
		ocl.assertQueryEquals(null, "ab", "'ab'.trim()");
		ocl.assertQueryEquals(null, "a", "'a'.trim()");
		ocl.assertQueryEquals(null, "", "''.trim()");
		ocl.assertQueryEquals(null, "a \t\n\r\fb", "'\na \t\n\r\fb\n'.trim()");
		ocl.assertQueryEquals(null, "", "' \t\n\r\f \t\n\r\f'.trim()");
		// invalid
		ocl.assertQueryInvalid(null, "let s : String = invalid in s.trim()");
		// null
		ocl.assertQueryInvalid(null, "let s : String = null in s.trim()");
		ocl.dispose();
	}
}
