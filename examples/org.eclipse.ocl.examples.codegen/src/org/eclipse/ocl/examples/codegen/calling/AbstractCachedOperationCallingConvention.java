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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
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
import org.eclipse.ocl.pivot.NamedElement;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.PivotFactory;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.evaluation.Evaluator;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
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
	 * A CacheProperty instance supports evaluation of a property in a CGed cache. (It has no opposite).
	 */
	public static class CacheProperty extends AbstractProperty
	{
		private final @NonNull PropertyId propertyId;
		private final @Nullable OCLExpression initExpression;
		private final @Nullable Object defaultValue;

		public CacheProperty(@NonNull PropertyId propertyId, @Nullable OCLExpression initExpression, @Nullable Object defaultValue) {
			this.propertyId = propertyId;
			this.initExpression = initExpression;
			this.defaultValue = defaultValue;
		}

		/** @deprecated use Executor */
		@Deprecated
		@Override
		public @Nullable Object evaluate(@NonNull Evaluator evaluator, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
			return evaluate(getExecutor(evaluator), returnTypeId, sourceValue);
		}

		/**
		 * @since 1.1
		 */
		@Override
		public @Nullable Object evaluate(@NonNull Executor executor, @NonNull TypeId returnTypeId, @Nullable Object sourceValue) {
			throw new UnsupportedOperationException();
		}
	}

	public static abstract class AbstractEvaluateOperationCallingConvention extends AbstractUncachedOperationCallingConvention
	{
		@Override
		public void createCGBody(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation) {
			//	Implemented as direct synthesis
		}

		protected @Nullable Parameter createCacheEvaluateOperationSelfParameter(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
			return null;
		}

		public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull CGClass cgCacheClass, @NonNull Operation asOperation, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
			//
			// AS Class - yyy2zzz
			// AS Properties -
			// AS Operation - yyy2zzz
			// AS Operation.ownedParameters - x, y
			// AS Cache Operation - evaluate
			// AS Cache Operation.parameters - x, y
			// AS Cache ExpressionInOCL.ownedContext - this
			// AS Cache ExpressionInOCL.ownedParameters -
			// CG Cache Operation - evaluate
			// CG Cache Operation.lets -
			//
			JavaCodeGenerator codeGenerator = analyzer.getCodeGenerator();
			GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
			org.eclipse.ocl.pivot.@NonNull Class asCacheClass = CGUtil.getAST(cgCacheClass);
			//
			//	Create AS declaration for newInstance
			//
			NameResolution evaluateNameResolution = globalNameManager.getEvaluateNameResolution();
			String newInstanceName = evaluateNameResolution.getResolvedName();
			Operation asCacheEvaluateOperation = PivotUtil.createOperation(newInstanceName, asEntryClass, null, null);
			asCacheEvaluateOperation.setType(asOperation.getType());
			asCacheEvaluateOperation.setIsRequired(asOperation.isIsRequired());

			List<@NonNull Parameter> asParameters = PivotUtilInternal.getOwnedParametersList(asOperation);
			List<@NonNull Parameter> asCacheEvaluateParameters = PivotUtilInternal.getOwnedParametersList(asCacheEvaluateOperation);
			Parameter asCacheEvaluateSelfParameter = createCacheEvaluateOperationSelfParameter(analyzer, asOperation);
			if (asCacheEvaluateSelfParameter != null) {
				asCacheEvaluateParameters.add(asCacheEvaluateSelfParameter);
			}
			for (@NonNull Parameter asParameter : asParameters) {
				Parameter asEvaluateParameter = PivotUtil.createParameter(PivotUtil.getName(asParameter), PivotUtil.getType(asParameter), asParameter.isIsRequired());
				asCacheEvaluateParameters.add(asEvaluateParameter);
			}
			asCacheClass.getOwnedOperations().add(asCacheEvaluateOperation);
			//
			//	Create AS body for newInstance
			//
			//	not implemented
			//
			//	Create CG declaration for newInstance
			//
			CGOperation cgCacheEvaluateOperation = createCGOperation(analyzer, asCacheEvaluateOperation);
			analyzer.initAst(cgCacheEvaluateOperation, asCacheEvaluateOperation, true);
			cgCacheEvaluateOperation.setCallingConvention(this);
			evaluateNameResolution.addCGElement(cgCacheEvaluateOperation);
			ExecutableNameManager operationNameManager = analyzer.getOperationNameManager(cgCacheEvaluateOperation, asCacheEvaluateOperation);
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
				}
				cgEvaluateParameters.add(cgParameter);
			}
			cgCacheClass.getOperations().add(cgCacheEvaluateOperation);
			return cgCacheEvaluateOperation;
		}
	}

	public static class CacheInstancePropertyCallingConvention extends ImmutableCachePropertyCallingConvention
	{
		private static final @NonNull CacheInstancePropertyCallingConvention INSTANCE = new CacheInstancePropertyCallingConvention();

		public static @NonNull CacheInstancePropertyCallingConvention getInstance(@NonNull Property asProperty) {
			INSTANCE.logInstance(asProperty);
			return INSTANCE;
		}

		@Override
		public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
			TypeRepresentation boxedTypeRepresentation = js.getBoxedTypeRepresentation();
			js.append("protected final");
			js.append(" /*@NonInvalid*/ ");
			boxedTypeRepresentation.appendClassReference(cgProperty.isRequired(), cgProperty);
			js.append(" ");
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

	protected final @NonNull Property createCacheInstance(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation, org.eclipse.ocl.pivot.@NonNull Class asCacheClass, org.eclipse.ocl.pivot.@NonNull Class asEntryClass) {
		CGClass cgRootClass = analyzer.getCGRootClass(asEntryClass);
		ClassNameManager classNameManager = analyzer.getGlobalNameManager().getClassNameManager(cgRootClass);
		String propertyName = classNameManager.getUniquePropertyName(NameManagerHelper.INSTANCE_PROPERTY_NAME_PREFIX, asCacheClass);
		Property asProperty = PivotUtil.createProperty(propertyName, asCacheClass);
		analyzer.addCacheInstance(asOperation, asProperty, asEntryClass);
		//
		CGProperty cgProperty = analyzer.createCGElement(CGProperty.class, asProperty);
		cgProperty.setCallingConvention(CacheInstancePropertyCallingConvention.getInstance(asProperty));
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
		asCacheProperty.setImplementation(new CacheProperty(asCacheProperty.getPropertyId(), null, null));
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
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		// FIXME could be a regular CG call if CG and AS separately created by generateDeclaration
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
		boolean isFirst = true;
		for (@NonNull CGValuedElement cgArgument : cgArguments) {
			if (!isFirst) {
				js.append(", ");
			}
			CGValuedElement argument = cg2javaVisitor.getExpression(cgArgument);
			js.appendValueName(argument);
			isFirst = false;
		}
		js.append(");\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
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

//	protected org.eclipse.ocl.pivot.@NonNull Package getCachePackage(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {	// XXX Regularly overridden
//		return AbstractLanguageSupport.getCachePackage(asOperation);
//	}

	protected @NonNull AbstractEntryClassCallingConvention getEntryClassCallingConvention(@NonNull Operation asOperation) {
		return DefaultEntryClassCallingConvention.getInstance(asOperation, false);
	}
}
