/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library.ecore;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TemplateParameters;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.IdManager;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorOperation;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.types.ParameterTypesImpl;

/**
 * @since 1.18
 */
public class EcoreExecutorOperation extends ExecutorOperation implements LibraryOperation.LibraryOperationExtension
{			// FIXME Eliminate spurious ExecutorProperty rather than AbstractExecutorProperty once API has evolved publicly

	protected final @NonNull EOperation eOperation;

//	public ExecutorOperation(@NonNull String name, @NonNull ParameterTypes parameterTypes, @NonNull Type type, int index, @NonNull TemplateParameters typeParameters, @Nullable LibraryFeature implementation) {
	public EcoreExecutorOperation(/*@NonNull*/ EOperation eOperation, @NonNull Type executorType, int operationIndex) {
		super(IdManager.getOperationId(eOperation), ParameterTypesImpl.EMPTY_LIST, executorType, operationIndex, TemplateParameters.EMPTY_LIST, null);	// FIXME templates
		this.eOperation = eOperation;
	}

	@Override
	public @Nullable Object dispatch(@NonNull Evaluator evaluator,
			@NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor,
			@NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		// TODO Auto-generated method stub
		return null;
	}

	/** @deprecated use Executor
	@Deprecated
	@Override
	public @Nullable Object evaluate(@NonNull Evaluator evaluator, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		return evaluate(evaluator, returnTypeId, sourceValue);
	} */

	/**
	 * @since 1.1
	 *
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
		EObject eObject = ValueUtil.asNavigableObject(sourceValue, eOperation, executor);
		Object eValue = eObject.eGet(eOperation);
		return eValue != null ? executor.getIdResolver().boxedValueOf(eValue, eOperation, returnTypeId) : null;
	} */

	public @NonNull EOperation getEOperation() {
		return eOperation;
	}

	@Override
	public EObject getESObject() {
		return eOperation;
	}

	@Override
	public @NonNull LibraryOperation getImplementation() {
		return this;
	}

	@Override
	public boolean isIsMany() {
		return eOperation.isMany();
	}

	@Override
	public boolean isIsRequired() {
		return eOperation.isRequired();
	}
}