/*******************************************************************************
 * Copyright (c) 2013, 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.uml25;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.RootXMLContentHandlerImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.uml2.uml.internal.resource.XMI2UMLResourceFactoryImpl;
import org.eclipse.uml2.uml.resource.CMOF2UMLResourceHandler;
import org.eclipse.uml2.uml.resource.XMI2UMLResource;

/**
 * XMI252UMLResourceFactoryImpl supports loading all OMG UML 2.5 family XMI files as Eclipse UML resources.
 * <p>
 * install() should be invoked to initialize a ResourceSet with the locations of the UML 2.5 XMI files.
 * <p>
 * Thereafter ReseourceSet.getResource(...) should work.
 *
 */
@SuppressWarnings("restriction")
public class XMI252UMLResourceFactoryImpl extends XMI2UMLResourceFactoryImpl implements XMI2UMLResource.Factory
{
	private static final String MYUML_2_5_CONTENT_TYPE_IDENTIFIER = "org.omg.myuml_2_5"; //$NON-NLS-1$
	public static final String MYUML_METAMODEL_2_5_NS_URI = "http://www.omg.org/spec/UML/20131001";

	private static final ContentHandler MYOMG_2_5_CONTENT_HANDLER = new RootXMLContentHandlerImpl(
			MYUML_2_5_CONTENT_TYPE_IDENTIFIER, new String[]{XMI2UMLResource.FILE_EXTENSION},
		RootXMLContentHandlerImpl.XMI_KIND, MYUML_METAMODEL_2_5_NS_URI, null);

	public static @NonNull Map<URI, URI> install(@NonNull ResourceSet resourceSet, @NonNull URI modelFolderURI) {
		URIConverter uriConverter = resourceSet.getURIConverter();
		List<ContentHandler> contentHandlers = uriConverter.getContentHandlers();
		if (!contentHandlers.contains(MYOMG_2_5_CONTENT_HANDLER)) {
			contentHandlers.add(0, MYOMG_2_5_CONTENT_HANDLER);
		}
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().put(
				MYUML_2_5_CONTENT_TYPE_IDENTIFIER, new XMI252UMLResourceFactoryImpl());
		Map<URI, URI> extraURImap = new HashMap<URI, URI>();
		extraURImap.put(URI.createURI("http://www.omg.org/spec/DC/20131001/"), modelFolderURI);
		extraURImap.put(URI.createURI("http://www.omg.org/spec/DD/20131001/"), modelFolderURI);
		extraURImap.put(URI.createURI("http://www.omg.org/spec/UML/20131001/"), modelFolderURI);
		return extraURImap;
	}

	public XMI252UMLResourceFactoryImpl() {
	}

	@Override
	public Resource createResource(URI uri) {
		XMI2UMLResource resource = (XMI2UMLResource) createResourceGen(uri);

		ExtendedMetaData extendedMetaData = new XMI252UMLExtendedMetaData(EPackage.Registry.INSTANCE);

		Map<Object, Object> defaultSaveOptions = resource.getDefaultSaveOptions();

		defaultSaveOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		defaultSaveOptions.put(XMLResource.OPTION_SAVE_TYPE_INFORMATION, Boolean.TRUE);

		Map<Object, Object> defaultLoadOptions = resource.getDefaultLoadOptions();

		defaultLoadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, extendedMetaData);
		defaultLoadOptions.put(XMLResource.OPTION_RESOURCE_HANDLER, new CMOF2UMLResourceHandler(null));
		defaultLoadOptions.put(XMLResource.OPTION_LAX_FEATURE_PROCESSING, Boolean.TRUE);

		return resource;
	}
}