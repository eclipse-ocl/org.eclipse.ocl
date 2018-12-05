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
import org.eclipse.ocl.tests.GenericEvaluationCollectionOperationTest;

@SuppressWarnings("nls")
public class EvaluationCollectionOperationTest
extends GenericEvaluationCollectionOperationTest<EObject, EPackage, EClassifier, EClassifier, EClass, EDataType, EClassifier, EEnum, EOperation, EParameter, EStructuralFeature,
EAttribute, EReference, EEnumLiteral, EObject, CallOperationAction, SendSignalAction, Constraint>
{
	@Override
	public EcoreTestReflection getStaticReflection() {
		return EcoreTestReflection.INSTANCE;
	}

	public void testCollectionSelectByKind() {										// BUG 378036 this doesn't work for UML
		assertExpressionResults("Bag{4,4}", "Bag{4, 4, 5.0, 'test', null}->selectByKind(Integer)");
		assertExpressionResults("OrderedSet{4}", "OrderedSet{4, 4, 5.0, 'test', null}->selectByKind(Integer)");
		assertExpressionResults("Sequence{4,4}", "Sequence{4, 4, 5.0, 'test', null}->selectByKind(Integer)");
		assertExpressionResults("Set{4}", "Set{4, 4, 5.0, 'test', null}->selectByKind(Integer)");
		//
		assertExpressionResults("Bag{}", "Bag{}->selectByKind(Integer)");
		assertExpressionResults("OrderedSet{}", "OrderedSet{}->selectByKind(Integer)");
		assertExpressionResults("Sequence{}", "Sequence{}->selectByKind(Integer)");
		assertExpressionResults("Set{}", "Set{}->selectByKind(Integer)");
		//
		assertExpressionResults("Bag{}", "Bag{null}->selectByKind(Integer)");
		assertExpressionResults("OrderedSet{}", "OrderedSet{null}->selectByKind(Integer)");
		assertExpressionResults("Sequence{}", "Sequence{null}->selectByKind(Integer)");
		assertExpressionResults("Set{}", "Set{null}->selectByKind(Integer)");
		//
		assertExpressionResults("Bag{}", "Bag{null}->selectByKind(OclVoid)");
		assertExpressionResults("OrderedSet{}", "OrderedSet{null}->selectByKind(OclVoid)");
		assertExpressionResults("Sequence{}", "Sequence{null}->selectByKind(OclVoid)");
		assertExpressionResults("Set{}", "Set{null}->selectByKind(OclVoid)");
		//
		assertExpressionResults("Bag{}", "Bag{null}->selectByKind(OclInvalid)");
		assertExpressionResults("OrderedSet{}", "OrderedSet{null}->selectByKind(OclInvalid)");
		assertExpressionResults("Sequence{}", "Sequence{null}->selectByKind(OclInvalid)");
		assertExpressionResults("Set{}", "Set{null}->selectByKind(OclInvalid)");
		//
		assertExpressionResults("Bag{4, 4}", "let test : Bag(UnlimitedNatural) = Bag{4, 4, 5.0, 'test'}->selectByKind(UnlimitedNatural) in test");
		assertExpressionResults("OrderedSet{4}", "let test : OrderedSet(UnlimitedNatural) = OrderedSet{4, 4, 5.0, 'test'}->selectByKind(UnlimitedNatural) in test");
		assertExpressionResults("Sequence{4, 4}", "let test : Sequence(UnlimitedNatural) = Sequence{4, 4, 5.0, 'test'}->selectByKind(UnlimitedNatural) in test");
		assertExpressionResults("Set{4}", "let test : Set(UnlimitedNatural) = Set{4, 4, 5.0, 'test'}->selectByKind(UnlimitedNatural) in test");
		//
		assertExpressionResults("Sequence{'TEST'}", "Sequence{4, 4, 5.0, 'test'}->selectByKind(String).toUpperCase()");
		assertResult(9.0, "Set{4, 4, 5.0, 'test'}->selectByKind(Real)->sum()");
		assertResult(4, "Set{4, 4, 5.0, 'test'}->selectByKind(UnlimitedNatural)->sum()");
	}

	public void testCollectionSelectByType() {										// BUG 378036 this doesn't work for UML
//		assertExpressionResults("Bag{}", "Bag{4, 4, 5.0, 'test', null}->selectByType(Integer)");
		assertExpressionResults("Bag{}", "Bag{4, 4, 5.0, 'test', null}->selectByType(Boolean)");
//		assertExpressionResults("OrderedSet{}", "OrderedSet{4, 4, 5.0, 'test', null}->selectByType(Integer)");
		assertExpressionResults("OrderedSet{}", "OrderedSet{4, 4, 5.0, 'test', null}->selectByType(Boolean)");
//		assertExpressionResults("Sequence{}", "Sequence{4, 4, 5.0, 'test', null}->selectByType(Integer)");
		assertExpressionResults("Sequence{}", "Sequence{4, 4, 5.0, 'test', null}->selectByType(Boolean)");
//		assertExpressionResults("Set{}", "Set{4, 4, 5.0, 'test', null}->selectByType(Integer)");
		assertExpressionResults("Set{}", "Set{4, 4, 5.0, 'test', null}->selectByType(Boolean)");
		//
		assertExpressionResults("Bag{}", "Bag{}->selectByType(Integer)");
		assertExpressionResults("OrderedSet{}", "OrderedSet{}->selectByType(Integer)");
		assertExpressionResults("Sequence{}", "Sequence{}->selectByType(Integer)");
		assertExpressionResults("Set{}", "Set{}->selectByType(Integer)");
		//
		assertExpressionResults("Bag{}", "Bag{null}->selectByType(Integer)");
		assertExpressionResults("OrderedSet{}", "OrderedSet{null}->selectByType(Integer)");
		assertExpressionResults("Sequence{}", "Sequence{null}->selectByType(Integer)");
		assertExpressionResults("Set{}", "Set{null}->selectByType(Integer)");
		//
		assertExpressionResults("Bag{null}", "Bag{null}->selectByType(OclVoid)");
		assertExpressionResults("OrderedSet{null}", "OrderedSet{null}->selectByType(OclVoid)");
		assertExpressionResults("Sequence{null}", "Sequence{null}->selectByType(OclVoid)");
		assertExpressionResults("Set{null}", "Set{null}->selectByType(OclVoid)");
		//
		assertExpressionResults("Bag{}", "Bag{null}->selectByType(OclInvalid)");
		assertExpressionResults("OrderedSet{}", "OrderedSet{null}->selectByType(OclInvalid)");
		assertExpressionResults("Sequence{}", "Sequence{null}->selectByType(OclInvalid)");
		assertExpressionResults("Set{}", "Set{null}->selectByType(OclInvalid)");
		//
		assertExpressionResults("Bag{4, 4}", "let test : Bag(UnlimitedNatural) = Bag{4, 4, 5.0, 'test'}->selectByType(UnlimitedNatural) in test");
		assertExpressionResults("OrderedSet{4}", "let test : OrderedSet(UnlimitedNatural) = OrderedSet{4, 4, 5.0, 'test'}->selectByType(UnlimitedNatural) in test");
		assertExpressionResults("Sequence{4, 4}", "let test : Sequence(UnlimitedNatural) = Sequence{4, 4, 5.0, 'test'}->selectByType(UnlimitedNatural) in test");
		assertExpressionResults("Set{4}", "let test : Set(UnlimitedNatural) = Set{4, 4, 5.0, 'test'}->selectByType(UnlimitedNatural) in test");
		//
		assertExpressionResults("Sequence{'TEST'}", "Sequence{4, 4, 5.0, 'test'}->selectByType(String).toUpperCase()");
		assertResult(5.0, "Set{4, 4, 5.0, 'test'}->selectByType(Real)->sum()");
		assertResult(4, "Set{4, 4, 5.0, 'test'}->selectByType(UnlimitedNatural)->sum()");
	}
}
