/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - Bugs 296409, 295166
 *******************************************************************************/

package org.eclipse.ocl.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.options.ParsingOptions;

/**
 * Tests for usages of model features whose names coincide with "keywords"
 * defined as reserved words by the OCL specification.
 * 
 * @author Christian W. Damus (cdamus)
 */
@SuppressWarnings("nls")
public abstract class GenericTuplesTest<E extends EObject, PK extends E, T extends E, C extends T, CLS extends C, DT extends C, PT extends C, ET extends DT, O extends E, PM extends E, P extends E, PA extends P, PR extends P, EL, S, COA, SSA, CT>
	extends GenericInitializedFruitTestSuite<E, PK, T, C, CLS, DT, PT, ET, O, PM, P, PA, PR, EL, S, COA, SSA, CT> {

	/**
	 * Tests the parsing of tuple literals with part types.
	 */
	public void test_tupleLiteral_withTypes() {
		// one part
		parse("package ocltest context Fruit " +
				"inv: Tuple{a : Integer = 1} " +
				"endpackage");
		
		// multiple parts
		parse("package ocltest context Fruit " +
				"inv: Tuple{a : Integer = 1, b : String = 'foo', c : Color = Color::red} " +
				"endpackage");
	}
	
	/**
	 * Tests the parsing of tuple literals without part types.
	 */
	public void test_tupleLiteral_noTypes() {
		// one part
		parse("package ocltest context Fruit " +
				"inv: Tuple{a = 1} " +
				"endpackage");
		
		// multiple parts
		parse("package ocltest context Fruit " +
				"inv: Tuple{a = 1, b = 'foo', c = Color::red} " +
				"endpackage");
	}
	
	/**
	 * Tests the parsing of tuple literals with a mixture of explicit and
	 * implicit part types.
	 */
	public void test_tupleLiteral_mixedTypes() {
		parse("package ocltest context Fruit " +
				"inv: Tuple{a : Integer = 1, b = 'foo', c : Color = Color::red} " +
				"endpackage");
		
		parse("package ocltest context Fruit " +
				"inv: Tuple{a = 1, b : String = 'foo', c = Color::red} " +
				"endpackage");
	}
	
	/**
	 * Tests that OclVoid and other tuple components with null values work as
	 * expected
	 */
	public void test_tupleLiteral_nullValues() {
		// one part
		assertEquals(null, evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a = null}.a " +
			"endpackage")));
		assertEquals(null, evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a:OclVoid = null}.a " +
			"endpackage")));
		assertEquals(null, evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a:String = null}.a " +
			"endpackage")));
		assertEquals(null, evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a:Fruit = null}.a " +
			"endpackage")));
		
		// multiple parts
		assertEquals(1, evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a = null, b=1}.b " +
			"endpackage")));
		assertEquals(null, evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a = null, b=1}.a " +
			"endpackage")));
		assertEquals(null, evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a:String = null, b='abc'}.a " +
			"endpackage")));
		assertEquals("abc", evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a:String = null, b='abc'}.b " +
			"endpackage")));
		assertEquals(null, evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a:Fruit = null, b:String='abc'}.a " +
			"endpackage")));
		assertEquals(null, evaluate(parse(
			"package ocltest context Fruit " +
			"inv: Tuple{a:Fruit = null, b:OclVoid=null}.b " +
			"endpackage")));
	}
	
	/**
	 * Tests the parsing of initialization of variables of tuple type.
	 */
	public void test_tupleAssignment() {
		parse("package ocltest context Fruit " +
				"inv: let t : Tuple(a : Integer, b : String, c : Color) " +
					" = Tuple{a = 1, b = 'foo', c = Color::red} " +
					" in t " +
				"endpackage");
	}
	
	/**
	 * Tests the parsing of collections of tuple types.
	 */
	public void test_tupleCollections() {
		parse("package ocltest context Fruit " +
				"inv: let ts : Set(Tuple(a : Integer, b : String, c : Color)) " +
					" = Set{}->including(Tuple{a = 1, b = 'foo', c = Color::red}) " +
					" in ts " +
				"endpackage");
	}
	
	/**
	 * Tests the equivalence of tuples, including irrelevance of ordering of
	 * the parts
	 */
	public void test_tupleEquivalence() {
		// different value in one part
		assertEquals(Boolean.FALSE, evaluate(parse(
				"package ocltest context Fruit " +
				"inv: Tuple{a = 1, b = 'foo', c = Color::red} " +
					" = Tuple{b = 'foo', c = Color::black, a = 1} " +
				"endpackage")));
		
		// test the inequality operator
		assertEquals(Boolean.TRUE, evaluate(parse(
				"package ocltest context Fruit " +
				"inv: Tuple{a = 1, b = 'foo', c = Color::red} " +
					" <> Tuple{b = 'foo', c = Color::black, a = 1} " +
				"endpackage")));
		
		// all parts have same value
		assertEquals(Boolean.TRUE, evaluate(parse(
				"package ocltest context Fruit " +
				"inv: Tuple{a = 1, b = 'foo', c = Color::red} " +
					" = Tuple{b = 'foo', c = Color::red, a = 1} " +
				"endpackage")));
		
		// equality of collections of tuples
		assertEquals(Boolean.TRUE, evaluate(parse(
				"package ocltest context Fruit " +
				"inv: Set{Tuple{a = 1, b = 'foo'}, Tuple{a = 5, b = 'bar'}} " +
					" = Set{Tuple{b = 'bar', a = 5}, Tuple{b = 'foo', a = 1}} " +
				"endpackage")));
	}
    
    /**
     * Tests that the parser distinguishes <code>TupleType</code>s by the names
     * and types of their parts, not by the names alone.
     */
	public void test_tupleTypeDistinction_192832() {
        OCLInput doc = new OCLInput(
            "package ocltest context Fruit " +
            "def: foo : Tuple(a : String) = Tuple{a = 'foo'} " +
            "def: bar : Tuple(a : Integer) = Tuple{a = 100} " +
            "endpackage");
        
        try {
            ocl.parse(doc);
            
            helper.setContext(fruit);
            OCLExpression<C> expr1 = helper.createQuery("self.foo");
            OCLExpression<C> expr2 = helper.createQuery("self.bar");
            
            assertNotSame(expr1.getType(), expr2.getType());
            
            C type = expr1.getType();
			P a = reflection.getAttribute(type, "a", null);
            assertNotNull(a);
            assertEquals(getOCLStandardLibrary().getString(), reflection.getOCLType(a));
            
            type = expr2.getType();
            a = reflection.getAttribute(type, "a", null);
            assertNotNull(a);
            assertEquals(getOCLStandardLibrary().getInteger(), reflection.getOCLType(a));
        } catch (Exception e) {
            fail("Failed to parse: " + e.getLocalizedMessage());
        }
    }
	
	/**
	 * Tests access to tuple parts by name when all parts have the same type.
	 */
	public void test_tuplePartAccess_238049() {
        OCLExpression<C> expr = parse(
            "package ocltest context Fruit " +
            "inv: Tuple{first : String = 'Roger', last : String = 'Bacon'}.first " +
            "endpackage");
    
		assertEquals("Roger", evaluate(expr));
	}
	
	public void test_tupleWithLongLiteral_349117() {
        ParsingOptions.setOption(helper.getOCL().getEnvironment(), ParsingOptions.USE_LONG_INTEGERS, true);
		OCLExpression<C> expr = parse("package ocltest context Fruit "
			+ "inv: Tuple{first : Integer = 5000000000}.first " + "endpackage");
		assertEquals(5000000000l, evaluate(expr));
        ParsingOptions.setOption(helper.getOCL().getEnvironment(), ParsingOptions.USE_LONG_INTEGERS, false);
	}

	public void test_tupleWithLongIntegerProperty_349117() throws ParserException {
        ParsingOptions.setOption(helper.getOCL().getEnvironment(), ParsingOptions.USE_LONG_INTEGERS, true);
		// tuples work fine with int / java.lang.Integer
		helper.setContext(reflection.getUMLInteger());
		OCLExpression<C> expr2 = helper.createQuery("Tuple{first : Integer = self}.first");
		assertEquals(5000000, evaluate(expr2, Integer.valueOf(5000000)));

		// we can compute with Long values
		helper.setContext(reflection.getUMLLong());
		OCLExpression<C> expr3 = helper.createQuery("2*self");
		assertEquals(10000000000l, evaluate(expr3, Long.valueOf(5000000000l)));

		// we should be able to create the tuple based on a value exceeding int, represented as long
		helper.setContext(reflection.getMetametaclass(denormalize("%Package")));
		OCLExpression<C> expr4 = helper.createQuery("Tuple{first : Integer = 1000*5000000}.first");
		assertEquals(5000000000l, evaluate(expr4, reflection.getMetametaclass("Package")));

		// we should be able to create the tuple based on a java.lang.Long as an OCL Integer
		helper.setContext(reflection.getUMLInteger());
		OCLExpression<C> expr5 = helper.createQuery("Tuple{first : Integer = self}.first");
		assertEquals(5000000000l, evaluate(expr5, Long.valueOf(5000000000l)));
        ParsingOptions.setOption(helper.getOCL().getEnvironment(), ParsingOptions.USE_LONG_INTEGERS, false);
	}
}
