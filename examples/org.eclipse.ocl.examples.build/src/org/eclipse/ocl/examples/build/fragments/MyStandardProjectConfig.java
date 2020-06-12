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
package org.eclipse.ocl.examples.build.fragments;

import org.eclipse.xtext.xtext.generator.model.project.StandardProjectConfig;

/**
 * This override supports the specifocation of an activatorName to fix Bug 564238
 */
public class MyStandardProjectConfig extends StandardProjectConfig
{
	private String activatorName;

	public String getActivatorName() {
		return activatorName;
	}

	/**
	 * Specify the spelling of the UI activator class name.
	 * @param activatorName
	 */
	public void setActivatorName(String activatorName) {
		this.activatorName = activatorName;
	}

}
