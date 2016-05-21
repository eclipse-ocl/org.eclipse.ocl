/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.emf.validation.validity.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.*;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ValidityFactoryImpl extends EFactoryImpl implements ValidityFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ValidityFactory init() {
		try {
			ValidityFactory theValidityFactory = (ValidityFactory)EPackage.Registry.INSTANCE.getEFactory(ValidityPackage.eNS_URI);
			if (theValidityFactory != null) {
				return theValidityFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ValidityFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidityFactoryImpl() {
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
			case ValidityPackage.CONSTRAINING_NODE: return createConstrainingNode();
			case ValidityPackage.LEAF_CONSTRAINING_NODE: return createLeafConstrainingNode();
			case ValidityPackage.RESULT: return createResult();
			case ValidityPackage.RESULT_CONSTRAINING_NODE: return createResultConstrainingNode();
			case ValidityPackage.RESULT_SET: return createResultSet();
			case ValidityPackage.RESULT_VALIDATABLE_NODE: return createResultValidatableNode();
			case ValidityPackage.ROOT_NODE: return createRootNode();
			case ValidityPackage.ROOT_CONSTRAINING_NODE: return createRootConstrainingNode();
			case ValidityPackage.ROOT_VALIDATABLE_NODE: return createRootValidatableNode();
			case ValidityPackage.VALIDATABLE_NODE: return createValidatableNode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ValidityPackage.SEVERITY:
				return createSeverityFromString(eDataType, initialValue);
			case ValidityPackage.THROWABLE:
				return createThrowableFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ValidityPackage.SEVERITY:
				return convertSeverityToString(eDataType, instanceValue);
			case ValidityPackage.THROWABLE:
				return convertThrowableToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull ConstrainingNode createConstrainingNode() {
		ConstrainingNodeImpl constrainingNode = new ConstrainingNodeImpl();
		return constrainingNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull LeafConstrainingNode createLeafConstrainingNode() {
		LeafConstrainingNodeImpl leafConstrainingNode = new LeafConstrainingNodeImpl();
		return leafConstrainingNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull Result createResult() {
		ResultImpl result = new ResultImpl();
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull ResultConstrainingNode createResultConstrainingNode() {
		ResultConstrainingNodeImpl resultConstrainingNode = new ResultConstrainingNodeImpl();
		return resultConstrainingNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull ResultSet createResultSet() {
		ResultSetImpl resultSet = new ResultSetImpl();
		return resultSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull ResultValidatableNode createResultValidatableNode() {
		ResultValidatableNodeImpl resultValidatableNode = new ResultValidatableNodeImpl();
		return resultValidatableNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull RootNode createRootNode() {
		RootNodeImpl rootNode = new RootNodeImpl();
		return rootNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull RootConstrainingNode createRootConstrainingNode() {
		RootConstrainingNodeImpl rootConstrainingNode = new RootConstrainingNodeImpl();
		return rootConstrainingNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull RootValidatableNode createRootValidatableNode() {
		RootValidatableNodeImpl rootValidatableNode = new RootValidatableNodeImpl();
		return rootValidatableNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public @NonNull ValidatableNode createValidatableNode() {
		ValidatableNodeImpl validatableNode = new ValidatableNodeImpl();
		return validatableNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Severity createSeverityFromString(EDataType eDataType, String initialValue) {
		Severity result = Severity.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSeverityToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Throwable createThrowableFromString(EDataType eDataType, String initialValue) {
		return (Throwable)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertThrowableToString(EDataType eDataType, Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ValidityPackage getValidityPackage() {
		return (ValidityPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ValidityPackage getPackage() {
		return ValidityPackage.eINSTANCE;
	}

} //ValidationFactoryImpl
