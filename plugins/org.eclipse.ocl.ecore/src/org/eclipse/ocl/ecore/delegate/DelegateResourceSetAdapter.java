/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.ecore.delegate;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * DelegateResourceSetAdapter extends a ResourceSet to support a registry of local
 * registries.
 * 
 * @since 3.0
 * @deprecated use org.eclipse.ocl.common.delegate.DelegateResourceSetAdapter
 */
@Deprecated
public class DelegateResourceSetAdapter extends org.eclipse.ocl.common.delegate.DelegateResourceSetAdapter
{
	public static DelegateResourceSetAdapter getAdapter(EModelElement modelElement) {
		Resource resource = modelElement.eResource();
		if (resource == null) {
			return null;
		}
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			return null;
		}
		return getAdapter(resourceSet);
	}
	
	public static DelegateResourceSetAdapter getAdapter(ResourceSet resourceSet) {
		DelegateResourceSetAdapter adapter = (DelegateResourceSetAdapter) EcoreUtil.getAdapter(resourceSet.eAdapters(), DelegateResourceSetAdapter.class);
		if (adapter == null) {
			adapter = new DelegateResourceSetAdapter();
			resourceSet.eAdapters().add(adapter);
		}
		return adapter;
	}
	
	public static <T> T getRegistry(EModelElement modelElement, Class<T> registryClass, T defaultRegistry) {
		Resource resource = modelElement.eResource();
		if (resource == null) {
			return defaultRegistry;
		}
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			return defaultRegistry;
		}
		DelegateResourceSetAdapter adapter = getAdapter(resourceSet);
		T registry = null;
		if (adapter != null) {
			registry = adapter.getRegistry(registryClass);
		}
		return registry != null ? registry : defaultRegistry;
	}
}
