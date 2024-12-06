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
package org.eclipse.ocl.xtext.markup.ui;

import com.google.inject.Injector;
import org.eclipse.ocl.xtext.markup.ui.internal.MarkupActivator;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass.
 */
public class MarkupExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FrameworkUtil.getBundle(MarkupActivator.class);
	}

	@Override
	protected Injector getInjector() {
		MarkupActivator activator = MarkupActivator.getInstance();
		return activator != null ? activator.getInjector(MarkupActivator.ORG_ECLIPSE_OCL_XTEXT_MARKUP_MARKUP) : null;
	}

}
