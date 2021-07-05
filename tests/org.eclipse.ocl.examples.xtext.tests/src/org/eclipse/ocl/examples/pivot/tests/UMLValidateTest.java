/*******************************************************************************
 * Copyright (c) 2010, 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *  C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 * 	E.D.Willink - Bug 306079, 322159, 353171
 *  K.Hussey - Bug 331143
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.common.internal.preferences.CommonPreferenceInitializer;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.PivotConstantsInternal;
import org.eclipse.ocl.pivot.internal.values.IntIntegerValueImpl;
import org.eclipse.ocl.pivot.messages.PivotMessages;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.StringUtil;
import org.eclipse.ocl.pivot.utilities.ThreadLocalExecutor;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLLoader;
import org.eclipse.ocl.xtext.oclinecore.validation.OCLinEcoreEObjectValidator;
import org.eclipse.uml2.uml.Enumeration;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.Stereotype;
import org.junit.After;
import org.junit.Before;

/**
 * Tests for the OCL delegate implementations.
 */
public class UMLValidateTest extends AbstractValidateTests
{
	public static final class LoaderWithLog extends CompleteOCLLoader
	{
		StringBuilder s = new StringBuilder();

		public LoaderWithLog(@NonNull EnvironmentFactory environmentFactory) {
			super(environmentFactory);
		}

		@Override
		protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
			s.append("\n");
			s.append(primaryMessage);
			s.append("\n");
			s.append(detailMessage);
			return false;
		}

