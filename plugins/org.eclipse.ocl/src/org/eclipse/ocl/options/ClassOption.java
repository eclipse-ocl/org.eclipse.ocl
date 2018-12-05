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
 * Implementation of the {@link PreferenceableOption} interface for class OCL options.
 * 
 * When persisted as a preference, the representation is a Java-class name.
 * e.g. "org.eclipse.uml2.uml.Association".
 *
 * @since 3.2
 */
public class ClassOption<T> extends BasicOption<Class<? extends T>> implements PreferenceableOption<Class<? extends T>>
{
	protected final Class<? extends T> classType;
	
	public ClassOption(String pluginId, String key, Class<? extends T> defaultValue, Class<? extends T> classType) {
		super(pluginId, key, defaultValue);
		this.classType = classType;
	}

	@SuppressWarnings("unchecked")
	public Class<? extends T> getValueOf(String string) {
		if ((string == null) || (string.length() <= 0)) {
			return null;
		}
		try {
			ClassLoader classLoader = classType.getClassLoader();
			if (classLoader == null) {					// May be null for java.lang.Object
				classLoader = getClass().getClassLoader();
			}
			return (Class<T>) classLoader.loadClass(string);
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			return getDefaultValue();
		}
	}		
}