/*******************************************************************************
 * Copyright (c) 2022 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.library;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.Executor.ExecutorExtension;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal.EnvironmentFactoryInternalExtension;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 * ForeignOperation provides the access implementation for an operation implementation that cannot
 * be embedded as part of the reifocation of its containing class. Rather it must be genertaed somewhere
 * else.
 *
 * This is used to support statoc operation for Ecore models or additional operations for any model.
 *
 * The ForeignOperation instances are realized as operations in approriate namespace classes within
 * XXXTables for OCLinEcore or the test class for JUnit tests.
 *
 * @since 1.18
 */
public class ForeignOperation extends AbstractStaticOperation
{
	protected final @NonNull ExpressionInOCL specification;

	public ForeignOperation(@NonNull ExpressionInOCL specification) {
		this.specification = specification;
	//	assert specification.getOwnedBody() != null;
	}

	/**
	 * @since 1.3
	 */
	@Override
	public @Nullable Object basicEvaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] boxedSourceAndArgumentValues) {
		assert boxedSourceAndArgumentValues[0] == null;
		if (specification.getOwnedBody() == null) {
			try {
				EnvironmentFactoryInternalExtension environmentFactory = (EnvironmentFactoryInternalExtension) executor.getEnvironmentFactory();
				environmentFactory.parseSpecification(specification);
			} catch (ParserException e) {		// FIXME move to constructor's caller
				throw new InvalidValueException(e, "parse failure", executor.getEvaluationEnvironment(), boxedSourceAndArgumentValues[0], caller);
			}
		}
		Variable ownedContext = specification.getOwnedContext();
		assert ownedContext == null;
		EvaluationEnvironment nestedEvaluationEnvironment = ((ExecutorExtension)executor).pushEvaluationEnvironment(specification, caller);
		List<Variable> parameterVariables = specification.getOwnedParameters();
		int iMax = Math.min(parameterVariables.size(), boxedSourceAndArgumentValues.length-1);
		for (int i = 0; i < iMax; i++) {
			nestedEvaluationEnvironment.add(ClassUtil.nonNullModel(parameterVariables.get(i)), boxedSourceAndArgumentValues[i+1]);
		}
		try {
			return executor.evaluate(ClassUtil.nonNullPivot(specification.getOwnedBody()));
		}
		finally {
			executor.popEvaluationEnvironment();
		}
	}

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		assert sourceValue == null;
		assert !PivotUtil.getReferredOperation(callExp).isIsValidating();
		List<@NonNull OCLExpression> arguments = ClassUtil.nullFree(callExp.getOwnedArguments());
		@Nullable Object[] sourceAndArgumentValues = new @Nullable Object[1+arguments.size()];
		int argumentIndex = 0;
		sourceAndArgumentValues[argumentIndex++] = null;
		for (@NonNull OCLExpression argument : arguments) {
			sourceAndArgumentValues[argumentIndex++] = executor.evaluate(argument);
		}
		return evaluate(executor, callExp, sourceAndArgumentValues);
	}
}