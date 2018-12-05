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
package persistence.expressions;

import dataaccess.expressions.Expression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Commit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Returns the Snapshot produced
 * <!-- end-model-doc -->
 *
 *
 * @see persistence.expressions.ExpressionsPackage#getCommit()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL ReturnsSnapshot='self.getType().oclIsKindOf(data::classes::ClassTypeDefinition) and\r\n  self.getType().oclAsType(data::classes::ClassTypeDefinition).clazz.name = \'Snapshot\''"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='ReturnsSnapshot'"
 * @generated
 */
public interface Commit extends Expression {
} // Commit
