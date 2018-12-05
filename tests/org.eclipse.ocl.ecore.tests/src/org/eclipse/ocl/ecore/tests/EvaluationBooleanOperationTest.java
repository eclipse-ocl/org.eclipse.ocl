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
import org.eclipse.ocl.tests.GenericEvaluationBooleanOperationTest;

@SuppressWarnings("nls")
public class EvaluationBooleanOperationTest
extends GenericEvaluationBooleanOperationTest<EObject, EPackage, EClassifier, EClassifier, EClass, EDataType, EClassifier, EEnum, EOperation, EParameter, EStructuralFeature,
EAttribute, EReference, EEnumLiteral, EObject, CallOperationAction, SendSignalAction, Constraint>
{
	@Override
	public EcoreTestReflection getStaticReflection() {
		return EcoreTestReflection.INSTANCE;
	}

	public void testBooleanOclIsKindOf() {										// BUG 378036 this doesn't work for UML
		assertResultFalse("true.oclIsKindOf(Integer)");
		assertResultFalse("true.oclIsKindOf(String)");
		assertResultTrue("true.oclIsKindOf(Boolean)");
		assertResultTrue("true.oclIsKindOf(OclAny)");
		assertResultFalse("true.oclIsKindOf(OclVoid)");
		assertResultFalse("true.oclIsKindOf(OclInvalid)");
	}

	public void testBooleanOclIsTypeOf() {										// BUG 378036 this doesn't work for UML
		assertResultFalse("true.oclIsTypeOf(Integer)");
		assertResultFalse("true.oclIsTypeOf(String)");
		assertResultTrue("true.oclIsTypeOf(Boolean)");
		assertResultFalse("true.oclIsTypeOf(OclAny)");
		assertResultFalse("true.oclIsTypeOf(OclVoid)");
		assertResultFalse("true.oclIsTypeOf(OclInvalid)");
	}
}
