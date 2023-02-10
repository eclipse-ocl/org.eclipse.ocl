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
import org.eclipse.ocl.examples.codegen.calling.AbstractCacheClassCallingConvention.CacheConstructorOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.AbstractCacheClassCallingConvention.CacheNewInstanceOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.AbstractCacheClassCallingConvention.DefaultEvaluateOperationCallingConvention;
import org.eclipse.ocl.examples.codegen.calling.AbstractCachePropertyCallingConvention.ImmutableCachePropertyCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.TypeRepresentation;
import org.eclipse.ocl.examples.codegen.naming.ClassNameManager;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.naming.NameManagerHelper;
import org.eclipse.ocl.examples.codegen.naming.NameResolution;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.Class;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.library.AbstractProperty;
import org.eclipse.ocl.pivot.utilities.PivotUtil;

/**
 * AbstractCachedOperationCallingConvention defines the common functionality that may use a cache to suppress repeated evaluations.
 *
 * XXX This should be a superclass solely for operations that may cache evaluations (not for operations within caches).
 */
public abstract class AbstractCachedOperationCallingConvention extends AbstractOperationCallingConvention
{
	/**
	 * A CacheProperty instance signals a property in a CGed cache. (It has no opposite).
	 */
	public static class CacheProperty extends AbstractProperty
	{
		public static final @NonNull CacheProperty INSTANCE = new CacheProperty();

		private CacheProperty() {}
	}

	public static abstract class AbstractEvaluateOperationCallingConvention extends AbstractUncachedOperationCallingConvention
	{
		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	Implemented as direct synthesis
		}

