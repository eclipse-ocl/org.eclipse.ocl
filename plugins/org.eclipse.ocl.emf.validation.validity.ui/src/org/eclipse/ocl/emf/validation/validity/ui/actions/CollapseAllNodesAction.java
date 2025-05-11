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
package org.eclipse.ocl.emf.validation.validity.ui.actions;

import java.net.URL;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ocl.emf.validation.validity.RootNode;
import org.eclipse.ocl.emf.validation.validity.ui.messages.ValidityUIMessages;
import org.eclipse.ocl.emf.validation.validity.ui.plugin.ValidityUIPlugin;
import org.eclipse.ocl.emf.validation.validity.ui.view.ValidityView;

public final class CollapseAllNodesAction extends Action
{
	private final @NonNull ValidityView validityView;
	private final boolean isValidatableCollapseAction;
	private final boolean isConstrainingCollapseAction;

	public CollapseAllNodesAction(@NonNull ValidityView validityView,
			boolean isValidatableCollapseAction, boolean isConstrainingCollapseAction) {
		super(ValidityUIMessages.ValidityView_Action_CollapseAllNodes_Title);
		this.validityView = validityView;
		this.isValidatableCollapseAction = isValidatableCollapseAction;
		this.isConstrainingCollapseAction = isConstrainingCollapseAction;
		if (isValidatableCollapseAction && isConstrainingCollapseAction){
			setToolTipText(ValidityUIMessages.ValidityView_Action_CollapseAllNodes_ToolTipText);
		} else if (isValidatableCollapseAction){
			setToolTipText(ValidityUIMessages.ValidityView_Action_CollapseAllValidatableNodes_ToolTipText);
		} else if (isConstrainingCollapseAction) {
			setToolTipText(ValidityUIMessages.ValidityView_Action_CollapseAllConstrainingNodes_ToolTipText);
		}

		URL image = (URL) ValidityUIPlugin.INSTANCE.getImage(ValidityUIMessages.ValidityView_Action_CollapseAllNodes_ImageLocation);
		setImageDescriptor(ImageDescriptor.createFromURL(image));
	}

	@Override
	public void run() {
		RootNode rootNode = validityView.getValidityManager().getRootNode();
		if (rootNode != null) {
			if (isValidatableCollapseAction) {
				validityView.collapseAll(true);
			}
			if (isConstrainingCollapseAction) {
				validityView.collapseAll(false);
			}
		}
	}
}