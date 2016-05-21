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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Package</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPackageImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPackageImpl#getPackages <em>Packages</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGPackageImpl#getContainingPackage <em>Containing Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGPackageImpl extends CGNamedElementImpl implements CGPackage {
	/**
	 * The cached value of the '{@link #getClasses() <em>Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<CGClass> classes;

	/**
	 * The cached value of the '{@link #getPackages() <em>Packages</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackages()
	 * @generated
	 * @ordered
	 */
	protected EList<CGPackage> packages;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGPackageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_PACKAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGClass> getClasses() {
		if (classes == null) {
			classes = new EObjectContainmentWithInverseEList<CGClass>(CGClass.class, this, CGModelPackage.CG_PACKAGE__CLASSES, CGModelPackage.CG_CLASS__CONTAINING_PACKAGE);
		}
		return classes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGPackage> getPackages() {
		if (packages == null) {
			packages = new EObjectContainmentWithInverseEList<CGPackage>(CGPackage.class, this, CGModelPackage.CG_PACKAGE__PACKAGES, CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE);
		}
		return packages;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGPackage getContainingPackage() {
		if (eContainerFeatureID() != CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE) return null;
		return (CGPackage)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainingPackage(CGPackage newContainingPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainingPackage, CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainingPackage(CGPackage newContainingPackage) {
		if (newContainingPackage != eInternalContainer() || (eContainerFeatureID() != CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE && newContainingPackage != null)) {
			if (EcoreUtil.isAncestor(this, newContainingPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainingPackage != null)
				msgs = ((InternalEObject)newContainingPackage).eInverseAdd(this, CGModelPackage.CG_PACKAGE__PACKAGES, CGPackage.class, msgs);
			msgs = basicSetContainingPackage(newContainingPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE, newContainingPackage, newContainingPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGModelPackage.CG_PACKAGE__CLASSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClasses()).basicAdd(otherEnd, msgs);
			case CGModelPackage.CG_PACKAGE__PACKAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPackages()).basicAdd(otherEnd, msgs);
			case CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainingPackage((CGPackage)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGModelPackage.CG_PACKAGE__CLASSES:
				return ((InternalEList<?>)getClasses()).basicRemove(otherEnd, msgs);
			case CGModelPackage.CG_PACKAGE__PACKAGES:
				return ((InternalEList<?>)getPackages()).basicRemove(otherEnd, msgs);
			case CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE:
				return basicSetContainingPackage(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE:
				return eInternalContainer().eInverseRemove(this, CGModelPackage.CG_PACKAGE__PACKAGES, CGPackage.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CGModelPackage.CG_PACKAGE__CLASSES:
				return getClasses();
			case CGModelPackage.CG_PACKAGE__PACKAGES:
				return getPackages();
			case CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE:
				return getContainingPackage();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CGModelPackage.CG_PACKAGE__CLASSES:
				getClasses().clear();
				getClasses().addAll((Collection<? extends CGClass>)newValue);
				return;
			case CGModelPackage.CG_PACKAGE__PACKAGES:
				getPackages().clear();
				getPackages().addAll((Collection<? extends CGPackage>)newValue);
				return;
			case CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE:
				setContainingPackage((CGPackage)newValue);
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
			case CGModelPackage.CG_PACKAGE__CLASSES:
				getClasses().clear();
				return;
			case CGModelPackage.CG_PACKAGE__PACKAGES:
				getPackages().clear();
				return;
			case CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE:
				setContainingPackage((CGPackage)null);
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
			case CGModelPackage.CG_PACKAGE__CLASSES:
				return classes != null && !classes.isEmpty();
			case CGModelPackage.CG_PACKAGE__PACKAGES:
				return packages != null && !packages.isEmpty();
			case CGModelPackage.CG_PACKAGE__CONTAINING_PACKAGE:
				return getContainingPackage() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGPackage(this);
	}

} //CGPackageImpl
