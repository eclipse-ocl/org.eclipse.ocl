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

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.operation.LibraryOperationHandler;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.OCLExpression;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.evaluation.Executor;
import org.eclipse.ocl.pivot.ids.TypeId;
import org.eclipse.ocl.pivot.internal.ecore.EObjectOperation;
import org.eclipse.ocl.pivot.internal.library.ConstrainedOperation;
import org.eclipse.ocl.pivot.internal.library.ForeignOperation;
import org.eclipse.ocl.pivot.internal.manager.PivotMetamodelManager;
import org.eclipse.ocl.pivot.internal.utilities.PivotUtilInternal;
import org.eclipse.ocl.pivot.library.LibraryFeature;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.oclany.OclAnyUnsupportedOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.utilities.PivotUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 *  LibraryOperationCallingConvention defines the support for the call of a standard operation realized by
 *  typically custom code. The arguments depend on the derived AbstractOperation from which CustomOperation derives
 *   *  </br>
 *  e.g. as CustomOperation.INSTANCE.evaluate(executor, arguments)
 *  </br>
 *  The call adapts to the actual signature of the invoked method for which
 *  </br> An Executor parameter is passed the prevailing executor
 *  </br> A TypeId parameter is passed the return TypeId
 *  </br> If non-static, a first Object parameter is passed the source value
 *  </br> Subsequent Object parameters are passed the argument values in order
 *  </br> An Object[] parameter is passed source and argument values
 */
public class LibraryOperationCallingConvention extends AbstractUncachedOperationCallingConvention
{
	private static final @NonNull LibraryOperationCallingConvention INSTANCE = new LibraryOperationCallingConvention();

