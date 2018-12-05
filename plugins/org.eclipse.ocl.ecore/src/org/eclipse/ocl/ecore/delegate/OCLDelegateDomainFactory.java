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

import org.eclipse.emf.ecore.EPackage;

/**
 * Factory for OCL delegate domains.
 * 
 * @since 3.0
 */
public class OCLDelegateDomainFactory implements DelegateDomain.Factory
{	
	public OCLDelegateDomainFactory() {}

	public OCLDelegateDomain createDelegateDomain(String delegateURI, EPackage ePackage) {
		return new OCLDelegateDomain(delegateURI, ePackage);
	}
	
	/**
	 * Delegator provides a Factory entry that maps one delegate URI key to another.
	 * 
	 * @since 3.2
	 */
	public static class Delegator implements DelegateDomain.Factory
	{
		protected final DelegateDomain.Factory.Registry registry;
		
		public Delegator() {
			this(DelegateDomain.Factory.Registry.INSTANCE);
		}
		
		public Delegator(DelegateDomain.Factory.Registry registry) {
			this.registry = registry;
		}

		public DelegateDomain createDelegateDomain(String delegateURI, EPackage ePackage) {
			DelegateDomain.Factory factory = registry.getFactory(delegateURI);
			if (factory == null) {
				factory = OCLDelegateDomainFactory.INSTANCE;
			}
			return factory.createDelegateDomain(delegateURI, ePackage);
		}
	}
}
