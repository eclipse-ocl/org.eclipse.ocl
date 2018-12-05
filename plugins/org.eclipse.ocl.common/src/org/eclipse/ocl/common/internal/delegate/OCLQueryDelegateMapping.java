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

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.util.QueryDelegate;
import org.eclipse.ocl.common.delegate.VirtualDelegateMapping;
import org.eclipse.ocl.common.internal.options.CommonOptions;

/**
 * OCLQueryDelegateMapping provides a Factory entry that maps one delegate URI key to another.
 */
public class OCLQueryDelegateMapping implements QueryDelegate.Factory
{
	protected final QueryDelegate.Factory.Registry registry;
	protected final VirtualDelegateMapping virtualDelegateMapping;
	
	public OCLQueryDelegateMapping() {
		this(QueryDelegate.Factory.Registry.INSTANCE, CommonOptions.DEFAULT_DELEGATION_MODE);
	}
	
	public OCLQueryDelegateMapping(QueryDelegate.Factory.Registry registry, VirtualDelegateMapping virtualDelegateMapping) {
		this.registry = registry;
		this.virtualDelegateMapping = virtualDelegateMapping;
	}

	public QueryDelegate createQueryDelegate(EClassifier context,
			Map<String, EClassifier> parameters, String expression) {
		String delegateURI = virtualDelegateMapping.getPreferredValue();
		QueryDelegate.Factory factory = registry.getFactory(delegateURI);
		return factory.createQueryDelegate(context, parameters, expression);
	}
}