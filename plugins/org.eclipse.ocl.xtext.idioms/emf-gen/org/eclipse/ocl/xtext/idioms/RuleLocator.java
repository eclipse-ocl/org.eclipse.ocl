/**
 * Copyright (c) 2018, 2024 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.idioms;

import org.eclipse.xtext.AbstractRule;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rule Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A RuleLocator references the use of a (typically DataType) rule.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.RuleLocator#getReferredGrammar <em>Referred Grammar</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.idioms.RuleLocator#getReferredRule <em>Referred Rule</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getRuleLocator()
 * @model
 * @generated
 */
public interface RuleLocator extends Locator
{
	/**
	 * Returns the value of the '<em><b>Referred Grammar</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Grammar</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Grammar</em>' reference.
	 * @see #setReferredGrammar(GrammarDeclaration)
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getRuleLocator_ReferredGrammar()
	 * @model
	 * @generated
	 */
	GrammarDeclaration getReferredGrammar();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.idioms.RuleLocator#getReferredGrammar <em>Referred Grammar</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Grammar</em>' reference.
	 * @see #getReferredGrammar()
	 * @generated
	 */
	void setReferredGrammar(GrammarDeclaration value);

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
	 * @see org.eclipse.ocl.xtext.idioms.IdiomsPackage#getRuleLocator_ReferredRule()
	 * @model required="true"
	 * @generated
	 */
	AbstractRule getReferredRule();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.idioms.RuleLocator#getReferredRule <em>Referred Rule</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Rule</em>' reference.
	 * @see #getReferredRule()
	 * @generated
	 */
	void setReferredRule(AbstractRule value);

} // RuleLocator
