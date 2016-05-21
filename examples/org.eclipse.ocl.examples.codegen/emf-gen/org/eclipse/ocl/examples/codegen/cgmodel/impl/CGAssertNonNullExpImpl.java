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

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;

import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Assert Non Null Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class CGAssertNonNullExpImpl extends CGCallExpImpl implements CGAssertNonNullExp {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGAssertNonNullExpImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_ASSERT_NON_NULL_EXP;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGAssertNonNullExp(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable EClassifier getEcoreClassifier() {
		return source != null ? source.getEcoreClassifier() : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getNamedValue() {
		return source != null ? source.getNamedValue() : this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getReferredValue() {
		return source != null ? source : this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getSourceValue() {
		return source != null ? source.getSourceValue() : this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull CGValuedElement getTypedValue() {
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable String getValueName() {
		return source != null ? source.getValueName() : null;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isBoxed() {
		return (source != null) && source.isBoxed();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isEcore() {
		return (source != null) && source.isEcore();
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonNull() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNull() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isUnboxed() {
		return (source != null) && source.isUnboxed();
	}

} //CGAssertNonNullExpImpl
