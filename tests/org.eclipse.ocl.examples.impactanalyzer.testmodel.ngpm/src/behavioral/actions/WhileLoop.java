/*******************************************************************************
 * Copyright (c) 2009, 2018 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     SAP AG - initial API and implementation
 ******************************************************************************
 */
package behavioral.actions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>While Loop</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see behavioral.actions.ActionsPackage#getWhileLoop()
 * @model
 * @generated
 */
public interface WhileLoop extends ConditionalStatement, SingleBlockStatement {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false" required="true" ordered="false"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL body='self.nestedBlocks->at(1)'"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='body'"
	 * @generated
	 */
	Block getLoopBody();

} // WhileLoop
