/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.genmodel;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.uml2.codegen.ecore.genmodel.generator.UML2GenModelGeneratorAdapterFactory;

public class OCLBuildUMLGenModelGeneratorAdapterFactory extends UML2GenModelGeneratorAdapterFactory
{
  /**
   * A descriptor for this adapter factory, which can be used to programatically
   * {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry#addDescriptor(String, org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor) register}
   * it.
   * @see org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry
   */
  public static final GeneratorAdapterFactory.Descriptor DESCRIPTOR = new GeneratorAdapterFactory.Descriptor()
  {
    @Override
	public GeneratorAdapterFactory createAdapterFactory()
    {
      return new OCLBuildUMLGenModelGeneratorAdapterFactory();
    }
  };

  public OCLBuildUMLGenModelGeneratorAdapterFactory()
  {
    super();
  }

	@Override
	public Adapter createGenPackageAdapter() {
		if (genPackageGeneratorAdapter == null) {
			genPackageGeneratorAdapter = new OCLBuildUMLGenPackageGeneratorAdapter(this);
		}
		return genPackageGeneratorAdapter;
	}
}
