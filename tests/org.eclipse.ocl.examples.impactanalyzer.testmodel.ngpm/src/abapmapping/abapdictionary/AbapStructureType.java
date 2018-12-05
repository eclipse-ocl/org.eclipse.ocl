/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************
 */
package abapmapping.abapdictionary;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abap Structure Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link abapmapping.abapdictionary.AbapStructureType#getFields <em>Fields</em>}</li>
 * </ul>
 * </p>
 *
 * @see abapmapping.abapdictionary.AbapdictionaryPackage#getAbapStructureType()
 * @model
 * @generated
 */
public interface AbapStructureType extends AbapType {
	/**
	 * Returns the value of the '<em><b>Fields</b></em>' reference list.
	 * The list contents are of type {@link abapmapping.abapdictionary.AbapStructureField}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fields</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' reference list.
	 * @see abapmapping.abapdictionary.AbapdictionaryPackage#getAbapStructureType_Fields()
	 * @model
	 * @generated
	 */
	EList<AbapStructureField> getFields();

} // AbapStructureType
