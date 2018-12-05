/*******************************************************************************
 * Copyright (c) 2017, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.tests;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.resource.BasicProjectManager;

/**
 * A TestProjectManager extends the gloabl ProjectManager with its knowledge of all projects on the
 * classpath to add a temporary test project created for the writeable files.
 */
public class TestProjectManager extends BasicProjectManager
{
	private final @NonNull URI platformResourceURI;
	private final @NonNull URI platformPluginURI;
	private final @NonNull URI fileURI;

	public TestProjectManager(@NonNull TestProject testProject) {
		String projectName = testProject.getURI().lastSegment();
		assert projectName != null;
		String pathName = "/" + projectName + "/";
		this.platformResourceURI = URI.createPlatformResourceURI(pathName, true);
		this.platformPluginURI = URI.createPlatformPluginURI(pathName, true);
		String fileString = testProject.getFile().toString();
		if (!fileString.endsWith("/")) {
			fileString = fileString + "/";
		}
		this.fileURI = URI.createFileURI(fileString);
	}

	@Override
	public IPackageDescriptor getPackageDescriptor(@NonNull URI ecoreURI) {
		return CLASS_PATH.getPackageDescriptor(ecoreURI);
	}

	@Override
	public @Nullable IResourceDescriptor getResourceDescriptor(@NonNull URI uri) {
		return CLASS_PATH.getResourceDescriptor(uri);
	}

	@Override
	public void initializeResourceSet(@Nullable ResourceSet resourceSet) {
		if (resourceSet != null) {
			Map<URI, URI> uriMap = resourceSet.getURIConverter().getURIMap();
			uriMap.put(platformResourceURI, fileURI);
			uriMap.put(platformPluginURI, fileURI);
		}
		CLASS_PATH.initializeResourceSet(resourceSet);
		if (resourceSet != null) {
			List<Adapter> eAdapters = resourceSet.eAdapters();
			if (eAdapters.contains(CLASS_PATH)) {
				eAdapters.remove(this);
			}
			if (!eAdapters.contains(this)) {
				eAdapters.add(this);
			}
		}
	}
}