		@Override
		public String toString() {
			return s.toString();
		}
	}

	public @Nullable EObject getStereotypeApplication(org.eclipse.uml2.uml.@NonNull Element umlElement, org.eclipse.uml2.uml.@NonNull Stereotype umlStereotype) {
		for (EObject eObject : umlElement.eResource().getContents()) {
			if (ClassUtil.safeEquals(eObject.eClass().getName(), umlStereotype.getName())) {
				for (EStructuralFeature eFeature : eObject.eClass().getEAllStructuralFeatures()) {
					if ((eFeature instanceof EReference) && !eFeature.isMany()) {
						Object object = eObject.eGet(eFeature);
						if (object == umlElement) {
							return eObject;
						}
					}
				}
			}
		}
		return null;
	}
	//
	// Test framework
	//
	@Override
	@Before public void setUp() throws Exception {
		super.setUp();
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			UMLStandaloneSetup.init();
		}
		EValidator.Registry.INSTANCE.put(null, new OCLinEcoreEObjectValidator());

		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
		//			"xmi", new EcoreResourceFactoryImpl());
		OCLstdlib.install();
	}

	@Override
	@After public void tearDown() throws Exception {
		EValidator.Registry.INSTANCE.remove(null);
		GlobalEnvironmentFactory.disposeInstance();
		//		OCLstdlib.uninstall();
		super.tearDown();
	}

	public void testValidate_Bug417062_uml() throws IOException, InterruptedException, ParserException {
		//		EcorePlugin.ExtensionProcessor.process(getClass().getClassLoader());
		//		UMLPlugin.ExtensionProcessor.process(getClass().getClassLoader());
		//		new UMLPlugin.BodySupportRegistryReader().readRegistry();
		//
		//	Create model
		//
		OCL ocl = OCL.newInstance();
		Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug417062.uml"));
		org.eclipse.uml2.uml.Model model = (org.eclipse.uml2.uml.Model) umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class book = (org.eclipse.uml2.uml.Class) model.getOwnedType("Book");
		//		org.eclipse.uml2.uml.Property price = book.getOwnedAttribute("price", null);
		org.eclipse.uml2.uml.Constraint constraint = book.getOwnedRules().get(0);
		//		org.eclipse.uml2.uml.InstanceSpecification validBook = (org.eclipse.uml2.uml.InstanceSpecification) model.getOwnedMember("1) Valid book");
		org.eclipse.uml2.uml.InstanceSpecification invalidBook = (org.eclipse.uml2.uml.InstanceSpecification) model.getOwnedMember("2) Invalid book");
		org.eclipse.uml2.uml.InstanceSpecification partialBook = (org.eclipse.uml2.uml.InstanceSpecification) model.getOwnedMember("3) Book with undefined price");
		//		org.eclipse.uml2.uml.InstanceSpecification confusingBook = (org.eclipse.uml2.uml.InstanceSpecification) model.getOwnedMember("4) Opaque expressions and other things");
		//		org.eclipse.uml2.uml.Slot slot = confusingBook.getSlots().get(0);
		//		org.eclipse.uml2.uml.OpaqueExpression opaqueExpression = (org.eclipse.uml2.uml.OpaqueExpression) slot.getOwnedElements().get(0);
		//		Property asPrice = ocl.getMetamodelManager().getPivotOf(Property.class, price);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource,
			//			ClassUtil.bind(UMLMessages.BodyLanguageSupportError, IllegalStateException.class.getName() + ": " + NLS.bind(UMLMessages.MissingBodyLanguageSupport, "Natural language"), ClassUtil.getLabel(opaqueExpression)),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, book.getName() + "::" + constraint.getName(), LabelUtil.getLabel(invalidBook)),
			StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_, book.getName(), constraint.getName(), LabelUtil.getLabel(partialBook),
				StringUtil.bind(PivotMessages.UnsupportedCompareTo, "null", IntIntegerValueImpl.class.getName())));
		//		ocl.dispose();
		ocl = null;		// UMLOCLEValidator.WeakOCLReference will dispose.
	}

	public void test_tutorial_umlValidation_with_lpg_408990() {
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(OCLConstants.OCL_DELEGATE_URI_LPG);
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			assertNull(UML2AS.initialize(resourceSet));
		}
		URI uri = getTestModelURI("models/uml/Bug408990.uml");
		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		assertNoResourceErrors("Loading", umlResource);
		String label = NameUtil.qualifiedNameFor(umlResource.getContents().get(1));
		assertValidationDiagnostics("Loading", umlResource, getMessages(StringUtil.bind(VIOLATED_TEMPLATE, "Stereotype1::IntegerConstraint", label)));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_tutorial_umlValidation_with_pivot_408990() {
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			assertNull(UML2AS.initialize(resourceSet));
		}
		URI uri = getTestModelURI("models/uml/Bug408990.uml");
		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		assertNoResourceErrors("Loading", umlResource);
		org.eclipse.uml2.uml.Model umlModel = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class umlClass1 = (org.eclipse.uml2.uml.Class)umlModel.getOwnedType("Class1");
		org.eclipse.uml2.uml.Profile umlProfile = umlModel.getProfileApplications().get(0).getAppliedProfile();
		org.eclipse.uml2.uml.Stereotype umlStereotype1 = (org.eclipse.uml2.uml.Stereotype)umlProfile.getOwnedType("Stereotype1");
		assert (umlClass1 != null) && (umlStereotype1 != null);
		String label = NameUtil.qualifiedNameFor(getStereotypeApplication(umlClass1, umlStereotype1));
		assertValidationDiagnostics("Loading", umlResource, getMessages(StringUtil.bind(VIOLATED_TEMPLATE, "Stereotype1::IntegerConstraint", label)));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_tutorial_umlValidation_436903() {
		OCL ocl = OCL.newInstance(getProjectMap());
		ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			assertNull(UML2AS.initialize(resourceSet));
		}
		else {
			UML2AS.initializeUML(resourceSet);
		}
		URI uri = getTestModelURI("models/uml/PapyrusTestFile.uml");
		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		assertNoResourceErrors("Loading", umlResource);
		assertValidationDiagnostics("Loading", umlResource, NO_MESSAGES);
		URI oclURI = getTestModelURI("models/uml/ExtraUMLValidation.ocl");
		CompleteOCLLoader helper = new CompleteOCLLoader(ocl.getEnvironmentFactory())
		{
			@Override
			protected boolean error(@NonNull String primaryMessage, @Nullable String detailMessage) {
				return false;
			}
		};
		EnvironmentFactory environmentFactory = helper.getEnvironmentFactory();
		ProjectManager projectMap = environmentFactory.getProjectManager();
		projectMap.configure(environmentFactory.getResourceSet(), StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
		@SuppressWarnings("unused")Resource oclResource = helper.loadResource(oclURI);
		if (!helper.loadMetamodels()) {
			fail("Failed to loadMetamodels");
		}
		//
		//	Load all the documents
		//
		if (!helper.loadDocument(oclURI)) {
			fail("Failed to loadDocument");
		}
		helper.installPackages();
		org.eclipse.uml2.uml.Model umlModel = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class umlClass1 = (org.eclipse.uml2.uml.Class)umlModel.getOwnedType("lowercase");
		//BUG 437450		assertValidationDiagnostics("Loading", umlClass1,
		//		ClassUtil.bind(EvaluatorMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class", "CamelCaseName", EcoreUtils.qualifiedNameFor(umlClass1)));
		List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(umlClass1, validationContext);
		diagnostics.addAll(diagnostic.getChildren());
		assertDiagnostics("Loading", umlResource, diagnostics,
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class::CamelCaseName", NameUtil.qualifiedNameFor(umlClass1)));
		//
		disposeResourceSet(resourceSet);
		helper.dispose();
		ocl.dispose();
	}

	public void test_umlValidation_404882() {
		OCL ocl = OCL.newInstance(getProjectMap());
		ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			assertNull(UML2AS.initialize(resourceSet));
		}
		else {
			UML2AS.initializeUML(resourceSet);
		}
		URI uri = getTestModelURI("models/uml/Bug404882.uml");
		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		assertNoResourceErrors("Loading", umlResource);
		assertValidationDiagnostics("Loading", umlResource, NO_MESSAGES);
		URI oclURI = getTestModelURI("models/uml/Bug404882.ocl");
		LoaderWithLog helper = new LoaderWithLog(ocl.getEnvironmentFactory());
		EnvironmentFactory environmentFactory = helper.getEnvironmentFactory();
		ProjectManager projectMap = environmentFactory.getProjectManager();
		projectMap.configure(environmentFactory.getResourceSet(), StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
		@SuppressWarnings("unused")Resource oclResource = helper.loadResource(oclURI);
		if (!helper.loadMetamodels()) {
			fail("Failed to loadMetamodels :\n" + helper.toString());
		}
		//
		//	Load all the documents
		//
		if (!helper.loadDocument(oclURI)) {
			fail("Failed to loadDocument '" + oclURI + "'");
		}
		helper.installPackages();
		//BUG 437450				assertValidationDiagnostics("Loading", umlResource);
		//
		disposeResourceSet(resourceSet);
		helper.dispose();
		ocl.dispose();
	}

	public void test_umlValidation_423905() {
		OCL ocl = OCL.newInstance(getProjectMap());
		ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			assertNull(UML2AS.initialize(resourceSet));
		}
		else {
			UML2AS.initializeUML(resourceSet);
		}
		URI uri = getTestModelURI("models/uml/Bug423905.uml");
		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		assertNoResourceErrors("Loading", umlResource);
		assertValidationDiagnostics("Loading", umlResource, NO_MESSAGES);
		URI oclURI = getTestModelURI("models/uml/Bug423905.ocl");
		LoaderWithLog helper = new LoaderWithLog(ocl.getEnvironmentFactory());
		EnvironmentFactory environmentFactory = helper.getEnvironmentFactory();
		ProjectManager projectMap = environmentFactory.getProjectManager();
		projectMap.configure(environmentFactory.getResourceSet(), StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
		@SuppressWarnings("unused")Resource oclResource = helper.loadResource(oclURI);
		if (!helper.loadMetamodels()) {
			fail("Failed to loadMetamodels :\n" + helper.toString());
		}
		//
		//	Load all the documents
		//
		if (!helper.loadDocument(oclURI)) {
			fail("Failed to loadDocument '" + oclURI + "'\n" + helper);
		}
		helper.installPackages();
		//BUG 437450				assertValidationDiagnostics("Loading", umlResource);
		//
		disposeResourceSet(resourceSet);
		helper.dispose();
		ocl.dispose();
	}

	public void test_umlValidation_432920() {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			assertNull(UML2AS.initialize(resourceSet));
		}
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		URI uri = getTestModelURI("models/uml/bug432920.uml");
		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		org.eclipse.uml2.uml.Model umlModel = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class umlClass1 = (org.eclipse.uml2.uml.Class)umlModel.getOwnedType("Class1");
		org.eclipse.uml2.uml.Property umlAttribute1 = umlClass1.getOwnedAttribute("Attribute1", null);
		org.eclipse.uml2.uml.ValueSpecification lowerValue = umlAttribute1.getLowerValue();
		org.eclipse.uml2.uml.ValueSpecification upperValue = umlAttribute1.getUpperValue();
		org.eclipse.uml2.uml.Profile umlProfile = umlModel.getProfileApplications().get(0).getAppliedProfile();
		org.eclipse.uml2.uml.Stereotype umlMyClassExtension = (org.eclipse.uml2.uml.Stereotype)umlProfile.getOwnedType("MyClassExtension");
		org.eclipse.uml2.uml.Stereotype umlMyPropertyExtension = (org.eclipse.uml2.uml.Stereotype)umlProfile.getOwnedType("MyPropertyExtension");
		assert (lowerValue != null) && (upperValue != null) && (umlMyClassExtension != null) && (umlMyPropertyExtension != null);
		String string1 = NameUtil.qualifiedNameFor(getStereotypeApplication(upperValue, umlMyClassExtension));
		String string2 = NameUtil.qualifiedNameFor(getStereotypeApplication(upperValue, umlMyPropertyExtension));
		String string3 = NameUtil.qualifiedNameFor(getStereotypeApplication(lowerValue, umlMyClassExtension));
		String string4 = NameUtil.qualifiedNameFor(getStereotypeApplication(lowerValue, umlMyPropertyExtension));
		String string5 = NameUtil.qualifiedNameFor(getStereotypeApplication(umlAttribute1, umlMyPropertyExtension));
		assertValidationDiagnostics("Loading", umlResource, validationContext, getMessages(
			StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_, "MyClassExtension", "ClassConstraint1", string1,
				StringUtil.bind(PivotMessages.IncompatibleOclAsTypeSourceType, "UML::LiteralUnlimitedNatural", "UML::Class")),
			StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_, "MyPropertyExtension", "Constraint1", string2,
				StringUtil.bind(PivotMessages.IncompatibleOclAsTypeSourceType, "UML::LiteralUnlimitedNatural", "UML::Property")),
			StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_, "MyClassExtension", "ClassConstraint1", string3,
				StringUtil.bind(PivotMessages.IncompatibleOclAsTypeSourceType, "UML::LiteralInteger", "UML::Class")),
			StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_, "MyPropertyExtension", "Constraint1", string4,
				StringUtil.bind(PivotMessages.IncompatibleOclAsTypeSourceType, "UML::LiteralInteger", "UML::Property")),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "MyPropertyExtension::Constraint1", string5),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "MyPropertyExtension::Constraint2", string2),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "MyPropertyExtension::Constraint2", string4)));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_434433() {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			assertNull(UML2AS.initialize(resourceSet));
		}
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		URI uri = getTestModelURI("models/uml/Bug434433.uml");
		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		org.eclipse.uml2.uml.Model umlModel = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class umlClass1 = (org.eclipse.uml2.uml.Class)umlModel.getOwnedType("Class1");
		org.eclipse.uml2.uml.Profile umlProfile = umlModel.getProfileApplications().get(0).getAppliedProfile();
		org.eclipse.uml2.uml.Stereotype umlStereotype1 = (org.eclipse.uml2.uml.Stereotype)umlProfile.getOwnedType("Stereotype1");
		assert (umlClass1 != null) && (umlStereotype1 != null);
		String label = NameUtil.qualifiedNameFor(getStereotypeApplication(umlClass1, umlStereotype1));
		assertValidationDiagnostics("Loading", umlResource, validationContext, getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::Constraint3", label)));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug434356() {
		//		EssentialOCLLinkingService.DEBUG_RETRY = true;
		//		UML2AS.TYPE_EXTENSIONS.setState(true);
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initialize(resourceSet, PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			assertNull(UML2AS.initialize(resourceSet));
		}
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		URI uri = getTestModelURI("models/uml/Bug434356.uml");
		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		org.eclipse.uml2.uml.Model umlModel = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Realization umlRealization1 = (org.eclipse.uml2.uml.Realization)umlModel.getPackagedElement("Realization1");
		org.eclipse.uml2.uml.Profile umlProfile = umlModel.getProfileApplications().get(0).getAppliedProfile();
		org.eclipse.uml2.uml.Stereotype umlStereotype1 = (org.eclipse.uml2.uml.Stereotype)umlProfile.getOwnedType("ParentRealization");
		assert (umlRealization1 != null) && (umlStereotype1 != null);
		String label = NameUtil.qualifiedNameFor(getStereotypeApplication(umlRealization1, umlStereotype1));
		ThreadLocalExecutor.resetEnvironmentFactory();;
	//	PivotUtilInternal.getEnvironmentFactory(null).setOption(PivotValidationOptions.PotentialInvalidResult, StatusCodes.Severity.IGNORE);
		assertValidationDiagnostics("Loading", umlResource, validationContext, getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "ParentRealization::In case of a ParentRealization relationship, the supplier should be a child of the client", label)));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug436945() throws IOException {   // This is org.eclipse.ocl.doc/doc/models/1710-m1.uml
		//		UML2AS.TYPE_EXTENSIONS.setState(true);
		//		resetRegistries();
		//		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(OCLDelegateDomain.OCL_DELEGATE_URI_PIVOT);
		//		ResourceSet resourceSet = createResourceSet();
		//		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		//		OCLDelegateDomain.initialize(resourceSet, OCLDelegateDomain.OCL_DELEGATE_URI_PIVOT);
		//		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
		//			assertNull(UML2AS.initialize(resourceSet));
		//		}
		//		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		//		URI uri = getTestModelURI("models/uml/Bug436945.uml");
		//		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		OCL ocl = createOCL();
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug436945.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		//		org.eclipse.uml2.uml.Model umlModel = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES); //,
		//			ClassUtil.bind(EvaluatorMessages.ValidationConstraintIsNotSatisfied_ERROR_, "ParentRealization::In case of a ParentRealization relationship, the supplier should be a child of the client", label));
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource); //,
		//			ClassUtil.bind(UMLMessages.BodyLanguageSupportError, IllegalStateException.class.getName() + ": " + NLS.bind(UMLMessages.MissingBodyLanguageSupport, "Natural language"), ClassUtil.getLabel(opaqueExpression)),
		//			ClassUtil.bind(EvaluatorMessages.ValidationConstraintIsNotSatisfied_ERROR_, book.getName(), constraint.getName(), ClassUtil.getLabel(invalidBook)),
		//			ClassUtil.bind(OCLMessages.ValidationResultIsInvalid_ERROR_, book.getName(), constraint.getName(), ClassUtil.getLabel(partialBook),
		//				ClassUtil.bind(EvaluatorMessages.TypedValueRequired, "Real", "OclVoid")),
		//			ClassUtil.bind(OCLMessages.ValidationResultIsInvalid_ERROR_, book.getName(), constraint.getName(), ClassUtil.getLabel(confusingBook),
		//				"Failed to evaluate " + asPrice),
		//			ClassUtil.bind(OCLMessages.ParsingError, ClassUtil.getLabel(opaqueExpression), "No containing namespace for 3 + 0.4"));
		ocl.dispose();
		ocl = null;		// UMLOCLEValidator.WeakOCLReference will dispose too in due course.
	}

	public void test_umlValidation_446007() {
		OCL ocl = OCL.newInstance(getProjectMap());
		ResourceSet resourceSet = ocl.getResourceSet(); //createResourceSet();
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			assertNull(UML2AS.initialize(resourceSet));
		}
		else {
			UML2AS.initializeUML(resourceSet);
		}
		URI uri = getTestModelURI("models/uml/Bug446007.uml");
		Resource umlResource = ClassUtil.nonNullState(resourceSet.getResource(uri, true));
		assertNoResourceErrors("Loading", umlResource);
		assertValidationDiagnostics("Loading", umlResource, NO_MESSAGES);
		URI oclURI = getTestModelURI("models/uml/Bug446007.ocl");
		LoaderWithLog helper = new LoaderWithLog(ocl.getEnvironmentFactory());
		EnvironmentFactory environmentFactory = helper.getEnvironmentFactory();
		ProjectManager projectMap = environmentFactory.getProjectManager();
		projectMap.configure(environmentFactory.getResourceSet(), StandaloneProjectMap.LoadGeneratedPackageStrategy.INSTANCE, StandaloneProjectMap.MapToFirstConflictHandler.INSTANCE);
		@SuppressWarnings("unused")Resource oclResource = helper.loadResource(oclURI);
		if (!helper.loadMetamodels()) {
			fail("Failed to loadMetamodels :\n" + helper.toString());
		}
		//
		//	Load all the documents
		//
		if (!helper.loadDocument(oclURI)) {
			fail("Failed to loadDocument '" + oclURI + "'");
		}
		helper.installPackages();
		org.eclipse.uml2.uml.Model umlModel = (org.eclipse.uml2.uml.Model)umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Class umlClass1 = (org.eclipse.uml2.uml.Class)umlModel.getOwnedType("Class1");
		Stereotype appliedStereotype = umlClass1.getAppliedStereotype("Profile1::St1");
		EObject stereotypeApplication = umlClass1.getStereotypeApplication(appliedStereotype);
		List<Diagnostic> diagnostics = new ArrayList<Diagnostic>();
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		Diagnostic diagnostic = Diagnostician.INSTANCE.validate(umlModel, validationContext);
		diagnostics.addAll(diagnostic.getChildren());
		assertDiagnostics("Loading", umlResource, diagnostics); //,
		//			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class::CamelCaseName", NameUtil.qualifiedNameFor(umlModel)));

		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource,
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "St1::ff", NameUtil.qualifiedNameFor(stereotypeApplication))); //,
		//
		helper.dispose();
		ocl.dispose();
	}

	public void test_umlValidation_Bug448470() throws IOException { // formerly Bug 447557
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug448470.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		Model model = (Model) umlResource.getContents().get(0);
		Enumeration xx = (Enumeration) model.getOwnedType("Xx");
		assertValidationDiagnostics("Loading", umlResource, validationContext, getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "MyEnum::Constraint1", "«MyEnum»" + LabelUtil.getLabel(xx)),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "MyEnum::Constraint2", "«MyEnum»" + LabelUtil.getLabel(xx))));
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource, getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "MyEnum::Constraint1", "«MyEnum»" + LabelUtil.getLabel(xx)),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "MyEnum::Constraint2", "«MyEnum»" + LabelUtil.getLabel(xx)),
			EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "Constraint1", "«MyEnum»" + LabelUtil.getLabel(xx) }),
			EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "Constraint2", "«MyEnum»" + LabelUtil.getLabel(xx) })));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug452621() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug452621.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		Model model = (Model) umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Type xx = model.getOwnedType("Class1");
		assertValidationDiagnostics("Loading", umlResource, validationContext, getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::unique_default_values", "«Stereotype1»" + LabelUtil.getLabel(xx))));
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource, getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::unique_default_values", "«Stereotype1»" + LabelUtil.getLabel(xx)),
			EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "unique_default_values", "«Stereotype1»" + LabelUtil.getLabel(xx) })));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug458326() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug458326.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES); //,
		//			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::unique_default_values", "«Stereotype1»" + LabelUtil.getLabel(xx)));
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource); //,
		//			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::unique_default_values", "«Stereotype1»" + LabelUtil.getLabel(xx)),
		//			EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "unique_default_values", "«Stereotype1»" + LabelUtil.getLabel(xx) }));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug458394() throws IOException {
