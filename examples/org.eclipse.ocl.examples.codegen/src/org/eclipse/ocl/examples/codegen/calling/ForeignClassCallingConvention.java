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
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.NameUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 *  ExternalClassCallingConvention defines the style of a nested Class that augments an external Java class.
 */
public class ForeignClassCallingConvention extends AbstractClassCallingConvention
{
	private static final @NonNull ForeignClassCallingConvention INSTANCE = new ForeignClassCallingConvention();

	public static @NonNull ForeignClassCallingConvention getInstance() {
		INSTANCE.logInstance(null);
		return INSTANCE;
	}

	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
		installCGRootClassParent(analyzer, cgClass, asClass);
		return cgClass;
	}

	public @NonNull CGClass createForeignClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Package asParentPackage) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		org.eclipse.ocl.pivot.Class asClass = ClassUtil.nonNullState(NameUtil.getNameable(asParentPackage.getOwningPackage().getOwnedClasses(), asParentPackage.getName()));
		//
		PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, asParentPackage);
		String foreignClassName = packageNameManager.getUniqueClassName(NameManagerHelper.FOREIGN_CLASS_NAME_PREFIX, asClass);
		org.eclipse.ocl.pivot.Class asForeignClass = AbstractLanguageSupport.getClass(asParentPackage, foreignClassName);
		importNameManager.reserveLocalName(PivotUtil.getName(asForeignClass));
		//
		CGClass cgForeignClass = analyzer.generateClassDeclaration(asForeignClass, this);
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
		String title = cgClass.getName() + " provides the Java implementations for foreign feature accesses";
		js.appendCommentWithOCL(title, null);
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
	//	return analyzer.getCodeGenerator().getExternalClassName((org.eclipse.ocl.pivot.Class)asNamedElement);
		return PivotUtil.getName(asNamedElement);
	}

//	@Override
//	protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Feature asFeature) {
//		return getRootClassParentPackage(analyzer, asFeature);
//	}
}
