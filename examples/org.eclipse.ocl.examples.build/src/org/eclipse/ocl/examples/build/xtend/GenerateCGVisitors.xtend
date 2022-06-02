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
import org.eclipse.ocl.examples.codegen.analyzer.FieldingAnalyzer.ReturnState
import org.eclipse.ocl.examples.codegen.analyzer.FieldingAnalyzer

class GenerateCGVisitors extends GenerateVisitorsXtend
{
	override void generateVisitors(/*@NonNull*/ GenPackage genPackage) {
		var EPackage ePackage = genPackage.getEcorePackage();
		ePackage.generateAbstractExtendingVisitor();
		ePackage.generateAbstractNonNullExtendingVisitor();
		genPackage.generateVisitorInterface();
		/* ePackage.generateDecorableVisitorInterface("org.eclipse.ocl.xtext.base.util.BaseCSVisitor"); */
		ePackage.generateAbstractVisitor();
		ePackage.generateAbstractNullVisitor();
		if (isDerived()) {
			ePackage.generateAbstractFieldingAnalyzerVisitor("FieldingAnalysis", typeof(ReturnState), typeof(FieldingAnalyzer));
		}
	}
}
