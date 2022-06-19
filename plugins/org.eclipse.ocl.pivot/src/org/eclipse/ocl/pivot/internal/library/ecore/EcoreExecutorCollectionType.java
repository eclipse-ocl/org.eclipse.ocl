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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.CollectionType;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.ids.BuiltInTypeId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorPackage;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorTypeParameter;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.UnlimitedNaturalValue;

public class EcoreExecutorCollectionType extends EcoreExecutorType implements CollectionType
{
	public EcoreExecutorCollectionType(@NonNull BuiltInTypeId typeId, @NonNull ExecutorPackage evaluationPackage, int flags, @NonNull ExecutorTypeParameter @NonNull ... typeParameters) {
		super(typeId, evaluationPackage, flags, typeParameters);
	}

	@Override
	public @NonNull Class getContainerType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Type getElementType() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Number getLower() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull IntegerValue getLowerValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull CollectionTypeId getTypeId() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Number getUpper() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull UnlimitedNaturalValue getUpperValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isIsNullFree() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setElementType(Type value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setIsNullFree(boolean value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLower(Number value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setLowerValue(@NonNull IntegerValue lower) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setUpper(Number value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setUpperValue(@NonNull UnlimitedNaturalValue upper) {
		throw new UnsupportedOperationException();
	}
}
