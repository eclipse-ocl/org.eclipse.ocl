/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *   E.D.Willink (CEA LIST) - Bug 382981
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.utilities.OCL;

/**
 * Tests that exercise the Xtext OCL Console using a UML model.
 */
public class UMLConsoleTests extends AbstractConsoleTests
{
	//	@Override
	//	protected void setUp() throws Exception {
	//		AbstractEnvironmentFactory.ENVIRONMENT_FACTORY_ATTACH.setState(true);
	//		super.setUp();
	//	}

	//	@Override
	//	protected void tearDown() throws Exception {
	//		super.tearDown();
	//		AbstractEnvironmentFactory.ENVIRONMENT_FACTORY_ATTACH.setState(false);
	//	}

	public void testConsole_Bug400090() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();		// Emulate the separate UML Editor's AdapterFactoryEditingDomainResourceSet

		URI testModelURI = getTestModelURI("models/uml/Bug400090.uml");
		Resource umlResource = resourceSet.getResource(testModelURI, true);
		org.eclipse.uml2.uml.Model model = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class bookClass = (org.eclipse.uml2.uml.Class)model.getOwnedType("Book");
		org.eclipse.uml2.uml.InstanceSpecification bookInstance = (org.eclipse.uml2.uml.InstanceSpecification)model.getOwnedMember("TheBook");
		//
		assertConsoleResult(consolePage, bookClass, "self", "Bug400090::Book\n");
		assertFalse(consolePage.isPopUpModelTypesUsageInformation());
		assertConsoleResult(consolePage, bookInstance, "self.classifier", "Bug400090::Book\n");
		assertTrue(consolePage.isPopUpModelTypesUsageInformation());
		consolePage.resetPopUpModelTypesUsageInformation();
		assertConsoleResult(consolePage, bookInstance, "self.price", "<b><error>Parsing failure\n</error></b><error>\n1:6: Unresolved Property 'UML::InstanceSpecification::price'\n</error>");
		assertFalse(consolePage.isPopUpModelTypesUsageInformation());
		assertConsoleResult(consolePage, bookClass, "self", "Bug400090::Book\n");
		assertFalse(consolePage.isPopUpModelTypesUsageInformation());
		assertConsoleResult(consolePage, bookInstance, "self.classifier", "Bug400090::Book\n");
		assertTrue(consolePage.isPopUpModelTypesUsageInformation());
		consolePage.resetPopUpModelTypesUsageInformation();
		assertConsoleResult(consolePage, bookInstance, "self.oclAsModelType(Bug400090::Book).price", "7.5\n");
		assertFalse(consolePage.isPopUpModelTypesUsageInformation());
		//
		consolePage.cancelValidation();
	}

	public void testConsole_Bug419556() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();		// Emulate the separate UML Editor's AdapterFactoryEditingDomainResourceSet

		URI testModelURI = getTestModelURI("models/uml/Bug419556.uml");
		Resource umlResource = resourceSet.getResource(testModelURI, true);
		org.eclipse.uml2.uml.Model model = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class class1 = (org.eclipse.uml2.uml.Class)model.getOwnedType("Class1");
		org.eclipse.uml2.uml.Property attribute1 = class1.getOwnedAttribute("Attribute1", null);
		//
		assertConsoleResult(consolePage, class1, "self.extension_Stereotype3", "Class1$Stereotype3\n");
		//
		assertConsoleResult(consolePage, attribute1, "self.extension_Stereotype1", "Attribute1$Stereotype1\n");		// Bug 419557
		assertConsoleResult(consolePage, attribute1, "self.extension_Stereotype2", "<error>null\n</error>");
		//
		consolePage.cancelValidation();
	}

	public void testConsole_Bug437715() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();		// Emulate the separate UML Editor's AdapterFactoryEditingDomainResourceSet

		URI testModelURI = getTestModelURI("models/uml/Bug437715.uml");
		Resource umlResource = resourceSet.getResource(testModelURI, true);
		org.eclipse.uml2.uml.Model model = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class class1 = (org.eclipse.uml2.uml.Class)model.getOwnedType("Class1");
		org.eclipse.uml2.uml.Class class2 = (org.eclipse.uml2.uml.Class)model.getOwnedType("Class2");
		//        org.eclipse.uml2.uml.Property attribute1 = class1.getOwnedAttribute("Attribute1", null);
		//
		assertConsoleResult(consolePage, class1, "self.extension_Stereotype1.stereotype2", "«Stereotype2»model::Class2\n");
		assertConsoleResult(consolePage, class2, "self.extension_Stereotype2.stereotype1", "«Stereotype1»model::Class1\n");
		//
		//		assertConsoleResult(consolePage, attribute1, "self.extension_Stereotype1", "Attribute1$Stereotype1\n");		// Bug 419557
		//		assertConsoleResult(consolePage, attribute1, "self.extension_Stereotype2", "<error>null\n</error>");
		//
		consolePage.cancelValidation();
	}

	public void testConsole_Bug474085() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();		// Emulate the separate UML Editor's AdapterFactoryEditingDomainResourceSet

		URI testModelURI = getTestModelURI("models/uml/Bug474085.uml");
		Resource umlResource = resourceSet.getResource(testModelURI, true);
		org.eclipse.uml2.uml.Model model = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.StateMachine sm = (org.eclipse.uml2.uml.StateMachine)model.getOwnedType("StateMachine0");
		org.eclipse.uml2.uml.Region region0a = sm.getRegion("Region0");
		org.eclipse.uml2.uml.Transition transition0a = region0a.getTransition("Transition0");
		org.eclipse.uml2.uml.Transition transition1a = region0a.getTransition("Transition1");
		org.eclipse.uml2.uml.Transition transition2a = region0a.getTransition("Transition2");
		org.eclipse.uml2.uml.State state0 = (org.eclipse.uml2.uml.State)region0a.getSubvertex("State0");
		org.eclipse.uml2.uml.Region region0b = state0.getRegion("Region0");
		org.eclipse.uml2.uml.Transition transition3b = region0b.getTransition("Transition3");
		//        org.eclipse.uml2.uml.Property attribute1 = class1.getOwnedAttribute("Attribute1", null);
		//
		assertConsoleResult(consolePage, transition3b, "self.extension_EnsureRole", "Transition3$EnsureRole\n");
		assertConsoleResult(consolePage, transition3b, "self.extension_EnsureRole.role", "'user=bob'\n'role=admin'\n");
		assertConsoleResult(consolePage, transition2a, "self.extension_EnsureRole.role", "'One'\n'Two'\n");
		assertConsoleResult(consolePage, transition1a, "self.extension_EnsureRole.role", "'One'\n");
		assertConsoleResult(consolePage, transition0a, "self.extension_EnsureRole.role", "");
		//		assertConsoleResult(consolePage, class2, "self.extension_Stereotype2.stereotype1", "«Stereotype1»model::Class1\n");
		//
		//		assertConsoleResult(consolePage, attribute1, "self.extension_Stereotype1", "Attribute1$Stereotype1\n");		// Bug 419557
		//		assertConsoleResult(consolePage, attribute1, "self.extension_Stereotype2", "<error>null\n</error>");
		//
		consolePage.cancelValidation();
	}

	public void testConsole_Bug507406() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();		// Emulate the separate UML Editor's AdapterFactoryEditingDomainResourceSet

		URI testModelURI = getTestModelURI("models/uml/Bug507406.uml");
		Resource umlResource = resourceSet.getResource(testModelURI, true);
		org.eclipse.uml2.uml.Model model = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Interaction interaction1 = (org.eclipse.uml2.uml.Interaction)model.getOwnedType("Interaction1");
		org.eclipse.uml2.uml.Message message = interaction1.getMessage("Message");
		//
		assertConsoleResult(consolePage, message, "self", "RootElement::Interaction1::Message\n");
		assertConsoleResult(consolePage, message, "self.receiveEvent", "RootElement::Interaction1::MessageRecv\n");
		assertConsoleResult(consolePage, message, "self.receiveEvent.oclAsType(MessageOccurrenceSpecification).covered", "RootElement::Interaction1::Lifeline0\n");
		assertConsoleResult(consolePage, message, "self.receiveEvent.oclAsType(MessageOccurrenceSpecification).covered.extension_MyLifeline2", "Lifeline0$MyLifeline2\n");
		//
		consolePage.cancelValidation();
	}

	public void testConsole_Bug512553() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();		// Emulate the separate UML Editor's AdapterFactoryEditingDomainResourceSet

		URI testModelURI = getTestModelURI("models/uml/Bug512553.uml");
		Resource umlResource = resourceSet.getResource(testModelURI, true);
		org.eclipse.uml2.uml.Model model = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class person = (org.eclipse.uml2.uml.Class)model.getOwnedType("Person");
		//
		assertConsoleResult(consolePage, person, "isLower('john')", "true\n");
		assertConsoleResult(consolePage, person, "Person::isLower('John')", "false\n");
		assertConsoleResult(consolePage, person, "test::Person::isLower('John')", "false\n");
		assertConsoleResult(consolePage, person, "test::Person::isLower('john')", "true\n");
		assertConsoleResult(consolePage, person, "test::Person.isLower('John')", "false\n");
		assertConsoleResult(consolePage, person, "test::Person.isLower('john')", "true\n");
		//		assertConsoleResult(consolePage, person, "test::Person.isLower('John')", "<b><error>Parsing failure\n</error></b><error>\n1: Unresolved Static Operation 'test::Person::isLower('John')'\n</error>");
		//		assertConsoleResult(consolePage, person, "test::Person.isLower('john')", "<b><error>Parsing failure\n</error></b><error>\n1: Unresolved Static Operation 'test::Person::isLower('john')'\n</error>");
		//
		consolePage.cancelValidation();
	}

	public void testConsole_Bug516285() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();		// Emulate the separate UML Editor's AdapterFactoryEditingDomainResourceSet

		URI testModelURI = getTestModelURI("models/uml/Bug516285.uml");
		Resource umlResource = resourceSet.getResource(testModelURI, true);
		org.eclipse.uml2.uml.Model model = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		//
		assertConsoleResult(consolePage, model, "allOwnedElements()->selectByKind(Property)", "RootElement::CustomerService::ServiceAgreement::serviceLevel\n");
		//
		consolePage.cancelValidation();
	}

	@SuppressWarnings({"unused"})
	public void testConsole_UML() throws Exception {
		doDelete(PLUGIN_ID);
		OCL ocl = consolePage.getEditorOCL();
		EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension)ocl.getEnvironmentFactory();
		ResourceSet resourceSet = ocl.getResourceSet();

		Resource umlResource = resourceSet.getResource(getTestModelURI("models/uml/InternationalizedClasses.uml"), true);
		Resource umlProfileResource = resourceSet.getResource(getTestModelURI("models/uml/Internationalized.profile.uml"), true);
		//        ASResource asResource = ocl.uml2as(umlResource);
		//        Root root = (Root) asResource.getContents().get(0);
		//        org.eclipse.ocl.pivot.Package modelPackage = ClassUtil.getNamedElement(root.getNestedPackage(), "Model");
		org.eclipse.uml2.uml.Package umlPackage = (org.eclipse.uml2.uml.Package) umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Type umlEnglishClass = umlPackage.getOwnedType("EnglishClass");
		org.eclipse.uml2.uml.Profile umlProfile = (org.eclipse.uml2.uml.Profile) umlProfileResource.getContents().get(0);
		org.eclipse.uml2.uml.Stereotype umlInEnglishStereotype = umlProfile.getOwnedStereotype("InEnglish");
		org.eclipse.uml2.uml.Stereotype umlInFrenchStereotype = umlProfile.getOwnedStereotype("InFrench");
		org.eclipse.uml2.uml.Stereotype umlInGermanStereotype = umlProfile.getOwnedStereotype("InGerman");
		Type asEnglishClass = environmentFactory.getASOf(Type.class, umlEnglishClass);
		//        Type englishClass = ClassUtil.getNamedElement(modelPackage.getOwnedType(), "EnglishClass");
		//        Type frenchClass = ClassUtil.getNamedElement(modelPackage.getOwnedType(), "FrenchClass");
		//        Type germanClass = ClassUtil.getNamedElement(modelPackage.getOwnedType(), "GermanClass");
		//        Type plainClass = ClassUtil.getNamedElement(modelPackage.getOwnedType(), "PlainClass");
		//        PackageServer profile = metamodelManager.getPackageManager().getPackageByURI("http://www.eclipse.org/ocl/examples/Internationalized");
		//        Type inEnglishStereotype = profile.getMemberType("InEnglish");
		//        Type inFrenchStereotype = profile.getMemberType("InFrench");
		//        Type inGermanStereotype = profile.getMemberType("InGerman");
		//        ElementExtension englishClassInEnglish = ClassUtil.getNamedElement(englishClass.getExtension(), "EnglishClass$InEnglish");
		//
		assertConsoleResult(consolePage, umlEnglishClass, "self.name", "'EnglishClass'\n");
		// allInstances
		assertConsoleResult(consolePage, umlEnglishClass, "Stereotype.allInstances()->sortedBy(name)", "");		// Tests Bug 382981
		assertConsoleResult(consolePage, umlInEnglishStereotype, "Stereotype.allInstances()->sortedBy(name)", "InternationalizedProfile::InEnglish\nInternationalizedProfile::InFrench\nInternationalizedProfile::InGerman\nInternationalizedProfile::Internationalized\n");
		assertConsoleResult(consolePage, umlEnglishClass, "Class.allInstances()->sortedBy(name)", "Model::EnglishClass\nModel::FrenchClass\nModel::GermanClass\nModel::LanguageClass\nModel::MultiLingualClass\nModel::PlainClass\n");
		assertConsoleResult(consolePage, asEnglishClass, "ocl::Class.allInstances()->sortedBy(name)", "Model::EnglishClass\nEnglishClass$InEnglish\nModel::FrenchClass\nFrenchClass$InFrench\nModel::GermanClass\nGermanClass$InGerman\nModel::LanguageClass\nModel::MultiLingualClass\nMultiLingualClass$InEnglish\nMultiLingualClass$InFrench\nMultiLingualClass$InGerman\nModel::PlainClass\nString\n");
		//
		consolePage.cancelValidation();
		//		ocl.dispose();
	}
}
