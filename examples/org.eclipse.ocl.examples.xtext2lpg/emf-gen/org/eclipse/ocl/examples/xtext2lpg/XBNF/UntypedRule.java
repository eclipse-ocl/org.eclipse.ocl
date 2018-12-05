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
 * A representation of the model object '<em><b>Untyped Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule#getTypedRule <em>Typed Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getUntypedRule()
 * @model
 * @generated
 */
public interface UntypedRule extends AbstractRule {

	/**
	 * Returns the value of the '<em><b>Typed Rule</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getSubrules <em>Subrules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Typed Rule</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Typed Rule</em>' container reference.
	 * @see #setTypedRule(TypedRule)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getUntypedRule_TypedRule()
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getSubrules
	 * @model opposite="subrules" transient="false"
	 * @generated
	 */
	TypedRule getTypedRule();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule#getTypedRule <em>Typed Rule</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Typed Rule</em>' container reference.
	 * @see #getTypedRule()
	 * @generated
	 */
	void setTypedRule(TypedRule value);
} // UntypedRule
