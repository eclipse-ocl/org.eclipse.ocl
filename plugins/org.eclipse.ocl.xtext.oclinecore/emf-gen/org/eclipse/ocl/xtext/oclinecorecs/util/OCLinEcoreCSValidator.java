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
package org.eclipse.ocl.xtext.oclinecorecs.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.ocl.xtext.oclinecorecs.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage
 * @generated
 */
public class OCLinEcoreCSValidator extends EObjectValidator
{
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final OCLinEcoreCSValidator INSTANCE = new OCLinEcoreCSValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.ocl.xtext.oclinecorecs";

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
	public OCLinEcoreCSValidator()
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
	  return OCLinEcoreCSPackage.eINSTANCE;
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
				return validateDummyOCLinEcoreConstraintClass((DummyOCLinEcoreConstraintClass)value, diagnostics, context);
			case 1:
				return validateOCLinEcoreConstraintCS((OCLinEcoreConstraintCS)value, diagnostics, context);
			case 2:
				return validateSysMLCS((SysMLCS)value, diagnostics, context);
			case 3:
				return validateTopLevelCS((TopLevelCS)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDummyOCLinEcoreConstraintClass(DummyOCLinEcoreConstraintClass dummyOCLinEcoreConstraintClass, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		if (!validate_NoCircularContainment(dummyOCLinEcoreConstraintClass, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dummyOCLinEcoreConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dummyOCLinEcoreConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dummyOCLinEcoreConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dummyOCLinEcoreConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dummyOCLinEcoreConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dummyOCLinEcoreConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dummyOCLinEcoreConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dummyOCLinEcoreConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateDummyOCLinEcoreConstraintClass_DummyConstraint(dummyOCLinEcoreConstraintClass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the DummyConstraint constraint of '<em>Dummy OC Lin Ecore Constraint Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDummyOCLinEcoreConstraintClass_DummyConstraint(DummyOCLinEcoreConstraintClass dummyOCLinEcoreConstraintClass, DiagnosticChain diagnostics, Map<Object, Object> context)
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
						 new Object[] { "DummyConstraint", getObjectLabel(dummyOCLinEcoreConstraintClass, context) },
						 new Object[] { dummyOCLinEcoreConstraintClass },
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
	public boolean validateOCLinEcoreConstraintCS(OCLinEcoreConstraintCS ocLinEcoreConstraintCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(ocLinEcoreConstraintCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSysMLCS(SysMLCS sysMLCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(sysMLCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTopLevelCS(TopLevelCS topLevelCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(topLevelCS, diagnostics, context);
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

} //OCLinEcoreCSValidator
