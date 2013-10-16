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
 * $Id: OCLinEcoreStandaloneSetup.java,v 1.4 2011/03/01 08:46:34 ewillink Exp $
 */

package org.eclipse.ocl.examples.xtext.oclinecore;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.oclinecore.cs2as.OCLinEcoreCS2ASRuntimeModule;
import org.eclipse.ocl.examples.xtext.oclinecore.oclinecorecs.OCLinEcoreCSPackage;
import org.eclipse.ocl.examples.xtext.oclinecore.utilities.OCLinEcoreASResourceFactory;

import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages 
 * without equinox extension registry
 */
public class OCLinEcoreStandaloneSetup extends OCLinEcoreStandaloneSetupGenerated
{
	private static Injector injector = null;
	
	public static void doSetup() {
		if (injector == null) {
			injector = new OCLinEcoreStandaloneSetup().createInjectorAndDoEMFRegistration();
		}
	}
	
	public static void doTearDown() {
		injector = null;
	}

	public static void init() {
		OCLinEcoreASResourceFactory.INSTANCE.getClass();
//		OCLinEcorePivot2CS.FACTORY.getClass();
		EPackage.Registry.INSTANCE.put(OCLinEcoreCSPackage.eNS_URI, OCLinEcoreCSPackage.eINSTANCE);
	}
	
	/**
	 * Return the Injector for this plugin.
	 */
	@SuppressWarnings("null")
	@NonNull
	public static final Injector getInjector() {
		if (injector == null) {
			if (EMFPlugin.IS_ECLIPSE_RUNNING) {
				injector =  new OCLinEcoreStandaloneSetup().createInjector();
			} else {
				doSetup();
			}
		}
		return injector;
	}

	@Override
	public Injector createInjector() {
		init();
		injector = super.createInjector()
			.createChildInjector(new OCLinEcoreCS2ASRuntimeModule());
		return injector;
	}
}

