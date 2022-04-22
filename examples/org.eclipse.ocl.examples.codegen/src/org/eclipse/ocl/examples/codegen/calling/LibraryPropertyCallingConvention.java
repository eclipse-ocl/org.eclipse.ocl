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

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLibraryPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  NativePropertyCallingConvention defines the support for the call of a property realized by native code.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class LibraryPropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull LibraryPropertyCallingConvention INSTANCE = new LibraryPropertyCallingConvention();

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		CGLibraryPropertyCallExp cgLibraryPropertyCallExp = (CGLibraryPropertyCallExp) cgPropertyCallExp;
		CGValuedElement source = cg2javaVisitor.getExpression(cgPropertyCallExp.getSource());
		LibraryProperty libraryProperty = ClassUtil.nonNullState(cgLibraryPropertyCallExp.getLibraryProperty());
		Method actualMethod = libraryProperty.getEvaluateMethod(CGUtil.getReferredProperty(cgPropertyCallExp));
		Class<?> actualReturnClass = actualMethod.getReturnType();
		boolean actualIsNonNull = codeGenerator.getIsNonNull(actualMethod) == Boolean.TRUE;
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
		final JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = codeGenerator.getGlobalContext();
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendClassReference(null, libraryProperty.getClass());
				//		CGOperation cgOperation = ClassUtil.nonNullState(CGUtils.getContainingOperation(cgPropertyCallExp));
				js.append(".");
				js.append(globalContext.getInstanceName());
				js.append(".");
				js.append(globalContext.getEvaluateName());
				js.append("(");
				//		if (!(libraryOperation instanceof LibrarySimpleOperation)) {
				//			js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(globalContext.getExecutorName());
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
	public boolean isBoxed() {
		return true;
	}
}
