/**
 * <copyright>
 *
 * Copyright (c) 2012, 2013 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 */
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

	@Override
	public QueryDelegate createQueryDelegate(EClassifier context,
			Map<String, EClassifier> parameters, String expression) {
		String delegateURI = virtualDelegateMapping.getPreferredValue();
		QueryDelegate.Factory factory = registry.getFactory(delegateURI);
		return factory.createQueryDelegate(context, parameters, expression);
	}
}