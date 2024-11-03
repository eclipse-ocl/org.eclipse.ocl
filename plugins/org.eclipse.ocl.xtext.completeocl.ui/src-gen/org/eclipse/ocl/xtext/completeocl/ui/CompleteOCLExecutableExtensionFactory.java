/*******************************************************************************
 * Copyright (c) 2011, 2024 Willink Transformations and others.
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
package org.eclipse.ocl.xtext.completeocl.ui;

import com.google.inject.Injector;
import org.eclipse.ocl.xtext.completeocl.ui.internal.CompleteOCLActivator;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass.
 */
public class CompleteOCLExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FrameworkUtil.getBundle(CompleteOCLActivator.class);
	}

	@Override
	protected Injector getInjector() {
		CompleteOCLActivator activator = CompleteOCLActivator.getInstance();
		return activator != null ? activator.getInjector(CompleteOCLActivator.ORG_ECLIPSE_OCL_XTEXT_COMPLETEOCL_COMPLETEOCL) : null;
	}

}
