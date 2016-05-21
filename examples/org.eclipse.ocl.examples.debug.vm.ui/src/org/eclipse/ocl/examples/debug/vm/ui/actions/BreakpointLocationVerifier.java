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

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ocl.examples.debug.launching.OCLDebuggableRunnerFactory;
import org.eclipse.ocl.examples.debug.vm.ui.DebugVMUIPlugin;
import org.eclipse.ocl.examples.debug.vm.utils.CompiledUnit;
import org.eclipse.ocl.examples.debug.vm.utils.LineNumberProvider;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.IEditorStatusLine;
import org.eclipse.ui.texteditor.ITextEditor;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.util.concurrent.IUnitOfWork;

class BreakpointLocationVerifier {
	
	private static @NonNull LineNumberProvider getLineNumberProvider(final @NonNull IDocument doc) {
		
		return new LineNumberProvider() {
			
			public int getLineNumber(int offset) {
				try {
					return doc.getLineOfOffset(offset) + 1;
				} catch (BadLocationException e) {
					return -1;
				}
			}
			
			public int getLineEnd(int lineNumber) {
				try {
					IRegion lineInfo = doc.getLineInformation(lineNumber);
					return lineInfo.getOffset() + lineInfo.getLength();
				} catch (BadLocationException e) {
					return -1;
				}
			}

			public int getLineCount() {
				return doc.getNumberOfLines();
			}
		};
	}		
	
//	private static final int GET_AST_TIMEOUT = 10 * 1000;
	
	private final ILineBreakpoint fBreakpoint;
	private final ITextEditor fEditor;
	private final String fInvalidLocationMessage;
	
	BreakpointLocationVerifier(ITextEditor editor, ILineBreakpoint breakpoint, String invalidLocationMessage) {
		if(editor == null || breakpoint == null || invalidLocationMessage == null) {
			throw new IllegalArgumentException();
		}
		
		fEditor = editor; 			
		fBreakpoint = breakpoint;
		fInvalidLocationMessage = invalidLocationMessage; 			
	}
	
	IStatus run()  {
		IStatus status = checkBreakpointableElements();
		if(!status.isOK()) {				
			if (fBreakpoint != null) {
				try {
					DebugPlugin.getDefault().getBreakpointManager().removeBreakpoint(fBreakpoint, true);
				} catch (CoreException e) {
					DebugVMUIPlugin.log(e.getStatus());
				}
			}
		}
		return status;
	}
	
	private IStatus checkBreakpointableElements()  {
		int lineNumber;
		try {
			lineNumber = fBreakpoint.getLineNumber();
		} catch (CoreException e) {
			return e.getStatus();
		}
		
		IDocumentProvider docProvider = fEditor.getDocumentProvider();
		if(docProvider == null) {
			return canceled();
		}
		
		IDocument doc = docProvider.getDocument(fEditor.getEditorInput());
		if(doc == null) {
			return canceled();
		}
		Model root = ((XtextDocument)doc).readOnly(new IUnitOfWork<Model, XtextResource>()
		{
			@Override
			public Model exec(@Nullable XtextResource state) throws Exception {
				if (state instanceof BaseCSResource) {
					BaseCSResource csResource = (BaseCSResource)state;
					CS2AS cs2as = csResource.findCS2AS();
					if (cs2as != null) {
						ASResource asResource = cs2as.getASResource();
						if ((asResource != null) && (asResource.getContents().size() > 0)) {
							EObject eObject = asResource.getContents().get(0);
							if (eObject instanceof Model) {
								return (Model)eObject;
							}
						}
					}
				}
				return null;
			}
		});
        CompiledUnit compilationUnit = root != null ? new CompiledUnit(root) : null;
        if(compilationUnit == null) {
        	return DebugVMUIPlugin.createErrorStatus("Failed to obtain AST"); //$NON-NLS-1$
        }

		List<Element> elements = OCLDebuggableRunnerFactory.validBreakpointLocator
				.getBreakpointableElementsForLine(compilationUnit, getLineNumberProvider(doc), lineNumber);
		if (elements.isEmpty()) {
            report(NLS.bind(fInvalidLocationMessage, new Integer(lineNumber)));
            return canceled();
		}

		return Status.OK_STATUS;
	}
		
	/**
	 * Reports any status to the current active workbench shell
	 * @param message the message to display
	 */
	protected void report(final String message) {
		DebugVMUIPlugin.getStandardDisplay().asyncExec(new Runnable() {
			public void run() {
				@Nullable IEditorStatusLine statusLine = (IEditorStatusLine) fEditor.getAdapter(IEditorStatusLine.class);
				if (statusLine != null) {
					statusLine.setMessage(true, message, null);
				}
				if (message != null && DebugVMUIPlugin.getActiveWorkbenchShell() != null) {
					Display.getCurrent().beep();
				}
			}
		});
	}
	
	private IStatus canceled() {
		return DebugVMUIPlugin.createStatus(IStatus.CANCEL, fInvalidLocationMessage);
	}
}