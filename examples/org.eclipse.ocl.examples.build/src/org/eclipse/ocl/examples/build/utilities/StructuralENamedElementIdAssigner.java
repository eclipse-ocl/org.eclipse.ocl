/*******************************************************************************
 * Copyright (c) 2016, 2018 Willink Transformations and others.
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
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.ocl.pivot.utilities.XMIUtil;

/**
 * Assigns simple xmi:ids to EPackages, EClassifiers, EStructuralFeatures to all Ecore elements.
 */
public class StructuralENamedElementIdAssigner extends WorkflowComponentWithModelSlot
{
	protected Logger log = Logger.getLogger(getClass());

	@Override
	public void checkConfiguration(Issues issues) {
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		XMLResource resource = (XMLResource) ctx.get(getModelSlot());
		log.info("Assigning xmi:ids for '" + resource.getURI() + "'");
		XMIUtil.assignIds(resource, new XMIUtil.StructuralENamedElementIdCreator(), null);
	}
}
