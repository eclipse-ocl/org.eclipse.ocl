/*******************************************************************************
 * Copyright (c) 2005, 2024 IBM Corporation, Zeligsoft Inc., and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Zeligsoft - Bugs 243976, 251349
 *******************************************************************************/
package org.eclipse.ocl.cst.impl;

import lpg.runtime.IToken;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.ocl.cst.BooleanLiteralExpCS;
import org.eclipse.ocl.cst.CSTFactory;
import org.eclipse.ocl.cst.CSTPackage;
import org.eclipse.ocl.cst.CallExpCS;
import org.eclipse.ocl.cst.ClassifierContextDeclCS;
import org.eclipse.ocl.cst.CollectionLiteralExpCS;
import org.eclipse.ocl.cst.CollectionLiteralPartCS;
import org.eclipse.ocl.cst.CollectionRangeCS;
import org.eclipse.ocl.cst.CollectionTypeCS;
import org.eclipse.ocl.cst.CollectionTypeIdentifierEnum;
import org.eclipse.ocl.cst.DefCS;
import org.eclipse.ocl.cst.DefExpressionCS;
import org.eclipse.ocl.cst.DerValueCS;
import org.eclipse.ocl.cst.DotOrArrowEnum;
import org.eclipse.ocl.cst.FeatureCallExpCS;
import org.eclipse.ocl.cst.IfExpCS;
import org.eclipse.ocl.cst.InitValueCS;
import org.eclipse.ocl.cst.IntegerLiteralExpCS;
import org.eclipse.ocl.cst.InvCS;
import org.eclipse.ocl.cst.InvalidLiteralExpCS;
import org.eclipse.ocl.cst.IsMarkedPreCS;
import org.eclipse.ocl.cst.IterateExpCS;
import org.eclipse.ocl.cst.IteratorExpCS;
import org.eclipse.ocl.cst.LetExpCS;
import org.eclipse.ocl.cst.LoopExpCS;
import org.eclipse.ocl.cst.MessageExpCS;
import org.eclipse.ocl.cst.MessageExpKind;
import org.eclipse.ocl.cst.NullLiteralExpCS;
import org.eclipse.ocl.cst.OCLDocumentCS;
import org.eclipse.ocl.cst.OCLMessageArgCS;
import org.eclipse.ocl.cst.OperationCS;
import org.eclipse.ocl.cst.OperationCallExpCS;
import org.eclipse.ocl.cst.OperationContextDeclCS;
import org.eclipse.ocl.cst.PackageDeclarationCS;
import org.eclipse.ocl.cst.PathNameCS;
import org.eclipse.ocl.cst.PrePostOrBodyDeclCS;
import org.eclipse.ocl.cst.PrePostOrBodyEnum;
import org.eclipse.ocl.cst.PrimitiveLiteralExpCS;
import org.eclipse.ocl.cst.PrimitiveTypeCS;
import org.eclipse.ocl.cst.PropertyContextCS;
import org.eclipse.ocl.cst.RealLiteralExpCS;
import org.eclipse.ocl.cst.SimpleNameCS;
import org.eclipse.ocl.cst.SimpleTypeEnum;
import org.eclipse.ocl.cst.StringLiteralExpCS;
import org.eclipse.ocl.cst.TupleLiteralExpCS;
import org.eclipse.ocl.cst.TupleTypeCS;
import org.eclipse.ocl.cst.UnlimitedNaturalLiteralExpCS;
import org.eclipse.ocl.cst.VariableCS;
import org.eclipse.ocl.cst.VariableExpCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CSTFactoryImpl
		extends EFactoryImpl
		implements CSTFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CSTFactory init() {
		try {
			CSTFactory theCSTFactory = (CSTFactory) EPackage.Registry.INSTANCE
				.getEFactory(CSTPackage.eNS_URI);
			if (theCSTFactory != null) {
				return theCSTFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CSTFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CSTFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CSTPackage.PACKAGE_DECLARATION_CS :
				return createPackageDeclarationCS();
			case CSTPackage.PATH_NAME_CS :
				return createPathNameCS();
			case CSTPackage.SIMPLE_NAME_CS :
				return createSimpleNameCS();
			case CSTPackage.PROPERTY_CONTEXT_CS :
				return createPropertyContextCS();
			case CSTPackage.CLASSIFIER_CONTEXT_DECL_CS :
				return createClassifierContextDeclCS();
			case CSTPackage.OPERATION_CONTEXT_DECL_CS :
				return createOperationContextDeclCS();
			case CSTPackage.OPERATION_CS :
				return createOperationCS();
			case CSTPackage.VARIABLE_CS :
				return createVariableCS();
			case CSTPackage.PRE_POST_OR_BODY_DECL_CS :
				return createPrePostOrBodyDeclCS();
			case CSTPackage.DER_VALUE_CS :
				return createDerValueCS();
			case CSTPackage.INIT_VALUE_CS :
				return createInitValueCS();
			case CSTPackage.INV_CS :
				return createInvCS();
			case CSTPackage.DEF_CS :
				return createDefCS();
			case CSTPackage.DEF_EXPRESSION_CS :
				return createDefExpressionCS();
			case CSTPackage.VARIABLE_EXP_CS :
				return createVariableExpCS();
			case CSTPackage.IS_MARKED_PRE_CS :
				return createIsMarkedPreCS();
			case CSTPackage.PRIMITIVE_TYPE_CS :
				return createPrimitiveTypeCS();
			case CSTPackage.TUPLE_TYPE_CS :
				return createTupleTypeCS();
			case CSTPackage.COLLECTION_TYPE_CS :
				return createCollectionTypeCS();
			case CSTPackage.LET_EXP_CS :
				return createLetExpCS();
			case CSTPackage.IF_EXP_CS :
				return createIfExpCS();
			case CSTPackage.MESSAGE_EXP_CS :
				return createMessageExpCS();
			case CSTPackage.OCL_MESSAGE_ARG_CS :
				return createOCLMessageArgCS();
			case CSTPackage.COLLECTION_LITERAL_EXP_CS :
				return createCollectionLiteralExpCS();
			case CSTPackage.COLLECTION_LITERAL_PART_CS :
				return createCollectionLiteralPartCS();
			case CSTPackage.TUPLE_LITERAL_EXP_CS :
				return createTupleLiteralExpCS();
			case CSTPackage.PRIMITIVE_LITERAL_EXP_CS :
				return createPrimitiveLiteralExpCS();
			case CSTPackage.INTEGER_LITERAL_EXP_CS :
				return createIntegerLiteralExpCS();
			case CSTPackage.UNLIMITED_NATURAL_LITERAL_EXP_CS :
				return createUnlimitedNaturalLiteralExpCS();
			case CSTPackage.REAL_LITERAL_EXP_CS :
				return createRealLiteralExpCS();
			case CSTPackage.STRING_LITERAL_EXP_CS :
				return createStringLiteralExpCS();
			case CSTPackage.BOOLEAN_LITERAL_EXP_CS :
				return createBooleanLiteralExpCS();
			case CSTPackage.NULL_LITERAL_EXP_CS :
				return createNullLiteralExpCS();
			case CSTPackage.INVALID_LITERAL_EXP_CS :
				return createInvalidLiteralExpCS();
			case CSTPackage.COLLECTION_RANGE_CS :
				return createCollectionRangeCS();
			case CSTPackage.CALL_EXP_CS :
				return createCallExpCS();
			case CSTPackage.LOOP_EXP_CS :
				return createLoopExpCS();
			case CSTPackage.ITERATOR_EXP_CS :
				return createIteratorExpCS();
			case CSTPackage.ITERATE_EXP_CS :
				return createIterateExpCS();
			case CSTPackage.FEATURE_CALL_EXP_CS :
				return createFeatureCallExpCS();
			case CSTPackage.OPERATION_CALL_EXP_CS :
				return createOperationCallExpCS();
			case CSTPackage.OCL_DOCUMENT_CS :
				return createOCLDocumentCS();
			default :
				throw new IllegalArgumentException("The class '" //$NON-NLS-1$
					+ eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case CSTPackage.SIMPLE_TYPE_ENUM :
				return createSimpleTypeEnumFromString(eDataType, initialValue);
			case CSTPackage.PRE_POST_OR_BODY_ENUM :
				return createPrePostOrBodyEnumFromString(eDataType,
					initialValue);
			case CSTPackage.COLLECTION_TYPE_IDENTIFIER_ENUM :
				return createCollectionTypeIdentifierEnumFromString(eDataType,
					initialValue);
			case CSTPackage.MESSAGE_EXP_KIND :
				return createMessageExpKindFromString(eDataType, initialValue);
			case CSTPackage.DOT_OR_ARROW_ENUM :
				return createDotOrArrowEnumFromString(eDataType, initialValue);
			case CSTPackage.ITOKEN :
				return createITokenFromString(eDataType, initialValue);
			default :
				throw new IllegalArgumentException("The datatype '" //$NON-NLS-1$
					+ eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case CSTPackage.SIMPLE_TYPE_ENUM :
				return convertSimpleTypeEnumToString(eDataType, instanceValue);
			case CSTPackage.PRE_POST_OR_BODY_ENUM :
				return convertPrePostOrBodyEnumToString(eDataType,
					instanceValue);
			case CSTPackage.COLLECTION_TYPE_IDENTIFIER_ENUM :
				return convertCollectionTypeIdentifierEnumToString(eDataType,
					instanceValue);
			case CSTPackage.MESSAGE_EXP_KIND :
				return convertMessageExpKindToString(eDataType, instanceValue);
			case CSTPackage.DOT_OR_ARROW_ENUM :
				return convertDotOrArrowEnumToString(eDataType, instanceValue);
			case CSTPackage.ITOKEN :
				return convertITokenToString(eDataType, instanceValue);
			default :
				throw new IllegalArgumentException("The datatype '" //$NON-NLS-1$
					+ eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PackageDeclarationCS createPackageDeclarationCS() {
		PackageDeclarationCSImpl packageDeclarationCS = new PackageDeclarationCSImpl();
		return packageDeclarationCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PropertyContextCS createPropertyContextCS() {
		PropertyContextCSImpl propertyContextCS = new PropertyContextCSImpl();
		return propertyContextCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ClassifierContextDeclCS createClassifierContextDeclCS() {
		ClassifierContextDeclCSImpl classifierContextDeclCS = new ClassifierContextDeclCSImpl();
		return classifierContextDeclCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationContextDeclCS createOperationContextDeclCS() {
		OperationContextDeclCSImpl operationContextDeclCS = new OperationContextDeclCSImpl();
		return operationContextDeclCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PrePostOrBodyDeclCS createPrePostOrBodyDeclCS() {
		PrePostOrBodyDeclCSImpl prePostOrBodyDeclCS = new PrePostOrBodyDeclCSImpl();
		return prePostOrBodyDeclCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationCS createOperationCS() {
		OperationCSImpl operationCS = new OperationCSImpl();
		return operationCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DerValueCS createDerValueCS() {
		DerValueCSImpl derValueCS = new DerValueCSImpl();
		return derValueCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InitValueCS createInitValueCS() {
		InitValueCSImpl initValueCS = new InitValueCSImpl();
		return initValueCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InvCS createInvCS() {
		InvCSImpl invCS = new InvCSImpl();
		return invCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DefCS createDefCS() {
		DefCSImpl defCS = new DefCSImpl();
		return defCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public DefExpressionCS createDefExpressionCS() {
		DefExpressionCSImpl defExpressionCS = new DefExpressionCSImpl();
		return defExpressionCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PathNameCS createPathNameCS() {
		PathNameCSImpl pathNameCS = new PathNameCSImpl();
		return pathNameCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public VariableExpCS createVariableExpCS() {
		VariableExpCSImpl variableExpCS = new VariableExpCSImpl();
		return variableExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public SimpleNameCS createSimpleNameCS() {
		SimpleNameCSImpl simpleNameCS = new SimpleNameCSImpl();
		return simpleNameCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public PrimitiveTypeCS createPrimitiveTypeCS() {
		PrimitiveTypeCSImpl primitiveTypeCS = new PrimitiveTypeCSImpl();
		return primitiveTypeCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public TupleTypeCS createTupleTypeCS() {
		TupleTypeCSImpl tupleTypeCS = new TupleTypeCSImpl();
		return tupleTypeCS;
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
	public MessageExpCS createMessageExpCS() {
		MessageExpCSImpl messageExpCS = new MessageExpCSImpl();
		return messageExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLMessageArgCS createOCLMessageArgCS() {
		OCLMessageArgCSImpl oclMessageArgCS = new OCLMessageArgCSImpl();
		return oclMessageArgCS;
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
	public IntegerLiteralExpCS createIntegerLiteralExpCS() {
		IntegerLiteralExpCSImpl integerLiteralExpCS = new IntegerLiteralExpCSImpl();
		return integerLiteralExpCS;
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
	public RealLiteralExpCS createRealLiteralExpCS() {
		RealLiteralExpCSImpl realLiteralExpCS = new RealLiteralExpCSImpl();
		return realLiteralExpCS;
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
	public CollectionRangeCS createCollectionRangeCS() {
		CollectionRangeCSImpl collectionRangeCS = new CollectionRangeCSImpl();
		return collectionRangeCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CallExpCS createCallExpCS() {
		CallExpCSImpl callExpCS = new CallExpCSImpl();
		return callExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public LoopExpCS createLoopExpCS() {
		LoopExpCSImpl loopExpCS = new LoopExpCSImpl();
		return loopExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IteratorExpCS createIteratorExpCS() {
		IteratorExpCSImpl iteratorExpCS = new IteratorExpCSImpl();
		return iteratorExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IterateExpCS createIterateExpCS() {
		IterateExpCSImpl iterateExpCS = new IterateExpCSImpl();
		return iterateExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public FeatureCallExpCS createFeatureCallExpCS() {
		FeatureCallExpCSImpl featureCallExpCS = new FeatureCallExpCSImpl();
		return featureCallExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OperationCallExpCS createOperationCallExpCS() {
		OperationCallExpCSImpl operationCallExpCS = new OperationCallExpCSImpl();
		return operationCallExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IsMarkedPreCS createIsMarkedPreCS() {
		IsMarkedPreCSImpl isMarkedPreCS = new IsMarkedPreCSImpl();
		return isMarkedPreCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public OCLDocumentCS createOCLDocumentCS() {
		OCLDocumentCSImpl oclDocumentCS = new OCLDocumentCSImpl();
		return oclDocumentCS;
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
	public InvalidLiteralExpCS createInvalidLiteralExpCS() {
		InvalidLiteralExpCSImpl invalidLiteralExpCS = new InvalidLiteralExpCSImpl();
		return invalidLiteralExpCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrePostOrBodyEnum createPrePostOrBodyEnumFromString(
			EDataType eDataType, String initialValue) {
		PrePostOrBodyEnum result = PrePostOrBodyEnum.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue //$NON-NLS-1$
				+ "' is not a valid enumerator of '" + eDataType.getName() //$NON-NLS-1$
				+ "'"); //$NON-NLS-1$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertPrePostOrBodyEnumToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null
			? null
			: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SimpleTypeEnum createSimpleTypeEnumFromString(EDataType eDataType,
			String initialValue) {
		SimpleTypeEnum result = SimpleTypeEnum.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue //$NON-NLS-1$
				+ "' is not a valid enumerator of '" + eDataType.getName() //$NON-NLS-1$
				+ "'"); //$NON-NLS-1$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSimpleTypeEnumToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null
			? null
			: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionTypeIdentifierEnum createCollectionTypeIdentifierEnumFromString(
			EDataType eDataType, String initialValue) {
		CollectionTypeIdentifierEnum result = CollectionTypeIdentifierEnum
			.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue //$NON-NLS-1$
				+ "' is not a valid enumerator of '" + eDataType.getName() //$NON-NLS-1$
				+ "'"); //$NON-NLS-1$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertCollectionTypeIdentifierEnumToString(
			EDataType eDataType, Object instanceValue) {
		return instanceValue == null
			? null
			: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DotOrArrowEnum createDotOrArrowEnumFromString(EDataType eDataType,
			String initialValue) {
		DotOrArrowEnum result = DotOrArrowEnum.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue //$NON-NLS-1$
				+ "' is not a valid enumerator of '" + eDataType.getName() //$NON-NLS-1$
				+ "'"); //$NON-NLS-1$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDotOrArrowEnumToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null
			? null
			: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IToken createITokenFromString(EDataType eDataType,
			String initialValue) {
		return (IToken) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 1.3
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertITokenToString(EDataType eDataType,
			Object instanceValue) {
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MessageExpKind createMessageExpKindFromString(EDataType eDataType,
			String initialValue) {
		MessageExpKind result = MessageExpKind.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException("The value '" + initialValue //$NON-NLS-1$
				+ "' is not a valid enumerator of '" + eDataType.getName() //$NON-NLS-1$
				+ "'"); //$NON-NLS-1$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertMessageExpKindToString(EDataType eDataType,
			Object instanceValue) {
		return instanceValue == null
			? null
			: instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CSTPackage getCSTPackage() {
		return (CSTPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CSTPackage getPackage() {
		return CSTPackage.eINSTANCE;
	}

} //CSTFactoryImpl
