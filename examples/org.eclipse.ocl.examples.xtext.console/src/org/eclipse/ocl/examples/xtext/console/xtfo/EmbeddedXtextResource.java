/*******************************************************************************
 * Copyright (c) 2010, 2018 ProxiAD and Others
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *    Cedric Vidal (ProxiAD) - initial API and implementation
 *    E.D.Willink - integration of XTFO code uder CQ 4866
 *******************************************************************************/

package org.eclipse.ocl.examples.xtext.console.xtfo;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.xtext.essentialocl.utilities.EssentialOCLCSResource;

public class EmbeddedXtextResource extends EssentialOCLCSResource {

	private Resource parentResource = null;

	public Resource getParentResource() {
		return parentResource;
	}

	public void setParentResource(Resource parentResource) {
		this.parentResource = parentResource;
	}

	public EmbeddedXtextResource() {
		super();
	}

}
