/*******************************************************************************
 * Copyright (c) 2010, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - rework of LPG OCL Console for Xtext
 *******************************************************************************/

package org.eclipse.ocl.examples.xtext.console;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ocl.examples.xtext.console.messages.ConsoleMessages;
import org.eclipse.ocl.pivot.utilities.LabelUtil;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.console.AbstractConsole;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleView;
import org.eclipse.ui.part.IPageBookViewPage;


/**
 * The Interactive OCL console.
 */
public class OCLConsole
	extends AbstractConsole {

	private static OCLConsole instance;
//	private OCLConsolePage page;
	
	/**
	 * Initializes me.
	 */
	protected OCLConsole() {
		super(
			ConsoleMessages.Console_Title,
			ImageDescriptor.createFromURL(
				XtextConsolePlugin.getInstance().getBundle().getEntry(
					"/icons/ocl.gif"))); //$NON-NLS-1$
	}
	
	/**
	 * Closes me and clears the singleton instance reference, so that it will
	 * be reinitialized when another console is requested.
	 */
	public void close() {
		try {
			ConsolePlugin.getDefault().getConsoleManager().removeConsoles(
				new IConsole[] {this});
			dispose();
		} finally {
			instance = null;
		}
	}
	
	@Override
	public IPageBookViewPage createPage(IConsoleView view) {
		OCLConsolePage page = new OCLConsolePage(this);
		return page;
	}

	/**
	 * Obtains the singleton instance.  It is created, if necessary.
	 * 
	 * @return the singleton console instance
	 */
	public static OCLConsole getInstance() {
		if (instance == null) {
			instance = new OCLConsole();
			ConsolePlugin.getDefault().getConsoleManager().addConsoles(
				new IConsole[] {instance});
		}
		
		return instance;
	}

	/*
	 * @deprecated supply contextClass(es)
	 */
	@Deprecated
	public void setSelection(EObject contextObject) {
		String typeName = "null"; //$NON-NLS-1$;
		String objectName = "null"; //$NON-NLS-1$
		if (contextObject != null) {
			objectName = LabelUtil.getLabel(contextObject);
			typeName = contextObject.eClass().getName();				
		}
		setName(NLS.bind(ConsoleMessages.Console_TitleWithContext, objectName, typeName));		
	}

	public void setSelection(@Nullable EObject contextObject, org.eclipse.ocl.pivot.@Nullable Class contextClass) {
		String typeName = "null"; //$NON-NLS-1$;
		String objectName = "null"; //$NON-NLS-1$
		if (contextObject != null) {
			objectName = LabelUtil.getLabel(contextObject);
			if (contextClass != null) {
				typeName = contextClass.getName();
			}
		}
		setName(NLS.bind(ConsoleMessages.Console_TitleWithContext, objectName, typeName));		
	}
}
