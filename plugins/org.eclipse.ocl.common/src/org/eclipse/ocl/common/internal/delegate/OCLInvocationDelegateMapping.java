/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.common.internal.delegate;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EOperation.Internal.InvocationDelegate;
import org.eclipse.ocl.common.delegate.VirtualDelegateMapping;
import org.eclipse.ocl.common.internal.options.CommonOptions;

/**
 * OCLInvocationDelegateMapping provides a Factory entry that maps one delegate URI key to another.
 */
public class OCLInvocationDelegateMapping implements EOperation.Internal.InvocationDelegate.Factory
{
	protected final EOperation.Internal.InvocationDelegate.Factory.Registry registry;
	protected final VirtualDelegateMapping virtualDelegateMapping;
	
	public OCLInvocationDelegateMapping() {
		this(EOperation.Internal.InvocationDelegate.Factory.Registry.INSTANCE, CommonOptions.DEFAULT_DELEGATION_MODE);
	}
	
	public OCLInvocationDelegateMapping(EOperation.Internal.InvocationDelegate.Factory.Registry registry, VirtualDelegateMapping virtualDelegateMapping) {
		this.registry = registry;
		this.virtualDelegateMapping = virtualDelegateMapping;
	}

	public InvocationDelegate createInvocationDelegate(EOperation operation) {
		String delegateURI = virtualDelegateMapping.getPreferredValue();
		EOperation.Internal.InvocationDelegate.Factory factory = registry.getFactory(delegateURI);
		return factory != null ? factory.createInvocationDelegate(operation) : null;
	}
}