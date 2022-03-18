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
package org.eclipse.ocl.pivot.internal.library.executor;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteInheritance;
import org.eclipse.ocl.pivot.InheritanceFragment;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.ids.OperationId;
import org.eclipse.ocl.pivot.utilities.FeatureFilter;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;


/**
 * @since 1.18
 */
public class ReflectiveOperations
{
	protected final @NonNull CompleteInheritance inheritance;
	protected final @NonNull Map<@NonNull OperationId, @NonNull Operation> operationId2operation = new HashMap<>();

	public ReflectiveOperations(@NonNull CompleteInheritance inheritance) {
		this.inheritance = inheritance;
		InheritanceFragment selfFragment = inheritance.getSelfFragment();
		for (@NonNull Operation asOperation : selfFragment.getLocalOperations()) {
			operationId2operation.put(asOperation.getOperationId(), asOperation);
		}
	}

	public @NonNull Iterable<@NonNull Operation> getAllOperations(final @Nullable FeatureFilter featureFilter) {
		@NonNull Collection<@NonNull Operation> values = operationId2operation.values();
		if (featureFilter == null) {
			return values;
		}
		@NonNull Iterable<@NonNull Operation> subItOps = Iterables.filter(values, new Predicate<@NonNull Operation>()
		{
			@Override
			public boolean apply(@NonNull Operation asOperation) {
				return featureFilter.accept(asOperation);
			}
		});
		return subItOps;
	}

	public @Nullable Operation getMemberOperation(@NonNull OperationId operationId) {
		return operationId2operation.get(operationId);
	}
}
