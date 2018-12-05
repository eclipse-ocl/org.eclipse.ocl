/*******************************************************************************
 * Copyright (c) 2015, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.jdt.annotation.NonNull;

/**
 * A NoHttpURIHandlerImpl traps attempts to access http: resources over the Internet since such accesses
 * are not required in test applications. This therefore diagnoses outroght bugs or at least poor performance.
 */
public final class NoHttpURIHandlerImpl extends URIHandlerImpl
{
	public static final @NonNull URIHandler INSTANCE = new NoHttpURIHandlerImpl();

	public static void install(@NonNull ResourceSet resourceSet) {
		EList<URIHandler> uriHandlers = resourceSet.getURIConverter().getURIHandlers();
		int index = uriHandlers.indexOf(INSTANCE);
		if (index > 0) {
			uriHandlers.move(0, index);
		}
		else if (index < 0) {
			uriHandlers.add(0, INSTANCE);
		}
	}

	@Override
	public boolean canHandle(URI uri) {
		return "http".equals(uri.scheme());
	}

	@Override
	public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
		throw new IOException("Resolution of '" + uri + "' over network trapped by use of " + getClass().getName());
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		throw new IOException("Resolution of '" + uri + "' over network trapped by use of " + getClass().getName());
	}
}