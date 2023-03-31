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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.ocl.OCLASFactory
 * @generated
 */
public interface OCLASPackage extends EPackage
{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "ocl"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/2023/Library"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "ocl"; //$NON-NLS-1$

	/**
	 * The package content type ID.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eCONTENT_TYPE = "org.eclipse.ocl.oclas"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OCLASPackage eINSTANCE = org.eclipse.ocl.ocl.internal.OCLASPackageImpl.init();



	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Annotation <em>Annotation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Annotation</em>'.
	 * @see org.eclipse.ocl.ocl.Annotation
	 * @generated
	 */
	EClass getAnnotation();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Annotation#getOwnedContents <em>Owned Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Contents</em>'.
	 * @see org.eclipse.ocl.ocl.Annotation#getOwnedContents()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_OwnedContents();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Annotation#getOwnedDetails <em>Owned Details</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Details</em>'.
	 * @see org.eclipse.ocl.ocl.Annotation#getOwnedDetails()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_OwnedDetails();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Annotation#getReferences <em>References</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>References</em>'.
	 * @see org.eclipse.ocl.ocl.Annotation#getReferences()
	 * @see #getAnnotation()
	 * @generated
	 */
	EReference getAnnotation_References();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.AnyType <em>Any Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Any Type</em>'.
	 * @see org.eclipse.ocl.ocl.AnyType
	 * @generated
	 */
	EClass getAnyType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.AssociationClass <em>Association Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association Class</em>'.
	 * @see org.eclipse.ocl.ocl.AssociationClass
	 * @generated
	 */
	EClass getAssociationClass();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.AssociationClass#getUnownedAttributes <em>Unowned Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Unowned Attributes</em>'.
	 * @see org.eclipse.ocl.ocl.AssociationClass#getUnownedAttributes()
	 * @see #getAssociationClass()
	 * @generated
	 */
	EReference getAssociationClass_UnownedAttributes();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.AssociationClassCallExp <em>Association Class Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association Class Call Exp</em>'.
	 * @see org.eclipse.ocl.ocl.AssociationClassCallExp
	 * @generated
	 */
	EClass getAssociationClassCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.AssociationClassCallExp#getReferredAssociationClass <em>Referred Association Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Association Class</em>'.
	 * @see org.eclipse.ocl.ocl.AssociationClassCallExp#getReferredAssociationClass()
	 * @see #getAssociationClassCallExp()
	 * @generated
	 */
	EReference getAssociationClassCallExp_ReferredAssociationClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.Bag <em>Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bag</em>'.
	 * @see org.eclipse.ocl.pivot.values.Bag
	 * @generated
	 */
	EClass getBag();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.BagType <em>Bag Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bag Type</em>'.
	 * @see org.eclipse.ocl.ocl.BagType
	 * @generated
	 */
	EClass getBagType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Behavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behavior</em>'.
	 * @see org.eclipse.ocl.ocl.Behavior
	 * @generated
	 */
	EClass getBehavior();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Behavior#getOwningTransition <em>Owning Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Transition</em>'.
	 * @see org.eclipse.ocl.ocl.Behavior#getOwningTransition()
	 * @see #getBehavior()
	 * @generated
	 */
	EReference getBehavior_OwningTransition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.BooleanLiteralExp <em>Boolean Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.BooleanLiteralExp
	 * @generated
	 */
	EClass getBooleanLiteralExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.BooleanLiteralExp#getBooleanSymbol <em>Boolean Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Symbol</em>'.
	 * @see org.eclipse.ocl.ocl.BooleanLiteralExp#getBooleanSymbol()
	 * @see #getBooleanLiteralExp()
	 * @generated
	 */
	EAttribute getBooleanLiteralExp_BooleanSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.BooleanType <em>Boolean Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Type</em>'.
	 * @see org.eclipse.ocl.ocl.BooleanType
	 * @generated
	 */
	EClass getBooleanType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CallExp <em>Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Exp</em>'.
	 * @see org.eclipse.ocl.ocl.CallExp
	 * @generated
	 */
	EClass getCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.CallExp#getIsImplicit <em>Is Implicit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Implicit</em>'.
	 * @see org.eclipse.ocl.ocl.CallExp#getIsImplicit()
	 * @see #getCallExp()
	 * @generated
	 */
	EAttribute getCallExp_IsImplicit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.CallExp#getIsSafe <em>Is Safe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Safe</em>'.
	 * @see org.eclipse.ocl.ocl.CallExp#getIsSafe()
	 * @see #getCallExp()
	 * @generated
	 */
	EAttribute getCallExp_IsSafe();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.CallExp#getOwnedSource <em>Owned Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Source</em>'.
	 * @see org.eclipse.ocl.ocl.CallExp#getOwnedSource()
	 * @see #getCallExp()
	 * @generated
	 */
	EReference getCallExp_OwnedSource();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CallOperationAction <em>Call Operation Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Operation Action</em>'.
	 * @see org.eclipse.ocl.ocl.CallOperationAction
	 * @generated
	 */
	EClass getCallOperationAction();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.CallOperationAction#getOperation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Operation</em>'.
	 * @see org.eclipse.ocl.ocl.CallOperationAction#getOperation()
	 * @see #getCallOperationAction()
	 * @generated
	 */
	EReference getCallOperationAction_Operation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Class <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Class</em>'.
	 * @see org.eclipse.ocl.ocl.Class
	 * @generated
	 */
	EClass getClass_();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Class#getExtenders <em>Extenders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extenders</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getExtenders()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_Extenders();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Class#getInstanceClassName <em>Instance Class Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Instance Class Name</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getInstanceClassName()
	 * @see #getClass_()
	 * @generated
	 */
	EAttribute getClass_InstanceClassName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Class#getIsAbstract <em>Is Abstract</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Abstract</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getIsAbstract()
	 * @see #getClass_()
	 * @generated
	 */
	EAttribute getClass_IsAbstract();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Class#getIsActive <em>Is Active</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Active</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getIsActive()
	 * @see #getClass_()
	 * @generated
	 */
	EAttribute getClass_IsActive();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Class#getIsInterface <em>Is Interface</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Interface</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getIsInterface()
	 * @see #getClass_()
	 * @generated
	 */
	EAttribute getClass_IsInterface();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Class#getOwnedBehaviors <em>Owned Behaviors</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Behaviors</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getOwnedBehaviors()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_OwnedBehaviors();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Class#getOwnedInvariants <em>Owned Invariants</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Invariants</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getOwnedInvariants()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_OwnedInvariants();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Class#getOwnedOperations <em>Owned Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Operations</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getOwnedOperations()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_OwnedOperations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Class#getOwnedProperties <em>Owned Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Properties</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getOwnedProperties()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_OwnedProperties();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Class#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Package</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getOwningPackage()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_OwningPackage();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Class#getSuperClasses <em>Super Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Super Classes</em>'.
	 * @see org.eclipse.ocl.ocl.Class#getSuperClasses()
	 * @see #getClass_()
	 * @generated
	 */
	EReference getClass_SuperClasses();

	/**
	 * Returns the meta object for class '{@link java.util.Collection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection</em>'.
	 * @see java.util.Collection
	 * @generated
	 */
	EClass getCollection();

	/**
	 * Returns the meta object for the reference '{@link java.util.Collection#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element Type</em>'.
	 * @see java.util.Collection#getElementType()
	 * @see #getCollection()
	 * @generated
	 */
	EReference getCollection_ElementType();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Collection#getLower <em>Lower</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower</em>'.
	 * @see java.util.Collection#getLower()
	 * @see #getCollection()
	 * @generated
	 */
	EAttribute getCollection_Lower();

