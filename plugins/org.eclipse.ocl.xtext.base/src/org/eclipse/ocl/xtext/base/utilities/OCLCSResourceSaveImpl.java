/*******************************************************************************
 * Copyright (c) 2024 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.base.utilities;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.resource.ASResourceFactory;
import org.eclipse.ocl.pivot.resource.CSResource;

/**
 * OCLCSResourceSaveImpl supports saving the contents of an EMF Resource using regular XMI serialization.
 * This is typically used to save an Xtext Resource as XMI rather than serializing to text.
 * It ensures that references to AS elements within the XMI are serialized as equivalent CS/AS references.
 */
public class OCLCSResourceSaveImpl extends BaseCSXMIResourceImpl
{
	protected final @NonNull CSResource csResource;

	public OCLCSResourceSaveImpl(@NonNull URI uri, @NonNull ASResourceFactory asResourceFactory, @NonNull CSResource csResource) {
		super(uri, asResourceFactory);
		this.csResource = csResource;
	}

	public @NonNull CSResource getCSResource() {
		return csResource;
	}

	/**
	 * Return the top level resource contents delegating to the Xtext-friendly CSResource.
	 */
	@Override
	public @NonNull EList<@NonNull EObject> getContents() {
		return csResource.getContents();
	}
}
