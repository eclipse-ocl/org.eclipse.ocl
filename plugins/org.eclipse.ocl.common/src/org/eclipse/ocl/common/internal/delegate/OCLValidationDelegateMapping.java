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

import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.ocl.common.delegate.VirtualDelegateMapping;
import org.eclipse.ocl.common.internal.options.CommonOptions;
import org.eclipse.ocl.common.preferences.PreferenceableOption;

/**
 * OCLValidationDelegateMapping provides a ValidationDelegate that maps one delegate URI key to another.
 */
public class OCLValidationDelegateMapping implements EValidator.ValidationDelegate, PreferenceableOption.Listener
{
	protected final EValidator.ValidationDelegate.Registry validationDelegateRegistry;
	protected final VirtualDelegateMapping virtualDelegateMapping;
	private EValidator.ValidationDelegate delegate = null;
	
	public OCLValidationDelegateMapping() {
		this(EValidator.ValidationDelegate.Registry.INSTANCE, CommonOptions.DEFAULT_DELEGATION_MODE);
	}
	
	public OCLValidationDelegateMapping(EValidator.ValidationDelegate.Registry validationDelegateRegistry, VirtualDelegateMapping virtualDelegateMapping) {
		this.validationDelegateRegistry = validationDelegateRegistry;
		this.virtualDelegateMapping = virtualDelegateMapping;
		virtualDelegateMapping.addListener(this);
	}

	public void changed(String key, Object oldValue, Object newValue) {
		reset();
	}
	
	public void reset() {
		delegate = null;
	}

	protected EValidator.ValidationDelegate resolveDelegate(Map<Object, Object> context) {
//		EValidator.ValidationDelegate.Registry validationDelegateRegistry = null;
//	    if (context != null)
//	    {
//	    	validationDelegateRegistry = (EValidator.ValidationDelegate.Registry)context.get(EValidator.ValidationDelegate.Registry.class);
//	    }
//	    if (validationDelegateRegistry == null) {
//	    	validationDelegateRegistry = EValidator.ValidationDelegate.Registry.INSTANCE;
//	    }
		String delegatedURI = virtualDelegateMapping.getPreferredValue();
		return validationDelegateRegistry.getValidationDelegate(delegatedURI);
	}

	public boolean validate(EClass eClass, EObject eObject,
			Map<Object, Object> context, EOperation invariant,
			String expression) {
		if (delegate == null) {
			delegate = resolveDelegate(context);
			if (delegate == null) {
				return false;
			}
		}
		return delegate.validate(eClass, eObject, context, invariant, expression);
	}

	public boolean validate(EClass eClass, EObject eObject,
			Map<Object, Object> context, String constraint,
			String expression) {
		if (delegate == null) {
			delegate = resolveDelegate(context);
			if (delegate == null) {
				return false;
			}
		}
		return delegate.validate(eClass, eObject, context, constraint, expression);
	}

	public boolean validate(EDataType eDataType, Object value,
			Map<Object, Object> context, String constraint,
			String expression) {
		if (delegate == null) {
			delegate = resolveDelegate(context);
			if (delegate == null) {
				return false;
			}
		}
		return delegate.validate(eDataType, value, context, constraint, expression);
	}
}