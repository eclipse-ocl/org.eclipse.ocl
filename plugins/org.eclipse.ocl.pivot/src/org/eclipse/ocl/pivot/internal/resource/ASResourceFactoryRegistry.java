/*******************************************************************************
 * Copyright (c) 2010, 2015 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.ecore.EcoreASResourceFactory;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotEnvironmentFactory;
import org.eclipse.ocl.pivot.internal.utilities.Technology;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.pivot.resource.CSResource;
import org.eclipse.ocl.pivot.resource.ProjectManager;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * The ASResourceFactoryRegistry maintains the mapping from known ASResource content type
 * identifiers to the ASResourceFactory instance appropriate to the content type.
 * <p>
 *
 */
public class ASResourceFactoryRegistry
{
	public static final class ContributionFunction implements Function<ASResourceFactoryContribution, ASResourceFactory>
	{
		public static final @NonNull ContributionFunction INSTANCE = new ContributionFunction();

		@Override
		public ASResourceFactory apply(ASResourceFactoryContribution asResourceFactoryContribution) {
			return asResourceFactoryContribution.getASResourceFactory();
		}
	}

	public static final class ExternalResourcePredicate implements Predicate<ASResourceFactoryContribution>
	{
		public static final @NonNull ExternalResourcePredicate INSTANCE = new ExternalResourcePredicate();

		@Override
		public boolean apply(ASResourceFactoryContribution asResourceFactoryContribution) {
			return asResourceFactoryContribution.getPriority() != null;
		}
	}

	public static final class LoadedResourcePredicate implements Predicate<ASResourceFactoryContribution>
	{
		public static final @NonNull LoadedResourcePredicate INSTANCE = new LoadedResourcePredicate();

		@Override
		public boolean apply(ASResourceFactoryContribution asResourceFactoryContribution) {
			return (asResourceFactoryContribution.getPriority() != null) || (asResourceFactoryContribution.basicGetASResourceFactory() != null);
		}
	}

	public static final @NonNull ASResourceFactoryRegistry INSTANCE = new ASResourceFactoryRegistry();

	protected final @NonNull Map<String, ASResourceFactoryContribution> contentType2resourceFactory = new HashMap<String, ASResourceFactoryContribution>();
	protected final @NonNull Map<String, ASResourceFactoryContribution> extension2resourceFactory = new HashMap<String, ASResourceFactoryContribution>();
	protected final @NonNull Map<String, ASResourceFactoryContribution> resourceClassName2resourceFactory = new HashMap<String, ASResourceFactoryContribution>();

	public synchronized Object addASResourceFactory(@Nullable String contentType, @Nullable String oclasExtension, @Nullable String resourceClassName, @NonNull ASResourceFactoryContribution asResourceFactory) {
		ASResourceFactoryContribution oldASResourceFactory1 = null;
		if (contentType != null) {
			oldASResourceFactory1 = contentType2resourceFactory.put(contentType, asResourceFactory);
			assert (oldASResourceFactory1 == null) || (oldASResourceFactory1 == asResourceFactory)
			|| (oldASResourceFactory1.basicGetASResourceFactory() == asResourceFactory)
			|| (oldASResourceFactory1.basicGetASResourceFactory() == null);
		}
		if (oclasExtension != null) {
			ASResourceFactoryContribution oldASResourceFactory2 = extension2resourceFactory.put(oclasExtension, asResourceFactory);
			assert (oldASResourceFactory2 == null) || (oldASResourceFactory2 == asResourceFactory)
			|| (oldASResourceFactory2.basicGetASResourceFactory() == asResourceFactory)
			|| (oldASResourceFactory2.basicGetASResourceFactory() == null);
		}
		if (resourceClassName != null) {
			ASResourceFactoryContribution oldASResourceFactory3 = resourceClassName2resourceFactory.put(resourceClassName, asResourceFactory);
			assert (oldASResourceFactory3 == null) || (oldASResourceFactory3 == asResourceFactory)
			|| (oldASResourceFactory3.basicGetASResourceFactory() == asResourceFactory)
			|| (oldASResourceFactory3.basicGetASResourceFactory() == null);
		}
		return oldASResourceFactory1;
	}

	public synchronized void configureResourceSet(@NonNull ResourceSet resourceSet) {
		for (ASResourceFactoryContribution asResourceFactoryContribution : contentType2resourceFactory.values()) {
			ASResourceFactory asResourceFactory = asResourceFactoryContribution.basicGetASResourceFactory();
			if (asResourceFactory != null) {
				asResourceFactory.configure(resourceSet);
			}
			else if (asResourceFactoryContribution.getPriority() != null) {
				asResourceFactoryContribution.getASResourceFactory().configure(resourceSet);
			}
		}
	}

	/**
	 * Create a new EnvironmentFactory appropriate to the resources in ResourceSet.
	 */
	public @NonNull EnvironmentFactoryInternal createEnvironmentFactory(@NonNull ProjectManager projectManager, @Nullable ResourceSet externalResourceSet) {
		if (externalResourceSet != null) {
			EnvironmentFactoryAdapter environmentFactoryAdapter = EnvironmentFactoryAdapter.find(externalResourceSet);
			if (environmentFactoryAdapter != null) {
				return environmentFactoryAdapter.getEnvironmentFactory();
			}
		}
		return new PivotEnvironmentFactory(projectManager, externalResourceSet);
	}