	/**
	 * Returns the meta object for the attribute '{@link java.util.Collection#getUpper <em>Upper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper</em>'.
	 * @see java.util.Collection#getUpper()
	 * @see #getCollection()
	 * @generated
	 */
	EAttribute getCollection_Upper();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CollectionItem <em>Collection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Item</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionItem
	 * @generated
	 */
	EClass getCollectionItem();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.CollectionItem#getOwnedItem <em>Owned Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Item</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionItem#getOwnedItem()
	 * @see #getCollectionItem()
	 * @generated
	 */
	EReference getCollectionItem_OwnedItem();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CollectionLiteralExp <em>Collection Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionLiteralExp
	 * @generated
	 */
	EClass getCollectionLiteralExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.CollectionLiteralExp#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionLiteralExp#getKind()
	 * @see #getCollectionLiteralExp()
	 * @generated
	 */
	EAttribute getCollectionLiteralExp_Kind();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.CollectionLiteralExp#getOwnedParts <em>Owned Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Parts</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionLiteralExp#getOwnedParts()
	 * @see #getCollectionLiteralExp()
	 * @generated
	 */
	EReference getCollectionLiteralExp_OwnedParts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CollectionLiteralPart <em>Collection Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Literal Part</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionLiteralPart
	 * @generated
	 */
	EClass getCollectionLiteralPart();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CollectionRange <em>Collection Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Range</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionRange
	 * @generated
	 */
	EClass getCollectionRange();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.CollectionRange#getOwnedFirst <em>Owned First</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned First</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionRange#getOwnedFirst()
	 * @see #getCollectionRange()
	 * @generated
	 */
	EReference getCollectionRange_OwnedFirst();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.CollectionRange#getOwnedLast <em>Owned Last</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Last</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionRange#getOwnedLast()
	 * @see #getCollectionRange()
	 * @generated
	 */
	EReference getCollectionRange_OwnedLast();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CollectionType <em>Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Type</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionType
	 * @generated
	 */
	EClass getCollectionType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.CollectionType#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element Type</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionType#getElementType()
	 * @see #getCollectionType()
	 * @generated
	 */
	EReference getCollectionType_ElementType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.CollectionType#getIsNullFree <em>Is Null Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Null Free</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionType#getIsNullFree()
	 * @see #getCollectionType()
	 * @generated
	 */
	EAttribute getCollectionType_IsNullFree();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.CollectionType#getLower <em>Lower</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lower</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionType#getLower()
	 * @see #getCollectionType()
	 * @generated
	 */
	EAttribute getCollectionType_Lower();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.CollectionType#getUpper <em>Upper</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Upper</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionType#getUpper()
	 * @see #getCollectionType()
	 * @generated
	 */
	EAttribute getCollectionType_Upper();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Comment</em>'.
	 * @see org.eclipse.ocl.ocl.Comment
	 * @generated
	 */
	EClass getComment();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Comment#getAnnotatedElements <em>Annotated Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Annotated Elements</em>'.
	 * @see org.eclipse.ocl.ocl.Comment#getAnnotatedElements()
	 * @see #getComment()
	 * @generated
	 */
	EReference getComment_AnnotatedElements();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Comment#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.eclipse.ocl.ocl.Comment#getBody()
	 * @see #getComment()
	 * @generated
	 */
	EAttribute getComment_Body();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Comment#getOwningElement <em>Owning Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Element</em>'.
	 * @see org.eclipse.ocl.ocl.Comment#getOwningElement()
	 * @see #getComment()
	 * @generated
	 */
	EReference getComment_OwningElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CompleteClass <em>Complete Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Complete Class</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteClass
	 * @generated
	 */
	EClass getCompleteClass();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.CompleteClass#getOwningCompletePackage <em>Owning Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Complete Package</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteClass#getOwningCompletePackage()
	 * @see #getCompleteClass()
	 * @generated
	 */
	EReference getCompleteClass_OwningCompletePackage();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.CompleteClass#getPartialClasses <em>Partial Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partial Classes</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteClass#getPartialClasses()
	 * @see #getCompleteClass()
	 * @generated
	 */
	EReference getCompleteClass_PartialClasses();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CompleteEnvironment <em>Complete Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Complete Environment</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteEnvironment
	 * @generated
	 */
	EClass getCompleteEnvironment();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedCompleteModel <em>Owned Complete Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Complete Model</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedCompleteModel()
	 * @see #getCompleteEnvironment()
	 * @generated
	 */
	EReference getCompleteEnvironment_OwnedCompleteModel();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedStandardLibrary <em>Owned Standard Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Standard Library</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteEnvironment#getOwnedStandardLibrary()
	 * @see #getCompleteEnvironment()
	 * @generated
	 */
	EReference getCompleteEnvironment_OwnedStandardLibrary();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CompleteModel <em>Complete Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Complete Model</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteModel
	 * @generated
	 */
	EClass getCompleteModel();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.CompleteModel#getOrphanCompletePackage <em>Orphan Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Orphan Complete Package</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteModel#getOrphanCompletePackage()
	 * @see #getCompleteModel()
	 * @generated
	 */
	EReference getCompleteModel_OrphanCompletePackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.CompleteModel#getOwnedCompletePackages <em>Owned Complete Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Complete Packages</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteModel#getOwnedCompletePackages()
	 * @see #getCompleteModel()
	 * @generated
	 */
	EReference getCompleteModel_OwnedCompletePackages();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.CompleteModel#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Complete Environment</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteModel#getOwningCompleteEnvironment()
	 * @see #getCompleteModel()
	 * @generated
	 */
	EReference getCompleteModel_OwningCompleteEnvironment();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.CompleteModel#getPartialModels <em>Partial Models</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partial Models</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteModel#getPartialModels()
	 * @see #getCompleteModel()
	 * @generated
	 */
	EReference getCompleteModel_PartialModels();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.CompleteModel#getPrimitiveCompletePackage <em>Primitive Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Primitive Complete Package</em>'.
	 * @see org.eclipse.ocl.ocl.CompleteModel#getPrimitiveCompletePackage()
	 * @see #getCompleteModel()
	 * @generated
	 */
	EReference getCompleteModel_PrimitiveCompletePackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.CompletePackage <em>Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Complete Package</em>'.
	 * @see org.eclipse.ocl.ocl.CompletePackage
	 * @generated
	 */
	EClass getCompletePackage();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.CompletePackage#getOwnedCompleteClasses <em>Owned Complete Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Complete Classes</em>'.
	 * @see org.eclipse.ocl.ocl.CompletePackage#getOwnedCompleteClasses()
	 * @see #getCompletePackage()
	 * @generated
	 */
	EReference getCompletePackage_OwnedCompleteClasses();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.CompletePackage#getOwnedCompletePackages <em>Owned Complete Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Complete Packages</em>'.
	 * @see org.eclipse.ocl.ocl.CompletePackage#getOwnedCompletePackages()
	 * @see #getCompletePackage()
	 * @generated
	 */
	EReference getCompletePackage_OwnedCompletePackages();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.CompletePackage#getOwningCompleteModel <em>Owning Complete Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Complete Model</em>'.
	 * @see org.eclipse.ocl.ocl.CompletePackage#getOwningCompleteModel()
	 * @see #getCompletePackage()
	 * @generated
	 */
	EReference getCompletePackage_OwningCompleteModel();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.CompletePackage#getOwningCompletePackage <em>Owning Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Complete Package</em>'.
	 * @see org.eclipse.ocl.ocl.CompletePackage#getOwningCompletePackage()
	 * @see #getCompletePackage()
	 * @generated
	 */
	EReference getCompletePackage_OwningCompletePackage();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.CompletePackage#getPartialPackages <em>Partial Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Partial Packages</em>'.
	 * @see org.eclipse.ocl.ocl.CompletePackage#getPartialPackages()
	 * @see #getCompletePackage()
	 * @generated
	 */
	EReference getCompletePackage_PartialPackages();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ConnectionPointReference <em>Connection Point Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Connection Point Reference</em>'.
	 * @see org.eclipse.ocl.ocl.ConnectionPointReference
	 * @generated
	 */
	EClass getConnectionPointReference();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.ConnectionPointReference#getEntries <em>Entries</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Entries</em>'.
	 * @see org.eclipse.ocl.ocl.ConnectionPointReference#getEntries()
	 * @see #getConnectionPointReference()
	 * @generated
	 */
	EReference getConnectionPointReference_Entries();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.ConnectionPointReference#getExits <em>Exits</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Exits</em>'.
	 * @see org.eclipse.ocl.ocl.ConnectionPointReference#getExits()
	 * @see #getConnectionPointReference()
	 * @generated
	 */
	EReference getConnectionPointReference_Exits();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.ConnectionPointReference#getOwningState <em>Owning State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning State</em>'.
	 * @see org.eclipse.ocl.ocl.ConnectionPointReference#getOwningState()
	 * @see #getConnectionPointReference()
	 * @generated
	 */
	EReference getConnectionPointReference_OwningState();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Constraint#getConstrainedElements <em>Constrained Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Constrained Elements</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint#getConstrainedElements()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_ConstrainedElements();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Constraint#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Context</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint#getContext()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_Context();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Constraint#getIsCallable <em>Is Callable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Callable</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint#getIsCallable()
	 * @see #getConstraint()
	 * @generated
	 */
	EAttribute getConstraint_IsCallable();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.Constraint#getOwnedSpecification <em>Owned Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Specification</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint#getOwnedSpecification()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_OwnedSpecification();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Constraint#getOwningPostContext <em>Owning Post Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Post Context</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint#getOwningPostContext()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_OwningPostContext();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Constraint#getOwningPreContext <em>Owning Pre Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Pre Context</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint#getOwningPreContext()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_OwningPreContext();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Constraint#getOwningState <em>Owning State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning State</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint#getOwningState()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_OwningState();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Constraint#getOwningTransition <em>Owning Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Transition</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint#getOwningTransition()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_OwningTransition();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Constraint#getRedefinedConstraints <em>Redefined Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Redefined Constraints</em>'.
	 * @see org.eclipse.ocl.ocl.Constraint#getRedefinedConstraints()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_RedefinedConstraints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.DataType <em>Data Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Type</em>'.
	 * @see org.eclipse.ocl.ocl.DataType
	 * @generated
	 */
	EClass getDataType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.DataType#getBehavioralClass <em>Behavioral Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Behavioral Class</em>'.
	 * @see org.eclipse.ocl.ocl.DataType#getBehavioralClass()
	 * @see #getDataType()
	 * @generated
	 */
	EReference getDataType_BehavioralClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.DataType#getIsSerializable <em>Is Serializable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Serializable</em>'.
	 * @see org.eclipse.ocl.ocl.DataType#getIsSerializable()
	 * @see #getDataType()
	 * @generated
	 */
	EAttribute getDataType_IsSerializable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.DataType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.ocl.ocl.DataType#getValue()
	 * @see #getDataType()
	 * @generated
	 */
	EAttribute getDataType_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Detail <em>Detail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Detail</em>'.
	 * @see org.eclipse.ocl.ocl.Detail
	 * @generated
	 */
	EClass getDetail();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.ocl.ocl.Detail#getValues <em>Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Values</em>'.
	 * @see org.eclipse.ocl.ocl.Detail#getValues()
	 * @see #getDetail()
	 * @generated
	 */
	EAttribute getDetail_Values();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.DynamicBehavior <em>Dynamic Behavior</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Behavior</em>'.
	 * @see org.eclipse.ocl.ocl.DynamicBehavior
	 * @generated
	 */
	EClass getDynamicBehavior();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.DynamicElement <em>Dynamic Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Element</em>'.
	 * @see org.eclipse.ocl.ocl.DynamicElement
	 * @generated
	 */
	EClass getDynamicElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.DynamicElement#getMetaType <em>Meta Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Meta Type</em>'.
	 * @see org.eclipse.ocl.ocl.DynamicElement#getMetaType()
	 * @see #getDynamicElement()
	 * @generated
	 */
	EReference getDynamicElement_MetaType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.DynamicProperty <em>Dynamic Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Property</em>'.
	 * @see org.eclipse.ocl.ocl.DynamicProperty
	 * @generated
	 */
	EClass getDynamicProperty();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.DynamicProperty#getDefault <em>Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default</em>'.
	 * @see org.eclipse.ocl.ocl.DynamicProperty#getDefault()
	 * @see #getDynamicProperty()
	 * @generated
	 */
	EAttribute getDynamicProperty_Default();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.DynamicProperty#getReferredProperty <em>Referred Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Property</em>'.
	 * @see org.eclipse.ocl.ocl.DynamicProperty#getReferredProperty()
	 * @see #getDynamicProperty()
	 * @generated
	 */
	EReference getDynamicProperty_ReferredProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.DynamicType <em>Dynamic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Type</em>'.
	 * @see org.eclipse.ocl.ocl.DynamicType
	 * @generated
	 */
	EClass getDynamicType();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.DynamicType#getOwnedDynamicProperties <em>Owned Dynamic Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Dynamic Properties</em>'.
	 * @see org.eclipse.ocl.ocl.DynamicType#getOwnedDynamicProperties()
	 * @see #getDynamicType()
	 * @generated
	 */
	EReference getDynamicType_OwnedDynamicProperties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.DynamicValueSpecification <em>Dynamic Value Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dynamic Value Specification</em>'.
	 * @see org.eclipse.ocl.ocl.DynamicValueSpecification
	 * @generated
	 */
	EClass getDynamicValueSpecification();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see org.eclipse.ocl.ocl.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Element#getAnnotatingComments <em>Annotating Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Annotating Comments</em>'.
	 * @see org.eclipse.ocl.ocl.Element#getAnnotatingComments()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_AnnotatingComments();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Element#getOwnedAnnotations <em>Owned Annotations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Annotations</em>'.
	 * @see org.eclipse.ocl.ocl.Element#getOwnedAnnotations()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_OwnedAnnotations();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Element#getOwnedComments <em>Owned Comments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Comments</em>'.
	 * @see org.eclipse.ocl.ocl.Element#getOwnedComments()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_OwnedComments();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Element#getOwnedExtensions <em>Owned Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Extensions</em>'.
	 * @see org.eclipse.ocl.ocl.Element#getOwnedExtensions()
	 * @see #getElement()
	 * @generated
	 */
	EReference getElement_OwnedExtensions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ElementExtension <em>Element Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Extension</em>'.
	 * @see org.eclipse.ocl.ocl.ElementExtension
	 * @generated
	 */
	EClass getElementExtension();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.ElementExtension#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Base</em>'.
	 * @see org.eclipse.ocl.ocl.ElementExtension#getBase()
	 * @see #getElementExtension()
	 * @generated
	 */
	EReference getElementExtension_Base();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.ElementExtension#getIsApplied <em>Is Applied</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Applied</em>'.
	 * @see org.eclipse.ocl.ocl.ElementExtension#getIsApplied()
	 * @see #getElementExtension()
	 * @generated
	 */
	EAttribute getElementExtension_IsApplied();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.ElementExtension#getIsRequired <em>Is Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Required</em>'.
	 * @see org.eclipse.ocl.ocl.ElementExtension#getIsRequired()
	 * @see #getElementExtension()
	 * @generated
	 */
	EAttribute getElementExtension_IsRequired();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.ElementExtension#getStereotype <em>Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Stereotype</em>'.
	 * @see org.eclipse.ocl.ocl.ElementExtension#getStereotype()
	 * @see #getElementExtension()
	 * @generated
	 */
	EReference getElementExtension_Stereotype();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ElementLiteralExp <em>Element Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.ElementLiteralExp
	 * @generated
	 */
	EClass getElementLiteralExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.ElementLiteralExp#getReferredElement <em>Referred Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Referred Element</em>'.
	 * @see org.eclipse.ocl.ocl.ElementLiteralExp#getReferredElement()
	 * @see #getElementLiteralExp()
	 * @generated
	 */
	EAttribute getElementLiteralExp_ReferredElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.EnumLiteralExp <em>Enum Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.EnumLiteralExp
	 * @generated
	 */
	EClass getEnumLiteralExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.EnumLiteralExp#getReferredLiteral <em>Referred Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Literal</em>'.
	 * @see org.eclipse.ocl.ocl.EnumLiteralExp#getReferredLiteral()
	 * @see #getEnumLiteralExp()
	 * @generated
	 */
	EReference getEnumLiteralExp_ReferredLiteral();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Enumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration</em>'.
	 * @see org.eclipse.ocl.ocl.Enumeration
	 * @generated
	 */
	EClass getEnumeration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Enumeration#getOwnedLiterals <em>Owned Literals</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Literals</em>'.
	 * @see org.eclipse.ocl.ocl.Enumeration#getOwnedLiterals()
	 * @see #getEnumeration()
	 * @generated
	 */
	EReference getEnumeration_OwnedLiterals();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.EnumerationLiteral <em>Enumeration Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enumeration Literal</em>'.
	 * @see org.eclipse.ocl.ocl.EnumerationLiteral
	 * @generated
	 */
	EClass getEnumerationLiteral();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.EnumerationLiteral#getLiteral <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Literal</em>'.
	 * @see org.eclipse.ocl.ocl.EnumerationLiteral#getLiteral()
	 * @see #getEnumerationLiteral()
	 * @generated
	 */
	EAttribute getEnumerationLiteral_Literal();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.EnumerationLiteral#getOwningEnumeration <em>Owning Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Enumeration</em>'.
	 * @see org.eclipse.ocl.ocl.EnumerationLiteral#getOwningEnumeration()
	 * @see #getEnumerationLiteral()
	 * @generated
	 */
	EReference getEnumerationLiteral_OwningEnumeration();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.EnumerationLiteral#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.ocl.ocl.EnumerationLiteral#getValue()
	 * @see #getEnumerationLiteral()
	 * @generated
	 */
	EAttribute getEnumerationLiteral_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ExpressionInOCL <em>Expression In OCL</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression In OCL</em>'.
	 * @see org.eclipse.ocl.ocl.ExpressionInOCL
	 * @generated
	 */
	EClass getExpressionInOCL();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.ExpressionInOCL#getOwnedBody <em>Owned Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Body</em>'.
	 * @see org.eclipse.ocl.ocl.ExpressionInOCL#getOwnedBody()
	 * @see #getExpressionInOCL()
	 * @generated
	 */
	EReference getExpressionInOCL_OwnedBody();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.ExpressionInOCL#getOwnedContext <em>Owned Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Context</em>'.
	 * @see org.eclipse.ocl.ocl.ExpressionInOCL#getOwnedContext()
	 * @see #getExpressionInOCL()
	 * @generated
	 */
	EReference getExpressionInOCL_OwnedContext();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.ExpressionInOCL#getOwnedParameters <em>Owned Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Parameters</em>'.
	 * @see org.eclipse.ocl.ocl.ExpressionInOCL#getOwnedParameters()
	 * @see #getExpressionInOCL()
	 * @generated
	 */
	EReference getExpressionInOCL_OwnedParameters();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.ExpressionInOCL#getOwnedResult <em>Owned Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Result</em>'.
	 * @see org.eclipse.ocl.ocl.ExpressionInOCL#getOwnedResult()
	 * @see #getExpressionInOCL()
	 * @generated
	 */
	EReference getExpressionInOCL_OwnedResult();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see org.eclipse.ocl.ocl.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Feature#getImplementation <em>Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation</em>'.
	 * @see org.eclipse.ocl.ocl.Feature#getImplementation()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_Implementation();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Feature#getImplementationClass <em>Implementation Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Implementation Class</em>'.
	 * @see org.eclipse.ocl.ocl.Feature#getImplementationClass()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_ImplementationClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Feature#getIsStatic <em>Is Static</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Static</em>'.
	 * @see org.eclipse.ocl.ocl.Feature#getIsStatic()
	 * @see #getFeature()
	 * @generated
	 */
	EAttribute getFeature_IsStatic();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.FeatureCallExp <em>Feature Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Call Exp</em>'.
	 * @see org.eclipse.ocl.ocl.FeatureCallExp
	 * @generated
	 */
	EClass getFeatureCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.FeatureCallExp#getIsPre <em>Is Pre</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Pre</em>'.
	 * @see org.eclipse.ocl.ocl.FeatureCallExp#getIsPre()
	 * @see #getFeatureCallExp()
	 * @generated
	 */
	EAttribute getFeatureCallExp_IsPre();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.FinalState <em>Final State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Final State</em>'.
	 * @see org.eclipse.ocl.ocl.FinalState
	 * @generated
	 */
	EClass getFinalState();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.IfExp <em>If Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Exp</em>'.
	 * @see org.eclipse.ocl.ocl.IfExp
	 * @generated
	 */
	EClass getIfExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.IfExp#getIsElseIf <em>Is Else If</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Else If</em>'.
	 * @see org.eclipse.ocl.ocl.IfExp#getIsElseIf()
	 * @see #getIfExp()
	 * @generated
	 */
	EAttribute getIfExp_IsElseIf();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.IfExp#getOwnedCondition <em>Owned Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Condition</em>'.
	 * @see org.eclipse.ocl.ocl.IfExp#getOwnedCondition()
	 * @see #getIfExp()
	 * @generated
	 */
	EReference getIfExp_OwnedCondition();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.IfExp#getOwnedElse <em>Owned Else</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Else</em>'.
	 * @see org.eclipse.ocl.ocl.IfExp#getOwnedElse()
	 * @see #getIfExp()
	 * @generated
	 */
	EReference getIfExp_OwnedElse();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.IfExp#getOwnedThen <em>Owned Then</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Then</em>'.
	 * @see org.eclipse.ocl.ocl.IfExp#getOwnedThen()
	 * @see #getIfExp()
	 * @generated
	 */
	EReference getIfExp_OwnedThen();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Import <em>Import</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Import</em>'.
	 * @see org.eclipse.ocl.ocl.Import
	 * @generated
	 */
	EClass getImport();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Import#getImportedNamespace <em>Imported Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Imported Namespace</em>'.
	 * @see org.eclipse.ocl.ocl.Import#getImportedNamespace()
	 * @see #getImport()
	 * @generated
	 */
	EReference getImport_ImportedNamespace();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Import#getXmiidVersion <em>Xmiid Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xmiid Version</em>'.
	 * @see org.eclipse.ocl.ocl.Import#getXmiidVersion()
	 * @see #getImport()
	 * @generated
	 */
	EAttribute getImport_XmiidVersion();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.InstanceSpecification <em>Instance Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Instance Specification</em>'.
	 * @see org.eclipse.ocl.ocl.InstanceSpecification
	 * @generated
	 */
	EClass getInstanceSpecification();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.InstanceSpecification#getClasses <em>Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Classes</em>'.
	 * @see org.eclipse.ocl.ocl.InstanceSpecification#getClasses()
	 * @see #getInstanceSpecification()
	 * @generated
	 */
	EReference getInstanceSpecification_Classes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.InstanceSpecification#getOwnedSlots <em>Owned Slots</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Slots</em>'.
	 * @see org.eclipse.ocl.ocl.InstanceSpecification#getOwnedSlots()
	 * @see #getInstanceSpecification()
	 * @generated
	 */
	EReference getInstanceSpecification_OwnedSlots();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.InstanceSpecification#getOwnedSpecification <em>Owned Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Specification</em>'.
	 * @see org.eclipse.ocl.ocl.InstanceSpecification#getOwnedSpecification()
	 * @see #getInstanceSpecification()
	 * @generated
	 */
	EReference getInstanceSpecification_OwnedSpecification();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.InstanceSpecification#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Package</em>'.
	 * @see org.eclipse.ocl.ocl.InstanceSpecification#getOwningPackage()
	 * @see #getInstanceSpecification()
	 * @generated
	 */
	EReference getInstanceSpecification_OwningPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.IntegerLiteralExp <em>Integer Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.IntegerLiteralExp
	 * @generated
	 */
	EClass getIntegerLiteralExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.IntegerLiteralExp#getIntegerSymbol <em>Integer Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integer Symbol</em>'.
	 * @see org.eclipse.ocl.ocl.IntegerLiteralExp#getIntegerSymbol()
	 * @see #getIntegerLiteralExp()
	 * @generated
	 */
	EAttribute getIntegerLiteralExp_IntegerSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.InvalidLiteralExp <em>Invalid Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.InvalidLiteralExp
	 * @generated
	 */
	EClass getInvalidLiteralExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.InvalidType <em>Invalid Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Type</em>'.
	 * @see org.eclipse.ocl.ocl.InvalidType
	 * @generated
	 */
	EClass getInvalidType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.IterableType <em>Iterable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterable Type</em>'.
	 * @see org.eclipse.ocl.ocl.IterableType
	 * @generated
	 */
	EClass getIterableType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.IterateExp <em>Iterate Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterate Exp</em>'.
	 * @see org.eclipse.ocl.ocl.IterateExp
	 * @generated
	 */
	EClass getIterateExp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.IterateExp#getOwnedResult <em>Owned Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Result</em>'.
	 * @see org.eclipse.ocl.ocl.IterateExp#getOwnedResult()
	 * @see #getIterateExp()
	 * @generated
	 */
	EReference getIterateExp_OwnedResult();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Iteration <em>Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iteration</em>'.
	 * @see org.eclipse.ocl.ocl.Iteration
	 * @generated
	 */
	EClass getIteration();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Iteration#getOwnedAccumulators <em>Owned Accumulators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Accumulators</em>'.
	 * @see org.eclipse.ocl.ocl.Iteration#getOwnedAccumulators()
	 * @see #getIteration()
	 * @generated
	 */
	EReference getIteration_OwnedAccumulators();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Iteration#getOwnedIterators <em>Owned Iterators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Iterators</em>'.
	 * @see org.eclipse.ocl.ocl.Iteration#getOwnedIterators()
	 * @see #getIteration()
	 * @generated
	 */
	EReference getIteration_OwnedIterators();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.IteratorExp <em>Iterator Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterator Exp</em>'.
	 * @see org.eclipse.ocl.ocl.IteratorExp
	 * @generated
	 */
	EClass getIteratorExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.IteratorVariable <em>Iterator Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterator Variable</em>'.
	 * @see org.eclipse.ocl.ocl.IteratorVariable
	 * @generated
	 */
	EClass getIteratorVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.JavaType <em>Java Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Java Type</em>'.
	 * @see org.eclipse.ocl.ocl.JavaType
	 * @generated
	 */
	EClass getJavaType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.JavaType#getJavaClass <em>Java Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Java Class</em>'.
	 * @see org.eclipse.ocl.ocl.JavaType#getJavaClass()
	 * @see #getJavaType()
	 * @generated
	 */
	EAttribute getJavaType_JavaClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.LambdaType <em>Lambda Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Lambda Type</em>'.
	 * @see org.eclipse.ocl.ocl.LambdaType
	 * @generated
	 */
	EClass getLambdaType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.LambdaType#getContextType <em>Context Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Context Type</em>'.
	 * @see org.eclipse.ocl.ocl.LambdaType#getContextType()
	 * @see #getLambdaType()
	 * @generated
	 */
	EReference getLambdaType_ContextType();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.LambdaType#getParameterType <em>Parameter Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parameter Type</em>'.
	 * @see org.eclipse.ocl.ocl.LambdaType#getParameterType()
	 * @see #getLambdaType()
	 * @generated
	 */
	EReference getLambdaType_ParameterType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.LambdaType#getResultType <em>Result Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Result Type</em>'.
	 * @see org.eclipse.ocl.ocl.LambdaType#getResultType()
	 * @see #getLambdaType()
	 * @generated
	 */
	EReference getLambdaType_ResultType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.LanguageExpression <em>Language Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Language Expression</em>'.
	 * @see org.eclipse.ocl.ocl.LanguageExpression
	 * @generated
	 */
	EClass getLanguageExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.LanguageExpression#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see org.eclipse.ocl.ocl.LanguageExpression#getBody()
	 * @see #getLanguageExpression()
	 * @generated
	 */
	EAttribute getLanguageExpression_Body();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.LanguageExpression#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see org.eclipse.ocl.ocl.LanguageExpression#getLanguage()
	 * @see #getLanguageExpression()
	 * @generated
	 */
	EAttribute getLanguageExpression_Language();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.LanguageExpression#getOwningConstraint <em>Owning Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Constraint</em>'.
	 * @see org.eclipse.ocl.ocl.LanguageExpression#getOwningConstraint()
	 * @see #getLanguageExpression()
	 * @generated
	 */
	EReference getLanguageExpression_OwningConstraint();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.LetExp <em>Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Let Exp</em>'.
	 * @see org.eclipse.ocl.ocl.LetExp
	 * @generated
	 */
	EClass getLetExp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.LetExp#getOwnedIn <em>Owned In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned In</em>'.
	 * @see org.eclipse.ocl.ocl.LetExp#getOwnedIn()
	 * @see #getLetExp()
	 * @generated
	 */
	EReference getLetExp_OwnedIn();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.LetExp#getOwnedVariable <em>Owned Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Variable</em>'.
	 * @see org.eclipse.ocl.ocl.LetExp#getOwnedVariable()
	 * @see #getLetExp()
	 * @generated
	 */
	EReference getLetExp_OwnedVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.LetVariable <em>Let Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Let Variable</em>'.
	 * @see org.eclipse.ocl.ocl.LetVariable
	 * @generated
	 */
	EClass getLetVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Library <em>Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Library</em>'.
	 * @see org.eclipse.ocl.ocl.Library
	 * @generated
	 */
	EClass getLibrary();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Library#getOwnedPrecedences <em>Owned Precedences</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Precedences</em>'.
	 * @see org.eclipse.ocl.ocl.Library#getOwnedPrecedences()
	 * @see #getLibrary()
	 * @generated
	 */
	EReference getLibrary_OwnedPrecedences();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.LiteralExp <em>Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.LiteralExp
	 * @generated
	 */
	EClass getLiteralExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.LoopExp <em>Loop Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loop Exp</em>'.
	 * @see org.eclipse.ocl.ocl.LoopExp
	 * @generated
	 */
	EClass getLoopExp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.LoopExp#getOwnedBody <em>Owned Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Body</em>'.
	 * @see org.eclipse.ocl.ocl.LoopExp#getOwnedBody()
	 * @see #getLoopExp()
	 * @generated
	 */
	EReference getLoopExp_OwnedBody();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.LoopExp#getOwnedCoIterators <em>Owned Co Iterators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Co Iterators</em>'.
	 * @see org.eclipse.ocl.ocl.LoopExp#getOwnedCoIterators()
	 * @see #getLoopExp()
	 * @generated
	 */
	EReference getLoopExp_OwnedCoIterators();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.LoopExp#getOwnedIterators <em>Owned Iterators</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Iterators</em>'.
	 * @see org.eclipse.ocl.ocl.LoopExp#getOwnedIterators()
	 * @see #getLoopExp()
	 * @generated
	 */
	EReference getLoopExp_OwnedIterators();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.LoopExp#getReferredIteration <em>Referred Iteration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Iteration</em>'.
	 * @see org.eclipse.ocl.ocl.LoopExp#getReferredIteration()
	 * @see #getLoopExp()
	 * @generated
	 */
	EReference getLoopExp_ReferredIteration();

	/**
	 * Returns the meta object for class '{@link java.util.Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map</em>'.
	 * @see java.util.Map
	 * @generated
	 */
	EClass getMap();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map#getKeyType <em>Key Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key Type</em>'.
	 * @see java.util.Map#getKeyType()
	 * @see #getMap()
	 * @generated
	 */
	EReference getMap_KeyType();

