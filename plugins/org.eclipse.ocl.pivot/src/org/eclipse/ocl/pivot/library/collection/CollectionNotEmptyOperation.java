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
package org.eclipse.ocl.pivot.library.collection;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicNumericValue;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * CollectionNotEmptyOperation realises the Collection::notEmpty() library operation.
 */
public class CollectionNotEmptyOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull CollectionNotEmptyOperation INSTANCE = new CollectionNotEmptyOperation();

	@Override
	public @NonNull Boolean evaluate(@Nullable Object argument) {
		CollectionValue collectionValue = asCollectionValue(argument);
		return collectionValue.notEmpty();
	}

	/**
	 * @since 1.16
	 */
	@Override
	public @NonNull SymbolicValue symbolicEvaluate(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue symbolicPreconditionValue = checkPreconditions(evaluationEnvironment, callExp);
		if (symbolicPreconditionValue != null) {
			return symbolicPreconditionValue;
		}
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		SymbolicValue sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		SymbolicContent content = sourceValue.getContent();
	//	if (content != null) {
			SymbolicValue sizeValue = content.getSize();
			SymbolicNumericValue zeroStatus = sizeValue.getNumericValue();
			if (zeroStatus.isZero()) {
				return evaluationEnvironment.getKnownValue(Boolean.FALSE);
			}
			else if (zeroStatus.isNotZero()) {
				return evaluationEnvironment.getKnownValue(Boolean.TRUE);
			}
	//	}
		Type booleanType = evaluationEnvironment.getEnvironmentFactory().getStandardLibrary().getBooleanType();
		SymbolicValue emptyValue = AbstractSymbolicRefinedValue.createIsZeroValue(sizeValue, booleanType);
		SymbolicValue notEmptyValue = AbstractSymbolicRefinedValue.createNotValue(emptyValue);
		return notEmptyValue;
	}
}
