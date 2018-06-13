/*******************************************************************************
 * Copyright (c) 2010, 2018 Willink Transformations and others.
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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.utilities.XMIUtil;
import org.eclipse.ocl.pivot.utilities.XMIUtil.ExcludedEClassIdFilter;

/**
 * Alphabeticizes a designated <tt>modelSlot</tt> so that primitive types
 * appear before enumerations before classes before associations, each
 * in alphabetical order.
 */
public class EcoreIdAssigner extends WorkflowComponentWithModelSlot
{
	private Logger log = Logger.getLogger(getClass());

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		Resource resource = (Resource) ctx.get(getModelSlot());
		log.info("Ecore Id Assigning '" + resource.getURI() + "'");
		XMIUtil.HierachicalENamedElementIdCreator idCreator = new XMIUtil.HierachicalENamedElementIdCreator();
		@SuppressWarnings("null")
		ExcludedEClassIdFilter idFilter = new ExcludedEClassIdFilter(new @NonNull EClass[] {
			XMLTypePackage.Literals.ANY_TYPE,
			EcorePackage.Literals.EANNOTATION,
			EcorePackage.Literals.EGENERIC_TYPE,
			EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY
		});
		XMIUtil.assignIds(resource, idCreator, idFilter);
	}
}
