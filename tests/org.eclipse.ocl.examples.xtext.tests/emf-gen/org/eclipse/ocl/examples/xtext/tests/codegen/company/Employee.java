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
 * A representation of the model object '<em><b>Employee</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getManager <em>Manager</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany <em>Company</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getDirectReports <em>Direct Reports</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getAllReports <em>All Reports</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getReportingChain <em>Reporting Chain</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#isHasNameAsAttribute <em>Has Name As Attribute</em>}</li>
 * </ul>
 *
 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee()
 * @model annotation="http://www.eclipse.org/emf/2002/Ecore constraints='mustHaveNonEmptyName'"
 * @generated
 */
public interface Employee extends EObject {
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
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Manager</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Manager</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Manager</em>' reference.
	 * @see #setManager(Employee)
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_Manager()
	 * @model
	 * @generated
	 */
	Employee getManager();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getManager <em>Manager</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Manager</em>' reference.
	 * @see #getManager()
	 * @generated
	 */
	void setManager(Employee value);

	/**
	 * Returns the value of the '<em><b>Company</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getEmployees <em>Employees</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Company</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Company</em>' container reference.
	 * @see #setCompany(Company)
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_Company()
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getEmployees
	 * @model opposite="employees" required="true" transient="false"
	 * @generated
	 */
	Company getCompany();

	/**
	 * Sets the value of the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany <em>Company</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Company</em>' container reference.
	 * @see #getCompany()
	 * @generated
	 */
	void setCompany(Company value);

	/**
	 * Returns the value of the '<em><b>Direct Reports</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Direct Reports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Direct Reports</em>' reference list.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_DirectReports()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Employee> getDirectReports();

	/**
	 * Returns the value of the '<em><b>All Reports</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>All Reports</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>All Reports</em>' reference list.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_AllReports()
	 * @model transient="true" changeable="false" volatile="true" derived="true" ordered="false"
	 * @generated
	 */
	EList<Employee> getAllReports();

	/**
	 * Returns the value of the '<em><b>Reporting Chain</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reporting Chain</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reporting Chain</em>' reference list.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_ReportingChain()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	EList<Employee> getReportingChain();

	/**
	 * Returns the value of the '<em><b>Has Name As Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Has Name As Attribute</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Has Name As Attribute</em>' attribute.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage#getEmployee_HasNameAsAttribute()
	 * @model required="true" transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	boolean isHasNameAsAttribute();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot body='self.reportingChain-&gt;includes(manager)'"
	 * @generated
	 */
	boolean reportsTo(Employee manager);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model required="true"
	 *        annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot body='name &lt;&gt; null'"
	 * @generated
	 */
	boolean hasNameAsOperation();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot body='manager.oclIsUndefined() implies directReports-&gt;size() &gt; 0'"
	 * @generated
	 */
	boolean noManagerImpliesDirectReports(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot body='Tuple {\n\tmessage : String = \'Employee must have a name\',\n\tstatus : Boolean = not name.oclIsUndefined() and hasNameAsAttribute and hasNameAsOperation()\n}.status'"
	 * @generated
	 */
	boolean mustHaveName(DiagnosticChain diagnostics, Map<Object, Object> context);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model annotation="http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot body='name-&gt;notEmpty() implies name.size() &gt; 0'"
	 * @generated
	 */
	boolean mustHaveNonEmptyName(DiagnosticChain diagnostics, Map<Object, Object> context);

} // Employee
