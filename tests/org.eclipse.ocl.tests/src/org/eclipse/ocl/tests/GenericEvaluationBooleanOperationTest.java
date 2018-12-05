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

import org.eclipse.emf.ecore.EObject;

// FIXME we're missing oclIsNew and oclIsInState
/**
 * This unit test focuses on the Standard library's Boolean operations.
 * 
 * @author Laurent Goubet (lgoubet)
 */
@SuppressWarnings("nls")
public abstract class GenericEvaluationBooleanOperationTest<E extends EObject, PK extends E, T extends E, C extends T, CLS extends C, DT extends C, PT extends C, ET extends DT, O extends E, PM extends E, P extends E, PA extends P, PR extends P, EL, S, COA, SSA, CT>
extends GenericEvaluationTestSuite<E, PK, T, C, CLS, DT, PT, ET, O, PM, P, PA, PR, EL, S, COA, SSA, CT> {

	public void testBooleanAnd() {
		assertResultFalse("false and false");
		assertResultFalse("false and true");
		assertResultFalse("true and false");
		assertResultTrue("true and true");
	}

	public void testBooleanAndInvalid() {
		assertResultFalse("let b : Boolean = invalid in false and b");
		assertResultInvalid("let b : Boolean = invalid in true and b");
		assertResultFalse("let a : Boolean = invalid in a and false");
		assertResultInvalid("let a : Boolean = invalid in a and true");
		assertResultInvalid("let a : Boolean = invalid, b : Boolean = invalid in a and b");
	}

	public void testBooleanAndNull() {
		assertResultFalse("let b : Boolean = null in false and b");
		assertResultInvalid("let b : Boolean = null in true and b");
		assertResultFalse("let a : Boolean = null in a and false");
		assertResultInvalid("let a : Boolean = null in a and true");
		assertResultInvalid("let a : Boolean = null, b : Boolean = null in a and b");
	}

	public void testBooleanEqual() {
		assertResultFalse("true = false");

		assertResultTrue("true = true");
		assertResultTrue("false = false");
	}

	public void testBooleanEqualInvalid() {
		// operation invocations on invalid except for oclIsInvalid and oclIsUndefined yield invalid
		assertResultInvalid("let b : Boolean = invalid in b = true");
		assertResultInvalid("let b : Boolean = invalid in false = b");

		assertResultInvalid("let b1 : Boolean = invalid, b2 : Boolean = invalid in b1 = b2");
	}

	public void testBooleanEqualNull() {
		assertResultFalse("let b : Boolean = null in b = true");
		assertResultFalse("let b : Boolean = null in false = b");

		assertResultTrue("let b1 : Boolean = null, b2 : Boolean = null in b1 = b2");
	}

	public void testBooleanImplies() {
		assertResultTrue("false implies false");
		assertResultTrue("false implies true");
		assertResultFalse("true implies false");
		assertResultTrue("true implies true");
	}

	public void testBooleanImpliesInvalid() {
		assertResultTrue("let b : Boolean = invalid in false implies b");
		assertResultInvalid("let b : Boolean = invalid in true implies b");
		assertResultInvalid("let a : Boolean = invalid in a implies false");
		assertResultTrue("let a : Boolean = invalid in a implies true");
		assertResultInvalid("let a : Boolean = invalid, b : Boolean = invalid in a implies b");
	}

	public void testBooleanImpliesNull() {
		assertResultTrue("let b : Boolean = null in false implies b");
		assertResultInvalid("let b : Boolean = null in true implies b");
		assertResultInvalid("let a : Boolean = null in a implies false");
		assertResultTrue("let a : Boolean = null in a implies true");
		assertResultInvalid("let a : Boolean = null, b : Boolean = null in a implies b");
	}

	public void testBooleanNot() {
		assertResultTrue("not false");
		assertResultFalse("not true");
	}

	public void testBooleanNotEqual() {
		assertResultTrue("true <> false");

		assertResultFalse("true <> true");
		assertResultFalse("false <> false");
	}

	public void testBooleanNotEqualInvalid() {
		// operation invocations on invalid except for oclIsInvalid and oclIsUndefined yield invalid
		assertResultInvalid("let b : Boolean = invalid in b <> true");
		assertResultInvalid("let b : Boolean = invalid in false <> b");

		assertResultInvalid("let b1 : Boolean = invalid, b2 : Boolean = invalid in b1 <> b2");
	}

	public void testBooleanNotEqualNull() {
		assertResultTrue("let b : Boolean = null in b <> true");
		assertResultTrue("let b : Boolean = null in false <> b");

		assertResultFalse("let b1 : Boolean = null, b2 : Boolean = null in b1 <> b2");
	}

	public void testBooleanNotInvalid() {
		assertResultInvalid("let a : Boolean = invalid in not a");
	}

	public void testBooleanNotNull() {
		assertResultInvalid("let a : Boolean = null in not a");
	}

	public void testBooleanOclAsType() {
		assertResultInvalid("true.oclAsType(Integer)");
		assertResultInvalid("true.oclAsType(String)");
		assertResultTrue("true.oclAsType(Boolean)");
		assertResultTrue("true.oclAsType(OclAny)");
		assertResultInvalid("true.oclAsType(OclVoid)");
		assertResultInvalid("true.oclAsType(OclInvalid)");
	}

	public void testBooleanOclIsInvalid() {
		assertResultFalse("true.oclIsInvalid()");
		assertResultFalse("false.oclIsInvalid()");
	}

	public void testBooleanOclIsUndefined() {
		assertResultFalse("true.oclIsUndefined()");
		assertResultFalse("false.oclIsUndefined()");
	}

	public void testBooleanOr() {
		assertResultFalse("false or false");
		assertResultTrue("false or true");
		assertResultTrue("true or false");
		assertResultTrue("true or true");
	}

	public void testBooleanOrInvalid() {
		assertResultInvalid("let b : Boolean = invalid in false or b");
		assertResultTrue("let b : Boolean = invalid in true or b");
		assertResultInvalid("let a : Boolean = invalid in a or false");
		assertResultTrue("let a : Boolean = invalid in a or true");
		assertResultInvalid("let a : Boolean = invalid, b : Boolean = invalid in a or b");
	}

	public void testBooleanOrNull() {
		assertResultInvalid("let b : Boolean = null in false or b");
		assertResultTrue("let b : Boolean = null in true or b");
		assertResultInvalid("let a : Boolean = null in a or false");
		assertResultTrue("let a : Boolean = null in a or true");
		assertResultInvalid("let a : Boolean = null, b : Boolean = null in a or b");
	}

	public void testBooleanToString() {
		assertResult("false", "false.toString()");
		assertResult("true", "true.toString()");
		assertResult("true", "(not false).toString()");
	}

	public void testBooleanXor() {
		assertResultFalse("false xor false");
		assertResultTrue("false xor true");
		assertResultTrue("true xor false");
		assertResultFalse("true xor true");
	}

	public void testBooleanXorInvalid() {
		assertResultInvalid("let b : Boolean = invalid in false xor b");
		assertResultInvalid("let b : Boolean = invalid in true xor b");
		assertResultInvalid("let a : Boolean = invalid in a xor false");
		assertResultInvalid("let a : Boolean = invalid in a xor true");
		assertResultInvalid("let a : Boolean = invalid, b : Boolean = invalid in a xor b");
	}

	public void testBooleanXorNull() {
		assertResultInvalid("let b : Boolean = null in false xor b");
		assertResultInvalid("let b : Boolean = null in true xor b");
		assertResultInvalid("let a : Boolean = null in a xor false");
		assertResultInvalid("let a : Boolean = null in a xor true");
		assertResultInvalid("let a : Boolean = null, b : Boolean = null in a xor b");
	}
}
