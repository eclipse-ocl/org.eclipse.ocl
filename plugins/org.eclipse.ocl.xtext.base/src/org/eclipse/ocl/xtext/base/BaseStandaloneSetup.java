/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.xtext.base;

import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.ocl.pivot.labels.ILabelGenerator;
import org.eclipse.ocl.pivot.utilities.PivotStandaloneSetup;
import org.eclipse.ocl.xtext.base.scoping.BaseScoping;
import org.eclipse.ocl.xtext.base.utilities.NamedElementCSLabelGenerator;
import org.eclipse.ocl.xtext.basecs.BaseCSPackage;
import org.eclipse.ocl.xtext.basecs.util.BaseCSValidator;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Initialization support for Base models without equinox extension registry
 */
public class BaseStandaloneSetup extends BaseStandaloneSetupGenerated
{
	private static Injector injector = null;

	public static void doSetup() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;			// Enforces Bug 381901/382058 fix
		if (injector == null) {
			new BaseStandaloneSetup().createInjectorAndDoEMFRegistration();
		}
	}

	public static void doTearDown() {
		injector = null;
	}

	/**
	 * Return the Injector for this plugin.
	 */
	public static final Injector getInjector() {
		if (injector == null) {
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
				doSetup();
			}
			else {
				injector = Guice.createInjector(new BaseRuntimeModule());
			}
		}
		return injector;
	}

	public static void init() {
		PivotStandaloneSetup.doSetup();
		BaseScoping.init();
		EPackage.Registry.INSTANCE.put(BaseCSPackage.eNS_URI, BaseCSPackage.eINSTANCE);
		EValidator.Registry.INSTANCE.put(BaseCSPackage.eINSTANCE, BaseCSValidator.INSTANCE);
		NamedElementCSLabelGenerator.initialize(ILabelGenerator.Registry.INSTANCE);

	}

	@Override
	public Injector createInjector() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;
		init();

		// register default ePackages
		Map<String, Object> globalExtensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
	/*	if (!globalExtensionToFactoryMap.containsKey("ecore"))
			globalExtensionToFactoryMap.put("ecore", new org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl());
		if (!globalExtensionToFactoryMap.containsKey("xmi"))
			globalExtensionToFactoryMap.put("xmi", new org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl());
		if (!globalExtensionToFactoryMap.containsKey("xtextbin"))
			globalExtensionToFactoryMap.put("xtextbin", new BinaryGrammarResourceFactoryImpl());
		if (!EPackage.Registry.INSTANCE.containsKey(BaseCSPackage.eNS_URI))
			EPackage.Registry.INSTANCE.put(BaseCSPackage.eNS_URI, BaseCSPackage.eINSTANCE); */

		if (globalExtensionToFactoryMap.containsKey("xmi"))
			globalExtensionToFactoryMap.remove("xmi");
		if (!globalExtensionToFactoryMap.containsKey(Resource.Factory.Registry.DEFAULT_EXTENSION))
			globalExtensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());

		injector = super.createInjector();
		return injector;
	}

	/**
	 * Overridden to eliminate the registration of the unsupported abstract "base" editing.
	 */
	@Override
	public void register(Injector injector) {
//		org.eclipse.xtext.resource.IResourceFactory resourceFactory = injector.getInstance(org.eclipse.xtext.resource.IResourceFactory.class);
//		org.eclipse.xtext.resource.IResourceServiceProvider serviceProvider = injector.getInstance(org.eclipse.xtext.resource.IResourceServiceProvider.class);
//		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("base", resourceFactory);
//		org.eclipse.xtext.resource.IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("base", serviceProvider);
	}
}

