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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.jdt.annotation.NonNull;

/**
 * The <b>Resource </b> associated with the package.
 */
public class CGModelResourceImpl extends XMIResourceImpl implements CGModelResource
{
	protected final @NonNull CGModelResourceFactory resourceFactory;
	
	/**
	 * Creates an instance of the resource.
	 */
	public CGModelResourceImpl(@NonNull URI uri, @NonNull CGModelResourceFactory resourceFactory) {
		super(uri);
		this.resourceFactory = resourceFactory;
	}

	@Override
	public @NonNull CGModelResourceFactory getResourceFactory() {
		return resourceFactory;
	}

} //CGModelResourceImpl
