/**
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 */
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
import org.eclipse.ocl.ecore.CallOperationAction;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.SendSignalAction;
import org.eclipse.ocl.tests.GenericEvaluationNumberOperationTest;

@SuppressWarnings("nls")
public class EvaluationNumberOperationTest
extends GenericEvaluationNumberOperationTest<EObject, EPackage, EClassifier, EClassifier, EClass, EDataType, EClassifier, EEnum, EOperation, EParameter, EStructuralFeature,
EAttribute, EReference, EEnumLiteral, EObject, CallOperationAction, SendSignalAction, Constraint>
{
	@Override
	public EcoreTestReflection getStaticReflection() {
		return EcoreTestReflection.INSTANCE;
	}

	public void testNumberOclAsType() {										// BUG 378036 this doesn't work for UML
		assertResult(Integer.valueOf(3), "3.oclAsType(Integer)");
		// FIXME should this be Double or Integer?
		assertResult(Double.valueOf(3), "3.oclAsType(Real)");
		assertResultInvalid("3.oclAsType(String)");
		assertResult(Integer.valueOf(3), "3.oclAsType(OclAny)");
		assertResultInvalid("3.oclAsType(OclVoid)");
		assertResultInvalid("3.oclAsType(OclInvalid)");

		assertResultInvalid("(3.0).oclAsType(Integer)");
		assertResult(Double.valueOf(3), "(3.0).oclAsType(Real)");
		assertResultInvalid("(3.0).oclAsType(String)");
		// FIXME should this be Double or Integer?
		assertResult(Double.valueOf(3), "(3.0).oclAsType(OclAny)");
		assertResultInvalid("(3.0).oclAsType(OclVoid)");
		assertResultInvalid("(3.0).oclAsType(OclInvalid)");
	}

	public void testNumberOclIsKindOf() {										// BUG 378036 this doesn't work for UML
		assertResultTrue("3.oclIsKindOf(Integer)");
		assertResultTrue("3.oclIsKindOf(Real)");
		assertResultFalse("3.oclIsKindOf(String)");
		assertResultTrue("3.oclIsKindOf(OclAny)");
		assertResultFalse("3.oclIsKindOf(OclVoid)");
		assertResultFalse("3.oclIsKindOf(OclInvalid)");

		assertResultFalse("(3.0).oclIsKindOf(Integer)");
		assertResultTrue("(3.0).oclIsKindOf(Real)");
		assertResultFalse("(3.0).oclIsKindOf(String)");
		assertResultTrue("(3.0).oclIsKindOf(OclAny)");
		assertResultFalse("(3.0).oclIsKindOf(OclVoid)");
		assertResultFalse("(3.0).oclIsKindOf(OclInvalid)");
	}

	public void testNumberOclIsTypeOf() {										// BUG 378036 this doesn't work for UML
		assertResultTrue("3.oclIsTypeOf(Integer)");
		assertResultFalse("3.oclIsTypeOf(Real)");
		assertResultFalse("3.oclIsTypeOf(String)");
		assertResultFalse("3.oclIsTypeOf(OclAny)");
		assertResultFalse("3.oclIsTypeOf(OclVoid)");
		assertResultFalse("3.oclIsTypeOf(OclInvalid)");

		assertResultFalse("(3.0).oclIsTypeOf(Integer)");
		assertResultTrue("(3.0).oclIsTypeOf(Real)");
		assertResultFalse("(3.0).oclIsTypeOf(String)");
		assertResultFalse("(3.0).oclIsTypeOf(OclAny)");
		assertResultFalse("(3.0).oclIsTypeOf(OclVoid)");
		assertResultFalse("(3.0).oclIsTypeOf(OclInvalid)");
	}

	@Override
	public void testNumberToString() {											// BUG 378036 this doesn't work for UML
		super.testNumberToString();
		assertResult("6", "6.oclAsType(UnlimitedNatural).toString()");			// Tests fix for BUG 528246
	}

	public void testUnlimitedOclIsKindOf() {									// BUG 378036 this doesn't work for UML
		assertResultTrue("*.oclIsKindOf(Integer)");
		assertResultTrue("*.oclIsKindOf(Real)");
		assertResultTrue("*.oclIsKindOf(UnlimitedNatural)");
		assertResultFalse("*.oclIsKindOf(String)");
		assertResultTrue("*.oclIsKindOf(OclAny)");
		assertResultFalse("*.oclIsKindOf(OclVoid)");
		assertResultFalse("*.oclIsKindOf(OclInvalid)");
	}

	@Override
	public void testUnlimitedToInteger() {											// BUG 378036 this doesn't work for UML
		super.testUnlimitedToInteger();
		assertResult(Integer.valueOf(-1), "-1.oclAsType(UnlimitedNatural).toInteger()");
		assertResultInvalid("(-1).oclAsType(UnlimitedNatural).toInteger()");
		assertResult(Integer.valueOf(4), "4.oclAsType(UnlimitedNatural).toInteger()");
	}
}
