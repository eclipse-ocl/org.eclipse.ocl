/*******************************************************************************
 * Copyright (c) 2013, 2015 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.generator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AnalysisVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.internal.manager.FinalAnalysis;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;

public abstract class AbstractCodeGenerator implements CodeGenerator
{
	public static final @NonNull String ORG_ECLIPSE_JDT_ANNOTATION_NON_NULL = "org.eclipse.jdt.annotation.NonNull";
	public static final @NonNull String ORG_ECLIPSE_JDT_ANNOTATION_NULLABLE = "org.eclipse.jdt.annotation.Nullable";

	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull PivotMetamodelManager metamodelManager;
	protected final @NonNull NameManager nameManager;
	protected final @NonNull GenModelHelper genModelHelper;
	private /*@LazyNonNull*/ CodeGenOptions options = null;
	//
	private /*@LazyNonNull*/ List<Exception> problems = null;
	private @NonNull String defaultIndent = "    ";

	protected AbstractCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory) {
		this.environmentFactory = environmentFactory;
		this.metamodelManager = environmentFactory.getMetamodelManager();
		this.nameManager = createNameManager();
		this.genModelHelper = createGenModelHelper();
	}

	protected AbstractCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull NameManager nameManager,
			@NonNull GenModelHelper genModelHelper) {
		this.environmentFactory = environmentFactory;
		this.metamodelManager = environmentFactory.getMetamodelManager();
		this.nameManager = nameManager;
		this.genModelHelper = genModelHelper;
	}

	@Override
	public @NonNull EnvironmentFactoryInternal getEnvironmentFactory() {
		return environmentFactory;
	}
	
	@Override
	public void addProblem(@NonNull Exception problem) {
		List<Exception> problems2 = problems;
		if (problems2 == null) {
			problems = problems2 = new ArrayList<Exception>();
		}
		problems2.add(problem);
	}

	@Override
	public @NonNull AnalysisVisitor createAnalysisVisitor() {
		return new AnalysisVisitor(getAnalyzer());
	}

	protected abstract @NonNull GenModelHelper createGenModelHelper();

	protected abstract @NonNull NameManager createNameManager();

	protected @NonNull CodeGenOptions createOptions() {
		return new CodeGenOptions();
	}

	@Override
	public @NonNull String getDefaultIndent() {
		return defaultIndent;
	}

	@Override
	public @NonNull GenModelHelper getGenModelHelper() {
		return genModelHelper;
	}

	@Override
	public @NonNull NameManager getNameManager() {
		return nameManager;
	}

	@Override
	public @NonNull CodeGenOptions getOptions() {
		CodeGenOptions options2 = options;
		if (options2 == null) {
			options = options2 = createOptions();
		}
		return options2;
	}

	@Override
	public @Nullable Operation isFinal(@NonNull Operation anOperation, org.eclipse.ocl.pivot.@NonNull Class staticType) {
		PivotMetamodelManager metamodelManager = environmentFactory.getMetamodelManager();
		FinalAnalysis finalAnalysis = metamodelManager.getFinalAnalysis();
		return finalAnalysis.isFinal(anOperation, metamodelManager.getCompleteClass(staticType));
	}

	/**
	 * Return true if anOperation has an overload for invalid values.
	 *
	public boolean mayEvaluateForInvalid(@NonNull Operation anOperation) {
		Type targetType = metamodelManager.getOclInvalidType();
		String name = anOperation.getName();
		if (name == null) {
			return false;
		}
		DomainInheritance inheritance = targetType.getInheritance(metamodelManager);
		DomainInheritance[] arguments;
		List<Parameter> parameters = anOperation.getOwnedParameter();
		int iSize = parameters.size();
		if (iSize > 0) {
			arguments = new DomainInheritance[iSize];
			for (int i = 0; i < iSize; i++) {
				Parameter parameter = parameters.get(i);
				Type type = parameter.getType();
				if (type == null) {
					return false;
				}
				if (type.getOwningTemplateParameter() != null) {
					return false;					// FIX ME invalid not supported for templated operations
				}
				arguments[i] = type.getInheritance(metamodelManager);
			}
		}
		else {
			arguments = DomainInheritance.EMPTY_ARRAY;
		}
		DomainOperation localOperation = inheritance.lookupLocalOperation(metamodelManager, name, arguments);
		return localOperation != null;
	} */
	
//	protected abstract void resetLocals();
}
