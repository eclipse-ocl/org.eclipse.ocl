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
 * A representation of the model object '<em><b>Return</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Returns a value from a block. Must be the last statement of a block.
 * <!-- end-model-doc -->
 *
 *
 * @see behavioral.actions.ActionsPackage#getReturn()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL ReturnMustBeLastInBlock='self = self.block.statements->last()' ReturnTypeMustMatch='self.argument.getType().conformsTo(self.getOutermostBlock().getImplementedSignature().output)'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ReturnMustBeLastInBlock ReturnTypeMustMatch'"
 * @generated
 */
public interface Return extends StatementWithArgument {
} // Return
