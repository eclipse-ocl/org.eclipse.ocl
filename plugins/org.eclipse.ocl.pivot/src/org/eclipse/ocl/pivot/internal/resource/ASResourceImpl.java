/*******************************************************************************
 * Copyright (c) 2010, 2016 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.utilities.AS2XMIid;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

public class ASResourceImpl extends XMIResourceImpl implements ASResource
{
	protected final @NonNull ASResourceFactory asResourceFactory;

	/**
	 * Creates an instance of the resource.
	 */
	public ASResourceImpl(@NonNull URI uri, @NonNull ASResourceFactory asResourceFactory) {
		super(uri);
		this.asResourceFactory = asResourceFactory;
		assert PivotUtilInternal.isASURI(uri);
//		PivotUtilInternal.debugPrintln("Create " + NameUtil.debugSimpleName(this));	
	}

	@Override
	protected XMLSave createXMLSave() {
		return new PivotSaveImpl(new XMIHelperImpl(this));
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return asResourceFactory;
	}

	@Override
	public Map<Object, Object> getDefaultSaveOptions() {
		if (defaultSaveOptions == null) {
			defaultSaveOptions = XMIUtil.createSaveOptions();
		}
		return defaultSaveOptions;
	}

	@Override
	protected EObject getEObjectByID(String id) {
	    if (idToEObjectMap == null) {				// Lazy xmi:id creation need by generated ASResources
			AS2XMIid as2xmIid = new AS2XMIid();
			as2xmIid.assignIds(this, null);
	    }
		return super.getEObjectByID(id);
	}

	@Override
	public @NonNull Model getModel() {
		EList<EObject> contents = getContents();
		if (contents.size() <= 0) {
			throw new IllegalStateException("No Model at root of empty '" + getURI() + "'");
		}
		EObject eObject = contents.get(0);
		if (!(eObject instanceof Model)) {
			throw new IllegalStateException("Non-Model at root of '" + getURI() + "'");
		}
		return (Model)eObject;
	}

	@Override
	public void setURI(URI uri) {
//		assert PivotUtilInternal.isASURI(uri);
		super.setURI(uri);
	}

	@Override
	protected void unloaded(InternalEObject internalEObject) {
		if (internalEObject instanceof PivotObjectImpl) {
			((PivotObjectImpl)internalEObject).unloaded(this);
		}
		super.unloaded(internalEObject);
	}

	@Override
	protected boolean useIDs() {
		return true;
	}
}
