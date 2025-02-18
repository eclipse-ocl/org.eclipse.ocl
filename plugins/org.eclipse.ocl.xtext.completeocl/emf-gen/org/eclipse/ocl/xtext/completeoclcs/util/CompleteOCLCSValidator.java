/**
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.completeoclcs.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.ocl.xtext.completeoclcs.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.xtext.completeoclcs.CompleteOCLCSPackage
 * @generated
 */
public class CompleteOCLCSValidator extends EObjectValidator
{
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final CompleteOCLCSValidator INSTANCE = new CompleteOCLCSValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.ocl.xtext.completeoclcs";

	/**
	 * A constant with a fixed name that can be used as the base value for additional hand written constants.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final int GENERATED_DIAGNOSTIC_CODE_COUNT = 0;

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
	public CompleteOCLCSValidator()
	{
		super();
	}

	/**
	 * Returns the package of this validator switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EPackage getEPackage()
	{
	  return CompleteOCLCSPackage.eINSTANCE;
	}

	/**
	 * Calls <code>validateXXX</code> for the corresponding classifier of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		switch (classifierID)
		{
			case 0:
				return validateClassifierContextDeclCS((ClassifierContextDeclCS)value, diagnostics, context);
			case 1:
				return validateCompleteOCLDocumentCS((CompleteOCLDocumentCS)value, diagnostics, context);
			case 2:
				return validateContextDeclCS((ContextDeclCS)value, diagnostics, context);
			case 3:
				return validateDefCS((DefCS)value, diagnostics, context);
			case 4:
				return validateDefOperationCS((DefOperationCS)value, diagnostics, context);
			case 5:
				return validateDefPropertyCS((DefPropertyCS)value, diagnostics, context);
			case 6:
				return validateDummyCompleteOCLConstraintClass((DummyCompleteOCLConstraintClass)value, diagnostics, context);
			case 7:
				return validateFeatureContextDeclCS((FeatureContextDeclCS)value, diagnostics, context);
			case 8:
				return validateOCLMessageArgCS((OCLMessageArgCS)value, diagnostics, context);
			case 9:
				return validateOperationContextDeclCS((OperationContextDeclCS)value, diagnostics, context);
			case 10:
				return validatePackageDeclarationCS((PackageDeclarationCS)value, diagnostics, context);
			case 11:
				return validatePathNameDeclCS((PathNameDeclCS)value, diagnostics, context);
			case 12:
				return validatePropertyContextDeclCS((PropertyContextDeclCS)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateClassifierContextDeclCS(ClassifierContextDeclCS classifierContextDeclCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(classifierContextDeclCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCompleteOCLDocumentCS(CompleteOCLDocumentCS completeOCLDocumentCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(completeOCLDocumentCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContextDeclCS(ContextDeclCS contextDeclCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(contextDeclCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefCS(DefCS defCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(defCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefOperationCS(DefOperationCS defOperationCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(defOperationCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDefPropertyCS(DefPropertyCS defPropertyCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(defPropertyCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDummyCompleteOCLConstraintClass(DummyCompleteOCLConstraintClass dummyCompleteOCLConstraintClass, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		if (!validate_NoCircularContainment(dummyCompleteOCLConstraintClass, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dummyCompleteOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dummyCompleteOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dummyCompleteOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dummyCompleteOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dummyCompleteOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dummyCompleteOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dummyCompleteOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dummyCompleteOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateDummyCompleteOCLConstraintClass_DummyConstraint(dummyCompleteOCLConstraintClass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the DummyConstraint constraint of '<em>Dummy Complete OCL Constraint Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDummyCompleteOCLConstraintClass_DummyConstraint(DummyCompleteOCLConstraintClass dummyCompleteOCLConstraintClass, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		// TODO implement the constraint
		// -> specify the condition that violates the constraint
		// -> verify the diagnostic details, including severity, code, and message
		// Ensure that you remove @generated or mark it @generated NOT
		if (false)
		{
			if (diagnostics != null)
			{
				diagnostics.add
					(createDiagnostic
						(Diagnostic.ERROR,
						 DIAGNOSTIC_SOURCE,
						 0,
						 "_UI_GenericConstraint_diagnostic",
						 new Object[] { "DummyConstraint", getObjectLabel(dummyCompleteOCLConstraintClass, context) },
						 new Object[] { dummyCompleteOCLConstraintClass },
						 context));
			}
			return false;
		}
		return true;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateFeatureContextDeclCS(FeatureContextDeclCS featureContextDeclCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(featureContextDeclCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOCLMessageArgCS(OCLMessageArgCS oclMessageArgCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(oclMessageArgCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperationContextDeclCS(OperationContextDeclCS operationContextDeclCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(operationContextDeclCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePackageDeclarationCS(PackageDeclarationCS packageDeclarationCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(packageDeclarationCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePathNameDeclCS(PathNameDeclCS pathNameDeclCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(pathNameDeclCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePropertyContextDeclCS(PropertyContextDeclCS propertyContextDeclCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(propertyContextDeclCS, diagnostics, context);
	}

	/**
	 * Returns the resource locator that will be used to fetch messages for this validator's diagnostics.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator()
	{
		// TODO
		// Specialize this to return a resource locator for messages specific to this validator.
		// Ensure that you remove @generated or mark it @generated NOT
		return super.getResourceLocator();
	}

} //CompleteOCLCSValidator
