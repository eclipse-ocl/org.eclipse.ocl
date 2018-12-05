/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.options;

import org.eclipse.ocl.common.preferences.PreferenceableOption;

/**
 * Implementation of the {@link PreferenceableOption} interface for enum OCL options.
 *
 * @since 3.2
 */
public class EnumeratedOption<T extends Enum<T>> extends BasicOption<T> implements PreferenceableOption<T>
{
	protected final Class<T> enumType;
	
	public EnumeratedOption(String pluginId, String key, T defaultValue, Class<T> enumType) {
		super(pluginId, key, defaultValue);
		this.enumType = enumType;
	}

	public final Class<T> getEnumType() {
		return enumType;
	}		

	public T getValueOf(String string) {
		if (string == null) {
			return null;
		}
		return Enum.valueOf(enumType, string);
	}		
}