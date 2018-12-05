/**
 * Copyright (c) 2009, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *     Axel Uhl (SAP AG) - Bug 342644
 */
package org.eclipse.ocl.tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

// FIXME we're missing oclIsNew and oclIsInState
/**
 * This unit test focuses on the Standard library's String operations.
 * 
 * @author Laurent Goubet (lgoubet)
 */
@SuppressWarnings("nls")
public abstract class GenericEvaluationStringOperationTest<E extends EObject, PK extends E, T extends E, C extends T, CLS extends C, DT extends C, PT extends C, ET extends DT, O extends E, PM extends E, P extends E, PA extends P, PR extends P, EL, S, COA, SSA, CT>
	extends GenericEvaluationTestSuite<E, PK, T, C, CLS, DT, PT, ET, O, PM, P, PA, PR, EL, S, COA, SSA, CT> {

	public void testStringAt() {
		assertResult("t", "'test'.at(1)");
		assertResult("e", "'test'.at(2)");
		assertResult("t", "'test'.at(4)");
		// out of bounds
		assertResultInvalid("'test'.at(0)");
		assertResultInvalid("'test'.at(5)");
		assertResultInvalid("''.at(1)");
		// invalid
		assertResultInvalid("let s : String = invalid in s.at(1)");
		// null
		assertResultInvalid("let s : String = null in s.at(1)");
	}

	private List<String> buildString(String... strings) {
		List<String> result = new ArrayList<String>();
		for (String s : strings) {
			for (int i = 0; i < s.length(); i++) {
				result.add(s.substring(i, i+1));
			}
		}
		return result;
	}
	
	
	public void testStringCharacters() {
		assertResult(buildString(), "''.characters()");
		assertResult(buildString("a"), "'a'.characters()");
		assertResult(buildString("a", "\r", "\n", "b"), "'a\\r\\nb'.characters()");
		// invalid
		assertResultInvalid("let s : String = invalid in s.characters()");
		// null
		assertResultInvalid("let s : String = null in s.characters()");
	}

	public void testStringConcat() {
		assertResult("concatenationTest", "'concatenation'.concat('Test')");
		// according to 9.3.20, StringLiteralExpCS, '\' 'n' is the escape for a life feed LF
		assertResult("concatenation\n", "'concatenation'.concat('\\n')");
		assertResult("concatenationTest", "'concatenation' + 'Test'");
		// according to 9.3.20, StringLiteralExpCS, '\' 'n' is the escape for a life feed LF
		assertResult("concatenation\n", "'concatenation' + '\\n'");
	}

	public void testStringConcatInvalid() {
		assertResultInvalid("let s : String = invalid in 'concatenation'.concat(s)");
		assertResultInvalid("let s : String = invalid in s.concat('concatenation')");
		assertResultInvalid("let s : String = invalid in 'concatenation' + s");
		assertResultInvalid("let s : String = invalid in s + 'concatenation'");
	}

	public void testStringConcatNull() {
		assertResultInvalid("let s : String = null in 'concatenation'.concat(s)");
		assertResultInvalid("let s : String = null in s.concat('concatenation')");
		assertResultInvalid("let s : String = null in 'concatenation' + s");
		assertResultInvalid("let s : String = null in s + 'concatenation'");
	}

	public void testStringEndsWith() {
		assertResultFalse("'abcdef'.endsWith('aabcdef')");
		assertResultTrue("'abcdef'.endsWith('abcdef')");
		assertResultTrue("'abcdef'.endsWith('cdef')");
		assertResultTrue("'abcdef'.endsWith('f')");
		assertResultTrue("'abcdef'.endsWith('')");
		assertResultTrue("''.endsWith('')");
		assertResultFalse("''.endsWith('a')");
		assertResultTrue("'abcdef'.endsWith('')");
		assertResultFalse("'abcdef'.endsWith('bcd')");
		assertResultFalse("'abcdef'.endsWith('ab')");
		assertResultFalse("'abcdef'.endsWith('a')");
		// invalid
		assertResultInvalid("let s : String = invalid in s.endsWith('')");
		assertResultInvalid("let s : String = invalid in ''.endsWith(s)");
		// null
		assertResultInvalid("let s : String = null in s.endsWith('')");
		assertResultInvalid("let s : String = null in ''.endsWith(s)");
	}

	public void testStringEqual() {
		assertResultFalse("'test' = 'se'");
		assertResultTrue("'test' = 'test'");
	}

	public void testStringEqualInvalid() {
		assertResultInvalid("let s : String = invalid in s = 'se'");
		assertResultInvalid("let s : String = invalid in 'test' = s");

		assertResultInvalid("let s1 : String = invalid, s2 : String = invalid in s1 = s2");
	}

	public void testStringEqualNull() {
		assertResultFalse("let s : String = null in s = 'se'");
		assertResultFalse("let s : String = null in 'test' = s");

		assertResultTrue("let s1 : String = null, s2 : String = null in s1 = s2");
	}

	public void testStringEqualIgnoresCase() {
		assertResultFalse("'test'.equalsIgnoreCase('se')");
		assertResultTrue("'test'.equalsIgnoreCase('test')");
		assertResultTrue("'Test'.equalsIgnoreCase('tEst')");
		assertResultTrue("'tesT'.equalsIgnoreCase('teSt')");
		assertResultTrue("'TEST'.equalsIgnoreCase('test')");
		assertResultTrue("'test'.equalsIgnoreCase('TEST')");
	}

	public void testStringGreaterThan() {
		assertResultFalse("'3' > '4'");
		assertResultFalse("'a' > 'b'");
		assertResultFalse("'aardvark' > 'aardvarks'");

		assertResultTrue("'3.2' > '3.1'");
		assertResultTrue("'a' > 'A'");
		assertResultTrue("'aardvark' > 'aardvarK'");

		assertResultFalse("'3' > '3'");
		assertResultFalse("'a' > 'a'");
		assertResultFalse("'aardvark' > 'aardvark'");
	}

	public void testStringGreaterThanInvalid() {
		assertResultInvalid("let s : String = invalid in s > 'se'");
		assertResultInvalid("let s : String = invalid in 'test' > s");

		assertResultInvalid("let s1 : String = invalid, s2 : String = invalid in s1 > s2");
	}

	public void testStringGreaterThanNull() {
		assertResultInvalid("let s : String = null in s > 'se'");
		assertResultInvalid("let s : String = null in 'test' > s");

		assertResultInvalid("let s1 : String = null, s2 : String = null in s1 > s2");
	}

	public void testStringGreaterThanOrEqual() {
		assertResultFalse("'3' >= '4'");
		assertResultFalse("'a' >= 'b'");
		assertResultFalse("'aardvark' >= 'aardvarks'");

		assertResultTrue("'3.2' >= '3.1'");
		assertResultTrue("'a' >= 'A'");
		assertResultTrue("'aardvark' >= 'aardvarK'");

		assertResultTrue("'3' >= '3'");
		assertResultTrue("'a' >= 'a'");
		assertResultTrue("'aardvark' >= 'aardvark'");
	}

	public void testStringGreaterThanOrEqualInvalid() {
		assertResultInvalid("let s : String = invalid in s >= 'se'");
		assertResultInvalid("let s : String = invalid in 'test' >= s");

		assertResultInvalid("let s1 : String = invalid, s2 : String = invalid in s1 >= s2");
	}

	public void testStringGreaterThanOrEqualNull() {
		assertResultInvalid("let s : String = null in s >= 'se'");
		assertResultInvalid("let s : String = null in 'test' >= s");

		assertResultInvalid("let s1 : String = null, s2 : String = null in s1 >= s2");
	}

	public void testStringIndexOf() {
		assertResult(1, "'test'.indexOf('t')");
		assertResult(1, "'test'.indexOf('te')");
		assertResult(2, "'test'.indexOf('es')");
		assertResult(3, "'test'.indexOf('st')");
		assertResult(5, "'tesla'.indexOf('a')");
		// out of bounds
		assertResult(0, "'test'.indexOf('xyzzy')");
		assertResult(0, "'test'.indexOf('est2')");
		// empty
		assertResult(1, "'test'.indexOf('')");
		assertResult(1, "''.indexOf('')");
		assertResult(0, "''.indexOf('t')");
		// invalid
		assertResultInvalid("let s : String = invalid in 'test'.indexOf(s)");
		assertResultInvalid("let s : String = invalid in s.indexOf('s')");
		// null
		assertResultInvalid("let s : String = null in 'test'.indexOf(s)");
		assertResultInvalid("let s : String = null in s.indexOf('s')");
	}

	public void testStringLastIndexOf() {
		assertResult(4, "'test'.lastIndexOf('t')");
		assertResult(1, "'test'.lastIndexOf('te')");
		assertResult(2, "'test'.lastIndexOf('es')");
		assertResult(3, "'test'.lastIndexOf('st')");
		assertResult(5, "'tesla'.lastIndexOf('a')");
		assertResult(1, "'ates'.lastIndexOf('a')");
		// out of bounds
		assertResult(0, "'test'.lastIndexOf('xyzzy')");
		assertResult(0, "'test'.lastIndexOf('est2')");
		// empty
		assertResult(5, "'test'.lastIndexOf('')");
		assertResult(1, "''.lastIndexOf('')");
		assertResult(0, "''.lastIndexOf('t')");
		// invalid
		assertResultInvalid("let s : String = invalid in 'test'.lastIndexOf(s)");
		assertResultInvalid("let s : String = invalid in s.lastIndexOf('s')");
		// null
		assertResultInvalid("let s : String = null in 'test'.lastIndexOf(s)");
		assertResultInvalid("let s : String = null in s.lastIndexOf('s')");
	}

	public void testStringLessThan() {
		/*
		 * FIXME the standard library doesn't define either less than, less than
		 * or equal, greater than or greater than or equal. Neither should then
		 * compile. If we do define these, why not define them for boolean?
		 */
		assertResultTrue("'3' < '4'");
		assertResultTrue("'a' < 'b'");
		assertResultTrue("'aardvark' < 'aardvarks'");

		assertResultFalse("'3.2' < '3.1'");
		assertResultFalse("'a' < 'A'");
		assertResultFalse("'aardvark' < 'aardvarK'");

		assertResultFalse("'3' < '3'");
		assertResultFalse("'a' < 'a'");
		assertResultFalse("'aardvark' < 'aardvark'");
	}

	public void testStringLessThanInvalid() {
		assertResultInvalid("let s : String = invalid in s < 'se'");
		assertResultInvalid("let s : String = invalid in 'test' < s");

		assertResultInvalid("let s1 : String = invalid, s2 : String = invalid in s1 < s2");
	}

	public void testStringLessThanNull() {
		assertResultInvalid("let s : String = null in s < 'se'");
		assertResultInvalid("let s : String = null in 'test' < s");

		assertResultInvalid("let s1 : String = null, s2 : String = null in s1 < s2");
	}

	public void testStringLessThanOrEqual() {
		assertResultTrue("'3' <= '4'");
		assertResultTrue("'a' <= 'b'");
		assertResultTrue("'aardvark' <= 'aardvarks'");

		assertResultFalse("'3.2' <= '3.1'");
		assertResultFalse("'a' <= 'A'");
		assertResultFalse("'aardvark' <= 'aardvarK'");

		assertResultTrue("'3' <= '3'");
		assertResultTrue("'a' <= 'a'");
		assertResultTrue("'aardvark' <= 'aardvark'");
	}

	public void testStringLessThanOrEqualInvalid() {
		assertResultInvalid("let s : String = invalid in s <= 'se'");
		assertResultInvalid("let s : String = invalid in 'test' <= s");

		assertResultInvalid("let s1 : String = invalid, s2 : String = invalid in s1 <= s2");
	}

	public void testStringLessThanOrEqualNull() {
		assertResultInvalid("let s : String = null in s <= 'se'");
		assertResultInvalid("let s : String = null in 'test' <= s");

		assertResultInvalid("let s1 : String = null, s2 : String = null in s1 <= s2");
	}

	public void testStringMatches() {
		assertResultTrue("'characters and spaces'.matches('[\\\\w\\\\s]+')");		// *2 for Java, *2 for OCL
		assertResultFalse("'characters and 3 digits'.matches('[\\\\p{Alpha}\\\\s]+')");
		//
		assertResultTrue("''.matches('')");
		assertResultTrue("''.matches('')");
		assertResultFalse("'a'.matches('')");
		assertResultFalse("''.matches('b')");
		//
		assertResultInvalid("'repla ce operation'.matches('a[b-')"); //, null, PatternSyntaxException.class);
		//
		assertResultInvalid("null.matches('(\\\\w+)\\\\s*')");
		assertResultInvalid("'repla ce operation'.matches(null)");
		//
		assertResultInvalid("invalid.matches('(\\\\w+)\\\\s*')");
		assertResultInvalid("'repla ce operation'.matches(invalid)");
		// -- visual inspection of println's demonstrates cache re-use; this test just conforms cache still ok once full
		assertQueryEquals(null, 50, "let seq : Sequence(Integer) = Sequence{1..20}, rseq : Sequence(Integer) = seq->collect(i | 21-i), seqs : Sequence(Integer) = Sequence{seq,rseq,seq,rseq,seq}->flatten() in seqs->iterate(i; acc : Integer = 0 | if '123456789'.matches('.*' + i.toString() + '.*') then acc + 1 else acc endif)");
	}

	public void testStringNotEqual() {
		assertResultTrue("'test' <> 'se'");
		assertResultFalse("'test' <> 'test'");
	}

	public void testStringNotEqualInvalid() {
		assertResultInvalid("let s : String = invalid in s <> 'se'");
		assertResultInvalid("let s : String = invalid in 'test' <> s");

		assertResultInvalid("let s1 : String = invalid, s2 : String = invalid in s1 <> s2");
	}

	public void testStringNotEqualNull() {
		assertResultTrue("let s : String = null in s <> 'se'");
		assertResultTrue("let s : String = null in 'test' <> s");

		assertResultFalse("let s1 : String = null, s2 : String = null in s1 <> s2");
	}

	public void testStringOclAsType() {
		assertResultInvalid("'test'.oclAsType(Integer)");
		assertResult("test", "'test'.oclAsType(String)");
		assertResult("test", "'test'.oclAsType(OclAny)");
		assertResultInvalid("'test'.oclAsType(OclVoid)");
		assertResultInvalid("'test'.oclAsType(OclInvalid)");
	}

	public void testStringOclIsInvalid() {
		assertResultFalse("'test'.oclIsInvalid()");
		assertResultFalse("''.oclIsInvalid()");
	}

	public void testStringOclIsUndefined() {
		assertResultFalse("'test'.oclIsUndefined()");
		assertResultFalse("''.oclIsUndefined()");
	}

	public void testStringPlus() {
		assertResult("concatenationTest", "'concatenation' + 'Test'");
		assertResult("concatenation\n", "'concatenation' + '\\n'");
		// invalid
		assertResultInvalid("let s : String = invalid in 'concatenation' + s");
		assertResultInvalid("let s : String = invalid in s + 'concatenation'");
		// null
		assertResultInvalid("let s : String = null in 'concatenation' + s");
		assertResultInvalid("let s : String = null in s + 'concatenation'");
		// EString
		assertResult("ab", "let s1 : String = 'a', s2 : String = 'b' in s1 + s2");
		assertResult("ab", "let s1 : EString = 'a', s2 : String = 'b' in s1 + s2");
		assertResult("ab", "let s1 : String = 'a', s2 : EString = 'b' in s1 + s2");
		assertResult("ab", "let s1 : EString = 'a', s2 : EString = 'b' in s1 + s2");
	}

	public void testStringReplaceAll() {
		assertResult("rePlaceAll oPeration", "'replaceAll operation'.replaceAll('p', 'P')");
		assertResult("ReplaceAllOperation", "'Repla ce All Operation'.replaceAll('(\\\\w+)\\\\s*', '$1')");
		//
		assertResult("xx", "''.replaceAll('', 'xx')");
		assertResult("xxrxxexxpxxlxxaxx xxcxxexx xxoxxpxxexxrxxaxxtxxixxoxxnxx", "'repla ce operation'.replaceAll('', 'xx')");
		assertResult("", "'repla ce operation'.replaceAll('(\\\\w+)\\\\s*', '')");
		assertResult("repla ce operation", "'repla ce operation'.replaceAll('', '')");
		//
		assertResultInvalid("'repla ce operation'.replaceAll('a[b-', '$1')"); //, null, PatternSyntaxException.class);
		assertResultInvalid("'repla ce operation'.replaceAll('', '$1')"); //, "No group 1", IndexOutOfBoundsException.class);
		//
//		assertResultInvalid("null.replaceAll('(\\\\w+)\\\\s*', '$1')");
		assertResultInvalid("'repla ce operation'.replaceAll(null, '$1')");
		assertResultInvalid("'repla ce operation'.replaceAll('(\\\\w+)\\\\s*', null)");
		//
//		assertResultInvalid("invalid.replaceAll('(\\\\w+)\\\\s*', '$1')");
		assertResultInvalid("'repla ce operation'.replaceAll(invalid, '$1')");
		assertResultInvalid("'repla ce operation'.replaceAll('(\\\\w+)\\\\s*', invalid)");
	}

	public void testStringReplaceFirst() {
		assertResult("rePlace operation", "'replace operation'.replaceFirst('p', 'P')");
		assertResult("replace operation", "'repla ce operation'.replaceFirst('(\\\\w+)\\\\s*', '$1')");
		//
		assertResult("xx", "''.replaceFirst('', 'xx')");
		assertResult("xxrepla ce operation", "'repla ce operation'.replaceFirst('', 'xx')");
		assertResult("ce operation", "'repla ce operation'.replaceFirst('(\\\\w+)\\\\s*', '')");
		assertResult("repla ce operation", "'repla ce operation'.replaceFirst('', '')");
		//
		assertResultInvalid("'repla ce operation'.replaceFirst('a[b-', '$1')"); //, null, PatternSyntaxException.class);
		assertResultInvalid("'repla ce operation'.replaceFirst('', '$1')"); //, "No group 1", IndexOutOfBoundsException.class);
		//
//		assertResultInvalid("null.replaceFirst('(\\\\w+)\\\\s*', '$1')");
		assertResultInvalid("'repla ce operation'.replaceFirst(null, '$1')");
		assertResultInvalid("'repla ce operation'.replaceFirst('(\\\\w+)\\\\s*', null)");
		//
//		assertResultInvalid("invalid.replaceFirst('(\\\\w+)\\\\s*', '$1')");
		assertResultInvalid("'repla ce operation'.replaceFirst(invalid, '$1')");
		assertResultInvalid("'repla ce operation'.replaceFirst('(\\\\w+)\\\\s*', invalid)");
	}

	public void testStringSize() {
		assertResult(Integer.valueOf(4), "'test'.size()"); //$NON-NLS-2$
		assertResult(Integer.valueOf(0), "''.size()"); //$NON-NLS-2$
	}

	public void testStringSizeInvalid() {
		assertResultInvalid("let s : String = invalid in s.size()"); //$NON-NLS-2$
	}

	public void testStringSizeNull() {
		assertResultInvalid("let s : String = null in s.size()"); //$NON-NLS-2$
	}

	public void testStringStartsWith() {
		assertResultFalse("'abcdef'.startsWith('abcdefg')");
		assertResultTrue("'abcdef'.startsWith('abcdef')");
		assertResultTrue("'abcdef'.startsWith('abcd')");
		assertResultTrue("'abcdef'.startsWith('a')");
		assertResultTrue("'abcdef'.startsWith('')");
		assertResultTrue("''.startsWith('')");
		assertResultFalse("''.startsWith('a')");
		assertResultTrue("'abcdef'.startsWith('')");
		assertResultFalse("'abcdef'.startsWith('bcd')");
		assertResultFalse("'abcdef'.startsWith('ef')");
		assertResultFalse("'abcdef'.startsWith('f')");
		// invalid
		assertResultInvalid("let s : String = invalid in s.startsWith('')");
		assertResultInvalid("let s : String = invalid in ''.startsWith(s)");
		// null
		assertResultInvalid("let s : String = null in s.startsWith('')");
		assertResultInvalid("let s : String = null in ''.startsWith(s)");
	}

	public void testStringSubstituteAll() {
		assertResult("subsTiTuTeAll operaTion", "'substituteAll operation'.substituteAll('t', 'T')");
		//
		assertResult("xx", "''.replaceAll('', 'xx')");
		assertResult("xxrxxexxpxxlxxaxx xxcxxexx xxoxxpxxexxrxxaxxtxxixxoxxnxx", "'repla ce operation'.substituteAll('', 'xx')");
		assertResult("repla ce operation", "'repla ce operation'.substituteAll('(\\\\w+)\\\\s*', '')");
		assertResult("repla ce operation", "'repla ce operation'.substituteAll('', '')");
		//
//		assertResultInvalid("null.substituteAll('(\\\\w+)\\\\s*', '$1')");
		assertResultInvalid("'repla ce operation'.substituteAll(null, '$1')");
		assertResultInvalid("'repla ce operation'.substituteAll('(\\\\w+)\\\\s*', null)");
		//
//		assertResultInvalid("invalid.substituteAll('(\\\\w+)\\\\s*', '$1')");
		assertResultInvalid("'repla ce operation'.substituteAll(invalid, '$1')");
		assertResultInvalid("'repla ce operation'.substituteAll('(\\\\w+)\\\\s*', invalid)");
	}

	public void testStringSubstituteFirst() {
		assertResult("subsTiTuTeFirst operaTion", "'substiTuTeFirst operaTion'.substituteFirst('t', 'T')");
		assertResult("SubstiTuTeFirst operaTion", "'substiTuTeFirst operaTion'.substituteFirst('s', 'S')");
		assertResult("substiTuTeFirst operaTioN", "'substiTuTeFirst operaTion'.substituteFirst('n', 'N')");
		assertResult("substiTuTeFirst operaTion", "'substiTuTeFirst operaTion'.substituteFirst('n', 'n')");
		assertResult("substiTuTeFirst operaTiON", "'substiTuTeFirst operaTion'.substituteFirst('on', 'ON')");
		assertResult("a[b-c]d\r\n*", "'a[b-c]d\\\\w*'.substituteFirst('\\\\w', '\\r\\n')");
		//
		assertResult("xx", "''.substituteFirst('', 'xx')");
		assertResult("xxrepla ce operation", "'repla ce operation'.substituteFirst('', 'xx')");
		assertResult("repla ce operation", "'repla ce operation'.substituteFirst('', '')");
		//
		assertResultInvalid("'repla ce operation'.substituteFirst('(\\\\w+)\\\\s*', '')"); //,
//			DomainUtil.bind(EvaluatorMessages.MissingSubstring, "(\\w+)\\s*", "repla ce operation"), null);
		//
//		assertResultInvalid("null.substituteFirst('(\\\\w+)\\\\s*', '$1')");
		assertResultInvalid("'repla ce operation'.substituteFirst(null, '$1')");
		assertResultInvalid("'repla ce operation'.substituteFirst('(\\\\w+)\\\\s*', null)");
		//
//		assertResultInvalid("invalid.substituteFirst('(\\\\w+)\\\\s*', '$1')");
		assertResultInvalid("'repla ce operation'.substituteFirst(invalid, '$1')");
		assertResultInvalid("'repla ce operation'.substituteFirst('(\\\\w+)\\\\s*', invalid)");
	}

	public void testStringSubstring() {
		assertResult("t", "'test'.substring(1, 1)");
		assertResult("es", "'test'.substring(2, 3)");
		assertResult("t", "'test'.substring(4, 4)");
	}

	public void testStringSubstringIllegalArguments() {
		/*
		 * FIXME in OCL, substring(2, 1) is the equivalent of java's
		 * substring(1,1) : an empty String. However this really looks like it
		 * should fail.
		 */
		assertResultInvalid("'test'.substring(2, 1)");
		assertResultInvalid("'test'.substring(3, 1)");
	}

	public void testStringSubstringInvalid() {
		assertResultInvalid("let s : String = invalid in s.substring(1, 1)");
		assertResultInvalid("let s : String = invalid in s.substring(5, 5)");
	}

	public void testStringSubstringNull() {
		assertResultInvalid("let s : String = null in s.substring(1, 1)");
		assertResultInvalid("let s : String = null in s.substring(5, 5)");
	}

	public void testStringSubstringOutOfBounds() {
		assertResultInvalid("'test'.substring(0, 1)");
		assertResultInvalid("'test'.substring(4, 5)");
	}

	public void testStringToBoolean() {
		assertResultTrue("'true'.toBoolean()");
		assertResultFalse("' true'.toBoolean()");
		assertResultFalse("'true '.toBoolean()");
		assertResultFalse("'True'.toBoolean()");
		assertResultFalse("'false'.toBoolean()");
		assertResultFalse("'-4'.toBoolean()");
		// invalid
		assertResultInvalid("let s : String = invalid in s.toBoolean()");
		// null
		assertResultInvalid("let s : String = null in s.toBoolean()");
	}

	public void testStringToInteger() {
		assertResult(Integer.valueOf(4), "'4'.toInteger()");
		assertResult(Integer.valueOf(-4), "'-4'.toInteger()");
		assertResultInvalid("'4.0'.toInteger()");

		assertResultInvalid("'2.4.0'.toInteger()");
		assertResultInvalid("'a'.toInteger()");
	}

	public void testStringToIntegerInvalid() {
		assertResultInvalid("let s : String = invalid in s.toInteger()");
	}

	public void testStringToIntegerNull() {
		assertResultInvalid("let s : String = null in s.toInteger()");
	}

	public void testStringToLower() {
		checkForUTF8Encoding();
		assertResult("4", "'4'.toLower()"); //$NON-NLS-2$
		assertResult("mixed", "'MiXeD'.toLower()"); //$NON-NLS-2$
		assertResult("upper", "'UPPER'.toLower()"); //$NON-NLS-2$
		// Ensures word-final sigma and regular sigmas are converted as needed
		// TODO re-enable once the Unicode problems on Hudson have been resolved
		// assertResult(
		//	"ὀδυσσεύς", "'ὈΔΥΣΣΕΎΣ'.toLower()");
	}

	public void testStringToLowerCase() {
		checkForUTF8Encoding();
		assertResult("4", "'4'.toLowerCase()"); //$NON-NLS-2$
		assertResult("mixed", "'MiXeD'.toLowerCase()"); //$NON-NLS-2$
		assertResult("upper", "'UPPER'.toLowerCase()"); //$NON-NLS-2$
		// Ensures word-final sigma and regular sigmas are converted as needed
		// TODO re-enable once the Unicode problems on Hudson have been resolved
		// assertResult(
		//	"ὀδυσσεύς", "'ὈΔΥΣΣΕΎΣ'.toLowerCase()");
	}

	public void testStringToLowerInvalid() {
		assertResultInvalid("let s : String = invalid in s.toLower()");
	}

	public void testStringToLowerNull() {
		assertResultInvalid("let s : String = null in s.toLower()");
	}

	public void testStringToReal() {
		assertResult(Double.valueOf(4d), "'4'.toReal()");
		assertResult(Double.valueOf(-4d), "'-4'.toReal()");
		assertResult(Double.valueOf(4d), "'4.0'.toReal()");

		assertResultInvalid("'2.4.0'.toReal()");
		assertResultInvalid("'a'.toReal()");
	}

	public void testStringToRealInvalid() {
		assertResultInvalid("let s : String = invalid in s.toReal()");
	}

	public void testStringToRealNull() {
		assertResultInvalid("let s : String = null in s.toReal()");
	}

	public void testStringToString() {
		assertExpressionResults("'4.0'", "'4.0'.toString()");
//		assertResult("'4.0\t4'", "('4.0' + '\t' + '4').toString()");
		assertResult("4.0 4", "('4.0' + ' ' + '4').toString()");
	}

	public void testStringToUpper() {
		checkForUTF8Encoding();
		assertResult("4", "'4'.toUpper()");
		assertResult("MIXED", "'MiXeD'.toUpper()");
		assertResult("LOWER", "'lower'.toUpper()");
		
		// Ensures word-final sigma and regular sigmas are converted as needed
		// TODO re-enable once the Unicode problems on Hudson have been resolved
		// assertResult("ὈΔΥΣΣΕΎΣ", "'ὀδυσσεύς'.toUpper()");
		
		// Sharp s should be mapped to a double S upper case
		// TODO re-enable once the Unicode problems on Hudson have been resolved
		// assertResult("SS", "'ß'.toUpper()");
	}

	public void testStringToUpperCase() {
		checkForUTF8Encoding();
		assertResult("4", "'4'.toUpperCase()");
		assertResult("MIXED", "'MiXeD'.toUpperCase()");
		assertResult("LOWER", "'lower'.toUpperCase()");
		
		// Ensures word-final sigma and regular sigmas are converted as needed
		// TODO re-enable once the Unicode problems on Hudson have been resolved
		// assertResult("ὈΔΥΣΣΕΎΣ", "'ὀδυσσεύς'.toUpperCase()");
		
		// Sharp s should be mapped to a double S upper case
		// TODO re-enable once the Unicode problems on Hudson have been resolved
		// assertResult("SS", "'ß'.toUpperCase()");
	}

	public void testStringToUpperInvalid() {
		assertResultInvalid("let s : String = invalid in s.toUpper()");
	}

	public void testStringToUpperNull() {
		assertResultInvalid("let s : String = null in s.toUpper()");
	}

	public void testStringTokenize() {
//		assertExpressionResults("Sequence{'', 'a','b','c','d', ''}", "'\\na b\\tc\\fd\\r'.tokenize()");
		assertExpressionResults("Sequence{'a','b','c','d'}", "'\\na b\\tc\\fd\\r'.tokenize()");
		assertExpressionResults("Sequence{'a','b','c','d'}", "' \\t\\n\\r\\fa b\\tc\\fd \\t\\n\\r\\f'.tokenize()");
		assertExpressionResults("Sequence{' ','\\t','\\n','\\r','\\f','a',' ','b','\\t','c','\\f','d',' ','\\t','\\n','\\r','\\f'}", "' \\t\\n\\r\\fa b\\tc\\fd \\t\\n\\r\\f'.tokenize(' \\t\\n\\r\\f', true)");
		assertExpressionResults("Sequence{'\\na',' ', 'b\\tc\\fd\\r'}", "'\\na b\\tc\\fd\\r'.tokenize(' ', true)");
		assertExpressionResults("Sequence{}", "''.tokenize(' ', true)");
		assertExpressionResults("Sequence{' \\t\\n\\r\\f'}", "' \\t\\n\\r\\f'.tokenize('', true)");
		assertExpressionResults("Sequence{}", "''.tokenize('', true)");
		assertExpressionResults("Sequence{}", "''.tokenize(' \\t\\n\\r\\f', true)");
		// invalid
		assertResultInvalid("let s : String = invalid in s.tokenize()");
		assertResultInvalid("let s : String = invalid in s.tokenize('')");
		assertResultInvalid("let s : String = invalid in s.tokenize('',true)");
		assertResultInvalid("let s : String = invalid in ''.tokenize(s)");
		assertResultInvalid("let s : String = invalid in ''.tokenize(s,true)");
		assertResultInvalid("let b : Boolean = invalid in ''.tokenize('',b)");
		// null
		assertResultInvalid("let s : String = null in s.tokenize()");
		assertResultInvalid("let s : String = null in s.tokenize('')");
		assertResultInvalid("let s : String = null in s.tokenize('',true)");
		assertResultInvalid("let s : String = null in ''.tokenize(s)");
		assertResultInvalid("let s : String = null in ''.tokenize(s,true)");
		assertResultInvalid("let b : Boolean = null in ''.tokenize('',b)");
		//
//		assertSemanticErrorQuery("''.tokenize('',false,null)", OCLMessages.UnresolvedOperationCall_ERROR_, "tokenize", "String", "String, Boolean, OclVoid");
	}

	public void testStringTrim() {
		assertResult("ab", "'ab'.trim()");
		assertResult("a", "'a'.trim()");
		assertResult("", "''.trim()");
		assertResult("a \t\n\r\fb", "'\\na \\t\\n\\r\\fb\\n'.trim()");
		assertResult("", "' \\t\\n\\r\\f \\t\\n\\r\\f'.trim()");
		// invalid
		assertResultInvalid("let s : String = invalid in s.trim()");
		// null
		assertResultInvalid("let s : String = null in s.trim()");
	}
}
