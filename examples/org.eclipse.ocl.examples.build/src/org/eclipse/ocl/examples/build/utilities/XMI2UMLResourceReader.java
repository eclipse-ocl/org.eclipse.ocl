/*******************************************************************************
 * Copyright (c) 2011, 2019 Willink Transformations and others.
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
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.resource.ProjectMap;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.XMI2UMLResource;

/**
 * Reads a specified <tt>uri</tt> into a designated <tt>modelSlot</tt>.
 */
public class XMI2UMLResourceReader extends WorkflowComponentWithModelSlot
{
	private Logger log = Logger.getLogger(getClass());
	private ResourceSet resourceSet = null;
	protected String uri;
	protected String nsURI;

	public @NonNull ResourceSet getResourceSet() {
		ResourceSet resourceSet2 = resourceSet;
		if (resourceSet2 == null) {
			resourceSet = resourceSet2 = new ResourceSetImpl();
		}
		return resourceSet2;
	}

	public String getUri() {
		return uri;
	}

	public String getNsURI() {
		return nsURI;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		EPackage.Registry.INSTANCE.put("http://www.omg.org/spec/UML/20120801", UMLPackage.eINSTANCE);	// Register this somehow
		URI fileURI = URI.createPlatformResourceURI(uri, true);
		log.info("Reading '" + fileURI + "'");
		try {
			ResourceSet resourceSet = getResourceSet();
			UML2AS.initializeUML(resourceSet);
			Resource resource = resourceSet.createResource(fileURI, XMI2UMLResource.UML_CONTENT_TYPE_IDENTIFIER);
			resource.load(null);
			new ProjectMap(false).initializeResourceSet(resourceSet);
			EcoreUtil.resolveAll(resourceSet);
			ResourceUtils.checkResourceSet(resourceSet);
			@SuppressWarnings("unused")
			int errorCount = 0;
			for (Resource aResource : resourceSet.getResources()) {
				List<Diagnostic> errors = aResource.getErrors();
				if (errors.size() > 0) {
					for (Diagnostic error : errors) {
						log.error(error + " for '" + aResource.getURI() + "'");
						errorCount++;
					}
				}
				List<Diagnostic> warnings = aResource.getWarnings();
				if (warnings.size() > 0) {
					for (Diagnostic warning : warnings) {
						log.warn(warning + " for '" + aResource.getURI() + "'");
					}
				}
			}
			if ((nsURI != null) && resource.getContents().size() > 0) {
				EObject eObject = resource.getContents().get(0);
				if (eObject instanceof org.eclipse.uml2.uml.Package) {
					((org.eclipse.uml2.uml.Package)eObject).setURI(nsURI);
				}
			}
			ctx.set(getModelSlot(), resource);
		} catch (IOException e) {
			throw new RuntimeException("Problems running " + getClass().getSimpleName(), e);
		}
	}

	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	public void setNsURI(String uri) {
		this.nsURI = uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
