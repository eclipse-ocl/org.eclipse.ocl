/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl#getClasses <em>Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.impl.CGClassImpl#getContainingClass <em>Containing Class</em>}</li>
 * </ul>
 *
 * @generated
 */
public class CGClassImpl extends CGNamedElementImpl implements CGClass {
	/**
	 * The number of structural features of the '<em>CG Class</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_CLASS_FEATURE_COUNT = CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 9;

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
	 * The cached value of the '{@link #getClasses() <em>Classes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClasses()
	 * @generated
	 * @ordered
	 */
	protected EList<CGClass> classes;

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
		if (eContainerFeatureID() != (CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0)) return null;
		return (CGPackage)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainingPackage(CGPackage newContainingPackage, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainingPackage, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainingPackage(CGPackage newContainingPackage) {
		if (newContainingPackage != eInternalContainer() || (eContainerFeatureID() != (CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0) && newContainingPackage != null)) {
			if (EcoreUtil.isAncestor(this, newContainingPackage))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainingPackage != null)
				msgs = ((InternalEObject)newContainingPackage).eInverseAdd(this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0, CGPackage.class, msgs);
			msgs = basicSetContainingPackage(newContainingPackage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0, newContainingPackage, newContainingPackage));
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
			operations = new EObjectContainmentWithInverseEList<CGOperation>(CGOperation.class, this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 1, CGCallableImpl.CG_CALLABLE_FEATURE_COUNT + 2);
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
			properties = new EObjectContainmentWithInverseEList<CGProperty>(CGProperty.class, this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 2, CGValuedElementImpl.CG_VALUED_ELEMENT_FEATURE_COUNT + 0);
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
			invariants = new EObjectContainmentEList<CGConstraint>(CGConstraint.class, this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 3);
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
			superTypes = new EObjectEList<CGClass>(CGClass.class, this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 4);
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
			eNotify(new ENotificationImpl(this, Notification.SET, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 5, oldInterface, interface_));
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
			templateParameters = new EObjectEList<CGClass>(CGClass.class, this, CGModelPackage.Literals.CG_CLASS__TEMPLATE_PARAMETERS.getFeatureID())
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
	public List<CGClass> getClasses() {
		if (classes == null) {
			classes = new EObjectContainmentWithInverseEList<CGClass>(CGClass.class, this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 7, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8);
		}
		return classes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CGClass getContainingClass() {
		if (eContainerFeatureID() != (CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8)) return null;
		return (CGClass)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContainingClass(CGClass newContainingClass, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newContainingClass, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setContainingClass(CGClass newContainingClass) {
		if (newContainingClass != eInternalContainer() || (eContainerFeatureID() != (CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8) && newContainingClass != null)) {
			if (EcoreUtil.isAncestor(this, newContainingClass))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newContainingClass != null)
				msgs = ((InternalEObject)newContainingClass).eInverseAdd(this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 7, CGClass.class, msgs);
			msgs = basicSetContainingClass(newContainingClass, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8, newContainingClass, newContainingClass));
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
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainingPackage((CGPackage)otherEnd, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 1:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOperations()).basicAdd(otherEnd, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 2:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProperties()).basicAdd(otherEnd, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 7:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClasses()).basicAdd(otherEnd, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetContainingClass((CGClass)otherEnd, msgs);
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
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0:
				return basicSetContainingPackage(null, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 1:
				return ((InternalEList<?>)getOperations()).basicRemove(otherEnd, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 2:
				return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 3:
				return ((InternalEList<?>)getInvariants()).basicRemove(otherEnd, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 7:
				return ((InternalEList<?>)getClasses()).basicRemove(otherEnd, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8:
				return basicSetContainingClass(null, msgs);
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
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0:
				return eInternalContainer().eInverseRemove(this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0, CGPackage.class, msgs);
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8:
				return eInternalContainer().eInverseRemove(this, CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 7, CGClass.class, msgs);
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
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0:
				return getContainingPackage();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 1:
				return getOperations();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 2:
				return getProperties();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 3:
				return getInvariants();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 4:
				return getSuperTypes();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 5:
				return isInterface();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 6:
				return getTemplateParameters();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 7:
				return getClasses();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8:
				return getContainingClass();
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
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0:
				setContainingPackage((CGPackage)newValue);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 1:
				getOperations().clear();
				getOperations().addAll((Collection<? extends CGOperation>)newValue);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 2:
				getProperties().clear();
				getProperties().addAll((Collection<? extends CGProperty>)newValue);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 3:
				getInvariants().clear();
				getInvariants().addAll((Collection<? extends CGConstraint>)newValue);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 4:
				getSuperTypes().clear();
				getSuperTypes().addAll((Collection<? extends CGClass>)newValue);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 5:
				setInterface((Boolean)newValue);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 6:
				getTemplateParameters().clear();
				getTemplateParameters().addAll((Collection<? extends CGClass>)newValue);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 7:
				getClasses().clear();
				getClasses().addAll((Collection<? extends CGClass>)newValue);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8:
				setContainingClass((CGClass)newValue);
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
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0:
				setContainingPackage((CGPackage)null);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 1:
				getOperations().clear();
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 2:
				getProperties().clear();
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 3:
				getInvariants().clear();
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 4:
				getSuperTypes().clear();
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 5:
				setInterface(INTERFACE_EDEFAULT);
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 6:
				getTemplateParameters().clear();
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 7:
				getClasses().clear();
				return;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8:
				setContainingClass((CGClass)null);
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
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 0:
				return getContainingPackage() != null;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 1:
				return operations != null && !operations.isEmpty();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 2:
				return properties != null && !properties.isEmpty();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 3:
				return invariants != null && !invariants.isEmpty();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 4:
				return superTypes != null && !superTypes.isEmpty();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 5:
				return interface_ != INTERFACE_EDEFAULT;
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 6:
				return templateParameters != null && !templateParameters.isEmpty();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 7:
				return classes != null && !classes.isEmpty();
			case CGNamedElementImpl.CG_NAMED_ELEMENT_FEATURE_COUNT + 8:
				return getContainingClass() != null;
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
