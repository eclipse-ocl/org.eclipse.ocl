/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.common.internal.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.common.preferences.PreferenceableOption;


/**
 * Abstract implementation of a configurable preference option. Derived classes support specific types.
 * 
 * @see BooleanPreference
 * @see ClassPreference
 * @see EnumerationPreference
 * 
 * @param <T> the type of the option's value
 */
public abstract class Preference<T> implements PreferenceableOption.PreferenceableOption2<T>
{	
	public final String pluginId;
	public final String key;
	public T defaultValue;
	private List<PreferenceableOption.Listener> listeners = null;

	public Preference(/*@NonNull*/ String pluginId, /*@NonNull*/ String key, /*@Nullable*/ T defaultValue) {
		this.pluginId = pluginId;
		this.key = key;
		this.defaultValue = defaultValue;
	}

	/**
	 * @since 1.1
	 */
	public synchronized void addListener(/*@NonNull*/ PreferenceableOption.Listener listener) {
		if (listeners == null) {
			listeners = new ArrayList<PreferenceableOption.Listener>();
	 		OCLCommon.installListener(this);
		}
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
	}

	/**
	 * @since 1.1
	 */
	public void fireChanged(String key, Object oldValue, Object newValue) {
		if (listeners != null) {
			for (PreferenceableOption.Listener listener : listeners) {
				listener.changed(key, oldValue, newValue);
			}
		}
	}

	/**
	 * Obtains my string key, which may be used for persistence in a
	 * preference store.
	 * 
	 * @return my key.  Is never <code>null</code>
	 */
	public /*@NonNull*/ String getKey() {
		return key;
	}

	public /*@NonNull*/ String getPluginId() {
		return pluginId;
	}

	/**
	 * Obtains the option's default value.
	 * 
	 * @return my default value, which default-default is <code>null</code>
	 */
	public /*@Nullable*/ T getDefaultValue() {
		return defaultValue;
	}

	/**
	 * Get the preferred value of this option. When running standalone this is the built-in default.
	 * When running in Eclipse, the built-in default may be overridden by a user preference.
	 *
	 * @since 1.1
	 */
	public /*@Nullable*/ T getPreferredValue() {
 		return OCLCommon.getPreference(this, null);
	}

	/**
	 * @since 1.1
	 */
	public synchronized void removeListener(/*@NonNull*/  PreferenceableOption.Listener listener) {
		if (listeners != null) {
			listeners.remove(listener);
		}
	}
	
	public void setDefaultValue(/*@Nullable*/ T defaultValue) {
		this.defaultValue = defaultValue;
	}
	
	@Override
	public String toString() {
		return String.valueOf(pluginId) + " / " + String.valueOf(key) + " = " + String.valueOf(defaultValue); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
