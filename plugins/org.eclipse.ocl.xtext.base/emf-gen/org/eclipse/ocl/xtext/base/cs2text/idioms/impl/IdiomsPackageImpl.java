/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.xtext.base.cs2text.idioms.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.ocl.xtext.base.cs2text.SerializationBuilder;
import org.eclipse.ocl.xtext.base.cs2text.elements.SerializationNode;
import org.eclipse.ocl.xtext.base.cs2text.idioms.AssignmentLocator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.CommentSegmentSupport;
import org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.CustomSegmentSupport;
import org.eclipse.ocl.xtext.base.cs2text.idioms.DefaultLocator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.HalfNewLineSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Idiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomMatch;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomModel;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsFactory;
import org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage;
import org.eclipse.ocl.xtext.base.cs2text.idioms.KeywordLocator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Locator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.NewLineSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.NoSpaceSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.PopSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.ProducedEClassLocator;
import org.eclipse.ocl.xtext.base.cs2text.idioms.PushSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.Segment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SoftNewLineSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SoftSpaceSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.StringSegment;
import org.eclipse.ocl.xtext.base.cs2text.idioms.SubIdiom;
import org.eclipse.ocl.xtext.base.cs2text.idioms.ValueSegment;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationRuleAnalysis;
import org.eclipse.ocl.xtext.base.cs2text.runtime.SerializationStep;
import org.eclipse.ocl.xtext.base.cs2text.user.UserElementSerializer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class IdiomsPackageImpl extends EPackageImpl implements IdiomsPackage
{
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass assignmentLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass commentSegmentSupportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customSegmentSupportEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass defaultLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass halfNewLineSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idiomEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass idiomModelEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass locatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass keywordLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass newLineSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass noSpaceSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass popSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass producedEClassLocatorEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pushSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softNewLineSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass softSpaceSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subIdiomEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass valueSegmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType serializationRuleAnalysisEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType idiomMatchEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType serializationBuilderEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType serializationNodeEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType userElementSerializerEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType rtSerializationStepEDataType = null;

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
	 * @see org.eclipse.ocl.xtext.base.cs2text.idioms.IdiomsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private IdiomsPackageImpl()
	{
		super(eNS_URI, IdiomsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link IdiomsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static IdiomsPackage init()
	{
		if (isInited) return (IdiomsPackage)EPackage.Registry.INSTANCE.getEPackage(IdiomsPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredIdiomsPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		IdiomsPackageImpl theIdiomsPackage = registeredIdiomsPackage instanceof IdiomsPackageImpl ? (IdiomsPackageImpl)registeredIdiomsPackage : new IdiomsPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theIdiomsPackage.createPackageContents();

		// Initialize created meta-data
		theIdiomsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theIdiomsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(IdiomsPackage.eNS_URI, theIdiomsPackage);
		return theIdiomsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getAssignmentLocator()
	{
		return assignmentLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getAssignmentLocator_EStructuralFeature()
	{
		return (EReference)assignmentLocatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCommentSegmentSupport()
	{
		return commentSegmentSupportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCommentSegmentSupport_Epilogue()
	{
		return (EAttribute)commentSegmentSupportEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCommentSegmentSupport_Indentation()
	{
		return (EAttribute)commentSegmentSupportEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCommentSegmentSupport_Prologue()
	{
		return (EAttribute)commentSegmentSupportEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomSegment()
	{
		return customSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomSegment_SupportClass()
	{
		return (EAttribute)customSegmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getCustomSegment_SupportClassName()
	{
		return (EAttribute)customSegmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getCustomSegmentSupport()
	{
		return customSegmentSupportEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getDefaultLocator()
	{
		return defaultLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getHalfNewLineSegment()
	{
		return halfNewLineSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIdiom()
	{
		return idiomEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIdiom_Name()
	{
		return (EAttribute)idiomEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiom_OwnedSubIdioms()
	{
		return (EReference)idiomEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getIdiomModel()
	{
		return idiomModelEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomModel_Imports()
	{
		return (EReference)idiomModelEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getIdiomModel_Name()
	{
		return (EAttribute)idiomModelEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomModel_OwnedIdioms()
	{
		return (EReference)idiomModelEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomModel_OwnedLocators()
	{
		return (EReference)idiomModelEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getIdiomModel_OwnedSegments()
	{
		return (EReference)idiomModelEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getLocator()
	{
		return locatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getLocator_Name()
	{
		return (EAttribute)locatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getKeywordLocator()
	{
		return keywordLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKeywordLocator_String()
	{
		return (EAttribute)keywordLocatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getKeywordLocator_InEClass()
	{
		return (EReference)keywordLocatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNewLineSegment()
	{
		return newLineSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getNoSpaceSegment()
	{
		return noSpaceSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPopSegment()
	{
		return popSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getProducedEClassLocator()
	{
		return producedEClassLocatorEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getProducedEClassLocator_EClass()
	{
		return (EReference)producedEClassLocatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getPushSegment()
	{
		return pushSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSegment()
	{
		return segmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSegment_Name()
	{
		return (EAttribute)segmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSoftNewLineSegment()
	{
		return softNewLineSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSoftSpaceSegment()
	{
		return softSpaceSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getStringSegment()
	{
		return stringSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringSegment_Printable()
	{
		return (EAttribute)stringSegmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getStringSegment_String()
	{
		return (EAttribute)stringSegmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSubIdiom()
	{
		return subIdiomEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSubIdiom_Locator()
	{
		return (EReference)subIdiomEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSubIdiom_Name()
	{
		return (EAttribute)subIdiomEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSubIdiom_Segments()
	{
		return (EReference)subIdiomEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getValueSegment()
	{
		return valueSegmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getSerializationRuleAnalysis()
	{
		return serializationRuleAnalysisEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getIdiomMatch()
	{
		return idiomMatchEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getSerializationBuilder()
	{
		return serializationBuilderEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getSerializationNode()
	{
		return serializationNodeEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getUserElementSerializer()
	{
		return userElementSerializerEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EDataType getRTSerializationStep()
	{
		return rtSerializationStepEDataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public IdiomsFactory getIdiomsFactory()
	{
		return (IdiomsFactory)getEFactoryInstance();
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
	public void createPackageContents()
	{
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		assignmentLocatorEClass = createEClass(0);
		createEReference(assignmentLocatorEClass, 1);

		commentSegmentSupportEClass = createEClass(1);
		createEAttribute(commentSegmentSupportEClass, 0);
		createEAttribute(commentSegmentSupportEClass, 1);
		createEAttribute(commentSegmentSupportEClass, 2);

		customSegmentEClass = createEClass(2);
		createEAttribute(customSegmentEClass, 1);
		createEAttribute(customSegmentEClass, 2);

		customSegmentSupportEClass = createEClass(3);

		defaultLocatorEClass = createEClass(4);

		halfNewLineSegmentEClass = createEClass(5);

		idiomEClass = createEClass(6);
		createEAttribute(idiomEClass, 0);
		createEReference(idiomEClass, 1);

		idiomModelEClass = createEClass(7);
		createEReference(idiomModelEClass, 0);
		createEAttribute(idiomModelEClass, 1);
		createEReference(idiomModelEClass, 2);
		createEReference(idiomModelEClass, 3);
		createEReference(idiomModelEClass, 4);

		locatorEClass = createEClass(8);
		createEAttribute(locatorEClass, 0);

		keywordLocatorEClass = createEClass(9);
		createEAttribute(keywordLocatorEClass, 1);
		createEReference(keywordLocatorEClass, 2);

		newLineSegmentEClass = createEClass(10);

		noSpaceSegmentEClass = createEClass(11);

		popSegmentEClass = createEClass(12);

		producedEClassLocatorEClass = createEClass(13);
		createEReference(producedEClassLocatorEClass, 1);

		pushSegmentEClass = createEClass(14);

		segmentEClass = createEClass(15);
		createEAttribute(segmentEClass, 0);

		softNewLineSegmentEClass = createEClass(16);

		softSpaceSegmentEClass = createEClass(17);

		stringSegmentEClass = createEClass(18);
		createEAttribute(stringSegmentEClass, 1);
		createEAttribute(stringSegmentEClass, 2);

		subIdiomEClass = createEClass(19);
		createEReference(subIdiomEClass, 0);
		createEAttribute(subIdiomEClass, 1);
		createEReference(subIdiomEClass, 2);

		valueSegmentEClass = createEClass(20);

		// Create data types
		serializationRuleAnalysisEDataType = createEDataType(21);
		idiomMatchEDataType = createEDataType(22);
		serializationBuilderEDataType = createEDataType(23);
		serializationNodeEDataType = createEDataType(24);
		userElementSerializerEDataType = createEDataType(25);
		rtSerializationStepEDataType = createEDataType(26);
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
	public void initializePackageContents()
	{
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		assignmentLocatorEClass.getESuperTypes().add(this.getLocator());
		commentSegmentSupportEClass.getESuperTypes().add(this.getCustomSegmentSupport());
		customSegmentEClass.getESuperTypes().add(this.getSegment());
		defaultLocatorEClass.getESuperTypes().add(this.getLocator());
		halfNewLineSegmentEClass.getESuperTypes().add(this.getSegment());
		keywordLocatorEClass.getESuperTypes().add(this.getLocator());
		newLineSegmentEClass.getESuperTypes().add(this.getSegment());
		noSpaceSegmentEClass.getESuperTypes().add(this.getSegment());
		popSegmentEClass.getESuperTypes().add(this.getSegment());
		producedEClassLocatorEClass.getESuperTypes().add(this.getLocator());
		pushSegmentEClass.getESuperTypes().add(this.getSegment());
		softNewLineSegmentEClass.getESuperTypes().add(this.getSegment());
		softSpaceSegmentEClass.getESuperTypes().add(this.getSegment());
		stringSegmentEClass.getESuperTypes().add(this.getSegment());
		valueSegmentEClass.getESuperTypes().add(this.getSegment());

		// Initialize classes and features; add operations and parameters
		initEClass(assignmentLocatorEClass, AssignmentLocator.class, "AssignmentLocator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getAssignmentLocator_EStructuralFeature(), ecorePackage.getEStructuralFeature(), null, "eStructuralFeature", null, 1, 1, AssignmentLocator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(commentSegmentSupportEClass, CommentSegmentSupport.class, "CommentSegmentSupport", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getCommentSegmentSupport_Epilogue(), ecorePackage.getEString(), "epilogue", null, 1, 1, CommentSegmentSupport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCommentSegmentSupport_Indentation(), ecorePackage.getEString(), "indentation", null, 1, 1, CommentSegmentSupport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCommentSegmentSupport_Prologue(), ecorePackage.getEString(), "prologue", null, 1, 1, CommentSegmentSupport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		EOperation op = addEOperation(commentSegmentSupportEClass, ecorePackage.getEString(), "getComment", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, ecorePackage.getEObject(), "eObject", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(customSegmentEClass, CustomSegment.class, "CustomSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		EGenericType g1 = createEGenericType(ecorePackage.getEJavaClass());
		EGenericType g2 = createEGenericType();
		g1.getETypeArguments().add(g2);
		initEAttribute(getCustomSegment_SupportClass(), g1, "supportClass", null, 0, 1, CustomSegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getCustomSegment_SupportClassName(), ecorePackage.getEString(), "supportClassName", null, 0, 1, CustomSegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(customSegmentSupportEClass, CustomSegmentSupport.class, "CustomSegmentSupport", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		op = addEOperation(customSegmentSupportEClass, null, "serialize", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getRTSerializationStep(), "serializationStep", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getUserElementSerializer(), "serializer", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getSerializationBuilder(), "serializationBuilder", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(defaultLocatorEClass, DefaultLocator.class, "DefaultLocator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(halfNewLineSegmentEClass, HalfNewLineSegment.class, "HalfNewLineSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(idiomEClass, Idiom.class, "Idiom", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getIdiom_Name(), ecorePackage.getEString(), "name", null, 1, 1, Idiom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getIdiom_OwnedSubIdioms(), this.getSubIdiom(), null, "ownedSubIdioms", null, 0, -1, Idiom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(idiomEClass, this.getIdiomMatch(), "firstMatch", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getSerializationNode(), "serializationNode", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getSerializationRuleAnalysis(), "serializationRule", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(idiomModelEClass, IdiomModel.class, "IdiomModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getIdiomModel_Imports(), this.getIdiomModel(), null, "imports", null, 0, -1, IdiomModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getIdiomModel_Name(), ecorePackage.getEString(), "name", null, 1, 1, IdiomModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getIdiomModel_OwnedIdioms(), this.getIdiom(), null, "ownedIdioms", null, 0, -1, IdiomModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getIdiomModel_OwnedLocators(), this.getLocator(), null, "ownedLocators", null, 0, -1, IdiomModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getIdiomModel_OwnedSegments(), this.getSegment(), null, "ownedSegments", null, 0, -1, IdiomModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(locatorEClass, Locator.class, "Locator", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getLocator_Name(), ecorePackage.getEString(), "name", null, 1, 1, Locator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(locatorEClass, ecorePackage.getEBoolean(), "matches", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getSerializationNode(), "serializationNode", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getSerializationRuleAnalysis(), "serializationRule", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(keywordLocatorEClass, KeywordLocator.class, "KeywordLocator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getKeywordLocator_String(), ecorePackage.getEString(), "string", null, 1, 1, KeywordLocator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getKeywordLocator_InEClass(), ecorePackage.getEClass(), null, "inEClass", null, 0, 1, KeywordLocator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(newLineSegmentEClass, NewLineSegment.class, "NewLineSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(noSpaceSegmentEClass, NoSpaceSegment.class, "NoSpaceSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(popSegmentEClass, PopSegment.class, "PopSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(producedEClassLocatorEClass, ProducedEClassLocator.class, "ProducedEClassLocator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getProducedEClassLocator_EClass(), ecorePackage.getEClass(), null, "eClass", null, 1, 1, ProducedEClassLocator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(pushSegmentEClass, PushSegment.class, "PushSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(segmentEClass, Segment.class, "Segment", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getSegment_Name(), ecorePackage.getEString(), "name", null, 1, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(segmentEClass, null, "serialize", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getRTSerializationStep(), "serializationStep", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getUserElementSerializer(), "serializer", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getSerializationBuilder(), "serializationBuilder", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(softNewLineSegmentEClass, SoftNewLineSegment.class, "SoftNewLineSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(softSpaceSegmentEClass, SoftSpaceSegment.class, "SoftSpaceSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(stringSegmentEClass, StringSegment.class, "StringSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(getStringSegment_Printable(), ecorePackage.getEBoolean(), "printable", "true", 1, 1, StringSegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
		initEAttribute(getStringSegment_String(), ecorePackage.getEString(), "string", null, 1, 1, StringSegment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(subIdiomEClass, SubIdiom.class, "SubIdiom", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(getSubIdiom_Locator(), this.getLocator(), null, "locator", null, 0, 1, SubIdiom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(getSubIdiom_Name(), ecorePackage.getEString(), "name", null, 1, 1, SubIdiom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(getSubIdiom_Segments(), this.getSegment(), null, "segments", null, 0, -1, SubIdiom.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(subIdiomEClass, ecorePackage.getEBoolean(), "matches", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getSerializationNode(), "serializationNode", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, this.getSerializationRuleAnalysis(), "serializationRule", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(valueSegmentEClass, ValueSegment.class, "ValueSegment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Initialize data types
		initEDataType(serializationRuleAnalysisEDataType, SerializationRuleAnalysis.class, "SerializationRuleAnalysis", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(idiomMatchEDataType, IdiomMatch.class, "IdiomMatch", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(serializationBuilderEDataType, SerializationBuilder.class, "SerializationBuilder", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(serializationNodeEDataType, SerializationNode.class, "SerializationNode", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(userElementSerializerEDataType, UserElementSerializer.class, "UserElementSerializer", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(rtSerializationStepEDataType, SerializationStep.class, "RTSerializationStep", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http://www.eclipse.org/OCL/Import
		createImportAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http://www.eclipse.org/OCL/Import</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createImportAnnotations()
	{
		String source = "http://www.eclipse.org/OCL/Import"; //$NON-NLS-1$
		addAnnotation
		  (this,
		   source,
		   new String[]
		   {
			   "ecore", "http://www.eclipse.org/emf/2002/Ecore" //$NON-NLS-1$ //$NON-NLS-2$
		   });
	}

} //IdiomsPackageImpl
