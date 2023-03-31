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
 * A representation of the model object '<em><b>Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getExtenders <em>Extenders</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getInstanceClassName <em>Instance Class Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getIsActive <em>Is Active</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getIsInterface <em>Is Interface</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getOwnedBehaviors <em>Owned Behaviors</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getOwnedInvariants <em>Owned Invariants</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getOwnedOperations <em>Owned Operations</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getOwnedProperties <em>Owned Properties</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getOwningPackage <em>Owning Package</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Class#getSuperClasses <em>Super Classes</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_()
 * @generated
 */
public interface Class extends OclElement, Type, Namespace, TemplateableElement
{

	/**
	 * Returns the value of the '<em><b>Extenders</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.StereotypeExtender}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.StereotypeExtender#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extenders</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extenders</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_Extenders()
	 * @see org.eclipse.ocl.ocl.StereotypeExtender#getClass_
	 * @generated
	 */
	List<StereotypeExtender> getExtenders();

	/**
	 * Returns the value of the '<em><b>Instance Class Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Instance Class Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Instance Class Name</em>' attribute.
	 * @see #setInstanceClassName(String)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_InstanceClassName()
	 * @generated
	 */
	String getInstanceClassName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Class#getInstanceClassName <em>Instance Class Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instance Class Name</em>' attribute.
	 * @see #getInstanceClassName()
	 * @generated
	 */
	void setInstanceClassName(String value);

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, the Class does not provide a complete declaration and cannot be instantiated. An abstract Class is typically used as a target of Associations or Generalizations.
	 * 
	 * If true, the Classifier can only be instantiated by instantiating one of its specializations. An abstract Classifier is intended to be used by other Classifiers e.g., as the target of Associations or Generalizations.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_IsAbstract()
	 * @generated
	 */
	Boolean getIsAbstract();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Class#getIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #getIsAbstract()
	 * @generated
	 */
	void setIsAbstract(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Active</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Determines whether an object specified by this Class is active or not. If true, then the owning Class is referred to as an active Class. If false, then such a Class is referred to as a passive Class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Active</em>' attribute.
	 * @see #setIsActive(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_IsActive()
	 * @generated
	 */
	Boolean getIsActive();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Class#getIsActive <em>Is Active</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Active</em>' attribute.
	 * @see #getIsActive()
	 * @generated
	 */
	void setIsActive(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Interface</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Interface</em>' attribute.
	 * @see #setIsInterface(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_IsInterface()
	 * @generated
	 */
	Boolean getIsInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Class#getIsInterface <em>Is Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Interface</em>' attribute.
	 * @see #getIsInterface()
	 * @generated
	 */
	void setIsInterface(Boolean value);

	/**
	 * Returns the value of the '<em><b>Owned Behaviors</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Behavior}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Behaviors owned by a BehavioredClassifier.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Behaviors</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_OwnedBehaviors()
	 * @generated
	 */
	List<Behavior> getOwnedBehaviors();

	/**
	 * Returns the value of the '<em><b>Owned Invariants</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Constraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Invariants</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Invariants</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_OwnedInvariants()
	 * @generated
	 */
	List<Constraint> getOwnedInvariants();

	/**
	 * Returns the value of the '<em><b>Owned Operations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Operation}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Operation#getOwningClass <em>Owning Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Operations owned by the Class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Operations</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_OwnedOperations()
	 * @see org.eclipse.ocl.ocl.Operation#getOwningClass
	 * @generated
	 */
	List<Operation> getOwnedOperations();

	/**
	 * Returns the value of the '<em><b>Owned Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Property}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Property#getOwningClass <em>Owning Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Properties owned by the StructuredClassifier.
	 * 
	 * The attributes (i.e., the Properties) owned by the Class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Properties</em>' containment reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_OwnedProperties()
	 * @see org.eclipse.ocl.ocl.Property#getOwningClass
	 * @generated
	 */
	List<Property> getOwnedProperties();

	/**
	 * Returns the value of the '<em><b>Owning Package</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Package#getOwnedClasses <em>Owned Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owning Package</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owning Package</em>' container reference.
	 * @see #setOwningPackage(org.eclipse.ocl.ocl.Package)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_OwningPackage()
	 * @see org.eclipse.ocl.ocl.Package#getOwnedClasses
	 * @generated
	 */
	org.eclipse.ocl.ocl.Package getOwningPackage();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Class#getOwningPackage <em>Owning Package</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Package</em>' container reference.
	 * @see #getOwningPackage()
	 * @generated
	 */
	void setOwningPackage(org.eclipse.ocl.ocl.Package value);

	/**
	 * Returns the value of the '<em><b>Super Classes</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Class}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Super Classes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Super Classes</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getClass_SuperClasses()
	 * @generated
	 */
	List<Class> getSuperClasses();
} // Class
