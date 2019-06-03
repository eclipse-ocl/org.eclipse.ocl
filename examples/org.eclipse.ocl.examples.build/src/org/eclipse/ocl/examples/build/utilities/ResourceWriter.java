/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.resource.ProjectManager;
import org.eclipse.ocl.pivot.resource.ProjectManager.IResourceDescriptor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

/**
 * Writes a designated <tt>modelSlot</tt> to a specified <tt>uri</tt>.
 */
public class ResourceWriter extends WorkflowComponentWithModelSlot
{
	private Logger log = Logger.getLogger(getClass());
	private ResourceSet resourceSet = null;
	private String uri;
	private String contentTypeIdentifier = null;

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	public String getContentTypeIdentifier() {
		return contentTypeIdentifier;
	}

	protected Map<Object, Object> getSaveOptions() {
		Map<Object, Object> result = XMIUtil.createSaveOptions();
		result.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		return result;
	}

	public String getUri() {
		return uri;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		ResourceSet resourceSet = ClassUtil.nonNullState(getResourceSet());
		Resource inputResource = (Resource) ctx.get(getModelSlot());
		try {
			Map<Object, Object> saveOptions = getSaveOptions();
			if (uri != null) {
				URI fileURI = URI.createPlatformResourceURI(uri, true);
				log.info("Writing '" + fileURI + "'");
				IResourceDescriptor resourceDescriptor = null;
				ProjectManager projectManager = StandaloneProjectMap.findAdapter(resourceSet);
				if (projectManager != null) {
					resourceDescriptor = projectManager.getResourceDescriptor(fileURI);
					if (resourceDescriptor != null) {
						resourceDescriptor.unload(resourceSet);
						resourceDescriptor.configure(resourceSet,
							StandaloneProjectMap.CreateStrategy.INSTANCE,
							StandaloneProjectMap.MapToFirstConflictHandlerWithLog.INSTANCE);
					}
				}
				Resource saveResource = resourceSet.createResource(fileURI, contentTypeIdentifier);
				Map<@NonNull EObject, @NonNull String> eObject2xmiId = null;
				if ((inputResource instanceof XMLResource) && (saveResource instanceof XMLResource)) {
					eObject2xmiId = XMIUtil.getIds((XMLResource)inputResource);
				}
				saveResource.getContents().addAll(inputResource.getContents());
				if (eObject2xmiId != null) {
					XMIUtil.setIds((XMLResource) saveResource, eObject2xmiId);
				}
				XMIUtil.retainLineWidth(saveOptions, saveResource);
				saveResource.save(saveOptions);
				inputResource.getContents().addAll(saveResource.getContents());
				saveResource.unload();
			}
			else {
				log.info("Writing '" + inputResource.getURI() + "'");
				inputResource.save(saveOptions);
			}
		} catch (IOException e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}

	public void setContentTypeIdentifier(String contentTypeIdentifier) {
		this.contentTypeIdentifier = contentTypeIdentifier;
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
