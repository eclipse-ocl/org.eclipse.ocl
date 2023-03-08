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
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.internal.library.executor.ReflectiveFragment;

@Deprecated /* @deprecated no longer used */
public abstract class PivotReflectiveFragment extends ReflectiveFragment
{
	public PivotReflectiveFragment(@NonNull FlatClass derivedFlatClass, @NonNull FlatClass baseFlatClass) {
		super(derivedFlatClass, baseFlatClass);
	}

/*	@Override
	public @Nullable Operation getLocalOperation(@NonNull Operation baseOperation) {
		CompleteFlatClass completeFlatClass = (CompleteFlatClass)derivedFlatClass;		// FIXME cast
		String baseOperationName = baseOperation.getName();
		ParametersId baseParametersId = baseOperation.getParametersId();
		Operation bestOperation = null;
		for (org.eclipse.ocl.pivot.Class partialClass : completeFlatClass.getCompleteClass().getPartialClasses()) {
			for (Operation localOperation : partialClass.getOwnedOperations()) {
				if (localOperation.getName().equals(baseOperationName) && (localOperation.getParametersId() == baseParametersId)) {
					if (localOperation.getESObject() != null) {
						return localOperation;
					}
					if (bestOperation == null) {
						bestOperation = localOperation;
					}
					else if ((localOperation.getBodyExpression() != null) && (bestOperation.getBodyExpression() == null)) {
						bestOperation = localOperation;
					}
				}
			}
		}
		return bestOperation;					// null if not known locally, caller must try superfragments.
	} */
}