		public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, @NonNull Operation asOperation, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			org.eclipse.ocl.pivot.@NonNull Class asCacheClass = CGUtil.getAST(cgCacheClass);
			//
			//	Create AS declaration for evaluate
			//
			NameResolution evaluateNameResolution = globalNameManager.getEvaluateName();
			Operation asCacheEvaluateOperation = createASOperationDeclaration(analyzer, asCacheClass, asOperation,
					evaluateNameResolution.getResolvedName(), ASResultStyle.RESULT);
			//
			//	Create AS body for newInstance
			//
			//	not implemented
			//
			//	Create CG declaration for evaluate
			//
			CGOperation cgCacheEvaluateOperation = createCGOperationDeclaration(analyzer, cgCacheClass, asCacheEvaluateOperation,
				evaluateNameResolution, null);
		/*	CGOperation cgCacheEvaluateOperation = createCGOperation(analyzer, asCacheEvaluateOperation);
			analyzer.initAst(cgCacheEvaluateOperation, asCacheEvaluateOperation, true);
			cgCacheEvaluateOperation.setCallingConvention(this);
			evaluateNameResolution.addCGElement(cgCacheEvaluateOperation);
			cgCacheClass.getOperations().add(cgCacheEvaluateOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgCacheEvaluateOperation, asCacheEvaluateOperation);
			createCGParameters(operationNameManager, null); */
		/*	GlobalNameManager globalNameManager = operationNameManager.getGlobalNameManager();
			CGOperation cgCacheEvaluateOperation = (CGOperation) operationNameManager.getCGScope();
			Operation asCacheEvaluateOperation = (Operation) operationNameManager.getASScope();
			List<@NonNull Parameter> asCacheEvaluateParameters = PivotUtilInternal.getOwnedParametersList(asCacheEvaluateOperation);
			List<@NonNull CGParameter> cgEvaluateParameters = CGUtil.getParametersList(cgCacheEvaluateOperation);
			for (@NonNull Parameter asCacheEvaluateParameter : asCacheEvaluateParameters) {
				CGParameter cgParameter = operationNameManager.getCGParameter(asCacheEvaluateParameter, null);
				String name = asCacheEvaluateParameter.getName();
				NameResolution selfNameResolution = globalNameManager.getSelfNameResolution();
				if (selfNameResolution.getResolvedName().equals(name)) {
					selfNameResolution.addCGElement(cgParameter);
				}
				else {
					NameResolution objectNameResolution = globalNameManager.getObjectNameResolution();
					if (objectNameResolution.getResolvedName().equals(name)) {
						objectNameResolution.addCGElement(cgParameter);
					}
					else {
						NameResolution contextObjectNameResolution = globalNameManager.getContextObjectNameResolution();
						if (contextObjectNameResolution.getResolvedName().equals(name)) {
							contextObjectNameResolution.addCGElement(cgParameter);
						}
					}
				}
				cgEvaluateParameters.add(cgParameter);
			} */
			return cgCacheEvaluateOperation;
		}

		@Override
		protected void generateJavaOperationBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
			GlobalNameManager globalNameManager = analyzer.getGlobalNameManager();
			Operation asOperation = CGUtil.getAST(cgOperation);
			org.eclipse.ocl.pivot.Class asConstructorClass = PivotUtil.getOwningClass(asOperation);
			org.eclipse.ocl.pivot.Class asCacheClass = analyzer.getEntryClass(asConstructorClass);
			CGClass cgCacheClass = analyzer.getCGClass(asCacheClass);
			js.append("return ((");
			js.appendClassReference(cgCacheClass);
			js.append(")getUniqueComputation(");
			generateUniqueComputationArguments(cg2javaVisitor, true, globalNameManager, cgOperation);
			js.append(")).");
			js.appendName(globalNameManager.getCachedResultName());
			js.append(";\n");
		}

		protected void generateUniqueComputationArguments(@NonNull CG2JavaVisitor cg2javaVisitor, boolean isFirst, @NonNull GlobalNameManager globalNameManager, @NonNull CGOperation cgOperation) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			for (@NonNull CGParameter cgParameter : CGUtil.getParameters(cgOperation)) {
				if (!isFirst) {
					js.append(", ");
				}
				js.appendValueName(cgParameter);
				isFirst = false;
			}
		}

		@Override
		protected @NonNull ASParameterStyle @NonNull [] getASParameterStyles(@NonNull TypedElement asOrigin) {
			Operation asOperation = (Operation) asOrigin;
			return asOperation.isIsStatic() ? AS_PARAMETER_STYLES_PARAMETERS : AS_PARAMETER_STYLES_SELF_PARAMETERS;
		}

		@Override
		protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager) {
			return CG_PARAMETER_STYLES_PARAMETERS;
		}
	}

	public static class CacheInstancePropertyCallingConvention extends AbstractCachePropertyCallingConvention
	{
		private static final @NonNull CacheInstancePropertyCallingConvention INSTANCE = new CacheInstancePropertyCallingConvention();

		public static @NonNull CacheInstancePropertyCallingConvention getInstance(@NonNull Property asProperty) {
			INSTANCE.logInstance(asProperty);
			return INSTANCE;
		}

		@Override
		public void createCGParameters(@NonNull ExecutableNameManager propertyNameManager, @Nullable ExpressionInOCL initExpression) {
			propertyNameManager.getGlobalNameManager().setNeedsExecutor();
		}

		@Override
		public boolean generateEcoreBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
			CGValuedElement cgBody = ((CGBodiedProperty)cgProperty).getBody();
			assert cgBody == null;
			return false;			// Support property is not needed in Ecore
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			TypeRepresentation boxedTypeRepresentation = js.getBoxedTypeRepresentation();
			if (JavaCodeGenerator.CALLING_CONVENTION_COMMENTS.isActive()) {
				js.append("// " + cgProperty.getCallingConvention() + "\n");
			}
			js.append("public final");
			js.append(" /*@NonInvalid*/ ");
			boxedTypeRepresentation.appendClassReference(cgProperty.isRequired(), cgProperty);
			js.append(" ");
			js.appendValueName(cgProperty);
			js.append(";\n");
			return true;
		}

		@Override
		public boolean generateJavaInitialization(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
			JavaStream js = cg2javaVisitor.getJavaStream();
			TypeRepresentation boxedTypeRepresentation = js.getBoxedTypeRepresentation();
			if (JavaCodeGenerator.CALLING_CONVENTION_COMMENTS.isActive()) {
				js.append("// " + cgProperty.getCallingConvention() + "\n");
			}
			js.append("this.");
			js.appendValueName(cgProperty);
			js.append(" = new ");
			boxedTypeRepresentation.appendClassReference(null, cgProperty);
			js.append("();\n");
			return true;
		}
	}

	public static class DefaultCacheClassCallingConvention extends AbstractCacheClassCallingConvention
	{
		private static final @NonNull DefaultCacheClassCallingConvention INSTANCE = new DefaultCacheClassCallingConvention();

		public static @NonNull DefaultCacheClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Class asClass) {	// XXX
			INSTANCE.logInstance(asClass);
			return INSTANCE;
		}

		public static @NonNull DefaultCacheClassCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
			INSTANCE.logInstance(asOperation, maybeVirtual);
			return INSTANCE;
		}

		@Override
		protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
			return getDefaultParentPackage(analyzer, asOperation);
		}
	}

	public static class DefaultEntryClassCallingConvention extends AbstractEntryClassCallingConvention
	{
		private static final @NonNull DefaultEntryClassCallingConvention INSTANCE = new DefaultEntryClassCallingConvention();

		public static @NonNull DefaultEntryClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Operation asOperation, boolean maybeVirtual) {
			INSTANCE.logInstance(asOperation, maybeVirtual);
			return INSTANCE;
		}
	}

	public static class RootCacheClassCallingConvention extends AbstractCacheClassCallingConvention
	{
		private static final @NonNull RootCacheClassCallingConvention INSTANCE = new RootCacheClassCallingConvention();

		public static @NonNull RootCacheClassCallingConvention getInstance(org.eclipse.ocl.pivot.@NonNull Operation asOperation, boolean maybeVirtual) {
			INSTANCE.logInstance(asOperation, maybeVirtual);
			return INSTANCE;
		}

		@Override
		protected org.eclipse.ocl.pivot.@NonNull Package getParentPackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
			return analyzer.getRootClassParentPackage(asOperation);
		}
	}

	protected final @NonNull Property createCacheInstance(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, org.eclipse.ocl.pivot.@NonNull Class asCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
		CGClass cgRootClass = analyzer.getCGRootClass(asEntryClass);
		ClassNameManager classNameManager = analyzer.getGlobalNameManager().getClassNameManager(cgRootClass);
		String propertyName = classNameManager.getUniquePropertyName(NameManagerHelper.INSTANCE_PROPERTY_NAME_PREFIX, asCacheClass);
		Property asProperty = PivotUtil.createProperty(propertyName, asCacheClass);
		analyzer.addCacheInstance(asOperation, asProperty, asEntryClass);
		//
		AbstractCachePropertyCallingConvention propertyCallingConvention = getCacheInstancePropertyCallingConvention(asProperty);
		CGProperty cgProperty = analyzer.generatePropertyDeclaration(asProperty, propertyCallingConvention);
		cgProperty.setCallingConvention(propertyCallingConvention);
		assert cgProperty.eContainer() != null;
		return asProperty;
	}

	protected final void createCacheProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass,
			@Nullable NameResolution nameResolution, @NonNull NamedElement asTypeOrTypedElement) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		//
		Property asCacheProperty = PivotFactory.eINSTANCE.createProperty();
		asCacheProperty.setName(nameResolution != null ? nameResolution.getResolvedName() : asTypeOrTypedElement.getName());
		if (asTypeOrTypedElement instanceof Type) {
			asCacheProperty.setType((Type)asTypeOrTypedElement);
			asCacheProperty.setIsRequired(true);
		}
		else if (asTypeOrTypedElement instanceof TypedElement) {
			TypedElement asTypedElement = (TypedElement)asTypeOrTypedElement;
			asCacheProperty.setType(PivotUtil.getType(asTypedElement));
			asCacheProperty.setIsRequired(asTypedElement.isIsRequired());
		}
		else {
			throw new IllegalStateException();
		}
		asCacheClass.getOwnedProperties().add(asCacheProperty);
		asCacheProperty.setImplementation(CacheProperty.INSTANCE);
		//
		CGProperty cgCacheProperty = analyzer.generatePropertyDeclaration(asCacheProperty, ImmutableCachePropertyCallingConvention.getInstance(asCacheProperty));
		if (nameResolution != null) {
			nameResolution.addCGElement(cgCacheProperty);
		}
		cgCacheClass.getProperties().add(cgCacheProperty);
		if (nameResolution == null) {
			ClassNameManager nameManager = analyzer.getClassNameManager(cgCacheClass, asCacheClass);
			//			nameManager.declareEagerName(cgCacheProperty)
			nameResolution = nameManager.getNameResolution(cgCacheProperty);
		}
	}

	protected final void createCachingClassesAndInstance(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
		Operation asOperation = CGUtil.getAST(cgOperation);
		AbstractEntryClassCallingConvention entryClassCallingConvention = getEntryClassCallingConvention(asOperation);
		CGClass cgEntryClass = entryClassCallingConvention.createEntryClass(analyzer, cgOperation);
		org.eclipse.ocl.pivot.Class asEntryClass = CGUtil.getAST(cgEntryClass);
		AbstractCacheClassCallingConvention cacheClassCallingConvention = getCacheClassCallingConvention(asOperation);
		CGClass cgCacheClass = cacheClassCallingConvention.createCacheClass(analyzer, asOperation, asEntryClass, this);
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		createCacheInstance(analyzer, asOperation, asCacheClass, asEntryClass);
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		// FIXME could be a regular CG call if CG and AS separately created by generateDeclaration
		JavaStream js = cg2javaVisitor.getJavaStream();
		CodeGenAnalyzer analyzer = cg2javaVisitor.getAnalyzer();
		CGOperation cgOperation = CGUtil.getOperation(cgOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
		Iterable<@NonNull CGValuedElement> cgArguments = CGUtil.getArguments(cgOperationCallExp);
		Property asCacheInstance = analyzer.getCacheInstance(asOperation);
		CGProperty cgCacheInstance = analyzer.getCGProperty(asCacheInstance);
		//
		for (@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		//
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		js.appendValueName(cgCacheInstance);
		js.append(".evaluate(");
		generateJavaCallArguments(cg2javaVisitor, true, cgArguments);
		js.append(");\n");
		return true;
	}

	protected void generateJavaCallArguments(@NonNull CG2JavaVisitor cg2javaVisitor, boolean isFirst, @NonNull Iterable<@NonNull CGValuedElement> cgArguments) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		for (@NonNull CGValuedElement cgArgument : cgArguments) {
			if (!isFirst) {
				js.append(", ");
			}
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			js.appendValueName(argument);
			isFirst = false;
		}
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		return true;		 // functionality realized by finer-grained CG elements
	}

	protected final @NonNull CGProperty getCGCacheProperty(@NonNull CGClass cgCacheClass, @NonNull String name) {
		for (@NonNull CGProperty cgProperty : CGUtil.getProperties(cgCacheClass)) {
			Property asProperty = CGUtil.getAST(cgProperty);
			if (name.equals(asProperty.getName())) {
				return cgProperty;
			}
		}
		throw new IllegalStateException();
	}

	protected @NonNull AbstractCacheClassCallingConvention getCacheClassCallingConvention(@NonNull Operation asOperation) {
		return DefaultCacheClassCallingConvention.getInstance(asOperation, false);
	}

	protected @NonNull AbstractCachePropertyCallingConvention getCacheInstancePropertyCallingConvention(@NonNull Property asProperty) {
		return CacheInstancePropertyCallingConvention.getInstance(asProperty);
	}

	protected @NonNull AbstractEntryClassCallingConvention getEntryClassCallingConvention(@NonNull Operation asOperation) {
		return DefaultEntryClassCallingConvention.getInstance(asOperation, false);
	}

	public void installConstructorOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, @NonNull Class asEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		CacheConstructorOperationCallingConvention callingConvention = CacheConstructorOperationCallingConvention.getInstance(asCacheClass);
		callingConvention.createOperation(analyzer, cgCacheClass, asOperation);
	}

	public void installEvaluateOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, @NonNull Class asEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		DefaultEvaluateOperationCallingConvention callingConvention = DefaultEvaluateOperationCallingConvention.getInstance(asCacheClass);
		callingConvention.createOperation(analyzer, cgCacheClass, asOperation, asEntryClass);
	}

	public void installNewInstanceOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass, @NonNull Operation asOperation) {
		org.eclipse.ocl.pivot.Class asCacheClass = CGUtil.getAST(cgCacheClass);
		CacheNewInstanceOperationCallingConvention callingConvention = CacheNewInstanceOperationCallingConvention.getInstance(asCacheClass);
		callingConvention.createOperation(analyzer, cgCacheClass, asEntryClass, asOperation);
	}
}
