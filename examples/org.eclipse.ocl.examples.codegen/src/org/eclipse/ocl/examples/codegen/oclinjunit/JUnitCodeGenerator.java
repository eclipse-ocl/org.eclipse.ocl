/*******************************************************************************
 * Copyright (c) 2013, 2022 CEA LIST and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink(CEA LIST) - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinjunit;

import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.NameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NameResolution;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaImportNameManager;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTablesUtils.CodeGenString;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Variable;
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

	protected final @NonNull CodeGenAnalyzer cgAnalyzer;

	protected JUnitCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable GenModel genModel, boolean useNullAnnotations) {
		super(environmentFactory, genModel);
		getOptions().setUseNullAnnotations(useNullAnnotations);
		cgAnalyzer = new CodeGenAnalyzer(this);
	}

	protected @NonNull CGPackage createCGPackage(@NonNull ExpressionInOCL expInOcl,
			@NonNull String packageName, @NonNull String className) {
		assert expInOcl.eContainer() == null;
		NameResolution evaluateNameResolution = globalNameManager.getEvaluateNameResolution();
	//	NameResolution typeIdNameResolution = globalContext.getTypeIdNameResolution();
		org.eclipse.ocl.pivot.Package asPackage = PivotFactory.eINSTANCE.createPackage();
		asPackage.setName(packageName);
	//	CGPackage cgPackage = CGModelFactory.eINSTANCE.createCGPackage();
//		cgPackage.setAst(asPackage);
	//	cgPackage.setName(packageName);
		//
		org.eclipse.ocl.pivot.Class asClass = PivotFactory.eINSTANCE.createClass();
		asClass.setName(className);
		asPackage.getOwnedClasses().add(asClass);
	//GClass cgRootClass = CGModelFactory.eINSTANCE.createCGClass();
	//gRootClass.setAst(asClass);
	//gRootClass.setName(className);
	//	cgPackage.getClasses().add(cgRootClass);
	//	cgAnalyzer.setCGRootClass(cgRootClass);
		//
		Operation asOperation = PivotFactory.eINSTANCE.createOperation();
		asOperation.setName(evaluateNameResolution.getResolvedName());
		asClass.getOwnedOperations().add(asOperation);
		asOperation.setBodyExpression(expInOcl);
	//	asOperation.setType(expInOcl.getType());
		asHelper.setType(asOperation, expInOcl.getType(), expInOcl.isIsRequired());
//		CGOperation cgOperation2 = CGModelFactory.eINSTANCE.createCGLibraryOperation();
//		cgOperation2.setAst(asOperation);
//		cgOperation2.setName("test");		// FIXME
//		cgRootClass.getOperations().add(cgOperation2);

		AS2CGVisitor as2cgVisitor = new JUnitAS2CGVisitor(this);
		CGPackage cgPackage = (CGPackage) ClassUtil.nonNullState(asPackage.accept(as2cgVisitor));
		CGClass cgRootClass = cgPackage.getClasses().get(0);
	//	cgAnalyzer.setCGRootClass(cgRootClass);
		assert cgRootClass != null;
		as2cgVisitor.pushClassNameManager(cgRootClass);


		//
		Variable contextVariable = expInOcl.getOwnedContext();
		if (contextVariable != null) {
			contextVariable.setIsRequired(false); // May be null for test
		}
		JUnitOperationCallingConvention junitCallingConvention = JUnitOperationCallingConvention.INSTANCE;
		CGOperation cgOperation = junitCallingConvention.createCGOperation(as2cgVisitor, asClass, asOperation);
		cgOperation.setCallingConvention(junitCallingConvention);
		evaluateNameResolution.addCGElement(cgOperation);
		as2cgVisitor.initAst(cgOperation, asOperation/*expInOcl*/);
		as2cgVisitor.pushNestedNameManager(cgOperation);
		junitCallingConvention.createCGParameters(as2cgVisitor, cgOperation, expInOcl);
		junitCallingConvention.createCGBody(as2cgVisitor, cgOperation);
		cgRootClass.getOperations().add(cgOperation);
		cgAnalyzer.analyzeExternalFeatures(as2cgVisitor);
		as2cgVisitor.popNestedNameManager();
		as2cgVisitor.popClassNameManager();
		as2cgVisitor.freeze();
		return cgPackage;
	}

	@Override
	public @NonNull NestedNameManager createNestedNameManager(@NonNull NameManager outerNameManager, @NonNull CGNamedElement cgScope) {
		return new JUnitNestedNameManager(this, outerNameManager, cgScope);
	}

	protected @NonNull String generate(@NonNull ExpressionInOCL expInOcl, @NonNull String packageName, @NonNull String className) {
		CGPackage cgPackage = createCGPackage(expInOcl, packageName, className);
		optimize(cgPackage);
		Iterable<@NonNull CGValuedElement> sortedGlobals = pregenerate(cgPackage);
		JUnitCG2JavaClassVisitor cg2JavaClassVisitor = new JUnitCG2JavaClassVisitor(this, expInOcl, sortedGlobals);
		cg2JavaClassVisitor.safeVisit(cgPackage);
		ImportNameManager importNameManager = cg2JavaClassVisitor.getImportNameManager();
	//	for (@NonNull CGClass cgClass : cgPackage.getClasses()) {
	//		for (@NonNull CGClass cgClass : cgPackage.getClasses()) {
	//			importNameManager.reserveLocalName(CGUtil.getName(cgClass));
	//		}
	//	}
		Map<@NonNull String, @Nullable String> long2ShortImportNames = importNameManager.getLong2ShortImportNames();
		return ImportUtils.resolveImports(cg2JavaClassVisitor.toString(), long2ShortImportNames, false);
	}

	@Override
	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return cgAnalyzer;
	}

	@Override
	public @NonNull JavaImportNameManager getImportNameManager() {
		return (JavaImportNameManager) super.getImportNameManager();
	}

	@Override
	public @NonNull String getQualifiedForeignClassName(org.eclipse.ocl.pivot.@NonNull Class asClass) {
	// XXX	assert false : "Unsupported getQualifiedForeignClassName";
	//	return JavaConstants.FOREIGN_CLASS_PREFIX + "statics_" + PivotUtil.getName(asClass);
		CodeGenString s = new CodeGenString(environmentFactory.getMetamodelManager(), false);
	//	s.append(genModelHelper.getQualifiedTableClassName(genPackage));
	//	s.append(".");
		s.append(JavaConstants.EXTERNAL_CLASS_PREFIX);
		s.appendAndEncodeQualifiedName(asClass);
		return s.toString();
	}
}
