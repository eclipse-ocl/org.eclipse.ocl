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

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Statement With Nested Blocks</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link behavioral.actions.StatementWithNestedBlocks#getNestedBlocks <em>Nested Blocks</em>}</li>
 * </ul>
 * </p>
 *
 * @see behavioral.actions.ActionsPackage#getStatementWithNestedBlocks()
 * @model
 * @generated
 */
public interface StatementWithNestedBlocks extends Statement {
	/**
	 * Returns the value of the '<em><b>Nested Blocks</b></em>' containment reference list.
	 * The list contents are of type {@link behavioral.actions.Block}.
	 * It is bidirectional and its opposite is '{@link behavioral.actions.Block#getOwningStatement <em>Owning Statement</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nested Blocks</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nested Blocks</em>' containment reference list.
	 * @see behavioral.actions.ActionsPackage#getStatementWithNestedBlocks_NestedBlocks()
	 * @see behavioral.actions.Block#getOwningStatement
	 * @model opposite="owningStatement" containment="true" resolveProxies="true" required="true" upper="2"
	 * @generated
	 */
	EList<Block> getNestedBlocks();

} // StatementWithNestedBlocks
