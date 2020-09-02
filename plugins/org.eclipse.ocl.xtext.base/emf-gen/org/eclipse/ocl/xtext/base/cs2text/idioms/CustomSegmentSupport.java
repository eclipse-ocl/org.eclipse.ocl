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
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.runtime.UserElementSerializer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Custom Segment Support</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * CustomSegmentSupport defines the interface that a user-defined class must implement
 * to contribute custom strings to the overall output.
 * <!-- end-model-doc -->
 *
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getCustomSegmentSupport()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface CustomSegmentSupport extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model serializationStepDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.SerializationStep" serializationStepRequired="true" serializerDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.UserElementSerializer" serializerRequired="true" serializationBuilderDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.SerializationBuilder" serializationBuilderRequired="true"
	 * @generated
	 */
	void serialize(SerializationStep serializationStep, UserElementSerializer serializer, SerializationBuilder serializationBuilder);

} // CustomSegmentSupport
