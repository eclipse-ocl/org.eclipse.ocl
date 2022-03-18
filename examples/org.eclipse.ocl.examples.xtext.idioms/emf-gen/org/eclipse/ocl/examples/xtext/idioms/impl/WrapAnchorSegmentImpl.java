/**
 * Copyright (c) 2020, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.WrapAnchorSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationBuilder;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Wrap Anchor Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class WrapAnchorSegmentImpl
		extends SegmentImpl
		implements WrapAnchorSegment {

	/**
	 * The number of structural features of the '<em>Wrap Anchor Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int WRAP_ANCHOR_SEGMENT_FEATURE_COUNT = SegmentImpl.SEGMENT_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WrapAnchorSegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.WRAP_ANCHOR_SEGMENT;
	}

	@Override
	public String toString() {
		return SerializationBuilder.WRAP_ANCHOR;
	}
} //WrapAnchorSegmentImpl
