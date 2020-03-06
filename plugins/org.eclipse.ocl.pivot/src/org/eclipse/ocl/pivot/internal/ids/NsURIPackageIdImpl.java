/*******************************************************************************
 * Copyright (c) 2012, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;

public class NsURIPackageIdImpl extends AbstractPackageIdImpl implements NsURIPackageId
{
	/**
	 * The Namespace URI of this package identity.
	 */
	protected final @NonNull String nsURI;

	/**
	 * The optional Namespace prefix that may be preferred as an XML namepsace prefix.
	 */
	protected final @Nullable String nsPrefix;

	/**
	 * Cached EPackage that has this Namespace URI, iff a not-unloadable genmodelled implementation is available.
	 */
	private @Nullable EPackage ePackage = null;

	public NsURIPackageIdImpl(@NonNull IdManager idManager, @NonNull String nsURI, @Nullable String nsPrefix, @Nullable EPackage ePackage) {
		super(nsURI.hashCode());
		this.nsURI = nsURI;
		this.nsPrefix = nsPrefix;
		if (ePackage != null) {
			setEPackage(ePackage);
		}
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitNsURIPackageId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		return nsURI;
	}

	@Override
	public @Nullable EPackage getEPackage() {
		if ((ePackage != null) && ePackage.eIsProxy()) {
		//	EcoreUtil.resolve(ePackage, ePackage);	See Bug 548225 -- cannot repair the damage by re-resolving in an unknown ResourceSet
			throw new IllegalStateException("'" + nsURI + "' is a proxy because it has been unloaded.");
		}
		return ePackage;
	}

	@Override
	public @Nullable String getNsPrefix() {
		return nsPrefix;
	}

	@Override
	public @NonNull String getNsURI() {
		return nsURI;
	}

	@Override
	public void setEPackage(@NonNull EPackage ePackage) {
		assert !ePackage.eIsProxy();												// See Bug 548225, must not cache something unloadable
		if (ePackage.getClass() != EPackageImpl.class) {							// Dynamic Ecore metamodels do not have derived EPackageImpl's
			Resource eResource = ePackage.eResource();
			if ((eResource == null) || (eResource.getResourceSet() == null)) {		// No Resource/ResourceSet inhibits unloading/proxy conversion
				this.ePackage = ePackage;
			}
		}
	}

	@Override
	public String toString() {
		return "'" + nsURI + "'";
	}
}