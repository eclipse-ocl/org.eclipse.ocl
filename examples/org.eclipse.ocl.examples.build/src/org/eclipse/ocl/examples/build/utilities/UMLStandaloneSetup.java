/*******************************************************************************
 * Copyright (c) 2010, 2019 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.utilities;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.mwe.utils.StandaloneSetup;
import org.eclipse.ocl.pivot.uml.internal.es2as.UML2AS;

/**
 * Initializes UML support.
 */
public class UMLStandaloneSetup extends StandaloneSetup
{
	private Logger log = Logger.getLogger(getClass());

	public UMLStandaloneSetup() {
		org.eclipse.ocl.pivot.uml.UMLStandaloneSetup.init();
	}

	@Override
	public void setResourceSet(ResourceSet resourceSet) {
		assert resourceSet != null;
		super.setResourceSet(resourceSet);
		log.info("Registering UML Resources");
		UML2AS.initializeUML(resourceSet);
	}

	@Override
	public void setResourceSetImpl(ResourceSetImpl resourceSet) {
		assert resourceSet != null;
		super.setResourceSetImpl(resourceSet);
		log.info("Registering UML Resources");
		UML2AS.initializeUML(resourceSet);
	}
}