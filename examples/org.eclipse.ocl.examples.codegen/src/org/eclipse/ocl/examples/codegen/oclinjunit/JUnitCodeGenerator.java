/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinjunit;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 * JUnitCodeGenerator supports generation of an ExpressionInOCL for execution in a JUNit test.
 * The ExpressionInOCL is wrpapped in an Operation in a Class with a static INSTANCE to provide
 * the polymorphic implementation of a LibraryOperation.
 */
public class JUnitCodeGenerator extends JavaCodeGenerator
{
	public static @NonNull String generateClassFile(@NonNull EnvironmentFactoryInternal environmentFactory, @NonNull ExpressionInOCL query,
			@NonNull String packageName, @NonNull String className) {
		CompleteEnvironmentInternal completeEnvironment = environmentFactory.getMetamodelManager().getCompleteEnvironment();
		boolean savedIsCodeGenerator = completeEnvironment.isCodeGeneration();
		try {
			completeEnvironment.setCodeGeneration(true);		// Workaround for BUG 452621
			JUnitCodeGenerator expressionInOCL2Class = new JUnitCodeGenerator(environmentFactory, null, true);
			return expressionInOCL2Class.generate(query, packageName, className);
		}
		finally {
			completeEnvironment.setCodeGeneration(savedIsCodeGenerator);
		}
	}

	protected final @NonNull JavaGlobalContext<@NonNull JUnitCodeGenerator> globalContext = new JavaGlobalContext<>(this);
	protected final @NonNull CodeGenAnalyzer cgAnalyzer;

	protected JUnitCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable GenModel genModel, boolean useNullAnnotations) {
		super(environmentFactory, genModel);
		getOptions().setUseNullAnnotations(useNullAnnotations);
		cgAnalyzer = new CodeGenAnalyzer(this);
	}

	protected @NonNull CGPackage createCGPackage(@NonNull ExpressionInOCL expInOcl,
			@NonNull String packageName, @NonNull String className) {
		CGPackage cgPackage = CGModelFactory.eINSTANCE.createCGPackage();
		cgPackage.setName(packageName);
		//
		CGClass cgClass = CGModelFactory.eINSTANCE.createCGClass();
		cgClass.setName(className);
		cgPackage.getClasses().add(cgClass);
		//
		Variable contextVariable = expInOcl.getOwnedContext();
		if (contextVariable != null) {
			contextVariable.setIsRequired(false); // May be null for test
		}
		AS2CGVisitor as2cgVisitor = new JUnitAS2CGVisitor(cgAnalyzer);
		CGValuedElement cgBody = (CGValuedElement) ClassUtil.nonNullState(expInOcl.accept(as2cgVisitor));
		CGOperation cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		List<CGParameter> cgParameters = cgOperation.getParameters();
		JavaLocalContext<@NonNull ?> localContext = globalContext.getLocalContext(cgOperation);
		if (localContext != null) {
			CGParameter executorParameter = localContext.createExecutorParameter();
			if (executorParameter != null) {
				cgParameters.add(executorParameter);
			}
			CGParameter typeIdParameter = localContext.createTypeIdParameter();
			if (typeIdParameter != null) {
				cgParameters.add(typeIdParameter);
			}
		}
		if (contextVariable != null) {
			CGParameter cgContext = as2cgVisitor.getParameter(contextVariable, null);
			cgParameters.add(cgContext);
		}
		for (@SuppressWarnings("null")@NonNull Variable parameterVariable : expInOcl.getOwnedParameters()) {
			CGParameter cgParameter = as2cgVisitor.getParameter(parameterVariable, null);
			cgParameters.add(cgParameter);
		}
		cgOperation.setAst(expInOcl);
		Type type = expInOcl.getType();
		assert type != null;
		TypeId asTypeId = type/*.behavioralType()*/.getTypeId();
		cgOperation.setTypeId(cgAnalyzer.getTypeId(asTypeId));
		cgOperation.setName(globalContext.getEvaluateName());
		cgOperation.setBody(cgBody);
		cgClass.getOperations().add(cgOperation);
		return cgPackage;
	}

	protected @NonNull String generate(@NonNull ExpressionInOCL expInOcl, @NonNull String packageName, @NonNull String className) {
		CGPackage cgPackage = createCGPackage(expInOcl, packageName, className);
		optimize(cgPackage);
		Iterable<@NonNull CGValuedElement> sortedGlobals = prepareGlobals();
		JUnitCG2JavaClassVisitor cg2JavaClassVisitor = new JUnitCG2JavaClassVisitor(this, expInOcl, sortedGlobals);
		cg2JavaClassVisitor.safeVisit(cgPackage);
		ImportNameManager importNameManager = cg2JavaClassVisitor.getImportNameManager();
		Map<@NonNull String, @Nullable String> long2ShortImportNames = importNameManager.getLong2ShortImportNames();
		return ImportUtils.resolveImports(cg2JavaClassVisitor.toString(), long2ShortImportNames, false);
	}

	@Override
	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return cgAnalyzer;
	}

	@Override
	public @NonNull JavaGlobalContext<@NonNull JUnitCodeGenerator> getGlobalContext() {
		return globalContext;
	}
}
