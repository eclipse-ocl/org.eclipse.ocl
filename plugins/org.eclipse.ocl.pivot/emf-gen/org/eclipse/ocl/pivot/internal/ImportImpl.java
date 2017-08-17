/*******************************************************************************
 * Copyright (c) 2013, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Import;
import org.eclipse.ocl.pivot.Namespace;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Import</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ImportImpl#getImportedNamespace <em>Imported Namespace</em>}</li>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ImportImpl#getXmiidVersion <em>Xmiid Version</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ImportImpl extends NamedElementImpl implements Import
{
	/**
	 * The cached value of the '{@link #getImportedNamespace() <em>Imported Namespace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportedNamespace()
	 * @generated
	 * @ordered
	 */
	protected Namespace importedNamespace;
	/**
	 * The default value of the '{@link #getXmiidVersion() <em>Xmiid Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.4
	 * <!-- end-user-doc -->
	 * @see #getXmiidVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Number XMIID_VERSION_EDEFAULT = (Number)PivotFactory.eINSTANCE.createFromString(PivotPackage.eINSTANCE.getInteger(), "0"); //$NON-NLS-1$
	/**
	 * The cached value of the '{@link #getXmiidVersion() <em>Xmiid Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 1.4
	 * <!-- end-user-doc -->
	 * @see #getXmiidVersion()
	 * @generated
	 * @ordered
	 */
	protected Number xmiidVersion = XMIID_VERSION_EDEFAULT;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImportImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return PivotPackage.Literals.IMPORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Namespace getImportedNamespace()
	{
		if (importedNamespace != null && importedNamespace.eIsProxy())
		{
			InternalEObject oldImportedNamespace = (InternalEObject)importedNamespace;
			importedNamespace = (Namespace)eResolveProxy(oldImportedNamespace);
			if (importedNamespace != oldImportedNamespace)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PivotPackage.IMPORT__IMPORTED_NAMESPACE, oldImportedNamespace, importedNamespace));
			}
		}
		return importedNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Namespace basicGetImportedNamespace()
	{
		return importedNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setImportedNamespace(Namespace newImportedNamespace)
	{
		Namespace oldImportedNamespace = importedNamespace;
		importedNamespace = newImportedNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.IMPORT__IMPORTED_NAMESPACE, oldImportedNamespace, importedNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.4
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Number getXmiidVersion()
	{
		return xmiidVersion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.4
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setXmiidVersion(Number newXmiidVersion)
	{
		Number oldXmiidVersion = xmiidVersion;
		xmiidVersion = newXmiidVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PivotPackage.IMPORT__XMIID_VERSION, oldXmiidVersion, xmiidVersion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case PivotPackage.IMPORT__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.IMPORT__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.IMPORT__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.IMPORT__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.IMPORT__NAME:
				return getName();
			case PivotPackage.IMPORT__IMPORTED_NAMESPACE:
				if (resolve) return getImportedNamespace();
				return basicGetImportedNamespace();
			case PivotPackage.IMPORT__XMIID_VERSION:
				return getXmiidVersion();
		}
		return eDynamicGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case PivotPackage.IMPORT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.IMPORT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.IMPORT__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.IMPORT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.IMPORT__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.IMPORT__IMPORTED_NAMESPACE:
				setImportedNamespace((Namespace)newValue);
				return;
			case PivotPackage.IMPORT__XMIID_VERSION:
				setXmiidVersion((Number)newValue);
				return;
		}
		eDynamicSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID)
		{
			case PivotPackage.IMPORT__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.IMPORT__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.IMPORT__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.IMPORT__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.IMPORT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.IMPORT__IMPORTED_NAMESPACE:
				setImportedNamespace((Namespace)null);
				return;
			case PivotPackage.IMPORT__XMIID_VERSION:
				setXmiidVersion(XMIID_VERSION_EDEFAULT);
				return;
		}
		eDynamicUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case PivotPackage.IMPORT__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.IMPORT__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.IMPORT__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.IMPORT__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.IMPORT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.IMPORT__IMPORTED_NAMESPACE:
				return importedNamespace != null;
			case PivotPackage.IMPORT__XMIID_VERSION:
				return XMIID_VERSION_EDEFAULT == null ? xmiidVersion != null : !XMIID_VERSION_EDEFAULT.equals(xmiidVersion);
		}
		return eDynamicIsSet(featureID);
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

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitImport(this);
	}

} //ImportImpl
