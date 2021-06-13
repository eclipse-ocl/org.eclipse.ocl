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
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.library.AbstractSimpleUnaryOperation;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.SequenceValue;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * OrderedCollectionFirstOperation realises the OrderedCollection::first() library operation.
 */
public class OrderedCollectionFirstOperation extends AbstractSimpleUnaryOperation
{
	public static final @NonNull OrderedCollectionFirstOperation INSTANCE = new OrderedCollectionFirstOperation();

	@Override
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment symbolicEvaluationEnvironment, @NonNull OperationCallExp callExp) {
		SymbolicValue superProblem = super.checkPreconditions(symbolicEvaluationEnvironment, callExp);
		if (superProblem != null) {
			return superProblem;
		}
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
	//	SymbolicValue sourceSymbolicValue = symbolicEvaluationEnvironment.getSymbolicValue(source);
	//	SymbolicCollectionContent sourceContent = sourceSymbolicValue.getCollectionContent();
		SymbolicValue sourceSizeProblem = symbolicEvaluationEnvironment.checkNotEmpty(source, callExp.getTypeId());
		if (sourceSizeProblem != null) {
			return sourceSizeProblem;
		}
		return null;
	}

	@Override
	public @Nullable Object evaluate(@Nullable Object argument) {
		SequenceValue orderedCollectionValue = asSequenceValue(argument);
		return orderedCollectionValue.first();
	}

	/**
	 *	Special case processing for return types based on the source collection element types.
	 *
	 * @since 1.15
	 */
	@Override
	public boolean resolveReturnNullity(@NonNull EnvironmentFactory environmentFactory, @NonNull CallExp callExp, boolean returnIsRequired) {
		return resolveCollectionSourceElementReturnNullity(environmentFactory, callExp, returnIsRequired);
	}
}
