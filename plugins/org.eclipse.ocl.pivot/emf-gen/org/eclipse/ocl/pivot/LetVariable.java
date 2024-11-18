/**
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.pivot;

import java.util.Map;
import org.eclipse.emf.common.util.DiagnosticChain;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Let Variable</b></em>'.
 * @since 1.3
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.ocl.pivot.PivotPackage#getLetVariable()
 * @generated
 */
public interface LetVariable extends Variable
{

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The nullity of a Let variable initializer expression is the nullity of the Let variable.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateCompatibleNullityForInitializer(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The type of a Let variable initializer expression conforms to the type of the Let variable.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateCompatibleTypeForInitializer(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Let variable has an initializer.
	 * <!-- end-model-doc -->
	 * @generated
	 */
	boolean validateHasInitializer(DiagnosticChain diagnostics, Map<Object, Object> context);
} // LetVariable
