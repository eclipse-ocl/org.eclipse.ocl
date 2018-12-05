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
 *   E.D.Willink - rework Delegate as Handler - 386701
 *******************************************************************************/

package org.eclipse.ocl.examples.xtext.console.actions;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ocl.examples.xtext.console.OCLConsoleFactory;
import org.eclipse.ui.console.IConsoleFactory;


/**
 * Action delegate that ensures that the console view is active, with the
 * Interactive OCL console active within it.
 */
public class ShowConsoleHandler extends AbstractHandler
{
	private IConsoleFactory factory = new OCLConsoleFactory();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		factory.openConsole();
		return null;
	}

}
