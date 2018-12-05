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
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CG2StringVisitor;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * The <b>Resource Factory</b> associated with the package.
 */
public class AbstractCGModelResourceFactory extends ResourceFactoryImpl implements CGModelResourceFactory
{
	/**
	 * Creates an instance of the resource.
	 */
	@Override
	public @NonNull CGModelResource createResource(URI uri) {
		return new CGModelResourceImpl(ClassUtil.nonNullState(uri), this);
	}

	@Override
	public @NonNull CG2StringVisitor createToStringVisitor() {
		return new CG2StringVisitor();
	}
}
