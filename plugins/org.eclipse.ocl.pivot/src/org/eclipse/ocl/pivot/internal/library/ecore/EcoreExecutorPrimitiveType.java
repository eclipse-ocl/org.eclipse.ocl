/*******************************************************************************
 * Copyright (c) 2012, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PrimitiveType;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorTypeParameter;

public class EcoreExecutorPrimitiveType extends EcoreExecutorType implements PrimitiveType
{
	public EcoreExecutorPrimitiveType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter @NonNull ... typeParameters) {
		super(typeId, evaluationPackage, flags, typeParameters);
	}

	@Override
	public List<Operation> getCoercions() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getValue() {
		throw new UnsupportedOperationException();
	}
}
