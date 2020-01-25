/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.eclipse.xtext.XtextStandaloneSetup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Initializes Xtext support.
 */
public class XtextStandaloneSetupBean extends XtextStandaloneSetup
{
	private static final Logger logger = LoggerFactory.getLogger(XtextStandaloneSetupBean.class);

	public XtextStandaloneSetupBean() {
		logger.info("Configuring 'xtext'");
		doSetup();
	}
}