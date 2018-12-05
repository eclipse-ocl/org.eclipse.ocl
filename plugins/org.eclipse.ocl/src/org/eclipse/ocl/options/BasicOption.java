/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.options;




/**
 * Useful implementation of the {@link Option} interface for OCL parsing options.
 * 
 * @author Christian W. Damus (cdamus)
 */
public class BasicOption<T> implements Option<T> {
	private final String pluginId;
	private final String key;
	private final T defaultValue;
	
	/**
	 * Initializes me with my key and default value for an unspecified plugin.
	 * 
	 * @param key identifies me
	 * @param defaultValue my default value
	 */
	public BasicOption(String key, T defaultValue) {
		this(null, key, defaultValue);
	}
	
	/**
	 * Initializes me with my plugin-qualified key and default value.
	 * 
	 * @param pluginId identifies my plugin
	 * @param key identifies me
	 * @param defaultValue my default value
	 * 
	 * @since 3.2
	 */
	public BasicOption(String pluginId, String key, T defaultValue) {
		this.pluginId = pluginId;
		this.key = key;
		this.defaultValue = defaultValue;
	}
	
	public final String getKey() {
		return key;
	}

	/**
	 * @since 3.2
	 */
	public String getPluginId() {
		return pluginId;
	}
	
	@Override
	public String toString() {
		return String.valueOf(pluginId) + " / " + String.valueOf(key) + "[" + String.valueOf(defaultValue) + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}
	
	public final T getDefaultValue() {
		return defaultValue;
	}
}
