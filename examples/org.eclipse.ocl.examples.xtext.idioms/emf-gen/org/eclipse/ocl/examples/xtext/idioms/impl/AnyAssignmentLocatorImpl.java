/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.examples.xtext.idioms.AnyAssignmentLocator;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Any Assignment Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class AnyAssignmentLocatorImpl
		extends LocatorImpl
		implements AnyAssignmentLocator {

	/**
	 * The number of structural features of the '<em>Any Assignment Locator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_ASSIGNMENT_LOCATOR_FEATURE_COUNT = LocatorImpl.LOCATOR_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnyAssignmentLocatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.ANY_ASSIGNMENT_LOCATOR;
	}

	@Override
	public String toString() {
		return "any-assignment";
	}
} //AnyAssignmentLocatorImpl
