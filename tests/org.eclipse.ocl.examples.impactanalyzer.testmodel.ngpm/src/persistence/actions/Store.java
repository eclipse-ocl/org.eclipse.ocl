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
package persistence.actions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Store</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Marks an entity object or multi-object as persistent. It will be stored in the repository, producing a new snapshot on calling commit. 
 * Together with the object(s) produced by the *argument* expression, all their composite children are stored by this statement, too, and so are all links attached to any of these objects as well as all value objects that are relevant for the equality of any of the objects stored.
 * 
 * TODO clarify the storing of deletions
 * <!-- end-model-doc -->
 *
 *
 * @see persistence.actions.ActionsPackage#getStore()
 * @model
 * @generated
 */
public interface Store extends StatementWithEntityArgument {
} // Store
