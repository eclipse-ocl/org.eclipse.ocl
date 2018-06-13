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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.AbstractWorkflowComponent;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Package;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.util.UMLUtil;

/**
 * Converts an Ecore resource to its UML form.
 */
public class Ecore2UMLConverter extends AbstractWorkflowComponent
{
	private Logger log = Logger.getLogger(getClass());

	/**
	 * @see org.eclipse.emf.mwe.core.WorkflowComponent#checkConfiguration(org.eclipse.emf.mwe.core.issues.Issues)
	 */
	@Override
	public void checkConfiguration(final Issues issues) {
		if (ecoreSlot == null) {
			issues.addError(this, "ecoreSlot not specified.");
		}
		if (umlSlot == null) {
			issues.addError(this, "umlSlot not specified.");
		}
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		Resource resource = (Resource) ctx.get(getEcoreSlot());
		URI ecoreURI = resource.getURI();
		log.info("Converting '" + ecoreURI + "'");
		ResourceSet resourceSet = ClassUtil.nonNullState(resource.getResourceSet());

		EPackage ePackage = (EPackage) resource.getContents().get(0);
		Map<String, String> options = new HashMap<String, String>();
		List<Package> umlPackages = new ArrayList<>(UMLUtil.convertFromEcore(ePackage, options));
		Resource umlResource = resourceSet.createResource(ecoreURI.trimFileExtension().appendFileExtension("uml"));
		umlResource.getContents().addAll(ClassUtil.nullFree(umlPackages));
		for (TreeIterator<EObject> tit = umlResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof Association) {
				Association association = (Association)eObject;
				Property thisEnd = association.getMemberEnds().get(0);
				Property thatEnd = association.getMemberEnds().get(1);
				String n0 = getEndName(thisEnd, thatEnd);
				String n1 = getEndName(thatEnd, thisEnd);
				association.setName("A_" + n0 + "_" + n1);
			}
		}
		ctx.set(getUmlSlot(), umlResource);
	}

	protected String getEndName(Property thisEnd, Property thatEnd) {
		if ((thisEnd == null) || (thisEnd.getName() == null)) {
			return thatEnd.getClass_().getName();
		}
		else {
			return thisEnd.getName();
		}
	}

	private String ecoreSlot;
	private String umlSlot;

	/**
	 * Sets the name of the Ecore slot.
	 *
	 * @param slot
	 *            name of slot
	 */
	public void setEcoreSlot(final String slot) {
		ecoreSlot = slot;
	}

	/**
	 * Sets the name of the UML slot.
	 *
	 * @param slot
	 *            name of slot
	 */
	public void setUmlSlot(final String slot) {
		umlSlot = slot;
	}

	protected String getEcoreSlot() {
		return ecoreSlot;
	}

	protected String getUmlSlot() {
		return umlSlot;
	}
}
