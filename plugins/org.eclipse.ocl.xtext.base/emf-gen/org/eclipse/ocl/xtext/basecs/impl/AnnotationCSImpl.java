/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.basecs.AnnotationCS;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Annotation CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.AnnotationCSImpl#getOwnedContents <em>Owned Contents</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.impl.AnnotationCSImpl#getOwnedReferences <em>Owned References</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AnnotationCSImpl extends AnnotationElementCSImpl implements AnnotationCS {
	/**
	 * The number of structural features of the '<em>Annotation CS</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANNOTATION_CS_FEATURE_COUNT = AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 2;

	/**
	 * The cached value of the '{@link #getOwnedContents() <em>Owned Contents</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedContents()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelElementCS> ownedContents;

	/**
	 * The cached value of the '{@link #getOwnedReferences() <em>Owned References</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOwnedReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<ModelElementRefCS> ownedReferences;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AnnotationCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return BaseCSPackage.Literals.ANNOTATION_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ModelElementCS> getOwnedContents()
	{
		if (ownedContents == null)
		{
			ownedContents = new EObjectContainmentEList<ModelElementCS>(ModelElementCS.class, this, AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 0);
		}
		return ownedContents;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ModelElementRefCS> getOwnedReferences()
	{
		if (ownedReferences == null)
		{
			ownedReferences = new EObjectContainmentEList<ModelElementRefCS>(ModelElementRefCS.class, this, AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 1);
		}
		return ownedReferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID)
		{
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 0:
				return ((InternalEList<?>)getOwnedContents()).basicRemove(otherEnd, msgs);
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 1:
				return ((InternalEList<?>)getOwnedReferences()).basicRemove(otherEnd, msgs);
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
		switch (featureID)
		{
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 0:
				return getOwnedContents();
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 1:
				return getOwnedReferences();
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
		switch (featureID)
		{
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 0:
				getOwnedContents().clear();
				getOwnedContents().addAll((Collection<? extends ModelElementCS>)newValue);
				return;
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 1:
				getOwnedReferences().clear();
				getOwnedReferences().addAll((Collection<? extends ModelElementRefCS>)newValue);
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
		switch (featureID)
		{
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 0:
				getOwnedContents().clear();
				return;
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 1:
				getOwnedReferences().clear();
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
		switch (featureID)
		{
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 0:
				return ownedContents != null && !ownedContents.isEmpty();
			case AnnotationElementCSImpl.ANNOTATION_ELEMENT_CS_FEATURE_COUNT + 1:
				return ownedReferences != null && !ownedReferences.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull BaseCSVisitor<R> visitor) {
		return visitor.visitAnnotationCS(this);
	}
} //AnnotationCSImpl
