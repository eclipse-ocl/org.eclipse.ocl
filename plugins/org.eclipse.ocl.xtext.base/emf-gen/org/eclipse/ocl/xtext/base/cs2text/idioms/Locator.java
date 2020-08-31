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
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationRuleAnalysis;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Locator identifies where a SubIdiom applies Segment formatting.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Locator#getName <em>Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getLocator()
 * @model abstract="true"
 * @generated
 */
public interface Locator extends EObject
{
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getLocator_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Locator#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" serializationNodeDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.SerializationNode" serializationNodeRequired="true" serializationRuleDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.SerializationRuleAnalysis" serializationRuleRequired="true"
	 * @generated
	 */
	boolean matches(SerializationNode serializationNode, SerializationRuleAnalysis serializationRule);

} // Locator
