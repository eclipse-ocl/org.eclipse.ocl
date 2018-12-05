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
package org.eclipse.ocl.uml.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.tests.GenericEvaluationBooleanOperationTest;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.DataType;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.PrimitiveType;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;
import org.eclipse.uml2.uml.Type;

public class EvaluationBooleanOperationTest
extends GenericEvaluationBooleanOperationTest<EObject, Package, Type, Classifier, Class, DataType, PrimitiveType, Enumeration, Operation, Parameter, Property,
Property, Property, EnumerationLiteral, State, CallOperationAction, SendSignalAction, Constraint>
{
	@Override
	public UMLTestReflection.Static getStaticReflection() {
		return UMLTestReflection.Static.INSTANCE;
	}
}
