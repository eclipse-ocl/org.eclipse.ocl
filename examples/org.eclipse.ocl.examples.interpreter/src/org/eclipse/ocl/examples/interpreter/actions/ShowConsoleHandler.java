/*******************************************************************************
 * Copyright (c) 2005, 2018 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - rework Delegate as Handler - 386701
 *******************************************************************************/

package org.eclipse.ocl.examples.interpreter.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ocl.examples.interpreter.console.OCLConsole;
import org.eclipse.ocl.examples.interpreter.console.OCLConsoleFactory;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.console.IConsoleFactory;
import org.eclipse.ui.handlers.HandlerUtil;


/**
 * Action handler that ensures that the console view is active, with the
 * Interactive OCL console active within it.
 * @since 3.3
 */
public class ShowConsoleHandler extends AbstractHandler
{
	private IConsoleFactory factory = new OCLConsoleFactory();
	
	protected void consoleOpened(OCLConsole console) {
	    // do nothing
	}

	public Object execute(ExecutionEvent event) throws ExecutionException {
		factory.openConsole();
		Shell shell = HandlerUtil.getActiveShell(event);
		if (shell != null) {
			shell.getDisplay().asyncExec(new Runnable() {
				public void run() {
					consoleOpened(OCLConsole.getInstance());
				}});
		}
		return null;
	}
}
