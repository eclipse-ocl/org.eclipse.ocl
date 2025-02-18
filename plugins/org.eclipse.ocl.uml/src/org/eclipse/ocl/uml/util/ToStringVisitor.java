/*******************************************************************************
 * Copyright (c) 2010, 2025 SAP AG and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Axel Uhl - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.uml.util;

import org.eclipse.ocl.Environment;
import org.eclipse.ocl.utilities.TypedElement;
import org.eclipse.uml2.uml.CallOperationAction;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.EnumerationLiteral;
import org.eclipse.uml2.uml.Operation;
import org.eclipse.uml2.uml.Parameter;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.SendSignalAction;
import org.eclipse.uml2.uml.State;

/**
 * @since 5.23
 */
public class ToStringVisitor extends org.eclipse.ocl.util.ToStringVisitor<
		Classifier, Operation, Property,
		EnumerationLiteral, Parameter, State,
		CallOperationAction, SendSignalAction, Constraint> {
    public ToStringVisitor(
			Environment<?, Classifier, Operation, Property, EnumerationLiteral, Parameter, State, CallOperationAction, SendSignalAction, Constraint, ?, ?> env) {
		super(env);
	}

	/**
	 * Obtains an instance of the <tt>toString()</tt> visitor for the specified
	 * expression or other typed element.
	 *
	 * @param element an OCL expression or other typed element such as a variable
	 *
	 * @return the corresponding instance
	 */
	public static ToStringVisitor getInstance(TypedElement<Classifier> element) {
		Environment<?, Classifier, Operation, Property, EnumerationLiteral, Parameter, State, CallOperationAction, SendSignalAction, Constraint, ?, ?> env =
			Environment.Registry.INSTANCE.getEnvironmentFor(element);
		return new ToStringVisitor(env);
	}
}
