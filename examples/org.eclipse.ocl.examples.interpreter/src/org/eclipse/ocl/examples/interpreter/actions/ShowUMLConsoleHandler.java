/*******************************************************************************
 * Copyright (c) 2007, 2018 IBM Corporation and others.
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

import org.eclipse.ocl.examples.interpreter.console.OCLConsole;
import org.eclipse.ocl.examples.interpreter.console.TargetMetamodel;


/**
 * Action handler that ensures that the OCL Console is active and set to
 * target the UML metamodel.
 * @since 3.3
 */
public class ShowUMLConsoleHandler extends ShowConsoleHandler
{
	@Override
    protected void consoleOpened(OCLConsole console) {
	    console.setTargetMetamodel(TargetMetamodel.UML);
	}
}
