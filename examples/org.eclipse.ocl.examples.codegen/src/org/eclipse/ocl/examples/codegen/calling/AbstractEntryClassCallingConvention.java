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
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
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
import org.eclipse.ocl.pivot.utilities.EnvironmentFactory;
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
			//
			// AS Class - yyy2zzz
			// AS Properties - thisTransformer, x1, x2, cachedResult
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters - x1, x2
			// AS Cache Operation - isEqual
			// AS Cache Operation.parameters - boxedValues
			// AS Cache ExpressionInOCL.ownedContext - this
			// AS Cache ExpressionInOCL.ownedParameters - thisTransformer, x1, x2
			// CG Cache Operation - isEqual
			// CG Cache Operation.parameters - idResolver, boxedValues
			// CG Cache Operation.lets - thisTransformer, x1, x2
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			EnvironmentFactory environmentFactory = codeGenerator.getEnvironmentFactory();
			PivotHelper asHelper = codeGenerator.getASHelper();
			org.eclipse.ocl.pivot.@NonNull Class asEntryClass = CGUtil.getAST(cgEntryClass);
			//
			//	Create AS declaration
			//
			NameResolution getResultNameResolution = globalNameManager.getGetResultNameResolution();
			String getResultName = getResultNameResolution.getResolvedName();
			List<@NonNull Property> asEntryProperties = PivotUtilInternal.getOwnedPropertiesList(asEntryClass);
			Property asEntryResultProperty = asEntryProperties.get(asEntryProperties.size()-1);
			Operation asEntryOperation = createASOperationDeclaration(analyzer, asEntryClass, asOperation,
				getResultName, asEntryResultProperty);
			//
			//	Create AS body - self.cachedResult for AS => this.cachedResult for CG
			//
			ExpressionInOCL asExpressionInOCL = createASExpressionInOCL(analyzer, asEntryOperation, ContextVariableStyle.SELF);
			ParameterVariable asSelfVariable = (ParameterVariable)PivotUtil.getOwnedContext(asExpressionInOCL);
			OCLExpression asSelfVariableExp = asHelper.createVariableExp(asSelfVariable);
			OCLExpression asBody = asHelper.createPropertyCallExp(asSelfVariableExp, asEntryResultProperty);
			//
			installExpressionInOCLBody(asEntryOperation, asExpressionInOCL, asBody);
			//
			//	Create CG declaration
			//
			CGOperation cgEntryOperation = createCGOperation(analyzer, asEntryOperation);
			analyzer.initAst(cgEntryOperation, asEntryOperation, true);
			cgEntryOperation.setCallingConvention(this);
			getResultNameResolution.addCGElement(cgEntryOperation);
			analyzer.getOperationNameManager(cgEntryOperation, asEntryOperation);
			//
			cgEntryClass.getOperations().add(cgEntryOperation);
			return cgEntryOperation;
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
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgEntryOperation, asEntryOperation);
			Parameter asEntryBoxedValuesParameter = PivotUtilInternal.getOwnedParametersList(asEntryOperation).get(0);
			CGParameter cgEntryBoxedValuesParameter = operationNameManager.getCGParameter(asEntryBoxedValuesParameter, (String)null);
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
			//
			// AS Class - yyy2zzz
			// AS Properties - contextObject, x1, x2, cachedResult
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters - x1, x2
			// AS Entry Operation - isEqual
			// AS Entry Operation.parameters - boxedValues{x1, x2}
			// AS Entry ExpressionInOCL.ownedContext - this
			// AS Entry ExpressionInOCL.ownedParameter(Variable)s - idResolver, self, x1, x2
			// CG Entry Operation - isEqual
			// CG Entry Operation.parameters - idResolver, boxedValues{x1, x2}
			// CG Entry Operation.lets - x1, x2
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		//	LanguageSupport jLanguageSupport = codeGenerator.getLanguageSupport();
			PivotHelper asHelper = codeGenerator.getASHelper();
			org.eclipse.ocl.pivot.@NonNull Class asEntryClass = CGUtil.getAST(cgEntryClass);
			//
			//	Create AS declaration for isEqual
			//
			NameResolution isEqualNameResolution = globalNameManager.getIsEqualNameResolution();
			Operation asEntryOperation = createASOperationDeclaration(analyzer, asEntryClass, asOperation,
				isEqualNameResolution.getResolvedName(), ResultStyle.BOOLEAN, ParameterStyle.BOXED_VALUES_OPTIONAL);
			//
			//	Create AS body for isEqual
			//
			OCLExpression asBody = null;
			ExpressionInOCL asEntryExpressionInOCL = createASExpressionInOCL(analyzer, asOperation,
				ContextVariableStyle.THIS, ParameterVariableStyle.ID_RESOLVER, ParameterVariableStyle.SELF, ParameterVariableStyle.BOXED_VALUES);
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
			CGOperation cgEntryOperation = createCGOperation(analyzer, asEntryOperation);
			analyzer.initAst(cgEntryOperation, asEntryOperation, true);
			cgEntryOperation.setCallingConvention(this);
			isEqualNameResolution.addCGElement(cgEntryOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgEntryOperation, asEntryOperation);
			List<@NonNull CGParameter> cgEntryParameters = CGUtil.getParametersList(cgEntryOperation);
	//		CGParameter cgIdResolverParameter = operationNameManager.getIdResolverParameter();	// FIXME notify operationNameManager that we need a regular idResolver parameter
			NameResolution idResolverNameResolution = globalNameManager.getIdResolverNameResolution();
			CGTypeId cgTypeId = analyzer.getCGTypeId(JavaConstants.ID_RESOLVER_TYPE_ID);
			CGParameter cgIdResolverParameter = analyzer.createCGParameter(idResolverNameResolution, cgTypeId, true);
			cgIdResolverParameter.setNonInvalid();
			cgIdResolverParameter.setRequired(true);
			cgEntryParameters.add(cgIdResolverParameter);
			Parameter asBoxedValuesParameter = getBoxedValuesParameter(analyzer, asEntryOperation);
			CGParameter cgEntryBoxedValuesParameter = operationNameManager.getCGParameter(asBoxedValuesParameter, (String)null);
			globalNameManager.getBoxedValuesNameResolution().addCGElement(cgEntryBoxedValuesParameter);
			cgEntryParameters.add(cgEntryBoxedValuesParameter);
			//
			cgEntryClass.getOperations().add(cgEntryOperation);
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
		NameResolution contextNameResolution = getContextNameResolution(globalNameManager);
