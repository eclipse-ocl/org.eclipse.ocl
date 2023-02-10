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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGBodiedProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositeProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorPropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.cgmodel.CGVariable;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.java.JavaStream.SubStream;
import org.eclipse.ocl.examples.codegen.naming.ExecutableNameManager;
import org.eclipse.ocl.examples.codegen.naming.GlobalNameManager;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.internal.library.ExtensionProperty;
import org.eclipse.ocl.pivot.internal.library.ImplicitNonCompositionProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  ForeignPropertyCallingConvention defines the support for the call of a property realized by an
 *  implementation in the *Tables class.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class ExecutorOppositePropertyCallingConvention extends AbstractPropertyCallingConvention
{
	private static final @NonNull ExecutorOppositePropertyCallingConvention INSTANCE = new ExecutorOppositePropertyCallingConvention();

	public static @NonNull ExecutorOppositePropertyCallingConvention getInstance(@NonNull Property asProperty) {
		INSTANCE.logInstance(asProperty);
		return INSTANCE;
	}

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asNavigationCallExp) {
		CodeGenerator codeGenerator = analyzer.getCodeGenerator();
		OppositePropertyCallExp asOppositePropertyCallExp = (OppositePropertyCallExp)asNavigationCallExp;
		Property asOppositeProperty = ClassUtil.nonNullModel(asOppositePropertyCallExp.getReferredProperty());
		Property asProperty = ClassUtil.nonNullModel(asOppositeProperty.getOpposite());
		boolean isRequired = asProperty.isIsRequired();
		LibraryProperty libraryProperty2 = codeGenerator.getEnvironmentFactory().getMetamodelManager().getImplementation(asOppositePropertyCallExp, null, asProperty);
		assert libraryProperty2 == libraryProperty;				// XXX
		assert /*(libraryProperty instanceof CompositionProperty) ||*/ (libraryProperty instanceof ImplicitNonCompositionProperty) || (libraryProperty instanceof ExtensionProperty);
		CGExecutorOppositePropertyCallExp cgPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorOppositePropertyCallExp();
		CGExecutorProperty cgExecutorProperty = analyzer.createExecutorOppositeProperty(asProperty);
		cgExecutorProperty.setCallingConvention(this);
		cgPropertyCallExp.setExecutorProperty(cgExecutorProperty);
		analyzer.addReferencedExtraChild(cgPropertyCallExp, cgExecutorProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		analyzer.initAst(cgPropertyCallExp, asOppositePropertyCallExp, true);
		cgPropertyCallExp.setRequired(isRequired);
		cgPropertyCallExp.setSource(cgSource);
		cgPropertyCallExp.setReferredProperty(cgProperty);
		return cgPropertyCallExp;
	}

/*	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull AS2CGVisitor as2cgVisitor, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asPropertyCallExp) {
		CodeGenerator codeGenerator = as2cgVisitor.getCodeGenerator();
		CodeGenAnalyzer analyzer = as2cgVisitor.getAnalyzer();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		CGExecutorPropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorPropertyCallExp();
		CGExecutorProperty cgExecutorProperty = analyzer.createExecutorProperty(asProperty);
		cgExecutorPropertyCallExp.setExecutorProperty(cgExecutorProperty);
		cgExecutorPropertyCallExp.getOwns().add(cgExecutorProperty);
		cgExecutorPropertyCallExp = cgExecutorPropertyCallExp;
		cgExecutorPropertyCallExp.setCgProperty(cgProperty);
		cgExecutorPropertyCallExp.setReferredProperty(asProperty);
		cgExecutorPropertyCallExp.setAst(asPropertyCallExp);
		cgExecutorPropertyCallExp.setTypeId(analyzer.getTypeId(asPropertyCallExp.getTypeId()));
		cgExecutorPropertyCallExp.setRequired(isRequired || codeGenerator.isPrimitive(cgExecutorPropertyCallExp));
		cgExecutorPropertyCallExp.setSource(cgSource);
		return cgExecutorPropertyCallExp;
	} */

	@Override
	public boolean generateEcoreBody(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
		CGValuedElement cgBody = ((CGBodiedProperty)cgProperty).getBody();
		assert cgBody == null;
		return false;
	}

	protected boolean generateForwardJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGExecutorPropertyCallExp cgPropertyCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		CGValuedElement asSource = cgPropertyCallExp.getSource();
		CGValuedElement cgSource = asSource != null ? cg2javaVisitor.getExpression(asSource) : null;
		if ((cgSource != null) && !js.appendLocalStatements(cgSource)) {
			return false;
		}
		GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
		ExecutableNameManager executableNameManager = globalNameManager.useRootExecutableNameManager(cgPropertyCallExp);
		CGVariable cgExecutorVariable = executableNameManager.lazyGetExecutorVariable();
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
		TypeDescriptor typeDescriptor = codeGenerator.getTypeDescriptor(cgPropertyCallExp);
		JavaStream.SubStream castBody = new JavaStream.SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgPropertyCallExp.getReferredProperty());
				js.append(".");
				js.appendName(globalNameManager.getEvaluateName());
				js.append("(");
				js.appendValueName(cgExecutorVariable);
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

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		if (cgPropertyCallExp instanceof CGExecutorPropertyCallExp) {
			return generateForwardJavaCall(cg2javaVisitor, (CGExecutorPropertyCallExp)cgPropertyCallExp);
		}
		else {
			return generateOppositeJavaCall(cg2javaVisitor, (CGExecutorOppositePropertyCallExp)cgPropertyCallExp);
		}
	}

	@Override
	public boolean generateJavaDeclaration(	@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGProperty cgProperty) {
		assert cgProperty instanceof CGExecutorOppositeProperty;
		return generateOppositeJavaDeclaration(cg2javaVisitor, (CGExecutorOppositeProperty)cgProperty);
	}

	protected boolean generateOppositeJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGExecutorOppositePropertyCallExp cgPropertyCallExp) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		GlobalNameManager globalNameManager = cg2javaVisitor.getCodeGenerator().getGlobalNameManager();
		CGValuedElement source = cg2javaVisitor.getExpression(cgPropertyCallExp.getSource());
		//
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		ExecutableNameManager executableNameManager = globalNameManager.useRootExecutableNameManager(cgPropertyCallExp);
		CGVariable cgExecutorVariable = executableNameManager.lazyGetExecutorVariable();
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		SubStream castBody = new SubStream() {
			@Override
			public void append() {
				js.appendReferenceTo(cgPropertyCallExp.getExecutorProperty());
				js.append(".");
				js.appendName(globalNameManager.getEvaluateName());
				js.append("(");
				js.appendValueName(cgExecutorVariable);
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

	private boolean generateOppositeJavaDeclaration(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull CGExecutorOppositeProperty cgProperty) {
		JavaStream js = cg2javaVisitor.getJavaStream();
		Property asProperty = (Property) cgProperty.getAst();
		Property asOppositeProperty = asProperty.getOpposite();
		if (JavaCodeGenerator.CALLING_CONVENTION_COMMENTS.isActive()) {
			js.append("// " + cgProperty.getCallingConvention() + "\n");
		}
		js.appendDeclaration(cgProperty);
		js.append(" = new ");
		js.appendClassReference(null, cgProperty);
		js.append("(");
		js.appendIdReference(asOppositeProperty.getPropertyId());
		js.append(");\n");
		return true;
	}

	@Override
	protected void rewriteWithResultBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		CGTypedElement cgParent = (CGTypedElement)cgNavigationCallExp.getParent();
		if (cgParent != null) {
			boxingAnalyzer.rewriteAsBoxed(cgNavigationCallExp);
		}
	}

	@Override
	protected void rewriteWithSourceBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		CGValuedElement cgSource = cgNavigationCallExp.getSource();
		if (cgSource != null) {
			boxingAnalyzer.rewriteAsUnboxed(cgSource);
		}
	}
}
