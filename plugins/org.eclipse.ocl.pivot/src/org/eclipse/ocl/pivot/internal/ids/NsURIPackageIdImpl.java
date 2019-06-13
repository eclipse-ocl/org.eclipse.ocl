/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;

public class NsURIPackageIdImpl extends AbstractPackageIdImpl implements NsURIPackageId
{
	protected final @NonNull String nsURI;
	protected final @Nullable String nsPrefix;
	private @Nullable EPackage ePackage;
	private @Nullable URI debugResourceURI;

	public NsURIPackageIdImpl(@NonNull IdManager idManager, @NonNull String nsURI, @Nullable String nsPrefix, @Nullable EPackage ePackage) {
		super(nsURI.hashCode());
		this.nsURI = nsURI;
		this.nsPrefix = nsPrefix;
		if (ePackage != null) {
			assert !ePackage.eIsProxy();
			this.ePackage = ePackage;
			Resource eResource = ePackage.eResource();
			this.debugResourceURI = eResource != null ? eResource.getURI() : null;
		}
		else {
			this.ePackage = null;
			this.debugResourceURI = null;
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
			throw new IllegalStateException("'" + nsURI + "' is a proxy because '" + debugResourceURI + "' has been unloaded.");
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
		assert !ePackage.eIsProxy();
		this.ePackage = ePackage;
		Resource eResource = ePackage.eResource();
		this.debugResourceURI = eResource != null ? eResource.getURI() : null;
	}

	@Override
	public String toString() {
		return "'" + nsURI + "'";
	}
}