/**
 * <copyright>
 *
 * Copyright (c) 2010, 2012 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *
 * </copyright>
 *
 * $Id: EssentialOCLStandaloneSetup.java,v 1.3 2011/03/01 08:46:48 ewillink Exp $
 */

package org.eclipse.ocl.examples.xtext.essentialocl;

import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.base.BaseStandaloneSetup;
import org.eclipse.ocl.examples.xtext.essentialocl.cs2as.EssentialOCLCS2ASRuntimeModule;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.EssentialOCLCSPackage;
import org.eclipse.ocl.examples.xtext.essentialocl.scoping.EssentialOCLScoping;
import org.eclipse.ocl.examples.xtext.essentialocl.utilities.EssentialOCLASResourceFactory;

import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class EssentialOCLStandaloneSetup extends EssentialOCLStandaloneSetupGenerated
{
	private static Injector injector = null;
	
	public static void doSetup() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;			// Enforces Bug 382058 fix
		if (injector == null) {
			injector = new EssentialOCLStandaloneSetup().createInjectorAndDoEMFRegistration();
		}
	}
	
	public static void doTearDown() {
		injector = null;
	}

	public static void init() {
		BaseStandaloneSetup.doSetup();
		EssentialOCLScoping.init();
		EssentialOCLASResourceFactory.INSTANCE.getClass();
//		EssentialOCLCS2Pivot.FACTORY.getClass();
//		EssentialOCLPivot2CS.FACTORY.getClass();
		EPackage.Registry.INSTANCE.put(EssentialOCLCSPackage.eNS_URI, EssentialOCLCSPackage.eINSTANCE);
//		EValidator.Registry.INSTANCE.put(EssentialOCLCSPackage.eINSTANCE, EssentialOCLCSValidator.INSTANCE);
	}
	
	/**
	 * Return the Injector for this plugin.
	 */
	@SuppressWarnings("null")
	@NonNull
	public static final Injector getInjector() {
		if (injector == null) {
			if (EMFPlugin.IS_ECLIPSE_RUNNING) {
				injector =  new EssentialOCLStandaloneSetup().createInjector();
			} else {
				doSetup();
			}
		}
		return injector;
	}

	@Override
	public Injector createInjector() {
		Map<String, Object> globalExtensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
		if (globalExtensionToFactoryMap.containsKey("xmi"))
			globalExtensionToFactoryMap.remove("xmi");
		if (!globalExtensionToFactoryMap.containsKey(Resource.Factory.Registry.DEFAULT_EXTENSION))
			globalExtensionToFactoryMap.put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		injector = super.createInjector()
				.createChildInjector(new EssentialOCLCS2ASRuntimeModule());
		return injector;
	}

	@Override
	public Injector createInjectorAndDoEMFRegistration() {
		init();
		return super.createInjectorAndDoEMFRegistration();
	}
}

