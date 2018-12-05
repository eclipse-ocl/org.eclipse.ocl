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
package data.classes;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Link Traversal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Navigates an association starting on the source object on which the implementation is invoked and navigating to the *end* specified.
 * <!-- end-model-doc -->
 *
 *
 * @see data.classes.ClassesPackage#getLinkTraversal()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL OutputTypeMatchEndType='self.implements_.input->isEmpty() and\r\n  self.implements_.output.oclIsKindOf(ClassTypeDefinition) and \r\n  self.implements_.output.oclAsType(ClassTypeDefinition).clazz = self.end.type.clazz' MustBeNavigable='self.end.navigable'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='OutputTypeMatchEndType MustBeNavigable'"
 * @generated
 */
public interface LinkTraversal extends AssociationEndSignatureImplementation {
} // LinkTraversal
