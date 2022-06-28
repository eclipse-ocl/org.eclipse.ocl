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
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.NameResolution;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Named Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNamedElementImpl#getAst <em>Ast</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGNamedElementImpl#getNameResolution <em>Name Resolution</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class CGNamedElementImpl extends CGElementImpl implements CGNamedElement {
	/**
	 * The number of structural features of the '<em>CG Named Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_NAMED_ELEMENT_FEATURE_COUNT = CGElementImpl.CG_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The default value of the '{@link #getAst() <em>Ast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAst()
	 * @generated
	 * @ordered
	 */
	protected static final Element AST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAst() <em>Ast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAst()
	 * @generated
	 * @ordered
	 */
	protected Element ast = AST_EDEFAULT;

	/**
	 * The default value of the '{@link #getNameResolution() <em>Name Resolution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameResolution()
	 * @generated
	 * @ordered
	 */
	protected static final NameResolution NAME_RESOLUTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNameResolution() <em>Name Resolution</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNameResolution()
	 * @generated
	 * @ordered
	 */
	protected NameResolution nameResolution = NAME_RESOLUTION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGNamedElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_NAMED_ELEMENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public @NonNull NameResolution getNameResolution() {
		return ClassUtil.nonNullState(nameResolution);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setNameResolution(NameResolution newNameResolution) {
		assert newNameResolution != null;
		assert this.nameResolution == null;
		NameResolution oldNameResolution = newNameResolution;
		nameResolution = newNameResolution;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 1, oldNameResolution, nameResolution));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Element getAst() {
		return ast;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAst(Element newAst) {
		Element oldAst = ast;
		ast = newAst;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, 0, oldAst, ast));
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
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isGlobal() {
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case 0:
				return getAst();
			case 1:
				return getNameResolution();
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
			case 0:
				setAst((Element)newValue);
				return;
			case 1:
				setNameResolution((NameResolution)newValue);
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
			case 0:
				setAst(AST_EDEFAULT);
				return;
			case 1:
				setNameResolution(NAME_RESOLUTION_EDEFAULT);
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
			case 0:
				return AST_EDEFAULT == null ? ast != null : !AST_EDEFAULT.equals(ast);
			case 1:
				return NAME_RESOLUTION_EDEFAULT == null ? nameResolution != null : !NAME_RESOLUTION_EDEFAULT.equals(nameResolution);
		}
		return super.eIsSet(featureID);
	}

	@Override
	public @Nullable NameResolution basicGetNameResolution() {
		return nameResolution;
	}

	@Override
	public String getName() {
		NameResolution nameResolution2 = nameResolution;
		if (nameResolution2 == null) {
			return null; //super.getName();			// XXX Obsolete the name field
		}
		String resolvedName = nameResolution2.basicGetResolvedName();
		if (resolvedName != null) {
			return resolvedName;
		}
		else {
			return nameResolution2.getNameHint();
		}
	}

	@Override
	public @NonNull String getResolvedName() {
		return getNameResolution().getResolvedName();
	}

	@Override
	public boolean isUnresolved() {
		NameResolution nameResolution2 = nameResolution;
		return (nameResolution2 == null) || nameResolution2.isUnresolved();
	}

} //CGNamedElementImpl
