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
package org.eclipse.ocl.examples.debug.ui.pages;

import org.eclipse.core.resources.IStorage;
import org.eclipse.debug.core.model.ILineBreakpoint;
import org.eclipse.ocl.examples.debug.vm.ui.pages.VMDebugModelPresentation;
import org.eclipse.ocl.xtext.completeocl.ui.CompleteOCLEditor;
import org.eclipse.ocl.xtext.oclinecore.ui.OCLinEcoreEditor;
import org.eclipse.ui.IEditorInput;

public class OCLDebugModelPresentation extends VMDebugModelPresentation
{
	public String getEditorId(IEditorInput input, Object element) {
		if (element instanceof IStorage || element instanceof ILineBreakpoint) {
			if (input.getName().endsWith(".ecore")) {
				return OCLinEcoreEditor.EDITOR_ID;
			}
			else {
				return CompleteOCLEditor.EDITOR_ID;
			}
		}
		return null;
	}
}
