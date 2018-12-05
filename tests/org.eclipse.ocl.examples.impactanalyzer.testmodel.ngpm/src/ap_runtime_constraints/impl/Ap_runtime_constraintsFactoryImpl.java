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
package ap_runtime_constraints.impl;

import ap_runtime_constraints.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Ap_runtime_constraintsFactoryImpl extends EFactoryImpl implements Ap_runtime_constraintsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Ap_runtime_constraintsFactory init() {
		try {
			Ap_runtime_constraintsFactory theAp_runtime_constraintsFactory = (Ap_runtime_constraintsFactory)EPackage.Registry.INSTANCE.getEFactory("http://eclipse.org/ocl/examples/impactanalyzer/testmodel/ngpm/ap_runtime_constraints.ecore"); 
			if (theAp_runtime_constraintsFactory != null) {
				return theAp_runtime_constraintsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Ap_runtime_constraintsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ap_runtime_constraintsFactoryImpl() {
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
			case Ap_runtime_constraintsPackage.QUERY_CONSTRAINT: return createQueryConstraint();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryConstraint createQueryConstraint() {
		QueryConstraintImpl queryConstraint = new QueryConstraintImpl();
		return queryConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ap_runtime_constraintsPackage getAp_runtime_constraintsPackage() {
		return (Ap_runtime_constraintsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Ap_runtime_constraintsPackage getPackage() {
		return Ap_runtime_constraintsPackage.eINSTANCE;
	}

} //Ap_runtime_constraintsFactoryImpl
