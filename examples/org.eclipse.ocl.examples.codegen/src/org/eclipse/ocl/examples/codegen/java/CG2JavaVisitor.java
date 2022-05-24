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
package org.eclipse.ocl.examples.codegen.java;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager.NameVariant;
import org.eclipse.ocl.examples.codegen.analyzer.NameResolution;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NestedNameManager.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.calling.OperationCallingConvention;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorType;
import org.eclipse.ocl.examples.codegen.cgmodel.CGGuardExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInteger;
import org.eclipse.ocl.examples.codegen.cgmodel.CGInvalid;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqual2Exp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsEqualExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsInvalidExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsKindOfExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIsUndefinedExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGIterator;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterateCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNamedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnboxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGUnlimited;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariableExp;
import org.eclipse.ocl.examples.codegen.cgmodel.util.AbstractExtendingCGModelVisitor;
import org.eclipse.ocl.examples.codegen.generator.GenModelHelper;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.java.operation.AndOperation2Handler;
import org.eclipse.ocl.examples.codegen.java.operation.AndOperationHandler;
import org.eclipse.ocl.examples.codegen.java.operation.ImpliesOperation2Handler;
import org.eclipse.ocl.examples.codegen.java.operation.ImpliesOperationHandler;
import org.eclipse.ocl.examples.codegen.java.operation.LibraryOperationHandler;
import org.eclipse.ocl.examples.codegen.java.operation.NotOperation2Handler;
import org.eclipse.ocl.examples.codegen.java.operation.NotOperationHandler;
import org.eclipse.ocl.examples.codegen.java.operation.OrOperation2Handler;
import org.eclipse.ocl.examples.codegen.java.operation.OrOperationHandler;
import org.eclipse.ocl.examples.codegen.java.operation.XorOperation2Handler;
import org.eclipse.ocl.examples.codegen.java.operation.XorOperationHandler;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.CallExp;
import org.eclipse.ocl.pivot.CollectionLiteralExp;
import org.eclipse.ocl.pivot.Element;
import org.eclipse.ocl.pivot.Enumeration;
import org.eclipse.ocl.pivot.Iteration;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.NestedTypeId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorMultipleIterationManager;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorMultipleMapIterationManager;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorSingleIterationManager;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.values.IntIntegerValueImpl;
import org.eclipse.ocl.pivot.internal.values.LongIntegerValueImpl;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.library.AbstractSimpleOperation;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;

import com.google.common.collect.Iterables;

/**
 * A CG2JavaVisitor serializes the contributions of a tree of model elements in a StringBuilder whose result may be
 * obtained by toString() on completion.
 *
 * The individual visits contribute a complete construct, usually one or more statements to the output. However
 * inlineable expressions contribute just their expression value.
 *
 * Visits return true if the generated flow of control flows out of the generated code,
 * false if an unconditional exception is thrown.
 */
public abstract class CG2JavaVisitor<@NonNull CG extends JavaCodeGenerator> extends AbstractExtendingCGModelVisitor<@NonNull Boolean, CG>
{
	protected class ArgumentSubStream implements SubStream
	{
		private final int argIndex;

		protected ArgumentSubStream(int argIndex) {
			this.argIndex = argIndex;
		}

		@Override
		public void append() {
			js.append(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
			js.append("[" + argIndex);
			js.append("]");
		}
	}

	protected final @NonNull GlobalNameManager globalNameManager;
	protected final @NonNull GenModelHelper genModelHelper;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull Id2JavaInterfaceVisitor id2JavaInterfaceVisitor;
	protected final @NonNull JavaStream js;
	protected final @NonNull Map<@NonNull Class<? extends LibraryOperation>, @NonNull LibraryOperationHandler> libraryOperation2handler = new HashMap<>();;

	/**
	 * The local Java context for the current operation.
	 */
	protected JavaLocalContext localContext;

	public CG2JavaVisitor(@NonNull CG codeGenerator) {
		super(codeGenerator);
		this.globalNameManager = codeGenerator.getGlobalNameManager();
		this.genModelHelper = context.getGenModelHelper();
		this.analyzer = context.getAnalyzer();
		this.id2JavaInterfaceVisitor = createId2JavaClassVisitor();
		this.environmentFactory = analyzer.getCodeGenerator().getEnvironmentFactory();
		this.js = codeGenerator.createJavaStream(this);
		installLibraryHandler(new AndOperationHandler(js));
		installLibraryHandler(new AndOperation2Handler(js));
		installLibraryHandler(new ImpliesOperationHandler(js));
		installLibraryHandler(new ImpliesOperation2Handler(js));
		installLibraryHandler(new NotOperationHandler(js));
		installLibraryHandler(new NotOperation2Handler(js));
		installLibraryHandler(new OrOperationHandler(js));
		installLibraryHandler(new OrOperation2Handler(js));
		installLibraryHandler(new XorOperationHandler(js));
		installLibraryHandler(new XorOperation2Handler(js));
	}

	protected void installLibraryHandler(@NonNull LibraryOperationHandler libraryOperationHandler) {
		libraryOperation2handler.put(libraryOperationHandler.getLibraryOperationClass(), libraryOperationHandler);
	}

	protected @NonNull String addImport(@Nullable Boolean isRequired, @NonNull String className) {
		return globalNameManager.addImport(isRequired, className);
	}

	/**
	 * Append the code for an EcorePropertyCall. If source is null, the code for the source will also be appended.
	 * If source is non-null the caller has already appended it.
	 *
	protected @NonNull Boolean appendCGEcorePropertyCallExp(@NonNull CGEcorePropertyCallExp cgPropertyCallExp, @Nullable CGValuedElement source) {
		Property asProperty = ClassUtil.nonNullState(cgPropertyCallExp.getReferredProperty());
		assert getESObject(asProperty) == ClassUtil.nonNullState(cgPropertyCallExp.getEStructuralFeature());
		//
		if (source == null) {
			source = getExpression(cgPropertyCallExp.getSource());
			if (!js.appendLocalStatements(source)) {
				return false;
			}
		}
		//
		Boolean ecoreIsRequired = context.isNonNull(asProperty);
		boolean isPrimitive = js.isPrimitive(cgPropertyCallExp);
		if (!isPrimitive) appendSuppressWarningsNull(cgPropertyCallExp, ecoreIsRequired);
		//		js.append("/ * " + ecoreIsRequired + " " + isRequired + " * /\n");
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		appendEcoreGet(source, asProperty);
		js.append(";\n");
		return true;
	} */

/*	protected void appendEcoreGet(@NonNull CGValuedElement cgSource, @NonNull Property asProperty) {
		CGTypeId cgTypeId = analyzer.getTypeId(asProperty.getOwningClass().getTypeId());
		ElementId elementId = ClassUtil.nonNullState(cgTypeId.getElementId());
		TypeDescriptor requiredTypeDescriptor = context.getUnboxedDescriptor(elementId);
		//		EStructuralFeature eStructuralFeature = ClassUtil.nonNullState(cgPropertyCallExp.getEStructuralFeature());
		EStructuralFeature eStructuralFeature = ClassUtil.nonNullState(getESObject(asProperty));
		String getAccessor;
		if (eStructuralFeature == OCLstdlibPackage.Literals.OCL_ELEMENT__OCL_CONTAINER) {
			getAccessor = JavaConstants.E_CONTAINER_NAME;
		}
		else {
			getAccessor = genModelHelper.getGetAccessor(eStructuralFeature);
		}
		Class<?> requiredJavaClass = requiredTypeDescriptor.hasJavaClass();
		Method leastDerivedMethod = requiredJavaClass != null ? context.getLeastDerivedMethod(requiredJavaClass, getAccessor) : null;
		Class<?> unboxedSourceClass;
		if (leastDerivedMethod != null) {
			unboxedSourceClass = leastDerivedMethod.getDeclaringClass();
		}
		else {
			unboxedSourceClass = requiredJavaClass;
		}
		if ((unboxedSourceClass != null) && (unboxedSourceClass != Object.class)) {
			js.appendAtomicReferenceTo(unboxedSourceClass, cgSource);
		}
		else {
			js.appendAtomicReferenceTo(cgSource);
		}
		js.append(".");
		js.append(getAccessor);
		js.append("()");
	} */

	protected void appendGlobalPrefix() {}

	protected void appendGuardFailure(@NonNull CGGuardExp cgGuardExp) {
		js.append("throw new ");
		js.appendClassReference(null, InvalidValueException.class);
		js.append("(");
		js.appendString("Null " + cgGuardExp.getMessage());
		js.append(");\n");
	}

