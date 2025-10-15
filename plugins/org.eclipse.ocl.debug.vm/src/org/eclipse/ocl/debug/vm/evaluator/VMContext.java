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
package org.eclipse.ocl.debug.vm.evaluator;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.debug.vm.IVMDebuggerShell;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;

public class VMContext implements IVMContext
{
	protected final @NonNull EnvironmentFactory environmentFactory;
	private IVMDebuggerShell shell;

	public VMContext(@NonNull EnvironmentFactory environmentFactory) {
		this.environmentFactory = environmentFactory;
	}

	@Override
	public @NonNull EnvironmentFactory getEnvironmentFactory() {
		return environmentFactory;
	}

	@Override
	public @NonNull IVMDebuggerShell getShell() {
		return ClassUtil.requireNonNull(shell);
	}

	@Override
	public boolean keepDebug() {
		return true;
	}

	@Override
	public void setShell(@Nullable IVMDebuggerShell shell) {
		this.shell = shell;
	}
}
