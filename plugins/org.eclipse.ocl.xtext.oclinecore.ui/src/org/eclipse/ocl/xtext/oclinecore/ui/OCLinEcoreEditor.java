/*******************************************************************************
 * Copyright (c) 2014, 2015 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.oclinecore.ui;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.ocl.xtext.base.ui.BaseEditor;
import org.eclipse.ocl.xtext.oclinecore.ui.internal.OCLinEcoreActivator;

public class OCLinEcoreEditor extends BaseEditor implements IEditingDomainProvider
{
	public static final String EDITOR_ID = OCLinEcoreActivator.ORG_ECLIPSE_OCL_XTEXT_OCLINECORE_OCLINECORE;

	/**
	 * This keeps track of the editing domain that is used to track all changes to the model.
	 */
	protected AdapterFactoryEditingDomain editingDomain;

	public OCLinEcoreEditor() {
		super();
	}

	@Override
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}
}