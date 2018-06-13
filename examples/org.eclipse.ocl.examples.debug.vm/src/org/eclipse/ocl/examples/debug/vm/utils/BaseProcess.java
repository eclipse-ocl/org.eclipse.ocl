/*******************************************************************************
 * Copyright (c) 2014 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     R.Dvorak and others - QVTo debugger framework
 *     E.D.Willink - revised API for OCL debugger framework
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.vm.utils;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.debug.core.model.IProcess;
import org.eclipse.debug.core.model.IStreamsProxy;

public abstract class BaseProcess extends PlatformObject implements IProcess {
    
	public static interface IRunnable {
		
        public void run() throws Exception;
        
    }
    
	public void setStreamsProxy(IStreamsProxy streamsProxy) {
		myStreamsProxy = streamsProxy;
	}
	
    public IStreamsProxy getStreamsProxy() {
    	return myStreamsProxy;
    }
    
    private IStreamsProxy myStreamsProxy;
    
}
