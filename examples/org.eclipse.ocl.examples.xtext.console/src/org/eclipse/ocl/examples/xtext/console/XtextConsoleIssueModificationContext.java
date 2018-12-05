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

import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.PageBookView;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IssueModificationContext;

public class XtextConsoleIssueModificationContext extends IssueModificationContext
{
	@Override
	public IXtextDocument getXtextDocument(URI uri) {
		IWorkbench workbench = PlatformUI.getWorkbench();
		IWorkbenchWindow activeWorkbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
		for (IViewReference viewReference : activePage.getViewReferences()) {
			IWorkbenchPart viewPart = viewReference.getPart(false);
			if ((viewPart instanceof IConsoleView) && (viewPart instanceof PageBookView))  {
				IPage currentPage = ((PageBookView)viewPart).getCurrentPage();
				if (currentPage instanceof OCLConsolePage) {
					OCLConsolePage oclConsolePage = (OCLConsolePage)currentPage;
					IXtextDocument document = oclConsolePage.getDocument(uri.trimFragment());
					if (document != null) {
						return document;
					}
				}
			}
		}
		return super.getXtextDocument(uri);
	}
}
