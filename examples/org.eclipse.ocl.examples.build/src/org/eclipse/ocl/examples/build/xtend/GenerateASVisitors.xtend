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
import org.eclipse.ocl.pivot.internal.resource.ASSaver
import org.eclipse.ocl.pivot.internal.utilities.AS2Moniker
//import org.eclipse.ocl.pivot.internal.utilities.AS2XMIid
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal
import org.eclipse.ocl.pivot.internal.manager.FlowAnalysis
import org.eclipse.ocl.pivot.internal.resource.AbstractASSaver

class GenerateASVisitors extends GenerateVisitorsXtend
{
	override void generateVisitors(/*@NonNull*/ GenPackage genPackage) {
		var EPackage ePackage = genPackage.getEcorePackage();
		if (!isDerived()) {
			genPackage.generateVisitableInterface();
		}
		genPackage.generateVisitorInterface();
		ePackage.generateAbstractVisitor();
		ePackage.generateAbstractNullVisitor();
		ePackage.generateAbstractDelegatingVisitor();
		ePackage.generateAbstractExtendingVisitor();
		ePackage.generateAbstractMergedVisitor();
		ePackage.generateAbstractWrappingVisitor();
		if (isDerived()) {
//			ePackage.generateAbstractGenericVisitor("AS2XMIid", typeof(Boolean), typeof(AS2XMIid));
			ePackage.generateAbstractGenericVisitor("AS2Moniker", typeof(Object), typeof(AS2Moniker));
			ePackage.generateAbstractGenericVisitor("ASSaverLocate", typeof(Object), typeof(ASSaver));
			ePackage.generateAbstractGenericVisitor("ASSaverNormalize", typeof(Object), typeof(AbstractASSaver));
			ePackage.generateAbstractGenericVisitor("ASSaverResolve", typeof(Object), typeof(ASSaver));
			ePackage.generateAbstractTemplateParameterSubstitutionVisitor("TemplateParameterSubstitution", typeof(Object), typeof(EnvironmentFactoryInternal));
			ePackage.generateAbstractFlowAnalysisDeducerVisitor("FlowAnalysisDeducerFromFalse", typeof(Boolean), typeof(FlowAnalysis), false);
			ePackage.generateAbstractFlowAnalysisDeducerVisitor("FlowAnalysisDeducerFromNull", typeof(Boolean), typeof(FlowAnalysis), true);
			ePackage.generateAbstractFlowAnalysisDeducerVisitor("FlowAnalysisDeducerFromTrue", typeof(Boolean), typeof(FlowAnalysis), false);
//			ePackage.generateAbstractGenericVisitor("Stepper", typeof(IStepper), typeof(Object));
		}
		/* ePackage.generateDecorableVisitorInterface("org.eclipse.ocl.pivot.util.Visitor"); */
	}
}
