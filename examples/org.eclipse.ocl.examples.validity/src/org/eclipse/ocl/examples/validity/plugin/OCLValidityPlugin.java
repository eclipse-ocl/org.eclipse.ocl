/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.validity.plugin;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.jdt.annotation.NonNull;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class OCLValidityPlugin extends EMFPlugin
{
	public static final String PLUGIN_ID = "org.eclipse.ocl.examples.validity"; //$NON-NLS-1$

	/**
	 * The singleton instance of the plugin.
	 */
	public static final @NonNull OCLValidityPlugin INSTANCE = new OCLValidityPlugin();

	/**
	 * Creates the singleton instance.
	 */
	private OCLValidityPlugin() {
		super(new ResourceLocator[] {});
	}

	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the Eclipse plugin singleton.
	 * 
	 * @return the plugin singleton.
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * The plugin singleton
	 */
	private static Implementation plugin;

	/**
	 * A plugin implementation that handles Ecore plugin registration.
	 * 
	 * @see #startup()
	 */
	static public class Implementation extends EclipsePlugin
	{
		/**
		 * Creates the singleton instance.
		 */
		public Implementation() {
			super();
			plugin = this;
		}

		/**
		 * Starts up this plugin by reading some extensions and populating the
		 * relevant registries.
		 * 
		 * @throws Exception
		 *             if there is a show stopping problem.
		 */
		@Override
		public void start(BundleContext context) throws Exception {
			super.start(context);
		}

		@Override
		public void stop(BundleContext context) throws Exception {
			super.stop(context);
			plugin = null;
		}
	}
}
