/*******************************************************************************
 * Copyright (c) 2024 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.completeocl.utilities;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;

/**
 * A CompleteOCLCSXMIResourceFactory supports creation of an CompleteOCLCSXMIResourceImpl that supports persistence of the CS model directly as XMI
 * rather than exploiting Xtext to serialize to / parse from a text file.
 */
public class CompleteOCLCSXMIResourceFactory extends ResourceFactoryImpl
{
	/**
	 * Creates an instance of the resource factory.
	 */
	public CompleteOCLCSXMIResourceFactory() {}

	@Override
	public Resource createResource(URI uri) {
		assert uri != null;
		return new CompleteOCLCSXMIResourceImpl(uri);
	}
}
