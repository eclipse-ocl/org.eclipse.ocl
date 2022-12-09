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

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.codegen.analyzer.BoxingAnalyzer;
import org.eclipse.ocl.examples.codegen.analyzer.CodeGenAnalyzer;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcoreOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGEcorePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGExecutorProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGModelFactory;
import org.eclipse.ocl.examples.codegen.cgmodel.CGNavigationCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGOppositePropertyCallExp;
import org.eclipse.ocl.examples.codegen.cgmodel.CGProperty;
import org.eclipse.ocl.examples.codegen.cgmodel.CGTypeId;
import org.eclipse.ocl.examples.codegen.cgmodel.CGValuedElement;
import org.eclipse.ocl.examples.codegen.generator.CodeGenerator;
import org.eclipse.ocl.examples.codegen.generator.GenModelException;
import org.eclipse.ocl.examples.codegen.generator.TypeDescriptor;
import org.eclipse.ocl.examples.codegen.java.CG2JavaVisitor;
import org.eclipse.ocl.examples.codegen.java.JavaCodeGenerator;
import org.eclipse.ocl.examples.codegen.java.JavaConstants;
import org.eclipse.ocl.examples.codegen.java.JavaStream;
import org.eclipse.ocl.examples.codegen.utilities.CGUtil;
import org.eclipse.ocl.pivot.NavigationCallExp;
import org.eclipse.ocl.pivot.OppositePropertyCallExp;
import org.eclipse.ocl.pivot.Property;
import org.eclipse.ocl.pivot.ids.ElementId;
import org.eclipse.ocl.pivot.internal.library.CompositionProperty;
import org.eclipse.ocl.pivot.internal.library.ImplicitNonCompositionProperty;
import org.eclipse.ocl.pivot.library.LibraryProperty;
import org.eclipse.ocl.pivot.oclstdlib.OCLstdlibPackage;
import org.eclipse.ocl.pivot.utilities.ClassUtil;

/**
 *  NativePropertyCallingConvention defines the support for the call of a property realized by native code.
 *   *  </br>
 *  e.g. as XXXTables.FOREIGN_qualified_class.FC_class.INSTANCE.evaluate(executor, arguments)
 */
public class EcoreOppositePropertyCallingConvention extends AbstractPropertyCallingConvention
{
	private static final @NonNull EcoreOppositePropertyCallingConvention INSTANCE = new EcoreOppositePropertyCallingConvention();

	public static @NonNull PropertyCallingConvention getInstance(@NonNull Property asProperty) {
		INSTANCE.logInstance(asProperty);
		return INSTANCE;
	}

