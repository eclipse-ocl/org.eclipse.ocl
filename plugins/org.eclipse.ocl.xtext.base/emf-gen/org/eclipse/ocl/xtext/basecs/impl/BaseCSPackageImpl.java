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

package org.eclipse.ocl.xtext.basecs.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.scoping.ScopeFilter;
import org.eclipse.ocl.xtext.basecs.AnnotationCS;
import org.eclipse.ocl.xtext.basecs.AnnotationElementCS;
import org.eclipse.ocl.xtext.basecs.AttributeCS;
import org.eclipse.ocl.xtext.basecs.BaseCSFactory;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.ClassCS;
import org.eclipse.ocl.xtext.basecs.ConstraintCS;
import org.eclipse.ocl.xtext.basecs.ContextLessElementCS;
import org.eclipse.ocl.xtext.basecs.DataTypeCS;
import org.eclipse.ocl.xtext.basecs.DetailCS;
import org.eclipse.ocl.xtext.basecs.DocumentationCS;
import org.eclipse.ocl.xtext.basecs.ElementCS;
import org.eclipse.ocl.xtext.basecs.ElementRefCS;
import org.eclipse.ocl.xtext.basecs.EnumerationCS;
import org.eclipse.ocl.xtext.basecs.EnumerationLiteralCS;
import org.eclipse.ocl.xtext.basecs.FeatureCS;
import org.eclipse.ocl.xtext.basecs.ImportCS;
import org.eclipse.ocl.xtext.basecs.LambdaTypeCS;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.basecs.ModelElementRefCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityBoundsCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityCS;
import org.eclipse.ocl.xtext.basecs.MultiplicityStringCS;
import org.eclipse.ocl.xtext.basecs.NamedElementCS;
import org.eclipse.ocl.xtext.basecs.NamespaceCS;
import org.eclipse.ocl.xtext.basecs.OperationCS;
import org.eclipse.ocl.xtext.basecs.PackageCS;
import org.eclipse.ocl.xtext.basecs.PackageOwnerCS;
import org.eclipse.ocl.xtext.basecs.ParameterCS;
import org.eclipse.ocl.xtext.basecs.PathElementCS;
import org.eclipse.ocl.xtext.basecs.PathElementWithURICS;
import org.eclipse.ocl.xtext.basecs.PathNameCS;
import org.eclipse.ocl.xtext.basecs.PivotableElementCS;
import org.eclipse.ocl.xtext.basecs.PrimitiveTypeRefCS;
import org.eclipse.ocl.xtext.basecs.ReferenceCS;
import org.eclipse.ocl.xtext.basecs.RootCS;
import org.eclipse.ocl.xtext.basecs.RootPackageCS;
import org.eclipse.ocl.xtext.basecs.SpecificationCS;
import org.eclipse.ocl.xtext.basecs.StructuralFeatureCS;
import org.eclipse.ocl.xtext.basecs.StructuredClassCS;
import org.eclipse.ocl.xtext.basecs.TemplateBindingCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterCS;
import org.eclipse.ocl.xtext.basecs.TemplateParameterSubstitutionCS;
import org.eclipse.ocl.xtext.basecs.TemplateSignatureCS;
import org.eclipse.ocl.xtext.basecs.TemplateableElementCS;
import org.eclipse.ocl.xtext.basecs.TuplePartCS;
import org.eclipse.ocl.xtext.basecs.TupleTypeCS;
import org.eclipse.ocl.xtext.basecs.TypeCS;
import org.eclipse.ocl.xtext.basecs.TypeParameterCS;
import org.eclipse.ocl.xtext.basecs.TypeRefCS;
import org.eclipse.ocl.xtext.basecs.TypedElementCS;
import org.eclipse.ocl.xtext.basecs.TypedRefCS;
import org.eclipse.ocl.xtext.basecs.PathTypeCS;
import org.eclipse.ocl.xtext.basecs.WildcardTypeRefCS;
import org.eclipse.ocl.xtext.basecs.util.BaseCSValidator;
import org.eclipse.ocl.xtext.basecs.util.VisitableCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BaseCSPackageImpl extends EPackageImpl implements BaseCSPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass annotationElementCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass attributeCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass classCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass constraintCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass contextLessElementCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass dataTypeCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass detailCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass documentationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementRefCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumerationLiteralCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass importCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass lambdaTypeCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass modelElementRefCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiplicityBoundsCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiplicityCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass multiplicityStringCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedElementCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namespaceCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass packageOwnerCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass parameterCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pathElementCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pathElementWithURICSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pathNameCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pathTypeCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pivotableElementCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveTypeRefCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass referenceCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rootCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rootPackageCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass specificationCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structuredClassCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass structuralFeatureCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateBindingCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateParameterCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateParameterSubstitutionCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateSignatureCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass templateableElementCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tuplePartCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleTypeCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeParameterCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeRefCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typedElementCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typedRefCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass visitableCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass wildcardTypeRefCSEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType bigNumberEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType csiEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType scopeFilterEDataType = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.ocl.xtext.basecs.BaseCSPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private BaseCSPackageImpl() {
		super(eNS_URI, BaseCSFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link BaseCSPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static BaseCSPackage init() {
		if (isInited) return (BaseCSPackage)EPackage.Registry.INSTANCE.getEPackage(BaseCSPackage.eNS_URI);

		// Obtain or create and register package
		BaseCSPackageImpl theBaseCSPackage = (BaseCSPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BaseCSPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BaseCSPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		EcorePackage.eINSTANCE.eClass();
		PivotPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theBaseCSPackage.createPackageContents();

		// Initialize created meta-data
		theBaseCSPackage.initializePackageContents();

		// Register package validator
		EValidator.Registry.INSTANCE.put
			(theBaseCSPackage, 
			 new EValidator.Descriptor()
			 {
				 @Override
				public EValidator getEValidator()
				 {
					 return BaseCSValidator.INSTANCE;
				 }
			 });

		// Mark meta-data to indicate it can't be changed
		theBaseCSPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(BaseCSPackage.eNS_URI, theBaseCSPackage);
		return theBaseCSPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnnotationCS()
	{
		return annotationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnnotationCS_OwnedContents()
	{
		return (EReference)annotationCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnnotationCS_OwnedReferences()
	{
		return (EReference)annotationCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAnnotationElementCS() {
		return annotationElementCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAnnotationElementCS_OwnedDetails()
	{
		return (EReference)annotationElementCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAttributeCS() {
		return attributeCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getClassCS()
	{
		return classCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getClassCS_OwningPackage()
	{
		return (EReference)classCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getClassCS_InstanceClassName()
	{
		return (EAttribute)classCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getClassCS_OwnedConstraints()
	{
		return (EReference)classCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getConstraintCS()
	{
		return constraintCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getConstraintCS_Stereotype()
	{
		return (EAttribute)constraintCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraintCS_OwnedSpecification()
	{
		return (EReference)constraintCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getConstraintCS_OwnedMessageSpecification()
	{
		return (EReference)constraintCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getContextLessElementCS()
	{
		return contextLessElementCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDataTypeCS()
	{
		return dataTypeCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDataTypeCS_IsPrimitive()
	{
		return (EAttribute)dataTypeCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDataTypeCS_IsSerializable()
	{
		return (EAttribute)dataTypeCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDetailCS() {
		return detailCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDetailCS_Values()
	{
		return (EAttribute)detailCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDocumentationCS()
	{
		return documentationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getDocumentationCS_Value()
	{
		return (EAttribute)documentationCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getElementCS() {
		return elementCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getElementCS_Csi()
	{
		return (EAttribute)elementCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getElementCS_Parent()
	{
		return (EReference)elementCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getElementRefCS()
	{
		return elementRefCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnumerationCS()
	{
		return enumerationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnumerationCS_IsSerializable()
	{
		return (EAttribute)enumerationCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getEnumerationCS_OwnedLiterals()
	{
		return (EReference)enumerationCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getEnumerationLiteralCS()
	{
		return enumerationLiteralCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getEnumerationLiteralCS_Value()
	{
		return (EAttribute)enumerationLiteralCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getFeatureCS() {
		return featureCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getImportCS() {
		return importCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getImportCS_OwnedPathName()
	{
		return (EReference)importCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getImportCS_ReferredNamespace()
	{
		return (EReference)importCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getImportCS_IsAll()
	{
		return (EAttribute)importCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLambdaTypeCS()
	{
		return lambdaTypeCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLambdaTypeCS_Name()
	{
		return (EAttribute)lambdaTypeCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLambdaTypeCS_OwnedParameterTypes()
	{
		return (EReference)lambdaTypeCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getLambdaTypeCS_OwnedResultType()
	{
		return (EReference)lambdaTypeCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getModelElementCS() {
		return modelElementCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getModelElementCS_OwnedAnnotations()
	{
		return (EReference)modelElementCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getModelElementCS_OriginalXmiId() {
		return (EAttribute)modelElementCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getModelElementRefCS()
	{
		return modelElementRefCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getModelElementRefCS_OwnedPathName()
	{
		return (EReference)modelElementRefCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getModelElementRefCS_ReferredElement()
	{
		return (EReference)modelElementRefCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMultiplicityBoundsCS()
	{
		return multiplicityBoundsCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMultiplicityBoundsCS_LowerBound()
	{
		return (EAttribute)multiplicityBoundsCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMultiplicityBoundsCS_UpperBound()
	{
		return (EAttribute)multiplicityBoundsCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMultiplicityCS()
	{
		return multiplicityCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMultiplicityCS_IsNullFree()
	{
		return (EAttribute)multiplicityCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getMultiplicityStringCS()
	{
		return multiplicityStringCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getMultiplicityStringCS_StringBounds()
	{
		return (EAttribute)multiplicityStringCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNamedElementCS() {
		return namedElementCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getNamedElementCS_Name()
	{
		return (EAttribute)namedElementCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNamespaceCS() {
		return namespaceCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getOperationCS() {
		return operationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationCS_OwningClass() {
		return (EReference)operationCSEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationCS_OwnedParameters()
	{
		return (EReference)operationCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationCS_OwnedExceptions()
	{
		return (EReference)operationCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationCS_OwnedPreconditions()
	{
		return (EReference)operationCSEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationCS_OwnedPostconditions()
	{
		return (EReference)operationCSEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getOperationCS_OwnedBodyExpressions()
	{
		return (EReference)operationCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackageCS() {
		return packageCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageCS_OwnedClasses()
	{
		return (EReference)packageCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageCS_NsPrefix()
	{
		return (EAttribute)packageCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPackageCS_NsURI()
	{
		return (EAttribute)packageCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPackageOwnerCS()
	{
		return packageOwnerCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPackageOwnerCS_OwnedPackages()
	{
		return (EReference)packageOwnerCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getParameterCS() {
		return parameterCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getParameterCS_OwningOperation()
	{
		return (EReference)parameterCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPathElementCS()
	{
		return pathElementCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPathElementCS_OwningPathName()
	{
		return (EReference)pathElementCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPathElementCS_ReferredElement()
	{
		return (EReference)pathElementCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPathElementCS_ElementType()
	{
		return (EReference)pathElementCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPathElementWithURICS()
	{
		return pathElementWithURICSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPathElementWithURICS_Uri()
	{
		return (EAttribute)pathElementWithURICSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPathNameCS()
	{
		return pathNameCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPathNameCS_OwnedPathElements()
	{
		return (EReference)pathNameCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPathNameCS_ReferredElement()
	{
		return (EReference)pathNameCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPathNameCS_Context()
	{
		return (EReference)pathNameCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPathNameCS_ScopeFilter()
	{
		return (EAttribute)pathNameCSEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPathTypeCS()
	{
		return pathTypeCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPathTypeCS_IsTypeof()
	{
		return (EAttribute)pathTypeCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPathTypeCS_OwnedBinding()
	{
		return (EReference)pathTypeCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPathTypeCS_OwnedPathName()
	{
		return (EReference)pathTypeCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPathTypeCS_ReferredType()
	{
		return (EReference)pathTypeCSEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPivotableElementCS()
	{
		return pivotableElementCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getPivotableElementCS_Pivot()
	{
		return (EReference)pivotableElementCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPrimitiveTypeRefCS() {
		return primitiveTypeRefCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getPrimitiveTypeRefCS_Name()
	{
		return (EAttribute)primitiveTypeRefCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReferenceCS() {
		return referenceCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReferenceCS_ReferredOpposite()
	{
		return (EReference)referenceCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReferenceCS_ReferredKeys()
	{
		return (EReference)referenceCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRootCS()
	{
		return rootCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getRootCS_OwnedImports()
	{
		return (EReference)rootCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getRootPackageCS()
	{
		return rootPackageCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSpecificationCS()
	{
		return specificationCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSpecificationCS_ExprString()
	{
		return (EAttribute)specificationCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStructuredClassCS()
	{
		return structuredClassCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStructuredClassCS_IsAbstract()
	{
		return (EAttribute)structuredClassCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStructuredClassCS_IsInterface()
	{
		return (EAttribute)structuredClassCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStructuredClassCS_OwnedSuperTypes()
	{
		return (EReference)structuredClassCSEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStructuredClassCS_OwnedOperations()
	{
		return (EReference)structuredClassCSEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStructuredClassCS_OwnedProperties()
	{
		return (EReference)structuredClassCSEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStructuredClassCS_OwnedMetaclass()
	{
		return (EReference)structuredClassCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStructuralFeatureCS() {
		return structuralFeatureCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStructuralFeatureCS_OwningClass()
	{
		return (EReference)structuralFeatureCSEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStructuralFeatureCS_Default()
	{
		return (EAttribute)structuralFeatureCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStructuralFeatureCS_OwnedDefaultExpressions()
	{
		return (EReference)structuralFeatureCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getStructuralFeatureCS_OwnedDerivedConstraints()
	{
		return (EReference)structuralFeatureCSEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateBindingCS() {
		return templateBindingCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateBindingCS_OwningElement()
	{
		return (EReference)templateBindingCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateBindingCS_OwnedSubstitutions()
	{
		return (EReference)templateBindingCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateParameterCS() {
		return templateParameterCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateParameterCS_OwningSignature()
	{
		return (EReference)templateParameterCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateParameterSubstitutionCS() {
		return templateParameterSubstitutionCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateParameterSubstitutionCS_OwningBinding()
	{
		return (EReference)templateParameterSubstitutionCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateParameterSubstitutionCS_OwnedActualParameter() {
		return (EReference)templateParameterSubstitutionCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateSignatureCS() {
		return templateSignatureCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateSignatureCS_OwningElement()
	{
		return (EReference)templateSignatureCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateSignatureCS_OwnedParameters()
	{
		return (EReference)templateSignatureCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTemplateableElementCS() {
		return templateableElementCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTemplateableElementCS_OwnedSignature()
	{
		return (EReference)templateableElementCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTuplePartCS()
	{
		return tuplePartCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTupleTypeCS()
	{
		return tupleTypeCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTupleTypeCS_Name()
	{
		return (EAttribute)tupleTypeCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTupleTypeCS_OwnedParts()
	{
		return (EReference)tupleTypeCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeCS() {
		return typeCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeParameterCS() {
		return typeParameterCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypeParameterCS_OwnedExtends() {
		return (EReference)typeParameterCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypeRefCS() {
		return typeRefCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypedElementCS() {
		return typedElementCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypedElementCS_OwnedType() {
		return (EReference)typedElementCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getTypedElementCS_Qualifiers()
	{
		return (EAttribute)typedElementCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getTypedRefCS() {
		return typedRefCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getTypedRefCS_OwnedMultiplicity()
	{
		return (EReference)typedRefCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVisitableCS()
	{
		return visitableCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getWildcardTypeRefCS() {
		return wildcardTypeRefCSEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWildcardTypeRefCS_OwnedExtends()
	{
		return (EReference)wildcardTypeRefCSEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getWildcardTypeRefCS_OwnedSuper()
	{
		return (EReference)wildcardTypeRefCSEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getBigNumber()
	{
		return bigNumberEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getCSI()
	{
		return csiEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getScopeFilter()
	{
		return scopeFilterEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BaseCSFactory getBaseCSFactory()
	{
		return (BaseCSFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		annotationCSEClass = createEClass(ANNOTATION_CS);
		createEReference(annotationCSEClass, ANNOTATION_CS__OWNED_CONTENTS);
		createEReference(annotationCSEClass, ANNOTATION_CS__OWNED_REFERENCES);

		annotationElementCSEClass = createEClass(ANNOTATION_ELEMENT_CS);
		createEReference(annotationElementCSEClass, ANNOTATION_ELEMENT_CS__OWNED_DETAILS);

		attributeCSEClass = createEClass(ATTRIBUTE_CS);

		classCSEClass = createEClass(CLASS_CS);
		createEAttribute(classCSEClass, CLASS_CS__INSTANCE_CLASS_NAME);
		createEReference(classCSEClass, CLASS_CS__OWNED_CONSTRAINTS);
		createEReference(classCSEClass, CLASS_CS__OWNING_PACKAGE);

		constraintCSEClass = createEClass(CONSTRAINT_CS);
		createEReference(constraintCSEClass, CONSTRAINT_CS__OWNED_MESSAGE_SPECIFICATION);
		createEReference(constraintCSEClass, CONSTRAINT_CS__OWNED_SPECIFICATION);
		createEAttribute(constraintCSEClass, CONSTRAINT_CS__STEREOTYPE);

		contextLessElementCSEClass = createEClass(CONTEXT_LESS_ELEMENT_CS);

		dataTypeCSEClass = createEClass(DATA_TYPE_CS);
		createEAttribute(dataTypeCSEClass, DATA_TYPE_CS__IS_PRIMITIVE);
		createEAttribute(dataTypeCSEClass, DATA_TYPE_CS__IS_SERIALIZABLE);

		detailCSEClass = createEClass(DETAIL_CS);
		createEAttribute(detailCSEClass, DETAIL_CS__VALUES);

		documentationCSEClass = createEClass(DOCUMENTATION_CS);
		createEAttribute(documentationCSEClass, DOCUMENTATION_CS__VALUE);

		elementCSEClass = createEClass(ELEMENT_CS);
		createEAttribute(elementCSEClass, ELEMENT_CS__CSI);
		createEReference(elementCSEClass, ELEMENT_CS__PARENT);

		elementRefCSEClass = createEClass(ELEMENT_REF_CS);

		enumerationCSEClass = createEClass(ENUMERATION_CS);
		createEAttribute(enumerationCSEClass, ENUMERATION_CS__IS_SERIALIZABLE);
		createEReference(enumerationCSEClass, ENUMERATION_CS__OWNED_LITERALS);

		enumerationLiteralCSEClass = createEClass(ENUMERATION_LITERAL_CS);
		createEAttribute(enumerationLiteralCSEClass, ENUMERATION_LITERAL_CS__VALUE);

		featureCSEClass = createEClass(FEATURE_CS);

		importCSEClass = createEClass(IMPORT_CS);
		createEAttribute(importCSEClass, IMPORT_CS__IS_ALL);
		createEReference(importCSEClass, IMPORT_CS__OWNED_PATH_NAME);
		createEReference(importCSEClass, IMPORT_CS__REFERRED_NAMESPACE);

		lambdaTypeCSEClass = createEClass(LAMBDA_TYPE_CS);
		createEAttribute(lambdaTypeCSEClass, LAMBDA_TYPE_CS__NAME);
		createEReference(lambdaTypeCSEClass, LAMBDA_TYPE_CS__OWNED_PARAMETER_TYPES);
		createEReference(lambdaTypeCSEClass, LAMBDA_TYPE_CS__OWNED_RESULT_TYPE);

		modelElementCSEClass = createEClass(MODEL_ELEMENT_CS);
		createEAttribute(modelElementCSEClass, MODEL_ELEMENT_CS__ORIGINAL_XMI_ID);
		createEReference(modelElementCSEClass, MODEL_ELEMENT_CS__OWNED_ANNOTATIONS);

		modelElementRefCSEClass = createEClass(MODEL_ELEMENT_REF_CS);
		createEReference(modelElementRefCSEClass, MODEL_ELEMENT_REF_CS__OWNED_PATH_NAME);
		createEReference(modelElementRefCSEClass, MODEL_ELEMENT_REF_CS__REFERRED_ELEMENT);

		multiplicityBoundsCSEClass = createEClass(MULTIPLICITY_BOUNDS_CS);
		createEAttribute(multiplicityBoundsCSEClass, MULTIPLICITY_BOUNDS_CS__LOWER_BOUND);
		createEAttribute(multiplicityBoundsCSEClass, MULTIPLICITY_BOUNDS_CS__UPPER_BOUND);

		multiplicityCSEClass = createEClass(MULTIPLICITY_CS);
		createEAttribute(multiplicityCSEClass, MULTIPLICITY_CS__IS_NULL_FREE);

		multiplicityStringCSEClass = createEClass(MULTIPLICITY_STRING_CS);
		createEAttribute(multiplicityStringCSEClass, MULTIPLICITY_STRING_CS__STRING_BOUNDS);

		namedElementCSEClass = createEClass(NAMED_ELEMENT_CS);
		createEAttribute(namedElementCSEClass, NAMED_ELEMENT_CS__NAME);

		namespaceCSEClass = createEClass(NAMESPACE_CS);

		operationCSEClass = createEClass(OPERATION_CS);
		createEReference(operationCSEClass, OPERATION_CS__OWNED_BODY_EXPRESSIONS);
		createEReference(operationCSEClass, OPERATION_CS__OWNED_EXCEPTIONS);
		createEReference(operationCSEClass, OPERATION_CS__OWNED_PARAMETERS);
		createEReference(operationCSEClass, OPERATION_CS__OWNED_POSTCONDITIONS);
		createEReference(operationCSEClass, OPERATION_CS__OWNED_PRECONDITIONS);
		createEReference(operationCSEClass, OPERATION_CS__OWNING_CLASS);

		packageCSEClass = createEClass(PACKAGE_CS);
		createEAttribute(packageCSEClass, PACKAGE_CS__NS_PREFIX);
		createEAttribute(packageCSEClass, PACKAGE_CS__NS_URI);
		createEReference(packageCSEClass, PACKAGE_CS__OWNED_CLASSES);

		packageOwnerCSEClass = createEClass(PACKAGE_OWNER_CS);
		createEReference(packageOwnerCSEClass, PACKAGE_OWNER_CS__OWNED_PACKAGES);

		parameterCSEClass = createEClass(PARAMETER_CS);
		createEReference(parameterCSEClass, PARAMETER_CS__OWNING_OPERATION);

		pathElementCSEClass = createEClass(PATH_ELEMENT_CS);
		createEReference(pathElementCSEClass, PATH_ELEMENT_CS__ELEMENT_TYPE);
		createEReference(pathElementCSEClass, PATH_ELEMENT_CS__OWNING_PATH_NAME);
		createEReference(pathElementCSEClass, PATH_ELEMENT_CS__REFERRED_ELEMENT);

		pathElementWithURICSEClass = createEClass(PATH_ELEMENT_WITH_URICS);
		createEAttribute(pathElementWithURICSEClass, PATH_ELEMENT_WITH_URICS__URI);

		pathNameCSEClass = createEClass(PATH_NAME_CS);
		createEReference(pathNameCSEClass, PATH_NAME_CS__CONTEXT);
		createEReference(pathNameCSEClass, PATH_NAME_CS__OWNED_PATH_ELEMENTS);
		createEReference(pathNameCSEClass, PATH_NAME_CS__REFERRED_ELEMENT);
		createEAttribute(pathNameCSEClass, PATH_NAME_CS__SCOPE_FILTER);

		pathTypeCSEClass = createEClass(PATH_TYPE_CS);
		createEAttribute(pathTypeCSEClass, PATH_TYPE_CS__IS_TYPEOF);
		createEReference(pathTypeCSEClass, PATH_TYPE_CS__OWNED_BINDING);
		createEReference(pathTypeCSEClass, PATH_TYPE_CS__OWNED_PATH_NAME);
		createEReference(pathTypeCSEClass, PATH_TYPE_CS__REFERRED_TYPE);

		pivotableElementCSEClass = createEClass(PIVOTABLE_ELEMENT_CS);
		createEReference(pivotableElementCSEClass, PIVOTABLE_ELEMENT_CS__PIVOT);

		primitiveTypeRefCSEClass = createEClass(PRIMITIVE_TYPE_REF_CS);
		createEAttribute(primitiveTypeRefCSEClass, PRIMITIVE_TYPE_REF_CS__NAME);

		referenceCSEClass = createEClass(REFERENCE_CS);
		createEReference(referenceCSEClass, REFERENCE_CS__REFERRED_KEYS);
		createEReference(referenceCSEClass, REFERENCE_CS__REFERRED_OPPOSITE);

		rootCSEClass = createEClass(ROOT_CS);
		createEReference(rootCSEClass, ROOT_CS__OWNED_IMPORTS);

		rootPackageCSEClass = createEClass(ROOT_PACKAGE_CS);

		specificationCSEClass = createEClass(SPECIFICATION_CS);
		createEAttribute(specificationCSEClass, SPECIFICATION_CS__EXPR_STRING);

		structuralFeatureCSEClass = createEClass(STRUCTURAL_FEATURE_CS);
		createEAttribute(structuralFeatureCSEClass, STRUCTURAL_FEATURE_CS__DEFAULT);
		createEReference(structuralFeatureCSEClass, STRUCTURAL_FEATURE_CS__OWNED_DEFAULT_EXPRESSIONS);
		createEReference(structuralFeatureCSEClass, STRUCTURAL_FEATURE_CS__OWNED_DERIVED_CONSTRAINTS);
		createEReference(structuralFeatureCSEClass, STRUCTURAL_FEATURE_CS__OWNING_CLASS);

		structuredClassCSEClass = createEClass(STRUCTURED_CLASS_CS);
		createEAttribute(structuredClassCSEClass, STRUCTURED_CLASS_CS__IS_ABSTRACT);
		createEAttribute(structuredClassCSEClass, STRUCTURED_CLASS_CS__IS_INTERFACE);
		createEReference(structuredClassCSEClass, STRUCTURED_CLASS_CS__OWNED_METACLASS);
		createEReference(structuredClassCSEClass, STRUCTURED_CLASS_CS__OWNED_OPERATIONS);
		createEReference(structuredClassCSEClass, STRUCTURED_CLASS_CS__OWNED_PROPERTIES);
		createEReference(structuredClassCSEClass, STRUCTURED_CLASS_CS__OWNED_SUPER_TYPES);

		templateBindingCSEClass = createEClass(TEMPLATE_BINDING_CS);
		createEReference(templateBindingCSEClass, TEMPLATE_BINDING_CS__OWNED_SUBSTITUTIONS);
		createEReference(templateBindingCSEClass, TEMPLATE_BINDING_CS__OWNING_ELEMENT);

		templateParameterCSEClass = createEClass(TEMPLATE_PARAMETER_CS);
		createEReference(templateParameterCSEClass, TEMPLATE_PARAMETER_CS__OWNING_SIGNATURE);

		templateParameterSubstitutionCSEClass = createEClass(TEMPLATE_PARAMETER_SUBSTITUTION_CS);
		createEReference(templateParameterSubstitutionCSEClass, TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNED_ACTUAL_PARAMETER);
		createEReference(templateParameterSubstitutionCSEClass, TEMPLATE_PARAMETER_SUBSTITUTION_CS__OWNING_BINDING);

		templateSignatureCSEClass = createEClass(TEMPLATE_SIGNATURE_CS);
		createEReference(templateSignatureCSEClass, TEMPLATE_SIGNATURE_CS__OWNED_PARAMETERS);
		createEReference(templateSignatureCSEClass, TEMPLATE_SIGNATURE_CS__OWNING_ELEMENT);

		templateableElementCSEClass = createEClass(TEMPLATEABLE_ELEMENT_CS);
		createEReference(templateableElementCSEClass, TEMPLATEABLE_ELEMENT_CS__OWNED_SIGNATURE);

		tuplePartCSEClass = createEClass(TUPLE_PART_CS);

		tupleTypeCSEClass = createEClass(TUPLE_TYPE_CS);
		createEAttribute(tupleTypeCSEClass, TUPLE_TYPE_CS__NAME);
		createEReference(tupleTypeCSEClass, TUPLE_TYPE_CS__OWNED_PARTS);

		typeCSEClass = createEClass(TYPE_CS);

		typeParameterCSEClass = createEClass(TYPE_PARAMETER_CS);
		createEReference(typeParameterCSEClass, TYPE_PARAMETER_CS__OWNED_EXTENDS);

		typeRefCSEClass = createEClass(TYPE_REF_CS);

		typedElementCSEClass = createEClass(TYPED_ELEMENT_CS);
		createEReference(typedElementCSEClass, TYPED_ELEMENT_CS__OWNED_TYPE);
		createEAttribute(typedElementCSEClass, TYPED_ELEMENT_CS__QUALIFIERS);

		typedRefCSEClass = createEClass(TYPED_REF_CS);
		createEReference(typedRefCSEClass, TYPED_REF_CS__OWNED_MULTIPLICITY);

		visitableCSEClass = createEClass(VISITABLE_CS);

		wildcardTypeRefCSEClass = createEClass(WILDCARD_TYPE_REF_CS);
		createEReference(wildcardTypeRefCSEClass, WILDCARD_TYPE_REF_CS__OWNED_EXTENDS);
		createEReference(wildcardTypeRefCSEClass, WILDCARD_TYPE_REF_CS__OWNED_SUPER);

		// Create data types
		bigNumberEDataType = createEDataType(BIG_NUMBER);
		csiEDataType = createEDataType(CSI);
		scopeFilterEDataType = createEDataType(SCOPE_FILTER);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PivotPackage thePivotPackage = (PivotPackage)EPackage.Registry.INSTANCE.getEPackage(PivotPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		annotationCSEClass.getESuperTypes().add(this.getAnnotationElementCS());
		annotationElementCSEClass.getESuperTypes().add(this.getNamedElementCS());
		attributeCSEClass.getESuperTypes().add(this.getStructuralFeatureCS());
		classCSEClass.getESuperTypes().add(this.getNamedElementCS());
		classCSEClass.getESuperTypes().add(this.getTypeCS());
		classCSEClass.getESuperTypes().add(this.getTemplateableElementCS());
		constraintCSEClass.getESuperTypes().add(this.getNamedElementCS());
		contextLessElementCSEClass.getESuperTypes().add(this.getElementCS());
		dataTypeCSEClass.getESuperTypes().add(this.getClassCS());
		dataTypeCSEClass.getESuperTypes().add(this.getNamespaceCS());
		detailCSEClass.getESuperTypes().add(this.getNamedElementCS());
		documentationCSEClass.getESuperTypes().add(this.getAnnotationElementCS());
		elementCSEClass.getESuperTypes().add(this.getVisitableCS());
		elementRefCSEClass.getESuperTypes().add(this.getPivotableElementCS());
		enumerationCSEClass.getESuperTypes().add(this.getClassCS());
		enumerationCSEClass.getESuperTypes().add(this.getNamespaceCS());
		enumerationLiteralCSEClass.getESuperTypes().add(this.getNamedElementCS());
		featureCSEClass.getESuperTypes().add(this.getTypedElementCS());
		importCSEClass.getESuperTypes().add(this.getNamespaceCS());
		lambdaTypeCSEClass.getESuperTypes().add(this.getTypedRefCS());
		lambdaTypeCSEClass.getESuperTypes().add(this.getTemplateableElementCS());
		lambdaTypeCSEClass.getESuperTypes().add(thePivotPackage.getNameable());
		modelElementCSEClass.getESuperTypes().add(this.getPivotableElementCS());
		modelElementRefCSEClass.getESuperTypes().add(this.getElementRefCS());
		multiplicityBoundsCSEClass.getESuperTypes().add(this.getMultiplicityCS());
		multiplicityCSEClass.getESuperTypes().add(this.getElementCS());
		multiplicityStringCSEClass.getESuperTypes().add(this.getMultiplicityCS());
		namedElementCSEClass.getESuperTypes().add(this.getModelElementCS());
		namedElementCSEClass.getESuperTypes().add(thePivotPackage.getNameable());
		namespaceCSEClass.getESuperTypes().add(this.getNamedElementCS());
		operationCSEClass.getESuperTypes().add(this.getFeatureCS());
		operationCSEClass.getESuperTypes().add(this.getTemplateableElementCS());
		packageCSEClass.getESuperTypes().add(this.getPackageOwnerCS());
		packageCSEClass.getESuperTypes().add(this.getNamespaceCS());
		packageOwnerCSEClass.getESuperTypes().add(this.getModelElementCS());
		parameterCSEClass.getESuperTypes().add(this.getTypedElementCS());
		pathElementCSEClass.getESuperTypes().add(this.getElementCS());
		pathElementCSEClass.getESuperTypes().add(thePivotPackage.getPivotable());
		pathElementWithURICSEClass.getESuperTypes().add(this.getPathElementCS());
		pathNameCSEClass.getESuperTypes().add(this.getElementCS());
		pathNameCSEClass.getESuperTypes().add(thePivotPackage.getPivotable());
		pathTypeCSEClass.getESuperTypes().add(this.getTypedRefCS());
		pivotableElementCSEClass.getESuperTypes().add(this.getElementCS());
		pivotableElementCSEClass.getESuperTypes().add(thePivotPackage.getPivotable());
		primitiveTypeRefCSEClass.getESuperTypes().add(this.getTypedRefCS());
		primitiveTypeRefCSEClass.getESuperTypes().add(thePivotPackage.getNameable());
		referenceCSEClass.getESuperTypes().add(this.getStructuralFeatureCS());
		rootCSEClass.getESuperTypes().add(this.getModelElementCS());
		rootPackageCSEClass.getESuperTypes().add(this.getPackageOwnerCS());
		rootPackageCSEClass.getESuperTypes().add(this.getRootCS());
		specificationCSEClass.getESuperTypes().add(this.getModelElementCS());
		structuralFeatureCSEClass.getESuperTypes().add(this.getFeatureCS());
		structuredClassCSEClass.getESuperTypes().add(this.getClassCS());
		structuredClassCSEClass.getESuperTypes().add(this.getNamespaceCS());
		templateBindingCSEClass.getESuperTypes().add(this.getElementRefCS());
		templateParameterCSEClass.getESuperTypes().add(this.getNamedElementCS());
		templateParameterSubstitutionCSEClass.getESuperTypes().add(this.getModelElementCS());
		templateSignatureCSEClass.getESuperTypes().add(this.getModelElementCS());
		templateableElementCSEClass.getESuperTypes().add(this.getElementCS());
		tuplePartCSEClass.getESuperTypes().add(this.getTypedElementCS());
		tupleTypeCSEClass.getESuperTypes().add(this.getTypedRefCS());
		tupleTypeCSEClass.getESuperTypes().add(thePivotPackage.getNameable());
		typeCSEClass.getESuperTypes().add(this.getModelElementCS());
		typeParameterCSEClass.getESuperTypes().add(this.getTemplateParameterCS());
		typeParameterCSEClass.getESuperTypes().add(this.getTypeCS());
		typeRefCSEClass.getESuperTypes().add(this.getElementRefCS());
		typedElementCSEClass.getESuperTypes().add(this.getNamedElementCS());
		typedRefCSEClass.getESuperTypes().add(this.getTypeRefCS());
		wildcardTypeRefCSEClass.getESuperTypes().add(this.getTypeRefCS());

		// Initialize classes and features; add operations and parameters
		initEClass(annotationCSEClass, AnnotationCS.class, "AnnotationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAnnotationCS_OwnedContents(), this.getModelElementCS(), null, "ownedContents", null, 0, -1, AnnotationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getAnnotationCS_OwnedReferences(), this.getModelElementRefCS(), null, "ownedReferences", null, 0, -1, AnnotationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(annotationElementCSEClass, AnnotationElementCS.class, "AnnotationElementCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAnnotationElementCS_OwnedDetails(), this.getDetailCS(), null, "ownedDetails", null, 0, -1, AnnotationElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(attributeCSEClass, AttributeCS.class, "AttributeCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(classCSEClass, ClassCS.class, "ClassCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getClassCS_InstanceClassName(), ecorePackage.getEString(), "instanceClassName", null, 0, 1, ClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getClassCS_OwnedConstraints(), this.getConstraintCS(), null, "ownedConstraints", null, 0, -1, ClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getClassCS_OwningPackage(), this.getPackageCS(), this.getPackageCS_OwnedClasses(), "owningPackage", null, 0, 1, ClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(constraintCSEClass, ConstraintCS.class, "ConstraintCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getConstraintCS_OwnedMessageSpecification(), this.getSpecificationCS(), null, "ownedMessageSpecification", null, 0, 1, ConstraintCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getConstraintCS_OwnedSpecification(), this.getSpecificationCS(), null, "ownedSpecification", null, 0, 1, ConstraintCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getConstraintCS_Stereotype(), ecorePackage.getEString(), "stereotype", null, 0, 1, ConstraintCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(contextLessElementCSEClass, ContextLessElementCS.class, "ContextLessElementCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(dataTypeCSEClass, DataTypeCS.class, "DataTypeCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDataTypeCS_IsPrimitive(), ecorePackage.getEBoolean(), "isPrimitive", "false", 0, 1, DataTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getDataTypeCS_IsSerializable(), ecorePackage.getEBoolean(), "isSerializable", "false", 0, 1, DataTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(detailCSEClass, DetailCS.class, "DetailCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDetailCS_Values(), ecorePackage.getEString(), "values", null, 0, -1, DetailCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(documentationCSEClass, DocumentationCS.class, "DocumentationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getDocumentationCS_Value(), ecorePackage.getEString(), "value", null, 0, 1, DocumentationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(elementCSEClass, ElementCS.class, "ElementCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getElementCS_Csi(), this.getCSI(), "csi", null, 0, 1, ElementCS.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getElementCS_Parent(), this.getElementCS(), null, "parent", null, 0, 1, ElementCS.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(elementCSEClass, ecorePackage.getEString(), "getDescription", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(elementRefCSEClass, ElementRefCS.class, "ElementRefCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(enumerationCSEClass, EnumerationCS.class, "EnumerationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getEnumerationCS_IsSerializable(), ecorePackage.getEBoolean(), "isSerializable", "false", 0, 1, EnumerationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getEnumerationCS_OwnedLiterals(), this.getEnumerationLiteralCS(), null, "ownedLiterals", null, 0, -1, EnumerationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(enumerationLiteralCSEClass, EnumerationLiteralCS.class, "EnumerationLiteralCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getEnumerationLiteralCS_Value(), ecorePackage.getEInt(), "value", null, 0, 1, EnumerationLiteralCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(featureCSEClass, FeatureCS.class, "FeatureCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(importCSEClass, ImportCS.class, "ImportCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getImportCS_IsAll(), ecorePackage.getEBoolean(), "isAll", "false", 0, 1, ImportCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getImportCS_OwnedPathName(), this.getPathNameCS(), null, "ownedPathName", null, 0, 1, ImportCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getImportCS_ReferredNamespace(), thePivotPackage.getNamespace(), null, "referredNamespace", null, 0, 1, ImportCS.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		EOperation op = addEOperation(importCSEClass, ecorePackage.getEBoolean(), "validateImportedElementsAreValid", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEDiagnosticChain(), "diagnostics", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEJavaObject());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "context", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(lambdaTypeCSEClass, LambdaTypeCS.class, "LambdaTypeCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLambdaTypeCS_Name(), ecorePackage.getEString(), "name", null, 0, 1, LambdaTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLambdaTypeCS_OwnedParameterTypes(), this.getTypedRefCS(), null, "ownedParameterTypes", null, 0, -1, LambdaTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getLambdaTypeCS_OwnedResultType(), this.getTypedRefCS(), null, "ownedResultType", null, 0, 1, LambdaTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(modelElementCSEClass, ModelElementCS.class, "ModelElementCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getModelElementCS_OriginalXmiId(), ecorePackage.getEString(), "originalXmiId", null, 0, 1, ModelElementCS.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getModelElementCS_OwnedAnnotations(), this.getAnnotationElementCS(), null, "ownedAnnotations", null, 0, -1, ModelElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(modelElementRefCSEClass, ModelElementRefCS.class, "ModelElementRefCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getModelElementRefCS_OwnedPathName(), this.getPathNameCS(), null, "ownedPathName", null, 0, 1, ModelElementRefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getModelElementRefCS_ReferredElement(), thePivotPackage.getElement(), null, "referredElement", null, 0, 1, ModelElementRefCS.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(multiplicityBoundsCSEClass, MultiplicityBoundsCS.class, "MultiplicityBoundsCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getMultiplicityBoundsCS_LowerBound(), ecorePackage.getEInt(), "lowerBound", "1", 0, 1, MultiplicityBoundsCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getMultiplicityBoundsCS_UpperBound(), ecorePackage.getEIntegerObject(), "upperBound", null, 0, 1, MultiplicityBoundsCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(multiplicityCSEClass, MultiplicityCS.class, "MultiplicityCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getMultiplicityCS_IsNullFree(), ecorePackage.getEBoolean(), "isNullFree", "false", 0, 1, MultiplicityCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		addEOperation(multiplicityCSEClass, ecorePackage.getEInt(), "getLower", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(multiplicityCSEClass, ecorePackage.getEInt(), "getUpper", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(multiplicityStringCSEClass, MultiplicityStringCS.class, "MultiplicityStringCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getMultiplicityStringCS_StringBounds(), ecorePackage.getEString(), "stringBounds", "1", 0, 1, MultiplicityStringCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

		initEClass(namedElementCSEClass, NamedElementCS.class, "NamedElementCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getNamedElementCS_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(namespaceCSEClass, NamespaceCS.class, "NamespaceCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(operationCSEClass, OperationCS.class, "OperationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getOperationCS_OwnedBodyExpressions(), this.getSpecificationCS(), null, "ownedBodyExpressions", null, 0, -1, OperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperationCS_OwnedExceptions(), this.getTypedRefCS(), null, "ownedExceptions", null, 0, -1, OperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperationCS_OwnedParameters(), this.getParameterCS(), this.getParameterCS_OwningOperation(), "ownedParameters", null, 0, -1, OperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperationCS_OwnedPostconditions(), this.getConstraintCS(), null, "ownedPostconditions", null, 0, -1, OperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperationCS_OwnedPreconditions(), this.getConstraintCS(), null, "ownedPreconditions", null, 0, -1, OperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getOperationCS_OwningClass(), this.getStructuredClassCS(), this.getStructuredClassCS_OwnedOperations(), "owningClass", null, 0, 1, OperationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(packageCSEClass, PackageCS.class, "PackageCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPackageCS_NsPrefix(), ecorePackage.getEString(), "nsPrefix", null, 0, 1, PackageCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPackageCS_NsURI(), ecorePackage.getEString(), "nsURI", null, 0, 1, PackageCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPackageCS_OwnedClasses(), this.getClassCS(), this.getClassCS_OwningPackage(), "ownedClasses", null, 0, -1, PackageCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(packageOwnerCSEClass, PackageOwnerCS.class, "PackageOwnerCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPackageOwnerCS_OwnedPackages(), this.getPackageCS(), null, "ownedPackages", null, 0, -1, PackageOwnerCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(parameterCSEClass, ParameterCS.class, "ParameterCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getParameterCS_OwningOperation(), this.getOperationCS(), this.getOperationCS_OwnedParameters(), "owningOperation", null, 0, 1, ParameterCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(pathElementCSEClass, PathElementCS.class, "PathElementCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPathElementCS_ElementType(), ecorePackage.getEClassifier(), null, "elementType", null, 0, 1, PathElementCS.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPathElementCS_OwningPathName(), this.getPathNameCS(), this.getPathNameCS_OwnedPathElements(), "owningPathName", null, 1, 1, PathElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPathElementCS_ReferredElement(), thePivotPackage.getElement(), null, "referredElement", null, 1, 1, PathElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(pathElementWithURICSEClass, PathElementWithURICS.class, "PathElementWithURICS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPathElementWithURICS_Uri(), ecorePackage.getEString(), "uri", null, 0, 1, PathElementWithURICS.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(pathNameCSEClass, PathNameCS.class, "PathNameCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPathNameCS_Context(), this.getElementCS(), null, "context", null, 0, 1, PathNameCS.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPathNameCS_OwnedPathElements(), this.getPathElementCS(), this.getPathElementCS_OwningPathName(), "ownedPathElements", null, 1, -1, PathNameCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPathNameCS_ReferredElement(), thePivotPackage.getElement(), null, "referredElement", null, 1, 1, PathNameCS.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getPathNameCS_ScopeFilter(), this.getScopeFilter(), "scopeFilter", null, 0, 1, PathNameCS.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(pathTypeCSEClass, PathTypeCS.class, "PathTypeCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPathTypeCS_IsTypeof(), ecorePackage.getEBoolean(), "isTypeof", "false", 1, 1, PathTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getPathTypeCS_OwnedBinding(), this.getTemplateBindingCS(), this.getTemplateBindingCS_OwningElement(), "ownedBinding", null, 0, 1, PathTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPathTypeCS_OwnedPathName(), this.getPathNameCS(), null, "ownedPathName", null, 0, 1, PathTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getPathTypeCS_ReferredType(), thePivotPackage.getType(), null, "referredType", null, 0, 1, PathTypeCS.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(pivotableElementCSEClass, PivotableElementCS.class, "PivotableElementCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getPivotableElementCS_Pivot(), thePivotPackage.getElement(), null, "pivot", null, 0, 1, PivotableElementCS.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(primitiveTypeRefCSEClass, PrimitiveTypeRefCS.class, "PrimitiveTypeRefCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getPrimitiveTypeRefCS_Name(), ecorePackage.getEString(), "name", null, 0, 1, PrimitiveTypeRefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(referenceCSEClass, ReferenceCS.class, "ReferenceCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getReferenceCS_ReferredKeys(), thePivotPackage.getProperty(), null, "referredKeys", null, 0, -1, ReferenceCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getReferenceCS_ReferredOpposite(), thePivotPackage.getProperty(), null, "referredOpposite", null, 0, 1, ReferenceCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(rootCSEClass, RootCS.class, "RootCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getRootCS_OwnedImports(), this.getImportCS(), null, "ownedImports", null, 0, -1, RootCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(rootPackageCSEClass, RootPackageCS.class, "RootPackageCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(specificationCSEClass, SpecificationCS.class, "SpecificationCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSpecificationCS_ExprString(), ecorePackage.getEString(), "exprString", null, 0, 1, SpecificationCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(structuralFeatureCSEClass, StructuralFeatureCS.class, "StructuralFeatureCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getStructuralFeatureCS_Default(), ecorePackage.getEString(), "default", null, 0, 1, StructuralFeatureCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuralFeatureCS_OwnedDefaultExpressions(), this.getSpecificationCS(), null, "ownedDefaultExpressions", null, 0, -1, StructuralFeatureCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuralFeatureCS_OwnedDerivedConstraints(), this.getConstraintCS(), null, "ownedDerivedConstraints", null, 0, -1, StructuralFeatureCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuralFeatureCS_OwningClass(), this.getStructuredClassCS(), this.getStructuredClassCS_OwnedProperties(), "owningClass", null, 0, 1, StructuralFeatureCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(structuredClassCSEClass, StructuredClassCS.class, "StructuredClassCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getStructuredClassCS_IsAbstract(), ecorePackage.getEBoolean(), "isAbstract", "false", 0, 1, StructuredClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getStructuredClassCS_IsInterface(), ecorePackage.getEBoolean(), "isInterface", "false", 0, 1, StructuredClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEReference(getStructuredClassCS_OwnedMetaclass(), this.getTypedRefCS(), null, "ownedMetaclass", null, 0, 1, StructuredClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuredClassCS_OwnedOperations(), this.getOperationCS(), this.getOperationCS_OwningClass(), "ownedOperations", null, 0, -1, StructuredClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuredClassCS_OwnedProperties(), this.getStructuralFeatureCS(), this.getStructuralFeatureCS_OwningClass(), "ownedProperties", null, 0, -1, StructuredClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getStructuredClassCS_OwnedSuperTypes(), this.getTypedRefCS(), null, "ownedSuperTypes", null, 0, -1, StructuredClassCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateBindingCSEClass, TemplateBindingCS.class, "TemplateBindingCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateBindingCS_OwnedSubstitutions(), this.getTemplateParameterSubstitutionCS(), this.getTemplateParameterSubstitutionCS_OwningBinding(), "ownedSubstitutions", null, 0, -1, TemplateBindingCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateBindingCS_OwningElement(), this.getPathTypeCS(), this.getPathTypeCS_OwnedBinding(), "owningElement", null, 0, 1, TemplateBindingCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateParameterCSEClass, TemplateParameterCS.class, "TemplateParameterCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateParameterCS_OwningSignature(), this.getTemplateSignatureCS(), this.getTemplateSignatureCS_OwnedParameters(), "owningSignature", null, 1, 1, TemplateParameterCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateParameterSubstitutionCSEClass, TemplateParameterSubstitutionCS.class, "TemplateParameterSubstitutionCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateParameterSubstitutionCS_OwnedActualParameter(), this.getTypeRefCS(), null, "ownedActualParameter", null, 0, 1, TemplateParameterSubstitutionCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateParameterSubstitutionCS_OwningBinding(), this.getTemplateBindingCS(), this.getTemplateBindingCS_OwnedSubstitutions(), "owningBinding", null, 0, 1, TemplateParameterSubstitutionCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateSignatureCSEClass, TemplateSignatureCS.class, "TemplateSignatureCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateSignatureCS_OwnedParameters(), this.getTemplateParameterCS(), this.getTemplateParameterCS_OwningSignature(), "ownedParameters", null, 0, -1, TemplateSignatureCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTemplateSignatureCS_OwningElement(), this.getTemplateableElementCS(), this.getTemplateableElementCS_OwnedSignature(), "owningElement", null, 0, 1, TemplateSignatureCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(templateableElementCSEClass, TemplateableElementCS.class, "TemplateableElementCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTemplateableElementCS_OwnedSignature(), this.getTemplateSignatureCS(), this.getTemplateSignatureCS_OwningElement(), "ownedSignature", null, 0, 1, TemplateableElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(tuplePartCSEClass, TuplePartCS.class, "TuplePartCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(tupleTypeCSEClass, TupleTypeCS.class, "TupleTypeCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getTupleTypeCS_Name(), ecorePackage.getEString(), "name", null, 0, 1, TupleTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getTupleTypeCS_OwnedParts(), this.getTuplePartCS(), null, "ownedParts", null, 0, -1, TupleTypeCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(typeCSEClass, TypeCS.class, "TypeCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(typeParameterCSEClass, TypeParameterCS.class, "TypeParameterCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTypeParameterCS_OwnedExtends(), this.getTypedRefCS(), null, "ownedExtends", null, 0, -1, TypeParameterCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(typeRefCSEClass, TypeRefCS.class, "TypeRefCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(typedElementCSEClass, TypedElementCS.class, "TypedElementCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTypedElementCS_OwnedType(), this.getTypedRefCS(), null, "ownedType", null, 0, 1, TypedElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getTypedElementCS_Qualifiers(), ecorePackage.getEString(), "qualifiers", null, 0, -1, TypedElementCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(typedRefCSEClass, TypedRefCS.class, "TypedRefCS", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getTypedRefCS_OwnedMultiplicity(), this.getMultiplicityCS(), null, "ownedMultiplicity", null, 0, 1, TypedRefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(visitableCSEClass, VisitableCS.class, "VisitableCS", IS_ABSTRACT, IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(wildcardTypeRefCSEClass, WildcardTypeRefCS.class, "WildcardTypeRefCS", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getWildcardTypeRefCS_OwnedExtends(), this.getTypedRefCS(), null, "ownedExtends", null, 0, 1, WildcardTypeRefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getWildcardTypeRefCS_OwnedSuper(), this.getTypedRefCS(), null, "ownedSuper", null, 0, 1, WildcardTypeRefCS.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize data types
		initEDataType(bigNumberEDataType, Number.class, "BigNumber", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(csiEDataType, org.eclipse.ocl.xtext.base.utilities.CSI.class, "CSI", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(scopeFilterEDataType, ScopeFilter.class, "ScopeFilter", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/emf/2002/Ecore
		createEcoreAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/emf/2002/Ecore</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createEcoreAnnotations()
	{
		String source = "http://www.eclipse.org/emf/2002/Ecore"; //$NON-NLS-1$	
		addAnnotation
		  (this, 
		   source, 
		   new String[] 
		   {
		   });
	}

} //BaseCSPackageImpl
