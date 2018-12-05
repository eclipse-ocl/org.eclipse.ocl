/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.Mapping;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

/**
 * Splits the composite 'in' Ecore file into a distinct URI per selected EPackage.
 * <p>
 * Included packages are emitted to the mapped URI.
 * <p>
 * Excluded packages are adjusted to be referemced by the mapped URI, but are not emitted.
 */
public class CSSplitter extends AbstractWorkflowComponent
{
	private Logger log = Logger.getLogger(getClass());
	private ResourceSet resourceSet = null;
	protected URI in;
	private Map<String, URI> includes = new HashMap<String, URI>();
	private Map<String, URI> excludes = new HashMap<String, URI>();

	/**
	 * Define a mapping from a package to be excluded to the URI at which it is referenced.
	 */
	public void addExclude(final Mapping uriMap) {
		excludes.put(uriMap.getFrom(), URI.createURI(uriMap.getTo(), true));
	}

	/**
	 * Define a mapping from a package to be included to the URI at which it is referenced.
	 */
	public void addInclude(final Mapping uriMap) {
		includes.put(uriMap.getFrom(), URI.createURI(uriMap.getTo(), true));
	}

	/**
	 * @see org.eclipse.emf.mwe.core.WorkflowComponent#checkConfiguration(org.eclipse.emf.mwe.core.issues.Issues)
	 */
	@Override
	public void checkConfiguration(final Issues issues) {
		if (in == null) {
			issues.addError(this, "in not specified.");
		}
	}

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		log.info("Splitting '" + in + "'");
		ResourceSet resourceSet = getResourceSet();
		Resource resource = resourceSet.getResource(in, true);
		EcoreUtil.resolveAll(resource);
		//	    System.out.println("ResolvedAll " + resource.getClass().getName() + "@" + Integer.toHexString(resource.hashCode()) + " " + resource.getURI());
		ResourceUtils.checkResource(resource);
		EcoreUtil.resolveAll(resourceSet);
		ResourceUtils.checkResourceSet(resourceSet);
		Map<@NonNull EPackage, @NonNull URI> inclusions = new HashMap<@NonNull EPackage, @NonNull URI>();
		Map<@NonNull EPackage, @NonNull URI> exclusions = new HashMap<@NonNull EPackage, @NonNull URI>();
		gatherEPackages(ClassUtil.nonNullEMF(resource.getContents()), inclusions, exclusions);
		List<Resource> resources = new ArrayList<Resource>();
		for (EPackage ePackage : inclusions.keySet()) {
			URI uri = inclusions.get(ePackage);
			Resource includedResource = resourceSet.createResource(uri);
			includedResource.getContents().add(ePackage);
			resources.add(includedResource);
		}
		for (@NonNull EPackage ePackage : exclusions.keySet()) {
			URI uri = exclusions.get(ePackage);
			Resource excludedResource = resourceSet.createResource(uri);
			excludedResource.getContents().add(ePackage);
		}
		for (Resource csResource : resources) {
			try {
				log.info(" to '" + csResource.getURI() + "'");
				csResource.save(XMIUtil.createSaveOptions());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			log.info(" residue '" + resource.getURI() + "'");
			resource.save(null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void gatherEPackages(@NonNull Iterable<? extends EObject> contents, @NonNull Map<EPackage, URI> inclusions, @NonNull Map<EPackage, URI> exclusions) {
		for (EObject eObject : contents) {
			if (eObject instanceof EPackage) {
				EPackage ePackage = (EPackage) eObject;
				String name = ePackage.getName();
				URI uri = includes.get(name);
				if (uri != null) {
					inclusions.put(ePackage, uri);
					continue;
				}
				else {
					uri = excludes.get(name);
					if (uri != null) {
						exclusions.put(ePackage, uri);
						continue;
					}
				}
				gatherEPackages(ClassUtil.nonNullEMF(ePackage.getESubpackages()), inclusions, exclusions);
			}
		}
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void setIn(String in) {
		this.in = URI.createURI(in, true);
	}
}
