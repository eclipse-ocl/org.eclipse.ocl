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

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Disjunction</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Disjunction#getConjunctions <em>Conjunctions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getDisjunction()
 * @model
 * @generated
 */
public interface Disjunction extends AbstractElement {
	/**
	 * Returns the value of the '<em><b>Conjunctions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext2lpg.XBNF.Conjunction}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conjunctions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conjunctions</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext2lpg.XBNF.XBNFPackage#getDisjunction_Conjunctions()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Conjunction> getConjunctions();

} // Disjunction
