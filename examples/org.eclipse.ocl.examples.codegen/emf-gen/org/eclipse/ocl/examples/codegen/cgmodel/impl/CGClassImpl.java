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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl#getContainingPackage <em>Containing Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl#getOperations <em>Operations</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl#getProperties <em>Properties</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl#getInvariants <em>Invariants</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl#getSuperTypes <em>Super Types</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl#isInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl#getTemplateParameters <em>Template Parameters</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGClassImpl extends CGNamedElementImpl implements CGClass {
	/**
	 * The cached value of the '{@link #getOperations() <em>Operations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperations()
	 * @generated
	 * @ordered
	 */
	protected EList<CGOperation> operations;

	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<CGProperty> properties;

	/**
	 * The cached value of the '{@link #getInvariants() <em>Invariants</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInvariants()
	 * @generated
	 * @ordered
	 */
	protected EList<CGConstraint> invariants;

	/**
	 * The cached value of the '{@link #getSuperTypes() <em>Super Types</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSuperTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<CGClass> superTypes;

	/**
	 * The default value of the '{@link #isInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterface()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERFACE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInterface() <em>Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterface()
	 * @generated
	 * @ordered
	 */
	protected boolean interface_ = INTERFACE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTemplateParameters() <em>Template Parameters</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTemplateParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<CGClass> templateParameters;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGPackage getContainingPackage() {
		if (eContainerFeatureID() != CGModelPackage.CG_CLASS__CONTAINING_PACKAGE) return null;
		return (CGPackage)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainingPackage(CGPackage newContainingPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainingPackage, CGModelPackage.CG_CLASS__CONTAINING_PACKAGE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainingPackage(CGPackage newContainingPackage) {
		if (newContainingPackage != eInternalContainer() || (eContainerFeatureID() != CGModelPackage.CG_CLASS__CONTAINING_PACKAGE && newContainingPackage != null)) {
			if (EcoreUtil.isAncestor(this, newContainingPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainingPackage != null)
				msgs = ((InternalEObject)newContainingPackage).eInverseAdd(this, CGModelPackage.CG_PACKAGE__CLASSES, CGPackage.class, msgs);
			msgs = basicSetContainingPackage(newContainingPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_CLASS__CONTAINING_PACKAGE, newContainingPackage, newContainingPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<CGOperation> getOperations() {
		if (operations == null) {
			operations = new EObjectContainmentWithInverseEList<CGOperation>(CGOperation.class, this, CGModelPackage.CG_CLASS__OPERATIONS, CGModelPackage.CG_OPERATION__CONTAINING_CLASS);
		}
		return operations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("null")
	public @NonNull List<CGProperty> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentWithInverseEList<CGProperty>(CGProperty.class, this, CGModelPackage.CG_CLASS__PROPERTIES, CGModelPackage.CG_PROPERTY__CONTAINING_CLASS);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGConstraint> getInvariants() {
		if (invariants == null) {
			invariants = new EObjectContainmentEList<CGConstraint>(CGConstraint.class, this, CGModelPackage.CG_CLASS__INVARIANTS);
		}
		return invariants;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<CGClass> getSuperTypes() {
		if (superTypes == null) {
			superTypes = new EObjectEList<CGClass>(CGClass.class, this, CGModelPackage.CG_CLASS__SUPER_TYPES);
		}
		return superTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isInterface() {
		return interface_;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setInterface(boolean newInterface) {
		boolean oldInterface = interface_;
		interface_ = newInterface;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGModelPackage.CG_CLASS__INTERFACE, oldInterface, interface_));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT FIXME workaround BUG 89325
	 */
	@SuppressWarnings("serial")
	@Override
	public List<CGClass> getTemplateParameters() {
		if (templateParameters == null) {
			templateParameters = new EObjectEList<CGClass>(CGClass.class, this, CGModelPackage.CG_CLASS__TEMPLATE_PARAMETERS)
			{
				@Override
				protected boolean isUnique() {
					return false;
				}
			};
		}
		return templateParameters;
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGModelPackage.CG_CLASS__CONTAINING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainingPackage((CGPackage)otherEnd, msgs);
			case CGModelPackage.CG_CLASS__OPERATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOperations()).basicAdd(otherEnd, msgs);
			case CGModelPackage.CG_CLASS__PROPERTIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProperties()).basicAdd(otherEnd, msgs);
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
			case CGModelPackage.CG_CLASS__CONTAINING_PACKAGE:
				return basicSetContainingPackage(null, msgs);
			case CGModelPackage.CG_CLASS__OPERATIONS:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case CGModelPackage.CG_CLASS__PROPERTIES:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case CGModelPackage.CG_CLASS__INVARIANTS:
				return ((InternalEList<?>)getInvariants()).basicRemove(otherEnd, msgs);
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
			case CGModelPackage.CG_CLASS__CONTAINING_PACKAGE:
				return eInternalContainer().eInverseRemove(this, CGModelPackage.CG_PACKAGE__CLASSES, CGPackage.class, msgs);
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
			case CGModelPackage.CG_CLASS__CONTAINING_PACKAGE:
				return getContainingPackage();
			case CGModelPackage.CG_CLASS__OPERATIONS:
				return getOperations();
			case CGModelPackage.CG_CLASS__PROPERTIES:
				return getProperties();
			case CGModelPackage.CG_CLASS__INVARIANTS:
				return getInvariants();
			case CGModelPackage.CG_CLASS__SUPER_TYPES:
				return getSuperTypes();
			case CGModelPackage.CG_CLASS__INTERFACE:
				return isInterface();
			case CGModelPackage.CG_CLASS__TEMPLATE_PARAMETERS:
				return getTemplateParameters();
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
			case CGModelPackage.CG_CLASS__CONTAINING_PACKAGE:
				setContainingPackage((CGPackage)newValue);
				return;
			case CGModelPackage.CG_CLASS__OPERATIONS:
				getOperations().clear();
				getOperations().addAll((Collection<? extends CGOperation>)newValue);
				return;
			case CGModelPackage.CG_CLASS__PROPERTIES:
				getProperties().clear();
				getProperties().addAll((Collection<? extends CGProperty>)newValue);
				return;
			case CGModelPackage.CG_CLASS__INVARIANTS:
				getInvariants().clear();
				getInvariants().addAll((Collection<? extends CGConstraint>)newValue);
				return;
			case CGModelPackage.CG_CLASS__SUPER_TYPES:
				getSuperTypes().clear();
				getSuperTypes().addAll((Collection<? extends CGClass>)newValue);
				return;
			case CGModelPackage.CG_CLASS__INTERFACE:
				setInterface((Boolean)newValue);
				return;
			case CGModelPackage.CG_CLASS__TEMPLATE_PARAMETERS:
				getTemplateParameters().clear();
				getTemplateParameters().addAll((Collection<? extends CGClass>)newValue);
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
			case CGModelPackage.CG_CLASS__CONTAINING_PACKAGE:
				setContainingPackage((CGPackage)null);
				return;
			case CGModelPackage.CG_CLASS__OPERATIONS:
				getOperations().clear();
				return;
			case CGModelPackage.CG_CLASS__PROPERTIES:
				getProperties().clear();
				return;
			case CGModelPackage.CG_CLASS__INVARIANTS:
				getInvariants().clear();
				return;
			case CGModelPackage.CG_CLASS__SUPER_TYPES:
				getSuperTypes().clear();
				return;
			case CGModelPackage.CG_CLASS__INTERFACE:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case CGModelPackage.CG_CLASS__TEMPLATE_PARAMETERS:
				getTemplateParameters().clear();
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
			case CGModelPackage.CG_CLASS__CONTAINING_PACKAGE:
				return getContainingPackage() != null;
			case CGModelPackage.CG_CLASS__OPERATIONS:
				return operations != null && !operations.isEmpty();
			case CGModelPackage.CG_CLASS__PROPERTIES:
				return properties != null && !properties.isEmpty();
			case CGModelPackage.CG_CLASS__INVARIANTS:
				return invariants != null && !invariants.isEmpty();
			case CGModelPackage.CG_CLASS__SUPER_TYPES:
				return superTypes != null && !superTypes.isEmpty();
			case CGModelPackage.CG_CLASS__INTERFACE:
				return interface_ != INTERFACE_EDEFAULT;
			case CGModelPackage.CG_CLASS__TEMPLATE_PARAMETERS:
				return templateParameters != null && !templateParameters.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGClass(this);
	}

} //CGClassImpl
