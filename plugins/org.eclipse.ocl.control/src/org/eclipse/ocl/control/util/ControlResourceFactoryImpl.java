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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * The <b>Resource Factory</b> associated with the package.
 * @see org.eclipse.ocl.control.util.ControlResourceImpl
 */
public class ControlResourceFactoryImpl extends ResourceFactoryImpl
{
	/**
	 * Creates an instance of the resource factory.
	 */
	public ControlResourceFactoryImpl() {
		super();
	}

	/**
	 * Creates an instance of the resource.
	 */
	@Override
	public Resource createResource(URI uri) {
		Resource result = new ControlResourceImpl(uri);
		return result;
	}
}
