/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.idioms;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.ocl.pivot.utilities.PivotStandaloneSetup;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
public class IdiomsStandaloneSetup extends IdiomsStandaloneSetupGenerated
{
	private static Injector injector = null;

	public static void doSetup() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;			// Enforces Bug 381901/382058 fix
		if (injector == null) {
			new IdiomsStandaloneSetup().createInjectorAndDoEMFRegistration();
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
				injector = Guice.createInjector(new IdiomsRuntimeModule());
			}
		}
		return injector;
	}

	public static void init() {
		PivotStandaloneSetup.init(IdiomsPackage.eINSTANCE);
	}

	@Override
	public Injector createInjector() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;
		init();
		injector = super.createInjector();
		return injector;
	}
}
