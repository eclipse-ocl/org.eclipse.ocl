/*******************************************************************************
 * Copyright (c) 2013, 2021 CEA LIST and others.
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
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGAssertNonNullExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoolean;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBoxExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBuiltInIterationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCachedOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCastExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCatchExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGClass;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCollectionPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstantExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGConstraint;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreDataTypeShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorCompositionProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
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
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGMapPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGPackage;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGReal;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGShadowPart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGString;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTemplateParameterExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGText;
import org.eclipse.ocl.examples.codegen.cgmodel.CGThrowExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTupleExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePart;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTuplePartCallExp;
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
import org.eclipse.ocl.pivot.LanguageExpression;
import org.eclipse.ocl.pivot.LoopExp;
import org.eclipse.ocl.pivot.MapType;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.Type;
import org.eclipse.ocl.pivot.TypedElement;
import org.eclipse.ocl.pivot.Variable;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.evaluation.IterationManager;
import org.eclipse.ocl.pivot.ids.ClassId;
import org.eclipse.ocl.pivot.ids.CollectionTypeId;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.ids.EnumerationId;
import org.eclipse.ocl.pivot.ids.EnumerationLiteralId;
import org.eclipse.ocl.pivot.ids.MapTypeId;
import org.eclipse.ocl.pivot.ids.NestedTypeId;
import org.eclipse.ocl.pivot.ids.TuplePartId;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.library.executor.AbstractDispatchOperation;
import org.eclipse.ocl.pivot.internal.library.executor.AbstractEvaluationOperation;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorMultipleIterationManager;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorMultipleMapIterationManager;
import org.eclipse.ocl.pivot.internal.library.executor.ExecutorSingleIterationManager;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.prettyprint.PrettyPrinter;
import org.eclipse.ocl.pivot.internal.utilities.EnvironmentFactoryInternal;
import org.eclipse.ocl.pivot.internal.values.IntIntegerValueImpl;
import org.eclipse.ocl.pivot.internal.values.LongIntegerValueImpl;
import org.eclipse.ocl.pivot.library.AbstractBinaryOperation;
import org.eclipse.ocl.pivot.library.AbstractSimpleOperation;
import org.eclipse.ocl.pivot.library.LibraryIteration;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.library.LibrarySimpleOperation;
import org.eclipse.ocl.pivot.library.LibraryUntypedOperation;
import org.eclipse.ocl.pivot.library.oclany.OclElementOclContainerProperty;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.ValueUtil;
import org.eclipse.ocl.pivot.values.CollectionValue;
import org.eclipse.ocl.pivot.values.InvalidValueException;
import org.eclipse.ocl.pivot.values.MapValue;
import org.eclipse.ocl.pivot.values.TemplateParameterSubstitutions;
import org.eclipse.ocl.pivot.values.TupleValue;

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

	protected final @NonNull JavaGlobalContext<@NonNull ?> globalContext;
	protected final @NonNull GenModelHelper genModelHelper;
	protected final @NonNull CodeGenAnalyzer analyzer;
	protected final @NonNull EnvironmentFactoryInternal environmentFactory;
	protected final @NonNull Id2JavaInterfaceVisitor id2JavaInterfaceVisitor;
	protected final @NonNull JavaStream js;
	protected final @NonNull Map<@NonNull Class<? extends LibraryOperation>, @NonNull LibraryOperationHandler> libraryOperation2handler = new HashMap<>();;

	/**
	 * The local Java context for the current operation.
	 */
	protected JavaLocalContext<@NonNull ?> localContext;

	public CG2JavaVisitor(@NonNull CG codeGenerator) {
		super(codeGenerator);
		this.globalContext = codeGenerator.getGlobalContext();
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
		return globalContext.addImport(isRequired, className);
	}

	/**
	 * Append the code for an EcorePropertyCall. If source is null, the code for the source will also be appended.
	 * If source is non-null the caller has already appended it.
	 */
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
		//		js.append("/* " + ecoreIsRequired + " " + isRequired + " */\n");
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		appendEcoreGet(source, asProperty);
		js.append(";\n");
		return true;
	}

	protected void appendEcoreGet(@NonNull CGValuedElement cgSource, @NonNull Property asProperty) {
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
	}

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
		final Operation referredOperation = ((LoopExp)cgIterationCallExp.getAst()).getReferredIteration();
		final int arity = iterators.size();
		Type sourceType = ((CallExp)CGUtil.getAST(cgIterationCallExp)).getOwnedSource().getType();
		boolean isMap = sourceType instanceof MapType;
		final Class<?> managerClass; 	// FIXME ExecutorMultipleIterationManager
		final Class<?> operationClass;
		final boolean passesCoIterators;
		if (isMap) {
			managerClass = ExecutorMultipleMapIterationManager.class;
			operationClass = AbstractSimpleOperation.class;
			passesCoIterators = true;
		}
		else if ((arity == 1) && (coIterators.size() == 0)) {
			managerClass = ExecutorSingleIterationManager.class;
			operationClass = AbstractBinaryOperation.class;
			passesCoIterators = false;
		}
		else {
			managerClass = ExecutorMultipleIterationManager.class;
			operationClass = AbstractSimpleOperation.class;
			passesCoIterators = true;
		}
		final LibraryIteration libraryIteration = ClassUtil.nonNullState(cgIterationCallExp.getLibraryIteration());
		final Method actualMethod = getJavaMethod(libraryIteration);
		final Class<?> actualReturnClass = actualMethod != null ? actualMethod.getReturnType() : null;
		boolean actualIsNonNull = (actualMethod != null) && (context.getIsNonNull(actualMethod) == Boolean.TRUE);
		boolean expectedIsNonNull = cgIterationCallExp.isNonNull();
		final String astName = getSymbolName(null, cgIterationCallExp.getValueName());
		final String bodyName = getSymbolName(null, "BODY_" + astName);
		final String implementationName = getSymbolName(null, "IMPL_" + astName);
		final String managerName = getSymbolName(null, "MGR_" + astName);
		final String staticTypeName = getSymbolName(null, "TYPE_" + astName);
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
		js.append(JavaConstants.EXECUTOR_NAME);
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
		js.appendReferenceTo(localContext.getStandardLibraryVariable(cgIterationCallExp));
		js.append(", ");
		js.appendQualifiedLiteralName(ClassUtil.nonNullState(referredOperation));
		js.append(");\n");
		//
		if (iterateResult != null) {
			CGValuedElement init = iterateResult.getInit();
			accumulatorName = getSymbolName(null, init.getValueName());
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
			js.append(JavaConstants.EXECUTOR_NAME);
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
		js.append(" Object evaluate(");
		js.append("final ");
		//		js.appendDeclaration(evaluatorParameter);
		js.appendClassReference(true, Executor.class);
		js.append(" ");
		js.append(JavaConstants.EXECUTOR_NAME);
		js.append(", ");
		js.append("final ");
		//		js.appendDeclaration(localContext.getTypeIdParameter(cgIterateCallExp));
		js.appendClassReference(true, TypeId.class);
		js.append(" ");
		js.append(JavaConstants.TYPE_ID_NAME);
		if (isMap || (arity > 1)) {
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
		if (isMap || (arity > 1)) {
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
		JavaLocalContext<@NonNull ?> savedLocalContext = localContext;
		try {
			localContext = globalContext.getLocalContext(cgIterationCallExp);
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
		js.append(JavaConstants.EXECUTOR_NAME);
		js.append(", ");
		if (isMap || (arity > 1)) {
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

	protected void appendReturn(@NonNull CGValuedElement body) {
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

	protected void appendSuppressWarningsNull(@NonNull CGValuedElement cgActual, Boolean isNonNull) {
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

	protected void doCachedOperationDispatchInstaller(@NonNull CGCachedOperation cgOperation) {
		js.append("private ");
		js.append(getNativeOperationClassName(cgOperation));
		js.append("() {\n");
		js.pushIndentation(null);
		for (@NonNull CGCachedOperation cgFinalOperation : ClassUtil.nullFree(cgOperation.getFinalOperations())) {
			Operation asFinalOperation = (Operation)cgFinalOperation.getAst();
			assert asFinalOperation != null;
			js.append("install(");
			js.appendClassReference(null, cgFinalOperation.getParameters().get(0));
			js.append(".class, ");
			js.append(getNativeOperationDirectInstanceName(asFinalOperation));
			js.append(");\n");
		}
		js.popIndentation();
		js.append("}\n");
	}

	protected void doCachedOperationBasicEvaluate(@NonNull CGOperation cgOperation) {
		List<@NonNull CGParameter> cgParameters = ClassUtil.nullFree(cgOperation.getParameters());
		CGValuedElement body = getExpression(cgOperation.getBody());
		js.append("@Override\n");
		js.append("public ");
		//				boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		//				js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		js.appendClassReference(false, Object.class);
		js.append(" basicEvaluate(");
		js.appendClassReference(true, Executor.class);
		js.append(" ");
		js.append(JavaConstants.EXECUTOR_NAME);
		js.append(", ");
		js.appendClassReference(true, TypedElement.class);
		js.append(" ");
		js.append("caller");
		js.append(", ");
		js.appendClassReference(false, Object.class);
		js.append(" ");
		js.appendIsRequired(true);
		js.append(" [] ");
		js.append(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
		js.append(") {\n");
		js.pushIndentation(null);
		int i = 0;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			if (cgParameter.getASTypeId() instanceof CollectionTypeId) {
				js.append("@SuppressWarnings(\"unchecked\") ");
			}
			else if (cgParameter.isRequired()) {
				if (js.appendSuppressWarningsNull(false)) {
					js.append(" ");
				}
			}
			js.appendDeclaration(cgParameter);
			js.append(" = (");
			js.appendTypeDeclaration(cgParameter);
			js.append(")");
			js.append(JavaConstants.SOURCE_AND_ARGUMENT_VALUES_NAME);
			js.append("[" + i++ + "];\n");
		}
		appendReturn(body);
		js.popIndentation();
		js.append("}\n");
	}

	protected void doCachedOperationClassDirectInstance(@NonNull CGOperation cgOperation) {
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		String name = getNativeOperationClassName(cgOperation);
		js.append("protected final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(name);
		js.append(" ");
		js.append(getNativeOperationDirectInstanceName(asOperation));
		js.append(" = new ");
		js.append(name);
		js.append("();\n");
	}

	protected void doCachedOperationClassInstance(@NonNull CGOperation cgOperation) {
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		String name = getNativeOperationClassName(cgOperation);
		js.append("protected final ");
		js.appendIsRequired(true);
		js.append(" ");
		js.append(name);
		js.append(" ");
		js.append(getNativeOperationInstanceName(asOperation));
		js.append(" = new ");
		js.append(name);
		js.append("();\n");
	}

	protected void doCachedOperationEvaluate(@NonNull CGOperation cgOperation) {
		List<@NonNull CGParameter> cgParameters = ClassUtil.nullFree(cgOperation.getParameters());
		Boolean isRequiredReturn = cgOperation.isRequired() ? true : null;
		if (cgOperation.isEcore() && (cgOperation.getASTypeId() instanceof CollectionTypeId)) {
			js.append("@SuppressWarnings(\"unchecked\")\n");
		}
		else if ((isRequiredReturn == Boolean.TRUE)) {
			js.appendSuppressWarningsNull(true);
		}
		js.append("public ");
		//				boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
		//				js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
		//				js.append(" ");
		js.appendClassReference(isRequiredReturn, cgOperation);
		js.append(" evaluate(");
		boolean isFirst = true;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendDeclaration(cgParameter);
			isFirst = false;
		}
		js.append(") {\n");
		js.pushIndentation(null);
		js.append("return (");
		js.appendClassReference(isRequiredReturn, cgOperation);
		js.append(")");
		js.append(JavaConstants.EVALUATION_CACHE_NAME);
		js.append(".getCachedEvaluationResult(this, caller, new ");
		js.appendClassReference(false, Object.class);
		js.append("[]{");
		isFirst = true;
		for (@NonNull CGParameter cgParameter : cgParameters) {
			if (!isFirst) {
				js.append(", ");
			}
			js.appendValueName(cgParameter);
			isFirst = false;
		}
		js.append("});\n");
		js.popIndentation();
		js.append("}\n");
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

	protected @Nullable EStructuralFeature getESObject(@NonNull Property asProperty) {
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
		return analyzer.getExpression(cgExpression);
	}

	public @NonNull GenModelHelper getGenModelHelper() {
		return genModelHelper;
	}

	public @Nullable GenPackage getGenPackage() {
		return null;
	}

	public @NonNull ImportNameManager getImportNameManager() {
		return globalContext.getImportNameManager();
	}

	private Method getJavaMethod(@NonNull LibraryIteration libraryIteration) {
		try {
			Class<? extends LibraryIteration> implementationClass = libraryIteration.getClass();
			Method method = implementationClass.getMethod("evaluateIteration", IterationManager.class);
			return method;
		} catch (Exception e) {
			return null;
		}
	}

	private Method getJavaMethod(@NonNull LibraryOperation libraryOperation, int argumentSize) {
		try {
			Class<? extends LibraryOperation> implementationClass = libraryOperation.getClass();
			Class<?>[] arguments;
			int i = 0;
			if (libraryOperation instanceof LibrarySimpleOperation) {
				arguments = new Class<?>[argumentSize+1];
			}
			else if (libraryOperation instanceof LibraryUntypedOperation) {
				arguments = new Class<?>[argumentSize+2];
				arguments[i++] = Executor.class;
			}
			else {
				arguments = new Class<?>[argumentSize+3];
				arguments[i++] = Executor.class;
				arguments[i++] = TypeId.class;
			}
			while (i < arguments.length) {
				arguments[i++] = Object.class;
			}
			Method method = implementationClass.getMethod("evaluate", arguments);
			return method;
		} catch (Exception e) {
			return null;
		}
	}

	private Method getJavaMethod(@NonNull LibraryProperty libraryProperty) {
		try {
			Class<? extends LibraryProperty> implementationClass = libraryProperty.getClass();
			Class<?>@NonNull [] arguments = new Class<?>@NonNull [3];
			arguments[0] = Executor.class;
			arguments[1] = TypeId.class;
			arguments[2] = Object.class;
			Method method = implementationClass.getMethod("evaluate", arguments);
			return method;
		} catch (Exception e) {
			return null;
		}
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

	protected @NonNull String getNativeOperationClassName(@NonNull CGOperation cgOperation) {	// FIXME unique
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		if (isVirtualDispatcher(cgOperation)) {
			return "VCACHE_" + getNativeOperationName(asOperation);
		}
		else {
			return "CACHE_" + getNativeOperationName(asOperation);
		}
	}

	protected @NonNull String getNativeOperationDirectInstanceName(@NonNull Operation asOperation) {	// FIXME unique
		return "INST_" + getNativeOperationName(asOperation);
	}

	protected @NonNull String getNativeOperationInstanceName(@NonNull Operation asOperation) {	// FIXME unique
		return "INSTANCE_" + getNativeOperationName(asOperation);
	}

	protected @NonNull String getNativeOperationName(@NonNull Operation asOperation) {	// FIXME unique
		return ClassUtil.nonNullState(asOperation.getOwningClass()).getName() + "_" + asOperation.getName();
	}

	protected @NonNull String getSymbolName(@Nullable Object anObject, @Nullable String... nameHints) {
		return localContext.getNameManagerContext().getSymbolName(anObject, nameHints);
	}

	@Deprecated /* @deprecated this is QVTi specific */
	protected @NonNull String getThisName(@NonNull CGElement cgElement) {
		for (EObject eObject = cgElement; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof CGClass) {
				return ClassUtil.nonNullState(((CGClass)eObject).getName());
			}
		}
		assert false;
		return "";
	}

	protected String getValueName(@NonNull CGValuedElement cgElement) {
		String valueName = localContext != null ? localContext.getValueName(cgElement) : globalContext.getValueName(cgElement);
		return valueName;
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

	protected boolean isEnumerationLiteral(@NonNull CGValuedElement cgValue) {
		Element ast = cgValue.getAst();
		if (!(ast instanceof TypedElement)) {
			return false;
		}
		Type type = ((TypedElement)ast).getType();
		return type instanceof Enumeration;
	}

	protected boolean isVirtualDispatcher(@NonNull CGOperation cgOperation) {
		return (cgOperation instanceof CGCachedOperation) && (((CGCachedOperation)cgOperation).getFinalOperations().size() > 0);
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
		JavaLocalContext<@NonNull ?>localContext2 = localContext;
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
		CGIterator cgIterator = cgIterationCallExp.getIterators().get(0);
		CGIterator cgCoIterator = cgIterationCallExp.getCoIterators().size() > 0 ? cgIterationCallExp.getCoIterators().get(0) : null;
		String iteratorName = getSymbolName(null, "ITERATOR_" + cgIterator.getValueName());
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
		return flowContinues;
	}

	@Override
	public @NonNull Boolean visitCGCastExp(@NonNull CGCastExp cgCastExp) {
		CGValuedElement cgSource = getExpression(cgCastExp.getSource());
		CGExecutorType cgType = cgCastExp.getExecutorType();
		if (cgType != null) {
			TypeDescriptor typeDescriptor = context.getTypeDescriptor(cgCastExp);
			js.appendDeclaration(cgCastExp);
			js.append(" = ");
			typeDescriptor.appendCastTerm(js, cgSource);
			js.append(";\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGCachedOperation(@NonNull CGCachedOperation cgOperation) {
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		JavaLocalContext<@NonNull ?> localContext2 = globalContext.getLocalContext(cgOperation);
		if (localContext2 != null) {
			localContext = localContext2;
			boolean isVirtualDispatcher = isVirtualDispatcher(cgOperation);
			try {
				String operationClassName = getNativeOperationClassName(cgOperation);
				if (isVirtualDispatcher) {
					js.append("protected class ");
					js.append(operationClassName);
					js.append(" extends ");
					js.appendClassReference(null, AbstractDispatchOperation.class);
					js.pushClassBody(operationClassName);
					doCachedOperationDispatchInstaller(cgOperation);
					js.append("\n");
					doCachedOperationEvaluate(cgOperation);
					js.popClassBody(false);
					//
					js.append("\n");
					doCachedOperationClassInstance(cgOperation);
				}
				else {
					LanguageExpression expressionInOCL = asOperation.getBodyExpression();
					String title = PrettyPrinter.printName(asOperation);
					js.appendCommentWithOCL(title+"\n", expressionInOCL);
					//
					js.append("public class ");
					js.append(operationClassName);
					js.append(" extends ");
					js.appendClassReference(null, AbstractEvaluationOperation.class);
					js.pushClassBody(operationClassName);
					doCachedOperationBasicEvaluate(cgOperation);
					js.append("\n");
					doCachedOperationEvaluate(cgOperation);
					js.popClassBody(false);
					//
					if (cgOperation.getVirtualOperations().size() <= 0) {
						js.append("\n");
						doCachedOperationClassInstance(cgOperation);
					}
					else {
						js.append("\n");
						doCachedOperationClassDirectInstance(cgOperation);
					}
				}
			}
			finally {
				localContext = null;
			}
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGCachedOperationCallExp(@NonNull CGCachedOperationCallExp cgOperationCallExp) {
		Operation pOperation = cgOperationCallExp.getReferredOperation();
		boolean thisIsSelf = cgOperationCallExp.isThisIsSelf();
		CGValuedElement source = getExpression(cgOperationCallExp.getSource());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		List<Parameter> pParameters = pOperation.getOwnedParameters();
		//
		if (!js.appendLocalStatements(source)) {
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
		//		js.appendClassCast(cgOperationCallExp);
		/*		if (thisIsSelf) {
			js.appendValueName(source);
		}
		else {
			if (localPrefix != null) {
				js.append(localPrefix);
				js.append(".");
			}
			js.append("this");
		} */
		js.append(getNativeOperationInstanceName(pOperation));
		js.append(".evaluate");
		//		js.append(cgOperationCallExp.getReferredOperation().getName());
		js.append("(");
		if (!thisIsSelf) {
			js.appendValueName(source);
		}
		int iMax = Math.min(pParameters.size(), cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			if ((i > 0) || !thisIsSelf) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			Parameter pParameter = pParameters.get(i);
			CGTypeId cgTypeId = analyzer.getTypeId(pParameter.getTypeId());
			TypeDescriptor parameterTypeDescriptor = context.getBoxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
			CGValuedElement argument = getExpression(cgArgument);
			js.appendReferenceTo(parameterTypeDescriptor, argument);
		}
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGCatchExp(@NonNull CGCatchExp cgCatchExp) {
		String eName = globalContext.getEName();
		CGValuedElement cgSource = getExpression(cgCatchExp.getSource());
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
			js.append(" " + eName + ") {\n");
			js.pushIndentation(null);
			js.appendValueName(cgCatchExp);
			js.append(" = ");
			js.appendClassReference(null, ValueUtil.class);
			js.append(".createInvalidValue(" + eName + ");\n");
			js.popIndentation();
			js.append("}\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGClass(@NonNull CGClass cgClass) {
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
		localContext = globalContext.getLocalContext(cgConstraint);
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
		JavaLocalContext<@NonNull ?>localContext2 = localContext;
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
	public @NonNull Boolean visitCGEcoreOperationCallExp(@NonNull CGEcoreOperationCallExp cgOperationCallExp) {
		Operation pOperation = cgOperationCallExp.getReferredOperation();
		CGTypeId cgTypeId = analyzer.getTypeId(pOperation.getOwningClass().getTypeId());
		//		TypeDescriptor requiredTypeDescriptor = context.getUnboxedDescriptor(cgTypeId.getElementId());
		TypeDescriptor requiredTypeDescriptor = context.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
		CGValuedElement source = getExpression(cgOperationCallExp.getSource());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		List<Parameter> pParameters = pOperation.getOwnedParameters();
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			CGValuedElement argument = getExpression(cgArgument);
			if (!js.appendLocalStatements(argument)) {
				return false;
			}
		}
		//
		String operationAccessor = genModelHelper.getOperationAccessor(pOperation);
		//		Class<?> actualBoxedReturnClass = getBoxedReturnClass(libraryOperation, arguments.size());
		//		Class<?> unboxedSourceClass;
		//		try {		// FIXME this peeking is only needed for the Pivot Domain/non-Domain levels
		//			unboxedSourceClass = genModelHelper.getEcoreInterfaceClass(eOperation.getEContainingClass());
		//		}
		//		catch (GenModelException e) {
		//			unboxedSourceClass = getJavaClass(source);
		//		}
		Element asOperationCallExp = cgOperationCallExp.getAst();
		Boolean ecoreIsRequired = asOperationCallExp instanceof OperationCallExp ? context.isNonNull((OperationCallExp) asOperationCallExp) : null;
		appendSuppressWarningsNull(cgOperationCallExp, ecoreIsRequired);
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		js.appendAtomicReferenceTo(requiredTypeDescriptor, source);
		js.append(".");
		js.append(operationAccessor);
		js.append("(");
		int iMax = Math.min(pParameters.size(), cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			if (i > 0) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			CGValuedElement argument = getExpression(cgArgument);
			Parameter pParameter = ClassUtil.nonNullState(pParameters.get(i));
			GenParameter genParameter = context.getGenModelHelper().getGenParameter(pParameter);
			if (genParameter != null) {
				String rawBoundType = ClassUtil.nonNullState(genParameter.getRawBoundType());
				TypeDescriptor typeDescriptor = context.getTypeDescriptor(argument);
				typeDescriptor.appendEcoreValue(js, rawBoundType, argument);
			}
			else {	// ? never happens
				CGTypeId cgParameterTypeId = analyzer.getTypeId(pParameter.getTypeId());
				TypeDescriptor parameterTypeDescriptor = context.getUnboxedDescriptor(ClassUtil.nonNullState(cgParameterTypeId.getElementId()));
				js.appendReferenceTo(parameterTypeDescriptor, argument);

			}
		}
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGEcorePropertyCallExp(@NonNull CGEcorePropertyCallExp cgPropertyCallExp) {
		return appendCGEcorePropertyCallExp(cgPropertyCallExp, null);
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
			js.append(globalContext.getValueName(cgElementId));
			js.append(" = ");
			js.appendIdReference2(elementId);
			js.append(";\n");
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGExecutorCompositionProperty(@NonNull CGExecutorCompositionProperty cgExecutorProperty) {
		js.appendDeclaration(cgExecutorProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgExecutorProperty);
		js.append("(");
		js.appendIdReference(cgExecutorProperty.getUnderlyingPropertyId().getElementId());
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGExecutorShadowPart(@NonNull CGExecutorShadowPart cgExecutorShadowPart) {
		js.appendDeclaration(cgExecutorShadowPart);
		js.append(" = ");
		js.appendValueName(localContext.getIdResolverVariable(cgExecutorShadowPart));
		js.append(".getProperty(");
		js.appendIdReference(cgExecutorShadowPart.getUnderlyingPropertyId().getElementId());
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGExecutorNavigationProperty(@NonNull CGExecutorNavigationProperty cgExecutorProperty) {
		js.appendDeclaration(cgExecutorProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgExecutorProperty);
		js.append("(");
		js.appendIdReference(cgExecutorProperty.getUnderlyingPropertyId().getElementId());
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGExecutorOppositeProperty(@NonNull CGExecutorOppositeProperty cgExecutorProperty) {
		Property asProperty = (Property) cgExecutorProperty.getAst();
		Property asOppositeProperty = asProperty.getOpposite();
		js.appendDeclaration(cgExecutorProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgExecutorProperty);
		js.append("(");
		js.appendIdReference(asOppositeProperty.getPropertyId());
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGExecutorOperation(@NonNull CGExecutorOperation cgExecutorOperation) {
		js.appendDeclaration(cgExecutorOperation);
		js.append(" = ");
		try {
			js.appendValueName(localContext.getIdResolverVariable(cgExecutorOperation));
		}
		catch (Exception e) {			// FIXME
			js.appendString(String.valueOf(e));
		}
		js.append(".getOperation(");
		js.appendIdReference(cgExecutorOperation.getUnderlyingOperationId().getElementId());
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGExecutorOperationCallExp(@NonNull CGExecutorOperationCallExp cgOperationCallExp) {
		Operation pOperation = cgOperationCallExp.getReferredOperation();
		CGValuedElement source = getExpression(cgOperationCallExp.getSource());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		List<Parameter> pParameters = pOperation.getOwnedParameters();
		//
		if (!js.appendLocalStatements(source)) {
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
				js.append(".evaluate(");
				//		js.append(getValueName(localContext.getEvaluatorParameter(cgOperationCallExp)));
				js.append(JavaConstants.EXECUTOR_NAME);
				js.append(", ");
				js.appendIdReference(cgOperationCallExp.getASTypeId());
				js.append(", ");
				js.appendValueName(source);
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
	}

	@Override
	public @NonNull Boolean visitCGExecutorOppositePropertyCallExp(@NonNull CGExecutorOppositePropertyCallExp cgPropertyCallExp) {
		CGValuedElement source = getExpression(cgPropertyCallExp.getSource());
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgPropertyCallExp.getExecutorProperty());
				js.append(".evaluate(");
				//		js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(JavaConstants.EXECUTOR_NAME);
				js.append(", ");
				js.appendIdReference(cgPropertyCallExp.getASTypeId());
				js.append(", ");
				js.appendValueName(source);
				js.append(")");
			}
		};
		js.appendClassCast(cgPropertyCallExp, castBody);
		js.append(";\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGExecutorPropertyCallExp(@NonNull CGExecutorPropertyCallExp cgPropertyCallExp) {
		CGValuedElement source = getExpression(cgPropertyCallExp.getSource());
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		//	CGExecutorProperty cgExecutorProperty = ClassUtil.nonNullState(cgPropertyCallExp.getExecutorProperty());
		Boolean ecoreIsRequired = Boolean.FALSE;						// CP properties evaluate is nullable -- FIXME compute rather than assume
		//	boolean isPrimitive = js.isPrimitive(cgPropertyCallExp);
		Boolean isRequired = context.isRequired(cgPropertyCallExp);
		if ((isRequired == Boolean.TRUE) && (ecoreIsRequired != Boolean.TRUE)) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		TypeDescriptor typeDescriptor = context.getTypeDescriptor(cgPropertyCallExp);
		JavaStream.SubStream castBody = new JavaStream.SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgPropertyCallExp.getExecutorProperty());
				js.append(".evaluate(");
				//		js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(JavaConstants.EXECUTOR_NAME);
				js.append(", ");
				js.appendIdReference(cgPropertyCallExp.getASTypeId());
				js.append(", ");
				js.appendValueName(source);
				js.append(")");
			}
		};
		typeDescriptor.appendCast(js, isRequired, null, castBody);
		js.append(";\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGExecutorType(@NonNull CGExecutorType cgExecutorType) {
		js.appendDeclaration(cgExecutorType);
		js.append(" = ");
		SubStream castBody1 = new SubStream() {
			@Override
			public void append() {
				js.appendValueName(localContext.getIdResolverVariable(cgExecutorType));
				js.append(".getClass(");
				js.appendIdReference(cgExecutorType.getUnderlyingTypeId().getElementId());
				js.append(", null)");
			}
		};
		js.appendClassCast(cgExecutorType, Boolean.TRUE, org.eclipse.ocl.pivot.Class.class, castBody1);
		js.append(";\n");
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
		CGValuedElement cgIn = cgLetExp.getIn();
		if (cgIn != null) {
			if (!js.appendLocalStatements(cgIn)) {
				return false;
			}
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

	@Override
	public @NonNull Boolean visitCGLibraryOperation(@NonNull CGLibraryOperation cgOperation) {
		JavaLocalContext<@NonNull ?> localContext2 = globalContext.getLocalContext(cgOperation);
		if (localContext2 != null) {
			localContext = localContext2;
			try {
				List<CGParameter> cgParameters = cgOperation.getParameters();
				String operationName = cgOperation.getName();
				assert operationName != null;
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
				js.append(globalContext.getInstanceName());
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
					if (ast instanceof Operation) {
						String title = PrettyPrinter.printName(ast);
						js.appendCommentWithOCL(title+"\n", expressionInOCL);
					}
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
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGLibraryOperationCallExp(@NonNull CGLibraryOperationCallExp cgOperationCallExp) {
		final LibraryOperation libraryOperation = ClassUtil.nonNullState(cgOperationCallExp.getLibraryOperation());
		LibraryOperationHandler libraryOperationHandler = libraryOperation2handler.get(libraryOperation.getClass());
		if (libraryOperationHandler != null) {
			return libraryOperationHandler.generate(cgOperationCallExp);
		}
		final CGValuedElement source = getExpression(cgOperationCallExp.getSource());
		final List<CGValuedElement> arguments = cgOperationCallExp.getArguments();
		Method actualMethod = getJavaMethod(libraryOperation, arguments.size());
		Class<?> actualReturnClass = actualMethod != null ? actualMethod.getReturnType() : null;
		boolean actualIsNonNull = (actualMethod != null) && (context.getIsNonNull(actualMethod) == Boolean.TRUE);
		boolean expectedIsNonNull = cgOperationCallExp.isNonNull();
		final CGTypeId resultType = cgOperationCallExp.getTypeId();
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : arguments) {
			if (!js.appendLocalStatements(cgArgument)) {
				return false;
			}
		}
		for (int i = 0; i < arguments.size(); i++) {
			CGValuedElement cgArgument = arguments.get(i);
			Parameter asParameter = cgOperationCallExp.getReferredOperation().getOwnedParameters().get(i);
			if (asParameter.isIsRequired()) {
				if (cgArgument.isNull()) {
					js.append("throw new ");
					js.appendClassReference(null, InvalidValueException.class);
					js.append("(\"Null argument\");\n");
					return false;
				}
				else if (cgArgument.isInvalid()) {
					js.append("throw new ");
					js.appendClassReference(null, InvalidValueException.class);
					js.append("(\"Invalid argument\");\n");
					return false;
				}
				else {
					if (!cgArgument.isNonNull()) {
						js.append("if (");
						js.appendValueName(cgArgument);
						js.append(" == null) {\n");
						js.pushIndentation(null);
						js.append("throw new ");
						js.appendClassReference(null, InvalidValueException.class);
						js.append("(\"Null argument\");\n");
						js.popIndentation();
						js.append("}\n");
					}
					if (!cgArgument.isNonInvalid()) {
						js.append("if (");
						js.appendValueName(cgArgument);
						js.append(" instanceof ");
						js.appendClassReference(null, InvalidValueException.class);
						js.append(") {\n");
						js.pushIndentation(null);
						js.append("throw (");
						js.appendClassReference(null, InvalidValueException.class);
						js.append(")");
						js.appendValueName(cgArgument);
						js.append(";\n");
						js.popIndentation();
						js.append("}\n");
					}
				}
			}
		}
		if (expectedIsNonNull && !actualIsNonNull) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		boolean isRequiredNullCast = expectedIsNonNull && !actualIsNonNull;
		//		if (expectedIsNonNull && !actualIsNonNull) {
		//			js.appendClassReference(null, ClassUtil.class);
		//			js.append(".nonNullState(");
		//		}
		js.appendClassCast(cgOperationCallExp, isRequiredNullCast, actualReturnClass, new JavaStream.SubStream()
		{
			@Override
			public void append() {
				js.appendClassReference(null, libraryOperation.getClass());
				js.append("."+ globalContext.getInstanceName() + "."+ globalContext.getEvaluateName() + "(");
				if (!(libraryOperation instanceof LibrarySimpleOperation)) {
					//					js.append(getValueName(localContext.getEvaluatorParameter(cgOperationCallExp)));
					js.append(JavaConstants.EXECUTOR_NAME);
					js.append(", ");
					if (!(libraryOperation instanceof LibraryUntypedOperation)) {
						//						CGTypeVariable typeVariable = localContext.getTypeVariable(resultType);
						js.appendValueName(resultType);
						js.append(", ");
					}
				}
				if (source.isNull()) {
					js.append("(Object)");
				}
				js.appendValueName(source);
				for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : arguments) {
					js.append(", ");
					if (cgArgument.isNull()) {
						js.append("(Object)");
					}
					js.appendValueName(cgArgument);		// FIXME cast
				}
				js.append(")");
			}
		});
		//		if (expectedIsNonNull && !actualIsNonNull) {
		//			js.append(")");
		//		}
		js.append(";\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGLibraryPropertyCallExp(@NonNull CGLibraryPropertyCallExp cgPropertyCallExp) {
		CGValuedElement source = getExpression(cgPropertyCallExp.getSource());
		LibraryProperty libraryProperty = ClassUtil.nonNullState(cgPropertyCallExp.getLibraryProperty());
		Method actualMethod = getJavaMethod(libraryProperty);
		Class<?> actualReturnClass = actualMethod != null ? actualMethod.getReturnType() : null;
		boolean actualIsNonNull = (actualMethod != null) && (context.getIsNonNull(actualMethod) == Boolean.TRUE);
		boolean expectedIsNonNull = cgPropertyCallExp.isNonNull();
		//		Class<?> actualBoxedReturnClass = getBoxedReturnClass(libraryProperty);
		//		CGValuedElement resultVariable = cgOperationCallExp; //.getValue();
		CGTypeId resultType = cgPropertyCallExp.getTypeId();
		//		Class<?> requiredBoxedReturnClass = context.getBoxedClass(resultType.getElementId());
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		boolean isRequiredNullCast = expectedIsNonNull && !actualIsNonNull;
		//		if (expectedIsNonNull && !actualIsNonNull) {
		//			js.appendClassReference(null, ClassUtil.class);
		//			js.append(".nonNullState(");
		//		}
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendClassReference(null, libraryProperty.getClass());
				//		CGOperation cgOperation = ClassUtil.nonNullState(CGUtils.getContainingOperation(cgPropertyCallExp));
				js.append("."+ globalContext.getInstanceName() + "."+ globalContext.getEvaluateName() + "(");
				//		if (!(libraryOperation instanceof LibrarySimpleOperation)) {
				//			js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(JavaConstants.EXECUTOR_NAME);
				js.append(", ");
				//			if (!(libraryProperty instanceof LibraryUntypedOperation)) {
				//				CGTypeVariable typeVariable = localContext.getTypeVariable(resultType);
				js.appendValueName(resultType);
				js.append(", ");
				//			}
				//		}
				js.appendValueName(source);
				//				if (expectedIsNonNull && !actualIsNonNull) {
				//					js.append(")");
				//				}
				js.append(")");
			}
		};
		js.appendClassCast(cgPropertyCallExp, isRequiredNullCast, actualReturnClass, castBody);
		js.append(";\n");
		return true;
	}

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

	@Override		// FIXME revert to the pre-cached code
	public @NonNull Boolean visitCGNativeOperation(@NonNull CGNativeOperation cgOperation) {
		Operation asOperation = (Operation) cgOperation.getAst();
		assert asOperation != null;
		JavaLocalContext<@NonNull ?> localContext2 = globalContext.getLocalContext(cgOperation);
		if (localContext2 != null) {
			localContext = localContext2;
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
		}
		return true;
	}

	@Override
	public @NonNull Boolean visitCGNativeOperationCallExp(@NonNull CGNativeOperationCallExp cgOperationCallExp) {
		Operation pOperation = cgOperationCallExp.getReferredOperation();
		boolean thisIsSelf = cgOperationCallExp.isThisIsSelf();
		CGValuedElement source = getExpression(cgOperationCallExp.getSource());
		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		List<Parameter> pParameters = pOperation.getOwnedParameters();
		//
		if (!js.appendLocalStatements(source)) {
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
		//		js.appendClassCast(cgOperationCallExp);
		/*		if (thisIsSelf) {
			js.appendValueName(source);
		}
		else {
			if (localPrefix != null) {
				js.append(localPrefix);
				js.append(".");
			}
			js.append("this");
		} */
		js.appendThis(ClassUtil.nonNullState(cgOperationCallExp.getReferredOperation().getOwningClass().getName()));
		js.append(".");
		js.append(cgOperationCallExp.getReferredOperation().getName());
		js.append("(");
		if (!thisIsSelf) {
			js.appendValueName(source);
		}
		int iMax = Math.min(pParameters.size(), cgArguments.size());
		for (int i = 0; i < iMax; i++) {
			if ((i > 0) || !thisIsSelf) {
				js.append(", ");
			}
			CGValuedElement cgArgument = cgArguments.get(i);
			Parameter pParameter = pParameters.get(i);
			CGTypeId cgTypeId = analyzer.getTypeId(pParameter.getTypeId());
			TypeDescriptor parameterTypeDescriptor = context.getUnboxedDescriptor(ClassUtil.nonNullState(cgTypeId.getElementId()));
			CGValuedElement argument = getExpression(cgArgument);
			js.appendReferenceTo(parameterTypeDescriptor, argument);
		}
		js.append(");\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGNativeProperty(@NonNull CGNativeProperty cgNativeProperty) {
		localContext = globalContext.getLocalContext(cgNativeProperty);
		try {
			js.append("protected ");
			js.appendDeclaration(cgNativeProperty);
			js.append(";\n");
			return true;
		}
		finally {
			localContext = null;
		}
	}

	@Override
	public @NonNull Boolean visitCGNativePropertyCallExp(@NonNull CGNativePropertyCallExp cgPropertyCallExp) {
		CGValuedElement source = getExpression(cgPropertyCallExp.getSource());
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendValueName(source);
				js.append(".");
				js.append(cgPropertyCallExp.getReferredProperty().getName());
			}
		};
		js.appendClassCast(cgPropertyCallExp, castBody);
		js.append(";\n");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGNull(@NonNull CGNull object) {
		js.append("null");
		return true;
	}

	@Override
	public @NonNull Boolean visitCGOperation(@NonNull CGOperation cgOperation) {
		JavaLocalContext<@NonNull ?> localContext2 = globalContext.getLocalContext(cgOperation);
		if (localContext2 != null) {
			localContext = localContext2;
			try {
				//				CGValuedElement evaluatorParameter = localContext2.getEvaluatorParameter(cgOperation);
				//				CGParameter typeIdParameter = localContext2.getTypeIdParameter(cgOperation);
				List<CGParameter> cgParameters = cgOperation.getParameters();
				CGValuedElement body = getExpression(cgOperation.getBody());
				//
				Element ast = cgOperation.getAst();
				if (ast instanceof Operation) {
					LanguageExpression expressionInOCL = ((Operation)ast).getBodyExpression();
					if (ast instanceof Operation) {
						String title = PrettyPrinter.printName(ast);
						js.appendCommentWithOCL(title+"\n", expressionInOCL);
					}
				}
				//
				js.append("@Override\n");
				js.append("public ");
				boolean cgOperationIsInvalid = cgOperation.getInvalidValue() != null;
				js.appendIsCaught(!cgOperationIsInvalid, cgOperationIsInvalid);
				js.append(" ");
				js.appendClassReference(cgOperation.isRequired() ? true : null, cgOperation);
				js.append(" ");
				js.append(cgOperation.getName());
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
			}
			finally {
				localContext = null;
			}
		}
		return true;
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
		localContext = globalContext.getLocalContext(cgProperty);
		try {
			Boolean flowContinues = super.visitCGProperty(cgProperty);
			assert flowContinues != null;
			return flowContinues;
		}
		finally {
			localContext = null;
		}
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
		js.appendValueName(cgExecutorShadowPart);
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
	public @NonNull Boolean visitCGText(@NonNull CGText cgText) {
		js.appendDeclaration(cgText);
		js.append(" = ");
		js.append(cgText.getTextValue());
		js.append(";\n");
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
	public @NonNull Boolean visitCGTuplePartCallExp(@NonNull CGTuplePartCallExp cgTuplePartCallExp) {
		CGValuedElement source = getExpression(cgTuplePartCallExp.getSource());
		//		CGTypeId resultType = cgTuplePartCallExp.getTypeId();
		//		Class<?> requiredBoxedReturnClass = context.getBoxedClass(resultType.getElementId());
		TuplePartId partId = cgTuplePartCallExp.getAstTuplePartId();
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		boolean isRequired = cgTuplePartCallExp.isNonNull();
		boolean isPrimitive = js.isPrimitive(cgTuplePartCallExp);
		if (!isPrimitive && isRequired /*&& (ecoreIsRequired == Boolean.FALSE)*/) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgTuplePartCallExp);
		js.append(" = ");
		//		js.appendClassReference(null, ClassUtil.class);
		//		js.append(".nonNullState(");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendAtomicReferenceTo(TupleValue.class, source);
				js.append(".getValue(" + partId.getIndex() + "/*" + partId.getName() + "*/)");
			}
		};
		js.appendClassCast(cgTuplePartCallExp, castBody);
		//		js.append(")");
		js.append(";\n");
		return true;
	}

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
		JavaLocalContext<@NonNull ?> localContext2 = localContext;
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
		CGValuedElement init = cgVariable.getInit();
		if (init != null) {
			if (!js.appendLocalStatements(init)) {
				return false;
			}
		}
		//		js.appendDeclaration(cgVariable);
		//		if (init != null) {
		//			js.append(" = ");
		//			js.appendValueName(init);
		//		}
		//		js.append(";\n");
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
