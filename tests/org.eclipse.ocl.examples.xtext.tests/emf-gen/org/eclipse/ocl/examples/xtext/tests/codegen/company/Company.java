/**
 * <copyright>
 * 
 * Copyright (c) 2015, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 * 
 * </copyright>
 */
package org.eclipse.ocl.examples.xtext.tests.codegen.company;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Company</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getEmployees <em>Employees</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getSize <em>Size</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getCompany()
 * @model
 * @generated
 */
public interface Company extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getCompany_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Employees</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee}.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Employees</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Employees</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getCompany_Employees()
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany
	 * @model opposite="company" containment="true"
	 * @generated
	 */
	EList<Employee> getEmployees();

	/**
	 * Returns the value of the '<em><b>Size</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Size</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Size</em>' attribute.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getCompany_Size()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	CompanySizeKind getSize();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot body='true'"
	 * @generated
	 */
	boolean dummyInvariant(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Company
