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
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.internal.cse.CSEElement;
import org.eclipse.ocl.pivot.internal.manager.SymbolicExecutor;
import org.eclipse.ocl.pivot.internal.manager.SymbolicOCLExecutor;
import org.eclipse.ocl.pivot.values.SymbolicValue;

/**
 * Basic implementation of the {@link EvaluationEnvironment} interface,
 * providing some useful common behaviors.  Implementors of metamodel-specific
 * environments are encourage to extend this class rather than implement
 * an evaluation environment "from scratch."
 *
 * @since 1.15
 */
public abstract class AbstractSymbolicEvaluationEnvironment extends BasicEvaluationEnvironment implements SymbolicEvaluationEnvironment
{
	private /*@LazyNonNull*/ EvaluationVisitor undecoratedVisitor = null;

	protected AbstractSymbolicEvaluationEnvironment(@NonNull SymbolicExecutor executor, @NonNull NamedElement executableObject) {
		super(executor, executableObject);
	}

	protected AbstractSymbolicEvaluationEnvironment(@NonNull AbstractSymbolicEvaluationEnvironment parent, @NonNull NamedElement element) {
		super(parent, element, element);
	}

	public @NonNull SymbolicValue getBaseSymbolicValue(@NonNull CSEElement cseElement) {
		return getBaseSymbolicEvaluationEnvironment().getSymbolicValue(cseElement);
	}

	public @NonNull SymbolicValue getKnownValue(@Nullable Object boxedValue) {
		return getBaseSymbolicEvaluationEnvironment().getKnownValue(boxedValue);
	}

	@Override
	public @Nullable AbstractSymbolicEvaluationEnvironment getParent() {
		return (AbstractSymbolicEvaluationEnvironment) super.getParent();
	}

	protected @NonNull SymbolicAnalysis getSymbolicAnalysis() {
		return (SymbolicAnalysis)getSymbolicExecutor();
	}

	public @NonNull SymbolicOCLExecutor getSymbolicExecutor() {
		return (SymbolicOCLExecutor)getExecutor();
	}

	protected @NonNull EvaluationVisitor getUndecoratedVisitor() {
		EvaluationVisitor undecoratedVisitor2 = undecoratedVisitor;
		if (undecoratedVisitor2 == null) {
			this.undecoratedVisitor = undecoratedVisitor2 = executor.getEvaluationVisitor().getUndecoratedVisitor();
		}
		return undecoratedVisitor2;
	}

	@Override
	public boolean isFalse(@NonNull OCLExpression element) {
		return getSymbolicValue(element).isFalse();
	}

	@Override
	public boolean isInvalid(@NonNull OCLExpression element) {
		return getSymbolicValue(element).isInvalid();
	}

	@Override
	public boolean isNull(@NonNull OCLExpression element) {
		return getSymbolicValue(element).isNull();
	}

	@Override
	public boolean isTrue(@NonNull OCLExpression element) {
		return getSymbolicValue(element).isTrue();
	}

	@Override
	public boolean isZero(@NonNull OCLExpression element) {
		return getSymbolicValue(element).isZero();
	}

	@Override
	public boolean mayBeInvalid(@NonNull OCLExpression expression) {
		SymbolicValue symbolicValue = getSymbolicValue(expression);
		if (!symbolicValue.mayBeInvalid()) {
			return false;
		}
		SymbolicAnalysis symbolicAnalysis = getSymbolicAnalysis();
		Hypothesis hypothesis = new Hypothesis.MayBeInvalidHypothesis(symbolicAnalysis, expression, symbolicValue);
		symbolicAnalysis.addHypothesis(expression, hypothesis);
	//	HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment = symbolicExecutor.createHypothesizedSymbolicEvaluationEnvironment(element);
	//	try {
	//		SymbolicValue hypothesizedValue = new SymbolicKnownValueImpl(element.getTypeId(), ValueUtil.INVALID_VALUE);
	//		hypothesizedSymbolicEvaluationEnvironment.putHypothesizedValue(symbolicValue, hypothesizedValue);
		//	boolean isContradiction = hypothesizedSymbolicEvaluationEnvironment.isContradiction(this);
		//	return !isContradiction;
	//	}
	//	finally {
	//		symbolicExecutor.popEvaluationEnvironment();
	//	}
		return true;
	}

	@Override
	public boolean mayBeInvalidOrNull(@NonNull OCLExpression expression) {
		boolean mayBeInvalid = mayBeInvalid(expression);		// FIXME it would be nice to do both at once
		boolean mayBeNull = mayBeNull(expression);				// but that needs e.g. may-be-invalid + true.
		return mayBeInvalid || mayBeNull;					// hypothesize both before the potential logiical short circuit.
	}

	@Override
	public boolean mayBeNull(@NonNull OCLExpression expression) {
		SymbolicValue symbolicValue = getSymbolicValue(expression);
		if (!symbolicValue.mayBeNull()) {
			return false;
		}
		SymbolicAnalysis symbolicAnalysis = getSymbolicAnalysis();
		Hypothesis hypothesis = new Hypothesis.MayBeNullHypothesis(symbolicAnalysis, expression, symbolicValue);
		symbolicAnalysis.addHypothesis(expression, hypothesis);
	//	HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment = symbolicExecutor.createHypothesizedSymbolicEvaluationEnvironment(element);
	//	try {
	//		SymbolicValue hypothesizedValue = new SymbolicKnownValueImpl(element.getTypeId(), ValueUtil.NULL_VALUE);
	//		hypothesizedSymbolicEvaluationEnvironment.putHypothesizedValue(symbolicValue, hypothesizedValue);
		//	boolean isContradiction = hypothesizedSymbolicEvaluationEnvironment.isContradiction(this);
		//	return !isContradiction;
	//	}
	//	finally {
	//		symbolicExecutor.popEvaluationEnvironment();
	//	}
		return true;
	}

//	@Override
//	public boolean mayBeNull(@NonNull OCLExpression expression, @Nullable Object value) {
		// TODO Auto-generated method stub
//		return false;
//	}

	@Override
	public boolean mayBeZero(@NonNull OCLExpression expression) {
		SymbolicValue symbolicValue = getSymbolicValue(expression);
		if (!symbolicValue.mayBeZero()) {
			return false;
		}
		if (symbolicValue.isZero()) {
			return true;
		}
		SymbolicAnalysis symbolicAnalysis = getSymbolicAnalysis();
		Hypothesis hypothesis = new Hypothesis.MayBeZeroHypothesis(symbolicAnalysis, expression, symbolicValue);
		symbolicAnalysis.addHypothesis(expression, hypothesis);
	//	HypothesizedSymbolicEvaluationEnvironment hypothesizedSymbolicEvaluationEnvironment = symbolicExecutor.createHypothesizedSymbolicEvaluationEnvironment(element);
	//	try {
	//		hypothesizedSymbolicEvaluationEnvironment.putHypothesizedValue(symbolicValue, hypothesizedValue);
	//		hypothesizedSymbolicEvaluationEnvironment.putHypothesizedTerm(getSymbolicAnalysis(), element);
		//	boolean isContradiction = hypothesizedSymbolicEvaluationEnvironment.isContradiction(this);
		//	return !isContradiction;
	//	}
	//	finally {
	//		symbolicExecutor.popEvaluationEnvironment();
	//	}
		return true;
	}
}
