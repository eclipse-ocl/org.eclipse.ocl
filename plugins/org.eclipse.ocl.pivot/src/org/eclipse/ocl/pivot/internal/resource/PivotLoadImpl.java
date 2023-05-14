/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMILoadImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.utilities.Orphanage;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 *  PivotLoadImpl intercepts the load of orphans to delay their installation until
 *  they are sufficiently characterised to be installed.
 */
public final class PivotLoadImpl extends XMILoadImpl
{
	public PivotLoadImpl(@NonNull ASResource asResource) {
		super(new XMIHelperImpl(asResource));
	}

	protected void installOrphans(/*@NonNull*/ ASResource asResource) {
		assert asResource != null;
		ResourceSet asResourceSet = asResource.getResourceSet();
		assert asResourceSet != null;
		Orphanage sharedOrphanage = Orphanage.basicGetSharedOrphanage(asResourceSet);
		if (sharedOrphanage != null) {			// If loaded orphans needs integrating with a shared orphanage
			sharedOrphanage.installLoadedClasses(asResource);
		}
	}

	@Override
	public void load(XMLResource resource, InputStream inputStream, Map<?, ?> options) throws IOException {
		super.load(resource, inputStream, options);
		installOrphans((ASResource)resource);
	}

	@Override
	public void load(XMLResource resource, InputSource inputSource, Map<?, ?> options) throws IOException {
		super.load(resource, inputSource, options);
		installOrphans((ASResource)resource);
	}

	@Override
	public void load(XMLResource resource, Node node, Map<?, ?> options) throws IOException {
		super.load(resource, node, options);
		installOrphans((ASResource)resource);
	}
}