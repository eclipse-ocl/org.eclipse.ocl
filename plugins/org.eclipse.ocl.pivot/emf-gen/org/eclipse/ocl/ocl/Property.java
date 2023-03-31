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
 * A representation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getAssociationClass <em>Association Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getDefaultValueString <em>Default Value String</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getIsComposite <em>Is Composite</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getIsDerived <em>Is Derived</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getIsID <em>Is ID</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getIsImplicit <em>Is Implicit</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getIsResolveProxies <em>Is Resolve Proxies</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getIsTransient <em>Is Transient</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getIsUnsettable <em>Is Unsettable</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getIsVolatile <em>Is Volatile</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getKeys <em>Keys</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getOwnedExpression <em>Owned Expression</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getOwningClass <em>Owning Class</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getRedefinedProperties <em>Redefined Properties</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getReferredProperty <em>Referred Property</em>}</li>
 *   <li>{@link org.eclipse.ocl.ocl.Property#getSubsettedProperty <em>Subsetted Property</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty()
 * @generated
 */
public interface Property extends Feature
{
	/**
	 * Returns the value of the '<em><b>Association Class</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.AssociationClass#getUnownedAttributes <em>Unowned Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Class</em>' reference.
	 * @see #setAssociationClass(AssociationClass)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_AssociationClass()
	 * @see org.eclipse.ocl.ocl.AssociationClass#getUnownedAttributes
	 * @generated
	 */
	AssociationClass getAssociationClass();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getAssociationClass <em>Association Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Class</em>' reference.
	 * @see #getAssociationClass()
	 * @generated
	 */
	void setAssociationClass(AssociationClass value);

	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value</em>' attribute.
	 * @see #setDefaultValue(Object)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_DefaultValue()
	 * @generated
	 */
	Object getDefaultValue();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getDefaultValue <em>Default Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' attribute.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(Object value);

	/**
	 * Returns the value of the '<em><b>Default Value String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Default Value String</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Default Value String</em>' attribute.
	 * @see #setDefaultValueString(String)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_DefaultValueString()
	 * @generated
	 */
	String getDefaultValueString();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getDefaultValueString <em>Default Value String</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value String</em>' attribute.
	 * @see #getDefaultValueString()
	 * @generated
	 */
	void setDefaultValueString(String value);

	/**
	 * Returns the value of the '<em><b>Is Composite</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Composite</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Composite</em>' attribute.
	 * @see #setIsComposite(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_IsComposite()
	 * @generated
	 */
	Boolean getIsComposite();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getIsComposite <em>Is Composite</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Composite</em>' attribute.
	 * @see #getIsComposite()
	 * @generated
	 */
	void setIsComposite(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Derived</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether the Property is derived, i.e., whether its value or values can be computed from other information.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Derived</em>' attribute.
	 * @see #setIsDerived(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_IsDerived()
	 * @generated
	 */
	Boolean getIsDerived();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getIsDerived <em>Is Derived</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Derived</em>' attribute.
	 * @see #getIsDerived()
	 * @generated
	 */
	void setIsDerived(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is ID</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * True indicates this property can be used to uniquely identify an instance of the containing Class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is ID</em>' attribute.
	 * @see #setIsID(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_IsID()
	 * @generated
	 */
	Boolean getIsID();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getIsID <em>Is ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is ID</em>' attribute.
	 * @see #getIsID()
	 * @generated
	 */
	void setIsID(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Implicit</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Implicit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Implicit</em>' attribute.
	 * @see #setIsImplicit(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_IsImplicit()
	 * @generated
	 */
	Boolean getIsImplicit();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getIsImplicit <em>Is Implicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Implicit</em>' attribute.
	 * @see #getIsImplicit()
	 * @generated
	 */
	void setIsImplicit(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Read Only</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If isReadOnly is true, the StructuralFeature may not be written to after initialization.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Read Only</em>' attribute.
	 * @see #setIsReadOnly(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_IsReadOnly()
	 * @generated
	 */
	Boolean getIsReadOnly();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getIsReadOnly <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Read Only</em>' attribute.
	 * @see #getIsReadOnly()
	 * @generated
	 */
	void setIsReadOnly(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Resolve Proxies</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Resolve Proxies</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Resolve Proxies</em>' attribute.
	 * @see #setIsResolveProxies(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_IsResolveProxies()
	 * @generated
	 */
	Boolean getIsResolveProxies();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getIsResolveProxies <em>Is Resolve Proxies</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Resolve Proxies</em>' attribute.
	 * @see #getIsResolveProxies()
	 * @generated
	 */
	void setIsResolveProxies(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Transient</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Transient</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Transient</em>' attribute.
	 * @see #setIsTransient(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_IsTransient()
	 * @generated
	 */
	Boolean getIsTransient();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getIsTransient <em>Is Transient</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Transient</em>' attribute.
	 * @see #getIsTransient()
	 * @generated
	 */
	void setIsTransient(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Unsettable</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Unsettable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Unsettable</em>' attribute.
	 * @see #setIsUnsettable(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_IsUnsettable()
	 * @generated
	 */
	Boolean getIsUnsettable();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getIsUnsettable <em>Is Unsettable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Unsettable</em>' attribute.
	 * @see #getIsUnsettable()
	 * @generated
	 */
	void setIsUnsettable(Boolean value);

	/**
	 * Returns the value of the '<em><b>Is Volatile</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Volatile</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Volatile</em>' attribute.
	 * @see #setIsVolatile(Boolean)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_IsVolatile()
	 * @generated
	 */
	Boolean getIsVolatile();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getIsVolatile <em>Is Volatile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Volatile</em>' attribute.
	 * @see #getIsVolatile()
	 * @generated
	 */
	void setIsVolatile(Boolean value);

	/**
	 * Returns the value of the '<em><b>Keys</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Property}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Keys</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Keys</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_Keys()
	 * @generated
	 */
	List<Property> getKeys();

	/**
	 * Returns the value of the '<em><b>Opposite</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * In the case where the Property is one end of a binary association this gives the other end.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Opposite</em>' reference.
	 * @see #setOpposite(Property)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_Opposite()
	 * @generated
	 */
	Property getOpposite();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getOpposite <em>Opposite</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Opposite</em>' reference.
	 * @see #getOpposite()
	 * @generated
	 */
	void setOpposite(Property value);

	/**
	 * Returns the value of the '<em><b>Owned Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Expression</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Expression</em>' containment reference.
	 * @see #setOwnedExpression(LanguageExpression)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_OwnedExpression()
	 * @generated
	 */
	LanguageExpression getOwnedExpression();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getOwnedExpression <em>Owned Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owned Expression</em>' containment reference.
	 * @see #getOwnedExpression()
	 * @generated
	 */
	void setOwnedExpression(LanguageExpression value);

	/**
	 * Returns the value of the '<em><b>Owning Class</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.ocl.Class#getOwnedProperties <em>Owned Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Class that owns this Property, if any.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owning Class</em>' container reference.
	 * @see #setOwningClass(org.eclipse.ocl.ocl.Class)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_OwningClass()
	 * @see org.eclipse.ocl.ocl.Class#getOwnedProperties
	 * @generated
	 */
	org.eclipse.ocl.ocl.Class getOwningClass();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getOwningClass <em>Owning Class</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owning Class</em>' container reference.
	 * @see #getOwningClass()
	 * @generated
	 */
	void setOwningClass(org.eclipse.ocl.ocl.Class value);

	/**
	 * Returns the value of the '<em><b>Redefined Properties</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The properties that are redefined by this property, if any.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Redefined Properties</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_RedefinedProperties()
	 * @generated
	 */
	List<Property> getRedefinedProperties();

	/**
	 * Returns the value of the '<em><b>Referred Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referred Property</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Property</em>' reference.
	 * @see #setReferredProperty(Property)
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_ReferredProperty()
	 * @generated
	 */
	Property getReferredProperty();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.ocl.Property#getReferredProperty <em>Referred Property</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Referred Property</em>' reference.
	 * @see #getReferredProperty()
	 * @generated
	 */
	void setReferredProperty(Property value);

	/**
	 * Returns the value of the '<em><b>Subsetted Property</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.ocl.Property}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The properties of which this Property is constrained to be a subset, if any.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Subsetted Property</em>' reference list.
	 * @see org.eclipse.ocl.ocl.OCLASPackage#getProperty_SubsettedProperty()
	 * @generated
	 */
	List<Property> getSubsettedProperty();

} // Property
