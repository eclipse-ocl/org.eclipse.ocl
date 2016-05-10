/*******************************************************************************
 * Copyright (c) 2014, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EValidatorRegistryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.emf.ecore.util.EObjectValidator;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.PivotTables;
import org.eclipse.ocl.pivot.internal.delegate.InvocationBehavior;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.delegate.SettingBehavior;
import org.eclipse.ocl.pivot.internal.delegate.ValidationBehavior;
import org.eclipse.ocl.pivot.internal.resource.OCLASResourceFactory;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.internal.validation.EcoreOCLEValidator;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.xtext.base.utilities.ElementUtil;
import org.eclipse.ocl.xtext.basecs.ModelElementCS;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLLoader;
import org.eclipse.ocl.xtext.completeocl.validation.CompleteOCLEObjectValidator;
import org.eclipse.ocl.xtext.oclinecore.validation.OCLinEcoreEObjectValidator;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.junit.After;
import org.junit.Before;

import junit.framework.TestCase;

/**
 * Tests that OCL for model validation works.
 */
public class ValidateTests extends AbstractValidateTests
{
	public static @NonNull List<Diagnostic> assertEcoreOCLValidationDiagnostics(@Nullable OCL ocl, @NonNull String prefix, @NonNull Resource resource, String... messages) {
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		if (ocl != null) {
			validationContext.put(OCL.class,  ocl);
		}
		List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
		for (EObject eObject : resource.getContents()) {
			EValidatorRegistryImpl registry = new EValidatorRegistryImpl();
			registry.put(EcorePackage.eINSTANCE, EcoreOCLEValidator.INSTANCE);
			Diagnostician dignostician = new Diagnostician(registry);
			Diagnostic diagnostic = dignostician.validate(eObject, validationContext);
			diagnostics.addAll(diagnostic.getChildren());
		}
		return assertDiagnostics(prefix, diagnostics, messages);
	}

	public Resource doLoadEcore(OCL ocl, String stem) throws IOException {
		String ecoreName = stem + ".ecore";
		URI ecoreURI = getProjectFileURI(ecoreName);
		Resource ecoreResource = ocl.getResourceSet().getResource(ecoreURI, true);
		return ecoreResource;
	}

	@Override
	@Before public void setUp() throws Exception {
		super.setUp();
	}

	@Override
	@After public void tearDown() throws Exception {
		super.tearDown();
	}

	public void testValidate_Bug366229_oclinecore() throws IOException, InterruptedException {
		//
		//	Create model
		//
		OCL ocl1 = createOCL();
		OCL ocl2 = createOCL();
		Resource ecoreResource = doLoadOCLinEcore(ocl1, "Bug366229");
		ocl2.getResourceSet().getResources().add(ecoreResource);
		ocl1.dispose();
		EPackage overloadsPackage = (EPackage) ecoreResource.getContents().get(0);
		EObject testInstance = eCreate(overloadsPackage, "SubClass");
		//
		//	Check EObjectValidator errors
		//
		EValidator.Registry.INSTANCE.put(overloadsPackage, EObjectValidator.INSTANCE);
		checkValidationDiagnostics(testInstance, Diagnostic.ERROR);
		ocl2.dispose();
	}

