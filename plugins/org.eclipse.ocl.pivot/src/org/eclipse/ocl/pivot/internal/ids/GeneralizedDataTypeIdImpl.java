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
import org.eclipse.ocl.pivot.ids.DataTypeId;
import org.eclipse.ocl.pivot.ids.IdVisitor;
import org.eclipse.ocl.pivot.ids.PackageId;
import org.eclipse.ocl.pivot.ids.TypeId;

public class GeneralizedDataTypeIdImpl extends GeneralizedNestedTypeIdImpl implements DataTypeId
{
	public GeneralizedDataTypeIdImpl(@NonNull PackageId parent, int templateParameters, @NonNull String name) {
		super(parent, templateParameters, name);
	}

	@Override
	public <R> R accept(@NonNull IdVisitor<R> visitor) {
		return visitor.visitDataTypeId(this);
	}

	@Override
	public @NonNull String getMetaTypeName() {
		return TypeId.DATA_TYPE_NAME;
	}
}