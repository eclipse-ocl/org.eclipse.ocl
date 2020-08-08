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

import org.eclipse.ocl.xtext.base.cs2text.elements.BasicSerializationRule;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Idiom</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom#getOwnedSubIdioms <em>Owned Sub Idioms</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getIdiom()
 * @model
 * @generated
 */
public interface Idiom extends EObject
{
	/**
	 * Returns the value of the '<em><b>Owned Sub Idioms</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Sub Idioms</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Sub Idioms</em>' containment reference list.
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#getIdiom_OwnedSubIdioms()
	 * @model containment="true"
	 * @generated
	 */
	EList<SubIdiom> getOwnedSubIdioms();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model dataType="org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch" required="true" serializationNodeDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.SerializationNode" serializationNodeRequired="true" serializationRuleDataType="org.eclipse.ocl.xtext.base.cs2text.idioms.BasicSerializationRule" serializationRuleRequired="true"
	 * @generated
	 */
	IdiomMatch firstMatch(SerializationNode serializationNode, BasicSerializationRule serializationRule);

} // Idiom
