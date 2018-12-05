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

//FIXME we're missing oclIsNew and oclIsInState
/**
 * This unit test focuses on the Standard library's OclAny operations.
 * 
 * @author Laurent Goubet (lgoubet)
 */
@SuppressWarnings("nls")
public abstract class GenericEvaluationOclAnyOperationTest<E extends EObject, PK extends E, T extends E, C extends T, CLS extends C, DT extends C, PT extends C, ET extends DT, O extends E, PM extends E, P extends E, PA extends P, PR extends P, EL, S, COA, SSA, CT>
extends GenericEvaluationTestSuite<E, PK, T, C, CLS, DT, PT, ET, O, PM, P, PA, PR, EL, S, COA, SSA, CT> {
	
    /**
     * Tests the explicit oclAsSet() operator.
     */
	public void test_oclAsSet_explicit() {
		assertExpressionResults("Set{true}", "true.oclAsSet()");
		assertExpressionResults("Set{}", "null.oclAsSet()");
		assertQueryInvalid(null, "invalid.oclAsSet()");
		assertExpressionResults("Set{Set{1..4}}", "Set{1..4}->oclAsSet()");
	}
	
    /**
     * Tests the implicit oclAsSet() operator.
     */
	public void test_oclAsSet_implicit() {
		assertExpressionResults("Set{true}", "true->select(true)");
		assertExpressionResults("Set{true}", "Set{true}->select(true)");
		assertExpressionResults("Set{}", "null->select(true)");
		assertExpressionResults("Set{}", "Set{}->select(true)");
		assertExpressionResults("Set{null}", "Set{null}->select(true)");
		assertQueryInvalid(null, "invalid->select(true)");
		//
		assertExpressionResults("false", "true.oclIsUndefined()");
		assertExpressionResults("false", "true->oclIsUndefined()");	// Set{true}
		assertExpressionResults("true", "null.oclIsUndefined()");
		assertExpressionResults("false", "null->oclIsUndefined()");	// Set{}
		assertExpressionResults("true", "invalid.oclIsUndefined()");
		assertExpressionResults("true", "invalid->oclIsUndefined()");	// invalid
		//
		assertResult(4, "'1234'.size()");
		assertResult(1, "'1234'->size()");
	}

	public void testEqualInvalid() {
		/*
		 * FIXME we're expecting here results as specified in 2.0 : (11.2.4) any
		 * call on invalid results in invalid. Note that 2.1 didn't alter
		 * this statement but added in 11.2.5 an override of '=' so that it
		 * returns "true if object is invalid". Which should we respect in the
		 * implementation? respecting 11.2.4 is better as far as error handling
		 * is concerned ... but then we should probably respect 11.2.3 and have
		 * '=' fail on 'null'.
		 */
		assertResultInvalid("invalid = 3");
		assertResultInvalid("3 = invalid");
		assertResultInvalid("invalid = 3.0");
		assertResultInvalid("3.0 = invalid");

		assertResultInvalid("invalid = 'test'");
		assertResultInvalid("'test' = invalid");
		assertResultInvalid("invalid = true");
		assertResultInvalid("false = invalid");
		assertResultInvalid("invalid = Sequence{}");
		assertResultInvalid("Sequence{} = invalid");

		assertResultInvalid("invalid = invalid");
	}

	public void testEqualNull() {
		assertResultFalse("null = 3");
		assertResultFalse("3 = null");
		assertResultFalse("null = 3.0");
		assertResultFalse("3.0 = null");

		assertResultFalse("null = 'test'");
		assertResultFalse("'test' = null");
		assertResultFalse("null = true");
		assertResultFalse("false = null");
		assertResultFalse("null = Sequence{}");
		assertResultFalse("Sequence{} = null");

		assertResultTrue("null = null");
	}

	public void testGreaterThanInvalid() {
		assertResultInvalid("invalid > 0");
		assertResultInvalid("0 > invalid");
		assertResultInvalid("invalid > invalid");
	}

	public void testGreaterThanNull() {
		assertResultInvalid("null > 0");
		assertResultInvalid("0 > null");
		assertResultInvalid("null > null");
	}

	public void testGreaterThanOrEqualInvalid() {
		assertResultInvalid("invalid >= 0");
		assertResultInvalid("0 >= invalid");
		assertResultInvalid("invalid >= invalid");
	}

	public void testGreaterThanOrEqualNull() {
		assertResultInvalid("null >= 0");
		assertResultInvalid("0 >= null");
		assertResultInvalid("null >= null");
	}

	public void testLessThanInvalid() {
		assertResultInvalid("invalid < 0");
		assertResultInvalid("0 < invalid");
		assertResultInvalid("invalid < invalid");
	}

	public void testLessThanNull() {
		// FIXME '=' is defined for null ... why not <? at least <= should be.
		assertResultInvalid("null < 0");
		assertResultInvalid("0 < null");
		assertResultInvalid("null < null");
	}

	public void testLessThanOrEqualInvalid() {
		assertResultInvalid("invalid <= 0");
		assertResultInvalid("0 <= invalid");
		assertResultInvalid("invalid <= invalid");
	}

	public void testLessThanOrEqualNull() {
		assertResultInvalid("null <= 0");
		assertResultInvalid("0 <= null");
		assertResultInvalid("null <= null");
	}

	public void testNotEqualInvalid() {
		/*
		 * FIXME we're expecting here results as specified in 2.0 : (11.2.4) any
		 * call on invalid results in invalid. Note that 2.1 didn't alter
		 * this statement but added in 11.2.5 an override of '=' so that it
		 * returns "true if object is invalid". Which should we respect in the
		 * implementation? respecting 11.2.4 is better as far as error handling
		 * is concerned ... but then we should probably respect 11.2.3 and have
		 * '=' fail on 'null'.
		 */
		assertResultInvalid("invalid <> 3");
		assertResultInvalid("3 <> invalid");
		assertResultInvalid("invalid <> 3.0");
		assertResultInvalid("3.0 <> invalid");

		assertResultInvalid("invalid <> 'test'");
		assertResultInvalid("'test' <> invalid");
		assertResultInvalid("invalid <> true");
		assertResultInvalid("false <> invalid");
		assertResultInvalid("invalid <> Sequence{}");
		assertResultInvalid("Sequence{} <> invalid");

		assertResultInvalid("invalid <> invalid");
	}

	public void testNotEqualNull() {
		assertResultTrue("null <> 3");
		assertResultTrue("3 <> null");
		assertResultTrue("null <> 3.0");
		assertResultTrue("3.0 <> null");

		assertResultTrue("null <> 'test'");
		assertResultTrue("'test' <> null");
		assertResultTrue("null <> true");
		assertResultTrue("false <> null");
		assertResultTrue("null <> Sequence{}");
		assertResultTrue("Sequence{} <> null");

		assertResultFalse("null <> null");
	}

	public void testOclIsInvalidInvalid() {
		assertResultTrue("invalid.oclIsInvalid()");
	}

	public void testOclIsInvalidNull() {
		assertResultFalse("null.oclIsInvalid()");
	}

	public void testOclIsTypeOfOclAny() {
		assertResultFalse("null.oclIsTypeOf(OclAny)");
		assertResultFalse("'abc'.oclIsTypeOf(OclAny)");
		assertResultFalse("invalid.oclIsTypeOf(OclAny)");
	}

	public void testOclIsUndefinedInvalid() {
		assertResultTrue("invalid.oclIsUndefined()");
	}

	public void testOclIsUndefinedNull() {
		assertResultTrue("null.oclIsUndefined()");
	}
}
