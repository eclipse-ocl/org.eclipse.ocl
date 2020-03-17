/**
 * <copyright>
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests.noreflectioncompany;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.NoreflectioncompanyFactory
 * @model kind="package"
 *        annotation="http://www.eclipse.org/OCL/Import ecore='http://www.eclipse.org/emf/2002/Ecore'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore invocationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' settingDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot' validationDelegates='http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot'"
 * @generated
 */
public interface NoreflectioncompanyPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "noreflectioncompany"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/ocl/test/2010/NoReflectionCompany.ecore"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "nrco"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	NoreflectioncompanyPackage eINSTANCE = org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.NoreflectioncompanyPackageImpl.init();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Company <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Company</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Company
	 * @generated
	 */
	EClass getCompany();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Company#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Company#getName()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Company#getEmployees <em>Employees</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Employees</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Company#getEmployees()
	 * @see #getCompany()
	 * @generated
	 */
	EReference getCompany_Employees();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Company#getSize <em>Size</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Size</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Company#getSize()
	 * @see #getCompany()
	 * @generated
	 */
	EAttribute getCompany_Size();

	/**
	 * Returns the meta object for class '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee <em>Employee</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Employee</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee
	 * @generated
	 */
	EClass getEmployee();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getName()
	 * @see #getEmployee()
	 * @generated
	 */
	EAttribute getEmployee_Name();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getManager <em>Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Manager</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getManager()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_Manager();

	/**
	 * Returns the meta object for the container reference '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getCompany <em>Company</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Company</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getCompany()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_Company();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getDirectReports <em>Direct Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Direct Reports</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getDirectReports()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_DirectReports();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getAllReports <em>All Reports</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>All Reports</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getAllReports()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_AllReports();

	/**
	 * Returns the meta object for the reference list '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getReportingChain <em>Reporting Chain</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reporting Chain</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#getReportingChain()
	 * @see #getEmployee()
	 * @generated
	 */
	EReference getEmployee_ReportingChain();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#isHasNameAsAttribute <em>Has Name As Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Has Name As Attribute</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.Employee#isHasNameAsAttribute()
	 * @see #getEmployee()
	 * @generated
	 */
	EAttribute getEmployee_HasNameAsAttribute();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.CompanySizeKind <em>Company Size Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Company Size Kind</em>'.
	 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.CompanySizeKind
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
	NoreflectioncompanyFactory getNoreflectioncompanyFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.CompanyImpl <em>Company</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.CompanyImpl
		 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.NoreflectioncompanyPackageImpl#getCompany()
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
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.EmployeeImpl <em>Employee</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.EmployeeImpl
		 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.NoreflectioncompanyPackageImpl#getEmployee()
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
		 * The meta object literal for the '{@link org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.CompanySizeKind <em>Company Size Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.CompanySizeKind
		 * @see org.eclipse.ocl.examples.xtext.tests.noreflectioncompany.impl.NoreflectioncompanyPackageImpl#getCompanySizeKind()
		 * @generated
		 */
		EEnum COMPANY_SIZE_KIND = eINSTANCE.getCompanySizeKind();

	}

} //NoreflectioncompanyPackage
