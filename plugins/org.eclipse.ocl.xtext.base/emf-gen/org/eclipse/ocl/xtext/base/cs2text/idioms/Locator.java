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

import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getLocator()
 * @model abstract="true"
 * @generated
 */
public interface Locator extends EObject
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model serializationNodeDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.SerializationNode" serializationNodeRequired="true" serializationRuleDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.BasicSerializationRule" serializationRuleRequired="true"
	 * @generated
	 */
	boolean matches(SerializationNode serializationNode, BasicSerializationRule serializationRule);

} // Locator