//		BaseLinkingService.DEBUG_RETRY.setState(true);
		resetRegistries();
		//		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		//		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
		//			new CommonPreferenceInitializer().initializeDefaultPreferences();
		//		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug458394.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		Model model = (Model) umlResource.getContents().get(0);
		org.eclipse.uml2.uml.InstanceSpecification xx = (org.eclipse.uml2.uml.InstanceSpecification)model.getOwnedMember("Class1BadInstance");
		//		org.eclipse.uml2.uml.Package pack = model.getOwnedMember("Class1BadInstance");
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES); //,
		//			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::unique_default_values", "«Stereotype1»" + LabelUtil.getLabel(xx)));
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource,
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class1::Constraint1a", LabelUtil.getLabel(xx)),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class1::Constraint1b", LabelUtil.getLabel(xx)),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class1::IsModelKindOf", LabelUtil.getLabel(xx)),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class1::ModelType", LabelUtil.getLabel(xx)),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Class1::ModelTypes", LabelUtil.getLabel(xx)));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug458470() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug458470.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		Model model = (Model) umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Package pack = model.getNestedPackage("Package2");
		org.eclipse.uml2.uml.Type xx = pack.getOwnedType("ClassWith");
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES); //,
		//			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::unique_default_values", "«Stereotype1»" + LabelUtil.getLabel(xx)));
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource,
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::Constraint1", "«Stereotype1»" + LabelUtil.getLabel(xx)),
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype2::Constraint2", "«Stereotype2»" + LabelUtil.getLabel(xx)));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug464808() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug464808.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		Model model = (Model) umlResource.getContents().get(0);
		org.eclipse.uml2.uml.Type xx = model.getOwnedType("Class1");
		assertValidationDiagnostics("Loading", umlResource, validationContext, getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::Constraint1", "«Stereotype1»" + LabelUtil.getLabel(xx))));
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource, getMessages(
			StringUtil.bind(PivotMessages.ValidationConstraintIsNotSatisfied_ERROR_, "Stereotype1::Constraint1", "«Stereotype1»" + LabelUtil.getLabel(xx)),
			EcorePlugin.INSTANCE.getString("_UI_GenericInvariant_diagnostic", new Object[] { "Constraint1", "«Stereotype1»" + LabelUtil.getLabel(xx) })));
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug467192() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug467192.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		//		Model model = (Model) umlResource.getContents().get(0);
		//		org.eclipse.uml2.uml.NamedElement xx = model.getOwnedMember("InstanceSpecification1");
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);//,
		String message2 = StringUtil.bind(PivotMessagesInternal.ValidationConstraintIsInvalid_ERROR_, PivotConstantsInternal.INVARIANT_ROLE, "CustomPrimitiveTypes::Class1::SimpleDataTypeArithmetic",
			"self.simpleDataTypeAttribute + self.simpleDataTypeAttribute <> self.simpleDataTypeAttribute'\n" +
				"1: Unresolved Operation 'CustomPrimitiveTypes::SimpleDataType::+(CustomPrimitiveTypes::SimpleDataType)");
		String message3 = "OCL Validation error for \"self.simpleDataTypeAttribute + self.simpleDataTypeAttribute <> self.simpleDataTypeAttribute\"\n" +
				"\tThe 'CallExp::TypeIsNotInvalid' constraint is violated for 'self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)'\n" +
				"\tThe 'OperationCallExp::ArgumentCount' constraint is violated for 'self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)'\n" +
				"\tThe 'OperationCallExp::ArgumentTypeIsConformant' constraint is violated for 'self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)'";
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource,  message2, message3);
		//		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource,
		//			StringUtil.bind(PivotMessagesInternal.ParsingError, "CustomPrimitiveTypes::Class1::SimpleDataTypeArithmetic::" +
		//				"self.simpleDataTypeAttribute + self.simpleDataTypeAttribute <> self.simpleDataTypeAttribute ",
		//			 "The 'Class1::SimpleDataTypeArithmetic' constraint is invalid: 'self.simpleDataTypeAttribute + self.simpleDataTypeAttribute <> self.simpleDataTypeAttribute'\n" +
		//			"1: Unresolved Operation 'CustomPrimitiveTypes::SimpleDataType::+(CustomPrimitiveTypes::SimpleDataType)'"),
		//			StringUtil.bind(PivotMessagesInternal.ValidationResultIsInvalid_ERROR_, "Class1", "SimpleDataTypeArithmetic", LabelUtil.getLabel(xx),
		//					StringUtil.bind(PivotMessagesInternal.FailedToEvaluate_ERROR_, "OclInvalid::oclBadOperation() : OclInvalid[1]", "6.0", "self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)"))); //,
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}
	//	Diagnostic ERROR source=org.eclipse.emf.ecore code=0 Diagnosis of org.eclipse.uml2.uml.internal.impl.ModelImpl@6325f352{file:/E:/GIT/org.eclipse.ocl/tests/org.eclipse.ocl.examples.xtext.tests/bin/org/eclipse/ocl/examples/pivot/tests/models/Bug467192.uml#_7AJBoNeUEeSuVqZ4Q6mWmQ} data=[org.eclipse.uml2.uml.internal.impl.ModelImpl@6325f352 (name: CustomPrimitiveTypes, visibility: <unset>) (URI: null) (viewpoint: <unset>)] [Diagnostic ERROR source=org.eclipse.uml2.uml code=0 "Parsing error for CustomPrimitiveTypes::Class1::SimpleDataTypeArithmetic::self.simpleDataTypeAttribute + self.simpleDataTypeAttribute <> self.simpleDataTypeAttribute :
	//			 The 'Class1::SimpleDataTypeArithmetic' constraint is invalid: 'self.simpleDataTypeAttribute + self.simpleDataTypeAttribute <> self.simpleDataTypeAttribute'
	//			1: Unresolved Operation 'CustomPrimitiveTypes::SimpleDataType::+(CustomPrimitiveTypes::SimpleDataType)'" data=[org.eclipse.uml2.uml.internal.impl.OpaqueExpressionImpl@15405bd6 (name: <unset>, visibility: <unset>) (body: [self.simpleDataTypeAttribute + self.simpleDataTypeAttribute <> self.simpleDataTypeAttribute ], language: [OCL])], Diagnostic WARNING source=org.eclipse.ocl.pivot code=0 The 'CallExp::TypeIsNotInvalid' constraint is violated for 'self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)' data=[self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)], Diagnostic WARNING source=org.eclipse.ocl.pivot code=0 The 'OperationCallExp::ArgumentCount' constraint is violated for 'self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)' data=[self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)], Diagnostic WARNING source=org.eclipse.ocl.pivot code=0 The 'OperationCallExp::ArgumentTypeIsConformant' constraint is violated for 'self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)' data=[self.simpleDataTypeAttribute.oclBadOperation(self.simpleDataTypeAttribute)]]
	public void test_umlValidation_Bug472461() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug472461.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource);
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug472469() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug472469.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource);
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug476662() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug476662.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource);
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug478416() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug478416.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource);
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug485586() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug485586.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource);
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug506191() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug506191.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource);
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug513773() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug513773.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource);
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug514353() throws IOException {
		resetRegistries();
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug514353.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource/*,
			"\"Parsing error for RootElement::Class1::Attribute1::class2.Attribute1 + 1.0:\n" +
				" Failed to load 'file:/E:/GIT/org.eclipse.ocl/tests/org.eclipse.ocl.examples.xtext.tests/bin/org/eclipse/ocl/examples/pivot/tests/models/Bug514353.uml.oclas' : Unsupported Class::nestedClassifiers for \"Class1\" in UML2ASDeclarationSwitch\""*/);
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug515027() throws IOException {
		//		PartialClasses.ADD_BASE_PROPERTY.setState(true);
		//		PartialClasses.ADD_EXTENSION_PROPERTY.setState(true);
		//		PartialClasses.INIT_MEMBER_PROPERTIES.setState(true);
		resetRegistries();
		//		BaseLinkingService.DEBUG_RETRY.setState(true);
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug515027.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource,
				"The 'Test::NonNullInterfaceFull' constraint is violated for '«Test»Bug515027::TClass'");
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}

	public void test_umlValidation_Bug566594() throws IOException {
		//		PartialClasses.ADD_BASE_PROPERTY.setState(true);
		//		PartialClasses.ADD_EXTENSION_PROPERTY.setState(true);
		//		PartialClasses.INIT_MEMBER_PROPERTIES.setState(true);
		resetRegistries();
		//		BaseLinkingService.DEBUG_RETRY.setState(true);
		CommonOptions.DEFAULT_DELEGATION_MODE.setDefaultValue(PivotConstants.OCL_DELEGATE_URI_PIVOT);
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			new CommonPreferenceInitializer().initializeDefaultPreferences();
		}
		OCL ocl = createOCL();
		ResourceSet resourceSet = ocl.getResourceSet();
		org.eclipse.ocl.ecore.delegate.OCLDelegateDomain.initialize(resourceSet);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianResourceSet(resourceSet);
		@SuppressWarnings("null")@NonNull Resource umlResource = doLoadUML(ocl, getTestModelURI("models/uml/Bug566594.uml"));
		assertNoResourceErrors("Loading", umlResource);
		Map<Object, Object> validationContext = LabelUtil.createDefaultContext(Diagnostician.INSTANCE);
		OCLDelegateDomain.initializePivotOnlyDiagnosticianContext(validationContext);
		assertValidationDiagnostics("Loading", umlResource, validationContext, NO_MESSAGES);
		assertUMLOCLValidationDiagnostics(ocl, "UML Load", umlResource);
		disposeResourceSet(resourceSet);
		ocl.dispose();
	}
}
