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
package org.eclipse.ocl.common.delegate;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * DelegateResourceSetAdapter extends a ResourceSet to support a registry of local
 * registries.
 */
public class DelegateResourceSetAdapter extends AdapterImpl
{	
	public static DelegateResourceSetAdapter findAdapter(ResourceSet resourceSet) {
		return (DelegateResourceSetAdapter) EcoreUtil.getAdapter(resourceSet.eAdapters(), DelegateResourceSetAdapter.class);
	}

	public static DelegateResourceSetAdapter getAdapter(/*@NonNull*/ EModelElement modelElement) {
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
	
	public static /*@NonNull*/ DelegateResourceSetAdapter getAdapter(/*@NonNull*/ ResourceSet resourceSet) {
		DelegateResourceSetAdapter adapter = (DelegateResourceSetAdapter) EcoreUtil.getAdapter(resourceSet.eAdapters(), DelegateResourceSetAdapter.class);
		if (adapter == null) {
			adapter = new DelegateResourceSetAdapter();
			resourceSet.eAdapters().add(adapter);
		}
		return adapter;
	}
	
	public static /*@Nullable*/ <T> T getRegistry(/*@NonNull*/ EModelElement modelElement, /*@NonNull*/ Class<T> registryClass, /*@Nullable*/ T defaultRegistry) {
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
		
	private Map<Class<? extends Object>, Object> registryRegistry = new HashMap<Class<? extends Object>, Object>();
	
	public <T> T getRegistry(Class<T> registryClass) {
		@SuppressWarnings("unchecked")
		T registry = (T) registryRegistry.get(registryClass);
		return registry;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return (type instanceof Class<?>) && ((Class<?>)type).isAssignableFrom(getClass());
	}

	public /*@Nullable*/ <T> T putRegistry(/*@NonNull*/ Class<T> registryClass, /*@NonNull*/ T newRegistry) {
		@SuppressWarnings("unchecked")
		T oldRegistry = (T) registryRegistry.put(registryClass, newRegistry);
		return oldRegistry;
	}

	@Override
	public void setTarget(Notifier newTarget) {
		ResourceSet resourceSet = (ResourceSet)newTarget;
		super.setTarget(resourceSet);
	}
}
