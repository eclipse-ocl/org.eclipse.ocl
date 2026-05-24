/*******************************************************************************
 * Copyright (c) 2026 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *	 E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.emf.validation.validity.ui.actions;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.action.IAction;
import org.eclipse.ocl.examples.emf.validation.validity.ui.messages.ValidityUIMessages;
import org.eclipse.ocl.examples.emf.validation.validity.ui.view.IDEValidityManager;
import org.eclipse.ocl.examples.emf.validation.validity.ui.view.ValidityView;

public final class DisableMetamodelsAction extends AbstractFilterAction
{
	public DisableMetamodelsAction(@NonNull ValidityView validityView) {
		super(ValidityUIMessages.ValidityView_Action_DeselectMetamodels_Title,
				IAction.AS_CHECK_BOX, validityView, true);
		setChecked(true);
	}

	public void refreshChecked() {
		boolean deSelect = this.isChecked();
		if (deSelect) {
			setToolTipText(ValidityUIMessages.ValidityView_Action_SelectMetamodels_ToolTipText);
			setImage(ValidityUIMessages.ValidityView_Action_DeselectMetamodels_ImageLocation);
		} else {
			setToolTipText(ValidityUIMessages.ValidityView_Action_DeselectMetamodels_ToolTipText);
			setImage(ValidityUIMessages.ValidityView_Action_SelectMetamodels_ImageLocation);
		}
		IDEValidityManager validityManager = validityView.getValidityManager();
		validityManager.deselectMetamodels(deSelect);
	}

	@Override
	public void run() {
		refreshChecked();
		validityView.redraw();
	}

	@Override
	public void setChecked(boolean isChecked) {
		super.setChecked(isChecked);
		refreshChecked();
	}
}