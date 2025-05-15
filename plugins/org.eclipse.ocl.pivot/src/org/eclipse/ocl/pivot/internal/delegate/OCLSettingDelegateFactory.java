/*******************************************************************************
 * Copyright (c) 2010, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotConstants;

/**
 * Factory for OCL derived-attribute setting delegates.
 * @since 1.23
 */
public class OCLSettingDelegateFactory extends AbstractOCLDelegateFactory
		implements EStructuralFeature.Internal.SettingDelegate.Factory
{
	@Deprecated
	public OCLSettingDelegateFactory(@NonNull String delegateURI) {
		super(delegateURI);
	}

	/**
	 * @since 7.0
	 */
	public OCLSettingDelegateFactory(@NonNull String delegateURI, boolean isGlobal) {
		super(delegateURI, isGlobal);
	}

	@Override
	public EStructuralFeature.Internal.@Nullable SettingDelegate createSettingDelegate(EStructuralFeature structuralFeature) {
		if (structuralFeature == null) {
			return null;
		}
		if (isGlobal) {
			Class<EStructuralFeature.Internal.SettingDelegate.Factory.@NonNull Registry> castClass = EStructuralFeature.Internal.SettingDelegate.Factory.Registry.class;
			EStructuralFeature.Internal.SettingDelegate.Factory.@Nullable Registry localRegistry = OCLDelegateDomain.getDelegateResourceSetRegistry(structuralFeature, castClass, null);
			if (localRegistry != null) {
				EStructuralFeature.Internal.SettingDelegate.Factory factory = localRegistry.getFactory(delegateURI);
				if (factory != null) {
					return factory.createSettingDelegate(structuralFeature);
				}
			}
		}
		EPackage ePackage = structuralFeature.getEContainingClass().getEPackage();
		OCLDelegateDomain delegateDomain = getDelegateDomain(ClassUtil.requireNonNull(ePackage));
		if (delegateDomain == null) {
			return null;
		}
		if (structuralFeature.isChangeable() && !structuralFeature.isVolatile()) {
			return new OCLSettingDelegate.Changeable(delegateDomain, structuralFeature);
		}
		else {
			return new OCLSettingDelegate(delegateDomain, structuralFeature);
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
			super(PivotConstants.OCL_DELEGATE_URI_PIVOT, true);
		}
	}
}
