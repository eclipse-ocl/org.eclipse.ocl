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

package org.eclipse.ocl.xtext.oclinecore;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.xtext.oclinecore.utilities.OCLinEcoreASResourceFactory;
import org.eclipse.ocl.xtext.oclinecorecs.OCLinEcoreCSPackage;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages
 * without equinox extension registry
 */
public class OCLinEcoreStandaloneSetup extends OCLinEcoreStandaloneSetupGenerated
{
	private static Injector injector = null;

	public static void doSetup() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;			// Enforces Bug 381901/382058 fix
		if (injector == null) {
			new OCLinEcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
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
				injector = Guice.createInjector(new OCLinEcoreRuntimeModule());
			}
		}
		return injector;
	}

	public static void init() {
		OCLinEcoreASResourceFactory.getInstance();
//		OCLinEcoreAS2CS.FACTORY.getClass();
		EPackage.Registry.INSTANCE.put(OCLinEcoreCSPackage.eNS_URI, OCLinEcoreCSPackage.eINSTANCE);
	}

	@Override
	public Injector createInjector() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;
		init();
		injector = super.createInjector();
		return injector;
	}
}

