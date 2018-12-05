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
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class PropertyIdImpl extends AbstractElementId implements PropertyId //, ElementId.Internal
{
	protected final @NonNull TypeId parentId;
	protected final @NonNull String name;
	protected final @NonNull Integer hashCode;
//	protected final @Nullable EStructuralFeature eFeature;
	

	public PropertyIdImpl(@NonNull TypeId parentId, @NonNull String name/*, @Nullable EStructuralFeature eFeature*/) {
		this.parentId = parentId;
		this.name = name;
		this.hashCode = parentId.hashCode() * 31 + name.hashCode();
//		this.eFeature = eFeature;
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitPropertyId(this);
	}

	@Override
	public @NonNull String getDisplayName() {
		StringBuilder s = new StringBuilder();
		s.append(parentId);
		s.append("::");
		s.append(name);
		return s.toString();
	}
	
//	public @Nullable EStructuralFeature getEFeature() {
//		return eFeature;
//	}

	public @NonNull String getMetaTypeName() {
		return TypeId.PROPERTY_NAME;
	}

	@Override
	public @NonNull String getName() {
		return name;
	}

	@Override
	public @NonNull TypeId getParent() {
		return parentId;
	}

	@Override
	public final int hashCode() {
		return hashCode;
	}
}