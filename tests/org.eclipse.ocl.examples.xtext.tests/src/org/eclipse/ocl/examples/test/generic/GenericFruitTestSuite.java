/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Bug 254919; Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.examples.test.generic;

import java.util.ListIterator;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.examples.pivot.tests.PivotTestSuite;

/**
 * Generic extended test framework for tests using the Fruit meta-model.
 */
public abstract class GenericFruitTestSuite
	extends PivotTestSuite {
    
    protected static interface InitEnvironment {
    	void init();
    }

	/**
	 * Set this true to suppress a failure from modifying the fruitPackage
	 */
	protected boolean expectModified = false;

	public org.eclipse.ocl.pivot.Package fruitPackage;

	protected abstract void initFruitPackage();
	
	@Override
    protected void setUp() throws Exception {
		super.setUp();
		initFruitPackage();
//		if (environment instanceof InitEnvironment) {
//			((InitEnvironment)environment).init();
//		}
	}

	@Override
    protected void tearDown()
		throws Exception {
		final Resource resource = fruitPackage.eResource();
		final boolean isModified = resource.isModified();
		final boolean expectIsModified = expectModified;
		//
		//	Unload any resources that a test may have loaded.
		//
		for (ListIterator<Resource> i = resourceSet.getResources().listIterator(); i.hasNext(); ) {
			Resource res = i.next();
			if (((res == resource) && isModified)) {
				i.remove();
				res.unload();
                res.eAdapters().clear();
			}				
		}

		assertTrue(isModified == expectIsModified);	    
		super.tearDown();
	}
}
