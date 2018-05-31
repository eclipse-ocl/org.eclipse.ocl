/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *   E.D.Willink (CEA LIST) - Bug 388529
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot.tests;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.tests.TestFileSystem;
import org.eclipse.ocl.pivot.ElementExtension;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.EnumerationLiteral;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Profile;
import org.eclipse.ocl.pivot.Stereotype;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.uml.internal.library.UMLElementExtension;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.uml2.uml.util.UMLUtil;
import org.junit.After;
import org.junit.Before;

/**
 * Tests for stereotype expressions.
 */
@SuppressWarnings("null")
public class StereotypesTest extends PivotTestSuite
{
	public class InternationalizedProfile
	{
		org.eclipse.uml2.uml.Profile umlProfile;
		org.eclipse.uml2.uml.Stereotype umlInEnglishStereotype;
		org.eclipse.uml2.uml.Stereotype umlInFrenchStereotype;
		org.eclipse.uml2.uml.Stereotype umlInGermanStereotype;
		org.eclipse.uml2.uml.Enumeration umlFace;
		Profile asProfile;
		Stereotype asInEnglishStereotype;
		Stereotype asInFrenchStereotype;
		Stereotype asInGermanStereotype;
		Enumeration asFace;

		public InternationalizedProfile(@NonNull OCL ocl, org.eclipse.uml2.uml.Profile umlProfile) throws ParserException {
			this.umlProfile = umlProfile;
			umlInEnglishStereotype = umlProfile.getOwnedStereotype("InEnglish");
			umlInFrenchStereotype = umlProfile.getOwnedStereotype("InFrench");
			umlInGermanStereotype = umlProfile.getOwnedStereotype("InGerman");
			umlFace = (org.eclipse.uml2.uml.Enumeration) umlProfile.getOwnedType("Face");
			EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension)ocl.getEnvironmentFactory();
			asProfile = environmentFactory.getASOf(Profile.class, umlProfile);
			asInEnglishStereotype = environmentFactory.getASOf(Stereotype.class, umlInEnglishStereotype);
			asInFrenchStereotype = environmentFactory.getASOf(Stereotype.class, umlInFrenchStereotype);
			asInGermanStereotype = environmentFactory.getASOf(Stereotype.class, umlInGermanStereotype);
			asFace = environmentFactory.getASOf(Enumeration.class, umlFace);
		}
	}

	public class InternationalizedMetamodel
	{
		org.eclipse.uml2.uml.Package umlPackage;
		//		ASResource asResource;
		org.eclipse.uml2.uml.Type umlEnglishClass;
		org.eclipse.uml2.uml.Type umlFrenchClass;
		org.eclipse.uml2.uml.Type umlGermanClass;
		org.eclipse.uml2.uml.Type umlLanguageClass;
		org.eclipse.uml2.uml.Type umlPlainClass;
		org.eclipse.uml2.uml.Type umlString;
		UMLElementExtension umlEnglishClassInEnglish;
		org.eclipse.ocl.pivot.Class asEnglishClass;
		org.eclipse.ocl.pivot.Class asFrenchClass;
		org.eclipse.ocl.pivot.Class asGermanClass;
		ElementExtension asEnglishClassInEnglish;
		ElementExtension asFrenchClassInEnglish;
		ElementExtension asGermanClassInEnglish;

		public InternationalizedMetamodel(@NonNull OCL ocl, @NonNull InternationalizedProfile mmm, org.eclipse.uml2.uml.Package umlPackage) throws ParserException {
			EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension)ocl.getEnvironmentFactory();
			this.umlPackage = umlPackage;
			//	        umlMMM = metamodelManager.getPivotOf(Element.class, umlRoot.eClass());
			//	        asResource = ocl.uml2as(umlResource);
			//	        umlMMM = metamodelManager.getPivotOf(Element.class, umlPackage.eClass());
			//	        Root root = (Root) asResource.getContents().get(0);
			//	        assertNoResourceErrors("Loading models/uml/InternationalizedClasses.uml", asResource);
			//	        org.eclipse.ocl.pivot.Package modelPackage = ClassUtil.getNamedElement(root.getNestedPackage(), "Model");
			umlEnglishClass = umlPackage.getOwnedType("EnglishClass");
			umlFrenchClass = umlPackage.getOwnedType("FrenchClass");
			umlGermanClass = umlPackage.getOwnedType("GermanClass");
			umlLanguageClass = umlPackage.getOwnedType("LanguageClass");
			umlPlainClass = umlPackage.getOwnedType("PlainClass");
			umlString = umlPackage.getOwnedType("String");
			asEnglishClass = environmentFactory.getASOf(org.eclipse.ocl.pivot.Class.class, umlEnglishClass);
			asFrenchClass = environmentFactory.getASOf(org.eclipse.ocl.pivot.Class.class, umlFrenchClass);
			asGermanClass = environmentFactory.getASOf(org.eclipse.ocl.pivot.Class.class, umlGermanClass);
			//
			umlEnglishClassInEnglish = (UMLElementExtension) UMLElementExtension.getUMLElementExtension(mmm.asInEnglishStereotype, umlEnglishClass);
			asEnglishClassInEnglish = NameUtil.getNameable(asEnglishClass.getOwnedExtensions(), "EnglishClass$InEnglish");
			asFrenchClassInEnglish = NameUtil.getNameable(asFrenchClass.getOwnedExtensions(), "FrenchClass$InFrench");
			asGermanClassInEnglish = NameUtil.getNameable(asGermanClass.getOwnedExtensions(), "GermanClass$InGerman");
		}
	}

	public class InternationalizedModel
	{
		EClass eEnglishClass;
		EClass eFrenchClass;
		EClass eGermanClass;
		EObject eEnglishObject;
		EObject eFrenchObject;
		EObject eGermanObject;

		public InternationalizedModel(InternationalizedMetamodel mm, Resource ecoreModel, Resource model) throws ParserException {
			Collection<EPackage> ecorePackages = UMLUtil.convertToEcore(mm.umlPackage, null);
			ecoreModel.getContents().addAll(ecorePackages);
			for (EPackage ePackage : ecorePackages) {
				for (EClassifier eClassifier : ePackage.getEClassifiers()) {
					String name = eClassifier.getName();
					if ("EnglishClass".equals(name)) {
						eEnglishClass = (EClass)eClassifier;
					}
					else if ("FrenchClass".equals(name)) {
						eFrenchClass = (EClass)eClassifier;
					}
					else if ("GermanClass".equals(name)) {
						eGermanClass = (EClass)eClassifier;
					}
				}
			}
			EFactory eFactoryInstance = eEnglishClass.getEPackage().getEFactoryInstance();
			eEnglishObject = eFactoryInstance.create(eEnglishClass);
			model.getContents().add(eEnglishObject);
			eFrenchObject = eFactoryInstance.create(eFrenchClass);
			model.getContents().add(eFrenchObject);
			eGermanObject = eFactoryInstance.create(eGermanClass);
			model.getContents().add(eGermanObject);
		}
	}

	public class MyOCL extends TestOCL
	{
		InternationalizedProfile mmm = null;
		InternationalizedMetamodel mm = null;
		InternationalizedModel m = null;

		public MyOCL(@NonNull TestFileSystem testFileSystem, @NonNull String testPackageName, @NonNull String name) throws ParserException {
			super(testFileSystem, testPackageName, name, OCL.NO_PROJECTS);
			ResourceSet resourceSet = getResourceSet();
			ProjectMap.getAdapter(resourceSet);
			String problem = UML2AS.initialize(resourceSet);
			assertNull(problem);
			URI testModelURI = getTestModelURI("models/uml/InternationalizedClasses.uml");
			Resource umlResource = resourceSet.getResource(testModelURI, true);
			org.eclipse.uml2.uml.Package umlPackage = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
			URI testProfileURI = getTestModelURI("models/uml/Internationalized.profile.uml");
			org.eclipse.uml2.uml.Profile umlProfile = (org.eclipse.uml2.uml.Profile) resourceSet.getResource(testProfileURI, true).getContents().get(0);
			uml2as(umlResource);				// FIXME BUG 437826 must do full model conversion
			mmm = new InternationalizedProfile(this, umlProfile);
			mm = new InternationalizedMetamodel(this, mmm, umlPackage);
			URI ecoreURI = getTestModelURI("Languages.ecore");
			URI modelURI = getTestModelURI("Languages.xmi");
			Resource ecoreModel = resourceSet.createResource(ecoreURI);
			Resource model = resourceSet.createResource(modelURI);
			m = new InternationalizedModel(mm, ecoreModel, model);
		}

		public @NonNull ASResource uml2as(@NonNull Resource umlResource) throws ParserException {
			assert environmentFactory != null;
			UML2AS uml2as = UML2AS.getAdapter(umlResource, environmentFactory);
			Model pivotModel = uml2as.getASModel();
			ASResource asResource = (ASResource) pivotModel.eResource();
			return ClassUtil.nonNullModel(asResource);
		}
	}

	//	@Override
	//	protected @NonNull OCL createOCL() {
	//		return UMLOCL.newInstance();
	//	}

	@Override
	protected @NonNull MyOCL createOCL() throws ParserException {
		return new MyOCL(getTestFileSystem(), getTestPackageName(), getName());
	}

	@Override
	@Before public void setUp() throws Exception {
		//		UML2AS.ADD_ELEMENT_EXTENSION.setState(true);
		//		UML2AS.ADD_IMPORTED_RESOURCE.setState(true);
		//		UML2AS.ADD_PROFILE_APPLICATION.setState(true);
		//		UML2AS.ADD_TYPE_EXTENSION.setState(true);
		//		UML2AS.APPLICABLE_STEREOTYPES.setState(true);
		//		UML2AS.CONVERT_RESOURCE.setState(true);
		//		UML2AS.TYPE_EXTENSIONS.setState(true);
		//		AbstractTypeServer.ADD_BASE_PROPERTY.setState(true);
		//		AbstractTypeServer.ADD_EXTENSION_PROPERTY.setState(true);
		//		AbstractTypeServer.INIT_MEMBER_PROPERTIES.setState(true);
		//    	MetamodelManager.CREATE_MUTABLE_CLONE.setState(true);
		UMLStandaloneSetup.init();
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Tests M1 parsing using base_XXX and extension_YYY.
	 */
	public void test_stereotypeM1Navigation() throws Exception {
		MyOCL ocl = createOCL();
		//		ocl.assertValidQuery(ocl.mm.englishClass, "self.oclType().extension_Internationalized");
		//		ocl.assertValidQuery(ocl.mm.englishClass, "self.extension_Internationalized");
		ocl.assertSemanticErrorQuery(ocl.mm.asEnglishClass, "self.extension_InEnglish", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Model::EnglishClass", "extension_InEnglish");
		ocl.assertValidQuery(ocl.mm.asEnglishClass, "self.oclType().extension_Internationalized");
		//		ocl.assertValidQuery(ocl.mm.englishClass, "self.oclType().extension_InEnglish");
		ocl.assertValidQuery(ocl.mm.asEnglishClass, "self.extension_Internationalized.base_Class");
		ocl.assertSemanticErrorQuery(ocl.mm.asEnglishClass, "self.extension_InGerman", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Model::EnglishClass", "extension_InGerman");
		//xx		ocl.assertValidQuery(ocl.mm.englishClassInEnglish, "self.base_Class");
		//xx		ocl.assertValidQuery(ocl.mm.englishClassInEnglish, "self.base_Class.extension_InEnglish");
		ocl.assertSemanticErrorQuery(ocl.mm.asEnglishClass, "self.getAllAppliedStereotypes()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Model::EnglishClass", "getAllAppliedStereotypes");
		ocl.assertSemanticErrorQuery(ocl.mm.asEnglishClass, "self.getAppliedStereotypes()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Model::EnglishClass", "getAppliedStereotypes");
		//xx		ocl.assertValidQuery(ocl.mm.asEnglishClass, "self.oclType().getAppliedStereotypes()");
		ocl.dispose();
	}

	/**
	 * Tests M2 navigations using base_XXX and extension_YYY.
	 */
	public void test_stereotypeM2Navigation() throws Exception {
		MyOCL ocl = createOCL();
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, "EnglishClass", "self.NamedElement::name");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, "EnglishClass", "self.name");
		//    	ocl.assertQueryEquals(ocl.mm.asEnglishClass, "EnglishClass", "self.NamedElement::name");	// FIXME fails because wrong NamedElement::name chosen
		//    	ocl.assertQueryEquals(ocl.mm.asEnglishClass, "EnglishClass", "self.name");
		ocl.assertSemanticErrorQuery(ocl.mm.asEnglishClass, "self.extension_InEnglish", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Model::EnglishClass", "extension_InEnglish");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, ocl.mm.umlEnglishClassInEnglish, "self.extension_Internationalized");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, ocl.mmm.umlFace.getOwnedLiteral("NORMAL"), "self.extension_Internationalized.face");
		ocl.assertQueryEquals(ocl.mm.umlGermanClass, ocl.mmm.umlFace.getOwnedLiteral("BOLD"), "self.extension_Internationalized.face");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClassInEnglish, ocl.mm.umlEnglishClass, "self.base_Class");
		ocl.assertQueryEquals(ocl.mm.asEnglishClassInEnglish, ocl.mm.asEnglishClass, "self.base_Class");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, "InEnglish", "self.extension_Internationalized.oclType().name");
		//    	ocl.assertQueryEquals(ocl.mm.asEnglishClass, "InEnglish", "self.extension_Internationalized.oclType().name");
		//    	ocl.assertQueryEquals(ocl.mm.englishClass, "EnglishClass$InEnglish", "self.extension_Internationalized.oclType().instanceType.name");
		//    	ocl.assertQueryEquals(ocl.mm.englishClass, "EnglishClass$InEnglish", "self.extension_Internationalized.oclType().name");
		ocl.assertSemanticErrorQuery(ocl.mm.asEnglishClass, "self.extension_InGerman", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Model::EnglishClass", "extension_InGerman");
		ocl.assertSemanticErrorQuery(ocl.getIdResolver().getStaticTypeOf(ocl.mm.umlEnglishClass), "self.extension_Internationalized.extension_InEnglish", PivotMessagesInternal.UnresolvedProperty_ERROR_, "InternationalizedProfile::Internationalized", "extension_InEnglish");
		ocl.assertSemanticErrorQuery(/*metamodelManager.getMetaclass(*/ocl.mm.asEnglishClass/*)*/, "self.extension_Internationalized.extension_InEnglish", PivotMessagesInternal.UnresolvedProperty_ERROR_, "InternationalizedProfile::Internationalized", "extension_InEnglish");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, ocl.mm.umlEnglishClassInEnglish, "self.extension_Internationalized");
		ocl.assertQueryEquals(ocl.mm.asEnglishClass, ocl.mm.asEnglishClassInEnglish, "self.extension_Internationalized");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, ocl.mm.umlEnglishClass, "self.extension_Internationalized.base_Class");
		ocl.assertQueryEquals(ocl.mm.asEnglishClass, ocl.mm.asEnglishClass, "self.extension_Internationalized.base_Class");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClassInEnglish, ocl.mm.umlEnglishClassInEnglish, "self.base_Class.extension_Internationalized");
		ocl.assertQueryEquals(ocl.mm.asEnglishClassInEnglish, ocl.mm.asEnglishClassInEnglish, "self.base_Class.extension_Internationalized");
		ocl.assertQueryTrue(ocl.mm.umlEnglishClass, "extension_Internationalized.base_Class = self");
		ocl.assertQueryTrue(ocl.mm.asEnglishClassInEnglish,  "base_Class.extension_Internationalized = self");
		ocl.assertSemanticErrorQuery(ocl.mm.asFrenchClass, "self.text", PivotMessagesInternal.UnresolvedProperty_ERROR_, "Model::FrenchClass", "text");
		//    	ocl.assertQueryEquals(ocl.mm.frenchClass, "Merci", "extension_InFrench.oclType().instanceType.ownedAttribute->any(name='text').default");
		//    	ocl.assertQueryTrue(ocl.mm.frenchClass, "extension_InFrench.oclType().instanceType.ownedAttribute->any(name='text').default = 'Merci'");
		ocl.dispose();
	}

	/**
	 * Tests allInstances in a stereotyped context.
	 * @throws ParserException
	 */
	public void test_stereotyped_allInstances_382981() throws ParserException {
		MyOCL ocl = createOCL();
		IdResolver idResolver = ocl.getIdResolver();
		//M0
		ocl.assertQueryEquals(ocl.m.eEnglishObject, idResolver.createSetOfEach(null, ocl.m.eEnglishObject), "EnglishClass.allInstances()");
		ocl.assertQueryEquals(ocl.m.eEnglishObject, idResolver.createSetOfEach(null, ocl.m.eGermanObject), "GermanClass.allInstances()");
		//M1
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, idResolver.createSetOfEach(null), "Model::EnglishClass.allInstances()");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, idResolver.createSetOfEach(null, ocl.mm.umlPlainClass, ocl.mm.umlEnglishClass, ocl.mm.umlLanguageClass, ocl.mm.umlFrenchClass, ocl.mm.umlGermanClass), "Class.allInstances()");
		ocl.assertQueryEquals(ocl.mm.asEnglishClass, idResolver.createSetOfEach(null, ocl.mm.asEnglishClassInEnglish, ocl.mm.asFrenchClassInEnglish, ocl.mm.asGermanClassInEnglish), "ocl::ElementExtension.allInstances()");
		//
		//    	ocl.assertQueryEquals(ocl.mm.umlMMM, metamodelManager.createSetValueOf(null, ocl.mm.string, ocl.mm.plainClass, ocl.mm.englishClass, ocl.mm.languageClass, ocl.mm.frenchClass, ocl.mm.germanClass), "uml::Stereotype.allInstances()");
		//    	ocl.assertQueryEquals(metamodelManager.getOclAnyType(), metamodelManager.createSetValueOf(null, ocl.mm.string, ocl.mm.plainClass, ocl.mm.englishClass, ocl.mm.languageClass, ocl.mm.frenchClass, ocl.mm.germanClass), "ocl::Stereotype.allInstances()");
		//    	ocl.assertQueryEquals(ocl.mm.englishClass, getEmptySetValue(), "InEnglish.allInstances()");
		ocl.dispose();
	}

	/**
	 * Tests getAppliedStereotypes.
	 * @throws ParserException
	 */
	public void test_MDT_UML2_operations_382978() throws ParserException {
		MyOCL ocl = createOCL();
		IdResolver idResolver = ocl.getIdResolver();
		//    	ocl.assertQueryEquals(ocl.mm.asEnglishClass, ocl.mm.asEnglishClassInEnglish, "self.extension_Internationalized");
		//    	org.eclipse.uml2.uml.Element uml_EnglishClass = (org.eclipse.uml2.uml.Element)((PivotObjectImpl)mm.umlEnglishClass).getETarget();
		//M0
		ocl.assertSemanticErrorQuery(ocl.mm.asEnglishClass, "self.getAppliedStereotypes()", PivotMessagesInternal.UnresolvedOperation_ERROR_, "Model::EnglishClass", "getAppliedStereotypes");
		//    	ocl.assertQueryEquals(m.englishObject, idResolver.createSetOfEach(null, ((PivotObjectImpl)mm.inEnglishStereotype).getETarget()), "self.oclType().getAppliedStereotypes()");
		//    	ocl.assertQueryEquals(m.englishObject, idResolver.createSetOfEach(null, ((PivotObjectImpl)mm.inEnglishStereotype).getETarget()), "self.getAppliedStereotypes()");
		//M1
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, idResolver.createSetOfEach(null, ((PivotObjectImpl)ocl.mmm.asInEnglishStereotype).getESObject()), "self.getAppliedStereotypes()");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, ((PivotObjectImpl)ocl.mmm.asInEnglishStereotype).getESObject(), "self.getAppliedStereotype('InternationalizedProfile::InEnglish')");
		ocl.assertQueryEquals(ocl.mm.asEnglishClass, ocl.mm.asEnglishClassInEnglish, "self.extension_Internationalized");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, ocl.mm.umlEnglishClassInEnglish, "self.extension_Internationalized");
		ocl.assertQueryEquals(ocl.mm.umlEnglishClass, ocl.mm.umlEnglishClassInEnglish, "self.extension_Internationalized.oclAsType(InternationalizedProfile::InEnglish)");
		//    	ocl.assertQueryInvalid(ocl.mm.umlEnglishClass, "self.extension_Internationalized.oclAsType(InternationalizedProfile::InGerman)", ClassUtil.bind(EvaluatorMessages.IncompatibleOclAsTypeSourceType,
		//    		metamodelManager.getMetaclass(ocl.mm.asEnglishClassInEnglish), "InternationalizedProfile::InGerman"), InvalidValueException.class);
		ocl.assertQueryInvalid(ocl.mm.umlEnglishClass, "self.extension_Internationalized.oclAsType(InternationalizedProfile::InGerman)", StringUtil.bind(PivotMessages.IncompatibleOclAsTypeSourceType,
			ocl.mmm.asInEnglishStereotype, "InternationalizedProfile::InGerman"), InvalidValueException.class);
		ocl.dispose();
	}

	/**
	 * Tests M2 parsing and M1 evaluation using enumeration.
	 */
	public void test_uml_enums_412685() throws Exception {
		MyOCL ocl = createOCL();
		EnumerationLiteral asBold = ocl.mmm.asFace.getEnumerationLiteral("BOLD");
		org.eclipse.uml2.uml.EnumerationLiteral umlBold = ocl.mmm.umlFace.getOwnedLiteral("BOLD");
		//
		ocl.assertQueryEquals(ocl.mm.asGermanClass, asBold, "InternationalizedProfile::Face::BOLD");
		ocl.assertQueryEquals(ocl.mm.umlGermanClass, umlBold, "InternationalizedProfile::Face::BOLD");
		ocl.assertQueryEquals(ocl.mm.asGermanClass, asBold, "self.extension_Internationalized.face");
		ocl.assertQueryEquals(ocl.mm.umlGermanClass, umlBold, "self.extension_Internationalized.face");
		ocl.assertQueryTrue(ocl.mm.asFrenchClass, "self.extension_Internationalized.isFace(InternationalizedProfile::Face::ITALIC)");
		ocl.assertQueryTrue(ocl.mm.umlFrenchClass, "self.extension_Internationalized.isFace(InternationalizedProfile::Face::ITALIC)");
		ocl.assertQueryTrue(ocl.mm.asEnglishClass, "self.extension_Internationalized.face() = InternationalizedProfile::Face::NORMAL");
		ocl.assertQueryTrue(ocl.mm.umlEnglishClass, "self.extension_Internationalized.face() = InternationalizedProfile::Face::NORMAL");
		ocl.assertQueryTrue(ocl.mm.asEnglishClass, "self.extension_Internationalized.isFace(InternationalizedProfile::Face::NORMAL)");
		ocl.assertQueryTrue(ocl.mm.umlEnglishClass, "self.extension_Internationalized.isFace(InternationalizedProfile::Face::NORMAL)");
		ocl.assertQueryFalse(ocl.mm.asEnglishClass, "self.extension_Internationalized.isFace(InternationalizedProfile::Face::BOLD)");
		ocl.assertQueryFalse(ocl.mm.umlEnglishClass, "self.extension_Internationalized.isFace(InternationalizedProfile::Face::BOLD)");
		ocl.assertQueryTrue(ocl.mm.asEnglishClass, "self.extension_Internationalized.face = InternationalizedProfile::Face::NORMAL");
		ocl.assertQueryTrue(ocl.mm.umlEnglishClass, "self.extension_Internationalized.face = InternationalizedProfile::Face::NORMAL");
		ocl.assertQueryFalse(ocl.mm.asFrenchClass, "self.extension_Internationalized.isFace(InternationalizedProfile::Face::BOLD)");
		ocl.assertQueryFalse(ocl.mm.umlFrenchClass, "self.extension_Internationalized.isFace(InternationalizedProfile::Face::BOLD)");
		ocl.assertQueryEquals(ocl.mm.asGermanClass, asBold/*umlBold*/, "self.extension_Internationalized.face");
		ocl.assertQueryEquals(ocl.mm.umlGermanClass, umlBold, "self.extension_Internationalized.face");
		ocl.assertQueryEquals(ocl.mm.asGermanClass, asBold/*umlBold*/, "self.extension_Internationalized.face()");
		ocl.assertQueryEquals(ocl.mm.umlGermanClass, umlBold, "self.extension_Internationalized.face()");
		ocl.dispose();
	}
}
