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
import org.eclipse.jdt.annotation.NonNull
import org.eclipse.jdt.annotation.Nullable
import com.google.common.collect.Lists
import java.util.List
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator
import org.eclipse.ocl.examples.codegen.cse.GlobalPlace
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement
import org.eclipse.ocl.examples.codegen.analyzer.NameManagerHelper

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
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(NonNull, Nullable, CodeGenAnalyzer),
				"Analysis", "@Nullable Object", "@NonNull CodeGenAnalyzer analyzer", "analyzer");
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(NonNull, Nullable, CodeGenAnalyzer),
				"BoxingAnalysis", "@Nullable Object", "@NonNull CodeGenAnalyzer analyzer", "analyzer");
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(Iterable, NonNull, Nullable, JavaCodeGenerator, CGPackage, CGValuedElement),
				"CG2Java", "@NonNull Boolean", "@NonNull JavaCodeGenerator codeGenerator, @NonNull CGPackage cgPackage, @Nullable Iterable<@NonNull CGValuedElement> sortedGlobals", "codeGenerator, cgPackage, sortedGlobals");
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(NonNull, Nullable, JavaCodeGenerator),
				"CG2JavaName", "@Nullable Object", "@NonNull JavaCodeGenerator codeGenerator", "codeGenerator");
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(NonNull, Nullable, JavaCodeGenerator),
				"CG2JavaPre", "@Nullable Object", "@NonNull JavaCodeGenerator codeGenerator", "codeGenerator");
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(NonNull, NameManagerHelper),
				"CGNameHelper", "@NonNull String", "@NonNull NameManagerHelper context", "context");
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(NonNull, Nullable),
				"CG2String", "@Nullable String", "", "");
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(NonNull, JavaCodeGenerator, GlobalPlace),
				"Dependency", "Object", "@NonNull JavaCodeGenerator codeGenerator, @NonNull GlobalPlace globalPlace", "codeGenerator, globalPlace");
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(NonNull, FieldingAnalyzer, ReturnState),
				"FieldingAnalysis", "@NonNull ReturnState", "@NonNull FieldingAnalyzer context, @NonNull ReturnState requiredReturn", "context, requiredReturn");
			ePackage.generateAbstractGenericVisitor2(Lists.newArrayList(List, NonNull, Nullable),
				"References", "@NonNull List<@Nullable Object>", "@Nullable Object context", "context");
		}
	}
}
