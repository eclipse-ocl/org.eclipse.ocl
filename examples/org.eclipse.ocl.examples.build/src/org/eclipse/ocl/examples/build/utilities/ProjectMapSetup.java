/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;

/**
 * Initializes Eclipse standalone support.
 */
public class ProjectMapSetup
{
	private static ProjectMap projectMap;
	private Logger log = Logger.getLogger(getClass());

	public ProjectMapSetup() {
		if (projectMap == null) {
			log.info("Creating project map.");
			projectMap = new ProjectMap(false);
			projectMap.initializeResourceSet(null);
		}
		else {
			log.info("Reusing project map.");
		}
//		IProjectDescriptor projectDescriptor = projectMap.getProjectDescriptor("org.eclipse.ocl.pivot");
//		IPackageDescriptor packageDescriptor = projectDescriptor.getPackageDescriptor(URI.createURI(PivotPackage.eNS_URI));
//		packageDescriptor.setUseModel(true, ProjectMap.getPackageRegistry(null));
	}

	public void setResourceSet(ResourceSet resourceSet) {
		log.info("Applying project map");
		resourceSet.eAdapters().add(projectMap);
		ProjectMap.initializeURIResourceMap(resourceSet);
	}
}