	protected void appendEcoreGet(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGValuedElement cgSource, @NonNull Property asProperty) {
		CGTypeId cgTypeId = cg2javaVisitor.getAnalyzer().getCGTypeId(asProperty.getOwningClass().getTypeId());
		ElementId elementId = ClassUtil.nonNullState(cgTypeId.getElementId());
		JavaCodeGenerator codeGenerator = cg2javaVisitor.getCodeGenerator();
		TypeDescriptor requiredTypeDescriptor = codeGenerator.getUnboxedDescriptor(elementId);
		//		EStructuralFeature eStructuralFeature = ClassUtil.nonNullState(cgPropertyCallExp.getEStructuralFeature());
		EStructuralFeature eStructuralFeature = ClassUtil.nonNullState(cg2javaVisitor.getESObject(asProperty));
		String getAccessor;
		if (eStructuralFeature == OCLstdlibPackage.Literals.OCL_ELEMENT__OCL_CONTAINER) {
			getAccessor = JavaConstants.E_CONTAINER_NAME;
		}
		else {
			getAccessor = codeGenerator.getGenModelHelper().getGetAccessor(eStructuralFeature);
		}
		Class<?> requiredJavaClass = requiredTypeDescriptor.hasJavaClass();
		Method leastDerivedMethod = requiredJavaClass != null ? codeGenerator.getLeastDerivedMethod(requiredJavaClass, getAccessor) : null;
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

	@Override
	public @NonNull CGValuedElement createCGNavigationCallExp(@NonNull CodeGenAnalyzer analyzer, @NonNull CGProperty cgProperty,
			@NonNull LibraryProperty libraryProperty, @Nullable CGValuedElement cgSource, @NonNull NavigationCallExp asNavigationCallExp) {
		OppositePropertyCallExp asOppositePropertyCallExp = (OppositePropertyCallExp)asNavigationCallExp;
		CodeGenerator codeGenerator = analyzer.getCodeGenerator();
		Property asProperty = CGUtil.getAST(cgProperty);
		boolean isRequired = asProperty.isIsRequired();
		CGOppositePropertyCallExp cgPropertyCallExp = null;
		EStructuralFeature eStructuralFeature = null;
		assert (libraryProperty instanceof CompositionProperty) || (libraryProperty instanceof ImplicitNonCompositionProperty);
		eStructuralFeature = (EStructuralFeature) asProperty.getESObject();
		if (eStructuralFeature != null) {
			try {
				codeGenerator.getGenModelHelper().getGetAccessor(eStructuralFeature);
				isRequired = asProperty.isIsRequired();
			} catch (GenModelException e) {
				eStructuralFeature = null;
				codeGenerator.addProblem(e);		// FIXME drop through to better default
			}
		}
		if (eStructuralFeature != null) {
			CGEcoreOppositePropertyCallExp cgEcorePropertyCallExp = CGModelFactory.eINSTANCE.createCGEcoreOppositePropertyCallExp();
			cgEcorePropertyCallExp.setEStructuralFeature(eStructuralFeature);
			Boolean ecoreIsRequired = codeGenerator.isNonNull(asProperty);
			if (ecoreIsRequired != null) {
				isRequired = ecoreIsRequired;
			}
			cgPropertyCallExp = cgEcorePropertyCallExp;
		}
		else {
			CGExecutorOppositePropertyCallExp cgExecutorPropertyCallExp = CGModelFactory.eINSTANCE.createCGExecutorOppositePropertyCallExp();
			CGExecutorProperty cgExecutorProperty = analyzer.createExecutorOppositeProperty(asProperty);
			cgExecutorPropertyCallExp.setExecutorProperty(cgExecutorProperty);
			cgExecutorPropertyCallExp.getOwns().add(cgExecutorProperty);
			cgPropertyCallExp = cgExecutorPropertyCallExp;
		}
		cgPropertyCallExp.setReferredProperty(cgProperty);
		cgPropertyCallExp.setAsProperty(asProperty);
		cgPropertyCallExp.setAst(asOppositePropertyCallExp);
		cgPropertyCallExp.setTypeId(analyzer.getCGTypeId(asOppositePropertyCallExp.getTypeId()));
		cgPropertyCallExp.setRequired(isRequired); // || codeGenerator.isPrimitive(cgPropertyCallExp));
		cgPropertyCallExp.setSource(cgSource);
		return cgPropertyCallExp;
	}

	@Override
	public boolean generateJavaCall(@NonNull CG2JavaVisitor cg2javaVisitor, @NonNull JavaStream js, @NonNull CGNavigationCallExp cgPropertyCallExp) {
		CGEcorePropertyCallExp cgEcorePropertyCallExp = (CGEcorePropertyCallExp) cgPropertyCallExp;
		Property asProperty = ClassUtil.nonNullState(cgPropertyCallExp.getAsProperty());
		assert cg2javaVisitor.getESObject(asProperty) == ClassUtil.nonNullState(cgEcorePropertyCallExp.getEStructuralFeature());
		//
		CGValuedElement source = cg2javaVisitor.getExpression(cgPropertyCallExp.getSource());
		if (!js.appendLocalStatements(source)) {
			return false;
		}
		//
		Boolean ecoreIsRequired = cg2javaVisitor.getCodeGenerator().isNonNull(asProperty);
		boolean isPrimitive = js.isPrimitive(cgPropertyCallExp);
		if (!isPrimitive) cg2javaVisitor.appendSuppressWarningsNull(cgPropertyCallExp, ecoreIsRequired);
		//		js.append("/* " + ecoreIsRequired + " " + isRequired + " */\n");
		js.appendDeclaration(cgPropertyCallExp);
		js.append(" = ");
		appendEcoreGet(cg2javaVisitor, js, source, asProperty);
		js.append(";\n");
		return true;
	}

	@Override
	public boolean needsGeneration() {
		return false;
	}

	@Override
	protected void rewriteWithResultBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		CGEcoreOppositePropertyCallExp cgEcoreOppositePropertyCallExp = (CGEcoreOppositePropertyCallExp) cgNavigationCallExp;
		if (cgEcoreOppositePropertyCallExp.getEStructuralFeature().isMany()) {
			boxingAnalyzer.rewriteAsAssertNonNulled(cgEcoreOppositePropertyCallExp);
		}
	}

	@Override
	protected void rewriteWithSourceBoxing(@NonNull BoxingAnalyzer boxingAnalyzer, @NonNull CGNavigationCallExp cgNavigationCallExp) {
		CGEcoreOppositePropertyCallExp cgEcoreOppositePropertyCallExp = (CGEcoreOppositePropertyCallExp) cgNavigationCallExp;
		boxingAnalyzer.rewriteAsEcore(cgEcoreOppositePropertyCallExp.getSource(), cgEcoreOppositePropertyCallExp.getEStructuralFeature().getEType());
	}
}
