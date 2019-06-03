/*******************************************************************************
 * Copyright (c) 2016, 2019 Willink Transformations and others.
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.emf.mwe.utils.Mapping;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.resource.StandaloneProjectMap;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

/**
 * Renames a set of Resources as a group. All from Resources are loaded, then URIs are changed and saved.
 */
public class ResourceRenamer extends AbstractProjectComponent
{
	private Logger log = Logger.getLogger(getClass());
	private final @NonNull Map<@NonNull String, @NonNull String> resourceRenameMap = new HashMap<>();

	/**
	 * Defines a package rename only from some package to another package.
	 */
	public void addResourceRename(Mapping mapping) {
		String from = mapping.getFrom();
		String to = mapping.getTo();
		assert (from != null) && (to != null);
		String oldTo = resourceRenameMap.put(from, to);
		assert oldTo == null;
	}

	protected Map<Object, Object> getSaveOptions() {
		Map<Object, Object> result = XMIUtil.createSaveOptions();
		result.put(Resource.OPTION_SAVE_ONLY_IF_CHANGED, Resource.OPTION_SAVE_ONLY_IF_CHANGED_MEMORY_BUFFER);
		return result;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		StandaloneProjectMap.IProjectDescriptor projectDescriptor = ClassUtil.nonNullState(getProjectDescriptor());
		ResourceSet resourceSet = getResourceSet();
		Map<@NonNull String, @NonNull Resource> from2resource = new HashMap<>();
		List<@NonNull String> froms = new ArrayList<>(resourceRenameMap.keySet());
		Collections.sort(froms);
		for (@NonNull String from : froms) {
			URI uri = projectDescriptor.getPlatformResourceURI(from);
			Resource resource = resourceSet.getResource(uri, true);
			assert resource != null;
			EcoreUtil.resolveAll(resource);
			ResourceUtils.checkResource(resource);
			from2resource.put(from,  resource);
		}
		EcoreUtil.resolveAll(resourceSet);
		ResourceUtils.checkResourceSet(resourceSet);
		//
		for (@NonNull String from : froms) {
			Resource resource = from2resource.get(from);
			assert resource != null;
			String to = resourceRenameMap.get(from);
			assert to != null;
			URI uri = projectDescriptor.getPlatformResourceURI(to);
			log.info("Renaming " + resource.getURI() + " as " + uri);
			resource.setURI(uri);
			XMIUtil.assignIds(resource, new XMIUtil.StructuralENamedElementIdCreator(), null);
		}
		//
		try {
			for (@NonNull String from : froms) {
				Resource resource = from2resource.get(from);
				assert resource != null;
				Map<Object, Object> saveOptions = getSaveOptions();
				XMIUtil.retainLineWidth(saveOptions, resource);
				resource.save(saveOptions);
			}
		} catch (IOException e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}
}
