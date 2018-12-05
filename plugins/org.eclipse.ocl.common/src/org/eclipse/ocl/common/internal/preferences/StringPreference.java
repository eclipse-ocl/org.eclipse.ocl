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
 * A configurable preference with a String value.
 */
public class StringPreference extends Preference<String>
{
	public StringPreference(/*@NonNull*/ String pluginId, /*@NonNull*/ String key, /*@Nullable*/ String defaultValue) {
		super(pluginId, key, defaultValue);
	}

	public /*@NonNull*/ String getValueOf(/*@NonNull*/ String string) {
		return string;
	}
}
