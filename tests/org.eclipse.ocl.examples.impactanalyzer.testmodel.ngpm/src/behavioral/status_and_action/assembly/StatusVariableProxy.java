/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************
 */
package behavioral.status_and_action.assembly;

import behavioral.status_and_action.design.AbstractStatusVariable;
import behavioral.status_and_action.design.StatusVariable;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Status Variable Proxy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link behavioral.status_and_action.assembly.StatusVariableProxy#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @see behavioral.status_and_action.assembly.AssemblyPackage#getStatusVariableProxy()
 * @model
 * @generated
 */
public interface StatusVariableProxy extends AbstractStatusVariable, StatusVariable, ConnectableElement {
	/**
	 * Returns the value of the '<em><b>Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Variable</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable</em>' reference.
	 * @see #setVariable(StatusVariable)
	 * @see behavioral.status_and_action.assembly.AssemblyPackage#getStatusVariableProxy_Variable()
	 * @model annotation="http://schema.omg.org/spec/MOF/2.0/emof.xml Property.oppositeRoleName='proxy'"
	 * @generated
	 */
	StatusVariable getVariable();

	/**
	 * Sets the value of the '{@link behavioral.status_and_action.assembly.StatusVariableProxy#getVariable <em>Variable</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Variable</em>' reference.
	 * @see #getVariable()
	 * @generated
	 */
	void setVariable(StatusVariable value);

} // StatusVariableProxy
