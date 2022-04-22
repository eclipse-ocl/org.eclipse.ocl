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
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaGlobalContext;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.pivot.Property;

/**
 *  ForeignPropertyCallingConvention defines the support for the call of a property realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ExecutorPropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull ExecutorPropertyCallingConvention INSTANCE = new ExecutorPropertyCallingConvention();

	protected boolean generateForwardJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGExecutorPropertyCallExp cgPropertyCallExp) {
		CGValuedElement asSource = cgPropertyCallExp.getSource();
		CGValuedElement cgSource = asSource != null ? cg2javaVisitor.getExpression(asSource) : null;
		if ((cgSource != null) && !js.appendLocalStatements(cgSource)) {
			return false;
		}
		//
		//	CGExecutorProperty cgExecutorProperty = ClassUtil.nonNullState(cgPropertyCallExp.getExecutorProperty());
		Boolean ecoreIsRequired = Boolean.FALSE;						// CP properties evaluate is nullable -- FIXME compute rather than assume
		//	boolean isPrimitive = js.isPrimitive(cgPropertyCallExp);
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		Boolean isRequired = codeGenerator.isRequired(cgPropertyCallExp);
		if ((isRequired == Boolean.TRUE) && (ecoreIsRequired != Boolean.TRUE)) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		final JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = codeGenerator.getGlobalContext();
		TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(cgPropertyCallExp);
		JavaStream.SubStream castBody = new JavaStream.SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgPropertyCallExp.getExecutorProperty());
				js.append(".");
				js.append(globalContext.getEvaluateName());
				js.append("(");
				//		js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(globalContext.getExecutorName());
				js.append(", ");
				js.appendIdReference(cgPropertyCallExp.getASTypeId());
				js.append(", ");
				js.appendValueName(cgSource);
				js.append(")");
			}
		};
		typeDescriptor.appendCast(js, isRequired, null, castBody);
		js.append(";\n");
		return true;
	}

	protected boolean generateForwardJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGExecutorNavigationProperty cgProperty) {
		js.appendDeclaration(cgProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgProperty);
		js.append("(");
		js.appendIdReference(cgProperty.getUnderlyingPropertyId().getElementId());
		js.append(");\n");
		return true;
	}

//	@Override
	public boolean generateJavaDeclaration(	@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGExecutorProperty cgProperty) {
		if (cgProperty instanceof CGExecutorNavigationProperty) {
			return generateForwardJavaDeclaration(cg2javaVisitor, js, (CGExecutorNavigationProperty)cgProperty);
		}
		else {
			return generateOppositeJavaDeclaration(cg2javaVisitor, js, (CGExecutorOppositeProperty)cgProperty);
		}
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		if (cgPropertyCallExp instanceof CGExecutorPropertyCallExp) {
			return generateForwardJavaCall(cg2javaVisitor, js, (CGExecutorPropertyCallExp)cgPropertyCallExp);
		}
		else {
			return generateOppositeJavaCall(cg2javaVisitor, js, (CGExecutorOppositePropertyCallExp)cgPropertyCallExp);
		}
	}

	protected boolean generateOppositeJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGExecutorOppositePropertyCallExp cgPropertyCallExp) {
		CGValuedElement source = cg2javaVisitor.getExpression(cgPropertyCallExp.getSource());
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		final JavaGlobalContext<@NonNull ? extends JavaCodeGenerator> globalContext = cg2javaVisitor.getCodeGenerator().getGlobalContext();
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgPropertyCallExp.getExecutorProperty());
				js.append(".");
				js.append(globalContext.getEvaluateName());
				js.append("(");
				//		js.append(getValueName(localContext.getEvaluatorParameter(cgPropertyCallExp)));
				js.append(globalContext.getExecutorName());
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

	protected boolean generateOppositeJavaDeclaration(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGExecutorOppositeProperty cgProperty) {
		Property asProperty = (Property) cgProperty.getAst();
		Property asOppositeProperty = asProperty.getOpposite();
		js.appendDeclaration(cgProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgProperty);
		js.append("(");
		js.appendIdReference(asOppositeProperty.getPropertyId());
		js.append(");\n");
		return true;
	}

	@Override
	public boolean isBoxed() {
		return true;
	}
}
