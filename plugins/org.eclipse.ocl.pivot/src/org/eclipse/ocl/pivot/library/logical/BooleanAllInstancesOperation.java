/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library.logical;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractUntypedUnaryOperation;
import org.eclipse.ocl.pivot.values.SetValue;

/**
 * BooleanAllInstancesOperation realises the Boolean::allInstances() library operation.
 */
public class BooleanAllInstancesOperation extends AbstractUntypedUnaryOperation
{
	public static final @NonNull BooleanAllInstancesOperation INSTANCE = new BooleanAllInstancesOperation();
	public static final @NonNull CollectionTypeId SET_BOOLEAN = TypeId.SET.getSpecializedId(TypeId.BOOLEAN);
	
	/** @deprecated use Executor */
	@Deprecated
	@Override
	public @NonNull SetValue evaluate(@NonNull Evaluator evaluator, @Nullable Object sourceValue) {
		return evaluate(getExecutor(evaluator), sourceValue); 
	}
	
	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull SetValue evaluate(@NonNull Executor executor, @Nullable Object sourceVal) {
		// Boolean has two instances: false, true
		return executor.getIdResolver().createSetOfEach(SET_BOOLEAN, Boolean.FALSE, Boolean.TRUE);
	}
}