	protected @NonNull Boolean appendLoopCall(@NonNull CGLibraryIterationCallExp cgIterationCallExp, @Nullable CGIterator iterateResult) {
		final CGValuedElement source = getExpression(cgIterationCallExp.getSource());
		final List<@NonNull CGIterator> iterators = CGUtil.getIteratorsList(cgIterationCallExp);
		final List<@NonNull CGIterator> coIterators = CGUtil.getCoIteratorsList(cgIterationCallExp);
		final CGValuedElement body = getExpression(cgIterationCallExp.getBody());
		final CGTypeId resultType = cgIterationCallExp.getTypeId();
		LoopExp asLoopExp = (LoopExp) CGUtil.getAST(cgIterationCallExp);
		final Iteration referredIteration = PivotUtil.getReferredIteration(asLoopExp);
		final int arity = iterators.size();
		Type sourceType = ((CallExp)asLoopExp).getOwnedSource().getType();
		boolean isMap = sourceType instanceof MapType;
		final Class<?> managerClass; 	// FIXME ExecutorMultipleIterationManager
		final Class<?> operationClass;
		final boolean passesCoIterators;
		boolean useMultiple = isMap || (coIterators.size() > 0) || (arity > 1);
		if (isMap) {
			managerClass = ExecutorMultipleMapIterationManager.class;
			operationClass = AbstractSimpleOperation.class;
			passesCoIterators = true;
		}
		else if (useMultiple) {
			managerClass = ExecutorMultipleIterationManager.class;
			operationClass = AbstractSimpleOperation.class;
			passesCoIterators = true;
		}
		else {
			managerClass = ExecutorSingleIterationManager.class;
			operationClass = AbstractBinaryOperation.class;
			passesCoIterators = false;
		}
		final LibraryIteration libraryIteration = ClassUtil.nonNullState(cgIterationCallExp.getLibraryIteration());
		final Method actualMethod = libraryIteration.getEvaluateMethod(referredIteration);
		final Class<?> actualReturnClass = actualMethod.getReturnType();
		boolean actualIsNonNull = context.getIsNonNull(actualMethod) == Boolean.TRUE;
		boolean expectedIsNonNull = cgIterationCallExp.isNonNull();
		final String astName = getResolvedName(cgIterationCallExp);
		final String bodyName = getVariantResolvedName(cgIterationCallExp, context.getBODY_NameVariant());
		final String executorName = globalNameManager.getExecutorName();
		final String implementationName = getVariantResolvedName(cgIterationCallExp, context.getIMPL_NameVariant());
		final String managerName = getVariantResolvedName(cgIterationCallExp, context.getMGR_NameVariant());
		final String staticTypeName = getVariantResolvedName(cgIterationCallExp, context.getTYPE_NameVariant());
		String accumulatorName;
		//
		//	Pre-amble: hoisted assignments
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		//	Dispatch: Determine static type
		//
		js.append("final ");
		js.appendClassReference(true, org.eclipse.ocl.pivot.Class.class);
		js.append(" " + staticTypeName + " = ");
		//		js.appendReferenceTo(evaluatorParameter);
		js.append(executorName);
		js.append(".getStaticTypeOfValue(null, ");
		js.appendValueName(source);
		js.append(");\n");
		//
		//	Dispatch: Determine dynamic operation
		//
		js.append("final ");
		js.appendClassReference(true, LibraryIteration.LibraryIterationExtension.class);
		js.append(" " + implementationName + " = (");
		js.appendClassReference(null, LibraryIteration.LibraryIterationExtension.class);
		js.append( ")" + staticTypeName + ".lookupImplementation(");
		js.appendReferenceTo(localContext.getNameManager().getStandardLibraryVariable());
		js.append(", ");
		js.appendQualifiedLiteralName(referredIteration);
		js.append(");\n");
		//
		if (iterateResult != null) {
			CGValuedElement init = CGUtil.getInit(iterateResult);
			accumulatorName = init.getResolvedName();
			if (!js.appendLocalStatements(init)) {
				return false;
			}
			js.appendDeclaration(iterateResult);
			js.append(" = ");
			js.appendValueName(init);
			js.append(";\n");
			//
			/*			js.append("Object " + accumulatorName + " = " + implementationName + ".createAccumulatorValue(");
//			js.appendValueName(evaluatorParameter);
			js.append(JavaConstants.EVALUATOR_NAME);
			js.append(", ");
			js.appendValueName(resultType);
			js.append(", ");
			js.appendValueName(body.getTypeId());
			js.append(");\n"); */
		}
		else {
			accumulatorName = "ACC_" + astName;
			js.append("final ");
			js.appendIsRequired(true);
			js.append(" Object " + accumulatorName + " = " + implementationName + ".createAccumulatorValue(");
			//			js.appendValueName(evaluatorParameter);
			js.append(executorName);
			js.append(", ");
			js.appendValueName(resultType);
			js.append(", ");
			js.appendValueName(body.getTypeId());
			js.append(");\n");
		}
		//
		js.append("/**\n");
		js.append(" * Implementation of the iterator body.\n");
		js.append(" */\n");
		js.append("final ");
		js.appendClassReference(true, operationClass);
		js.append(" " + bodyName + " = new ");
		js.appendClassReference(null, operationClass);
		js.append("()");
		js.pushClassBody(String.valueOf(operationClass));
		js.appendCommentWithOCL(null, body.getAst());
		js.append("@Override\n");
		js.append("public ");
		js.appendIsRequired(false);
		js.append(" Object ");
		js.append(globalNameManager.getEvaluateName());
		js.append("(final ");
		//		js.appendDeclaration(evaluatorParameter);
		js.appendClassReference(true, Executor.class);
		js.append(" ");
		js.append(executorName);
		js.append(", ");
		js.append("final ");
		//		js.appendDeclaration(localContext.getTypeIdParameter(cgIterateCallExp));
		js.appendClassReference(true, TypeId.class);
		js.append(" ");
		js.append(globalNameManager.getTypeIdNameResolution().getResolvedName());
		if (useMultiple) {
			js.append(", final ");
			js.appendIsRequired(false);
			js.append(" Object ");
			js.appendIsRequired(true);
			js.append(" [] ");
			js.append(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
		}
		else {
			if (iterateResult != null) {
				js.append(", ");
				js.appendDeclaration(iterateResult);
			}
			else {
				js.append(", final ");
				js.appendIsRequired(false);
				js.append(" Object ");
				js.appendValueName(source);
				//				js.appendDeclaration(source);
			}
			for (int i = 0; i < arity; i++) {
				CGIterator iterator = iterators.get(i);
				js.append(", final ");
				js.appendDeclaration(iterator);
			}
			if (coIterators.size() > 0) {
				for (int i = 0; i < arity; i++) {
					js.append(", final ");
					js.appendDeclaration(coIterators.get(i));
				}
			}
		}
		js.append(") {\n");
		js.pushIndentation(null);
		if (useMultiple) {
			int argIndex = 0;			// Skip source
			Boolean isRequired = context.isRequired(source);
			if (isRequired == Boolean.TRUE) {
				js.appendSuppressWarningsNull(true);
			}
			js.append("final ");
			js.appendTypeDeclaration(source);
			js.append(" ");
			js.appendValueName(source);
			js.append(" = ");
			js.appendClassCast(source, isRequired, Object.class, new ArgumentSubStream(argIndex));
			js.append(";\n");
			argIndex++;
			for (int i = 0; i < arity; i++) {
				CGParameter iterator = iterators.get(i);
				isRequired = context.isRequired(iterator);
				if (isRequired == Boolean.TRUE) {
					js.appendSuppressWarningsNull(true);
				}
				js.append("final ");
				js.appendDeclaration(iterator);
				js.append(" = ");
				js.appendClassCast(iterator, isRequired, Object.class, new ArgumentSubStream(argIndex));
				js.append(";\n");
				argIndex++;
			}
			if (coIterators.size() > 0) {
				for (int i = 0; i < arity; i++) {
					CGIterator coIterator = coIterators.get(i);
					Variable asCoIterator = CGUtil.getAST(coIterator);
					if (!asCoIterator.isIsImplicit()) {
						isRequired = context.isRequired(coIterator);
						if (isRequired == Boolean.TRUE) {
							js.appendSuppressWarningsNull(true);
						}
						js.append("final ");
						js.appendDeclaration(coIterator);
						js.append(" = ");
						js.appendClassCast(coIterator, isRequired, Object.class, new ArgumentSubStream(argIndex));
						js.append(";\n");
					}
					argIndex++;
				}
			}
		}
		JavaLocalContext savedLocalContext = localContext;
		try {
			localContext = globalNameManager.findLocalContext(cgIterationCallExp);
			appendReturn(body);
		}
		finally {
			localContext = savedLocalContext;
		}
		js.popIndentation();
		js.append("}\n");
		js.popClassBody(true);
		//
		//	Dispatch: Create execution manager
		//
		js.append("final ");
		js.appendClassReference(true, managerClass);
		js.append(" " + managerName + " = new ");
		js.appendClassReference(null, managerClass);
		js.append("(");
		//		js.appendReferenceTo(evaluatorParameter);
		js.append(executorName);
		js.append(", ");
		if (useMultiple) {
			js.append(arity + ", ");
		}
		if (passesCoIterators) {
			js.append((coIterators.size() > 0) + ", ");
		}
		js.appendValueName(resultType);
		js.append(", " + bodyName + ", ");
		js.appendReferenceTo(isMap ? MapValue.class : CollectionValue.class, source);
		//		js.appendValueName(source);
		js.append(", " + accumulatorName + ");\n");
		//
		//	Dispatch: Invoke iteration
		//
		boolean isRequiredNullCast = expectedIsNonNull && !actualIsNonNull;
		if (isRequiredNullCast) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgIterationCallExp);
		js.append(" = ");
		//		if (isRequiredNullCast) {
		//			js.appendClassReference(null, ClassUtil.class);
		//			js.append(".nonNullState(");
		//		}
		SubStream castBody4 = new SubStream() {
			@Override
			public void append() {
				js.append(implementationName + ".evaluateIteration(" + managerName + ")");
				//				if (isRequiredNullCast) {
				//					js.append(")");
				//				}
			}
		};
		js.appendClassCast(cgIterationCallExp, isRequiredNullCast, actualReturnClass, castBody4);
		js.append(";\n");
		return true;
	}

	public @Nullable LibraryOperationHandler basicGetLibraryOperationHandler(@NonNull Class<? extends LibraryOperation> libraryOperation) {
		return libraryOperation2handler.get(libraryOperation);
	}

	public String getBodyPrefixedName() {
		return "BODY_";
	}

	public void appendReturn(@NonNull CGValuedElement body) {
		if (js.appendLocalStatements(body)) {
			if (body instanceof CGThrowExp) {				// FIXME generalize
				body.accept(this);
			}
			else {
				CGInvalid cgInvalidValue = body.getInvalidValue();
				if (cgInvalidValue != null) {
					js.append("throw ");
					js.appendValueName(cgInvalidValue);
				}
				else {
					js.append("return ");
					js.appendValueName(body);
				}
				js.append(";\n");
			}
		}
	}

	public void appendSuppressWarningsNull(@NonNull CGValuedElement cgActual, Boolean isNonNull) {
		boolean isRequired = cgActual.isNonNull();
		boolean isPrimitive = js.isPrimitive(cgActual);
		if (!isPrimitive && isRequired && (isNonNull != Boolean.TRUE)) {
			js.appendSuppressWarningsNull(true);
		}
	}

	protected @NonNull Id2JavaInterfaceVisitor createId2JavaClassVisitor() {
		return new Id2JavaInterfaceVisitor();
	}

	protected @NonNull Id2JavaExpressionVisitor createId2JavaExpressionVisitor(@NonNull JavaStream javaStream) {
		return new Id2JavaExpressionVisitor(javaStream);
	}

