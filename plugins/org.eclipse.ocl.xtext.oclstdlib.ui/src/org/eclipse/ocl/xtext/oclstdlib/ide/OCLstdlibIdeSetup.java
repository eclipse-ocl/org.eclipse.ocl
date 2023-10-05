/*******************************************************************************
 * Copyright (c) 2011, 2023 Willink Transformations and others.
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
package org.eclipse.ocl.xtext.oclstdlib.ide;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibRuntimeModule;
import org.eclipse.ocl.xtext.oclstdlib.OCLstdlibStandaloneSetup;
import org.eclipse.xtext.util.Modules2;

/**
 * Initialization support for running Xtext languages as language servers.
 */
public class OCLstdlibIdeSetup extends OCLstdlibStandaloneSetup {

	@Override
	public Injector createInjector() {
		return Guice.createInjector(Modules2.mixin(new OCLstdlibRuntimeModule(), new OCLstdlibIdeModule()));
	}

}
