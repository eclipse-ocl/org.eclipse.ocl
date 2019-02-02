/*******************************************************************************
 * Copyright (c) 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.genmodel;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jdt.annotation.NonNull;

/**
 * OCLGenModelGeneratorAdapterFactory is a hopefully temporary facility awaiting a fix for EMF Bug 543870.
 */
@Deprecated		/* @deprecated temporary workaround for Bug 543870 */
public class OCLGenModelGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory
{
	/**
	 * A descriptor for this adapter factory, which can be used to programatically
	 * {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry#addDescriptor(String, org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor) register}
	 * it.
	 * @see org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry
	 */
	public static final GeneratorAdapterFactory.@NonNull Descriptor DESCRIPTOR = new GeneratorAdapterFactory.Descriptor()
	{
		@Override
		public GeneratorAdapterFactory createAdapterFactory()
		{
			return new OCLGenModelGeneratorAdapterFactory();
		}
	};

	public OCLGenModelGeneratorAdapterFactory()
	{
		super();
	}

	@Override
	public Adapter createGenPackageAdapter() {
		if (genPackageGeneratorAdapter == null) {
			genPackageGeneratorAdapter = new OCLGenPackageGeneratorAdapter(this);
		}
		return genPackageGeneratorAdapter;
	}

	/**
	 * Returns a singleton {@link GenClassGeneratorAdapter}.
	 */
	@Override
	public Adapter createGenClassAdapter() {
		if (genClassGeneratorAdapter == null) {
			genClassGeneratorAdapter = new OCLGenClassGeneratorAdapter(this);
		}
		return genClassGeneratorAdapter;
	}

	@Override
	public Adapter createGenModelAdapter() {
		if (genModelGeneratorAdapter == null) {
			genModelGeneratorAdapter = new OCLGenModelGeneratorAdapter(this);
		}
		return genModelGeneratorAdapter;
	}
}
