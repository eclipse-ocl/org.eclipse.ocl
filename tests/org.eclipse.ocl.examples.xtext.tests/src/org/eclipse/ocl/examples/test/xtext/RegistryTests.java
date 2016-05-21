/*******************************************************************************
 * Copyright (c) 2014, 2016 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink (Obeo) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.test.xtext;

import java.util.ArrayList;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.tests.TestCaseAppender;
import org.eclipse.ocl.pivot.internal.registry.CompleteOCLRegistry;
import org.eclipse.ocl.pivot.internal.registry.CompleteOCLRegistry.Registration;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.xtext.junit4.GlobalRegistries;
import org.eclipse.xtext.junit4.GlobalRegistries.GlobalStateMemento;
import org.osgi.framework.Bundle;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Tests the CompleteOCLRegistry.
 */
public class RegistryTests extends TestCase
{
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		TestCaseAppender.INSTANCE.uninstall();
	}

	public void testCompleteOCLRegistry_Access() {
		GlobalStateMemento copyOfGlobalState = null;
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			copyOfGlobalState = GlobalRegistries.makeCopyOfGlobalState();
		}
		try {
			EcorePlugin.ExtensionProcessor.process(null);
			ResourceSet resourceSet = new ResourceSetImpl();
			new ProjectMap(false).initializeResourceSet(resourceSet);
			resourceSet.getResource(URI.createPlatformPluginURI("/org.eclipse.emf.ecore/model/Ecore.ecore", true), true);
			CompleteOCLRegistry registry = CompleteOCLRegistry.INSTANCE;
			Set<URI> registeredResourceURIs = registry.getResourceURIs(resourceSet);
			int expectedSize = 1;
			if (EMFPlugin.IS_ECLIPSE_RUNNING) {
				Bundle bundle = Platform.getBundle("org.eclipse.ocl.examples.project.completeocltutorial");
				if (bundle != null) {
					expectedSize++;
				}
			}
			assertEquals(expectedSize, registeredResourceURIs.size());
			// platform:/plugin/org.eclipse.ocl.examples.xtext.tests/model/ModelWithErrors.ocl
			// (running only) platform:/plugin/org.eclipse.ocl.examples.project.completeocltutorial/model/ExtraEcoreValidation.ocl
		}
		finally {		// Remove the bad Xtext ResourceFactories that EcorePlugin.ExtensionProcessor finds
			if (copyOfGlobalState != null) {
				copyOfGlobalState.restoreGlobalState();
			}
		}
	}
	
	public void testCompleteOCLRegistry_Rebuild() {
		@NonNull URI uriA = URI.createURI("A");
		@SuppressWarnings("null")@NonNull Set<URI> setOf = Sets.newHashSet();
		@SuppressWarnings("null")@NonNull Set<URI> setOf_A = Sets.newHashSet(uriA);
		@SuppressWarnings("null")@NonNull ArrayList<String> listOf_a1 = Lists.newArrayList("a1");
		@SuppressWarnings("null")@NonNull ArrayList<String> listOf_a2 = Lists.newArrayList("a2");
		@SuppressWarnings("null")@NonNull ArrayList<String> listOf_a1_a2 = Lists.newArrayList("a1", "a2");
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
		@SuppressWarnings("null")@NonNull Set<URI> setOf = Sets.newHashSet();
		@SuppressWarnings("null")@NonNull Set<URI> setOf_A = Sets.newHashSet(uriA);
		@SuppressWarnings("null")@NonNull ArrayList<String> listOf_a1 = Lists.newArrayList("a1");
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
