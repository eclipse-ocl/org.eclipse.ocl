/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.Map;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.xmi.impl.RootXMLContentHandlerImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.internal.ecore.EcoreASResourceFactory;
import org.eclipse.ocl.pivot.internal.library.StandardLibraryContribution;
import org.eclipse.ocl.pivot.resource.ASResource;

/**
 * The <b>Resource Factory</b> for the pivot and extended pivot abstract syntax.
 */
public class OCLASResourceFactory extends AbstractASResourceFactory
{
	private static @Nullable OCLASResourceFactory INSTANCE = null;

	public static synchronized @NonNull OCLASResourceFactory getInstance() {
		if (INSTANCE == null) {
			Map<String, Object> extensionToFactoryMap = Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap();
			Object object = extensionToFactoryMap.get(ASResource.FILE_EXTENSION);
			if (object instanceof Resource.Factory.Descriptor) {
				INSTANCE = (OCLASResourceFactory) ((Resource.Factory.Descriptor)object).createFactory();	// Create the registered singleton
			}
			else {
				INSTANCE = new OCLASResourceFactory();														// Create our own singleton
			}
			assert INSTANCE != null;
			INSTANCE.install(null,  null);
		}
		assert INSTANCE != null;
		return INSTANCE;
	}

	private static final @NonNull ContentHandler PIVOT_CONTENT_HANDLER = new RootXMLContentHandlerImpl(
		ASResource.CONTENT_TYPE, new String[]{ASResource.FILE_EXTENSION},
		RootXMLContentHandlerImpl.XMI_KIND, PivotPackage.eNS_URI, null);

	static {
		installContentHandler(ContentHandler.Registry.NORMAL_PRIORITY, PIVOT_CONTENT_HANDLER);
	}

	/**
	 * The private URIConverter avoids installing the (Standalone)PlatformURIHandlerImpl in a global URIConverter.
	 *
	 * This functionality might be resolveable via the URIMap if the ResourceFactory had access to the caller's
	 * ResourceSet and so the caller's URIMap.
	 */
	private @NonNull URIConverter uriConverter = new ExtensibleURIConverterImpl();

	/**
	 * Creates an instance of the resource factory.
	 */
	public OCLASResourceFactory() {
		super(ASResource.CONTENT_TYPE);
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			StandalonePlatformURIHandlerImpl.install(uriConverter, null);
		}
		else {
			PlatformURIHandlerImpl.install(uriConverter);
		}
	}

	@Override
	public void configure(@NonNull ResourceSet resourceSet) {
		Resource.Factory.Registry resourceFactoryRegistry = resourceSet.getResourceFactoryRegistry();
		resourceFactoryRegistry.getExtensionToFactoryMap().put(ASResource.FILE_EXTENSION, this);
	}

	@Override
	public Resource createResource(URI uri) {
		URI nonASuri = uri.trimFileExtension();
		String oclasExtension = nonASuri.fileExtension();
		ASResourceFactory asResourceFactory = ASResourceFactoryRegistry.INSTANCE.getASResourceFactoryForExtension(oclasExtension);
		//		String fileExtension = uri.fileExtension();
		//		if (fileExtension == null) {			// Must be an Ecore Package registration
		//			return EcoreASResourceFactory.INSTANCE.createResource(uri);
		//		}
		if ((asResourceFactory == null) || !nonASuri.isFile()) {					// If it's not a known double extension
			//
			//	If *.xxxas exists use it.
			//
			if (uriConverter.exists(uri, null)) {	// NB this expects a (Standalone)PlatformURIHandlerImpl to be installed
				return super.createResource(uri);
			}
		}
		//
		//	Otherwise trim *.xxxas and create a *.xxxas by converting the trimmed resource to XXX AS.
		//
		@SuppressWarnings("null")@NonNull String nonASuriString = nonASuri.toString();
		StandardLibraryContribution standardLibraryContribution = StandardLibraryContribution.REGISTRY.get(nonASuriString);
		if (standardLibraryContribution != null) {
			return standardLibraryContribution.getResource();
		}
		if (asResourceFactory == null) {			// Must be an Ecore Package registration possibly with a confusing 'extension'
			asResourceFactory = EcoreASResourceFactory.getInstance();
		}
		assert !(asResourceFactory instanceof OCLASResourceFactory);
		return asResourceFactory.createResource(uri);
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return getInstance();
	}
}
