/*******************************************************************************
 * Copyright (c) 2014, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.evaluator;

import java.util.regex.Pattern;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.debug.OCLDebugPlugin;
import org.eclipse.ocl.examples.debug.vm.VMVirtualMachine;
import org.eclipse.ocl.examples.debug.vm.evaluator.IVMEvaluationEnvironment;
import org.eclipse.ocl.examples.debug.vm.utils.VMInterruptedExecutionException;
import org.eclipse.ocl.pivot.CompleteEnvironment;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.StandardLibrary;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.pivot.evaluation.EvaluationLogger;
import org.eclipse.ocl.pivot.evaluation.EvaluationVisitor;
import org.eclipse.ocl.pivot.evaluation.ModelManager;
import org.eclipse.ocl.pivot.ids.IdResolver;
import org.eclipse.ocl.pivot.internal.evaluation.OCLEvaluationVisitor;
import org.eclipse.ocl.pivot.util.AbstractWrappingVisitor;
import org.eclipse.ocl.pivot.util.Visitable;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;

public abstract class AbstractOCLVMEvaluationVisitor extends AbstractWrappingVisitor<Object, Object, IOCLVMEvaluationVisitor, Element> implements IOCLVMEvaluationVisitor
{
	protected AbstractOCLVMEvaluationVisitor(@NonNull IOCLVMEvaluationVisitor nestedEvaluationVisitor) {
		super(nestedEvaluationVisitor, new Object());
		delegate.setUndecoratedVisitor(this);
	}

	@Override
	public @NonNull EvaluationVisitor getClonedEvaluator() {
		IOCLVMEvaluationEnvironment oldEvaluationEnvironment = getVMEvaluationEnvironment();
		IOCLVMEvaluationEnvironment clonedEvaluationEnvironment = oldEvaluationEnvironment.createClonedEvaluationEnvironment();
		return new OCLEvaluationVisitor(clonedEvaluationEnvironment);
	}

	public abstract int getDepth();

	@Override
	public int getDiagnosticSeverity(int severityPreference, @Nullable Object resultValue) {
        return delegate.getDiagnosticSeverity(severityPreference, resultValue);
	}

	public @NonNull /*OCLVM*/EnvironmentFactory getEnvironmentFactory() {
		return /*(OCLVMEnvironmentFactory)*/ delegate.getEnvironmentFactory();
	}

	public @NonNull EvaluationEnvironment getEvaluationEnvironment() {
		return delegate.getEvaluationEnvironment();
	}

	public @NonNull IOCLVMEvaluationEnvironment getVMEvaluationEnvironment() {
		return (IOCLVMEvaluationEnvironment) delegate.getEvaluationEnvironment();
	}

	@Override
	public @NonNull String getPluginId() {
		return OCLDebugPlugin.PLUGIN_ID;
	}
	
//	@Override
//	protected void poppedStack() {
//		popLocation();
//	}
		
//	@Override
//	protected void pushedStack(IDebugEvaluationEnvironment env) {
//		Element currentIP = env.getCurrentIP();

//		UnitLocation startLocation = newLocalLocation(env, currentIP, ASTBindingHelper.getStartPosition(currentIP)); 
//				getEndPosition(currentIP) - getStartPosition(currentIP));

//		pushLocation(startLocation);
//	}


//	@Override
//	protected void addToEnv(String varName, Object value, EClassifier declaredType) {
//		getEvaluationEnvironment().add(varName, value, declaredType);
//	}

//	@Override
//	protected void replaceInEnv(String varName, Object value, EClassifier declaredType) {
//		getEvaluationEnvironment().replace(varName, value, declaredType);
//	}

//	@Override
	protected void processDeferredTasks() {
//		IDebugEvaluationEnvironment evalEnv = getEvaluationEnvironment();
//		Transformation transformation = evalEnv.getTransformation();
//		UnitLocation startLocation = newLocalLocation(evalEnv, transformation, ASTBindingHelper.getEndPosition(transformation));//, 0);
//		try {
//			pushLocation(startLocation);

//			superProcessDeferredTasks();
//		} finally {
//			popLocation();
//		}
	}

	@Override
	protected @Nullable Object badVisit(@NonNull Visitable visitable, @Nullable Element preState, @NonNull Throwable e) throws RuntimeException {
		if (e instanceof VMInterruptedExecutionException) {
			throw (VMInterruptedExecutionException)e;
		}
		Element element = (Element)visitable;
		IVMEvaluationEnvironment evalEnv = getVMEvaluationEnvironment();
		Object result = badVisit(evalEnv, element, preState, e);		// FIXME bad code exception here is confusing to user
		if (VMVirtualMachine.POST_VISIT.isActive()) {
			VMVirtualMachine.POST_VISIT.println("[" + Thread.currentThread().getName() + "] " + element.eClass().getName() + ": " + element.toString());
		}
		return result;
	}

	protected abstract @Nullable Object badVisit(@NonNull IVMEvaluationEnvironment evalEnv, @NonNull Element element, Object preState, @NonNull Throwable e);

	protected void superProcessDeferredTasks() {
		IVMEvaluationEnvironment evalEnv = getVMEvaluationEnvironment();
		evalEnv.processDeferredTasks();
	}

	@Override
	protected Object postVisit(@NonNull Visitable visitable, @Nullable Element prologue, @Nullable Object result) {
		Element element = (Element)visitable;
		if (VMVirtualMachine.POST_VISIT.isActive()) {
			VMVirtualMachine.POST_VISIT.println("[" + Thread.currentThread().getName() + "] " + element.eClass().getName() + ": " + element.toString() + " => " + result);
		}
//		setCurrentEnvInstructionPointer(zzparentElement);
		IVMEvaluationEnvironment evalEnv = getVMEvaluationEnvironment();
		postVisit(evalEnv, element, result);
		return result;
	}

	protected abstract void postVisit(@NonNull IVMEvaluationEnvironment evalEnv, @NonNull Element element, @Nullable Object result);

	@Override
	protected @Nullable Element preVisit(@NonNull Visitable visitable) {
		Element element = (Element)visitable;
		if (VMVirtualMachine.PRE_VISIT.isActive()) {
			VMVirtualMachine.PRE_VISIT.println("[" + Thread.currentThread().getName() + "] " + element.eClass().getName() + ": " + element.toString());
		}
		Element previousIP = setCurrentEnvInstructionPointer(null/*element*/);
		IVMEvaluationEnvironment evalEnv = getVMEvaluationEnvironment();
		preVisit(evalEnv, element);
		return previousIP;
	}

	protected abstract Object preVisit(@NonNull IVMEvaluationEnvironment evalEnv, @NonNull Element element);

