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
 * A representation of the model object '<em><b>Single Block Statement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Constrains the number of blocks that this statement can own to 1.
 * <!-- end-model-doc -->
 *
 *
 * @see behavioral.actions.ActionsPackage#getSingleBlockStatement()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL OwnsExactlyOneBlock='self.nestedBlocks->size() = 1'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='OwnsExactlyOneBlock'"
 * @generated
 */
public interface SingleBlockStatement extends StatementWithNestedBlocks {
} // SingleBlockStatement
