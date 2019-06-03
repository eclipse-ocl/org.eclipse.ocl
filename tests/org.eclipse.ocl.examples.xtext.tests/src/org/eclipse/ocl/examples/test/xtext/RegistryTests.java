/*******************************************************************************
 * Copyright (c) 2014, 2019 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.examples.xtext.tests.TestUtil;
import org.eclipse.ocl.pivot.internal.registry.CompleteOCLRegistry;
import org.eclipse.ocl.pivot.internal.registry.CompleteOCLRegistry.Registration;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
//import org.eclipse.xtext.testing.GlobalRegistries;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import junit.framework.TestCase;

/**
 * Tests the CompleteOCLRegistry.
 */
public class RegistryTests extends TestCase
{
	@Override
	public String getName() {
		return TestUtil.getName(ClassUtil.nonNullState(super.getName()));
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestCaseAppender.INSTANCE.uninstall();
	}

	public void testCompleteOCLRegistry_Access() {
	//	StandaloneProjectMap.addTrace(EcorePackage.eNS_URI, ~0);
		//		GlobalRegistries.GlobalStateMemento copyOfGlobalState1 = null;
		GlobalRegistries2.GlobalStateMemento copyOfGlobalState2 = null;
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			//			try {
			copyOfGlobalState2 = GlobalRegistries2.makeCopyOfGlobalState();
			//			}
			//			catch (Throwable e) {
			//				copyOfGlobalState1 = GlobalRegistries.makeCopyOfGlobalState();
			//			}
		}
		try {
			EcorePlugin.ExtensionProcessor.process(null);
			ResourceSet resourceSet = new ResourceSetImpl();
			ProjectMap projectMap = new ProjectMap(false);
			projectMap.initializeResourceSet(resourceSet);
			projectMap.configureLoadFirst(resourceSet, EcorePackage.eNS_URI);
			resourceSet.getResource(URI.createPlatformPluginURI("/org.eclipse.emf.ecore/model/Ecore.ecore", true), true);
			CompleteOCLRegistry registry = CompleteOCLRegistry.INSTANCE;
			Set<URI> registeredResourceURIs = registry.getResourceURIs(resourceSet);
			// org.eclipse.ocl.examples.project.completeocltutorial/model/ExtraEcoreValidation.ocl
			// org.eclipse.ocl.examples.xtext.tests/models/ecore/ModelWithErrors.ocl
			int expectedSize = 2;
			//	int expectedSize = 1;
			//	if (EMFPlugin.IS_ECLIPSE_RUNNING) {`		// BUG 535144 at EMF 2.14 RC3 fixed the need to conditionalize standalone
			//		Bundle bundle = Platform.getBundle("org.eclipse.ocl.examples.project.completeocltutorial");
			//		if (bundle != null) {
			//			expectedSize++;
			//		}
			//	}
			assertEquals(registeredResourceURIs.toString(), expectedSize, registeredResourceURIs.size());
			// platform:/plugin/org.eclipse.ocl.examples.xtext.tests/models/ecore/ModelWithErrors.ocl
			// (running only) platform:/plugin/org.eclipse.ocl.examples.project.completeocltutorial/model/ExtraEcoreValidation.ocl
		}
		finally {		// Remove the bad Xtext ResourceFactories that EcorePlugin.ExtensionProcessor finds
			if (copyOfGlobalState2 != null) {
				copyOfGlobalState2.restoreGlobalState();
			}
			//			else if (copyOfGlobalState1 != null) {
			//				copyOfGlobalState1.restoreGlobalState();
			//			}
		}
	}

	public void testCompleteOCLRegistry_Rebuild() {
		@NonNull URI uriA = URI.createURI("A");
		@NonNull Set<@NonNull URI> setOf = new HashSet<>();
		@NonNull Set<@NonNull URI> setOf_A = Sets.newHashSet(uriA);
		@NonNull ArrayList<@NonNull String> listOf_a1 = Lists.newArrayList("a1");
		@NonNull ArrayList<@NonNull String> listOf_a2 = Lists.newArrayList("a2");
		@NonNull ArrayList<@NonNull String> listOf_a1_a2 = Lists.newArrayList("a1", "a2");
		Registration reg_A_a1 = new Registration(uriA, listOf_a1);
		Registration reg_A_a2 = new Registration(uriA, listOf_a2);
		Registration reg_A_a1_a2 = new Registration(uriA, listOf_a1_a2);
		//
		CompleteOCLRegistry registry = new CompleteOCLRegistry();
		assertEquals(setOf, registry.getResourceURIs(listOf_a1));
		assertEquals(setOf, registry.getResourceURIs(listOf_a2));
		//
		registry.addRegistration(reg_A_a1_a2);
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1));
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a2));
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1_a2));
		//
		registry.addRegistration(reg_A_a1);
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1));
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a2));
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1_a2));
		//
		registry.removeRegistration(reg_A_a2);
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1));
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a2));
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1_a2));
		//
		registry.removeRegistration(reg_A_a1_a2);
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1));
		assertEquals(setOf, registry.getResourceURIs(listOf_a2));
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1_a2));
		//
		registry.removeRegistration(reg_A_a1);
		assertEquals(setOf, registry.getResourceURIs(listOf_a1));
		assertEquals(setOf, registry.getResourceURIs(listOf_a2));
		assertEquals(setOf, registry.getResourceURIs(listOf_a1_a2));
	}

	/**
	 * Confirm that registrations are counted so after adding twice, it remains till removed twice.
	 */
	public void testCompleteOCLRegistry_Rebuild_Counted() {
		@NonNull URI uriA = URI.createURI("A");
		@NonNull Set<@NonNull URI> setOf = new HashSet<>();
		@NonNull Set<@NonNull URI> setOf_A = Sets.newHashSet(uriA);
		@NonNull List<@NonNull String> listOf_a1 = Lists.newArrayList("a1");
		Registration reg_A_a1 = new Registration(uriA, listOf_a1);
		//
		CompleteOCLRegistry registry = new CompleteOCLRegistry();
		assertEquals(setOf, registry.getResourceURIs(listOf_a1));
		//
		registry.addRegistration(reg_A_a1);
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1));
		//
		registry.addRegistration(reg_A_a1);
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1));
		//
		registry.removeRegistration(reg_A_a1);
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1));
		//
		registry.removeRegistration(reg_A_a1);
		assertEquals(setOf, registry.getResourceURIs(listOf_a1));
		//
		registry.addRegistration(reg_A_a1);
		assertEquals(setOf_A, registry.getResourceURIs(listOf_a1));
	}
}
