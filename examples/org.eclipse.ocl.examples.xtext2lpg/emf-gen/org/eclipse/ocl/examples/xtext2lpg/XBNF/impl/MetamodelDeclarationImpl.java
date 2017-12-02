/**
 */
package org.eclipse.ocl.examples.xtext2lpg.XBNF.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.MetamodelDeclaration;
import org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Metamodel Declaration</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.MetamodelDeclarationImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.impl.MetamodelDeclarationImpl#getReferredPackage <em>Referred Package</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MetamodelDeclarationImpl extends MinimalEObjectImpl.Container implements MetamodelDeclaration {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReferredPackage() <em>Referred Package</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReferredPackage()
	 * @generated
	 * @ordered
	 */
	protected EPackage referredPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MetamodelDeclarationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XBNFPackage.Literals.METAMODEL_DECLARATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XBNFPackage.METAMODEL_DECLARATION__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage getReferredPackage() {
		if (referredPackage != null && referredPackage.eIsProxy()) {
			InternalEObject oldReferredPackage = (InternalEObject)referredPackage;
			referredPackage = (EPackage)eResolveProxy(oldReferredPackage);
			if (referredPackage != oldReferredPackage) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, XBNFPackage.METAMODEL_DECLARATION__REFERRED_PACKAGE, oldReferredPackage, referredPackage));
			}
		}
		return referredPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EPackage basicGetReferredPackage() {
		return referredPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferredPackage(EPackage newReferredPackage) {
		EPackage oldReferredPackage = referredPackage;
		referredPackage = newReferredPackage;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XBNFPackage.METAMODEL_DECLARATION__REFERRED_PACKAGE, oldReferredPackage, referredPackage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XBNFPackage.METAMODEL_DECLARATION__NAME:
				return getName();
			case XBNFPackage.METAMODEL_DECLARATION__REFERRED_PACKAGE:
				if (resolve) return getReferredPackage();
				return basicGetReferredPackage();
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
			case XBNFPackage.METAMODEL_DECLARATION__NAME:
				setName((String)newValue);
				return;
			case XBNFPackage.METAMODEL_DECLARATION__REFERRED_PACKAGE:
				setReferredPackage((EPackage)newValue);
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
			case XBNFPackage.METAMODEL_DECLARATION__NAME:
				setName(NAME_EDEFAULT);
				return;
			case XBNFPackage.METAMODEL_DECLARATION__REFERRED_PACKAGE:
				setReferredPackage((EPackage)null);
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
			case XBNFPackage.METAMODEL_DECLARATION__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case XBNFPackage.METAMODEL_DECLARATION__REFERRED_PACKAGE:
				return referredPackage != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //MetamodelDeclarationImpl
