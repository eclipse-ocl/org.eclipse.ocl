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
package org.eclipse.ocl.pivot.library.oclany;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.flat.FlatClass;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.library.AbstractUntypedBinaryOperation;
import org.eclipse.ocl.pivot.library.LibraryBinaryOperation;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * OclComparableComparisonOperation provides the abstract support for a comparison operation.
 */
public abstract class OclComparableComparisonOperation extends AbstractUntypedBinaryOperation
{
	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull Boolean evaluate(@NonNull Executor executor, @Nullable Object left, @Nullable Object right) {
		StandardLibrary standardLibrary = executor.getStandardLibrary();
		IdResolver idResolver = executor.getIdResolver();
		FlatClass leftFlatClass = idResolver.getDynamicClassOf(left).getFlatClass(standardLibrary);
		FlatClass rightFlatClass = idResolver.getDynamicClassOf(right).getFlatClass(standardLibrary);
		FlatClass commonFlatClass = leftFlatClass.getCommonFlatClass(rightFlatClass);
		FlatClass comparableFlatClass = standardLibrary.getOclComparableType().getFlatClass(standardLibrary);
		FlatClass selfFlatClass = standardLibrary.getOclSelfType().getFlatClass(standardLibrary);
		Operation staticOperation = comparableFlatClass.lookupLocalOperation(standardLibrary, LibraryConstants.COMPARE_TO, selfFlatClass);
		LibraryBinaryOperation implementation = null;
		try {
			if (staticOperation != null) {
				implementation = (LibraryBinaryOperation)commonFlatClass.lookupImplementation(standardLibrary, staticOperation);
			}
		} catch (Exception e) {
			throw new InvalidValueException(e, "No 'compareTo' implementation"); //$NON-NLS-1$
		}
		if (implementation != null) {
			Object comparison = implementation.evaluate(executor, TypeId.INTEGER, left, right);
			Integer intComparison = ValueUtil.asInteger(comparison);
			return Boolean.valueOf(getResultValue(intComparison));
		}
		else {
			throw new InvalidValueException("Unsupported compareTo for ''{0}''", left != null ? left.getClass().getName() : NULL_STRING); //$NON-NLS-1$
		}
	}

	protected abstract boolean getResultValue(Integer comparison);
}
