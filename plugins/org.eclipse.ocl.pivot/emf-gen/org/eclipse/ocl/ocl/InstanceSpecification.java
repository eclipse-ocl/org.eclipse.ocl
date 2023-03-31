/**
 * Copyright (c) 2010, 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.ocl;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instance Specification</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.InstanceSpecification#getClasses <em>Classes</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.InstanceSpecification#getOwnedSlots <em>Owned Slots</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.InstanceSpecification#getOwnedSpecification <em>Owned Specification</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.InstanceSpecification#getOwningPackage <em>Owning Package</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getInstanceSpecification()
 * @generated
 */
public interface InstanceSpecification extends NamedElement
{
	/**
	 * Returns the value of the '<em><b>Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Class}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Classifier or Classifiers of the represented instance. If multiple Classifiers are specified, the instance is classified by all of them.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classes</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getInstanceSpecification_Classes()
	 * @generated
	 */
	List<org.eclipse.ocl.ocl.Class> getClasses();

	/**
	 * Returns the value of the '<em><b>Owned Slots</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Slot}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Slot#getOwningInstance <em>Owning Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A Slot giving the value or values of a StructuralFeature of the instance. An InstanceSpecification can have one Slot per StructuralFeature of its Classifiers, including inherited features. It is not necessary to model a Slot for every StructuralFeature, in which case the InstanceSpecification is a partial description.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Slots</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getInstanceSpecification_OwnedSlots()
	 * @see org.eclipse.ocl.ocl.Slot#getOwningInstance
	 * @generated
	 */
	List<Slot> getOwnedSlots();

	/**
	 * Returns the value of the '<em><b>Owned Specification</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A specification of how to compute, derive, or construct the instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Specification</em>' containment reference.
	 * @see #setOwnedSpecification(LanguageExpression)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getInstanceSpecification_OwnedSpecification()
	 * @generated
	 */
	LanguageExpression getOwnedSpecification();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.InstanceSpecification#getOwnedSpecification <em>Owned Specification</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Specification</em>' containment reference.
	 * @see #getOwnedSpecification()
	 * @generated
	 */
	void setOwnedSpecification(LanguageExpression value);

	/**
	 * Returns the value of the '<em><b>Owning Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Package#getOwnedInstances <em>Owned Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Package</em>' container reference.
	 * @see #setOwningPackage(org.eclipse.ocl.ocl.Package)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getInstanceSpecification_OwningPackage()
	 * @see org.eclipse.ocl.ocl.Package#getOwnedInstances
	 * @generated
	 */
	org.eclipse.ocl.ocl.Package getOwningPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.InstanceSpecification#getOwningPackage <em>Owning Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Package</em>' container reference.
	 * @see #getOwningPackage()
	 * @generated
	 */
	void setOwningPackage(org.eclipse.ocl.ocl.Package value);

} // InstanceSpecification