	/**
	 * Returns the meta object for the reference '{@link java.util.Map#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value Type</em>'.
	 * @see java.util.Map#getValueType()
	 * @see #getMap()
	 * @generated
	 */
	EReference getMap_ValueType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.MapLiteralExp <em>Map Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.MapLiteralExp
	 * @generated
	 */
	EClass getMapLiteralExp();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.MapLiteralExp#getOwnedParts <em>Owned Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Parts</em>'.
	 * @see org.eclipse.ocl.ocl.MapLiteralExp#getOwnedParts()
	 * @see #getMapLiteralExp()
	 * @generated
	 */
	EReference getMapLiteralExp_OwnedParts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.MapLiteralPart <em>Map Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Literal Part</em>'.
	 * @see org.eclipse.ocl.ocl.MapLiteralPart
	 * @generated
	 */
	EClass getMapLiteralPart();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.MapLiteralPart#getOwnedKey <em>Owned Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Key</em>'.
	 * @see org.eclipse.ocl.ocl.MapLiteralPart#getOwnedKey()
	 * @see #getMapLiteralPart()
	 * @generated
	 */
	EReference getMapLiteralPart_OwnedKey();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.MapLiteralPart#getOwnedValue <em>Owned Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Value</em>'.
	 * @see org.eclipse.ocl.ocl.MapLiteralPart#getOwnedValue()
	 * @see #getMapLiteralPart()
	 * @generated
	 */
	EReference getMapLiteralPart_OwnedValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.MapType <em>Map Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map Type</em>'.
	 * @see org.eclipse.ocl.ocl.MapType
	 * @generated
	 */
	EClass getMapType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.MapType#getEntryClass <em>Entry Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Entry Class</em>'.
	 * @see org.eclipse.ocl.ocl.MapType#getEntryClass()
	 * @see #getMapType()
	 * @generated
	 */
	EReference getMapType_EntryClass();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.MapType#getKeyType <em>Key Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Key Type</em>'.
	 * @see org.eclipse.ocl.ocl.MapType#getKeyType()
	 * @see #getMapType()
	 * @generated
	 */
	EReference getMapType_KeyType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.MapType#getKeysAreNullFree <em>Keys Are Null Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Keys Are Null Free</em>'.
	 * @see org.eclipse.ocl.ocl.MapType#getKeysAreNullFree()
	 * @see #getMapType()
	 * @generated
	 */
	EAttribute getMapType_KeysAreNullFree();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.MapType#getValueType <em>Value Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Value Type</em>'.
	 * @see org.eclipse.ocl.ocl.MapType#getValueType()
	 * @see #getMapType()
	 * @generated
	 */
	EReference getMapType_ValueType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.MapType#getValuesAreNullFree <em>Values Are Null Free</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Values Are Null Free</em>'.
	 * @see org.eclipse.ocl.ocl.MapType#getValuesAreNullFree()
	 * @see #getMapType()
	 * @generated
	 */
	EAttribute getMapType_ValuesAreNullFree();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.MessageExp <em>Message Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Exp</em>'.
	 * @see org.eclipse.ocl.ocl.MessageExp
	 * @generated
	 */
	EClass getMessageExp();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.MessageExp#getOwnedArguments <em>Owned Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Arguments</em>'.
	 * @see org.eclipse.ocl.ocl.MessageExp#getOwnedArguments()
	 * @see #getMessageExp()
	 * @generated
	 */
	EReference getMessageExp_OwnedArguments();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.MessageExp#getOwnedCalledOperation <em>Owned Called Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Called Operation</em>'.
	 * @see org.eclipse.ocl.ocl.MessageExp#getOwnedCalledOperation()
	 * @see #getMessageExp()
	 * @generated
	 */
	EReference getMessageExp_OwnedCalledOperation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.MessageExp#getOwnedSentSignal <em>Owned Sent Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Sent Signal</em>'.
	 * @see org.eclipse.ocl.ocl.MessageExp#getOwnedSentSignal()
	 * @see #getMessageExp()
	 * @generated
	 */
	EReference getMessageExp_OwnedSentSignal();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.MessageExp#getOwnedTarget <em>Owned Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Target</em>'.
	 * @see org.eclipse.ocl.ocl.MessageExp#getOwnedTarget()
	 * @see #getMessageExp()
	 * @generated
	 */
	EReference getMessageExp_OwnedTarget();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.MessageType <em>Message Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Type</em>'.
	 * @see org.eclipse.ocl.ocl.MessageType
	 * @generated
	 */
	EClass getMessageType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.MessageType#getReferredOperation <em>Referred Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Operation</em>'.
	 * @see org.eclipse.ocl.ocl.MessageType#getReferredOperation()
	 * @see #getMessageType()
	 * @generated
	 */
	EReference getMessageType_ReferredOperation();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.MessageType#getReferredSignal <em>Referred Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Signal</em>'.
	 * @see org.eclipse.ocl.ocl.MessageType#getReferredSignal()
	 * @see #getMessageType()
	 * @generated
	 */
	EReference getMessageType_ReferredSignal();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Model <em>Model</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Model</em>'.
	 * @see org.eclipse.ocl.ocl.Model
	 * @generated
	 */
	EClass getModel();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Model#getExternalURI <em>External URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>External URI</em>'.
	 * @see org.eclipse.ocl.ocl.Model#getExternalURI()
	 * @see #getModel()
	 * @generated
	 */
	EAttribute getModel_ExternalURI();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Model#getOwnedImports <em>Owned Imports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Imports</em>'.
	 * @see org.eclipse.ocl.ocl.Model#getOwnedImports()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_OwnedImports();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Model#getOwnedPackages <em>Owned Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Packages</em>'.
	 * @see org.eclipse.ocl.ocl.Model#getOwnedPackages()
	 * @see #getModel()
	 * @generated
	 */
	EReference getModel_OwnedPackages();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Model#getXmiidVersion <em>Xmiid Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xmiid Version</em>'.
	 * @see org.eclipse.ocl.ocl.Model#getXmiidVersion()
	 * @see #getModel()
	 * @generated
	 */
	EAttribute getModel_XmiidVersion();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.utilities.MorePivotable <em>More Pivotable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>More Pivotable</em>'.
	 * @see org.eclipse.ocl.pivot.utilities.MorePivotable
	 * @generated
	 */
	EClass getMorePivotable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.utilities.Nameable <em>Nameable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nameable</em>'.
	 * @see org.eclipse.ocl.pivot.utilities.Nameable
	 * @generated
	 */
	EClass getNameable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Named Element</em>'.
	 * @see org.eclipse.ocl.ocl.NamedElement
	 * @generated
	 */
	EClass getNamedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.NamedElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.ocl.NamedElement#getName()
	 * @see #getNamedElement()
	 * @generated
	 */
	EAttribute getNamedElement_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Namespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Namespace</em>'.
	 * @see org.eclipse.ocl.ocl.Namespace
	 * @generated
	 */
	EClass getNamespace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Namespace#getOwnedConstraints <em>Owned Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Constraints</em>'.
	 * @see org.eclipse.ocl.ocl.Namespace#getOwnedConstraints()
	 * @see #getNamespace()
	 * @generated
	 */
	EReference getNamespace_OwnedConstraints();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.NavigationCallExp <em>Navigation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Navigation Call Exp</em>'.
	 * @see org.eclipse.ocl.ocl.NavigationCallExp
	 * @generated
	 */
	EClass getNavigationCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.NavigationCallExp#getNavigationSource <em>Navigation Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Navigation Source</em>'.
	 * @see org.eclipse.ocl.ocl.NavigationCallExp#getNavigationSource()
	 * @see #getNavigationCallExp()
	 * @generated
	 */
	EReference getNavigationCallExp_NavigationSource();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.NavigationCallExp#getQualifiers <em>Qualifiers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Qualifiers</em>'.
	 * @see org.eclipse.ocl.ocl.NavigationCallExp#getQualifiers()
	 * @see #getNavigationCallExp()
	 * @generated
	 */
	EReference getNavigationCallExp_Qualifiers();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.NullLiteralExp <em>Null Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Null Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.NullLiteralExp
	 * @generated
	 */
	EClass getNullLiteralExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.NumericLiteralExp <em>Numeric Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Numeric Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.NumericLiteralExp
	 * @generated
	 */
	EClass getNumericLiteralExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OCLExpression <em>OCL Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>OCL Expression</em>'.
	 * @see org.eclipse.ocl.ocl.OCLExpression
	 * @generated
	 */
	EClass getOCLExpression();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.OCLExpression#getTypeValue <em>Type Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type Value</em>'.
	 * @see org.eclipse.ocl.ocl.OCLExpression#getTypeValue()
	 * @see #getOCLExpression()
	 * @generated
	 */
	EReference getOCLExpression_TypeValue();

	/**
	 * Returns the meta object for class '{@link java.lang.Object <em>Ocl Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Any</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	EClass getOclAny();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclComparable <em>Ocl Comparable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Comparable</em>'.
	 * @see org.eclipse.ocl.ocl.OclComparable
	 * @generated
	 */
	EClass getOclComparable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclElement <em>Ocl Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Element</em>'.
	 * @see org.eclipse.ocl.ocl.OclElement
	 * @generated
	 */
	EClass getOclElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.OclElement#getOclContainer <em>Ocl Container</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ocl Container</em>'.
	 * @see org.eclipse.ocl.ocl.OclElement#getOclContainer()
	 * @see #getOclElement()
	 * @generated
	 */
	EReference getOclElement_OclContainer();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.OclElement#getOclContents <em>Ocl Contents</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ocl Contents</em>'.
	 * @see org.eclipse.ocl.ocl.OclElement#getOclContents()
	 * @see #getOclElement()
	 * @generated
	 */
	EReference getOclElement_OclContents();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclEnumeration <em>Ocl Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Enumeration</em>'.
	 * @see org.eclipse.ocl.ocl.OclEnumeration
	 * @generated
	 */
	EClass getOclEnumeration();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.InvalidType <em>Ocl Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Invalid</em>'.
	 * @see org.eclipse.ocl.pivot.InvalidType
	 * @generated
	 */
	EClass getOclInvalid();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.pivot.InvalidType#getOclBadProperty <em>Ocl Bad Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ocl Bad Property</em>'.
	 * @see org.eclipse.ocl.pivot.InvalidType#getOclBadProperty()
	 * @see #getOclInvalid()
	 * @generated
	 */
	EReference getOclInvalid_OclBadProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclLambda <em>Ocl Lambda</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Lambda</em>'.
	 * @see org.eclipse.ocl.ocl.OclLambda
	 * @generated
	 */
	EClass getOclLambda();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclMessage <em>Ocl Message</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Message</em>'.
	 * @see org.eclipse.ocl.ocl.OclMessage
	 * @generated
	 */
	EClass getOclMessage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.SelfType <em>Ocl Self</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Self</em>'.
	 * @see org.eclipse.ocl.pivot.SelfType
	 * @generated
	 */
	EClass getOclSelf();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclState <em>Ocl State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl State</em>'.
	 * @see org.eclipse.ocl.ocl.OclState
	 * @generated
	 */
	EClass getOclState();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclStereotype <em>Ocl Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Stereotype</em>'.
	 * @see org.eclipse.ocl.ocl.OclStereotype
	 * @generated
	 */
	EClass getOclStereotype();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclSummable <em>Ocl Summable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Summable</em>'.
	 * @see org.eclipse.ocl.ocl.OclSummable
	 * @generated
	 */
	EClass getOclSummable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclTuple <em>Ocl Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Tuple</em>'.
	 * @see org.eclipse.ocl.ocl.OclTuple
	 * @generated
	 */
	EClass getOclTuple();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OclType <em>Ocl Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Type</em>'.
	 * @see org.eclipse.ocl.ocl.OclType
	 * @generated
	 */
	EClass getOclType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.VoidType <em>Ocl Void</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Void</em>'.
	 * @see org.eclipse.ocl.pivot.VoidType
	 * @generated
	 */
	EClass getOclVoid();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see org.eclipse.ocl.ocl.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.Operation#getBodyExpression <em>Body Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body Expression</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getBodyExpression()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_BodyExpression();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Operation#getIsInvalidating <em>Is Invalidating</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Invalidating</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getIsInvalidating()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_IsInvalidating();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Operation#getIsTransient <em>Is Transient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Transient</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getIsTransient()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_IsTransient();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Operation#getIsTypeof <em>Is Typeof</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Typeof</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getIsTypeof()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_IsTypeof();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Operation#getIsValidating <em>Is Validating</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Validating</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getIsValidating()
	 * @see #getOperation()
	 * @generated
	 */
	EAttribute getOperation_IsValidating();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Operation#getOwnedParameters <em>Owned Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Parameters</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getOwnedParameters()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_OwnedParameters();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Operation#getOwnedPostconditions <em>Owned Postconditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Postconditions</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getOwnedPostconditions()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_OwnedPostconditions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Operation#getOwnedPreconditions <em>Owned Preconditions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Preconditions</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getOwnedPreconditions()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_OwnedPreconditions();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Operation#getOwningClass <em>Owning Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Class</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getOwningClass()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_OwningClass();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Operation#getPrecedence <em>Precedence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Precedence</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getPrecedence()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Precedence();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Operation#getRaisedExceptions <em>Raised Exceptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Raised Exceptions</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getRaisedExceptions()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_RaisedExceptions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Operation#getRedefinedOperations <em>Redefined Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Redefined Operations</em>'.
	 * @see org.eclipse.ocl.ocl.Operation#getRedefinedOperations()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_RedefinedOperations();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OperationCallExp <em>Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Call Exp</em>'.
	 * @see org.eclipse.ocl.ocl.OperationCallExp
	 * @generated
	 */
	EClass getOperationCallExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.OperationCallExp#getIsVirtual <em>Is Virtual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Virtual</em>'.
	 * @see org.eclipse.ocl.ocl.OperationCallExp#getIsVirtual()
	 * @see #getOperationCallExp()
	 * @generated
	 */
	EAttribute getOperationCallExp_IsVirtual();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.OperationCallExp#getOwnedArguments <em>Owned Arguments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Arguments</em>'.
	 * @see org.eclipse.ocl.ocl.OperationCallExp#getOwnedArguments()
	 * @see #getOperationCallExp()
	 * @generated
	 */
	EReference getOperationCallExp_OwnedArguments();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.OperationCallExp#getReferredOperation <em>Referred Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Operation</em>'.
	 * @see org.eclipse.ocl.ocl.OperationCallExp#getReferredOperation()
	 * @see #getOperationCallExp()
	 * @generated
	 */
	EReference getOperationCallExp_ReferredOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OppositePropertyCallExp <em>Opposite Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Opposite Property Call Exp</em>'.
	 * @see org.eclipse.ocl.ocl.OppositePropertyCallExp
	 * @generated
	 */
	EClass getOppositePropertyCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.OppositePropertyCallExp#getReferredProperty <em>Referred Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Property</em>'.
	 * @see org.eclipse.ocl.ocl.OppositePropertyCallExp#getReferredProperty()
	 * @see #getOppositePropertyCallExp()
	 * @generated
	 */
	EReference getOppositePropertyCallExp_ReferredProperty();

	/**
	 * Returns the meta object for class '{@link java.util.Collection <em>Ordered Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordered Collection</em>'.
	 * @see java.util.Collection
	 * @generated
	 */
	EClass getOrderedCollection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.values.OrderedSet <em>Ordered Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordered Set</em>'.
	 * @see org.eclipse.ocl.pivot.values.OrderedSet
	 * @generated
	 */
	EClass getOrderedSet();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OrderedSetType <em>Ordered Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordered Set Type</em>'.
	 * @see org.eclipse.ocl.ocl.OrderedSetType
	 * @generated
	 */
	EClass getOrderedSetType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.OrphanCompletePackage <em>Orphan Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Orphan Complete Package</em>'.
	 * @see org.eclipse.ocl.ocl.OrphanCompletePackage
	 * @generated
	 */
	EClass getOrphanCompletePackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Package <em>Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package</em>'.
	 * @see org.eclipse.ocl.ocl.Package
	 * @generated
	 */
	EClass getPackage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Package#getURI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>URI</em>'.
	 * @see org.eclipse.ocl.ocl.Package#getURI()
	 * @see #getPackage()
	 * @generated
	 */
	EAttribute getPackage_URI();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Package#getImportedPackages <em>Imported Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Imported Packages</em>'.
	 * @see org.eclipse.ocl.ocl.Package#getImportedPackages()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_ImportedPackages();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Package#getNsPrefix <em>Ns Prefix</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ns Prefix</em>'.
	 * @see org.eclipse.ocl.ocl.Package#getNsPrefix()
	 * @see #getPackage()
	 * @generated
	 */
	EAttribute getPackage_NsPrefix();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Package#getOwnedClasses <em>Owned Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Classes</em>'.
	 * @see org.eclipse.ocl.ocl.Package#getOwnedClasses()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_OwnedClasses();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Package#getOwnedInstances <em>Owned Instances</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Instances</em>'.
	 * @see org.eclipse.ocl.ocl.Package#getOwnedInstances()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_OwnedInstances();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Package#getOwnedPackages <em>Owned Packages</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Packages</em>'.
	 * @see org.eclipse.ocl.ocl.Package#getOwnedPackages()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_OwnedPackages();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Package#getOwnedProfileApplications <em>Owned Profile Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Profile Applications</em>'.
	 * @see org.eclipse.ocl.ocl.Package#getOwnedProfileApplications()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_OwnedProfileApplications();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Package#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Package</em>'.
	 * @see org.eclipse.ocl.ocl.Package#getOwningPackage()
	 * @see #getPackage()
	 * @generated
	 */
	EReference getPackage_OwningPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter</em>'.
	 * @see org.eclipse.ocl.ocl.Parameter
	 * @generated
	 */
	EClass getParameter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Parameter#getIsTypeof <em>Is Typeof</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Typeof</em>'.
	 * @see org.eclipse.ocl.ocl.Parameter#getIsTypeof()
	 * @see #getParameter()
	 * @generated
	 */
	EAttribute getParameter_IsTypeof();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Parameter#getOwningOperation <em>Owning Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Operation</em>'.
	 * @see org.eclipse.ocl.ocl.Parameter#getOwningOperation()
	 * @see #getParameter()
	 * @generated
	 */
	EReference getParameter_OwningOperation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ParameterVariable <em>Parameter Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Parameter Variable</em>'.
	 * @see org.eclipse.ocl.ocl.ParameterVariable
	 * @generated
	 */
	EClass getParameterVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.utilities.Pivotable <em>Pivotable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pivotable</em>'.
	 * @see org.eclipse.ocl.pivot.utilities.Pivotable
	 * @generated
	 */
	EClass getPivotable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Precedence <em>Precedence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Precedence</em>'.
	 * @see org.eclipse.ocl.ocl.Precedence
	 * @generated
	 */
	EClass getPrecedence();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Precedence#getAssociativity <em>Associativity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Associativity</em>'.
	 * @see org.eclipse.ocl.ocl.Precedence#getAssociativity()
	 * @see #getPrecedence()
	 * @generated
	 */
	EAttribute getPrecedence_Associativity();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Precedence#getOrder <em>Order</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Order</em>'.
	 * @see org.eclipse.ocl.ocl.Precedence#getOrder()
	 * @see #getPrecedence()
	 * @generated
	 */
	EAttribute getPrecedence_Order();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.PrimitiveCompletePackage <em>Primitive Complete Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Complete Package</em>'.
	 * @see org.eclipse.ocl.ocl.PrimitiveCompletePackage
	 * @generated
	 */
	EClass getPrimitiveCompletePackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.PrimitiveLiteralExp <em>Primitive Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.PrimitiveLiteralExp
	 * @generated
	 */
	EClass getPrimitiveLiteralExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.PrimitiveType <em>Primitive Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Type</em>'.
	 * @see org.eclipse.ocl.ocl.PrimitiveType
	 * @generated
	 */
	EClass getPrimitiveType();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.PrimitiveType#getCoercions <em>Coercions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Coercions</em>'.
	 * @see org.eclipse.ocl.ocl.PrimitiveType#getCoercions()
	 * @see #getPrimitiveType()
	 * @generated
	 */
	EReference getPrimitiveType_Coercions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Profile <em>Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Profile</em>'.
	 * @see org.eclipse.ocl.ocl.Profile
	 * @generated
	 */
	EClass getProfile();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Profile#getProfileApplications <em>Profile Applications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Profile Applications</em>'.
	 * @see org.eclipse.ocl.ocl.Profile#getProfileApplications()
	 * @see #getProfile()
	 * @generated
	 */
	EReference getProfile_ProfileApplications();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ProfileApplication <em>Profile Application</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Profile Application</em>'.
	 * @see org.eclipse.ocl.ocl.ProfileApplication
	 * @generated
	 */
	EClass getProfileApplication();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.ProfileApplication#getAppliedProfile <em>Applied Profile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Applied Profile</em>'.
	 * @see org.eclipse.ocl.ocl.ProfileApplication#getAppliedProfile()
	 * @see #getProfileApplication()
	 * @generated
	 */
	EReference getProfileApplication_AppliedProfile();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.ProfileApplication#getIsStrict <em>Is Strict</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Strict</em>'.
	 * @see org.eclipse.ocl.ocl.ProfileApplication#getIsStrict()
	 * @see #getProfileApplication()
	 * @generated
	 */
	EAttribute getProfileApplication_IsStrict();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.ProfileApplication#getOwningPackage <em>Owning Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Package</em>'.
	 * @see org.eclipse.ocl.ocl.ProfileApplication#getOwningPackage()
	 * @see #getProfileApplication()
	 * @generated
	 */
	EReference getProfileApplication_OwningPackage();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Property <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property</em>'.
	 * @see org.eclipse.ocl.ocl.Property
	 * @generated
	 */
	EClass getProperty();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Property#getAssociationClass <em>Association Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Association Class</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getAssociationClass()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_AssociationClass();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getDefaultValue <em>Default Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getDefaultValue()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_DefaultValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getDefaultValueString <em>Default Value String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Default Value String</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getDefaultValueString()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_DefaultValueString();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getIsComposite <em>Is Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Composite</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getIsComposite()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_IsComposite();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getIsDerived <em>Is Derived</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Derived</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getIsDerived()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_IsDerived();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getIsID <em>Is ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is ID</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getIsID()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_IsID();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getIsImplicit <em>Is Implicit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Implicit</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getIsImplicit()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_IsImplicit();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getIsReadOnly <em>Is Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Read Only</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getIsReadOnly()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_IsReadOnly();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getIsResolveProxies <em>Is Resolve Proxies</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Resolve Proxies</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getIsResolveProxies()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_IsResolveProxies();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getIsTransient <em>Is Transient</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Transient</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getIsTransient()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_IsTransient();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getIsUnsettable <em>Is Unsettable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Unsettable</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getIsUnsettable()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_IsUnsettable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Property#getIsVolatile <em>Is Volatile</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Volatile</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getIsVolatile()
	 * @see #getProperty()
	 * @generated
	 */
	EAttribute getProperty_IsVolatile();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Property#getKeys <em>Keys</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Keys</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getKeys()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_Keys();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Property#getOpposite <em>Opposite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Opposite</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getOpposite()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_Opposite();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.Property#getOwnedExpression <em>Owned Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Expression</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getOwnedExpression()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_OwnedExpression();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Property#getOwningClass <em>Owning Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Class</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getOwningClass()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_OwningClass();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Property#getRedefinedProperties <em>Redefined Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Redefined Properties</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getRedefinedProperties()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_RedefinedProperties();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Property#getReferredProperty <em>Referred Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Property</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getReferredProperty()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_ReferredProperty();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Property#getSubsettedProperty <em>Subsetted Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Subsetted Property</em>'.
	 * @see org.eclipse.ocl.ocl.Property#getSubsettedProperty()
	 * @see #getProperty()
	 * @generated
	 */
	EReference getProperty_SubsettedProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.PropertyCallExp <em>Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Call Exp</em>'.
	 * @see org.eclipse.ocl.ocl.PropertyCallExp
	 * @generated
	 */
	EClass getPropertyCallExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.PropertyCallExp#getReferredProperty <em>Referred Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Property</em>'.
	 * @see org.eclipse.ocl.ocl.PropertyCallExp#getReferredProperty()
	 * @see #getPropertyCallExp()
	 * @generated
	 */
	EReference getPropertyCallExp_ReferredProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Pseudostate <em>Pseudostate</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Pseudostate</em>'.
	 * @see org.eclipse.ocl.ocl.Pseudostate
	 * @generated
	 */
	EClass getPseudostate();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Pseudostate#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.ocl.ocl.Pseudostate#getKind()
	 * @see #getPseudostate()
	 * @generated
	 */
	EAttribute getPseudostate_Kind();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Pseudostate#getOwningState <em>Owning State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning State</em>'.
	 * @see org.eclipse.ocl.ocl.Pseudostate#getOwningState()
	 * @see #getPseudostate()
	 * @generated
	 */
	EReference getPseudostate_OwningState();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Pseudostate#getOwningStateMachine <em>Owning State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning State Machine</em>'.
	 * @see org.eclipse.ocl.ocl.Pseudostate#getOwningStateMachine()
	 * @see #getPseudostate()
	 * @generated
	 */
	EReference getPseudostate_OwningStateMachine();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.RealLiteralExp <em>Real Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Real Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.RealLiteralExp
	 * @generated
	 */
	EClass getRealLiteralExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.RealLiteralExp#getRealSymbol <em>Real Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Real Symbol</em>'.
	 * @see org.eclipse.ocl.ocl.RealLiteralExp#getRealSymbol()
	 * @see #getRealLiteralExp()
	 * @generated
	 */
	EAttribute getRealLiteralExp_RealSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ReferringElement <em>Referring Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Referring Element</em>'.
	 * @see org.eclipse.ocl.ocl.ReferringElement
	 * @generated
	 */
	EClass getReferringElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Region <em>Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Region</em>'.
	 * @see org.eclipse.ocl.ocl.Region
	 * @generated
	 */
	EClass getRegion();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Region#getExtendedRegion <em>Extended Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Extended Region</em>'.
	 * @see org.eclipse.ocl.ocl.Region#getExtendedRegion()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_ExtendedRegion();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Region#getOwnedSubvertexes <em>Owned Subvertexes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Subvertexes</em>'.
	 * @see org.eclipse.ocl.ocl.Region#getOwnedSubvertexes()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_OwnedSubvertexes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Region#getOwnedTransitions <em>Owned Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Transitions</em>'.
	 * @see org.eclipse.ocl.ocl.Region#getOwnedTransitions()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_OwnedTransitions();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Region#getOwningState <em>Owning State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning State</em>'.
	 * @see org.eclipse.ocl.ocl.Region#getOwningState()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_OwningState();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Region#getOwningStateMachine <em>Owning State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning State Machine</em>'.
	 * @see org.eclipse.ocl.ocl.Region#getOwningStateMachine()
	 * @see #getRegion()
	 * @generated
	 */
	EReference getRegion_OwningStateMachine();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ResultVariable <em>Result Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Result Variable</em>'.
	 * @see org.eclipse.ocl.ocl.ResultVariable
	 * @generated
	 */
	EClass getResultVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.SelfType <em>Self Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Self Type</em>'.
	 * @see org.eclipse.ocl.ocl.SelfType
	 * @generated
	 */
	EClass getSelfType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.SendSignalAction <em>Send Signal Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Send Signal Action</em>'.
	 * @see org.eclipse.ocl.ocl.SendSignalAction
	 * @generated
	 */
	EClass getSendSignalAction();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.SendSignalAction#getSignal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Signal</em>'.
	 * @see org.eclipse.ocl.ocl.SendSignalAction#getSignal()
	 * @see #getSendSignalAction()
	 * @generated
	 */
	EReference getSendSignalAction_Signal();

