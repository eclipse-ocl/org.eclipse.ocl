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

package org.eclipse.ocl.xtext.oclstdlib;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.xtext.oclstdlib.utilities.OCLstdlibASResourceFactory;
import org.eclipse.ocl.xtext.oclstdlib.utilities.OCLstdlibCSResource.OCLstdlibCSResourceLoadFactory;
import org.eclipse.ocl.xtext.oclstdlibcs.OCLstdlibCSPackage;
import org.eclipse.ocl.xtext.oclstdlibcs.util.OCLstdlibCSValidator;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages
 * without equinox extension registry
 */
public class OCLstdlibStandaloneSetup extends OCLstdlibStandaloneSetupGenerated
{
	private static Injector injector = null;

	public static void doSetup() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;			// Enforces Bug 381901/382058 fix
		if (injector == null) {
			new OCLstdlibStandaloneSetup().createInjectorAndDoEMFRegistration();
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
				injector = Guice.createInjector(new OCLstdlibRuntimeModule());
			}
		}
		return injector;
	}

	public static void init() {
		OCLstdlibCSPackage.eINSTANCE.getName();
		OCLstdlibASResourceFactory.getInstance();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(PivotConstants.OCLSTDLIB_CS_FILE_EXTENSION, new OCLstdlibCSResourceLoadFactory());			// XXX
		}
		EPackage.Registry.INSTANCE.put(OCLstdlibCSPackage.eNS_URI, OCLstdlibCSPackage.eINSTANCE);
		EValidator.Registry.INSTANCE.put(OCLstdlibCSPackage.eINSTANCE, OCLstdlibCSValidator.INSTANCE);
	}

	@Override
	public Injector createInjector() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;
		init();
		injector = super.createInjector();
		return injector;
	}
}

