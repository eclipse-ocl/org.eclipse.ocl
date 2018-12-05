/*******************************************************************************
 * Copyright (c) 2011, 2018 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.xtext.console;

import org.eclipse.ocl.examples.xtext.console.OCLConsolePage.InterrogatableContentAssistantFactory;
import org.eclipse.ocl.xtext.essentialocl.ui.EssentialOCLUiModule;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.editor.contentassist.IContentAssistantFactory;
import org.eclipse.xtext.ui.editor.model.edit.IssueModificationContext;
import org.eclipse.xtext.ui.resource.IResourceSetProvider;

/**
 * Use this class to register components to be used within the IDE.
 */
public class XtextConsoleUiModule extends EssentialOCLUiModule
{
	public XtextConsoleUiModule(AbstractUIPlugin plugin) {
		super(plugin);
	}

	/**
	 * Enable Console to determine whether CompletionAssist is active or whether INputKey accelerators can be used.
	 */
	@Override
	public Class<? extends IContentAssistantFactory> bindIContentAssistantFactory() {
		return InterrogatableContentAssistantFactory.class;
	}

	public Class<? extends IssueModificationContext> bindIssueModificationContext() {
		return XtextConsoleIssueModificationContext.class;
	}

	@Override
	public Class<? extends IResourceSetProvider> bindIResourceSetProvider() {
		return XtextConsoleResourceSetProvider.class;
	}
}
