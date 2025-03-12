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
package org.eclipse.ocl.examples.test.xtext;

import java.io.IOException;
import java.util.Objects;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.pivot.tests.TestOCL;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.manager.MetamodelManagerInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.uml.UMLStandaloneSetup;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.uml2.uml.UMLPackage;

/**
 * Tests that load a UML model and verify that there are no unresolved proxies as a result.
 */
public class UMLLoadTests extends AbstractUMLLoadTests
{
/*	@Override
	public @NonNull TestOCL createOCL() {
		UMLStandaloneSetup.init();
		TestOCL ocl = new TestOCL(getTestFileSystem(), "UML25LoadTests", getName(), OCL.NO_PROJECTS);
		ResourceSet resourceSet = ocl.getResourceSet();
	//	XMI252UMLResourceFactoryImpl.install(resourceSet, URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true));
		return ocl;
	} */

	//	public void testLoad_UML_ecore() throws IOException, InterruptedException {
	//		doLoadEcore(URI.createPlatformResourceURI("/org.eclipse.uml2.uml/model/UML.ecore", true));
	//	}

	//	public void testLoad_UML_2_5() throws IOException, InterruptedException, ParserException {
	//		URI uml_2_5 = URI.createPlatformResourceURI("/UML-2.5/XMI-12-Jun-2012/UMLDI.xmi", true);
	//		doLoadUML(uml_2_5);
	//	}

	/*
	 * Supporting Stereotypes on Ecore models is an enthusiasm too far.
	 *
	public void testLoad_Bug580143ecore_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = createOCLWithProjectMap();
		UMLPackage.eINSTANCE.getClass();
		doLoad(ocl, getTestModelURI("models/uml/Bug580143ecore.ocl"));
		ocl.dispose();
	} */

	/*
	 * FIXME we really ought to sort out why UML model names are not redirected to their local models.
	 *
	public void testLoad_Bug580143omg_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = createOCLWithProjectMap();
		UMLPackage.eINSTANCE.getClass();
		doLoad(ocl, getTestModelURI("models/uml/Bug580143omg.ocl"));
		ocl.dispose();
	} */

	public void testLoad_Bug580143uml_ocl() throws IOException, InterruptedException {
		UMLStandaloneSetup.init();
		OCL ocl = createOCLWithProjectMap();
		UMLPackage.eINSTANCE.getClass();
		doLoadOCL(ocl, getTestModelURI("models/uml/Bug580143uml.ocl"));
		ocl.dispose();
	}

	public void testLoad_Fruit_ocl() throws IOException, InterruptedException {
	//	ASResourceImpl.RESOLVE_PROXY.setState(true);
		UMLStandaloneSetup.init();
		OCL ocl = createOCLWithProjectMap();
		UMLPackage.eINSTANCE.getClass();
		URI inputURI = getTestModelURI("models/uml/Fruit.ocl");
		doLoadOCL(ocl, inputURI);
		ocl.dispose();
		URI oclOutputURI = getOCLoutputURI(inputURI);
		checkLoadable(oclOutputURI);
		checkLoadableFromXMI(getXMIoutputURI(oclOutputURI));
	}

	/*
	 * Repeat the final checkLoadableFromXMI stage of testLoad_Fruit_ocl as a debug aid.
	 */
	public void testLoad_Fruit_oclcs() throws IOException, InterruptedException {
	//	ASResourceImpl.RESOLVE_PROXY.setState(true);
		UMLStandaloneSetup.init();
		URI oclOutputURI = getTestModelURI("models/uml/Fruit.saved.oclcs");		// Copied from testLoad_Fruit_ocl outputs
		checkLoadableFromXMI(oclOutputURI);
	}

	public void testLoad_Internationalized_profile_uml() throws IOException, InterruptedException, ParserException {
		//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
		//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uri = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.xtext.tests/models/uml/Internationalized.profile.uml", true);
		doLoadUML(null, uri, false, true, null, null);
	}

	public void testLoad_NullFree_uml() throws IOException, InterruptedException, ParserException {
		UMLStandaloneSetup.init();
		TestOCL ocl = createOCLWithProjectMap();
		URI uri = getTestModelURI("models/uml/NullFree.uml");
		Model model = doLoadUML(ocl, uri, false, true, NO_MESSAGES, null);
		org.eclipse.ocl.pivot.Package asPackage = model.getOwnedPackages().get(0);
		org.eclipse.ocl.pivot.Class asInheritedNullFree = Objects.requireNonNull(NameUtil.getNameable(asPackage.getOwnedClasses(), "InheritedNullFree"));
		org.eclipse.ocl.pivot.Class asNonNullFree = Objects.requireNonNull(NameUtil.getNameable(asPackage.getOwnedClasses(), "NonNullFree"));
		Property inf_nf = Objects.requireNonNull(NameUtil.getNameable(asInheritedNullFree.getOwnedProperties(), "nf"));
		Property inf_nnf = Objects.requireNonNull(NameUtil.getNameable(asInheritedNullFree.getOwnedProperties(), "nnf"));
		Property inf_inf = Objects.requireNonNull(NameUtil.getNameable(asInheritedNullFree.getOwnedProperties(), "inf"));
		Property nnf_nf = Objects.requireNonNull(NameUtil.getNameable(asNonNullFree.getOwnedProperties(), "nf"));
		Property nnf_nnf = Objects.requireNonNull(NameUtil.getNameable(asNonNullFree.getOwnedProperties(), "nnf"));
		Property nnf_inf = Objects.requireNonNull(NameUtil.getNameable(asNonNullFree.getOwnedProperties(), "inf"));
		assertEquals(true, ((CollectionType)inf_nf.getType()).isIsNullFree());
		assertEquals(false, ((CollectionType)inf_nnf.getType()).isIsNullFree());
		assertEquals(true, ((CollectionType)inf_inf.getType()).isIsNullFree());
		assertEquals(true, ((CollectionType)nnf_nf.getType()).isIsNullFree());
		assertEquals(false, ((CollectionType)nnf_nnf.getType()).isIsNullFree());
		assertEquals(false, ((CollectionType)nnf_inf.getType()).isIsNullFree());
		ocl.dispose();
	}

	public void testLoad_StereotypeApplications_uml() throws IOException, InterruptedException, ParserException {
		//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/MOF/20110701", UMLPackage.eINSTANCE);
		//		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);
		//		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xmi", XMI2UMLResource.Factory.INSTANCE);
		URI uri = getTestModelURI("models/uml/StereotypeApplications.uml");
		doLoadUML(null, uri, new AbstractLoadCallBack(false, NO_MESSAGES, false) {
			@Override
			public void postLoad(@NonNull OCL ocl, @NonNull ASResource asResource) {
				for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
					EObject obj = tit.next();
					if (obj instanceof Type) {
						((MetamodelManagerInternal)ocl.getMetamodelManager()).getAllInvariants((Type) obj);		// This gives the Bug 422938 CCE
					}
				}
			}
		}, null);
	}
}
