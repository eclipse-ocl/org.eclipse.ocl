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
 * A representation of the model object '<em><b>If Else</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see behavioral.actions.ActionsPackage#getIfElse()
 * @model
 * @generated
 */
public interface IfElse extends ConditionalStatement, StatementWithNestedBlocks {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false" required="true" ordered="false"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL body='self.nestedBlocks->at(1)'"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='body'"
	 * @generated
	 */
	Block getIfBlock();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation" unique="false" required="true" ordered="false"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL body='if self.nestedBlocks->size() > 1 then\n    self.nestedBlocks->at(2)\n  else\n    null\n  endif'"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='body'"
	 * @generated
	 */
	Block getElseBlock();

} // IfElse
