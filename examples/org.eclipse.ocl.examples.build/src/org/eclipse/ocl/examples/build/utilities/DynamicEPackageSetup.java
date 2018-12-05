/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * Initializes Eclipse standalone support for dynamic packages.
 */
public class DynamicEPackageSetup
{
	private ResourceSet resourceSet;
	private Logger log = Logger.getLogger(getClass());
	
	private List<String> dynamicEPackage = new ArrayList<String>();

	public void addDynamicEPackage(String ePackageURI) {
		dynamicEPackage.add(ePackageURI);
		Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(ePackageURI, true), true);		// FIXME errors
		EPackage ePackage = (EPackage) resource.getContents().get(0);
		EPackage.Registry.INSTANCE.put(ePackage.getNsURI(), ePackage);
		log.info("Registering " + ePackage.getNsURI());
	}
	
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
}