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
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUnknownValue;
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
 * @since 1.16
 */
public abstract class AbstractSymbolicEvaluationEnvironment implements SymbolicEvaluationEnvironment
{
	protected final @NonNull SymbolicAnalysis symbolicAnalysis;
	protected final @NonNull TypedElement executableObject;
	protected final @NonNull EnvironmentFactory environmentFactory;
	protected final @NonNull SymbolicEvaluationVisitor symbolicEvaluationVisitor;

	protected AbstractSymbolicEvaluationEnvironment(@NonNull SymbolicAnalysis symbolicAnalysis, @NonNull TypedElement executableObject) {
		this.symbolicAnalysis = symbolicAnalysis;
		this.executableObject = executableObject;
		this.environmentFactory = symbolicAnalysis.getEnvironmentFactory();
		this.symbolicEvaluationVisitor = symbolicAnalysis.createSymbolicEvaluationVisitor(this);
	}

//	protected abstract void addMayBeEmptyHypothesis(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue);

//	protected abstract void addMayBeInvalidHypothesis(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue);

//	protected abstract void addMayBeNullHypothesis(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue);

//	protected abstract void addMayBeZeroHypothesis(@NonNull TypedElement typedElement, @NonNull SymbolicValue symbolicValue);

	@Override
	public @Nullable SymbolicValue basicGetSymbolicValue(@NonNull TypedElement element) {
		CSEElement cseElement = symbolicAnalysis.getCSEElement(element);
		return basicGetSymbolicValue(cseElement);
	}

	public abstract @Nullable SymbolicValue basicGetSymbolicValue(@NonNull CSEElement cseElement);

	@Override
	public @Nullable SymbolicValue checkNotEmpty(@NonNull TypedElement typedElement, @NonNull TypeId typeId) {
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
		return symbolicAnalysis.getMayBeInvalidValue(typeId);
	}

	@Override
	public @Nullable SymbolicValue checkNotInvalid(@NonNull TypedElement typedElement, @NonNull TypeId typeId) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.isInvalid()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		if (!symbolicValue.mayBeInvalid()) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(typedElement);
		symbolicAnalysis.addMayBeInvalidHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(typeId);
	}

	@Override
	public @Nullable SymbolicValue checkNotNull(@NonNull TypedElement typedElement, @NonNull TypeId typeId) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.isNull()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		if (!symbolicValue.mayBeNull()) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(typedElement);
		symbolicAnalysis.addMayBeNullHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(typeId);
	}

	@Override
	public @Nullable SymbolicValue checkNotZero(@NonNull TypedElement typedElement, @NonNull TypeId typeId) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.isZero()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		if (!symbolicValue.mayBeZero()) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(typedElement);
		symbolicAnalysis.addMayBeZeroHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(typeId);
	}

	@Override
	public @NonNull SymbolicUnknownValue createUnknownValue(@NonNull TypeId typeId, boolean mayBeNull, boolean mayBeInvalid) {
		return symbolicAnalysis.createUnknownValue(typeId, mayBeNull, mayBeInvalid);
	}

	@Override
	public @NonNull SymbolicValue createUnknownValue(@NonNull TypedElement typedElement, boolean mayBeNull, boolean mayBeInvalid) {
		return symbolicAnalysis.createUnknownValue(typedElement, mayBeNull, mayBeInvalid);
	}

	protected abstract @NonNull Iterable<@NonNull TypedElement> getAffectedTypedElements(@NonNull TypedElement typedElement);

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	@Override
	public @NonNull ExecutorInternal getExecutor() {
		return symbolicAnalysis.getExecutor();
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

	@Override
	public boolean mayBeInvalid(@NonNull OCLExpression element) {
		return getSymbolicValue(element).mayBeInvalid();
	}

	@Override
	public boolean mayBeInvalidOrNull(@NonNull OCLExpression element) {
		return getSymbolicValue(element).mayBeInvalidOrNull();
	}

	@Override
	public boolean mayBeNull(@NonNull OCLExpression element) {
		return getSymbolicValue(element).mayBeNull();
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
