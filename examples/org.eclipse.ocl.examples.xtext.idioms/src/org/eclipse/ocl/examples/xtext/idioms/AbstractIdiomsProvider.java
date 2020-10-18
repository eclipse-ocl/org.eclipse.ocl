/**
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 */
package org.eclipse.ocl.examples.xtext.idioms;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.xtext.serializer.SerializationUtils;

public abstract class AbstractIdiomsProvider implements IdiomsProvider
{
	protected /*@NonNull*/ IdiomsModel getIdiomModel(@NonNull Class<?> contextClass, @NonNull ResourceSet resourceSet, /*@NonNull*/ String path) {
		URL url = contextClass.getResource(path);
		if (url == null) {
			throw new IllegalStateException("Failed to locate " + path + " wrt " + contextClass.getName());
		}
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			try {
				url = FileLocator.resolve(url);
			}
			catch (IOException e) {
				throw new IllegalStateException("Failed to resolve " + path + " wrt " + contextClass.getName(), e);
			}
		}
		@SuppressWarnings("null")
		@NonNull URI uri = URI.createFileURI(url.getPath());
		return getIdiomModel(resourceSet, uri);
	}

	public @NonNull IdiomsModel getIdiomModel(@NonNull ResourceSet resourceSet, @NonNull URI uri) {
		IdiomsPackage.eINSTANCE.getClass();
		Resource resource = resourceSet.getResource(uri, true);
		EcoreUtil.resolveAll(resourceSet);				// Avoid no-equality of proxies
		@SuppressWarnings("null")
		@NonNull IdiomsModel castIdiomModel = (IdiomsModel)resource.getContents().get(0);
		return castIdiomModel;
	}

	protected @NonNull Iterable<@NonNull Idiom> getIdioms(/*@NonNull*/ IdiomsModel rootIdiomModel) {
		List<@NonNull Idiom> allIdioms = new ArrayList<>();
		if (rootIdiomModel != null) {
			for (IdiomsModel idiomModel : rootIdiomModel.getIdiomsModels()) {
				allIdioms.addAll(SerializationUtils.nullFree(idiomModel.getOwnedIdioms()));
			}
		}
		return allIdioms;
	}

}
