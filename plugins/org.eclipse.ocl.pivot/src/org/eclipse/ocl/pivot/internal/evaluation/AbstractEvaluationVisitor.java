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
package org.eclipse.ocl.pivot.internal.evaluation;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.Constraint;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.complete.StandardLibraryInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.util.AbstractExtendingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.util.Visitor;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.ValueUtil;

/**
 * An evaluation visitor implementation for OCL expressions.
 * <p>
 * <b>Note</b> that this class is not intended to be used or extended by
 * clients.  Use the {@link EvaluationVisitor} interface, instead.
 * </p>
 */
public abstract class AbstractEvaluationVisitor
extends AbstractExtendingVisitor<@Nullable Object, @NonNull ExecutorInternal> implements EvaluationVisitor
{
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	/**
	 * @since 1.1
	 */
	protected final @NonNull IdResolver idResolver;
	protected final @NonNull StandardLibraryInternal standardLibrary;

	protected @NonNull EvaluationVisitor undecoratedVisitor;

	/**
	 * Set non-null by {@link #setMonitor} to terminate execution at next iteration/operation call.
	 */
	protected @Nullable Monitor monitor = null;

	/**
	 * @since 1.1
	 */
	protected AbstractEvaluationVisitor(@NonNull ExecutorInternal executor) {
		super(executor);
		this.environmentFactory = executor.getEnvironmentFactory();
		this.idResolver = environmentFactory.getIdResolver();
		this.standardLibrary = environmentFactory.getStandardLibrary();
		this.undecoratedVisitor = this;  // assume I have no decorator
	}

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	/**
	 * @since 1.1
	 */
	@Override
	public @NonNull ExecutorInternal getExecutor() {
		return context;
	}

	@Override
	public @Nullable Monitor getMonitor() {
		return monitor;
	}

	/**
	 * Obtains the visitor on which I perform nested
	 * {@link Visitable#accept(Visitor)} calls.  This
	 * handles the case in which I am decorated by another visitor that must
	 * intercept every <tt>visitXxx()</tt> method.  If I internally just
	 * recursively visit myself, then this decorator is cut out of the picture.
	 *
	 * @return my delegate visitor, which may be my own self or some other
	 */
	protected final @NonNull EvaluationVisitor getUndecoratedVisitor() {
		return undecoratedVisitor;
	}

	@Override
	public boolean isCanceled() {
		return (monitor != null) && monitor.isCanceled();
	}

	@Override
	public void setCanceled(boolean isCanceled) {
		if (monitor != null) {
			monitor.setCanceled(isCanceled);
		}
		else if (isCanceled) {
			monitor = new BasicMonitor();
			monitor.setCanceled(isCanceled);
		}
	}

	@Override
	public void setMonitor(@Nullable Monitor monitor) {
		this.monitor = monitor;
	}

	/**
	 * Sets the visitor on which I perform nested
	 * {@link Visitable#accept(Visitor)} calls.
	 *
	 * @param evaluationVisitor my delegate visitor
	 *
	 * @see #getUndecoratedVisitor()
	 */
	@Override
	public void setUndecoratedVisitor(@NonNull EvaluationVisitor evaluationVisitor) {
		this.undecoratedVisitor = evaluationVisitor;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (evaluation environment: ");//$NON-NLS-1$
		result.append(context.getEvaluationEnvironment());
		result.append(')');
		return result.toString();
	}

	@Override
	public @Nullable Object visit(@NonNull Visitable visitable) {
		return visitable.accept(undecoratedVisitor);
	}

	/**
	 * This default implementation asserts that the <tt>constraint</tt> is
	 * boolean-valued if it is an invariant, pre-condition, or post-condition
	 * constraint and returns the value of its body expression by delegation to
	 * {@link Visitable#accept(Visitor)}.
	 */
	@Override
	public Object visitConstraint(@NonNull Constraint constraint) {
		LanguageExpression specification = constraint.getOwnedSpecification();
		if (!(specification instanceof ExpressionInOCL)) {
			return null;
		}
		OCLExpression body = ((ExpressionInOCL)specification).getOwnedBody();
		//		boolean isBoolean = BOOLEAN_CONSTRAINTS.contains(constraint.getStereotype());

		if (body == null) {
			throw new IllegalArgumentException("constraint has no body expression"); //$NON-NLS-1$
		}

		//		if (isBoolean && !(body.getType() != metamodelManager.getBooleanType())) {
		//			throw new IllegalArgumentException("constraint is not boolean"); //$NON-NLS-1$
		//		}

		Object result = body.accept(undecoratedVisitor);
		//		try {
		//			if (result == null) {
		//				return evaluationEnvironment.throwInvalidEvaluation("null constraint result");
		//			}
		return ValueUtil.asBoolean(result);
		//		} catch (InvalidValueException e) {
		//			return e.getValue();
		//		}
	}
} //EvaluationVisitorImpl
