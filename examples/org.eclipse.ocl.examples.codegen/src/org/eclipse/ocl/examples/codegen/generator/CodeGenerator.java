/*******************************************************************************
 * Copyright (c) 2013, 2018 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.generator;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AnalysisVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.DependencyVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.FieldingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.examples.codegen.analyzer.ReferencesVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cse.CommonSubexpressionEliminator;
import org.eclipse.ocl.examples.codegen.cse.GlobalPlace;
import org.eclipse.ocl.examples.codegen.java.types.BoxedDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.EcoreDescriptor;
import org.eclipse.ocl.examples.codegen.java.types.UnboxedDescriptor;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;

public interface CodeGenerator
{
	boolean addConstrainedOperation(@NonNull Operation constrainedOperation);
	void addProblem(@NonNull Exception e);
	@NonNull AnalysisVisitor createAnalysisVisitor();
	@NonNull BoxingAnalyzer createBoxingAnalyzer();
	@NonNull CommonSubexpressionEliminator createCommonSubexpressionEliminator();
	@NonNull DependencyVisitor createDependencyVisitor();
	@NonNull FieldingAnalyzer createFieldingAnalyzer();
	@NonNull ReferencesVisitor createReferencesVisitor();
	@NonNull CodeGenAnalyzer getAnalyzer();
	@NonNull BoxedDescriptor getBoxedDescriptor(@NonNull ElementId elementId);
	@Nullable String getConstantsClass();
	@NonNull String getDefaultIndent();
	@NonNull EcoreDescriptor getEcoreDescriptor(@NonNull ElementId elementId, @Nullable Class<?> instanceClass);
	@NonNull EnvironmentFactoryInternal getEnvironmentFactory();
	@NonNull GenModelHelper getGenModelHelper();
	@NonNull GlobalContext getGlobalContext();
	@NonNull GlobalPlace getGlobalPlace();
	@Nullable IterationHelper getIterationHelper(@NonNull Iteration iteration);
	@NonNull NameManager getNameManager();
	@NonNull CodeGenOptions getOptions();
	@Nullable List<@NonNull Exception> getProblems();
	@NonNull TypeDescriptor getTypeDescriptor(@NonNull CGValuedElement cgElement);
	@NonNull UnboxedDescriptor getUnboxedDescriptor(@NonNull ElementId elementId);
	@Nullable Operation isFinal(@NonNull Operation anOperation, org.eclipse.ocl.pivot.@NonNull Class staticType);

	/**
	 * Return true if asOperationCallExp may return a nonNull value,
	 * false if asOperationCallExp may return a null value,
	 * null if no determination can be made.
	 */
	@Nullable Boolean isNonNull(@NonNull OperationCallExp asOperationCallExp);

	/**
	 * Return true if asProperty may provide a nonNull value,
	 * false if asOperationCallExp may provide a null value,
	 * null if no determination can be made.
	 */
	@Nullable Boolean isNonNull(@NonNull Property asProperty);

	/**
	 * Return true is this is a built-in primitive type such as boolean or int.
	 * Such types cannot have @NonNull annotations.
	 */
	boolean isPrimitive(@NonNull CGValuedElement cgValue);

	/**
	 * Return true if cgValue could be represented by a primitive value. i.e. if it cannot convey a null or invalid value.
	 */
	boolean maybePrimitive(@NonNull CGValuedElement cgValue);
}