	/**
	 * Returns the meta object for class '{@link java.util.List <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence</em>'.
	 * @see java.util.List
	 * @generated
	 */
	EClass getSequence();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.SequenceType <em>Sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Type</em>'.
	 * @see org.eclipse.ocl.ocl.SequenceType
	 * @generated
	 */
	EClass getSequenceType();

	/**
	 * Returns the meta object for class '{@link java.util.Set <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set</em>'.
	 * @see java.util.Set
	 * @generated
	 */
	EClass getSet();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.SetType <em>Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set Type</em>'.
	 * @see org.eclipse.ocl.ocl.SetType
	 * @generated
	 */
	EClass getSetType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ShadowExp <em>Shadow Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shadow Exp</em>'.
	 * @see org.eclipse.ocl.ocl.ShadowExp
	 * @generated
	 */
	EClass getShadowExp();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.ShadowExp#getOwnedParts <em>Owned Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Parts</em>'.
	 * @see org.eclipse.ocl.ocl.ShadowExp#getOwnedParts()
	 * @see #getShadowExp()
	 * @generated
	 */
	EReference getShadowExp_OwnedParts();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.ShadowExp#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.ocl.ocl.ShadowExp#getValue()
	 * @see #getShadowExp()
	 * @generated
	 */
	EAttribute getShadowExp_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ShadowPart <em>Shadow Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Shadow Part</em>'.
	 * @see org.eclipse.ocl.ocl.ShadowPart
	 * @generated
	 */
	EClass getShadowPart();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.ShadowPart#getOwnedInit <em>Owned Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Init</em>'.
	 * @see org.eclipse.ocl.ocl.ShadowPart#getOwnedInit()
	 * @see #getShadowPart()
	 * @generated
	 */
	EReference getShadowPart_OwnedInit();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.ShadowPart#getReferredProperty <em>Referred Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Property</em>'.
	 * @see org.eclipse.ocl.ocl.ShadowPart#getReferredProperty()
	 * @see #getShadowPart()
	 * @generated
	 */
	EReference getShadowPart_ReferredProperty();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Signal <em>Signal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Signal</em>'.
	 * @see org.eclipse.ocl.ocl.Signal
	 * @generated
	 */
	EClass getSignal();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Slot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Slot</em>'.
	 * @see org.eclipse.ocl.ocl.Slot
	 * @generated
	 */
	EClass getSlot();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Slot#getDefiningProperty <em>Defining Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Defining Property</em>'.
	 * @see org.eclipse.ocl.ocl.Slot#getDefiningProperty()
	 * @see #getSlot()
	 * @generated
	 */
	EReference getSlot_DefiningProperty();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Slot#getOwnedValues <em>Owned Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Values</em>'.
	 * @see org.eclipse.ocl.ocl.Slot#getOwnedValues()
	 * @see #getSlot()
	 * @generated
	 */
	EReference getSlot_OwnedValues();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Slot#getOwningInstance <em>Owning Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Instance</em>'.
	 * @see org.eclipse.ocl.ocl.Slot#getOwningInstance()
	 * @see #getSlot()
	 * @generated
	 */
	EReference getSlot_OwningInstance();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.StandardLibrary <em>Standard Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Standard Library</em>'.
	 * @see org.eclipse.ocl.ocl.StandardLibrary
	 * @generated
	 */
	EClass getStandardLibrary();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.StandardLibrary#getOwningCompleteEnvironment <em>Owning Complete Environment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Complete Environment</em>'.
	 * @see org.eclipse.ocl.ocl.StandardLibrary#getOwningCompleteEnvironment()
	 * @see #getStandardLibrary()
	 * @generated
	 */
	EReference getStandardLibrary_OwningCompleteEnvironment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see org.eclipse.ocl.ocl.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.State#getIsComposite <em>Is Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Composite</em>'.
	 * @see org.eclipse.ocl.ocl.State#getIsComposite()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_IsComposite();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.State#getIsOrthogonal <em>Is Orthogonal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Orthogonal</em>'.
	 * @see org.eclipse.ocl.ocl.State#getIsOrthogonal()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_IsOrthogonal();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.State#getIsSimple <em>Is Simple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Simple</em>'.
	 * @see org.eclipse.ocl.ocl.State#getIsSimple()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_IsSimple();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.State#getIsSubmachineState <em>Is Submachine State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Submachine State</em>'.
	 * @see org.eclipse.ocl.ocl.State#getIsSubmachineState()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_IsSubmachineState();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.State#getOwnedConnectionPoints <em>Owned Connection Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Connection Points</em>'.
	 * @see org.eclipse.ocl.ocl.State#getOwnedConnectionPoints()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_OwnedConnectionPoints();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.State#getOwnedConnections <em>Owned Connections</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Connections</em>'.
	 * @see org.eclipse.ocl.ocl.State#getOwnedConnections()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_OwnedConnections();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.State#getOwnedDeferrableTriggers <em>Owned Deferrable Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Deferrable Triggers</em>'.
	 * @see org.eclipse.ocl.ocl.State#getOwnedDeferrableTriggers()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_OwnedDeferrableTriggers();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.State#getOwnedDoActivity <em>Owned Do Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Do Activity</em>'.
	 * @see org.eclipse.ocl.ocl.State#getOwnedDoActivity()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_OwnedDoActivity();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.State#getOwnedEntry <em>Owned Entry</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Entry</em>'.
	 * @see org.eclipse.ocl.ocl.State#getOwnedEntry()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_OwnedEntry();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.State#getOwnedExit <em>Owned Exit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Exit</em>'.
	 * @see org.eclipse.ocl.ocl.State#getOwnedExit()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_OwnedExit();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.State#getOwnedRegions <em>Owned Regions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Regions</em>'.
	 * @see org.eclipse.ocl.ocl.State#getOwnedRegions()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_OwnedRegions();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.State#getOwnedStateInvariant <em>Owned State Invariant</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned State Invariant</em>'.
	 * @see org.eclipse.ocl.ocl.State#getOwnedStateInvariant()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_OwnedStateInvariant();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.State#getRedefinedState <em>Redefined State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Redefined State</em>'.
	 * @see org.eclipse.ocl.ocl.State#getRedefinedState()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_RedefinedState();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.State#getSubmachines <em>Submachines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Submachines</em>'.
	 * @see org.eclipse.ocl.ocl.State#getSubmachines()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Submachines();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.StateExp <em>State Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Exp</em>'.
	 * @see org.eclipse.ocl.ocl.StateExp
	 * @generated
	 */
	EClass getStateExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.StateExp#getReferredState <em>Referred State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred State</em>'.
	 * @see org.eclipse.ocl.ocl.StateExp#getReferredState()
	 * @see #getStateExp()
	 * @generated
	 */
	EReference getStateExp_ReferredState();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.StateMachine <em>State Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State Machine</em>'.
	 * @see org.eclipse.ocl.ocl.StateMachine
	 * @generated
	 */
	EClass getStateMachine();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.StateMachine#getExtendedStateMachines <em>Extended State Machines</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Extended State Machines</em>'.
	 * @see org.eclipse.ocl.ocl.StateMachine#getExtendedStateMachines()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_ExtendedStateMachines();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.StateMachine#getOwnedConnectionPoints <em>Owned Connection Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Connection Points</em>'.
	 * @see org.eclipse.ocl.ocl.StateMachine#getOwnedConnectionPoints()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_OwnedConnectionPoints();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.StateMachine#getOwnedRegions <em>Owned Regions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Regions</em>'.
	 * @see org.eclipse.ocl.ocl.StateMachine#getOwnedRegions()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_OwnedRegions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.StateMachine#getSubmachineStates <em>Submachine States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Submachine States</em>'.
	 * @see org.eclipse.ocl.ocl.StateMachine#getSubmachineStates()
	 * @see #getStateMachine()
	 * @generated
	 */
	EReference getStateMachine_SubmachineStates();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Stereotype <em>Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stereotype</em>'.
	 * @see org.eclipse.ocl.ocl.Stereotype
	 * @generated
	 */
	EClass getStereotype();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Stereotype#getOwnedExtenders <em>Owned Extenders</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Extenders</em>'.
	 * @see org.eclipse.ocl.ocl.Stereotype#getOwnedExtenders()
	 * @see #getStereotype()
	 * @generated
	 */
	EReference getStereotype_OwnedExtenders();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.StereotypeExtender <em>Stereotype Extender</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Stereotype Extender</em>'.
	 * @see org.eclipse.ocl.ocl.StereotypeExtender
	 * @generated
	 */
	EClass getStereotypeExtender();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.StereotypeExtender#getClass_ <em>Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Class</em>'.
	 * @see org.eclipse.ocl.ocl.StereotypeExtender#getClass_()
	 * @see #getStereotypeExtender()
	 * @generated
	 */
	EReference getStereotypeExtender_Class();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.StereotypeExtender#getIsRequired <em>Is Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Required</em>'.
	 * @see org.eclipse.ocl.ocl.StereotypeExtender#getIsRequired()
	 * @see #getStereotypeExtender()
	 * @generated
	 */
	EAttribute getStereotypeExtender_IsRequired();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.StereotypeExtender#getOwningStereotype <em>Owning Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Stereotype</em>'.
	 * @see org.eclipse.ocl.ocl.StereotypeExtender#getOwningStereotype()
	 * @see #getStereotypeExtender()
	 * @generated
	 */
	EReference getStereotypeExtender_OwningStereotype();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.StringLiteralExp <em>String Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.StringLiteralExp
	 * @generated
	 */
	EClass getStringLiteralExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.StringLiteralExp#getStringSymbol <em>String Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Symbol</em>'.
	 * @see org.eclipse.ocl.ocl.StringLiteralExp#getStringSymbol()
	 * @see #getStringLiteralExp()
	 * @generated
	 */
	EAttribute getStringLiteralExp_StringSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TemplateBinding <em>Template Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Binding</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateBinding
	 * @generated
	 */
	EClass getTemplateBinding();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.TemplateBinding#getOwnedSubstitutions <em>Owned Substitutions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Substitutions</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateBinding#getOwnedSubstitutions()
	 * @see #getTemplateBinding()
	 * @generated
	 */
	EReference getTemplateBinding_OwnedSubstitutions();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.TemplateBinding#getOwningElement <em>Owning Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Element</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateBinding#getOwningElement()
	 * @see #getTemplateBinding()
	 * @generated
	 */
	EReference getTemplateBinding_OwningElement();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.TemplateBinding#getTemplateSignature <em>Template Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Template Signature</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateBinding#getTemplateSignature()
	 * @see #getTemplateBinding()
	 * @generated
	 */
	EReference getTemplateBinding_TemplateSignature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TemplateParameter <em>Template Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Parameter</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateParameter
	 * @generated
	 */
	EClass getTemplateParameter();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.TemplateParameter#getConstrainingClasses <em>Constraining Classes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Constraining Classes</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateParameter#getConstrainingClasses()
	 * @see #getTemplateParameter()
	 * @generated
	 */
	EReference getTemplateParameter_ConstrainingClasses();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.TemplateParameter#getOwningSignature <em>Owning Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Signature</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateParameter#getOwningSignature()
	 * @see #getTemplateParameter()
	 * @generated
	 */
	EReference getTemplateParameter_OwningSignature();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TemplateParameterSubstitution <em>Template Parameter Substitution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Parameter Substitution</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateParameterSubstitution
	 * @generated
	 */
	EClass getTemplateParameterSubstitution();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.TemplateParameterSubstitution#getActual <em>Actual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Actual</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateParameterSubstitution#getActual()
	 * @see #getTemplateParameterSubstitution()
	 * @generated
	 */
	EReference getTemplateParameterSubstitution_Actual();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.TemplateParameterSubstitution#getFormal <em>Formal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Formal</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateParameterSubstitution#getFormal()
	 * @see #getTemplateParameterSubstitution()
	 * @generated
	 */
	EReference getTemplateParameterSubstitution_Formal();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.TemplateParameterSubstitution#getOwnedWildcard <em>Owned Wildcard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Wildcard</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateParameterSubstitution#getOwnedWildcard()
	 * @see #getTemplateParameterSubstitution()
	 * @generated
	 */
	EReference getTemplateParameterSubstitution_OwnedWildcard();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.TemplateParameterSubstitution#getOwningBinding <em>Owning Binding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Binding</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateParameterSubstitution#getOwningBinding()
	 * @see #getTemplateParameterSubstitution()
	 * @generated
	 */
	EReference getTemplateParameterSubstitution_OwningBinding();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TemplateSignature <em>Template Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Template Signature</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateSignature
	 * @generated
	 */
	EClass getTemplateSignature();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.TemplateSignature#getOwnedParameters <em>Owned Parameters</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Parameters</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateSignature#getOwnedParameters()
	 * @see #getTemplateSignature()
	 * @generated
	 */
	EReference getTemplateSignature_OwnedParameters();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.TemplateSignature#getOwningElement <em>Owning Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Element</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateSignature#getOwningElement()
	 * @see #getTemplateSignature()
	 * @generated
	 */
	EReference getTemplateSignature_OwningElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TemplateableElement <em>Templateable Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Templateable Element</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateableElement
	 * @generated
	 */
	EClass getTemplateableElement();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.TemplateableElement#getOwnedBindings <em>Owned Bindings</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Bindings</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateableElement#getOwnedBindings()
	 * @see #getTemplateableElement()
	 * @generated
	 */
	EReference getTemplateableElement_OwnedBindings();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.TemplateableElement#getOwnedSignature <em>Owned Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Signature</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateableElement#getOwnedSignature()
	 * @see #getTemplateableElement()
	 * @generated
	 */
	EReference getTemplateableElement_OwnedSignature();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.TemplateableElement#getUnspecializedElement <em>Unspecialized Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Unspecialized Element</em>'.
	 * @see org.eclipse.ocl.ocl.TemplateableElement#getUnspecializedElement()
	 * @see #getTemplateableElement()
	 * @generated
	 */
	EReference getTemplateableElement_UnspecializedElement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Transition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transition</em>'.
	 * @see org.eclipse.ocl.ocl.Transition
	 * @generated
	 */
	EClass getTransition();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Transition#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.eclipse.ocl.ocl.Transition#getKind()
	 * @see #getTransition()
	 * @generated
	 */
	EAttribute getTransition_Kind();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.Transition#getOwnedEffect <em>Owned Effect</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Effect</em>'.
	 * @see org.eclipse.ocl.ocl.Transition#getOwnedEffect()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_OwnedEffect();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.Transition#getOwnedGuard <em>Owned Guard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Guard</em>'.
	 * @see org.eclipse.ocl.ocl.Transition#getOwnedGuard()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_OwnedGuard();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.Transition#getOwnedTriggers <em>Owned Triggers</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Triggers</em>'.
	 * @see org.eclipse.ocl.ocl.Transition#getOwnedTriggers()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_OwnedTriggers();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Transition#getOwningRegion <em>Owning Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Region</em>'.
	 * @see org.eclipse.ocl.ocl.Transition#getOwningRegion()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_OwningRegion();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Transition#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see org.eclipse.ocl.ocl.Transition#getSource()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Source();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Transition#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see org.eclipse.ocl.ocl.Transition#getTarget()
	 * @see #getTransition()
	 * @generated
	 */
	EReference getTransition_Target();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Trigger <em>Trigger</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Trigger</em>'.
	 * @see org.eclipse.ocl.ocl.Trigger
	 * @generated
	 */
	EClass getTrigger();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Trigger#getOwningState <em>Owning State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning State</em>'.
	 * @see org.eclipse.ocl.ocl.Trigger#getOwningState()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_OwningState();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Trigger#getOwningTransition <em>Owning Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Transition</em>'.
	 * @see org.eclipse.ocl.ocl.Trigger#getOwningTransition()
	 * @see #getTrigger()
	 * @generated
	 */
	EReference getTrigger_OwningTransition();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TupleLiteralExp <em>Tuple Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.TupleLiteralExp
	 * @generated
	 */
	EClass getTupleLiteralExp();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.ocl.TupleLiteralExp#getOwnedParts <em>Owned Parts</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Owned Parts</em>'.
	 * @see org.eclipse.ocl.ocl.TupleLiteralExp#getOwnedParts()
	 * @see #getTupleLiteralExp()
	 * @generated
	 */
	EReference getTupleLiteralExp_OwnedParts();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TupleLiteralPart <em>Tuple Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Literal Part</em>'.
	 * @see org.eclipse.ocl.ocl.TupleLiteralPart
	 * @generated
	 */
	EClass getTupleLiteralPart();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.TupleLiteralPart#getOwnedInit <em>Owned Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Init</em>'.
	 * @see org.eclipse.ocl.ocl.TupleLiteralPart#getOwnedInit()
	 * @see #getTupleLiteralPart()
	 * @generated
	 */
	EReference getTupleLiteralPart_OwnedInit();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Type</em>'.
	 * @see org.eclipse.ocl.ocl.TupleType
	 * @generated
	 */
	EClass getTupleType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Type <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type</em>'.
	 * @see org.eclipse.ocl.ocl.Type
	 * @generated
	 */
	EClass getType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TypeExp <em>Type Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Exp</em>'.
	 * @see org.eclipse.ocl.ocl.TypeExp
	 * @generated
	 */
	EClass getTypeExp();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.TypeExp#getReferredType <em>Referred Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Type</em>'.
	 * @see org.eclipse.ocl.ocl.TypeExp#getReferredType()
	 * @see #getTypeExp()
	 * @generated
	 */
	EReference getTypeExp_ReferredType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Typed Element</em>'.
	 * @see org.eclipse.ocl.ocl.TypedElement
	 * @generated
	 */
	EClass getTypedElement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.TypedElement#getIsMany <em>Is Many</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Many</em>'.
	 * @see org.eclipse.ocl.ocl.TypedElement#getIsMany()
	 * @see #getTypedElement()
	 * @generated
	 */
	EAttribute getTypedElement_IsMany();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.TypedElement#getIsRequired <em>Is Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Required</em>'.
	 * @see org.eclipse.ocl.ocl.TypedElement#getIsRequired()
	 * @see #getTypedElement()
	 * @generated
	 */
	EAttribute getTypedElement_IsRequired();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.TypedElement#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.ocl.ocl.TypedElement#getType()
	 * @see #getTypedElement()
	 * @generated
	 */
	EReference getTypedElement_Type();