	protected boolean doClassFields(@NonNull CGClass cgClass, boolean needsBlankLine) {
		if (cgClass.getProperties().size() > 0) {
			if (needsBlankLine) {
				js.append("\n");
			}
			for (CGProperty cgProperty : cgClass.getProperties()) {
				cgProperty.accept(this);
			}
			needsBlankLine = true;
		}
		return needsBlankLine;
	}

	protected boolean doClassMethods(@NonNull CGClass cgClass, boolean needsBlankLine) {
		for (CGOperation cgOperation : cgClass.getOperations()) {
			if (needsBlankLine) {
				js.append("\n");
			}
			cgOperation.accept(this);
			needsBlankLine = true;
		}
		return needsBlankLine;
	}

	protected boolean doClassStatics(@NonNull CGClass cgClass, boolean needsBlankLine) {
		return needsBlankLine;
	}

	protected boolean doNestedClasses(@NonNull CGClass cgClass, boolean needsBlankLine) {
		for (CGClass cgNestedClass : cgClass.getClasses()) {
			if (needsBlankLine) {
				js.append("\n");
			}
			cgNestedClass.accept(this);
			needsBlankLine = true;
		}
		return needsBlankLine;
	}

	public void generateGlobals(@NonNull Iterable<@NonNull ? extends CGValuedElement> sortedElements) {
		for (@NonNull CGValuedElement cgElement : sortedElements) {
			assert cgElement.isGlobal();
			cgElement.accept(this);
		}
	}

	@Deprecated /* deprecated use getImportManager */
	public @NonNull Set<String> getAllImports() {
		return getImportNameManager().getLong2ShortImportNames().keySet();
	}

	public @NonNull CodeGenAnalyzer getAnalyzer() {
		return analyzer;
	}

	public @NonNull CG getCodeGenerator() {
		return context;
	}

	public @Nullable EStructuralFeature getESObject(@NonNull Property asProperty) {
		EObject esObject = asProperty.getESObject();
		if (esObject instanceof EStructuralFeature) {
			return (EStructuralFeature)esObject;
		}
		Property oppositeProperty = asProperty.getOpposite();
		if (oppositeProperty == null) {
			return null;
		}
		if (!oppositeProperty.isIsComposite()) {
			LibraryProperty libraryProperty = environmentFactory.getMetamodelManager().getImplementation(null, null, asProperty);
			if (!(libraryProperty instanceof OclElementOclContainerProperty)) {
				return null;
			}
		}
		return OCLstdlibPackage.Literals.OCL_ELEMENT__OCL_CONTAINER;
	}

	public @NonNull CGValuedElement getExpression(@Nullable CGValuedElement cgExpression) {
		return analyzer.getCGExpression(cgExpression);
	}

	public @NonNull GenModelHelper getGenModelHelper() {
		return genModelHelper;
	}

	public @Nullable GenPackage getGenPackage() {
		return null;
	}

	public @NonNull ImportNameManager getImportNameManager() {
		return context.getImportNameManager();
	}

