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
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.operation.LibraryOperationHandler;
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
		init(as2cgVisitor, cgOperationCallExp, asOperationCallExp, cgOperation, cgSource, isRequired);
		return cgOperationCallExp;
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
		final CGValuedElement source = cg2JavaVisitor.getExpression(cgOperationCallExp.getSource());
		final List<CGValuedElement> arguments = cgOperationCallExp.getArguments();
		Method actualMethod = cg2JavaVisitor.getJavaMethod(libraryOperation, arguments.size());
		Class<?> actualReturnClass = actualMethod != null ? actualMethod.getReturnType() : null;
		boolean actualIsNonNull = (actualMethod != null) && (cg2JavaVisitor.getCodeGenerator().getIsNonNull(actualMethod) == Boolean.TRUE);
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
				JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = cg2JavaVisitor.getCodeGenerator().getGlobalContext();
				js.appendClassReference(null, libraryOperation.getClass());
				js.append(".");
				js.append(globalContext.getInstanceName());
				js.append(".");
				js.append(globalContext.getEvaluateName());
				js.append("(");
				if (!(libraryOperation instanceof LibrarySimpleOperation)) {
					//					js.append(getValueName(localContext.getEvaluatorParameter(cgOperationCallExp)));
					js.append(globalContext.getExecutorName());
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
	public boolean isBoxed() {
		return true;
	}
}
