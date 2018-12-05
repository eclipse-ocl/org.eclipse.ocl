/*******************************************************************************
 * Copyright (c) 2013, 2018 Willink Transformations Ltd., University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.ocl.pivot.model.OCLstdlib;
import org.eclipse.ocl.xtext.essentialocl.EssentialOCLStandaloneSetup;

public class GenerateAutoCS2ASVisitors extends GenerateCSVisitors
{
	
	@Override
	protected void doSetup() {
		EssentialOCLStandaloneSetup.doSetup();
		OCLstdlib.install();
	}
	
	@Override
	public void generateVisitors(/*@NonNull*/ GenPackage genPackage) {
/*		if (isDerived()) {
			CS2ASCodeGenerator.generate(genPackage, projectPrefix, projectName, visitorPackageName, visitorClassName, visitableClassName,
				superProjectPrefix, superProjectName, superVisitorClassName);
		} else {
			CS2ASCodeGenerator.generate(genPackage, projectPrefix, projectName, visitorPackageName, visitorClassName, visitableClassName,
				null, null, null);
		} */
	}
}
