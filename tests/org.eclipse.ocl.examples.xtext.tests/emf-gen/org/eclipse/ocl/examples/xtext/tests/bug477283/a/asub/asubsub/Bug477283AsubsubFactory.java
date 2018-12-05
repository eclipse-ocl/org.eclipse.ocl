/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
/**
 */
package org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub.Bug477283AsubsubPackage
 * @generated
 */
public interface Bug477283AsubsubFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Bug477283AsubsubFactory eINSTANCE = org.eclipse.ocl.examples.xtext.tests.bug477283.a.asub.asubsub.impl.Bug477283AsubsubFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>ASub Sub</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>ASub Sub</em>'.
	 * @generated
	 */
	ASubSub createASubSub();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Bug477283AsubsubPackage getBug477283AsubsubPackage();

} //Bug477283AsubsubFactory
