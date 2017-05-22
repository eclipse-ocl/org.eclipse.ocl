/*******************************************************************************
 * Copyright (c) 2010, 2017 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.basecs.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.scoping.ScopeFilter;
import org.eclipse.ocl.xtext.basecs.AnnotationCS;
import org.eclipse.ocl.xtext.basecs.AttributeCS;
import org.eclipse.ocl.xtext.basecs.BaseCSFactory;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.DataTypeCS;
import org.eclipse.ocl.xtext.basecs.DetailCS;
import org.eclipse.ocl.xtext.basecs.DocumentationCS;
import org.eclipse.ocl.xtext.basecs.EnumerationCS;
import org.eclipse.ocl.xtext.basecs.EnumerationLiteralCS;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.LambdaTypeCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityBoundsCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityStringCS;
import org.eclipse.ocl.xtext.basecs.OperationCS;
import org.eclipse.ocl.xtext.basecs.PackageCS;
import org.eclipse.ocl.xtext.basecs.ParameterCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathElementWithURICS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.PrimitiveTypeRefCS;
import org.eclipse.ocl.xtext.basecs.ReferenceCS;
import org.eclipse.ocl.xtext.basecs.RootPackageCS;
import org.eclipse.ocl.xtext.basecs.SpecificationCS;
import org.eclipse.ocl.xtext.basecs.StructuredClassCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TuplePartCS;
import org.eclipse.ocl.xtext.basecs.TupleTypeCS;
import org.eclipse.ocl.xtext.basecs.TypeParameterCS;
import org.eclipse.ocl.xtext.basecs.TypedTypeRefCS;
import org.eclipse.ocl.xtext.basecs.WildcardTypeRefCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BaseCSFactoryImpl extends EFactoryImpl implements BaseCSFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static BaseCSFactory init() {
		try
		{
			BaseCSFactory theBaseCSFactory = (BaseCSFactory)EPackage.Registry.INSTANCE.getEFactory(BaseCSPackage.eNS_URI);
			if (theBaseCSFactory != null)
			{
				return theBaseCSFactory;
			}
		}
		catch (Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new BaseCSFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseCSFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull EObject create(EClass eClass) {
		switch (eClass.getClassifierID())
		{
			case BaseCSPackage.ANNOTATION_CS: return createAnnotationCS();
			case BaseCSPackage.ATTRIBUTE_CS: return createAttributeCS();
			case BaseCSPackage.CONSTRAINT_CS: return createConstraintCS();
			case BaseCSPackage.DATA_TYPE_CS: return createDataTypeCS();
			case BaseCSPackage.DETAIL_CS: return createDetailCS();
			case BaseCSPackage.DOCUMENTATION_CS: return createDocumentationCS();
			case BaseCSPackage.ENUMERATION_CS: return createEnumerationCS();
			case BaseCSPackage.ENUMERATION_LITERAL_CS: return createEnumerationLiteralCS();
			case BaseCSPackage.IMPORT_CS: return createImportCS();
			case BaseCSPackage.LAMBDA_TYPE_CS: return createLambdaTypeCS();
			case BaseCSPackage.MODEL_ELEMENT_REF_CS: return createModelElementRefCS();
			case BaseCSPackage.MULTIPLICITY_BOUNDS_CS: return createMultiplicityBoundsCS();
			case BaseCSPackage.MULTIPLICITY_STRING_CS: return createMultiplicityStringCS();
			case BaseCSPackage.OPERATION_CS: return createOperationCS();
			case BaseCSPackage.PACKAGE_CS: return createPackageCS();
			case BaseCSPackage.PARAMETER_CS: return createParameterCS();
			case BaseCSPackage.PATH_ELEMENT_CS: return createPathElementCS();
			case BaseCSPackage.PATH_ELEMENT_WITH_URICS: return createPathElementWithURICS();
			case BaseCSPackage.PATH_NAME_CS: return createPathNameCS();
			case BaseCSPackage.PRIMITIVE_TYPE_REF_CS: return createPrimitiveTypeRefCS();
			case BaseCSPackage.REFERENCE_CS: return createReferenceCS();
			case BaseCSPackage.ROOT_PACKAGE_CS: return createRootPackageCS();
			case BaseCSPackage.SPECIFICATION_CS: return createSpecificationCS();
			case BaseCSPackage.STRUCTURED_CLASS_CS: return createStructuredClassCS();
			case BaseCSPackage.TEMPLATE_BINDING_CS: return createTemplateBindingCS();
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS: return createTemplateParameterSubstitutionCS();
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS: return createTemplateSignatureCS();
			case BaseCSPackage.TUPLE_PART_CS: return createTuplePartCS();
			case BaseCSPackage.TUPLE_TYPE_CS: return createTupleTypeCS();
			case BaseCSPackage.TYPE_PARAMETER_CS: return createTypeParameterCS();
			case BaseCSPackage.TYPED_TYPE_REF_CS: return createTypedTypeRefCS();
			case BaseCSPackage.WILDCARD_TYPE_REF_CS: return createWildcardTypeRefCS();
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
	public Object createFromString(EDataType eDataType, String initialValue)
	{
		switch (eDataType.getClassifierID())
		{
			case BaseCSPackage.BIG_NUMBER:
				return createBigNumberFromString(eDataType, initialValue);
			case BaseCSPackage.SCOPE_FILTER:
				return createScopeFilterFromString(eDataType, initialValue);
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
	public String convertToString(EDataType eDataType, Object instanceValue)
	{
		switch (eDataType.getClassifierID())
		{
			case BaseCSPackage.BIG_NUMBER:
				return convertBigNumberToString(eDataType, instanceValue);
			case BaseCSPackage.SCOPE_FILTER:
				return convertScopeFilterToString(eDataType, instanceValue);
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
	public @NonNull AnnotationCS createAnnotationCS()
	{
		AnnotationCSImpl annotationCS = new AnnotationCSImpl();
		return annotationCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull AttributeCS createAttributeCS()
	{
		AttributeCSImpl attributeCS = new AttributeCSImpl();
		return attributeCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ConstraintCS createConstraintCS()
	{
		ConstraintCSImpl constraintCS = new ConstraintCSImpl();
		return constraintCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull DataTypeCS createDataTypeCS()
	{
		DataTypeCSImpl dataTypeCS = new DataTypeCSImpl();
		return dataTypeCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull DetailCS createDetailCS() {
		DetailCSImpl detailCS = new DetailCSImpl();
		return detailCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull DocumentationCS createDocumentationCS()
	{
		DocumentationCSImpl documentationCS = new DocumentationCSImpl();
		return documentationCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull EnumerationCS createEnumerationCS()
	{
		EnumerationCSImpl enumerationCS = new EnumerationCSImpl();
		return enumerationCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull EnumerationLiteralCS createEnumerationLiteralCS()
	{
		EnumerationLiteralCSImpl enumerationLiteralCS = new EnumerationLiteralCSImpl();
		return enumerationLiteralCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ImportCS createImportCS() {
		ImportCSImpl importCS = new ImportCSImpl();
		return importCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull LambdaTypeCS createLambdaTypeCS()
	{
		LambdaTypeCSImpl lambdaTypeCS = new LambdaTypeCSImpl();
		return lambdaTypeCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ModelElementRefCS createModelElementRefCS()
	{
		ModelElementRefCSImpl modelElementRefCS = new ModelElementRefCSImpl();
		return modelElementRefCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull MultiplicityBoundsCS createMultiplicityBoundsCS()
	{
		MultiplicityBoundsCSImpl multiplicityBoundsCS = new MultiplicityBoundsCSImpl();
		return multiplicityBoundsCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull MultiplicityStringCS createMultiplicityStringCS()
	{
		MultiplicityStringCSImpl multiplicityStringCS = new MultiplicityStringCSImpl();
		return multiplicityStringCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull OperationCS createOperationCS()
	{
		OperationCSImpl operationCS = new OperationCSImpl();
		return operationCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull PackageCS createPackageCS() {
		PackageCSImpl packageCS = new PackageCSImpl();
		return packageCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ParameterCS createParameterCS() {
		ParameterCSImpl parameterCS = new ParameterCSImpl();
		return parameterCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull PathElementCS createPathElementCS()
	{
		PathElementCSImpl pathElementCS = new PathElementCSImpl();
		return pathElementCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull PathElementWithURICS createPathElementWithURICS()
	{
		PathElementWithURICSImpl pathElementWithURICS = new PathElementWithURICSImpl();
		return pathElementWithURICS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull PathNameCS createPathNameCS()
	{
		PathNameCSImpl pathNameCS = new PathNameCSImpl();
		return pathNameCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull PrimitiveTypeRefCS createPrimitiveTypeRefCS() {
		PrimitiveTypeRefCSImpl primitiveTypeRefCS = new PrimitiveTypeRefCSImpl();
		return primitiveTypeRefCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull ReferenceCS createReferenceCS()
	{
		ReferenceCSImpl referenceCS = new ReferenceCSImpl();
		return referenceCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull RootPackageCS createRootPackageCS()
	{
		RootPackageCSImpl rootPackageCS = new RootPackageCSImpl();
		return rootPackageCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull SpecificationCS createSpecificationCS()
	{
		SpecificationCSImpl specificationCS = new SpecificationCSImpl();
		return specificationCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull StructuredClassCS createStructuredClassCS()
	{
		StructuredClassCSImpl structuredClassCS = new StructuredClassCSImpl();
		return structuredClassCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull TemplateBindingCS createTemplateBindingCS() {
		TemplateBindingCSImpl templateBindingCS = new TemplateBindingCSImpl();
		return templateBindingCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull TemplateParameterSubstitutionCS createTemplateParameterSubstitutionCS() {
		TemplateParameterSubstitutionCSImpl templateParameterSubstitutionCS = new TemplateParameterSubstitutionCSImpl();
		return templateParameterSubstitutionCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull TemplateSignatureCS createTemplateSignatureCS() {
		TemplateSignatureCSImpl templateSignatureCS = new TemplateSignatureCSImpl();
		return templateSignatureCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull TuplePartCS createTuplePartCS()
	{
		TuplePartCSImpl tuplePartCS = new TuplePartCSImpl();
		return tuplePartCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull TupleTypeCS createTupleTypeCS()
	{
		TupleTypeCSImpl tupleTypeCS = new TupleTypeCSImpl();
		return tupleTypeCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull TypeParameterCS createTypeParameterCS() {
		TypeParameterCSImpl typeParameterCS = new TypeParameterCSImpl();
		return typeParameterCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull TypedTypeRefCS createTypedTypeRefCS() {
		TypedTypeRefCSImpl typedTypeRefCS = new TypedTypeRefCSImpl();
		return typedTypeRefCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public @NonNull WildcardTypeRefCS createWildcardTypeRefCS() {
		WildcardTypeRefCSImpl wildcardTypeRefCS = new WildcardTypeRefCSImpl();
		return wildcardTypeRefCS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Number createBigNumberFromString(EDataType eDataType, String initialValue)
	{
		return (Number)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertBigNumberToString(EDataType eDataType, Object instanceValue)
	{
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopeFilter createScopeFilterFromString(EDataType eDataType, String initialValue)
	{
		return (ScopeFilter)super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertScopeFilterToString(EDataType eDataType, Object instanceValue)
	{
		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BaseCSPackage getBaseCSPackage()
	{
		return (BaseCSPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static BaseCSPackage getPackage() {
		return BaseCSPackage.eINSTANCE;
	}

} //BaseCSFactoryImpl
