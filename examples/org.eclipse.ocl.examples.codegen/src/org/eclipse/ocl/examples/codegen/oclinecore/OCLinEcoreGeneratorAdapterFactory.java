/*******************************************************************************
 * Copyright (c) 2012, 2019 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapter;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jdt.annotation.NonNull;

/**
 * The OCLinEcoreGeneratorAdapterFactory registers the OCLinEcore code generation capabilities.
 * <p>
 * For Eclipse usage it is activated by the <tt>org.eclipse.emf.codegen.ecore.generatorAdapters</tt> extension point registration.
 * <p>
 * For standalone usage, {@link #install()} should be invoked.
 */
public class OCLinEcoreGeneratorAdapterFactory extends GenModelAdapterFactory implements GeneratorAdapterFactory
{
	/**
	 * A descriptor for this adapter factory, which can be used to programatically
	 * {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry#addDescriptor(String, org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor)
	 * register} it.
	 *
	 * @see org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry
	 */
	public static final GeneratorAdapterFactory.@NonNull Descriptor DESCRIPTOR = new GeneratorAdapterFactory.Descriptor()
	{
		@Override
		public GeneratorAdapterFactory createAdapterFactory() {
			return new OCLinEcoreGeneratorAdapterFactory();
		}
	};

	public static void install() {
		GeneratorAdapterFactory.Descriptor.Registry.INSTANCE.addDescriptor(GenModelPackage.eNS_URI, DESCRIPTOR);
	}

	protected Generator generator;
	protected GenBaseGeneratorAdapter genModelGeneratorAdapter;

	public OCLinEcoreGeneratorAdapterFactory() {
		super();
	}

	@Override
	public Adapter createGenModelAdapter() {
		if (genModelGeneratorAdapter == null) {
			genModelGeneratorAdapter = new OCLinEcoreGenModelGeneratorAdapter(this);
		}
		return genModelGeneratorAdapter;
	}

	@Override
	public void dispose() {
		if (genModelGeneratorAdapter != null) genModelGeneratorAdapter.dispose();
	}

	@Override
	public Generator getGenerator() {
		return generator;
	}

	@Override
	public void initialize(Object input) {}

	/**
	 * Returns <code>true</code> when the type is <code>GeneratorAdapter.class</code>.
	 */
	@Override
	public boolean isFactoryForType(Object type) {
		return type == GeneratorAdapter.class;
	}

	@Override
	public void setGenerator(Generator generator) {
		this.generator = generator;
	}
}
