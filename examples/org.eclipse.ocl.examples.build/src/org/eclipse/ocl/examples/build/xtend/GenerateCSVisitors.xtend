/*******************************************************************************
 * Copyright (c) 2013 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.build.xtend

import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage

 class GenerateCSVisitors extends GenerateVisitorsXtend
{
	override void generateVisitors(/*@NonNull*/ GenPackage genPackage) {
		var EPackage ePackage = genPackage.getEcorePackage();
		if (!isDerived()) {
			genPackage.generateVisitableInterface();
		}
		genPackage.generateVisitorInterface();
		ePackage.generateDecorableVisitorInterface("org.eclipse.ocl.xtext.basecs.util.BaseCSVisitor");
		ePackage.generateAbstractVisitor();
		ePackage.generateAbstractNullVisitor();
		ePackage.generateAbstractDelegatingVisitor();
		ePackage.generateAbstractExtendingVisitor();
		/* ePackage.generateAbstractExtendingDelegatingVisitor();*/
	}
}
