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

import java.math.BigInteger;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CompleteClass;
import org.eclipse.ocl.pivot.CompleteModel;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.SelfType;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.cse.CommonSubExpressionAnalysis;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicContent;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicReason;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicSimpleReason;
import org.eclipse.ocl.pivot.internal.symbolic.SymbolicUtil;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.IntegerValue;
import org.eclipse.ocl.pivot.values.InvalidValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.RealValue;
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
	protected final @NonNull StandardLibrary standardLibrary;
	protected final @NonNull CompleteModel completeModel;
	protected final @NonNull IdResolver idResolver;
	protected final @NonNull SymbolicEvaluationVisitor symbolicEvaluationVisitor;
	protected final @NonNull CommonSubExpressionAnalysis cseAnalysis;

	protected AbstractSymbolicEvaluationEnvironment(@NonNull SymbolicAnalysis symbolicAnalysis) {
		this.symbolicAnalysis = symbolicAnalysis;
		this.environmentFactory = symbolicAnalysis.getEnvironmentFactory();
		this.standardLibrary = environmentFactory.getStandardLibrary();
		this.completeModel = environmentFactory.getCompleteModel();
		this.idResolver = environmentFactory.getIdResolver();
		this.symbolicEvaluationVisitor = symbolicAnalysis.createSymbolicEvaluationVisitor(this);
		this.cseAnalysis = symbolicAnalysis.getCSEAnalysis();
	}

	public abstract @Nullable SymbolicValue basicGetSymbolicValue(@NonNull CSEElement cseElement);

	@Override
	public @Nullable SymbolicValue checkCompatibility(@NonNull OCLExpression typedElement, @NonNull Type returnType) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.asIncompatibility() != null) {
			return symbolicValue;
		}
		return null;
	}

	@Override
	public boolean checkConformance(@NonNull OCLExpression typedElement, @NonNull Type returnType,
			@NonNull TypedElement callTerm, @NonNull CallExp callExp) {			// XXX redundant callTerm
		Feature feature = PivotUtil.getReferredFeature(callExp);
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		Type actualType = null;
		if (feature.isIsStatic()) {
			if (symbolicValue.isKnown()) {
				Object knownValue = symbolicValue.getKnownValue();
				return knownValue instanceof Type;
			}
			else {
				actualType = symbolicValue.getType();
				return actualType instanceof org.eclipse.ocl.pivot.Class;
			}
		}
		else {
			actualType = SymbolicUtil.getType(symbolicValue, standardLibrary);
		}
		Type requiredType = PivotUtil.getType(callTerm);
		if (requiredType instanceof SelfType) {
			requiredType = PivotUtil.getOwningClass(PivotUtil.getReferredOperation(callExp));
		}
		CompleteClass actualClass = completeModel.getCompleteClass(PivotUtil.getBehavioralType(actualType));
		CompleteClass requiredClass = completeModel.getCompleteClass(PivotUtil.getBehavioralType(requiredType));
		if (actualClass.conformsTo(requiredClass)) {
			return true;
		}
		if (!symbolicValue.isKnown()) {
			return false;
		}
		// Known literals can be downcast,
		Object knownValue = symbolicValue.getKnownValue();
		List<org.eclipse.ocl.pivot.Class> requiredPartialClasses = requiredClass.getPartialClasses();
		if (requiredPartialClasses.contains(standardLibrary.getIntegerType())) {
			if (knownValue instanceof RealValue) {
				RealValue realValue = (RealValue)knownValue;
				try {
					@SuppressWarnings("unused") BigInteger bigIntegerValue = realValue.bigDecimalValue().toBigIntegerExact();
					return true;
				}
				catch (ArithmeticException e) {}
			}
		}
		if (requiredPartialClasses.contains(standardLibrary.getUnlimitedNaturalType())) {
			if (knownValue instanceof RealValue) {
				RealValue realValue = (RealValue)knownValue;
				if (realValue.signum() >= 0) {
					try {
						@SuppressWarnings("unused") BigInteger bigIntegerValue = realValue.bigDecimalValue().toBigIntegerExact();
						return true;
					}
					catch (ArithmeticException e) {}
				}
			}
			else if (knownValue instanceof IntegerValue) {
				IntegerValue integerValue = (IntegerValue)knownValue;
				if (integerValue.signum() >= 0) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public @Nullable SymbolicValue checkNotEmpty(@NonNull TypedElement typedElement, @NonNull Type type, boolean mayBeNull) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		SymbolicContent symbolicContent = symbolicValue.getContent();
		SymbolicValue symbolicSize = symbolicContent.getSize();
		if (symbolicSize.isZero()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);		// XXX More detailed invalid
		}
		if (!symbolicSize.mayBeZero()) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(typedElement);
		symbolicAnalysis.addMayBeEmptyHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(type, SymbolicUtil.mayBeNullReason(mayBeNull), SymbolicSimpleReason.MAY_BE_INVALID_REASON);
	}

	@Override
	public @Nullable SymbolicValue checkNotInvalid(@NonNull TypedElement typedElement, @NonNull Type type, @Nullable SymbolicReason mayBeNullReason, @NonNull CallExp callExp) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.asIncompatibility() != null) {
			return symbolicValue;
		}
		if (symbolicValue.isInvalid()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);		// XXX More detailed invalid
		}
		SymbolicReason mayBeInvalidReason = symbolicValue.mayBeInvalidReason();
		if (mayBeInvalidReason == null) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(typedElement);
		symbolicAnalysis.addMayBeInvalidHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(type, mayBeNullReason, SymbolicUtil.mayBeInvalidReason(mayBeInvalidReason, callExp));
	}

	@Override
	public @Nullable SymbolicValue checkNotNull(@NonNull OCLExpression sourceExp, @NonNull Type type, @Nullable SymbolicReason mayBeNullReason, @NonNull CallExp callExp) {
		SymbolicValue sourceSymbolicValue = getSymbolicValue(sourceExp);
		if (sourceSymbolicValue.isNull()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);		// XXX More detailed invalid
		}
		SymbolicReason sourceMayBeNullReason = sourceSymbolicValue.mayBeNullReason();
		if (sourceMayBeNullReason == null) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(sourceExp);
		symbolicAnalysis.addMayBeNullHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(type, mayBeNullReason, SymbolicUtil.mayBeInvalidReason(sourceMayBeNullReason, callExp));
	}

	@Override
	public @Nullable SymbolicValue checkNotZero(@NonNull TypedElement typedElement, @NonNull Type type, boolean mayBeNull) {
		SymbolicValue symbolicValue = getSymbolicValue(typedElement);
		if (symbolicValue.isZero()) {
			return getKnownValue(ValueUtil.INVALID_VALUE);		// XXX More detailed invalid
		}
		if (!symbolicValue.mayBeZero()) {
			return null;
		}
		Iterable<@NonNull TypedElement> affectedTypedElements = getAffectedTypedElements(typedElement);
		symbolicAnalysis.addMayBeZeroHypothesis(affectedTypedElements);
		return symbolicAnalysis.getMayBeInvalidValue(type, SymbolicUtil.mayBeNullReason(mayBeNull), SymbolicSimpleReason.MAY_BE_INVALID_REASON);
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
	public @NonNull SymbolicValue getInvalidValue(@NonNull Type type, @NonNull String isInvalidReason) {
	//	SymbolicValue mayBeInvalidValue = symbolicAnalysis.getMayBeInvalidValue(type, null, new SymbolicSimpleReason(isInvalidReason));
		SymbolicValue mayBeInvalidValue = symbolicAnalysis.getKnownValue(new InvalidValueException(isInvalidReason));
		assert mayBeInvalidValue != null;
		return mayBeInvalidValue;
	}

	@Override
	public final @NonNull SymbolicValue getKnownValue(@Nullable Object boxedValue) {
		if (boxedValue instanceof InvalidValue) {
			return getInvalidValue(standardLibrary.getOclInvalidType(), "invalid literal");		// XXX Fix caller
		}
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
