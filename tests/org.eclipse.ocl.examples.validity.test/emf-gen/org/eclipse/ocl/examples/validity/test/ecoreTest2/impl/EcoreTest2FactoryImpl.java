/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.test.ecoreTest2.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.ocl.examples.validity.test.ecoreTest2.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EcoreTest2FactoryImpl extends EFactoryImpl implements EcoreTest2Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EcoreTest2Factory init() {
		try {
			EcoreTest2Factory theEcoreTest2Factory = (EcoreTest2Factory)EPackage.Registry.INSTANCE.getEFactory(EcoreTest2Package.eNS_URI);
			if (theEcoreTest2Factory != null) {
				return theEcoreTest2Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EcoreTest2FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EcoreTest2FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case EcoreTest2Package.ECLASS5: return createEclass5();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Eclass5 createEclass5() {
		Eclass5Impl eclass5 = new Eclass5Impl();
		return eclass5;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EcoreTest2Package getEcoreTest2Package() {
		return (EcoreTest2Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EcoreTest2Package getPackage() {
		return EcoreTest2Package.eINSTANCE;
	}

} //EcoreTest2FactoryImpl
