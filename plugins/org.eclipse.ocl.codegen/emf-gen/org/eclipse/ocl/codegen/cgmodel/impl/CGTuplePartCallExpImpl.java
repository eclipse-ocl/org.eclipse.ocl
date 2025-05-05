/*******************************************************************************
 * Copyright (c) 2013, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.codegen.cgmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.codegen.cgmodel.CGTuplePartCallExp;
import org.eclipse.ocl.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.pivot.ids.PartId;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Tuple Part Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.codegen.cgmodel.impl.CGTuplePartCallExpImpl#getAstPartId <em>Ast Part Id</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGTuplePartCallExpImpl extends CGPropertyCallExpImpl implements CGTuplePartCallExp {
	/**
	 * The number of structural features of the '<em>CG Tuple Part Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_TUPLE_PART_CALL_EXP_FEATURE_COUNT = CGPropertyCallExpImpl.CG_PROPERTY_CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The default value of the '{@link #getAstPartId() <em>Ast Part Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAstPartId()
	 * @generated
	 * @ordered
	 */
	protected static final PartId AST_PART_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAstPartId() <em>Ast Part Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAstPartId()
	 * @generated
	 * @ordered
	 */
	protected PartId astPartId = AST_PART_ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGTuplePartCallExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_TUPLE_PART_CALL_EXP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PartId getAstPartId() {
		return astPartId;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAstPartId(PartId newAstPartId) {
		PartId oldAstPartId = astPartId;
		astPartId = newAstPartId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 10, oldAstPartId, astPartId));
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

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case 10:
				return getAstPartId();
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
			case 10:
				setAstPartId((PartId)newValue);
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
			case 10:
				setAstPartId(AST_PART_ID_EDEFAULT);
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
			case 10:
				return AST_PART_ID_EDEFAULT == null ? astPartId != null : !AST_PART_ID_EDEFAULT.equals(astPartId);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGTuplePartCallExp(this);
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
	public boolean isUnboxed() {
		return false;
	}

} //CGTuplePartCallExpImpl
