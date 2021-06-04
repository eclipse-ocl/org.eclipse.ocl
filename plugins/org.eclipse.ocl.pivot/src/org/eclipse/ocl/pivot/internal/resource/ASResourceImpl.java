/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.XMLSave;
import org.eclipse.emf.ecore.xmi.impl.XMIHelperImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.PivotPackage;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.utilities.PivotObjectImpl;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.util.PivotPlugin;
import org.eclipse.ocl.pivot.utilities.PivotConstants;
import org.eclipse.ocl.pivot.utilities.TracingAdapter;
import org.eclipse.ocl.pivot.utilities.TracingOption;
import org.eclipse.ocl.pivot.utilities.TreeIterable;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

/**
 * ASResourceImpl is the mandatory implementation of the ASResource interface that refines an
 * a standard EMF XMIResource to be used as a Pivot AS Resource.
 * @author ed
 *
 */
public class ASResourceImpl extends XMIResourceImpl implements ASResource
{
	/**
	 * If CHECK_IMMUTABILITY is set active, an ImmutabilityCheckingAdapter instance is installed for all
	 * contents of any ASREsource that is set not-saveable. Any mutation then causes an IllegalStateException.
	 *
	 * @since 1.5
	 */
	public static final TracingOption CHECK_IMMUTABILITY = new TracingOption(PivotPlugin.PLUGIN_ID, "resource/checkImmutability"); //$NON-NLS-1$

	/**
	 * An adapter implementation for tracking resource modification.
	 */
	private class ImmutabilityCheckingAdapter extends AdapterImpl
	{
		private @NonNull String formatMutationMessage(@NonNull Notification notification) {
			StringBuilder s = new StringBuilder();
			s.append("'");
			s.append(ASResourceImpl.this.getURI());
			s.append("' modified at a '");
			s.append(TracingAdapter.getFeatureType(notification));
			s.append("'");
			return s.toString();
		}

		@Override
		public void notifyChanged(Notification notification) {
			if (!notification.isTouch() && !ASResourceImpl.this.isUnloading) {
				Object notifier = notification.getNotifier();
				int eventType = notification.getEventType();
				if (eventType == Notification.SET) {
					if (notifier instanceof Feature) {
						int featureId = notification.getFeatureID(Feature.class);
						if (featureId == PivotPackage.Literals.FEATURE__IMPLEMENTATION.getFeatureID()) {	// A known safe transient See Bug 535888#c6
							Object oldValue = notification.getOldValue();
							if (oldValue == null) {
								return;
							}
						}
					}
					else if (notifier instanceof ExpressionInOCL) {					// A known safe laziness See Bug 535888#c6
						//	System.out.println(formatMutationMessage(notification));
						return;
					}
				}
				else if (eventType == Notification.ADD) {
					if (notifier instanceof ExpressionInOCL) {						// A known safe laziness See Bug 535888#c6
						//	System.out.println(formatMutationMessage(notification));
						return;
					}
				}
				throw new IllegalStateException(formatMutationMessage(notification));
			}
		}
	}

	/**
	 * ImmutableResource provides additional API for derived ReadOnly/Immutable implementations.
	 *
	 * @since 1.5
	 */
	public static interface ImmutableResource
	{
		/**
		 * Return true if this Immutable/ReadOnly Resource is compatible with the given metamodelURI.
		 * This is typically used to allow a metamodelURI implementation to be re-used rather than cloned.
		 */
		boolean isCompatibleWith(@NonNull String metamodelURI);
	}

	/**
	 * @since 1.5
	 */
	private @Nullable ImmutabilityCheckingAdapter immutabilityCheckingAdapter = null;

	protected final @NonNull ASResourceFactory asResourceFactory;
	private @Nullable LUSSIDs lussids = null;
	private @Nullable Map<@NonNull String, @NonNull EObject> legacyXMIId2eObject = null;

	/**
	 * An attempt to save an unsaveable ASResource is ignored, probably because it is immuatble..
	 */
	private boolean isSaveable = true;

	/**
	 * Set true during doUnload()
	 */
	private boolean isUnloading = true;

	/**
	 * Creates an instance of the resource.
	 */
	public ASResourceImpl(@NonNull URI uri, @NonNull ASResourceFactory asResourceFactory) {
		super(uri);
		this.asResourceFactory = asResourceFactory;
		assert PivotUtilInternal.isASURI(uri);
		//		PivotUtilInternal.debugPrintln("Create " + NameUtil.debugSimpleName(this));
	}

