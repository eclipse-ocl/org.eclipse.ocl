/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.basecs;

import org.eclipse.emf.common.util.EList;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.internal.scoping.ScopeFilter;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Qualified Element Ref CS</b></em>'.
 * @extends org.eclipse.ocl.pivot.utilities.Pivotable
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getContext <em>Context</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getOwnedPathElements <em>Owned Path Elements</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getReferredElement <em>Referred Element</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getScopeFilter <em>Scope Filter</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getSerialized <em>Serialized</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getParameterName <em>Parameter Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getResultName <em>Result Name</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathNameCS()
 * @model
 * @generated
 */
public interface PathNameCS extends ElementCS, org.eclipse.ocl.pivot.utilities.Pivotable
{
	/**
	 * Returns the value of the '<em><b>Owned Path Elements</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.xtext.basecs.PathElementCS}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.xtext.basecs.PathElementCS#getOwningPathName <em>Owning Path Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Path Elements</em>' containment reference list.
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathNameCS_OwnedPathElements()
	 * @see org.eclipse.ocl.xtext.basecs.PathElementCS#getOwningPathName
	 * @model opposite="owningPathName" containment="true" required="true"
	 * @generated
	 */
	EList<PathElementCS> getOwnedPathElements();

	/**
	 * Returns the value of the '<em><b>Referred Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Element</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referred Element</em>' reference.
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathNameCS_ReferredElement()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	Element getReferredElement();

	/**
	 * Returns the value of the '<em><b>Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Context</em>' reference.
	 * @see #isSetContext()
	 * @see #unsetContext()
	 * @see #setContext(ElementCS)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathNameCS_Context()
	 * @model resolveProxies="false" unsettable="true" transient="true"
	 * @generated
	 */
	ElementCS getContext();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getContext <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Context</em>' reference.
	 * @see #isSetContext()
	 * @see #unsetContext()
	 * @see #getContext()
	 * @generated
	 */
	void setContext(ElementCS value);

	/**
	 * Unsets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getContext <em>Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetContext()
	 * @see #getContext()
	 * @see #setContext(ElementCS)
	 * @generated
	 */
	void unsetContext();

	/**
	 * Returns whether the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getContext <em>Context</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Context</em>' reference is set.
	 * @see #unsetContext()
	 * @see #getContext()
	 * @see #setContext(ElementCS)
	 * @generated
	 */
	boolean isSetContext();

	/**
	 * Returns the value of the '<em><b>Scope Filter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope Filter</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope Filter</em>' attribute.
	 * @see #setScopeFilter(ScopeFilter)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathNameCS_ScopeFilter()
	 * @model dataType="org.eclipse.ocl.xtext.basecs.ScopeFilter" transient="true"
	 * @generated
	 */
	ScopeFilter getScopeFilter();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getScopeFilter <em>Scope Filter</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope Filter</em>' attribute.
	 * @see #getScopeFilter()
	 * @generated
	 */
	void setScopeFilter(ScopeFilter value);

	/**
	 * Returns the value of the '<em><b>Serialized</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Serialized</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The derived serialized value persists the path name when the CS resource is saved and loaded directly. The textual values are normally provided by Xtext's Node model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Serialized</em>' attribute.
	 * @see #isSetSerialized()
	 * @see #unsetSerialized()
	 * @see #setSerialized(String)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathNameCS_Serialized()
	 * @model unsettable="true" volatile="true" derived="true"
	 * @generated
	 */
	String getSerialized();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getSerialized <em>Serialized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Serialized</em>' attribute.
	 * @see #isSetSerialized()
	 * @see #unsetSerialized()
	 * @see #getSerialized()
	 * @generated
	 */
	void setSerialized(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getSerialized <em>Serialized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSerialized()
	 * @see #getSerialized()
	 * @see #setSerialized(String)
	 * @generated
	 */
	void unsetSerialized();

	/**
	 * Returns whether the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getSerialized <em>Serialized</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Serialized</em>' attribute is set.
	 * @see #unsetSerialized()
	 * @see #getSerialized()
	 * @see #setSerialized(String)
	 * @generated
	 */
	boolean isSetSerialized();

	/**
	 * Returns the value of the '<em><b>Parameter Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter Name</em>' attribute.
	 * @see #isSetParameterName()
	 * @see #unsetParameterName()
	 * @see #setParameterName(String)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathNameCS_ParameterName()
	 * @model unsettable="true" volatile="true" derived="true"
	 * @generated
	 */
	String getParameterName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getParameterName <em>Parameter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Name</em>' attribute.
	 * @see #isSetParameterName()
	 * @see #unsetParameterName()
	 * @see #getParameterName()
	 * @generated
	 */
	void setParameterName(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getParameterName <em>Parameter Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetParameterName()
	 * @see #getParameterName()
	 * @see #setParameterName(String)
	 * @generated
	 */
	void unsetParameterName();

	/**
	 * Returns whether the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getParameterName <em>Parameter Name</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Parameter Name</em>' attribute is set.
	 * @see #unsetParameterName()
	 * @see #getParameterName()
	 * @see #setParameterName(String)
	 * @generated
	 */
	boolean isSetParameterName();

	/**
	 * Returns the value of the '<em><b>Result Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Name</em>' attribute.
	 * @see #isSetResultName()
	 * @see #unsetResultName()
	 * @see #setResultName(String)
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#getPathNameCS_ResultName()
	 * @model unsettable="true" volatile="true" derived="true"
	 * @generated
	 */
	String getResultName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getResultName <em>Result Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Name</em>' attribute.
	 * @see #isSetResultName()
	 * @see #unsetResultName()
	 * @see #getResultName()
	 * @generated
	 */
	void setResultName(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getResultName <em>Result Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResultName()
	 * @see #getResultName()
	 * @see #setResultName(String)
	 * @generated
	 */
	void unsetResultName();

	/**
	 * Returns whether the value of the '{@link org.eclipse.ocl.xtext.basecs.PathNameCS#getResultName <em>Result Name</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Result Name</em>' attribute is set.
	 * @see #unsetResultName()
	 * @see #getResultName()
	 * @see #setResultName(String)
	 * @generated
	 */
	boolean isSetResultName();

} // QualifiedElementRefCS
