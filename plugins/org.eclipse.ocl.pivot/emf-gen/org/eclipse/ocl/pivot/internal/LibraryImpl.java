/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Comment;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.InstanceSpecification;
import org.eclipse.ocl.pivot.Library;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Precedence;
import org.eclipse.ocl.pivot.ProfileApplication;
import org.eclipse.ocl.pivot.util.Visitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.pivot.internal.LibraryImpl#getOwnedPrecedences <em>Owned Precedences</em>}</li>
 * </ul>
 *
 * @generated
 */
public class LibraryImpl extends PackageImpl implements Library
{
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
		return PivotPackage.Literals.LIBRARY;
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
			ownedPrecedences = new EObjectContainmentEList<Precedence>(Precedence.class, this, PivotPackage.LIBRARY__OWNED_PRECEDENCES);
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
			case PivotPackage.LIBRARY__ANNOTATING_COMMENTS:
				return ((InternalEList<?>)getAnnotatingComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.LIBRARY__OWNED_ANNOTATIONS:
				return ((InternalEList<?>)getOwnedAnnotations()).basicRemove(otherEnd, msgs);
			case PivotPackage.LIBRARY__OWNED_COMMENTS:
				return ((InternalEList<?>)getOwnedComments()).basicRemove(otherEnd, msgs);
			case PivotPackage.LIBRARY__OWNED_EXTENSIONS:
				return ((InternalEList<?>)getOwnedExtensions()).basicRemove(otherEnd, msgs);
			case PivotPackage.LIBRARY__OWNED_CONSTRAINTS:
				return ((InternalEList<?>)getOwnedConstraints()).basicRemove(otherEnd, msgs);
			case PivotPackage.LIBRARY__OWNED_CLASSES:
				return ((InternalEList<?>)getOwnedClasses()).basicRemove(otherEnd, msgs);
			case PivotPackage.LIBRARY__OWNED_INSTANCES:
				return ((InternalEList<?>)getOwnedInstances()).basicRemove(otherEnd, msgs);
			case PivotPackage.LIBRARY__OWNED_PACKAGES:
				return ((InternalEList<?>)getOwnedPackages()).basicRemove(otherEnd, msgs);
			case PivotPackage.LIBRARY__OWNED_PROFILE_APPLICATIONS:
				return ((InternalEList<?>)getOwnedProfileApplications()).basicRemove(otherEnd, msgs);
			case PivotPackage.LIBRARY__OWNING_PACKAGE:
				return basicSetOwningPackage(null, msgs);
			case PivotPackage.LIBRARY__OWNED_PRECEDENCES:
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
			case PivotPackage.LIBRARY__ANNOTATING_COMMENTS:
				return getAnnotatingComments();
			case PivotPackage.LIBRARY__OWNED_ANNOTATIONS:
				return getOwnedAnnotations();
			case PivotPackage.LIBRARY__OWNED_COMMENTS:
				return getOwnedComments();
			case PivotPackage.LIBRARY__OWNED_EXTENSIONS:
				return getOwnedExtensions();
			case PivotPackage.LIBRARY__NAME:
				return getName();
			case PivotPackage.LIBRARY__OWNED_CONSTRAINTS:
				return getOwnedConstraints();
			case PivotPackage.LIBRARY__URI:
				return getURI();
			case PivotPackage.LIBRARY__IMPORTED_PACKAGES:
				return getImportedPackages();
			case PivotPackage.LIBRARY__NS_PREFIX:
				return getNsPrefix();
			case PivotPackage.LIBRARY__OWNED_CLASSES:
				return getOwnedClasses();
			case PivotPackage.LIBRARY__OWNED_INSTANCES:
				return getOwnedInstances();
			case PivotPackage.LIBRARY__OWNED_PACKAGES:
				return getOwnedPackages();
			case PivotPackage.LIBRARY__OWNED_PROFILE_APPLICATIONS:
				return getOwnedProfileApplications();
			case PivotPackage.LIBRARY__OWNING_PACKAGE:
				return getOwningPackage();
			case PivotPackage.LIBRARY__OWNED_PRECEDENCES:
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
			case PivotPackage.LIBRARY__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				getAnnotatingComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.LIBRARY__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				getOwnedAnnotations().addAll((Collection<? extends Element>)newValue);
				return;
			case PivotPackage.LIBRARY__OWNED_COMMENTS:
				getOwnedComments().clear();
				getOwnedComments().addAll((Collection<? extends Comment>)newValue);
				return;
			case PivotPackage.LIBRARY__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				getOwnedExtensions().addAll((Collection<? extends ElementExtension>)newValue);
				return;
			case PivotPackage.LIBRARY__NAME:
				setName((String)newValue);
				return;
			case PivotPackage.LIBRARY__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				getOwnedConstraints().addAll((Collection<? extends Constraint>)newValue);
				return;
			case PivotPackage.LIBRARY__URI:
				setURI((String)newValue);
				return;
			case PivotPackage.LIBRARY__IMPORTED_PACKAGES:
				getImportedPackages().clear();
				getImportedPackages().addAll((Collection<? extends org.eclipse.ocl.pivot.Package>)newValue);
				return;
			case PivotPackage.LIBRARY__NS_PREFIX:
				setNsPrefix((String)newValue);
				return;
			case PivotPackage.LIBRARY__OWNED_CLASSES:
				getOwnedClasses().clear();
				getOwnedClasses().addAll((Collection<? extends org.eclipse.ocl.pivot.Class>)newValue);
				return;
			case PivotPackage.LIBRARY__OWNED_INSTANCES:
				getOwnedInstances().clear();
				getOwnedInstances().addAll((Collection<? extends InstanceSpecification>)newValue);
				return;
			case PivotPackage.LIBRARY__OWNED_PACKAGES:
				getOwnedPackages().clear();
				getOwnedPackages().addAll((Collection<? extends org.eclipse.ocl.pivot.Package>)newValue);
				return;
			case PivotPackage.LIBRARY__OWNED_PROFILE_APPLICATIONS:
				getOwnedProfileApplications().clear();
				getOwnedProfileApplications().addAll((Collection<? extends ProfileApplication>)newValue);
				return;
			case PivotPackage.LIBRARY__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)newValue);
				return;
			case PivotPackage.LIBRARY__OWNED_PRECEDENCES:
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
			case PivotPackage.LIBRARY__ANNOTATING_COMMENTS:
				getAnnotatingComments().clear();
				return;
			case PivotPackage.LIBRARY__OWNED_ANNOTATIONS:
				getOwnedAnnotations().clear();
				return;
			case PivotPackage.LIBRARY__OWNED_COMMENTS:
				getOwnedComments().clear();
				return;
			case PivotPackage.LIBRARY__OWNED_EXTENSIONS:
				getOwnedExtensions().clear();
				return;
			case PivotPackage.LIBRARY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PivotPackage.LIBRARY__OWNED_CONSTRAINTS:
				getOwnedConstraints().clear();
				return;
			case PivotPackage.LIBRARY__URI:
				setURI(URI_EDEFAULT);
				return;
			case PivotPackage.LIBRARY__IMPORTED_PACKAGES:
				getImportedPackages().clear();
				return;
			case PivotPackage.LIBRARY__NS_PREFIX:
				setNsPrefix(NS_PREFIX_EDEFAULT);
				return;
			case PivotPackage.LIBRARY__OWNED_CLASSES:
				getOwnedClasses().clear();
				return;
			case PivotPackage.LIBRARY__OWNED_INSTANCES:
				getOwnedInstances().clear();
				return;
			case PivotPackage.LIBRARY__OWNED_PACKAGES:
				getOwnedPackages().clear();
				return;
			case PivotPackage.LIBRARY__OWNED_PROFILE_APPLICATIONS:
				getOwnedProfileApplications().clear();
				return;
			case PivotPackage.LIBRARY__OWNING_PACKAGE:
				setOwningPackage((org.eclipse.ocl.pivot.Package)null);
				return;
			case PivotPackage.LIBRARY__OWNED_PRECEDENCES:
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
			case PivotPackage.LIBRARY__ANNOTATING_COMMENTS:
				return annotatingComments != null && !annotatingComments.isEmpty();
			case PivotPackage.LIBRARY__OWNED_ANNOTATIONS:
				return ownedAnnotations != null && !ownedAnnotations.isEmpty();
			case PivotPackage.LIBRARY__OWNED_COMMENTS:
				return ownedComments != null && !ownedComments.isEmpty();
			case PivotPackage.LIBRARY__OWNED_EXTENSIONS:
				return ownedExtensions != null && !ownedExtensions.isEmpty();
			case PivotPackage.LIBRARY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PivotPackage.LIBRARY__OWNED_CONSTRAINTS:
				return ownedConstraints != null && !ownedConstraints.isEmpty();
			case PivotPackage.LIBRARY__URI:
				return URI_EDEFAULT == null ? uri != null : !URI_EDEFAULT.equals(uri);
			case PivotPackage.LIBRARY__IMPORTED_PACKAGES:
				return importedPackages != null && !importedPackages.isEmpty();
			case PivotPackage.LIBRARY__NS_PREFIX:
				return NS_PREFIX_EDEFAULT == null ? nsPrefix != null : !NS_PREFIX_EDEFAULT.equals(nsPrefix);
			case PivotPackage.LIBRARY__OWNED_CLASSES:
				return ownedClasses != null && !ownedClasses.isEmpty();
			case PivotPackage.LIBRARY__OWNED_INSTANCES:
				return ownedInstances != null && !ownedInstances.isEmpty();
			case PivotPackage.LIBRARY__OWNED_PACKAGES:
				return ownedPackages != null && !ownedPackages.isEmpty();
			case PivotPackage.LIBRARY__OWNED_PROFILE_APPLICATIONS:
				return ownedProfileApplications != null && !ownedProfileApplications.isEmpty();
			case PivotPackage.LIBRARY__OWNING_PACKAGE:
				return getOwningPackage() != null;
			case PivotPackage.LIBRARY__OWNED_PRECEDENCES:
				return ownedPrecedences != null && !ownedPrecedences.isEmpty();
		}
		return eDynamicIsSet(featureID);
	}

	@Override
	public <R> R accept(@NonNull Visitor<R> visitor) {
		return visitor.visitLibrary(this);
	}
} //LibraryImpl