	/**
	 * @since 1.4
	 */
	@Override
	public @Nullable EObject basicGetEObjectByID(@Nullable String id) {
		return idToEObjectMap != null ? idToEObjectMap.get(id) : null;
	}

	/**
	 * @since 1.4
	 */
	@Override
	public @Nullable LUSSIDs basicGetLUSSIDs() {
		return lussids;
	}

	/**
	 * Overridden to ensure that the ResourceFactoryRegistry ExtensionToFactoryMap entries for AS file extensions
	 * have ASResourceFactory instnaces that are able to fall back from AS extension to CS extension using the
	 * resourceSet as the AS ResourceSet for OCL parsing.
	 */
	@Override
	public NotificationChain basicSetResourceSet(ResourceSet resourceSet, NotificationChain notifications) {
		NotificationChain notificationChain = super.basicSetResourceSet(resourceSet, notifications);
		if (resourceSet != null) {
			String fileExtension = getURI().fileExtension();
			if (fileExtension != null) {
				Object resourceFactory = resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().get(fileExtension);
				if (resourceFactory == null) {
					ASResourceFactoryRegistry.INSTANCE.configureResourceFactoryRegistry(resourceSet);
				}
			}
		}
		return notificationChain;
	}

	@Override
	protected XMLSave createXMLSave() {
		return new PivotSaveImpl(new XMIHelperImpl(this)
		{
			@Override
			public String getHREF(EObject obj) {
				if (obj instanceof Property) {			// Avoid generating a referemce to an EObject that might not exist
					Property asProperty = (Property)obj;
					if (asProperty.isIsImplicit() && (asProperty.getOpposite() != null)) {
						return null;
					}
				}
				return super.getHREF(obj);
			}
		});
	}

	@Override
	protected void doUnload() {
		isUnloading = true;
		try {
			super.doUnload();
			if (lussids != null) {
				resetLUSSIDs();
			}
		}
		finally {
			isUnloading = false;
		}
	}

	@Override
	public @NonNull ASResourceFactory getASResourceFactory() {
		return asResourceFactory;
	}

	@Override
	public @NonNull Map<Object, Object> getDefaultSaveOptions() {
		Map<Object, Object> defaultSaveOptions2 = defaultSaveOptions;
		if (defaultSaveOptions2 == null) {
			defaultSaveOptions = defaultSaveOptions2 = XMIUtil.createPivotSaveOptions();
		}
		return defaultSaveOptions2;
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
	@Override
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
	@Override
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

	@Override
	public boolean isOrphanage() {
		return false;
	}

	@Override
	public boolean isSaveable() {
		return isSaveable;
	}

	/**
	 * @since 1.4
	 */
	@Override
	public void resetLUSSIDs() {
		LUSSIDs lussids2 = lussids;
		if (lussids2 != null) {
			lussids = null;
			//	System.out.println("resetLUSSIDs for "  + getURI());
			lussids2.dispose();
		}
	}

	/**
	 * Overridden to suppress saving unsaveable content to a probably read-only destination.
	 */
	@Override
	public void save(Map<?, ?> options) throws IOException {
		if (isSaveable) {
			setXmiidVersion(PivotConstants.XMIIDS_CURRENT);
			if (options == null) {
				options = getDefaultSaveOptions();
			}
			XMIUtil.IdResourceEntityHandler.reset(options);
			super.save(options);
		}
	}

	/**
	 * @since 1.5
	 */
	@Override
	public void setSaveable(boolean isSaveable) {
		this.isSaveable = isSaveable;
		if (!isSaveable) {
			if (CHECK_IMMUTABILITY.isActive()) {
				if (immutabilityCheckingAdapter == null) {
					immutabilityCheckingAdapter = new ImmutabilityCheckingAdapter();
				}
				for (TreeIterator<EObject> i = getAllProperContents(getContents()); i.hasNext(); ) {
					EObject eObject = i.next();
					eObject.eAdapters().add(immutabilityCheckingAdapter);
				}
			}
		}
		else if (immutabilityCheckingAdapter != null) {
			for (TreeIterator<EObject> i = getAllProperContents(getContents()); i.hasNext(); ) {
				EObject eObject = i.next();
				eObject.eAdapters().remove(immutabilityCheckingAdapter);
			}
		}
	}

	/**
	 * @since 1.4
	 */
	@Override
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
