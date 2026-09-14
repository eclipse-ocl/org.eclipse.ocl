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

import java.lang.reflect.Method;
import java.util.List;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;

//
//Overridden to redirect overridden EMF codegen templates to their OCL overrides.
//
public class OCLGenModelGeneratorAdapter extends GenModelGeneratorAdapter
{
	/**
	 * Cached sorted standalone classpath entries.
	 */
	private @NonNull String[] sortedClasspath = null;

	public OCLGenModelGeneratorAdapter(@NonNull GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	/**
	 * If {@link Generator.Options#dynamicTemplates dynamic templates} are not being used,
	 * attempts to set the emitter to use an existing, precompiled template class
	 * that has the given method name and argument types.
	 * @since 2.5
	 */
	@Override
	protected void setStaticTemplateClass(JETEmitter jetEmitter, String className, String methodName, Class<?>[] arguments)
	{
		List<String> userTemplatePath = getUserTemplatePath();	// Omit the built-in emf.codegen.ecore to avoid caching; go direct to the fallback
		setStaticTemplateClass(jetEmitter, userTemplatePath, className, methodName, arguments);
	}
	protected void setStaticTemplateClass(JETEmitter jetEmitter, List<String> userTemplatePaths, String className, String methodName, Class<?>[] arguments)
	{
		assert className != null;
		if (!getGenerator().getOptions().dynamicTemplates)
		{
			Class<?> templateClass = null;
			String emfPackageName = org.eclipse.emf.codegen.ecore.templates.model.Class.class.getPackage().getName();
			if (className.startsWith(emfPackageName)) {
				for (String userTemplatePath : userTemplatePaths) {
					URI uri = URI.createURI(userTemplatePath);
					String project = uri.segment(1);
					String redirectedClassName = project + ".ecore.templates.model" + className.substring(emfPackageName.length());
					try {
						templateClass = getClass().getClassLoader().loadClass(redirectedClassName);
						break;
					}
					catch (ClassNotFoundException e) {
						// No need for redirected class to exist
					}
				}
			}
			try {
				if (templateClass == null) {							// Fall-back load as an ordinary class
					templateClass = getClass().getClassLoader().loadClass(className);
				}
				Method emitterMethod = templateClass.getDeclaredMethod(methodName, arguments);
				jetEmitter.setMethod(emitterMethod);
			}
			catch (Exception exception) {
				// It's okay for there not be a precompiled template, so fail quietly.
			}
		}
	}
}
