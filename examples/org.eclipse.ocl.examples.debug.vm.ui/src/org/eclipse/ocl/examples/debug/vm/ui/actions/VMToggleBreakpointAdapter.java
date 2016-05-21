/*******************************************************************************
 * Copyright (c) 2014, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.vm.ui.actions;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ocl.examples.debug.core.OCLDebugCore;
import org.eclipse.ocl.examples.debug.core.OCLLineBreakpoint;
import org.eclipse.ocl.examples.debug.vm.core.VMLineBreakpoint;
import org.eclipse.ocl.examples.debug.vm.ui.DebugVMUIPlugin;
import org.eclipse.ocl.examples.debug.vm.ui.messages.DebugVMUIMessages;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.texteditor.ITextEditor;

public abstract class VMToggleBreakpointAdapter implements IToggleBreakpointsTarget
{
	public void toggleLineBreakpoints(final IWorkbenchPart part, ISelection selection) throws CoreException {
		final ITextEditor oclEditor = (ITextEditor)part;
		IResource unitFile = ClassUtil.getAdapter(oclEditor.getEditorInput(), IResource.class);
		if (unitFile == null) {
			return;
		}
		ITextSelection textSelection = (ITextSelection) selection;
		int lineNumber = textSelection.getStartLine() + 1;
		
		List<ILineBreakpoint> breakpoints = OCLDebugCore.INSTANCE.getOCLBreakpoints(ILineBreakpoint.class);
		for(ILineBreakpoint next : breakpoints) {			 
			if(!unitFile.equals(next.getMarker().getResource())) {
				continue;
			}

			if(next.getLineNumber() == lineNumber) {
				try {
					// a breakpoint already exists at this line =>toggle again means remove
					DebugPlugin.getDefault().getBreakpointManager().removeBreakpoint(next, true);
				} catch (CoreException e) {
					DebugVMUIPlugin.log(e.getStatus());
				}
				next.delete();
				return;
			}
		}

		@NonNull URI sourceURI = URI.createPlatformResourceURI(unitFile.getFullPath().toString(), true);
		final VMLineBreakpoint lineBreakpoint = new OCLLineBreakpoint(sourceURI, lineNumber);
		lineBreakpoint.register(true);
        
        Job job = new Job(DebugVMUIMessages.ToggleBreakpointAdapter_VerifyBreakpointJob) {
            @Override
			protected IStatus run(IProgressMonitor monitor) {
				return new BreakpointLocationVerifier(oclEditor, lineBreakpoint,
						DebugVMUIMessages.ToggleBreakpointAdapter_CannotSetBreakpoint).run();
            }
            
            @Override
            public boolean belongsTo(Object family) {
            	return OCLLineBreakpoint.OCL_BREAKPOINT_JOBFAMILY == family;
            }
        };
        
        job.setPriority(Job.INTERACTIVE);
        job.setSystem(true);
        job.schedule();        
	} 
		
	public void toggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
	}

	public boolean canToggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) {
		return false;
	}

	public void toggleWatchpoints(IWorkbenchPart part, ISelection selection) throws CoreException {
	}

	public boolean canToggleWatchpoints(IWorkbenchPart part, ISelection selection) {
		return false;
	}
}
