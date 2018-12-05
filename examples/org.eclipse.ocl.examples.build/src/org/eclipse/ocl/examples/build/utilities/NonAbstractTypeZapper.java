/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;

/**
 * Zaps the "Type" class to be non-abstract.
 * <p>
 * It appears that the UM L merge was any-non-abstract implies non-abstract, and that this has changed to any-abstract implies abstract.
 * The *.cmof content was irregular and so the change has an effect.
 * <p>
 * For Type, there is a problem in creating superclass proxies if Type is abstract, so force it non-abstract.
 * <p>
 * For DataType non-abstract is in use so preserve it for now.
 */
@Deprecated  // No longer in use
public class NonAbstractTypeZapper extends WorkflowComponentWithModelSlot
{
	private Logger log = Logger.getLogger(getClass());	
	private ResourceSet resourceSet = null;	

	public ResourceSet getResourceSet() {
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}
		return resourceSet;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		Resource resource = (Resource) ctx.get(getModelSlot());
		log.info("Zapping non-abstract Type in '" + resource.getURI() + "'");
		org.eclipse.uml2.uml.Package umlPackage = (org.eclipse.uml2.uml.Package)resource.getContents().get(0);
		org.eclipse.uml2.uml.Class typeClass = (org.eclipse.uml2.uml.Class)umlPackage.getOwnedType("Type");
		typeClass.setIsAbstract(false);
		org.eclipse.uml2.uml.Class dataTypeClass = (org.eclipse.uml2.uml.Class)umlPackage.getOwnedType("DataType");
		dataTypeClass.setIsAbstract(false);
	}
	
	public void setResourceSet(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}
}
