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

import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ocl.examples.debug.core.OCLDebugCore;
import org.eclipse.ocl.examples.debug.core.OCLLineBreakpoint;
import org.eclipse.ocl.examples.debug.vm.core.VMLineBreakpoint;
import org.eclipse.ocl.examples.debug.vm.ui.actions.BreakpointLocationVerifier;
import org.eclipse.ocl.examples.debug.vm.ui.actions.VMToggleBreakpointAdapter;
import org.eclipse.ocl.examples.debug.vm.ui.messages.DebugVMUIMessages;
import org.eclipse.ocl.xtext.completeocl.ui.CompleteOCLEditor;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

public class CompleteOCLToggleBreakpointAdapter extends VMToggleBreakpointAdapter
{
	@Override
	public boolean canToggleLineBreakpoints(IWorkbenchPart part, ISelection selection) {
		return part instanceof CompleteOCLEditor;
	}

	@Override
	protected @NonNull BreakpointLocationVerifier createBreakpointLocationVerifier(@NonNull ITextEditor textEditor, @NonNull VMLineBreakpoint lineBreakpoint) {
		String invalidLocationMessage = DebugVMUIMessages.ToggleBreakpointAdapter_CannotSetBreakpoint;
		assert invalidLocationMessage != null;
		return new OCLBreakpointLocationVerifier(textEditor, lineBreakpoint, invalidLocationMessage);
	}

	@Override
	protected @NonNull OCLLineBreakpoint createLineBreakpoint(int lineNumber, @NonNull URI sourceURI) throws CoreException {
		return new OCLLineBreakpoint(sourceURI, lineNumber);
	}

	@Override
	protected @NonNull Object getBreakpointJobFamily(){
		return OCLLineBreakpoint.OCL_BREAKPOINT_JOBFAMILY;
	}

	@Override
	protected @NonNull List<@NonNull ILineBreakpoint> getOCLBreakpoints() {
		return OCLDebugCore.INSTANCE.getOCLBreakpoints(ILineBreakpoint.class);
	}

	@Override
	public void toggleLineBreakpoints(final IWorkbenchPart part, ISelection selection) throws CoreException {
		if (part instanceof CompleteOCLEditor) {
			super.toggleLineBreakpoints(part, selection);;
		}
	}
}