//		org.eclipse.ocl.pivot.Class asContextClass = getContextClass(analyzer, cgEntryClass);
//		createEntryProperty(analyzer, cgEntryClass, contextNameResolution, asContextClass);

		org.eclipse.ocl.pivot.Class asOldContextClass = getContextClass(analyzer, cgEntryClass);

		CGClass cgForeignClass = analyzer.getForeignCGClass(asParentPackage);
		org.eclipse.ocl.pivot.Class asNewContextClass = CGUtil.getAST(cgForeignClass);
	//	NameUtil.errPrintln("createEntryClass: old: " + asOldContextClass + " new: " + asNewContextClass);

		createEntryProperty(analyzer, cgEntryClass, contextNameResolution, asOldContextClass);
		for (@NonNull Parameter asParameter : PivotUtil.getOwnedParameters(asOperation)) {
			createEntryProperty(analyzer, cgEntryClass, null, asParameter);
			// XXX need to support a cached invalid
		}
		NameResolution cachedResultNameResolution = globalNameManager.getCachedResultNameResolution();
		createEntryProperty(analyzer, cgEntryClass, cachedResultNameResolution, asOperation);
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

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGClass cgClass) {
		assert cgClass.getContainingPackage() == null;			// container is a cgClass
		JavaStream js = cg2javaVisitor.getJavaStream();
		String className = CGUtil.getName(cgClass);
		String title = getTitle(cgClass);
		org.eclipse.ocl.pivot.Class asClass = CGUtil.getAST(cgClass);
		Operation asOperation = cg2javaVisitor.getAnalyzer().basicGetCachedOperation(asClass);
		js.appendCommentWithOCL(title, asOperation);
		js.append("protected class " + className);		// Could be static if dynamic INSTANCE_CACHE accessible statically
		appendSuperTypes(js, cgClass);
		js.pushClassBody(className);
		generateProperties(cg2javaVisitor, cgClass);
		generateOperations(cg2javaVisitor, cgClass);
		js.popClassBody(false);
		return true;
	}

	protected @NonNull Class getContextClass(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass) {
		return analyzer.getCodeGenerator().getContextClass();
	}

	protected @NonNull NameResolution getContextNameResolution(@NonNull GlobalNameManager globalNameManager) {
		return globalNameManager.getContextObjectNameResolution();
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
