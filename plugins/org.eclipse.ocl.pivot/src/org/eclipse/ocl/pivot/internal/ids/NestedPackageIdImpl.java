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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.NestedPackageId;
import org.eclipse.ocl.pivot.ids.NsURIPackageId;
import org.eclipse.ocl.pivot.ids.PackageId;

public class NestedPackageIdImpl extends AbstractPackageIdImpl implements NestedPackageId
{
	protected final @NonNull PackageId parent;
	protected final @NonNull String name;

	NestedPackageIdImpl(@NonNull PackageId parent, @NonNull String name) {
		super(97 * parent.hashCode() + name.hashCode());
		this.parent = parent;
		this.name = name;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitNestedPackageId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		if (parent instanceof NsURIPackageId) {
			return name;
		}
		else {
			return parent + "::" + name;
		}
	}

	@Override
	public @NonNull String getName() {
		return name;
	}
	
	@Override
	public @NonNull PackageId getParent() {
		return parent;
	}
	
	@Override
	public String toString() {
		return parent + "::" + name;
	}

}