/*******************************************************************************
 * Copyright (c) 2015, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.genmodel.OCLGenModelUtil;

/**
 * Initializes the Ecore-based genmodel support for GenerateModel.mwe2. This includes registration of the
 * OCLBuildGenModelGenerator that supports extended Class generation such as Visitors and ModelSpecs
 * and the OCLinEcoreGenerator that support generation of OCL embedded in Ecore.
 * Use UMLGenModelSetup.mwe2 if a UML-based genmodel is in use.
 */
public class EcoreGenModelSetup
{
	private ResourceSet resourceSet = null;

	public EcoreGenModelSetup() {
		System.setProperty("line.separator", "\n");
	}

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	/**
	 * Define the ResourceSet and consequently ensure that it is initialized with GenModel declarations.
	 */
	public void setResourceSet(@NonNull ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
		resourceSet.getPackageRegistry().put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("genmodel", new EcoreResourceFactoryImpl());
		OCLGenModelUtil.initializeGeneratorAdapterFactoryRegistry();
		org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup.doSetup();
	}
}
