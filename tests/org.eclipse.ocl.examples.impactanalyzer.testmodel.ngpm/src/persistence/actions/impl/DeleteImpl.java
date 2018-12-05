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
package persistence.actions.impl;

import org.eclipse.emf.ecore.EClass;

import persistence.actions.ActionsPackage;
import persistence.actions.Delete;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Delete</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class DeleteImpl extends StatementWithEntityArgumentImpl implements Delete {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeleteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ActionsPackage.Literals.DELETE;
	}

} //DeleteImpl
