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
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Null</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class CGNullImpl extends CGConstantImpl implements CGNull {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGNullImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_NULL;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGNull(this);
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @NonNull Object getConstantValue() {
		return ValueUtil.NULL_VALUE;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public @Nullable Boolean isEquivalentToInternal(@NonNull CGValuedElement thatValue) {
		if (thatValue.isNull()) {
			return Boolean.TRUE;
		}
		else if (thatValue.isNonNull()) {
			return Boolean.FALSE;
		}
		else {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isInlined() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNonNull() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public boolean isNull() {
		return true;
	}

} //CGNullImpl
