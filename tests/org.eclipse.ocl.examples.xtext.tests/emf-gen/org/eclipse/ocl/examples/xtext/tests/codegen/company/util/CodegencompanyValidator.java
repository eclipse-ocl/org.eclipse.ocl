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
package org.eclipse.ocl.examples.xtext.tests.codegen.company.util;

import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.ocl.examples.xtext.tests.codegen.company.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.examples.xtext.tests.codegen.company.CodegencompanyPackage
 * @generated
 */
public class CodegencompanyValidator extends EObjectValidator {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final CodegencompanyValidator INSTANCE = new CodegencompanyValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.ocl.examples.xtext.tests.codegen.company"; //$NON-NLS-1$

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Dummy Invariant' of 'Company'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int COMPANY__DUMMY_INVARIANT = 1;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'No Manager Implies Direct Reports' of 'Employee'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int EMPLOYEE__NO_MANAGER_IMPLIES_DIRECT_REPORTS = 2;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Must Have Name' of 'Employee'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int EMPLOYEE__MUST_HAVE_NAME = 3;

	/**
	 * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint 'Must Have Non Empty Name' of 'Employee'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final int EMPLOYEE__MUST_HAVE_NON_EMPTY_NAME = 4;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 4;

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CodegencompanyValidator() {
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage() {
	  return CodegencompanyPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context) {
		switch (classifierID) {
			case 0:
				return validateCompany((Company)value, diagnostics, context);
			case 1:
				return validateEmployee((Employee)value, diagnostics, context);
			case 2:
				return validateBug418716((Bug418716)value, diagnostics, context);
			case 3:
				return validateCompanySizeKind((CompanySizeKind)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompany(Company company, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(company, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(company, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(company, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(company, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(company, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(company, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(company, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(company, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(company, diagnostics, context);
		if (result || diagnostics != null) result &= validateCompany_dummyInvariant(company, diagnostics, context);
		return result;
	}

	/**
	 * Validates the dummyInvariant constraint of '<em>Company</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompany_dummyInvariant(Company company, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return company.dummyInvariant(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEmployee(Employee employee, DiagnosticChain diagnostics, Map<Object, Object> context) {
		if (!validate_NoCircularContainment(employee, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validateEmployee_mustHaveNonEmptyName(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validateEmployee_noManagerImpliesDirectReports(employee, diagnostics, context);
		if (result || diagnostics != null) result &= validateEmployee_mustHaveName(employee, diagnostics, context);
		return result;
	}

	/**
	 * Validates the mustHaveNonEmptyName constraint of '<em>Employee</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEmployee_mustHaveNonEmptyName(Employee employee, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return employee.mustHaveNonEmptyName(diagnostics, context);
	}

	/**
	 * Validates the noManagerImpliesDirectReports constraint of '<em>Employee</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEmployee_noManagerImpliesDirectReports(Employee employee, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return employee.noManagerImpliesDirectReports(diagnostics, context);
	}

	/**
	 * Validates the mustHaveName constraint of '<em>Employee</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateEmployee_mustHaveName(Employee employee, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return employee.mustHaveName(diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBug418716(Bug418716 bug418716, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return validate_EveryDefaultConstraint(bug418716, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompanySizeKind(CompanySizeKind companySizeKind, DiagnosticChain diagnostics, Map<Object, Object> context) {
		return true;
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //CodegencompanyValidator
