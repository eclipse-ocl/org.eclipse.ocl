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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

public class ASResourceImpl extends XMIResourceImpl implements ASResource
{
	protected final @NonNull ASResourceFactory asResourceFactory;
	private @Nullable LUSSIDs lussids = null;
	private @Nullable Map<@NonNull String, @NonNull EObject> legacyXMIId2eObject = null;

	/**
	 * Creates an instance of the resource.
	 */
	public ASResourceImpl(@NonNull URI uri, @NonNull ASResourceFactory asResourceFactory) {
		super(uri);
		this.asResourceFactory = asResourceFactory;
		assert PivotUtilInternal.isASURI(uri);
		//		PivotUtilInternal.debugPrintln("Create " + NameUtil.debugSimpleName(this));
	}

	//	@Override
	/**
	 * @since 1.4
	 */
	public @Nullable EObject basicGetEObjectByID(@Nullable String id) {
		return idToEObjectMap != null ? idToEObjectMap.get(id) : null;
	}

	/**
	 * @since 1.4
	 */
	// FIXME @Override promote API
	public @Nullable LUSSIDs basicGetLUSSIDs() {
		return lussids;
	}

	@Override
	protected XMLSave createXMLSave() {
		return new PivotSaveImpl(new XMIHelperImpl(this));
	}

	@Override
	protected void doUnload() {
		super.doUnload();
		if (lussids != null) {
			resetLUSSIDs();
		}
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

	@SuppressWarnings("deprecation")
	@Override
	protected EObject getEObjectByID(String id) {
		if ((unloadingContents == null) && (idToEObjectMap == null)) { // Lazy xmi:id creation needed by generated ASResources
			AS2ID.assignIds(this, null);
		}
		if (idToEObjectMap == null) {
			return null;
		}
		EObject eObject = idToEObjectMap.get(id);
		if (eObject != null) {
			return eObject;
		}
		if (isLoading()) {
			return null;
		}
		// FIXME Use getXmiidVersion() to select appropriate algorithm
		Map<@NonNull String, @NonNull EObject> legacyXMIId2eObject2 = legacyXMIId2eObject;
		if (legacyXMIId2eObject2 == null) {
			org.eclipse.ocl.pivot.internal.utilities.AS2XMIid as2id = new org.eclipse.ocl.pivot.internal.utilities.AS2XMIid();
			legacyXMIId2eObject = legacyXMIId2eObject2 = new HashMap<>();
			for (EObject eObject2 : new TreeIterable(this)) {
				if (eObject2 instanceof Element) {
					Element element = (Element)eObject2;
					org.eclipse.ocl.pivot.utilities.AS2XMIidVisitor idVisitor = asResourceFactory.createAS2XMIidVisitor(as2id);
					Boolean status = element.accept(idVisitor);
					if (status == Boolean.TRUE) {
						String legacyId = idVisitor.toString();
						if (legacyId != null) {
							legacyXMIId2eObject2.put(legacyId,  eObject2);;
						}
					}
				}
			}
		}
		EObject eObject2 = legacyXMIId2eObject2.get(id);
		return eObject2;
	}

	/**
	 * @since 1.4
	 */
	// FIXME @Override promote API
	public @NonNull LUSSIDs getLUSSIDs(@NonNull Map<@NonNull Object, @Nullable Object> options) {
		LUSSIDs lussids2 = lussids;
		if (lussids2 == null) {
			lussids = lussids2 = ((ASResourceFactory.ASResourceFactoryExtension)asResourceFactory).createLUSSIDs(this, options);
		}
		return lussids2;
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
	public String getURIFragment(EObject eObject) {
		if ((unloadingContents == null) && (idToEObjectMap == null)) {
			AS2ID.assignIds(this, null);
		}
		return super.getURIFragment(eObject);
	}

	/**
	 * @since 1.4
	 */
	// FIXME @Override promote API
	public int getXmiidVersion() {
		for (EObject eRoot : getContents()) {
			if (eRoot instanceof Model) {
				Number xmiidVersion = ((Model)eRoot).getXmiidVersion();
				if (xmiidVersion != null) {
					return xmiidVersion.intValue();
				}
			}
		}
		return 0;
	}

	/**
	 * @since 1.4
	 */
	// FIXME @Override promote API
	public void resetLUSSIDs() {
		LUSSIDs lussids2 = lussids;
		lussids = null;
		if (lussids2 != null) {
			lussids2.dispose();
		}
	}

	/**
	 * @since 1.4
	 */
	// FIXME @Override promote API
	public void setXmiidVersion(int xmiidVersion) {
		for (EObject eRoot : getContents()) {
			if (eRoot instanceof Model) {
				((Model)eRoot).setXmiidVersion(xmiidVersion);
			}
		}
	}

	@Override
	protected void unloaded(InternalEObject internalEObject) {
		if (internalEObject instanceof PivotObjectImpl) {
			((PivotObjectImpl)internalEObject).unloaded(this);
		}
		super.unloaded(internalEObject);
	}

	@Override
	protected boolean useIDAttributes() {
		return false;
	}

	@Override
	protected boolean useIDs() {
		return true;
	}
}
