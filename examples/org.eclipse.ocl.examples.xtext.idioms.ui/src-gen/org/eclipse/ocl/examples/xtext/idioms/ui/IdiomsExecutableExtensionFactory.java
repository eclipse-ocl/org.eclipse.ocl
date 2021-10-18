/*******************************************************************************
 * Copyright (c) 2011, 2021 Willink Transformations and others.
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
package org.eclipse.ocl.examples.xtext.idioms.ui;

import com.google.inject.Injector;
import org.eclipse.ocl.examples.xtext.idioms.ui.internal.IdiomsActivator;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass.
 */
public class IdiomsExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FrameworkUtil.getBundle(IdiomsActivator.class);
	}

	@Override
	protected Injector getInjector() {
		IdiomsActivator activator = IdiomsActivator.getInstance();
		return activator != null ? activator.getInjector(IdiomsActivator.ORG_ECLIPSE_OCL_EXAMPLES_XTEXT_IDIOMS_IDIOMS) : null;
	}

}
