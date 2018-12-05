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
package org.eclipse.ocl.common.delegate;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.common.internal.preferences.StringPreference;

/**
 * VirtualDelegateMapping provides the preference option to support mapping of the virtual delegate URI
 * {@link OCLConstants#OCL_DELEGATE_URI} (http://www.eclipse.org/emf/2002/Ecore/OCL) to a physical delegate URI such as 
 * http://www.eclipse.org/emf/2002/Ecore/OCL/LPG or http://www.eclipse.org/emf/2002/Ecore/OCL/Pivot.
 */
public class VirtualDelegateMapping extends StringPreference
{
	public static VirtualDelegateMapping getRegistry(/*@NonNull*/ EModelElement modelElement) {
		VirtualDelegateMapping registry = DelegateResourceSetAdapter.getRegistry(
			modelElement, VirtualDelegateMapping.class, CommonOptions.DEFAULT_DELEGATION_MODE);
		return registry;
	}
	
	public VirtualDelegateMapping(/*@NonNull*/ String pluginId, /*@NonNull*/ String key, /*@Nullable*/ String defaultValue) {
		super(pluginId, key, defaultValue);
	}

	public /*@NonNull*/ String resolve(/*@NonNull*/ String delegateURI) {
		if (OCLConstants.OCL_DELEGATE_URI.equals(delegateURI)) {
			String defaultValue = getPreferredValue();
			if (defaultValue != null) {
				return defaultValue;
			}
		}
		return delegateURI;
	}
}