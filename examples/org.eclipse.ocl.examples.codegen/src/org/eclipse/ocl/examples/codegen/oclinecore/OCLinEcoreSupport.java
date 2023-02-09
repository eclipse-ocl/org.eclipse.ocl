/*******************************************************************************
 * Copyright (c) 2023 Willink Transformations and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.oclinecore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.ImportManager;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.AbstractClassCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.ClassCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.ImportUtils;
import org.eclipse.ocl.examples.codegen.java.JavaImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.oclinecore.OCLinEcoreGenModelGeneratorAdapter.OCLinEcoreStateAdapter;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.evaluation.AbstractExecutionSupport;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

import com.google.common.collect.Lists;

/**
 * The OCLinEcoreSupport instance maintains the state passed from the pre-generate to the generate phase.
 * This state eventually materializes in the XXXSupport class.
 */
public class OCLinEcoreSupport
{
	/**
	 *  OCLinEcoreSupportClassCallingConvention defines the support class augmenting nested classes/operations/properties
	 *  that cannot  be reifoed in Ecore.
	 */
	protected class OCLinEcoreSupportClassCallingConvention extends AbstractClassCallingConvention
	{
//		private static final @NonNull OCLinEcoreSupportClassCallingConvention INSTANCE = new OCLinEcoreSupportClassCallingConvention();

//		public static @NonNull OCLinEcoreSupportClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
//			INSTANCE.logInstance(asClass);
//			return INSTANCE;
//		}

		public OCLinEcoreSupportClassCallingConvention(@NonNull Class asClass) {
			logInstance(asClass);
		}

		@Override
		public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
			CGClass cgClass = createCGClass();
			installCGDefaultClassParent(analyzer, cgClass, asClass);
			return cgClass;
		}

