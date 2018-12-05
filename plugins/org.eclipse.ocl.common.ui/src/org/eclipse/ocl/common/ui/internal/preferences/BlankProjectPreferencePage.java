/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.common.ui.internal.preferences;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.preference.IPreferencePageContainer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbenchPropertyPage;

/**
 * A blank place holder Project/Property preference page.
 */
public class BlankProjectPreferencePage extends DialogPage implements IWorkbenchPreferencePage, IWorkbenchPropertyPage
{
	public Point computeSize() {
		return new Point(10,10);
	}

	public void createControl(Composite parent) {
		Composite composite1= new Composite(parent, SWT.NONE);
		composite1.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite1.setLayout(layout);
		setControl(composite1);
	}

	public IAdaptable getElement() {
		return null;
	}

	public void init(IWorkbench workbench) {
	}

	public boolean isValid() {
		return true;
	}

	public boolean okToLeave() {
		return true;
	}

	public boolean performCancel() {
		return true;
	}

	public boolean performOk() {
		return true;
	}

	public void setContainer(IPreferencePageContainer preferencePageContainer) {
	}

	public void setElement(IAdaptable element) {
	}

	public void setSize(Point size) {
	}
}