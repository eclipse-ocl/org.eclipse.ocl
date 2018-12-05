/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.utilities;

import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.jdt.annotation.NonNull;

/**
 * The <b>Resource </b> associated with the package.
 */
public interface CGModelResource extends XMIResource
{
	
	/**
	 * Return the CGModelResourceFactory that created this CGModelResource and which may be used
	 * to create further artefacts.
	 */
	@NonNull CGModelResourceFactory getResourceFactory();
}