	public void testValidate_Bug418551_ecore() throws IOException, InterruptedException {
		//
		//	Create model
		//
		OCL ocl = createOCL();
		Resource ecoreResource = doLoadEcore(ocl, "Bug418551");
		EPackage temp = (EPackage) ecoreResource.getContents().get(0);
		EClass tester = (EClass) temp.getEClassifier("Tester");
		EOperation badOp = NameUtil.getENamedElement(tester.getEOperations(), "badOp");
		//
		//	Check EObjectValidator errors
		//
		checkValidationDiagnostics(temp, Diagnostic.ERROR);
		assertEcoreOCLValidationDiagnostics(ocl, "Ecore Load", ecoreResource,
			StringUtil.bind(EcoreOCLEValidator.MISSING_DELEGATE, InvocationBehavior.NAME, LabelUtil.getLabel(temp)),
			StringUtil.bind(EcoreOCLEValidator.MISSING_DELEGATE, SettingBehavior.NAME, LabelUtil.getLabel(temp)),
			StringUtil.bind(EcoreOCLEValidator.MISSING_DELEGATE, ValidationBehavior.NAME, LabelUtil.getLabel(temp)),
			StringUtil.bind(EcoreOCLEValidator.MISSING_CONSTRAINTS_ANNOTATION_ENTRY, "extraInvariant", LabelUtil.getLabel(tester)),
			StringUtil.bind(EcoreOCLEValidator.EXTRA_CONSTRAINTS_ANNOTATION_ENTRY, "missingInvariant", LabelUtil.getLabel(tester)),
			StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "String", LabelUtil.getLabel(badOp), "body"),
			StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "Integer", LabelUtil.getLabel(badOp), "pre"),
			StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_2, "Integer", LabelUtil.getLabel(badOp), "post"),
			StringUtil.bind(EcoreOCLEValidator.INCOMPATIBLE_TYPE_1, "Boolean", LabelUtil.getLabel(tester.getEStructuralFeature("badType"))),
			StringUtil.bind(EcoreOCLEValidator.MISSING_PROPERTY_KEY, LabelUtil.getLabel(tester.getEStructuralFeature("badDetailName"))),
			StringUtil.bind(EcoreOCLEValidator.DOUBLE_PROPERTY_KEY, LabelUtil.getLabel(tester.getEStructuralFeature("derivationAndInitial"))));
		//
		ocl.dispose();
	}

	public void testValidate_Bug418552_oclinecore() throws IOException, InterruptedException {
		String testDocument =
				"import ecore : 'http://www.eclipse.org/emf/2002/Ecore#/';\n" +
						"\n" +
						"package temp : Test = 'http://www.eclipse.org/mdt/ocl/oclinecore/tutorial'\n" +
						"{\n" +
						"	class Tester\n" +
						"	{\n" +
						"		attribute total : ecore::EDoubleObject { derived volatile }\n" +
						"		{\n" +
						"			derivation: true;\n" +
						"		}\n" +
						"	}\n" +
						"}\n";
		createOCLinEcoreFile("Bug418552.oclinecore", testDocument);
		OCL ocl1 = createOCL();
		@NonNull List<Diagnostic> diagnostics = doValidateOCLinEcore(ocl1, "Bug418552",
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, PivotTables.STR_Property_c_c_CompatibleDefaultExpression, "temp::Tester::total"));
		Object property = diagnostics.get(0).getData().get(0);
		assert property != null;
		assertEquals(PivotPackage.Literals.PROPERTY, ((EObject)property).eClass());
		ModelElementCS csElement = ElementUtil.getCsElement((Element) property);
		ICompositeNode node = NodeModelUtils.getNode(csElement);
		assert node != null;
		assertEquals(7, node.getStartLine());
		assertEquals(10, node.getEndLine());
		ocl1.dispose();
	}

	public void testValidate_Bug492801_xmi() throws IOException, InterruptedException {
		String testModel = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<ecore:EPackage xmi:version=\"2.0\" xmlns:xmi=\"http://www.omg.org/XMI\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" + 
				"    xmlns:ecore=\"http://www.eclipse.org/emf/2002/Ecore\" name=\"example\" nsURI=\"http://www.example.org/generics/validate\" nsPrefix=\"example\">\n" + 
				"  <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore\">\n" + 
				"    <details key=\"invocationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" + 
				"    <details key=\"settingDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" + 
				"    <details key=\"validationDelegates\" value=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\"/>\n" + 
				"  </eAnnotations>\n" + 
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Interface\" abstract=\"true\">\n" + 
				"    <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore\">\n" + 
				"      <details key=\"constraints\" value=\"HasEvents\"/>\n" + 
				"    </eAnnotations>\n" + 
				"    <eAnnotations source=\"http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot\">\n" + 
				"      <details key=\"HasEvents\" value=\"self.events->size() > 0\"/>\n" + 
				"    </eAnnotations>\n" + 
				"    <eTypeParameters name=\"T\">\n" + 
				"      <eBounds eClassifier=\"#//Event\"/>\n" + 
				"    </eTypeParameters>\n" + 
				"    <eStructuralFeatures xsi:type=\"ecore:EReference\" name=\"events\" upperBound=\"-1\"\n" + 
				"        containment=\"true\">\n" + 
				"      <eGenericType eTypeParameter=\"#//Interface/T\"/>\n" + 
				"    </eStructuralFeatures>\n" + 
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"name\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" + 
				"  </eClassifiers>\n" + 
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"CallInterface\">\n" + 
				"    <eGenericSuperTypes eClassifier=\"#//Interface\">\n" + 
				"      <eTypeArguments eClassifier=\"#//CallEvent\"/>\n" + 
				"    </eGenericSuperTypes>\n" + 
				"  </eClassifiers>\n" + 
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"ReplyInterface\">\n" + 
				"    <eGenericSuperTypes eClassifier=\"#//Interface\">\n" + 
				"      <eTypeArguments eClassifier=\"#//ReplyEvent\"/>\n" + 
				"    </eGenericSuperTypes>\n" + 
				"  </eClassifiers>\n" + 
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"Event\">\n" + 
				"    <eStructuralFeatures xsi:type=\"ecore:EAttribute\" name=\"name\" lowerBound=\"1\" eType=\"ecore:EDataType http://www.eclipse.org/emf/2002/Ecore#//EString\"/>\n" + 
				"  </eClassifiers>\n" + 
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"CallEvent\" eSuperTypes=\"#//Event\"/>\n" + 
				"  <eClassifiers xsi:type=\"ecore:EClass\" name=\"ReplyEvent\" eSuperTypes=\"#//Event\"/>\n" + 
				"</ecore:EPackage>\n";
		String testDocument = 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<example:CallInterface\n" + 
				"    xmi:version=\"2.0\"\n" + 
				"    xmlns:xmi=\"http://www.omg.org/XMI\"\n" + 
				"    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" + 
				"    xmlns:example=\"http://www.example.org/generics/validate\"\n" + 
				"    xsi:schemaLocation=\"http://www.example.org/generics/validate Bug492801.ecore\"\n" + 
				"    name=\"inter\">\n" + 
				"  <events xsi:type=\"example:CallEvent\"\n" + 
				"      name=\"a\"/>\n" + 
				"</example:CallInterface>\n";
		createOCLinEcoreFile("Bug492801.ecore", testModel);
		createOCLinEcoreFile("Bug492801.xmi", testDocument);
		OCL ocl1 = createOCL();
		@NonNull List<Diagnostic> diagnostics = doValidateXMI(ocl1, "Bug492801",
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, PivotTables.STR_Property_c_c_CompatibleDefaultExpression, "temp::Tester::total"));
		Object property = diagnostics.get(0).getData().get(0);
		assert property != null;
		assertEquals(PivotPackage.Literals.PROPERTY, ((EObject)property).eClass());
		ModelElementCS csElement = ElementUtil.getCsElement((Element) property);
		ICompositeNode node = NodeModelUtils.getNode(csElement);
		assert node != null;
		assertEquals(7, node.getStartLine());
		assertEquals(10, node.getEndLine());
		ocl1.dispose();
	}

	public @NonNull List<Diagnostic> doValidateXMI(OCL ocl, String stem, String... validationDiagnostics) throws IOException {
		String inputName = stem + ".xmi";
		URI inputURI = getProjectFileURI(inputName);
		Resource xmiResource = ocl.getResourceSet().getResource(inputURI, true);
		assertNoResourceErrors("Load failed", xmiResource);
		assertNoUnresolvedProxies("Unresolved proxies", xmiResource);
		return assertValidationDiagnostics("Pivot validation errors", xmiResource, validationDiagnostics);
	}

	public void testValidate_Pivot_ecore() throws IOException, InterruptedException {
		//
		//	Create model
		//
		OCL ocl = OCL.newInstance(getProjectMap());
		URI ecoreURI = URI.createPlatformResourceURI("/org.eclipse.ocl.pivot/model/Pivot.ecore", true);
		Resource ecoreResource = ocl.getResourceSet().getResource(ecoreURI, true);
		assert ecoreResource != null;
		//
		//	Check EObjectValidator errors
		//
		assertEcoreOCLValidationDiagnostics(ocl, "Ecore Load", ecoreResource);
		//
		ocl.dispose();
	}

	public void testValidate_OCL_2_5_oclas() throws IOException, InterruptedException {
		ResourceSet resourceSet = new ResourceSetImpl();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			getProjectMap().initializeResourceSet(resourceSet);
		}
		Resource resource = resourceSet.getResource(URI.createPlatformResourceURI("org.eclipse.ocl.pivot/model-gen/OCL-2.5.oclas", true), true);
		assertNoValidationErrors("Validating", ClassUtil.nonNullState(resource));
	}

	public void testValidate_Pivot_oclas() throws IOException, InterruptedException {
		ResourceSet resourceSet = new ResourceSetImpl();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			getProjectMap().initializeResourceSet(resourceSet);
			OCLASResourceFactory.getInstance().configure(resourceSet);
		}
		Resource resource = resourceSet.getResource(URI.createPlatformResourceURI("org.eclipse.ocl.pivot/model-gen/Pivot.oclas", true), true);
		assertNoValidationErrors("Validating", ClassUtil.nonNullState(resource));
	}

	public void testValidate_Validate_completeocl() throws IOException, InterruptedException {
		//
		//	Create model
		//
		//	0 - the complementing type system for the validator
		//	1 - the evolving complemented type system under test
		//	2 - the stable complemented type system under test
		//
		OCL ocl0 = createOCL();
		OCL ocl1 = createOCL();
		OCL ocl2 = createOCL();
		Resource ecoreResource1 = doLoadOCLinEcore(ocl1, "Validate");
		Resource ecoreResource2 = doLoadOCLinEcore(ocl2, "Validate");
		EPackage validatePackage1 = ClassUtil.nonNullState((EPackage) ecoreResource1.getContents().get(0));
		EPackage validatePackage2 = ClassUtil.nonNullState((EPackage) ecoreResource2.getContents().get(0));
		URI oclURI = getProjectFileURI("Validate.ocl");
		CompleteOCLEObjectValidator completeOCLEObjectValidator = new CompleteOCLEObjectValidator(validatePackage1, oclURI, ocl0.getEnvironmentFactory());
		EValidator.Registry.INSTANCE.put(validatePackage1, completeOCLEObjectValidator);
		try {
			EObject testInstance1 = eCreate(validatePackage1, "Level3");
			EObject testInstance2 = eCreate(validatePackage2, "Level3");
			String template = PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_;
			String objectLabel;
			//
			//	No errors
			//
			eSet(testInstance1, "ref", "xx");
			eSet(testInstance1, "l1", "xx");
			eSet(testInstance1, "l2a", "xx");
			eSet(testInstance1, "l2b", "xx");
			eSet(testInstance1, "l3", "xx");
			eSet(testInstance2, "ref", "yy");
			eSet(testInstance2, "l1", "yy");
			eSet(testInstance2, "l2a", "yy");
			eSet(testInstance2, "l2b", "yy");
			eSet(testInstance2, "l3", "yy");
			checkValidationDiagnostics(testInstance1, Diagnostic.WARNING);
			checkValidationDiagnostics(testInstance2, Diagnostic.WARNING);
			//
			//	CompleteOCL errors all round
			//
			eSet(testInstance1, "ref", "xxx");
			eSet(testInstance1, "l1", "xxx");
			eSet(testInstance1, "l2a", "xxx");
			eSet(testInstance1, "l2b", "xxx");
			eSet(testInstance1, "l3", "xxx");
			eSet(testInstance2, "ref", "yyy");
			eSet(testInstance2, "l1", "yyy");
			eSet(testInstance2, "l2a", "yyy");
			eSet(testInstance2, "l2b", "yyy");
			eSet(testInstance2, "l3", "yyy");
			objectLabel = LabelUtil.getLabel(testInstance1);
			checkValidationDiagnostics(testInstance1, Diagnostic.WARNING,
				StringUtil.bind(template, "Level1::V1", objectLabel),
				StringUtil.bind(template, "Level2a::V2a", objectLabel),
				StringUtil.bind(template, "Level2b::V2b", objectLabel),
				StringUtil.bind(template, "Level3::V3", objectLabel));
			checkValidationDiagnostics(testInstance2, Diagnostic.WARNING);
			//
			//	One CompleteOCl and one OCLinEcore
			//
			eSet(testInstance1, "ref", "ok");
			eSet(testInstance1, "l1", "ok");
			eSet(testInstance1, "l2a", "bad");
			eSet(testInstance1, "l2b", "ok");
			eSet(testInstance1, "l3", "ok");
			eSet(testInstance2, "ref", "ok");
			eSet(testInstance2, "l1", "ok");
			eSet(testInstance2, "l2a", "bad");
			eSet(testInstance2, "l2b", "ok");
			eSet(testInstance2, "l3", "ok");
			objectLabel = LabelUtil.getLabel(testInstance1);
			checkValidationDiagnostics(testInstance1, Diagnostic.WARNING,
				StringUtil.bind(template,  "Level2a::L2a", objectLabel),
				StringUtil.bind(template,  "Level2a::V2a", objectLabel));
			objectLabel = LabelUtil.getLabel(testInstance2);
			checkValidationDiagnostics(testInstance2, Diagnostic.ERROR,
				StringUtil.bind(VIOLATED_TEMPLATE, "L2a", "Level3 ok", objectLabel));
		}
		finally {
			ocl0.dispose();
			ocl1.dispose();
			ocl2.dispose();
			EValidator.Registry.INSTANCE.remove(validatePackage1);
		}
	}

	public void testValidate_Validate_completeocl_loadresource() throws IOException, InterruptedException {
		OCL ocl = createOCL();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		//
		URI ecoreURI = getTestModelURI("model/OCLinEcoreTutorial.ecore");
		URI xmiURI = getTestModelURI("model/OCLinEcoreTutorial.xmi");
		URI oclURI = getProjectFileURI("ExtraOCLinEcoreTutorial.ocl");
		String testDocument =
				"import '" + ecoreURI.toString() + "'\n" +
						"package tutorial\n" +
						"context Book\n" +
						"inv ExactlyOneCopy: copies=1\n" +
						"endpackage\n";
		createOCLinEcoreFile("ExtraOCLinEcoreTutorial.ocl", testDocument);
		//
		Resource resource = ClassUtil.nonNullState(resourceSet.getResource(xmiURI, true));
		assertValidationDiagnostics("Without Complete OCL", resource,
			StringUtil.bind(VIOLATED_TEMPLATE, "SufficientCopies", "Library lib::Book b2"),
			StringUtil.bind(VIOLATED_TEMPLATE, "AtMostTwoLoans", "Library lib::Member m3"),
			StringUtil.bind(VIOLATED_TEMPLATE, "UniqueLoans", "Library lib::Member m3"));
		//
		CompleteOCLLoader helper = new CompleteOCLLoader(ocl.getEnvironmentFactory()) {
			@Override
			protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
				TestCase.fail(primaryMessage + "\n\t" + detailMessage);
				return false;
			}
		};
		assertTrue(helper.loadMetamodels());
		assertTrue(helper.loadDocument(oclURI));
		helper.installPackages();

		assertValidationDiagnostics("Without Complete OCL", resource, //validationContext,
			StringUtil.bind(VIOLATED_TEMPLATE, "SufficientCopies", "Library lib::Book b2"),
			StringUtil.bind(VIOLATED_TEMPLATE, "AtMostTwoLoans", "Library lib::Member m3"),
			StringUtil.bind(VIOLATED_TEMPLATE, "UniqueLoans", "Library lib::Member m3"),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Book::ExactlyOneCopy", "Library lib::Book b2"));
		//		disposeResourceSet(resourceSet);
		helper.dispose();
		ocl.dispose();
	}

	public void testValidate_Validate_completeocl_Bug422583() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();

		ProjectMap.initializeURIResourceMap(resourceSet);
		Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			uriMap.putAll(EcorePlugin.computePlatformURIMap(false));
		}
		UML2AS.initialize(resourceSet);


		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		//		MetamodelManagerResourceSetAdapter adapter = MetamodelManagerResourceSetAdapter.getAdapter(resourceSet, metamodelManager);
		//
		URI umlURI = getProjectFileURI("Names.uml");
		URI oclURI = getProjectFileURI("Bug422583.ocl");
		String testDocument =
				//				"import uml : '" + UMLResource.UML_METAMODEL_URI + "#/'\n" +
				//				"import uml : '" + XMI2UMLResource.UML_METAMODEL_NS_URI + "'\n" +
				"import uml : 'http://www.eclipse.org/uml2/5.0.0/UML#/'\n" +
				"package uml\n" +
				"  context Element\n" +
				"  def: alwaysTrue() : Boolean = true\n" +
				"  def: rootFalse() : Boolean = false\n" +
				"  inv IsElement: self.alwaysTrue()\n" +
				"  context Classifier\n" +
				"  def: rootFalse() : Boolean = true\n" +
				"  def: leafFalse() : Boolean = true\n" +
				"  inv IsClassifier: self.alwaysTrue()\n" +
				"  inv IsClassifierWrtLeaf: self.leafFalse()\n" +
				"  context Class\n" +
				"  def: leafFalse() : Boolean = false\n" +
				"  inv IsClass: self.alwaysTrue()\n" +
				"  inv IsClassWrtRoot: self.rootFalse()\n" +
				"  inv IsClassWrtLeaf: self.leafFalse()\n" +
				"endpackage\n";
		createOCLinEcoreFile("Bug422583.ocl", testDocument);
		//
		Resource resource = ClassUtil.nonNullState(resourceSet.getResource(umlURI, true));
		org.eclipse.uml2.uml.Class uNamed = null;
		for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof org.eclipse.uml2.uml.Class) {
				if ("UNamed".equals(((org.eclipse.uml2.uml.Class)eObject).getName())) {
					uNamed = (org.eclipse.uml2.uml.Class)eObject;
					break;
				}
			}
		}
		assert uNamed != null;
		assertValidationDiagnostics("Without Complete OCL", resource);
		//
		CompleteOCLLoader helper = new CompleteOCLLoader(ocl.getEnvironmentFactory()) {
			@Override
			protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
				TestCase.fail(primaryMessage + "\n\t" + detailMessage);
				return false;
			}
		};
		assertTrue(helper.loadMetamodels());
		assertTrue(helper.loadDocument(oclURI));
		helper.installPackages();
		String objectLabel1 = LabelUtil.getLabel(uNamed);
		//		String objectLabel3 = ClassUtil.getLabel(uNamed.getOwnedAttribute("r", null).getLowerValue());
		//		String objectLabel4 = ClassUtil.getLabel(uNamed.getOwnedAttribute("s", null).getLowerValue());
		assertValidationDiagnostics("Without Complete OCL", resource,
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Classifier::IsClassifierWrtLeaf", objectLabel1),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class::IsClassWrtLeaf", objectLabel1)/*,
			ClassUtil.bind(EvaluatorMessages.ValidationConstraintIsNotSatisfied_ERROR_, "NamedElement", "visibility_needs_ownership", objectLabel3),	// FIXME BUG 437450
			ClassUtil.bind(EvaluatorMessages.ValidationConstraintIsNotSatisfied_ERROR_, "NamedElement", "visibility_needs_ownership", objectLabel4)*/);	// FIXME BUG 437450
		//		adapter.getMetamodelManager().dispose();
		//		disposeResourceSet(resourceSet);
		helper.dispose();
		ocl.dispose();
	}

	@SuppressWarnings("null")
	public void testValidate_Validate_oclinecore() throws IOException, InterruptedException {
		//
		//	Create model
		//
		OCL ocl1 = createOCL();
		Resource ecoreResource = doLoadOCLinEcore(ocl1, "Validate");
		ocl1.getEnvironmentFactory().adapt(ecoreResource.getResourceSet());
		EPackage validatePackage = (EPackage) ecoreResource.getContents().get(0);
		EObject testInstance = eCreate(validatePackage, "Level3");
		eSet(testInstance, "ref", "ref");
		eSet(testInstance, "l1", "l1");
		eSet(testInstance, "l2a", "l2a");
		eSet(testInstance, "l2b", "l2b");
		eSet(testInstance, "l3", "l3");
		String objectLabel = LabelUtil.getLabel(testInstance);
		//
		//	Check EObjectValidator errors
		//
		EValidator.Registry.INSTANCE.put(validatePackage, EObjectValidator.INSTANCE);
		try {
			String template = EcorePlugin.INSTANCE.getString("_UI_GenericConstraint_diagnostic");
			checkValidationDiagnostics(testInstance, Diagnostic.ERROR,
				StringUtil.bind(template,  "L1", objectLabel),
				StringUtil.bind(template,  "L2a", objectLabel),
				//BUG355184		ClassUtil.bind(template,  "L2b", objectLabel),
				StringUtil.bind(template,  "L3", objectLabel));
			//
			//	Check OCLinEcoreEObjectValidator warnings and distinct message
			//
			EValidator.Registry.INSTANCE.put(validatePackage, new OCLinEcoreEObjectValidator());
			template = PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_;
			checkValidationDiagnostics(testInstance, Diagnostic.WARNING,
				StringUtil.bind(template, "Level1::L1", objectLabel),
				StringUtil.bind(template, "Level2a::L2a", objectLabel),
				//BUG355184		ClassUtil.bind(template,  "L2b", objectLabel),
				StringUtil.bind(template, "Level3::L3", objectLabel));
			//
			//	No errors
			//
			eSet(testInstance, "ref", "ok");
			eSet(testInstance, "l1", "ok");
			eSet(testInstance, "l2a", "ok");
			eSet(testInstance, "l2b", "ok");
			eSet(testInstance, "l3", "ok");
			objectLabel = LabelUtil.getLabel(testInstance);
			checkValidationDiagnostics(testInstance, Diagnostic.WARNING);
			//
			//	Just one error
			//
			eSet(testInstance, "ref", "ok");
			eSet(testInstance, "l1", "bad");
			eSet(testInstance, "l2a", "ok");
			eSet(testInstance, "l2b", "ok");
			eSet(testInstance, "l3", "ok");
			objectLabel = LabelUtil.getLabel(testInstance);
			checkValidationDiagnostics(testInstance, Diagnostic.WARNING,
				StringUtil.bind(template, "Level1::L1", objectLabel));
		} finally {
			ocl1.dispose();
			EValidator.Registry.INSTANCE.remove(validatePackage);
		}
	}
}
