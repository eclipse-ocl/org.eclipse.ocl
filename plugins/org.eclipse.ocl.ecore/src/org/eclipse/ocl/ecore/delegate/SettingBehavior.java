/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   E.D.Willink - Initial API and implementation
 *   E.D.Willink - Bug 360072
 *******************************************************************************/
package org.eclipse.ocl.ecore.delegate;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Internal.SettingDelegate;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.common.OCLCommon;
import org.eclipse.ocl.common.delegate.DelegateResourceSetAdapter;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.ExpressionInOCL;
import org.eclipse.ocl.ecore.OCL;
import org.eclipse.ocl.ecore.OCLExpression;

/**
 * @since 3.0
 */
public class SettingBehavior extends AbstractDelegatedBehavior<EStructuralFeature, SettingDelegate.Factory.Registry, SettingDelegate.Factory>
{
	public static final SettingBehavior INSTANCE = new SettingBehavior();
	public static final String DERIVATION_CONSTRAINT_KEY = "derivation"; //$NON-NLS-1$
	public static final String INITIAL_CONSTRAINT_KEY = "initial"; //$NON-NLS-1$
	public static final String NAME = "settingDelegates"; //$NON-NLS-1$
	
	/**
	 * Creates an {@link org.eclipse.ocl.ecore.delegate.AbstractDelegatedBehavior.ExpressionCacheAdapter ExpressionCacheAdapter} for expression <code>e</code> and adds
	 * it to <code>property</code>'s adapter list so that {@link #getCachedOCLExpression(EStructuralFeature)}
	 * will return <code>e</code> when called for <code>property</code>. To achieve this, any other
	 * {@link org.eclipse.ocl.ecore.delegate.AbstractDelegatedBehavior.ExpressionCacheAdapter ExpressionCacheAdapter} in <code>property</code>'s adapter list is removed.
	 * 
	 * @param e if <code>null</code>, any existing cache entry is removed and no new entry
	 * is created. {@link #getCachedOCLExpression(EStructuralFeature)} will then return <code>null</code>. 
	 * 
	 * @since 3.1
	 */
	public void cacheOCLExpression(EStructuralFeature property, OCLExpression e) {
		ExpressionCacheAdapter.cacheOCLExpression(property, e);
	}

	/**
	 * Looks for an {@link org.eclipse.ocl.ecore.delegate.AbstractDelegatedBehavior.ExpressionCacheAdapter ExpressionCacheAdapter} attached to <code>property</code>.
	 * If such an adapter is found, its cached expression is returned. The cached expression
	 * may be a reserved expression indicating that no OCL expression exists and that an
	 * unsuccessful attempt to obtain one has been made before.
	 * {@link #isNoOCLDefinition(OCLExpression)} should be used to check for the reserved expression.
	 * null is returned if no cached expression is available.
	 * 
	 * @since 3.1
	 */
	public OCLExpression getCachedOCLExpression(EStructuralFeature property) {
		return ExpressionCacheAdapter.getCachedOCLExpression(property);
	}

	public SettingDelegate.Factory getDefaultFactory() {
		return SettingDelegate.Factory.Registry.INSTANCE.getFactory(getName());
	}

	public SettingDelegate.Factory.Registry getDefaultRegistry() {
		return SettingDelegate.Factory.Registry.INSTANCE;
	}

	public EPackage getEPackage(EStructuralFeature eStructuralFeature) {
		return eStructuralFeature.getEContainingClass().getEPackage();
	}

	public SettingDelegate.Factory getFactory(DelegateDomain delegateDomain, EStructuralFeature eStructuralFeature) {
		SettingDelegate.Factory.Registry registry = DelegateResourceSetAdapter.getRegistry(
			eStructuralFeature, getRegistryClass(), getDefaultRegistry());
	    return registry.getFactory(delegateDomain.getURI());
	}	

	public Class<SettingDelegate.Factory> getFactoryClass() {
		return SettingDelegate.Factory.class;
	}

	/**
	 * Return the feature body associated with structuralFeature, if necessary using ocl to
	 * create the relevant parsing environment for a textual definition..
	 */
	public OCLExpression getFeatureBody(OCL ocl, EStructuralFeature structuralFeature) {
		OCLExpression result = ExpressionCacheAdapter.getCachedOCLExpression(structuralFeature);
		if (result != null){
			return result != NO_OCL_DEFINITION ? result : null;
		}
	    EAnnotation eAnnotation = OCLCommon.getDelegateAnnotation(structuralFeature);
	    if (eAnnotation == null) {
			cacheOCLExpression(structuralFeature, NO_OCL_DEFINITION);
	    	return null;
	    }
	    EMap<String, String> details = eAnnotation.getDetails();
		String expr = details.get(DERIVATION_CONSTRAINT_KEY);
		if (expr == null) {
			expr = details.get(INITIAL_CONSTRAINT_KEY);
			if (expr == null) {
				return null;
			}
		}
		EClass context = structuralFeature.getEContainingClass();
		OCL.Helper helper = ocl.createOCLHelper();
		helper.setAttributeContext(context, structuralFeature);
		Constraint constraint;
		try {
			constraint = helper.createDerivedValueExpression(expr);
		} catch (ParserException e) {
			throw new org.eclipse.ocl.ecore.delegate.OCLDelegateException(e.getLocalizedMessage(), e);
		}
		if (constraint == null) {
			return null;
		}
		ExpressionInOCL specification = (ExpressionInOCL) constraint.getSpecification();
		if (specification == null) {
			return null;
		}
		OCLExpression body = (OCLExpression) specification.getBodyExpression();
		cacheOCLExpression(structuralFeature, body);
		return body;
	}
	
	public String getName() {
		return NAME;
	}

	public Class<SettingDelegate.Factory.Registry> getRegistryClass() {
		return SettingDelegate.Factory.Registry.class;
	}
	
	/**
	 * Tells if there is a textual expression for the <code>structuralFeature</code> in an
	 * annotation that can be compiled by {@link #getFeatureBody(OCL, EStructuralFeature)}. Probing
	 * this saves callers the more expensive construction of an {@link OCL} object.
	 * @since 3.1
	 */
	public boolean hasCompileableFeatureBody(EStructuralFeature structuralFeature) {
		return OCLCommon.getDelegateAnnotation(structuralFeature) != null;
	}
}