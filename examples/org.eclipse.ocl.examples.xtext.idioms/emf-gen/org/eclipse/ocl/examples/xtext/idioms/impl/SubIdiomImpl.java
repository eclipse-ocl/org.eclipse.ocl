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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.examples.xtext.idioms.Idiom;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.Locator;
import org.eclipse.ocl.examples.xtext.idioms.Segment;
import org.eclipse.ocl.examples.xtext.idioms.SubIdiom;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sub Idiom</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.SubIdiomImpl#isAll <em>All</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.SubIdiomImpl#getOwnedLocator <em>Owned Locator</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.SubIdiomImpl#getOwnedSegments <em>Owned Segments</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.idioms.impl.SubIdiomImpl#getOwningIdiom <em>Owning Idiom</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SubIdiomImpl
		extends IdiomsElementImpl
		implements SubIdiom {

	/**
	 * The default value of the '{@link #isAll() <em>All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAll()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAll() <em>All</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAll()
	 * @generated
	 * @ordered
	 */
	protected boolean all = ALL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOwnedLocator() <em>Owned Locator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedLocator()
	 * @generated
	 * @ordered
	 */
	protected Locator ownedLocator;

	/**
	 * The cached value of the '{@link #getOwnedSegments() <em>Owned Segments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedSegments()
	 * @generated
	 * @ordered
	 */
	protected EList<Segment> ownedSegments;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubIdiomImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.SUB_IDIOM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isAll() {
		return all;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAll(boolean newAll) {
		boolean oldAll = all;
		all = newAll;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.SUB_IDIOM__ALL, oldAll, all));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Locator getOwnedLocator() {
		return ownedLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwnedLocator(Locator newOwnedLocator,
			NotificationChain msgs) {
		Locator oldOwnedLocator = ownedLocator;
		ownedLocator = newOwnedLocator;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
				Notification.SET, IdiomsPackage.SUB_IDIOM__OWNED_LOCATOR,
				oldOwnedLocator, newOwnedLocator);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwnedLocator(Locator newOwnedLocator) {
		if (newOwnedLocator != ownedLocator) {
			NotificationChain msgs = null;
			if (ownedLocator != null)
				msgs = ((InternalEObject) ownedLocator)
					.eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
							- IdiomsPackage.SUB_IDIOM__OWNED_LOCATOR,
						null, msgs);
			if (newOwnedLocator != null)
				msgs = ((InternalEObject) newOwnedLocator)
					.eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
							- IdiomsPackage.SUB_IDIOM__OWNED_LOCATOR,
						null, msgs);
			msgs = basicSetOwnedLocator(newOwnedLocator, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.SUB_IDIOM__OWNED_LOCATOR, newOwnedLocator,
				newOwnedLocator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Segment> getOwnedSegments() {
		if (ownedSegments == null) {
			ownedSegments = new EObjectContainmentEList<Segment>(Segment.class,
				this, IdiomsPackage.SUB_IDIOM__OWNED_SEGMENTS);
		}
		return ownedSegments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Idiom getOwningIdiom() {
		if (eContainerFeatureID() != IdiomsPackage.SUB_IDIOM__OWNING_IDIOM)
			return null;
		return (Idiom) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOwningIdiom(Idiom newOwningIdiom,
			NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newOwningIdiom,
			IdiomsPackage.SUB_IDIOM__OWNING_IDIOM, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setOwningIdiom(Idiom newOwningIdiom) {
		if (newOwningIdiom != eInternalContainer()
			|| (eContainerFeatureID() != IdiomsPackage.SUB_IDIOM__OWNING_IDIOM
				&& newOwningIdiom != null)) {
			if (EcoreUtil.isAncestor(this, newOwningIdiom))
				throw new IllegalArgumentException(
					"Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOwningIdiom != null)
				msgs = ((InternalEObject) newOwningIdiom).eInverseAdd(this,
					IdiomsPackage.IDIOM__OWNED_SUB_IDIOMS, Idiom.class, msgs);
			msgs = basicSetOwningIdiom(newOwningIdiom, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
				IdiomsPackage.SUB_IDIOM__OWNING_IDIOM, newOwningIdiom,
				newOwningIdiom));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdiomsPackage.SUB_IDIOM__OWNING_IDIOM :
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetOwningIdiom((Idiom) otherEnd, msgs);
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
			case IdiomsPackage.SUB_IDIOM__OWNED_LOCATOR :
				return basicSetOwnedLocator(null, msgs);
			case IdiomsPackage.SUB_IDIOM__OWNED_SEGMENTS :
				return ((InternalEList<?>) getOwnedSegments())
					.basicRemove(otherEnd, msgs);
			case IdiomsPackage.SUB_IDIOM__OWNING_IDIOM :
				return basicSetOwningIdiom(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case IdiomsPackage.SUB_IDIOM__OWNING_IDIOM :
				return eInternalContainer().eInverseRemove(this,
					IdiomsPackage.IDIOM__OWNED_SUB_IDIOMS, Idiom.class, msgs);
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
			case IdiomsPackage.SUB_IDIOM__ALL :
				return isAll();
			case IdiomsPackage.SUB_IDIOM__OWNED_LOCATOR :
				return getOwnedLocator();
			case IdiomsPackage.SUB_IDIOM__OWNED_SEGMENTS :
				return getOwnedSegments();
			case IdiomsPackage.SUB_IDIOM__OWNING_IDIOM :
				return getOwningIdiom();
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
			case IdiomsPackage.SUB_IDIOM__ALL :
				setAll((Boolean) newValue);
				return;
			case IdiomsPackage.SUB_IDIOM__OWNED_LOCATOR :
				setOwnedLocator((Locator) newValue);
				return;
			case IdiomsPackage.SUB_IDIOM__OWNED_SEGMENTS :
				getOwnedSegments().clear();
				getOwnedSegments()
					.addAll((Collection<? extends Segment>) newValue);
				return;
			case IdiomsPackage.SUB_IDIOM__OWNING_IDIOM :
				setOwningIdiom((Idiom) newValue);
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
			case IdiomsPackage.SUB_IDIOM__ALL :
				setAll(ALL_EDEFAULT);
				return;
			case IdiomsPackage.SUB_IDIOM__OWNED_LOCATOR :
				setOwnedLocator((Locator) null);
				return;
			case IdiomsPackage.SUB_IDIOM__OWNED_SEGMENTS :
				getOwnedSegments().clear();
				return;
			case IdiomsPackage.SUB_IDIOM__OWNING_IDIOM :
				setOwningIdiom((Idiom) null);
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
			case IdiomsPackage.SUB_IDIOM__ALL :
				return all != ALL_EDEFAULT;
			case IdiomsPackage.SUB_IDIOM__OWNED_LOCATOR :
				return ownedLocator != null;
			case IdiomsPackage.SUB_IDIOM__OWNED_SEGMENTS :
				return ownedSegments != null && !ownedSegments.isEmpty();
			case IdiomsPackage.SUB_IDIOM__OWNING_IDIOM :
				return getOwningIdiom() != null;
		}
		return super.eIsSet(featureID);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("at ");
		s.append(all
			? "all "
			: "each ");
		s.append(ownedLocator != null
			? ownedLocator.toString()
			: "«null»");
		s.append(" do ");
		boolean isFirst = true;
		if (ownedSegments != null) {
			for (Segment segment : ownedSegments) {
				if (!isFirst) {
					s.append(" + ");
				}
				s.append(segment.toString());
				isFirst = false;
			}
		}
		return s.toString();
	}
} //SubIdiomImpl
