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
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUnknownValue;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
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
public abstract class AbstractSymbolicEvaluationEnvironment extends BasicEvaluationEnvironment implements SymbolicEvaluationEnvironment
{
	private /*@LazyNonNull*/ EvaluationVisitor undecoratedVisitor = null;

	protected AbstractSymbolicEvaluationEnvironment(@NonNull SymbolicAnalysis executor, @NonNull NamedElement executableObject) {
		super(executor, executableObject);
	}

	protected AbstractSymbolicEvaluationEnvironment(@NonNull AbstractSymbolicEvaluationEnvironment parent, @NonNull NamedElement element) {
		super(parent, element, element);
	}

	public @Nullable SymbolicValue basicGetSymbolicValue(@NonNull Element element) {
		CSEElement cseElement = getSymbolicAnalysis().getCSEElement(element);
		return basicGetSymbolicValue(cseElement);
	}

	public abstract @Nullable SymbolicValue basicGetSymbolicValue(@NonNull CSEElement cseElement);

	@Override
	public @Nullable SymbolicValue checkNotEmpty(@NonNull TypedElement typedElement, @NonNull TypeId typeId) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		SymbolicContent symbolicContent = symbolicValue.getContent();
//		symbolicContent.toString();		// XXX
		SymbolicValue symbolicSize = symbolicContent.getSize();
		if (symbolicSize.isZero()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		if (!symbolicSize.mayBeZero()) {
			return null;
		}
		SymbolicAnalysis symbolicAnalysis = getSymbolicAnalysis();
		symbolicAnalysis.addMayBeEmptyHypothesis(typedElement, symbolicValue);
		return getMayBeInvalidValue(typeId);
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
		SymbolicAnalysis symbolicAnalysis = getSymbolicAnalysis();
		symbolicAnalysis.addMayBeInvalidHypothesis(typedElement, symbolicValue);
		return getMayBeInvalidValue(typeId);
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
		SymbolicAnalysis symbolicAnalysis = getSymbolicAnalysis();
		symbolicAnalysis.addMayBeNullHypothesis(typedElement, symbolicValue);
		return getMayBeInvalidValue(typeId);
	}

/*	@Override
	public @Nullable SymbolicValue checkNotSmallerThan(@NonNull TypedElement typedElement, @NonNull SymbolicValue minSizeValue, @NonNull TypeId typeId) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.isSmallerThan(minSizeValue)) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		if (!symbolicValue.mayBeSmallerThan(minSizeValue)) {
			return null;
		}
		SymbolicAnalysis symbolicAnalysis = getSymbolicAnalysis();
		symbolicAnalysis.addMayBeSmallerThanHypothesis(typedElement, symbolicValue, minSizeValue);
		return getMayBeInvalidValue(typeId);
	} */

	@Override
	public @Nullable SymbolicValue checkNotZero(@NonNull TypedElement typedElement, @NonNull TypeId typeId) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.isZero()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);
		}
		if (!symbolicValue.mayBeZero()) {
			return null;
		}
		SymbolicAnalysis symbolicAnalysis = getSymbolicAnalysis();
		symbolicAnalysis.addMayBeZeroHypothesis(typedElement, symbolicValue);
		return getMayBeInvalidValue(typeId);
	}

	public @NonNull SymbolicUnknownValue createUnknownValue(@NonNull TypeId typeId, boolean mayBeNull, boolean mayBeInvalid) {
		return new SymbolicUnknownValue(getSymbolicAnalysis().createVariableName(), typeId, mayBeNull, mayBeInvalid);
	}

	@Override
	public @NonNull SymbolicValue createUnknownValue(@NonNull TypedElement typedElement, boolean mayBeNull, boolean mayBeInvalid) {
		return createUnknownValue(typedElement.getTypeId(), mayBeNull, mayBeInvalid);
	}

	public @NonNull SymbolicValue getBaseSymbolicValue(@NonNull CSEElement cseElement) {
		return getBaseSymbolicEvaluationEnvironment().getSymbolicValue(cseElement);
	}

	public @NonNull SymbolicValue getBaseSymbolicValue(@NonNull TypedElement typedElement) {
		return getBaseSymbolicEvaluationEnvironment().getSymbolicValue(typedElement);
	}

	@Override
	public final @NonNull SymbolicValue getKnownValue(@Nullable Object boxedValue) {
		return getSymbolicAnalysis().getKnownValue(boxedValue);
	}

	public @Nullable SymbolicValue getMayBeInvalidValue(@NonNull TypeId typeid) {
		return getBaseSymbolicEvaluationEnvironment().getMayBeInvalidValue(typeid);
	}

	@Override
	public @Nullable AbstractSymbolicEvaluationEnvironment getParent() {
		return (AbstractSymbolicEvaluationEnvironment) super.getParent();
	}

	@Override
	public final @NonNull SymbolicAnalysis getSymbolicAnalysis() {
		return (SymbolicAnalysis)executor;
	}

	@Override
	public final @NonNull SymbolicValue getSymbolicValue(@NonNull Element element) {
		return ClassUtil.nonNullState(basicGetSymbolicValue(element));
	}

	public final @NonNull SymbolicValue getSymbolicValue(@NonNull CSEElement cseElement) {
		return ClassUtil.nonNullState(basicGetSymbolicValue(cseElement));
	}

	protected @NonNull EvaluationVisitor getUndecoratedVisitor() {
		EvaluationVisitor undecoratedVisitor2 = undecoratedVisitor;
		if (undecoratedVisitor2 == null) {
			this.undecoratedVisitor = undecoratedVisitor2 = executor.getEvaluationVisitor().getUndecoratedVisitor();
		}
		return undecoratedVisitor2;
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
	public void toString(@NonNull StringBuilder s) {
		toString(s, 0);
	}

	protected abstract void toString(@NonNull StringBuilder s, int depth);
}
