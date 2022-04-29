/**
 * <copyright>
 *
 * Copyright (c) 2015, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.examples.codegen.cgmodel.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstrainedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.util.CGModelVisitor;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>CG Constrained Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class CGConstrainedPropertyImpl extends CGBodiedPropertyImpl implements CGConstrainedProperty {
	/**
	 * The number of structural features of the '<em>CG Constrained Property</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CG_CONSTRAINED_PROPERTY_FEATURE_COUNT = CGBodiedPropertyImpl.CG_BODIED_PROPERTY_FEATURE_COUNT + 0;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CGConstrainedPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CGModelPackage.Literals.CG_CONSTRAINED_PROPERTY;
	}

	/**
	 * {@inheritDoc}
	 * @generated
	 */
	@Override
	public <R> R accept(@NonNull CGModelVisitor<R> visitor) {
		return visitor.visitCGConstrainedProperty(this);
	}

	@Override
	public void setAst(Element newAst) {
		if ((newAst != null) && ((NamedElement)newAst).getName().equals("t3m")) {
			getClass();		// XXX
		}
		super.setAst(newAst);
	}

} //CGConstrainedPropertyImpl
