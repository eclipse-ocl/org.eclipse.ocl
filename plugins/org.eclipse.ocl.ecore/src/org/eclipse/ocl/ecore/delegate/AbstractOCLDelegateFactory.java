/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 *   E.D.Willink - Bug 353171
 *******************************************************************************/
package org.eclipse.ocl.ecore.delegate;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.common.OCLConstants;

/**
 * Partial implementation of a factory of OCL delegates for Ecore features.
 * 
 * @since 3.0
 */
public abstract class AbstractOCLDelegateFactory
{
	protected final String delegateURI;
	protected OCLDelegateDomain delegateDomain;

	/**
	 * Construct a factory for an unknown delegate domain; often the global factory.
	 * 
	 * @deprecated Specify explicit delegateURI
	 */
	@Deprecated
	protected AbstractOCLDelegateFactory() {
		this.delegateURI = OCLConstants.OCL_DELEGATE_URI;
		this.delegateDomain = null;
	}

	/**
	 * Construct a factory for a known delegate domain.
	 * 
	 * @param delegateURI the delegate domain.
	 * @since 3.2
	 */
	protected AbstractOCLDelegateFactory(String delegateURI) {
		this.delegateURI = delegateURI;
		this.delegateDomain = null;
	}

	/**
	 * Construct a factory for a known delegate domain.
	 * 
	 * @param delegateDomain the delegate domain.
	 * @deprecated Use String argument to avoid leak hazards
	 */
	@Deprecated
	protected AbstractOCLDelegateFactory(OCLDelegateDomain delegateDomain) {
		this.delegateURI = delegateDomain.getURI();
		this.delegateDomain = delegateDomain;
	}

	protected OCLDelegateDomain getDelegateDomain(EPackage ePackage) {
		if (delegateDomain != null) {
			return delegateDomain;
		}
		else {
			DelegateEPackageAdapter ePackageAdapter = DelegateEPackageAdapter.getAdapter(ePackage);
			return (OCLDelegateDomain) ePackageAdapter.getDelegateDomain(delegateURI);
		}
	}

	/**
	 * @nooverride This is not intended to be overridden by clients.
	 */
	public String getURI() {
		return delegateURI;
	}

	/**
	 * Return the DelegateDomain for this package, creating one if it does not already exist. 
	 *
	 * @since 3.2
	 */
	protected OCLDelegateDomain loadDelegateDomain(EPackage ePackage) {
		if (delegateDomain != null) {
			return delegateDomain;
		}
		else {
			DelegateEPackageAdapter ePackageAdapter = DelegateEPackageAdapter.getAdapter(ePackage);
			return (OCLDelegateDomain) ePackageAdapter.loadDelegateDomain(delegateURI);
		}
	}
}
