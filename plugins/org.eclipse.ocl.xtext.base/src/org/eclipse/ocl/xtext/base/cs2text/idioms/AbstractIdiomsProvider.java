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
package org.eclipse.ocl.xtext.base.cs2text.idioms;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;

public abstract class AbstractIdiomsProvider implements IdiomsProvider
{
	protected /*@NonNull*/ IdiomModel getIdiomModel(@NonNull Class<?> contextClass, /*@NonNull*/ String path) {
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
		URI uri = URI.createFileURI(url.getPath());
		ResourceSet resourceSet = new ResourceSetImpl();
		IdiomsPackage.eINSTANCE.getClass();
	//	Resource resource = resourceSet.getResource(uri, true);
		Resource resource = resourceSet.createResource(uri);
		Map<Object,Object> options = new HashMap<>();
	//	options.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
		try {
			resource.load(options);
			for (Idiom idiom : ((IdiomModel)resource.getContents().get(0)).getOwnedIdioms()) {
				for (SubIdiom subIdiom : idiom.getOwnedSubIdioms()) {
					for (Segment segment : subIdiom.getSegments()) {
						segment.toString();		// XXX debugging
					}
				}
			}
			EcoreUtil.resolveAll(resourceSet);				// Avoid no-equality of proxies
			return (IdiomModel)resource.getContents().get(0);	//OPTION_DEFER_IDREF_RESOLUTION
		} catch (IOException e) {
			throw new IllegalStateException("Failed to load " + uri, e);
		}
	}

	protected /*@NonNull*/ Iterable</*@NonNull*/ Idiom> getIdioms(/*@NonNull*/ IdiomModel rootIdiomModel) {
		List<Idiom> allIdioms = new ArrayList<>();
		if (rootIdiomModel != null) {
			List<IdiomModel> allIdiomModels = new ArrayList<>();
			allIdiomModels.add(rootIdiomModel);
			for (int i = 0; i < allIdiomModels.size(); i++) {
				IdiomModel idiomModel = allIdiomModels.get(i);
				allIdioms.addAll(idiomModel.getOwnedIdioms());
				for (IdiomModel importedIdiomModel : idiomModel.getImports()) {
					if (!allIdiomModels.contains(importedIdiomModel)) {
						allIdiomModels.add(importedIdiomModel);
					}
				}
			}
		}
		return allIdioms;
	}

}
