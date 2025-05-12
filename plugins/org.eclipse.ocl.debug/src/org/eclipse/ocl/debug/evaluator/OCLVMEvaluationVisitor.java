/*******************************************************************************
 * Copyright (c) 2014, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.debug.evaluator;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.debug.vm.evaluator.VMEvaluationStepper;
import org.eclipse.ocl.debug.vm.evaluator.VMEvaluationVisitor;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.util.AbstractMergedVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

public class OCLVMEvaluationVisitor extends AbstractMergedVisitor<@Nullable Object, @NonNull Executor> implements VMEvaluationVisitor, EvaluationVisitor.EvaluationVisitorExtension
{
	protected final @NonNull EvaluationVisitor evaluationVisitor;
	protected final @NonNull VMEvaluationStepper vmEvaluationStepper;

	protected OCLVMEvaluationVisitor(@NonNull VMEvaluationStepper vmEvaluationStepper, @NonNull EvaluationVisitor nestedEvaluationVisitor) {
		super(((EvaluationVisitor.EvaluationVisitorExtension)nestedEvaluationVisitor).getExecutor());
		this.evaluationVisitor = nestedEvaluationVisitor;
		this.vmEvaluationStepper = vmEvaluationStepper;
		nestedEvaluationVisitor.setUndecoratedVisitor(this);
	}

	@Override
	public @Nullable Object evaluate(@NonNull OCLExpression body) {
		return evaluationVisitor.evaluate(body);
	}

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return context.getEnvironmentFactory();
	}

	@Override
	public @NonNull EvaluationEnvironment getEvaluationEnvironment() {
		return context.getEvaluationEnvironment();
	}

	/** @deprecated Moved to Evaluator */
	@Deprecated
	@Override
	public @NonNull EvaluationVisitor getEvaluator() {
		return this;
	}

	@Override
	public @NonNull Executor getExecutor() {
		return context;
	}

	/** @deprecated moved to Executor */
	@Deprecated
	@Override
	public @NonNull MetamodelManager getMetamodelManager() {
		return context.getMetamodelManager();
	}

	@Override
	public @Nullable Monitor getMonitor() {
		return evaluationVisitor.getMonitor();
	}

	public @NonNull OCLVMEvaluationEnvironment getVMEvaluationEnvironment() {
		return (OCLVMEvaluationEnvironment) context.getEvaluationEnvironment();
	}

	@Override
	public @NonNull VMEvaluationStepper getVMEvaluationStepper() {
		return vmEvaluationStepper;
	}

	@Override
	public boolean isCanceled() {
		return evaluationVisitor.isCanceled();
	}

	@Override
	public void setCanceled(boolean isCanceled) {
		evaluationVisitor.setCanceled(isCanceled);
	}

	@Override
	public void setMonitor(@Nullable Monitor monitor) {
		evaluationVisitor.setMonitor(monitor);
	}

	@Override
	public void setUndecoratedVisitor(@NonNull EvaluationVisitor evaluationVisitor) {
		this.evaluationVisitor.setUndecoratedVisitor(evaluationVisitor);
	}

	@Override
	public String toString() {
		return getClass().getSimpleName();// + "(" + getDepth() + ")";
	}

	@Override
	public Object visitVariable(@NonNull Variable vd) {
		Object result = super.visitVariable(vd);
		Type declaredType = vd.getType();
		//		String name = vd.getName();
		EvaluationEnvironment env = getEvaluationEnvironment();
		env.replace(vd, declaredType);
		//		env.replace(name, env.getValueOf(name), declaredType);

		return result;
	}

	@Override
	public @Nullable Object visiting(@NonNull Visitable visitable) {
		return vmEvaluationStepper.visiting((Element) visitable);
	}
}
