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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.Locator;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public abstract class LocatorImpl
		extends IdiomsElementImpl
		implements Locator {

	/**
	 * The number of structural features of the '<em>Locator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOCATOR_FEATURE_COUNT = IdiomsElementImpl.IDIOMS_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LocatorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.LOCATOR;
	}

	/**
	 * Transient cached reference to org.eclipse.ocl.xtext.base.cs2text.xtext.SerializationRuleAnalysis.LocatorHelper location support.
	 */
	protected @Nullable Object helper = null;

	public @Nullable Object basicGetHelper() {
		return helper;
	}

	public void setHelper(Object newHelper) {
		this.helper = newHelper;
	}

} //LocatorImpl
