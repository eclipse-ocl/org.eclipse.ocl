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
package org.eclipse.ocl.examples.codegen.genmodel;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenClassGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.jet.JETEmitter;

/**
 * OCLGenPackageGeneratorAdapter provides the redirection of overridden EMF codegen templates
 * to their OCL overrides.
 */
public class OCLGenClassGeneratorAdapter extends GenClassGeneratorAdapter
{
	public OCLGenClassGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	@Override
	protected void setStaticTemplateClass(JETEmitter jetEmitter, String className, String methodName, Class<?>[] arguments) {
		GenModelGeneratorAdapterFactory adapterFactory2 = (GenModelGeneratorAdapterFactory)adapterFactory;
		OCLGenModelGeneratorAdapter genModelAdapter = (OCLGenModelGeneratorAdapter)adapterFactory2.createGenModelAdapter();      // Use the GenModelAdapter to avoid multiple templatePath caches
		genModelAdapter.setStaticTemplateClass(jetEmitter, getUserTemplatePath(), className, methodName, arguments);
	}
}
