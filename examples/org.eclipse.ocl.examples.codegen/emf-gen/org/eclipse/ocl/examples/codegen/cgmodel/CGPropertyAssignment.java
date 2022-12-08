/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.cgmodel;

import org.eclipse.ocl.pivot.Property;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>CG Assignment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment#getReferredProperty <em>Referred Property</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment#getOwnedInitValue <em>Owned Init Value</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment#getOwnedSlotValue <em>Owned Slot Value</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment#getAsProperty <em>As Property</em>}</li>
 * </ul>
 *
 * @see org.eclipse.qvtd.codegen.qvticgmodel.QVTiCGModelPackage#getCGPropertyAssignment()
 * @model
 * @generated
 */
public interface CGPropertyAssignment extends CGValuedElement {
	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment#getReferredProperty <em>Referred Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Property</em>' reference.
	 * @see #getReferredProperty()
	 * @generated
	 */
	void setReferredProperty(CGProperty value);

	/**
	 * Returns the value of the '<em><b>As Property</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>As Property</em>' attribute.
	 * @see #setAsProperty(Property)
	 * @see org.eclipse.qvtd.codegen.qvticgmodel.QVTiCGModelPackage#getCGPropertyAssignment_AsProperty()
	 * @model dataType="org.eclipse.ocl.examples.codegen.cgmodel.Property" required="true"
	 * @generated
	 */
	Property getAsProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment#getAsProperty <em>As Property</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>As Property</em>' attribute.
	 * @see #getAsProperty()
	 * @generated
	 */
	void setAsProperty(Property value);

	/**
	 * Returns the value of the '<em><b>Referred Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Property</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Property</em>' reference.
	 * @see #setReferredProperty(CGProperty)
	 * @see org.eclipse.qvtd.codegen.qvticgmodel.QVTiCGModelPackage#getCGPropertyAssignment_ReferredProperty()
	 * @model resolveProxies="false" required="true"
	 * @generated
	 */
	CGProperty getReferredProperty();

	/**
	 * Returns the value of the '<em><b>Owned Init Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Init Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Init Value</em>' containment reference.
	 * @see #setOwnedInitValue(CGValuedElement)
	 * @see org.eclipse.qvtd.codegen.qvticgmodel.QVTiCGModelPackage#getCGPropertyAssignment_OwnedInitValue()
	 * @model containment="true" required="true"
	 * @generated
	 */
	CGValuedElement getOwnedInitValue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment#getOwnedInitValue <em>Owned Init Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Init Value</em>' containment reference.
	 * @see #getOwnedInitValue()
	 * @generated
	 */
	void setOwnedInitValue(CGValuedElement value);

	/**
	 * Returns the value of the '<em><b>Owned Slot Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Slot Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Slot Value</em>' containment reference.
	 * @see #setOwnedSlotValue(CGValuedElement)
	 * @see org.eclipse.qvtd.codegen.qvticgmodel.QVTiCGModelPackage#getCGPropertyAssignment_OwnedSlotValue()
	 * @model containment="true" required="true"
	 * @generated
	 */
	CGValuedElement getOwnedSlotValue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.codegen.cgmodel.CGPropertyAssignment#getOwnedSlotValue <em>Owned Slot Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Slot Value</em>' containment reference.
	 * @see #getOwnedSlotValue()
	 * @generated
	 */
	void setOwnedSlotValue(CGValuedElement value);

} // CGAssignment
