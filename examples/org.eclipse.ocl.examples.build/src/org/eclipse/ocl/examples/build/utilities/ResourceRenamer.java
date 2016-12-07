/*******************************************************************************
 * Copyright (c) 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;

/**
 * Renames a Resource in a designated <tt>modelSlot</tt> by changing its URI to a specified <tt>uri</tt> and
 * by registering the new URO with its ResourceSet so that references to the new URI resolve to the Resource.
 */
public class ResourceRenamer extends WorkflowComponentWithModelSlot
{
	private Logger log = Logger.getLogger(getClass());
	protected String uri;

	public String getUri() {
		return uri;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		URI fileURI = URI.createPlatformResourceURI(uri, true);
		log.info("Renaming as '" + fileURI + "'");
		Resource resource = (Resource) ctx.get(getModelSlot());
		resource.setURI(fileURI);
		Map<URI, Resource> uriResourceMap = ((ResourceSetImpl)resource.getResourceSet()).getURIResourceMap();
		uriResourceMap.put(fileURI, resource);
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
