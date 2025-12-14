/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.plugin.CompletePackageIdRegistryReader;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

/**
 * BuiltInASResourceFactory supports construction of a built-in immutable BuiltInASResource.
 *
 * @since 7.0
 */
public class BuiltInASResourceFactory extends AbstractASResourceFactory
{
	public static @NonNull BuiltInASResourceFactory INSTANCE = new BuiltInASResourceFactory();

	private static final @NonNull Map<@NonNull URI, @NonNull Object> uri2builtInModel = new HashMap<>();

    public static void addModel(@NonNull URI uri, @NonNull Model model) {
    	Object old = uri2builtInModel.put(uri, model);
		assert (old == null) || (old instanceof CompletePackageIdRegistryReader.Descriptor);
    }

	public static void addModelDescriptor(@NonNull URI packageURI, CompletePackageIdRegistryReader.@NonNull Descriptor descriptor) {
		Object old = uri2builtInModel.put(packageURI, descriptor);
		assert old == null;
	}

	public static @Nullable Model basicGetModel(@NonNull URI uri) {
		assert !CompletePackageIdRegistryReader.initialize();
		Object object = uri2builtInModel.get(uri);
//		if (object == null) {
//			return null;
//		}
		if (object instanceof CompletePackageIdRegistryReader.Descriptor) {
			return ((CompletePackageIdRegistryReader.Descriptor)object).getModel();
		}
		return (Model)object;
	}

	public static void removeModelDescriptor(@NonNull URI packageURI) {
		uri2builtInModel.remove(packageURI);
	}

	public BuiltInASResourceFactory() {
		super("built-in", null);
	}

	@Override
	public @NonNull Resource createResource(URI uri) {
		assert uri != null;
		ASResource result = new BuiltInASResourceImpl(uri);
		configureResource(result);
		return result;
	}

	@Override
	public @NonNull ICS2AS createCS2AS(@NonNull EnvironmentFactory environmentFactory, @NonNull CSResource csResource, @NonNull ASResource asResource) {
		throw new IllegalStateException();
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return INSTANCE;
	}
}