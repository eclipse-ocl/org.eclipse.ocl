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

/**
 * A configurable preference with an Enumeration value.
 */
public class EnumerationPreference<T extends Enum<T>> extends Preference<T>
{
	public final Class<T> type;

	public EnumerationPreference(String pluginId, String key, T defaultValue, Class<T> type) {
		super(pluginId, key, defaultValue);
		this.type = type;
	}

	public T getValueOf(String string) {
		if (string == null) {
			return null;
		}
		return Enum.valueOf(type, string);
	}		
}
