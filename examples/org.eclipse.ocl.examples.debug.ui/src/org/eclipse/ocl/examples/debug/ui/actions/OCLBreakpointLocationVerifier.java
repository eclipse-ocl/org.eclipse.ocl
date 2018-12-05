/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.ui.actions;

import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.debug.launching.OCLDebuggableRunnerFactory;
import org.eclipse.ocl.examples.debug.vm.ValidBreakpointLocator;
import org.eclipse.ocl.examples.debug.vm.ui.actions.BreakpointLocationVerifier;
import org.eclipse.ui.texteditor.ITextEditor;

public class OCLBreakpointLocationVerifier extends BreakpointLocationVerifier
{
	protected OCLBreakpointLocationVerifier(@NonNull ITextEditor editor, @NonNull ILineBreakpoint breakpoint, @NonNull String invalidLocationMessage) {
		super(editor, breakpoint, invalidLocationMessage);
	}

	@Override
	protected @NonNull ValidBreakpointLocator getValidBreakpointLocator() {
		return OCLDebuggableRunnerFactory.validBreakpointLocator;
	}
}