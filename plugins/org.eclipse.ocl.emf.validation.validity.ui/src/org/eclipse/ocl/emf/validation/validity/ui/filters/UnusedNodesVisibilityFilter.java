/*******************************************************************************
 * Copyright (c) 2014, 2025 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.emf.validation.validity.ui.filters;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.emf.validation.validity.AbstractNode;
import org.eclipse.ocl.emf.validation.validity.ResultConstrainingNode;
import org.eclipse.ocl.emf.validation.validity.ResultValidatableNode;
import org.eclipse.ocl.emf.validation.validity.RootNode;
import org.eclipse.ocl.emf.validation.validity.utilities.IVisibilityFilter;

public class UnusedNodesVisibilityFilter implements IVisibilityFilter
{
	@Override
	public boolean isVisible(@NonNull AbstractNode abstractNode) {
		return isUsed(abstractNode);
	}

	protected boolean isUsed(@NonNull AbstractNode node) {
		for (EObject eContainer = node; (eContainer = eContainer.eContainer()) != null; ) {
			if (eContainer instanceof AbstractNode) {
				if (!((AbstractNode)eContainer).isEnabled()) {
					return false;		// Not used if an ancestor disabled.
				}
			}
		}
		if (!node.isEnabled() || (node.eContainer() == null) || (node.eContainer() instanceof RootNode)) {
			return true;				// root-most disabled node is visible.
		}
		if ((node instanceof ResultConstrainingNode) || (node instanceof ResultValidatableNode)) {
			return true;				// (leaf) Constraint is used (and visible)
		}
		for (AbstractNode child : node.getChildren()) {
			assert child != null;
			if (isUsed(child)) {
				return true;			// intermediate node used (and visible) if a child is
			}
		}
		return false;					// not used even if enabled
	}

}
