package org.eclipse.ocl.examples.build.fragments;
/*******************************************************************************
 * Copyright (c) 2020 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/

import org.eclipse.xtext.xtext.generator.XtextGeneratorNaming;
import org.eclipse.xtext.xtext.generator.model.TypeReference;
import org.eclipse.xtext.xtext.generator.model.project.IXtextProjectConfig;

import com.google.inject.Inject;

/**
 * This override supports the specifocation of an activatorName to fix Bug 564238
 */
@SuppressWarnings("restriction")
public class MyXtextGeneratorNaming extends XtextGeneratorNaming
{
	@Inject
	private IXtextProjectConfig projectConfig;

	public MyXtextGeneratorNaming() {
		super();
	}

	@Override
	public TypeReference getEclipsePluginActivator() {
		String pluginName = projectConfig.getEclipsePlugin().getName();
		if (pluginName == null) {
			return null;
		}
		String activatorName = pluginName.replaceAll("\\.ui$", "");
//		activatorName = StringExtensions.toFirstUpper(activatorName.substring(activatorName.lastIndexOf('.') + 1))
//				+ "Activator";
		activatorName = ((MyStandardProjectConfig)projectConfig).getActivatorName();
		return new TypeReference(pluginName + ".internal", activatorName);
	}

}
