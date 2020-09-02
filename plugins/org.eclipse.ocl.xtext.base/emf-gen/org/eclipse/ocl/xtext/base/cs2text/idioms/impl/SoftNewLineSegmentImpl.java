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
package org.eclipse.ocl.xtext.base.cs2text.idioms.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SoftNewLineSegment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.UserElementSerializer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Soft New Line Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SoftNewLineSegmentImpl extends SegmentImpl implements SoftNewLineSegment
{
	/**
	 * The number of structural features of the '<em>Soft New Line Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SOFT_NEW_LINE_SEGMENT_FEATURE_COUNT = SegmentImpl.SEGMENT_FEATURE_COUNT + 0;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SoftNewLineSegmentImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return IdiomsPackage.Literals.SOFT_NEW_LINE_SEGMENT;
	}

	@Override
	public void serialize(SerializationStep serializationStep, UserElementSerializer serializer, SerializationBuilder serializationBuilder) {
		serializationBuilder.append(SerializationBuilder.SOFT_NEW_LINE);
	}

	@Override
	public String toString() {
		return SerializationBuilder.SOFT_NEW_LINE;
	}
} //SoftNewLineSegmentImpl
