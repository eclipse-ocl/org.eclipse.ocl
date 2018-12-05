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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grammar</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getSyntax <em>Syntax</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getGoals <em>Goals</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getRules <em>Rules</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getDebug <em>Debug</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getGrammar()
 * @model
 * @generated
 */
public interface Grammar extends EObject {
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
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getGrammar_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Syntax</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax#getGrammars <em>Grammars</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Syntax</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Syntax</em>' container reference.
	 * @see #setSyntax(Syntax)
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getGrammar_Syntax()
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.Syntax#getGrammars
	 * @model opposite="grammars" resolveProxies="false" required="true" transient="false"
	 * @generated
	 */
	Syntax getSyntax();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getSyntax <em>Syntax</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Syntax</em>' container reference.
	 * @see #getSyntax()
	 * @generated
	 */
	void setSyntax(Syntax value);

	/**
	 * Returns the value of the '<em><b>Goals</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Goals</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Goals</em>' reference list.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getGrammar_Goals()
	 * @model ordered="false"
	 * @generated
	 */
	EList<TypedRule> getGoals();

	/**
	 * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getGrammar <em>Grammar</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Rules</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rules</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getGrammar_Rules()
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.TypedRule#getGrammar
	 * @model opposite="grammar" containment="true" ordered="false"
	 * @generated
	 */
	EList<TypedRule> getRules();

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
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getGrammar_Debug()
	 * @model
	 * @generated
	 */
	String getDebug();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Grammar#getDebug <em>Debug</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Debug</em>' attribute.
	 * @see #getDebug()
	 * @generated
	 */
	void setDebug(String value);

} // Grammar
