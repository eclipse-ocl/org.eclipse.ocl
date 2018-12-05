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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule Call</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall#getReferredRule <em>Referred Rule</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getRuleCall()
 * @model
 * @generated
 */
public interface RuleCall extends AbstractElement {
	/**
	 * Returns the value of the '<em><b>Referred Rule</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Rule</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Rule</em>' reference.
	 * @see #setReferredRule(AbstractRule)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getRuleCall_ReferredRule()
	 * @model required="true"
	 * @generated
	 */
	AbstractRule getReferredRule();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.RuleCall#getReferredRule <em>Referred Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Rule</em>' reference.
	 * @see #getReferredRule()
	 * @generated
	 */
	void setReferredRule(AbstractRule value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getRuleCall_Name()
	 * @model transient="true" changeable="false" volatile="true"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot derivation='if referredRule <> null then referredRule.name else \'\' endif'"
	 * @generated
	 */
	String getName();

} // RuleCall
