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
import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.cgmodel.CGCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperation;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGParameter;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaLocalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.operation.LibraryOperationHandler;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.ExpressionInOCL;
import org.eclipse.ocl.pivot.Operation;
import org.eclipse.ocl.pivot.OperationCallExp;
import org.eclipse.ocl.pivot.Parameter;
import org.eclipse.ocl.pivot.library.LibraryOperation;
import org.eclipse.ocl.pivot.library.LibrarySimpleOperation;
import org.eclipse.ocl.pivot.library.LibraryUntypedOperation;
import org.eclipse.ocl.pivot.utilities.ClassUtil;
import org.eclipse.ocl.pivot.values.InvalidValueException;

/**
 *  LibraryOperationCallingConvention defines the support for the call of a standard operation realized by
 *  typically custom code. The arguments depend on the derived AbstractOperation from which CustomOperation derives
 *   *  </br>
 *  e.g. as CustomOperation.INSTANCE.evaluate(executor, arguments)
 */
public class LibraryOperationCallingConvention extends AbstractOperationCallingConvention
{
	public static final @NonNull LibraryOperationCallingConvention INSTANCE = new LibraryOperationCallingConvention();

	@Override
	public @NonNull CGCallExp createCGOperationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation,
			@Nullable CGValuedElement cgSource, @NonNull OperationCallExp asOperationCallExp) {
		Operation asOperation = ClassUtil.nonNullState(asOperationCallExp.getReferredOperation());
		boolean isRequired = asOperation.isIsRequired();
		CGLibraryOperationCallExp cgOperationCallExp = CGModelFactory.eINSTANCE.createCGLibraryOperationCallExp();
		cgOperationCallExp.setLibraryOperation(libraryOperation);
		cgOperationCallExp.getArguments().add(cgSource);
		init(as2cgVisitor, cgOperationCallExp, asOperationCallExp, cgOperation, null, isRequired);
		return cgOperationCallExp;
	}

	@Override
	public void createParameters(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGOperation cgOperation, @Nullable ExpressionInOCL expressionInOCL) {
	//	super.createParameters(as2cgVisitor, cgOperation, expressionInOCL);
	//	assert expressionInOCL == null;		-- some library operations also have OCL bodies
		JavaLocalContext<?> localContext = as2cgVisitor.getLocalContext();
		List<CGParameter> cgParameters = cgOperation.getParameters();
		Operation asOperation = CGUtil.getAST(cgOperation);
		final LibraryOperation libraryOperation = (LibraryOperation) asOperation.getImplementation();		// XXX
		if (libraryOperation instanceof LibrarySimpleOperation) {
		}
		else if (libraryOperation instanceof LibraryUntypedOperation) {
			cgParameters.add(localContext.getExecutorParameter());
		}
		else {
			cgParameters.add(localContext.getExecutorParameter());
			cgParameters.add(localContext.getTypeIdParameter());
		}
		if (!asOperation.isIsStatic()) {
			cgParameters.add(localContext.getSelfParameter());
		}
		for (@NonNull Parameter asParameter : ClassUtil.nullFree(asOperation.getOwnedParameters())) {
			CGParameter cgParameter = as2cgVisitor.getParameter(asParameter, (String)null);
			cgParameters.add(cgParameter);
		}
	}

	@Override
	public @NonNull Boolean generateJava(@NonNull CG2JavaVisitor<?> cg2JavaVisitor, @NonNull JavaStream js, @NonNull CGOperationCallExp cgOperationCallExp) {
		CGLibraryOperationCallExp cgLibraryOperationCallExp = (CGLibraryOperationCallExp)cgOperationCallExp;
		final LibraryOperation libraryOperation = ClassUtil.nonNullState(cgLibraryOperationCallExp.getLibraryOperation());
		LibraryOperationHandler libraryOperationHandler = cg2JavaVisitor.basicGetLibraryOperationHandler(libraryOperation.getClass());
		if (libraryOperationHandler != null) {
			return libraryOperationHandler.generate(cgLibraryOperationCallExp);		// XXX BuiltIn ??
		}
	// false;		// XXX
		assert cgOperationCallExp.getSource() == null;
	//	final CGValuedElement source = cg2JavaVisitor.getExpression(cgOperationCallExp.getSource());
		final List<CGValuedElement> cgArguments = cgOperationCallExp.getArguments();
		int iMax = cgArguments.size();
		CGOperation cgOperation = cgOperationCallExp.getOperation();
		Method actualMethod = getJavaMethod(cgOperation, libraryOperation);
		Class<?> actualReturnClass = actualMethod != null ? actualMethod.getReturnType() : null;
		boolean actualIsNonNull = (actualMethod != null) && (cg2JavaVisitor.getCodeGenerator().getIsNonNull(actualMethod) == Boolean.TRUE);
		boolean expectedIsNonNull = cgOperationCallExp.isNonNull();
		final CGTypeId resultType = cgOperationCallExp.getTypeId();
	//	if (!js.appendLocalStatements(source)) {
	//		return false;
	//	}
		for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
			if (!js.appendLocalStatements(cgArgument)) {
				return false;
			}
		}
		List<CGParameter> cgParameters = cgOperation.getParameters();
		assert cgParameters.size() == iMax;
		for (int i = 0; i < iMax; i++) {
			CGValuedElement cgArgument = cgArguments.get(i);
		//	Parameter asParameter = cgOperationCallExp.getReferredOperation().getOwnedParameters().get(i);
			CGParameter cgParameter = cgParameters.get(i);
			if (cgParameter.isRequired()) {
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
					if (!cgArgument.isNonInvalid() && cgArgument.isCaught()) {
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
				JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = cg2JavaVisitor.getCodeGenerator().getGlobalContext();
				js.appendClassReference(null, libraryOperation.getClass());
				js.append(".");
				js.append(globalContext.getInstanceName());
				js.append(".");
				js.append(globalContext.getEvaluateName());
				js.append("(");
				if (!(libraryOperation instanceof LibrarySimpleOperation)) {		// XXX reify as true CG parameters/arguments
					//					js.append(getValueName(localContext.getEvaluatorParameter(cgOperationCallExp)));
					js.append(globalContext.getExecutorName());
					js.append(", ");
					if (!(libraryOperation instanceof LibraryUntypedOperation)) {
						//						CGTypeVariable typeVariable = localContext.getTypeVariable(resultType);
						js.appendValueName(resultType);
						js.append(", ");
					}
				}
		//		if (source.isNull()) {
		//			js.append("(Object)");
		//		}
		//		js.appendValueName(source);
				boolean needsComma = false;
				for (@SuppressWarnings("null")@NonNull CGValuedElement cgArgument : cgArguments) {
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

	public Method getJavaMethod(@NonNull CGOperation cgOperation, @NonNull LibraryOperation libraryOperation) {
		try {
			Class<? extends LibraryOperation> implementationClass = libraryOperation.getClass();
			List<CGParameter> cgParameters = cgOperation.getParameters();
			Class<?>[] arguments = new Class<?>[cgParameters.size()];
			int i = 0;
			for (CGParameter cgParameter : cgParameters) {
				arguments[i++] = Object.class;
			}
		/*	int i = 0;
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
			} */
			Method method = implementationClass.getMethod(JavaConstants.EVALUATE_NAME, arguments);
			return method;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean isBoxed() {
		return true;
	}

	@Override
	public boolean isStatic(@NonNull CGOperation cgOperation) {
		return true;
	}
}
