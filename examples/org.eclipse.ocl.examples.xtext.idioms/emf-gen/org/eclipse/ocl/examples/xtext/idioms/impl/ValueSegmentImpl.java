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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.examples.xtext.idioms.IdiomsPackage;
import org.eclipse.ocl.examples.xtext.idioms.ValueSegment;
import org.eclipse.ocl.examples.xtext.serializer.SerializationBuilder;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class ValueSegmentImpl
		extends SegmentImpl
		implements ValueSegment {

	/**
	 * The number of structural features of the '<em>Value Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VALUE_SEGMENT_FEATURE_COUNT = SegmentImpl.SEGMENT_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ValueSegmentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdiomsPackage.Literals.VALUE_SEGMENT;
	}

	@Override
	public String toString() {
		return SerializationBuilder.VALUE;
	}
} //ValueSegmentImpl
