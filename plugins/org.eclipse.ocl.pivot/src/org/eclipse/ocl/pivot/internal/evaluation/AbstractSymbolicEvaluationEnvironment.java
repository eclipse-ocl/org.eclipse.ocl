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

package org.eclipse.ocl.pivot.internal.evaluation;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicSimpleReason;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * Basic implementation of the {@link EvaluationEnvironment} interface,
 * providing some useful common behaviors.  Implementors of metamodel-specific
 * environments are encourage to extend this class rather than implement
 * an evaluation environment "from scratch."
 *
 * @since 1.17
 */
public abstract class AbstractSymbolicEvaluationEnvironment implements SymbolicEvaluationEnvironment
{
	protected final @NonNull SymbolicAnalysis symbolicAnalysis;
	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull SymbolicEvaluationVisitor symbolicEvaluationVisitor;
	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;

	protected AbstractSymbolicEvaluationEnvironment(@NonNull SymbolicAnalysis symbolicAnalysis) {
		this.symbolicAnalysis = symbolicAnalysis;
		this.environmentFactory = symbolicAnalysis.getEnvironmentFactory();
		this.symbolicEvaluationVisitor = symbolicAnalysis.createSymbolicEvaluationVisitor(this);
		this.cseAnalysis = symbolicAnalysis.getCSEAnalysis();
	}

	public abstract @Nullable SymbolicValue basicGetSymbolicValue(@NonNull CSEElement cseElement);

	@Override
	public @Nullable SymbolicValue checkCompatibility(@NonNull OCLExpression typedElement, @NonNull TypeId returnTypeId) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.asIncompatibility() != null) {
			return symbolicValue;
		}
		return null;
	}

	@Override
	public @Nullable SymbolicValue checkNotEmpty(@NonNull TypedElement typedElement, @NonNull TypeId typeId, boolean mayBeNull) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		SymbolicContent symbolicContent = symbolicValue.getContent();
		SymbolicValue symbolicSize = symbolicContent.getSize();
		if (symbolicSize.isZero()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		if (!symbolicSize.mayBeZero()) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(typedElement);
		symbolicAnalysis.addMayBeEmptyHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(typeId, SymbolicUtil.mayBeNullReason(mayBeNull), SymbolicSimpleReason.MAY_BE_INVALID_REASON);
	}

	@Override
	public @Nullable SymbolicValue checkNotInvalid(@NonNull TypedElement typedElement, @NonNull TypeId typeId, @Nullable SymbolicReason mayBeNullReason, @NonNull CallExp callExp) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.asIncompatibility() != null) {
			return symbolicValue;
		}
		if (symbolicValue.isInvalid()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		SymbolicReason mayBeInvalidReason = symbolicValue.mayBeInvalidReason();
		if (mayBeInvalidReason == null) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(typedElement);
		symbolicAnalysis.addMayBeInvalidHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(typeId, mayBeNullReason, SymbolicUtil.mayBeInvalidReason(mayBeInvalidReason, callExp));
	}

	@Override
	public @Nullable SymbolicValue checkNotNull(@NonNull OCLExpression sourceExp, @NonNull TypeId typeId, @Nullable SymbolicReason mayBeNullReason, @NonNull CallExp callExp) {
		SymbolicValue sourceSymbolicValue = getSymbolicValue(sourceExp);
		if (sourceSymbolicValue.isNull()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		SymbolicReason sourceMayBeNullReason = sourceSymbolicValue.mayBeNullReason();
		if (sourceMayBeNullReason == null) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(sourceExp);
		symbolicAnalysis.addMayBeNullHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(typeId, mayBeNullReason, SymbolicUtil.mayBeInvalidReason(sourceMayBeNullReason, callExp));
	}

	@Override
	public @Nullable SymbolicValue checkNotZero(@NonNull TypedElement typedElement, @NonNull TypeId typeId, boolean mayBeNull) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.isZero()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		if (!symbolicValue.mayBeZero()) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(typedElement);
		symbolicAnalysis.addMayBeZeroHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(typeId, SymbolicUtil.mayBeNullReason(mayBeNull), SymbolicSimpleReason.MAY_BE_INVALID_REASON);
	}

	protected abstract @NonNull Iterable<@NonNull TypedElement> getAffectedTypedElements(@NonNull TypedElement typedElement);

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	@Override
	public @NonNull IdResolver getIdResolver() {
		return environmentFactory.getIdResolver();
	}

	@Override
	public final @NonNull SymbolicValue getKnownValue(@Nullable Object boxedValue) {
		return symbolicAnalysis.getKnownValue(boxedValue);
	}

	@Override
	public final @NonNull SymbolicAnalysis getSymbolicAnalysis() {
		return symbolicAnalysis;
	}

	@Override
	public final @NonNull SymbolicValue getSymbolicValue(@NonNull TypedElement element) {
		return ClassUtil.nonNullState(basicGetSymbolicValue(element));
	}

	public final @NonNull SymbolicValue getSymbolicValue(@NonNull CSEElement cseElement) {
		return ClassUtil.nonNullState(basicGetSymbolicValue(cseElement));
	}

	@Override
	public @NonNull SymbolicValue getUnknownValue(@NonNull TypedElement typedElement,  @Nullable SymbolicReason mayBeNullReason, @Nullable SymbolicReason mayBeInvalidReason) {
		return symbolicAnalysis.getUnknownValue(typedElement, mayBeNullReason, mayBeInvalidReason);
	}

	@Override
	public boolean isFalse(@NonNull TypedElement element) {
		return getSymbolicValue(element).isFalse();
	}

	@Override
	public boolean isInvalid(@NonNull TypedElement element) {
		return getSymbolicValue(element).isInvalid();
	}

	@Override
	public boolean isNull(@NonNull TypedElement element) {
		return getSymbolicValue(element).isNull();
	}

	@Override
	public boolean isTrue(@NonNull TypedElement element) {
		return getSymbolicValue(element).isTrue();
	}

	@Override
	public boolean isZero(@NonNull TypedElement element) {
		return getSymbolicValue(element).isZero();
	}

//	@Override
//	public boolean mayBeInvalid(@NonNull TypedElement typedElement) {
//		return getSymbolicValue(typedElement).mayBeInvalid();
//	}

	@Override
	public @Nullable SymbolicReason mayBeInvalidReason(@NonNull TypedElement typedElement) {
		return getSymbolicValue(typedElement).mayBeInvalidReason();
	}

	@Override
	public boolean mayBeInvalidOrNull(@NonNull TypedElement typedElement) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		return (symbolicValue.mayBeInvalidReason() != null) || (symbolicValue.mayBeNullReason() != null);
	}

//	@Override
//	public boolean mayBeNull(@NonNull TypedElement typedElement) {
//		return getSymbolicValue(typedElement).mayBeNull();
//	}

	@Override
	public @Nullable SymbolicReason mayBeNullReason(@NonNull TypedElement typedElement) {
		return getSymbolicValue(typedElement).mayBeNullReason();
	}

	@Override
	public void setDead(@NonNull OCLExpression expression) {}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		toString(s,0);
		return s.toString();
	}

	protected abstract void toString(@NonNull StringBuilder s, int depth);
}
