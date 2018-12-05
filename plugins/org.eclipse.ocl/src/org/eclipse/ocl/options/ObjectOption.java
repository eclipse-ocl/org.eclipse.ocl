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

import java.lang.reflect.Field;

import org.eclipse.ocl.common.preferences.PreferenceableOption;

/**
 * Implementation of the {@link PreferenceableOption} interface for object OCL options.
 * 
 * When persisted as a preference, the representation is a Java-class name followed by a dot and
 * then the static object name. e.g. "org.eclipse.emf.ecore.EPackage$Literals.EOBJECT" or "null".
 *
 * @since 3.2
 */
public class ObjectOption<T> extends BasicOption<T> implements PreferenceableOption<T>
{
	protected final Class<?> classType;
	
	public ObjectOption(String pluginId, String key, T defaultValue, Class<? extends T> classType) {
		super(pluginId, key, defaultValue);
		this.classType = classType;
	}

	@SuppressWarnings("unchecked")
	public T getValueOf(String string) {
		if (string == null) {
			return null;
		}
		int index = string.lastIndexOf("."); //$NON-NLS-1$
		if (index <= 0) {
			return null;
		}
		String className = string.substring(0, index);
		String instanceName = string.substring(index+1);
		try {
			ClassLoader classLoader = classType.getClassLoader();
			if (classLoader == null) {					// May be null for java.lang.Object
				classLoader = getClass().getClassLoader();
			}
			Class<?> loadClass = classLoader.loadClass(className);
			if (loadClass == null) {
				return null;
			}
			Field field = loadClass.getField(instanceName);
			return (T) field.get(null);
		} catch (Exception e) {
//			e.printStackTrace();
			return getDefaultValue();
		}
	}		
}