/*******************************************************************************
 * Copyright (c) 2011, 2025 Willink Transformations and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D. Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.xtext.tests.xtext;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.utilities.OCL;
import org.eclipse.ocl.xtext.base.cs2as.CS2AS;
import org.eclipse.ocl.xtext.base.utilities.BaseCSResource;
import org.eclipse.ocl.xtext.tests.XtextTestCase;

public class TestPrettyPrinter extends XtextTestCase
{
	public void testDeclarations() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		URI libraryURI = getTestModelURI("models/oclstdlib/OCL-2.3.oclstdlib");
		BaseCSResource xtextResource = (BaseCSResource) ocl.getResourceSet().getResource(libraryURI, true);
		CS2AS cs2as = xtextResource.getCS2AS(ocl.getEnvironmentFactory());
		Resource asResource = cs2as.getASResource();
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof NamedElement) {
				String s = PrettyPrinter.printName((Element) eObject);
				System.out.println(eObject.eClass().getName() + " : " + s);
			}
		}
		ocl.dispose();
	}

	public void testSignatures() throws Exception {
		OCL ocl = OCL.newInstance(getProjectMap());
		URI libraryURI = getTestModelURI("models/oclstdlib/OCL-2.3.oclstdlib");
		BaseCSResource xtextResource = (BaseCSResource) ocl.getResourceSet().getResource(libraryURI, true);
		CS2AS cs2as = xtextResource.getCS2AS(ocl.getEnvironmentFactory());
		Resource asResource = cs2as.getASResource();
		for (TreeIterator<EObject> tit = asResource.getAllContents(); tit.hasNext(); ) {
			EObject eObject = tit.next();
			if (eObject instanceof NamedElement) {
				String s = PrettyPrinter.printType((Element) eObject);
				System.out.println(eObject.eClass().getName() + " : " + s);
			}
		}
		ocl.dispose();
	}
}
