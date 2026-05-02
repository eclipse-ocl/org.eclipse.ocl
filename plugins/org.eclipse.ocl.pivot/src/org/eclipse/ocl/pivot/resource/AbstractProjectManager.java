/*******************************************************************************
 * Copyright (c) 2015, 2026 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *
 *******************************************************************************/
package org.eclipse.ocl.pivot.resource;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * BasicProjectManager provides a default implementation of the ProjectManager API that should be used by
 * all clients. The default provides very lightweight functionality that contributes no external projects
 * to a user application and incurs no classpath analysis costs to do so.
 *
 * @since 6.23
 */
public abstract class AbstractProjectManager extends AdapterImpl implements ProjectManager
{
	protected AbstractProjectManager() {
		super();
	}

	protected @Nullable StringBuilder assessEPackage(@NonNull Resource assessedResource, @NonNull EPackage ePackage, @Nullable StringBuilder s) {
		if (ClassUtil.isRegistered(assessedResource)) {			// A registered resource is ok; ?? what if registered after a casual dynamic resource used ??
			return null;
		}
		String nsURI = ePackage.getNsURI();
		if (nsURI != null) {									// Nested packages may be uri-less
			URI assessedResourceURI = assessedResource.getURI();
			URI assessedPackageURI = URI.createURI(nsURI);
			URI assessedProjectURI = assessedResourceURI.trimSegments(1);
			IPackageDescriptor packageDescriptor = getPackageDescriptor(assessedPackageURI);
			if (packageDescriptor != null) {					// Might be a novel project
				IResourceDescriptor resourceDescriptor = packageDescriptor.getResourceDescriptor();
				boolean hasConflict;
				URI knownResourceURI;
				if (resourceDescriptor.hasGenModel()) {
					knownResourceURI = resourceDescriptor.getLocationURI();
					URI knownPlatformPluginProjectURI = resourceDescriptor.getPlatformPluginURI().trimSegments(1);
					URI knownPlatformResourceProjectURI = resourceDescriptor.getPlatformResourceURI().trimSegments(1);
					hasConflict = !knownPlatformPluginProjectURI.equals(assessedProjectURI) && !knownPlatformResourceProjectURI.equals(assessedProjectURI);
				}
				else {
					knownResourceURI = resourceDescriptor.getGenModelURI();
					URI knownProjectURI = knownResourceURI.trimSegments(1);
					hasConflict = !knownProjectURI.equals(assessedProjectURI);
					if (hasConflict && knownProjectURI.isPlatformPlugin()) {
						// Project may be in half-baked shape, with genmodel  normalized by scanClassPath
						StringBuilder b = new StringBuilder();
						for (int i = 0; i < knownProjectURI.segmentCount(); i++) {
							if (i > 1) {
								b.append("/");
							}
							if (i > 0) {
								b.append(knownProjectURI.segment(i));
							}
						}
						URI knownProjectURI2 = URI.createPlatformResourceURI(b.toString(), true);
						hasConflict = !knownProjectURI2.equals(assessedProjectURI);
					}
				}
				if (hasConflict) {
					if (s == null) {
						s = new StringBuilder();
					}
					else {
						s.append("\n");
					}
					s.append("EPackage nsURI '" + nsURI + "' is defined by both '" + assessedResourceURI + "' and '" + knownResourceURI + "'");
				}
			}
		}
		for (EPackage eSubPackage : ePackage.getESubpackages()) {
			assert eSubPackage != null;
			s = assessEPackage(assessedResource, eSubPackage, s);
		}
		return s;
	}

	@Override
	public @Nullable String assessResource(@NonNull Resource resource) {
		@Nullable StringBuilder s = null;
		for (EObject eObject : resource.getContents()) {
			if (eObject instanceof EPackage) {
				s = assessEPackage(resource, (EPackage)eObject, s);
			}
		}
		return s != null ? s.toString() : null;
	}
}