	public static @NonNull LibraryOperationCallingConvention getInstance(@NonNull Operation asOperation, boolean maybeVirtual) {
		INSTANCE.logInstance(asOperation, maybeVirtual);
		return INSTANCE;
	}

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
 		PivotMetamodelManager metamodelManager = analyzer.getMetamodelManager();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		assert !(libraryOperation instanceof EObjectOperation);
		assert !(libraryOperation instanceof ForeignOperation);
		assert !(libraryOperation instanceof ConstrainedOperation);
		return CGModelFactory.eINSTANCE.createCGLibraryOperation();
	}

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
	//	System.out.println("createCGOperationCallExp: to " + asOperation);
		assert (cgSource == null) == asOperation.isIsStatic();
		boolean isRequired = cgOperation.isRequired();
		Method jMethod = libraryOperation.getEvaluateMethod(asOperation);
		//	assert (cgSource == null) == Modifier.isStatic(jMethod.getModifiers());
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(libraryOperation);
		initCallExp(analyzer, cgOperationCallExp, asOperationCallExp, cgOperation, isRequired);

		List<@NonNull OCLExpression> asArguments = PivotUtilInternal.getOwnedArgumentsList(asOperationCallExp);
		int asArgumentSize = asArguments.size();
		assert asArgumentSize == asOperation.getOwnedParameters().size();

		List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		Class<?>[] jParameterTypes = jMethod.getParameterTypes();
		int syntheticArgumentSize = jParameterTypes.length - asArgumentSize;
		for (int i = 0; i < syntheticArgumentSize; i++) {
			Class<?> jParameterType = jParameterTypes[i];
			if (jParameterType == Executor.class) {
				ExecutableNameManager executableNameManager = analyzer.useExecutableNameManager(asOperationCallExp);
				CGVariable executorVariable = executableNameManager.lazyGetExecutorVariable();
				cgArguments.add(analyzer.createCGVariableExp(executorVariable));
			}
			else if (jParameterType == TypeId.class) {
				addTypeIdArgument(analyzer, cgOperationCallExp, asOperationCallExp.getTypeId());
			}
			else if (jParameterType == Object.class) {
				if (cgSource != null) {
					cgArguments.add(cgSource);
				}
				break;
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		for (@NonNull OCLExpression asArgument : asArguments) {
			CGValuedElement cgArgument = analyzer.createCGElement(CGValuedElement.class, asArgument);
			cgArguments.add(cgArgument);
		}
	//	initCallArguments(as2cgVisitor, cgOperationCallExp);
		return cgOperationCallExp;
	}

/*	public @NonNull CGCallExp createCGOperationCallExp2(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation,
			@Nullable CGValuedElement cgSource, @NonNull CGValuedElement @Nullable ... cgArguments) {
		@NonNull Operation asOperation = CGUtil.getAST(cgOperation);
		@NonNull LibraryOperation libraryOperation = (LibraryOperation) asOperation.getImplementation();
		//	Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
	//	System.out.println("createCGOperationCallExp: to " + asOperation);
		assert (cgSource == null) == asOperation.isIsStatic();
		boolean isRequired = cgOperation.isRequired();
	//	Method jMethod = libraryOperation.getEvaluateMethod(asOperation);
		//	assert (cgSource == null) == Modifier.isStatic(jMethod.getModifiers());
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(libraryOperation);
		initCallExp2(analyzer, cgOperationCallExp, cgOperation, isRequired);

	//	List<@NonNull OCLExpression> asArguments = PivotUtilInternal.getOwnedArgumentsList(asOperationCallExp);
	//	int asArgumentSize = asArguments.size();
	//	assert asArgumentSize == asOperation.getOwnedParameters().size();

	//	List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
	/*	Class<?>[] jParameterTypes = jMethod.getParameterTypes();
		int syntheticArgumentSize = jParameterTypes.length - asArgumentSize;
		for (int i = 0; i < syntheticArgumentSize; i++) {
			Class<?> jParameterType = jParameterTypes[i];
			if (jParameterType == Executor.class) {
				ExecutableNameManager executableNameManager = analyzer.useExecutableNameManager(asOperationCallExp);
				CGVariable executorVariable = analyzer.getExecutorVariable(executableNameManager);
				cgArguments.add(analyzer.createCGVariableExp(executorVariable));
			}
			else if (jParameterType == TypeId.class) {
				addTypeIdArgument(analyzer, cgOperationCallExp, asOperation.getTypeId());
			}
			else if (jParameterType == Object.class) {
				if (cgSource != null) {
					cgArguments.add(cgSource);
				}
				break;
			}
			else {
				throw new UnsupportedOperationException();
			}
		} * /
		if (cgArguments != null) {
			for (@NonNull CGValuedElement cgArgument : cgArguments) {
			//	CGValuedElement cgArgument = analyzer.createCGElement(CGValuedElement.class, asArgument);
				cgOperationCallExp.getArguments().add(cgArgument);
			}
		}
	//	initCallArguments(as2cgVisitor, cgOperationCallExp);
		return cgOperationCallExp;
	} */

/*	private void initCallExp2(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperationCallExp cgOperationCallExp,
			@NonNull CGOperation cgOperation, boolean isRequired) {		// XXX wip eliminate isRequired
	//	Operation asOperation = PivotUtil.getReferredOperation(asOperationCallExp);
		Operation asOperation = CGUtil.getAST(cgOperation);
	//	boolean isRequired2 = asOperation.isIsRequired();
	//	Boolean ecoreIsRequired = as2cgVisitor.getCodeGenerator().isNonNull(asOperationCallExp);
	//	if (ecoreIsRequired != null) {
	//		isRequired2 = ecoreIsRequired;
	//	}
	//	assert isRequired == isRequired2;
		cgOperationCallExp.setAsOperation(asOperation);
		analyzer.initAst(cgOperationCallExp, asOperation, false);
		cgOperationCallExp.setReferredOperation(cgOperation);
		cgOperationCallExp.setInvalidating(asOperation.isIsInvalidating());
		cgOperationCallExp.setValidating(asOperation.isIsValidating());
	} */

	public @NonNull CGLibraryOperationCallExp createCGMethodOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull LibraryOperation libraryOperation,
	//		CGValuedElement cgSource, @NonNull Operation asOperation) {
			CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = PivotUtil.getReferredOperation(asOperationCallExp);
		Method jMethod = libraryOperation.getEvaluateMethod(asOperation);
		//	assert (cgSource == null) == Modifier.isStatic(jMethod.getModifiers());
			CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
			cgOperationCallExp.setLibraryOperation(libraryOperation);
			List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
			for (Class<?> jParameterType : jMethod.getParameterTypes()) {
				if (jParameterType == Executor.class) {
					ExecutableNameManager executableNameManager = analyzer.useExecutableNameManager(asOperationCallExp);
					CGVariable executorVariable = executableNameManager.lazyGetExecutorVariable();
					cgArguments.add(analyzer.createCGVariableExp(executorVariable));
				}
				else if (jParameterType == TypeId.class) {
					addTypeIdArgument(analyzer, cgOperationCallExp, asOperation/*CallExp*/.getTypeId());
				}
				else if (jParameterType == Object.class) {
					if (cgSource != null) {
						cgArguments.add(cgSource);
					}
					break;
				}
				else {
					throw new UnsupportedOperationException();
				}
			}
		return cgOperationCallExp;
	}

	@Override
	public @NonNull CGOperation createOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
		return createOperation(analyzer, asOperation, null);		// Bypass redundant body parsing
