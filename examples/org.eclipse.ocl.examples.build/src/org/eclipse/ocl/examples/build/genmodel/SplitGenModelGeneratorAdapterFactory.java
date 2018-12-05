/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
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
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreGenModelGeneratorAdapter;

/**
 * The Split GenModelGenerator supports splitting the interface classes off into a separate plugin identified by the
 * GenModel OCL_GENMODEL_SPLIT_URI INTERFACE_MODEL_DIRECTORY GenAnnotation.
 * @author Edward
 *
 */
public class SplitGenModelGeneratorAdapterFactory extends GenModelGeneratorAdapterFactory
{
	public static final @NonNull String OCL_GENMODEL_SPLIT_URI = OCLinEcoreGenModelGeneratorAdapter.OCL_GENMODEL_URI + "/Split";
	public static final @NonNull String INTERFACE_MODEL_DIRECTORY = "Interface Model Directory";

	/**
	 * Return a non-null model directory if a distinct directory has been specified for the interfaces as
	 * opposed to the standard model directory for the implementation.
	 * <p>
	 * This facility is not supported by the OCLinEcoreGenModelGeneratorAdapter which complements the standard
	 * Ecore or UML2 GenModelGeneratorAdapter. It is available when the SplitGenModelGeneratorAdapter has been
	 * registered in place of the Ecore GenModelGeneratorAdapter.
	 */
	public static @Nullable String getInterfaceModelDirectory(@NonNull GenModel genModel) {
		GenAnnotation genAnnotation = genModel.getGenAnnotation(OCL_GENMODEL_SPLIT_URI);
		if (genAnnotation != null) {
			EMap<String, String> details = genAnnotation.getDetails();
			return details.get(INTERFACE_MODEL_DIRECTORY);
		}
		return null;
	}

	/**
	 * A descriptor for this adapter factory, which can be used to
	 * programatically
	 * {@link org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry#addDescriptor(String, org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor)
	 * register} it.
	 * 
	 * @see org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory.Descriptor.Registry
	 */
	public static final GeneratorAdapterFactory.Descriptor DESCRIPTOR = new GeneratorAdapterFactory.Descriptor() {

		@Override
		public GeneratorAdapterFactory createAdapterFactory() {
			return new SplitGenModelGeneratorAdapterFactory();
		}
	};

	public SplitGenModelGeneratorAdapterFactory() {
		super();
	}

	/**
	 * Returns a singleton {@link GenClassGeneratorAdapter}.
	 */
	@Override
	public Adapter createGenClassAdapter() {
		if (genClassGeneratorAdapter == null) {
			genClassGeneratorAdapter = new SplitGenClassGeneratorAdapter(this);
		}
		return genClassGeneratorAdapter;
	}

	@Override
	public Adapter createGenEnumAdapter() {
		if (genEnumGeneratorAdapter == null) {
			genEnumGeneratorAdapter = new SplitGenEnumGeneratorAdapter(this);
		}
		return genEnumGeneratorAdapter;
	}
}
