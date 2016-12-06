/*******************************************************************************
 * Copyright (c) 2011, 2016 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.pivot.internal.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.SAXParser;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ExtensibleURIConverterImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.internal.compatibility.EMF_2_9;
import org.eclipse.ocl.pivot.resource.ProjectManager;

/**
 * ProjectMap extends {@link ProjectManager} to support polymorphic access in either plugin or standalone environments
 * to EMF resources and EPackages.
 *
 *<h4>Plugin Environment</h4>
 *
 * A resolvable location is perhaps <tt>platform:/plugin/org.antlr.runtime/</tt> for a bundle or
 * <tt>platform:/resource/org.eclipse.ocl.domain/</tt> for an open project.
 * <p>
 * {@link #getProjectDescriptors()} returns a map of project names, but not bundle names, to resolvable location.
 * <p>
 * {@link #initializePackageRegistry(ResourceSet)} augments the default EMF startup in a plugin environment
 * whereby the global package registry acquires a registration for each namespace URI
 * (e.g. <tt>http://www.eclipse.org/emf/2002/Ecore</tt>) defined by the
 * <tt>org.eclipse.emf.ecore.generated_package</tt> extension point in plugins.
 * The standard reguistration is auugmented where appropriate, by two further registrations for
 * the project URI (e.g. <tt>platform:/resource/org.eclipse.emf.ecore/model/Ecore.ecore</tt>) and the plugin URI
 * (e.g. <tt>platform:/plugin/org.eclipse.emf.ecore/model/Ecore.ecore</tt>). These extra registrations
 * derived from the <tt>genPackages.ecorePackage</tt> referenced by the <tt>genmodel</tt>
 * <tt>org.eclipse.emf.ecore.generated_package</tt> declarations ensure that all three URIs resolve to
 * the same Resource eliminating most opportunities for meta-model schizophrenia.
 * <p>
 * {@link #initializePlatformResourceMap(boolean)} does nothing since the standard EMF Platform URI Handler
 * can open platform resources directly.
 * <p>
 * {@link #initializeGenModelLocationMap(boolean)} does nothing, since the standard EMF startup in a
 * plugin environment populates the {@link EcorePlugin#getEPackageNsURIToGenModelLocationMap()}.
 * <p>
 * {@link #initializeURIMap(ResourceSet)} installs explicit URI mappings into the {@link URIConverter}
 * so that for each project so that both <tt>platform:/resource/project</tt> and
 * <tt>platform:/plugin/<i>project</i></tt> reference <tt>platform:/resource/<i>project</i></tt>. An additional
 * backstop URI mapping redirects <tt>platform:/resource</tt> to <tt>platform:/plugin</tt>.
 * <p>
 * The explicit mapping ensures that projects are accessible as either
 * <tt>platform:/resource/<i>project</i></tt> or <tt>platform:/plugin/<i>project</i></tt>.
 * The backstop mapping ensures that plugins, that are not occluded by projects, are
 * accessible as <tt>platform:/plugin/<i>project</i></tt> or
 * <tt>platform:/resource/<i>project</i></tt>, without needing to create an
 * explicit URI map entry for each of the many hundreds of bundles in typical use.
 */
public class ProjectMap extends StandaloneProjectMap
{
	public static class ProjectDescriptor extends StandaloneProjectMap.ProjectDescriptor
	{
		public ProjectDescriptor(@NonNull ProjectMap projectMap, @NonNull String name, @NonNull URI locationURI) {
			super(projectMap, name, locationURI);
		}

		@Override
		protected @NonNull URI getResolvedGenModelURI(@NonNull IResourceDescriptor resourceDescriptor) {
			URI genModelURI = resourceDescriptor.getGenModelURI();
			return genModelURI.resolve(getLocationURI());
		}

