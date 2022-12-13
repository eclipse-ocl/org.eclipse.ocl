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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention2.CacheProperty;
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention2.GetResultOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention2.IsEqualOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.ImportNameManager;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.qvtd.runtime.evaluation.AbstractComputation;

/**
 *  EntryClassCallingConvention defines the style of a nested Class whose instance caches a feature computation.
 */
public abstract class AbstractEntryClassCallingConvention extends AbstractClassCallingConvention
{
	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
		installCGRootClassParent(analyzer, cgClass, asClass);
		return cgClass;
	}

	private void createCacheProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass,
			@Nullable NameResolution nameResolution, @NonNull NamedElement asTypeOrTypedElement) {
		org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
		//
		Property asEntryProperty = PivotFactory.eINSTANCE.createProperty();
		asEntryProperty.setName(nameResolution != null ? nameResolution.getResolvedName() : asTypeOrTypedElement.getName());
		if (asTypeOrTypedElement instanceof Type) {
			asEntryProperty.setType((Type)asTypeOrTypedElement);
			asEntryProperty.setIsRequired(true);
		}
		else if (asTypeOrTypedElement instanceof TypedElement) {
			TypedElement asTypedElement = (TypedElement)asTypeOrTypedElement;
			asEntryProperty.setType(PivotUtil.getType(asTypedElement));
			asEntryProperty.setIsRequired(asTypedElement.isIsRequired());
		}
		else {
			throw new IllegalStateException();
		}
		asEntryClass.getOwnedProperties().add(asEntryProperty);
		asEntryProperty.setImplementation(new CacheProperty(asEntryProperty.getPropertyId(), null, null));
		//
		CGProperty cgEntryProperty = analyzer.generatePropertyDeclaration(asEntryProperty, ImmutableCachePropertyCallingConvention.getInstance(asEntryProperty));
		if (nameResolution != null) {
			nameResolution.addCGElement(cgEntryProperty);
		}
		cgEntryClass.getProperties().add(cgEntryProperty);
		if (nameResolution == null) {
			ClassNameManager nameManager = analyzer.getClassNameManager(cgEntryClass, asEntryClass);
			//			nameManager.declareEagerName(cgEntryProperty)
			nameResolution = nameManager.getNameResolution(cgEntryProperty);
		}
	}

	public org.eclipse.ocl.pivot.@NonNull Class createEntryClass(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		boolean isIncremental = codeGenerator.getOptions().isIncremental();
		GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		ImportNameManager importNameManager = codeGenerator.getImportNameManager();
		LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
		Operation asOperation = CGUtil.getAST(cgOperation);
		org.eclipse.ocl.pivot.@NonNull Package asParentPackage = getParentPackage(analyzer, asOperation);
		//
		PackageNameManager packageNameManager = analyzer.getPackageNameManager(null, asParentPackage);
		String entryClassName = packageNameManager.getUniqueClassName(NameManagerHelper.ENTRY_CLASS_NAME_PREFIX, asOperation);
		org.eclipse.ocl.pivot.Class asEntryClass = AbstractLanguageSupport.getClass(asParentPackage, entryClassName);
		analyzer.addCachedOperation(asEntryClass, asOperation);
		org.eclipse.ocl.pivot.Class asEntrySuperClass = jLanguageSupport.getNativeClass(isIncremental ? AbstractComputation.Incremental.class : AbstractComputation.class);
		asEntryClass.getSuperClasses().add(asEntrySuperClass);
		importNameManager.reserveLocalName(PivotUtil.getName(asEntryClass));
		//
		CGClass cgEntryClass = analyzer.generateClassDeclaration(asEntryClass, this);
		CGClass cgEntrySuperClass = analyzer.generateClassDeclaration(asEntrySuperClass, null);
		cgEntryClass.getSuperTypes().add(cgEntrySuperClass);
		//
		NameResolution contextNameResolution = getContextNameResolution(globalNameManager);
		org.eclipse.ocl.pivot.Class asContextClass = getContextClass(analyzer, cgEntryClass);
		createCacheProperty(analyzer, cgEntryClass, contextNameResolution, asContextClass);
		for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
			createCacheProperty(analyzer, cgEntryClass, null, asParameter);
			// XXX need to support a cached invalid
		}
		NameResolution cachedResultNameResolution = globalNameManager.getCachedResultNameResolution();
		createCacheProperty(analyzer, cgEntryClass, cachedResultNameResolution, asOperation);
		//
//		getConstructorOperationCallingConvention(asEntryClass).createCacheConstructor(analyzer, cgEntryClass, asOperation);
		installConstructorOperation(analyzer, cgEntryClass, asOperation);
//		GetResultOperationCallingConvention.getInstance(asEntryClass).createCacheGetResultOperation(analyzer, cgEntryClass, asOperation);
		installGetResultOperation(analyzer, cgEntryClass, asOperation);
//		IsEqualOperationCallingConvention.getInstance(asEntryClass).createCacheIsEqualOperation(analyzer, cgEntryClass, asOperation);
		installIsEqualOperation(analyzer, cgEntryClass, asOperation);
		return asEntryClass;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGClass cgClass) {
		assert cgClass.getContainingPackage() == null;			// container is a cgClass
		js.append("\n");
		String className = CGUtil.getName(cgClass);
		String title = getTitle(cgClass);
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		Operation asOperation = cg2javaVisitor.getAnalyzer().basicGetCachedOperation(asClass);
		js.appendCommentWithOCL(title, asOperation);
		js.append("protected class " + className);
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		generateProperties(cg2javaVisitor, js, cgClass);
		generateOperations(cg2javaVisitor, js, cgClass);
		js.popClassBody(false);
		return true;
	}

	protected @NonNull Class getContextClass(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass) {
		return analyzer.getCodeGenerator().getContextClass();
	}

	protected @NonNull NameResolution getContextNameResolution(@NonNull GlobalNameManager globalNameManager) {
		return globalNameManager.getThisTransformerNameResolution();
	}

	@Override
	public @NonNull String getName(@NonNull CodeGenAnalyzer analyzer, @NonNull NamedElement asNamedElement) {
		if (asNamedElement instanceof Feature) {
			Feature asFeature = (Feature)asNamedElement;
			return /*"CACHE_" +*/ ClassUtil.nonNullState(asFeature.getOwningClass()).getName() + "_" + asFeature.getName();
		}
		else {
			return /*"CACHE_" +*/ asNamedElement.getName();
		}
	}

	/**
	 * Return the Package within which the caache claass support for asOperation shuld be supported.
	 */
	protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {	// XXX Regularly overridden
		return getDefaultParentPackage(analyzer, asOperation);
	}

	protected @NonNull String getTitle(@NonNull CGClass cgClass) {
		return "Each " + cgClass.getName() + " instance caches a distinct evaluation of\n";
	}

	protected void installConstructorOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
		ConstructorOperationCallingConvention.getInstance(asEntryClass).createOperation(analyzer, cgEntryClass, asOperation);
	}

	protected void installGetResultOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
		GetResultOperationCallingConvention.getInstance(asEntryClass).createOperation(analyzer, cgEntryClass, asOperation);
	}

	protected void installIsEqualOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
		IsEqualOperationCallingConvention.getInstance(asEntryClass).createOperation(analyzer, cgEntryClass, asOperation);
	}
}
