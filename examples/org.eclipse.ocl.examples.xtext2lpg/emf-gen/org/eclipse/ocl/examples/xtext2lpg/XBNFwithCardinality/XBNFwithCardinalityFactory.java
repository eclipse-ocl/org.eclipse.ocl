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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.XBNFwithCardinalityPackage
 * @generated
 */
public interface XBNFwithCardinalityFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	XBNFwithCardinalityFactory eINSTANCE = org.eclipse.ocl.examples.xtext2lpg.XBNFwithCardinality.impl.XBNFwithCardinalityFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Alternatives</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Alternatives</em>'.
	 * @generated
	 */
	Alternatives createAlternatives();

	/**
	 * Returns a new object of class '<em>One Or More</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>One Or More</em>'.
	 * @generated
	 */
	OneOrMore createOneOrMore();

	/**
	 * Returns a new object of class '<em>Succession</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Succession</em>'.
	 * @generated
	 */
	Succession createSuccession();

	/**
	 * Returns a new object of class '<em>Zero Or More</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Zero Or More</em>'.
	 * @generated
	 */
	ZeroOrMore createZeroOrMore();

	/**
	 * Returns a new object of class '<em>Zero Or One</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Zero Or One</em>'.
	 * @generated
	 */
	ZeroOrOne createZeroOrOne();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	XBNFwithCardinalityPackage getXBNFwithCardinalityPackage();

} //XBNFwithCardinalityFactory
