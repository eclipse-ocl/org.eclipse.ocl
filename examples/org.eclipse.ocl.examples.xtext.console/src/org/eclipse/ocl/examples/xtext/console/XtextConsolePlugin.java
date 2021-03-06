/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.xtext.console;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.xtext.essentialocl.ui.internal.EssentialOCLActivator;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLPlugin;

import com.google.inject.Module;

/**
 * The main plugin class to be used in the desktop.
 */
public class XtextConsolePlugin extends EssentialOCLActivator
{
	public static final @NonNull String PLUGIN_ID = "org.eclipse.ocl.examples.xtext.console";

	// The shared instance.
	private static XtextConsolePlugin plugin;

	/**
	 * Returns the shared instance.
	 */
	public static XtextConsolePlugin getInstance() {
		return plugin;
	}

	/**
	 * Obtains my plug-in ID.
	 *
	 * @return my plug-in ID
	 */
	public static String getPluginId() {
		return getInstance().getBundle().getSymbolicName();
	}

	/**
	 * The constructor.
	 */
	public XtextConsolePlugin() {
		super();
		plugin = this;
	}

	@Override
	protected Module getRuntimeModule(String grammar) {
		if (EssentialOCLPlugin.LANGUAGE_ID.equals(grammar)) {
		  return new XtextConsoleRuntimeModule();
		}

		throw new IllegalArgumentException(grammar);
	}

	@Override
	protected Module getUiModule(String grammar) {
		if (EssentialOCLPlugin.LANGUAGE_ID.equals(grammar)) {
		  return new XtextConsoleUiModule(this);
		}

		throw new IllegalArgumentException(grammar);
	}
}
