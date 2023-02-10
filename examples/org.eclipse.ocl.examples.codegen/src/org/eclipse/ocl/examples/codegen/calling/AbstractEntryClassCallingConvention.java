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

import java.util.List;
import java.util.Stack;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.calling.AbstractCachePropertyCallingConvention.ImmutableCachePropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.AbstractCachedOperationCallingConvention.CacheProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIndexExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.naming.PackageNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Feature;
import org.eclipse.ocl.pivot.LetVariable;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.ParameterVariable;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.VariableExp;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryConstants;
import org.eclipse.ocl.pivot.utilities.AbstractLanguageSupport;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.LanguageSupport;
import org.eclipse.ocl.pivot.utilities.PivotHelper;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.qvtd.runtime.evaluation.AbstractComputation;

/**
 *  EntryClassCallingConvention defines the style of a nested Class whose instance caches a feature computation.
 */
public abstract class AbstractEntryClassCallingConvention extends AbstractClassCallingConvention
{
	public static class EntryConstructorOperationCallingConvention extends AbstractConstructorOperationCallingConvention
	{
		private static final @NonNull EntryConstructorOperationCallingConvention INSTANCE = new EntryConstructorOperationCallingConvention();

		public static @NonNull EntryConstructorOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles( @NonNull ExecutableNameManager operationNameManager) {
			return CG_PARAMETER_STYLES_THIS_BOXED_VALUES;
		}
	}

	public static class EntryGetResultOperationCallingConvention extends AbstractUncachedOperationCallingConvention
	{
		private static final @NonNull EntryGetResultOperationCallingConvention INSTANCE = new EntryGetResultOperationCallingConvention();

		public static @NonNull EntryGetResultOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	super.createCGBody(analyzer, cgOperation);
			Element asOperation = cgOperation.getAst();
			ExpressionInOCL asExpressionInOCL = (ExpressionInOCL) (asOperation instanceof ExpressionInOCL ? asOperation : ((Operation)asOperation).getBodyExpression());
			assert (asExpressionInOCL != null);
			CGValuedElement cgResult = analyzer.createCGElement(CGValuedElement.class, asExpressionInOCL);
			cgOperation.setBody(cgResult);
		}

