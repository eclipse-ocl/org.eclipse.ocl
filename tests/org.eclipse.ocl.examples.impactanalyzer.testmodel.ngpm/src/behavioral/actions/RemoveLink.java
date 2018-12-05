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
 * A representation of the model object '<em><b>Remove Link</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Removes the link if it exists, from the association specified.
 * 
 * If no *at* position is specified for an association with one ordered end, one occurrence of the link specified by the two *objects* will be removed at random.
 * <!-- end-model-doc -->
 *
 *
 * @see behavioral.actions.ActionsPackage#getRemoveLink()
 * @model
 * @generated
 */
public interface RemoveLink extends LinkManipulationStatement {
} // RemoveLink
