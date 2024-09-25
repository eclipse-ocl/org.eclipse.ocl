/*******************************************************************************
 * Copyright (c) 2014, 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.xtext.resource.ClassloaderClasspathUriResolver;
import org.eclipse.xtext.resource.ClasspathUriUtil;

/**
 * ClasspathURIHandler may be installed by code such as
 * <p>
 * 	resourceSet.getURIConverter().getURIHandlers().add(0, new ClasspathURIHandler());
 * <p>
 * to rectify the missing support for the classpath: protocol in Xtext (Bug 446073).
 */
public class ClasspathURIHandler extends URIHandlerImpl
{
	public static void init(@NonNull ResourceSet resourceSet) {
		URIConverter uriConverter = resourceSet.getURIConverter();
		synchronized (uriConverter) {
			EList<URIHandler> uriHandlers = uriConverter.getURIHandlers();
			URIHandler classpathURIHandler = null;
			int index = 0;
			for (URIHandler uriHandler : uriHandlers) {
				if (uriHandler instanceof ClasspathURIHandler) {
					classpathURIHandler = uriHandler;
					break;
				}
				index++;
			}
			if (classpathURIHandler == null) {
				classpathURIHandler = new ClasspathURIHandler();
				uriHandlers.add(0, classpathURIHandler);
			}
			else if (index != 0) {
				uriHandlers.move(0, index);
			}
		}
	}

	private Logger log = Logger.getLogger(getClass());

	private final @NonNull ClassloaderClasspathUriResolver resolver = new ClassloaderClasspathUriResolver();

	@Override
	public boolean canHandle(URI uri) {
		return ClasspathUriUtil.isClasspathUri(uri);
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		try {
			URI resolvedURI = resolver.findResourceOnClasspath(getClass().getClassLoader(), uri);
			return super.createInputStream(resolvedURI, options);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Install support in an MWE2 script.
	 * @param resourceSet
	 */
	public void setResourceSet(ResourceSet resourceSet) {
		log.info("Setup classpath URI protocol");
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		if (!uriHandlers.contains(this)) {
			uriHandlers.add(0, this);
		}
	}
}