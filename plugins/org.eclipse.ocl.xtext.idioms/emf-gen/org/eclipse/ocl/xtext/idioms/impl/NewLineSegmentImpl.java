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
import org.eclipse.ocl.xtext.idioms.NewLineSegment;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>New Line Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class NewLineSegmentImpl
		extends SegmentImpl
		implements NewLineSegment {

	/**
	 * The number of structural features of the '<em>New Line Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NEW_LINE_SEGMENT_FEATURE_COUNT = SegmentImpl.SEGMENT_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NewLineSegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.NEW_LINE_SEGMENT;
	}

	@Override
	public String toString() {
		return SerializationBuilder.NEW_LINE;
	}
} //NewLineSegmentImpl
