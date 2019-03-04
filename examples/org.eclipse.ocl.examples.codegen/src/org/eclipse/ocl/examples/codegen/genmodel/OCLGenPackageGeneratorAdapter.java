/*******************************************************************************
 * Copyright (c) 2015, 2019 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.genmodel;

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenPackageGeneratorAdapter;
import org.eclipse.emf.codegen.jet.JETEmitter;

//
//	Overridden to allow static templates to be invoked standalone.
//
@Deprecated		/* @deprecated temporary workaround for Bug 543870 */
public class OCLGenPackageGeneratorAdapter extends GenPackageGeneratorAdapter
{
	public OCLGenPackageGeneratorAdapter(GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	@Override
	protected void setStaticTemplateClass(JETEmitter jetEmitter, String className, String methodName, Class<?>[] arguments) {
		assert className != null;
		if (!getGenerator().getOptions().dynamicTemplates) {
			Class<?> templateClass = null;
			List<String> userTemplatePath = getUserTemplatePath();	// Omit the built-in emf.codegen.ecore to avoid caching; go direct to the fallback
			if (!userTemplatePath.isEmpty()) {
		        GenModelGeneratorAdapterFactory adapterFactory2 = (GenModelGeneratorAdapterFactory)adapterFactory;
		        OCLGenModelGeneratorAdapter genModelAdapter = (OCLGenModelGeneratorAdapter)adapterFactory2.createGenModelAdapter();      // Use the GenModelAdapter to avoid multiple templatePath caches
		        templateClass = genModelAdapter.getStaticUserTemplateClass(userTemplatePath, className);
			}
			try {
				if (templateClass == null) {							// Fall-back load as an ordinary class
					templateClass = getClass().getClassLoader().loadClass(className);
				}
				Method emitterMethod = templateClass.getDeclaredMethod(methodName, arguments);
				jetEmitter.setMethod(emitterMethod);
			}
			catch (Exception exception)
			{
				// It's okay for there not be a precompiled template, so fail quietly.
			}
		}
	}
}
