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

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClassifier;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Typed Rule</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getSubrules <em>Subrules</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getGrammar <em>Grammar</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getTypedRule()
 * @model abstract="true"
 * @generated
 */
public interface TypedRule extends AbstractRule {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(EClassifier)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getTypedRule_Type()
	 * @model required="true"
	 * @generated
	 */
	EClassifier getType();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(EClassifier value);

	/**
	 * Returns the value of the '<em><b>Subrules</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule#getTypedRule <em>Typed Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subrules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subrules</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getTypedRule_Subrules()
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.UntypedRule#getTypedRule
	 * @model opposite="typedRule" containment="true"
	 * @generated
	 */
	EList<UntypedRule> getSubrules();

	/**
	 * Returns the value of the '<em><b>Grammar</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getRules <em>Rules</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grammar</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grammar</em>' container reference.
	 * @see #setGrammar(Grammar)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getTypedRule_Grammar()
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getRules
	 * @model opposite="rules" required="true" transient="false"
	 * @generated
	 */
	Grammar getGrammar();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getGrammar <em>Grammar</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grammar</em>' container reference.
	 * @see #getGrammar()
	 * @generated
	 */
	void setGrammar(Grammar value);

} // TypedRule
