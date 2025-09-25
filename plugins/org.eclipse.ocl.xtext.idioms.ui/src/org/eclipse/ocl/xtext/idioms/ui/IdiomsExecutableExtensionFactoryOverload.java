/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
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
package org.eclipse.ocl.xtext.idioms.ui;

import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.xtext.idioms.IdiomsStandaloneSetup;

import com.google.inject.Injector;

/**
 * This overload works around the global registry corruption by EcorePlugin.ExtensionProcessor if called after doSetup().
 * See https://github.com/eclipse-emf/org.eclipse.emf/issues/79 and https://github.com/eclipse-ocl/org.eclipse.ocl/issues/2382.
 */
public class IdiomsExecutableExtensionFactoryOverload extends IdiomsExecutableExtensionFactory
{
	@Override
	public Object create() throws CoreException {
		if (EcorePlugin.IS_ECLIPSE_RUNNING) {
			return super.create();
		}
		Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
		Object object = extensionToFactoryMap.get("idioms");
		if (object instanceof Resource.Factory.Descriptor) {	// OOPS EcorePlugin.ExtensionProcessor has installed an object requiring OSGI getBundle() support.
			new IdiomsStandaloneSetup().register(getInjector());
			object = extensionToFactoryMap.get("idioms");
		}
		return object;
	}

	@Override
	protected Injector getInjector() {
		Injector injector = super.getInjector();
		if (injector == null) {
			injector = IdiomsStandaloneSetup.getInjector();
		}
		return injector;
	}
}
