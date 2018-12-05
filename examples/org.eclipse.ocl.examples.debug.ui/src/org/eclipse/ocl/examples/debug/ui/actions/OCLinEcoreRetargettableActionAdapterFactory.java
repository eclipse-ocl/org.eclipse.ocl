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
import org.eclipse.ocl.xtext.oclinecore.ui.OCLinEcoreEditor;

/**
 * Creates adapters for retargettable actions in debug platform.
 * Contributed via <code>org.eclipse.core.runtime.adapters</code> 
 * extension point. 
 */
public class OCLinEcoreRetargettableActionAdapterFactory implements IAdapterFactory
{	
	public OCLinEcoreRetargettableActionAdapterFactory() {
		super();
	}
	
	@SuppressWarnings("unchecked")
	public <T> @Nullable T getAdapter(Object adaptableObject, Class<T> adapterType) {
		if (!(adaptableObject instanceof OCLinEcoreEditor)) {
			return null;
        }
        if (IRunToLineTarget.class == adapterType) {
			return (T) new OCLinEcoreRunToLineAdapter();
        } else if (IToggleBreakpointsTarget.class == adapterType) {
			return (T) new OCLinEcoreToggleBreakpointAdapter();
        } 
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Class[] getAdapterList() {
		return new Class[] { OCLinEcoreToggleBreakpointAdapter.class, OCLinEcoreRunToLineAdapter.class };
	}
}
