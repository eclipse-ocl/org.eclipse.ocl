/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.ecore.delegate;

import java.util.List;

import org.eclipse.emf.ecore.EPackage;

/**
 * @since 3.0
 */
public interface DelegatedBehavior<E, R, F>
{
	F getDefaultFactory();
	
	R getDefaultRegistry();
	
	EPackage getEPackage(E eObject);
	
	List<F> getFactories(E eObject);
	
	F getFactory(E eObject);
	
	Class<? extends F> getFactoryClass();

	String getName();
	
	Class<? extends R> getRegistryClass();

	void setDelegates(EPackage ePackage, List<String> delegateURIs);
}