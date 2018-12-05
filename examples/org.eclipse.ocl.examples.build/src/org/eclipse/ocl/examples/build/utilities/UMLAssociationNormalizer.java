/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

/**
 * Converts a UML resource to its Pivot form.
 */
public class UMLAssociationNormalizer extends WorkflowComponentWithModelSlot
{
	private Logger log = Logger.getLogger(getClass());
	private String uri;

	public String getUri() {
		return uri;
	}

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		OCLstdlib.install();
		Resource resource = (Resource) ctx.get(getModelSlot());
		log.info("Re-associating '" + resource.getURI() + "'");
		for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof org.eclipse.uml2.uml.Package) {
				org.eclipse.uml2.uml.Package pkg = (org.eclipse.uml2.uml.Package) eObject;
				Resource eResource = pkg.eResource();
				if ((eResource != null) && (eResource != resource)) {
					eResource.getContents().clear();
				}
			}
			else if (eObject instanceof Association) {
				Association association = (Association) eObject;
				List<Property> ownedEnds = association.getOwnedEnds();
				int iMax = ownedEnds.size();
				if (iMax > 0) {
					List<Type> endTypes = association.getEndTypes();
//					System.out.println(association.getName());
					for (int i = iMax-1; i >= 0; --i) {
						Property property = ownedEnds.get(i);
						int endNumber = association.getMemberEnds().indexOf(property);
						@SuppressWarnings("unused")
						Type type = endNumber < endTypes.size() ? endTypes.get(endNumber) : endTypes.get(0);
						@SuppressWarnings("unused")
						Property otherProperty = property.getOtherEnd();
						Type otherType = endTypes.get(1-endNumber);
//						System.out.println("  " + endNumber + " " + property.getName() + ":" + type.getName() + " " + otherProperty.getName() + ":" + otherType.getName());
						((org.eclipse.uml2.uml.Class)otherType).getOwnedAttributes().add(property);
					}
				}
			}
		}
		if (uri != null) {
			resource.setURI(URI.createPlatformResourceURI(uri, true));
		}
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
