/*******************************************************************************
 * Copyright (c) 2010, 2020 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.library;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationHaltedException;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.evaluation.BaseSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal;
import org.eclipse.ocl.pivot.internal.evaluation.ExecutorInternal.ExecutorInternalExtension;
import org.eclipse.ocl.pivot.internal.evaluation.HypothesizedSymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.evaluation.SymbolicEvaluationEnvironment;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.internal.symbolic.AbstractSymbolicRefinedValue;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.SymbolicValue;

import com.google.common.collect.Iterables;

/**
 * AbstractOperation defines the minimal functionality of all Operation implementations. Each implemented
 * operation is implemented by a distinct derived class whose evaluate method implements the operation.
 *
 * For interpreted purposes {@link #evaluate(Executor, OperationCallExp, Object[])} offers the maximum polymorphism
 * with source and arguments packed to facilitate cache lookup.
 *
 * For code generated purposes the many Unary/Binary/Ternary derived classes offer significantly simpler signatures
 * suitable for direct invocation from auto-generated Java code.
 */
public abstract class AbstractOperation extends AbstractIterationOrOperation implements LibraryOperation.LibraryOperationExtension2
{
	/**
	 * Return the evaluation from sourceAndArgumentValues using the executor for context wrt a caller.
	 *
	 * Derived calsses should override basicEvalute to evaluate a cacheable result, or evaluate to bypass caching.
	 *
	 * @since 1.3
	 */
	@Override
	public @Nullable Object basicEvaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Return the evaluation from sourceAndArgumentValues using the executor for context wrt a caller.
	 * The implementation attempts to re-use a cached result which is initially provided by basicEvaluate.
	 *
	 * This method may be invoked by derived classes that have inherited an unwanted override of evaluate.
	 *
	 * @since 1.3
	 */
	protected final @Nullable Object cachedEvaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		if (executor instanceof ExecutorInternal.ExecutorInternalExtension) {
			return ((ExecutorInternal.ExecutorInternalExtension)executor).getCachedEvaluationResult(this, caller, sourceAndArgumentValues);
		}
		else {
			return basicEvaluate(executor, caller, sourceAndArgumentValues);
		}
	}

	/**
	 * @since 1.17
	 */
	protected @Nullable SymbolicValue checkPreconditions(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp) {
		EnvironmentFactory environmentFactory = evaluationEnvironment.getEnvironmentFactory();
		CompleteModel completeModel = environmentFactory.getCompleteModel();
		StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
		Operation referredOperation = PivotUtil.getReferredOperation(callExp);
		TypeId returnTypeId = callExp.getTypeId();
		SymbolicReason returnMayBeNullReason = SymbolicUtil.isRequiredReason(callExp);
		//
		//	Propagate the known incompatibility of any source/argument.
		//
		OCLExpression source = PivotUtil.getOwnedSource(callExp);
		SymbolicValue sourceValue = evaluationEnvironment.symbolicEvaluate(source);
		if (sourceValue.asIncompatibility() != null) {
			return sourceValue;
		}
		for (@NonNull OCLExpression argument : PivotUtil.getOwnedArguments(callExp)) {
			SymbolicValue argumentValue = evaluationEnvironment.symbolicEvaluate(argument);
			if (argumentValue.asIncompatibility() != null) {
				return argumentValue;
			}
		}
		//
		//	Check whether an invalid source is permissible,
		//
		boolean sourceMayBeInvalid = sourceMayBeInvalid();
		if (sourceMayBeInvalid) {
			assert !hasRedundantOverloadForInvalid();
			assert completeModel.getCompleteClass(standardLibrary.getOclInvalidType()).getOperation(referredOperation) != null : "Missing OclInvalid overload for " + referredOperation;
		}
		else {
			if (!hasRedundantOverloadForInvalid()) {
				assert completeModel.getCompleteClass(standardLibrary.getOclInvalidType()).getOperation(referredOperation) == null : "Spurious OclInvalid overload for " + referredOperation;
			}
			SymbolicValue invalidSourceProblem = evaluationEnvironment.checkNotInvalid(source, returnTypeId, returnMayBeNullReason, callExp);
			if (invalidSourceProblem != null) {
				return invalidSourceProblem;
			}
		}
		//
		//	Check whether a null source is permissible,
		//
		boolean sourceMayBeNull = sourceMayBeNull();
		if (sourceMayBeNull) {
			assert completeModel.getCompleteClass(standardLibrary.getOclVoidType()).getOperation(referredOperation) != null : "Missing OcVoid overload for " + referredOperation;
		}
		else {
			if (!hasRedundantOverloadForNull()) {
				assert completeModel.getCompleteClass(standardLibrary.getOclVoidType()).getOperation(referredOperation) == null : "Spurious OcVoid overload for " + referredOperation;
			}
			if (!callExp.isIsSafe()) {
				SymbolicValue nullSourceProblem = evaluationEnvironment.checkNotNull(source, returnTypeId, returnMayBeNullReason, callExp);
				if (nullSourceProblem != null) {
					return nullSourceProblem;
				}
			}
		}
		int i = 0;
		for (@NonNull OCLExpression argument : PivotUtil.getOwnedArguments(callExp)) {
			//
			//	Check whether an invalid argument is permissible,
			//
			if (!referredOperation.isIsValidating() && !sourceMayBeInvalid) {
				SymbolicValue invalidArgumentProblem = evaluationEnvironment.checkNotInvalid(argument, returnTypeId, returnMayBeNullReason, callExp);
				if (invalidArgumentProblem != null) {
					return invalidArgumentProblem;
				}
			}
			//
			//	Check whether a null argument is permissible,
			//
			Parameter parameter = PivotUtil.getOwnedParameter(referredOperation, i);
			if (parameter.isIsRequired() && !sourceMayBeNull) {
				SymbolicValue nullArgumentProblem = evaluationEnvironment.checkNotNull(argument, returnTypeId, returnMayBeNullReason, callExp);
				if (nullArgumentProblem != null) {
					return nullArgumentProblem;
				}
			}
			SymbolicValue compatibleArgumentProblem = evaluationEnvironment.checkCompatibility(argument, returnTypeId);
			if (compatibleArgumentProblem != null) {
				return compatibleArgumentProblem;
			}
			i++;
		}
		SymbolicValue compatibleSourceProblem = evaluationEnvironment.checkCompatibility(source, returnTypeId);
		if (compatibleSourceProblem != null) {
			return compatibleSourceProblem;
		}
		return null;
	}

	/**
	 * @since 1.17
	 */
	protected @NonNull SymbolicValue createResultValue(@NonNull SymbolicEvaluationEnvironment evaluationEnvironment, @NonNull OperationCallExp callExp,
			@NonNull SymbolicValue sourceSymbolicValue, @NonNull List<@NonNull SymbolicValue> argumentSymbolicValues) {
		OCLExpression ownedSource = PivotUtil.getOwnedSource(callExp);
		Operation referredOperation = PivotUtil.getReferredOperation(callExp);
		assert (this instanceof ConstrainedOperation) || !referredOperation.isIsValidating() : "Missing createResultValue overload for " + referredOperation.getImplementationClass();
		SymbolicReason mayBeInvalidReason = evaluationEnvironment.mayBeInvalidReason(ownedSource);
		SymbolicReason mayBeNullReason = null;
		SymbolicReason mayBeNullReason2 = evaluationEnvironment.mayBeNullReason(ownedSource);
		if (mayBeNullReason2 != null) {
			if (callExp.isIsSafe()) {
				mayBeNullReason = mayBeNullReason2;
			}
			else {
				mayBeInvalidReason = mayBeNullReason2;
			}
		}
		int i = 0;
		for (@NonNull SymbolicValue argumentSymbolicValue : argumentSymbolicValues) {		// XXX correlate parameter/return nullity
			if (mayBeInvalidReason == null) {
				mayBeInvalidReason = argumentSymbolicValue.mayBeInvalidReason();
				if (mayBeInvalidReason == null) {
					mayBeNullReason2 = argumentSymbolicValue.mayBeNullReason();
					if (mayBeNullReason2 != null) {
						Parameter ownedParameter = PivotUtil.getOwnedParameter(referredOperation, i);
						if (ownedParameter.isIsRequired()) {
							mayBeInvalidReason = mayBeNullReason2;
						}
					}
				}
			}
			i++;
		}
		SymbolicReason resultMayBeInvalidReason = null;
		SymbolicReason resultMayBeNullReason = null;
		if (mayBeInvalidReason != null) {
			resultMayBeInvalidReason = SymbolicUtil.mayBeInvalidReason(mayBeInvalidReason, callExp);
		}
		if (mayBeNullReason != null) {
			if (callExp.isIsSafe()) {
				resultMayBeNullReason = SymbolicUtil.mayBeNullReason(mayBeNullReason, callExp);
			}
			else if (resultMayBeInvalidReason == null) {
				resultMayBeInvalidReason = SymbolicUtil.mayBeInvalidReason(mayBeNullReason, callExp);
			}
		}
		return evaluationEnvironment.getUnknownValue(callExp, resultMayBeNullReason, resultMayBeInvalidReason);
	}

	/**
	 * Return the evaluation from sourceAndArgumentValues using the executor for context wrt a caller.
	 * The default implementation attempts to re-use a cached result which is initially provided by basicEvaluate.
	 *
	 * basicEvaluate should be overridden if caching is required, evaluate if caching is to be bypassed.
	 *
	 * @since 1.3
	 */
	@Override
	public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypedElement caller, @Nullable Object @NonNull [] sourceAndArgumentValues) {
		if (executor instanceof ExecutorInternal.ExecutorInternalExtension) {
			return ((ExecutorInternal.ExecutorInternalExtension)executor).getCachedEvaluationResult(this, caller, sourceAndArgumentValues);
		}
		else {
			return basicEvaluate(executor, caller, sourceAndArgumentValues);
		}
	}
	/** @deprecated use Executor
	 * @since 1.1*/
	@Deprecated
	@Override
	public @Nullable Object dispatch(@NonNull Evaluator evaluator, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		return dispatch(getExecutor(evaluator), callExp, sourceValue);
	}

	@Override
	public @Nullable Object dispatch(@NonNull Executor executor, @NonNull OperationCallExp callExp, @Nullable Object sourceValue) {
		ExecutorInternalExtension castExecutor = (ExecutorInternalExtension)executor;
		Operation apparentOperation = callExp.getReferredOperation();
		assert apparentOperation != null;
		//
		//	Resolve argument values catching invalid values for validating operations.
		//
		List<@NonNull OCLExpression> arguments = ClassUtil.nullFree(callExp.getOwnedArguments());
		@Nullable Object[] sourceAndArgumentValues = new @Nullable Object[1+arguments.size()];
		int argumentIndex = 0;
		sourceAndArgumentValues[argumentIndex++] = sourceValue;
		if (!apparentOperation.isIsValidating()) {
			for (@NonNull OCLExpression argument : arguments) {
				Object argValue = castExecutor.evaluate(argument);
				sourceAndArgumentValues[argumentIndex++] = argValue;
			}
		}
		else {
			for (@NonNull OCLExpression argument : arguments) {
				Object argValue;
				try {
					argValue = castExecutor.evaluate(argument);
					assert ValueUtil.isBoxed(argValue);	// Make sure Integer/Real are boxed, invalid is an exception, null is null
				}
				catch (EvaluationHaltedException e) {
					throw e;
				}
				catch (InvalidValueException e) {
					argValue = e;	// FIXME ?? propagate part of environment
				}
				sourceAndArgumentValues[argumentIndex++] = argValue;
			}
		}
		return castExecutor.internalExecuteOperationCallExp(callExp, sourceAndArgumentValues);
	}

	/**
	 * @since 1.3
	 */
	//	@Override
	//	public boolean isCached() {
	//		return true;
	//	}

	@Override
	public @Nullable String installPathConstraints(@NonNull HypothesizedSymbolicEvaluationEnvironment evaluationEnvironment, @NonNull TypedElement activeTypedElement, @NonNull OperationCallExp operationCallExp) {
		Operation operation = PivotUtil.getReferredOperation(operationCallExp);
		BaseSymbolicEvaluationEnvironment baseSymbolicEvaluationEnvironment = evaluationEnvironment.getBaseSymbolicEvaluationEnvironment();
		String incompatibility = null;
		//
		OCLExpression ownedSource = PivotUtil.getOwnedSource(operationCallExp);
		if (ownedSource != activeTypedElement) {
			SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(ownedSource);
			SymbolicValue refinedSymbolicValue = baseSymbolicValue;
			//
			boolean mayBeInvalid = false;
			EnvironmentFactory environmentFactory = evaluationEnvironment.getEnvironmentFactory();
			CompleteModel completeModel = environmentFactory.getCompleteModel();
			StandardLibrary standardLibrary = environmentFactory.getStandardLibrary();
			CompleteClass oclInvalidClass = completeModel.getCompleteClass(standardLibrary.getOclInvalidType());
			Operation oclInvalidOperation = oclInvalidClass.getOperation(operation);
			if (oclInvalidOperation != null) {
				mayBeInvalid = true;
			}
			if (!mayBeInvalid && (baseSymbolicValue.mayBeInvalidReason() != null)) {
				refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, ValueUtil.INVALID_VALUE);
			}
			//
			boolean mayBeNull = false;
			if (operationCallExp.isIsSafe()) {
				mayBeNull = true;
			}
			else {
				CompleteClass oclVoidClass = completeModel.getCompleteClass(standardLibrary.getOclVoidType());
				Operation oclVoidOperation = oclVoidClass.getOperation(operation);
				if (oclVoidOperation != null) {
					mayBeNull = true;
				}
			}
			if (!mayBeNull && (baseSymbolicValue.mayBeNullReason() != null)) {
				refinedSymbolicValue = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue, null);
			}
			if (refinedSymbolicValue != baseSymbolicValue) {
				incompatibility = evaluationEnvironment.installRefinement(ownedSource, refinedSymbolicValue);
			}
		}
		if (incompatibility == null) {
			List<@NonNull OCLExpression> ownedArguments = PivotUtilInternal.getOwnedArgumentsList(operationCallExp);
			if (ownedArguments.size() > 0) {
				assert !operation.isIsValidating();
				int i = 0;
				for (@NonNull Parameter parameter : PivotUtil.getOwnedParameters(operation)) {
					OCLExpression argument = ownedArguments.get(i);
					if (argument != activeTypedElement) {
						SymbolicValue baseSymbolicValue = baseSymbolicEvaluationEnvironment.getSymbolicValue(argument);
						if (parameter.isIsRequired()) {
							SymbolicValue refinedSymbolicValue2 = baseSymbolicValue;
							if (baseSymbolicValue.mayBeInvalidReason() != null) {
								refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue2, ValueUtil.INVALID_VALUE);
							}
							if (baseSymbolicValue.mayBeNullReason() != null) {
								refinedSymbolicValue2 = AbstractSymbolicRefinedValue.createExceptValue(refinedSymbolicValue2, null);
							}
							if (refinedSymbolicValue2 != baseSymbolicValue) {
								incompatibility = evaluationEnvironment.installRefinement(argument, refinedSymbolicValue2);
								if (incompatibility != null) {
									break;
								}
							}
						}
					}
					i++;
				}
			}
		}
		return incompatibility;
	}

	/**
	 * Return true if this implementation has a modelled declaration for handling an invalid input, but
	 * actually just returns invalid.
	 *
	 * @since 1.17
	 */
	protected boolean hasRedundantOverloadForInvalid() {
		return false;
	}

	/**
	 * Return true if this implementation has a modelled declaration for handling a null input, but
	 * actually just returns invalid.
	 *
	 * @since 1.17
	 */
	protected boolean hasRedundantOverloadForNull() {
		return false;
	}

	/**
	 * Return true if this implementation handles an invalid source value.
	 *
	 * @since 1.17
	 */
	protected boolean sourceMayBeInvalid() {
		return false;
	}

	/**
	 * Return true if this implementation handles a null source value.
	 *
	 * @since 1.17
	 */
	protected boolean sourceMayBeNull() {
		return false;
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
		SymbolicValue sourceSymbolicValue = evaluationEnvironment.symbolicEvaluate(PivotUtil.getOwnedSource(callExp));
		boolean isKnown = sourceSymbolicValue.isKnown();
		Iterable<@NonNull OCLExpression> ownedArguments = PivotUtil.getOwnedArguments(callExp);
		int argumentsSize = Iterables.size(ownedArguments);
		List<@NonNull SymbolicValue> argumentSymbolicValues = new ArrayList<@NonNull SymbolicValue>(argumentsSize);
		for (@NonNull OCLExpression argument : ownedArguments) {
			SymbolicValue argumentSymbolicValue = evaluationEnvironment.symbolicEvaluate(argument);
			if (!argumentSymbolicValue.isKnown()) {
				isKnown = false;
			}
			argumentSymbolicValues.add(argumentSymbolicValue);
		}
		if (isKnown) {
			@Nullable Object[] sourceAndArgumentValues = new @Nullable Object[1+argumentsSize];
			sourceAndArgumentValues[0] = sourceSymbolicValue.getKnownValue();
			for (int i = 0; i < argumentsSize; i++) {
				sourceAndArgumentValues[i+1] = argumentSymbolicValues.get(i).getKnownValue();
			}
			ExecutorInternal executor = evaluationEnvironment.getSymbolicAnalysis().getExecutor();
			Object result = ((LibraryOperationExtension2)this).evaluate(executor, callExp, sourceAndArgumentValues);
			return evaluationEnvironment.getKnownValue(result);
		}
		else {
			return createResultValue(evaluationEnvironment, callExp, sourceSymbolicValue, argumentSymbolicValues);
		}
	}
}
