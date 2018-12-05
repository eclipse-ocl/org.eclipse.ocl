/*******************************************************************************
 * Copyright (c) 2012, 2018 Willink Transformations, and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/

package org.eclipse.ocl.ecore.util;

/**
 * Utilities and definitions for the Ecore binding.
 * 
 * @since 3.2
 */
public class OCLEcoreUtil {
	/**
	 * The shared Ecore binding plugin identification.
	 */
	public static final String PLUGIN_ID = "org.eclipse.ocl.ecore"; //$NON-NLS-1$
	
    // not instantiable by clients
	private OCLEcoreUtil() {
		super();
	}
}
