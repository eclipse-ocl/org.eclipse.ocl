/*******************************************************************************
 * Copyright (c) 2013, 2021 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
//import org.eclipse.ocl.examples.uml25.XMI252UMLResourceFactoryImpl;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.utilities.GlobalEnvironmentFactory;
import org.eclipse.ocl.pivot.messages.StatusCodes;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.util.PivotValidator;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;

/**
 * Tests that load a model and verify that there are no unresolved proxies as a result.
 */
public class UML25LoadTests extends LoadTests
{
/*	@Override
	public @NonNull TestOCL createOCL() {
		UMLStandaloneSetup.init();
		TestOCL ocl = new TestOCL(getTestFileSystem(), "UML25LoadTests", getName(), OCL.NO_PROJECTS);
		ResourceSet resourceSet = ocl.getResourceSet();
	//	XMI252UMLResourceFactoryImpl.install(resourceSet, URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true));
		return ocl;
	} */
	protected @Nullable Map<URI, URI> extraURImap = null;

	public @NonNull TestOCL createOCL(@Nullable URI modelFolderURI) {
		if (modelFolderURI == null) {
			return super.createOCL();
		}
		UMLStandaloneSetup.init();
		TestOCL ocl = new TestOCL(getTestFileSystem(), "UML25LoadTests", getName(), OCL.NO_PROJECTS, null);
		ResourceSet resourceSet = ocl.getResourceSet();
//		extraURImap = XMI252UMLResourceFactoryImpl.install(resourceSet, modelFolderURI);
		initializeExtraURIMappings(resourceSet);
		return ocl;
	}

	@Override
	public @NonNull TestOCL createOCLWithProjectMap() {
		UMLStandaloneSetup.init();
		TestOCL ocl = new TestOCL(getTestFileSystem(), "UML25LoadTests", getName(), getProjectMap(), null);
	//	ResourceSet resourceSet = ocl.getResourceSet();
	//	XMI252UMLResourceFactoryImpl.install(resourceSet, URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true));
		return ocl;
	}

	//	public void testLoad_UML_ecore() throws IOException, InterruptedException {
	//		doLoadEcore(URI.createPlatformResourceURI("/org.eclipse.uml2.uml/model/UML.ecore", true));
	//	}

	//	public void testLoad_UML_2_5() throws IOException, InterruptedException, ParserException {
	//		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-12-Jun-2012/UMLDI.xmi", true);
	//		doLoadUML(uml_2_5);
	//	}

	@Override
	protected void initializeExtraURIMappings(@NonNull ResourceSet resourceSet) {
		if (extraURImap != null) {
			resourceSet.getURIConverter().getURIMap().putAll(extraURImap);
		}
	}

	@Override
	protected void tearDown() throws Exception {
		extraURImap = null;
		EValidator.Registry.INSTANCE.put(PivotPackage.eINSTANCE, PivotValidator.INSTANCE);
		super.tearDown();
	}

/*	public void testLoad_UML_2_5_Beta_PrimitiveTypes() throws IOException, InterruptedException, ParserException {
		OCL ocl = createOCL();
		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
		//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
		ocl.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-2.5-Beta/PrimitiveTypes.xmi", true);
		doLoadUML(ocl, uml_2_5, false, true, NO_MESSAGES, null);
		ocl.dispose();
	} */

	/*	public void testLoad_UML_2_5_Beta_UML() throws IOException, InterruptedException, ParserException {
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-2.5-Beta-Edited/UML.uml", true);
		doLoadUML(uml_2_5, true, true);
	} */

	/*	public void testLoad_UML_2_5_Beta_XMI() throws IOException, InterruptedException, ParserException {
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-2.5-Beta/UML.xmi", true);
		doLoadUML(uml_2_5, true, true);
	} */

	/*	public void testLoad_UML_2_5_22Sep2013_PrimitiveTypes() throws IOException, InterruptedException, ParserException {
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-22-Sep-2013-Edited/PrimitiveTypes.uml", true);
		doLoadUML(uml_2_5, true, true);
	} */

	/*	public void testLoad_UML_2_5_22Sep2013_DC() throws IOException, InterruptedException, ParserException {
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-22-Sep-2013-Edited/DC.uml", true);
		doLoadUML(uml_2_5, true, true);
	} */

	/*	public void testLoad_UML_2_5_22Sep2013_DI() throws IOException, InterruptedException, ParserException {
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-22-Sep-2013-Edited/DI.uml", true);
		doLoadUML(uml_2_5, true, true);
	} */

	/*	public void testLoad_UML_2_5_22Sep2013_UML() throws IOException, InterruptedException, ParserException {
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-22-Sep-2013-Edited/UML.uml", true);
		doLoadUML(uml_2_5, true, true);
	} */