//	private void pushLocation(UnitLocation location) {
//		fLocationStack.add(0, location);
//	}

//	private UnitLocation popLocation() {
//		UnitLocation removed = fLocationStack.remove(0);
//		return removed;
//	}
	
//	@Override
//	protected InternalEvaluator createInterruptibleVisitor() {
//		return (DebugInterceptor)createDebugInterceptor();
//	}
	   
    protected Element setCurrentEnvInstructionPointer(Element element) {
		IVMEvaluationEnvironment evalEnv = getVMEvaluationEnvironment();
    	if (element != null) {
    		return evalEnv.setCurrentIP(element);
    	}
    	else {
    		return evalEnv.getCurrentIP();
    	}
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
	public @NonNull IOCLVMEvaluationVisitor createNestedEvaluator() { // FIXME Pass 'operation'
//		return delegate.createNestedEvaluator();
		return new OCLVMNestedEvaluationVisitor(this, delegate.createNestedEvaluator());
	}

	@Override
	public @Nullable Object evaluate(@NonNull OCLExpression body) {
		return delegate.evaluate(body);
	}

	@Override
	public @NonNull CompleteEnvironment getCompleteEnvironment() {
		return delegate.getCompleteEnvironment();
	}

	@Override
	public @NonNull EvaluationVisitor getEvaluator() {
		return delegate.getEvaluator();
	}

	@Override
	public @NonNull ModelManager getModelManager() {
		return delegate.getModelManager();
	}

	@Override
	public @NonNull MetamodelManager getMetamodelManager() {
		return delegate.getMetamodelManager();
	}

	@Override
	public int getSeverity(@Nullable Object validationKey) {
        return delegate.getSeverity(validationKey);
	}

	@Override
	public @NonNull StandardLibrary getStandardLibrary() {
		return delegate.getStandardLibrary();
	}

	@Override
	public void setUndecoratedVisitor(@NonNull EvaluationVisitor evaluationVisitor) {
		delegate.setUndecoratedVisitor(evaluationVisitor);
	}

	@Override
	public @NonNull IdResolver getIdResolver() {
		return delegate.getIdResolver();
	}

	@Override
	public @Nullable EvaluationLogger getLogger() {
		return delegate.getLogger();
	}

	public @Nullable Monitor getMonitor() {
		return delegate.getMonitor();
	}

	@Override
	public @NonNull Pattern getRegexPattern(@NonNull String regex) {
		return delegate.getRegexPattern(regex);
	}

	public abstract @NonNull OCLVMRootEvaluationVisitor getVMRootEvaluationVisitor();
 
	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value) {
		return delegate.getStaticTypeOf(value);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @NonNull Object... values) {
		return delegate.getStaticTypeOf(value, values);
	}

	@Override
	public org.eclipse.ocl.pivot.@NonNull Class getStaticTypeOf(@Nullable Object value, @NonNull Iterable<?> values) {
		return delegate.getStaticTypeOf(value, values);
	}

	@Override
	public boolean isCanceled() {
		return delegate.isCanceled();
	}

	@Override
	public void setCanceled(boolean isCanceled) {
		delegate.setCanceled(isCanceled);
	}

	@Override
	public void setLogger(@Nullable EvaluationLogger logger) {
		delegate.setLogger(logger);
	}

	public void setMonitor(@Nullable Monitor monitor) {
		delegate.setMonitor(monitor);
	}

//	@Override
//	public @Nullable Object visitTransformation(@NonNull Transformation object) {
//	    try {
//	    	pushedStack(getEvaluationEnvironment());
//			return super.visitTransformation(object);
//	    }
//	    finally {
//	    	poppedStack();
//	    }
//	}

/*	@Override
	public @Nullable Object visitIterateExp(@NonNull IterateExp object) {
		// TODO Auto-generated method stub
		return super.visitIterateExp(object);
	}

	@Override
	public @Nullable Object visitIteratorExp(@NonNull IteratorExp object) {
		// TODO Auto-generated method stub
		return super.visitIteratorExp(object);
	}

	@Override
	public @Nullable Object visitOperationCallExp(@NonNull OperationCallExp object) {
		// TODO Auto-generated method stub
		return super.visitOperationCallExp(object);
	}

	@Override
	public @Nullable Object visitPropertyCallExp(@NonNull PropertyCallExp object) {
		pushStepper(object, PropertyCallExpStepper.INSTANCE);
		try {
			return super.visitPropertyCallExp(object);
		}
		finally {
			popStepper();
		}
	} */

	@Override
	public String toString() {
		return getClass().getSimpleName() + "(" + getDepth() + ")";
	}
}
