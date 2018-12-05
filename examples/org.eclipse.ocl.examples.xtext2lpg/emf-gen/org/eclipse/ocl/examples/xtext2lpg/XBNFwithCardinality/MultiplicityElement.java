/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality;

import org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multiplicity Element</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.MultiplicityElement#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityPackage#getMultiplicityElement()
 * @model abstract="true"
 * @generated
 */
public interface MultiplicityElement extends AbstractElement {
	/**
	 * Returns the value of the '<em><b>Element</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' containment reference.
	 * @see #setElement(AbstractElement)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityPackage#getMultiplicityElement_Element()
	 * @model containment="true" required="true"
	 * @generated
	 */
	AbstractElement getElement();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.MultiplicityElement#getElement <em>Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' containment reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(AbstractElement value);

} // MultiplicityElement
