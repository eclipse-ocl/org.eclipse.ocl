/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
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
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.ocl.pivot.Model;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ParserException;

/**
 * Converts a UML resource to its Pivot form.
 */
public class UML2ASLoader extends WorkflowComponentWithModelSlot
{
	private Logger log = Logger.getLogger(getClass());

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		OCLstdlib.install();
		Resource resource = (Resource) ctx.get(getUmlSlot());
		log.info("Pivoting '" + resource.getURI() + "'");
		ResourceSet asResourceSet = ClassUtil.nonNullState(resource.getResourceSet());
		PivotMetamodelManager metamodelManager = PivotMetamodelManager.getAdapter(asResourceSet);
		UML2AS uml2as = UML2AS.getAdapter(resource, metamodelManager.getEnvironmentFactory());
		Model root;
		try {
			root = uml2as.getASModel();
		} catch (ParserException e) {
			throw new RuntimeException("Problems pivoting '" + resource.getURI() + "'", e);
		}
		Resource resource2 = root.eResource();
		ctx.set(getModelSlot(), resource2);
	}

	private String umlSlot;

	/**
	 * Sets the name of the UML slot.
	 * 
	 * @param slot
	 *            name of slot
	 */
	public void setUmlSlot(final String slot) {
		umlSlot = slot;
	}

	protected String getUmlSlot() {
		return umlSlot;
	}
}
