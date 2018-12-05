/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.ocl.examples.build.genmodel.SplitGenModelGeneratorAdapterFactory;

/**
 * Initializes UML genmodel support.
 */
public class SplitGenModelSetup
{
	public static final GeneratorAdapterFactory.Descriptor DESCRIPTOR = new GeneratorAdapterFactory.Descriptor() {
		@Override
		public GeneratorAdapterFactory createAdapterFactory() {
			return new SplitGenModelGeneratorAdapterFactory();
		}
	};

	private ResourceSet resourceSet = null;
	
	public SplitGenModelSetup() {
//		UMLImporter.class.getClass();		// Dummy reference to enforce class path
	}

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}
	
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
//		resourceSet.getPackageRegistry().put(GenModelPackage.eNS_URI, GenModelPackage.eINSTANCE);
		GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor(org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage.eNS_URI, DESCRIPTOR);
		GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor(org.eclipse.uml2.codegen.ecore.genmodel.GenModelPackage.eNS_URI, DESCRIPTOR);
	}
}
