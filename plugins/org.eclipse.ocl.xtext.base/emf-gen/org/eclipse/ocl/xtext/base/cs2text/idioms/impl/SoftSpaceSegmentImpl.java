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
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SoftSpaceSegment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.RTSerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Soft Space Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * @generated
 */
public class SoftSpaceSegmentImpl extends SegmentImpl implements SoftSpaceSegment
{
	/**
	 * The number of structural features of the '<em>Soft Space Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SOFT_SPACE_SEGMENT_FEATURE_COUNT = SegmentImpl.SEGMENT_FEATURE_COUNT + 0;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SoftSpaceSegmentImpl()
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
		return IdiomsPackage.Literals.SOFT_SPACE_SEGMENT;
	}


	@Override
	public void serialize(RTSerializationStep serializationStep, UserElementSerializer serializer, SerializationBuilder serializationBuilder) {
		serializationBuilder.append(SerializationBuilder.SOFT_SPACE);
	}

	@Override
	public String toString() {
		return SerializationBuilder.SOFT_SPACE;
	}
} //SoftSpaceSegmentImpl