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
package org.eclipse.ocl.pivot.internal.resource;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.RootXMLContentHandlerImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.library.RegisteredContribution;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.resource.ASResource;

/**
 * The <b>Resource Factory</b> for the pivot and extended pivot abstract syntax.
 */
public class OCLASResourceFactory extends AbstractASResourceFactory
{
	/**
	 * @since 1.5
	 */
	public static class ASRegistry<@NonNull C extends RegisteredContribution<C>> //implements RegisteredContribution.Registry<C>
	{
		private final @NonNull Map<@NonNull URI, C>  map = new HashMap<>();

		//		@Override
		public @Nullable C get(@NonNull URI key) {
			@Nullable C contribution = map.get(key);
			return contribution != null ? contribution.getContribution() : null;
		}

		//		@Override
		public @Nullable C put(@NonNull URI key, @NonNull C contribution) {
			return map.put(key, contribution);
		}

		//		@Override
		public @Nullable C remove(@NonNull URI key) {
			return map.remove(key);
		}

		//		@Override
		public int size() {
			return map.size();
		}
	}

	private static @Nullable OCLASResourceFactory CONTENT_TYPE_INSTANCE = null;
	/**
	 * @since 1.5
	 */
	public static @NonNull ASRegistry<@NonNull StandardLibraryContribution> REGISTRY = new ASRegistry<@NonNull StandardLibraryContribution>();

	public static synchronized @NonNull OCLASResourceFactory getInstance() {
		if (CONTENT_TYPE_INSTANCE == null) {
			CONTENT_TYPE_INSTANCE = getInstances(PivotPackage.eCONTENT_TYPE, ASResource.FILE_EXTENSION, null,
				OCLASResourceFactory.class);
		}
		assert CONTENT_TYPE_INSTANCE != null;
		return CONTENT_TYPE_INSTANCE;
	}

	private static final @NonNull ContentHandler PIVOT_CONTENT_HANDLER = new RootXMLContentHandlerImpl(
		ASResource.CONTENT_TYPE, new String[]{ASResource.FILE_EXTENSION},
		RootXMLContentHandlerImpl.XMI_KIND, PivotPackage.eNS_URI, null);

	static {
		installContentHandler(ContentHandler.Registry.NORMAL_PRIORITY, PIVOT_CONTENT_HANDLER);
	}

	/**
	 * Creates an instance of the resource factory.
	 */
	public OCLASResourceFactory() {
		super(ASResource.CONTENT_TYPE, ASResource.FILE_EXTENSION);
	}

	@Override
	public Resource createResource(URI uri) {
		assert uri != null;
		//
		//	If it's a *.oclas suffixed standard library or metamodel, return the registered AS resource.
		//
		StandardLibraryContribution standardLibraryContribution = REGISTRY.get(uri);
		if (standardLibraryContribution != null) {
			return standardLibraryContribution.getResource();
		}
		return super.createResource(uri);
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return getInstance();
	}
}
