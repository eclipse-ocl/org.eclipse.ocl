/*******************************************************************************
 * Copyright (c) 2014, 2018 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.emf.validation.validity.utilities;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.emf.validation.validity.AbstractNode;
import org.eclipse.ocl.examples.emf.validation.validity.Result;
import org.eclipse.ocl.examples.emf.validation.validity.Severity;

/**
 * A SeveritiesVisibilityFilter is used to filter the validatable of constrainable node view
 * to show only nodes whose severity matches one of the chosen severities.
 */
public class SeveritiesVisibilityFilter implements IVisibilityFilter
{
	private int rejectedSeverities = 0;

	/**
	 * Add severity from the set of severities to be filtered out.
	 */
	public void addFilteredSeverity(@NonNull Severity severity) {
		rejectedSeverities |= 1 << severity.getValue();
	//	System.out.println("addFilteredSeverity " + NameUtil.debugSimpleName(this) + " " + Integer.toHexString(rejectedSeverities));
	}

	public boolean isEmpty() {
		return rejectedSeverities == 0;
	}

	@Override
	public boolean isVisible(@NonNull AbstractNode node) {
		Result worstResultForNode = node.getWorstResult();
		if (worstResultForNode != null) {
			int severityMask = 1 << worstResultForNode.getSeverity().getValue();
			if ((rejectedSeverities & severityMask) == 0) {
				return true;
			}
		}
		for (AbstractNode child : node.getChildren()) {
			assert child != null;
			if (isVisible(child)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove severity from the set of severities to be filtered out.
	 * Returns true if no severities remain to be removed.
	 */
	public boolean removeFilteredSeverity(@NonNull Severity severity) {
		rejectedSeverities &= ~(1 << severity.getValue());
	//	System.out.println("removeFilteredSeverity " + NameUtil.debugSimpleName(this) + " " + Integer.toHexString(rejectedSeverities));
		return isEmpty();
	}
}
