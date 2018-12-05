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
 * Implementation of the {@link PreferenceableOption} interface for boolean OCL options.
 *
 * @since 3.2
 */
public class BooleanOption extends BasicOption<Boolean> implements PreferenceableOption<Boolean>
{
	public BooleanOption(String pluginId, String key, Boolean defaultValue) {
		super(pluginId, key, defaultValue);
	}

	public Boolean getValueOf(String string) {
		return Boolean.valueOf(string);
	}		
}