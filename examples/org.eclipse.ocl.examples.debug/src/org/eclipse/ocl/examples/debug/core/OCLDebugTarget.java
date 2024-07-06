/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.core;

import org.eclipse.debug.core.DebugEvent;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.debug.vm.IVMVirtualMachineShell;
import org.eclipse.ocl.examples.debug.vm.core.VMDebugTarget;

public class OCLDebugTarget extends VMDebugTarget
{
	public OCLDebugTarget(IProcess process, IVMVirtualMachineShell vm) {
		super(process, vm);
	}

	@Override
	public @NonNull OCLDebugCore getDebugCore() {
		return OCLDebugCore.INSTANCE;
	}

	@Override
	public @NonNull String getModelIdentifier() {
		return getDebugCore().getModelId();
	}

	@Override
	public boolean canTerminate() {
		// TODO Auto-generated method stub
		return super.canTerminate();
	}

	@Override
	public boolean isTerminated() {
		// TODO Auto-generated method stub
		return super.isTerminated();
	}

	@Override
	public void terminate() throws DebugException {
		// TODO Auto-generated method stub
		super.terminate();
	}

	@Override
	protected void terminated() {
		// TODO Auto-generated method stub
		super.terminated();
	}

	@Override
	public void fireEvent(DebugEvent event) {
		// TODO Auto-generated method stub
		super.fireEvent(event);
	}

	@Override
	public void fireChangeEvent(int detail) {
		// TODO Auto-generated method stub
		super.fireChangeEvent(detail);
	}

	@Override
	public void fireCreationEvent() {
		// TODO Auto-generated method stub
		super.fireCreationEvent();
	}

	@Override
	public void fireResumeEvent(int detail) {
		// TODO Auto-generated method stub
		super.fireResumeEvent(detail);
	}

	@Override
	public void fireSuspendEvent(int detail) {
		// TODO Auto-generated method stub
		super.fireSuspendEvent(detail);
	}

	@Override
	public void fireTerminateEvent() {
		// TODO Auto-generated method stub
		super.fireTerminateEvent();
	}
}
