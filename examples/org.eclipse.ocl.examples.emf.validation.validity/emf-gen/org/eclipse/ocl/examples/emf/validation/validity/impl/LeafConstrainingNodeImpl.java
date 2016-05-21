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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.emf.validation.validity.LeafConstrainingNode;
import org.eclipse.ocl.examples.emf.validation.validity.ValidityPackage;
import org.eclipse.ocl.examples.emf.validation.validity.locator.ConstraintLocator;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Leaf ConstrainingNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.LeafConstrainingNodeImpl#getConstraintLocator <em>Constraint Locator</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.LeafConstrainingNodeImpl#getConstraintResource <em>Constraint Resource</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.emf.validation.validity.impl.LeafConstrainingNodeImpl#getConstraintString <em>Constraint String</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LeafConstrainingNodeImpl extends ConstrainingNodeImpl implements LeafConstrainingNode {
	/**
	 * The default value of the '{@link #getConstraintLocator() <em>Constraint Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintLocator()
	 * @generated
	 * @ordered
	 */
	protected static final ConstraintLocator CONSTRAINT_LOCATOR_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getConstraintLocator() <em>Constraint Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintLocator()
	 * @generated
	 * @ordered
	 */
	protected ConstraintLocator constraintLocator = CONSTRAINT_LOCATOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getConstraintResource() <em>Constraint Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintResource()
	 * @generated
	 * @ordered
	 */
	protected static final Resource CONSTRAINT_RESOURCE_EDEFAULT = null;
	/**
	 * The default value of the '{@link #getConstraintString() <em>Constraint String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConstraintString()
	 * @generated
	 * @ordered
	 */
	protected static final String CONSTRAINT_STRING_EDEFAULT = null;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LeafConstrainingNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ValidityPackage.Literals.LEAF_CONSTRAINING_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConstraintLocator getConstraintLocator() {
		return constraintLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConstraintLocator(ConstraintLocator newConstraintLocator) {
		constraintLocator = newConstraintLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR:
				return getConstraintLocator();
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_RESOURCE:
				return getConstraintResource();
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_STRING:
				return getConstraintString();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR:
				setConstraintLocator((ConstraintLocator)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR:
				setConstraintLocator(CONSTRAINT_LOCATOR_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_LOCATOR:
				return CONSTRAINT_LOCATOR_EDEFAULT == null ? constraintLocator != null : !CONSTRAINT_LOCATOR_EDEFAULT.equals(constraintLocator);
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_RESOURCE:
				return CONSTRAINT_RESOURCE_EDEFAULT == null ? getConstraintResource() != null : !CONSTRAINT_RESOURCE_EDEFAULT.equals(getConstraintResource());
			case ValidityPackage.LEAF_CONSTRAINING_NODE__CONSTRAINT_STRING:
				return CONSTRAINT_STRING_EDEFAULT == null ? getConstraintString() != null : !CONSTRAINT_STRING_EDEFAULT.equals(getConstraintString());
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getConstraintString() {
		return constraintLocator != null ? constraintLocator.getSourceExpression(this) : "<no-constraint-locator>";
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Resource getConstraintResource() {
		return constraintLocator != null ? constraintLocator.getSourceResource(this) : null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String toString() {
		return super.toString();
	}
} //LeafConstrainingNodeImpl