	/**
	 * Returns the meta object for class '{@link java.util.Collection <em>Unique Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unique Collection</em>'.
	 * @see java.util.Collection
	 * @generated
	 */
	EClass getUniqueCollection();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.UnlimitedNaturalLiteralExp <em>Unlimited Natural Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unlimited Natural Literal Exp</em>'.
	 * @see org.eclipse.ocl.ocl.UnlimitedNaturalLiteralExp
	 * @generated
	 */
	EClass getUnlimitedNaturalLiteralExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.UnlimitedNaturalLiteralExp#getUnlimitedNaturalSymbol <em>Unlimited Natural Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Unlimited Natural Symbol</em>'.
	 * @see org.eclipse.ocl.ocl.UnlimitedNaturalLiteralExp#getUnlimitedNaturalSymbol()
	 * @see #getUnlimitedNaturalLiteralExp()
	 * @generated
	 */
	EAttribute getUnlimitedNaturalLiteralExp_UnlimitedNaturalSymbol();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.UnspecifiedValueExp <em>Unspecified Value Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unspecified Value Exp</em>'.
	 * @see org.eclipse.ocl.ocl.UnspecifiedValueExp
	 * @generated
	 */
	EClass getUnspecifiedValueExp();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.ValueSpecification <em>Value Specification</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Specification</em>'.
	 * @see org.eclipse.ocl.ocl.ValueSpecification
	 * @generated
	 */
	EClass getValueSpecification();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see org.eclipse.ocl.ocl.Variable
	 * @generated
	 */
	EClass getVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.Variable#getIsImplicit <em>Is Implicit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Implicit</em>'.
	 * @see org.eclipse.ocl.ocl.Variable#getIsImplicit()
	 * @see #getVariable()
	 * @generated
	 */
	EAttribute getVariable_IsImplicit();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.ocl.ocl.Variable#getOwnedInit <em>Owned Init</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Owned Init</em>'.
	 * @see org.eclipse.ocl.ocl.Variable#getOwnedInit()
	 * @see #getVariable()
	 * @generated
	 */
	EReference getVariable_OwnedInit();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.Variable#getRepresentedParameter <em>Represented Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Represented Parameter</em>'.
	 * @see org.eclipse.ocl.ocl.Variable#getRepresentedParameter()
	 * @see #getVariable()
	 * @generated
	 */
	EReference getVariable_RepresentedParameter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.VariableDeclaration <em>Variable Declaration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Declaration</em>'.
	 * @see org.eclipse.ocl.ocl.VariableDeclaration
	 * @generated
	 */
	EClass getVariableDeclaration();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.VariableDeclaration#getTypeValue <em>Type Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type Value</em>'.
	 * @see org.eclipse.ocl.ocl.VariableDeclaration#getTypeValue()
	 * @see #getVariableDeclaration()
	 * @generated
	 */
	EReference getVariableDeclaration_TypeValue();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.VariableExp <em>Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Exp</em>'.
	 * @see org.eclipse.ocl.ocl.VariableExp
	 * @generated
	 */
	EClass getVariableExp();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.ocl.VariableExp#getIsImplicit <em>Is Implicit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Implicit</em>'.
	 * @see org.eclipse.ocl.ocl.VariableExp#getIsImplicit()
	 * @see #getVariableExp()
	 * @generated
	 */
	EAttribute getVariableExp_IsImplicit();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.VariableExp#getReferredVariable <em>Referred Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Variable</em>'.
	 * @see org.eclipse.ocl.ocl.VariableExp#getReferredVariable()
	 * @see #getVariableExp()
	 * @generated
	 */
	EReference getVariableExp_ReferredVariable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.Vertex <em>Vertex</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vertex</em>'.
	 * @see org.eclipse.ocl.ocl.Vertex
	 * @generated
	 */
	EClass getVertex();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Vertex#getIncomingTransitions <em>Incoming Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Incoming Transitions</em>'.
	 * @see org.eclipse.ocl.ocl.Vertex#getIncomingTransitions()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_IncomingTransitions();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.ocl.Vertex#getOutgoingTransitions <em>Outgoing Transitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Outgoing Transitions</em>'.
	 * @see org.eclipse.ocl.ocl.Vertex#getOutgoingTransitions()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_OutgoingTransitions();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.ocl.Vertex#getOwningRegion <em>Owning Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Owning Region</em>'.
	 * @see org.eclipse.ocl.ocl.Vertex#getOwningRegion()
	 * @see #getVertex()
	 * @generated
	 */
	EReference getVertex_OwningRegion();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.pivot.util.Visitable <em>Visitable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Visitable</em>'.
	 * @see org.eclipse.ocl.pivot.util.Visitable
	 * @generated
	 */
	EClass getVisitable();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.VoidType <em>Void Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Void Type</em>'.
	 * @see org.eclipse.ocl.ocl.VoidType
	 * @generated
	 */
	EClass getVoidType();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.ocl.WildcardType <em>Wildcard Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Wildcard Type</em>'.
	 * @see org.eclipse.ocl.ocl.WildcardType
	 * @generated
	 */
	EClass getWildcardType();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.WildcardType#getLowerBound <em>Lower Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Lower Bound</em>'.
	 * @see org.eclipse.ocl.ocl.WildcardType#getLowerBound()
	 * @see #getWildcardType()
	 * @generated
	 */
	EReference getWildcardType_LowerBound();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.ocl.WildcardType#getUpperBound <em>Upper Bound</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Upper Bound</em>'.
	 * @see org.eclipse.ocl.ocl.WildcardType#getUpperBound()
	 * @see #getWildcardType()
	 * @generated
	 */
	EReference getWildcardType_UpperBound();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.ocl.ocl.AssociativityKind <em>Associativity Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Associativity Kind</em>'.
	 * @see org.eclipse.ocl.ocl.AssociativityKind
	 * @generated
	 */
	EEnum getAssociativityKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.ocl.ocl.CollectionKind <em>Collection Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Collection Kind</em>'.
	 * @see org.eclipse.ocl.ocl.CollectionKind
	 * @generated
	 */
	EEnum getCollectionKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.ocl.ocl.PseudostateKind <em>Pseudostate Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Pseudostate Kind</em>'.
	 * @see org.eclipse.ocl.ocl.PseudostateKind
	 * @generated
	 */
	EEnum getPseudostateKind();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.ocl.ocl.TransitionKind <em>Transition Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Transition Kind</em>'.
	 * @see org.eclipse.ocl.ocl.TransitionKind
	 * @generated
	 */
	EEnum getTransitionKind();

	/**
	 * Returns the meta object for data type '{@link java.lang.Boolean <em>Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Boolean</em>'.
	 * @see java.lang.Boolean
	 * @generated
	 */
	EDataType getBoolean();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.ecore.EObject <em>Ecore Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Ecore Object</em>'.
	 * @see org.eclipse.emf.ecore.EObject
	 * @generated
	 */
	EDataType getEcoreObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.values.IntegerValue <em>Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Integer</em>'.
	 * @see org.eclipse.ocl.pivot.values.IntegerValue
	 * @generated
	 */
	EDataType getInteger();

	/**
	 * Returns the meta object for data type '{@link java.lang.Class <em>Java Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Java Class</em>'.
	 * @see java.lang.Class
	 * @generated
	 */
	EDataType getJavaClass();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.library.LibraryFeature <em>Library Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Library Feature</em>'.
	 * @see org.eclipse.ocl.pivot.library.LibraryFeature
	 * @generated
	 */
	EDataType getLibraryFeature();

	/**
	 * Returns the meta object for data type '{@link java.lang.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Object</em>'.
	 * @see java.lang.Object
	 * @generated
	 */
	EDataType getObject();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.values.RealValue <em>Real</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Real</em>'.
	 * @see org.eclipse.ocl.pivot.values.RealValue
	 * @generated
	 */
	EDataType getReal();

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>String</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	EDataType getString();

	/**
	 * Returns the meta object for data type '{@link java.lang.Throwable <em>Throwable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Throwable</em>'.
	 * @see java.lang.Throwable
	 * @generated
	 */
	EDataType getThrowable();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.ocl.pivot.values.UnlimitedNaturalValue <em>Unlimited Natural</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Unlimited Natural</em>'.
	 * @see org.eclipse.ocl.pivot.values.UnlimitedNaturalValue
	 * @generated
	 */
	EDataType getUnlimitedNatural();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OCLASFactory getOCLASFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.AnnotationImpl <em>Annotation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.AnnotationImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getAnnotation()
		 * @generated
		 */
		EClass ANNOTATION = eINSTANCE.getAnnotation();

		/**
		 * The meta object literal for the '<em><b>Owned Contents</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__OWNED_CONTENTS = eINSTANCE.getAnnotation_OwnedContents();

		/**
		 * The meta object literal for the '<em><b>Owned Details</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__OWNED_DETAILS = eINSTANCE.getAnnotation_OwnedDetails();

		/**
		 * The meta object literal for the '<em><b>References</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ANNOTATION__REFERENCES = eINSTANCE.getAnnotation_References();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.AnyTypeImpl <em>Any Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.AnyTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getAnyType()
		 * @generated
		 */
		EClass ANY_TYPE = eINSTANCE.getAnyType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.AssociationClassImpl <em>Association Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.AssociationClassImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getAssociationClass()
		 * @generated
		 */
		EClass ASSOCIATION_CLASS = eINSTANCE.getAssociationClass();

		/**
		 * The meta object literal for the '<em><b>Unowned Attributes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_CLASS__UNOWNED_ATTRIBUTES = eINSTANCE.getAssociationClass_UnownedAttributes();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.AssociationClassCallExpImpl <em>Association Class Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.AssociationClassCallExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getAssociationClassCallExp()
		 * @generated
		 */
		EClass ASSOCIATION_CLASS_CALL_EXP = eINSTANCE.getAssociationClassCallExp();

		/**
		 * The meta object literal for the '<em><b>Referred Association Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ASSOCIATION_CLASS_CALL_EXP__REFERRED_ASSOCIATION_CLASS = eINSTANCE.getAssociationClassCallExp_ReferredAssociationClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.Bag <em>Bag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.Bag
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getBag()
		 * @generated
		 */
		EClass BAG = eINSTANCE.getBag();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.BagTypeImpl <em>Bag Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.BagTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getBagType()
		 * @generated
		 */
		EClass BAG_TYPE = eINSTANCE.getBagType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.BehaviorImpl <em>Behavior</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.BehaviorImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getBehavior()
		 * @generated
		 */
		EClass BEHAVIOR = eINSTANCE.getBehavior();

		/**
		 * The meta object literal for the '<em><b>Owning Transition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOR__OWNING_TRANSITION = eINSTANCE.getBehavior_OwningTransition();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.BooleanLiteralExpImpl <em>Boolean Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.BooleanLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getBooleanLiteralExp()
		 * @generated
		 */
		EClass BOOLEAN_LITERAL_EXP = eINSTANCE.getBooleanLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Boolean Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL = eINSTANCE.getBooleanLiteralExp_BooleanSymbol();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.BooleanTypeImpl <em>Boolean Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.BooleanTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getBooleanType()
		 * @generated
		 */
		EClass BOOLEAN_TYPE = eINSTANCE.getBooleanType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CallExpImpl <em>Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CallExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCallExp()
		 * @generated
		 */
		EClass CALL_EXP = eINSTANCE.getCallExp();

		/**
		 * The meta object literal for the '<em><b>Is Implicit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALL_EXP__IS_IMPLICIT = eINSTANCE.getCallExp_IsImplicit();

		/**
		 * The meta object literal for the '<em><b>Is Safe</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALL_EXP__IS_SAFE = eINSTANCE.getCallExp_IsSafe();

		/**
		 * The meta object literal for the '<em><b>Owned Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_EXP__OWNED_SOURCE = eINSTANCE.getCallExp_OwnedSource();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CallOperationActionImpl <em>Call Operation Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CallOperationActionImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCallOperationAction()
		 * @generated
		 */
		EClass CALL_OPERATION_ACTION = eINSTANCE.getCallOperationAction();

		/**
		 * The meta object literal for the '<em><b>Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CALL_OPERATION_ACTION__OPERATION = eINSTANCE.getCallOperationAction_Operation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ClassImpl <em>Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ClassImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getClass_()
		 * @generated
		 */
		EClass CLASS = eINSTANCE.getClass_();

		/**
		 * The meta object literal for the '<em><b>Extenders</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__EXTENDERS = eINSTANCE.getClass_Extenders();

		/**
		 * The meta object literal for the '<em><b>Instance Class Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS__INSTANCE_CLASS_NAME = eINSTANCE.getClass_InstanceClassName();

		/**
		 * The meta object literal for the '<em><b>Is Abstract</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS__IS_ABSTRACT = eINSTANCE.getClass_IsAbstract();

		/**
		 * The meta object literal for the '<em><b>Is Active</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS__IS_ACTIVE = eINSTANCE.getClass_IsActive();

		/**
		 * The meta object literal for the '<em><b>Is Interface</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASS__IS_INTERFACE = eINSTANCE.getClass_IsInterface();

		/**
		 * The meta object literal for the '<em><b>Owned Behaviors</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__OWNED_BEHAVIORS = eINSTANCE.getClass_OwnedBehaviors();

		/**
		 * The meta object literal for the '<em><b>Owned Invariants</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__OWNED_INVARIANTS = eINSTANCE.getClass_OwnedInvariants();

		/**
		 * The meta object literal for the '<em><b>Owned Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__OWNED_OPERATIONS = eINSTANCE.getClass_OwnedOperations();

		/**
		 * The meta object literal for the '<em><b>Owned Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__OWNED_PROPERTIES = eINSTANCE.getClass_OwnedProperties();

		/**
		 * The meta object literal for the '<em><b>Owning Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__OWNING_PACKAGE = eINSTANCE.getClass_OwningPackage();

		/**
		 * The meta object literal for the '<em><b>Super Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASS__SUPER_CLASSES = eINSTANCE.getClass_SuperClasses();

		/**
		 * The meta object literal for the '{@link java.util.Collection <em>Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Collection
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCollection()
		 * @generated
		 */
		EClass COLLECTION = eINSTANCE.getCollection();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION__ELEMENT_TYPE = eINSTANCE.getCollection_ElementType();

		/**
		 * The meta object literal for the '<em><b>Lower</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION__LOWER = eINSTANCE.getCollection_Lower();

		/**
		 * The meta object literal for the '<em><b>Upper</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION__UPPER = eINSTANCE.getCollection_Upper();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CollectionItemImpl <em>Collection Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CollectionItemImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCollectionItem()
		 * @generated
		 */
		EClass COLLECTION_ITEM = eINSTANCE.getCollectionItem();

		/**
		 * The meta object literal for the '<em><b>Owned Item</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_ITEM__OWNED_ITEM = eINSTANCE.getCollectionItem_OwnedItem();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CollectionLiteralExpImpl <em>Collection Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CollectionLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCollectionLiteralExp()
		 * @generated
		 */
		EClass COLLECTION_LITERAL_EXP = eINSTANCE.getCollectionLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_LITERAL_EXP__KIND = eINSTANCE.getCollectionLiteralExp_Kind();

		/**
		 * The meta object literal for the '<em><b>Owned Parts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_LITERAL_EXP__OWNED_PARTS = eINSTANCE.getCollectionLiteralExp_OwnedParts();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CollectionLiteralPartImpl <em>Collection Literal Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CollectionLiteralPartImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCollectionLiteralPart()
		 * @generated
		 */
		EClass COLLECTION_LITERAL_PART = eINSTANCE.getCollectionLiteralPart();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CollectionRangeImpl <em>Collection Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CollectionRangeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCollectionRange()
		 * @generated
		 */
		EClass COLLECTION_RANGE = eINSTANCE.getCollectionRange();

		/**
		 * The meta object literal for the '<em><b>Owned First</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_RANGE__OWNED_FIRST = eINSTANCE.getCollectionRange_OwnedFirst();

		/**
		 * The meta object literal for the '<em><b>Owned Last</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_RANGE__OWNED_LAST = eINSTANCE.getCollectionRange_OwnedLast();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CollectionTypeImpl <em>Collection Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CollectionTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCollectionType()
		 * @generated
		 */
		EClass COLLECTION_TYPE = eINSTANCE.getCollectionType();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COLLECTION_TYPE__ELEMENT_TYPE = eINSTANCE.getCollectionType_ElementType();

		/**
		 * The meta object literal for the '<em><b>Is Null Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_TYPE__IS_NULL_FREE = eINSTANCE.getCollectionType_IsNullFree();

		/**
		 * The meta object literal for the '<em><b>Lower</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_TYPE__LOWER = eINSTANCE.getCollectionType_Lower();

		/**
		 * The meta object literal for the '<em><b>Upper</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COLLECTION_TYPE__UPPER = eINSTANCE.getCollectionType_Upper();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CommentImpl <em>Comment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CommentImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getComment()
		 * @generated
		 */
		EClass COMMENT = eINSTANCE.getComment();

		/**
		 * The meta object literal for the '<em><b>Annotated Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMENT__ANNOTATED_ELEMENTS = eINSTANCE.getComment_AnnotatedElements();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMMENT__BODY = eINSTANCE.getComment_Body();

		/**
		 * The meta object literal for the '<em><b>Owning Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMMENT__OWNING_ELEMENT = eINSTANCE.getComment_OwningElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CompleteClassImpl <em>Complete Class</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CompleteClassImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCompleteClass()
		 * @generated
		 */
		EClass COMPLETE_CLASS = eINSTANCE.getCompleteClass();

		/**
		 * The meta object literal for the '<em><b>Owning Complete Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_CLASS__OWNING_COMPLETE_PACKAGE = eINSTANCE.getCompleteClass_OwningCompletePackage();

		/**
		 * The meta object literal for the '<em><b>Partial Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_CLASS__PARTIAL_CLASSES = eINSTANCE.getCompleteClass_PartialClasses();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CompleteEnvironmentImpl <em>Complete Environment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CompleteEnvironmentImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCompleteEnvironment()
		 * @generated
		 */
		EClass COMPLETE_ENVIRONMENT = eINSTANCE.getCompleteEnvironment();

		/**
		 * The meta object literal for the '<em><b>Owned Complete Model</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_ENVIRONMENT__OWNED_COMPLETE_MODEL = eINSTANCE.getCompleteEnvironment_OwnedCompleteModel();

		/**
		 * The meta object literal for the '<em><b>Owned Standard Library</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_ENVIRONMENT__OWNED_STANDARD_LIBRARY = eINSTANCE.getCompleteEnvironment_OwnedStandardLibrary();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CompleteModelImpl <em>Complete Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CompleteModelImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCompleteModel()
		 * @generated
		 */
		EClass COMPLETE_MODEL = eINSTANCE.getCompleteModel();

		/**
		 * The meta object literal for the '<em><b>Orphan Complete Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_MODEL__ORPHAN_COMPLETE_PACKAGE = eINSTANCE.getCompleteModel_OrphanCompletePackage();

		/**
		 * The meta object literal for the '<em><b>Owned Complete Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_MODEL__OWNED_COMPLETE_PACKAGES = eINSTANCE.getCompleteModel_OwnedCompletePackages();

		/**
		 * The meta object literal for the '<em><b>Owning Complete Environment</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_MODEL__OWNING_COMPLETE_ENVIRONMENT = eINSTANCE.getCompleteModel_OwningCompleteEnvironment();

		/**
		 * The meta object literal for the '<em><b>Partial Models</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_MODEL__PARTIAL_MODELS = eINSTANCE.getCompleteModel_PartialModels();

		/**
		 * The meta object literal for the '<em><b>Primitive Complete Package</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_MODEL__PRIMITIVE_COMPLETE_PACKAGE = eINSTANCE.getCompleteModel_PrimitiveCompletePackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.CompletePackageImpl <em>Complete Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.CompletePackageImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCompletePackage()
		 * @generated
		 */
		EClass COMPLETE_PACKAGE = eINSTANCE.getCompletePackage();

		/**
		 * The meta object literal for the '<em><b>Owned Complete Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_PACKAGE__OWNED_COMPLETE_CLASSES = eINSTANCE.getCompletePackage_OwnedCompleteClasses();

		/**
		 * The meta object literal for the '<em><b>Owned Complete Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_PACKAGE__OWNED_COMPLETE_PACKAGES = eINSTANCE.getCompletePackage_OwnedCompletePackages();

		/**
		 * The meta object literal for the '<em><b>Owning Complete Model</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_PACKAGE__OWNING_COMPLETE_MODEL = eINSTANCE.getCompletePackage_OwningCompleteModel();

		/**
		 * The meta object literal for the '<em><b>Owning Complete Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_PACKAGE__OWNING_COMPLETE_PACKAGE = eINSTANCE.getCompletePackage_OwningCompletePackage();

		/**
		 * The meta object literal for the '<em><b>Partial Packages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPLETE_PACKAGE__PARTIAL_PACKAGES = eINSTANCE.getCompletePackage_PartialPackages();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ConnectionPointReferenceImpl <em>Connection Point Reference</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ConnectionPointReferenceImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getConnectionPointReference()
		 * @generated
		 */
		EClass CONNECTION_POINT_REFERENCE = eINSTANCE.getConnectionPointReference();

