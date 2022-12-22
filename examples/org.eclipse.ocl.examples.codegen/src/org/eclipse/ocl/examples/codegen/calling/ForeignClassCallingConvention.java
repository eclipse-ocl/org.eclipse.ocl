/*******************************************************************************
 * Copyright (c) 2022 Willink Transformation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.codegen.calling;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ExternalClassCallingConvention defines the style of a nested Class that augments an external Java class.
 */
@Deprecated // XXX not used
public class ForeignClassCallingConvention extends AbstractClassCallingConvention
{
	private static final @NonNull ForeignClassCallingConvention INSTANCE = new ForeignClassCallingConvention();

//	public static @NonNull ForeignClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
//		INSTANCE.logInstance(asClass);
//		return INSTANCE;
//	}

	public static @NonNull ForeignClassCallingConvention getInstance(@NonNull Property asProperty) {
		INSTANCE.logInstance(asProperty);
		return INSTANCE;
	}

	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
		installCGRootClassParent(analyzer, cgClass, asClass);
		return cgClass;
	}

	public @NonNull CGClass createForeignClass(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		boolean isIncremental = codeGenerator.getOptions().isIncremental();
		GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		Property asProperty = CGUtil.getAST(cgProperty);
		org.eclipse.ocl.pivot.Class asClass = PivotUtil.getOwningClass(asProperty);
		org.eclipse.ocl.pivot.@NonNull Package asParentPackage = getParentPackage(analyzer, asProperty);
		//
		PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, asParentPackage);
		String entryClassName = packageNameManager.getUniqueClassName(NameManagerHelper.FOREIGN_CLASS_NAME_PREFIX, asClass);
		org.eclipse.ocl.pivot.Class asForeignClass = AbstractLanguageSupport.getClass(asParentPackage, entryClassName);
	//	analyzer.addCachedOperation(asForeignClass, asOperation);
	//	org.eclipse.ocl.pivot.Class asEntrySuperClass = jLanguageSupport.getNativeClass(isIncremental ? AbstractComputation.Incremental.class : AbstractComputation.class);
	//	asEntryClass.getSuperClasses().add(asEntrySuperClass);
		importNameManager.reserveLocalName(PivotUtil.getName(asForeignClass));
		//
		CGClass cgForeignClass = analyzer.generateClassDeclaration(asForeignClass, this);
	//	CGClass cgEntrySuperClass = analyzer.generateClassDeclaration(asEntrySuperClass, null);
	//	cgEntryClass.getSuperTypes().add(cgEntrySuperClass);
		//
	//	NameResolution contextNameResolution = getContextNameResolution(globalNameManager);
	//	org.eclipse.ocl.pivot.Class asContextClass = getContextClass(analyzer, cgEntryClass);
	//	createEntryProperty(analyzer, cgEntryClass, contextNameResolution, asContextClass);
	//	for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
	//		createEntryProperty(analyzer, cgEntryClass, null, asParameter);
	//		// XXX need to support a cached invalid
	//	}
	//	NameResolution cachedResultNameResolution = globalNameManager.getCachedResultNameResolution();
	//	createEntryProperty(analyzer, cgEntryClass, cachedResultNameResolution, asOperation);
		//
	//	installConstructorOperation(analyzer, cgEntryClass, asOperation);
	//	installGetResultOperation(analyzer, cgEntryClass, asOperation);
	//	installIsEqualOperation(analyzer, cgEntryClass, asOperation);
		return cgForeignClass;
	}

	/**
	 * Generate the Java code for a Class declaration.
	 * Returns true if control flow continues, false if an exception throw has been synthesized.
	 */
	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGClass cgClass) {
	//	if (isEmpty(cgClass)) {
	//		return true;
	//	}
		JavaStream js = cg2javaVisitor.getJavaStream();
		js.appendOptionalBlankLine();
		String className = CGUtil.getName(cgClass);
		CGPackage cgContainingPackage = cgClass.getContainingPackage();
		assert cgContainingPackage == null;
		String title = cgClass.getName() + " provides the Java implementation for the foreign features of\n";
		js.appendCommentWithOCL(title, cgClass.getAst());
		js.append("public static class " + className);
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		generateProperties(cg2javaVisitor, cgClass);
		generateOperations(cg2javaVisitor, cgClass);
		js.popClassBody(false);
		return true;
	}

	@Override
	public @NonNull String getName(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull NamedElement asNamedElement) {
		return analyzer.getCodeGenerator().getExternalClassName((org.eclipse.ocl.pivot.Class)asNamedElement);
	}

	@Override
	protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Feature asFeature) {
		return getRootClassParentPackage(analyzer, asFeature);
	}
}
