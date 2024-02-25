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
package org.eclipse.ocl.pivot.ui;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.resource.strategy.AbstractResourceStrategyImpl;
import org.eclipse.sirius.business.api.session.Session;

/**
 * OCLNotAResourceStrategyImpl provides the OCL-specific implementation of the Sirius AbstractResourceStrategyImpl
 * to prevent *.oclas files being loaded as Sirius semantic resources. See Bug 582958.
 * <p>
 * A more elegant solution could be available if Sirius responds to
 * https://github.com/eclipse-sirius/sirius-desktop/issues/301
 * by providing a parameterizable NotAResourceStrategyImpl.
 */
public class OCLNotAResourceStrategyImpl extends AbstractResourceStrategyImpl
{
	@Override
	public boolean canHandle(URI resourceURI, ResourceStrategyType resourceStrategyType) {
		if ("oclas".equals(resourceURI.fileExtension())) {
			return true;
		}
		return false;
	}

	@Override
	public boolean canHandle(Resource resource, ResourceStrategyType resourceStrategyType) {
		return canHandle(resource.getURI(), resourceStrategyType);
	}

	@Override
	public boolean isPotentialSemanticResource(URI uri) {
		return true;
	}

	@Override
	public boolean isLoadableModel(URI uri, Session session) {
		return false;
	}

}
