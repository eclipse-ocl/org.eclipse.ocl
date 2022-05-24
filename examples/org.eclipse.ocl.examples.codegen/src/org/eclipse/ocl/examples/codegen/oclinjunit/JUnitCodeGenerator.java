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
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.LocalContext;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaImportNameManager;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTablesUtils.CodeGenString;
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

	protected final @NonNull CodeGenAnalyzer cgAnalyzer;

	protected JUnitCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable GenModel genModel, boolean useNullAnnotations) {
		super(environmentFactory, genModel);
		getOptions().setUseNullAnnotations(useNullAnnotations);
		cgAnalyzer = new CodeGenAnalyzer(this);
	}

	protected @NonNull CGPackage createCGPackage(@NonNull ExpressionInOCL expInOcl,
			@NonNull String packageName, @NonNull String className) {
		NameResolution evaluateNameResolution = globalNameManager.getEvaluateNameResolution();
	//	NameResolution typeIdNameResolution = globalContext.getTypeIdNameResolution();
		CGPackage cgPackage = CGModelFactory.eINSTANCE.createCGPackage();
		cgPackage.setName(packageName);
		//
		CGClass cgRootClass = CGModelFactory.eINSTANCE.createCGClass();
		cgRootClass.setName(className);
		cgPackage.getClasses().add(cgRootClass);
		cgAnalyzer.setCGRootClass(cgRootClass);
		//
		Variable contextVariable = expInOcl.getOwnedContext();
		if (contextVariable != null) {
			contextVariable.setIsRequired(false); // May be null for test
		}
		AS2CGVisitor as2cgVisitor = new JUnitAS2CGVisitor(this);
		CGOperation cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		JUnitOperationCallingConvention junitCallingConvention = JUnitOperationCallingConvention.INSTANCE;
		//	junitCallingConvention.createCGOperationWithoutBody(as2cgVisitor, asOperation);		// no root asOperation
		cgOperation.setCallingConvention(junitCallingConvention);
		as2cgVisitor.initAst(cgOperation, expInOcl);
		LocalContext savedLocalContext = as2cgVisitor.pushLocalContext(cgOperation);
		junitCallingConvention.createCGParameters(as2cgVisitor, cgOperation, expInOcl);
	//	cgOperation.setAst(expInOcl);
		Type type = expInOcl.getType();
		assert type != null;
		TypeId asTypeId = type/*.behavioralType()*/.getTypeId();
		cgOperation.setTypeId(cgAnalyzer.getCGTypeId(asTypeId));
		evaluateNameResolution.addCGElement(cgOperation);
		CGValuedElement cgBody = (CGValuedElement) ClassUtil.nonNullState(expInOcl.accept(as2cgVisitor));
		cgOperation.setBody(cgBody);
	//	cgClass.getOperations().add(cgOperation);

	//	Iterable<@NonNull CGClass> cgForeignClasses = cgAnalyzer.analyzeForeignFeatures(as2cgVisitor);
	//	if (cgForeignClasses != null) {
	//		List<CGClass> cgNestedClasses = cgClass.getClasses();
	//		for (@NonNull CGClass cgForeignClass : cgForeignClasses) {
	//			cgNestedClasses.add(cgForeignClass);
	//		}
	//	}
		cgRootClass.getOperations().add(cgOperation);
		cgAnalyzer.analyzeExternalFeatures(as2cgVisitor);
		as2cgVisitor.popLocalContext(savedLocalContext);
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
		Iterable<@NonNull CGValuedElement> sortedGlobals = prepareGlobals();				// XXX support globals within cgRootClass
		resolveNames(sortedGlobals, cgPackage);		// XXX share with OCLinEcoreCG
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
