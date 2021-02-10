/**
 * <copyright>
 *
 * Copyright (c) 2021 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.ocl.control.util;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

/**
 * The <b>Resource </b> associated with the package.
 * @see org.eclipse.ocl.control.util.ControlResourceFactoryImpl
 */
public class ControlResourceImpl extends XMIResourceImpl
{
	/**
	 * Creates an instance of the resource.
	 *
	 * @param uri the URI of the new resource.
	 */
	public ControlResourceImpl(URI uri) {
		super(uri);
	}
}
