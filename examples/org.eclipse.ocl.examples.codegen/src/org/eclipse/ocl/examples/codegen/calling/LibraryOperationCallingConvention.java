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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.FeatureNameManager;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager;
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
public class LibraryOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull LibraryOperationCallingConvention INSTANCE = new LibraryOperationCallingConvention();

	@Override
	public @NonNull CGOperation createCGOperation(@NonNull CodeGenAnalyzer analyzer, @NonNull Operation asOperation) {
 		PivotMetamodelManager metamodelManager = analyzer.getMetamodelManager();
		LibraryFeature libraryOperation = metamodelManager.getImplementation(asOperation);
		assert !(libraryOperation instanceof EObjectOperation);
		assert !(libraryOperation instanceof ForeignOperation);
		assert !(libraryOperation instanceof ConstrainedOperation);
		CGLibraryOperation cgOperation = CGModelFactory.eINSTANCE.createCGLibraryOperation();
		initOperation(analyzer, cgOperation, asOperation);
		analyzer.addCGOperation(cgOperation);
		return cgOperation;
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
				CGVariable executorVariable = analyzer.useFeatureNameManager(asOperationCallExp).getExecutorVariable();
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

	public @NonNull CGLibraryOperationCallExp createCGMethodOperationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull LibraryOperation libraryOperation,
			CGValuedElement cgSource, @NonNull Operation asOperation) {
	//		CGValuedElement cgSource, @NonNull OperationCallExp zzasOperationCallExp) {
	//	Operation zzasOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		Method jMethod = libraryOperation.getEvaluateMethod(asOperation);
		//	assert (cgSource == null) == Modifier.isStatic(jMethod.getModifiers());
			CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
			cgOperationCallExp.setLibraryOperation(libraryOperation);
			List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
			for (Class<?> jParameterType : jMethod.getParameterTypes()) {
				if (jParameterType == Executor.class) {
					CGVariable executorVariable = analyzer.getFeatureNameManager().getExecutorVariable();
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
	public void createCGParameters(@NonNull CodeGenAnalyzer analyzer, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL expressionInOCL) {
	//	assert expressionInOCL == null;		-- some library operations also have OCL bodies
		Operation asOperation = CGUtil.getAST(cgOperation);
		FeatureNameManager nameManager = analyzer.useOperationNameManager(cgOperation);
		List<CGParameter> cgParameters = cgOperation.getParameters();
		LibraryOperation libraryOperation = (LibraryOperation)analyzer.getMetamodelManager().getImplementation(asOperation);
		Method jMethod = libraryOperation.getEvaluateMethod(asOperation);
		cgOperation.setRequired(analyzer.getCodeGenerator().getIsNonNull(jMethod) == Boolean.TRUE);
		List<@NonNull Parameter> asParameters = ClassUtil.nullFree(asOperation.getOwnedParameters());
		int i = asOperation.isIsStatic() ? 0 : -1;
		if (Modifier.isStatic(jMethod.getModifiers())) {
			cgParameters.add(nameManager.getThisParameter());
		}
		for (Class<?> jParameterType : jMethod.getParameterTypes()) {
			if (jParameterType == Executor.class) {
				cgParameters.add(nameManager.getExecutorParameter());
			}
			else if (jParameterType == TypeId.class) {
				cgParameters.add(nameManager.getTypeIdParameter());
			}
			else if (jParameterType == Object.class)  {
				if (i < 0) {
					CGParameter selfParameter;
					if (expressionInOCL != null) {
						selfParameter = nameManager.getSelfParameter(PivotUtil.getOwnedContext(expressionInOCL));
					}
					else {
						selfParameter = nameManager.getSelfParameter();
					}
					if (analyzer.hasOclVoidOperation(asOperation.getOperationId())) {
						selfParameter.setRequired(false);
					}
					cgParameters.add(selfParameter);
					i = 0;
				}
				else {
					Parameter aParameter = asParameters.get(i++);
					CGParameter cgParameter = nameManager.getParameter(aParameter, (String)null);
					cgParameters.add(cgParameter);
				}
			}
			else {
				throw new UnsupportedOperationException();
			}
		}
		assert (i == asParameters.size()) || (libraryOperation == OclAnyUnsupportedOperation.INSTANCE);
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGLibraryOperationCallExp cgLibraryOperationCallExp = (CGLibraryOperationCallExp)cgOperationCallExp;
		assert cgOperationCallExp.getCgThis() == null;
		final LibraryOperation libraryOperation = ClassUtil.nonNullState(cgLibraryOperationCallExp.getLibraryOperation());
		LibraryOperationHandler libraryOperationHandler = cg2javaVisitor.basicGetLibraryOperationHandler(libraryOperation.getClass());
		if (libraryOperationHandler != null) {
			return libraryOperationHandler.generate(cgLibraryOperationCallExp);		// XXX BuiltIn ??
		}
		final List<@NonNull CGValuedElement> cgArguments = ClassUtil.nullFree(cgOperationCallExp.getArguments());
		int iMax = cgArguments.size();
		CGOperation cgOperation = cgOperationCallExp.getReferredOperation();
		Method jMethod = libraryOperation.getEvaluateMethod(CGUtil.getAST(cgOperation));
		Class<?> actualReturnClass = jMethod.getReturnType();
		Boolean actualNullity = cg2javaVisitor.getCodeGenerator().getIsNonNull(jMethod);
		boolean actualIsNonNull = actualNullity == Boolean.TRUE;
	//	boolean actualIsNullable = actualNullity == Boolean.FALSE;
		boolean expectedIsNonNull = cgOperationCallExp.isNonNull();
		if (!generateLocals(cg2javaVisitor, js, cgOperationCallExp)) {
			return false;
		}
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
					boolean mayBeNull = !cgArgument.isNonNull();
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
	//	Boolean returnNullity = cg2javaVisitor.getCodeGenerator().getIsNonNull(jMethod);
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
				GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
				js.appendClassReference(null, libraryOperation.getClass());
				js.append(".");
				js.append(globalNameManager.getInstanceName());
				js.append(".");
				js.append(globalNameManager.getEvaluateName());
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
		});
		//		if (expectedIsNonNull && !actualIsNonNull) {
		//			js.append(")");
		//		}
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGOperation cgOperation) {
		throw new UnsupportedOperationException();		// Library operations are declared natively
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
}
