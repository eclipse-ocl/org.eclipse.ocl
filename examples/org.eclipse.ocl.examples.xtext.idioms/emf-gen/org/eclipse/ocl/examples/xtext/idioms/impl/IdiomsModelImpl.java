/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms.impl;

import java.util.ArrayList;
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
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.xtext.idioms.EPackageImport;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsImport;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsModel;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.LocatorDeclaration;
import org.eclipse.ocl.examples.xtext.idioms.SegmentDeclaration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Idiom Model</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomsModelImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomsModelImpl#getOwnedIdioms <em>Owned Idioms</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomsModelImpl#getOwnedImports <em>Owned Imports</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomsModelImpl#getOwnedLocatorDeclarations <em>Owned Locator Declarations</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomsModelImpl#getOwnedSegmentDeclarations <em>Owned Segment Declarations</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.IdiomsModelImpl#getOwnedWiths <em>Owned Withs</em>}</li>
 * </ul>
 *
 * @generated
 */
public class IdiomsModelImpl
		extends IdiomsElementImpl
		implements IdiomsModel {

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
	 * The cached value of the '{@link #getOwnedIdioms() <em>Owned Idioms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedIdioms()
	 * @generated
	 * @ordered
	 */
	protected EList<Idiom> ownedIdioms;

	/**
	 * The cached value of the '{@link #getOwnedImports() <em>Owned Imports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedImports()
	 * @generated
	 * @ordered
	 */
	protected EList<EPackageImport> ownedImports;

	/**
	 * The cached value of the '{@link #getOwnedLocatorDeclarations() <em>Owned Locator Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLocatorDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<LocatorDeclaration> ownedLocatorDeclarations;

	/**
	 * The cached value of the '{@link #getOwnedSegmentDeclarations() <em>Owned Segment Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSegmentDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<SegmentDeclaration> ownedSegmentDeclarations;

	/**
	 * The cached value of the '{@link #getOwnedWiths() <em>Owned Withs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedWiths()
	 * @generated
	 * @ordered
	 */
	protected EList<IdiomsImport> ownedWiths;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdiomsModelImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.IDIOMS_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.IDIOMS_MODEL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<EPackageImport> getOwnedImports() {
		if (ownedImports == null) {
			ownedImports = new EObjectContainmentEList<EPackageImport>(
				EPackageImport.class, this,
				IdiomsPackage.IDIOMS_MODEL__OWNED_IMPORTS);
		}
		return ownedImports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<LocatorDeclaration> getOwnedLocatorDeclarations() {
		if (ownedLocatorDeclarations == null) {
			ownedLocatorDeclarations = new EObjectContainmentWithInverseEList<LocatorDeclaration>(
				LocatorDeclaration.class, this,
				IdiomsPackage.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS,
				IdiomsPackage.LOCATOR_DECLARATION__OWNING_IDIOMS_MODEL);
		}
		return ownedLocatorDeclarations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<SegmentDeclaration> getOwnedSegmentDeclarations() {
		if (ownedSegmentDeclarations == null) {
			ownedSegmentDeclarations = new EObjectContainmentWithInverseEList<SegmentDeclaration>(
				SegmentDeclaration.class, this,
				IdiomsPackage.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS,
				IdiomsPackage.SEGMENT_DECLARATION__OWNING_IDIOMS_MODEL);
		}
		return ownedSegmentDeclarations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Idiom> getOwnedIdioms() {
		if (ownedIdioms == null) {
			ownedIdioms = new EObjectContainmentEList<Idiom>(Idiom.class, this,
				IdiomsPackage.IDIOMS_MODEL__OWNED_IDIOMS);
		}
		return ownedIdioms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<IdiomsImport> getOwnedWiths() {
		if (ownedWiths == null) {
			ownedWiths = new EObjectContainmentEList<IdiomsImport>(
				IdiomsImport.class, this,
				IdiomsPackage.IDIOMS_MODEL__OWNED_WITHS);
		}
		return ownedWiths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdiomsPackage.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS :
				return ((InternalEList<InternalEObject>) (InternalEList<?>) getOwnedLocatorDeclarations())
					.basicAdd(otherEnd, msgs);
			case IdiomsPackage.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS :
				return ((InternalEList<InternalEObject>) (InternalEList<?>) getOwnedSegmentDeclarations())
					.basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IDIOMS :
				return ((InternalEList<?>) getOwnedIdioms())
					.basicRemove(otherEnd, msgs);
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IMPORTS :
				return ((InternalEList<?>) getOwnedImports())
					.basicRemove(otherEnd, msgs);
			case IdiomsPackage.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS :
				return ((InternalEList<?>) getOwnedLocatorDeclarations())
					.basicRemove(otherEnd, msgs);
			case IdiomsPackage.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS :
				return ((InternalEList<?>) getOwnedSegmentDeclarations())
					.basicRemove(otherEnd, msgs);
			case IdiomsPackage.IDIOMS_MODEL__OWNED_WITHS :
				return ((InternalEList<?>) getOwnedWiths())
					.basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IdiomsPackage.IDIOMS_MODEL__NAME :
				return getName();
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IDIOMS :
				return getOwnedIdioms();
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IMPORTS :
				return getOwnedImports();
			case IdiomsPackage.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS :
				return getOwnedLocatorDeclarations();
			case IdiomsPackage.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS :
				return getOwnedSegmentDeclarations();
			case IdiomsPackage.IDIOMS_MODEL__OWNED_WITHS :
				return getOwnedWiths();
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
			case IdiomsPackage.IDIOMS_MODEL__NAME :
				setName((String) newValue);
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IDIOMS :
				getOwnedIdioms().clear();
				getOwnedIdioms().addAll((Collection<? extends Idiom>) newValue);
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IMPORTS :
				getOwnedImports().clear();
				getOwnedImports()
					.addAll((Collection<? extends EPackageImport>) newValue);
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS :
				getOwnedLocatorDeclarations().clear();
				getOwnedLocatorDeclarations().addAll(
					(Collection<? extends LocatorDeclaration>) newValue);
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS :
				getOwnedSegmentDeclarations().clear();
				getOwnedSegmentDeclarations().addAll(
					(Collection<? extends SegmentDeclaration>) newValue);
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_WITHS :
				getOwnedWiths().clear();
				getOwnedWiths()
					.addAll((Collection<? extends IdiomsImport>) newValue);
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
			case IdiomsPackage.IDIOMS_MODEL__NAME :
				setName(NAME_EDEFAULT);
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IDIOMS :
				getOwnedIdioms().clear();
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IMPORTS :
				getOwnedImports().clear();
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS :
				getOwnedLocatorDeclarations().clear();
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS :
				getOwnedSegmentDeclarations().clear();
				return;
			case IdiomsPackage.IDIOMS_MODEL__OWNED_WITHS :
				getOwnedWiths().clear();
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
			case IdiomsPackage.IDIOMS_MODEL__NAME :
				return NAME_EDEFAULT == null
					? name != null
					: !NAME_EDEFAULT.equals(name);
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IDIOMS :
				return ownedIdioms != null && !ownedIdioms.isEmpty();
			case IdiomsPackage.IDIOMS_MODEL__OWNED_IMPORTS :
				return ownedImports != null && !ownedImports.isEmpty();
			case IdiomsPackage.IDIOMS_MODEL__OWNED_LOCATOR_DECLARATIONS :
				return ownedLocatorDeclarations != null
					&& !ownedLocatorDeclarations.isEmpty();
			case IdiomsPackage.IDIOMS_MODEL__OWNED_SEGMENT_DECLARATIONS :
				return ownedSegmentDeclarations != null
					&& !ownedSegmentDeclarations.isEmpty();
			case IdiomsPackage.IDIOMS_MODEL__OWNED_WITHS :
				return ownedWiths != null && !ownedWiths.isEmpty();
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
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(')');
		return result.toString();
	}

	@Override
	public IdiomsModel getIdiomsModel(@Nullable String name) {
		if (name != null) {
			for (@NonNull
			IdiomsModel idiomsModel : getIdiomsModels()) {
				if (name.equals(idiomsModel.getName())) {
					return idiomsModel;
				}
			}
		}
		return null;
	}

	/**
	 * Lazily computed closure of the ownedWiths relationship.
	 */
	private @Nullable List<@NonNull IdiomsModel> idiomsModels = null;

	@Override
	public @NonNull Iterable<@NonNull IdiomsModel> getIdiomsModels() {
		List<@NonNull IdiomsModel> idiomsModels2 = idiomsModels;
		if (idiomsModels2 == null) {
			idiomsModels = idiomsModels2 = new ArrayList<>();
			idiomsModels2.add(this);
			gatherIdiomsModels(this, idiomsModels2);
		}
		return idiomsModels2;
	}

	private void gatherIdiomsModels(@NonNull IdiomsModel idiomsModel,
			@NonNull List<@NonNull IdiomsModel> idiomsModels) {
		for (IdiomsImport idiomsImport : idiomsModel.getOwnedWiths()) {
			IdiomsModel importedIdiomsModel = idiomsImport.getIdiomsModel();
			assert importedIdiomsModel != null;
			if (!idiomsModels.contains(importedIdiomsModel)) {
				idiomsModels.add(importedIdiomsModel);
				gatherIdiomsModels(importedIdiomsModel, idiomsModels);
			}
		}
	}

	@Override
	public LocatorDeclaration getOwnedLocator(@Nullable String name) {
		if (name != null) {
			for (LocatorDeclaration locator : getOwnedLocatorDeclarations()) {
				if (name.equals(locator.getName())) {
					return locator;
				}
			}
		}
		return null;
	}

	@Override
	public SegmentDeclaration getOwnedSegment(@Nullable String name) {
		if (name != null) {
			for (SegmentDeclaration segment : getOwnedSegmentDeclarations()) {
				if (name.equals(segment.getName())) {
					return segment;
				}
			}
		}
		return null;
	}
} //IdiomModelImpl