		/**
		 * The meta object literal for the '<em><b>Entries</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_POINT_REFERENCE__ENTRIES = eINSTANCE.getConnectionPointReference_Entries();

		/**
		 * The meta object literal for the '<em><b>Exits</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_POINT_REFERENCE__EXITS = eINSTANCE.getConnectionPointReference_Exits();

		/**
		 * The meta object literal for the '<em><b>Owning State</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONNECTION_POINT_REFERENCE__OWNING_STATE = eINSTANCE.getConnectionPointReference_OwningState();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ConstraintImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '<em><b>Constrained Elements</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__CONSTRAINED_ELEMENTS = eINSTANCE.getConstraint_ConstrainedElements();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__CONTEXT = eINSTANCE.getConstraint_Context();

		/**
		 * The meta object literal for the '<em><b>Is Callable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONSTRAINT__IS_CALLABLE = eINSTANCE.getConstraint_IsCallable();

		/**
		 * The meta object literal for the '<em><b>Owned Specification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__OWNED_SPECIFICATION = eINSTANCE.getConstraint_OwnedSpecification();

		/**
		 * The meta object literal for the '<em><b>Owning Post Context</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__OWNING_POST_CONTEXT = eINSTANCE.getConstraint_OwningPostContext();

		/**
		 * The meta object literal for the '<em><b>Owning Pre Context</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__OWNING_PRE_CONTEXT = eINSTANCE.getConstraint_OwningPreContext();

		/**
		 * The meta object literal for the '<em><b>Owning State</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__OWNING_STATE = eINSTANCE.getConstraint_OwningState();

		/**
		 * The meta object literal for the '<em><b>Owning Transition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__OWNING_TRANSITION = eINSTANCE.getConstraint_OwningTransition();

		/**
		 * The meta object literal for the '<em><b>Redefined Constraints</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__REDEFINED_CONSTRAINTS = eINSTANCE.getConstraint_RedefinedConstraints();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.DataTypeImpl <em>Data Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.DataTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getDataType()
		 * @generated
		 */
		EClass DATA_TYPE = eINSTANCE.getDataType();

		/**
		 * The meta object literal for the '<em><b>Behavioral Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_TYPE__BEHAVIORAL_CLASS = eINSTANCE.getDataType_BehavioralClass();

		/**
		 * The meta object literal for the '<em><b>Is Serializable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_TYPE__IS_SERIALIZABLE = eINSTANCE.getDataType_IsSerializable();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_TYPE__VALUE = eINSTANCE.getDataType_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.DetailImpl <em>Detail</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.DetailImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getDetail()
		 * @generated
		 */
		EClass DETAIL = eINSTANCE.getDetail();

		/**
		 * The meta object literal for the '<em><b>Values</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DETAIL__VALUES = eINSTANCE.getDetail_Values();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.DynamicBehaviorImpl <em>Dynamic Behavior</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.DynamicBehaviorImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getDynamicBehavior()
		 * @generated
		 */
		EClass DYNAMIC_BEHAVIOR = eINSTANCE.getDynamicBehavior();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.DynamicElementImpl <em>Dynamic Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.DynamicElementImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getDynamicElement()
		 * @generated
		 */
		EClass DYNAMIC_ELEMENT = eINSTANCE.getDynamicElement();

		/**
		 * The meta object literal for the '<em><b>Meta Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_ELEMENT__META_TYPE = eINSTANCE.getDynamicElement_MetaType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.DynamicPropertyImpl <em>Dynamic Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.DynamicPropertyImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getDynamicProperty()
		 * @generated
		 */
		EClass DYNAMIC_PROPERTY = eINSTANCE.getDynamicProperty();

		/**
		 * The meta object literal for the '<em><b>Default</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DYNAMIC_PROPERTY__DEFAULT = eINSTANCE.getDynamicProperty_Default();

		/**
		 * The meta object literal for the '<em><b>Referred Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_PROPERTY__REFERRED_PROPERTY = eINSTANCE.getDynamicProperty_ReferredProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.DynamicTypeImpl <em>Dynamic Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.DynamicTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getDynamicType()
		 * @generated
		 */
		EClass DYNAMIC_TYPE = eINSTANCE.getDynamicType();

		/**
		 * The meta object literal for the '<em><b>Owned Dynamic Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DYNAMIC_TYPE__OWNED_DYNAMIC_PROPERTIES = eINSTANCE.getDynamicType_OwnedDynamicProperties();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.DynamicValueSpecificationImpl <em>Dynamic Value Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.DynamicValueSpecificationImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getDynamicValueSpecification()
		 * @generated
		 */
		EClass DYNAMIC_VALUE_SPECIFICATION = eINSTANCE.getDynamicValueSpecification();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ElementImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em><b>Annotating Comments</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT__ANNOTATING_COMMENTS = eINSTANCE.getElement_AnnotatingComments();

		/**
		 * The meta object literal for the '<em><b>Owned Annotations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT__OWNED_ANNOTATIONS = eINSTANCE.getElement_OwnedAnnotations();

		/**
		 * The meta object literal for the '<em><b>Owned Comments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT__OWNED_COMMENTS = eINSTANCE.getElement_OwnedComments();

		/**
		 * The meta object literal for the '<em><b>Owned Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT__OWNED_EXTENSIONS = eINSTANCE.getElement_OwnedExtensions();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ElementExtensionImpl <em>Element Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ElementExtensionImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getElementExtension()
		 * @generated
		 */
		EClass ELEMENT_EXTENSION = eINSTANCE.getElementExtension();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_EXTENSION__BASE = eINSTANCE.getElementExtension_Base();

		/**
		 * The meta object literal for the '<em><b>Is Applied</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT_EXTENSION__IS_APPLIED = eINSTANCE.getElementExtension_IsApplied();

		/**
		 * The meta object literal for the '<em><b>Is Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT_EXTENSION__IS_REQUIRED = eINSTANCE.getElementExtension_IsRequired();

		/**
		 * The meta object literal for the '<em><b>Stereotype</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ELEMENT_EXTENSION__STEREOTYPE = eINSTANCE.getElementExtension_Stereotype();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ElementLiteralExpImpl <em>Element Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ElementLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getElementLiteralExp()
		 * @generated
		 */
		EClass ELEMENT_LITERAL_EXP = eINSTANCE.getElementLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Referred Element</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT_LITERAL_EXP__REFERRED_ELEMENT = eINSTANCE.getElementLiteralExp_ReferredElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.EnumLiteralExpImpl <em>Enum Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.EnumLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getEnumLiteralExp()
		 * @generated
		 */
		EClass ENUM_LITERAL_EXP = eINSTANCE.getEnumLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Referred Literal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUM_LITERAL_EXP__REFERRED_LITERAL = eINSTANCE.getEnumLiteralExp_ReferredLiteral();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.EnumerationImpl <em>Enumeration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.EnumerationImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getEnumeration()
		 * @generated
		 */
		EClass ENUMERATION = eINSTANCE.getEnumeration();

		/**
		 * The meta object literal for the '<em><b>Owned Literals</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION__OWNED_LITERALS = eINSTANCE.getEnumeration_OwnedLiterals();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.EnumerationLiteralImpl <em>Enumeration Literal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.EnumerationLiteralImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getEnumerationLiteral()
		 * @generated
		 */
		EClass ENUMERATION_LITERAL = eINSTANCE.getEnumerationLiteral();

		/**
		 * The meta object literal for the '<em><b>Literal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUMERATION_LITERAL__LITERAL = eINSTANCE.getEnumerationLiteral_Literal();

		/**
		 * The meta object literal for the '<em><b>Owning Enumeration</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENUMERATION_LITERAL__OWNING_ENUMERATION = eINSTANCE.getEnumerationLiteral_OwningEnumeration();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENUMERATION_LITERAL__VALUE = eINSTANCE.getEnumerationLiteral_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ExpressionInOCLImpl <em>Expression In OCL</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ExpressionInOCLImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getExpressionInOCL()
		 * @generated
		 */
		EClass EXPRESSION_IN_OCL = eINSTANCE.getExpressionInOCL();

		/**
		 * The meta object literal for the '<em><b>Owned Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_IN_OCL__OWNED_BODY = eINSTANCE.getExpressionInOCL_OwnedBody();

		/**
		 * The meta object literal for the '<em><b>Owned Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_IN_OCL__OWNED_CONTEXT = eINSTANCE.getExpressionInOCL_OwnedContext();

		/**
		 * The meta object literal for the '<em><b>Owned Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_IN_OCL__OWNED_PARAMETERS = eINSTANCE.getExpressionInOCL_OwnedParameters();

		/**
		 * The meta object literal for the '<em><b>Owned Result</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION_IN_OCL__OWNED_RESULT = eINSTANCE.getExpressionInOCL_OwnedResult();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.FeatureImpl <em>Feature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.FeatureImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
		 * The meta object literal for the '<em><b>Implementation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__IMPLEMENTATION = eINSTANCE.getFeature_Implementation();

		/**
		 * The meta object literal for the '<em><b>Implementation Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__IMPLEMENTATION_CLASS = eINSTANCE.getFeature_ImplementationClass();

		/**
		 * The meta object literal for the '<em><b>Is Static</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE__IS_STATIC = eINSTANCE.getFeature_IsStatic();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.FeatureCallExpImpl <em>Feature Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.FeatureCallExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getFeatureCallExp()
		 * @generated
		 */
		EClass FEATURE_CALL_EXP = eINSTANCE.getFeatureCallExp();

		/**
		 * The meta object literal for the '<em><b>Is Pre</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_CALL_EXP__IS_PRE = eINSTANCE.getFeatureCallExp_IsPre();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.FinalStateImpl <em>Final State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.FinalStateImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getFinalState()
		 * @generated
		 */
		EClass FINAL_STATE = eINSTANCE.getFinalState();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.IfExpImpl <em>If Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.IfExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getIfExp()
		 * @generated
		 */
		EClass IF_EXP = eINSTANCE.getIfExp();

		/**
		 * The meta object literal for the '<em><b>Is Else If</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IF_EXP__IS_ELSE_IF = eINSTANCE.getIfExp_IsElseIf();

		/**
		 * The meta object literal for the '<em><b>Owned Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXP__OWNED_CONDITION = eINSTANCE.getIfExp_OwnedCondition();

		/**
		 * The meta object literal for the '<em><b>Owned Else</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXP__OWNED_ELSE = eINSTANCE.getIfExp_OwnedElse();

		/**
		 * The meta object literal for the '<em><b>Owned Then</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IF_EXP__OWNED_THEN = eINSTANCE.getIfExp_OwnedThen();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ImportImpl <em>Import</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ImportImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getImport()
		 * @generated
		 */
		EClass IMPORT = eINSTANCE.getImport();

		/**
		 * The meta object literal for the '<em><b>Imported Namespace</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IMPORT__IMPORTED_NAMESPACE = eINSTANCE.getImport_ImportedNamespace();

		/**
		 * The meta object literal for the '<em><b>Xmiid Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IMPORT__XMIID_VERSION = eINSTANCE.getImport_XmiidVersion();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.InstanceSpecificationImpl <em>Instance Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.InstanceSpecificationImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getInstanceSpecification()
		 * @generated
		 */
		EClass INSTANCE_SPECIFICATION = eINSTANCE.getInstanceSpecification();

		/**
		 * The meta object literal for the '<em><b>Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_SPECIFICATION__CLASSES = eINSTANCE.getInstanceSpecification_Classes();

		/**
		 * The meta object literal for the '<em><b>Owned Slots</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_SPECIFICATION__OWNED_SLOTS = eINSTANCE.getInstanceSpecification_OwnedSlots();

		/**
		 * The meta object literal for the '<em><b>Owned Specification</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_SPECIFICATION__OWNED_SPECIFICATION = eINSTANCE.getInstanceSpecification_OwnedSpecification();

		/**
		 * The meta object literal for the '<em><b>Owning Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INSTANCE_SPECIFICATION__OWNING_PACKAGE = eINSTANCE.getInstanceSpecification_OwningPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.IntegerLiteralExpImpl <em>Integer Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.IntegerLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getIntegerLiteralExp()
		 * @generated
		 */
		EClass INTEGER_LITERAL_EXP = eINSTANCE.getIntegerLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Integer Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTEGER_LITERAL_EXP__INTEGER_SYMBOL = eINSTANCE.getIntegerLiteralExp_IntegerSymbol();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.InvalidLiteralExpImpl <em>Invalid Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.InvalidLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getInvalidLiteralExp()
		 * @generated
		 */
		EClass INVALID_LITERAL_EXP = eINSTANCE.getInvalidLiteralExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.InvalidTypeImpl <em>Invalid Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.InvalidTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getInvalidType()
		 * @generated
		 */
		EClass INVALID_TYPE = eINSTANCE.getInvalidType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.IterableTypeImpl <em>Iterable Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.IterableTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getIterableType()
		 * @generated
		 */
		EClass ITERABLE_TYPE = eINSTANCE.getIterableType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.IterateExpImpl <em>Iterate Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.IterateExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getIterateExp()
		 * @generated
		 */
		EClass ITERATE_EXP = eINSTANCE.getIterateExp();

		/**
		 * The meta object literal for the '<em><b>Owned Result</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITERATE_EXP__OWNED_RESULT = eINSTANCE.getIterateExp_OwnedResult();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.IterationImpl <em>Iteration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.IterationImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getIteration()
		 * @generated
		 */
		EClass ITERATION = eINSTANCE.getIteration();

		/**
		 * The meta object literal for the '<em><b>Owned Accumulators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITERATION__OWNED_ACCUMULATORS = eINSTANCE.getIteration_OwnedAccumulators();

		/**
		 * The meta object literal for the '<em><b>Owned Iterators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ITERATION__OWNED_ITERATORS = eINSTANCE.getIteration_OwnedIterators();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.IteratorExpImpl <em>Iterator Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.IteratorExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getIteratorExp()
		 * @generated
		 */
		EClass ITERATOR_EXP = eINSTANCE.getIteratorExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.IteratorVariableImpl <em>Iterator Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.IteratorVariableImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getIteratorVariable()
		 * @generated
		 */
		EClass ITERATOR_VARIABLE = eINSTANCE.getIteratorVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.JavaTypeImpl <em>Java Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.JavaTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getJavaType()
		 * @generated
		 */
		EClass JAVA_TYPE = eINSTANCE.getJavaType();

		/**
		 * The meta object literal for the '<em><b>Java Class</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JAVA_TYPE__JAVA_CLASS = eINSTANCE.getJavaType_JavaClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.LambdaTypeImpl <em>Lambda Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.LambdaTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getLambdaType()
		 * @generated
		 */
		EClass LAMBDA_TYPE = eINSTANCE.getLambdaType();

		/**
		 * The meta object literal for the '<em><b>Context Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAMBDA_TYPE__CONTEXT_TYPE = eINSTANCE.getLambdaType_ContextType();

		/**
		 * The meta object literal for the '<em><b>Parameter Type</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAMBDA_TYPE__PARAMETER_TYPE = eINSTANCE.getLambdaType_ParameterType();

		/**
		 * The meta object literal for the '<em><b>Result Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LAMBDA_TYPE__RESULT_TYPE = eINSTANCE.getLambdaType_ResultType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.LanguageExpressionImpl <em>Language Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.LanguageExpressionImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getLanguageExpression()
		 * @generated
		 */
		EClass LANGUAGE_EXPRESSION = eINSTANCE.getLanguageExpression();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LANGUAGE_EXPRESSION__BODY = eINSTANCE.getLanguageExpression_Body();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LANGUAGE_EXPRESSION__LANGUAGE = eINSTANCE.getLanguageExpression_Language();

		/**
		 * The meta object literal for the '<em><b>Owning Constraint</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LANGUAGE_EXPRESSION__OWNING_CONSTRAINT = eINSTANCE.getLanguageExpression_OwningConstraint();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.LetExpImpl <em>Let Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.LetExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getLetExp()
		 * @generated
		 */
		EClass LET_EXP = eINSTANCE.getLetExp();

		/**
		 * The meta object literal for the '<em><b>Owned In</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LET_EXP__OWNED_IN = eINSTANCE.getLetExp_OwnedIn();

		/**
		 * The meta object literal for the '<em><b>Owned Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LET_EXP__OWNED_VARIABLE = eINSTANCE.getLetExp_OwnedVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.LetVariableImpl <em>Let Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.LetVariableImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getLetVariable()
		 * @generated
		 */
		EClass LET_VARIABLE = eINSTANCE.getLetVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.LibraryImpl <em>Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.LibraryImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getLibrary()
		 * @generated
		 */
		EClass LIBRARY = eINSTANCE.getLibrary();

		/**
		 * The meta object literal for the '<em><b>Owned Precedences</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LIBRARY__OWNED_PRECEDENCES = eINSTANCE.getLibrary_OwnedPrecedences();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.LiteralExpImpl <em>Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.LiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getLiteralExp()
		 * @generated
		 */
		EClass LITERAL_EXP = eINSTANCE.getLiteralExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.LoopExpImpl <em>Loop Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.LoopExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getLoopExp()
		 * @generated
		 */
		EClass LOOP_EXP = eINSTANCE.getLoopExp();

		/**
		 * The meta object literal for the '<em><b>Owned Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOP_EXP__OWNED_BODY = eINSTANCE.getLoopExp_OwnedBody();

		/**
		 * The meta object literal for the '<em><b>Owned Co Iterators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOP_EXP__OWNED_CO_ITERATORS = eINSTANCE.getLoopExp_OwnedCoIterators();

		/**
		 * The meta object literal for the '<em><b>Owned Iterators</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOP_EXP__OWNED_ITERATORS = eINSTANCE.getLoopExp_OwnedIterators();

		/**
		 * The meta object literal for the '<em><b>Referred Iteration</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOOP_EXP__REFERRED_ITERATION = eINSTANCE.getLoopExp_ReferredIteration();

		/**
		 * The meta object literal for the '{@link java.util.Map <em>Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Map
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getMap()
		 * @generated
		 */
		EClass MAP = eINSTANCE.getMap();

		/**
		 * The meta object literal for the '<em><b>Key Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP__KEY_TYPE = eINSTANCE.getMap_KeyType();

		/**
		 * The meta object literal for the '<em><b>Value Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP__VALUE_TYPE = eINSTANCE.getMap_ValueType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.MapLiteralExpImpl <em>Map Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.MapLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getMapLiteralExp()
		 * @generated
		 */
		EClass MAP_LITERAL_EXP = eINSTANCE.getMapLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Owned Parts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_LITERAL_EXP__OWNED_PARTS = eINSTANCE.getMapLiteralExp_OwnedParts();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.MapLiteralPartImpl <em>Map Literal Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.MapLiteralPartImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getMapLiteralPart()
		 * @generated
		 */
		EClass MAP_LITERAL_PART = eINSTANCE.getMapLiteralPart();

		/**
		 * The meta object literal for the '<em><b>Owned Key</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_LITERAL_PART__OWNED_KEY = eINSTANCE.getMapLiteralPart_OwnedKey();

		/**
		 * The meta object literal for the '<em><b>Owned Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_LITERAL_PART__OWNED_VALUE = eINSTANCE.getMapLiteralPart_OwnedValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.MapTypeImpl <em>Map Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.MapTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getMapType()
		 * @generated
		 */
		EClass MAP_TYPE = eINSTANCE.getMapType();

		/**
		 * The meta object literal for the '<em><b>Entry Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_TYPE__ENTRY_CLASS = eINSTANCE.getMapType_EntryClass();

		/**
		 * The meta object literal for the '<em><b>Key Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_TYPE__KEY_TYPE = eINSTANCE.getMapType_KeyType();

		/**
		 * The meta object literal for the '<em><b>Keys Are Null Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAP_TYPE__KEYS_ARE_NULL_FREE = eINSTANCE.getMapType_KeysAreNullFree();

		/**
		 * The meta object literal for the '<em><b>Value Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP_TYPE__VALUE_TYPE = eINSTANCE.getMapType_ValueType();

		/**
		 * The meta object literal for the '<em><b>Values Are Null Free</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAP_TYPE__VALUES_ARE_NULL_FREE = eINSTANCE.getMapType_ValuesAreNullFree();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.MessageExpImpl <em>Message Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.MessageExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getMessageExp()
		 * @generated
		 */
		EClass MESSAGE_EXP = eINSTANCE.getMessageExp();

		/**
		 * The meta object literal for the '<em><b>Owned Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_EXP__OWNED_ARGUMENTS = eINSTANCE.getMessageExp_OwnedArguments();

		/**
		 * The meta object literal for the '<em><b>Owned Called Operation</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_EXP__OWNED_CALLED_OPERATION = eINSTANCE.getMessageExp_OwnedCalledOperation();

		/**
		 * The meta object literal for the '<em><b>Owned Sent Signal</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_EXP__OWNED_SENT_SIGNAL = eINSTANCE.getMessageExp_OwnedSentSignal();

		/**
		 * The meta object literal for the '<em><b>Owned Target</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_EXP__OWNED_TARGET = eINSTANCE.getMessageExp_OwnedTarget();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.MessageTypeImpl <em>Message Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.MessageTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getMessageType()
		 * @generated
		 */
		EClass MESSAGE_TYPE = eINSTANCE.getMessageType();

		/**
		 * The meta object literal for the '<em><b>Referred Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_TYPE__REFERRED_OPERATION = eINSTANCE.getMessageType_ReferredOperation();

		/**
		 * The meta object literal for the '<em><b>Referred Signal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_TYPE__REFERRED_SIGNAL = eINSTANCE.getMessageType_ReferredSignal();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ModelImpl <em>Model</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ModelImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getModel()
		 * @generated
		 */
		EClass MODEL = eINSTANCE.getModel();

