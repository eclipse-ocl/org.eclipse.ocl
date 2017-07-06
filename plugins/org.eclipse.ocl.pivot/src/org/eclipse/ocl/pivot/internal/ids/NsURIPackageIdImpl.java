/*******************************************************************************
 * Copyright (c) 2012, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.ids;

import org.eclipse.emf.ecore.EPackage;
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

	public NsURIPackageIdImpl(@NonNull IdManager idManager, @NonNull String nsURI, @Nullable String nsPrefix, @Nullable EPackage ePackage) {
		super(nsURI.hashCode());
		this.nsURI = nsURI;
		this.nsPrefix = nsPrefix;
		this.ePackage = ePackage;
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
		this.ePackage = ePackage;
	}

	@Override
	public @NonNull String toString() {
		return "'" + nsURI + "'";
	}
}