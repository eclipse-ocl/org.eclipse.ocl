/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
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
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl;
import org.eclipse.jdt.annotation.NonNull;

/**
 * The Installer provides a self0standing main() application that can be run to add the OMG 20131001 family declarations
 * to the current workspace.
 *
 * Beware this is bad practice since it changes the workspace for all other applications. Only reverts on restart.
 */
public class Installer
{
	/**
	 * Following registrations for use in this local workspace should duplicate those in plugin.xml
	 * for use in a nested workspace.
	 */
	public static @NonNull Map<URI, URI> getExtraURImap(@NonNull URI modelFolderURI) {
		Map<URI, URI> extraURImap = new HashMap<URI, URI>();
		extraURImap.put(URI.createURI("http://www.omg.org/spec/DC/20131001/"), modelFolderURI);
		extraURImap.put(URI.createURI("http://www.omg.org/spec/DD/20131001/"), modelFolderURI);
		extraURImap.put(URI.createURI("http://www.omg.org/spec/UML/20131001/"), modelFolderURI);
		return extraURImap;
	}

	/**
	 * Main program installs registrations
	 */
	public static void main(String[] args) {
		Map<URI, URI> dynamicUriMap = new ResourceSetImpl().getURIConverter().getURIMap();
		Map<URI, URI> ststicUriMap = URIConverter.URI_MAP;
		URIMappingRegistryImpl eUriMap = URIMappingRegistryImpl.INSTANCE;
		System.out.println("pre-static" + debugUriMap(ststicUriMap));
		System.out.println("pre-e-static" + debugUriMap(eUriMap));
		System.out.println("pre-dynamic" + debugUriMap(dynamicUriMap));
		URI modelFolderURI = URI.createPlatformResourceURI("/org.eclipse.ocl.examples.uml25/model/", true);
		Map<URI, URI> extraURImap = getExtraURImap(modelFolderURI);
		ststicUriMap.putAll(extraURImap);
		System.out.println("post-static" + debugUriMap(ststicUriMap));
		System.out.println("post-e-static" + debugUriMap(eUriMap));
		System.out.println("post-dynamic" + debugUriMap(dynamicUriMap));
	}

	public static @NonNull String debugUriMap(@NonNull EMap<URI, URI> uriMap) {
		return "@" + Integer.toHexString(System.identityHashCode(uriMap)) + "[" + uriMap.size() + "]";
	}

	public static @NonNull String debugUriMap(@NonNull Map<URI, URI> uriMap) {
		return "@" + Integer.toHexString(System.identityHashCode(uriMap)) + "[" + uriMap.size() + "]";
	}
}
