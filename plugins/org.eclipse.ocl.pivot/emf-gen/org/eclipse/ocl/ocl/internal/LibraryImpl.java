/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl.internal;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.ocl.ocl.Comment;
import org.eclipse.ocl.ocl.Constraint;
import org.eclipse.ocl.ocl.Element;
import org.eclipse.ocl.ocl.ElementExtension;
import org.eclipse.ocl.ocl.InstanceSpecification;
import org.eclipse.ocl.ocl.Library;
import org.eclipse.ocl.ocl.OCLASPackage;
import org.eclipse.ocl.ocl.OclElement;
import org.eclipse.ocl.ocl.Precedence;
import org.eclipse.ocl.ocl.ProfileApplication;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.internal.LibraryImpl#getOwnedPrecedences <em>Owned Precedences</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LibraryImpl extends PackageImpl implements Library
{
	/**
	 * The number of structural features of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LIBRARY_FEATURE_COUNT = PackageImpl.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LIBRARY_OPERATION_COUNT = PackageImpl.PACKAGE_OPERATION_COUNT + 0;


	/**
	 * The cached value of the '{@link #getOwnedPrecedences() <em>Owned Precedences</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedPrecedences()
	 * @generated
	 * @ordered
	 */
	protected EList<Precedence> ownedPrecedences;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LibraryImpl()
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
		return OCLASPackage.Literals.LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Precedence> getOwnedPrecedences()
	{
		if (ownedPrecedences == null)
		{
			ownedPrecedences = new EObjectContainmentEList<Precedence>(Precedence.class, this, 16);
		}
		return ownedPrecedences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case 2:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case 3:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case 4:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case 5:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case 7:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case 11:
				return ((InternalEList<?>)getOwnedClasses()).basicRemove(otherEnd, msgs);
			case 12:
				return ((InternalEList<?>)getOwnedInstances()).basicRemove(otherEnd, msgs);
			case 13:
				return ((InternalEList<?>)getOwnedPackages()).basicRemove(otherEnd, msgs);
			case 14:
				return ((InternalEList<?>)getOwnedProfileApplications()).basicRemove(otherEnd, msgs);
			case 15:
				return basicSetOwningPackage(null, msgs);
			case 16:
				return ((InternalEList<?>)getOwnedPrecedences()).basicRemove(otherEnd, msgs);
		}
		return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
			case 0:
				if (resolve) return getOclContainer();
				return basicGetOclContainer();
			case 1:
				return getOclContents();
			case 2:
				return getAnnotatingComments();
			case 3:
				return getOwnedAnnotations();
			case 4:
				return getOwnedComments();
			case 5:
				return getOwnedExtensions();
			case 6:
				return getName();
			case 7:
				return getOwnedConstraints();
			case 8:
				return getURI();
			case 9:
				return getImportedPackages();
			case 10:
				return getNsPrefix();
			case 11:
				return getOwnedClasses();
			case 12:
				return getOwnedInstances();
			case 13:
				return getOwnedPackages();
			case 14:
				return getOwnedProfileApplications();
			case 15:
				return getOwningPackage();
			case 16:
				return getOwnedPrecedences();
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
			case 0:
				setOclContainer((OclElement)newValue);
				return;
			case 1:
				getOclContents().clear();
				getOclContents().addAll((Collection<? extends OclElement>)newValue);
				return;
			case 2:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 3:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case 4:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case 5:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case 6:
				setName((String)newValue);
				return;
			case 7:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case 8:
				setURI((String)newValue);
				return;
			case 9:
				getImportedPackages().clear();
				getImportedPackages().addAll((Collection<? extends org.eclipse.ocl.ocl.Package>)newValue);
				return;
			case 10:
				setNsPrefix((String)newValue);
				return;
			case 11:
				getOwnedClasses().clear();
				getOwnedClasses().addAll((Collection<? extends org.eclipse.ocl.ocl.Class>)newValue);
				return;
			case 12:
				getOwnedInstances().clear();
				getOwnedInstances().addAll((Collection<? extends InstanceSpecification>)newValue);
				return;
			case 13:
				getOwnedPackages().clear();
				getOwnedPackages().addAll((Collection<? extends org.eclipse.ocl.ocl.Package>)newValue);
				return;
			case 14:
				getOwnedProfileApplications().clear();
				getOwnedProfileApplications().addAll((Collection<? extends ProfileApplication>)newValue);
				return;
			case 15:
				setOwningPackage((org.eclipse.ocl.ocl.Package)newValue);
				return;
			case 16:
				getOwnedPrecedences().clear();
				getOwnedPrecedences().addAll((Collection<? extends Precedence>)newValue);
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
			case 0:
				setOclContainer((OclElement)null);
				return;
			case 1:
				getOclContents().clear();
				return;
			case 2:
				getAnnotatingComments().clear();
				return;
			case 3:
				getOwnedAnnotations().clear();
				return;
			case 4:
				getOwnedComments().clear();
				return;
			case 5:
				getOwnedExtensions().clear();
				return;
			case 6:
				setName(NAME_EDEFAULT);
				return;
			case 7:
				getOwnedConstraints().clear();
				return;
			case 8:
				setURI(URI_EDEFAULT);
				return;
			case 9:
				getImportedPackages().clear();
				return;
			case 10:
				setNsPrefix(NS_PREFIX_EDEFAULT);
				return;
			case 11:
				getOwnedClasses().clear();
				return;
			case 12:
				getOwnedInstances().clear();
				return;
			case 13:
				getOwnedPackages().clear();
				return;
			case 14:
				getOwnedProfileApplications().clear();
				return;
			case 15:
				setOwningPackage((org.eclipse.ocl.ocl.Package)null);
				return;
			case 16:
				getOwnedPrecedences().clear();
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
			case 0:
				return oclContainer != null;
			case 1:
				return oclContents != null && !oclContents.isEmpty();
			case 2:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case 3:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case 4:
				return ownedComments != null && !ownedComments.isEmpty();
			case 5:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case 6:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case 7:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case 8:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case 9:
				return importedPackages != null && !importedPackages.isEmpty();
			case 10:
				return NS_PREFIX_EDEFAULT == null ? nsPrefix != null : !NS_PREFIX_EDEFAULT.equals(nsPrefix);
			case 11:
				return ownedClasses != null && !ownedClasses.isEmpty();
			case 12:
				return ownedInstances != null && !ownedInstances.isEmpty();
			case 13:
				return ownedPackages != null && !ownedPackages.isEmpty();
			case 14:
				return ownedProfileApplications != null && !ownedProfileApplications.isEmpty();
			case 15:
				return getOwningPackage() != null;
			case 16:
				return ownedPrecedences != null && !ownedPrecedences.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}


} //LibraryImpl