	protected @Nullable Class<?> getLeastDerivedClass(Class<?> requiredClass, @NonNull String getAccessor) {
		Class<?> superClass = requiredClass.getSuperclass();
		if (superClass != null) {
			try {
				Class<?> lessDerivedSuperClass = getLeastDerivedClass(superClass, getAccessor);
				if (lessDerivedSuperClass != null) {
					return lessDerivedSuperClass;
				}
				Method method = superClass.getMethod(getAccessor);
				if (method != null) {
					return superClass;
				}
			} catch (Exception e) {
			}
		}
		for (Class<?> superInterface : requiredClass.getInterfaces()) {
			Class<?> lessDerivedSuperInterface = getLeastDerivedClass(superInterface, getAccessor);
			if (lessDerivedSuperInterface != null) {
				return lessDerivedSuperInterface;
			}
			try {
				Method method = superInterface.getMethod(getAccessor);
				if (method != null) {
					return superInterface;
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	protected @NonNull JavaLocalContext getLocalContext() {
		return ClassUtil.nonNullState(localContext);
	}

	protected @NonNull String getResolvedName(@NonNull CGValuedElement cgElement) {
		return cgElement.getResolvedName();
	}

	public @NonNull String getVariantResolvedName(@NonNull CGNamedElement cgElement, @NonNull NameVariant nameVariant) {
		NestedNameManager nameManager = localContext.getNameManager();
		String variantResolvedName = nameManager.basicGetVariantResolvedName(cgElement, nameVariant);
		if (variantResolvedName == null) {
			nameManager = globalNameManager.findLocalContext(cgElement).getNameManager();
			variantResolvedName = nameManager.basicGetVariantResolvedName(cgElement, nameVariant);
			assert variantResolvedName != null;
		}
		return variantResolvedName;
	}

//	protected @NonNull String getValueName(@NonNull CGValuedElement cgElement) {
//		String valueName = localContext != null ? localContext.getValueName(cgElement) : context.getValueName(cgElement);
//		return valueName;
//	}

	@Deprecated /* @deprecated no longer used */
	protected @Nullable CGVariable installExecutorVariable(@NonNull CGValuedElement cgValuedElement) {
		return localContext.getNameManager().getExecutorVariable();
	}

	@Deprecated /* @deprecated no longer used */
	protected @Nullable CGVariable installIdResolverVariable(@NonNull CGValuedElement cgValuedElement) {
		return localContext.getNameManager().createIdResolverVariable();
	}

	@Deprecated /* @deprecated no longer used */
	protected @NonNull CGVariable installStandardLibraryVariable(@NonNull CGValuedElement cgValuedElement) {
		return localContext.getNameManager().createStandardLibraryVariable();
	}

	protected boolean isBoxedElement(@NonNull CGValuedElement cgValue) {
		TypeId typeId = cgValue.getASTypeId();
		if (typeId instanceof EnumerationLiteralId) {
			return true;
		}
		if (typeId instanceof EnumerationId) {
			return true;
		}
		if (typeId instanceof ClassId) {
			return true;
		}
		return false;
	}

	protected boolean isBoxedType2(@NonNull CGValuedElement cgValue) {
		TypeId typeId = cgValue.getASTypeId();
		if (typeId instanceof NestedTypeId) {
			return true;
		}
		return false;
	}

	protected boolean isBoxedType(@NonNull CGValuedElement cgValue) {
		Element ast = cgValue.getAst();
		if (!(ast instanceof TypedElement)) {
			return false;
		}
		Type type = ((TypedElement)ast).getType();
		if (type == null) {
			return false;
		}
		if (type instanceof Enumeration) {
			return false;
		}
		PivotMetamodelManager metamodelManager = context.getEnvironmentFactory().getMetamodelManager();
		Type oclTypeType = environmentFactory.getStandardLibrary().getOclTypeType();
		return metamodelManager.conformsTo(type, TemplateParameterSubstitutions.EMPTY, oclTypeType, TemplateParameterSubstitutions.EMPTY);
	}

	protected boolean isEmpty(@NonNull CGClass cgClass) {
		if (cgClass.getOperations().size() > 0) {
			return false;
		}
		if (cgClass.getProperties().size() > 0) {
			return false;
		}
		List<@NonNull CGClass> cgClasses = CGUtil.getClassesList(cgClass);
		if (cgClasses.size() > 0) {
			for (CGClass cgNestedClass : cgClasses) {
				if (!isEmpty(cgNestedClass)) {
					return false;
				}
			}
		}
		return true;
	}


	protected boolean isEnumerationLiteral(@NonNull CGValuedElement cgValue) {
		Element ast = cgValue.getAst();
		if (!(ast instanceof TypedElement)) {
			return false;
		}
		Type type = ((TypedElement)ast).getType();
		return type instanceof Enumeration;
	}

	@Override
	public @NonNull String toString() {
		String string = js.toString();
		return string;
	}

	@Override
	public @NonNull Boolean visiting(@NonNull CGElement visitable) {
		throw new UnsupportedOperationException(getClass().getSimpleName() + ": " + visitable.getClass().getSimpleName());
	}

	@Override
	public @NonNull Boolean visitCGAssertNonNullExp(@NonNull CGAssertNonNullExp cgAssertNonNullExp) {
		CGValuedElement cgSource = getExpression(cgAssertNonNullExp.getSource());
		//
		if (cgSource.isNull()) {
			js.append("throw new ");
			js.appendClassReference(null, InvalidValueException.class);
			js.append("();\n");
		}
		else {
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			if (!cgSource.isNonNull()) {
				js.append("assert ");
				js.appendValueName(cgSource);
				js.append(" != null;\n");
			}
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGBodiedProperty(@NonNull CGBodiedProperty cgProperty) {
		localContext = globalNameManager.findLocalContext(cgProperty);
		try {
			return cgProperty.getCallingConvention().generateJavaDeclaration(this, js, cgProperty);
		}
		finally {
			localContext = null;
		}
	}

	@Override
	public @NonNull Boolean visitCGBoolean(@NonNull CGBoolean cgBoolean) {
		boolean booleanValue = cgBoolean.isBooleanValue();
		if (booleanValue) {
			js.appendTrue();
		}
		else {
			js.appendFalse();
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGBoxExp(@NonNull CGBoxExp cgBoxExp) {
		CGValuedElement unboxedValue = getExpression(cgBoxExp.getSource());
		TypeDescriptor unboxedTypeDescriptor = context.getTypeDescriptor(unboxedValue);
		JavaLocalContext localContext2 = localContext;
		assert localContext2 != null;
		//
		if (!js.appendLocalStatements(unboxedValue)) {
			return false;
		}
		return unboxedTypeDescriptor.appendBox(js, localContext2, cgBoxExp, unboxedValue);
	}

	@Override
	public @NonNull Boolean visitCGBuiltInIterationCallExp(@NonNull CGBuiltInIterationCallExp cgIterationCallExp) {
		CGValuedElement cgSource = getExpression(cgIterationCallExp.getSource());
		CGValuedElement cgBody = getExpression(cgIterationCallExp.getBody());
		CGIterator cgAccumulator = cgIterationCallExp.getAccumulator();
		CGIterator cgIterator = CGUtil.getIterator(cgIterationCallExp, 0);
		CGIterator cgCoIterator = cgIterationCallExp.getCoIterators().size() > 0 ? cgIterationCallExp.getCoIterators().get(0) : null;
		String iteratorName = getVariantResolvedName(cgIterator, context.getITER_NameVariant());
		Iteration2Java iterationHelper = context.getIterationHelper(ClassUtil.nonNullState(cgIterationCallExp.getReferredIteration()));
		assert iterationHelper != null;
		boolean flowContinues = false;
		boolean isMap = cgSource.getASTypeId() instanceof MapTypeId;
		//
		if (!js.appendLocalStatements(cgSource)) {
			return false;
		}
		//
		//	Declare and initialize accumulator
		//
		if (cgAccumulator != null) {
			CGValuedElement cgInit = cgAccumulator.getInit();
			if (cgInit != null) {
				if (!js.appendLocalStatements(cgInit)) {
					return false;
				}
			}
			cgAccumulator.toString();
			js.appendDeclaration(cgAccumulator);
			js.append(" = ");
			iterationHelper.appendAccumulatorInit(js, cgIterationCallExp);
			js.append(";\n");
		}
		//
		//	Declare iterator
		//
		js.appendClassReference(cgIterator.isRequired(), Iterator.class, false, Object.class); //, getJavaClass(cgIterator));
		js.append(" " + iteratorName + " = ");
		js.appendAtomicReferenceTo(cgSource);
		js.append(".iterator();\n");
		if (!isMap && (cgCoIterator != null)) {
			js.appendDeclaration(cgCoIterator);
			js.append(" = ");
			js.appendClassReference(null, ValueUtil.class);
			js.append(".ONE_VALUE;\n");
		}
		//
		//	Declare body result
		//
		js.appendDeclaration(cgIterationCallExp);
		js.append(";\n");
		JavaLocalContext savedLocalContext = localContext;
		try {
			localContext = globalNameManager.findLocalContext(cgIterationCallExp);
			//
			//	Declare loop head
			//
			js.append("while (true) {\n");
			js.pushIndentation(null);
			//
			//	Terminate loop once done
			//
			js.append("if (!" + iteratorName + ".hasNext()) {\n");
			js.pushIndentation(null);
			if (iterationHelper.appendFinalValue(js, cgIterationCallExp)) {
				js.append("break;\n");
				flowContinues = true;
			}
			js.popIndentation();
			js.append("}\n");
			//
			// Declare iterator advance.
			//
			appendSuppressWarningsNull(cgIterator, Boolean.FALSE);
			js.appendDeclaration(cgIterator);
			js.append(" = ");
			SubStream castBody1 = new SubStream() {
				@Override
				public void append() {
					js.append(iteratorName + ".next()");
				}
			};
			js.appendClassCast(cgIterator, castBody1);
			js.append(";\n");
			//
			// Declare coiterator/key access.
			//
			if (isMap && (cgCoIterator != null)) { // && !isImplicit
				Variable asCoIterator = CGUtil.getAST(cgCoIterator);
				if (!asCoIterator.isIsImplicit()) {
					if (cgCoIterator.isRequired()) {
						js.appendSuppressWarningsNull(true);
					}
					js.appendDeclaration(cgCoIterator);
					js.append(" = ");
					SubStream castBody2 = new SubStream() {
						@Override
						public void append() {
							js.appendReferenceTo(cgSource);
							js.append(".at(");
							js.appendReferenceTo(cgIterator);
							js.append(")");
						}
					};
					js.appendClassCast(cgCoIterator, castBody2);
					js.append(";\n");
				}
			}
			//
			// Declare iteration body.
			//
			js.appendCommentWithOCL(null, cgBody.getAst());
			if (js.appendLocalStatements(cgBody)) {
				js.append("//\n");
				if (iterationHelper.appendUpdate(js, cgIterationCallExp)) {
					flowContinues = true;
				}
			}
			if (!isMap && (cgCoIterator != null)) {
				js.appendReferenceTo(cgCoIterator);
				js.append(" = ");
				js.appendReferenceTo(cgCoIterator);
				js.append(".addInteger(");
				js.appendClassReference(null, ValueUtil.class);
				js.append(".ONE_VALUE);\n");
			}
			//
			//	Declare loop tail
			//
			js.popIndentation();
			js.append("}\n");
		}
		finally {
			localContext = savedLocalContext;
		}
		return flowContinues;
	}

	@Override
	public @NonNull Boolean visitCGCastExp(@NonNull CGCastExp cgCastExp) {
		CGValuedElement cgSource = getExpression(cgCastExp.getSource());
		if (!js.appendLocalStatements(cgSource)) {
			return false;
		}
		CGExecutorType cgType = cgCastExp.getExecutorType();
		if (cgType != null) {
			TypeDescriptor requiredTypeDescriptor = context.getTypeDescriptor(cgCastExp);
		//	TypeDescriptor actualTypeDescriptor;
		//	if (cgSource instanceof CGVariableExp) {	// CAST self has a distinct OclVoid type
		//		actualTypeDescriptor = context.getTypeDescriptor(CGUtil.getReferredVariable((CGVariableExp)cgSource));
		//	}
		//	else {
		//		actualTypeDescriptor = context.getTypeDescriptor(cgSource);
		//	}
			js.appendDeclaration(cgCastExp);
			js.append(" = ");
		//	if (requiredTypeDescriptor != actualTypeDescriptor) {
				requiredTypeDescriptor.appendCastTerm(js, cgSource);
		//	}
		//	else {
		//		js.appendReferenceTo(cgSource);
		//	}
			js.append(";\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGCatchExp(@NonNull CGCatchExp cgCatchExp) {
		CGValuedElement cgSource = getExpression(cgCatchExp.getSource());
		final String thrownName = getVariantResolvedName(cgCatchExp, context.getTHROWN_NameVariant());
		if (cgSource.isNonInvalid()) {
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			js.appendDeclaration(cgCatchExp);
			js.append(" = ");
			js.appendValueName(cgSource);
			js.append(";\n");
		}
		else {
			js.appendDeclaration(cgCatchExp);
			js.append(";\n");
			js.append("try {\n");
			js.pushIndentation(null);
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			js.appendValueName(cgCatchExp);
			js.append(" = ");
			js.appendValueName(cgSource);
			js.append(";\n");
			js.popIndentation();
			js.append("}\n");
			js.append("catch (");
			js.appendClassReference(null, Exception.class);
			js.append(" ");
			js.append(thrownName);
			js.append(") {\n");
			js.pushIndentation(null);
			js.appendValueName(cgCatchExp);
			js.append(" = ");
			js.appendClassReference(null, ValueUtil.class);
			js.append(".createInvalidValue(");
			js.append(thrownName);
			js.append(");\n");
			js.popIndentation();
			js.append("}\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGClass(@NonNull CGClass cgClass) {
		if (!isEmpty(cgClass)) {
			CGPackage containingPackage = cgClass.getContainingPackage();
			if (containingPackage != null) {
				js.appendClassHeader(containingPackage);
			}
			String className = cgClass.getName();
			js.append("public");
			if (containingPackage == null) {
				js.append(" static");
			}
			js.append(" class " + className);
			List<CGClass> cgSuperTypes = cgClass.getSuperTypes();
			boolean isFirst = true;
			for (CGClass cgSuperType : cgSuperTypes) {
				if (!cgSuperType.isInterface()) {
					if (isFirst) {
						js.append("\n\textends ");
					}
					else {
						js.append(", ");
					}
					js.appendClassReference(cgSuperType);
					isFirst = false;
				}
			}
			isFirst = true;
			for (CGClass cgSuperType : cgSuperTypes) {
				if (cgSuperType.isInterface()) {
					if (isFirst) {
						js.append("\n\timplements ");
					}
					else {
						js.append(", ");
					}
					js.appendClassReference(cgSuperType);
					isFirst = false;
				}
			}
			js.append("\n");
			js.append("{\n");
			js.pushIndentation(null);
			boolean needsBlankLine = false;
			needsBlankLine = doClassStatics(cgClass, needsBlankLine);
			needsBlankLine = doClassFields(cgClass, needsBlankLine);
			needsBlankLine = doClassMethods(cgClass, needsBlankLine);
			needsBlankLine = doNestedClasses(cgClass, needsBlankLine);
			js.popIndentation();
			js.append("}\n");
		}
		return true;
	}
	@Override
	public @NonNull Boolean visitCGCollectionExp(@NonNull CGCollectionExp cgCollectionExp) {
		int ranges = 0;
		for (CGCollectionPart cgPart : cgCollectionExp.getParts()) {
			if (cgPart.isRange()) {
				ranges++;
			}
			if (!js.appendLocalStatements(cgPart)) {
				return false;
			}
		}
		js.appendDeclaration(cgCollectionExp);
		js.append(" = ");
		js.appendClassReference(null, ValueUtil.class);
		String kind = ((CollectionLiteralExp)cgCollectionExp.getAst()).getKind().getName();
		if (ranges > 0) {
			js.append(".create" + kind + "Range(");
			//			CGTypeVariable typeVariable = localContext.getTypeVariable(cgCollectionExp.getTypeId());
			js.appendIdReference(cgCollectionExp.getTypeId().getElementId());
			for (CGCollectionPart cgPart : cgCollectionExp.getParts()) {
				js.append(", ");
				js.appendValueName(cgPart);
			}
		}
		else {
			js.append(".create" + kind + "OfEach(");
			//		CGTypeVariable typeVariable = localContext.getTypeVariable(cgCollectionExp.getTypeId());
			js.appendIdReference(cgCollectionExp.getTypeId().getElementId());
			for (CGCollectionPart cgPart : cgCollectionExp.getParts()) {
				js.append(", ");
				if (cgPart.isNull() && (cgCollectionExp.getParts().size() == 1)) {
					js.append("(Object)");
				}
				js.appendValueName(cgPart);
			}
		}
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGCollectionPart(@NonNull CGCollectionPart cgCollectionPart) {
		CGValuedElement first = getExpression(cgCollectionPart.getFirst());
		CGValuedElement last = cgCollectionPart.getLast();
		if (last != null) {
			if (!js.appendLocalStatements(first)) {
				return false;
			}
			if (!js.appendLocalStatements(last)) {
				return false;
			}
			js.appendDeclaration(cgCollectionPart);
			js.append(" = ");
			js.appendClassReference(null, ValueUtil.class);
			js.append(".createRange(");
			js.appendValueName(first);
			js.append(", ");
			js.appendValueName(last);
			js.append(");\n");
		}
		else {
			if (first.isInlined()) {
				js.appendValueName(first);
			}
			else {
				if (!js.appendLocalStatements(first)) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGConstantExp(@NonNull CGConstantExp cgConstantExp) {
		CGValuedElement referredConstant = cgConstantExp.getReferredConstant();
		if ((referredConstant != null) && referredConstant.isInlined()) {
			referredConstant.accept(this);
		}
		else {
			// Non-inline constants should be generated somewhere else and referenced by name in the caller
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGConstraint(@NonNull CGConstraint cgConstraint) {
		localContext = globalNameManager.findLocalContext(cgConstraint);
		try {
			Boolean flowContinues = super.visitCGConstraint(cgConstraint);
			assert flowContinues != null;
			return flowContinues;
		}
		finally {
			localContext = null;
		}
	}

	@Override
	public @NonNull Boolean visitCGEcoreDataTypeShadowExp(@NonNull CGEcoreDataTypeShadowExp cgShadowExp) {
		//
		//	Availability of a GenPackage is mandatory since we must have an EFactory.createFromString method to do the construction.
		//
		EDataType eDataType = cgShadowExp.getEDataType();
		final Class<?> javaClass = eDataType.getInstanceClass();
		if (javaClass == null) {
			throw new IllegalStateException("No Java class for " + cgShadowExp + " in CG2JavaVisitor.visitCGEcoreDataTypeShadowExp()");
		}
		final EPackage ePackage = eDataType.getEPackage();
		String nsURI = ePackage.getNsURI();
		if (nsURI == null) {
			throw new IllegalStateException("No EPackage NsURI for " + cgShadowExp + " in CG2JavaVisitor.visitCGEcoreDataTypeShadowExp()");
		}
		GenPackage genPackage = environmentFactory.getMetamodelManager().getGenPackage(nsURI);
		if (genPackage == null) {
			throw new IllegalStateException("No GenPackage for " + cgShadowExp + " in CG2JavaVisitor.visitCGEcoreDataTypeShadowExp()");
		}
		final String eFactoryName = genPackage.getQualifiedFactoryInterfaceName();
		final String ePackageName = genPackage.getQualifiedPackageInterfaceName();
		final String dataTypeName = CodeGenUtil.upperName(eDataType.getName());
		ClassLoader classLoader = eDataType.getClass().getClassLoader();
		Class<?> factoryClass;
		Class<?> packageClass;
		try {
			factoryClass = classLoader.loadClass(eFactoryName);
			packageClass = eDataType.getClass().getClassLoader().loadClass(ePackageName);
		}
		catch (ClassNotFoundException e) {
			throw new IllegalStateException("Load class failure for " + cgShadowExp + " in CG2JavaVisitor.visitCGEcoreDataTypeShadowExp()", e);
		}
		//
		CGValuedElement init = ClassUtil.nonNullState(cgShadowExp.getParts().get(0).getInit());
		if (!js.appendLocalStatements(init)) {
			return false;
		}
		Boolean ecoreIsRequired = null;						// createFromString is undeclared-nonnull -- FIXME compute rather than assume
		Boolean isRequired = context.isRequired(cgShadowExp);
		@SuppressWarnings("null")
		boolean suppressWarnings = (isRequired == Boolean.TRUE) && (ecoreIsRequired != Boolean.TRUE);
		if (suppressWarnings) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgShadowExp);
		js.append(" = ");
		js.append("(");
		js.appendClassReference(isRequired, javaClass);
		js.append(")");
		js.appendClassReference(null, factoryClass);
		js.append(".eINSTANCE.createFromString(");
		js.appendClassReference(null, packageClass);
		js.append(".Literals." + dataTypeName + ", ");
		js.appendValueName(init);
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGEcoreExp(@NonNull CGEcoreExp cgEcoreExp) {
		CGValuedElement boxedValue = getExpression(cgEcoreExp.getSource());
		TypeDescriptor boxedTypeDescriptor = context.getTypeDescriptor(boxedValue);
		JavaLocalContext localContext2 = localContext;
		assert localContext2 != null;
		//
		if (!js.appendLocalStatements(boxedValue)) {
			return false;
		}
	/*	EClassifier eClassifier = cgEcoreExp.getEClassifier();
		if (eClassifier != null) {		// FIXME ignores Collections
			Class<?> ecoreClass = eClassifier.getInstanceClass();
			if (ecoreClass != null) {
				String functionName = null;
				if (ecoreClass == BigDecimal.class) {
					functionName = "bigDecimalValueOf";
				}
				else if (ecoreClass == BigInteger.class) {
					functionName = "bigIntegerValueOf";
				}
				else if ((ecoreClass == Byte.class) || (ecoreClass == byte.class)) {
					functionName = "byteValueOf";
				}
				else if ((ecoreClass == Character.class) || (ecoreClass == char.class)) {
					functionName = "characterValueOf";
				}
				else if ((ecoreClass == Double.class) || (ecoreClass == double.class)) {
					functionName = "doubleValueOf";
				}
				else if ((ecoreClass == Float.class) || (ecoreClass == float.class)) {
					functionName = "floatValueOf";
				}
				else if ((ecoreClass == Integer.class) || (ecoreClass == int.class)) {
					functionName = "intValueOf";
				}
				else if ((ecoreClass == Long.class) || (ecoreClass == long.class)) {
					functionName = "longValueOf";
				}
				else if ((ecoreClass == Short.class) || (ecoreClass == short.class)) {
					functionName = "shortValueOf";
				}
				if (functionName != null) {
					js.append("final ");
					js.appendClassReference(null, ecoreClass);
					js.append(" ");
					js.appendValueName(cgEcoreExp);
					js.append(" = ");
					js.appendClassReference(null, ValueUtil.class);
					js.append(".");
					js.append(functionName);
					js.append("(");
					js.appendValueName(cgEcoreExp.getSource());
					js.append(");\n");
					return true;
				}
			}
		} */
		//		return boxedTypeDescriptor.getEcoreDescriptor(context, null).appendEcore(js, localContext2, cgEcoreExp, boxedValue);
		return boxedTypeDescriptor.appendEcoreStatements(js, localContext2, cgEcoreExp, boxedValue);
	}

	@Override
	public @NonNull Boolean visitCGElementId(@NonNull CGElementId cgElementId) {
		ElementId elementId = cgElementId.getElementId();
		if ((elementId != null) && !CGUtil.isInlinedId(elementId)) {
			js.append("public static final ");
			js.appendIsCaught(true, false);
			js.append(" ");
			js.appendClassReference(true, elementId.accept(id2JavaInterfaceVisitor));
			js.append(" ");
			js.append(cgElementId.getResolvedName());
			js.append(" = ");
			js.appendIdReference2(elementId);
			js.append(";\n");
		}
		return true;
	}

/*	@Override
	public @NonNull Boolean visitCGExecutorCompositionProperty(@NonNull CGExecutorCompositionProperty cgExecutorProperty) {
		js.appendDeclaration(cgExecutorProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgExecutorProperty);
		js.append("(");
		js.appendIdReference(cgExecutorProperty.getUnderlyingPropertyId().getElementId());
		js.append(");\n");
		return true;
	} */

/*	@Override
	public @NonNull Boolean visitCGExecutorShadowPart(@NonNull CGExecutorShadowPart cgExecutorShadowPart) {
		js.appendDeclaration(cgExecutorShadowPart);
		js.append(" = ");
		js.appendValueName(localContext.getIdResolverVariable());
		js.append(".getProperty(");
		js.appendIdReference(cgExecutorShadowPart.getUnderlyingPropertyId().getElementId());
		js.append(");\n");
		return true;
	} */

/*	@Override
	public @NonNull Boolean visitCGExecutorOperation(@NonNull CGExecutorOperation cgExecutorOperation) {
		js.appendDeclaration(cgExecutorOperation);
		js.append(" = ");
		try {
			js.appendValueName(localContext.getIdResolverVariable());
		}
		catch (Exception e) {			// FIXME
			js.appendString(String.valueOf(e));
		}
		js.append(".getOperation(");
		js.appendIdReference(cgExecutorOperation.getUnderlyingOperationId().getElementId());
		js.append(");\n");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				try {
					js.appendValueName(localContext.getIdResolverVariable());
				}
				catch (Exception e) {			// FIXME
					js.appendString(String.valueOf(e));
				}
				js.append(".getOperation(");
				js.appendIdReference(cgExecutorOperation.getUnderlyingOperationId().getElementId());
				js.append(")");
			}
		};
		js.appendClassCast(cgExecutorOperation, castBody);
		js.append(";\n");
		return true;
	} */

/*	@Override
	public @NonNull Boolean visitCGExecutorOperationCallExp(@NonNull CGExecutorOperationCallExp cgOperationCallExp) {
		Operation pOperation = cgOperationCallExp.getReferredOperation();
		CGValuedElement cgThis = cgOperationCallExp.getCgThis();
		CGValuedElement cgSource = cgThis != null ? getExpression(cgThis) : null;
		List<CGValuedElement> cgArguments = cgOperationCallExp.getCgArguments();
		List<Parameter> pParameters = pOperation.getOwnedParameters();
		//
		if ((cgSource != null) && !js.appendLocalStatements(cgSource)) {
			return false;
		}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		//
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgOperationCallExp.getExecutorOperation());
				js.append(".");
				js.append(context.getEvaluateName());
				js.append("(");
				//		js.append(getValueName(localContext.getEvaluatorParameter(cgOperationCallExp)));
				js.append(context.getExecutorName());
				js.append(", ");
				js.appendIdReference(cgOperationCallExp.getASTypeId());
				if (cgSource != null) {
					js.append(", ");
					js.appendValueName(cgSource);
				}
				int iMax = Math.min(pParameters.size(), cgArguments.size());
				for (int i = 0; i < iMax; i++) {
					js.append(", ");
					CGValuedElement cgArgument = cgArguments.get(i);
					Parameter pParameter = pParameters.get(i);
					CGTypeId cgTypeId = analyzer.getTypeId(pParameter.getTypeId());
					TypeDescriptor parameterTypeDescriptor = context.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
					CGValuedElement argument = getExpression(cgArgument);
					js.appendReferenceTo(parameterTypeDescriptor, argument);
				}
				js.append(")");
			}
		};
		js.appendClassCast(cgOperationCallExp, castBody);
		js.append(";\n");
		return true;
	} */

/*	@Override
	public @NonNull Boolean visitCGExecutorProperty(@NonNull CGExecutorProperty cgExecutorProperty) {
		return cgExecutorProperty.getCallingConvention().generateJavaDeclaration(this, js, cgExecutorProperty);
	} */

	@Override
	public @NonNull Boolean visitCGExecutorType(@NonNull CGExecutorType cgExecutorType) {
		js.appendDeclaration(cgExecutorType);
		js.append(" = ");
		js.appendValueName(localContext.getNameManager().getIdResolverVariable());
		js.append(".getClass(");
		js.appendIdReference(cgExecutorType.getUnderlyingTypeId().getElementId());
		js.append(", null);\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGGuardExp(@NonNull CGGuardExp cgGuardExp) {
		CGValuedElement cgSource = getExpression(cgGuardExp.getSource());
		//
		if (cgSource.isNull()) {
			js.append("throw new ");
			js.appendClassReference(null, InvalidValueException.class);
			js.append("();\n");
		}
		else {
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			if (cgGuardExp.isSafe()) {
				js.append("assert ");
				js.appendValueName(cgSource);
				js.append(" != null;\n");
			}
			else if (!cgSource.isNonNull()) {
				js.append("if (");
				js.appendValueName(cgSource);
				js.append(" == null) {\n");
				js.pushIndentation(null);
				appendGuardFailure(cgGuardExp);
				js.popIndentation();
				js.append("}\n");
			}
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGIfExp(@NonNull CGIfExp cgIfExp) {
		CGValuedElement condition = getExpression(cgIfExp.getCondition());
		CGValuedElement thenExpression = getExpression(cgIfExp.getThenExpression());
		CGValuedElement elseExpression = getExpression(cgIfExp.getElseExpression());
		//		CGVariable resultVariable = localContext.getLocalVariable(cgIfExp);
		boolean flowContinues = false;
		//
		if (!js.appendLocalStatements(condition)) {
			return flowContinues;
		}
		js.appendDeclaration(cgIfExp);
		js.append(";\n");
		//
		js.append("if (");
		js.appendBooleanValueName(condition, true);
		js.append(") {\n");
		try {
			js.pushIndentation(null);
			if (js.appendAssignment(cgIfExp, thenExpression)) {
				flowContinues = true;
			}
		} finally {
			js.popIndentation();
		}
		js.append("}\n");
		js.append("else {\n");
		try {
			js.pushIndentation(null);
			if (js.appendAssignment(cgIfExp, elseExpression)) {
				flowContinues = true;
			}
		} finally {
			js.popIndentation();
		}
		js.append("}\n");
		return flowContinues;
	}

	@Override
	public @NonNull Boolean visitCGInteger(@NonNull CGInteger object) {
		js.appendDeclaration(object);
		js.append(" = ");
		js.appendClassReference(null, ValueUtil.class);
		js.append(".integerValueOf(");
		Number integerValue = object.getNumericValue();
		String valueString = integerValue.toString();
		assert valueString != null;
		if (integerValue instanceof IntIntegerValueImpl) {
			js.append(valueString);
		}
		else if (integerValue instanceof LongIntegerValueImpl) {
			js.append(valueString + "L");
		}
		else {
			js.append("\"" + valueString + "\"");
		}
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGInvalid(@NonNull CGInvalid object) {
		String message = object.getMessageTemplate();
		if (message != null) {
			js.append("new ");
			js.appendClassReference(null, InvalidValueException.class);
			js.append("(");
			js.appendString(message);
			for (Object binding : object.getBindings()) {
				js.append(", ");
				js.appendString(ClassUtil.nonNullState(String.valueOf(binding)));
			}
			js.append(")");
		}
		else {
			js.appendClassReference(null, ValueUtil.class);
			js.append(".INVALID_VALUE");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGIsEqualExp(@NonNull CGIsEqualExp cgIsEqualExp) {		// FIXME BUG 421738 move irregulaties to e.g. BooleanPrimitiveDescriptor
		if (cgIsEqualExp.isTrue()) {
			js.appendAssignBooleanLiteral(false, cgIsEqualExp, true);
		}
		else if (cgIsEqualExp.isFalse()) {
			js.appendAssignBooleanLiteral(false, cgIsEqualExp, false);
		}
		else {
			CGValuedElement cgSource = getExpression(cgIsEqualExp.getSource());
			CGValuedElement cgArgument = getExpression(cgIsEqualExp.getArgument());
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			if (!js.appendLocalStatements(cgArgument)) {
				return false;
			}
			//
			boolean notEquals = cgIsEqualExp.isNotEquals();
			js.appendDeclaration(cgIsEqualExp);
			js.append(" = ");
			if (cgSource.isNull()) {
				if (cgArgument.isNull()) {
					js.appendBooleanString(true ^ notEquals);
				}
				else if (cgArgument.isNonNull()) {
					js.appendBooleanString(false ^ notEquals);
				}
				else {
					js.appendValueName(cgArgument);
					js.append(notEquals ? " != " : " == ");
					js.append("null");
				}
			}
			else if (cgArgument.isNull()) {
				if (cgSource.isNonNull()) {
					js.appendBooleanString(false ^ notEquals);
				}
				else {
					js.appendValueName(cgSource);
					js.append(notEquals ? " != " : " == ");
					js.append("null");
				}
			}
			else if (cgSource.isTrue()) {
				if (cgArgument.isTrue()) {
					js.appendBooleanString(true ^ notEquals);
				}
				else if (cgArgument.isFalse()) {
					js.appendBooleanString(false ^ notEquals);
				}
				else {
					js.appendBooleanValueName(cgArgument, true ^ notEquals);
				}
			}
			else if (cgSource.isFalse()) {
				if (cgArgument.isFalse()) {
					js.appendBooleanString(true ^ notEquals);
				}
				else if (cgArgument.isTrue()) {
					js.appendBooleanString(false ^ notEquals);
				}
				else {
					js.appendBooleanValueName(cgArgument, false ^ notEquals);
				}
			}
			else if (cgArgument.isTrue()) {
				js.appendBooleanValueName(cgSource, true ^ notEquals);
			}
			else if (cgArgument.isFalse()) {
				js.appendBooleanValueName(cgSource, false ^ notEquals);
			}
			else {
				TypeDescriptor sourceTypeDescriptor = context.getTypeDescriptor(cgSource);
				sourceTypeDescriptor.appendEqualsValue(js, cgSource, cgArgument, notEquals);
			}
			js.append(";\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGIsEqual2Exp(@NonNull CGIsEqual2Exp cgIsEqualExp) {		// FIXME BUG 421738 move irregulaties to e.g. BooleanPrimitiveDescriptor
		if (cgIsEqualExp.isTrue()) {
			js.appendAssignBooleanLiteral(false, cgIsEqualExp, true);
		}
		else if (cgIsEqualExp.isFalse()) {
			js.appendAssignBooleanLiteral(false, cgIsEqualExp, false);
		}
		else {
			CGValuedElement cgSource = getExpression(cgIsEqualExp.getSource());
			CGValuedElement cgArgument = getExpression(cgIsEqualExp.getArgument());
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			if (!js.appendLocalStatements(cgArgument)) {
				return false;
			}
			//
			js.appendDeclaration(cgIsEqualExp);
			js.append(" = ");
			boolean isNull1 = cgSource.isNull();
			boolean isNull2 = cgArgument.isNull();
			if (isNull1 && isNull2) {
				js.appendBooleanString(isNull1 == isNull2);
			}
			else if (isNull1 && !isNull2) {
				if (cgArgument.isNonNull()) {
					js.appendBooleanString(false);
				}
				else {
					js.appendValueName(cgArgument);
					js.append(" == ");
					js.append("null");
				}
			}
			else if (isNull2 && !isNull1) {
				if (cgSource.isNonNull()) {
					js.appendBooleanString(false);
				}
				else {
					js.appendValueName(cgSource);
					js.append(" == ");
					js.append("null");
				}
			}
			else if (cgSource.isTrue()) {
				if (cgArgument.isTrue()) {
					js.appendBooleanString(true);
				}
				else if (cgArgument.isFalse()) {
					js.appendBooleanString(false);
				}
				else {
					js.appendBooleanValueName(cgArgument, true);
				}
			}
			else if (cgSource.isFalse()) {
				if (cgArgument.isFalse()) {
					js.appendBooleanString(true);
				}
				else if (cgArgument.isTrue()) {
					js.appendBooleanString(false);
				}
				else {
					js.appendBooleanValueName(cgArgument, false);
				}
			}
			else if (cgArgument.isTrue()) {
				js.appendBooleanValueName(cgSource, true);
			}
			else if (cgArgument.isFalse()) {
				js.appendBooleanValueName(cgSource, false);
			}
			else {
				TypeDescriptor sourceTypeDescriptor = context.getTypeDescriptor(cgSource);
				sourceTypeDescriptor.appendEqualsValue(js, cgSource, cgArgument, false);
			}
			js.append(";\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGIsInvalidExp(@NonNull CGIsInvalidExp cgIsInvalidExp) {
		if (cgIsInvalidExp.isTrue()) {
			js.appendTrue();
		}
		else if (cgIsInvalidExp.isFalse()) {
			js.appendFalse();
		}
		else {
			CGValuedElement cgSource = getExpression(cgIsInvalidExp.getSource());
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			//
			js.appendDeclaration(cgIsInvalidExp);
			js.append(" = ");
			js.appendValueName(cgSource);
			js.append(" instanceof ");
			js.appendClassReference(null, InvalidValueException.class);
			js.append(";\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGIsKindOfExp(@NonNull CGIsKindOfExp cgIsKindOfExp) {
		CGValuedElement cgSource = getExpression(cgIsKindOfExp.getSource());
		CGExecutorType cgType = cgIsKindOfExp.getExecutorType();
		if (cgType != null) {
			TypeId asTypeId = cgType.getASTypeId();
			assert asTypeId != null;
			TypeDescriptor typeDescriptor = context.getBoxedDescriptor(asTypeId);
			js.appendDeclaration(cgIsKindOfExp);
			js.append(" = ");
			js.appendValueName(cgSource);
			js.append(" instanceof ");
			typeDescriptor.append(js, null);
			js.append(";\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGIsUndefinedExp(@NonNull CGIsUndefinedExp cgIsUndefinedExp) {
		if (cgIsUndefinedExp.isTrue()) {
			js.appendTrue();
		}
		else if (cgIsUndefinedExp.isFalse()) {
			js.appendFalse();
		}
		else {
			CGValuedElement cgSource = getExpression(cgIsUndefinedExp.getSource());
			boolean sourceIsNonInvalid = cgSource.isNonInvalid();
			boolean sourceIsNonNull = cgSource.isNonNull();
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			//
			js.appendDeclaration(cgIsUndefinedExp);
			js.append(" = ");
			if (!sourceIsNonNull && !sourceIsNonInvalid) {
				js.append("(");
				js.appendValueName(cgSource);
				js.append(" == null) || (");
				js.appendValueName(cgSource);
				js.append(" instanceof ");
				js.appendClassReference(null, InvalidValueException.class);
				js.append(")");
			}
			else if (!sourceIsNonNull && sourceIsNonInvalid) {
				js.appendValueName(cgSource);
				js.append(" == null");
			}
			else if (sourceIsNonNull && !sourceIsNonInvalid) {
				js.appendValueName(cgSource);
				js.append(" instanceof ");
				js.appendClassReference(null, InvalidValueException.class);
			}
			js.append(";\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGLetExp(@NonNull CGLetExp cgLetExp) {
		cgLetExp.getInit().accept(this);
		CGValuedElement cgIn = CGUtil.getIn(cgLetExp);
		if (!js.appendLocalStatements(cgIn)) {
			return false;
		}
		// The following fallback would not be required if the inner name propagated better, see testBug458724
		// (a rewrite of an in might fail to re-down-propagate the let name).
		NameResolution inNameResolution = cgIn.getNameResolution();
		NameResolution letNameResolution = cgLetExp.getNameResolution();
		if (inNameResolution != letNameResolution) {
			js.appendDeclaration(cgLetExp);
			js.append(" = ");
			js.appendValueName(cgIn);
			js.append(";\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGLibraryIterateCallExp(@NonNull CGLibraryIterateCallExp cgIterateCallExp) {
		return appendLoopCall(cgIterateCallExp, cgIterateCallExp.getResult());
	}

	@Override
	public @NonNull Boolean visitCGLibraryIterationCallExp(@NonNull CGLibraryIterationCallExp cgIterationCallExp) {
		return appendLoopCall(cgIterationCallExp, null);
	}

/*	@Override
	public @NonNull Boolean visitCGLibraryOperation(@NonNull CGLibraryOperation cgOperation) {
		localContext = context.getLocalContext(cgOperation);
		try {
			List<CGParameter> cgParameters = cgOperation.getParameters();
			String operationName = JavaConstants.FOREIGN_OPERATION_PREFIX + cgOperation.getName();
			assert operationName != null;
			js.appendCommentWithOCL(null, cgOperation.getAst());
		//	assert false;		// XXX
			js.append("public static class ");
			js.append(operationName);
			js.append(" extends ");
			js.appendClassReference(null, genModelHelper.getAbstractOperationClass(cgParameters.size()-3)); // executor, typeId, self
			js.pushClassBody(operationName);
			js.append("public static final ");
			js.appendIsRequired(true);
			js.append(" ");
			js.append(operationName);
			js.append(" ");
			js.append(context.getInstanceName());
			js.append(" = new ");
			js.append(operationName);
			js.append("();\n");
			js.append("\n");
			//				js.append("public static final ");
			//				CGValuedElement evaluatorParameter = localContext2.getEvaluatorParameter(cgOperation);
			//				CGParameter typeIdParameter = localContext2.getTypeIdParameter(cgOperation);
			CGValuedElement body = getExpression(cgOperation.getBody());
			//
			Element ast = cgOperation.getAst();
			if (ast instanceof Operation) {
				LanguageExpression expressionInOCL = ((Operation)ast).getBodyExpression();
			//	if (ast instanceof Operation) {
			//		String title = PrettyPrinter.printName(ast);
					js.appendCommentWithOCL(null/ *title+"\n"* /, expressionInOCL);
			//	}
			}
			//
			js.append("@Override\n");
			js.append("public ");
			boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
			js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
			js.append(" ");
			js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
			js.append(" ");
			js.append(globalContext.getEvaluateName());
			js.append("(");
			boolean isFirst = true;
			for (@SuppressWarnings("null")@NonNull CGParameter cgParameter : cgParameters) {
				if (!isFirst) {
					js.append(", ");
				}
				js.appendDeclaration(cgParameter);
				isFirst = false;
			}
			js.append(") {\n");
			js.pushIndentation(null);
			appendReturn(body);
			js.popIndentation();
			js.append("}\n");
			js.popClassBody(false);
		}
		finally {
			localContext = null;
		}
		return true;
	} */

	@Override
	public @NonNull Boolean visitCGMapExp(@NonNull CGMapExp cgMapExp) {
		for (CGMapPart cgPart : cgMapExp.getParts()) {
			if ((cgPart != null) && !js.appendLocalStatements(cgPart)) {
				return false;
			}
		}
		js.appendDeclaration(cgMapExp);
		js.append(" = ");
		js.appendClassReference(null, ValueUtil.class);
		js.append(".createMapOfEach(");
		//		CGTypeVariable typeVariable = localContext.getTypeVariable(cgMapExp.getTypeId());
		js.appendIdReference(cgMapExp.getTypeId().getElementId());
		for (CGMapPart cgPart : cgMapExp.getParts()) {
			js.append(", ");
			if (cgPart.isNull() && (cgMapExp.getParts().size() == 1)) {
				js.append("(Object)");
			}
			js.appendValueName(cgPart);
		}
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGMapPart(@NonNull CGMapPart cgMapPart) {
		CGValuedElement key = getExpression(cgMapPart.getKey());
		CGValuedElement value = getExpression(cgMapPart.getValue());
		if (!js.appendLocalStatements(key)) {
			return false;
		}
		if (!js.appendLocalStatements(value)) {
			return false;
		}
		js.appendDeclaration(cgMapPart);
		js.append(" = ");
		js.appendClassReference(null, ValueUtil.class);
		js.append(".createMapEntry(");
		js.appendValueName(key);
		js.append(", ");
		js.appendValueName(value);
		js.append(");\n");
		return true;
	}

/*	@Override		// FIXME revert to the pre-cached code
	public @NonNull Boolean visitCGNativeOperation(@NonNull CGNativeOperation cgOperation) {
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		localContext = globalContext.findLocalContext(cgOperation);
		try {
			String operationClassName = getNativeOperationClassName(cgOperation);
			LanguageExpression expressionInOCL = asOperation.getBodyExpression();
			String title = PrettyPrinter.printName(asOperation);
			js.appendCommentWithOCL(title+"\n", expressionInOCL);
			//
			js.append("protected class ");
			js.append(operationClassName);
			js.append(" extends ");
			js.appendClassReference(null, AbstractEvaluationOperation.class);
			js.pushClassBody(operationClassName);
			doCachedOperationBasicEvaluate(cgOperation);
			js.append("\n");
			doCachedOperationEvaluate(cgOperation);
			js.popClassBody(false);
			//
			js.append("\n");
			doCachedOperationClassInstance(cgOperation);
		}
		finally {
			localContext = null;
		}
		return true;
	} */

	@Override
	public @NonNull Boolean visitCGNavigationCallExp(@NonNull CGNavigationCallExp cgGNavigationCallExp) {
		return cgGNavigationCallExp.getCgProperty().getCallingConvention().generateJavaCall(this, js, cgGNavigationCallExp);
	/*	if (cgGNavigationCallExp instanceof CGEcorePropertyCallExp) {
			return EcorePropertyCallingConvention.INSTANCE.generateJavaCall(this, js, cgGNavigationCallExp);
		}
		if (cgGNavigationCallExp instanceof CGExecutorOppositePropertyCallExp) {
			return ExecutorPropertyCallingConvention.INSTANCE.generateJavaCall(this, js, cgGNavigationCallExp);
		}
		if (cgGNavigationCallExp instanceof CGExecutorPropertyCallExp) {
			return ExecutorPropertyCallingConvention.INSTANCE.generateJavaCall(this, js, cgGNavigationCallExp);
		}
		if (cgGNavigationCallExp instanceof CGForeignPropertyCallExp) {
			return ForeignPropertyCallingConvention.INSTANCE.generateJavaCall(this, js, cgGNavigationCallExp);
		}
		if (cgGNavigationCallExp instanceof CGLibraryPropertyCallExp) {
			return LibraryPropertyCallingConvention.INSTANCE.generateJavaCall(this, js, cgGNavigationCallExp);
		}
		if (cgGNavigationCallExp instanceof CGNativePropertyCallExp) {
			return NativePropertyCallingConvention.INSTANCE.generateJavaCall(this, js, cgGNavigationCallExp);
		}
		if (cgGNavigationCallExp instanceof CGTuplePartCallExp) {
			return TuplePropertyCallingConvention.INSTANCE.generateJavaCall(this, js, cgGNavigationCallExp);
		}
		return super.visitCGNavigationCallExp(cgGNavigationCallExp); */
	}

	@Override
	public @NonNull Boolean visitCGNull(@NonNull CGNull object) {
		js.append("null");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGOperation(@NonNull CGOperation cgOperation) {
		localContext = globalNameManager.findLocalContext(cgOperation);
		try {
			OperationCallingConvention callingConvention = cgOperation.getCallingConvention();
			callingConvention.generateJavaDeclaration(this, js, cgOperation);
		}
		finally {
			localContext = null;
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGOperationCallExp(@NonNull CGOperationCallExp cgOperationCallExp) {
		CGOperation cgOperation = cgOperationCallExp.getCgOperation();
		OperationCallingConvention callingConvention = cgOperation.getCallingConvention();
		return callingConvention.generateJavaCall(this, js, cgOperationCallExp);
	}

	@Override
	public @NonNull Boolean visitCGPackage(@NonNull CGPackage cgPackage) {
		for (CGPackage cgNestedPackage : cgPackage.getPackages()) {
			cgNestedPackage.accept(this);
		}
		for (CGClass cgClass : cgPackage.getClasses()) {
			cgClass.accept(this);
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGParameter(@NonNull CGParameter object) {
		return true;			// Parameters are declared by their Operation
	}

	@Override
	public @NonNull Boolean visitCGProperty(@NonNull CGProperty cgProperty) {
		return cgProperty.getCallingConvention().generateJavaDeclaration(this, js, cgProperty);
	}

	@Override
	public @NonNull Boolean visitCGReal(@NonNull CGReal object) {
		js.appendDeclaration(object);
		js.append(" = ");
		js.appendClassReference(null, ValueUtil.class);
		js.append(".realValueOf(");
		Number realValue = object.getNumericValue();
		String valueString = realValue.toString();
		if (realValue instanceof Double) {
			js.append(valueString + "d");
		}
		else {
			js.append("\"" + valueString + "\"");
		}
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGShadowExp(@NonNull CGShadowExp cgShadowExp) {
		/*
		CodeGenAnalysis analysis = context.getAnalysis(element);
		if (analysis.isConstant()) {
			return context.getSnippet(analysis.getConstantValue());
		}
		final Type type = ClassUtil.nonNullModel(element.getTypeId());
		final Class<?> resultClass = Object.class; //context.getBoxedClass(element.getTypeId());
		int flags = CodeGenSnippet.NON_NULL | CodeGenSnippet.UNBOXED;
		if (/*isValidating* / analysis.isCatching()) {
			flags |= CodeGenSnippet.CAUGHT | CodeGenSnippet.MUTABLE;
		}
		else { //if (/*isValidating* / analysis.isThrowing()) {
			flags |= CodeGenSnippet.THROWN;
		}
//		else {
//			flags |= CodeGenSnippet.FINAL;
//		}
		CodeGenSnippet snippet = new JavaSnippet("", analysis, resultClass, flags);
		snippet = snippet.appendText("", new AbstractTextAppender()
		{
			@Override
			public void appendToBody(@NonNull CodeGenText text) {
//				text.append("(");
//				text.appendClassReference(null, EObject.class);
//				text.append(")");
//				text.appendClassReference(null, ObjectValue.class);
//				text.append(")");
		 */
		CGExecutorType cgExecutorType = cgShadowExp.getExecutorType();
		//
		js.appendDeclaration(cgShadowExp);
		js.append(" = ");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendValueName(cgExecutorType);
				js.append(".createInstance()");
			}
		};
		js.appendClassCast(cgShadowExp, castBody);
		js.append(";\n");
		for (CGShadowPart part : cgShadowExp.getParts()) {
			part.accept(this);
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGShadowPart(@NonNull CGShadowPart cgShadowPart) {
		/*		final OCLExpression initExpression = ClassUtil.nonNullModel(element.getInitExpression());
		final Property referredProperty = ClassUtil.nonNullModel(element.getReferredProperty());
		ShadowExp eContainer = (ShadowExp)element.eContainer();
		final CodeGenSnippet instanceSnippet = context.getSnippet(eContainer);
		Class<?> resultClass = Object.class; //context.getBoxedClass(element.getTypeId());
		CodeGenSnippet snippet = new JavaSnippet("", context, TypeId.OCL_INVALID, resultClass, element,
			CodeGenSnippet.THROWN | CodeGenSnippet.UNASSIGNED | CodeGenSnippet.UNBOXED);
		return snippet.appendText("", new AbstractTextAppender()
		{
			private CodeGenSnippet initSnippet;

			@Override
			public boolean appendAtHead(@NonNull CodeGenSnippet snippet) {
				initSnippet = snippet.appendUnboxedGuardedChild(initExpression, null, DomainMessage.INVALID);
				return true;
			}

			@Override
			public void appendToBody(@NonNull CodeGenText text) { */
		//		appendReferenceTo(context.getSnippet(referredProperty));
		CGExecutorShadowPart cgExecutorShadowPart = cgShadowPart.getExecutorPart();
		CGValuedElement init = getExpression(cgShadowPart.getInit());
		//
		if (!js.appendLocalStatements(init)) {
			return false;
		}
		//
		js.appendValueName(cgExecutorShadowPart);			// XXX use super
		js.append(".initValue(");
		js.appendValueName(cgShadowPart.getShadowExp());
		js.append(", ");
		js.appendValueName(init);
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGString(@NonNull CGString object) {
		js.appendDeclaration(object);
		js.append(" = ");
		js.appendString(ClassUtil.nonNullState(object.getStringValue()));
		js.append(";\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGTemplateParameterExp(@NonNull CGTemplateParameterExp cgTemplateParameterExp) {
		CGValuedElement cgType = getExpression(cgTemplateParameterExp.getTemplateableElement());
		js.appendDeclaration(cgTemplateParameterExp);
		js.append(" = ");
		js.appendReferenceTo(cgType);
		js.append(".getOwnedSignature().getOwnedParameters().get(" + cgTemplateParameterExp.getIndex() + ");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGThrowExp(@NonNull CGThrowExp cgThrowExp) {
		CGValuedElement cgSource = getExpression(cgThrowExp.getSource());
		CGInvalid cgInvalidValue;
		if (cgSource.isNonInvalid()) {
			cgSource.accept(this);
		}
		else if ((cgInvalidValue = cgSource.getInvalidValue()) != null) {
			js.append("throw ");
			js.appendReferenceTo(InvalidValueException.class, cgInvalidValue);
			js.append(";\n");
			return false;
		}
		else {
			if (!js.appendLocalStatements(cgSource)) {
				return false;
			}
			if (cgSource.isCaught()) {
				js.append("if (");
				js.appendValueName(cgSource);
				js.append(" instanceof ");
				js.appendClassReference(null, InvalidValueException.class);
				js.append(") {\n");
				js.pushIndentation(null);
				js.append("throw ");
				js.appendReferenceTo(InvalidValueException.class, cgSource);
				js.append(";\n");
				js.popIndentation();
				js.append("}\n");
			}
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGTupleExp(@NonNull CGTupleExp cgTupleExp) {
		Iterable<@NonNull CGTuplePart> parts = CGUtil.getParts(cgTupleExp);
		for (@NonNull CGTuplePart cgPart : parts) {
			if (!js.appendLocalStatements(CGUtil.getInit(cgPart))) {
				return false;
			}
		}
		js.appendDeclaration(cgTupleExp);
		js.append(" = ");
		js.appendClassReference(null, ValueUtil.class);
		js.append(".createTupleOfEach(");
		js.appendIdReference(cgTupleExp.getTypeId().getElementId());
		int iSize = Iterables.size(parts);
		for (@NonNull CGTuplePart cgPart : parts) {
			CGValuedElement cgInit = CGUtil.getInit(cgPart);
			js.append(", ");
			if ((cgInit.isNull()) && (iSize == 1)) {
				js.append("(Object)");						// Disambiguate Object... from Object[]
			}
			js.appendValueName(cgInit);
		}
		js.append(");\n");
		return true;
	}

	//	@Override
	//	public @NonNull Boolean visitCGTuplePart(@NonNull CGTuplePart cgTuplePart) {
	//		js.appendLocalStatements(cgTuplePart.getInit());
	//		return true;
	//	}

	@Override
	public @NonNull Boolean visitCGTypeId(@NonNull CGTypeId cgTypeId) {
		if (cgTypeId.isInlined()) {
			js.appendIdReference(cgTypeId.getElementId());
		}
		else {
			super.visitCGTypeId(cgTypeId);
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGTypeExp(@NonNull CGTypeExp cgTypeExp) {
		//		getTypeVariable(cgTypeExp.getReferredType());
		//		CGExecutorType type = cgTypeExp.getExecutorType();
		//		if (type != null) {
		//			type.accept(this);
		//		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGUnboxExp(@NonNull CGUnboxExp cgUnboxExp) {
		CGValuedElement boxedValue = getExpression(cgUnboxExp.getSource());
		TypeDescriptor boxedTypeDescriptor = context.getTypeDescriptor(boxedValue);
		JavaLocalContext localContext2 = localContext;
		assert localContext2 != null;
		//
		if (!js.appendLocalStatements(boxedValue)) {
			return false;
		}
		return boxedTypeDescriptor.appendUnboxStatements(js, localContext2, cgUnboxExp, boxedValue);
	}

	@Override
	public @NonNull Boolean visitCGUnlimited(@NonNull CGUnlimited object) {
		js.appendClassReference(null, ValueUtil.class);
		js.append(".UNLIMITED_VALUE");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGVariable(@NonNull CGVariable cgVariable) {
		CGValuedElement cgInit = cgVariable.getInit();
		if (cgInit != null) {
			if (!js.appendLocalStatements(cgInit)) {
				return false;
			}
			if (!cgInit.isGlobal()) {
				// The following fallback would not be required if the init name propagated better, see testSysML_QUDV
				// (a rewrite of an init might fail to re-down-propagate the variable name).
				NameResolution varNameResolution = cgVariable.getNameResolution();
			//	if (varNameResolution == varNameResolution.getNameResolution()) {			// A variant did not need propagating.
					NameResolution initNameResolution = cgInit.getNameResolution();//.getNameResolution();
					if (varNameResolution != initNameResolution) {
						boolean hasAccess = false;
						assert false;
						Iterable<@NonNull CGValuedElement> cgElements = varNameResolution.getCGElements();
						if (cgElements != null) {
							for (CGValuedElement cgElement : cgElements) {
								if (cgElement instanceof CGVariableExp) {
									CGPackage cgPackage = CGUtil.basicGetContainingPackage(cgElement);
									if (cgPackage != null) {
										hasAccess = true;
										break;
									}
								}
							}
						}
						if (hasAccess) {
							js.appendDeclaration(cgVariable);
							js.append(" = ");
							js.appendValueName(cgInit);
							js.append(";\n");
						}
					}
			//	}
			}
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGVariableExp(@NonNull CGVariableExp cgVariableExp) {
		//		CGValuedElement variable = cgVariableExp.getReferredVariable();
		//		if (variable != null) {
		//			variable.accept(this);
		//		}
		return true;
	}
}
