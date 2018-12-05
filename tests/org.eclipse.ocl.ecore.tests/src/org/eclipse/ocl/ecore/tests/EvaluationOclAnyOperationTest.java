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
import org.eclipse.ocl.options.EvaluationOptions;
import org.eclipse.ocl.tests.GenericEvaluationOclAnyOperationTest;

@SuppressWarnings("nls")
public class EvaluationOclAnyOperationTest
extends GenericEvaluationOclAnyOperationTest<EObject, EPackage, EClassifier, EClassifier, EClass, EDataType, EClassifier, EEnum, EOperation, EParameter, EStructuralFeature,
EAttribute, EReference, EEnumLiteral, EObject, CallOperationAction, SendSignalAction, Constraint>
{
	@Override
	public EcoreTestReflection getStaticReflection() {
		return EcoreTestReflection.INSTANCE;
	}

	public void testOclAsTypeInvalidLaxNullHandlingInvalid() {										// BUG 378036 this doesn't work for UML
		assertResultInvalid("invalid.oclAsType(String)");
		assertResultInvalid("invalid.oclAsType(EClass)");
		assertResultInvalid("invalid.oclAsType(OclVoid)");
		assertResultInvalid("invalid.oclAsType(OclInvalid)");
	}

	public void testOclAsTypeLaxNullHandlingNull() {										// BUG 378036 this doesn't work for UML
		/*
		 * FIXME EvaluationOptions.LAX_NULL_HANDLING is on, its javadoc tells us
		 * "null" is the expected result whatever the given type. We should
		 * either fix the evaluation or the javadoc.
		 */
		assertResult(null, "null.oclAsType(String)");
		assertResult(null, "null.oclAsType(Integer)");
		assertResult(null, "null.oclAsType(EClass)");
		assertResult(null, "null.oclAsType(OclVoid)");
		assertResult(null, "null.oclAsType(OclInvalid)");
	}

	public void testOclAsTypeNoLaxNullHandlingInvalid() {										// BUG 378036 this doesn't work for UML
		Boolean oldNullHandling = EvaluationOptions.getValue(ocl
			.getEvaluationEnvironment(), EvaluationOptions.LAX_NULL_HANDLING);
		// If this assert ever fails, LAX_NULL_HANDLING's default changed
		assertEquals(Boolean.TRUE, oldNullHandling);
		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.FALSE);

		assertResultInvalid("invalid.oclAsType(String)");
		assertResultInvalid("invalid.oclAsType(Integer)");
		assertResultInvalid("invalid.oclAsType(EClass)");
		assertResultInvalid("invalid.oclAsType(OclVoid)");
		assertResultInvalid("invalid.oclAsType(OclInvalid)");

		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.TRUE);
	}

	public void testOclAsTypeNoLaxNullHandlingNull() {										// BUG 378036 this doesn't work for UML
		Boolean oldNullHandling = EvaluationOptions.getValue(ocl
			.getEvaluationEnvironment(), EvaluationOptions.LAX_NULL_HANDLING);
		// If this assert ever fails, LAX_NULL_HANDLING's default changed
		assertEquals(Boolean.TRUE, oldNullHandling);
		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.FALSE);

		assertResultInvalid("null.oclAsType(String)");
		assertResultInvalid("null.oclAsType(Integer)");
		assertResultInvalid("null.oclAsType(EClass)");
		assertResultInvalid("null.oclAsType(OclVoid)");
		assertResultInvalid("null.oclAsType(OclInvalid)");

		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.TRUE);
	}

	public void testOclIsKindOfInvalidLaxNullHandling() {										// BUG 378036 this doesn't work for UML
		/*
		 * FIXME why is the evaluation of oclIsKindOf altered for invalid
		 * with LAX_NULL_HANDLING off? That is no documented behavior.
		 */
		assertResultTrue("invalid.oclIsKindOf(String)");
		assertResultTrue("invalid.oclIsKindOf(EClass)");
		assertResultTrue("invalid.oclIsKindOf(OclVoid)");
		assertResultTrue("null.oclIsKindOf(OclVoid)");
		assertResultTrue("invalid.oclIsKindOf(OclInvalid)");
		assertResultFalse("null.oclIsKindOf(OclInvalid)");
	}

	public void testOclIsKindOfInvalidNoLaxHandling() {										// BUG 378036 this doesn't work for UML
		Boolean oldNullHandling = EvaluationOptions.getValue(ocl
			.getEvaluationEnvironment(), EvaluationOptions.LAX_NULL_HANDLING);
		// If this assert ever fails, LAX_NULL_HANDLING's default changed
		assertEquals(Boolean.TRUE, oldNullHandling);
		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.FALSE);

		assertResultInvalid("invalid.oclIsKindOf(String)");
		assertResultInvalid("invalid.oclIsKindOf(Integer)");
		assertResultInvalid("invalid.oclIsKindOf(EClass)");
		assertResultInvalid("invalid.oclIsKindOf(OclVoid)");
		assertResultInvalid("invalid.oclIsKindOf(OclInvalid)");

		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.TRUE);
	}

	public void testOclIsKindOfNullLaxNullHandling() {										// BUG 378036 this doesn't work for UML
		/*
		 * FIXME EvaluationOptions.LAX_NULL_HANDLING is on, its javadoc tells us
		 * "true" is the expected result whatever the given type. We should
		 * either fix the evaluation or the javadoc.
		 */
		assertResultTrue("null.oclIsKindOf(String)");
		assertResultTrue("null.oclIsKindOf(Integer)");
		assertResultTrue("null.oclIsKindOf(EClass)");
		assertResultTrue("null.oclIsKindOf(OclVoid)");
		// OclVoid conforms to all other types except OclInvalid,
		// see OCL 2.3 specification (10-11-42) section 8.2
		assertResultFalse("null.oclIsKindOf(OclInvalid)");
	}

	public void testOclIsKindOfNullNoLaxHandling() {										// BUG 378036 this doesn't work for UML
		Boolean oldNullHandling = EvaluationOptions.getValue(ocl
			.getEvaluationEnvironment(), EvaluationOptions.LAX_NULL_HANDLING);
		// If this assert ever fails, LAX_NULL_HANDLING's default changed
		assertEquals(Boolean.TRUE, oldNullHandling);
		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.FALSE);

		assertResultInvalid("null.oclIsKindOf(String)");
		assertResultInvalid("null.oclIsKindOf(Integer)");
		assertResultInvalid("null.oclIsKindOf(EClass)");
		assertResultInvalid("null.oclIsKindOf(OclVoid)");
		assertResultInvalid("null.oclIsKindOf(OclInvalid)");

		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.TRUE);
	}
	
	public void testOclIsKindOfOclAny() {										// BUG 378036 this doesn't work for UML
		assertResultTrue("null.oclIsKindOf(OclAny)");
		assertResultTrue("'abc'.oclIsKindOf(OclAny)");
		assertResultTrue("invalid.oclIsKindOf(OclAny)");
	}

	public void testOclIsTypeOfInvalidLaxNullHandling() {										// BUG 378036 this doesn't work for UML
		/*
		 * FIXME why is the evaluation of oclIsTypeOf altered for invalid
		 * with LAX_NULL_HANDLING off? That is no documented behavior.
		 */
		// invalid is kind of but not type of String
		// because OclInvalid "conforms" to but is not
		// identical to all types
		assertResultFalse("invalid.oclIsTypeOf(String)");
		assertResultFalse("invalid.oclIsTypeOf(EClass)");
		assertResultFalse("invalid.oclIsTypeOf(OclVoid)");
		assertResultTrue("invalid.oclIsTypeOf(OclInvalid)");
		assertResultFalse("null.oclIsTypeOf(OclInvalid)");
		assertResultTrue("null.oclIsTypeOf(OclVoid)");
	}

	public void testOclIsTypeOfInvalidNoLaxNullHandling() {										// BUG 378036 this doesn't work for UML
		Boolean oldNullHandling = EvaluationOptions.getValue(ocl
			.getEvaluationEnvironment(), EvaluationOptions.LAX_NULL_HANDLING);
		// If this assert ever fails, LAX_NULL_HANDLING's default changed
		assertEquals(Boolean.TRUE, oldNullHandling);
		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.FALSE);

		assertResultInvalid("invalid.oclIsTypeOf(String)");
		assertResultInvalid("invalid.oclIsTypeOf(Integer)");
		assertResultInvalid("invalid.oclIsTypeOf(EClass)");
		assertResultInvalid("invalid.oclIsTypeOf(OclVoid)");
		assertResultInvalid("invalid.oclIsTypeOf(OclInvalid)");

		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.TRUE);
	}

	public void testOclIsTypeOfNullLaxNullHandling() {										// BUG 378036 this doesn't work for UML
		/*
		 * FIXME EvaluationOptions.LAX_NULL_HANDLING is on, its javadoc tells us
		 * "true" is the expected result whatever the given type. We should
		 * either fix the evaluation or the javadoc.
		 */
		// OclVoid "conforms" to but is not identical to all types
		// except OclInvalid. Therefore, oclIsTypeOf (as opposed to
		// oclIsKindOf) ha sto return false for the respective checks: 
		assertResultFalse("null.oclIsTypeOf(String)");
		assertResultFalse("null.oclIsTypeOf(Integer)");
		assertResultFalse("null.oclIsTypeOf(EClass)");
		// null's type is OclVoid
		assertResultTrue("null.oclIsTypeOf(OclVoid)");
		// OclVoid conforms to all other types except OclInvalid,
		// see OCL 2.3 specification (10-11-42) section 8.2
		assertResultFalse("null.oclIsTypeOf(OclInvalid)");
	}

	public void testOclIsTypeOfNullNoLaxNullHandling() {										// BUG 378036 this doesn't work for UML
		Boolean oldNullHandling = EvaluationOptions.getValue(ocl
			.getEvaluationEnvironment(), EvaluationOptions.LAX_NULL_HANDLING);
		// If this assert ever fails, LAX_NULL_HANDLING's default changed
		assertEquals(Boolean.TRUE, oldNullHandling);
		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.FALSE);

		assertResultInvalid("null.oclIsTypeOf(String)");
		assertResultInvalid("null.oclIsTypeOf(Integer)");
		assertResultInvalid("null.oclIsTypeOf(EClass)");
		assertResultInvalid("null.oclIsTypeOf(OclVoid)");
		assertResultInvalid("null.oclIsTypeOf(OclInvalid)");

		EvaluationOptions.setOption(ocl.getEvaluationEnvironment(),
			EvaluationOptions.LAX_NULL_HANDLING, Boolean.TRUE);
	}
}
