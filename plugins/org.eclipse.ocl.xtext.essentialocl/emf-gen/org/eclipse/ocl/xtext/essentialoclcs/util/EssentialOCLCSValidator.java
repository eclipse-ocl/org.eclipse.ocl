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
package org.eclipse.ocl.xtext.essentialoclcs.util;

import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.EObjectValidator;

import org.eclipse.ocl.xtext.essentialoclcs.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Validator</b> for the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.xtext.essentialoclcs.EssentialOCLCSPackage
 * @generated
 */
public class EssentialOCLCSValidator extends EObjectValidator
{
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final EssentialOCLCSValidator INSTANCE = new EssentialOCLCSValidator();

	/**
	 * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.Diagnostic#getSource()
	 * @see org.eclipse.emf.common.util.Diagnostic#getCode()
	 * @generated
	 */
	public static final String DIAGNOSTIC_SOURCE = "org.eclipse.ocl.xtext.essentialoclcs"; //$NON-NLS-1$

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
	public EssentialOCLCSValidator()
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
	  return EssentialOCLCSPackage.eINSTANCE;
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
				return validateAbstractNameExpCS((AbstractNameExpCS)value, diagnostics, context);
			case 1:
				return validateAssociationClassCallExpCS((AssociationClassCallExpCS)value, diagnostics, context);
			case 2:
				return validateBooleanLiteralExpCS((BooleanLiteralExpCS)value, diagnostics, context);
			case 3:
				return validateCallExpCS((CallExpCS)value, diagnostics, context);
			case 4:
				return validateCollectionLiteralExpCS((CollectionLiteralExpCS)value, diagnostics, context);
			case 5:
				return validateCollectionLiteralPartCS((CollectionLiteralPartCS)value, diagnostics, context);
			case 6:
				return validateCollectionPatternCS((CollectionPatternCS)value, diagnostics, context);
			case 7:
				return validateCollectionTypeCS((CollectionTypeCS)value, diagnostics, context);
			case 8:
				return validateContextCS((ContextCS)value, diagnostics, context);
			case 9:
				return validateCurlyBracketedClauseCS((CurlyBracketedClauseCS)value, diagnostics, context);
			case 10:
				return validateDummyEssentialOCLConstraintClass((DummyEssentialOCLConstraintClass)value, diagnostics, context);
			case 11:
				return validateExpCS((ExpCS)value, diagnostics, context);
			case 12:
				return validateExpSpecificationCS((ExpSpecificationCS)value, diagnostics, context);
			case 13:
				return validateIfExpCS((IfExpCS)value, diagnostics, context);
			case 14:
				return validateIfThenExpCS((IfThenExpCS)value, diagnostics, context);
			case 15:
				return validateInfixExpCS((InfixExpCS)value, diagnostics, context);
			case 16:
				return validateInvalidLiteralExpCS((InvalidLiteralExpCS)value, diagnostics, context);
			case 17:
				return validateIterateCallExpCS((IterateCallExpCS)value, diagnostics, context);
			case 18:
				return validateIterationCallExpCS((IterationCallExpCS)value, diagnostics, context);
			case 19:
				return validateLambdaLiteralExpCS((LambdaLiteralExpCS)value, diagnostics, context);
			case 20:
				return validateLetExpCS((LetExpCS)value, diagnostics, context);
			case 21:
				return validateLetVariableCS((LetVariableCS)value, diagnostics, context);
			case 22:
				return validateLiteralExpCS((LiteralExpCS)value, diagnostics, context);
			case 23:
				return validateMapLiteralExpCS((MapLiteralExpCS)value, diagnostics, context);
			case 24:
				return validateMapLiteralPartCS((MapLiteralPartCS)value, diagnostics, context);
			case 25:
				return validateMapTypeCS((MapTypeCS)value, diagnostics, context);
			case 26:
				return validateNameExpCS((NameExpCS)value, diagnostics, context);
			case 27:
				return validateNavigatingArgCS((NavigatingArgCS)value, diagnostics, context);
			case 28:
				return validateNestedExpCS((NestedExpCS)value, diagnostics, context);
			case 29:
				return validateNullLiteralExpCS((NullLiteralExpCS)value, diagnostics, context);
			case 30:
				return validateNumberLiteralExpCS((NumberLiteralExpCS)value, diagnostics, context);
			case 31:
				return validateOperationCallExpCS((OperationCallExpCS)value, diagnostics, context);
			case 32:
				return validateOperatorExpCS((OperatorExpCS)value, diagnostics, context);
			case 33:
				return validatePatternExpCS((PatternExpCS)value, diagnostics, context);
			case 34:
				return validatePrefixExpCS((PrefixExpCS)value, diagnostics, context);
			case 35:
				return validatePrimitiveLiteralExpCS((PrimitiveLiteralExpCS)value, diagnostics, context);
			case 36:
				return validatePropertyCallExpCS((PropertyCallExpCS)value, diagnostics, context);
			case 37:
				return validateRoundBracketedClauseCS((RoundBracketedClauseCS)value, diagnostics, context);
			case 38:
				return validateSelfExpCS((SelfExpCS)value, diagnostics, context);
			case 39:
				return validateShadowExpCS((ShadowExpCS)value, diagnostics, context);
			case 40:
				return validateShadowPartCS((ShadowPartCS)value, diagnostics, context);
			case 41:
				return validateSquareBracketedClauseCS((SquareBracketedClauseCS)value, diagnostics, context);
			case 42:
				return validateStringLiteralExpCS((StringLiteralExpCS)value, diagnostics, context);
			case 43:
				return validateTupleLiteralExpCS((TupleLiteralExpCS)value, diagnostics, context);
			case 44:
				return validateTupleLiteralPartCS((TupleLiteralPartCS)value, diagnostics, context);
			case 45:
				return validateTypeLiteralExpCS((TypeLiteralExpCS)value, diagnostics, context);
			case 46:
				return validateTypeNameExpCS((TypeNameExpCS)value, diagnostics, context);
			case 47:
				return validateUnlimitedNaturalLiteralExpCS((UnlimitedNaturalLiteralExpCS)value, diagnostics, context);
			case 48:
				return validateVariableCS((VariableCS)value, diagnostics, context);
			case 49:
				return validateVariableExpCS((VariableExpCS)value, diagnostics, context);
			case 50:
				return validateNavigationRole((NavigationRole)value, diagnostics, context);
			default:
				return true;
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAbstractNameExpCS(AbstractNameExpCS abstractNameExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(abstractNameExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateAssociationClassCallExpCS(AssociationClassCallExpCS associationClassCallExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(associationClassCallExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateBooleanLiteralExpCS(BooleanLiteralExpCS booleanLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(booleanLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCallExpCS(CallExpCS callExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(callExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCollectionLiteralExpCS(CollectionLiteralExpCS collectionLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(collectionLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCollectionLiteralPartCS(CollectionLiteralPartCS collectionLiteralPartCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(collectionLiteralPartCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCollectionPatternCS(CollectionPatternCS collectionPatternCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(collectionPatternCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCollectionTypeCS(CollectionTypeCS collectionTypeCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(collectionTypeCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateContextCS(ContextCS contextCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(contextCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateCurlyBracketedClauseCS(CurlyBracketedClauseCS curlyBracketedClauseCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(curlyBracketedClauseCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDummyEssentialOCLConstraintClass(DummyEssentialOCLConstraintClass dummyEssentialOCLConstraintClass, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		if (!validate_NoCircularContainment(dummyEssentialOCLConstraintClass, diagnostics, context)) return false;
		boolean result = validate_EveryMultiplicityConforms(dummyEssentialOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryDataValueConforms(dummyEssentialOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryReferenceIsContained(dummyEssentialOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryBidirectionalReferenceIsPaired(dummyEssentialOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryProxyResolves(dummyEssentialOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_UniqueID(dummyEssentialOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryKeyUnique(dummyEssentialOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validate_EveryMapEntryUnique(dummyEssentialOCLConstraintClass, diagnostics, context);
		if (result || diagnostics != null) result &= validateDummyEssentialOCLConstraintClass_DummyConstraint(dummyEssentialOCLConstraintClass, diagnostics, context);
		return result;
	}

	/**
	 * Validates the DummyConstraint constraint of '<em>Dummy Essential OCL Constraint Class</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateDummyEssentialOCLConstraintClass_DummyConstraint(DummyEssentialOCLConstraintClass dummyEssentialOCLConstraintClass, DiagnosticChain diagnostics, Map<Object, Object> context)
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
						 "_UI_GenericConstraint_diagnostic", //$NON-NLS-1$
						 new Object[] { "DummyConstraint", getObjectLabel(dummyEssentialOCLConstraintClass, context) }, //$NON-NLS-1$
						 new Object[] { dummyEssentialOCLConstraintClass },
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
	public boolean validateExpCS(ExpCS expCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(expCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateExpSpecificationCS(ExpSpecificationCS expSpecificationCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(expSpecificationCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIfExpCS(IfExpCS ifExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(ifExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIfThenExpCS(IfThenExpCS ifThenExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(ifThenExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInfixExpCS(InfixExpCS infixExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(infixExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateInvalidLiteralExpCS(InvalidLiteralExpCS invalidLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(invalidLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIterateCallExpCS(IterateCallExpCS iterateCallExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(iterateCallExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateIterationCallExpCS(IterationCallExpCS iterationCallExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(iterationCallExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLambdaLiteralExpCS(LambdaLiteralExpCS lambdaLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(lambdaLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLetExpCS(LetExpCS letExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(letExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLetVariableCS(LetVariableCS letVariableCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(letVariableCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateLiteralExpCS(LiteralExpCS literalExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(literalExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMapLiteralExpCS(MapLiteralExpCS mapLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(mapLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMapLiteralPartCS(MapLiteralPartCS mapLiteralPartCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(mapLiteralPartCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateMapTypeCS(MapTypeCS mapTypeCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(mapTypeCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNameExpCS(NameExpCS nameExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(nameExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNavigatingArgCS(NavigatingArgCS navigatingArgCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(navigatingArgCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNestedExpCS(NestedExpCS nestedExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(nestedExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNullLiteralExpCS(NullLiteralExpCS nullLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(nullLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNumberLiteralExpCS(NumberLiteralExpCS numberLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(numberLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperationCallExpCS(OperationCallExpCS operationCallExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(operationCallExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateOperatorExpCS(OperatorExpCS operatorExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(operatorExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePatternExpCS(PatternExpCS patternExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(patternExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePrefixExpCS(PrefixExpCS prefixExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(prefixExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePrimitiveLiteralExpCS(PrimitiveLiteralExpCS primitiveLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(primitiveLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validatePropertyCallExpCS(PropertyCallExpCS propertyCallExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(propertyCallExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateRoundBracketedClauseCS(RoundBracketedClauseCS roundBracketedClauseCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(roundBracketedClauseCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSelfExpCS(SelfExpCS selfExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(selfExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateShadowExpCS(ShadowExpCS shadowExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(shadowExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateShadowPartCS(ShadowPartCS shadowPartCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(shadowPartCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateSquareBracketedClauseCS(SquareBracketedClauseCS squareBracketedClauseCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(squareBracketedClauseCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateStringLiteralExpCS(StringLiteralExpCS stringLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(stringLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTupleLiteralExpCS(TupleLiteralExpCS tupleLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(tupleLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTupleLiteralPartCS(TupleLiteralPartCS tupleLiteralPartCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(tupleLiteralPartCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeLiteralExpCS(TypeLiteralExpCS typeLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(typeLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateTypeNameExpCS(TypeNameExpCS typeNameExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(typeNameExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateUnlimitedNaturalLiteralExpCS(UnlimitedNaturalLiteralExpCS unlimitedNaturalLiteralExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(unlimitedNaturalLiteralExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVariableCS(VariableCS variableCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(variableCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateVariableExpCS(VariableExpCS variableExpCS, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return validate_EveryDefaultConstraint(variableExpCS, diagnostics, context);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean validateNavigationRole(NavigationRole navigationRole, DiagnosticChain diagnostics, Map<Object, Object> context)
	{
		return true;
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

} //EssentialOCLCSValidator
