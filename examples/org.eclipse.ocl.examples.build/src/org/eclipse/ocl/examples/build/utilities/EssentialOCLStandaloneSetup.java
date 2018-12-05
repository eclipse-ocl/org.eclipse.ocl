/*******************************************************************************
 * Copyright (c) 2014, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.apache.log4j.Logger;

/**
 * Initializes Essential OCL parsing support.
 */
public class EssentialOCLStandaloneSetup
{
	private Logger log = Logger.getLogger(getClass());

	public EssentialOCLStandaloneSetup() {
		log.info("Registering EssentialOCLStandaloneSetup");
		org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup.doSetup();
	}
}
