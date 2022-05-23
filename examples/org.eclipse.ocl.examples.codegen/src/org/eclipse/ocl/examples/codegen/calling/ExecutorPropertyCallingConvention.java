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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.AS2CGVisitor;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.GlobalNameManager;
import org.eclipse.ocl.examples.codegen.analyzer.NameResolution;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.PropertyId;
import org.eclipse.ocl.pivot.internal.library.UnboxedExplicitNavigationProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;

/**
 *  ExecutorPropertyCallingConvention defines the support for the call of a (forward) property realized by an
 *  a self-sensitive UnboxedExplicitNavigationProperty implementation helper.
 *   *  </br>
 *  e.g. as IMPPROPid_child1.evaluate(executor, arguments)
 */
public class ExecutorPropertyCallingConvention extends AbstractPropertyCallingConvention
{
	public static final @NonNull ExecutorPropertyCallingConvention INSTANCE = new ExecutorPropertyCallingConvention();

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull AS2CGVisitor as2cgVisitor, @NonNull Property asProperty) {
	//	return as2cgVisitor.getAnalyzer().getCGExecutorNavigationProperty(asProperty, this);
		return CGModelFactory.eINSTANCE.createCGExecutorNavigationProperty();
	}

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		Property asProperty = CGUtil.getAST(cgProperty);
		PropertyId propertyId = asProperty.getPropertyId();
		CGElementId cgPropertyId = analyzer.getCGElementId(propertyId);
		analyzer.addGlobal(cgPropertyId);
		boolean isRequired = asProperty.isIsRequired();
		Method jMethod = UnboxedExplicitNavigationProperty.CREATE_METHOD;
		NativeOperationCallingConvention nativeOperationCallingConvention = NativeOperationCallingConvention.INSTANCE;
		CGNativeOperationCallExp cgNativeOperationCallExp = analyzer.createCGNativeOperationCallExp(jMethod, nativeOperationCallingConvention);
		cgNativeOperationCallExp.getCgArguments().add(analyzer.createCGConstantExp(cgPropertyId));
		cgNativeOperationCallExp.setTypeId(analyzer.getCGTypeId(JavaConstants.UNBOXED_EXPLICIT_NAVIGATION_PROPERTY_TYPE_ID));
		NameResolution nameResolution = cgProperty.getNameResolution();
		nameResolution.addCGElement(cgNativeOperationCallExp);
		CGFinalVariable cgVariable = as2cgVisitor.createCGFinalVariable(cgNativeOperationCallExp);
		CGExecutorPropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorPropertyCallExp();
		cgExecutorPropertyCallExp.setCgProperty(cgProperty);
		cgExecutorPropertyCallExp.setReferredProperty(asProperty);
		cgExecutorPropertyCallExp.setAst(asPropertyCallExp);
		cgExecutorPropertyCallExp.setTypeId(analyzer.getCGTypeId(asPropertyCallExp.getTypeId()));
		cgExecutorPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgExecutorPropertyCallExp));
		cgExecutorPropertyCallExp.setSource(cgSource);
		cgExecutorPropertyCallExp.setCgArgument(analyzer.createCGVariableExp(cgVariable));
		return analyzer.createCGLetExp(cgVariable, cgExecutorPropertyCallExp);
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		assert cgPropertyCallExp instanceof CGExecutorPropertyCallExp;
		CGExecutorPropertyCallExp cgExecutorPropertyCallExp = (CGExecutorPropertyCallExp)cgPropertyCallExp;
		CGValuedElement asSource = cgExecutorPropertyCallExp.getSource();
		CGValuedElement cgSource = asSource != null ? cg2javaVisitor.getExpression(asSource) : null;
		if ((cgSource != null) && !js.appendLocalStatements(cgSource)) {
			return false;
		}
		//
		//	CGExecutorProperty cgExecutorProperty = ClassUtil.nonNullState(cgPropertyCallExp.getExecutorProperty());
		Boolean ecoreIsRequired = Boolean.FALSE;						// CP properties evaluate is nullable -- FIXME compute rather than assume
		//	boolean isPrimitive = js.isPrimitive(cgPropertyCallExp);
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
		Boolean isRequired = codeGenerator.isRequired(cgExecutorPropertyCallExp);
		if ((isRequired == Boolean.TRUE) && (ecoreIsRequired != Boolean.TRUE)) {
			js.appendSuppressWarningsNull(true);
		}
		js.appendDeclaration(cgExecutorPropertyCallExp);
		js.append(" = ");
		TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(cgExecutorPropertyCallExp);
		JavaStream.SubStream castBody = new JavaStream.SubStream() {
			@Override
			public void append() {
				if (cgExecutorPropertyCallExp.getCgArgument() != null) {
					js.appendValueName(cgExecutorPropertyCallExp.getCgArgument());
				}
				else {
					js.appendReferenceTo(cgExecutorPropertyCallExp.getExecutorProperty());
				}
				js.append(".");
				js.append(globalNameManager.getEvaluateName());
				js.append("(");
				js.append(globalNameManager.getExecutorName());
				js.append(", ");
				js.appendIdReference(cgExecutorPropertyCallExp.getASTypeId());
				js.append(", ");
				js.appendValueName(cgSource);
				js.append(")");
			}
		};
		typeDescriptor.appendCast(js, isRequired, null, castBody);
		js.append(";\n");
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(	@NonNull CG2JavaVisitor<?> cg2javaVisitor, @NonNull JavaStream js, @NonNull CGProperty cgProperty) {
		assert cgProperty instanceof CGExecutorNavigationProperty;
		CGExecutorNavigationProperty cgExecutorNavigationProperty = (CGExecutorNavigationProperty)cgProperty;
		js.appendDeclaration(cgExecutorNavigationProperty);
		js.append(" = new ");
		js.appendClassReference(null, UnboxedExplicitNavigationProperty.class);
		js.append("(");
	//	js.appendIdReference(cgExecutorNavigationProperty.getUnderlyingPropertyId().getElementId());
		js.appendValueName(cgProperty.getTypeId());
		js.append(");\n");
		return true;
	}

	@Override
	public boolean needsGeneration() {
		return false;				// XXX
	}

	@Override
	protected void rewriteWithResultBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		CGExecutorPropertyCallExp cgExecutorPropertyCallExp = (CGExecutorPropertyCallExp) cgNavigationCallExp;
		CGTypedElement cgParent = (CGTypedElement) cgExecutorPropertyCallExp.getParent();
		if (cgParent != null) {
			boxingAnalyzer.rewriteAsBoxed(cgExecutorPropertyCallExp);
		}
	}

	@Override
	protected void rewriteWithSourceBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		CGExecutorPropertyCallExp cgExecutorPropertyCallExp = (CGExecutorPropertyCallExp) cgNavigationCallExp;
		boxingAnalyzer.rewriteAsUnboxed(cgExecutorPropertyCallExp.getSource());				// XXX boxed ???
	}
}