		/**
		 * Generate the Java code for a Class declaration.
		 * Returns true if control flow continues, false if an exception throw has been synthesized.
		 */
		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2java, @NonNull CGClass cgClass) {
			GlobalNameManager globalNameManager = cg2java.getGlobalNameManager();
			NameResolution rootExecutorName = globalNameManager.getRootExecutorName();
			String supportPackageName = cgClass.getContainingPackage().toString().replace("::", "."); //generator.getQualifiedSupportClassName();
		//	int index = qualifiedSupportClassName.lastIndexOf(".");
		//	String supportPackageName = index >= 0 ? qualifiedSupportClassName.substring(0, index) : null;
			String supportClassName = CGUtil.getName(cgClass);
			JavaStream js = cg2java.getJavaStream();

			js.append("/*******************************************************************************\n");
			//	if (copyright != null) {
			//		String copyrightText = " * " + copyright.replace("\r", "") + "\n";
			//		s1.append(copyrightText.replaceAll("\\s+\\n", "\n"));
			//	}
			js.append(" *************************************************************************\n");
			js.append(" * This code is 100% auto-generated\n");
			js.append(" * from:\n");
			for (GenPackage genPackage : genModel.getAllUsedGenPackagesWithClassifiers()) {
			//		EObject eRoot = ((EObject)dPackage).eContainer();
			//		if (eRoot instanceof Model) {
			//			s1.append(" *   " + deresolveFileName(((Model)eRoot).getExternalURI()) + "\n");
			//		}
			}
			js.append(" * using:\n");
			//	js.append(" *   " + deresolveFileName(genModel.eResource().getURI().toString()) + "\n");
			js.append(" *   " + getClass().getName() + "\n");
			js.append(" *\n");
			js.append(" * Do not edit it.\n");
			js.append(" *******************************************************************************/\n");
			if ((supportPackageName != null) && (supportPackageName.length() > 0)) {
				js.append("package ");
				js.append(supportPackageName);
				js.append(";\n\n");
			}
			js.append(ImportUtils.IMPORTS_MARKER + "\n");
//				js.appendClassHeader(null);
			js.append("public class ");
			js.append(supportClassName);
			js.append(" extends ");
			js.appendClassReference(null, AbstractExecutionSupport.class);
			js.pushClassBody(supportClassName);
			js.appendOptionalBlankLine();
			if (cgGlobals != null) {
				cg2java.generateGlobals(cgGlobals);
			}
			generatePropertyDeclarations(cg2java, cgClass);
			js.appendOptionalBlankLine();
			js.append("public ");
			js.append(supportClassName);
			js.append("(");
			js.appendClassReference(true, Executor.class);
			js.append(" ");
			js.appendName(rootExecutorName);
			js.append(") {\n");
			js.pushIndentation(null);
			js.append("super(");
			js.appendName(rootExecutorName);
			js.append(");\n");
			generatePropertyInitializations(cg2java, cgClass);
			js.popIndentation();
			js.append("}\n");
			//
		//	generateProperties(cg2java, cgClass);
			generateOperations(cg2java, cgClass);
			generateClasses(cg2java, cgClass);
			js.popClassBody(false);
			return true;
		}

		@Override
		protected boolean isEmpty(@NonNull CGClass cgClass) {
			return false;
		}
	}

	protected final @NonNull GenModel genModel;
	protected final @NonNull OCLinEcoreStateAdapter stateAdapter;
	protected final org.eclipse.ocl.pivot.@NonNull Package asSupportPackage;
	protected final org.eclipse.ocl.pivot.@NonNull Class asSupportClass;
	private /*@LazyNonNull*/ OCLinEcoreCodeGenerator generator;
	private /*@LazyNonNull*/ CGClass cgSupportClass;

	/**
	 * The global constants used by operation and property bodies that must be emitted
	 * as part of the Tables classes.
	 */
	private @Nullable Iterable<@NonNull CGValuedElement> cgGlobals = null;

	public OCLinEcoreSupport(@NonNull GenModel genModel, @NonNull OCLinEcoreStateAdapter stateAdapter, org.eclipse.ocl.pivot.@NonNull Package asSupportPackage, org.eclipse.ocl.pivot.@NonNull Class asSupportClass) {
		this.genModel = genModel;
		this.stateAdapter = stateAdapter;
		this.asSupportPackage = asSupportPackage;
		this.asSupportClass = asSupportClass;
	//	this.tablesPostamble = OCLinEcoreGenModelGeneratorAdapter.tablesPostamble(genModel);
	}

	public @NonNull Map<@NonNull String, @NonNull String> createFeatureBodies(@NonNull ImportManager importManager) throws IOException {
		EnvironmentFactoryInternal environmentFactory = PivotUtilInternal.getEnvironmentFactory(genModel);
		this.generator = new OCLinEcoreCodeGenerator(environmentFactory, genModel);
		CodeGenAnalyzer analyzer = generator.getAnalyzer();
		analyzer.setRootClass(asSupportClass);
		Map<@NonNull CGPackage, @Nullable GenPackage> cgPackage2genPackage = this.generator.generateDeclarations(this);
		CGPackage cgSupportPackage = analyzer.generatePackageDeclaration(asSupportPackage);		// After GenPackages to avoid usurping an ancestor
		cgPackage2genPackage.put(cgSupportPackage, null);
		ClassCallingConvention callingConvention = new OCLinEcoreSupportClassCallingConvention(asSupportClass);
		this.cgSupportClass = analyzer.generateClassDeclaration(asSupportClass, callingConvention);
		analyzer.generateQueuedClassesContents();						// Contents of user-defined classes
		Map<@NonNull String, @NonNull String> uri2body = this.generator.generateBodies(this, cgPackage2genPackage);
		for (@NonNull String uri : uri2body.keySet()) {
			String bodyText = uri2body.get(uri);
			assert bodyText != null;
			bodyText = ImportUtils.rewriteManagedImports(bodyText, null);	// Adjust bodies to suit JET
			uri2body.put(uri, bodyText);
		}
		analyzer.generateQueuedClassesContents();						// Additional contents of referenced foreign classes
		return uri2body;
	}

	public void generateSupportAndTables(@NonNull File projectFolder) throws IOException {
		generateSupportClass(projectFolder);
		List<@NonNull GenPackage> genPackages = ClassUtil.nullFree(genModel.getAllGenPackagesWithClassifiers());
		for (@NonNull GenPackage genPackage : genPackages) {
			generateTablesClass(projectFolder, genPackage);
		}
	}

	protected void generateSupportClass(@NonNull File projectFolder) throws IOException {
		String qualifiedSupportClassName = generator.getQualifiedSupportClassName();
		int index = qualifiedSupportClassName.lastIndexOf(".");
		String supportPackageName = index >= 0 ? qualifiedSupportClassName.substring(0, index) : null;
		String supportClassName = cgSupportClass.getName();
		File supportFolder = supportPackageName != null ? new File(projectFolder, supportPackageName.replace(".", "/")) : projectFolder;
		supportFolder.mkdirs();
		CG2JavaVisitor cg2java = new CG2JavaVisitor(generator)
		{
			@Override
			protected @NonNull ImportNameManager createImportNameManager() {
				return new JavaImportNameManager();
			}

		};
		JavaStream js = cg2java.getJavaStream();
		cg2java.visitCGClass(cgSupportClass);
		Map<@NonNull String, @Nullable String> long2ShortImportNames = js.getImportNameManager().getLong2ShortImportNames();
		String javaSourceCode = ImportUtils.resolveImports(cg2java.toString(), long2ShortImportNames, false);
		File supportFile = new File(supportFolder, supportClassName + ".java");
		FileWriter supportWriter = new FileWriter(supportFile);
		supportWriter.append(javaSourceCode);
		supportWriter.close();
	}

	protected void generateTablesClass(@NonNull File projectFolder, @NonNull GenPackage genPackage) throws IOException {
		OCLinEcoreTables generateTables = new OCLinEcoreTables(genPackage);
		generateTables.analyzeExpressions();
		String tablesClass = generateTables.getTablesClassName();
		String dir = genPackage.getReflectionPackageName().replace(".", "/");
		generateTables.generateTablesClass();
		String str = generateTables.toString();
		File tablesFolder = new File(projectFolder, dir);
		tablesFolder.mkdirs();
		File file = new File(tablesFolder, tablesClass + ".java");
		FileWriter tablesWriter = new FileWriter(file);
		tablesWriter.append(str);
		tablesWriter.close();
	}

	public void setGlobals(@NonNull Iterable<@NonNull CGValuedElement> cgGlobals) {
		this.cgGlobals = Lists.newArrayList(cgGlobals);
	}
}
