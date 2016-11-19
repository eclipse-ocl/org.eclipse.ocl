/*******************************************************************************
 * Copyright (c) 2010, 2014 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.utilities.NameUtil;


public class TuplePartImpl
		extends PropertyImpl {

	protected TuplePartImpl() {
		super();
	}

	private TuplePartId partId;

	public TuplePartImpl(@NonNull TuplePartId partId, @NonNull Type partType) {
		this.partId = partId;
		setName(NameUtil.getSafeName(partId));
		setType(partType);
	}

	public @NonNull TuplePartId getTuplePartId() {
		TuplePartId partId2 = partId;
		if (partId2 == null) {
			String name2 = NameUtil.getSafeName(this);
			int index = ((TupleType)eContainer()).getOwnedProperties().indexOf(this);
			partId = partId2 = IdManager.getTuplePartId(index, name2, getTypeId());
		}
		return partId2;
	}
} //TupleImpl
