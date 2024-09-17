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
package org.eclipse.ocl.xtext.completeocl;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.pivot.labels.ILabelGenerator;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.xtext.completeocl.scoping.CompleteOCLScoping;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLASResourceFactory;
import org.eclipse.ocl.xtext.completeocl.utilities.CompleteOCLCSResource.CompleteOCLCSResourceLoadFactory;
import org.eclipse.ocl.xtext.completeocl.utilities.PathNameDeclCSLabelGenerator;
import org.eclipse.ocl.xtext.completeoclcs.CompleteOCLCSPackage;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Initialization support for running Xtext languages
 * without equinox extension registry
 */
public class CompleteOCLStandaloneSetup extends CompleteOCLStandaloneSetupGenerated
{
	private static Injector injector = null;

	public static void doSetup() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;			// Enforces Bug 381901/382058 fix
		if (injector == null) {
			new CompleteOCLStandaloneSetup().createInjectorAndDoEMFRegistration();
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
				injector = Guice.createInjector(new CompleteOCLRuntimeModule());
			}
		}
		return injector;
	}

	public static void init() {
		CompleteOCLScoping.init();			// XXX CompleteOCL delegate URI registration
		CompleteOCLASResourceFactory.getInstance();
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put(PivotConstants.OCL_CS_FILE_EXTENSION, new CompleteOCLCSResourceLoadFactory());			// XXX
		}
		EPackage.Registry.INSTANCE.put(CompleteOCLCSPackage.eNS_URI, CompleteOCLCSPackage.eINSTANCE);
		PathNameDeclCSLabelGenerator.initialize(ILabelGenerator.Registry.INSTANCE);
	}

	@Override
	public Injector createInjector() {
		assert !EMFPlugin.IS_ECLIPSE_RUNNING;
		init();
		injector = super.createInjector();
		return injector;
	}
}

