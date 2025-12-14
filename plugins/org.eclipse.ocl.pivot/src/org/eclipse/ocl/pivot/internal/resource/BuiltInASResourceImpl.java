/*******************************************************************************
 * Copyright (c) 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.resource.ASResourceImpl.ImmutableResource;

/**
 * BuiltInASResourceImpl enforces immutability for the shared built-in ASResources implementating
 * the OCL Pivot and OCL Standard Library metamodels. EXtensible to ECore and UML2 and QVT.
 *
 * @since 7.0
 */
public class BuiltInASResourceImpl extends ASResourceImpl implements ImmutableResource
{
	public static @NonNull BuiltInASResourceFactory INSTANCE = new BuiltInASResourceFactory();

	public BuiltInASResourceImpl(@NonNull URI uri) {
		super(uri, BuiltInASResourceFactory.INSTANCE);
	}

	/**
	 * Overridden to inhibit unloading of the shared instance.
	 */
	@Override
	protected void doUnload() {}

	@Override
	public boolean isCompatibleWith(@NonNull String metamodelURI) {
	//	return PivotPackage.eNS_URI.equals(metamodelURI);
		return uri.equals(metamodelURI);
	}

	@Override
	public boolean setUpdating(boolean isUpdating) {
		throw new IllegalStateException();
	//	return super.setUpdating(isUpdating);
	}
}