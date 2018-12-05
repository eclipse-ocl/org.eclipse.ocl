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
 * A representation of the model object '<em><b>Extent Modifying Association End Signature Implementation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Abstract base class for all association extent-manipulating signature implementations. Groups all constraints that apply to those.
 * <!-- end-model-doc -->
 *
 *
 * @see data.classes.ClassesPackage#getExtentModifyingAssociationEndSignatureImplementation()
 * @model abstract="true"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL MustNotModifyExtentIfEqualityRelevantForValueClass='end.association.ends->forAll(ae:AssociationEnd |\r\n    ae.contributesToEquality implies not ae.type.clazz.valueType)' MustNotImplementSideEffectFreeOperation='not self.implements_.sideEffectFree'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='MustNotModifyExtentIfEqualityRelevantForValueClass MustNotImplementSideEffectFreeOperation'"
 * @generated
 */
public interface ExtentModifyingAssociationEndSignatureImplementation extends AssociationEndSignatureImplementation {
} // ExtentModifyingAssociationEndSignatureImplementation
