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
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.mwe.core.WorkflowContext;
import org.eclipse.emf.mwe.core.issues.Issues;
import org.eclipse.emf.mwe.core.lib.WorkflowComponentWithModelSlot;
import org.eclipse.emf.mwe.core.monitor.ProgressMonitor;
import org.eclipse.ocl.common.OCLCommon;

/**
 * Removes all constraints from the model in the designated <tt>modelSlot</tt>.
 */
public class EConstraintRemover extends WorkflowComponentWithModelSlot
{	
	private Logger log = Logger.getLogger(getClass());	

	@Override
	public void invokeInternal(WorkflowContext ctx, ProgressMonitor arg1, Issues arg2) {
		Resource resource = (Resource) ctx.get(getModelSlot());
		log.info("Removing constraints from '" + resource.getURI() + "'");
		List<EAnnotation> allConstraints = new ArrayList<EAnnotation>();
		List<EOperation> allInvariants = new ArrayList<EOperation>();
		for (TreeIterator<EObject> tit = resource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if ((eObject instanceof EAnnotation) && OCLCommon.isDelegateURI(((EAnnotation)eObject).getSource())) {
				allConstraints.add((EAnnotation)eObject);
				tit.prune();
			}
			else if ((eObject instanceof EOperation) && EcoreUtil.isInvariant((EOperation) eObject)) {
				allInvariants.add((EOperation) eObject);
				tit.prune();
			}
		}
		for (EAnnotation constraint : allConstraints) {
			((EModelElement)constraint.eContainer()).getEAnnotations().remove(constraint);
		}
		for (EOperation invariant : allInvariants) {
			invariant.getEContainingClass().getEOperations().remove(invariant);
		}
	}
}
