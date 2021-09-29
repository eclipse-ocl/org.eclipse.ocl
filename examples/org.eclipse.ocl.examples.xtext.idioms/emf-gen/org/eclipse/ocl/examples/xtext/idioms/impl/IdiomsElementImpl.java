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

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.ocl.examples.xtext.idioms.IdiomsElement;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Idiom Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class IdiomsElementImpl
		extends EObjectImpl
		implements IdiomsElement {

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IDIOMS_ELEMENT_FEATURE_COUNT = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdiomsElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.IDIOMS_ELEMENT;
	}

} //IdiomElementImpl
