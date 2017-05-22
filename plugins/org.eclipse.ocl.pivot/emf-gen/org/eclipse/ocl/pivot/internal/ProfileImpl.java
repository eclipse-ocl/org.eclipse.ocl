/*******************************************************************************
 * Copyright (c) 2012, 2016 Willink Transformations and others.
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
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.InstanceSpecification;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Profile;
import org.eclipse.ocl.pivot.ProfileApplication;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Profile</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.ProfileImpl#getProfileApplications <em>Profile Applications</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ProfileImpl extends PackageImpl implements Profile
{
	/**
	 * The cached value of the '{@link #getProfileApplications() <em>Profile Applications</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfileApplications()
	 * @generated
	 * @ordered
	 */
	protected EList<ProfileApplication> profileApplications;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProfileImpl()
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
		return PivotPackage.Literals.PROFILE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<ProfileApplication> getProfileApplications()
	{
		if (profileApplications == null)
		{
			profileApplications = new EObjectWithInverseResolvingEList<ProfileApplication>(ProfileApplication.class, this, PivotPackage.PROFILE__PROFILE_APPLICATIONS, PivotPackage.PROFILE_APPLICATION__APPLIED_PROFILE);
		}
		return profileApplications;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case PivotPackage.PROFILE__ANNOTATING_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getAnnotatingComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_COMMENTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedComments()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_EXTENSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedExtensions()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_CLASSES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedClasses()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_INSTANCES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedInstances()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_PACKAGES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedPackages()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_PROFILE_APPLICATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getOwnedProfileApplications()).basicAdd(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNING_PACKAGE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningPackage((org.eclipse.ocl.pivot.Package)otherEnd, msgs);
			case PivotPackage.PROFILE__PROFILE_APPLICATIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getProfileApplications()).basicAdd(otherEnd, msgs);
		}
		return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
			case PivotPackage.PROFILE__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_CLASSES:
				return ((InternalEList<?>)getOwnedClasses()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_INSTANCES:
				return ((InternalEList<?>)getOwnedInstances()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_PACKAGES:
				return ((InternalEList<?>)getOwnedPackages()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNED_PROFILE_APPLICATIONS:
				return ((InternalEList<?>)getOwnedProfileApplications()).basicRemove(otherEnd, msgs);
			case PivotPackage.PROFILE__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
			case PivotPackage.PROFILE__PROFILE_APPLICATIONS:
				return ((InternalEList<?>)getProfileApplications()).basicRemove(otherEnd, msgs);
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
			case PivotPackage.PROFILE__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.PROFILE__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.PROFILE__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.PROFILE__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.PROFILE__NAME:
				return getName();
			case PivotPackage.PROFILE__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.PROFILE__URI:
				return getURI();
			case PivotPackage.PROFILE__IMPORTED_PACKAGES:
				return getImportedPackages();
			case PivotPackage.PROFILE__NS_PREFIX:
				return getNsPrefix();
			case PivotPackage.PROFILE__OWNED_CLASSES:
				return getOwnedClasses();
			case PivotPackage.PROFILE__OWNED_INSTANCES:
				return getOwnedInstances();
			case PivotPackage.PROFILE__OWNED_PACKAGES:
				return getOwnedPackages();
			case PivotPackage.PROFILE__OWNED_PROFILE_APPLICATIONS:
				return getOwnedProfileApplications();
			case PivotPackage.PROFILE__OWNING_PACKAGE:
				return getOwningPackage();
			case PivotPackage.PROFILE__PROFILE_APPLICATIONS:
				return getProfileApplications();
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
			case PivotPackage.PROFILE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PROFILE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.PROFILE__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.PROFILE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.PROFILE__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.PROFILE__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.PROFILE__URI:
				setURI((String)newValue);
				return;
			case PivotPackage.PROFILE__IMPORTED_PACKAGES:
				getImportedPackages().clear();
				getImportedPackages().addAll((Collection<? extends org.eclipse.ocl.pivot.Package>)newValue);
				return;
			case PivotPackage.PROFILE__NS_PREFIX:
				setNsPrefix((String)newValue);
				return;
			case PivotPackage.PROFILE__OWNED_CLASSES:
				getOwnedClasses().clear();
				getOwnedClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.PROFILE__OWNED_INSTANCES:
				getOwnedInstances().clear();
				getOwnedInstances().addAll((Collection<? extends InstanceSpecification>)newValue);
				return;
			case PivotPackage.PROFILE__OWNED_PACKAGES:
				getOwnedPackages().clear();
				getOwnedPackages().addAll((Collection<? extends org.eclipse.ocl.pivot.Package>)newValue);
				return;
			case PivotPackage.PROFILE__OWNED_PROFILE_APPLICATIONS:
				getOwnedProfileApplications().clear();
				getOwnedProfileApplications().addAll((Collection<? extends ProfileApplication>)newValue);
				return;
			case PivotPackage.PROFILE__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case PivotPackage.PROFILE__PROFILE_APPLICATIONS:
				getProfileApplications().clear();
				getProfileApplications().addAll((Collection<? extends ProfileApplication>)newValue);
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
			case PivotPackage.PROFILE__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.PROFILE__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.PROFILE__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.PROFILE__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.PROFILE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.PROFILE__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.PROFILE__URI:
				setURI(URI_EDEFAULT);
				return;
			case PivotPackage.PROFILE__IMPORTED_PACKAGES:
				getImportedPackages().clear();
				return;
			case PivotPackage.PROFILE__NS_PREFIX:
				setNsPrefix(NS_PREFIX_EDEFAULT);
				return;
			case PivotPackage.PROFILE__OWNED_CLASSES:
				getOwnedClasses().clear();
				return;
			case PivotPackage.PROFILE__OWNED_INSTANCES:
				getOwnedInstances().clear();
				return;
			case PivotPackage.PROFILE__OWNED_PACKAGES:
				getOwnedPackages().clear();
				return;
			case PivotPackage.PROFILE__OWNED_PROFILE_APPLICATIONS:
				getOwnedProfileApplications().clear();
				return;
			case PivotPackage.PROFILE__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case PivotPackage.PROFILE__PROFILE_APPLICATIONS:
				getProfileApplications().clear();
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
			case PivotPackage.PROFILE__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.PROFILE__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.PROFILE__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.PROFILE__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.PROFILE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.PROFILE__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.PROFILE__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case PivotPackage.PROFILE__IMPORTED_PACKAGES:
				return importedPackages != null && !importedPackages.isEmpty();
			case PivotPackage.PROFILE__NS_PREFIX:
				return NS_PREFIX_EDEFAULT == null ? nsPrefix != null : !NS_PREFIX_EDEFAULT.equals(nsPrefix);
			case PivotPackage.PROFILE__OWNED_CLASSES:
				return ownedClasses != null && !ownedClasses.isEmpty();
			case PivotPackage.PROFILE__OWNED_INSTANCES:
				return ownedInstances != null && !ownedInstances.isEmpty();
			case PivotPackage.PROFILE__OWNED_PACKAGES:
				return ownedPackages != null && !ownedPackages.isEmpty();
			case PivotPackage.PROFILE__OWNED_PROFILE_APPLICATIONS:
				return ownedProfileApplications != null && !ownedProfileApplications.isEmpty();
			case PivotPackage.PROFILE__OWNING_PACKAGE:
				return getOwningPackage() != null;
			case PivotPackage.PROFILE__PROFILE_APPLICATIONS:
				return profileApplications != null && !profileApplications.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitProfile(this);
	}

} //ProfileImpl
