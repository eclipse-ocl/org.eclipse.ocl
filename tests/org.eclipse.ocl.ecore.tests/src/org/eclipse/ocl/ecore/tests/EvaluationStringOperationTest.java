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
import org.eclipse.ocl.tests.GenericEvaluationStringOperationTest;

@SuppressWarnings("nls")
public class EvaluationStringOperationTest
extends GenericEvaluationStringOperationTest<EObject, EPackage, EClassifier, EClassifier, EClass, EDataType, EClassifier, EEnum, EOperation, EParameter, EStructuralFeature,
EAttribute, EReference, EEnumLiteral, EObject, CallOperationAction, SendSignalAction, Constraint>
{
	@Override
	public EcoreTestReflection getStaticReflection() {
		return EcoreTestReflection.INSTANCE;
	}

	public void testStringOclIsKindOf() {										// BUG 378036 this doesn't work for UML
		assertResultFalse("'test'.oclIsKindOf(Integer)");
		assertResultTrue("'test'.oclIsKindOf(String)");
		assertResultTrue("'test'.oclIsKindOf(OclAny)");
		assertResultFalse("'test'.oclIsKindOf(OclVoid)");
		assertResultFalse("'test'.oclIsKindOf(OclInvalid)");
	}

	public void testStringOclIsTypeOf() {										// BUG 378036 this doesn't work for UML
		assertResultFalse("'test'.oclIsTypeOf(Integer)");
		assertResultTrue("'test'.oclIsTypeOf(String)");
		assertResultFalse("'test'.oclIsTypeOf(OclAny)");
		assertResultFalse("'test'.oclIsTypeOf(OclVoid)");
		assertResultFalse("'test'.oclIsTypeOf(OclInvalid)");
	}
}
