/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.executor;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TupleType;
import org.eclipse.ocl.pivot.ids.TupleTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.elements.AbstractExecutorClass;
import org.eclipse.ocl.pivot.library.LibraryFeature;

public class ExecutorTupleType extends AbstractExecutorClass implements TupleType
{
	protected final @NonNull TupleTypeId typeId;

	public ExecutorTupleType(@NonNull TupleTypeId typeId) {
		super(TypeId.TUPLE_NAME, 0);
		this.typeId = typeId;
	}

	@Override
	public @NonNull TupleTypeId getTupleTypeId() {
		return typeId;
	}

	@Override
	public @NonNull TupleTypeId getTypeId() {
		return typeId;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public String getValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public @NonNull Operation lookupActualOperation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return standardLibrary.getOclTupleType().lookupActualOperation(standardLibrary, apparentOperation);
	}

	@Override
	public @NonNull LibraryFeature lookupImplementation(@NonNull StandardLibrary standardLibrary, @NonNull Operation apparentOperation) {
		return standardLibrary.getOclTupleType().lookupImplementation(standardLibrary, apparentOperation);
	}

	@Override
	public String toString() {
		return typeId.toString();
	}
}