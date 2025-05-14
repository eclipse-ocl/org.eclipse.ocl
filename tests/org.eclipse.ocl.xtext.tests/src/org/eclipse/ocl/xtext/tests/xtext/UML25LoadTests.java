/*******************************************************************************
 * Copyright (c) 2013, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.tests.xtext;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ParserException;

/**
 * Tests that load a UML model and verify that there are no unresolved proxies as a result.
 * These tests were originally used to support debugging of the OMG UML 2.5 models during the RFP phase.
 * They now perform testing of the nominally released models, except for local tweaks to resolve residual bugs.
 */
public class UML25LoadTests extends AbstractUMLLoadTests
{
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
	//	ThreadLocalExecutor.THREAD_LOCAL_ENVIRONMENT_FACTORY.setState(true);
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("UML.xmi");
		doLoadUML(null, modelURI, false, true, null /*new @NonNull String[] {		// FIXME BUG 551915 validation disabled
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::Classification::Operation::returnResult() : Set(UML::Classification::Parameter)'", // needs ->asSet()
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::StructuredClassifiers::Association::endType() : Set(UML::CommonStructure::Type[+|1])'", // needs ->oclAsType(Set(uml::CommonStructure::Type[+|1]))
			"The 'Operation::CompatibleReturn' constraint is violated for 'UML::StructuredClassifiers::StructuredClassifier::part() : Set(UML::Classification::Property)'" // needs ->asSet()
		}*/, null);		// FIXME BUG 419132 eliminate last argument; always true
	}

	public void testLoad_UML_2_5_Final_UMLDI() throws IOException, InterruptedException, ParserException {
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("DI.xmi");
		doLoadUML(null, modelURI, false, true, NO_MESSAGES, null);		// FIXME BUG 419132 eliminate last argument; always true
	}

	public void testLoad_UML_2_5_Final_StandardProfile() throws IOException, InterruptedException, ParserException {
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		URI modelURI = modelFolderURI.trimSegments(1).appendSegment("StandardProfile.xmi");
		doLoadUML(null, modelURI, false, true, NO_MESSAGES, null);
	}
}
