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

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.debug.ui.actions.IRunToLineTarget;
import org.eclipse.debug.ui.actions.IToggleBreakpointsTarget;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.xtext.completeocl.ui.CompleteOCLEditor;

/**
 * Creates adapters for retargettable actions in debug platform.
 * Contributed via <code>org.eclipse.core.runtime.adapters</code> 
 * extension point. 
 */
public class CompleteOCLRetargettableActionAdapterFactory implements IAdapterFactory
{	
	public CompleteOCLRetargettableActionAdapterFactory() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public <T> @Nullable T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (!(adaptableObject instanceof CompleteOCLEditor)) {
			return null;
        }
        if (IRunToLineTarget.class == adapterType) {
			return (T)new CompleteOCLRunToLineAdapter();
        } else if (IToggleBreakpointsTarget.class == adapterType) {
			return (T)new CompleteOCLToggleBreakpointAdapter();
        } 
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class[] getAdapterList() {
		return new Class[] { CompleteOCLToggleBreakpointAdapter.class, CompleteOCLRunToLineAdapter.class };
	}
}
