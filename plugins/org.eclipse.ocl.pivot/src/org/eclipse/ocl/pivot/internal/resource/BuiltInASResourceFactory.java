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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.ecore.es2as.BuiltInEcore2AS;
import org.eclipse.ocl.pivot.internal.plugin.CompletePackageIdRegistryReader;
import org.eclipse.ocl.pivot.internal.utilities.External2AS;
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
	public @NonNull ICS2AS createCS2AS(@NonNull EnvironmentFactory environmentFactory, @NonNull CSResource csResource, @NonNull ASResource asResource) {
		throw new IllegalStateException();
	}

	@Override
	public @NonNull BuiltInEcore2AS createExternal2AS(@NonNull Resource resource, @NonNull EnvironmentFactory environmentFactory) {
//		public @NonNull CompletePackageId registerCompletePackageContribution(@NonNull String metamodelName, /*@NonNull*/ EPackage ePackage) {
		URI uri = resource.getURI();
		assert uri != null;
		Model asModel = BuiltInASResourceFactory.basicGetModel(uri);
		assert asModel != null;
		return new BuiltInEcore2AS(resource, environmentFactory, asModel);		// XXX need to use MetamodelManager.uri2es2as to avoid duplication
	}

	@Override
	public @NonNull Resource createResource(URI uri) {
		assert uri != null;
		ASResource result = new BuiltInASResourceImpl(uri);
		configureResource(result);
		return result;
	}

	@Override
	public @Nullable <T extends Element> T getASElement(@NonNull EnvironmentFactory environmentFactory, @NonNull Class<T> pivotClass, @NonNull EObject eObject) {
		return environmentFactory.getMetamodelManager().getASOfEcore(pivotClass, eObject);			// XXX faster solution
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return INSTANCE;
	}

	/**
	 * @since 7.0
	 */
	@Override
	public @Nullable Element importFromResource(@NonNull EnvironmentFactory environmentFactory, @NonNull Resource ecoreResource, @Nullable URI uri) {
		BuiltInEcore2AS conversion = (BuiltInEcore2AS)External2AS.findAdapter(ecoreResource, environmentFactory);		// XXX bad cast
		if (conversion == null) {
			conversion = createExternal2AS(ecoreResource, environmentFactory);
		}
		Model pivotModel = conversion.getASModel();
		if (uri == null) {
			return pivotModel;
		}
		String uriFragment = uri.fragment();
		if (uriFragment == null) {
			return pivotModel;
		}
		EObject eObject = ecoreResource.getEObject(uriFragment);
		if (eObject == null) {
			return null;
		}
		return conversion.getCreated(Element.class, eObject);
	}
}