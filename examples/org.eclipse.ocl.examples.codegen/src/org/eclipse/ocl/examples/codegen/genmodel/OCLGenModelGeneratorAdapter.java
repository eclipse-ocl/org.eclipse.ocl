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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.GeneratorAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenModelGeneratorAdapter;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.dynamic.JavaFileUtil;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.osgi.framework.Bundle;
import org.osgi.framework.wiring.BundleWiring;

//
//	Overridden to allow static templates to be invoked.
//
@Deprecated		/* @deprecated temporary workaround for Bug 543870 */
public class OCLGenModelGeneratorAdapter extends GenModelGeneratorAdapter
{
	/**
	 * TemplateClassLoader overrides URLClassLoader to provide the option to return null
	 * from loadTemplateClass if the explicitly requested class is not found. Other, referenced
	 * classes, requested via loadClass fall-back on the classLoader if the urls access fails.
	 *
	 * This class and OCLGenModelGeneratorAdapter can be deleted if Bug 543870 fix is available.
	 */
	protected static class TemplateClassLoader extends URLClassLoader
	{
		/**
		 * The ClassLoader to be used to load not-nested classes referenced from a template class.
		 */
		private final @NonNull ClassLoader classLoader;

		/**
		 * The root (qualified, but not-nested) template class names handled by this TemplateClassLoader.
		 */
		private final @NonNull Set<@NonNull String> templateClassNames = new HashSet<>();

		public TemplateClassLoader(@NonNull URL url, @NonNull ClassLoader classLoader) {
			super(new URL[]{url}, null);		// Suppress default fall-back to parent.
			assert url != null;
			assert classLoader != null;
			this.classLoader =  classLoader;
		}

		/**
		 * Re-implement to load any nested class of any of the templateClassNames direct from its class file.
		 * Other classes are loaded by the normal classLoader.
		 */
		@Override
		protected Class<?> loadClass(String className, boolean resolve) throws ClassNotFoundException {
			int index = className.indexOf("$");
			String rootClassName = index >= 0 ? className.substring(0, index) : className;
			if (templateClassNames.contains(rootClassName)) {
				return super.loadClass(className, resolve);
			}
			else {
				return classLoader.loadClass(className);
			}
		}

		/**
		 * Load and return the templateClassName using the templateClassPath configuring the template
		 * class loader to also load the templateClassName's nested classes. Returns null if no
		 * template class found. This allows a variety of explicit template class paths to be tried
		 * before a fall back to the normal class loader.
		 */
		public Class<?> loadTemplateClass(@NonNull String templateClassName) throws ClassNotFoundException {
			assert templateClassName != null;
			Class<?> templateClass = super.loadClass(templateClassName, true);
			if (templateClass != null) {
				templateClassNames.add(templateClassName);
			}
			return templateClass;
		}
	}

	/**
	 * Cached sorted standalone classpath entries.
	 */
	private @NonNull String[] sortedClasspath = null;

	/**
	 * Map from templateClassPath to the TemplatePathClassLoader that loads direct from the class file. These classes
	 * remain loaded for the lifetime of this AbstractGeneratorAdapter. i.e. Re-open the GenModel
	 * editor to respond to changed classes.
	 */
	private Map<@NonNull File, @NonNull TemplateClassLoader> templatePath2classLoader = null;

	public OCLGenModelGeneratorAdapter(@NonNull GeneratorAdapterFactory generatorAdapterFactory) {
		super(generatorAdapterFactory);
	}

