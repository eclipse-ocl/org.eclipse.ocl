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

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.resource.ASResource;
import org.eclipse.ocl.xtext.base.as2cs.AS2CS;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.base.utilities.BaseCSXMIResourceImpl;
import org.eclipse.ocl.xtext.completeocl.cs2as.CompleteOCLCS2AS;

/**
 * The CompleteOCLCSXMIResourceImpl implementation of BaseCSResource that ensures that loading resolves references to CS/ES elements
 * to equivalent AS references and conversely ensures that saving replaces AS references by CS/ES references.
 */
public class CompleteOCLCSXMIResourceImpl extends BaseCSXMIResourceImpl
{
	/**
	 * Creates an instance of the resource.
	 */
	protected CompleteOCLCSXMIResourceImpl(@NonNull URI uri) {
		super(uri, CompleteOCLASResourceFactory.getInstance());
	}

	@Override
	public @NonNull CS2AS createCS2AS(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ASResource asResource) {
		return new CompleteOCLCS2AS(environmentFactory, this, asResource);
	}
}
