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
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGElementId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorNavigationProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGFinalVariable;
import org.eclipse.ocl.examples.codegen.cgmodel.CGLetExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNativeOperationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
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
	private static final @NonNull ExecutorPropertyCallingConvention INSTANCE = new ExecutorPropertyCallingConvention();

	public static @NonNull ExecutorPropertyCallingConvention getInstance(@NonNull Property asProperty) {
		INSTANCE.logInstance(asProperty);
		return INSTANCE;
	}

	@Override
	public @NonNull CGProperty createCGProperty(@NonNull CodeGenAnalyzer analyzer, @NonNull Property asProperty) {
		return CGModelFactory.eINSTANCE.createCGExecutorNavigationProperty();
	}

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		PropertyId propertyId = asProperty.getPropertyId();
		CGElementId cgPropertyId = analyzer.getCGElementId(propertyId);
		analyzer.addGlobal(cgPropertyId);
		boolean isRequired = asProperty.isIsRequired();
		Method jMethod = UnboxedExplicitNavigationProperty.CREATE_METHOD;
		OperationCallingConvention supportOperationCallingConvention = SupportOperationCallingConvention.getInstance(jMethod);
		CGNativeOperationCallExp cgNativeOperationCallExp = analyzer.createCGNativeOperationCallExp(jMethod, supportOperationCallingConvention);
//		cgNativeOperationCallExp.setAst(asPropertyCallExp);
		cgNativeOperationCallExp.getArguments().add(analyzer.createCGConstantExp(cgPropertyId));
		cgNativeOperationCallExp.setTypeId(analyzer.getCGTypeId(JavaConstants.UNBOXED_EXPLICIT_NAVIGATION_PROPERTY_TYPE_ID));
		CGFinalVariable cgVariable = analyzer.useExecutableNameManager(asPropertyCallExp).createCGVariable(cgNativeOperationCallExp);
		CGExecutorPropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorPropertyCallExp();
		cgExecutorPropertyCallExp.setReferredProperty(cgProperty);
		cgExecutorPropertyCallExp.setAsProperty(asProperty);
		analyzer.initAst(cgExecutorPropertyCallExp, asPropertyCallExp, false);
		cgExecutorPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgExecutorPropertyCallExp));
		cgExecutorPropertyCallExp.setSource(cgSource);
		cgExecutorPropertyCallExp.setCgArgument(analyzer.createCGVariableExp(cgVariable));
		CGLetExp cgLetExp = analyzer.createCGLetExp(cgVariable, cgExecutorPropertyCallExp);
		analyzer.initAst(cgLetExp, asPropertyCallExp, true);
		return cgLetExp;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		assert cgPropertyCallExp instanceof CGExecutorPropertyCallExp;
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGExecutorPropertyCallExp cgExecutorPropertyCallExp = (CGExecutorPropertyCallExp)cgPropertyCallExp;
		CGValuedElement cgRawSource = cgPropertyCallExp.getSource();
		CGValuedElement cgSource = cgRawSource != null ? cg2javaVisitor.getExpression(cgRawSource) : null;
		if ((cgSource != null) && !js.appendLocalStatements(cgSource)) {
			return false;
		}
		GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
		ExecutableNameManager executableNameManager = globalNameManager.useRootExecutableNameManager(cgPropertyCallExp);
		CGVariable cgExecutorVariable = executableNameManager.lazyGetExecutorVariable();
		JavaStream.SubStream sourceStream = new JavaStream.SubStream() {
			@Override
			public void append() {
				JavaCodeGenerator codeGenerator = js.getCodeGenerator();
				GlobalNameManager globalNameManager = codeGenerator.getGlobalNameManager();
				if (cgExecutorPropertyCallExp.getCgArgument() != null) {
					js.appendValueName(cgExecutorPropertyCallExp.getCgArgument());
				}
				else {
					js.appendReferenceTo(CGUtil.getReferredProperty(cgPropertyCallExp));
				}
				js.append(".");
				js.appendName(globalNameManager.getEvaluateName());
				js.append("(");
				js.appendValueName(cgExecutorVariable);
				js.append(", ");
				js.appendIdReference(cgExecutorPropertyCallExp.getASTypeId());
				js.append(", ");
				js.appendValueName(cgSource);
				js.append(")");
			}
		};
		Boolean evaluateIsRequired = Boolean.FALSE;
		Class<?> evaluateClass = Object.class;
		js.appendAssignWithCast(cgPropertyCallExp, evaluateIsRequired, evaluateClass, sourceStream);
		return true;
	}

	@Override
	public boolean generateJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
		assert cgProperty instanceof CGExecutorNavigationProperty;
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGExecutorNavigationProperty cgExecutorNavigationProperty = (CGExecutorNavigationProperty)cgProperty;
		js.append("// " + cgProperty.getCallingConvention() + "\n");
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
