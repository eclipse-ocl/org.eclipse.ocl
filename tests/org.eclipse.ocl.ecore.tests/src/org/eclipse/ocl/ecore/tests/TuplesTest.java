/*******************************************************************************
 * Copyright (c) 2006, 2018 IBM Corporation, Zeligsoft Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - bug 238049
 *******************************************************************************/

package org.eclipse.ocl.ecore.tests;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.tests.GenericTuplesTest;

/**
 * Tests for tuple expressions.
 *
 * @author Christian W. Damus (cdamus)
 */
@SuppressWarnings("nls")
public class TuplesTest
	extends GenericTuplesTest<EObject, EPackage, EClassifier, EClassifier, EClass, EDataType, EClassifier, EEnum, EOperation, EParameter, EStructuralFeature,
	EAttribute, EReference, EEnumLiteral, EObject, CallOperationAction, SendSignalAction, Constraint>
{
	@Override
	public EcoreTestReflection getStaticReflection() {
		return EcoreTestReflection.INSTANCE;
	}
	
	@Override
	protected void initFruitPackage() {
		super.initFruitPackage();
		resourceSet.getPackageRegistry().put(fruitPackage.getNsURI(), fruitPackage);
	}

	/**
	 * Tests that TupleTypes are Ecore EDataTypes as well as EClasses.
	 */
	public void test_tupleTypesAreEDataTypes_222287() {
        OCLExpression<EClassifier> expr = parse(
                "package ocltest context Fruit " +
                "inv: Tuple{a = 1, b = 'foo', c = Color::red} " +
                "endpackage");
        
        assertTrue(EcorePackage.Literals.ECLASS.isSuperTypeOf(
                expr.getType().eClass()));
        assertTrue(expr.getType() instanceof EClass);
        assertTrue(EcorePackage.Literals.EDATA_TYPE.isSuperTypeOf(
                expr.getType().eClass()));
        assertTrue(expr.getType() instanceof EDataType);
	}
}
