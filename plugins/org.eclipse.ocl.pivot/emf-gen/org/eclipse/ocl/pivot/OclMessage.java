/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Message</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * OclMessage
 * This sub clause contains the definition of the standard type OclMessage.
 * As defined in this sub clause, each ocl message type is actually a template type with one parameter.
 * ‘T’ denotes the parameter.
 * A concrete ocl message type is created by substituting an operation or signal for the T.
 *
 * The predefined type OclMessage is an instance of MessageType.
 * Every OclMessage is fully determined by either the operation, or signal given as parameter.
 * Note that there is conceptually an undefined (infinite) number of these types,
 * as each is determined by a different operation or signal.
 * These types are unnamed. Every type has as attributes the name of the operation or signal,
 * and either all formal parameters of the operation, or all attributes of the signal.
 * OclMessage is itself an instance of the metatype MessageType.
 *
 * OclMessage has a number of predefined operations, as shown in the OCL Standard Library.
 *
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getOclMessage()
 * @generated
 */
public interface OclMessage extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True if type of template parameter is an operation call, and the called operation has returned a value.
	 * This implies the fact that the message has been sent. False in all other cases.
	 *
	 * <!-- end-model-doc -->
	 * @generated
	 */
	Boolean hasReturned();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns oclText[true] if the OclMessage represents the sending of a UML Operation call.
	 *
	 * <!-- end-model-doc -->
	 * @generated
	 */
	Boolean isOperationCall();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns oclText[true] if the OclMessage represents the sending of a UML Signal.
	 *
	 * <!-- end-model-doc -->
	 * @generated
	 */
	Boolean isSignalSent();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns the result of the called operation, if type of template parameter is an operation call,
	 * and the called operation has returned a value. Otherwise the oclText[invalid] value is returned.
	 *
	 * <!-- end-model-doc -->
	 * @generated
	 */
	Object result();

} // OclMessage
