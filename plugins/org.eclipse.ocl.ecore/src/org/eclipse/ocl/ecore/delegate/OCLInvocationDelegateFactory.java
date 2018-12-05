/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.ecore.delegate;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.common.delegate.DelegateResourceSetAdapter;

/**
 * Factory for OCL operation-invocation delegates.
 * 
 * @since 3.0
 */
public class OCLInvocationDelegateFactory extends AbstractOCLDelegateFactory
		implements EOperation.Internal.InvocationDelegate.Factory {
	
	/**
	 * Construct a factory for an unknown delegate domain; often the global factory.
	 * 
	 * @deprecated Specify explicit delegateURI
	 */
	@Deprecated
	public OCLInvocationDelegateFactory() {}
	
	/**
	 * Construct a factory for a known delegate domain.
	 * 
	 * @param delegateURI the delegate domain.
	 * @since 3.2
	 */
	public OCLInvocationDelegateFactory(String delegateURI) {
		super(delegateURI);
	}
	
	/**
	 * Construct a factory for a known delegate domain.
	 * 
	 * @param delegateDomain the delegate domain.
	 * @deprecated Use String argument to avoid leak hazards
	 */
	@Deprecated
	public OCLInvocationDelegateFactory(OCLDelegateDomain delegateDomain) {
		super(delegateDomain);
	}

	public EOperation.Internal.InvocationDelegate createInvocationDelegate(EOperation operation) {
		EPackage ePackage = operation.getEContainingClass().getEPackage();
		return new OCLInvocationDelegate(getDelegateDomain(ePackage), operation);
	}
	
	/**
	 * The Global variant of the Factory delegates to a local ResourceSet factory if one
	 * can be located at the EOperation.Internal.InvocationDelegate.Factory.Registry
	 * by the DelegateResourceSetAdapter.
	 */
	public static class Global extends OCLInvocationDelegateFactory
	{
		public Global() {
			super(OCLConstants.OCL_DELEGATE_URI_LPG);
		}

		public EOperation.Internal.InvocationDelegate createInvocationDelegate(EOperation operation) {
			EOperation.Internal.InvocationDelegate.Factory.Registry localRegistry = DelegateResourceSetAdapter.getRegistry(
				operation, EOperation.Internal.InvocationDelegate.Factory.Registry.class, null);
			if (localRegistry != null) {
				EOperation.Internal.InvocationDelegate.Factory factory = localRegistry.getFactory(delegateURI);
				if (factory != null) {
					return factory.createInvocationDelegate(operation);
				}
			}
			return super.createInvocationDelegate(operation);
		}	
	}
}
