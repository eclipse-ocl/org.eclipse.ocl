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
package modelmanagement.deploymentunits;

import modelmanagement.NamedElement;
import modelmanagement.PackageOwner;

import modelmanagement.processcomponents.ProcessComponent;
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Deployment Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link modelmanagement.deploymentunits.DeploymentUnit#getPcsInsideCompany <em>Pcs Inside Company</em>}</li>
 * </ul>
 * </p>
 *
 * @see modelmanagement.deploymentunits.DeploymentunitsPackage#getDeploymentUnit()
 * @model
 * @generated
 */
public interface DeploymentUnit extends PackageOwner, NamedElement {
	/**
	 * Returns the value of the '<em><b>Pcs Inside Company</b></em>' containment reference list.
	 * The list contents are of type {@link modelmanagement.processcomponents.ProcessComponent}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pcs Inside Company</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pcs Inside Company</em>' containment reference list.
	 * @see modelmanagement.deploymentunits.DeploymentunitsPackage#getDeploymentUnit_PcsInsideCompany()
	 * @model containment="true" resolveProxies="true"
	 * @generated
	 */
	EList<ProcessComponent> getPcsInsideCompany();

} // DeploymentUnit