		public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		//	EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
			PivotHelper asHelper = codeGenerator.getASHelper();
			org.eclipse.ocl.pivot.@NonNull Class asEntryClass = CGUtil.getAST(cgEntryClass);
			//
			//	Create AS declaration
			//
			NameResolution getResultNameResolution = globalNameManager.getGetResultName();
			String getResultName = getResultNameResolution.getResolvedName();
			List<@NonNull Property> asEntryProperties = PivotUtilInternal.getOwnedPropertiesList(asEntryClass);
			Property asEntryResultProperty = asEntryProperties.get(asEntryProperties.size()-1);
			Operation asEntryOperation = createASOperationDeclaration(analyzer, asEntryClass, asOperation,
				getResultName, asEntryResultProperty);
			//
			//	Create AS body - self.cachedResult for AS => this.cachedResult for CG
			//
			ExpressionInOCL asExpressionInOCL = createASExpressionInOCL(analyzer, asEntryOperation, ASContextVariableStyle.SELF);
			ParameterVariable asSelfVariable = (ParameterVariable)PivotUtil.getOwnedContext(asExpressionInOCL);
			OCLExpression asSelfVariableExp = asHelper.createVariableExp(asSelfVariable);
			OCLExpression asBody = asHelper.createPropertyCallExp(asSelfVariableExp, asEntryResultProperty);
			//
			installExpressionInOCLBody(asEntryOperation, asExpressionInOCL, asBody);
			//
			//	Create CG declaration
			//
			CGOperation cgEntryOperation = createCGOperationDeclaration(analyzer, cgEntryClass, asEntryOperation,
				getResultNameResolution, null);
			return cgEntryOperation;
		}

		@Override
		protected @NonNull ASParameterStyle @NonNull [] getASParameterStyles(@NonNull TypedElement asOrigin) {
			return AS_PARAMETER_STYLES;
		}

		@Override
		protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager) {
			return CG_PARAMETER_STYLES;
		}
	}

	public static class EntryIsEqualOperationCallingConvention extends AbstractUncachedOperationCallingConvention
	{
		private static final @NonNull EntryIsEqualOperationCallingConvention INSTANCE = new EntryIsEqualOperationCallingConvention();

		public static @NonNull EntryIsEqualOperationCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgEntryOperation) {
			Operation asEntryOperation = CGUtil.getAST(cgEntryOperation);
			org.eclipse.ocl.pivot.Class asEntryClass = PivotUtil.getOwningClass(asEntryOperation);
			ExpressionInOCL asEntryExpressionInOCL = (ExpressionInOCL) asEntryOperation.getBodyExpression();
			assert (asEntryExpressionInOCL != null);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgEntryOperation, asEntryOperation, null);
		//	Parameter asEntryBoxedValuesParameter = PivotUtilInternal.getOwnedParametersList(asEntryOperation).get(0);
			CGParameter cgEntryBoxedValuesParameter = operationNameManager.getBoxedValuesParameter();
			List<@NonNull Property> asEntryProperties = PivotUtilInternal.getOwnedPropertiesList(asEntryClass);
			List<@NonNull Variable> asEntryParameterVariables = PivotUtilInternal.getOwnedParametersList(asEntryExpressionInOCL);
			Stack<@NonNull CGFinalVariable> cgLetVariables = new Stack<>();
			// idResolver then boxedValues
			for (int i = 0; i < asEntryProperties.size()-1; i++) {		// not cachedResult
				Property asEntryProperty = asEntryProperties.get(i);
				ParameterVariable asEntryParameterVariable = (ParameterVariable)asEntryParameterVariables.get(i+1);		// skip idResolver
				CGVariableExp cgVariableExp = analyzer.createCGVariableExp(cgEntryBoxedValuesParameter);
				CGIndexExp cgIndexExp = analyzer.createCGIndexExp(cgVariableExp, i, asEntryProperty);
				cgIndexExp.setAst(asEntryParameterVariable);
				cgIndexExp.setRequired(asEntryProperty.isIsRequired());
				CGFinalVariable cgEntryParameterVariable = operationNameManager.createCGVariable(cgIndexExp);
				operationNameManager.addVariable(asEntryParameterVariable, cgEntryParameterVariable);
				cgLetVariables.push(cgEntryParameterVariable);
			}
			//
			CGValuedElement cgResult = analyzer.createCGElement(CGValuedElement.class, asEntryExpressionInOCL);
			while (!cgLetVariables.isEmpty()) {
				CGFinalVariable cgLetVariable = cgLetVariables.pop();
				cgResult = analyzer.createCGLetExp(cgLetVariable, cgResult);
			}
			cgEntryOperation.setBody(cgResult);
		}

		public final @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			PivotHelper asHelper = codeGenerator.getASHelper();
			org.eclipse.ocl.pivot.@NonNull Class asEntryClass = CGUtil.getAST(cgEntryClass);
			//
			//	Create AS declaration for isEqual
			//
			NameResolution isEqualNameResolution = globalNameManager.getIsEqualName();
			Operation asEntryOperation = createASOperationDeclaration(analyzer, asEntryClass, asOperation,
				isEqualNameResolution.getResolvedName(), ASResultStyle.BOOLEAN);
			//
			//	Create AS body for isEqual
			//
			OCLExpression asBody = null;
			ExpressionInOCL asEntryExpressionInOCL = createASExpressionInOCL(analyzer, asOperation,
				ASContextVariableStyle.THIS, AS_PARAMETER_VARIABLE_STYLES_ID_RESOLVER_SELF_BOXED_VALUES);
			ParameterVariable asEntryThisVariable = (ParameterVariable)PivotUtil.getOwnedContext(asEntryExpressionInOCL);
			List<@NonNull Variable> asEntryParameterVariables = PivotUtilInternal.getOwnedParametersList(asEntryExpressionInOCL);
			List<@NonNull Property> asEntryProperties = PivotUtilInternal.getOwnedPropertiesList(asEntryClass);
			Stack<@NonNull LetVariable> asLetVariables = new Stack<>();
			for (int i = 0; i < asEntryProperties.size()-1; i++) {		// not cachedResult
				Property asEntryProperty = asEntryProperties.get(i);
				@NonNull ParameterVariable asEntryParameterVariable = (ParameterVariable) asEntryParameterVariables.get(1);	// skip idResolver
				String name = PivotUtil.getName(asEntryProperty);
				VariableExp asInit = asHelper.createVariableExp(asEntryParameterVariable);
				LetVariable asLetVariable = asHelper.createLetVariable(name, asInit);
				asLetVariables.push(asLetVariable);
				//
				OCLExpression asEntryThisVariableExp = asHelper.createVariableExp(asEntryThisVariable);
				OCLExpression asEntryParameterVariableExp = asHelper.createVariableExp(asLetVariable);
				OCLExpression asEntryPropertyCallExp = asHelper.createPropertyCallExp(asEntryThisVariableExp, asEntryProperty);
				OCLExpression asEquals = asHelper.createOperationCallExp(asEntryParameterVariableExp, "=", asEntryPropertyCallExp);
				asBody = asBody != null ? asHelper.createOperationCallExp(asBody, LibraryConstants.AND2, asEquals) : asEquals;
			}
			assert asBody != null;
			while (!asLetVariables.isEmpty()) {
				LetVariable asVariable = asLetVariables.pop();
				asBody = asHelper.createLetExp(asVariable, asBody);
			}
			installExpressionInOCLBody(asEntryOperation, asEntryExpressionInOCL, asBody);
			//
			//	Create CG declaration for isEqual
			//
			CGOperation cgEntryOperation = createCGOperationDeclaration(analyzer, cgEntryClass, asEntryOperation,
				isEqualNameResolution, null);
			return cgEntryOperation;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			js.append("/**\n");
			js.append(" * Return true if the boxedValues exactly match OCL-wise the values from which this entry was constructed.\n");
			js.append(" * If so, the cachedResult can be re-used and no further ENTRY needs construction.\n");
			js.append(" */\n");
			return super.generateJavaDeclaration(cg2javaVisitor, cgOperation);
		}

		@Override
		protected @NonNull ASParameterStyle @NonNull [] getASParameterStyles(@NonNull TypedElement asOrigin) {
			return AS_PARAMETER_STYLES_BOXED_VALUES_OPTIONAL;
		}

		@Override
		protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager) {
			return CG_PARAMETER_STYLES_ID_RESOLVER_BOXED_VALUES;
		}
	}

	@Override
	public @NonNull CGClass createCGClass(@NonNull CodeGenAnalyzer analyzer, org.eclipse.ocl.pivot.@NonNull Class asClass) {
		CGClass cgClass = createCGClass();
		installCGRootClassParent(analyzer, cgClass, asClass);
		return cgClass;
	}

	public @NonNull CGClass createEntryClass(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
		JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
		boolean isIncremental = codeGenerator.getOptions().isIncremental();
		GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
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
		//
		CGClass cgEntryClass = analyzer.generateClassDeclaration(asEntryClass, this);
		CGClass cgEntrySuperClass = analyzer.generateClassDeclaration(asEntrySuperClass, null);
		cgEntryClass.getSuperTypes().add(cgEntrySuperClass);
		//
		NameResolution contextName = getContextName(globalNameManager);
//		org.eclipse.ocl.pivot.Class asContextClass = getContextClass(analyzer, cgEntryClass);
//		createEntryProperty(analyzer, cgEntryClass, contextNameResolution, asContextClass);

		org.eclipse.ocl.pivot.Class asOldContextClass = getContextClass(analyzer, cgEntryClass);

	//	CGClass cgForeignClass = analyzer.getForeignCGClass(asParentPackage);
	//	org.eclipse.ocl.pivot.Class asNewContextClass = CGUtil.getAST(cgForeignClass);
	//	NameUtil.errPrintln("createEntryClass: old: " + asOldContextClass + " new: " + asNewContextClass);

		createEntryProperty(analyzer, cgEntryClass, contextName, asOldContextClass);
		for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
			createEntryProperty(analyzer, cgEntryClass, null, asParameter);
			// XXX need to support a cached invalid
		}
		NameResolution cachedResultName = globalNameManager.getCachedResultName();
		createEntryProperty(analyzer, cgEntryClass, cachedResultName, asOperation);
		//
		installConstructorOperation(analyzer, cgEntryClass, asOperation);
		installGetResultOperation(analyzer, cgEntryClass, asOperation);
		installIsEqualOperation(analyzer, cgEntryClass, asOperation);
		return cgEntryClass;
	}

	private void createEntryProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass,
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
		asEntryProperty.setImplementation(CacheProperty.INSTANCE);
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

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGClass cgClass) {
		assert cgClass.getContainingPackage() == null;			// container is a cgClass
		JavaStream js = cg2javaVisitor.getJavaStream();
		String className = CGUtil.getName(cgClass);
		String title = getTitle(cgClass);
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		Operation asOperation = cg2javaVisitor.getAnalyzer().basicGetCachedOperation(asClass);
		js.appendCommentWithOCL(title, asOperation);
		js.append("// " + cgClass.getCallingConvention() + "\n");
		js.append("protected class " + className);		// Could be static if dynamic INSTANCE_CACHE accessible statically
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		generatePropertyDeclarations(cg2javaVisitor, cgClass);
		generateOperations(cg2javaVisitor, cgClass);
		js.popClassBody(false);
		return true;
	}

	protected @NonNull Class getContextClass(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass) {
		return analyzer.getCodeGenerator().getContextClass();
	}

	protected @NonNull NameResolution getContextName(@NonNull GlobalNameManager globalNameManager) {
		return globalNameManager.getContextObjectName();
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

	protected @NonNull String getTitle(@NonNull CGClass cgClass) {
		return "Each " + cgClass.getName() + " instance caches a distinct evaluation of\n";
	}

	protected void installConstructorOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
		EntryConstructorOperationCallingConvention callingConvention = EntryConstructorOperationCallingConvention.getInstance(asEntryClass);
		callingConvention.createOperation(analyzer, cgEntryClass, asOperation);
	}

	protected void installGetResultOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
		EntryGetResultOperationCallingConvention callingConvention = EntryGetResultOperationCallingConvention.getInstance(asEntryClass);
		callingConvention.createOperation(analyzer, cgEntryClass, asOperation);
	}

	protected void installIsEqualOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
		EntryIsEqualOperationCallingConvention callingConvention = EntryIsEqualOperationCallingConvention.getInstance(asEntryClass);
		callingConvention.createOperation(analyzer, cgEntryClass, asOperation);
	}
}
