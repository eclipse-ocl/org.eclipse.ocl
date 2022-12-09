/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.calling;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.pivot.Operation;

/**
 *  FunctionOperationCallingConvention defines the support for the call of a QVTi function.
 */
public abstract class FunctionOperationCallingConvention extends AbstractCachedOperationCallingConvention2	// cf ConstrainedOperationCallingConvention
{
	protected final org.eclipse.ocl.pivot.@NonNull Class createEntryClass(@NonNull ExecutableNameManager operationNameManager, @NonNull Operation asOperation) {
		return getEntryClassCallingConvention(asOperation).createEntryClass(operationNameManager);
	}

	protected final org.eclipse.ocl.pivot.@NonNull Class createEntryClass(@NonNull ExecutableNameManager operationNameManager, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return getEntryClassCallingConvention(asClass).createEntryClass(operationNameManager);
	}

	protected @NonNull EntryClassCallingConvention getEntryClassCallingConvention(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		return EntryClassCallingConvention.getInstance(asClass);
	}

	protected @NonNull EntryClassCallingConvention getEntryClassCallingConvention(@NonNull Operation asOperation) {
		return EntryClassCallingConvention.getInstance(asOperation, false);
	}
}
