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
package dataaccess.expressions;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object Count</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Computes the Number 1 for single objects, and the number of objects in a multi-valued object otherwise.
 * 
 * Type inference in getType() can be done once TypeDefinition is a value type that can be constructed in OCL.
 * <!-- end-model-doc -->
 *
 *
 * @see dataaccess.expressions.ExpressionsPackage#getObjectCount()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL TypeIsNumber='self.getType().oclIsKindOf(data::classes::ClassTypeDefinition) and\r\n  self.getType().oclAsType(data::classes::ClassTypeDefinition).clazz.name = \'Number\'' MultiplicityIsOne='self.getType().upperMultiplicity = 1 and\r\n  self.getType().lowerMultiplicity = 1'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='TypeIsNumber MultiplicityIsOne'"
 * @generated
 */
public interface ObjectCount extends ObjectBasedExpression {
} // ObjectCount
