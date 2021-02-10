/**
 * <copyright>
 *
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.control.util;

import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.control.ControlFactory;
import org.eclipse.ocl.control.ControlModel;

/**
 * A ControlAdapter makes a ControlModel available as a ResourceSet adapter independent of
 * whether the ControlResource makes the ContrrolModel visible as a Resource.
 */
public class ControlAdapter extends AdapterImpl
{
	/**
	 * Return the ControlAdapter associated with the eObject's ResourceSet or null if none.
	 */
	public static @Nullable ControlAdapter basicGetControlAdapter(@NonNull EObject eObject) {
		Resource resource = eObject.eResource();
		if (resource == null) {
			return null;
		}
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			return null;
		}
		for (Adapter adapter : resourceSet.eAdapters()) {
			if (adapter instanceof ControlAdapter) {
				return ((ControlAdapter)adapter);
			}
		}
		return null;
	}

	/**
	 * Return the ControlModel associated with the eObject's ResourceSet or null if none.
	 */
	public static @Nullable ControlModel basicGetControlModel(@NonNull EObject eObject) {
		ControlAdapter controlAdapter = basicGetControlAdapter(eObject);
		return controlAdapter != null ? controlAdapter.getControlModel() : null;
	}

	/**
	 * Return the ControlAdapter associated with the eObject's ResourceSet.
	 */
	public static @NonNull ControlAdapter getControlAdapter(@NonNull EObject eObject) {
		Resource resource = eObject.eResource();
		if (resource == null) {
			throw new IllegalStateException("Resource required.");
		}
		ResourceSet resourceSet = resource.getResourceSet();
		if (resourceSet == null) {
			throw new IllegalStateException("ResourceSet required.");
		}
		List<Adapter> eAdapters = resourceSet.eAdapters();
		for (Adapter adapter : eAdapters) {
			if (adapter instanceof ControlAdapter) {
				return ((ControlAdapter)adapter);
			}
		}
		ControlModel controlModel = ControlFactory.eINSTANCE.createControlModel();
		controlModel.setSettings(ControlFactory.eINSTANCE.createControlSettings());
		ControlAdapter controlAdapter = new ControlAdapter(controlModel);
		eAdapters.add(controlAdapter);
		return controlAdapter;
	}

	/**
	 * Return the ControlModel associated with the eObject's ResourceSet.
	 */
	public static @Nullable ControlModel getControlModel(@NonNull EObject eObject) {
		return getControlAdapter(eObject).getControlModel();
	}

	protected final @NonNull ControlModel controlModel;

	public ControlAdapter(@NonNull ControlModel controlModel) {
		this.controlModel = controlModel;
	}

	public @NonNull ControlModel getControlModel() {
		return controlModel;
	}
}
