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
package org.eclipse.ocl.xtext.completeocl.ui.internal;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.util.Collections;
import java.util.Map;
import org.apache.log4j.Logger;
import org.eclipse.ocl.xtext.completeocl.CompleteOCLRuntimeModule;
import org.eclipse.ocl.xtext.completeocl.ui.CompleteOCLUiModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.shared.SharedStateModule;
import org.eclipse.xtext.util.Modules2;
import org.osgi.framework.BundleContext;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass.
 */
public class CompleteOCLActivator extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "org.eclipse.ocl.xtext.completeocl.ui";
	public static final String ORG_ECLIPSE_OCL_XTEXT_COMPLETEOCL_COMPLETEOCL = "org.eclipse.ocl.xtext.completeocl.CompleteOCL";

	private static final Logger logger = Logger.getLogger(CompleteOCLActivator.class);

	private static CompleteOCLActivator INSTANCE;

	private Map<String, Injector> injectors = Collections.synchronizedMap(Maps.<String, Injector> newHashMapWithExpectedSize(1));

	@Override
	public void start(BundleContext context) throws Exception {
		super.start(context);
		INSTANCE = this;
	}

	@Override
	public void stop(BundleContext context) throws Exception {
		injectors.clear();
		INSTANCE = null;
		super.stop(context);
	}

	public static CompleteOCLActivator getInstance() {
		return INSTANCE;
	}

	public Injector getInjector(String language) {
		synchronized (injectors) {
			Injector injector = injectors.get(language);
			if (injector == null) {
				injectors.put(language, injector = createInjector(language));
			}
			return injector;
		}
	}

	protected Injector createInjector(String language) {
		try {
			com.google.inject.Module runtimeModule = getRuntimeModule(language);
			com.google.inject.Module sharedStateModule = getSharedStateModule();
			com.google.inject.Module uiModule = getUiModule(language);
			com.google.inject.Module mergedModule = Modules2.mixin(runtimeModule, sharedStateModule, uiModule);
			return Guice.createInjector(mergedModule);
		} catch (Exception e) {
			logger.error("Failed to create injector for " + language);
			logger.error(e.getMessage(), e);
			throw new RuntimeException("Failed to create injector for " + language, e);
		}
	}

	protected com.google.inject.Module getRuntimeModule(String grammar) {
		if (ORG_ECLIPSE_OCL_XTEXT_COMPLETEOCL_COMPLETEOCL.equals(grammar)) {
			return new CompleteOCLRuntimeModule();
		}
		throw new IllegalArgumentException(grammar);
	}

	protected com.google.inject.Module getUiModule(String grammar) {
		if (ORG_ECLIPSE_OCL_XTEXT_COMPLETEOCL_COMPLETEOCL.equals(grammar)) {
			return new CompleteOCLUiModule(this);
		}
		throw new IllegalArgumentException(grammar);
	}

	protected com.google.inject.Module getSharedStateModule() {
		return new SharedStateModule();
	}


}