	public @Nullable ASResourceFactoryContribution get(@NonNull String contentType) {
		return contentType2resourceFactory.get(contentType);
	}

	/**
	 * Return the ASResourceFactory to be used in association with a particular resource.
	 * <p>
	 * For an ASResource, the ASResourceFactory is directly accessible.
	 * <p>
	 * For a CSResource, the CSResourceFactory is directly related to the CSResource class.
	 * <p>
	 * For more general resources, inheritance from defined correspondences is used. Thus
	 * the UMLASResourceFactory is defined for the UMLResource interface and so is used for
	 * all variants of derived UMLResource class. Inheritance is determined by registered
	 * class name to avoid loading the UML support plugins when UML is not in use.
	 * <p>
	 * In the absence of a configured selection an EcoreASResourceFactory is used.
	 */
	public @Nullable ASResourceFactory getASResourceFactory(@NonNull Resource resource) {
		if (resource instanceof ASResource) {
			return ((ASResource)resource).getASResourceFactory();
		}
		if (resource instanceof CSResource) {
			return ((CSResource)resource).getASResourceFactory();
		}
		//
		//	This complexity is solely for the benefit of UML which may not be loaded so we cannot use UML classes.
		//
		synchronized(this) {
			Class<? extends Resource> resourceClass = resource.getClass();
			String resourceClassName = resourceClass.getName();
			ASResourceFactoryContribution asResourceFactoryContribution = resourceClassName2resourceFactory.get(resourceClassName);
			if (asResourceFactoryContribution != null) {
				return asResourceFactoryContribution.getASResourceFactory();
			}
			for (Class<?> aClass = resourceClass; aClass != null; aClass = aClass.getSuperclass()) {
				{
					String aClassName = aClass.getName();
					asResourceFactoryContribution = resourceClassName2resourceFactory.get(aClassName);
					if (asResourceFactoryContribution != null) {
						ASResourceFactory asResourceFactory = asResourceFactoryContribution.getASResourceFactory();
						resourceClassName2resourceFactory.put(resourceClassName, asResourceFactory);
						return asResourceFactory;
					}
				}
				for (Class<?> anInterface : aClass.getInterfaces()) {
					String anInterfaceName = anInterface.getName();
					asResourceFactoryContribution = resourceClassName2resourceFactory.get(anInterfaceName);
					if (asResourceFactoryContribution != null) {
						ASResourceFactory asResourceFactory = asResourceFactoryContribution.getASResourceFactory();
						resourceClassName2resourceFactory.put(resourceClassName, asResourceFactory);
						return asResourceFactory;
					}
				}
			}
			ASResourceFactory asResourceFactory = EcoreASResourceFactory.getInstance();
			resourceClassName2resourceFactory.put(resourceClassName, asResourceFactory);
			return asResourceFactory;
		}
	}

	public @Nullable ASResourceFactory getASResourceFactoryForExtension(@Nullable String extension) {
		ASResourceFactoryContribution asResourceFactoryContribution = extension2resourceFactory.get(extension);
		return asResourceFactoryContribution != null ? asResourceFactoryContribution.getASResourceFactory() : null;
	}

	public Iterable<ASResourceFactory> getExternalResourceFactories() {
		return Iterables.transform(Iterables.filter(contentType2resourceFactory.values(), ExternalResourcePredicate.INSTANCE), ContributionFunction.INSTANCE);
	}

	public Iterable<ASResourceFactory> getLoadedResourceFactories() {
		return Iterables.transform(Iterables.filter(contentType2resourceFactory.values(), LoadedResourcePredicate.INSTANCE), ContributionFunction.INSTANCE);
	}

	/**
	 * Determine the getTechnology appropriate to the registered ASResourceFactories.
	 */
	public @NonNull Technology getTechnology() {
		Integer bestPriority = null;
		ASResourceFactory bestASResourceFactory = null;
		for (ASResourceFactory asResourceFactory : getExternalResourceFactories()) {
			Integer priority = asResourceFactory.getPriority();
			if ((bestPriority == null) || ((priority != null) && (priority > bestPriority))) {
				bestPriority = priority;
				bestASResourceFactory = asResourceFactory;
			}
		}
		if (bestASResourceFactory == null) {
			bestASResourceFactory = EcoreASResourceFactory.getInstance();
		}
		return bestASResourceFactory.getTechnology();
	}

	public synchronized void remove(@Nullable String contentType, @Nullable String extension, @Nullable String resourceClassName) {
		if (contentType != null) {
			contentType2resourceFactory.remove(contentType);
		}
		if (extension != null) {
			extension2resourceFactory.remove(extension);
		}
		if (resourceClassName != null) {
			resourceClassName2resourceFactory.remove(resourceClassName);			// FIXME derived usages
		}
	}
}