		@Override
		public void initializeURIMap(@NonNull Map<URI, URI> uriMap) {
			if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
				super.initializeURIMap(uriMap);
			}
			else {
				if (locationURI.isPlatformResource()) {
					URI resourceURI = locationURI;
					URI pluginURI = getPlatformPluginURI();
					uriMap.put(resourceURI, resourceURI);
					uriMap.put(pluginURI, resourceURI);
					if (PROJECT_MAP_ADD_URI_MAP.isActive()) {
						PROJECT_MAP_ADD_URI_MAP.println(resourceURI + " => " + resourceURI);
						PROJECT_MAP_ADD_URI_MAP.println(pluginURI + " => " + resourceURI);
					}
				}
			}
		}
	}

	public static @Nullable StandaloneProjectMap findAdapter(@NonNull ResourceSet resourceSet) {
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			return StandaloneProjectMap.findAdapter(resourceSet);
		}
		return (StandaloneProjectMap) EcoreUtil.getAdapter(resourceSet.eAdapters(), ProjectMap.class);
	}

	public static synchronized @NonNull StandaloneProjectMap getAdapter(@NonNull ResourceSet resourceSet) {
		if (!EcorePlugin.IS_ECLIPSE_RUNNING) {
			return StandaloneProjectMap.getAdapter(resourceSet);
		}
		StandaloneProjectMap adapter = findAdapter(resourceSet);
		if (adapter == null) {
			adapter = new ProjectMap(false);
			adapter.initializeResourceSet(resourceSet);
		}
		return adapter;
	}

	public ProjectMap(boolean isGlobal) {
		super(isGlobal);
	}

	@Override
	protected @NonNull IProjectDescriptor createProjectDescriptor(@NonNull String projectName, @NonNull URI locationURI) {
		return new ProjectDescriptor(this, projectName, locationURI);
	}

	@Override
	public @Nullable URI getLocation(@NonNull String projectName) {
		URI uri = super.getLocation(projectName);
		if ((uri == null) && EMFPlugin.IS_ECLIPSE_RUNNING) {
			uri = URI.createPlatformPluginURI("/" + projectName + "/", true);
		}
		return uri;
	}

	/*	@Override
	public synchronized void initializeGenModelLocationMap(boolean force) {
		if (force || ((nsURI2package == null) && !EMFPlugin.IS_ECLIPSE_RUNNING)) {
			super.initializeGenModelLocationMap(force);
		}
	} */

	@Override
	public synchronized void initializePlatformResourceMap(boolean force) {
		if (force || (!initializedPlatformResourceMap && !EMFPlugin.IS_ECLIPSE_RUNNING)) {
			super.initializePlatformResourceMap(force);
		}
	}

	@Override
	public synchronized void initializeURIMap(@Nullable ResourceSet resourceSet) {
		super.initializeURIMap(resourceSet);
		if (EMFPlugin.IS_ECLIPSE_RUNNING) {
			Map<URI, URI> uriMap = getURIMap(resourceSet);
			URI resourceURI = URI.createPlatformResourceURI("/", true);
			URI pluginURI = URI.createPlatformPluginURI("/", true);
			uriMap.put(resourceURI, pluginURI);
		}
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return (type instanceof Class<?>) && ((Class<?>)type).isAssignableFrom(ProjectMap.class);
	}

	@Override
	protected void scanClassPath(@NonNull Map<@NonNull String, @NonNull IProjectDescriptor> projectDescriptors, @NonNull SAXParser saxParser) {
		if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
			super.scanClassPath(projectDescriptors, saxParser);
		}
		else {
			//			scanBundles();  -- no need to scan hundreds of bundles when a single URI map entry will handle them all.
			scanProjects(projectDescriptors);
			scanGenModels(saxParser);
		}
	}

	/*	protected void scanBundles() {
		for (IBundleGroupProvider bundleGroupProvider : Platform.getBundleGroupProviders()) {
			for (IBundleGroup bundleGroup : bundleGroupProvider.getBundleGroups()) {
				for (Bundle bundle : bundleGroup.getBundles()) {
					String bundleName = bundle.getSymbolicName();
					String projectKey = "/" + bundleName + "/";
					project2location.put(bundleName, URI.createPlatformPluginURI(projectKey, true));
				}
			}
		}
	} */

	protected void scanGenModels(@NonNull SAXParser saxParser) {
		URIConverter uriConverter = new ExtensibleURIConverterImpl();
		Map<String, URI> ePackageNsURIToGenModelLocationMap = EMF_2_9.EcorePlugin.getEPackageNsURIToGenModelLocationMap(false);
		Map<@NonNull URI, @NonNull Map<@NonNull URI, @Nullable String>> genModel2nsURI2className = new HashMap<>();
		for (String ePackageNsURI : ePackageNsURIToGenModelLocationMap.keySet()) {
			URI genModelURI = ePackageNsURIToGenModelLocationMap.get(ePackageNsURI);
			if (genModelURI != null) {
				Map<@NonNull URI, @Nullable String> nsURI2className = genModel2nsURI2className.get(genModelURI);
				if (nsURI2className == null) {
					nsURI2className = new HashMap<>();
					genModel2nsURI2className.put(genModelURI, nsURI2className);
				}
				nsURI2className.put(URI.createURI(ePackageNsURI), null);
			}
		}
		for (@NonNull URI genModelURI : genModel2nsURI2className.keySet()) {
			if (genModelURI.isPlatformPlugin()) {
				IProjectDescriptor projectDescriptor = getProjectDescriptorInternal(genModelURI);
				Map<@NonNull URI, @Nullable String> nsURI2className = genModel2nsURI2className.get(genModelURI);
				assert nsURI2className != null;
				@NonNull URI deresolvedGenModelURI = genModelURI.deresolve(projectDescriptor.getLocationURI(), true, true, true);
				@NonNull String genModelString = String.valueOf(deresolvedGenModelURI);
				IResourceDescriptor resourceDescriptor = projectDescriptor.createResourceDescriptor(genModelString, nsURI2className);
				GenModelReader genModelReader = new GenModelReader(resourceDescriptor);
				InputStream inputStream = null;
				try {
					inputStream = uriConverter.createInputStream(genModelURI);
					saxParser.parse(inputStream, genModelReader);
				} catch (Exception e) {
					logException("Failed to parse '" + genModelURI + "'", e);
				} finally {
					try {
						if (inputStream != null) {
							inputStream.close();
						}
					} catch (IOException e) {}
				}
			}
		}
	}

	protected void scanProjects(@NonNull Map<String, IProjectDescriptor> projectDescriptors) {
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		for (IProject project : root.getProjects()) {
			if (project.isOpen()) {
				@SuppressWarnings("null")@NonNull String projectName = project.getName();
				String projectKey = "/" + projectName + "/";
				@NonNull URI platformResourceURI = URI.createPlatformResourceURI(projectKey, true);
				projectDescriptors.put(projectName, createProjectDescriptor(projectName, platformResourceURI));
			}
		}
	}
}
