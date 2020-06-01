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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
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
 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore"
 * @generated
 */
public interface CodegencompanyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "company"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/test/Pivot/Company.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "co"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CodegencompanyPackage eINSTANCE = org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CodegencompanyPackageImpl.init();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Company</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Company
	 * @generated
	 */
	EClass getCompany();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getName()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getEmployees <em>Employees</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Employees</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getEmployees()
	 * @see #getCompany()
	 * @generated
	 */
	EReference getCompany_Employees();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#getSize()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Size();

	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#dummyInvariant(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Dummy Invariant</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Dummy Invariant</em>' operation.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Company#dummyInvariant(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getCompany__DummyInvariant__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee <em>Employee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Employee</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee
	 * @generated
	 */
	EClass getEmployee();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getName()
	 * @see #getEmployee()
	 * @generated
	 */
	EAttribute getEmployee_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getManager <em>Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Manager</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getManager()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_Manager();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Company</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getCompany()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_Company();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getDirectReports <em>Direct Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Direct Reports</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getDirectReports()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_DirectReports();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getAllReports <em>All Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>All Reports</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getAllReports()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_AllReports();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getReportingChain <em>Reporting Chain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reporting Chain</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#getReportingChain()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_ReportingChain();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#isHasNameAsAttribute <em>Has Name As Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Name As Attribute</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#isHasNameAsAttribute()
	 * @see #getEmployee()
	 * @generated
	 */
	EAttribute getEmployee_HasNameAsAttribute();

	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#reportsTo(org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee) <em>Reports To</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reports To</em>' operation.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#reportsTo(org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee)
	 * @generated
	 */
	EOperation getEmployee__ReportsTo__Employee();

	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#hasNameAsOperation() <em>Has Name As Operation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Has Name As Operation</em>' operation.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#hasNameAsOperation()
	 * @generated
	 */
	EOperation getEmployee__HasNameAsOperation();

	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#noManagerImpliesDirectReports(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>No Manager Implies Direct Reports</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>No Manager Implies Direct Reports</em>' operation.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#noManagerImpliesDirectReports(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getEmployee__NoManagerImpliesDirectReports__DiagnosticChain_Map();

	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#mustHaveName(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Must Have Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Must Have Name</em>' operation.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#mustHaveName(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getEmployee__MustHaveName__DiagnosticChain_Map();

	/**
	 * Returns the meta object for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#mustHaveNonEmptyName(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map) <em>Must Have Non Empty Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Must Have Non Empty Name</em>' operation.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Employee#mustHaveNonEmptyName(org.eclipse.emf.common.util.DiagnosticChain, java.util.Map)
	 * @generated
	 */
	EOperation getEmployee__MustHaveNonEmptyName__DiagnosticChain_Map();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716 <em>Bug418716</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bug418716</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716
	 * @generated
	 */
	EClass getBug418716();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716#getAttributeWithInitital <em>Attribute With Initital</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute With Initital</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716#getAttributeWithInitital()
	 * @see #getBug418716()
	 * @generated
	 */
	EAttribute getBug418716_AttributeWithInitital();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716#getAttributeWithoutInitital <em>Attribute Without Initital</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Attribute Without Initital</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.Bug418716#getAttributeWithoutInitital()
	 * @see #getBug418716()
	 * @generated
	 */
	EAttribute getBug418716_AttributeWithoutInitital();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind <em>Company Size Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Company Size Kind</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind
	 * @generated
	 */
	EEnum getCompanySizeKind();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CodegencompanyFactory getCodegencompanyFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CompanyImpl <em>Company</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CompanyImpl
		 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CodegencompanyPackageImpl#getCompany()
		 * @generated
		 */
		EClass COMPANY = eINSTANCE.getCompany();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__NAME = eINSTANCE.getCompany_Name();

		/**
		 * The meta object literal for the '<em><b>Employees</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPANY__EMPLOYEES = eINSTANCE.getCompany_Employees();

		/**
		 * The meta object literal for the '<em><b>Size</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPANY__SIZE = eINSTANCE.getCompany_Size();

		/**
		 * The meta object literal for the '<em><b>Dummy Invariant</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation COMPANY___DUMMY_INVARIANT__DIAGNOSTICCHAIN_MAP = eINSTANCE.getCompany__DummyInvariant__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.EmployeeImpl <em>Employee</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.EmployeeImpl
		 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CodegencompanyPackageImpl#getEmployee()
		 * @generated
		 */
		EClass EMPLOYEE = eINSTANCE.getEmployee();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMPLOYEE__NAME = eINSTANCE.getEmployee_Name();

		/**
		 * The meta object literal for the '<em><b>Manager</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMPLOYEE__MANAGER = eINSTANCE.getEmployee_Manager();

		/**
		 * The meta object literal for the '<em><b>Company</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMPLOYEE__COMPANY = eINSTANCE.getEmployee_Company();

		/**
		 * The meta object literal for the '<em><b>Direct Reports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMPLOYEE__DIRECT_REPORTS = eINSTANCE.getEmployee_DirectReports();

		/**
		 * The meta object literal for the '<em><b>All Reports</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMPLOYEE__ALL_REPORTS = eINSTANCE.getEmployee_AllReports();

		/**
		 * The meta object literal for the '<em><b>Reporting Chain</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMPLOYEE__REPORTING_CHAIN = eINSTANCE.getEmployee_ReportingChain();

		/**
		 * The meta object literal for the '<em><b>Has Name As Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMPLOYEE__HAS_NAME_AS_ATTRIBUTE = eINSTANCE.getEmployee_HasNameAsAttribute();

		/**
		 * The meta object literal for the '<em><b>Reports To</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation EMPLOYEE___REPORTS_TO__EMPLOYEE = eINSTANCE.getEmployee__ReportsTo__Employee();

		/**
		 * The meta object literal for the '<em><b>Has Name As Operation</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation EMPLOYEE___HAS_NAME_AS_OPERATION = eINSTANCE.getEmployee__HasNameAsOperation();

		/**
		 * The meta object literal for the '<em><b>No Manager Implies Direct Reports</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation EMPLOYEE___NO_MANAGER_IMPLIES_DIRECT_REPORTS__DIAGNOSTICCHAIN_MAP = eINSTANCE.getEmployee__NoManagerImpliesDirectReports__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '<em><b>Must Have Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation EMPLOYEE___MUST_HAVE_NAME__DIAGNOSTICCHAIN_MAP = eINSTANCE.getEmployee__MustHaveName__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '<em><b>Must Have Non Empty Name</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation EMPLOYEE___MUST_HAVE_NON_EMPTY_NAME__DIAGNOSTICCHAIN_MAP = eINSTANCE.getEmployee__MustHaveNonEmptyName__DiagnosticChain_Map();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.Bug418716Impl <em>Bug418716</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.Bug418716Impl
		 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CodegencompanyPackageImpl#getBug418716()
		 * @generated
		 */
		EClass BUG418716 = eINSTANCE.getBug418716();

		/**
		 * The meta object literal for the '<em><b>Attribute With Initital</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUG418716__ATTRIBUTE_WITH_INITITAL = eINSTANCE.getBug418716_AttributeWithInitital();

		/**
		 * The meta object literal for the '<em><b>Attribute Without Initital</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUG418716__ATTRIBUTE_WITHOUT_INITITAL = eINSTANCE.getBug418716_AttributeWithoutInitital();

		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind <em>Company Size Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CompanySizeKind
		 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.impl.CodegencompanyPackageImpl#getCompanySizeKind()
		 * @generated
		 */
		EEnum COMPANY_SIZE_KIND = eINSTANCE.getCompanySizeKind();

	}

} //CodegencompanyPackage
