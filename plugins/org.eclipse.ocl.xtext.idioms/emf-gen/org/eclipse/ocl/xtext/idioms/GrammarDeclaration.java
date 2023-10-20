/**
 * Copyright (c) 2018, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.idioms;

import org.eclipse.xtext.Grammar;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grammar Import</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A GrammarImport supports import of a grammar so that its rules can be referenced by locators
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.GrammarDeclaration#getAs <em>As</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.GrammarDeclaration#getGrammar <em>Grammar</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getGrammarDeclaration()
 * @model
 * @generated
 */
public interface GrammarDeclaration extends IdiomsElement
{
	/**
	 * Returns the value of the '<em><b>As</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>As</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As</em>' attribute.
	 * @see #setAs(String)
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getGrammarDeclaration_As()
	 * @model
	 * @generated
	 */
	String getAs();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.idioms.GrammarDeclaration#getAs <em>As</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As</em>' attribute.
	 * @see #getAs()
	 * @generated
	 */
	void setAs(String value);

	/**
	 * Returns the value of the '<em><b>Grammar</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Grammar</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Grammar</em>' reference.
	 * @see #setGrammar(Grammar)
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getGrammarDeclaration_Grammar()
	 * @model required="true"
	 * @generated
	 */
	Grammar getGrammar();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.idioms.GrammarDeclaration#getGrammar <em>Grammar</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grammar</em>' reference.
	 * @see #getGrammar()
	 * @generated
	 */
	void setGrammar(Grammar value);

} // GrammarImport
