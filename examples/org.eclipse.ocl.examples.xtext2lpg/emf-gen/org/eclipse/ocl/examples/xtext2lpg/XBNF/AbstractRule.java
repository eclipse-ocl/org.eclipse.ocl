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
package org.eclipse.ocl.examples.xtext2lpg.XBNF;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getElement <em>Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getDebug <em>Debug</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getKind <em>Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getAbstractRule()
 * @model
 * @generated
 */
public interface AbstractRule extends EObject {
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
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getAbstractRule_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Element</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement#getParentRule <em>Parent Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Element</em>' containment reference.
	 * @see #setElement(AbstractElement)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getAbstractRule_Element()
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractElement#getParentRule
	 * @model opposite="parentRule" containment="true" required="true"
	 * @generated
	 */
	AbstractElement getElement();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getElement <em>Element</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Element</em>' containment reference.
	 * @see #getElement()
	 * @generated
	 */
	void setElement(AbstractElement value);

	/**
	 * Returns the value of the '<em><b>Debug</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Debug</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Debug</em>' attribute.
	 * @see #setDebug(String)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getAbstractRule_Debug()
	 * @model
	 * @generated
	 */
	String getDebug();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getDebug <em>Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Debug</em>' attribute.
	 * @see #getDebug()
	 * @generated
	 */
	void setDebug(String value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see #setKind(String)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getAbstractRule_Kind()
	 * @model
	 * @generated
	 */
	String getKind();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.AbstractRule#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see #getKind()
	 * @generated
	 */
	void setKind(String value);

} // AbstractRule
