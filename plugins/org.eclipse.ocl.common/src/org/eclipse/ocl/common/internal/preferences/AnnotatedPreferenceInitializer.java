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
package org.eclipse.ocl.common.internal.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.ocl.common.preferences.PreferenceableOption;

/**
 * Abstract support for initialization of default preference values.
 */
public abstract class AnnotatedPreferenceInitializer extends AbstractPreferenceInitializer
{
	@SuppressWarnings("deprecation")
	private static final DefaultScope DEFAULT_SCOPE_INSTANCE = new DefaultScope();	// DefaultScope.INSTANCE not available for Galileo

	protected void putPreference(PreferenceableOption<?> preference) {
		String qualifier = preference.getPluginId();
		if (qualifier != null) {
			IScopeContext context = DEFAULT_SCOPE_INSTANCE;
			IEclipsePreferences node = context.getNode(qualifier);
			if (node != null) {
				Object defaultValue = preference.getDefaultValue();
				String defaultString = defaultValue != null ? defaultValue.toString() : ""; //$NON-NLS-1$
				node.put(preference.getKey(), defaultString);
			}
		}
	}
}
