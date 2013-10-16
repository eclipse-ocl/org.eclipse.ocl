/**
 * <copyright>
 *
 * Copyright (c) 2011, 2012 E.D.Willink and others.
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
 * $Id$
 */
package org.eclipse.ocl.examples.xtext.markup;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class MarkupStandaloneSetup extends MarkupStandaloneSetupGenerated
{
	private static Injector injector = null;
	
	public static void doSetup() {
		if (injector == null) {
			injector = new MarkupStandaloneSetup().createInjectorAndDoEMFRegistration();
		}
	}
	
	public static void doTearDown() {
		injector = null;
	}

	public static Injector getInjector() {
		if (injector == null) {
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
				doSetup();
			}
			else {
				injector = Guice.createInjector(new MarkupRuntimeModule());
			}
		}
		return injector;
	}

	public static void init() {
		EPackage.Registry.INSTANCE.put(MarkupPackage.eNS_URI, MarkupPackage.eINSTANCE);
	}

	@Override
	public Injector createInjector() {
		init();
		injector = super.createInjector();
		return injector;
	}
}

