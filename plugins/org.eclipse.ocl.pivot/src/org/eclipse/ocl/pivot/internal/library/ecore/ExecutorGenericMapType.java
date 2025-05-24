/*******************************************************************************
 * Copyright (c) 2021, 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorTypeParameter;

/**
 * @since 7.0
 */
public class ExecutorGenericMapType extends EcoreExecutorType implements MapType
//Initialization of OCLstdlibTables gives a NoSuchFieldError if EcoreExecutorAnyType is a nested class.
{
	public ExecutorGenericMapType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter keyParameter, @NonNull ExecutorTypeParameter valueParameter) {
		super(typeId, evaluationPackage, flags, keyParameter, valueParameter);
	}

	@Override
	public Type getKeyType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setKeyType(Type value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Class getEntryClass() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setEntryClass(Class value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Type getValueType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setValueType(Type value) {
		throw new UnsupportedOperationException();
	}
}
