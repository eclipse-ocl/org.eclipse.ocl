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
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.ClassCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.JUnitClassCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaImportNameManager;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.FeatureNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreTablesUtils.CodeGenString;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.internal.complete.CompleteEnvironmentInternal;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;

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

	private org.eclipse.ocl.pivot.@Nullable Class asTestClass = null;
	private @Nullable Operation asTestOperation = null;

	protected JUnitCodeGenerator(@NonNull EnvironmentFactoryInternal environmentFactory, @Nullable GenModel genModel, boolean useNullAnnotations) {
		super(environmentFactory, genModel);
		getOptions().setUseNullAnnotations(useNullAnnotations);
	}

	@Override
	public @NonNull AS2CGVisitor createAS2CGVisitor(@NonNull CodeGenAnalyzer analyzer) {
		return new JUnitAS2CGVisitor(analyzer);
	}

	protected @NonNull CGPackage createCGPackage(@NonNull ExpressionInOCL expInOcl,			// XXX Change to createASPackage then regular AS2CG
			@NonNull String qualifiedPackageName, @NonNull String className) {
		assert expInOcl.eContainer() == null;
		assert asTestOperation == null;
		//
		Variable contextVariable = expInOcl.getOwnedContext();
		if (contextVariable != null) {
			contextVariable.setIsRequired(false); // May be null for test
		}
		Resource eResource = expInOcl.eResource();
		NameResolution evaluateNameResolution = globalNameManager.getEvaluateNameResolution();
		@NonNull String packageNames[] = qualifiedPackageName.split("\\.");
		org.eclipse.ocl.pivot.Package asRootPackage = null;
		org.eclipse.ocl.pivot.Package asLeafPackage = null;
		for (@NonNull String packageName : packageNames) {
			org.eclipse.ocl.pivot.Package asPackage = PivotFactory.eINSTANCE.createPackage();
			asPackage.setName(packageName);
			if (asLeafPackage != null) {
				asLeafPackage.getOwnedPackages().add(asPackage);
			}
			else {
				asRootPackage = asPackage;
				if (eResource != null) {
					eResource.getContents().add(asRootPackage);
				}
			}
			asLeafPackage = asPackage;
		}
		assert asRootPackage != null;
		assert asLeafPackage != null;
		//
		org.eclipse.ocl.pivot.Class asClass = asTestClass = PivotFactory.eINSTANCE.createClass();
		asClass.setName(className);
		asLeafPackage.getOwnedClasses().add(asClass);
		//
		Operation asOperation = asTestOperation = PivotFactory.eINSTANCE.createOperation();
		asOperation.setName(evaluateNameResolution.getResolvedName());
		asClass.getOwnedOperations().add(asOperation);
		asOperation.setBodyExpression(expInOcl);
		asHelper.setType(asOperation, expInOcl.getType(), expInOcl.isIsRequired());

		CGPackage cgPackage = cgAnalyzer.createCGElement(CGPackage.class, asRootPackage);
		cgAnalyzer.analyzeExternalFeatures();
		return cgPackage;
	}

	@Override
	public @NonNull FeatureNameManager createFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGConstraint cgConstraint) {
		return new JUnitFeatureNameManager(classNameManager, cgConstraint);
	}

	@Override
	public @NonNull FeatureNameManager createFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull FeatureNameManager outerNameManager, @NonNull CGIterationCallExp cgIterationCallExp) {
		return new JUnitFeatureNameManager(classNameManager, outerNameManager, cgIterationCallExp);
	}

	@Override
	public @NonNull FeatureNameManager createFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGOperation cgOperation) {
		return new JUnitFeatureNameManager(classNameManager, cgOperation);
	}

	@Override
	public @NonNull FeatureNameManager createFeatureNameManager(@NonNull ClassNameManager classNameManager, @NonNull CGProperty cgProperty) {
		return new JUnitFeatureNameManager(classNameManager, cgProperty);
	}

	protected @NonNull String generate(@NonNull ExpressionInOCL expInOcl, @NonNull String packageName, @NonNull String className) {
		CGPackage cgPackage = createCGPackage(expInOcl, packageName, className);
		optimize(cgPackage);
		Iterable<@NonNull CGValuedElement> sortedGlobals = pregenerate(cgPackage);
		JUnitCG2JavaClassVisitor cg2JavaClassVisitor = new JUnitCG2JavaClassVisitor(this, expInOcl, sortedGlobals);
		cgAnalyzer.setGlobals(sortedGlobals);
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
	public @NonNull ClassCallingConvention getCallingConvention(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		assert asTestClass != null;
		if (asClass == asTestClass)  {
			return JUnitClassCallingConvention.INSTANCE;
		}
		return super.getCallingConvention(asClass);
	}

	@Override
	public @NonNull OperationCallingConvention getCallingConvention(@NonNull Operation asOperation, boolean requireFinal) {
		assert asTestOperation != null;
		if (asOperation == asTestOperation)  {
			return JUnitOperationCallingConvention.INSTANCE;
		}
		return super.getCallingConvention(asOperation, requireFinal);
	}

	@Override
	public @NonNull JavaImportNameManager getImportNameManager() {
		return (JavaImportNameManager) super.getImportNameManager();
	}

	@Override
	public @NonNull String getQualifiedForeignClassName(org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CodeGenString s = new CodeGenString(environmentFactory.getMetamodelManager(), false);
	//	s.append(genModelHelper.getQualifiedTableClassName(genPackage));
	//	s.append(".");
		s.append(JavaConstants.EXTERNAL_CLASS_PREFIX);
		s.appendAndEncodeQualifiedName(asClass);
		return s.toString();
	}
}
