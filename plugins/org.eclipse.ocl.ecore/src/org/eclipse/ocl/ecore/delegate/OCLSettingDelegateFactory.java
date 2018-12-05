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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.common.OCLConstants;
import org.eclipse.ocl.common.delegate.DelegateResourceSetAdapter;

/**
 * Factory for OCL derived-attribute setting delegates.
 * 
 * @since 3.0
 */
public class OCLSettingDelegateFactory extends AbstractOCLDelegateFactory
		implements EStructuralFeature.Internal.SettingDelegate.Factory
{
	/**
	 * Construct a factory for an unknown delegate domain; often the global factory.
	 * 
	 * @deprecated Specify explicit delegateURI
	 */
	@Deprecated
	public OCLSettingDelegateFactory() {}
	
	/**
	 * Construct a factory for a known delegate domain.
	 * 
	 * @param delegateURI the delegate domain.
	 * @since 3.2
	 */
	public OCLSettingDelegateFactory(String delegateURI) {
		super(delegateURI);
	}

	/**
	 * Construct a factory for a known delegate domain.
	 * 
	 * @param delegateDomain the delegate domain.
	 * @deprecated Use String argument to avoid leak hazards
	 */
	@Deprecated
	public OCLSettingDelegateFactory(OCLDelegateDomain delegateDomain) {
		super(delegateDomain);
	}

	public EStructuralFeature.Internal.SettingDelegate createSettingDelegate(EStructuralFeature structuralFeature) {
		EPackage ePackage = structuralFeature.getEContainingClass().getEPackage();
		if (structuralFeature.isChangeable() && !structuralFeature.isVolatile()) {
			return new OCLSettingDelegate.Changeable(getDelegateDomain(ePackage), structuralFeature);
		}
		else {
			return new OCLSettingDelegate(getDelegateDomain(ePackage), structuralFeature);
		}
	}
	
	/**
	 * The Global variant of the Factory delegates to a local ResourceSet factory if one
	 * can be located at the EStructuralFeature.Internal.SettingDelegate.Factory.Registry
	 * by the DelegateResourceSetAdapter.
	 */
	public static class Global extends OCLSettingDelegateFactory
	{
		public Global() {
			super(OCLConstants.OCL_DELEGATE_URI_LPG);
		}

		public EStructuralFeature.Internal.SettingDelegate createSettingDelegate(EStructuralFeature structuralFeature) {
			EStructuralFeature.Internal.SettingDelegate.Factory.Registry localRegistry = DelegateResourceSetAdapter.getRegistry(
				structuralFeature, EStructuralFeature.Internal.SettingDelegate.Factory.Registry.class, null);
			if (localRegistry != null) {
				EStructuralFeature.Internal.SettingDelegate.Factory factory = localRegistry.getFactory(delegateURI);
				if (factory != null) {
					return factory.createSettingDelegate(structuralFeature);
				}
			}
			return super.createSettingDelegate(structuralFeature);
		}	
	}
}