		/**
		 * The meta object literal for the '<em><b>External URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL__EXTERNAL_URI = eINSTANCE.getModel_ExternalURI();

		/**
		 * The meta object literal for the '<em><b>Owned Imports</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__OWNED_IMPORTS = eINSTANCE.getModel_OwnedImports();

		/**
		 * The meta object literal for the '<em><b>Owned Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MODEL__OWNED_PACKAGES = eINSTANCE.getModel_OwnedPackages();

		/**
		 * The meta object literal for the '<em><b>Xmiid Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MODEL__XMIID_VERSION = eINSTANCE.getModel_XmiidVersion();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.utilities.MorePivotable <em>More Pivotable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.utilities.MorePivotable
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getMorePivotable()
		 * @generated
		 */
		EClass MORE_PIVOTABLE = eINSTANCE.getMorePivotable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.utilities.Nameable <em>Nameable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.utilities.Nameable
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getNameable()
		 * @generated
		 */
		EClass NAMEABLE = eINSTANCE.getNameable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.NamedElementImpl <em>Named Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.NamedElementImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getNamedElement()
		 * @generated
		 */
		EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.NamespaceImpl <em>Namespace</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.NamespaceImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getNamespace()
		 * @generated
		 */
		EClass NAMESPACE = eINSTANCE.getNamespace();

		/**
		 * The meta object literal for the '<em><b>Owned Constraints</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAMESPACE__OWNED_CONSTRAINTS = eINSTANCE.getNamespace_OwnedConstraints();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.NavigationCallExpImpl <em>Navigation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.NavigationCallExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getNavigationCallExp()
		 * @generated
		 */
		EClass NAVIGATION_CALL_EXP = eINSTANCE.getNavigationCallExp();

		/**
		 * The meta object literal for the '<em><b>Navigation Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_CALL_EXP__NAVIGATION_SOURCE = eINSTANCE.getNavigationCallExp_NavigationSource();

		/**
		 * The meta object literal for the '<em><b>Qualifiers</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NAVIGATION_CALL_EXP__QUALIFIERS = eINSTANCE.getNavigationCallExp_Qualifiers();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.NullLiteralExpImpl <em>Null Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.NullLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getNullLiteralExp()
		 * @generated
		 */
		EClass NULL_LITERAL_EXP = eINSTANCE.getNullLiteralExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.NumericLiteralExpImpl <em>Numeric Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.NumericLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getNumericLiteralExp()
		 * @generated
		 */
		EClass NUMERIC_LITERAL_EXP = eINSTANCE.getNumericLiteralExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.OCLExpressionImpl <em>OCL Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.OCLExpressionImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOCLExpression()
		 * @generated
		 */
		EClass OCL_EXPRESSION = eINSTANCE.getOCLExpression();

		/**
		 * The meta object literal for the '<em><b>Type Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_EXPRESSION__TYPE_VALUE = eINSTANCE.getOCLExpression_TypeValue();

		/**
		 * The meta object literal for the '{@link java.lang.Object <em>Ocl Any</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclAny()
		 * @generated
		 */
		EClass OCL_ANY = eINSTANCE.getOclAny();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclComparable <em>Ocl Comparable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclComparable
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclComparable()
		 * @generated
		 */
		EClass OCL_COMPARABLE = eINSTANCE.getOclComparable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclElement <em>Ocl Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclElement
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclElement()
		 * @generated
		 */
		EClass OCL_ELEMENT = eINSTANCE.getOclElement();

		/**
		 * The meta object literal for the '<em><b>Ocl Container</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_ELEMENT__OCL_CONTAINER = eINSTANCE.getOclElement_OclContainer();

		/**
		 * The meta object literal for the '<em><b>Ocl Contents</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_ELEMENT__OCL_CONTENTS = eINSTANCE.getOclElement_OclContents();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclEnumeration <em>Ocl Enumeration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclEnumeration
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclEnumeration()
		 * @generated
		 */
		EClass OCL_ENUMERATION = eINSTANCE.getOclEnumeration();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.InvalidType <em>Ocl Invalid</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.InvalidType
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclInvalid()
		 * @generated
		 */
		EClass OCL_INVALID = eINSTANCE.getOclInvalid();

		/**
		 * The meta object literal for the '<em><b>Ocl Bad Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OCL_INVALID__OCL_BAD_PROPERTY = eINSTANCE.getOclInvalid_OclBadProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclLambda <em>Ocl Lambda</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclLambda
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclLambda()
		 * @generated
		 */
		EClass OCL_LAMBDA = eINSTANCE.getOclLambda();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclMessage <em>Ocl Message</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclMessage
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclMessage()
		 * @generated
		 */
		EClass OCL_MESSAGE = eINSTANCE.getOclMessage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.SelfType <em>Ocl Self</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.SelfType
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclSelf()
		 * @generated
		 */
		EClass OCL_SELF = eINSTANCE.getOclSelf();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclState <em>Ocl State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclState
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclState()
		 * @generated
		 */
		EClass OCL_STATE = eINSTANCE.getOclState();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclStereotype <em>Ocl Stereotype</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclStereotype
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclStereotype()
		 * @generated
		 */
		EClass OCL_STEREOTYPE = eINSTANCE.getOclStereotype();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclSummable <em>Ocl Summable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclSummable
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclSummable()
		 * @generated
		 */
		EClass OCL_SUMMABLE = eINSTANCE.getOclSummable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclTuple <em>Ocl Tuple</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclTuple
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclTuple()
		 * @generated
		 */
		EClass OCL_TUPLE = eINSTANCE.getOclTuple();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.OclType <em>Ocl Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.OclType
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclType()
		 * @generated
		 */
		EClass OCL_TYPE = eINSTANCE.getOclType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.VoidType <em>Ocl Void</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.VoidType
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOclVoid()
		 * @generated
		 */
		EClass OCL_VOID = eINSTANCE.getOclVoid();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.OperationImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Body Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__BODY_EXPRESSION = eINSTANCE.getOperation_BodyExpression();

		/**
		 * The meta object literal for the '<em><b>Is Invalidating</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__IS_INVALIDATING = eINSTANCE.getOperation_IsInvalidating();

		/**
		 * The meta object literal for the '<em><b>Is Transient</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__IS_TRANSIENT = eINSTANCE.getOperation_IsTransient();

		/**
		 * The meta object literal for the '<em><b>Is Typeof</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__IS_TYPEOF = eINSTANCE.getOperation_IsTypeof();

		/**
		 * The meta object literal for the '<em><b>Is Validating</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION__IS_VALIDATING = eINSTANCE.getOperation_IsValidating();

		/**
		 * The meta object literal for the '<em><b>Owned Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__OWNED_PARAMETERS = eINSTANCE.getOperation_OwnedParameters();

		/**
		 * The meta object literal for the '<em><b>Owned Postconditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__OWNED_POSTCONDITIONS = eINSTANCE.getOperation_OwnedPostconditions();

		/**
		 * The meta object literal for the '<em><b>Owned Preconditions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__OWNED_PRECONDITIONS = eINSTANCE.getOperation_OwnedPreconditions();

		/**
		 * The meta object literal for the '<em><b>Owning Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__OWNING_CLASS = eINSTANCE.getOperation_OwningClass();

		/**
		 * The meta object literal for the '<em><b>Precedence</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__PRECEDENCE = eINSTANCE.getOperation_Precedence();

		/**
		 * The meta object literal for the '<em><b>Raised Exceptions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__RAISED_EXCEPTIONS = eINSTANCE.getOperation_RaisedExceptions();

		/**
		 * The meta object literal for the '<em><b>Redefined Operations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__REDEFINED_OPERATIONS = eINSTANCE.getOperation_RedefinedOperations();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.OperationCallExpImpl <em>Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.OperationCallExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOperationCallExp()
		 * @generated
		 */
		EClass OPERATION_CALL_EXP = eINSTANCE.getOperationCallExp();

		/**
		 * The meta object literal for the '<em><b>Is Virtual</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute OPERATION_CALL_EXP__IS_VIRTUAL = eINSTANCE.getOperationCallExp_IsVirtual();

		/**
		 * The meta object literal for the '<em><b>Owned Arguments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CALL_EXP__OWNED_ARGUMENTS = eINSTANCE.getOperationCallExp_OwnedArguments();

		/**
		 * The meta object literal for the '<em><b>Referred Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION_CALL_EXP__REFERRED_OPERATION = eINSTANCE.getOperationCallExp_ReferredOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.OppositePropertyCallExpImpl <em>Opposite Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.OppositePropertyCallExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOppositePropertyCallExp()
		 * @generated
		 */
		EClass OPPOSITE_PROPERTY_CALL_EXP = eINSTANCE.getOppositePropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>Referred Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPPOSITE_PROPERTY_CALL_EXP__REFERRED_PROPERTY = eINSTANCE.getOppositePropertyCallExp_ReferredProperty();

		/**
		 * The meta object literal for the '{@link java.util.Collection <em>Ordered Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Collection
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOrderedCollection()
		 * @generated
		 */
		EClass ORDERED_COLLECTION = eINSTANCE.getOrderedCollection();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.values.OrderedSet <em>Ordered Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.OrderedSet
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOrderedSet()
		 * @generated
		 */
		EClass ORDERED_SET = eINSTANCE.getOrderedSet();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.OrderedSetTypeImpl <em>Ordered Set Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.OrderedSetTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOrderedSetType()
		 * @generated
		 */
		EClass ORDERED_SET_TYPE = eINSTANCE.getOrderedSetType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.OrphanCompletePackageImpl <em>Orphan Complete Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.OrphanCompletePackageImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getOrphanCompletePackage()
		 * @generated
		 */
		EClass ORPHAN_COMPLETE_PACKAGE = eINSTANCE.getOrphanCompletePackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.PackageImpl <em>Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.PackageImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getPackage()
		 * @generated
		 */
		EClass PACKAGE = eINSTANCE.getPackage();

		/**
		 * The meta object literal for the '<em><b>URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE__URI = eINSTANCE.getPackage_URI();

		/**
		 * The meta object literal for the '<em><b>Imported Packages</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__IMPORTED_PACKAGES = eINSTANCE.getPackage_ImportedPackages();

		/**
		 * The meta object literal for the '<em><b>Ns Prefix</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE__NS_PREFIX = eINSTANCE.getPackage_NsPrefix();

		/**
		 * The meta object literal for the '<em><b>Owned Classes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__OWNED_CLASSES = eINSTANCE.getPackage_OwnedClasses();

		/**
		 * The meta object literal for the '<em><b>Owned Instances</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__OWNED_INSTANCES = eINSTANCE.getPackage_OwnedInstances();

		/**
		 * The meta object literal for the '<em><b>Owned Packages</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__OWNED_PACKAGES = eINSTANCE.getPackage_OwnedPackages();

		/**
		 * The meta object literal for the '<em><b>Owned Profile Applications</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__OWNED_PROFILE_APPLICATIONS = eINSTANCE.getPackage_OwnedProfileApplications();

		/**
		 * The meta object literal for the '<em><b>Owning Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PACKAGE__OWNING_PACKAGE = eINSTANCE.getPackage_OwningPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ParameterImpl <em>Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ParameterImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getParameter()
		 * @generated
		 */
		EClass PARAMETER = eINSTANCE.getParameter();

		/**
		 * The meta object literal for the '<em><b>Is Typeof</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAMETER__IS_TYPEOF = eINSTANCE.getParameter_IsTypeof();

		/**
		 * The meta object literal for the '<em><b>Owning Operation</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAMETER__OWNING_OPERATION = eINSTANCE.getParameter_OwningOperation();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ParameterVariableImpl <em>Parameter Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ParameterVariableImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getParameterVariable()
		 * @generated
		 */
		EClass PARAMETER_VARIABLE = eINSTANCE.getParameterVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.utilities.Pivotable <em>Pivotable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.utilities.Pivotable
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getPivotable()
		 * @generated
		 */
		EClass PIVOTABLE = eINSTANCE.getPivotable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.PrecedenceImpl <em>Precedence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.PrecedenceImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getPrecedence()
		 * @generated
		 */
		EClass PRECEDENCE = eINSTANCE.getPrecedence();

		/**
		 * The meta object literal for the '<em><b>Associativity</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRECEDENCE__ASSOCIATIVITY = eINSTANCE.getPrecedence_Associativity();

		/**
		 * The meta object literal for the '<em><b>Order</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRECEDENCE__ORDER = eINSTANCE.getPrecedence_Order();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.PrimitiveCompletePackageImpl <em>Primitive Complete Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.PrimitiveCompletePackageImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getPrimitiveCompletePackage()
		 * @generated
		 */
		EClass PRIMITIVE_COMPLETE_PACKAGE = eINSTANCE.getPrimitiveCompletePackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.PrimitiveLiteralExpImpl <em>Primitive Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.PrimitiveLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getPrimitiveLiteralExp()
		 * @generated
		 */
		EClass PRIMITIVE_LITERAL_EXP = eINSTANCE.getPrimitiveLiteralExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.PrimitiveTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getPrimitiveType()
		 * @generated
		 */
		EClass PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();

		/**
		 * The meta object literal for the '<em><b>Coercions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRIMITIVE_TYPE__COERCIONS = eINSTANCE.getPrimitiveType_Coercions();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ProfileImpl <em>Profile</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ProfileImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getProfile()
		 * @generated
		 */
		EClass PROFILE = eINSTANCE.getProfile();

		/**
		 * The meta object literal for the '<em><b>Profile Applications</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROFILE__PROFILE_APPLICATIONS = eINSTANCE.getProfile_ProfileApplications();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ProfileApplicationImpl <em>Profile Application</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ProfileApplicationImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getProfileApplication()
		 * @generated
		 */
		EClass PROFILE_APPLICATION = eINSTANCE.getProfileApplication();

		/**
		 * The meta object literal for the '<em><b>Applied Profile</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROFILE_APPLICATION__APPLIED_PROFILE = eINSTANCE.getProfileApplication_AppliedProfile();

		/**
		 * The meta object literal for the '<em><b>Is Strict</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROFILE_APPLICATION__IS_STRICT = eINSTANCE.getProfileApplication_IsStrict();

		/**
		 * The meta object literal for the '<em><b>Owning Package</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROFILE_APPLICATION__OWNING_PACKAGE = eINSTANCE.getProfileApplication_OwningPackage();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.PropertyImpl <em>Property</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.PropertyImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getProperty()
		 * @generated
		 */
		EClass PROPERTY = eINSTANCE.getProperty();

		/**
		 * The meta object literal for the '<em><b>Association Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__ASSOCIATION_CLASS = eINSTANCE.getProperty_AssociationClass();

		/**
		 * The meta object literal for the '<em><b>Default Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__DEFAULT_VALUE = eINSTANCE.getProperty_DefaultValue();

		/**
		 * The meta object literal for the '<em><b>Default Value String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__DEFAULT_VALUE_STRING = eINSTANCE.getProperty_DefaultValueString();

		/**
		 * The meta object literal for the '<em><b>Is Composite</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__IS_COMPOSITE = eINSTANCE.getProperty_IsComposite();

		/**
		 * The meta object literal for the '<em><b>Is Derived</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__IS_DERIVED = eINSTANCE.getProperty_IsDerived();

		/**
		 * The meta object literal for the '<em><b>Is ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__IS_ID = eINSTANCE.getProperty_IsID();

		/**
		 * The meta object literal for the '<em><b>Is Implicit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__IS_IMPLICIT = eINSTANCE.getProperty_IsImplicit();

		/**
		 * The meta object literal for the '<em><b>Is Read Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__IS_READ_ONLY = eINSTANCE.getProperty_IsReadOnly();

		/**
		 * The meta object literal for the '<em><b>Is Resolve Proxies</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__IS_RESOLVE_PROXIES = eINSTANCE.getProperty_IsResolveProxies();

		/**
		 * The meta object literal for the '<em><b>Is Transient</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__IS_TRANSIENT = eINSTANCE.getProperty_IsTransient();

		/**
		 * The meta object literal for the '<em><b>Is Unsettable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__IS_UNSETTABLE = eINSTANCE.getProperty_IsUnsettable();

		/**
		 * The meta object literal for the '<em><b>Is Volatile</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROPERTY__IS_VOLATILE = eINSTANCE.getProperty_IsVolatile();

		/**
		 * The meta object literal for the '<em><b>Keys</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__KEYS = eINSTANCE.getProperty_Keys();

		/**
		 * The meta object literal for the '<em><b>Opposite</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__OPPOSITE = eINSTANCE.getProperty_Opposite();

		/**
		 * The meta object literal for the '<em><b>Owned Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__OWNED_EXPRESSION = eINSTANCE.getProperty_OwnedExpression();

		/**
		 * The meta object literal for the '<em><b>Owning Class</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__OWNING_CLASS = eINSTANCE.getProperty_OwningClass();

		/**
		 * The meta object literal for the '<em><b>Redefined Properties</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__REDEFINED_PROPERTIES = eINSTANCE.getProperty_RedefinedProperties();

		/**
		 * The meta object literal for the '<em><b>Referred Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__REFERRED_PROPERTY = eINSTANCE.getProperty_ReferredProperty();

		/**
		 * The meta object literal for the '<em><b>Subsetted Property</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY__SUBSETTED_PROPERTY = eINSTANCE.getProperty_SubsettedProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.PropertyCallExpImpl <em>Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.PropertyCallExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getPropertyCallExp()
		 * @generated
		 */
		EClass PROPERTY_CALL_EXP = eINSTANCE.getPropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>Referred Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROPERTY_CALL_EXP__REFERRED_PROPERTY = eINSTANCE.getPropertyCallExp_ReferredProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.PseudostateImpl <em>Pseudostate</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.PseudostateImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getPseudostate()
		 * @generated
		 */
		EClass PSEUDOSTATE = eINSTANCE.getPseudostate();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PSEUDOSTATE__KIND = eINSTANCE.getPseudostate_Kind();

		/**
		 * The meta object literal for the '<em><b>Owning State</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PSEUDOSTATE__OWNING_STATE = eINSTANCE.getPseudostate_OwningState();

		/**
		 * The meta object literal for the '<em><b>Owning State Machine</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PSEUDOSTATE__OWNING_STATE_MACHINE = eINSTANCE.getPseudostate_OwningStateMachine();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.RealLiteralExpImpl <em>Real Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.RealLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getRealLiteralExp()
		 * @generated
		 */
		EClass REAL_LITERAL_EXP = eINSTANCE.getRealLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Real Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute REAL_LITERAL_EXP__REAL_SYMBOL = eINSTANCE.getRealLiteralExp_RealSymbol();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.ReferringElement <em>Referring Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.ReferringElement
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getReferringElement()
		 * @generated
		 */
		EClass REFERRING_ELEMENT = eINSTANCE.getReferringElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.RegionImpl <em>Region</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.RegionImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getRegion()
		 * @generated
		 */
		EClass REGION = eINSTANCE.getRegion();

		/**
		 * The meta object literal for the '<em><b>Extended Region</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__EXTENDED_REGION = eINSTANCE.getRegion_ExtendedRegion();

		/**
		 * The meta object literal for the '<em><b>Owned Subvertexes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__OWNED_SUBVERTEXES = eINSTANCE.getRegion_OwnedSubvertexes();

		/**
		 * The meta object literal for the '<em><b>Owned Transitions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__OWNED_TRANSITIONS = eINSTANCE.getRegion_OwnedTransitions();

		/**
		 * The meta object literal for the '<em><b>Owning State</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__OWNING_STATE = eINSTANCE.getRegion_OwningState();

		/**
		 * The meta object literal for the '<em><b>Owning State Machine</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REGION__OWNING_STATE_MACHINE = eINSTANCE.getRegion_OwningStateMachine();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ResultVariableImpl <em>Result Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ResultVariableImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getResultVariable()
		 * @generated
		 */
		EClass RESULT_VARIABLE = eINSTANCE.getResultVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.SelfTypeImpl <em>Self Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.SelfTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getSelfType()
		 * @generated
		 */
		EClass SELF_TYPE = eINSTANCE.getSelfType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.SendSignalActionImpl <em>Send Signal Action</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.SendSignalActionImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getSendSignalAction()
		 * @generated
		 */
		EClass SEND_SIGNAL_ACTION = eINSTANCE.getSendSignalAction();

		/**
		 * The meta object literal for the '<em><b>Signal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEND_SIGNAL_ACTION__SIGNAL = eINSTANCE.getSendSignalAction_Signal();

		/**
		 * The meta object literal for the '{@link java.util.List <em>Sequence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getSequence()
		 * @generated
		 */
		EClass SEQUENCE = eINSTANCE.getSequence();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.SequenceTypeImpl <em>Sequence Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.SequenceTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getSequenceType()
		 * @generated
		 */
		EClass SEQUENCE_TYPE = eINSTANCE.getSequenceType();

		/**
		 * The meta object literal for the '{@link java.util.Set <em>Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Set
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getSet()
		 * @generated
		 */
		EClass SET = eINSTANCE.getSet();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.SetTypeImpl <em>Set Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.SetTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getSetType()
		 * @generated
		 */
		EClass SET_TYPE = eINSTANCE.getSetType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ShadowExpImpl <em>Shadow Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ShadowExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getShadowExp()
		 * @generated
		 */
		EClass SHADOW_EXP = eINSTANCE.getShadowExp();

