/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.debug.ui.delegate;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.delegate.AbstractOCLDelegateFactory;
import org.eclipse.ocl.pivot.internal.delegate.OCLDelegateDomain;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * Factory for OCL operation-invocation delegates.
 */
public class OCLDebugInvocationDelegateFactory extends AbstractOCLDelegateFactory
		implements EOperation.Internal.InvocationDelegate.Factory
{
	public OCLDebugInvocationDelegateFactory(@NonNull String delegateURI) {
		super(delegateURI);
	}

	public EOperation.Internal.@Nullable InvocationDelegate createInvocationDelegate(EOperation operation) {
		if (operation == null) {
			return null;
		}
		EPackage ePackage = ClassUtil.nonNullEMF(operation.getEContainingClass().getEPackage());
		OCLDelegateDomain delegateDomain = getDelegateDomain(ePackage);
		return delegateDomain != null ? new OCLDebugInvocationDelegate(delegateDomain, operation) : null;
	}
	
	/**
	 * The Global variant of the Factory delegates to a local ResourceSet factory if one
	 * can be located at the EOperation.Internal.InvocationDelegate.Factory.Registry
	 * by the DelegateResourceSetAdapter.
	 */
	public static class Global extends OCLDebugInvocationDelegateFactory
	{
		public Global() {
			super(PivotConstants.OCL_DELEGATE_URI_DEBUG);
		}

		@Override
		public EOperation.Internal.@Nullable InvocationDelegate createInvocationDelegate(EOperation operation) {
			if (operation == null) {
				return null;
			}
			Class<EOperation.Internal.InvocationDelegate.Factory.@NonNull Registry> castClass = EOperation.Internal.InvocationDelegate.Factory.Registry.class;
			EOperation.Internal.InvocationDelegate.Factory.@Nullable Registry localRegistry = OCLDelegateDomain.getDelegateResourceSetRegistry(operation, castClass, null);
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
