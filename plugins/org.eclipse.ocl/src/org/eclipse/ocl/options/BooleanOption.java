/*******************************************************************************
 * Copyright (c) 2011,2012 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

	@Override
	public Boolean getValueOf(String string) {
		return Boolean.valueOf(string);
	}		
}