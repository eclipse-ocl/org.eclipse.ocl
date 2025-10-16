/*******************************************************************************
 * Copyright (c) 2014, 2025 Willink Transformations and others.
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
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.xtext.resource.ClasspathUriUtil;
import org.eclipse.xtext.resource.FileNotFoundOnClasspathException;

import com.google.common.collect.Iterables;

/**
 * ClasspathURIHandler may be installed by code such as
 * <p>
 * 	resourceSet.getURIConverter().getURIHandlers().add(0, new ClasspathURIHandler());
 * <p>
 * to rectify the missing support for the classpath: protocol in Xtext (Bug 446073).
 */
public class ClasspathURIHandler extends URIHandlerImpl
{
	public static void init(@NonNull ResourceSet resourceSet, @Nullable Iterable<@NonNull ClassLoader> classLoaders) {
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
				classpathURIHandler = new ClasspathURIHandler(classLoaders);
				uriHandlers.add(0, classpathURIHandler);
			}
			else if (index != 0) {
				uriHandlers.move(0, index);
			}
		}
	}

	protected @NonNull ClassLoader @Nullable  [] classLoaders = null;
	private Logger log = Logger.getLogger(getClass());

	public ClasspathURIHandler() {
		this(null);
	}

	public ClasspathURIHandler(@Nullable Iterable<@NonNull ClassLoader> classLoaders) {
		this.classLoaders = classLoaders != null ? Iterables.toArray(classLoaders, ClassLoader.class) : null;
	}

	@Override
	public boolean canHandle(URI uri) {
		return ClasspathUriUtil.isClasspathUri(uri);
	}

	@Override
	public InputStream createInputStream(URI classpathUri, Map<?, ?> options) throws IOException {
		assert classpathUri != null;
		String pathAsString = classpathUri.path();
		if (classpathUri.hasAbsolutePath()) {
			assert pathAsString != null;
			pathAsString = pathAsString.substring(1);
		}
		URL resolvedURL = null;
		@NonNull ClassLoader[] classLoaders2 = classLoaders;
		if (classLoaders2 == null) {
			resolvedURL = getClass().getClassLoader().getResource(pathAsString);
		} else {
			for (@NonNull ClassLoader classLoader : classLoaders2) {
				resolvedURL = classLoader.getResource(pathAsString);
				if (resolvedURL != null) {
					break;
				}
			}
		}
		if (resolvedURL == null) {		// Based on ClassloaderClasspathUriResolver
			throw new FileNotFoundOnClasspathException("Couldn't find resource on classpath. URI was '" + classpathUri + "'");
		}
		URI fileUri = URI.createURI(resolvedURL.toString(), true);
		URI resolvedURI = fileUri.appendFragment(classpathUri.fragment());
		return super.createInputStream(resolvedURI, options);
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