/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.basecs.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.Nameable;
import org.eclipse.ocl.pivot.utilities.Pivotable;
import org.eclipse.ocl.xtext.basecs.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage
 * @generated
 */
public class BaseCSSwitch<@Nullable T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static BaseCSPackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BaseCSSwitch() {
		if (modelPackage == null)
		{
			modelPackage = BaseCSPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage)
	{
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID)
		{
			case BaseCSPackage.ANNOTATION_CS:
			{
				AnnotationCS annotationCS = (AnnotationCS)theEObject;
				T result = caseAnnotationCS(annotationCS);
				if (result == null) result = caseAnnotationElementCS(annotationCS);
				if (result == null) result = caseNamedElementCS(annotationCS);
				if (result == null) result = caseModelElementCS(annotationCS);
				if (result == null) result = caseNameable(annotationCS);
				if (result == null) result = casePivotableElementCS(annotationCS);
				if (result == null) result = caseElementCS(annotationCS);
				if (result == null) result = casePivotable(annotationCS);
				if (result == null) result = caseVisitableCS(annotationCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.ANNOTATION_ELEMENT_CS:
			{
				AnnotationElementCS annotationElementCS = (AnnotationElementCS)theEObject;
				T result = caseAnnotationElementCS(annotationElementCS);
				if (result == null) result = caseNamedElementCS(annotationElementCS);
				if (result == null) result = caseModelElementCS(annotationElementCS);
				if (result == null) result = caseNameable(annotationElementCS);
				if (result == null) result = casePivotableElementCS(annotationElementCS);
				if (result == null) result = caseElementCS(annotationElementCS);
				if (result == null) result = casePivotable(annotationElementCS);
				if (result == null) result = caseVisitableCS(annotationElementCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.ATTRIBUTE_CS:
			{
				AttributeCS attributeCS = (AttributeCS)theEObject;
				T result = caseAttributeCS(attributeCS);
				if (result == null) result = caseStructuralFeatureCS(attributeCS);
				if (result == null) result = caseFeatureCS(attributeCS);
				if (result == null) result = caseTypedElementCS(attributeCS);
				if (result == null) result = caseNamedElementCS(attributeCS);
				if (result == null) result = caseModelElementCS(attributeCS);
				if (result == null) result = caseNameable(attributeCS);
				if (result == null) result = casePivotableElementCS(attributeCS);
				if (result == null) result = caseElementCS(attributeCS);
				if (result == null) result = casePivotable(attributeCS);
				if (result == null) result = caseVisitableCS(attributeCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.CLASS_CS:
			{
				ClassCS classCS = (ClassCS)theEObject;
				T result = caseClassCS(classCS);
				if (result == null) result = caseNamedElementCS(classCS);
				if (result == null) result = caseTypeCS(classCS);
				if (result == null) result = caseTemplateableElementCS(classCS);
				if (result == null) result = caseModelElementCS(classCS);
				if (result == null) result = caseNameable(classCS);
				if (result == null) result = casePivotableElementCS(classCS);
				if (result == null) result = caseElementCS(classCS);
				if (result == null) result = casePivotable(classCS);
				if (result == null) result = caseVisitableCS(classCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.CONSTRAINT_CS:
			{
				ConstraintCS constraintCS = (ConstraintCS)theEObject;
				T result = caseConstraintCS(constraintCS);
				if (result == null) result = caseNamedElementCS(constraintCS);
				if (result == null) result = caseModelElementCS(constraintCS);
				if (result == null) result = caseNameable(constraintCS);
				if (result == null) result = casePivotableElementCS(constraintCS);
				if (result == null) result = caseElementCS(constraintCS);
				if (result == null) result = casePivotable(constraintCS);
				if (result == null) result = caseVisitableCS(constraintCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.CONTEXT_LESS_ELEMENT_CS:
			{
				ContextLessElementCS contextLessElementCS = (ContextLessElementCS)theEObject;
				T result = caseContextLessElementCS(contextLessElementCS);
				if (result == null) result = caseElementCS(contextLessElementCS);
				if (result == null) result = caseVisitableCS(contextLessElementCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.DATA_TYPE_CS:
			{
				DataTypeCS dataTypeCS = (DataTypeCS)theEObject;
				T result = caseDataTypeCS(dataTypeCS);
				if (result == null) result = caseClassCS(dataTypeCS);
				if (result == null) result = caseNamespaceCS(dataTypeCS);
				if (result == null) result = caseNamedElementCS(dataTypeCS);
				if (result == null) result = caseTypeCS(dataTypeCS);
				if (result == null) result = caseTemplateableElementCS(dataTypeCS);
				if (result == null) result = caseModelElementCS(dataTypeCS);
				if (result == null) result = caseNameable(dataTypeCS);
				if (result == null) result = casePivotableElementCS(dataTypeCS);
				if (result == null) result = caseElementCS(dataTypeCS);
				if (result == null) result = casePivotable(dataTypeCS);
				if (result == null) result = caseVisitableCS(dataTypeCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.DETAIL_CS:
			{
				DetailCS detailCS = (DetailCS)theEObject;
				T result = caseDetailCS(detailCS);
				if (result == null) result = caseNamedElementCS(detailCS);
				if (result == null) result = caseModelElementCS(detailCS);
				if (result == null) result = caseNameable(detailCS);
				if (result == null) result = casePivotableElementCS(detailCS);
				if (result == null) result = caseElementCS(detailCS);
				if (result == null) result = casePivotable(detailCS);
				if (result == null) result = caseVisitableCS(detailCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.DOCUMENTATION_CS:
			{
				DocumentationCS documentationCS = (DocumentationCS)theEObject;
				T result = caseDocumentationCS(documentationCS);
				if (result == null) result = caseAnnotationElementCS(documentationCS);
				if (result == null) result = caseNamedElementCS(documentationCS);
				if (result == null) result = caseModelElementCS(documentationCS);
				if (result == null) result = caseNameable(documentationCS);
				if (result == null) result = casePivotableElementCS(documentationCS);
				if (result == null) result = caseElementCS(documentationCS);
				if (result == null) result = casePivotable(documentationCS);
				if (result == null) result = caseVisitableCS(documentationCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.ELEMENT_CS:
			{
				ElementCS elementCS = (ElementCS)theEObject;
				T result = caseElementCS(elementCS);
				if (result == null) result = caseVisitableCS(elementCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.ELEMENT_REF_CS:
			{
				ElementRefCS elementRefCS = (ElementRefCS)theEObject;
				T result = caseElementRefCS(elementRefCS);
				if (result == null) result = casePivotableElementCS(elementRefCS);
				if (result == null) result = caseElementCS(elementRefCS);
				if (result == null) result = casePivotable(elementRefCS);
				if (result == null) result = caseVisitableCS(elementRefCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.ENUMERATION_CS:
			{
				EnumerationCS enumerationCS = (EnumerationCS)theEObject;
				T result = caseEnumerationCS(enumerationCS);
				if (result == null) result = caseClassCS(enumerationCS);
				if (result == null) result = caseNamespaceCS(enumerationCS);
				if (result == null) result = caseNamedElementCS(enumerationCS);
				if (result == null) result = caseTypeCS(enumerationCS);
				if (result == null) result = caseTemplateableElementCS(enumerationCS);
				if (result == null) result = caseModelElementCS(enumerationCS);
				if (result == null) result = caseNameable(enumerationCS);
				if (result == null) result = casePivotableElementCS(enumerationCS);
				if (result == null) result = caseElementCS(enumerationCS);
				if (result == null) result = casePivotable(enumerationCS);
				if (result == null) result = caseVisitableCS(enumerationCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.ENUMERATION_LITERAL_CS:
			{
				EnumerationLiteralCS enumerationLiteralCS = (EnumerationLiteralCS)theEObject;
				T result = caseEnumerationLiteralCS(enumerationLiteralCS);
				if (result == null) result = caseNamedElementCS(enumerationLiteralCS);
				if (result == null) result = caseModelElementCS(enumerationLiteralCS);
				if (result == null) result = caseNameable(enumerationLiteralCS);
				if (result == null) result = casePivotableElementCS(enumerationLiteralCS);
				if (result == null) result = caseElementCS(enumerationLiteralCS);
				if (result == null) result = casePivotable(enumerationLiteralCS);
				if (result == null) result = caseVisitableCS(enumerationLiteralCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.FEATURE_CS:
			{
				FeatureCS featureCS = (FeatureCS)theEObject;
				T result = caseFeatureCS(featureCS);
				if (result == null) result = caseTypedElementCS(featureCS);
				if (result == null) result = caseNamedElementCS(featureCS);
				if (result == null) result = caseModelElementCS(featureCS);
				if (result == null) result = caseNameable(featureCS);
				if (result == null) result = casePivotableElementCS(featureCS);
				if (result == null) result = caseElementCS(featureCS);
				if (result == null) result = casePivotable(featureCS);
				if (result == null) result = caseVisitableCS(featureCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.IMPORT_CS:
			{
				ImportCS importCS = (ImportCS)theEObject;
				T result = caseImportCS(importCS);
				if (result == null) result = caseNamespaceCS(importCS);
				if (result == null) result = caseNamedElementCS(importCS);
				if (result == null) result = caseModelElementCS(importCS);
				if (result == null) result = caseNameable(importCS);
				if (result == null) result = casePivotableElementCS(importCS);
				if (result == null) result = caseElementCS(importCS);
				if (result == null) result = casePivotable(importCS);
				if (result == null) result = caseVisitableCS(importCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.LAMBDA_TYPE_CS:
			{
				LambdaTypeCS lambdaTypeCS = (LambdaTypeCS)theEObject;
				T result = caseLambdaTypeCS(lambdaTypeCS);
				if (result == null) result = caseTypedRefCS(lambdaTypeCS);
				if (result == null) result = caseTemplateableElementCS(lambdaTypeCS);
				if (result == null) result = caseNameable(lambdaTypeCS);
				if (result == null) result = caseTypeRefCS(lambdaTypeCS);
				if (result == null) result = caseElementRefCS(lambdaTypeCS);
				if (result == null) result = casePivotableElementCS(lambdaTypeCS);
				if (result == null) result = caseElementCS(lambdaTypeCS);
				if (result == null) result = casePivotable(lambdaTypeCS);
				if (result == null) result = caseVisitableCS(lambdaTypeCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.MODEL_ELEMENT_CS:
			{
				ModelElementCS modelElementCS = (ModelElementCS)theEObject;
				T result = caseModelElementCS(modelElementCS);
				if (result == null) result = casePivotableElementCS(modelElementCS);
				if (result == null) result = caseElementCS(modelElementCS);
				if (result == null) result = casePivotable(modelElementCS);
				if (result == null) result = caseVisitableCS(modelElementCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.MODEL_ELEMENT_REF_CS:
			{
				ModelElementRefCS modelElementRefCS = (ModelElementRefCS)theEObject;
				T result = caseModelElementRefCS(modelElementRefCS);
				if (result == null) result = caseElementRefCS(modelElementRefCS);
				if (result == null) result = casePivotableElementCS(modelElementRefCS);
				if (result == null) result = caseElementCS(modelElementRefCS);
				if (result == null) result = casePivotable(modelElementRefCS);
				if (result == null) result = caseVisitableCS(modelElementRefCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.MULTIPLICITY_BOUNDS_CS:
			{
				MultiplicityBoundsCS multiplicityBoundsCS = (MultiplicityBoundsCS)theEObject;
				T result = caseMultiplicityBoundsCS(multiplicityBoundsCS);
				if (result == null) result = caseMultiplicityCS(multiplicityBoundsCS);
				if (result == null) result = caseElementCS(multiplicityBoundsCS);
				if (result == null) result = caseVisitableCS(multiplicityBoundsCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.MULTIPLICITY_CS:
			{
				MultiplicityCS multiplicityCS = (MultiplicityCS)theEObject;
				T result = caseMultiplicityCS(multiplicityCS);
				if (result == null) result = caseElementCS(multiplicityCS);
				if (result == null) result = caseVisitableCS(multiplicityCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.MULTIPLICITY_STRING_CS:
			{
				MultiplicityStringCS multiplicityStringCS = (MultiplicityStringCS)theEObject;
				T result = caseMultiplicityStringCS(multiplicityStringCS);
				if (result == null) result = caseMultiplicityCS(multiplicityStringCS);
				if (result == null) result = caseElementCS(multiplicityStringCS);
				if (result == null) result = caseVisitableCS(multiplicityStringCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.NAMED_ELEMENT_CS:
			{
				NamedElementCS namedElementCS = (NamedElementCS)theEObject;
				T result = caseNamedElementCS(namedElementCS);
				if (result == null) result = caseModelElementCS(namedElementCS);
				if (result == null) result = caseNameable(namedElementCS);
				if (result == null) result = casePivotableElementCS(namedElementCS);
				if (result == null) result = caseElementCS(namedElementCS);
				if (result == null) result = casePivotable(namedElementCS);
				if (result == null) result = caseVisitableCS(namedElementCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.NAMESPACE_CS:
			{
				NamespaceCS namespaceCS = (NamespaceCS)theEObject;
				T result = caseNamespaceCS(namespaceCS);
				if (result == null) result = caseNamedElementCS(namespaceCS);
				if (result == null) result = caseModelElementCS(namespaceCS);
				if (result == null) result = caseNameable(namespaceCS);
				if (result == null) result = casePivotableElementCS(namespaceCS);
				if (result == null) result = caseElementCS(namespaceCS);
				if (result == null) result = casePivotable(namespaceCS);
				if (result == null) result = caseVisitableCS(namespaceCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.OPERATION_CS:
			{
				OperationCS operationCS = (OperationCS)theEObject;
				T result = caseOperationCS(operationCS);
				if (result == null) result = caseFeatureCS(operationCS);
				if (result == null) result = caseTemplateableElementCS(operationCS);
				if (result == null) result = caseTypedElementCS(operationCS);
				if (result == null) result = caseNamedElementCS(operationCS);
				if (result == null) result = caseModelElementCS(operationCS);
				if (result == null) result = caseNameable(operationCS);
				if (result == null) result = casePivotableElementCS(operationCS);
				if (result == null) result = caseElementCS(operationCS);
				if (result == null) result = casePivotable(operationCS);
				if (result == null) result = caseVisitableCS(operationCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.PACKAGE_CS:
			{
				PackageCS packageCS = (PackageCS)theEObject;
				T result = casePackageCS(packageCS);
				if (result == null) result = casePackageOwnerCS(packageCS);
				if (result == null) result = caseNamespaceCS(packageCS);
				if (result == null) result = caseNamedElementCS(packageCS);
				if (result == null) result = caseModelElementCS(packageCS);
				if (result == null) result = casePivotableElementCS(packageCS);
				if (result == null) result = caseNameable(packageCS);
				if (result == null) result = caseElementCS(packageCS);
				if (result == null) result = casePivotable(packageCS);
				if (result == null) result = caseVisitableCS(packageCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.PACKAGE_OWNER_CS:
			{
				PackageOwnerCS packageOwnerCS = (PackageOwnerCS)theEObject;
				T result = casePackageOwnerCS(packageOwnerCS);
				if (result == null) result = caseModelElementCS(packageOwnerCS);
				if (result == null) result = casePivotableElementCS(packageOwnerCS);
				if (result == null) result = caseElementCS(packageOwnerCS);
				if (result == null) result = casePivotable(packageOwnerCS);
				if (result == null) result = caseVisitableCS(packageOwnerCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.PARAMETER_CS:
			{
				ParameterCS parameterCS = (ParameterCS)theEObject;
				T result = caseParameterCS(parameterCS);
				if (result == null) result = caseTypedElementCS(parameterCS);
				if (result == null) result = caseNamedElementCS(parameterCS);
				if (result == null) result = caseModelElementCS(parameterCS);
				if (result == null) result = caseNameable(parameterCS);
				if (result == null) result = casePivotableElementCS(parameterCS);
				if (result == null) result = caseElementCS(parameterCS);
				if (result == null) result = casePivotable(parameterCS);
				if (result == null) result = caseVisitableCS(parameterCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.PATH_ELEMENT_CS:
			{
				PathElementCS pathElementCS = (PathElementCS)theEObject;
				T result = casePathElementCS(pathElementCS);
				if (result == null) result = caseElementCS(pathElementCS);
				if (result == null) result = casePivotable(pathElementCS);
				if (result == null) result = caseVisitableCS(pathElementCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.PATH_ELEMENT_WITH_URICS:
			{
				PathElementWithURICS pathElementWithURICS = (PathElementWithURICS)theEObject;
				T result = casePathElementWithURICS(pathElementWithURICS);
				if (result == null) result = casePathElementCS(pathElementWithURICS);
				if (result == null) result = caseElementCS(pathElementWithURICS);
				if (result == null) result = casePivotable(pathElementWithURICS);
				if (result == null) result = caseVisitableCS(pathElementWithURICS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.PATH_NAME_CS:
			{
				PathNameCS pathNameCS = (PathNameCS)theEObject;
				T result = casePathNameCS(pathNameCS);
				if (result == null) result = caseElementCS(pathNameCS);
				if (result == null) result = casePivotable(pathNameCS);
				if (result == null) result = caseVisitableCS(pathNameCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.PIVOTABLE_ELEMENT_CS:
			{
				PivotableElementCS pivotableElementCS = (PivotableElementCS)theEObject;
				T result = casePivotableElementCS(pivotableElementCS);
				if (result == null) result = caseElementCS(pivotableElementCS);
				if (result == null) result = casePivotable(pivotableElementCS);
				if (result == null) result = caseVisitableCS(pivotableElementCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.PRIMITIVE_TYPE_REF_CS:
			{
				PrimitiveTypeRefCS primitiveTypeRefCS = (PrimitiveTypeRefCS)theEObject;
				T result = casePrimitiveTypeRefCS(primitiveTypeRefCS);
				if (result == null) result = caseTypedRefCS(primitiveTypeRefCS);
				if (result == null) result = caseNameable(primitiveTypeRefCS);
				if (result == null) result = caseTypeRefCS(primitiveTypeRefCS);
				if (result == null) result = caseElementRefCS(primitiveTypeRefCS);
				if (result == null) result = casePivotableElementCS(primitiveTypeRefCS);
				if (result == null) result = caseElementCS(primitiveTypeRefCS);
				if (result == null) result = casePivotable(primitiveTypeRefCS);
				if (result == null) result = caseVisitableCS(primitiveTypeRefCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.REFERENCE_CS:
			{
				ReferenceCS referenceCS = (ReferenceCS)theEObject;
				T result = caseReferenceCS(referenceCS);
				if (result == null) result = caseStructuralFeatureCS(referenceCS);
				if (result == null) result = caseFeatureCS(referenceCS);
				if (result == null) result = caseTypedElementCS(referenceCS);
				if (result == null) result = caseNamedElementCS(referenceCS);
				if (result == null) result = caseModelElementCS(referenceCS);
				if (result == null) result = caseNameable(referenceCS);
				if (result == null) result = casePivotableElementCS(referenceCS);
				if (result == null) result = caseElementCS(referenceCS);
				if (result == null) result = casePivotable(referenceCS);
				if (result == null) result = caseVisitableCS(referenceCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.ROOT_CS:
			{
				RootCS rootCS = (RootCS)theEObject;
				T result = caseRootCS(rootCS);
				if (result == null) result = caseModelElementCS(rootCS);
				if (result == null) result = casePivotableElementCS(rootCS);
				if (result == null) result = caseElementCS(rootCS);
				if (result == null) result = casePivotable(rootCS);
				if (result == null) result = caseVisitableCS(rootCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.ROOT_PACKAGE_CS:
			{
				RootPackageCS rootPackageCS = (RootPackageCS)theEObject;
				T result = caseRootPackageCS(rootPackageCS);
				if (result == null) result = casePackageOwnerCS(rootPackageCS);
				if (result == null) result = caseRootCS(rootPackageCS);
				if (result == null) result = caseModelElementCS(rootPackageCS);
				if (result == null) result = casePivotableElementCS(rootPackageCS);
				if (result == null) result = caseElementCS(rootPackageCS);
				if (result == null) result = casePivotable(rootPackageCS);
				if (result == null) result = caseVisitableCS(rootPackageCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.SPECIFICATION_CS:
			{
				SpecificationCS specificationCS = (SpecificationCS)theEObject;
				T result = caseSpecificationCS(specificationCS);
				if (result == null) result = caseModelElementCS(specificationCS);
				if (result == null) result = casePivotableElementCS(specificationCS);
				if (result == null) result = caseElementCS(specificationCS);
				if (result == null) result = casePivotable(specificationCS);
				if (result == null) result = caseVisitableCS(specificationCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.STRUCTURAL_FEATURE_CS:
			{
				StructuralFeatureCS structuralFeatureCS = (StructuralFeatureCS)theEObject;
				T result = caseStructuralFeatureCS(structuralFeatureCS);
				if (result == null) result = caseFeatureCS(structuralFeatureCS);
				if (result == null) result = caseTypedElementCS(structuralFeatureCS);
				if (result == null) result = caseNamedElementCS(structuralFeatureCS);
				if (result == null) result = caseModelElementCS(structuralFeatureCS);
				if (result == null) result = caseNameable(structuralFeatureCS);
				if (result == null) result = casePivotableElementCS(structuralFeatureCS);
				if (result == null) result = caseElementCS(structuralFeatureCS);
				if (result == null) result = casePivotable(structuralFeatureCS);
				if (result == null) result = caseVisitableCS(structuralFeatureCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.STRUCTURED_CLASS_CS:
			{
				StructuredClassCS structuredClassCS = (StructuredClassCS)theEObject;
				T result = caseStructuredClassCS(structuredClassCS);
				if (result == null) result = caseClassCS(structuredClassCS);
				if (result == null) result = caseNamespaceCS(structuredClassCS);
				if (result == null) result = caseNamedElementCS(structuredClassCS);
				if (result == null) result = caseTypeCS(structuredClassCS);
				if (result == null) result = caseTemplateableElementCS(structuredClassCS);
				if (result == null) result = caseModelElementCS(structuredClassCS);
				if (result == null) result = caseNameable(structuredClassCS);
				if (result == null) result = casePivotableElementCS(structuredClassCS);
				if (result == null) result = caseElementCS(structuredClassCS);
				if (result == null) result = casePivotable(structuredClassCS);
				if (result == null) result = caseVisitableCS(structuredClassCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TEMPLATE_BINDING_CS:
			{
				TemplateBindingCS templateBindingCS = (TemplateBindingCS)theEObject;
				T result = caseTemplateBindingCS(templateBindingCS);
				if (result == null) result = caseElementRefCS(templateBindingCS);
				if (result == null) result = casePivotableElementCS(templateBindingCS);
				if (result == null) result = caseElementCS(templateBindingCS);
				if (result == null) result = casePivotable(templateBindingCS);
				if (result == null) result = caseVisitableCS(templateBindingCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TEMPLATE_PARAMETER_CS:
			{
				TemplateParameterCS templateParameterCS = (TemplateParameterCS)theEObject;
				T result = caseTemplateParameterCS(templateParameterCS);
				if (result == null) result = caseNamedElementCS(templateParameterCS);
				if (result == null) result = caseModelElementCS(templateParameterCS);
				if (result == null) result = caseNameable(templateParameterCS);
				if (result == null) result = casePivotableElementCS(templateParameterCS);
				if (result == null) result = caseElementCS(templateParameterCS);
				if (result == null) result = casePivotable(templateParameterCS);
				if (result == null) result = caseVisitableCS(templateParameterCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TEMPLATE_PARAMETER_SUBSTITUTION_CS:
			{
				TemplateParameterSubstitutionCS templateParameterSubstitutionCS = (TemplateParameterSubstitutionCS)theEObject;
				T result = caseTemplateParameterSubstitutionCS(templateParameterSubstitutionCS);
				if (result == null) result = caseModelElementCS(templateParameterSubstitutionCS);
				if (result == null) result = casePivotableElementCS(templateParameterSubstitutionCS);
				if (result == null) result = caseElementCS(templateParameterSubstitutionCS);
				if (result == null) result = casePivotable(templateParameterSubstitutionCS);
				if (result == null) result = caseVisitableCS(templateParameterSubstitutionCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TEMPLATE_SIGNATURE_CS:
			{
				TemplateSignatureCS templateSignatureCS = (TemplateSignatureCS)theEObject;
				T result = caseTemplateSignatureCS(templateSignatureCS);
				if (result == null) result = caseModelElementCS(templateSignatureCS);
				if (result == null) result = casePivotableElementCS(templateSignatureCS);
				if (result == null) result = caseElementCS(templateSignatureCS);
				if (result == null) result = casePivotable(templateSignatureCS);
				if (result == null) result = caseVisitableCS(templateSignatureCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TEMPLATEABLE_ELEMENT_CS:
			{
				TemplateableElementCS templateableElementCS = (TemplateableElementCS)theEObject;
				T result = caseTemplateableElementCS(templateableElementCS);
				if (result == null) result = caseElementCS(templateableElementCS);
				if (result == null) result = caseVisitableCS(templateableElementCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TUPLE_PART_CS:
			{
				TuplePartCS tuplePartCS = (TuplePartCS)theEObject;
				T result = caseTuplePartCS(tuplePartCS);
				if (result == null) result = caseTypedElementCS(tuplePartCS);
				if (result == null) result = caseNamedElementCS(tuplePartCS);
				if (result == null) result = caseModelElementCS(tuplePartCS);
				if (result == null) result = caseNameable(tuplePartCS);
				if (result == null) result = casePivotableElementCS(tuplePartCS);
				if (result == null) result = caseElementCS(tuplePartCS);
				if (result == null) result = casePivotable(tuplePartCS);
				if (result == null) result = caseVisitableCS(tuplePartCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TUPLE_TYPE_CS:
			{
				TupleTypeCS tupleTypeCS = (TupleTypeCS)theEObject;
				T result = caseTupleTypeCS(tupleTypeCS);
				if (result == null) result = caseTypedRefCS(tupleTypeCS);
				if (result == null) result = caseNameable(tupleTypeCS);
				if (result == null) result = caseTypeRefCS(tupleTypeCS);
				if (result == null) result = caseElementRefCS(tupleTypeCS);
				if (result == null) result = casePivotableElementCS(tupleTypeCS);
				if (result == null) result = caseElementCS(tupleTypeCS);
				if (result == null) result = casePivotable(tupleTypeCS);
				if (result == null) result = caseVisitableCS(tupleTypeCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TYPE_CS:
			{
				TypeCS typeCS = (TypeCS)theEObject;
				T result = caseTypeCS(typeCS);
				if (result == null) result = caseModelElementCS(typeCS);
				if (result == null) result = casePivotableElementCS(typeCS);
				if (result == null) result = caseElementCS(typeCS);
				if (result == null) result = casePivotable(typeCS);
				if (result == null) result = caseVisitableCS(typeCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TYPE_PARAMETER_CS:
			{
				TypeParameterCS typeParameterCS = (TypeParameterCS)theEObject;
				T result = caseTypeParameterCS(typeParameterCS);
				if (result == null) result = caseTemplateParameterCS(typeParameterCS);
				if (result == null) result = caseTypeCS(typeParameterCS);
				if (result == null) result = caseNamedElementCS(typeParameterCS);
				if (result == null) result = caseModelElementCS(typeParameterCS);
				if (result == null) result = caseNameable(typeParameterCS);
				if (result == null) result = casePivotableElementCS(typeParameterCS);
				if (result == null) result = caseElementCS(typeParameterCS);
				if (result == null) result = casePivotable(typeParameterCS);
				if (result == null) result = caseVisitableCS(typeParameterCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TYPE_REF_CS:
			{
				TypeRefCS typeRefCS = (TypeRefCS)theEObject;
				T result = caseTypeRefCS(typeRefCS);
				if (result == null) result = caseElementRefCS(typeRefCS);
				if (result == null) result = casePivotableElementCS(typeRefCS);
				if (result == null) result = caseElementCS(typeRefCS);
				if (result == null) result = casePivotable(typeRefCS);
				if (result == null) result = caseVisitableCS(typeRefCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TYPED_ELEMENT_CS:
			{
				TypedElementCS typedElementCS = (TypedElementCS)theEObject;
				T result = caseTypedElementCS(typedElementCS);
				if (result == null) result = caseNamedElementCS(typedElementCS);
				if (result == null) result = caseModelElementCS(typedElementCS);
				if (result == null) result = caseNameable(typedElementCS);
				if (result == null) result = casePivotableElementCS(typedElementCS);
				if (result == null) result = caseElementCS(typedElementCS);
				if (result == null) result = casePivotable(typedElementCS);
				if (result == null) result = caseVisitableCS(typedElementCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TYPED_REF_CS:
			{
				TypedRefCS typedRefCS = (TypedRefCS)theEObject;
				T result = caseTypedRefCS(typedRefCS);
				if (result == null) result = caseTypeRefCS(typedRefCS);
				if (result == null) result = caseElementRefCS(typedRefCS);
				if (result == null) result = casePivotableElementCS(typedRefCS);
				if (result == null) result = caseElementCS(typedRefCS);
				if (result == null) result = casePivotable(typedRefCS);
				if (result == null) result = caseVisitableCS(typedRefCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.TYPED_TYPE_REF_CS:
			{
				TypedTypeRefCS typedTypeRefCS = (TypedTypeRefCS)theEObject;
				T result = caseTypedTypeRefCS(typedTypeRefCS);
				if (result == null) result = caseTypedRefCS(typedTypeRefCS);
				if (result == null) result = caseTypeRefCS(typedTypeRefCS);
				if (result == null) result = caseElementRefCS(typedTypeRefCS);
				if (result == null) result = casePivotableElementCS(typedTypeRefCS);
				if (result == null) result = caseElementCS(typedTypeRefCS);
				if (result == null) result = casePivotable(typedTypeRefCS);
				if (result == null) result = caseVisitableCS(typedTypeRefCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.VISITABLE_CS:
			{
				VisitableCS visitableCS = (VisitableCS)theEObject;
				T result = caseVisitableCS(visitableCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case BaseCSPackage.WILDCARD_TYPE_REF_CS:
			{
				WildcardTypeRefCS wildcardTypeRefCS = (WildcardTypeRefCS)theEObject;
				T result = caseWildcardTypeRefCS(wildcardTypeRefCS);
				if (result == null) result = caseTypeRefCS(wildcardTypeRefCS);
				if (result == null) result = caseElementRefCS(wildcardTypeRefCS);
				if (result == null) result = casePivotableElementCS(wildcardTypeRefCS);
				if (result == null) result = caseElementCS(wildcardTypeRefCS);
				if (result == null) result = casePivotable(wildcardTypeRefCS);
				if (result == null) result = caseVisitableCS(wildcardTypeRefCS);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotationCS(AnnotationCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Annotation Element CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Annotation Element CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnnotationElementCS(AnnotationElementCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Attribute CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Attribute CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAttributeCS(AttributeCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Class CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Class CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseClassCS(ClassCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constraint CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constraint CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstraintCS(ConstraintCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Context Less Element CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Context Less Element CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseContextLessElementCS(ContextLessElementCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Data Type CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Data Type CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDataTypeCS(DataTypeCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Detail CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Detail CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDetailCS(DetailCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Documentation CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Documentation CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseDocumentationCS(DocumentationCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementCS(ElementCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Element Ref CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Element Ref CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseElementRefCS(ElementRefCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumerationCS(EnumerationCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Enumeration Literal CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Enumeration Literal CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseEnumerationLiteralCS(EnumerationLiteralCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Feature CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Feature CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFeatureCS(FeatureCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Import CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Import CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseImportCS(ImportCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Lambda Type CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Lambda Type CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLambdaTypeCS(LambdaTypeCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElementCS(ModelElementCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Model Element Ref CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Model Element Ref CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseModelElementRefCS(ModelElementRefCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multiplicity Bounds CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multiplicity Bounds CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiplicityBoundsCS(MultiplicityBoundsCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multiplicity CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multiplicity CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiplicityCS(MultiplicityCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Multiplicity String CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Multiplicity String CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseMultiplicityStringCS(MultiplicityStringCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElementCS(NamedElementCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Namespace CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Namespace CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamespaceCS(NamespaceCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Operation CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Operation CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOperationCS(OperationCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageCS(PackageCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Package Owner CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Package Owner CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePackageOwnerCS(PackageOwnerCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Parameter CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Parameter CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterCS(ParameterCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Path Element CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Path Element CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePathElementCS(PathElementCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Path Element With URICS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Path Element With URICS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePathElementWithURICS(PathElementWithURICS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Path Name CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Path Name CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePathNameCS(PathNameCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pivotable Element CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pivotable Element CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePivotableElementCS(PivotableElementCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Primitive Type Ref CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Primitive Type Ref CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePrimitiveTypeRefCS(PrimitiveTypeRefCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Reference CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Reference CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseReferenceCS(ReferenceCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Root CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Root CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRootCS(RootCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Root Package CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Root Package CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseRootPackageCS(RootPackageCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Specification CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Specification CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSpecificationCS(SpecificationCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structured Class CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structured Class CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructuredClassCS(StructuredClassCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Structural Feature CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Structural Feature CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStructuralFeatureCS(StructuralFeatureCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Binding CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Binding CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateBindingCS(TemplateBindingCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Parameter CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Parameter CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateParameterCS(TemplateParameterCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Parameter Substitution CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Parameter Substitution CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateParameterSubstitutionCS(TemplateParameterSubstitutionCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Template Signature CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Template Signature CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateSignatureCS(TemplateSignatureCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Templateable Element CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Templateable Element CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTemplateableElementCS(TemplateableElementCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Part CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Part CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTuplePartCS(TuplePartCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Type CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Type CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTupleTypeCS(TupleTypeCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeCS(TypeCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Parameter CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Parameter CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeParameterCS(TypeParameterCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Ref CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Ref CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeRefCS(TypeRefCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Element CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Element CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypedElementCS(TypedElementCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Ref CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Ref CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypedRefCS(TypedRefCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Typed Type Ref CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Typed Type Ref CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypedTypeRefCS(TypedTypeRefCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Visitable CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Visitable CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVisitableCS(VisitableCS object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Wildcard Type Ref CS</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Wildcard Type Ref CS</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseWildcardTypeRefCS(WildcardTypeRefCS object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Pivotable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Pivotable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T casePivotable(Pivotable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Nameable</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNameable(Nameable object)
	{
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //BaseCSSwitch
