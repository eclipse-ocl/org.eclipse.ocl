/*******************************************************************************
 * Copyright (c) 2013, 2019 Willink Transformations and others.
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
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;

public class OCLBuildGenModelGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory
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
			return new OCLBuildGenModelGeneratorAdapterFactory();
		}
	};

	public OCLBuildGenModelGeneratorAdapterFactory()
	{
		super();
	}

	@Override
	public Adapter createGenModelAdapter() {
		return null;
	}

	@Override
	public Adapter createGenPackageAdapter() {
		return null;
	}

	/**
	 * Returns a singleton {@link GenClassGeneratorAdapter}.
	 */
	@Override
	public Adapter createGenClassAdapter() {
		if (genClassGeneratorAdapter == null) {
			genClassGeneratorAdapter = new OCLBuildGenClassGeneratorAdapter(this);
		}
		return genClassGeneratorAdapter;
	}
}
