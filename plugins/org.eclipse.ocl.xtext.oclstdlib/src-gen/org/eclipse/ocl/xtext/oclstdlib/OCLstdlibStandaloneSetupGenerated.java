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
package org.eclipse.ocl.xtext.oclstdlib;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;
import org.eclipse.xtext.ISetup;
import org.eclipse.xtext.resource.IResourceFactory;
import org.eclipse.xtext.resource.IResourceServiceProvider;

@SuppressWarnings("all")
public class OCLstdlibStandaloneSetupGenerated implements ISetup {

	@Override
	public Injector createInjectorAndDoEMFRegistration() {
		EssentialOCLStandaloneSetup.doSetup();

		Injector injector = createInjector();
		register(injector);
		return injector;
	}

	public Injector createInjector() {
		return Guice.createInjector(new OCLstdlibRuntimeModule());
	}

	public void register(Injector injector) {
		IResourceFactory resourceFactory = injector.getInstance(IResourceFactory.class);
		IResourceServiceProvider serviceProvider = injector.getInstance(IResourceServiceProvider.class);

		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("oclstdlib", resourceFactory);
		IResourceServiceProvider.Registry.INSTANCE.getExtensionToFactoryMap().put("oclstdlib", serviceProvider);
	}
}