	/**
	 * Return the java.class.path entry that resolves absoluteTemplateName references.
	 * This is only useful when running standalone. When using OSGI, the appropriate bundle-specific
	 * class loader should be used instead.
	 * @throws IOException
	 */
	protected @Nullable String getStandaloneTemplateClassPath(@NonNull String workspaceName) throws IOException {
		URI absoluteTemplateURI = EcorePlugin.resolvePlatformResourcePath(workspaceName);
		if (absoluteTemplateURI == null) {
			return null;
		}
		if (absoluteTemplateURI.isArchive()) {
			absoluteTemplateURI = URI.createURI(absoluteTemplateURI.authority() + absoluteTemplateURI.path());
		}
		String absoluteTemplateName = absoluteTemplateURI.isFile() ? absoluteTemplateURI.toFileString() : absoluteTemplateURI.toString();
		if (absoluteTemplateName == null) {			// Never happens
			return null;
		}
		absoluteTemplateName = new File(absoluteTemplateName).getCanonicalPath();
		if (sortedClasspath == null) {
			sortedClasspath = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
			for (int i = 0; i < sortedClasspath.length; i++) {
				sortedClasspath[i] = new File(sortedClasspath[i]).getCanonicalPath();
			}
			Arrays.sort(sortedClasspath);
		}
		int matchIndex  = Arrays.binarySearch(sortedClasspath, absoluteTemplateName);
		if (matchIndex >= 0) {          // Never happens - unless the templateDirectory is actually the bin folder
			return sortedClasspath[matchIndex];
		}
		int insertionPoint = -(matchIndex + 1);  //  classpath[insertionPoint-1] < absoluteTemplateName < classpath[insertionPoint]
		if (insertionPoint <= 0) {
			return sortedClasspath[0];
		}
		if (insertionPoint >= sortedClasspath.length) {
			return sortedClasspath[sortedClasspath.length-1];
		}
		String prevClassElement = sortedClasspath[insertionPoint-1];
		String nextClassElement = sortedClasspath[insertionPoint];
		int prevLength = prevClassElement.length();
		int nextLength = nextClassElement.length();
		int absoluteTemplateNameLength = absoluteTemplateName.length();
		for (int i = 0; i < absoluteTemplateNameLength; i++) {
			char c = absoluteTemplateName.charAt(i);
			if (i >= prevLength) {          // prevClassElement is prefix
				return prevClassElement;
			}
			if (i >= nextLength) {          // nextClassElement is prefix
				return nextClassElement;
			}
			char prevC = prevClassElement.charAt(i);
			if (prevC != c) {
				return nextClassElement;
			}
			char nextC = nextClassElement.charAt(i);
			if (nextC != c) {
				return prevClassElement;
			}
		}
		return prevClassElement;          // Never happens, unless there are duplicates in which case nextClassElement is the same.
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
		assert className != null;
		if (!getGenerator().getOptions().dynamicTemplates)
		{
			Class<?> templateClass = null;
			List<String> userTemplatePath = getUserTemplatePath();	// Omit the built-in emf.codegen.ecore to avoid caching; go direct to the fallback
			if (!userTemplatePath.isEmpty()) {
				templateClass = getStaticUserTemplateClass(userTemplatePath, className);
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

	public @Nullable Class<?> getStaticUserTemplateClass(@NonNull List<String> userTemplatePath, @NonNull String className) {
		if (templatePath2classLoader == null) {
			templatePath2classLoader = new HashMap<>();
		}
		//
		//  Try to load the className direct from the classPath corresponding to each templatePath element,
		//
		for (String templateElement : userTemplatePath) {
			try {
				ClassLoader classLoader = getClass().getClassLoader();
				File templateClassPathFile = null;					// e.g. E:\...
				URI uri = URI.createURI(templateElement);
				if (uri.isPlatform()) {
					URI deresolveURI = uri.isPlatformResource() ? URI.createPlatformResourceURI("/", false) : URI.createPlatformPluginURI("/", false);
					URI workspaceURI = uri.deresolve(deresolveURI);
					String workspaceName = "/" + workspaceURI.toString();
					IWorkspaceRoot root = EcorePlugin.getWorkspaceRoot();
					if (root != null) {
						String projectName = workspaceURI.segment(0);
						Bundle bundle = Platform.getBundle(projectName);
						BundleWiring bundleWiring = ClassUtil.nonNullState(bundle.adapt(BundleWiring.class));
						classLoader = bundleWiring.getClassLoader();
						templateClassPathFile = JavaFileUtil.getOSGIClassPath(bundle);
					}
					else {
						String templateClassPath2 = getStandaloneTemplateClassPath(workspaceName);
						if (templateClassPath2 != null) {
							templateClassPathFile = new File(templateClassPath2);
						}
					}
				}
				else if (uri.isFile()) {
					templateClassPathFile = new File(uri.toString());
				}
				else {
					templateClassPathFile = new File(uri.toFileString());
				}
				if (templateClassPathFile != null) {
					TemplateClassLoader templatePathClassLoader = templatePath2classLoader.get(templateClassPathFile);
					if (templatePathClassLoader == null) {
						assert classLoader != null;
						String templateClassPathString = URI.createFileURI(templateClassPathFile.toString()).toString();		// e.g. file:/E:/...
						URL templateClassPathURL = new URL(templateClassPathFile.isFile() ? templateClassPathString : templateClassPathString + "/");	// e.g. file:/E:/... ...jar or .../bin/
						templatePathClassLoader = new TemplateClassLoader(templateClassPathURL, classLoader);
						templatePath2classLoader.put(templateClassPathFile, templatePathClassLoader);
					}
					return templatePathClassLoader.loadTemplateClass(className);
				}
			}
			catch (Exception e) {
				getClass();
				// If some template path attempt fails just move on to the next one.}
			}
		}
		return null;
	}
}
