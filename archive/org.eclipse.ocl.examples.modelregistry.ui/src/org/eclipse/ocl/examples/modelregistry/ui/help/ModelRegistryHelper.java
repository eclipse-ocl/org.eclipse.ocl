/*******************************************************************************
 * Copyright (c) 2010 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.modelregistry.ui.help;

import org.eclipse.ocl.examples.modelregistry.ui.ModelRegistryUIPlugin;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;

public class ModelRegistryHelper
{
	public static void setHelp(Control control, Enum<?> contextId) {
		String helpId = ModelRegistryUIPlugin.PLUGIN_ID + '.' + contextId.getDeclaringClass().getSimpleName() + '_'  + contextId.name();
		PlatformUI.getWorkbench().getHelpSystem().setHelp(control, helpId);
	}
}
