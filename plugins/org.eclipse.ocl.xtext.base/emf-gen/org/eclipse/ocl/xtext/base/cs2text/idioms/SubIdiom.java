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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;

import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;

import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sub Idiom</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getLocator <em>Locator</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getSegments <em>Segments</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getSubIdiom()
 * @model
 * @generated
 */
public interface SubIdiom extends EObject
{
	/**
	 * Returns the value of the '<em><b>Locator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locator</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locator</em>' containment reference.
	 * @see #setLocator(Locator)
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getSubIdiom_Locator()
	 * @model containment="true"
	 * @generated
	 */
	Locator getLocator();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom#getLocator <em>Locator</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Locator</em>' containment reference.
	 * @see #getLocator()
	 * @generated
	 */
	void setLocator(Locator value);

	/**
	 * Returns the value of the '<em><b>Segments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.xtext.base.cs2text.idioms.Segment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Segments</em>' containment reference list.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getSubIdiom_Segments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Segment> getSegments();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true" serializationNodeDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.SerializationNode" serializationNodeRequired="true" serializationRuleDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.BasicSerializationRule" serializationRuleRequired="true"
	 * @generated
	 */
	boolean matches(SerializationNode serializationNode, BasicSerializationRule serializationRule);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model serializationNodeDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.SerializationNode" serializationNodeRequired="true" serializerDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.UserElementSerializer" serializerRequired="true" serializationBuilderDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.SerializationBuilder" serializationBuilderRequired="true"
	 * @generated
	 */
	void serialize(SerializationNode serializationNode, UserElementSerializer serializer, SerializationBuilder serializationBuilder);

} // SubIdiom