	/*	public void testLoad_UML_2_5_22Sep2013_UMLDI() throws IOException, InterruptedException, ParserException {
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-22-Sep-2013-Edited/UMLDI.uml", true);
		doLoadUML(uml_2_5, true, true);
	} */

	// DI.xmi is missing
	/*	public void testLoad_UML_2_5_Beta_UMLDI() throws IOException, InterruptedException, ParserException {
		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-2.5-Beta/UMLDI.xmi", true);
		doLoadUML(uml_2_5, true, true);
	} */

	/*	public void testLoad_UML_2_5_Final_PrimitiveTypes2() throws IOException, InterruptedException, ParserException {
		URIConverter.URI_MAP.put(URI.createURI("http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi"), URI.createPlatformResourceURI("/UML-2.5/XMI-2.5-Final/PrimitiveTypes.xmi", true));
		URIConverter.URI_MAP.put(URI.createURI("http://www.omg.org/spec/UML/20131001/PrimitiveTypes.xmi"), URI.createPlatformResourceURI("/UML-2.5/XMI-2.5-Final/PrimitiveTypes.xmi", true));
//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20131001", UMLPackage.eINSTANCE);
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-2.5-Final/PrimitiveTypes.xmi", true);
		doLoadUML(uml_2_5, true, true);
	} */

	public void testLoad_UML_2_5_Final_PrimitiveTypes() throws IOException, InterruptedException, ParserException {
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("PrimitiveTypes.xmi");
		doLoadUML(null, modelURI, false, true, NO_MESSAGES, null);
	}

	/* FIXME 2 OperationReturnCompatibility warnings
	public void testLoad_Eclipse_UML_2_5() throws IOException, InterruptedException, ParserException {
		if (metamodelManager == null) {
			metamodelManager = new MetamodelManager();
		}
		final MetamodelManager metamodelManager = this.metamodelManager;
		metamodelManager.setAutoLoadASmetamodel(false);
		StandardLibraryContribution.REGISTRY.put(XMI2UMLResource.UML_METAMODEL_NS_URI, new OCLstdlib.Loader());
		URI uml_2_5 = URI.createURI(UMLResource.UML_METAMODEL_URI, true);
		doLoadUML(uml_2_5, true, true, true);
		StandardLibraryContribution.REGISTRY.put(XMI2UMLResource.UML_METAMODEL_NS_URI, new OCLstdlib.RenamingLoader(XMI2UMLResource.UML_METAMODEL_NS_URI));
		this.metamodelManager.dispose();
		this.metamodelManager = null;
	} */

	public void testLoad_UML_2_5_Final_DC() throws IOException, InterruptedException, ParserException {
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("DC.xmi");
		doLoadUML(null, modelURI, false, true, NO_MESSAGES, null);
	}

	public void testLoad_UML_2_5_Final_DI() throws IOException, InterruptedException, ParserException {
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("DI.xmi");
		doLoadUML(null, modelURI, false, true, NO_MESSAGES, null);
	}

	public void testLoad_UML_2_5_Final_DG() throws IOException, InterruptedException, ParserException {
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("DG.xmi");
		doLoadUML(null, modelURI, false, true, NO_MESSAGES, new @NonNull String[] {
			"The 'Class::NameIsNotNull' constraint is violated for 'DG::null'"
		});
	}

	public void testLoad_UML_2_5_Final_UML() throws IOException, InterruptedException, ParserException {
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("UML.xmi");
		GlobalEnvironmentFactory.getInstance().setSafeNavigationValidationSeverity(StatusCodes.Severity.IGNORE);
		doLoadUML(null, modelURI, false, true, null /*new @NonNull String[] {		// FIXME BUG 551915 validation disabled
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::Classification::Operation::returnResult() : Set(UML::Classification::Parameter)'", // needs ->asSet()
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::StructuredClassifiers::Association::endType() : Set(UML::CommonStructure::Type[+|1])'", // needs ->oclAsType(Set(uml::CommonStructure::Type[+|1]))
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::StructuredClassifiers::StructuredClassifier::part() : Set(UML::Classification::Property)'" // needs ->asSet()
		}*/, null);		// FIXME BUG 419132 eliminate last argument; always true
	}
	/*	junit.framework.AssertionFailedError: 5 validation errors
	 */
	public void testLoad_UML_2_5_Final_UMLDI() throws IOException, InterruptedException, ParserException {
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("DI.xmi");
		GlobalEnvironmentFactory.getInstance().setSafeNavigationValidationSeverity(StatusCodes.Severity.IGNORE);
		doLoadUML(null, modelURI, false, true, NO_MESSAGES, null);		// FIXME BUG 419132 eliminate last argument; always true
	}

	public void testLoad_UML_2_5_Final_StandardProfile() throws IOException, InterruptedException, ParserException {
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("StandardProfile.xmi");
		doLoadUML(null, modelURI, false, true, NO_MESSAGES, null);
	}
}
