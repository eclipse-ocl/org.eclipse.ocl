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
package persistence.expressions.impl;

import dataaccess.expressions.impl.ExpressionImpl;

import org.eclipse.emf.ecore.EClass;

import persistence.expressions.Commit;
import persistence.expressions.ExpressionsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Commit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class CommitImpl extends ExpressionImpl implements Commit {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CommitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackage.Literals.COMMIT;
	}

} //CommitImpl
