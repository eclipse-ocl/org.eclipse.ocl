/**
 * Copyright (c) 2020, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.idioms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.xtext.base.serializer.SerializationBuilder;
import org.eclipse.ocl.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.idioms.WrapBeginSomeSegment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wrap Begin Some Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class WrapBeginSomeSegmentImpl
		extends SegmentImpl
		implements WrapBeginSomeSegment {

	/**
	 * The number of structural features of the '<em>Wrap Begin Some Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRAP_BEGIN_SOME_SEGMENT_FEATURE_COUNT = SegmentImpl.SEGMENT_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WrapBeginSomeSegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.WRAP_BEGIN_SOME_SEGMENT;
	}

	@Override
	public String toString() {
		return SerializationBuilder.WRAP_BEGIN_SOME;
	}
} //WrapBeginSomeSegmentImpl
