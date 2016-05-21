/*******************************************************************************
 * Copyright (c) 2013, 2016 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.pivot.library.LibraryOperation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Library Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGLibraryOperationCallExpImpl#getLibraryOperation <em>Library Operation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGLibraryOperationCallExpImpl extends CGOperationCallExpImpl implements CGLibraryOperationCallExp {
	/**
	 * The default value of the '{@link #getLibraryOperation() <em>Library Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibraryOperation()
	 * @generated
	 * @ordered
	 */
	protected static final LibraryOperation LIBRARY_OPERATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLibraryOperation() <em>Library Operation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLibraryOperation()
	 * @generated
	 * @ordered
	 */
	protected LibraryOperation libraryOperation = LIBRARY_OPERATION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGLibraryOperationCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_LIBRARY_OPERATION_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LibraryOperation getLibraryOperation() {
		return libraryOperation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setLibraryOperation(LibraryOperation newLibraryOperation) {
		LibraryOperation oldLibraryOperation = libraryOperation;
		libraryOperation = newLibraryOperation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_LIBRARY_OPERATION_CALL_EXP__LIBRARY_OPERATION, oldLibraryOperation, libraryOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CGModelPackage.CG_LIBRARY_OPERATION_CALL_EXP__LIBRARY_OPERATION:
				return getLibraryOperation();
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
			case CGModelPackage.CG_LIBRARY_OPERATION_CALL_EXP__LIBRARY_OPERATION:
				setLibraryOperation((LibraryOperation)newValue);
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
			case CGModelPackage.CG_LIBRARY_OPERATION_CALL_EXP__LIBRARY_OPERATION:
				setLibraryOperation(LIBRARY_OPERATION_EDEFAULT);
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
			case CGModelPackage.CG_LIBRARY_OPERATION_CALL_EXP__LIBRARY_OPERATION:
				return LIBRARY_OPERATION_EDEFAULT == null ? libraryOperation != null : !LIBRARY_OPERATION_EDEFAULT.equals(libraryOperation);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGLibraryOperationCallExp(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isEcore() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonInvalid() {
		CGValuedElement source = getSource();
		return (referredOperation != null) && !referredOperation.isIsInvalidating() && source.isNonNull() && source.isNonInvalid();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUnboxed() {
		return false;
	}

} //CGLibraryOperationCallExpImpl
