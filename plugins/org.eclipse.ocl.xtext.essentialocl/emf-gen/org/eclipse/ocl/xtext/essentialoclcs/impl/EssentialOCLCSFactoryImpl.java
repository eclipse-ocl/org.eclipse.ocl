/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.essentialoclcs.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.ocl.xtext.essentialoclcs.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EssentialOCLCSFactoryImpl
		extends EFactoryImpl
		implements EssentialOCLCSFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EssentialOCLCSFactory init() {
		try
		{
			EssentialOCLCSFactory theEssentialOCLCSFactory = (EssentialOCLCSFactory)EPackage.Registry.INSTANCE.getEFactory(EssentialOCLCSPackage.eNS_URI);
			if (theEssentialOCLCSFactory != null)
			{
				return theEssentialOCLCSFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EssentialOCLCSFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EssentialOCLCSFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID())
		{
			case EssentialOCLCSPackage.BOOLEAN_LITERAL_EXP_CS: return createBooleanLiteralExpCS();
			case EssentialOCLCSPackage.COLLECTION_LITERAL_EXP_CS: return createCollectionLiteralExpCS();
			case EssentialOCLCSPackage.COLLECTION_LITERAL_PART_CS: return createCollectionLiteralPartCS();
			case EssentialOCLCSPackage.COLLECTION_PATTERN_CS: return createCollectionPatternCS();
			case EssentialOCLCSPackage.COLLECTION_TYPE_CS: return createCollectionTypeCS();
			case EssentialOCLCSPackage.CONTEXT_CS: return createContextCS();
			case EssentialOCLCSPackage.CURLY_BRACKETED_CLAUSE_CS: return createCurlyBracketedClauseCS();
			case EssentialOCLCSPackage.EXP_CS: return createExpCS();
			case EssentialOCLCSPackage.EXP_SPECIFICATION_CS: return createExpSpecificationCS();
			case EssentialOCLCSPackage.IF_EXP_CS: return createIfExpCS();
			case EssentialOCLCSPackage.IF_THEN_EXP_CS: return createIfThenExpCS();
			case EssentialOCLCSPackage.INFIX_EXP_CS: return createInfixExpCS();
			case EssentialOCLCSPackage.INVALID_LITERAL_EXP_CS: return createInvalidLiteralExpCS();
			case EssentialOCLCSPackage.LAMBDA_LITERAL_EXP_CS: return createLambdaLiteralExpCS();
			case EssentialOCLCSPackage.LET_EXP_CS: return createLetExpCS();
			case EssentialOCLCSPackage.LET_VARIABLE_CS: return createLetVariableCS();
			case EssentialOCLCSPackage.LITERAL_EXP_CS: return createLiteralExpCS();
			case EssentialOCLCSPackage.MAP_LITERAL_EXP_CS: return createMapLiteralExpCS();
			case EssentialOCLCSPackage.MAP_LITERAL_PART_CS: return createMapLiteralPartCS();
			case EssentialOCLCSPackage.MAP_TYPE_CS: return createMapTypeCS();
			case EssentialOCLCSPackage.NAME_EXP_CS: return createNameExpCS();
			case EssentialOCLCSPackage.NAVIGATING_ARG_CS: return createNavigatingArgCS();
			case EssentialOCLCSPackage.NESTED_EXP_CS: return createNestedExpCS();
			case EssentialOCLCSPackage.NULL_LITERAL_EXP_CS: return createNullLiteralExpCS();
			case EssentialOCLCSPackage.NUMBER_LITERAL_EXP_CS: return createNumberLiteralExpCS();
			case EssentialOCLCSPackage.PATH_EXP_CS: return createPathExpCS();
			case EssentialOCLCSPackage.PATTERN_EXP_CS: return createPatternExpCS();
			case EssentialOCLCSPackage.PREFIX_EXP_CS: return createPrefixExpCS();
			case EssentialOCLCSPackage.PRIMITIVE_LITERAL_EXP_CS: return createPrimitiveLiteralExpCS();
			case EssentialOCLCSPackage.ROUND_BRACKETED_CLAUSE_CS: return createRoundBracketedClauseCS();
			case EssentialOCLCSPackage.SELF_EXP_CS: return createSelfExpCS();
			case EssentialOCLCSPackage.SHADOW_PART_CS: return createShadowPartCS();
			case EssentialOCLCSPackage.SQUARE_BRACKETED_CLAUSE_CS: return createSquareBracketedClauseCS();
			case EssentialOCLCSPackage.STRING_LITERAL_EXP_CS: return createStringLiteralExpCS();
			case EssentialOCLCSPackage.TUPLE_LITERAL_EXP_CS: return createTupleLiteralExpCS();
			case EssentialOCLCSPackage.TUPLE_LITERAL_PART_CS: return createTupleLiteralPartCS();
			case EssentialOCLCSPackage.TYPE_LITERAL_EXP_CS: return createTypeLiteralExpCS();
			case EssentialOCLCSPackage.UNLIMITED_NATURAL_LITERAL_EXP_CS: return createUnlimitedNaturalLiteralExpCS();
			case EssentialOCLCSPackage.VARIABLE_CS: return createVariableCS();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID())
		{
			case EssentialOCLCSPackage.NAVIGATION_ROLE:
				return createNavigationRoleFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID())
		{
			case EssentialOCLCSPackage.NAVIGATION_ROLE:
				return convertNavigationRoleToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PrefixExpCS createPrefixExpCS() {
		PrefixExpCSImpl prefixExpCS = new PrefixExpCSImpl();
		return prefixExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CollectionTypeCS createCollectionTypeCS() {
		CollectionTypeCSImpl collectionTypeCS = new CollectionTypeCSImpl();
		return collectionTypeCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ContextCS createContextCS() {
		ContextCSImpl contextCS = new ContextCSImpl();
		return contextCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CurlyBracketedClauseCS createCurlyBracketedClauseCS()
	{
		CurlyBracketedClauseCSImpl curlyBracketedClauseCS = new CurlyBracketedClauseCSImpl();
		return curlyBracketedClauseCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpCS createExpCS() {
		ExpCSImpl expCS = new ExpCSImpl();
		return expCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ExpSpecificationCS createExpSpecificationCS() {
		ExpSpecificationCSImpl expSpecificationCS = new ExpSpecificationCSImpl();
		return expSpecificationCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TypeLiteralExpCS createTypeLiteralExpCS() {
		TypeLiteralExpCSImpl typeLiteralExpCS = new TypeLiteralExpCSImpl();
		return typeLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public UnlimitedNaturalLiteralExpCS createUnlimitedNaturalLiteralExpCS() {
		UnlimitedNaturalLiteralExpCSImpl unlimitedNaturalLiteralExpCS = new UnlimitedNaturalLiteralExpCSImpl();
		return unlimitedNaturalLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableCS createVariableCS() {
		VariableCSImpl variableCS = new VariableCSImpl();
		return variableCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NavigationRole createNavigationRoleFromString(EDataType eDataType,
			String initialValue) {
		NavigationRole result = NavigationRole.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNavigationRoleToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LiteralExpCS createLiteralExpCS() {
		LiteralExpCSImpl literalExpCS = new LiteralExpCSImpl();
		return literalExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MapLiteralExpCS createMapLiteralExpCS()
	{
		MapLiteralExpCSImpl mapLiteralExpCS = new MapLiteralExpCSImpl();
		return mapLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MapLiteralPartCS createMapLiteralPartCS()
	{
		MapLiteralPartCSImpl mapLiteralPartCS = new MapLiteralPartCSImpl();
		return mapLiteralPartCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public MapTypeCS createMapTypeCS()
	{
		MapTypeCSImpl mapTypeCS = new MapTypeCSImpl();
		return mapTypeCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NameExpCS createNameExpCS() {
		NameExpCSImpl nameExpCS = new NameExpCSImpl();
		return nameExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NavigatingArgCS createNavigatingArgCS() {
		NavigatingArgCSImpl navigatingArgCS = new NavigatingArgCSImpl();
		return navigatingArgCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NestedExpCS createNestedExpCS() {
		NestedExpCSImpl nestedExpCS = new NestedExpCSImpl();
		return nestedExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CollectionLiteralExpCS createCollectionLiteralExpCS() {
		CollectionLiteralExpCSImpl collectionLiteralExpCS = new CollectionLiteralExpCSImpl();
		return collectionLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CollectionLiteralPartCS createCollectionLiteralPartCS() {
		CollectionLiteralPartCSImpl collectionLiteralPartCS = new CollectionLiteralPartCSImpl();
		return collectionLiteralPartCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CollectionPatternCS createCollectionPatternCS()
	{
		CollectionPatternCSImpl collectionPatternCS = new CollectionPatternCSImpl();
		return collectionPatternCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PrimitiveLiteralExpCS createPrimitiveLiteralExpCS() {
		PrimitiveLiteralExpCSImpl primitiveLiteralExpCS = new PrimitiveLiteralExpCSImpl();
		return primitiveLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public RoundBracketedClauseCS createRoundBracketedClauseCS()
	{
		RoundBracketedClauseCSImpl roundBracketedClauseCS = new RoundBracketedClauseCSImpl();
		return roundBracketedClauseCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SelfExpCS createSelfExpCS() {
		SelfExpCSImpl selfExpCS = new SelfExpCSImpl();
		return selfExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ShadowPartCS createShadowPartCS()
	{
		ShadowPartCSImpl shadowPartCS = new ShadowPartCSImpl();
		return shadowPartCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SquareBracketedClauseCS createSquareBracketedClauseCS()
	{
		SquareBracketedClauseCSImpl squareBracketedClauseCS = new SquareBracketedClauseCSImpl();
		return squareBracketedClauseCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TupleLiteralExpCS createTupleLiteralExpCS() {
		TupleLiteralExpCSImpl tupleLiteralExpCS = new TupleLiteralExpCSImpl();
		return tupleLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TupleLiteralPartCS createTupleLiteralPartCS() {
		TupleLiteralPartCSImpl tupleLiteralPartCS = new TupleLiteralPartCSImpl();
		return tupleLiteralPartCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public StringLiteralExpCS createStringLiteralExpCS() {
		StringLiteralExpCSImpl stringLiteralExpCS = new StringLiteralExpCSImpl();
		return stringLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BooleanLiteralExpCS createBooleanLiteralExpCS() {
		BooleanLiteralExpCSImpl booleanLiteralExpCS = new BooleanLiteralExpCSImpl();
		return booleanLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InvalidLiteralExpCS createInvalidLiteralExpCS() {
		InvalidLiteralExpCSImpl invalidLiteralExpCS = new InvalidLiteralExpCSImpl();
		return invalidLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LambdaLiteralExpCS createLambdaLiteralExpCS()
	{
		LambdaLiteralExpCSImpl lambdaLiteralExpCS = new LambdaLiteralExpCSImpl();
		return lambdaLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NullLiteralExpCS createNullLiteralExpCS() {
		NullLiteralExpCSImpl nullLiteralExpCS = new NullLiteralExpCSImpl();
		return nullLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NumberLiteralExpCS createNumberLiteralExpCS() {
		NumberLiteralExpCSImpl numberLiteralExpCS = new NumberLiteralExpCSImpl();
		return numberLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PathExpCS createPathExpCS()
	{
		PathExpCSImpl pathExpCS = new PathExpCSImpl();
		return pathExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PatternExpCS createPatternExpCS()
	{
		PatternExpCSImpl patternExpCS = new PatternExpCSImpl();
		return patternExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EssentialOCLCSPackage getEssentialOCLCSPackage() {
		return (EssentialOCLCSPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IfExpCS createIfExpCS() {
		IfExpCSImpl ifExpCS = new IfExpCSImpl();
		return ifExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IfThenExpCS createIfThenExpCS()
	{
		IfThenExpCSImpl ifThenExpCS = new IfThenExpCSImpl();
		return ifThenExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InfixExpCS createInfixExpCS() {
		InfixExpCSImpl infixExpCS = new InfixExpCSImpl();
		return infixExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LetExpCS createLetExpCS() {
		LetExpCSImpl letExpCS = new LetExpCSImpl();
		return letExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LetVariableCS createLetVariableCS() {
		LetVariableCSImpl letVariableCS = new LetVariableCSImpl();
		return letVariableCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EssentialOCLCSPackage getPackage() {
		return EssentialOCLCSPackage.eINSTANCE;
	}

} //EssentialOCLCSFactoryImpl
