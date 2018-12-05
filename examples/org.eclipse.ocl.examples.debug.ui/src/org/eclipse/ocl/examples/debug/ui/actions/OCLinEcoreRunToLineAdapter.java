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
package org.eclipse.ocl.examples.debug.ui.actions;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.ISuspendResume;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ocl.examples.debug.core.OCLLineBreakpoint;
import org.eclipse.ocl.examples.debug.vm.core.VMLineBreakpoint;
import org.eclipse.ocl.examples.debug.vm.ui.actions.BreakpointLocationVerifier;
import org.eclipse.ocl.examples.debug.vm.ui.actions.VMRunToLineAdapter;
import org.eclipse.ocl.xtext.oclinecore.ui.OCLinEcoreEditor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class OCLinEcoreRunToLineAdapter extends VMRunToLineAdapter
{
	@Override
	public boolean canRunToLine(IWorkbenchPart part, ISelection selection, ISuspendResume target) {
		return (part instanceof OCLinEcoreEditor) && super.canRunToLine(part, selection, target);
	}

	@Override
	protected @NonNull BreakpointLocationVerifier createBreakpointLocationVerifier(@NonNull ITextEditor textEditor,
			@NonNull VMLineBreakpoint vmBreakpoint, @NonNull String invalidLocationMessage) {
		return new OCLBreakpointLocationVerifier(textEditor, vmBreakpoint, invalidLocationMessage);
	}

	@Override
	protected @NonNull VMLineBreakpoint createRunToLineBreakpoint(@NonNull URI resourceURI, int lineNumber) throws CoreException {
		return OCLLineBreakpoint.createRunToLineBreakpoint(resourceURI, lineNumber);
	}
}