		/**
		 * The meta object literal for the '<em><b>Owned Parts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHADOW_EXP__OWNED_PARTS = eINSTANCE.getShadowExp_OwnedParts();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SHADOW_EXP__VALUE = eINSTANCE.getShadowExp_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ShadowPartImpl <em>Shadow Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ShadowPartImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getShadowPart()
		 * @generated
		 */
		EClass SHADOW_PART = eINSTANCE.getShadowPart();

		/**
		 * The meta object literal for the '<em><b>Owned Init</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHADOW_PART__OWNED_INIT = eINSTANCE.getShadowPart_OwnedInit();

		/**
		 * The meta object literal for the '<em><b>Referred Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SHADOW_PART__REFERRED_PROPERTY = eINSTANCE.getShadowPart_ReferredProperty();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.SignalImpl <em>Signal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.SignalImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getSignal()
		 * @generated
		 */
		EClass SIGNAL = eINSTANCE.getSignal();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.SlotImpl <em>Slot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.SlotImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getSlot()
		 * @generated
		 */
		EClass SLOT = eINSTANCE.getSlot();

		/**
		 * The meta object literal for the '<em><b>Defining Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SLOT__DEFINING_PROPERTY = eINSTANCE.getSlot_DefiningProperty();

		/**
		 * The meta object literal for the '<em><b>Owned Values</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SLOT__OWNED_VALUES = eINSTANCE.getSlot_OwnedValues();

		/**
		 * The meta object literal for the '<em><b>Owning Instance</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SLOT__OWNING_INSTANCE = eINSTANCE.getSlot_OwningInstance();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.StandardLibraryImpl <em>Standard Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.StandardLibraryImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getStandardLibrary()
		 * @generated
		 */
		EClass STANDARD_LIBRARY = eINSTANCE.getStandardLibrary();

		/**
		 * The meta object literal for the '<em><b>Owning Complete Environment</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STANDARD_LIBRARY__OWNING_COMPLETE_ENVIRONMENT = eINSTANCE.getStandardLibrary_OwningCompleteEnvironment();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.StateImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Is Composite</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__IS_COMPOSITE = eINSTANCE.getState_IsComposite();

		/**
		 * The meta object literal for the '<em><b>Is Orthogonal</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__IS_ORTHOGONAL = eINSTANCE.getState_IsOrthogonal();

		/**
		 * The meta object literal for the '<em><b>Is Simple</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__IS_SIMPLE = eINSTANCE.getState_IsSimple();

		/**
		 * The meta object literal for the '<em><b>Is Submachine State</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__IS_SUBMACHINE_STATE = eINSTANCE.getState_IsSubmachineState();

		/**
		 * The meta object literal for the '<em><b>Owned Connection Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__OWNED_CONNECTION_POINTS = eINSTANCE.getState_OwnedConnectionPoints();

		/**
		 * The meta object literal for the '<em><b>Owned Connections</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__OWNED_CONNECTIONS = eINSTANCE.getState_OwnedConnections();

		/**
		 * The meta object literal for the '<em><b>Owned Deferrable Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__OWNED_DEFERRABLE_TRIGGERS = eINSTANCE.getState_OwnedDeferrableTriggers();

		/**
		 * The meta object literal for the '<em><b>Owned Do Activity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__OWNED_DO_ACTIVITY = eINSTANCE.getState_OwnedDoActivity();

		/**
		 * The meta object literal for the '<em><b>Owned Entry</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__OWNED_ENTRY = eINSTANCE.getState_OwnedEntry();

		/**
		 * The meta object literal for the '<em><b>Owned Exit</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__OWNED_EXIT = eINSTANCE.getState_OwnedExit();

		/**
		 * The meta object literal for the '<em><b>Owned Regions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__OWNED_REGIONS = eINSTANCE.getState_OwnedRegions();

		/**
		 * The meta object literal for the '<em><b>Owned State Invariant</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__OWNED_STATE_INVARIANT = eINSTANCE.getState_OwnedStateInvariant();

		/**
		 * The meta object literal for the '<em><b>Redefined State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__REDEFINED_STATE = eINSTANCE.getState_RedefinedState();

		/**
		 * The meta object literal for the '<em><b>Submachines</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__SUBMACHINES = eINSTANCE.getState_Submachines();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.StateExpImpl <em>State Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.StateExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getStateExp()
		 * @generated
		 */
		EClass STATE_EXP = eINSTANCE.getStateExp();

		/**
		 * The meta object literal for the '<em><b>Referred State</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_EXP__REFERRED_STATE = eINSTANCE.getStateExp_ReferredState();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.StateMachineImpl <em>State Machine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.StateMachineImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getStateMachine()
		 * @generated
		 */
		EClass STATE_MACHINE = eINSTANCE.getStateMachine();

		/**
		 * The meta object literal for the '<em><b>Extended State Machines</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__EXTENDED_STATE_MACHINES = eINSTANCE.getStateMachine_ExtendedStateMachines();

		/**
		 * The meta object literal for the '<em><b>Owned Connection Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__OWNED_CONNECTION_POINTS = eINSTANCE.getStateMachine_OwnedConnectionPoints();

		/**
		 * The meta object literal for the '<em><b>Owned Regions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__OWNED_REGIONS = eINSTANCE.getStateMachine_OwnedRegions();

		/**
		 * The meta object literal for the '<em><b>Submachine States</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE_MACHINE__SUBMACHINE_STATES = eINSTANCE.getStateMachine_SubmachineStates();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.StereotypeImpl <em>Stereotype</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.StereotypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getStereotype()
		 * @generated
		 */
		EClass STEREOTYPE = eINSTANCE.getStereotype();

		/**
		 * The meta object literal for the '<em><b>Owned Extenders</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEREOTYPE__OWNED_EXTENDERS = eINSTANCE.getStereotype_OwnedExtenders();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.StereotypeExtenderImpl <em>Stereotype Extender</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.StereotypeExtenderImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getStereotypeExtender()
		 * @generated
		 */
		EClass STEREOTYPE_EXTENDER = eINSTANCE.getStereotypeExtender();

		/**
		 * The meta object literal for the '<em><b>Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEREOTYPE_EXTENDER__CLASS = eINSTANCE.getStereotypeExtender_Class();

		/**
		 * The meta object literal for the '<em><b>Is Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STEREOTYPE_EXTENDER__IS_REQUIRED = eINSTANCE.getStereotypeExtender_IsRequired();

		/**
		 * The meta object literal for the '<em><b>Owning Stereotype</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STEREOTYPE_EXTENDER__OWNING_STEREOTYPE = eINSTANCE.getStereotypeExtender_OwningStereotype();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.StringLiteralExpImpl <em>String Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.StringLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getStringLiteralExp()
		 * @generated
		 */
		EClass STRING_LITERAL_EXP = eINSTANCE.getStringLiteralExp();

		/**
		 * The meta object literal for the '<em><b>String Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STRING_LITERAL_EXP__STRING_SYMBOL = eINSTANCE.getStringLiteralExp_StringSymbol();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TemplateBindingImpl <em>Template Binding</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TemplateBindingImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTemplateBinding()
		 * @generated
		 */
		EClass TEMPLATE_BINDING = eINSTANCE.getTemplateBinding();

		/**
		 * The meta object literal for the '<em><b>Owned Substitutions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_BINDING__OWNED_SUBSTITUTIONS = eINSTANCE.getTemplateBinding_OwnedSubstitutions();

		/**
		 * The meta object literal for the '<em><b>Owning Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_BINDING__OWNING_ELEMENT = eINSTANCE.getTemplateBinding_OwningElement();

		/**
		 * The meta object literal for the '<em><b>Template Signature</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_BINDING__TEMPLATE_SIGNATURE = eINSTANCE.getTemplateBinding_TemplateSignature();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TemplateParameterImpl <em>Template Parameter</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TemplateParameterImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTemplateParameter()
		 * @generated
		 */
		EClass TEMPLATE_PARAMETER = eINSTANCE.getTemplateParameter();

		/**
		 * The meta object literal for the '<em><b>Constraining Classes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_PARAMETER__CONSTRAINING_CLASSES = eINSTANCE.getTemplateParameter_ConstrainingClasses();

		/**
		 * The meta object literal for the '<em><b>Owning Signature</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_PARAMETER__OWNING_SIGNATURE = eINSTANCE.getTemplateParameter_OwningSignature();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TemplateParameterSubstitutionImpl <em>Template Parameter Substitution</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TemplateParameterSubstitutionImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTemplateParameterSubstitution()
		 * @generated
		 */
		EClass TEMPLATE_PARAMETER_SUBSTITUTION = eINSTANCE.getTemplateParameterSubstitution();

		/**
		 * The meta object literal for the '<em><b>Actual</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_PARAMETER_SUBSTITUTION__ACTUAL = eINSTANCE.getTemplateParameterSubstitution_Actual();

		/**
		 * The meta object literal for the '<em><b>Formal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_PARAMETER_SUBSTITUTION__FORMAL = eINSTANCE.getTemplateParameterSubstitution_Formal();

		/**
		 * The meta object literal for the '<em><b>Owned Wildcard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_PARAMETER_SUBSTITUTION__OWNED_WILDCARD = eINSTANCE.getTemplateParameterSubstitution_OwnedWildcard();

		/**
		 * The meta object literal for the '<em><b>Owning Binding</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_PARAMETER_SUBSTITUTION__OWNING_BINDING = eINSTANCE.getTemplateParameterSubstitution_OwningBinding();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TemplateSignatureImpl <em>Template Signature</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TemplateSignatureImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTemplateSignature()
		 * @generated
		 */
		EClass TEMPLATE_SIGNATURE = eINSTANCE.getTemplateSignature();

		/**
		 * The meta object literal for the '<em><b>Owned Parameters</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_SIGNATURE__OWNED_PARAMETERS = eINSTANCE.getTemplateSignature_OwnedParameters();

		/**
		 * The meta object literal for the '<em><b>Owning Element</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATE_SIGNATURE__OWNING_ELEMENT = eINSTANCE.getTemplateSignature_OwningElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TemplateableElementImpl <em>Templateable Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TemplateableElementImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTemplateableElement()
		 * @generated
		 */
		EClass TEMPLATEABLE_ELEMENT = eINSTANCE.getTemplateableElement();

		/**
		 * The meta object literal for the '<em><b>Owned Bindings</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATEABLE_ELEMENT__OWNED_BINDINGS = eINSTANCE.getTemplateableElement_OwnedBindings();

		/**
		 * The meta object literal for the '<em><b>Owned Signature</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATEABLE_ELEMENT__OWNED_SIGNATURE = eINSTANCE.getTemplateableElement_OwnedSignature();

		/**
		 * The meta object literal for the '<em><b>Unspecialized Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TEMPLATEABLE_ELEMENT__UNSPECIALIZED_ELEMENT = eINSTANCE.getTemplateableElement_UnspecializedElement();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TransitionImpl <em>Transition</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TransitionImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTransition()
		 * @generated
		 */
		EClass TRANSITION = eINSTANCE.getTransition();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSITION__KIND = eINSTANCE.getTransition_Kind();

		/**
		 * The meta object literal for the '<em><b>Owned Effect</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__OWNED_EFFECT = eINSTANCE.getTransition_OwnedEffect();

		/**
		 * The meta object literal for the '<em><b>Owned Guard</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__OWNED_GUARD = eINSTANCE.getTransition_OwnedGuard();

		/**
		 * The meta object literal for the '<em><b>Owned Triggers</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__OWNED_TRIGGERS = eINSTANCE.getTransition_OwnedTriggers();

		/**
		 * The meta object literal for the '<em><b>Owning Region</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__OWNING_REGION = eINSTANCE.getTransition_OwningRegion();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__SOURCE = eINSTANCE.getTransition_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSITION__TARGET = eINSTANCE.getTransition_Target();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TriggerImpl <em>Trigger</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TriggerImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTrigger()
		 * @generated
		 */
		EClass TRIGGER = eINSTANCE.getTrigger();

		/**
		 * The meta object literal for the '<em><b>Owning State</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__OWNING_STATE = eINSTANCE.getTrigger_OwningState();

		/**
		 * The meta object literal for the '<em><b>Owning Transition</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRIGGER__OWNING_TRANSITION = eINSTANCE.getTrigger_OwningTransition();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TupleLiteralExpImpl <em>Tuple Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TupleLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTupleLiteralExp()
		 * @generated
		 */
		EClass TUPLE_LITERAL_EXP = eINSTANCE.getTupleLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Owned Parts</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE_LITERAL_EXP__OWNED_PARTS = eINSTANCE.getTupleLiteralExp_OwnedParts();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TupleLiteralPartImpl <em>Tuple Literal Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TupleLiteralPartImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTupleLiteralPart()
		 * @generated
		 */
		EClass TUPLE_LITERAL_PART = eINSTANCE.getTupleLiteralPart();

		/**
		 * The meta object literal for the '<em><b>Owned Init</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TUPLE_LITERAL_PART__OWNED_INIT = eINSTANCE.getTupleLiteralPart_OwnedInit();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TupleTypeImpl <em>Tuple Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TupleTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTupleType()
		 * @generated
		 */
		EClass TUPLE_TYPE = eINSTANCE.getTupleType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TypeImpl <em>Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getType()
		 * @generated
		 */
		EClass TYPE = eINSTANCE.getType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TypeExpImpl <em>Type Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TypeExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTypeExp()
		 * @generated
		 */
		EClass TYPE_EXP = eINSTANCE.getTypeExp();

		/**
		 * The meta object literal for the '<em><b>Referred Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPE_EXP__REFERRED_TYPE = eINSTANCE.getTypeExp_ReferredType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.TypedElementImpl <em>Typed Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.TypedElementImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTypedElement()
		 * @generated
		 */
		EClass TYPED_ELEMENT = eINSTANCE.getTypedElement();

		/**
		 * The meta object literal for the '<em><b>Is Many</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPED_ELEMENT__IS_MANY = eINSTANCE.getTypedElement_IsMany();

		/**
		 * The meta object literal for the '<em><b>Is Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPED_ELEMENT__IS_REQUIRED = eINSTANCE.getTypedElement_IsRequired();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TYPED_ELEMENT__TYPE = eINSTANCE.getTypedElement_Type();

		/**
		 * The meta object literal for the '{@link java.util.Collection <em>Unique Collection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Collection
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getUniqueCollection()
		 * @generated
		 */
		EClass UNIQUE_COLLECTION = eINSTANCE.getUniqueCollection();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.UnlimitedNaturalLiteralExpImpl <em>Unlimited Natural Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.UnlimitedNaturalLiteralExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getUnlimitedNaturalLiteralExp()
		 * @generated
		 */
		EClass UNLIMITED_NATURAL_LITERAL_EXP = eINSTANCE.getUnlimitedNaturalLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Unlimited Natural Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute UNLIMITED_NATURAL_LITERAL_EXP__UNLIMITED_NATURAL_SYMBOL = eINSTANCE.getUnlimitedNaturalLiteralExp_UnlimitedNaturalSymbol();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.UnspecifiedValueExpImpl <em>Unspecified Value Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.UnspecifiedValueExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getUnspecifiedValueExp()
		 * @generated
		 */
		EClass UNSPECIFIED_VALUE_EXP = eINSTANCE.getUnspecifiedValueExp();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.ValueSpecificationImpl <em>Value Specification</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.ValueSpecificationImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getValueSpecification()
		 * @generated
		 */
		EClass VALUE_SPECIFICATION = eINSTANCE.getValueSpecification();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.VariableImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getVariable()
		 * @generated
		 */
		EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Is Implicit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE__IS_IMPLICIT = eINSTANCE.getVariable_IsImplicit();

		/**
		 * The meta object literal for the '<em><b>Owned Init</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE__OWNED_INIT = eINSTANCE.getVariable_OwnedInit();

		/**
		 * The meta object literal for the '<em><b>Represented Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE__REPRESENTED_PARAMETER = eINSTANCE.getVariable_RepresentedParameter();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.VariableDeclarationImpl <em>Variable Declaration</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.VariableDeclarationImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getVariableDeclaration()
		 * @generated
		 */
		EClass VARIABLE_DECLARATION = eINSTANCE.getVariableDeclaration();

		/**
		 * The meta object literal for the '<em><b>Type Value</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_DECLARATION__TYPE_VALUE = eINSTANCE.getVariableDeclaration_TypeValue();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.VariableExpImpl <em>Variable Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.VariableExpImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getVariableExp()
		 * @generated
		 */
		EClass VARIABLE_EXP = eINSTANCE.getVariableExp();

		/**
		 * The meta object literal for the '<em><b>Is Implicit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VARIABLE_EXP__IS_IMPLICIT = eINSTANCE.getVariableExp_IsImplicit();

		/**
		 * The meta object literal for the '<em><b>Referred Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VARIABLE_EXP__REFERRED_VARIABLE = eINSTANCE.getVariableExp_ReferredVariable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.VertexImpl <em>Vertex</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.VertexImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getVertex()
		 * @generated
		 */
		EClass VERTEX = eINSTANCE.getVertex();

		/**
		 * The meta object literal for the '<em><b>Incoming Transitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__INCOMING_TRANSITIONS = eINSTANCE.getVertex_IncomingTransitions();

		/**
		 * The meta object literal for the '<em><b>Outgoing Transitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__OUTGOING_TRANSITIONS = eINSTANCE.getVertex_OutgoingTransitions();

		/**
		 * The meta object literal for the '<em><b>Owning Region</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VERTEX__OWNING_REGION = eINSTANCE.getVertex_OwningRegion();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.pivot.util.Visitable <em>Visitable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.util.Visitable
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getVisitable()
		 * @generated
		 */
		EClass VISITABLE = eINSTANCE.getVisitable();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.VoidTypeImpl <em>Void Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.VoidTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getVoidType()
		 * @generated
		 */
		EClass VOID_TYPE = eINSTANCE.getVoidType();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.internal.WildcardTypeImpl <em>Wildcard Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.internal.WildcardTypeImpl
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getWildcardType()
		 * @generated
		 */
		EClass WILDCARD_TYPE = eINSTANCE.getWildcardType();

		/**
		 * The meta object literal for the '<em><b>Lower Bound</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WILDCARD_TYPE__LOWER_BOUND = eINSTANCE.getWildcardType_LowerBound();

		/**
		 * The meta object literal for the '<em><b>Upper Bound</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WILDCARD_TYPE__UPPER_BOUND = eINSTANCE.getWildcardType_UpperBound();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.AssociativityKind <em>Associativity Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.AssociativityKind
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getAssociativityKind()
		 * @generated
		 */
		EEnum ASSOCIATIVITY_KIND = eINSTANCE.getAssociativityKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.CollectionKind <em>Collection Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.CollectionKind
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getCollectionKind()
		 * @generated
		 */
		EEnum COLLECTION_KIND = eINSTANCE.getCollectionKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.PseudostateKind <em>Pseudostate Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.PseudostateKind
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getPseudostateKind()
		 * @generated
		 */
		EEnum PSEUDOSTATE_KIND = eINSTANCE.getPseudostateKind();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.ocl.TransitionKind <em>Transition Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.ocl.TransitionKind
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getTransitionKind()
		 * @generated
		 */
		EEnum TRANSITION_KIND = eINSTANCE.getTransitionKind();

		/**
		 * The meta object literal for the '<em>Boolean</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Boolean
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getBoolean()
		 * @generated
		 */
		EDataType BOOLEAN = eINSTANCE.getBoolean();

		/**
		 * The meta object literal for the '<em>Ecore Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.ecore.EObject
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getEcoreObject()
		 * @generated
		 */
		EDataType ECORE_OBJECT = eINSTANCE.getEcoreObject();

		/**
		 * The meta object literal for the '<em>Integer</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.IntegerValue
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getInteger()
		 * @generated
		 */
		EDataType INTEGER = eINSTANCE.getInteger();

		/**
		 * The meta object literal for the '<em>Java Class</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Class
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getJavaClass()
		 * @generated
		 */
		EDataType JAVA_CLASS = eINSTANCE.getJavaClass();

		/**
		 * The meta object literal for the '<em>Library Feature</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.library.LibraryFeature
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getLibraryFeature()
		 * @generated
		 */
		EDataType LIBRARY_FEATURE = eINSTANCE.getLibraryFeature();

		/**
		 * The meta object literal for the '<em>Object</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Object
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getObject()
		 * @generated
		 */
		EDataType OBJECT = eINSTANCE.getObject();

		/**
		 * The meta object literal for the '<em>Real</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.RealValue
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getReal()
		 * @generated
		 */
		EDataType REAL = eINSTANCE.getReal();

		/**
		 * The meta object literal for the '<em>String</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getString()
		 * @generated
		 */
		EDataType STRING = eINSTANCE.getString();

		/**
		 * The meta object literal for the '<em>Throwable</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.Throwable
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getThrowable()
		 * @generated
		 */
		EDataType THROWABLE = eINSTANCE.getThrowable();

		/**
		 * The meta object literal for the '<em>Unlimited Natural</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.pivot.values.UnlimitedNaturalValue
		 * @see org.eclipse.ocl.ocl.internal.OCLASPackageImpl#getUnlimitedNatural()
		 * @generated
		 */
		EDataType UNLIMITED_NATURAL = eINSTANCE.getUnlimitedNatural();

	}

} //OCLASPackage