/*		CGOperation cgOperation;
		ExpressionInOCL asExpressionInOCL = null;
		LanguageExpression asSpecification = asOperation.getBodyExpression();
		if (asSpecification != null) {
			try {
				AbstractEnvironmentFactory environmentFactory = (AbstractEnvironmentFactory)analyzer.getCodeGenerator().getEnvironmentFactory();
				asExpressionInOCL = environmentFactory.parseSpecification(asSpecification);			// XXX Not appropriate for virtual dispatcher
			} catch (ParserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		cgOperation = createOperation(analyzer, asOperation, asExpressionInOCL);
	//	if (asSpecification != null) {
	//		analyzer.scanBody(asSpecification);
	//	}
		return cgOperation; */
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGLibraryOperationCallExp cgLibraryOperationCallExp = (CGLibraryOperationCallExp)cgOperationCallExp;
		assert cgOperationCallExp.getCgThis() == null;
		final LibraryOperation libraryOperation = CGUtil.getLibraryOperation(cgLibraryOperationCallExp);
		LibraryOperationHandler libraryOperationHandler = cg2javaVisitor.basicGetLibraryOperationHandler(libraryOperation.getClass());
		if (libraryOperationHandler != null) {
			return libraryOperationHandler.generate(cgLibraryOperationCallExp);		// XXX BuiltIn ??
		}
		final List<@NonNull CGValuedElement> cgArguments = ClassUtil.nullFree(cgOperationCallExp.getArguments());
		int iMax = cgArguments.size();
		CGOperation cgOperation = CGUtil.getReferredOperation(cgOperationCallExp);
		if (!generateLocals(cg2javaVisitor, cgOperationCallExp)) {
			return false;
		}
		JavaStream js = cg2javaVisitor.getJavaStream();
		List<@NonNull CGParameter> cgParameters = ClassUtil.nullFree(cgOperation.getParameters());
		assert cgParameters.size() == iMax;
		for (int i = 0; i < iMax; i++) {
			@NonNull CGParameter cgParameter = cgParameters.get(i);
			if (cgParameter.isRequired() /*&& !cgParameter.isSelf()*/) {			// XXX YYY isSelf
				CGValuedElement cgArgument = cgArguments.get(i);
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
					boolean mayBeNull = !cgArgument.isRequiredOrNonNull();
					if (mayBeNull) {
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
					boolean mayBeInvalid = !cgArgument.isNonInvalid();
					if (mayBeInvalid && cgArgument.isCaught()) {
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
		JavaStream.SubStream sourceStream = new JavaStream.SubStream() {
			@Override
			public void append() {
				GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
				js.appendClassReference(null, libraryOperation.getClass());
				js.append(".");
				js.appendName(globalNameManager.getInstanceName());
				js.append(".");
				js.appendName(globalNameManager.getEvaluateName());
				js.append("(");
				boolean needsComma = false;
				for (@NonNull CGValuedElement cgArgument : cgArguments) {
					if (needsComma) {
						js.append(", ");
					}
					if (cgArgument.isNull()) {
						js.append("(Object)");
					}
					js.appendValueName(cgArgument);		// FIXME cast
					needsComma = true;
				}
				js.append(")");
			}
		};
		Method jMethod = libraryOperation.getEvaluateMethod(CGUtil.getAST(cgOperation));
		Boolean sourceIsRequired = cg2javaVisitor.getCodeGenerator().getIsNonNull(jMethod);
		Class<?> sourceClass = jMethod.getReturnType();
		assert sourceClass != null;
		js.appendAssignWithCast(cgOperationCallExp, sourceIsRequired, sourceClass, sourceStream);
		return true;
/*



	//	Boolean returnNullity = cg2javaVisitor.getCodeGenerator().getIsNonNull(jMethod);
		if (expectedIsNonNull && !actualIsNonNull) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgOperationCallExp);
		js.append(" = ");
		boolean isRequiredNullCast = expectedIsNonNull;// && !actualIsNonNull;
		//		if (expectedIsNonNull && !actualIsNonNull) {
		//			js.appendClassReference(null, ClassUtil.class);
		//			js.append(".nonNullState(");
		//		}
		js.appendClassCast(cgOperationCallExp, isRequiredNullCast, actualReturnClass, new JavaStream.SubStream()
		{
		});
		//		if (expectedIsNonNull && !actualIsNonNull) {
		//			js.append(")");
		//		}
		js.append(";\n");
		return true; */
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGOperation cgOperation) {
		throw new UnsupportedOperationException();		// Library operations are declared natively
	}

	@Override
	protected @NonNull CGParameterStyle @NonNull [] getCGParameterStyles(@NonNull ExecutableNameManager operationNameManager) {
		Operation asOperation = (Operation)operationNameManager.getASScope();
		CodeGenAnalyzer analyzer = operationNameManager.getAnalyzer();
		LibraryOperation libraryOperation = (LibraryOperation)analyzer.getMetamodelManager().getImplementation(asOperation);
		Method jMethod = libraryOperation.getEvaluateMethod(asOperation);
		List<@NonNull CGParameterStyle> cgParameterStyles = new ArrayList<>();
		List<@NonNull Parameter> asParameters = ClassUtil.nullFree(asOperation.getOwnedParameters());
		int i = asOperation.isIsStatic() ? 0 : -1;
		if (Modifier.isStatic(jMethod.getModifiers())) {
			cgParameterStyles.add(CGParameterStyle.THIS);
		}
		boolean hasTypeId = false;
		boolean hasSelf = false;
		boolean hasParameters = false;
		for (Class<?> jParameterType : jMethod.getParameterTypes()) {
			if (jParameterType == Executor.class) {
				assert !hasTypeId : "executor must precede typeId";
				assert !hasSelf : "executor must precede self";
				assert !hasParameters : "executor must precede parameters";
				cgParameterStyles.add(CGParameterStyle.EXECUTOR);
			}
			else if (jParameterType == TypeId.class) {
				hasTypeId = true;
				assert !hasSelf : "typeId must precede self";
				assert !hasParameters : "typeId must precede parameters";
				cgParameterStyles.add(CGParameterStyle.TYPE_ID);
			}
			else if (jParameterType == Object.class)  {
				if (i < 0) {
					hasSelf = true;
					assert !hasParameters : "self must precede parameters";
					ExpressionInOCL expressionInOCL = (ExpressionInOCL)asOperation.getBodyExpression();	//	-- some library operations also have OCL bodies

					boolean mayBeVoid = analyzer.hasOclVoidOperation(asOperation.getOperationId());
					cgParameterStyles.add(mayBeVoid ? CGParameterStyle.OPTIONAL_SELF : CGParameterStyle.SELF);
					i = 0;
				}
				else {
					hasParameters = true;
					cgParameterStyles.add(CGParameterStyle.PARAMETERS);
					i++;
				}
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		assert (i == asParameters.size()) || (libraryOperation == OclAnyUnsupportedOperation.INSTANCE);
		return cgParameterStyles.toArray(new @NonNull CGParameterStyle @NonNull [cgParameterStyles.size()]);
	}

	@Override
	public boolean needsGeneration() {
		return false;
	}

	@Override
	public void rewriteWithBoxingAndGuards(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGOperation cgOperation) {
		CGLibraryOperation cgLibraryOperation = (CGLibraryOperation)cgOperation;
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgLibraryOperation);
		boxingAnalyzer.rewriteAsBoxed(cgLibraryOperation.getBody());
	}

	@Override
	public void rewriteWithBoxingAndGuards(
			@NonNull BoxingAnalyzer boxingAnalyzer,
			@NonNull CGOperationCallExp cgOperationCallExp) {
		// TODO Auto-generated method stub
		super.rewriteWithBoxingAndGuards(boxingAnalyzer, cgOperationCallExp);
	}
}
