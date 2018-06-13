/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   C.Damus, K.Hussey, E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.delegate;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.BasicSettingDelegate;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.evaluation.EvaluationException;
import org.eclipse.ocl.pivot.internal.messages.PivotMessagesInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.MetamodelManager;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.pivot.utilities.Query;
import org.eclipse.ocl.pivot.utilities.SemanticException;

/**
 * An implementation of a setting delegate that computes OCL derived features.
 */
public class OCLSettingDelegate extends BasicSettingDelegate.Stateless
{
	/**
	 * An implementation of a setting delegate that computes OCL derived features
	 * and caches explicitly changed values.
	 * 
	 * @since 3.5
	 */
	public static class Changeable extends OCLSettingDelegate
	{
		private Map<InternalEObject, Object> valueMap = null;

		public Changeable(@NonNull OCLDelegateDomain delegateDomain, @NonNull EStructuralFeature structuralFeature) {
			super(delegateDomain, structuralFeature);
		}

		@Override
		protected Object get(InternalEObject owner, boolean resolve, boolean coreType) {
			if ((valueMap != null) && valueMap.containsKey(owner)) {
				return valueMap.get(owner);
			}
			return super.get(owner, resolve, coreType);
		}

		@Override
		protected boolean isSet(InternalEObject owner) {
			return (valueMap != null) && valueMap.containsKey(owner);
		}

		@Override
		protected void set(InternalEObject owner, Object newValue) {
			if (owner != null) {
				if (valueMap == null) {
					valueMap = new HashMap<InternalEObject, Object>();
				}
				valueMap.put(owner, newValue);
			}
		}

		@Override
		protected void unset(InternalEObject owner) {
			if (valueMap != null) {
				valueMap.remove(owner);
			}
		}
	}
	
	protected final @NonNull OCLDelegateDomain delegateDomain;
	private Property property;
	private ExpressionInOCL query;

	/**
	 * Initializes me with my structural feature.
	 * 
	 * @param structuralFeature
	 *            the structural feature that I handle
	 */
	public OCLSettingDelegate(@NonNull OCLDelegateDomain delegateDomain, @NonNull EStructuralFeature structuralFeature) {
		super(structuralFeature);
		this.delegateDomain = delegateDomain;
	}

	protected @Nullable Object evaluateEcore(@NonNull OCL ocl, @NonNull ExpressionInOCL query, @Nullable Object target) {
		Query query2 = ocl.createQuery(query);
		return query2.evaluateEcore(eStructuralFeature.getEType().getInstanceClass(), target);
	}

	@Override
	protected Object get(InternalEObject owner, boolean resolve, boolean coreType) {
		try {
			OCL ocl = delegateDomain.getOCL();
			MetamodelManager metamodelManager = ocl.getMetamodelManager();
			ExpressionInOCL query2 = query;
			if (query2 == null) {
				Property property2 = getProperty();
				query2 = query = SettingBehavior.INSTANCE.getQueryOrThrow(metamodelManager, property2);
				SettingBehavior.INSTANCE.validate(property2);
			}
			Object ecoreResult = evaluateEcore(ocl, query2, owner);
			return ecoreResult;
		}
		catch (EvaluationException e) {
			throw new OCLDelegateException(new EvaluationException(e, PivotMessagesInternal.EvaluationResultIsInvalid_ERROR_, property));
		}
	}

	public @NonNull Property getProperty() {
		Property property2 = property;
		if (property2 == null) {
			property2 = property = delegateDomain.getPivot(Property.class, ClassUtil.nonNullEMF(eStructuralFeature));
			if (property2 == null) {
				throw new OCLDelegateException(new SemanticException("No pivot property for " + eStructuralFeature)) ;
			}
		}
		return property2;
	}

	@Override
	protected boolean isSet(InternalEObject owner) {
		return false; // derived features are, implicitly, never set
	}

	@Override
	public String toString() {
		if (property != null) {
			return "<" + delegateDomain.getURI() + ":setting> " + property; //$NON-NLS-1$ //$NON-NLS-2$
		}
		else {
			String name = eStructuralFeature.getEContainingClass().getEPackage().getName()
			+ "::" + eStructuralFeature.getEContainingClass().getName()
			+ "." + eStructuralFeature.getName();
			return "<" + delegateDomain.getURI() + ":setting> " + name; //$NON-NLS-1$ //$NON-NLS-2$
		}
	}
}
